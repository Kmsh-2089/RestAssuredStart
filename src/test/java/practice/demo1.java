package practice;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;
public class demo1 
{
	int id;
	
		@Test(priority=1)
		public void getuser() 
		{
			given()
			
			
			.when()
				.get("https://reqres.in/api/users?page=2")
			
			.then()
				.statusCode(200)
				.body("page",equalTo(2))
				.log().all();			
		}
		
		@Test(priority=2)
		public void createuser() 
		{
			
			HashMap Data=new HashMap();
			Data.put("name","kmsh");
			Data.put("job","QA");
			
			id=given()
				.contentType("application/json")
				.body(Data)
			
			.when()
				.post("https://reqres.in/api/users")
				.jsonPath().getInt("id");
				
			
			//.then()
			//	.statusCode(201)
			//	.log().all();			
		}
		@Test(priority=3,dependsOnMethods= {"createuser"})
		public void updateuser() 
		{

			HashMap Data=new HashMap();
			Data.put("name","kmsh");
			Data.put("job","QA Engineer");
			
			
			given()
				.contentType("application/json")
				.body(Data)
			
			.when()
				.put("https://reqres.in/api/users/"+id)
			
			.then()
				.statusCode(200)
				.log().all();	
		}
		
		@Test(priority=4)
		public void Deleteuser() 
		{
			given()
				
			
			.when()
				.delete("https://reqres.in/api/users/"+id)
			
			.then()
				.statusCode(204)
				.log().all();
				
		}
		
	
}
