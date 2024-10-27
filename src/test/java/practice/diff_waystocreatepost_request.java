package practice;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class diff_waystocreatepost_request 
{
	//1) Using HashMAp
	//@Test(priority=1)
	public void createuser() 
	{
		HashMap data=new HashMap();
		data.put("name", "nice");
		data.put("job","QA");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post(" https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("nice"))
			.body("job",equalTo("QA"))
			.log().all();
			
	}
	
	//2)using org.json
	//@Test(priority=2)
	public void postreq_orgjson() 
	{
		JSONObject data= new JSONObject();
			data.put("name","new");
			data.put("job","Dev");
		
		given()
			.contentType("application/json")
			.body(data.toString())// need to convert the json in to string
		.when()
			.post(" https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("new"))
			.body("job",equalTo("Dev"))
			.log().all();		
	}
	//3) using POJO class
	//@Test(priority=3)
	public void postreq_pojoclass() 
	{
		POJOclass_post data= new POJOclass_post();
				data.setName("geet");
				data.setJob("germanProfessor");
		
		given()
			.contentType("application/json")
			.body(data)
		.when()
			.post(" https://reqres.in/api/users")
		.then()
			.statusCode(201)
			.body("name",equalTo("geet"))
			.body("job",equalTo("germanProfessor"))
			.log().all();		
	}
	//@Test(priority=4)
	public void deleteuser() 
	{
		given()
		.when()
			.delete("https://reqres.in/api/users/232")
		.then()
		.statusCode(204);
	}
	//4)using external json file 
		//@Test(priority=5)
		public void postreq_externalfile() throws FileNotFoundException
		{
			File f= new File(".//body.json");//need to pass the path of jon file to open 
			FileReader fr=new FileReader(f);//to read the data from file 
			JSONTokener jt=new JSONTokener(fr);// need to create the token 
			JSONObject data=new JSONObject(jt);
			
			given()
				.contentType("application/json")
				.body(data.toString())// need to convert the json in to string 
			.when()
				.post(" https://reqres.in/api/users")
			.then()
				.statusCode(201)
				.body("name",equalTo("geet"))
				.body("job",equalTo("germanProfessor"))
				.log().all();		
		}
		@Test(priority=6)
		public void deleteuser1() 
		{
			given()
			.when()
				.delete("https://reqres.in/api/users/148")
			.then()
			.statusCode(204);
		}
	
}
