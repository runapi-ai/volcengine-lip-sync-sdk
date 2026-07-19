# Volcengine Lip Sync API Java SDK for RunAPI

The Volcengine Lip Sync Java SDK submits lip-sync video tasks and retrieves task results through RunAPI.

## Install

```kotlin
implementation("ai.runapi:runapi-volcengine-lip-sync:0.1.1")
```

## Quick start

```java
import ai.runapi.volcenginelipsync.VolcengineLipSyncClient;
import ai.runapi.volcenginelipsync.types.LipSyncVideoModel;
import ai.runapi.volcenginelipsync.types.LipSyncVideoParams;

VolcengineLipSyncClient client = VolcengineLipSyncClient.builder().build();
var task = client.lipSyncVideo().create(
    LipSyncVideoParams.builder()
        .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
        .mode("lite")
        .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
        .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
        .build());
```

Use `create`, `get`, and `run` for async workflows. Keep `RUNAPI_API_KEY` in the environment or your secret manager.

## Links

- Model page: https://runapi.ai/models/volcengine-lip-sync
- SDK docs: https://runapi.ai/docs#sdk-volcengine-lip-sync
- Product docs: https://runapi.ai/docs#volcengine-lip-sync
- Pricing and rate limits: https://runapi.ai/models/volcengine-lip-sync
- Repository: https://github.com/runapi-ai/volcengine-lip-sync-sdk

## License

Licensed under the Apache License, Version 2.0.
