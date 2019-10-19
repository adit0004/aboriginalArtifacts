package entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.io.Serializable;
import java.util.Set;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author Aditya
 */

@NamedQueries ({
    @NamedQuery (name = UserData.GET_USER_BY_EMAIL, query = "Select u from UserData u WHERE u.email = :userEmail"),
})

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="userId")
@JsonIgnoreProperties(value = { "password" })
@Access(AccessType.PROPERTY)
public class UserData implements Serializable{

    public static final String GET_USER_BY_EMAIL = "UserData.getUserByEmail";
    
    private int userId;
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private Set<TicketRecord> tickets;
    private String userType;

    public UserData() {
    }

    public UserData(String email, String password, String firstName, String lastName, String phoneNumber, Set<TicketRecord> tickets, String userType) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.tickets = tickets;
        this.userType = userType;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    public int getUserId(){
        return userId;
    }
    
    public void setUserId(int userId) {
        this.userId = userId;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "phone_number")
    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @OneToMany(fetch = FetchType.EAGER)
    public Set<TicketRecord> getTickets() {
        return tickets;
    }

    public void setTickets(Set<TicketRecord> tickets) {
        this.tickets = tickets;
    }

    @Column(name = "user_type")
    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" + "email=" + email + ", password=" + password + ", firstName=" + firstName + ", lastName=" + lastName + ", phoneNumber=" + phoneNumber + ", tickets=" + tickets + '}';
    }
}
