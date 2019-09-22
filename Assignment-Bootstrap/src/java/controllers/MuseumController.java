/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.Collection;
import entities.Museum;
import java.io.Serializable;
import java.util.Set;

import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import javax.persistence.Transient;
/**
 *
 * @author Aditya
 */
@Named(value = "museumController")
@RequestScoped
public class MuseumController{
    static @ManagedProperty(value = "#{museumApplication}")
    MuseumApplication museumApplication;
    
    private int selectedMuseumId;
    private Museum selectedMuseum;
    
    public MuseumController() throws Exception {
        selectedMuseumId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("museumId"));
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumApplication = (MuseumApplication) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumApplication");
        selectedMuseum = getSelectedMuseum();
    }

    public int getSelectedMuseumId() {
        return selectedMuseumId;
    }

    public void setSelectedMuseumId(int selectedMuseumId) {
        this.selectedMuseumId = selectedMuseumId;
    }

    public Museum getSelectedMuseum() {
        if (selectedMuseum == null)
            return museumApplication.getMuseumById(selectedMuseumId);
        return selectedMuseum;
    }

    public void setSelectedMuseum(Museum selectedMuseum) {
        this.selectedMuseum = selectedMuseum;
    }
}
