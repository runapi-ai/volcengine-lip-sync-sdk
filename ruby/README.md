# Volcengine Lip Sync API Ruby SDK for RunAPI

The Volcengine Lip Sync Ruby SDK submits lip-sync video tasks and retrieves task results through RunAPI.

## Install

```bash
bundle add runapi-volcengine-lip-sync
```

## Quick start

```ruby
require "runapi/volcengine_lip_sync"

client = RunApi::VolcengineLipSync::Client.new
task = client.lip_sync_video.create(
  model: "volcengine-lip-sync",
  mode: "lite",
  source_video_url: "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
  source_audio_url: "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3"
)
status = client.lip_sync_video.get(task.id)
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
