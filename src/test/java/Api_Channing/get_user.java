package Api_Channing;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;
public class get_user 
{	
	@Test
	void test_getuser(ITestContext context) 
	{
		int id=(int) context.getSuite().getAttribute("user_id");
		//int id=(int) contex.getAttribute("user_id");
		
		String bearertoken="8d2e92769586414469a858bdc9488fc0caee36524a83c79e00ea4a44fc64ee13";
		
		given()
			.headers("Authorization","Bearer "+bearertoken)
			.pathParam("id",id)
		.when()
			.get("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(200)
			.log().all();
	}
	
	
	
}
