/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Museum;
import java.util.ArrayList;
import javax.el.ELContext;
import javax.faces.bean.ApplicationScoped;
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
    
    private ArrayList<Museum> museums;

    public MuseumApplication() {
        museums = new ArrayList();
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumManagedBean = (MuseumManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumManagedBean");
        updateMuseumList();
    }
    
    public ArrayList<Museum> getMuseums() {
        return museums;
    }
    
    
    private void setMuseums(ArrayList<Museum> newMuseums) {
        this.museums = newMuseums;
    }
    
    public void updateMuseumList() {
        if (museums != null && museums.size() > 0) {
            
        }
        else {
            museums.clear();
            for (entities.Museum museum : museumManagedBean.getAllMuseums()) {
                museums.add(museum);
            }
            setMuseums(museums);
        }
    }
    
}
