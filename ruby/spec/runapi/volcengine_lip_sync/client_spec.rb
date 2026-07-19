# frozen_string_literal: true

require "spec_helper"

RSpec.describe RunApi::VolcengineLipSync::Client do
  it "exposes lip_sync_video resource" do
    client = described_class.new(api_key: "test-key")

    expect(client.lip_sync_video).to be_a(RunApi::VolcengineLipSync::Resources::LipSyncVideo)
  end
end
