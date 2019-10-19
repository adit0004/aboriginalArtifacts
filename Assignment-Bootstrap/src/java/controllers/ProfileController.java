/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.UserData;
import javax.el.ELContext;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.validation.constraints.Size;
import mbeans.UserManagedBean;

/**
 *
 * @author Aditya
 */

@Named(value = "profileController")
@RequestScoped
public class ProfileController {
    static @ManagedProperty(value = "#{userManagedBean}")
    UserManagedBean userManagedBean;
    
    UserData currentUser;
    
    @Size(min=2, max=240, message="Oops! Name must have at least 2 characters!")
    private String firstName;
    @Size(min=2, max=240, message="Oops! Name must have at least 2 characters!")
    private String lastName;
    private String email;
    private String phoneNumber;
    
    private String oldPassword;
    private String newPassword;
    private String confirmNewPassword;
    
    public ProfileController(){
        ELContext elContext = FacesContext.getCurrentInstance().getELContext();
        if (userManagedBean == null)
            userManagedBean = (UserManagedBean) FacesContext.getCurrentInstance().getApplication().getELResolver().getValue(elContext, null, "userManagedBean");
        currentUser = userManagedBean.getCurrentLoggedInUser();
        
        // Instantiate all fields
        firstName = currentUser.getFirstName();
        lastName = currentUser.getLastName();
        email = currentUser.getEmail();
        phoneNumber = currentUser.getPhoneNumber();
        
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String updateDetails() {
        // Only triggered if all validations passed
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setPhoneNumber(phoneNumber);
        currentUser.setEmail(email);
        userManagedBean.updateUserDetails(currentUser);
        userManagedBean.setCurrentLoggedInUser(currentUser);
        return "/user/profile?success=true";
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return confirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        this.confirmNewPassword = confirmNewPassword;
    }
    
    public String updatePassword() {
        
        boolean flag = false;
        // 1. Check if old password is correct
        
        if (!userManagedBean.checkUserPassword(oldPassword)) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("That's not correct! Please verify that you are entering your current password!");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage("passwordResetForm:oldPassword", facesMessage);
            flag = true;
        }
        
        // 2. Check if new password passes regexp *AND* be more than 8 characters
        
        if (!newPassword.matches("(?:[A-Z])+(?:[a-z])+(?:[0-9]+)(?:[\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)\\{\\}\\[\\]\\\\\\?\\.\\>\\<\\,\\'\\\"\\:\\;\\+\\_\\-\\=])+") || !(newPassword.length() > 8)){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("The new password must be at least 8 characters, contain 1 lowercase letter (a-z), 1 uppercase letter (A-Z), 1 number (0-9), and one special character (!@#$%^&*(){}[]?.><,'\":;+_-=\\)");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage("passwordResetForm:newPassword", facesMessage);
            flag = true;
        }
        
        // 3. Check if confirm password is same as new password
        if (!newPassword.equals(confirmNewPassword)){
            FacesContext facesContext = FacesContext.getCurrentInstance();
            FacesMessage facesMessage = new FacesMessage("Confirm password did not match!");
            facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
            facesContext.addMessage("passwordResetForm:confirmNewPassword", facesMessage);
            flag = true;
        }
        
        // 4. Update password (entityManager.merge)
        return "/user/profile?passwordSuccess=true";
    }
    
    
}
