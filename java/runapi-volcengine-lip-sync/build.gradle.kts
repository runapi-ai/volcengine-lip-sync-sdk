plugins {
  `java-library`
  `maven-publish`
}

extra["runapiSlug"] = "volcengine-lip-sync"

description = "RunAPI Volcengine Lip Sync Java SDK for Volcengine Lip Sync workflows."

java {
  withSourcesJar()
  withJavadocJar()
}

dependencies {
  api("ai.runapi:runapi-core:0.2.6")

  testImplementation(platform("org.junit:junit-bom:5.10.3"))
  testImplementation("org.junit.jupiter:junit-jupiter")
}

publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      from(components["java"])
      artifactId = "runapi-volcengine-lip-sync"
      pom {
        name = "RunAPI Volcengine Lip Sync Java SDK"
        description = "RunAPI Volcengine Lip Sync Java SDK for Volcengine Lip Sync workflows."
        url = "https://runapi.ai/models/volcengine-lip-sync"
        licenses {
          license {
            name = "Apache License, Version 2.0"
            url = "https://www.apache.org/licenses/LICENSE-2.0"
          }
        }
        developers {
          developer {
            id = "runapi"
            name = "RunAPI"
            email = "contact@runapi.ai"
          }
        }
        scm {
          url = "https://github.com/runapi-ai/volcengine-lip-sync-sdk"
          connection = "scm:git:https://github.com/runapi-ai/volcengine-lip-sync-sdk.git"
          developerConnection = "scm:git:ssh://git@github.com/runapi-ai/volcengine-lip-sync-sdk.git"
        }
      }
    }
  }
}
