package com.museumApplication.Backend.controller;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.model.EventBookingDTO;
import com.museumApplication.Backend.model.EventRequestDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.service.EventsService;
import jdk.jfr.Description;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/events")
@Description("API to Access the Event Table Details and Data...")
public class EventController {
    @Autowired
    private LoggerService logger;

    @Autowired
    private EventsService eventsService;

    @GetMapping("/all")
    public ResponseEntity<ResponseDTO> getAllEvents()
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("====================== Request to get All the Events Details ======================");
            response = this.eventsService.getAllEventsDetails();
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================== Error Occurred while fetching Data..====================== Error : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<ResponseDTO> getEventById(@RequestParam UUID id)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("====================== Request to get the Events Details By Id ======================");
            response = this.eventsService.getEventsDetailsById(id);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================== Error Occurred while fetching Data..====================== Error : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addNewEvent(@RequestBody EventRequestDTO eventDto)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("====================== Request to Add the New Events ======================");
            response = this.eventsService.createNewEvent(eventDto);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================== Error Occurred while Creating New Event...====================== Error : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateEventDetails(@RequestBody EventRequestDTO eventDTO)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("====================== Request to Update the Events ======================");
            response = this.eventsService.updateEvent(eventDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================== Error Occurred while Updating the Event...====================== Error : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteEventDetails(@RequestParam UUID id)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("====================== Request to Delete the Events ======================");
            response = this.eventsService.deleteEvent(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================== Error Occurred while Deleting the Event...====================== Error : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/book")
    public ResponseEntity<ResponseDTO> bookEvent(@RequestBody EventBookingDTO requestDTO)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("====================== Request to Book the Events ======================");
            response = this.eventsService.bookEvent(requestDTO);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================== Error Occurred while Booking the Event...====================== Error : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tickets/all")
    public ResponseEntity<ResponseDTO> getAllTickets()
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("====================== Request to Get All the Tickets ======================");
            response = this.eventsService.getAllTickets();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================== Error Occurred while Fetching All Tickets Details...====================== Error : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
