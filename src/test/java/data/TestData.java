package data;

import com.github.javafaker.Faker;

import java.util.Locale;

public class TestData {

  public Faker faker = new Faker();
  public Faker fakerRu = new Faker(new Locale("ru"));

  public final String
    userFakerFirstName = fakerRu.name().firstName(),
    userFakerLastName = fakerRu.name().lastName(),
    userFakerEmail = faker.internet().emailAddress(),
    userFakerGender = faker.options().option("Male", "Female", "Other"),
    userFakerNumber = faker.phoneNumber().subscriberNumber(10),
    userFakerMonth = faker.options().option(
      "January", "February", "March", "April",
      "May", "June", "July", "August",
      "September", "October", "November", "December"
    ),
    userFakerSubject = faker.options().option("History", "English", "Maths"),
    userFakerHobby = faker.options().option("Sports", "Reading", "Music"),

    userFakerState,
    userFakerCity,
    userPicture = "Code.png",
    userCurrentAddress = faker.address().fullAddress();

  public final int
    userFakerYear = faker.number().numberBetween(1991, 2026),
    userFakerDay = faker.number().numberBetween(11, 28);

  public TestData() {
    userFakerState = faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");

    switch (userFakerState) {
      case "NCR" -> userFakerCity = faker.options().option("Delhi", "Gurgaon", "Noida");
      case "Uttar Pradesh" -> userFakerCity = faker.options().option("Agra", "Lucknow", "Merrut");
      case "Haryana" -> userFakerCity = faker.options().option("Karnal", "Gurgaon", "Panipat");
      case "Rajasthan" -> userFakerCity = faker.options().option("Jaipur", "Jaiselmer", "Udaipur");
      default -> userFakerCity = "Delhi";
    }

  }
}
