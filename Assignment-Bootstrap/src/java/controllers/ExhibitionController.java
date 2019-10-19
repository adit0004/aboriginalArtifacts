package controllers;

import entities.Artifact;
import entities.Collection;
import entities.Exhibition;
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

@Named(value = "exhibitionController")
@RequestScoped
public class ExhibitionController{
    static @ManagedProperty(value = "#{museumApplication}")
    MuseumApplication museumApplication;
    
    private int selectedExhibitionId;
    private Exhibition selectedExhibition;

    public ExhibitionController() {
        selectedExhibitionId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("exhibitionId"));
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumApplication = (MuseumApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumApplication");
        selectedExhibition = getSelectedExhibition();
    }

    public int getSelectedExhibitionId() {
        return selectedExhibitionId;
    }

    public void setSelectedExhibitionId(int selectedExhibitionId) {
        this.selectedExhibitionId = selectedExhibitionId;
    }

    public Exhibition getSelectedExhibition() {
        if (selectedExhibition == null) {
            return museumApplication.getExhibitionById(selectedExhibitionId);
        }
        return selectedExhibition;
    }

    public void setSelectedExhibition(Exhibition selectedExhibition) {
        this.selectedExhibition = selectedExhibition;
    }
    

    
}
