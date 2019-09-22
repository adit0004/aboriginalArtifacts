/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;

/**
 * This class stores information about which collection is currently on exhibition at which museum
 * @author Aditya
 */

@NamedQueries ({
    @NamedQuery (name = Exhibition.GET_ALL_QUERY_NAME, query = "Select e from Exhibition e"),
    @NamedQuery (name = Exhibition.GET_EXHIBITIONS_FOR_MUSEUM, query = "Select e from Exhibition e WHERE e.exhibitionMuseum.museumId = :museumId")
})

@Entity
public class Exhibition implements Serializable{
    
    public static final String GET_ALL_QUERY_NAME = "Exhibition.getAll";
    public static final String GET_EXHIBITIONS_FOR_MUSEUM = "Exhibition.getAllForMuseum";
    
    private int exhibitionId;
    private String exhibitionName;
    private String exhibitionDescription;
    private Date exhibitionStartDate;
    private Date exhibitionEndDate;
    private Collection exhibitionCollection;
    private Museum exhibitionMuseum;
    private Ticket exhibitionTicket;
    private String imagePath;

    public Exhibition() {
        exhibitionName = "New Exhibition";
        exhibitionDescription = null;
        exhibitionStartDate = null;
        exhibitionEndDate = null;
        exhibitionCollection = null;
        exhibitionMuseum = null;
        exhibitionTicket = null;
        imagePath = null;
    }
    public Exhibition(int exhibitionId, String exhibitionName, String exhibitionDescription, Date exhibitionStartDate, Date exhibitionEndDate, Collection exhibitionCollection, Museum exhibitionMuseum, Ticket exhibitionTicket, String imagePath){
        this.exhibitionId = exhibitionId;
        this.exhibitionName = exhibitionName;
        this.exhibitionDescription = exhibitionDescription;
        this.exhibitionStartDate = exhibitionStartDate;
        this.exhibitionEndDate = exhibitionEndDate;
        this.exhibitionCollection = exhibitionCollection;
        this.exhibitionMuseum = exhibitionMuseum;
        this.exhibitionTicket = exhibitionTicket;
        this.imagePath = imagePath;
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
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getExhibitionStartDate() {
        return exhibitionStartDate;
    }

    public void setExhibitionStartDate(Date exhibitionStartDate) {
        this.exhibitionStartDate = exhibitionStartDate;
    }

    @Column(name = "exhibition_end_date")
    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getExhibitionEndDate() {
        return exhibitionEndDate;
    }

    public void setExhibitionEndDate(Date exhibitionEndDate) {
        this.exhibitionEndDate = exhibitionEndDate;
    }
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collection_id", nullable = true)
    public Collection getExhibitionCollection() {
        return exhibitionCollection;
    }

    public void setExhibitionCollection(Collection exhibitionCollection) {
        this.exhibitionCollection = exhibitionCollection;
    }

    @ManyToOne()
    @JoinColumn(name = "museum_id", nullable = false)
    public Museum getExhibitionMuseum() {
        return exhibitionMuseum;
    }

    public void setExhibitionMuseum(Museum exhibitionMuseum) {
        this.exhibitionMuseum = exhibitionMuseum;
    }

    @Embedded
    public Ticket getExhibitionTicket() {
        return exhibitionTicket;
    }

    public void setExhibitionTicket(Ticket exhibitionTicket) {
        this.exhibitionTicket = exhibitionTicket;
    }

    @Column(name = "image_path")
    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    
    @Override
    public String toString() {
        return "Exhibition{" + "exhibitionId=" + exhibitionId + ", exhibitionName=" + exhibitionName + ", exhibitionDescription=" + exhibitionDescription + ", exhibitionStartDate=" + exhibitionStartDate + ", exhibitionEndDate=" + exhibitionEndDate + ", exhibitionCollection=" + exhibitionCollection + ", exhibitionMuseum=" + exhibitionMuseum + ", exhibitionTickets=" + exhibitionTicket + '}';
    }
}
