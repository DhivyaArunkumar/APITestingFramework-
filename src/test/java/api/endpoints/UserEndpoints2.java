package api.endpoints;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payload.UserPOJO;

import java.util.ResourceBundle;

import static io.restassured.RestAssured.given;

//UserEndpoints.java
//Perform Create, Retrieve, Update and Delete operations
public class UserEndpoints2 {

    static ResourceBundle getURL(){
        ResourceBundle routes = ResourceBundle.getBundle("routes"); //Load Properties File //Name of the Property File
        return routes;
    }

    public static Response createUser(UserPOJO payload){
        String posturl =getURL().getString("postURL");
        Response res = given()
                .contentType(ContentType.JSON)
                                .accept(ContentType.JSON)
                .body(payload)
                .when()
                .post(posturl); //static variable can be directly accessed with class name
        return res;
    }

    public static Response getUser(String username){
        String getURL =getURL().getString("getURL");
        Response res = given()
                .pathParam("username",username)
                .when()
                .get(getURL); //static variable can be directly accessed with class name
        return res;
    }

    public static Response updateUser(String username, UserPOJO payload){
        String updateURL =getURL().getString("updateURL");
        Response res = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .pathParam("username",username)
                .body(payload)
                .when()
                .put(updateURL); //static variable can be directly accessed with class name
        return res;
    }
    public static Response deleteUser(String username){
        String deleteURL =getURL().getString("deleteURL");
        Response res = given()
                .pathParam("username",username)
                .when()
                .delete(deleteURL); //static variable can be directly accessed with class name
        return res;
    }


}
