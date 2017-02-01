import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class BookingManagerImpl implements BookingManager {

    private final ConcurrentHashMap<Integer, ConcurrentHashMap<Date, String>> bookings
            = new ConcurrentHashMap<>();

    //once initialised, cannot add or remove rooms
    public BookingManagerImpl(List<Integer> rooms){
        for(Integer room : rooms){
            bookings.put(room, new ConcurrentHashMap<Date, String>(16,0.75f,1));
        }
    }

    @Override
    public boolean isRoomAvailable(Integer room, Date date) {
        if(bookings.containsKey(room)){
            return !bookings.get(room).containsKey(date);
        }
        System.out.println("Room does not exist");
        return false;
    }

    @Override
    public void addBooking(String guest, Integer room, Date date) {
        ConcurrentHashMap<Date, String> roomBookings = bookings.get(room);
        if(roomBookings != null){
            roomBookings.putIfAbsent(date,guest);
            String booking = roomBookings.get(date);
            if(!booking.equals(guest)){
                System.out.println("room already taken");
            }
        } else {
            System.out.println("Room does not exist");
        }
    }

    @Override
    public Iterable<Integer> getAvailableRooms(Date date) {
        List<Integer> results = new ArrayList<>();
        for(Integer room : bookings.keySet()){
            if( bookings.get(room).get(date)==null)
                results.add(room);
        }
        return results;
    }

}