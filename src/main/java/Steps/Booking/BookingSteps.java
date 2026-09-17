package Steps.Booking;

import Calls.Booking.BookingCalls;
import Models.Booking.BookingDates;
import Models.Booking.BookingRequestModel;
import io.restassured.response.Response;
import org.testng.Assert;

import static org.hamcrest.Matchers.equalTo;

public class BookingSteps {
    BookingCalls bookingCalls = new BookingCalls();
    int bookingId;
    Response BookingResponse;
    BookingRequestModel bookingRequestModel = new BookingRequestModel();
    BookingRequestModel bookingResponseModel;

    public BookingSteps addBooking(){
        BookingResponse = bookingCalls.addBooking("Rusa", "Korinteli", 500);
        return this;

    }

    public BookingSteps statusCodeChecks(){
        BookingResponse
                .then()
                .statusCode(200);
        return this;

    }

    public BookingSteps getBookingId(){
        bookingId = BookingResponse
                .jsonPath()
                .getInt("bookingid");
        return this;
    }

    //methods for task 2

    public BookingSteps addBookingWithModel() {

        BookingDates bookingDates = new BookingDates();

        bookingDates.checkin = "2026-09-10";
        bookingDates.checkout = "2026-09-15";

        bookingRequestModel.firstname = "Rusa";
        bookingRequestModel.lastname = "Korinteli";
        bookingRequestModel.totalprice = 500;
        bookingRequestModel.depositpaid = true;
        bookingRequestModel.bookingdates = bookingDates;
        bookingRequestModel.additionalneeds = "Breakfast";

        BookingResponse = bookingCalls.addBookingByModel(bookingRequestModel);

        return this;
    }

    public BookingSteps checkBookingData(){
        BookingResponse
                .then()
                .body("booking.firstname", equalTo(bookingRequestModel.firstname))
                .body("booking.lastname", equalTo(bookingRequestModel.lastname))
                .body("booking.totalprice", equalTo(bookingRequestModel.totalprice))
                .body("booking.depositpaid", equalTo(bookingRequestModel.depositpaid))
                .body("booking.bookingdates.checkin",
                        equalTo(bookingRequestModel.bookingdates.checkin))
                .body("booking.bookingdates.checkout",
                        equalTo(bookingRequestModel.bookingdates.checkout))
                .body("booking.additionalneeds",
                        equalTo(bookingRequestModel.additionalneeds));

        return this;
    }


    // task 3

    public BookingSteps getBooking(){
        BookingResponse = bookingCalls.getBooking(bookingId);
        return this;
    }

    public BookingSteps checkBookingDataWithJsonPath(){
        String firstName = BookingResponse.jsonPath().getString("firstname");
        String lastName = BookingResponse.jsonPath().getString("lastname");
        int totalPrice = BookingResponse.jsonPath().getInt("totalprice");
        String aditonalNeeds = BookingResponse.jsonPath().getString("additionalneeds");

        Assert.assertEquals(firstName, bookingRequestModel.firstname);
        Assert.assertEquals(lastName, bookingRequestModel.lastname);
        Assert.assertEquals(totalPrice, bookingRequestModel.totalprice);
        Assert.assertEquals(aditonalNeeds, bookingRequestModel.additionalneeds);

        return this;
    }

    //Task 4

    public BookingSteps bookingResponseDeserialization(){
        bookingResponseModel = BookingResponse.as(BookingRequestModel.class);
        return this;
    } // აქ ხდება დესერიალიზაცია ანუ ჯესიონის რესპონსი გარდაიქმნება ჯავა ობიექტად


    public BookingSteps checkDataAfterDeserialization(){
        Assert.assertEquals(bookingResponseModel.firstname, bookingRequestModel.firstname);
        Assert.assertEquals(bookingResponseModel.lastname, bookingRequestModel.lastname);
        Assert.assertEquals(bookingResponseModel.totalprice, bookingRequestModel.totalprice);
        Assert.assertEquals(bookingResponseModel.additionalneeds, bookingRequestModel.additionalneeds);
        Assert.assertEquals(bookingResponseModel.bookingdates.checkin, bookingRequestModel.bookingdates.checkin);
        Assert.assertEquals(bookingResponseModel.bookingdates.checkout, bookingRequestModel.bookingdates.checkout);

        return this;
    }


}
