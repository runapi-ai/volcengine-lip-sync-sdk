import pytest

from runapi.core import config
from runapi.core.errors import AuthenticationError, ValidationError
from runapi.volcengine_lip_sync import VolcengineLipSyncClient
from runapi.volcengine_lip_sync.resources.lip_sync_video import LipSyncVideo
from runapi.volcengine_lip_sync.types import CompletedLipSyncVideoResponse, LipSyncVideoResponse


class FakeHttp:
    """Records (method, path, body) and replays preset responses by call order."""

    def __init__(self, *responses):
        self._responses = list(responses)
        self.calls = []

    def request(self, method, path, body=None, options=None):
        self.calls.append((method, path, body))
        if self._responses:
            return self._responses.pop(0)
        return {"id": "task_1", "status": "pending"}


@pytest.fixture(autouse=True)
def reset_config(monkeypatch):
    monkeypatch.delenv("RUNAPI_API_KEY", raising=False)
    monkeypatch.setattr(config, "api_key", None)
    yield


def test_accepts_api_key_parameter():
    assert isinstance(VolcengineLipSyncClient(api_key="param-key", http_client=FakeHttp()), VolcengineLipSyncClient)


def test_falls_back_to_global(monkeypatch):
    monkeypatch.setattr(config, "api_key", "global-key")
    assert isinstance(VolcengineLipSyncClient(http_client=FakeHttp()), VolcengineLipSyncClient)


def test_falls_back_to_env(monkeypatch):
    monkeypatch.setenv("RUNAPI_API_KEY", "env-key")
    assert isinstance(VolcengineLipSyncClient(http_client=FakeHttp()), VolcengineLipSyncClient)


def test_raises_without_api_key():
    with pytest.raises(AuthenticationError, match="API key is required"):
        VolcengineLipSyncClient()


def test_uses_injected_http_client():
    fake = FakeHttp()
    client = VolcengineLipSyncClient(api_key="k", http_client=fake)
    assert client.lip_sync_video._http is fake


def test_exposes_resource_accessors():
    client = VolcengineLipSyncClient(api_key="k", http_client=FakeHttp())
    assert isinstance(client.lip_sync_video, LipSyncVideo)


def test_create_posts_compacted_body():
    fake = FakeHttp({"id": "t1", "status": "pending"})
    client = VolcengineLipSyncClient(api_key="k", http_client=fake)
    result = client.lip_sync_video.create(
        model="volcengine-lip-sync",
        mode="lite",
        source_video_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
        source_audio_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3",
        callback_url=None,
        enable_vocal_separation=False,
    )
    assert fake.calls == [
        (
            "post",
            "/api/v1/volcengine_lip_sync/lip_sync_video",
            {
                "model": "volcengine-lip-sync",
                "mode": "lite",
                "source_video_url": "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
                "source_audio_url": "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3",
                "enable_vocal_separation": False,
            },
        ),
    ]
    assert isinstance(result, LipSyncVideoResponse)
    assert result.id == "t1"


def test_get_fetches_by_id():
    fake = FakeHttp({"id": "t1", "status": "processing"})
    client = VolcengineLipSyncClient(api_key="k", http_client=fake)
    client.lip_sync_video.get("t1")
    assert fake.calls == [("get", "/api/v1/volcengine_lip_sync/lip_sync_video/t1", None)]


def test_run_polls_and_narrows_completed_type():
    fake = FakeHttp(
        {"id": "t1", "status": "pending"},
        {
            "id": "t1",
            "status": "completed",
            "videos": [{"url": "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-result-adam.mp4"}],
        },
    )
    client = VolcengineLipSyncClient(api_key="k", http_client=fake)
    result = client.lip_sync_video.run(
        model="volcengine-lip-sync",
        mode="lite",
        source_video_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
        source_audio_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3",
    )

    assert isinstance(result, CompletedLipSyncVideoResponse)
    assert result.videos[0].url == "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-result-adam.mp4"
    assert [call[0] for call in fake.calls] == ["post", "get"]


def test_create_requires_model():
    client = VolcengineLipSyncClient(api_key="k", http_client=FakeHttp())
    with pytest.raises(ValidationError, match="model must be one of: volcengine-lip-sync"):
        client.lip_sync_video.create(
            mode="lite",
            source_video_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
            source_audio_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3",
        )


def test_create_requires_source_audio_url():
    client = VolcengineLipSyncClient(api_key="k", http_client=FakeHttp())
    with pytest.raises(ValidationError, match="source_audio_url is required"):
        client.lip_sync_video.create(
            model="volcengine-lip-sync",
            mode="lite",
            source_video_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
        )
