package controllers;

import entities.Artifact;
import entities.Collection;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author Aditya
 */

@Named(value = "collectionController")
@RequestScoped
public class CollectionController {
    static @ManagedProperty(value = "#{museumApplication}")
    MuseumApplication museumApplication;
    
    private int selectedCollectionId;
    private Collection selectedCollection;

    public CollectionController() {
        selectedCollectionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("collectionId"));
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumApplication = (MuseumApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumApplication");
        selectedCollection = getSelectedCollection();
    }

    
    public int getSelectedCollectionId() {
        return selectedCollectionId;
    }

    public void setSelectedCollectionId(int selectedCollectionId) {
        this.selectedCollectionId = selectedCollectionId;
    }

    public Collection getSelectedCollection() {
        if (selectedCollection == null)
            return museumApplication.getCollectionById(selectedCollectionId);
        return selectedCollection;
    }

    public void setSelectedCollection(Collection selectedCollection) {
        this.selectedCollection = selectedCollection;
    }
    
    public List<Artifact> getArtifactsForCollection() {
        selectedCollectionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("collectionId"));
        Collection collection = museumApplication.getCollectionById(selectedCollectionId);
        List<Artifact> artifacts = new ArrayList<Artifact>(collection.getCollectionArtifacts());
        return artifacts;
    }
    
}
