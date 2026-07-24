<p align="center">
  <a href="https://runapi.ai"><img src="https://runapi.ai/icon.svg" height="56" alt="RunAPI"></a>
</p>

<h3 align="center">
  <a href="https://github.com/runapi-ai/volcengine-lip-sync-sdk">Volcengine Lip Sync API SDK for RunAPI</a>
</h3>

<p align="center">
  Volcengine Lip Sync API SDKs for JavaScript, Python, Ruby, Go, Java, and PHP on RunAPI.
</p>

<div align="center">

[![npm](https://img.shields.io/npm/v/@runapi.ai/volcengine-lip-sync)](https://www.npmjs.com/package/@runapi.ai/volcengine-lip-sync)
[![PyPI](https://img.shields.io/pypi/v/runapi-volcengine-lip-sync)](https://pypi.org/project/runapi-volcengine-lip-sync/)
[![RubyGems](https://img.shields.io/gem/v/runapi-volcengine-lip-sync)](https://rubygems.org/gems/runapi-volcengine-lip-sync)
[![Go Reference](https://pkg.go.dev/badge/github.com/runapi-ai/volcengine-lip-sync-sdk/go.svg)](https://pkg.go.dev/github.com/runapi-ai/volcengine-lip-sync-sdk/go)
[![Maven Central](https://img.shields.io/maven-central/v/ai.runapi/runapi-volcengine-lip-sync)](https://central.sonatype.com/artifact/ai.runapi/runapi-volcengine-lip-sync)
[![License](https://img.shields.io/github/license/runapi-ai/volcengine-lip-sync-sdk)](https://github.com/runapi-ai/volcengine-lip-sync-sdk/blob/main/LICENSE)

</div>
<br/>

The Volcengine Lip Sync API SDK packages JavaScript, Python, Ruby, Go, Java, and PHP clients for Volcengine Lip Sync on RunAPI. Use it for audio-driven video-to-video lip-sync workflows when your app needs typed request builders, predictable task polling, file upload helpers, account helpers, and consistent RunAPI errors.

Volcengine Lip Sync is listed in the RunAPI model catalog at https://runapi.ai/models/volcengine-lip-sync. Variant pages carry pricing, rate-limit, and commercial-usage details. The public `volcengine-lip-sync-sdk` repository groups the non-PHP language packages, examples, CI, and release tags for this model. The PHP package is released from a split Composer repository.

## Install

```bash
npm install @runapi.ai/volcengine-lip-sync
pip install runapi-volcengine-lip-sync
gem install runapi-volcengine-lip-sync
go get github.com/runapi-ai/volcengine-lip-sync-sdk/go@latest
```

Gradle:

```kotlin
dependencies {
  implementation("ai.runapi:runapi-volcengine-lip-sync:0.1.1")
}
```

Maven:

```xml
<dependency>
  <groupId>ai.runapi</groupId>
  <artifactId>runapi-volcengine-lip-sync</artifactId>
  <version>0.1.1</version>
</dependency>
```

Use the Java BOM when installing multiple RunAPI Java modules:

```kotlin
dependencies {
  implementation(platform("ai.runapi:runapi-bom:0.2.6"))
  implementation("ai.runapi:runapi-volcengine-lip-sync")
}
```

The PHP package is published from the split Composer repository as `runapi-ai/volcengine-lip-sync`; see https://github.com/runapi-ai/volcengine-lip-sync-php for PHP install and examples.

## What You Can Build

- Build apps, agent workflows, batch jobs, and production services around Volcengine Lip Sync requests.
- Install only the language package your app needs while keeping one model-specific repository for docs and releases.
- Use `create` for submit-only jobs, `get` for status lookup, and `run` for submit-and-poll scripts.
- Upload local files, URL files, or base64 files through shared RunAPI file helpers.
- Handle validation, authentication, rate limits, billing errors, task failures, and polling timeouts through RunAPI SDK errors.

## JavaScript Quick Start

```ts
import { VolcengineLipSyncClient } from '@runapi.ai/volcengine-lip-sync';

const client = new VolcengineLipSyncClient({ apiKey: process.env.RUNAPI_API_KEY });

const result = await client.lipSyncVideo.run({
  model: 'volcengine-lip-sync',
  mode: 'lite',
  source_video_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4',
  source_audio_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3',
});

console.log(result.videos[0].url);
```

## Java Quick Start

```java
import ai.runapi.volcenginelipsync.VolcengineLipSyncClient;
import ai.runapi.volcenginelipsync.types.CompletedLipSyncVideoResponse;
import ai.runapi.volcenginelipsync.types.LipSyncVideoModel;
import ai.runapi.volcenginelipsync.types.LipSyncVideoParams;

VolcengineLipSyncClient client = VolcengineLipSyncClient.builder()
    .apiKey(System.getenv("RUNAPI_API_KEY"))
    .build();

CompletedLipSyncVideoResponse result = client.lipSyncVideo().run(
    LipSyncVideoParams.builder()
        .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
        .mode("lite")
        .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
        .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
        .build()
);
```

Java packages target Java 8 bytecode and are tested on Java 8, 11, 17, and 21. Each model artifact depends on `ai.runapi:runapi-core`, so application code normally installs only `ai.runapi:runapi-volcengine-lip-sync`.

## Task Lifecycle

Volcengine Lip Sync is asynchronous. `create()` submits a task and returns its id, `get(id)` fetches the latest task state, and `run(params)` creates the task and polls until it reaches a terminal state. In web request handlers, prefer `create()` plus webhook or later `get()` polling so the server does not hold a worker open.

## Repository Layout

- `js/` publishes `@runapi.ai/volcengine-lip-sync`.
- `python/` publishes `runapi-volcengine-lip-sync`.
- `ruby/` publishes `runapi-volcengine-lip-sync`.
- `go/` publishes `github.com/runapi-ai/volcengine-lip-sync-sdk/go`.
- `java/` publishes `ai.runapi:runapi-volcengine-lip-sync` and uses `ai.runapi:runapi-core`.

## Public Links

- Model page: https://runapi.ai/models/volcengine-lip-sync
- SDK docs: https://runapi.ai/docs#sdk-volcengine-lip-sync
- Product docs: https://runapi.ai/docs#volcengine-lip-sync
- SDK repository: https://github.com/runapi-ai/volcengine-lip-sync-sdk
- PHP package repository: https://github.com/runapi-ai/volcengine-lip-sync-php
- Skill repository: https://github.com/runapi-ai/volcengine-lip-sync
- Provider comparison: https://runapi.ai/providers/bytedance
- Full catalog: https://runapi.ai/models

## Pricing and Variants

Use the Volcengine Lip Sync variant page for pricing, rate limits, and commercial usage:
- [Lip sync video](https://runapi.ai/models/volcengine-lip-sync)

Default pricing link for the Volcengine Lip Sync SDK: https://runapi.ai/models/volcengine-lip-sync

## File Storage

RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.

## FAQ

### Which package should I install for Volcengine Lip Sync work?

Install the model package for your language: `@runapi.ai/volcengine-lip-sync` on npm, `runapi-volcengine-lip-sync` on PyPI, `runapi-volcengine-lip-sync` on RubyGems, `github.com/runapi-ai/volcengine-lip-sync-sdk/go`, `ai.runapi:runapi-volcengine-lip-sync` on Maven Central, or `runapi-ai/volcengine-lip-sync` on Packagist. Install core SDK packages only when you are building shared SDK infrastructure.

### Where should public links point?

Primary Volcengine Lip Sync links point to https://runapi.ai/models/volcengine-lip-sync. Pricing and usage-policy links point to https://runapi.ai/models/volcengine-lip-sync. Provider comparisons point to https://runapi.ai/providers/bytedance, and broad browsing points to https://runapi.ai/models.

## License

Licensed under the Apache License, Version 2.0.
