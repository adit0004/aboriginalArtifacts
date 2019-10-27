/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;

import entities.TicketRecord;
import entities.UserData;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import repository.UserRepository;

/**
 *
 * @author Aditya
 */
@ManagedBean(name = "userManagedBean", eager = true)
@SessionScoped

public class UserManagedBean implements Serializable {

    @EJB
    UserRepository userRepository;

    // Only need this for the name in the navbar, don't need to access object all the time
    private String currentLoggedInUserName;
    private UserData currentLoggedInUser;

    public UserManagedBean() {
        currentLoggedInUserName = null;
        currentLoggedInUser = null;
    }

    public String getCurrentLoggedInUserName() {
        setCurrentLoggedInUserName(FacesContext.getCurrentInstance().getExternalContext().getRemoteUser());
        if (currentLoggedInUserName != null) {
            // Set user object here
            try {
                currentLoggedInUser = userRepository.getUserByEmail(currentLoggedInUserName);
            } catch (Exception e) {
                Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return currentLoggedInUserName;
    }

    public void setCurrentLoggedInUserName(String currentLoggedInUserName) {
        this.currentLoggedInUserName = currentLoggedInUserName;
    }

    public UserData getCurrentLoggedInUser() {
        return currentLoggedInUser;
    }

    public void setCurrentLoggedInUser(UserData currentLoggedInUser) {
        this.currentLoggedInUser = currentLoggedInUser;
    }

    public TicketRecord fetchTicketRecord(int userId, int exhibitionId) {
        try {
            return userRepository.getTicketById(userId, exhibitionId);
        } catch (Exception e) {
            return null;
        }
    }

    public void updateUserDetails(UserData user) {
        try {
            userRepository.updateUser(user);
        } catch (Exception e) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    // Converts a string to SHA-256
    // Reference: https://www.baeldung.com/sha-256-hashing-java, but customized to a single function 
    // with the try-catch block contained within
    private String getShaHash(String originalString) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(originalString.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (int i = 0; i < encodedHash.length; i++) {
                String hex = Integer.toHexString(0xff & encodedHash[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public boolean checkUserPassword(String checkPassword) {
        // User's current password hash is already in currentLoggedInUser.getPassworD()
        if (currentLoggedInUser.getPassword().equals(getShaHash(checkPassword)))
            return true;
        return false;
    }
    
    public void updatePassword(String newPassword) {
        currentLoggedInUser.setPassword(getShaHash(newPassword));
        updateUserDetails(currentLoggedInUser);
    }
    
    public List<TicketRecord> getUserBookings() {
        try {
            return userRepository.getUserBookings(currentLoggedInUser);
        } catch (Exception e) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public TicketRecord getBookingById(int bookingId) {
        try {
            return userRepository.getBooking(bookingId);
        } catch (Exception e) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public void deleteBooking(int bookingId) {
        try {
            userRepository.deleteBooking(bookingId);
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateBooking(TicketRecord booking) {
        try {
            userRepository.updateBooking(booking);
        } catch (Exception ex) {
            Logger.getLogger(UserManagedBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
