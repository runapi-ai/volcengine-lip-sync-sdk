# frozen_string_literal: true

Dir.chdir(__dir__) do

  Gem::Specification.new do |spec|
    spec.name = "runapi-volcengine-lip-sync"
    spec.version = "0.1.1"
    spec.authors = ["RunAPI"]
    spec.email = ["contact@runapi.ai"]

    spec.summary = "Volcengine Lip Sync API Ruby SDK for RunAPI"
    spec.description = "The Volcengine Lip Sync Ruby SDK submits lip-sync video tasks and retrieves task results through RunAPI."
    spec.homepage = "https://runapi.ai/models/volcengine-lip-sync"
    spec.license = "Apache-2.0"
    spec.required_ruby_version = ">= 3.1.0"
    spec.metadata["homepage_uri"] = "https://runapi.ai/models/volcengine-lip-sync"
    spec.metadata["documentation_uri"] = "https://github.com/runapi-ai/volcengine-lip-sync-sdk/blob/main/ruby/README.md"
    spec.metadata["source_code_uri"] = "https://github.com/runapi-ai/volcengine-lip-sync-sdk"
    spec.metadata["bug_tracker_uri"] = "https://github.com/runapi-ai/volcengine-lip-sync-sdk/issues"
    spec.metadata["changelog_uri"] = "https://github.com/runapi-ai/volcengine-lip-sync-sdk/blob/main/CHANGELOG.md"


    spec.files = Dir.glob("lib/**/*") + %w[LICENSE README.md]
    spec.extra_rdoc_files = ["README.md"]
        spec.require_paths = ["lib"]

    spec.add_dependency "runapi-core", "~> 0.2.11"
  end
end
