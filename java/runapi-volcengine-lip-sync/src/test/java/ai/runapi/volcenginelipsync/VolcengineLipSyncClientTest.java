package ai.runapi.volcenginelipsync;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import ai.runapi.core.RequestOptions;
import ai.runapi.core.errors.ValidationException;
import ai.runapi.core.http.HttpRequest;
import ai.runapi.core.http.HttpResponse;
import ai.runapi.core.http.HttpTransport;
import ai.runapi.core.http.JsonRequestBody;
import ai.runapi.core.json.Json;
import ai.runapi.volcenginelipsync.types.CompletedLipSyncVideoResponse;
import ai.runapi.volcenginelipsync.types.LipSyncVideoResponse;
import ai.runapi.volcenginelipsync.types.CompletedLipSyncVideoResponse;
import ai.runapi.volcenginelipsync.types.LipSyncVideoModel;
import ai.runapi.volcenginelipsync.types.LipSyncVideoParams;
import ai.runapi.volcenginelipsync.types.LipSyncVideoResponse;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.ByteArrayOutputStream;
import java.time.Duration;
import java.util.Collections;
import org.junit.jupiter.api.Test;

class VolcengineLipSyncClientTest {
  @Test
  void builderCreatesClientAndUniversalResources() {
    VolcengineLipSyncClient client = VolcengineLipSyncClient.builder().apiKey("sk-test").build();

    assertNotNull(client.lipSyncVideo());
    assertNotNull(client.files());
    assertNotNull(client.account());
  }

  @Test
  void openValueClassesSerializeAsScalarStrings() throws Exception {
    String json = Json.mapper().writeValueAsString(new LipSyncVideoModel("volcengine-lip-sync"));

    assertEquals("\"volcengine-lip-sync\"", json);
    assertEquals(new LipSyncVideoModel("volcengine-lip-sync"), Json.mapper().readValue(json, LipSyncVideoModel.class));
  }

  @Test
  void createSendsExpectedRequestShape() throws Exception {
    CapturingTransport transport = new CapturingTransport("{\"id\":\"task_123\",\"status\":\"processing\"}");
    VolcengineLipSyncClient client = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(transport).build();

    client.lipSyncVideo().create(
        LipSyncVideoParams.builder()
            .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
            .mode("lite")
            .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
            .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
            .build()
    );

    assertEquals("POST", transport.request.getMethod().name());
    assertEquals("/api/v1/volcengine_lip_sync/lip_sync_video", transport.request.getPath());
    JsonNode body = bodyJson(transport.request);
    assertNotNull(body);
  }

  @Test
  void getDecodesTaskResponseAndExtraFields() {
    CapturingTransport transport = new CapturingTransport("{\"id\":\"task_456\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}],\"custom\":\"kept\"}");
    VolcengineLipSyncClient client = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(transport).build();

    LipSyncVideoResponse response = client.lipSyncVideo().get("task_456");

    assertEquals("GET", transport.request.getMethod().name());
    assertEquals("/api/v1/volcengine_lip_sync/lip_sync_video/task_456", transport.request.getPath());
    assertEquals("completed", response.getStatus().value());
    assertNotNull(response.getVideos());
    assertEquals("kept", response.extraFields().get("custom").asText());
  }

  @Test
  void runPollsUntilCompletedAndKeepsExtraFields() {
    SequenceTransport transport = new SequenceTransport(
        "{\"id\":\"task_789\",\"status\":\"processing\"}",
        "{\"id\":\"task_789\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}],\"custom\":\"kept\"}");
    VolcengineLipSyncClient client = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(transport).build();

    CompletedLipSyncVideoResponse response = client.lipSyncVideo().run(
        LipSyncVideoParams.builder()
            .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
            .mode("lite")
            .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
            .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
            .build(),
        RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).pollingMaxWait(Duration.ofSeconds(1)).build());

    assertEquals("completed", response.getStatus().value());
    assertNotNull(response.getVideos());
    assertEquals("kept", response.extraFields().get("custom").asText());
    assertEquals(2, transport.calls);
  }

  @Test
  void runRejectsCompletedResponseMissingResultField() {
    SequenceTransport transport = new SequenceTransport(
        "{\"id\":\"task_missing\",\"status\":\"processing\"}",
        "{\"id\":\"task_missing\",\"status\":\"completed\"}");
    VolcengineLipSyncClient client = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(transport).build();

    assertThrows(
        ValidationException.class,
        () -> client.lipSyncVideo().run(
                LipSyncVideoParams.builder()
                    .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
                    .mode("lite")
                    .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
                    .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
                    .build(),
            RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).pollingMaxWait(Duration.ofSeconds(1)).build()));
  }

    @Test
    void coversLipsyncvideoResourceMethods() {
      CapturingTransport createTransport = new CapturingTransport("{\"id\":\"task_lip_sync_video\",\"status\":\"processing\"}");
      VolcengineLipSyncClient createClient = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(createTransport).build();
      assertNotNull(createClient.lipSyncVideo().create(
              LipSyncVideoParams.builder()
                  .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
                  .mode("lite")
                  .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
                  .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
                  .build()
      ));

      CapturingTransport createWithOptionsTransport = new CapturingTransport("{\"id\":\"task_lip_sync_video_options\",\"status\":\"processing\"}");
      VolcengineLipSyncClient createWithOptionsClient = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(createWithOptionsTransport).build();
      assertNotNull(createWithOptionsClient.lipSyncVideo().create(
              LipSyncVideoParams.builder()
                  .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
                  .mode("lite")
                  .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
                  .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
                  .build(),
          RequestOptions.none()));

      CapturingTransport getTransport = new CapturingTransport("{\"id\":\"task_lip_sync_video\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}]}");
      VolcengineLipSyncClient getClient = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(getTransport).build();
      assertNotNull(getClient.lipSyncVideo().get("task_lip_sync_video"));

      CapturingTransport getWithOptionsTransport = new CapturingTransport("{\"id\":\"task_lip_sync_video_options\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}]}");
      VolcengineLipSyncClient getWithOptionsClient = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(getWithOptionsTransport).build();
      assertNotNull(getWithOptionsClient.lipSyncVideo().get("task_lip_sync_video_options", RequestOptions.none()));

      SequenceTransport runTransport = new SequenceTransport(
          "{\"id\":\"task_lip_sync_video_run\",\"status\":\"processing\"}",
          "{\"id\":\"task_lip_sync_video_run\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}]}");
      VolcengineLipSyncClient runClient = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(runTransport).build();
      CompletedLipSyncVideoResponse runResponse = runClient.lipSyncVideo().run(
              LipSyncVideoParams.builder()
                  .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
                  .mode("lite")
                  .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
                  .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
                  .build(),
          RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).pollingMaxWait(Duration.ofSeconds(1)).build());
      assertNotNull(runResponse);

      SequenceTransport runWithOptionsTransport = new SequenceTransport(
          "{\"id\":\"task_lip_sync_video_run_options\",\"status\":\"processing\"}",
          "{\"id\":\"task_lip_sync_video_run_options\",\"status\":\"completed\",\"videos\":[{\"url\":\"https://file.runapi.ai/generated\"}]}");
      VolcengineLipSyncClient runWithOptionsClient = VolcengineLipSyncClient.builder().apiKey("sk-test").transport(runWithOptionsTransport).build();
      assertNotNull(runWithOptionsClient.lipSyncVideo().run(
              LipSyncVideoParams.builder()
                  .model(LipSyncVideoModel.VOLCENGINE_LIP_SYNC)
                  .mode("lite")
                  .sourceVideoUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-source.mp4")
                  .sourceAudioUrl("https://cdn.runapi.ai/public/samples/volcengine-lip-sync-voice-adam.mp3")
                  .build(),
          RequestOptions.builder().pollingInterval(Duration.ofMillis(1)).pollingMaxWait(Duration.ofSeconds(1)).build()));
    }

  private static JsonNode bodyJson(HttpRequest request) throws Exception {
    JsonRequestBody body = (JsonRequestBody) request.getBody();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    body.writeTo(out);
    return Json.mapper().readTree(out.toByteArray());
  }

  private static final class CapturingTransport implements HttpTransport {
    private final String body;
    private HttpRequest request;

    private CapturingTransport(String body) {
      this.body = body;
    }

    public HttpResponse send(HttpRequest request) {
      this.request = request;
      return new HttpResponse(200, body, Collections.<String, java.util.List<String>>emptyMap());
    }

    public void close() {}
  }

  private static final class SequenceTransport implements HttpTransport {
    private final String[] responses;
    private int calls;

    private SequenceTransport(String... responses) {
      this.responses = responses;
    }

    public HttpResponse send(HttpRequest request) {
      String response = responses[Math.min(calls, responses.length - 1)];
      calls++;
      return new HttpResponse(200, response, Collections.<String, java.util.List<String>>emptyMap());
    }

    public void close() {}
  }
}
