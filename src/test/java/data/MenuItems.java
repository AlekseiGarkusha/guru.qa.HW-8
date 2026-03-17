package data;

import java.util.List;
import java.util.stream.Stream;

public class MenuItems {

  public static Stream<List<String>> testMenuItems() {
    return Stream.of(
      List.of(
        "Text Box",
        "Check Box",
        "Radio Button",
        "Web Tables",
        "Buttons",
        "Links",
        "Broken Links - Images",
        "Upload and Download",
        "Dynamic Properties"
      ));
  }
}
