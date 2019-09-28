/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;

import entities.Collection;
import entities.Exhibition;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import repository.MuseumRepository;
import entities.Museum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author adity
 */
@ManagedBean(name = "museumManagedBean", eager = true)
@SessionScoped
public class MuseumManagedBean implements Serializable{
    
    @EJB
    MuseumRepository museumRepository;
    
    public MuseumManagedBean() {
    }
    
    public List<Museum> getAllMuseums() {
        try {
            List<Museum> museums = museumRepository.getAllMuseums();
            return museums;
        } catch (Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public Set<Collection> getCollectionsForMuseum(int museumId) {
        try {
            Museum museum = museumRepository.getMuseumById(museumId);
            Set<Collection> collections = museum.getMuseumCollections();
            return collections;
        } catch (Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
        public ArrayList<Exhibition> getAllExhibitions() {
        try {
            List<Exhibition> exhibition = museumRepository.getAllExhibitions();
            return (ArrayList<Exhibition>) exhibition;
        } catch (Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public List<Exhibition> getExhibitionsForMuseum(int museumId) {
        try {
            Museum museum = museumRepository.getMuseumById(museumId);
            List<Exhibition> exhibitions = museum.getMuseumExhibitions();
            return exhibitions;
        } catch (Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
}
