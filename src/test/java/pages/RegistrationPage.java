package pages;

import com.codeborne.selenide.SelenideElement;
import pages.components.*;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationPage {

  private final CalenderComponent calendar = new CalenderComponent();

  private final SelenideElement
    userFirstNameInput = $("#firstName"),
    userLastNameInput = $("#lastName"),
    userEmailInput = $("#userEmail"),
    userGender = $("#genterWrapper"),

  userNumberInput = $("#userNumber"),
    userSubjects = $("#subjectsInput"),

  hobbySports = $("#hobbies-checkbox-1"),
    hobbyReading = $("#hobbies-checkbox-2"),
    hobbyMusic = $("#hobbies-checkbox-3"),

  userState = $("#react-select-3-input"),
    userCity = $("#react-select-4-input"),
    userUploadPicture = $("#uploadPicture"),
    userCurrentAddress = $("#currentAddress"),

  btnSubmit = $("#submit");
  ;


  public void openPage() {
    open("/automation-practice-form");

  }

  public RegistrationPage typeUserName(String name, String userLastName) {
    userFirstNameInput.setValue(name);
    userLastNameInput.setValue(userLastName);

    return this;
  }

  public RegistrationPage typeUserEmail(String value) {
    userEmailInput.setValue(value);

    return this;
  }

  public RegistrationPage chooseGender(String value) {
    userGender.$(byText(value)).click();

    return this;
  }

  public RegistrationPage typeUserNumber(String value) {
    userNumberInput.setValue(value);

    return this;
  }

  public RegistrationPage setDateOfBirth(int day, String month, int year) {
    calendar.setDate(day, month, year);

    return this;
  }

  public RegistrationPage setUserSubjets(String value) {
    userSubjects.setValue(value).pressEnter();

    return this;
  }

  public RegistrationPage setUserHobbies(String value) {
    switch (value) {
      case "Sports" -> $(hobbySports).click();
      case "Reading" -> $(hobbyReading).click();
      case "Music" -> $(hobbyMusic).click();
    }

    return this;
  }

  public RegistrationPage uploadPicture(String value) {
    userUploadPicture.uploadFromClasspath(value);

    return this;
  }

  public RegistrationPage typeUserCurrentAddress(String value) {
    userCurrentAddress.sendKeys(value);

    return this;
  }

  public RegistrationPage setStateAndCity(String state, String city) {
    userState.setValue(state).pressEnter();
    userCity.setValue(city).pressEnter();

    return this;
  }

  public RegistrationPage clickBtnSubmitWithModalContent() {
    btnSubmit.scrollTo().click();
    $(".modal-content").shouldBe(visible);

    return this;
  }

  public RegistrationPage clickBtnSubmit() {
    btnSubmit.scrollTo().click();

    return this;
  }

}
