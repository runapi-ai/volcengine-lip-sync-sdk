"""Volcengine Lip Sync client."""

from __future__ import annotations

from typing import Any, Optional

from runapi.core import ProviderClient

from .resources.lip_sync_video import LipSyncVideo


class VolcengineLipSyncClient(ProviderClient):
    """Volcengine Lip Sync client."""

    def __init__(self, api_key: Optional[str] = None, **options: Any) -> None:
        super().__init__(api_key, **options)
        http = self._http
        self.lip_sync_video = LipSyncVideo(http)
