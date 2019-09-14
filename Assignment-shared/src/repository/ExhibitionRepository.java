/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import entities.Exhibition;
import entities.Ticket;

/**
 *
 * @author Aditya
 */
public interface ExhibitionRepository {
    public void addExhibition(Exhibition exhibition) throws Exception;
    public void addTicketToExhibition(Ticket ticket) throws Exception;
}
