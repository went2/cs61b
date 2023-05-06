package discussion05;

public class OHRequest {
  public String description;
  public String name;
  public boolean isSetup;
  public OHRequest next;
  public OHRequest(String description, String name, boolean isSetup, OHRequest next) {
    this.description = description;
    this.name = name;
    this.isSetup = isSetup;
    this.next = next;
  }
}