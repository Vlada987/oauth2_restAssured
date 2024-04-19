package testPack;

import org.testng.Assert;
import org.testng.annotations.Test;

import endpoints.Methods;
import io.restassured.response.Response;

public class Tests {
	
//@Test	
public void getOauth2TokenTest() {
	
Response res=Methods.getOauth2Token();
res.then().log().all();
}

//@Test
public void gettingTheData() {
	
Response res= Methods.passingTokenAndGetUrl2();
res.then().log().all();
int statusCode=res.getStatusCode();
Assert.assertEquals(statusCode, 200);
}

}
