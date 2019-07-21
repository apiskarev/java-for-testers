package jft;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.jayway.restassured.RestAssured;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertEquals;


public class RestAssuredTests extends TestBase{


  @BeforeClass
  public void init() {
    RestAssured.authentication = RestAssured.basic("288f44776e7bec4bf44fdfeb1e646490", "");
  }

  @Test
  public void testCreateIssue() {
    skipIfNotFixed(1636);
    Set<Issue> oldIssues = getIssues();
    Issue newIssue = new Issue().withSubject("test subject 21072019").withDescription("test description 21072019");
    int issueId = createIssue(newIssue);
    Set<Issue> newIssues = getIssues();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(newIssues, oldIssues);
  }

  private int createIssue(Issue newIssue) {
    String json = RestAssured.given()
            .params("subject", newIssue.getSubject())
            .params("description", newIssue.getDescription())
            .post("http://bugify.stqa.ru/api/issues.json?limit=500").asString();
    JsonElement parsed = new JsonParser().parse(json);
    return parsed.getAsJsonObject().get("issue_id").getAsInt();
  }

  private Set<Issue> getIssues() {
    String json = RestAssured.get("http://bugify.stqa.ru/api/issues.json?limit=500").asString();
    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {}.getType());
  }

}



