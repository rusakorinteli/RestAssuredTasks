package Calls.ReqRess;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class UserCalls {
    public Response getUsers(int page) {

        return given()
                .queryParam("page", page)
                .when()
                .get("https://reqres.in/api/users")
                .then()
                .extract()
                .response();
    }
}
