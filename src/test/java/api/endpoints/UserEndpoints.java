package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.UserPOJO;

import static io.restassured.RestAssured.given;

//UserEndpoints.java
//Perform Create, Retrieve, Update and Delete operations
public class UserEndpoints {

    public static Response createUser(UserPOJO payload){
        Response res = given()
                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(Routes.postURL); //static variable can be directly accessed with class name
        return res;
    }

    public static Response getUser(String username){
        Response res = given()
                .pathParam("username",username)
                .when()
                .get(Routes.getURL); //static variable can be directly accessed with class name
        return res;
    }

    public static Response updateUser(String username, UserPOJO payload){
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(Routes.updateURL); //static variable can be directly accessed with class name
        return res;
    }
    public static Response deleteUser(String username){
        Response res = given()
                .pathParam("username",username)
                .when()
                .delete(Routes.deleteURL); //static variable can be directly accessed with class name
        return res;
    }


}
