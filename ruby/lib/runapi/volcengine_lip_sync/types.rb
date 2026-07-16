# frozen_string_literal: true

module RunApi
  module VolcengineLipSync
    # Type definitions for Volcengine Lip Sync video operations.
    module Types
      # A generated video file.
      class Video < RunApi::Core::BaseModel
        optional :url, String
      end

      # Base async task response with id and status tracking.
      class AsyncTaskResponse < RunApi::Core::TaskResponse
        required :id, String
        optional :status, String, enum: -> { RunApi::Core::TaskResponse::Status::ALL }
      end

      # Task status response for a lip-sync video operation.
      class LipSyncVideoResponse < AsyncTaskResponse
        optional :videos, [-> { Video }]
        optional :error, String
      end

      # Completed lip-sync video response with guaranteed output videos.
      class CompletedLipSyncVideoResponse < LipSyncVideoResponse
        required :videos, [-> { Video }]
      end
    end
  end
end
