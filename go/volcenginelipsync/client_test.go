package volcenginelipsync

import (
	"context"
	"encoding/json"
	"testing"

	"github.com/runapi-ai/core-sdk/go/core"
)

type stubHTTPClient struct {
	method   string
	path     string
	body     any
	response json.RawMessage
}

func (s *stubHTTPClient) Request(_ context.Context, method, path string, opts *core.HTTPRequestOptions) (json.RawMessage, error) {
	s.method = method
	s.path = path
	if opts != nil {
		s.body = opts.Body
	}
	return s.response, nil
}

func TestLipSyncVideoCreate(t *testing.T) {
	enableVocalSeparation := false
	alignAudio := true
	alignAudioReverse := false
	templateStartSeconds := 1.5
	stub := &stubHTTPClient{response: json.RawMessage(`{"id":"task_volcengine_123","status":"processing"}`)}
	client := NewClientWithHTTP(stub)
	resp, err := client.LipSyncVideo.Create(context.Background(), LipSyncVideoParams{
		Model:                 "volcengine-lip-sync",
		Mode:                  "lite",
		SourceVideoURL:        "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4",
		SourceAudioURL:        "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3",
		CallbackURL:           "https://your-domain.com/api/callbacks/volcengine-lip-sync",
		EnableVocalSeparation: &enableVocalSeparation,
		AlignAudio:            &alignAudio,
		AlignAudioReverse:     &alignAudioReverse,
		TemplateStartSeconds:  &templateStartSeconds,
	})
	if err != nil {
		t.Fatal(err)
	}
	if stub.method != "POST" || stub.path != lipSyncVideoPath {
		t.Fatalf("unexpected request: %s %s", stub.method, stub.path)
	}
	body := stub.body.(map[string]any)
	if body["source_video_url"] != "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4" {
		t.Fatalf("unexpected source_video_url: %v", body["source_video_url"])
	}
	if _, ok := body["video_url"]; ok {
		t.Fatalf("expected request body to omit provider video_url key: %v", body)
	}
	if _, ok := body["audio_url"]; ok {
		t.Fatalf("expected request body to omit provider audio_url key: %v", body)
	}
	if body["enable_vocal_separation"] != false {
		t.Fatalf("unexpected enable_vocal_separation: %v", body["enable_vocal_separation"])
	}
	if resp.ID != "task_volcengine_123" {
		t.Fatalf("unexpected ID: %v", resp.ID)
	}
}

func TestLipSyncVideoGet(t *testing.T) {
	stub := &stubHTTPClient{response: json.RawMessage(`{"id":"task_volcengine_456","status":"completed","videos":[{"url":"https://cdn.runapi.ai/public/samples/volcengine-lip-sync-result-adam.mp4"}]}`)}
	client := NewClientWithHTTP(stub)
	resp, err := client.LipSyncVideo.Get(context.Background(), "task_volcengine_456")
	if err != nil {
		t.Fatal(err)
	}
	if stub.method != "GET" || stub.path != lipSyncVideoPath+"/task_volcengine_456" {
		t.Fatalf("unexpected request: %s %s", stub.method, stub.path)
	}
	if resp.Status != "completed" {
		t.Fatalf("unexpected status: %v", resp.Status)
	}
	if len(resp.Videos) != 1 || resp.Videos[0].URL != "https://cdn.runapi.ai/public/samples/volcengine-lip-sync-result-adam.mp4" {
		t.Fatalf("unexpected videos: %v", resp.Videos)
	}
}
