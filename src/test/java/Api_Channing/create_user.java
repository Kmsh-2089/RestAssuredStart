package Api_Channing;
import static io.restassured.RestAssured.given;

import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import com.github.javafaker.Faker;

public class create_user 
{
	@Test
	void test_createuser(ITestContext context) 
	{
		Faker faker=new Faker();
		
		JSONObject data=new JSONObject();
		
		data.put("name",faker.name().fullName());
		data.put("gender","male");
		data.put("email",faker.internet().emailAddress());
		data.put("status","inactive");
		
		String bearertoken="8d2e92769586414469a858bdc9488fc0caee36524a83c79e00ea4a44fc64ee13";
		
		int id=given()
			.headers("Authorization","Bearer "+bearertoken)
			.contentType("application/json")
			.body(data.toString())
		.when()
			.post("https://gorest.co.in/public/v2/users")
			.jsonPath().getInt("id");
		
		//contex.setAttribute("user_id",id);
		context.getSuite().setAttribute("user_id",id);

	}
	
}
