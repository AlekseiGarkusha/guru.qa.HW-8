package tests.model;

import com.google.gson.annotations.SerializedName;

public class GlossaryInner {

  @SerializedName("GlossTerm")
  private String glossTerm;
  @SerializedName("Acronym")
  private String acronym;
  @SerializedName("Abbrev")
  private String abbrev;

  public String getGlossTerm() {
    return glossTerm;
  }

  public void setGlossTerm(String glossTerm) {
    this.glossTerm = glossTerm;
  }

  public String getAcronym() {
    return acronym;
  }

  public void setAcronym(String acronym) {
    this.acronym = acronym;
  }

  public String getAbbrev() {
    return abbrev;
  }

  public void setAbbrev(String abbrev) {
    this.abbrev = abbrev;
  }
}
