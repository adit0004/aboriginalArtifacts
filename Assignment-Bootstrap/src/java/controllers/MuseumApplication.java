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

import entities.Collection;
import entities.TicketRecord;
import java.security.Principal;
import java.util.Set;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObject;
import javax.servlet.http.HttpSession;
import mbeans.UserManagedBean;

/**
 *
 * @author adity
 */
@Named(value = "museumApplication")
@ApplicationScoped

public class MuseumApplication {

    @ManagedProperty(value = "#{museumManagedBean}")
    MuseumManagedBean museumManagedBean;
    
    @ManagedProperty(value = "#{userManagedBean}")
    UserManagedBean userManagedBean;
    
    private ArrayList<Museum> museums = new ArrayList<>();
    
    public MuseumApplication() {
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        museumManagedBean = (MuseumManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "museumManagedBean");
        userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userManagedBean");
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
    
    public Museum getMuseumById(int museumId) {
        for (Museum museum : museums) {
            if (museum.getMuseumId() == museumId)
                return museum;
        }
        return null;
    }
    
    public Collection getCollectionById(int collectionId) {
        Collection collection = museumManagedBean.getCollectionById(collectionId);
        return collection;
    }
    
    public String getCollectionsForMuseum(){
        int selectedMuseumId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("museumId"));
        Set<Collection> collections = museumManagedBean.getCollectionsForMuseum(selectedMuseumId);
        
        // Attempt to manually create json
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        for (Collection temp : collections) {
            JsonObject collectionObject = Json.createObjectBuilder().add("id", temp.getCollectionID()).add("category", temp.getCollectionCategory()).add("curator", temp.getCollectionCurator()).add("description", temp.getCollectionDescription()).add("image", temp.getCollectionImagePath()).add("name", temp.getCollectionName()).build();
            arrayBuilder.add(collectionObject);
        }
        JsonArray collectionArray = arrayBuilder.build();
        String retStr = collectionArray.toString();
        return retStr;
    }
    
    public List<Collection> getCollectionsArrayForMuseum() {
        int selectedMuseumId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("museumId"));
        String selectedCategory = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("categoryName");
        Set<Collection> collections = museumManagedBean.getCollectionsForMuseum(selectedMuseumId);;
        List<Collection> collectionsToRemove = new ArrayList<Collection>();
        if (selectedCategory != null){
            for(Collection temp : collections) {
                if (!temp.getCollectionCategory().equals(selectedCategory)) {
                    collectionsToRemove.add(temp);
                }
            }
            for(Collection temp : collectionsToRemove) {
                collections.remove(temp);
            }
        }
        return new ArrayList<Collection>(collections);
    }
    
    public List<String> getCategoriesForMuseum() {
        int selectedMuseumId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("museumId"));
        List<String> categories = museumManagedBean.getCategoriesForMuseum(selectedMuseumId);
        return categories;
    }
    
    public boolean isUserLoggedIn() {
        userManagedBean.setCurrentLoggedInUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        if(userManagedBean.getCurrentLoggedInUserName() == null)
            return false;
        return true;
    }
    
    public List<Exhibition> getExhibitionsForMuseum(){
        int selectedMuseumId = Integer.valueOf(FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("museumId"));
        List<Exhibition> exhibitions = museumManagedBean.getExhibitionsForMuseum(selectedMuseumId);
//        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
//        for (Exhibition temp : exhibitions) {
//            JsonObject collectionObject = Json.createObjectBuilder()
//                    .add("id", temp.getExhibitionId())
//                    .add("name", temp.getExhibitionName())
//                    .add("image", temp.getImagePath())
//                    .build();
//            arrayBuilder.add(collectionObject);
//        }
//        JsonArray collectionArray = arrayBuilder.build();
//        String retStr = collectionArray.toString();
//        return retStr;
        return exhibitions;
    }

    public MuseumManagedBean getMuseumManagedBean() {
        return museumManagedBean;
    }

    public void setMuseumManagedBean(MuseumManagedBean museumManagedBean) {
        this.museumManagedBean = museumManagedBean;
    }

    public UserManagedBean getUserManagedBean() {
        return userManagedBean;
    }

    public void setUserManagedBean(UserManagedBean userManagedBean) {
        this.userManagedBean = userManagedBean;
    }
    
    public boolean checkLoginError() {
        String error = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("error");
        if (error == null) return false;
        return error.equals("true");
    }
    
    public Exhibition getExhibitionById(int exhibitionId) {
        return museumManagedBean.getExhibitionById(exhibitionId);
    }
    
    // Reference: https://stackoverflow.com/questions/2499317/how-to-implement-logout-process-in-jsf-application
    public String logout() {
        ((HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false)).invalidate();
        // Also set currently logged in user to null
        userManagedBean.setCurrentLoggedInUser(null);
        userManagedBean.setCurrentLoggedInUserName(null);
        return "index";
    }
    
    public TicketRecord getTicketRecordForUserExhibition(int userId, int exhibitionId) {
        return userManagedBean.fetchTicketRecord(userId, exhibitionId);
    }
}
