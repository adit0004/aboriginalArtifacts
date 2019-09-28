/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * This class stores details about each individual museum location for this chain.
 * @author Aditya
 */

@NamedQueries ({
    @NamedQuery (name = Museum.GET_ALL_QUERY_NAME, query = "Select m from Museum m"),
    @NamedQuery (name = Museum.GET_EXHIBITIONS_FOR_MUSEUM_QUERY, query = "Select m.museumExhibitions from Museum m")
})

@Entity
@Access(AccessType.PROPERTY)
public class Museum implements Serializable {
    
    public static final String GET_ALL_QUERY_NAME = "Museum.getAll";
    public static final String GET_EXHIBITIONS_FOR_MUSEUM_QUERY = "Museum.getAllExhibitionsForMuseum";
    
    private int museumId;
    private String museumName;
    private Address museumAddress;
    private String museumContactNumber;
    private String museumChiefCurator;
    private String collectionDescription;
    private String learnMoreTitle;
    private String learnMoreLeading;
    private String learnMoreHeading;
    private String learnMoreBody;
    private List<Exhibition> museumExhibitions;
    private Set<Collection> museumCollections;
    private String museumImagePath;

    
    public Museum () {
        museumName = "New Museum";
        museumAddress = null;
        museumContactNumber = null;
        museumChiefCurator = null;
        museumExhibitions = null;
        museumCollections = null;
        museumImagePath = null;
        collectionDescription = null;
        learnMoreTitle = null;
        learnMoreLeading = null;
        learnMoreHeading = null;
        learnMoreBody = null;
    }

    public Museum(int museumId, String museumName, Address museumAddress, String museumContactNumber, String museumChiefCurator, String collectionDescription, String learnMoreTitle, String learnMoreLeading, String learnMoreHeading, String learnMoreBody, List<Exhibition> museumExhibitions, Set<Collection> museumCollections, String museumImagePath) {
        this.museumId = museumId;
        this.museumName = museumName;
        this.museumAddress = museumAddress;
        this.museumContactNumber = museumContactNumber;
        this.museumChiefCurator = museumChiefCurator;
        this.collectionDescription = collectionDescription;
        this.learnMoreTitle = learnMoreTitle;
        this.learnMoreLeading = learnMoreLeading;
        this.learnMoreHeading = learnMoreHeading;
        this.learnMoreBody = learnMoreBody;
        this.museumExhibitions = museumExhibitions;
        this.museumCollections = museumCollections;
        this.museumImagePath = museumImagePath;
    }
   
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "museum_id")
    public int getMuseumId() {
        return museumId;
    }

    public void setMuseumId(int museumId) {
        this.museumId = museumId;
    }

    @Column(name = "museum_name")
    public String getMuseumName() {
        return museumName;
    }

    public void setMuseumName(String museumName) {
        this.museumName = museumName;
    }
    
    @Embedded
    public Address getMuseumAddress() {
        return museumAddress;
    }

    public void setMuseumAddress(Address museumAddress) {
        this.museumAddress = museumAddress;
    }

    @Column(name = "museum_contact_number")
    public String getMuseumContactNumber() {
        return museumContactNumber;
    }

    public void setMuseumContactNumber(String museumContactNumber) {
        this.museumContactNumber = museumContactNumber;
    }

    @Column(name = "museum_chief_curator")
    public String getMuseumChiefCurator() {
        return museumChiefCurator;
    }

    public void setMuseumChiefCurator(String museumChiefCurator) {
        this.museumChiefCurator = museumChiefCurator;
    }

    @OneToMany(mappedBy = "exhibitionMuseum", cascade=CascadeType.ALL)
    public List<Exhibition> getMuseumExhibitions() {
        return museumExhibitions;
    }

    public void setMuseumExhibitions(List<Exhibition> museumExhibitions) {
        this.museumExhibitions = museumExhibitions;
    }

    @OneToMany(mappedBy = "collectionMuseum", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    public Set<Collection> getMuseumCollections() {
        museumCollections.size();
        return museumCollections;
    }

    public void setMuseumCollections(Set<Collection> museumCollections) {
        this.museumCollections = museumCollections;
    }

    @Column(name = "museum_image_path")
    public String getMuseumImagePath() {
        return museumImagePath;
    }

    public void setMuseumImagePath(String museumImagePath) {
        this.museumImagePath = museumImagePath;
    }
    
    @Column(name = "collection_description")
    public String getCollectionDescription() {
        return collectionDescription;
    }

    public void setCollectionDescription(String collectionDescription) {
        this.collectionDescription = collectionDescription;
    }

    @Column(name = "learn_more_title")
    public String getLearnMoreTitle() {
        return learnMoreTitle;
    }

    public void setLearnMoreTitle(String learnMoreTitle) {
        this.learnMoreTitle = learnMoreTitle;
    }

    @Column(name = "learn_more_leading")
    public String getLearnMoreLeading() {
        return learnMoreLeading;
    }

    public void setLearnMoreLeading(String learnMoreLeading) {
        this.learnMoreLeading = learnMoreLeading;
    }

    @Column(name = "learn_more_heading")
    public String getLearnMoreHeading() {
        return learnMoreHeading;
    }

    public void setLearnMoreHeading(String learnMoreHeading) {
        this.learnMoreHeading = learnMoreHeading;
    }

    @Column(name = "learn_more_body", length = 1024)
    public String getLearnMoreBody() {
        return learnMoreBody;
    }

    public void setLearnMoreBody(String learnMoreBody) {
        this.learnMoreBody = learnMoreBody;
    }
    
    @Override
    public String toString() {
        return "Museum{" + "museumId=" + museumId + ", museumName=" + museumName + ", museumAddress=" + museumAddress + ", museumContactNumber=" + museumContactNumber + ", museumChiefCurator=" + museumChiefCurator + ", collectionDescription=" + collectionDescription + ", learnMoreTitle=" + learnMoreTitle + ", learnMoreLeading=" + learnMoreLeading + ", learnMoreHeading=" + learnMoreHeading + ", learnMoreBody=" + learnMoreBody + ", museumImagePath=" + museumImagePath + '}';
    }
    
}
