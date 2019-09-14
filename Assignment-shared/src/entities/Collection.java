/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * This class contains information about a collection of artifacts. Artifacts at a museum are usually grouped together under a collection, e.g. "Egyptian ruins"
 * @author Aditya
 */
@Entity
public class Collection implements Serializable {
    private int collectionID;
    private String collectionName;
    private String collectionDescription;
    private String collectionCurator;
    private String collectionCategory;
    private String collectionImagePath;
    private Set<Exhibition> collectionExhibition;
    private Set<Artifact> collectionArtifacts;
    private Museum collectionMuseum;

    public Collection() {
        collectionName = "New collection";
        collectionDescription = "New collection";
        collectionCurator = null;
        collectionCategory = null;
        collectionImagePath = null;
        collectionArtifacts = null;
        collectionMuseum = null;
        collectionExhibition = null;
    }
    public Collection(int collectionID, String collectionName, String collectionDescription, String collectionCurator, String collectionCategory, String collectionImagePath, Set<Artifact> collectionArtifacts, Museum collectionMuseum, Set<Exhibition> collectionExhibition) {
        this.collectionID = collectionID;
        this.collectionName = collectionName;
        this.collectionDescription = collectionDescription;
        this.collectionCurator = collectionCurator;
        this.collectionCategory = collectionCategory;
        this.collectionImagePath = collectionImagePath;
        this.collectionArtifacts = collectionArtifacts;
        this.collectionMuseum = collectionMuseum;
        this.collectionExhibition = collectionExhibition;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "collection_id")
    public int getCollectionID() {
        return collectionID;
    }

    public void setCollectionID(int collectionID) {
        this.collectionID = collectionID;
    }

    @Column(name = "collection_name")
    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Column(name = "collection_description")
    public String getCollectionDescription() {
        return collectionDescription;
    }

    public void setCollectionDescription(String collectionDescription) {
        this.collectionDescription = collectionDescription;
    }

    @Column(name = "collection_curator")
    public String getCollectionCurator() {
        return collectionCurator;
    }

    public void setCollectionCurator(String collectionCurator) {
        this.collectionCurator = collectionCurator;
    }

    @Column(name = "collection_category")
    public String getCollectionCategory() {
        return collectionCategory;
    }

    public void setCollectionCategory(String collectionCategory) {
        this.collectionCategory = collectionCategory;
    }

    @Column(name = "collection_image_path")
    public String getCollectionImagePath() {
        return collectionImagePath;
    }

    public void setCollectionImagePath(String collectionImagePath) {
        this.collectionImagePath = collectionImagePath;
    }

    @OneToMany(mappedBy = "artifactCollection")
    public Set<Artifact> getCollectionArtifacts() {
        return collectionArtifacts;
    }

    public void setCollectionArtifacts(Set<Artifact> collectionArtifacts) {
        this.collectionArtifacts = collectionArtifacts;
    }

    @ManyToOne()
    @JoinColumn(name = "museum_id", nullable = false)
    public Museum getCollectionMuseum() {
        return collectionMuseum;
    }

    public void setCollectionMuseum(Museum collectionMuseum) {
        this.collectionMuseum = collectionMuseum;
    }

    @OneToMany(mappedBy = "exhibitionCollection")
    public Set<Exhibition> getCollectionExhibition() {
        return collectionExhibition;
    }

    public void setCollectionExhibition(Set<Exhibition> collectionExhibition) {
        this.collectionExhibition = collectionExhibition;
    }
    
    
    @Override
    public String toString() {
        return "Collection{" + "collectionID=" + collectionID + ", collectionName=" + collectionName + ", collectionDescription=" + collectionDescription + ", collectionCurator=" + collectionCurator + ", collectionCategory=" + collectionCategory + ", collectionImagePath=" + collectionImagePath + ", collectionArtifacts=" + collectionArtifacts + ", collectionMuseum=" + collectionMuseum + '}';
    }    
}
