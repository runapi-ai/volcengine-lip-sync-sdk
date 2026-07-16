# frozen_string_literal: true

module RunApi
  module VolcengineLipSync
    CONTRACT = {
      "lip-sync-video" => {
        "models" => ["volcengine-lip-sync"],
        "fields_by_model" => {
          "volcengine-lip-sync" => {
            "mode" => {
              "enum" => ["lite", "basic"],
              "required" => true
            },
            "source_audio_url" => {
              "required" => true
            },
            "source_video_url" => {
              "required" => true
            },
            "template_start_seconds" => {
              "min" => 0
            }
          }
        },
        "rules" => [{
          "when" => {
            "mode" => "lite"
          },
          "forbidden" => ["enable_scene_detection"]
        }, {
          "when" => {
            "mode" => "basic"
          },
          "forbidden" => ["align_audio", "align_audio_reverse", "template_start_seconds"]
        }, {
          "when" => {
            "align_audio_reverse" => true
          },
          "required" => ["align_audio"]
        }]
      }
    }.freeze
  end
end
