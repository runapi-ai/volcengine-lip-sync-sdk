# Changelog

## [js/v0.2.0](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/js%2Fv0.2.0), [ruby/v0.2.0](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/ruby%2Fv0.2.0), [go/v0.3.0](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/go%2Fv0.3.0), [python/v0.3.0](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/python%2Fv0.3.0), [java/v0.2.0](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/java%2Fv0.2.0) - 2026-09-30

### Changed
- Send request parameters to the service without local validation. Model ids and parameter values the service supports work without an SDK upgrade; static types and enum constants remain for completion.
  Migration: Invalid parameters now fail with the validation error built from the service's 400 response, including its status and message, instead of a validation error raised locally before the request. The error type is unchanged: `ValidationError` in JavaScript, Python, and Ruby, `ValidationException` in Java and PHP, and `ErrValidation` in Go.


## [js/v0.1.3](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/js%2Fv0.1.3), [go/v0.2.11](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/go%2Fv0.2.11) - 2026-09-28

### Added
- Return usage.cost as a float USD amount on completed async Task query and webhook envelopes.

### Removed
- Remove the public Task billing object from Task envelopes.
  Migration: Read usage.cost on completed Task envelopes. Create, processing, and failed envelopes omit usage.


## [ruby/v0.1.4](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/ruby%2Fv0.1.4) - 2026-09-04

### Changed
- Update the `runapi-core` dependency range so this package remains installable with other current RunAPI Ruby SDKs.


## [ruby/v0.1.3](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/ruby%2Fv0.1.3) - 2026-08-18

### Changed
- Allow Ruby clients to install the core SDK release that adds persistent Files and multipart Uploads alongside this model SDK.


## [python/v0.2.1](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/python%2Fv0.2.1) - 2026-07-29

### Fixed
- Point package documentation metadata to the current RunAPI Developer Docs.


## [go/v0.2.10](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/go%2Fv0.2.10) - 2026-07-28

### Added
- Expose persisted billing facts on task responses.

## [js/v0.1.2](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/js%2Fv0.1.2) - 2026-07-28

### Added
- Type task billing facts on task responses.

## [ruby/v0.1.2](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/ruby%2Fv0.1.2) - 2026-07-28

### Added
- Expose live pricing through the shared core SDK.


## [python/v0.2.0](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/python%2Fv0.2.0) - 2026-07-24

### Added
- Expose shared Files, Account, and Pricing resources plus typed Task Billing Facts through the Provider Client.


## [js/v0.1.1](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/js%2Fv0.1.1), [ruby/v0.1.1](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/ruby%2Fv0.1.1), [go/v0.2.9](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/go%2Fv0.2.9), [python/v0.1.1](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/python%2Fv0.1.1), [java/v0.1.1](https://github.com/runapi-ai/volcengine-lip-sync-sdk/releases/tag/java%2Fv0.1.1) - 2026-07-16

### Changed
- Add typed Volcengine Lip Sync SDK packages for audio-driven video-to-video lip sync create and query workflows.
