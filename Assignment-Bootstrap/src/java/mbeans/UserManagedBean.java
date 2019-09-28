/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbeans;

import entities.UserData;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
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
        if(currentLoggedInUserName != null && currentLoggedInUser == null) {
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
    
}
