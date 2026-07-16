package ai.runapi.volcenginelipsync.types;

import com.fasterxml.jackson.annotation.JsonCreator;

/** Model slug for lip sync video operations. */
public final class LipSyncVideoModel extends VolcenginelipsyncValue {
  /** volcengine-lip-sync model slug. */
  public static final LipSyncVideoModel VOLCENGINE_LIP_SYNC = new LipSyncVideoModel("volcengine-lip-sync");

  /** Creates a model value from a literal model slug. */
  @JsonCreator
  public LipSyncVideoModel(String value) {
    super(value);
  }
}
