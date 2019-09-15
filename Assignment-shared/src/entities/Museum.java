/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 * This class stores details about each individual museum location for this chain.
 * @author Aditya
 */

@NamedQueries ({
    @NamedQuery (name = Museum.GET_ALL_QUERY_NAME, query = "Select m from Museum m"),
    @NamedQuery (name = "Museum.getAllCollections", query = "Select m.museumCollections from Museum m"),
    @NamedQuery (name = "Museum.getAllExhibitions", query = "Select m.museumExhibitions from Museum m")
})

@Entity
public class Museum implements Serializable {
    
    public static final String GET_ALL_QUERY_NAME = "Museum.getAll";
    
    private int museumId;
    private String museumLocation;
    private Address museumAddress;
    private String museumContactNumber;
    private String museumChiefCurator;
    private Set<Exhibition> museumExhibitions;
    private Set<Collection> museumCollections;

    public Museum () {
        museumLocation = "New Museum";
        museumAddress = null;
        museumContactNumber = null;
        museumChiefCurator = null;
        museumExhibitions = null;
        museumCollections = null;
    }
    
    public Museum(int museumId, String museumLocation, Address museumAddress, String museumContactNumber, String museumChiefCurator, Set<Exhibition> museumExhibitions, Set<Collection> museumCollections) {
        this.museumId = museumId;
        this.museumLocation = museumLocation;
        this.museumAddress = museumAddress;
        this.museumContactNumber = museumContactNumber;
        this.museumChiefCurator = museumChiefCurator;
        this.museumExhibitions = museumExhibitions;
        this.museumCollections = museumCollections;
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

    @Column(name = "museum_location")
    public String getMuseumLocation() {
        return museumLocation;
    }

    public void setMuseumLocation(String museumLocation) {
        this.museumLocation = museumLocation;
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

    @OneToMany(mappedBy = "exhibitionMuseum")
    public Set<Exhibition> getMuseumExhibitions() {
        return museumExhibitions;
    }

    public void setMuseumExhibitions(Set<Exhibition> museumExhibitions) {
        this.museumExhibitions = museumExhibitions;
    }

    @OneToMany(mappedBy = "collectionMuseum")
    public Set<Collection> getMuseumCollections() {
        return museumCollections;
    }

    public void setMuseumCollections(Set<Collection> museumCollections) {
        this.museumCollections = museumCollections;
    }

    @Override
    public String toString() {
        return "Museum{" + "museumId=" + museumId + ", museumLocation=" + museumLocation + ", museumAddress=" + museumAddress + ", museumContactNumber=" + museumContactNumber + ", museumChiefCurator=" + museumChiefCurator + ", museumExhibitions=" + museumExhibitions + ", museumCollections=" + museumCollections + '}';
    }
}
