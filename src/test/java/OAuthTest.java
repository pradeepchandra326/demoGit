
import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;


public class OAuthTest {
	
	@Test
	public void getdata() {
		
		String AccessTokenresponse=given().queryParams("code","")
		.queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type", "authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token?code=4")
		.asString();
		
		JsonPath js= new JsonPath(AccessTokenresponse);
		String accessToken=js.getString("acess_token");
		
		
		
		String response =given().queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php")
		.asString();
		
		
		
		System.out.println(response);
	}

}
