# frozen_string_literal: true

module RunApi
  module VolcengineLipSync
    # Client for Volcengine Lip Sync operations.
    class Client < RunApi::Core::Client
      attr_reader :lip_sync_video

      def initialize(**options)
        super
        @lip_sync_video = Resources::LipSyncVideo.new(@http)
      end
    end
  end
end
