package tests;

import api.endpoints.Routes;
import api.endpoints.UserEndpoints;
import com.opencsv.CSVReader;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utilities.CSVUtility;
import payload.UserPOJO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

public class UserDataDrivenTesting {

  /*  @Test(dataProvider = "userData", dataProviderClass = CSVUtility.class)
    public void testUserCreation(String[] userData) {
        // Extracting data from the CSV row
        String userId = userData[0];
        String userName = userData[1];
        String firstName = userData[2];
        String lastName = userData[3];
        String email = userData[4];
        String password = userData[5];
        String phone = userData[6];

        // Your test logic here, using the extracted user data
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + userName);
        System.out.println("First Name: " + firstName);
        System.out.println("Last Name: " + lastName);
        System.out.println("Email: " + email);
        System.out.println("Password: " + password);
        System.out.println("Phone: " + phone);
    }*/
    @Test(priority = 0,dataProvider = "userData", dataProviderClass = CSVUtility.class)
    public void testPostUser(String[] userData) {
        String userId = userData[0];
        String userName = userData[1];
        String firstName = userData[2];
        String lastName = userData[3];
        String email = userData[4];
        String password = userData[5];
        String phone = userData[6];

        // Create UserPOJO instance
        UserPOJO userpayload = new UserPOJO();
        userpayload.setUsername(userName);
        userpayload.setFirstname(firstName);
        userpayload.setLastname(lastName);
        userpayload.setEmail(email);
        userpayload.setPassword(password);
        userpayload.setPhone(phone);

        Response response= UserEndpoints.createUser(userpayload);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(),200);
    }

    @Test(priority = 2,dataProvider = "userData", dataProviderClass = CSVUtility.class)
    public void testDeleteUser(String[] userData) {
        String userName = userData[1];


        Response response = UserEndpoints.deleteUser(userName);
        response.then().log().body();
        Assert.assertEquals(response.getStatusCode(),200);

    }

}
