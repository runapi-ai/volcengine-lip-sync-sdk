# Volcengine Lip Sync API Go SDK for RunAPI

The Volcengine Lip Sync Go SDK submits lip-sync video tasks and retrieves task results through RunAPI.

## Install

```bash
go get github.com/runapi-ai/volcengine-lip-sync-sdk/go
```

## Quick start

```go
package main

import (
	"context"

	volcenginelipsync "github.com/runapi-ai/volcengine-lip-sync-sdk/go/volcenginelipsync"
)

func main() {
	client, err := volcenginelipsync.NewClient()
	if err != nil {
		panic(err)
	}
	result, err := client.LipSyncVideo.Run(context.Background(), volcenginelipsync.LipSyncVideoParams{
		Model:          "volcengine-lip-sync",
		Mode:           "lite",
		SourceVideoURL: "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
		SourceAudioURL: "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3",
	})
	if err != nil {
		panic(err)
	}
	_ = result.Videos
}
```

Use `Create`, `Get`, and `Run` for async workflows. Keep `RUNAPI_API_KEY` in the environment or your secret manager.

## Links

- Model page: https://runapi.ai/models/volcengine-lip-sync
- SDK docs: https://runapi.ai/docs/resources/sdks
- Product docs: https://runapi.ai/docs/api/volcengine-lip-sync/lip-sync-video
- Pricing and rate limits: https://runapi.ai/models/volcengine-lip-sync
- Repository: https://github.com/runapi-ai/volcengine-lip-sync-sdk

## License

Licensed under the Apache License, Version 2.0.
