/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * This class stores information about which collection is currently on exhibition at which museum
 * @author Aditya
 */
@Entity
public class Exhibition implements Serializable{
    private int exhibitionId;
    private String exhibitionName;
    private String exhibitionDescription;
    private Date exhibitionStartDate;
    private Date exhibitionEndDate;
    private Collection exhibitionCollection;
    private Museum exhibitionMuseum;
    private Set<Ticket> exhibitionTickets;

    public Exhibition() {
        exhibitionName = "New Exhibition";
        exhibitionDescription = null;
        exhibitionStartDate = null;
        exhibitionEndDate = null;
        exhibitionCollection = null;
        exhibitionMuseum = null;
        exhibitionTickets = null;
    }
    public Exhibition(int exhibitionId, String exhibitionName, String exhibitionDescription, Date exhibitionStartDate, Date exhibitionEndDate, Collection exhibitionCollection, Museum exhibitionMuseum, Set<Ticket> exhibitionTickets) {
        this.exhibitionId = exhibitionId;
        this.exhibitionName = exhibitionName;
        this.exhibitionDescription = exhibitionDescription;
        this.exhibitionStartDate = exhibitionStartDate;
        this.exhibitionEndDate = exhibitionEndDate;
        this.exhibitionCollection = exhibitionCollection;
        this.exhibitionMuseum = exhibitionMuseum;
        this.exhibitionTickets = exhibitionTickets;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "exhibition_id")
    public int getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(int exhibitionId) {
        this.exhibitionId = exhibitionId;
    }

    @Column(name = "exhibition_name")
    public String getExhibitionName() {
        return exhibitionName;
    }

    public void setExhibitionName(String exhibitionName) {
        this.exhibitionName = exhibitionName;
    }

    @Column(name = "exhibition_description")
    public String getExhibitionDescription() {
        return exhibitionDescription;
    }

    public void setExhibitionDescription(String exhibitionDescription) {
        this.exhibitionDescription = exhibitionDescription;
    }

    @Column(name = "exhibition_start_date")
    public Date getExhibitionStartDate() {
        return exhibitionStartDate;
    }

    public void setExhibitionStartDate(Date exhibitionStartDate) {
        this.exhibitionStartDate = exhibitionStartDate;
    }

    @Column(name = "exhibition_end_date")
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

    public Set<Ticket> getExhibitionTickets() {
        return exhibitionTickets;
    }

    public void setExhibitionTickets(Set<Ticket> exhibitionTickets) {
        this.exhibitionTickets = exhibitionTickets;
    }

    @Override
    public String toString() {
        return "Exhibition{" + "exhibitionId=" + exhibitionId + ", exhibitionName=" + exhibitionName + ", exhibitionDescription=" + exhibitionDescription + ", exhibitionStartDate=" + exhibitionStartDate + ", exhibitionEndDate=" + exhibitionEndDate + ", exhibitionCollection=" + exhibitionCollection + ", exhibitionMuseum=" + exhibitionMuseum + ", exhibitionTickets=" + exhibitionTickets + '}';
    }
}
