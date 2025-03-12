package com.museumApplication.Backend.service;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.entities.*;
import com.museumApplication.Backend.model.EventBookingDTO;
import com.museumApplication.Backend.model.EventRequestDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.repo.EventsRepository;
import com.museumApplication.Backend.repo.PaymentsRepository;
import com.museumApplication.Backend.repo.TicketsRepository;
import com.museumApplication.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class EventsService {

    @Autowired
    private EventsRepository eventsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private LoggerService logger;

    @Autowired
    private PaymentsRepository paymentsRepo;

    @Autowired
    private TicketsRepository ticketsRepo;

    public ResponseDTO getAllEventsDetails()
    {
        ResponseDTO response = new ResponseDTO();
        response.setData(this.eventsRepo.findAll());
        response.setStatus("200");
        response.setMessage("Data Fetching Completed Successfully..");
        this.logger.info("===========================Data Fetching Completed Successfully..===========================");
        return response;
    }

    public ResponseDTO getEventsDetailsById(UUID id) throws Exception {
        ResponseDTO response = new ResponseDTO();
        try
        {
            Event oldEvent = this.eventsRepo.findById(id).get();

            if(oldEvent==null)
            {
                this.logger.error("===========================Provide Valid Event Id ===========================");
                response.setMessage("Provide Valid Event Id");
                response.setStatus((HttpStatus.NOT_FOUND).toString());
                return response;
            }
            response.setData(oldEvent);
            response.setStatus("200");
            response.setMessage("=========================== Data Fetching is Completed Successfully ===========================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("=========================== Error Occurred while Fetching Data =========================== Error :"+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO createNewEvent(EventRequestDTO eventDTO) throws Exception{
        ResponseDTO response = new ResponseDTO();
        try
        {

            boolean isNull = Stream.of( eventDTO.getEvent_id()==null ? "Null" : eventDTO.getEvent_id(),
                                        eventDTO.getTitle(),eventDTO.getDescription(), eventDTO.getEvent_date(),
                                        eventDTO.getLocation(),eventDTO.getEvent_type(),
                                        eventDTO.getPrice_per_ticket()==0 ? null : eventDTO.getPrice_per_ticket(),
                                        eventDTO.getMax_capacity()==0 ? null : eventDTO.getMax_capacity(),
                                        eventDTO.getAvailable_seats()==0 ? null : eventDTO.getAvailable_seats())
                            .anyMatch(anyValue-> anyValue==null);
            if(isNull)
            {
                this.logger.error("===========================Provide Valid Event Payload ===========================");
                throw new Exception("Provide Valid Event Payload");
            }
            Event newEvent = new Event(eventDTO.getTitle(),eventDTO.getDescription(),eventDTO.getEvent_date(),
                    eventDTO.getLocation(),eventDTO.getEvent_type(),eventDTO.getPrice_per_ticket(),
                    eventDTO.getMax_capacity(),eventDTO.getAvailable_seats());

            response.setData(this.eventsRepo.save(newEvent));
            response.setMessage("New Event Created Successfully...");
            response.setStatus("201");
            this.logger.info("===========================New Event Created Successfully..===========================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("=========================== Error Occurred while Creating New Event =========================== Error :"+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO updateEvent(EventRequestDTO eventDTO) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            if(eventDTO.getEvent_id()==null)
            {
                this.logger.error("=========================== Provide Valid Event Id to Update ===========================");
                throw new Exception("Provide Valid Event Id to Update");
            }

            Event oldEvent = this.eventsRepo.findById(eventDTO.getEvent_id()).get();
            if(oldEvent==null)
            {
                this.logger.error("=========================== Provide Valid Event Id to Update ===========================");
                throw new Exception("Provide Valid Event Id to Update");
            }

            Event newEvent = new Event(eventDTO.getTitle()==null ? oldEvent.getTitle() : eventDTO.getTitle(),
                                eventDTO.getDescription()==null ? oldEvent.getDescription() : eventDTO.getDescription(),
                                eventDTO.getEvent_date()==null ? oldEvent.getEvent_date() : eventDTO.getEvent_date(),
                                eventDTO.getLocation()==null ? oldEvent.getLocation() : eventDTO.getLocation(),
                                eventDTO.getEvent_type()==null ? oldEvent.getEvent_type() : eventDTO.getEvent_type(),
                                eventDTO.getPrice_per_ticket()==0 ? oldEvent.getPrice_per_ticket() : eventDTO.getPrice_per_ticket(),
                                eventDTO.getMax_capacity()==0 ? oldEvent.getMax_capacity() : eventDTO.getMax_capacity(),
                                eventDTO.getAvailable_seats()==0 ? oldEvent.getAvailable_seats() : eventDTO.getAvailable_seats());
            newEvent.setEvent_id(oldEvent.getEvent_id());

            response.setData(this.eventsRepo.save(newEvent));
            response.setMessage("Event Updated Successfully...");
            response.setStatus("200");
            this.logger.info("===========================Event Updated Successfully..===========================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("=========================== Error Occurred while Updating New Event =========================== Error :"+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }


    public ResponseDTO deleteEvent(UUID id) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            Event oldEvent = this.eventsRepo.findById(id).get();
            if(oldEvent==null)
            {
                response.setStatus((HttpStatus.NOT_FOUND).toString());
                response.setMessage("Provide Valid Event Id to Delete");
                this.logger.error("======================= Provide Valid Event Id to Delete =======================");
                return response;
            }
            this.eventsRepo.deleteById(oldEvent.getEvent_id());
            response.setStatus("200");
            response.setMessage("The Event Data is Deleted...");
            this.logger.info("=======================Event Data is Deleted=======================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("======================= Error Occurred======================= Error: "+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO bookEvent(EventBookingDTO requestDTO) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            Users user = userRepo.findById(requestDTO.getUserId()).get();

            if(user==null)
            {
                this.logger.error("======================= Provide Valid User Id =======================");
                throw new Exception("Provide Valid User Id..");
            }

            Event event = this.eventsRepo.findById(requestDTO.getEventId()).get();
            if(event==null)
            {
                this.logger.error("======================= Provide Valid Event Id =======================");
                throw new Exception("Provide Valid Event Id..");
            }

            if(requestDTO.getQuantity()<0 || requestDTO.getQuantity()>event.getAvailable_seats())
            {
                this.logger.error("======================= Provide Valid Tickets Count =======================");
                throw new Exception("Provide Valid Tickets Count..");
            }

            double totalTicketPrice = requestDTO.getQuantity() * event.getPrice_per_ticket();

            if(requestDTO.getAmount()<totalTicketPrice)
            {
                this.logger.error("======================= Provide Sufficient Amount to Book Tickets =======================");
                throw new Exception("Provide Sufficient Amount to Book Tickets..");
            }

            this.logger.info("======================= Payment Initiated...=======================");
            Payments newPayment = new Payments(requestDTO.getAmount(),requestDTO.getPayment_method(),user);
            this.paymentsRepo.save(newPayment);
            this.logger.info("======================= Payment Completed...=======================");

            Ticket ticket = new Ticket(requestDTO.getQuantity(),totalTicketPrice,user,event,newPayment.isStatus());
            ticketsRepo.save(ticket);
            this.logger.info("======================= New Ticket Created... =======================");

            // Update the Events Table to reduce the No of Available Seats
            event.setAvailable_seats(event.getAvailable_seats()-requestDTO.getQuantity());
            this.eventsRepo.save(event);

            System.out.println(ticket.toString());
            response.setStatus("201");
            response.setMessage("New Ticket Created...");

            return response;
        }
        catch (Exception e)
        {
            this.logger.error("======================= Error Occurred======================= Error: "+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO getAllTickets() throws Exception {

        ResponseDTO response = new ResponseDTO();

        try
        {
            this.logger.info("======================= Request to Get All the Ticket Details =======================");
            Object data = this.ticketsRepo.findAll();

            response.setData(data);
            response.setStatus("200");
            response.setMessage("Data Fetching for All Tickets Completed..");
            this.logger.info("======================= Data Fetching for All Tickets Completed.. =======================");

            return response;
        }
        catch (Exception e)
        {
            this.logger.error("======================= Error Occurred======================= Error: "+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
