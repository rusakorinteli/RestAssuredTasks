package Steps.Booking;

import Calls.Booking.BookingCalls;
import Models.Booking.BookingDates;
import Models.Booking.BookingRequestModel;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.equalTo;

public class BookingSteps {
    BookingCalls bookingCalls = new BookingCalls();
    int bookingId;
    Response BookingResponse;
    BookingRequestModel bookingRequestModel = new BookingRequestModel();

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
}
