import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

public class BookingManagerTest {

    private BookingManager bm;
    private Date today;

    @Before
    public void setup(){
        bm = new BookingManagerImpl(Arrays.asList(101, 102, 103, 201, 202, 203));
        today = java.sql.Date.valueOf("2012-03-28");
    }

    @Test
    public void roomBookingTest() {
        System.out.println(bm.isRoomAvailable(101, today)); // outputs true
        assertTrue(bm.isRoomAvailable(101, today));
        bm.addBooking("Smith", 101, today);
        System.out.println(bm.isRoomAvailable(101, today)); // outputs false
        assertFalse(bm.isRoomAvailable(101, today));
        assertFalse(bm.isRoomAvailable(999,today));
    }

    @Test
    public void getAvailableRoomsTest(){
        bm.addBooking("Smith", 101, today);
        Iterable<Integer> availability = bm.getAvailableRooms(today);
        int count = 0;
        for(Integer room : availability){
            count++;
        }
        assertEquals(5,count);
    }

}
