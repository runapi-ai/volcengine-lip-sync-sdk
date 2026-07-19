# frozen_string_literal: true

require "spec_helper"

RSpec.describe RunApi::VolcengineLipSync::Resources::LipSyncVideo do
  let(:http) { instance_double(RunApi::Core::HttpClient) }
  let(:resource) { described_class.new(http) }
  let(:endpoint) { "/api/v1/volcengine_lip_sync/lip_sync_video" }

  describe "#create" do
    it "POSTs to the correct endpoint with public params" do
      params = {
        model: "volcengine-lip-sync",
        mode: "lite",
        source_video_url: "https://storage.googleapis.com/gtv-videos-bucket/sample/ForBiggerJoyrides.mp4",
        source_audio_url: "https://cdn.runapi.ai/public/samples/music.mp3",
        callback_url: "https://your-domain.com/api/callbacks/volcengine-lip-sync",
        enable_vocal_separation: false,
        align_audio: true,
        align_audio_reverse: false,
        template_start_seconds: 1.5
      }
      expect(http).to receive(:request).with(:post, endpoint, body: params)
        .and_return("id" => "task-1", "status" => "processing")

      result = resource.create(**params)
      expect(result).to be_a(RunApi::VolcengineLipSync::Types::LipSyncVideoResponse)
      expect(result.id).to eq("task-1")
    end

    it "raises ValidationError when model is invalid" do
      expect do
        resource.create(mode: "lite", source_video_url: "https://x/v.mp4", source_audio_url: "https://x/a.mp3")
      end.to raise_error(RunApi::Core::ValidationError, /model must be one of: volcengine-lip-sync/)
    end
  end

  describe "#get" do
    it "GETs the correct endpoint" do
      expect(http).to receive(:request).with(:get, "#{endpoint}/task-1")
        .and_return(
          "id" => "task-1",
          "status" => "completed",
          "videos" => [{"url" => "https://tempfile.runapi.ai/volcengine/result.mp4"}]
        )

      result = resource.get("task-1")
      expect(result).to be_a(RunApi::VolcengineLipSync::Types::LipSyncVideoResponse)
      expect(result.status).to eq("completed")
      expect(result.videos.first.url).to eq("https://tempfile.runapi.ai/volcengine/result.mp4")
    end
  end
end
