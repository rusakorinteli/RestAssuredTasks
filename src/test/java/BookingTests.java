import Steps.Booking.BookingSteps;
import org.testng.annotations.Test;

public class BookingTests {

    @Test
    public void AddBooking(){
        new BookingSteps()
                .addBooking()
                .statusCodeChecks()
                .getBookingId();
    }

    @Test
    public void AddBookingWithModels(){
        new BookingSteps()
                .addBookingWithModel()
                .statusCodeChecks()
                .checkBookingData()
                .getBookingId();
    }
}
