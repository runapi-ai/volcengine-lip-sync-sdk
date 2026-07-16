package ai.runapi.volcenginelipsync.types;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Parameters for lip sync video operations. */
public final class LipSyncVideoParams {
  private final String model;
  private final String mode;
  private final String sourceVideoUrl;
  private final String sourceAudioUrl;
  private final String callbackUrl;
  private final Boolean enableVocalSeparation;
  private final Boolean enableSceneDetection;
  private final Boolean alignAudio;
  private final Boolean alignAudioReverse;
  private final Double templateStartSeconds;

  private LipSyncVideoParams(Builder builder) {
    this.model = builder.model;
    this.mode = VolcenginelipsyncParamUtils.requireNonBlank(builder.mode, "mode");
    this.sourceVideoUrl = VolcenginelipsyncParamUtils.requireNonBlank(builder.sourceVideoUrl, "sourceVideoUrl");
    this.sourceAudioUrl = VolcenginelipsyncParamUtils.requireNonBlank(builder.sourceAudioUrl, "sourceAudioUrl");
    this.callbackUrl = builder.callbackUrl;
    this.enableVocalSeparation = builder.enableVocalSeparation;
    this.enableSceneDetection = builder.enableSceneDetection;
    this.alignAudio = builder.alignAudio;
    this.alignAudioReverse = builder.alignAudioReverse;
    this.templateStartSeconds = builder.templateStartSeconds;
  }

  /** Creates a new LipSyncVideoParams builder. */
  public static Builder builder() {
    return new Builder();
  }

  /** Returns the RunAPI action key for this request. */
  public String action() {
    return "volcengine-lip-sync/lip-sync-video";
  }

  /** Converts these parameters to the JSON request body shape. */
  public Map<String, Object> toMap() {
    Map<String, Object> raw = new LinkedHashMap<String, Object>();
    raw.put("model", VolcenginelipsyncParamUtils.wireValue(model));
    raw.put("mode", VolcenginelipsyncParamUtils.wireValue(mode));
    raw.put("source_video_url", VolcenginelipsyncParamUtils.wireValue(sourceVideoUrl));
    raw.put("source_audio_url", VolcenginelipsyncParamUtils.wireValue(sourceAudioUrl));
    raw.put("callback_url", VolcenginelipsyncParamUtils.wireValue(callbackUrl));
    raw.put("enable_vocal_separation", VolcenginelipsyncParamUtils.wireValue(enableVocalSeparation));
    raw.put("enable_scene_detection", VolcenginelipsyncParamUtils.wireValue(enableSceneDetection));
    raw.put("align_audio", VolcenginelipsyncParamUtils.wireValue(alignAudio));
    raw.put("align_audio_reverse", VolcenginelipsyncParamUtils.wireValue(alignAudioReverse));
    raw.put("template_start_seconds", VolcenginelipsyncParamUtils.wireValue(templateStartSeconds));
    return VolcenginelipsyncParamUtils.compact(raw);
  }



  /** Builder for {@link LipSyncVideoParams}. */
  public static final class Builder {
    private String model;
    private String mode;
    private String sourceVideoUrl;
    private String sourceAudioUrl;
    private String callbackUrl;
    private Boolean enableVocalSeparation;
    private Boolean enableSceneDetection;
    private Boolean alignAudio;
    private Boolean alignAudioReverse;
    private Double templateStartSeconds;

    private Builder() {}

    /** Sets the model slug using a typed model value. */
    public Builder model(LipSyncVideoModel value) {
      this.model = java.util.Objects.requireNonNull(value, "model").value();
      return this;
    }

    /** Sets the model slug using a string value. */
    public Builder model(String value) {
      this.model = VolcenginelipsyncParamUtils.requireNonBlankTrim(value, "model");
      return this;
    }


    /** Sets the mode. */
    public Builder mode(String value) {
      this.mode = VolcenginelipsyncParamUtils.requireNonBlank(value, "mode");
      return this;
    }

    /** Sets the source video URL. */
    public Builder sourceVideoUrl(String value) {
      this.sourceVideoUrl = VolcenginelipsyncParamUtils.requireNonBlank(value, "sourceVideoUrl");
      return this;
    }

    /** Sets the source audio URL. */
    public Builder sourceAudioUrl(String value) {
      this.sourceAudioUrl = VolcenginelipsyncParamUtils.requireNonBlank(value, "sourceAudioUrl");
      return this;
    }

    /** Sets the webhook URL for task completion notifications. */
    public Builder callbackUrl(String value) {
      this.callbackUrl = VolcenginelipsyncParamUtils.requireNonBlank(value, "callbackUrl");
      return this;
    }

    /** Sets the enable vocal separation. */
    public Builder enableVocalSeparation(boolean value) {
      this.enableVocalSeparation = value;
      return this;
    }

    /** Sets the enable scene detection. */
    public Builder enableSceneDetection(boolean value) {
      this.enableSceneDetection = value;
      return this;
    }

    /** Sets the align audio. */
    public Builder alignAudio(boolean value) {
      this.alignAudio = value;
      return this;
    }

    /** Sets the align audio reverse. */
    public Builder alignAudioReverse(boolean value) {
      this.alignAudioReverse = value;
      return this;
    }

    /** Sets the template start seconds. */
    public Builder templateStartSeconds(double value) {
      this.templateStartSeconds = value;
      return this;
    }

    /** Builds immutable lip sync video parameters. */
    public LipSyncVideoParams build() {
      return new LipSyncVideoParams(this);
    }
  }
}
