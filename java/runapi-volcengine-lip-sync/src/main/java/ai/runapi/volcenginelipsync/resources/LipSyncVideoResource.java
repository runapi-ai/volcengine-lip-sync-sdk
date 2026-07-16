package ai.runapi.volcenginelipsync.resources;

import ai.runapi.core.ClientOptions;
import ai.runapi.core.RequestOptions;
import ai.runapi.core.http.HttpTransport;
import ai.runapi.core.polling.TaskCreateResponse;
import ai.runapi.volcenginelipsync.types.CompletedLipSyncVideoResponse;
import ai.runapi.volcenginelipsync.types.LipSyncVideoParams;
import ai.runapi.volcenginelipsync.types.LipSyncVideoResponse;

/** Lip Sync Video operations. */
public final class LipSyncVideoResource extends VolcenginelipsyncResource {
  /** API endpoint path for lip sync video operations. */
  public static final String ENDPOINT = "/api/v1/volcengine_lip_sync/lip_sync_video";

  /** Creates a resource bound to the supplied transport and client options. */
  public LipSyncVideoResource(HttpTransport transport, ClientOptions options) {
    super(transport, options, ENDPOINT);
  }

  /** Creates a lip sync video task. */
  public TaskCreateResponse create(LipSyncVideoParams params) {
    return create(params, RequestOptions.none());
  }

  /** Creates a lip sync video task with per-request options. */
  public TaskCreateResponse create(LipSyncVideoParams params, RequestOptions options) {
    return createTask(params.action(), params.toMap(), options);
  }

  /** Retrieves a lip sync video task by ID. */
  public LipSyncVideoResponse get(String id) {
    return get(id, RequestOptions.none());
  }

  /** Retrieves a lip sync video task by ID with per-request options. */
  public LipSyncVideoResponse get(String id, RequestOptions options) {
    return getTask(id, options, LipSyncVideoResponse.class);
  }

  /** Creates a lip sync video task and polls until it completes. */
  public CompletedLipSyncVideoResponse run(LipSyncVideoParams params) {
    return run(params, RequestOptions.none());
  }

  /** Creates a lip sync video task with per-request options and polls until it completes. */
  public CompletedLipSyncVideoResponse run(LipSyncVideoParams params, RequestOptions options) {
    return runTask(params.action(), params.toMap(), options, LipSyncVideoResponse.class, CompletedLipSyncVideoResponse.class);
  }
}
