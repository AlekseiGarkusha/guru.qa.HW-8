package tests.hw;

import com.codeborne.pdftest.PDF;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.FileParsingTest;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class HwParsingFiles {
  private ClassLoader cl = FileParsingTest.class.getClassLoader();

  private byte[] readEntry(ZipInputStream zip) throws IOException {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    byte[] buffer = new byte[1024];
    int len;

    while ((len = zip.read(buffer)) != -1) {
      baos.write(buffer, 0, len);
    }

    return baos.toByteArray();
  }

  @Test
  void zipHwTest() throws Exception {
    try (ZipInputStream zip = new ZipInputStream(
      cl.getResourceAsStream("testHW.zip")
    )) {
      ZipEntry entry;

      // проходимся по архиву, до конца, записываем все названия
      while ((entry = zip.getNextEntry()) != null) {
        String entryName = entry.getName();
        byte[] content = readEntry(zip);

        //PDF
        // Если название оканчивается на .pdf - то создадим файл
        if (entryName.endsWith(".pdf")) {

          File testPdf = File.createTempFile("temp", ".pdf");

          try (FileOutputStream fos = new FileOutputStream(testPdf)) {
            fos.write(content);
          }

          PDF pdf = new PDF(testPdf);
          Assertions.assertTrue(
            pdf.text.contains("Электромонтаж в Крыму"),
            "Заголовок в pdf документе не найден"
          );
        }

        // CSV
        // Если название оканчивается на .csv - то создадим файл

        if (entryName.endsWith(".csv")) {
          try (CSVReader csvReader = new CSVReaderBuilder(
            new InputStreamReader(
              new ByteArrayInputStream(content),
              StandardCharsets.UTF_8))
            .withCSVParser(new CSVParserBuilder().withSeparator(';').build())
            .build()) {

            List<String[]> data = csvReader.readAll();

            for (String[] row : data) {
              for (int i = 0; i < row.length; i++) {
                row[i] = row[i].trim();
              }
            }


            Assertions.assertArrayEquals(new String[]{"Username", "Identifier", "First name", "Last name"}, data.get(0));
            Assertions.assertArrayEquals(new String[]{"booker12", "9012", "Rachel", "Booker"}, data.get(1));
            Assertions.assertArrayEquals(new String[]{"grey07", "2070", "Laura", "Grey"}, data.get(2));
            Assertions.assertArrayEquals(new String[]{"johnson81", "4081", "Craig", "Johnson"}, data.get(3));
            Assertions.assertArrayEquals(new String[]{"jenkins46", "9346", "Mary", "Jenkins"}, data.get(4));
            Assertions.assertArrayEquals(new String[]{"smith79", "5079", "Jamie", "Smith"}, data.get(5));
          }
        }

        // xls
        if (entry.getName().endsWith(".xlsx")) {

          File testXlsx = File.createTempFile("temp", ".xlsx");

          try (FileOutputStream fos = new FileOutputStream(testXlsx)) {
            fos.write(content);
          }

          XLS xls = new XLS(testXlsx);
          System.out.println(testXlsx);

          String actualValue = String.valueOf(xls.excel.getSheetAt(0).getRow(0).getCell(0));

          System.out.println("То что ищем в excel:" + actualValue);
          Assertions.assertTrue(actualValue.contains("TestData"));
        }
      }
    }
  }
}










