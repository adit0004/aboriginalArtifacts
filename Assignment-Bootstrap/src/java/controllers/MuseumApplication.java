/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Exhibition;
import entities.Museum;
import java.util.ArrayList;
import java.util.List;
import javax.el.ELContext;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import mbeans.MuseumManagedBean;

/**
 *
 * @author adity
 */
@Named(value = "museumApplication")
@ApplicationScoped

public class MuseumApplication {

    @ManagedProperty(value = "#{museumManagedBean}")
    MuseumManagedBean museumManagedBean;
    
    private ArrayList<Museum> museums = new ArrayList<>();
    
    public MuseumApplication() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumManagedBean = (MuseumManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumManagedBean");
        updateMuseumList();
    }
    
    public void updateMuseumList() {
        if (museums != null && museums.size() > 0)
        {
        }
        else
        {
            museums.clear();

            for (Museum museum : museumManagedBean.getAllMuseums()) {
                museums.add(museum);
            }
        }
    }

    public ArrayList<Museum> getMuseums() {
        return museums;
    }

    public void setMuseums(ArrayList<Museum> museums) {
        this.museums = museums;
    }
    
    public List<Exhibition> getExhibitionsForMuseum(int museumId) {
        return museumManagedBean.getExhibitionsForMuseum(museumId);
    }
    
    public Museum getMuseumById(int museumId) {
        for (Museum museum : museums) {
            if (museum.getMuseumId() == museumId)
                return museum;
        }
        return null;
    }
}
