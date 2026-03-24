package tests;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class WorkToFile {

  @Test
  void downloadFileTest() throws IOException {
    open("https://github.com/junit-team/junit-framework/blob/main/README.md");

    File downloaded =
      $("[href='https://github.com/junit-team/junit-framework/raw/refs/heads/main/README.md']").download();

    // I Метод, который происходит под капотом
    try (InputStream is = new FileInputStream(downloaded)) {
      byte[] data = is.readAllBytes();
      String dataAsString = new String(data, StandardCharsets.UTF_8);
      Assertions.assertTrue(dataAsString.contains("Contributions to JUnit are both welcomed and appreciated"));
    }
    // II   Метод, современный - сокращённый
    //    String dataAsString = FileUtils.readFileToString(downloaded, StandardCharsets.UTF_8);
  }

  @Test
  void uploadFileTest() {
    open("https://clideo.com/ru/compress-video");

    $("input[type=file]").uploadFromClasspath("recording.webm");
    $(byText("recording.webm")).shouldBe(visible, Duration.ofSeconds(20));

  }
}



