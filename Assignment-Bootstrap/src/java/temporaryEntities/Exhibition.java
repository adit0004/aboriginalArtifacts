/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package temporaryEntities;

import entities.Collection;
import entities.Museum;
import entities.Ticket;
import java.io.Serializable;
import java.util.Date;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 *
 * @author adity
 */
@RequestScoped
@Named(value = "newexhibition")
public class Exhibition implements Serializable{
    private String exhibitionName;
    private String exhibitionDescription;
    private Date exhibitionStartDate;
    private Date exhibitionEndDate;
    private Collection exhibitionCollection;
    private Museum exhibitionMuseum;
    private String ticketName;
    private String ticketDescription;
    private double ticketPrice;
    private String imagePath;

    public Exhibition(String exhibitionName, String exhibitionDescription, Date exhibitionStartDate, Date exhibitionEndDate, Collection exhibitionCollection, Museum exhibitionMuseum, String ticketName, String ticketDescription, double ticketPrice, String imagePath) {
        this.exhibitionName = exhibitionName;
        this.exhibitionDescription = exhibitionDescription;
        this.exhibitionStartDate = exhibitionStartDate;
        this.exhibitionEndDate = exhibitionEndDate;
        this.exhibitionCollection = exhibitionCollection;
        this.exhibitionMuseum = exhibitionMuseum;
        this.ticketName = ticketName;
        this.ticketDescription = ticketDescription;
        this.ticketPrice = ticketPrice;
        this.imagePath = imagePath;
    }

    public Exhibition() {
    }

    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    public String getExhibitionDescription() {
        return exhibitionDescription;
    }

    public void setExhibitionDescription(String exhibitionDescription) {
        this.exhibitionDescription = exhibitionDescription;
    }

    public Date getExhibitionStartDate() {
        return exhibitionStartDate;
    }

    public void setExhibitionStartDate(Date exhibitionStartDate) {
        this.exhibitionStartDate = exhibitionStartDate;
    }

    public Date getExhibitionEndDate() {
        return exhibitionEndDate;
    }

    public void setExhibitionEndDate(Date exhibitionEndDate) {
        this.exhibitionEndDate = exhibitionEndDate;
    }

    public Collection getExhibitionCollection() {
        return exhibitionCollection;
    }

    public void setExhibitionCollection(Collection exhibitionCollection) {
        this.exhibitionCollection = exhibitionCollection;
    }

    public Museum getExhibitionMuseum() {
        return exhibitionMuseum;
    }

    public void setExhibitionMuseum(Museum exhibitionMuseum) {
        this.exhibitionMuseum = exhibitionMuseum;
    }

    public String getTicketName() {
        return ticketName;
    }

    public void setTicketName(String ticketName) {
        this.ticketName = ticketName;
    }

    public String getTicketDescription() {
        return ticketDescription;
    }

    public void setTicketDescription(String ticketDescription) {
        this.ticketDescription = ticketDescription;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    @Override
    public String toString() {
        return "Exhibition{" + "exhibitionName=" + exhibitionName + ", exhibitionDescription=" + exhibitionDescription + ", exhibitionStartDate=" + exhibitionStartDate + ", exhibitionEndDate=" + exhibitionEndDate + ", exhibitionCollection=" + exhibitionCollection + ", exhibitionMuseum=" + exhibitionMuseum + ", ticketName=" + ticketName + ", ticketDescription=" + ticketDescription + ", ticketPrice=" + ticketPrice + ", imagePath=" + imagePath + '}';
    }
    
    

   
}
