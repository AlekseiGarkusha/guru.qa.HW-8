package tests;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.json.Json;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class FileParsingTest {

  private ClassLoader cl = FileParsingTest.class.getClassLoader();
  private static final Gson gson = new Gson();

  @Test
  void pdfFileParsingTest() throws Exception {

    open("https://docs.junit.org/5.7.0/user-guide/");

    File downloaded = $("[href*='junit-user-guide-5.7.0.pdf']").download();
    InputStream is = new FileInputStream(downloaded);
    PDF pdf = new PDF(downloaded);

    Assertions.assertEquals("Stefan Bechtold, Sam Brannen, Johannes Link, Matthias Merdes, Marc Philipp, Juliette de Rancourt, Christian Stein", pdf.author);
    System.out.println(downloaded);


    //  href="/images/Upload/Price-Crimea-Electric-2025.pdf"
  }

  @Test
  void xlsFileParsingTest() throws Exception {

    open("https://yandex.ru/routing/doc/ru/vrp/example");

    File downloaded = $("[href='https://doc-static.yandex.net/src/dev/routing/templates/mvrp-simple-example-ru.xlsx']").download();
    InputStream is = new FileInputStream(downloaded);
    XLS xls = new XLS(downloaded);
    String actualValue = xls.excel.getSheetAt(0).getRow(0).getCell(0).getStringCellValue();

    Assertions.assertTrue(actualValue.contains("Идентификатор заказа"));
  }

  @Test
  void csvFileParsingTest() throws Exception {

    try (InputStream is = cl.getResourceAsStream("InvalidEmailsData.csv")) {
      CSVReader csvReader = new CSVReader(new InputStreamReader(is));

      List<String[]> data = csvReader.readAll();

      Assertions.assertEquals(6, data.size());

      Assertions.assertArrayEquals(
        new String[]{"test@domain", " invalid"},
        data.get(0)
      );

      Assertions.assertArrayEquals(
        new String[]{"no-at-symbol.com", " invalid"},
        data.get(1)
      );

      Assertions.assertArrayEquals(
        new String[]{"testya@ya.", " invalid"},
        data.get(2)
      );

      Assertions.assertArrayEquals(
        new String[]{"test@@ya.ru", " invalid"},
        data.get(3)
      );

      Assertions.assertArrayEquals(
        new String[]{"@@ya.ru", " invalid"},
        data.get(4)
      );

      Assertions.assertArrayEquals(
        new String[]{"Test@.ru", " invalid"},
        data.get(5)
      );
    }
  }

  @Test
  void zipFileParsingTest() throws Exception {
    try (ZipInputStream zip = new ZipInputStream(
      cl.getResourceAsStream("test.zip")
    )) {
      ZipEntry entry;


      while ((entry = zip.getNextEntry()) != null) {
        System.out.println(entry.getName());


        // Домашка - распаковать зип, и прочитать файл.
      }
    }
  }

  @Test
  void jsonFileParsingTest() throws Exception {

    try (
      Reader reader = new InputStreamReader(cl.getResourceAsStream("test.json")
      )) {
      JsonObject actual = gson.fromJson(reader, JsonObject.class);

      Assertions.assertEquals("example glossary", actual.get("title").getAsString());
      Assertions.assertEquals(456, actual.get("ID").getAsInt());

      JsonObject inner = actual.get("glossary").getAsJsonObject();

      Assertions.assertEquals("Standard Generalized Markup Language", inner.get("GlossTerm").getAsString());
      Assertions.assertEquals("SGML", inner.get("Acronym").getAsString());
      Assertions.assertEquals("ISO 8879:1986", inner.get("Abbrev").getAsString());
    }

    // домашка - сделать с библиотекой jackson
  }
}
