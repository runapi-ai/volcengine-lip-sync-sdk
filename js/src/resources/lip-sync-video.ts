import type { HttpClient, PollingOptions, RequestOptions, ActionSchema } from '@runapi.ai/core';
import { compactParams, validateParams } from '@runapi.ai/core';
import { pollUntilComplete } from '@runapi.ai/core/internal';
import { contract } from '../contract_gen';
import type {
  CompletedLipSyncVideoResponse,
  LipSyncVideoParams,
  LipSyncVideoResponse,
  TaskCreateResponse,
} from '../types';

const ENDPOINT = '/api/v1/volcengine_lip_sync/lip_sync_video';

/** Drive a source video's lip movement from an audio track. */
export class LipSyncVideo {
  constructor(private readonly http: HttpClient) {}

  /**
   * Create a lip-sync video task and wait until it completes.
   * @param params Lip-sync parameters.
   * @param options Per-request and polling overrides.
   * @returns The completed task with videos.
   */
  async run(
    params: LipSyncVideoParams,
    options?: RequestOptions & PollingOptions,
  ): Promise<CompletedLipSyncVideoResponse> {
    const { id } = await this.create(params, options);
    const response = await pollUntilComplete<LipSyncVideoResponse>(() => this.get(id, options), {
      maxWaitMs: options?.maxWaitMs,
      pollIntervalMs: options?.pollIntervalMs,
    });
    return response as CompletedLipSyncVideoResponse;
  }

  /**
   * Start a lip-sync video task; returns immediately with a task id.
   * @param params Lip-sync parameters.
   * @param options Per-request overrides.
   * @returns The task creation result with id.
   */
  async create(params: LipSyncVideoParams, options?: RequestOptions): Promise<TaskCreateResponse> {
    const body = compactParams(params);
    validateParams(contract['lip-sync-video'] as ActionSchema, body as Record<string, unknown>);
    return this.http.request<TaskCreateResponse>('POST', ENDPOINT, {
      body,
      ...options,
    });
  }

  /**
   * Fetch the current status of a lip-sync video task.
   * @param id The task id.
   * @param options Per-request overrides.
   * @returns The current lip-sync video task status.
   */
  async get(id: string, options?: RequestOptions): Promise<LipSyncVideoResponse> {
    return this.http.request<LipSyncVideoResponse>('GET', `${ENDPOINT}/${id}`, {
      ...options,
    });
  }
}
