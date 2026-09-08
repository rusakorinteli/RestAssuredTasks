package Steps.Booking;

import Calls.Booking.BookingCalls;
import io.restassured.response.Response;

public class BookingSteps {
    BookingCalls bookingCalls = new BookingCalls();
    int bookingId;
    Response BookingResponse;

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
}
