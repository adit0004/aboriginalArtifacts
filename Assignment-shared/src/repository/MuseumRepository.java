/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

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
    public List<Collection> getCollectionsAtMuseum(Museum museum) throws Exception;

    /**
     * Add a collection to a museum
     * @param museum The museum to add the collection to 
     * @param collection The collection to add
     * @throws Exception
     */
    public void addCollectionToMuseum(Museum museum, Collection collection) throws Exception;

    /**
     * Add an exhibition to a museum
     * @param museum The museum to add the exhibition to
     * @param exhibition The exhibition to add
     * @throws Exception
     */
    public void addExhibitionToMuseum(Museum museum, Exhibition exhibition) throws Exception;

    /**
     * Remove a collection from a museum
     * @param museum The museum to remove the collection from
     * @param collection The collection to remove
     * @throws Exception
     */
    public void removeCollectionFromMuseum(Museum museum, Collection collection) throws Exception;

    /**
     * Remove an exhibition from a museum
     * @param museum The museum to remove the exhibition from
     * @param exhibition The exhibition to remove
     * @throws Exception
     */
    public void removeExhibitionFromMuseum(Museum museum, Exhibition exhibition) throws Exception;
}
