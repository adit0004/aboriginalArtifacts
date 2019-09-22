/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * This is the base class for Artifact. An artifact is any piece in any museum, under any collection
 * 
 * @author Aditya
 */

@Entity
public class Artifact implements Serializable {
    // Auto generated
    private int artifactId;
    private String artifactName;
    private String artifactDescription;
    private String artifactImagePath;
    private Collection artifactCollection;
    // Estimated time period. This is string because "250-200 BC"
    private String artifactEstimatedTimePeriod;
    private String artifactCategory;

    public Artifact() {
        artifactName = "New Artifact";
        artifactDescription = "New Artifact";
        artifactImagePath = "";
        artifactCollection = null;
        artifactEstimatedTimePeriod = "Estimated Time";
        artifactCategory = null;
    }
    
    public Artifact(int artifactId, String artifactName, String artifactDescription, String artifactImagePath, Collection artifactCollection, String artifactEstimatedTimePeriod, String artifactCategory) {
        this.artifactId = artifactId;
        this.artifactName = artifactName;
        this.artifactDescription = artifactDescription;
        this.artifactImagePath = artifactImagePath;
        this.artifactCollection = artifactCollection;
        this.artifactEstimatedTimePeriod = artifactEstimatedTimePeriod;
        this.artifactCategory = artifactCategory;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "artifact_id")
    public int getArtifactId() {
        return artifactId;
    }

    public void setArtifactId(int artifactId) {
        this.artifactId = artifactId;
    }

    @Column(name = "artifact_name")
    public String getArtifactName() {
        return artifactName;
    }

    public void setArtifactName(String artifactName) {
        this.artifactName = artifactName;
    }

    @Column(name = "artifact_description")
    public String getArtifactDescription() {
        return artifactDescription;
    }

    public void setArtifactDescription(String artifactDescription) {
        this.artifactDescription = artifactDescription;
    }

    @Column(name = "artifact_image_path")
    public String getArtifactImagePath() {
        return artifactImagePath;
    }

    public void setArtifactImagePath(String artifactImagePath) {
        this.artifactImagePath = artifactImagePath;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "collection_id", nullable = false)
    public Collection getArtifactCollection() {
        return artifactCollection;
    }
    
    public void setArtifactCollection(Collection artifactCollection) {
        this.artifactCollection = artifactCollection;
    }

    @Column(name = "artifact_estimated_time_period")
    public String getArtifactEstimatedTimePeriod() {
        return artifactEstimatedTimePeriod;
    }

    public void setArtifactEstimatedTimePeriod(String artifactEstimatedTimePeriod) {
        this.artifactEstimatedTimePeriod = artifactEstimatedTimePeriod;
    }
    
    @Column(name = "artifact_category")
    public String getArtifactCategory() {
        return artifactCategory;
    }

    public void setArtifactCategory(String artifactCategory) {
        this.artifactCategory = artifactCategory;
    }

    @Override
    public String toString() {
        return "Artifact{" + "artifactId=" + artifactId + ", artifactName=" + artifactName + ", artifactDescription=" + artifactDescription + ", artifactImagePath=" + artifactImagePath + ", artifactCollection=" + artifactCollection + ", artifactEstimatedTimePeriod=" + artifactEstimatedTimePeriod + ", artifactCategory=" + artifactCategory + '}';
    }
}
