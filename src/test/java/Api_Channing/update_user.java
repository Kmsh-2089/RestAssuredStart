package Api_Channing;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;
public class update_user 
{
	@Test
	void test_updateuser(ITestContext context) 
	{
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		
		data.put("name",faker.name().fullName());
		data.put("gender","male");
		data.put("email",faker.internet().emailAddress());
		data.put("status","active");
		
		String bearertoken="8d2e92769586414469a858bdc9488fc0caee36524a83c79e00ea4a44fc64ee13";
		
		int id=(Integer) context.getSuite().getAttribute("user_id");
		//int id=(int) contex.getAttribute("user_id");
		
		given()
			.headers("Authorization","Bearer "+bearertoken)
			.contentType("application/json")
			.body(data.toString())
			.pathParam("id",id)
		.when()
			.put("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
		
	}

}
