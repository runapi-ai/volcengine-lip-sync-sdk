# frozen_string_literal: true

module RunApi
  module VolcengineLipSync
    module Resources
      # Volcengine Lip Sync video resource.
      class LipSyncVideo
        include RunApi::Core::ResourceHelpers

        ENDPOINT = "/api/v1/volcengine_lip_sync/lip_sync_video"
        RESPONSE_CLASS = Types::LipSyncVideoResponse
        COMPLETED_RESPONSE_CLASS = Types::CompletedLipSyncVideoResponse

        def initialize(http)
          @http = http
        end

        # Create a lip-sync video task and wait until complete.
        #
        # @param params [Hash] lip-sync parameters
        # @return [RunApi::VolcengineLipSync::Types::CompletedLipSyncVideoResponse] completed task with videos
        def run(options: nil, **params)
          task = create(options: options, **params)
          poll_until_complete { get(task.id, options: options) }
        end

        # Start a lip-sync video task.
        #
        # @param params [Hash] lip-sync parameters
        # @return [RunApi::VolcengineLipSync::Types::LipSyncVideoResponse] task creation result with id
        def create(options: nil, **params)
          params = compact_params(params)
          validate_contract!(CONTRACT["lip-sync-video"], params)
          request(:post, ENDPOINT, body: params, options: options)
        end

        # Get lip-sync video task status by task ID.
        #
        # @param id [String] task ID
        # @return [RunApi::VolcengineLipSync::Types::LipSyncVideoResponse] current task status
        def get(id, options: nil)
          request(:get, "#{ENDPOINT}/#{id}", options: options)
        end
      end
    end
  end
end
