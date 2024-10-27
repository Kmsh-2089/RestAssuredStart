package practice;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

	
	public class jsonparse_tovalidate_Responce 
	{
			
	//@Test(priority=1)
	void parsingJsonresponcedata()// 1st way to do parsing
	{
		given()
			.contentType(ContentType.JSON)
		.when()
			.get("https://reqres.in/api/users?page=2")
		.then()
			.statusCode(200)
			.header("Content-Type","application/json; charset=utf-8")
			.body("data[4].email",equalTo("george.edwards@reqres.in"));	
	}
	@Test(priority=2)
	void parsingJsonresponcedata1()// 2ond way to do parsing approch 1
	{
		//Approch 1
		
	Response res=given()
			.contentType(ContentType.JSON)
		.when()
			.get("https://reqres.in/api/users?page=2");
		
		/*Assert.assertEquals(res.getStatusCode(),200);
		Assert.assertEquals(res.header("Content-Type"),"application/json; charset=utf-8");
		
		String email=res.jsonPath().get("data[4].email").toString();
		Assert.assertEquals(email, "george.edwards@reqres.in");*/
		
		//Approch 2 using jsonobject class
	
		JSONObject jo=new JSONObject(res.asString());// convert json object 
		// to get the ids 
		/*for(int i=0;i<jo.getJSONArray("data").length();i++) 
		{
			String ids=jo.getJSONArray("data").getJSONObject(i).get("id").toString();
			System.out.println(ids);
		}*/
		// validating ids in the response
		boolean status=false;
		for(int i=0;i<jo.getJSONArray("data").length();i++) 
		{
			String ids=jo.getJSONArray("data").getJSONObject(i).get("id").toString();
			if(ids.equals("10")) 
			{
				status=true;
				break;
			}
		}
		Assert.assertEquals(status, true);
		
		// validating first_name in the response
		for(int j=0;j<jo.getJSONArray("data").length();j++) 
		{
			String firstName=jo.getJSONArray("data").getJSONObject(j).get("first_name").toString();
			if(firstName.equals("Byron")) 
			{
				status=true;
				break;
			}
			
		}
		
		
		
	}
	
	
	
}
