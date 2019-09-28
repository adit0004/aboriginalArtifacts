package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author Aditya
 */
@Entity
public class TicketRecord implements Serializable {
    private int bookingId;
    private Exhibition exhibition;
    private Date bookingDate;
    private UserData userDetails;

    public TicketRecord() {
    }

    public TicketRecord(int bookingId, Exhibition exhibition, Date bookingDate, UserData user) {
        this.bookingId = bookingId;
        this.exhibition = exhibition;
        this.bookingDate = bookingDate;
        this.userDetails = user;
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
    
    @OneToOne
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

    @ManyToOne
    @JoinColumn(name = "user_tickets", nullable = true)
    public UserData getUser() {
        return userDetails;
    }

    public void setUser(UserData user) {
        this.userDetails = user;
    }
    
    @Override
    public String toString() {
        return "TicketRecord{" + "exhibition=" + exhibition + ", bookingDate=" + bookingDate + '}';
    }
}
