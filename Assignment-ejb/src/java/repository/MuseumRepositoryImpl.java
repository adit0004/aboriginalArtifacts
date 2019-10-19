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
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

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
        museum.getMuseumCollections().iterator().next().getCollectionArtifacts().size();
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

    @Override
    public List<String> getCollectionCategoriesForMuseum(int museumId) throws Exception {
        Query query = entityManager.createNamedQuery(Collection.GET_CATEGORY_LIST_FOR_MUSEUM);
        query.setParameter("museumId", museumId);
        return query.getResultList();
    }
    
    @Override
    public Collection getCollectionById(int collectionId) throws Exception {
        Collection collection = entityManager.find(Collection.class, collectionId);
        collection.getCollectionArtifacts().size();
        entityManager.refresh(collection);
        return collection;
    }

    @Override
    public Exhibition getExhibitionById(int exhibitionId) throws Exception {
        Exhibition exhibition = entityManager.find(Exhibition.class, exhibitionId);
        exhibition.getExhibitionCollection().getCollectionName();
        entityManager.refresh(exhibition);
        return exhibition;
    }

    @Override
    public List<Museum> searchMuseumByNameOrAddress(String searchQuery) throws Exception {
        Query query = entityManager.createNamedQuery(Museum.SEARCH_BY_NAME_ADDRESS);
        query.setParameter("museumName", "%" + searchQuery + "%");
        query.setParameter("postcode", "%" + searchQuery + "%");
        query.setParameter("state", "%" + searchQuery + "%");
        query.setParameter("suburb", "%" + searchQuery + "%");
        List<Museum> returnList = query.getResultList();
        return returnList;
    }

    @Override
    public List<Collection> searchCollectionByNameDescriptionCurator(String searchQuery) throws Exception {
        Query query = entityManager.createNamedQuery(Collection.SEARCH_BY_NAME_DESC_CURATOR);
        query.setParameter("collectionName", "%" + searchQuery + "%");
        query.setParameter("collectionDescription", "%" + searchQuery + "%");
        query.setParameter("collectionCurator", "%" + searchQuery + "%");
        List<Collection> returnList = query.getResultList();
        return returnList;
    }

    @Override
    public List<Artifact> searchArtifactByNameOrDescription(String searchQuery) throws Exception {
        Query query = entityManager.createNamedQuery(Artifact.SEARCH_BY_NAME_DESC);
        query.setParameter("artifactName", "%" + searchQuery + "%");
        query.setParameter("artifactDescription", "%" + searchQuery + "%");
        List<Artifact> returnList = query.getResultList();
        return returnList;
    }
    
    
    
}
