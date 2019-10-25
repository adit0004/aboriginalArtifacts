/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Artifact;
import entities.Collection;
import entities.Museum;
import java.util.List;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import mbeans.MuseumManagedBean;

/**
 *
 * @author Aditya
 */

@Named(value = "searchController")
@RequestScoped
public class SearchController {
    private String searchQuery;
    
    private List<Museum> museumResults;
    private List<Collection> collectionResults;
    private List<Artifact> artifactResults;
    
    // Managed bean to get search results
    @ManagedProperty(value = "#{museumManagedBean}")
    MuseumManagedBean museumManagedBean;

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }

    public SearchController() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumManagedBean =  (MuseumManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumManagedBean");
    }
    
    public String getSearchResults() {
        System.out.println("REQUEST CONTEXT " + FacesContext.getCurrentInstance().getExternalContext().getRequestContextPath());
        // Assign temp variables so we only call the named queries once, as named queries are an expensive operation
        List<Museum> tempMuseumList = museumManagedBean.searchMuseumByNameOrAddress(searchQuery);
        List<Collection> tempCollectionList = museumManagedBean.searchCollectionByNameDescriptionCurator(searchQuery);
        List<Artifact> tempArtifactList = museumManagedBean.searchArtifactByNameOrDescription(searchQuery);
        if (tempMuseumList == null || tempMuseumList.isEmpty()) 
            museumResults = null;
        else museumResults = tempMuseumList;
        if (tempCollectionList == null || tempCollectionList.isEmpty())
            collectionResults = null;
        else collectionResults = tempCollectionList;
        if(tempArtifactList == null || tempArtifactList.isEmpty())
            artifactResults = null;
        else artifactResults = tempArtifactList;
        return "/search_results";
    }

    public List<Museum> getMuseumResults() {
        return museumResults;
    }

    public void setMuseumResults(List<Museum> museumResults) {
        this.museumResults = museumResults;
    }

    public List<Collection> getCollectionResults() {
        return collectionResults;
    }

    public void setCollectionResults(List<Collection> collectionResults) {
        this.collectionResults = collectionResults;
    }

    public List<Artifact> getArtifactResults() {
        return artifactResults;
    }

    public void setArtifactResults(List<Artifact> artifactResults) {
        this.artifactResults = artifactResults;
    }
    
}
