// Package volcenginelipsync provides the Volcengine Lip Sync API client.
package volcenginelipsync

import "github.com/runapi-ai/core-sdk/go/core"

// TaskStatus is the async task lifecycle state (e.g. "processing", "completed", "failed").
type TaskStatus string

// LipSyncVideoParams configures video-to-video lip sync.
type LipSyncVideoParams struct {
	Model                 string   `json:"model" help:"required; model slug"`
	Mode                  string   `json:"mode" help:"required; mode enum: lite, basic"`
	SourceVideoURL        string   `json:"source_video_url" help:"required; publicly accessible source video URL"`
	SourceAudioURL        string   `json:"source_audio_url" help:"required; publicly accessible source audio URL"`
	CallbackURL           string   `json:"callback_url,omitempty" help:"optional; HTTPS callback URL for completion events"`
	EnableVocalSeparation *bool    `json:"enable_vocal_separation,omitempty" help:"optional; suppress background noise by separating vocals"`
	EnableSceneDetection  *bool    `json:"enable_scene_detection,omitempty" help:"optional; enable scene segmentation in basic mode"`
	AlignAudio            *bool    `json:"align_audio,omitempty" help:"optional; loop video when audio is longer; lite mode only"`
	AlignAudioReverse     *bool    `json:"align_audio_reverse,omitempty" help:"optional; reverse loop when align_audio is true; lite mode only"`
	TemplateStartSeconds  *float64 `json:"template_start_seconds,omitempty" help:"optional; source video start time in seconds; lite mode only"`
}

// AsyncTaskResponse carries the task ID, lifecycle status, and error.
type AsyncTaskResponse struct {
	core.TaskBillingFacts
	ID     string     `json:"id"`
	Status TaskStatus `json:"status"`
	Error  string     `json:"error,omitempty"`
}

func (r AsyncTaskResponse) GetID() string     { return r.ID }
func (r AsyncTaskResponse) GetStatus() string { return string(r.Status) }
func (r AsyncTaskResponse) GetError() string  { return r.Error }

// Video holds a URL to a generated video file.
type Video struct {
	URL string `json:"url"`
}

// LipSyncVideoResponse is the result of a lip-sync video task.
type LipSyncVideoResponse struct {
	AsyncTaskResponse
	Videos []Video `json:"videos,omitempty"`
}
