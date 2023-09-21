import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.files.AddPlaceDetails;
import com.files.location;

import io.restassured.RestAssured;




public class Serialisation {
	
	@Test
	public void addPlace() {
		
		AddPlaceDetails apd=new AddPlaceDetails();
		
		apd.setAccuracy("50");
		apd.setAddress("dummy adress");
		apd.setLanguage("Telugu");
		apd.setName("Testing address");
		apd.setPhone_number("8787676754");
		apd.setWebsite("test@gmail.com");
		
		List<String> myList=new ArrayList();
		myList.add("park");
		myList.add("benchmark");
		myList.add("airport");		
		
		
		apd.setType(myList);
		
		location l=new location();
		l.setLat(100.1);
		l.setLng(22.21);
		
		apd.setLocation(l);
		
		
		RestAssured.baseURI="https://www.rahulshettyacademy.com";
		
		String response=given().queryParam("key", "qaclick123")
		.body(apd)
		.when()
		.post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.extract().response().asString();
		
		System.out.println(response);
		
		
		
	}

}
