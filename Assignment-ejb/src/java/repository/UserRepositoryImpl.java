/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.UserData;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Aditya
 */
@Stateless
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "Assignment-ejbPU")
    private EntityManager entityManager;

    
    @Override
    public void addUser(UserData user) throws Exception {
        entityManager.persist(user);
    }

    @Override
    public UserData getUserById(int userId) throws Exception {
        UserData user = entityManager.find(UserData.class, userId);
        user.getTickets().size();
        entityManager.refresh(user);
        return user;
    }

    @Override
    public void updateUser(UserData user) throws Exception {
        entityManager.merge(user);
    }

    @Override
    public UserData getUserByEmail(String userEmail) throws Exception {
        Query query = entityManager.createNamedQuery(UserData.GET_USER_BY_EMAIL);
        query.setParameter("userEmail", userEmail);
        UserData user = (UserData) query.getSingleResult();
        user.getTickets().size();
        entityManager.refresh(user);
        return user;
    }
    
    
    
}
