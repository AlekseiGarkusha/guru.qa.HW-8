package pages.components;

import com.codeborne.selenide.SelenideElement;
import data.Gender;
import data.TestData;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class ComparisonFieldsComponent {

  public void checkField(String fieldName, String expectedValue) {
    $(".table-responsive")
      .$(byText(fieldName))
      .parent()
      .shouldHave(partialText((expectedValue)));
  }

  public void comparisonFieldsPositiveTest(TestData testData) {
    $(byText("Thanks for submitting the form")).shouldBe(visible.because("❌ Форма с результирующими данными пользователя - не загрузилась"));

      checkField("Student Name", (testData.userFakerFirstName + " " + testData.userFakerLastName));
      checkField( "Student Email", testData.userFakerEmail);
      checkField("Gender", (testData.userFakerGender));
      checkField("Mobile",(testData.userFakerNumber));
      checkField("Date of Birth",testData.userFakerDay + " " + testData.userFakerMonth + "," + testData.userFakerYear);
      checkField("Subjects",(testData.userFakerSubject));
      checkField("Hobbies",(testData.userFakerHobby));
      checkField("Picture",(testData.userPicture));
      checkField("Address",(testData.userCurrentAddress));
      checkField("State and City",(testData.userFakerState + " " + testData.userFakerCity));
  }

  public void comparisonRequiredFieldsPositiveTest(TestData testData) {
    $(byText("Thanks for submitting the form")).shouldBe(visible.because("❌ Форма с результирующими данными пользователя - не загрузилась"));
    checkField("Student Name", (testData.userFakerFirstName + " " + testData.userFakerLastName));
    checkField("Gender", (testData.userFakerGender));
    checkField("Mobile",(testData.userFakerNumber));
  }

  public void CheckFieldsGender(TestData testData, Gender gender) {
    $(byText("Thanks for submitting the form")).shouldBe(visible.because("❌ Форма с результирующими данными пользователя - не загрузилась"));
    checkField("Student Name", (testData.userFakerFirstName + " " + testData.userFakerLastName));
    checkField("Gender", (String.valueOf(gender)));
    checkField("Mobile",(testData.userFakerNumber));
  }

  public void checkingResultingFormIsOpen(TestData testData) {
    SelenideElement titleResultForm =
      $(byText("Thanks for submitting the form"));

    if (!titleResultForm.exists()){
     return;
    };
  }

}


