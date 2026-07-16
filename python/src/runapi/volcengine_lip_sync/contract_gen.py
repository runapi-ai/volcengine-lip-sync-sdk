CONTRACT = {
    "lip-sync-video": {
        "models": ["volcengine-lip-sync"],
        "fields_by_model": {
            "volcengine-lip-sync": {
                "mode": {
                    "enum": ["lite", "basic"],
                    "required": True
                },
                "source_audio_url": {
                    "required": True
                },
                "source_video_url": {
                    "required": True
                },
                "template_start_seconds": {
                    "min": 0
                }
            }
        },
        "rules": [{
            "when": {
                "mode": "lite"
            },
            "forbidden": ["enable_scene_detection"]
        }, {
            "when": {
                "mode": "basic"
            },
            "forbidden": ["align_audio", "align_audio_reverse", "template_start_seconds"]
        }, {
            "when": {
                "align_audio_reverse": True
            },
            "required": ["align_audio"]
        }]
    }
}
