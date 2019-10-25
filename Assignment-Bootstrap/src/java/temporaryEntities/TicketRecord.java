package temporaryEntities;

/**
 *
 * @author Aditya
 */
public class TicketRecord {
    private int exhibitionId;
    private String bookingDate;
    private String orderDate;
    private int userId;
    private int quantity;

    public TicketRecord() {
    }

    public TicketRecord(int exhibitionId, String bookingDate, String orderDate, int userId, int quantity) {
        this.exhibitionId = exhibitionId;
        this.bookingDate = bookingDate;
        this.orderDate = orderDate;
        this.userId = userId;
        this.quantity = quantity;
    }

    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    public String getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(String bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    
}
