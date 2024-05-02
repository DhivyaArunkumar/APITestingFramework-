package tests;

import api.endpoints.UserEndpoints;
import api.endpoints.UserEndpoints2;
import com.github.javafaker.Faker;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import payload.UserPOJO;


public class UserTest2 { //This class reads URL from property File

    Faker faker;
    UserPOJO payload;
    public Logger logger;

    @BeforeClass
    public void setUp() {
        faker = new Faker();
        payload = new UserPOJO();
        payload.setId(faker.idNumber().hashCode());
        payload.setUsername(faker.name().username());
        payload.setFirstname(faker.name().firstName());
        payload.setLastname(faker.name().lastName());
        payload.setEmail(faker.internet().safeEmailAddress());
        payload.setPassword(faker.internet().password(5,10)); //password min-length is 5 and max-length is 10
        payload.setPhone(faker.phoneNumber().cellPhone());
        //logs
        logger = LogManager.getLogger(this.getClass());

    }

    @Test(priority = 0)
    public void testPostUser() {
        logger.info("*************Creating User ***********");
        logger.debug("debugging.........................");
        Response response= UserEndpoints2.createUser(payload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
        logger.info("*************user is created ***********");
    }
    @Test(priority = 1)
    public void testGetUser() {
        logger.info("*************Read User ***********");
        Response response = UserEndpoints2.getUser(this.payload.getUsername());
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

    }
    @Test(priority = 3)
    public void testUpdateUser(){
        //Update user data
        logger.info("*************Update Users ***********");
        payload.setFirstname(faker.name().firstName());
        payload.setLastname(faker.name().lastName());
        payload.setEmail(faker.internet().safeEmailAddress());

        Response response = UserEndpoints2.updateUser(this.payload.getUsername(),payload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);

        //verify data after update
        Response responseAfterUpdate = UserEndpoints2.getUser(this.payload.getUsername());
        responseAfterUpdate.then().log().all();
        Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);
    }

    @Test(priority = 4)
    public void testDeleteUser() {
        logger.info("*************Delete Users ***********");
        Response response = UserEndpoints2.deleteUser(this.payload.getUsername());
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

    }
}
