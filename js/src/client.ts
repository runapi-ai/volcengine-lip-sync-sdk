import { BaseClient, type ClientOptions } from '@runapi.ai/core';
import { LipSyncVideo } from './resources/lip-sync-video';

/** Volcengine Lip Sync API client. */
export class VolcengineLipSyncClient extends BaseClient {
  /** Drive a source video's lip movement from an audio track. */
  public readonly lipSyncVideo: LipSyncVideo;

  constructor(options: ClientOptions = {}) {
    super(options);
    this.lipSyncVideo = new LipSyncVideo(this.http);
  }
}
