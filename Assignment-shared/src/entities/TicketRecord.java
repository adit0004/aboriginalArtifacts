package entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Aditya
 */
@NamedQueries ({
    @NamedQuery(name = TicketRecord.GET_BOOKING_DETAILS_QUERY, query = "SELECT t FROM TicketRecord t JOIN FETCH t.userDetails u JOIN FETCH t.exhibition e WHERE u.userId = :userId AND e.exhibitionId = :exhibitionId"),
    @NamedQuery(name = TicketRecord.GET_ALL_BOOKINGS_FOR_USER, query = "SELECT t FROM TicketRecord t JOIN FETCH t.userDetails u JOIN FETCH t.exhibition e WHERE u.userId = :userId")
})
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="bookingId")
public class TicketRecord implements Serializable {
    public static final String GET_BOOKING_DETAILS_QUERY = "TicketRecord.getBookingDetailsQuery";
    public static final String GET_ALL_BOOKINGS_FOR_USER = "TicketRecord.getAllBookingsForUser";
    private int bookingId;
    private Exhibition exhibition;
    private Date bookingDate;
    private int quantity;
    private UserData userDetails;

    public TicketRecord() {
    }

    public TicketRecord(int bookingId, Exhibition exhibition, Date bookingDate, UserData user, int quantity) {
        this.bookingId = bookingId;
        this.exhibition = exhibition;
        this.bookingDate = bookingDate;
        this.userDetails = user;
        this.quantity = quantity;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "booking_id")
    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
    
    @OneToOne(fetch = FetchType.EAGER)
    public Exhibition getExhibition() {
        return exhibition;
    }

    public void setExhibition(Exhibition exhibition) {
        this.exhibition = exhibition;
    }
    
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(Date bookingDate) {
        this.bookingDate = bookingDate;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_tickets", nullable = false)
    public UserData getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(UserData userDetails) {
        this.userDetails = userDetails;
    }
    
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return "TicketRecord{" + "bookingId=" + bookingId + ", exhibition=" + exhibition + ", bookingDate=" + bookingDate + ", quantity=" + quantity + "}";
    }    
}
