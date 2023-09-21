import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.files.jsonFile_one;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.*;

public class DynamicJson {
	
	
	@Test(dataProvider="BookData")
	public void addbook(String aisel_name,String isbn_number) {
		 RestAssured.baseURI="http://216.10.245.166";

		String response1=given().header("Content-Type", "application/json")
		.body(jsonFile_one.addBookPayload(aisel_name,isbn_number))
		.when()
		.post("Library/Addbook.php")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		
		System.out.println(response1);
		//JsonPath js=rawtoJson(response1)
	
	}
	
	@DataProvider(name="BookData")
	public Object[][] getData(){
		
		return new Object[][] {{"12988","abouuu"},{"12999","uytgr"},{"12966","uygggfr"}	};
	}

}
