package jft;


import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import org.testng.SkipException;

public class TestBase {

  public boolean isIssueOpen(int issueId) {
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues/" + issueId + ".json?limit=500").asString();
    JsonElement parse = new JsonParser().parse(json);
    JsonElement issues = parse.getAsJsonObject().get("issues");
    JsonElement issue = issues.getAsJsonArray().get(0);
    String issue_state = issue.getAsJsonObject().get("state_name").toString();
    return issue_state.contains("Open");
  }

  public void skipIfNotFixed(int issueId) {
    if (isIssueOpen(issueId)) {
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

}
