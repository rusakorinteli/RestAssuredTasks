package DataController.Booking;

import Models.Booking.BookingDates;
import Models.Booking.BookingRequestModel;

public class BookingDataController {
    public static BookingRequestModel bookingData() {

        BookingRequestModel bookingRequestModel = new BookingRequestModel();
        BookingDates bookingDates = new BookingDates();
        bookingRequestModel.setFirstname("Rusa");
        bookingRequestModel.setLastname("Korinteli");
        bookingRequestModel.setTotalprice(500);
        bookingRequestModel.setDepositpaid(true);
        bookingRequestModel.setAdditionalneeds("Breakfast");

        bookingDates.setCheckin("2026-09-10");
        bookingDates.setCheckout("2026-09-15");

        bookingRequestModel.setBookingdates(bookingDates);

        return bookingRequestModel;
    }
}
