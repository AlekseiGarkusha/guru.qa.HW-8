package tests.hw;

import com.codeborne.pdftest.PDF;

import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
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

  private byte[] readEntry(String zipFileName) throws Exception {
    try (ZipInputStream zis = new ZipInputStream(
      cl.getResourceAsStream("testHW.zip")
    )) {
      ZipEntry entry;
      while ((entry = zis.getNextEntry()) != null) {
        if (entry.getName().equals(zipFileName)) {
          return zis.readAllBytes();
        }
        zis.closeEntry();
      }
    }
    throw new IllegalArgumentException("Файл не найден в архиве: " + zipFileName);
  }

  @Test
  void parsingPdfFromZip() throws Exception {
    byte[] content = readEntry("Price-Crimea-Electric-2025.pdf");
    System.out.println("Тут отображается название файла: " + content);
    PDF pdf = new PDF(new ByteArrayInputStream(content));
    Assertions.assertTrue(pdf.text.contains("Электромонтаж в Крыму"));
  }

  @Test
  void parsingCsvFromZip() throws Exception {
    byte[] content = readEntry("username.csv");

    System.out.println("Тут отображается название файла: " + content);
    try (CSVReader csvReader = new CSVReader(
      new InputStreamReader(
        new ByteArrayInputStream(content),
        StandardCharsets.UTF_8))) {

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
}

//  @Test
//  void parsingXlsFromZip() throws Exception {
//    byte[] content = readEntry("testXlsx.xlsx");
//    System.out.println("Тут отображается название файла: " + content);
//    XLS xls = new XLS(new ByteArrayInputStream(content));
//
//    String actualValue = String.valueOf(xls.excel.getSheetAt(0).getRow(0).getCell(0));
//    Assertions.assertTrue(actualValue.contains("TestData"));
//  }





