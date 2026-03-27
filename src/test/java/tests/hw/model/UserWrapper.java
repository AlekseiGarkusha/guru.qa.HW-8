package tests.hw.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserWrapper {

  private List<User> users;

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public static class User {
    private int id;
    private String username;
    private String email;
    private boolean isActive;

    public int getId() {
      return id;
    }

    public void setId(int id) {
      this.id = id;
    }

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getEmail() {
      return email;
    }

    public void setEmail(String email) {
      this.email = email;
    }
    @JsonProperty("isActive")
    public boolean isActive() {
      return isActive;
    }

    public void setActive(boolean active) {
      isActive = active;
    }
  }
}
