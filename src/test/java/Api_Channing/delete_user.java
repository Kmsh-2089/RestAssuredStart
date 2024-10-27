package Api_Channing;
import static io.restassured.RestAssured.given;

import org.testng.ITestContext;
import org.testng.annotations.Test;
public class delete_user 
{
	@Test
	void test_deleteuser(ITestContext context) 
	{
		String bearertoken="8d2e92769586414469a858bdc9488fc0caee36524a83c79e00ea4a44fc64ee13";
		
		int id=(Integer) context.getSuite().getAttribute("user_id");
	//	int id=(int) contex.getAttribute("user_id");
		
		
		given()
			.headers("Authorization","Bearer "+bearertoken)
			.pathParam("id",id)
		
		.when()
			.delete("https://gorest.co.in/public/v2/users/{id}")
		.then()
			.statusCode(204)
			.log().ifValidationFails();
			
			
		
		
		
	}
	
	
	
	
}
