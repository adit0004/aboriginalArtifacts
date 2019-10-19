/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.TicketRecord;
import entities.UserData;
import javax.ejb.Remote;

/**
 *
 * @author Aditya
 */
@Remote
public interface UserRepository {
    public void addUser(UserData user) throws Exception;
    public UserData getUserById(int userId) throws Exception;
    public void updateUser(UserData user) throws Exception;
    public UserData getUserByEmail(String userEmail) throws Exception;
    public TicketRecord getTicketById(int userId, int exhibitionId) throws Exception;
}
