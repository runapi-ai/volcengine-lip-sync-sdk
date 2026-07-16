// Package volcenginelipsync provides the Volcengine Lip Sync API client.
//
//	client, err := volcenginelipsync.NewClient(option.WithAPIKey("sk-your-api-key"))
//	result, err := client.LipSyncVideo.Run(ctx, volcenginelipsync.LipSyncVideoParams{
//	    Model:          "volcengine-lip-sync",
//	    Mode:           "lite",
//	    SourceVideoURL: "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
//	    SourceAudioURL: "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3",
//	})
package volcenginelipsync

import (
	"context"

	"github.com/runapi-ai/core-sdk/go/base"
	"github.com/runapi-ai/core-sdk/go/core"
	"github.com/runapi-ai/core-sdk/go/option"
)

const (
	lipSyncVideoPath = "/api/v1/volcengine_lip_sync/lip_sync_video"
)

// Client provides Volcengine Lip Sync video operations.
type Client struct {
	base.Base
	LipSyncVideo *LipSyncVideo
}

// NewClient creates a Volcengine Lip Sync client with the given options.
func NewClient(opts ...option.ClientOption) (*Client, error) {
	resolved, err := option.ResolveClientOptions(opts...)
	if err != nil {
		return nil, err
	}
	httpClient, err := core.NewHTTPClient(resolved)
	if err != nil {
		return nil, err
	}
	return NewClientWithHTTP(httpClient), nil
}

// NewClientWithHTTP creates a Volcengine Lip Sync client with a pre-configured HTTP transport.
func NewClientWithHTTP(httpClient core.HTTPClient) *Client {
	return &Client{
		Base:         base.New(httpClient),
		LipSyncVideo: &LipSyncVideo{http: httpClient},
	}
}

// LipSyncVideo drives a source video's lip movement from an audio track.
type LipSyncVideo struct{ http core.HTTPClient }

// Create submits a lip-sync video task and returns immediately with a task id.
func (r *LipSyncVideo) Create(ctx context.Context, params LipSyncVideoParams, opts ...option.RequestOption) (*core.TaskCreateResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	body := core.CompactParams(params)
	if err := core.ValidateParams(contractSchema["lip-sync-video"], body); err != nil {
		return nil, err
	}
	return core.PostJSON[core.TaskCreateResponse](ctx, r.http, lipSyncVideoPath, body, requestOptions)
}

// Get fetches the current status of a lip-sync video task by id.
func (r *LipSyncVideo) Get(ctx context.Context, id string, opts ...option.RequestOption) (*LipSyncVideoResponse, error) {
	requestOptions, _ := option.ResolveRequestOptions(opts...)
	return core.GetJSON[LipSyncVideoResponse](ctx, r.http, core.ResourcePath(lipSyncVideoPath, id), requestOptions)
}

// Run submits a lip-sync video task and polls until it completes.
func (r *LipSyncVideo) Run(ctx context.Context, params LipSyncVideoParams, opts ...option.RequestOption) (*LipSyncVideoResponse, error) {
	_, pollingOptions := option.ResolveRequestOptions(opts...)
	return core.RunAsync(ctx, func(ctx context.Context) (*core.TaskCreateResponse, error) {
		return r.Create(ctx, params, opts...)
	}, func(ctx context.Context, id string) (*LipSyncVideoResponse, error) {
		return r.Get(ctx, id, opts...)
	}, pollingOptions)
}
