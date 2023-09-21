import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.fasterxml.jackson.databind.*;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.files.LoginRequest;
import com.files.LoginResponse;
import com.files.OrderDetails;
import com.files.Orders;

public class EcommerceAPITestes {
	
	
	public String token="";
	public String userID="";
	public String productId="";
	
	@BeforeTest
	public void Practice() {
		

		RequestSpecification rs=new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
		.setContentType(ContentType.JSON).build();
		
		LoginRequest loginRequest=new LoginRequest();
		
		loginRequest.setUserEmail("pradeep.chandra326@gmail.com");
		loginRequest.setUserPassword("India#1947");
		
		RequestSpecification reqSpec=given().log().all().spec(rs).body(loginRequest);
		
		LoginResponse LoginResponse=reqSpec.when().post("/api/ecom/auth/login").then().log().all().extract().response().as(LoginResponse.class);
		
		System.out.println("the response is   " + LoginResponse);
		
		token=LoginResponse.getToken();
		userID=LoginResponse.getUserId();
	
	
	}
	@Test(priority=1)
	public void addProduct() {
		
		RequestSpecification rsForAddProduct=new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
				.addHeader("Authorization",token ).build();
		
		
		RequestSpecification reqForAddProduct=given().log().all().spec(rsForAddProduct)
		.param("productName", "Juice glass")
		.param("productAddedBy", userID)
		.param("productCategory", "Kitchen wear")
		.param("productSubCategory", "glass")
		.param("productPrice", "100")
		.param("productDescription", "100ml Glass for juice")
		.param("productFor", "both")
		.multiPart("productImage", new File("//Users//pradeep//Downloads//sp 111.png"));
		
		String response=reqForAddProduct.when().log().all().post("/api/ecom/product/add-product")
		.then().extract().response().asString();
		
		JsonPath js=new JsonPath(response);
		 productId=js.get("productId");
		
		System.out.println(productId);
		
	}
	@Test(priority=2)
	public void placeOrder() {
		
		RequestSpecification rs=new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
				.addHeader("Authorization",token ).setContentType(ContentType.JSON).build();
		OrderDetails OrderDetails=new OrderDetails();
		OrderDetails.setCountry("India");
		OrderDetails.setProductOrderedId(productId);
		
		List<OrderDetails> OrderDetailsList=new ArrayList<OrderDetails>();
		OrderDetailsList.add(OrderDetails);
		
		Orders orders=new Orders();
		
		orders.setOrders(OrderDetailsList);
		
		
		
		
		
		
		
		RequestSpecification placeOrder=given().log().all().spec(rs).body(orders);
				
		String response=	placeOrder.when().log().all().post("/api/ecom/order/create-order")
		.then().extract().response().asString();
		
		
		
		
	}
	@Test(priority=3)
	public void deleteProduct() {
		
		RequestSpecification deleteBaseRequest=new RequestSpecBuilder().setBaseUri("https://www.rahulshettyacademy.com")
				.addHeader("Authorization",token ).setContentType(ContentType.JSON).build();
		
		
		RequestSpecification DeleteRequest=given().log().all().spec(deleteBaseRequest).pathParam("productId",productId);
		
		
	String response=	DeleteRequest.when().log().all().delete("/api/ecom/product/delete-product/{productId}").then().extract().response().asString();
	
	System.out.println("Delete response is " +response);
	}
	
	


}
