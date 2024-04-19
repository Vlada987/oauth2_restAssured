package endpoints;
import static io.restassured.RestAssured.*;

import api.Routes;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Methods {
	
public static String client_ID="XXXe2EyF1nc1AazUvV6Yv8FajpYkU-BWwvZ8aBA1qLrYzopWncBGLkhWHKfSKgLHkA0MgprkCnTg8VRm";
public static String client_Secret="XXX3wC0sJZKC3zot-uEXyGpTbPUWwNzm_Vv9mPcfUvIjyrv53RO5Lpbrm9EuJ42hD8QiXwPqhjGvxMcT";

public static Response getOauth2Token() {
	
Response res= given().auth().preemptive()
.basic(client_ID, client_Secret)
.formParam("grant_type", "client_credentials")
.when().post(Routes.reqTokenUrl);

return res;	
}

public static String getTokenAsString() {
	
Response res= getOauth2Token();
return res.jsonPath().get("access_token").toString();
}

public static Response passingTokenAndGetUrl() {
	
String token= getTokenAsString();
Response res= given().header("Authorization","Bearer "+token)
.header("Content-Type","application/json")
.when().get(Routes.invTemplatesUrl);
return res; 
}

//Other way to get Oauth2 Token and getURL with RequestSpec
//**********************************************************

public static Response getOauth2Token2() {

RestAssured ras=new RestAssured();
ras.baseURI=Routes.reqTokenUrl;
RequestSpecification rspec=ras.given();
rspec.auth().preemptive().basic(client_Secret, client_ID);
rspec.param("grant_type", "client_credentials");
Response res=rspec.post();
return res;
}
	
public static String getTokenAsString2() {
	
Response res=getOauth2Token2();
String token=res.jsonPath().getString("access_token");
return token;
}

public static Response passingTokenAndGetUrl2() {

String token=getTokenAsString2();	
RestAssured ras=new RestAssured();
ras.baseURI=Routes.invTemplatesUrl;
RequestSpecification rspec=ras.given();
rspec.header("Authorization","Bearer "+token);
rspec.header("Content-Type","application/json");
Response res=rspec.get();
return res;
}



} 
