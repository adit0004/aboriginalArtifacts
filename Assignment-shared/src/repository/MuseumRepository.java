/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Artifact;
import entities.Collection;
import entities.Exhibition;
import entities.Museum;
import java.util.List;
import java.util.Set;
import javax.ejb.Remote;

/**
 *
 * @author Aditya
 */
@Remote
public interface MuseumRepository {

    /**
     * Add museum to repository
     * @param museum The museum to add
     * @throws Exception
     */
    public void addMuseum(Museum museum) throws Exception;
    
    /**
     * Return a specific museum
     * @param museumId The ID for the museum to return
     * @throws Exception
     */
    public Museum getMuseumById(int museumId) throws Exception;
    
    /**
     * Delete a museum from the repository
     * @param museumId The ID for the museum to remove
     * @throws Exception
     */
    public void removeMuseum(int museumId) throws Exception;

    /**
     * Update a museum's details
     * @param museum The museum to update
     * @throws Exception
     */
    public void editMuseum(Museum museum) throws Exception;
    
    /**
     * Return all museums
     * @return A list of Museum object
     * @throws Exception
     */
    public List<Museum> getAllMuseums() throws Exception;

    /**
     * Return all exhibitions at a museum
     * @param museum The museum to return the exhibitions for
     * @return A list of Exhibition objects
     * @throws Exception
     */
    public List<Exhibition> getExhibitionsAtMuseum(Museum museum) throws Exception;

    /**
     * Return all collection for a museum
     * @param museum The museum to return the collections for
     * @return A list of Collection objects
     * @throws Exception
     */
    
    public void addExhibition(Exhibition exhibition) throws Exception;
    public List<Exhibition> getAllExhibitions() throws Exception;
    public List<Exhibition> getExhibitionsForMuseum(int museumId) throws Exception;
    public List<String> getCollectionCategoriesForMuseum(int museumId) throws Exception;
    public Collection getCollectionById(int collectionId) throws Exception;
    public Exhibition getExhibitionById(int exhibitionId) throws Exception;
}
