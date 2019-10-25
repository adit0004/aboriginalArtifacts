/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.TicketRecord;
import entities.UserData;
import java.util.List;
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

    @Override
    public TicketRecord getTicketById(int userId, int exhibitionId) throws Exception {
        Query query = entityManager.createNamedQuery(TicketRecord.GET_BOOKING_DETAILS_QUERY);
        query.setParameter("userId", userId);
        query.setParameter("exhibitionId", exhibitionId);
        try{
            TicketRecord record = (TicketRecord) query.getSingleResult();
            record.getUserDetails().getUserId();
            record.getExhibition().getExhibitionId();
            entityManager.refresh(record);
            return record;
        } catch(Exception e) {
            return null;
        }
    }

    @Override
    public List<TicketRecord> getUserBookings(UserData user) throws Exception {
        Query query = entityManager.createNamedQuery(TicketRecord.GET_ALL_BOOKINGS_FOR_USER);
        query.setParameter("userId", user.getUserId());
        List<TicketRecord> ticketRecords = query.getResultList();
        return ticketRecords;
    }

    @Override
    public TicketRecord getBooking(int bookingId) throws Exception {
        return entityManager.find(TicketRecord.class, bookingId);
    }

    @Override
    public void addBookingForUser(TicketRecord ticketRecord) throws Exception {
        List<TicketRecord> records = entityManager.createNamedQuery(TicketRecord.GET_ALL_BOOKINGS).getResultList();
        ticketRecord.setBookingId(records.get(0).getBookingId() + 1);
        entityManager.persist(ticketRecord);
    }

    @Override
    public void deleteBooking(int ticketId) throws Exception {
        TicketRecord ticket = entityManager.find(TicketRecord.class, ticketId);
        entityManager.remove(ticket);
    }
}
