package tests.model;

import com.google.gson.annotations.SerializedName;

public class Glossary {

  private String title;
  @SerializedName("ID")
  private Integer id;
  private GlossaryInner glossary;

  public String getTitle() {
    return title;
  }
  public Integer getID() {
    return id;
  }
  public GlossaryInner getGlossary() {
    return glossary;
  }

  public void setTitle(String title) {this.title = title;}
  public void setID(Integer id) {
    this.id = id;
  }
  public void setGlossary(GlossaryInner glossary) {
    this.glossary = glossary;
  }
}
