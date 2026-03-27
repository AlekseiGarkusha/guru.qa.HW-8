package tests.hw;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tests.hw.model.UserWrapper;
import tools.jackson.databind.ObjectMapper;

import java.io.InputStream;
import java.util.List;

public class JsonParsingTest {

  @Test
  void jsonParsingTest() throws Exception {

    ObjectMapper mapper = new ObjectMapper();
    InputStream is = getClass().getClassLoader().getResourceAsStream("hwJson.json");
    Assertions.assertNotNull(is, "Файл JSON не найден");

    UserWrapper wrapper = mapper.readValue(is, UserWrapper.class);
    List<UserWrapper.User> usersList = wrapper.getUsers();

    for (UserWrapper.User el : usersList) {
      switch (el.getId()) {
        case 1:
          Assertions.assertEquals("alex91", el.getUsername());
          Assertions.assertEquals("alex91@test.com", el.getEmail());
          Assertions.assertTrue(el.isActive());
          break;
        case 2:
          Assertions.assertEquals("maria_dev", el.getUsername());
          Assertions.assertEquals("maria@test.com", el.getEmail());

          Assertions.assertFalse(el.isActive());
          break;
        case 3:
          Assertions.assertEquals("ivan_admin", el.getUsername());
          Assertions.assertEquals("ivan@test.com", el.getEmail());
          Assertions.assertTrue(el.isActive());

          break;
        default:
          Assertions.fail("Неожиданный пользователь с id=" + el.getId());
      }
    }
  }

}
