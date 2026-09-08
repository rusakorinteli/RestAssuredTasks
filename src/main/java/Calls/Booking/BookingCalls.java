package Calls.Booking;

import Models.Booking.BookingRequestModel;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BookingCalls {
    public Response addBooking(String firstName, String lastName, int totalPrice){
        Response response = given()
                .contentType("Application/json")
                .body("""
                        {
                            "firstname": "%s",
                            "lastname": "%s",
                            "totalprice": %d,
                            "depositpaid": true,
                            "bookingdates": {
                                "checkin": "2026-09-10",
                                "checkout": "2026-09-15"
                            },
                            "additionalneeds": "Breakfast"
                        }
                        """.formatted(firstName, lastName, totalPrice))
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .extract().response();
        return response;


    }

    public Response getBooking(int id){
        return given()
                .pathParams("id", id)
                .when()
                .get("https://restful-booker.herokuapp.com/booking/{id}")
                .then()
                .extract()
                .response();
    }


    public Response addBookingByModel(BookingRequestModel bookingRequestModel){
        return given()
                .contentType("Application/json")
                .body(bookingRequestModel) // სერიალიზაცია ხდება აქ, აღარ გადავცემთ პირდაპირ ჯეისონს
                .when()
                .post("https://restful-booker.herokuapp.com/booking")
                .then()
                .extract().response();

    }
}
