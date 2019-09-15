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
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Collection> getCollectionsAtMuseum(Museum museum) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addCollectionToMuseum(Museum museum, Collection collection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addExhibitionToMuseum(Museum museum, Exhibition exhibition) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeCollectionFromMuseum(Museum museum, Collection collection) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeExhibitionFromMuseum(Museum museum, Exhibition exhibition) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
}
