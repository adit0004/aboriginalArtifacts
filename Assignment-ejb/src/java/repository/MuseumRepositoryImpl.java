/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Collection;
import entities.Exhibition;
import entities.Museum;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author adity
 */
@Stateless
public class MuseumRepositoryImpl implements MuseumRepository {

    @PersistenceContext(unitName = "Assignment-ejbPU")
    private EntityManager entityManager;
    
    @Override
    public void addMuseum(Museum museum) throws Exception {
        entityManager.persist(museum);
    }

    @Override
    public Museum getMuseumById(int museumId) throws Exception {
        Museum museum = entityManager.find(Museum.class, museumId);
        museum.getMuseumCollections().size();
        museum.getMuseumExhibitions().size();
        Set<Collection> collections = museum.getMuseumCollections();
        collections.size();
        entityManager.refresh(museum);
        return museum;
    }

    @Override
    public void removeMuseum(int museumId) throws Exception {
        Museum museum = this.getMuseumById(museumId);

        if (museum != null) {
            entityManager.remove(museum);
        }
    }

    @Override
    public void editMuseum(Museum museum) throws Exception {
        entityManager.merge(museum);
    }

    @Override
    public List<Museum> getAllMuseums() throws Exception {
        return entityManager.createNamedQuery(Museum.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public List<Exhibition> getExhibitionsAtMuseum(Museum museum) throws Exception {
        return entityManager.createNamedQuery(Museum.GET_EXHIBITIONS_FOR_MUSEUM_QUERY).getResultList();
    }
    
    

        @Override
    public void addExhibition(Exhibition exhibition) {
        entityManager.persist(exhibition);
    }

    @Override
    public ArrayList<Exhibition> getAllExhibitions() throws Exception {
        return (ArrayList<Exhibition>) entityManager.createNamedQuery(Exhibition.GET_ALL_QUERY_NAME).getResultList();
    }

    @Override
    public List<Exhibition> getExhibitionsForMuseum(int museumId) throws Exception {
        Query query = entityManager.createNamedQuery(Exhibition.GET_EXHIBITIONS_FOR_MUSEUM);
        query.setParameter("museumId", museumId);
        return query.getResultList();
    }
}
