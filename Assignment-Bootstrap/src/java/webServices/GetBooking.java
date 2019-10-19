/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webServices;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.TicketRecord;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import repository.UserRepository;

/**
 * REST Web Service
 *
 * @author Aditya
 */
@Path("getBooking/{userId}/{exhibitionId}")
public class GetBooking {

    @Context
    private UriInfo context;
    
    @EJB
    UserRepository userRepository;
    /**
     * Creates a new instance of GetBooking
     */
    public GetBooking() {
    }

    /**
     * Retrieves representation of an instance of webServices.GetBooking
     * @param userId
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getBookingDetails(@PathParam("userId") int userId, @PathParam("exhibitionId") int exhibitionId) {
//        // Fetch the actual record to return from userManagedBean
        try{
            TicketRecord record = userRepository.getTicketById(userId, exhibitionId);
            // Convert record to json representation (Using external Jackson library)
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(record);
            return json;
        } catch (Exception e) {
            Logger.getLogger(GetBooking.class.getName()).log(Level.SEVERE, null, e);
            return "";
        }
    }

    /**
     * PUT method for updating or creating an instance of GetBooking
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_XML)
    public void putXml(String content) {
    }
}
