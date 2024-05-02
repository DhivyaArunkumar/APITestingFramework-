package api.endpoints;

public class Routes {

   public static String baseURL = "https://petstore.swagger.io/v2";

   //USER Module
    public static String postURL = baseURL+"/user";
    public static String getURL = baseURL+"/user/{username}";
    public static String updateURL = baseURL+"/user/{username}";
    public static String deleteURL = baseURL+"/user/{username}";
    public static String csvTestData ="testdata/Userdata.csv";

}
