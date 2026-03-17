/**
 * Позитивный тест:
 * - переход к странице формы
 * - проверка на загрузку мормы (нахождение form wrapper)
 * - заполненме всех полей
 * - проверка на открытие результирующей формы - метод поиска title формы
 * - сравнение результирующий полей с вводимыми
 */

package tests;

import data.Gender;
import data.TestData;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import pages.RegistrationPage;
import pages.components.ComparisonFieldsComponent;
import setup.TestBase;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests extends TestBase {

  RegistrationPage registrationPage = new RegistrationPage();
  ComparisonFieldsComponent comparisonFields = new ComparisonFieldsComponent();
  TestData testData = new TestData();

  @BeforeEach
  void setUp() {
    registrationPage
      .openPage();
  }

  @Test
  @Tag("smoke")
  @DisplayName("Позитивный тест с проверкой на соответсвиет полей")
  public void practiceFormTest() {
    registrationPage
      .typeUserName(testData.userFakerFirstName, testData.userFakerLastName)
      .typeUserEmail(testData.userFakerEmail)
      .chooseGender(testData.userFakerGender)
      .typeUserNumber(testData.userFakerNumber)
      .setDateOfBirth(testData.userFakerDay, testData.userFakerMonth, testData.userFakerYear)
      .setUserSubjets(testData.userFakerSubject)
      .setUserHobbies(testData.userFakerHobby)
      .uploadPicture(testData.userPicture)
      .typeUserCurrentAddress(testData.userCurrentAddress)
      .setStateAndCity(testData.userFakerState, testData.userFakerCity)
      .clickBtnSubmit();

    comparisonFields.comparisonFieldsPositiveTest(testData);
  }


  @Test
  @DisplayName("Тест на заполнение только обязательных полей")
  public void onlyRequiredFields() {
    registrationPage
      .typeUserName(testData.userFakerFirstName, testData.userFakerLastName)
      .chooseGender(testData.userFakerGender)
      .typeUserNumber(testData.userFakerNumber)
      .clickBtnSubmitWithModalContent();

    comparisonFields.comparisonRequiredFieldsPositiveTest(testData);
  }

  @ParameterizedTest
  @DisplayName("Тест на невалидные Email + использование Csv")
  @CsvFileSource(resources = "/InvalidEmailsData.csv")
  void testEmailValidation(String email) {
    registrationPage
      .typeUserName(testData.userFakerFirstName, testData.userFakerLastName)
      .chooseGender(testData.userFakerGender)
      .typeUserNumber(testData.userFakerNumber)
      .typeUserEmail(email)
      .clickBtnSubmit();

    comparisonFields.checkingResultingFormIsOpen(testData);
  }

  @ParameterizedTest
  @EnumSource(Gender.class)
  @DisplayName("Проверка тест 'обучение' - перебор Enum")
  void testEnumGender(Gender gender) {
    registrationPage
      .typeUserName(testData.userFakerFirstName, testData.userFakerLastName)
      .typeUserEmail(testData.userFakerEmail)
      .chooseGender(String.valueOf(gender.description))
      .typeUserNumber(testData.userFakerNumber)
      .clickBtnSubmit();

    comparisonFields.CheckFieldsGender(testData, gender);
  }

  @ParameterizedTest
  @MethodSource("data.MenuItems#testMenuItems")
  @DisplayName("Учебная - Проверка на наличие всех элементов меню")
  public void testMenuItems(List<String> expectedItems) {
    $(".header-wrapper").shouldHave(text("Elements")).click();

    for (String item : expectedItems) {
      $$("ul.menu-list li").findBy(text(item)).shouldBe(visible);
    }
  }

}
