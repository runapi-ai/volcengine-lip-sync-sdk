import type { AsyncTaskStatus } from '@runapi.ai/core';

export type LipSyncMode = 'lite' | 'basic' | (string & {});

/** Parameters for driving a source video's lip movement from an audio track. */
export interface LipSyncVideoParams {
  /** Model slug. */
  model: string;
  /** Processing mode: `lite` for frontal single-person videos, or `basic` for more complex scenes. */
  mode: LipSyncMode;
  /** Publicly accessible source video URL. */
  source_video_url: string;
  /** Publicly accessible source audio URL used to drive lip movement. */
  source_audio_url: string;
  /** URL to receive a webhook notification when the task completes. */
  callback_url?: string;
  /** Suppress background noise by separating vocals. */
  enable_vocal_separation?: boolean;
  /** Enable scene segmentation in `basic` mode. */
  enable_scene_detection?: boolean;
  /** Loop the video when audio is longer than the source video; `lite` mode only. */
  align_audio?: boolean;
  /** Loop the video in reverse when `align_audio` is true; `lite` mode only. */
  align_audio_reverse?: boolean;
  /** Start time in seconds for the source video template; `lite` mode only. */
  template_start_seconds?: number;
}

/** Initial response when a lip-sync video task is created. */
export interface TaskCreateResponse {
  id: string;
  status?: AsyncTaskStatus;
}

/** A generated video file. */
export interface Video {
  url: string;
}

/** Task status response for a lip-sync video operation. */
export interface LipSyncVideoResponse {
  id: string;
  status: AsyncTaskStatus;
  /** Generated lip-sync video, populated when the task completes. */
  videos?: Video[];
  /** Human-readable error description when the task fails. */
  error?: string;
  [key: string]: unknown;
}

/** Completed lip-sync video response with guaranteed output videos. */
export type CompletedLipSyncVideoResponse = LipSyncVideoResponse & {
  status: 'completed';
  videos: Video[];
};
