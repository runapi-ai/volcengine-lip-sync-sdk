# Volcengine Lip Sync API Python SDK for RunAPI

The Volcengine Lip Sync Python SDK submits lip-sync video tasks and retrieves task results through RunAPI.

## Install

```bash
pip install runapi-volcengine-lip-sync
```

## Quick start

```python
from runapi.volcengine_lip_sync import VolcengineLipSyncClient

client = VolcengineLipSyncClient()
task = client.lip_sync_video.create(
    model="volcengine-lip-sync",
    mode="lite",
    source_video_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
    source_audio_url="https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3",
)
status = client.lip_sync_video.get(task.id)
```

Use `create`, `get`, and `run` for async workflows. Keep `RUNAPI_API_KEY` in the environment or your secret manager.

## Links

- Model page: https://runapi.ai/models/volcengine-lip-sync
- SDK docs: https://runapi.ai/docs/resources/sdks
- Product docs: https://runapi.ai/docs/api/volcengine-lip-sync/lip-sync-video
- Pricing and rate limits: https://runapi.ai/models/volcengine-lip-sync
- Repository: https://github.com/runapi-ai/volcengine-lip-sync-sdk

## License

Licensed under the Apache License, Version 2.0.
