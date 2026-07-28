# Volcengine Lip Sync API JavaScript SDK for RunAPI

The Volcengine Lip Sync JavaScript SDK is the language-specific package for Volcengine Lip Sync on RunAPI. Use this package when your application needs typed request bodies, task status lookup, and consistent RunAPI errors in JavaScript.

This README is the JavaScript package guide inside the public `volcengine-lip-sync-sdk` repository. For model details, use https://runapi.ai/models/volcengine-lip-sync; for API reference, use https://runapi.ai/docs/api/volcengine-lip-sync/lip-sync-video; for SDK docs, use https://runapi.ai/docs/resources/sdks.

## Install

```bash
npm install @runapi.ai/volcengine-lip-sync
```

## Quick start

```typescript
import { VolcengineLipSyncClient } from '@runapi.ai/volcengine-lip-sync';

const client = new VolcengineLipSyncClient();
const task = await client.lipSyncVideo.create({
  model: 'volcengine-lip-sync',
  mode: 'lite',
  source_video_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4',
  source_audio_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3',
});
const status = await client.lipSyncVideo.get(task.id);
```

Use `create` when you want to submit a task and return quickly, `get` when you need the latest task state, and `run` when a script should create and poll until completion. In web request handlers, prefer `create` plus webhook or later `get` polling so a worker is not held open.

RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.

## Links

- Model page: https://runapi.ai/models/volcengine-lip-sync
- SDK docs: https://runapi.ai/docs/resources/sdks
- Product docs: https://runapi.ai/docs/api/volcengine-lip-sync/lip-sync-video
- Pricing and rate limits: https://runapi.ai/models/volcengine-lip-sync
- Provider comparison: https://runapi.ai/providers/bytedance
- Repository: https://github.com/runapi-ai/volcengine-lip-sync-sdk

## License

Licensed under the Apache License, Version 2.0.
