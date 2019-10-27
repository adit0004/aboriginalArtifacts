/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;

import entities.Artifact;
import entities.Collection;
import entities.Exhibition;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import repository.MuseumRepository;
import entities.Museum;
import entities.TicketRecord;
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
    
        public List<Exhibition> getAllExhibitions() {
        try {
            List<Exhibition> exhibition = museumRepository.getAllExhibitions();
            return exhibition;
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
    
    public List<String> getCategoriesForMuseum(int museumId) {
        try {
            List<String> categories = museumRepository.getCollectionCategoriesForMuseum(museumId);
            return categories;
        } catch (Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public Collection getCollectionById(int collectionId) {
        try {
            Collection collection = museumRepository.getCollectionById(collectionId);
            return collection;
        } catch (Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public Exhibition getExhibitionById(int exhibitionId) {
        try {
            return museumRepository.getExhibitionById(exhibitionId);
        } catch(Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public List<Museum> searchMuseumByNameOrAddress(String searchQuery) {
        // Stub
        try {
            return museumRepository.searchMuseumByNameOrAddress(searchQuery);
        } catch(Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public List<Collection> searchCollectionByNameDescriptionCurator(String searchQuery) {
        // Stub
        try {
            return museumRepository.searchCollectionByNameDescriptionCurator(searchQuery);
        } catch(Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public List<Artifact> searchArtifactByNameOrDescription(String searchQuery) {
        // Stub
        try {
            return museumRepository.searchArtifactByNameOrDescription(searchQuery);
        } catch(Exception e) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, e);
            return null;
        }
    }
    
    public void deleteExhibition(int exhibitionId) {
        try {
            museumRepository.deleteExhibition(exhibitionId);
        } catch (Exception ex) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<Collection> getAllCollections() {
        try {
            return museumRepository.getAllCollections();
        } catch (Exception ex) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void updateExhibition(Exhibition exhibition) {
        try {
            museumRepository.updateExhibition(exhibition);
        } catch (Exception ex) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addExhibition(Exhibition exhibition) {
        try {
            museumRepository.addExhibition(exhibition);
        } catch (Exception ex) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public List<TicketRecord>  getAllBookings() {
        try {
            return museumRepository.getAllBookings();
        } catch (Exception ex) {
            Logger.getLogger(MuseumManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
