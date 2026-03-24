  /* Расширяющий класс для:
   * Вход в систему
   * Открытие страницы
   * Закрытие страницы
   */

  package setup;

  import com.codeborne.selenide.Configuration;
  import com.codeborne.selenide.TextCheck;
  import org.junit.jupiter.api.AfterAll;
  import org.junit.jupiter.api.BeforeAll;
  import org.openqa.selenium.chrome.ChromeOptions;

  import static com.codeborne.selenide.Configuration.baseUrl;
  import static com.codeborne.selenide.Selenide.closeWebDriver;
  import static com.codeborne.selenide.Selenide.open;

  public class TestBase {

    @BeforeAll
    public static void setup() {
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--remote-allow-origins=*");

      Configuration.browser = "chrome";
//      Configuration.headless = false;
//      Configuration.holdBrowserOpen = true;
      Configuration.reportsFolder = "target/selenide-reports";
      Configuration.timeout = 5000;
      Configuration.textCheck = TextCheck.FULL_TEXT;
      Configuration.browserCapabilities = options;
      Configuration.browserSize = "1920x1080";
      Configuration.baseUrl = "https://demoqa.com";
    }

    @AfterAll
    public static void tearDown() {
      closeWebDriver();
    }
  }

