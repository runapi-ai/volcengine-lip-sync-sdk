import { beforeEach, describe, expect, it, vi } from 'vitest';
import type { HttpClient } from '@runapi.ai/core';
import { LipSyncVideo } from '../../src/resources/lip-sync-video';

describe('Volcengine Lip Sync videos', () => {
  const mockHttp: HttpClient = {
    request: vi.fn(),
  };

  beforeEach(() => {
    vi.clearAllMocks();
  });

  it('creates a lip-sync video with flat public params', async () => {
    vi.mocked(mockHttp.request).mockResolvedValueOnce({ id: 'task-1', status: 'processing' });
    const resource = new LipSyncVideo(mockHttp);

    await resource.create({
      model: 'volcengine-lip-sync',
      mode: 'lite',
      source_video_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4',
      source_audio_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3',
      callback_url: 'https://your-domain.com/api/callbacks/volcengine-lip-sync',
      enable_vocal_separation: false,
      align_audio: true,
      align_audio_reverse: false,
      template_start_seconds: 1.5,
    });

    expect(mockHttp.request).toHaveBeenCalledWith('POST', '/api/v1/volcengine_lip_sync/lip_sync_video', {
      body: {
        model: 'volcengine-lip-sync',
        mode: 'lite',
        source_video_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4',
        source_audio_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3',
        callback_url: 'https://your-domain.com/api/callbacks/volcengine-lip-sync',
        enable_vocal_separation: false,
        align_audio: true,
        align_audio_reverse: false,
        template_start_seconds: 1.5,
      },
    });
  });

  it('gets a lip-sync video by id', async () => {
    vi.mocked(mockHttp.request).mockResolvedValueOnce({
      id: 'task-1',
      status: 'completed',
      videos: [{ url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-result-adam.mp4' }],
    });
    const resource = new LipSyncVideo(mockHttp);

    const result = await resource.get('task-1');

    expect(mockHttp.request).toHaveBeenCalledWith('GET', '/api/v1/volcengine_lip_sync/lip_sync_video/task-1', {});
    expect(result.videos?.[0]?.url).toBe('https://cdn.runapi.ai/public/samples/volcengine-lip-sync-result-adam.mp4');
  });
});
