import DataController.Booking.BookingDataController;
import Models.Booking.BookingRequestModel;
import Steps.Booking.BookingSteps;
import org.testng.annotations.Test;

public class BookingTests {

    BookingRequestModel bookingRequestModel = BookingDataController.bookingData();

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

    @Test
    public void getAndValidateBookingData(){
        new BookingSteps()
                .addBookingWithModel()
                .statusCodeChecks()
                .getBookingId()
                .getBooking()
                .checkBookingDataWithJsonPath();
    }

    @Test
    public void deserialazationTest() {
        new BookingSteps()
                .addBookingWithModel()
                .statusCodeChecks()
                .getBookingId()
                .getBooking()
                .bookingResponseDeserialization()
                .checkDataAfterDeserialization();

    }

    //GenericStepsTest

    @Test
    public void checkBookingWithGenericSteps(){
        new BookingSteps()
                .setData(bookingRequestModel)
                .addBookingGeneric()
                .getBooking()
                .checkBookingGeneric();
    }
}
