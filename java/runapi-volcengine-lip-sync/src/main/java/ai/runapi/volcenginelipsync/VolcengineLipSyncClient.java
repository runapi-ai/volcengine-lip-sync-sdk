package ai.runapi.volcenginelipsync;

import ai.runapi.core.BaseClient;
import ai.runapi.core.ClientOptions;
import ai.runapi.core.http.HttpTransport;
import java.net.URI;
import ai.runapi.volcenginelipsync.resources.LipSyncVideoResource;

/** VolcengineLipSync model-family Java SDK client. */
public final class VolcengineLipSyncClient extends BaseClient {
  private final LipSyncVideoResource lipSyncVideo;

  private VolcengineLipSyncClient(ClientOptions options) {
    super(options);
    this.lipSyncVideo = new LipSyncVideoResource(transport(), options());
  }

  /** Creates a new VolcengineLipSyncClient builder. */
  public static Builder builder() {
    return new Builder();
  }

  /** Lip Sync Video operations. */
  public LipSyncVideoResource lipSyncVideo() {
    return lipSyncVideo;
  }

  /** Builder for {@link VolcengineLipSyncClient}. */
  public static final class Builder extends BaseClient.Builder<Builder> {
    private Builder() {}

    /** Sets the API key. If omitted, the SDK reads {@code RUNAPI_API_KEY}. */
    @Override
    public Builder apiKey(String value) {
      return super.apiKey(value);
    }

    /** Sets the RunAPI base URL. If omitted, the SDK reads {@code RUNAPI_BASE_URL}. */
    @Override
    public Builder baseUrl(String value) {
      return super.baseUrl(value);
    }

    /** Sets the RunAPI base URL from a URI. */
    @Override
    public Builder baseUrl(URI value) {
      return super.baseUrl(value);
    }

    /** Sets a custom HTTP transport. User-provided transports are not closed by SDK clients. */
    @Override
    public Builder transport(HttpTransport value) {
      return super.transport(value);
    }

    /** Builds an immutable VolcengineLipSyncClient. */
    @Override
    public VolcengineLipSyncClient build() {
      return new VolcengineLipSyncClient(options.build());
    }
  }
}
