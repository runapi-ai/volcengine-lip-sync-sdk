<p align="center">
  <a href="https://github.com/runapi-ai/volcengine-lip-sync">
    <h3 align="center">Volcengine Lip Sync API Skill for RunAPI</h3>
  </a>
</p>

<p align="center">
  Install this agent skill, inspect lip-sync fields, then run jobs through the RunAPI CLI.
</p>

<p align="center">
  <a href="https://runapi.ai/models/volcengine-lip-sync"><strong>Model Reference</strong></a> · <a href="https://github.com/runapi-ai/cli"><strong>CLI</strong></a> · <a href="https://github.com/runapi-ai/volcengine-lip-sync-sdk"><strong>SDK</strong></a>
</p>

<div align="center">

[![skills.sh](https://www.skills.sh/b/runapi-ai/volcengine-lip-sync)](https://www.skills.sh/runapi-ai/volcengine-lip-sync/volcengine-lip-sync)
[![ClawHub](https://img.shields.io/badge/ClawHub-runapi--volcengine--lip--sync-111827)](https://clawhub.ai/runapi-ai/runapi-volcengine-lip-sync)
[![License](https://img.shields.io/github/license/runapi-ai/volcengine-lip-sync)](https://github.com/runapi-ai/volcengine-lip-sync/blob/main/LICENSE)

</div>
<br/>

Generate lip-sync video from a source video and target audio with Volcengine Lip Sync. This skill helps Claude Code, Codex, Gemini CLI, Cursor, and 50+ agents integrate Volcengine Lip Sync through RunAPI.

The canonical agent file is `skills/volcengine-lip-sync/SKILL.md`.

## Install

```bash
npx skills add runapi-ai/volcengine-lip-sync -g
```

Or paste this prompt to your AI agent:

```text
Install the volcengine-lip-sync skill for me:

1. Clone https://github.com/runapi-ai/volcengine-lip-sync
2. Copy the skills/volcengine-lip-sync/ directory into your
   user-level skills directory (e.g. ~/.claude/skills/
   for Claude Code, ~/.codex/skills/ for Codex).
3. Verify that SKILL.md is present.
4. Confirm the install path when done.
```

## Quick example

```typescript
import { VolcengineLipSyncClient } from '@runapi.ai/volcengine-lip-sync';

const client = new VolcengineLipSyncClient();
const result = await client.lipSyncVideo.run({
  model: 'volcengine-lip-sync',
  mode: 'lite',
  source_video_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4',
  source_audio_url: 'https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3',
});
const url = result.videos[0].url;
```

## Routing

- Model page: https://runapi.ai/models/volcengine-lip-sync
- Product docs: https://runapi.ai/docs#volcengine-lip-sync
- SDK docs: https://runapi.ai/docs#sdk-volcengine-lip-sync
- SDK repository: https://github.com/runapi-ai/volcengine-lip-sync-sdk
- Pricing and rate limits: https://runapi.ai/models/volcengine-lip-sync
- Provider comparison: https://runapi.ai/providers/bytedance
- Browse all RunAPI models and skills: https://runapi.ai/models

## Agent rules

- Integration work uses the target language SDK; one-off generation, manual smoke tests, debugging, or user-requested CLI runs use the RunAPI CLI skill: https://github.com/runapi-ai/cli-skill
- RunAPI-generated file URLs are temporary. Download and store generated images, videos, audio, or other files in your own durable storage within 7 days; do not treat returned URLs as long-term assets.
- Keep API keys in `RUNAPI_API_KEY` or RunAPI CLI config; never commit secrets.
- Prefer `create`, `get`, and `run` JSON passthrough patterns instead of inventing flags for every model parameter.
- For pricing, rate-limit, and commercial-usage answers, link to the model page rather than the repository README.

## License

Licensed under the Apache License, Version 2.0.
