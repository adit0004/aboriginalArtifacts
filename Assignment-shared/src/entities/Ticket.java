/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class stores details of tickets for each exhibition. This is not a simple attribute because each exhibition may have several types of tickets - e.g. "Gold", "Silver", "Collector's", "Premium" etc.
 * @author Aditya
 */
@Entity
public class Ticket implements Serializable {
    private int ticketId;
    private String ticketName;
    private double ticketPrice;
    private String ticketDescription;
    private Exhibition ticketExhibition;

    public Ticket () {
        ticketName = "New ticket";
        ticketPrice = 0.0;
        ticketDescription = null;
        ticketExhibition = null;
    }
    public Ticket(int ticketId, String ticketName, double ticketPrice, String ticketDescription, Exhibition ticketExhibition) {
        this.ticketId = ticketId;
        this.ticketName = ticketName;
        this.ticketPrice = ticketPrice;
        this.ticketDescription = ticketDescription;
        this.ticketExhibition = ticketExhibition;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ticket_id")
    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    @Column(name = "ticket_name")
    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    @Column(name = "ticket_price")
    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Column(name = "ticket_description")
    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public Exhibition getTicketExhibition() {
        return ticketExhibition;
    }

    public void setTicketExhibition(Exhibition ticketExhibition) {
        this.ticketExhibition = ticketExhibition;
    }

    @Override
    public String toString() {
        return "Ticket{" + "ticketId=" + ticketId + ", ticketName=" + ticketName + ", ticketPrice=" + ticketPrice + ", ticketDescription=" + ticketDescription + ", ticketExhibition=" + ticketExhibition + '}';
    }
}
