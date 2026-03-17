package pages.components;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class CalenderComponent {

  public void setDate(int userDay, String userMonth, int userYear) {
    $("#dateOfBirthInput").scrollTo().click();

    $(".react-datepicker").should(appear);

    $(".react-datepicker__month-select").shouldBe(visible).selectOption(userMonth);;
    $(".react-datepicker__year-select").shouldBe(visible).selectOption(String.valueOf(userYear));;
    $(".react-datepicker__day.react-datepicker__day--0" + userDay + "").shouldBe(visible).click();
  }

}
