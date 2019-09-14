/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Exhibition;
import entities.Ticket;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author adityakumar
 */
public class ExhibitionRepositoryImpl implements ExhibitionRepository{
    @PersistenceContext(unitName = "Assignment-ejbPU")
    private EntityManager entityManager;
    
    @Override
    public void addExhibition(Exhibition exhibition) {
        entityManager.persist(exhibition);
    }

    @Override
    public void addTicketToExhibition(Ticket ticket) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
