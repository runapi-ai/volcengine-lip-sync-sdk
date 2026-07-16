"""Volcengine Lip Sync response models."""

from __future__ import annotations

from runapi.core import BaseModel, TaskResponse, optional, required


class Video(BaseModel):
    """A generated video reference."""

    url = optional(str)


class LipSyncVideoResponse(TaskResponse):
    """Response for a lip-sync video task."""

    id = required(str)
    status = optional(str, enum=lambda: TaskResponse.Status.ALL)
    videos = optional([lambda: Video])
    error = optional(str)


class CompletedLipSyncVideoResponse(LipSyncVideoResponse):
    """Returned by ``lip_sync_video.run()`` once polling observes completion."""

    videos = required([lambda: Video])
