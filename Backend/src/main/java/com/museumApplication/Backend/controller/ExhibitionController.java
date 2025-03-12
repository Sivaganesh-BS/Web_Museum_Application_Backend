package com.museumApplication.Backend.controller;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.entities.Exhibition;
import com.museumApplication.Backend.model.ExhibitionRequestDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.service.ExhibitionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/exhibitions")
public class ExhibitionController {

    @Autowired
    private LoggerService logger;

    @Autowired
    private ExhibitionsService exhibitionsService;

    @GetMapping("/")
    public ResponseEntity<ResponseDTO> getAllExhibitions()
    {
        this.logger.info("====================Request to get All the Exhibitions====================");
        ResponseDTO response = new ResponseDTO();
        try
        {
            List<Exhibition> data = this.exhibitionsService.getAllExhibitions();
            response.setStatus("200");
            response.setData(data);
            response.setMessage("Data Fetching Completed Successfully..");
            this.logger.info("====================Data Fetching Completed Successfully====================");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================Data Fetching Failed==================== \nError : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<ResponseDTO> getExhibitionById(@RequestParam UUID id)
    {
        this.logger.info("====================Request to get the Exhibitions for Id"+id+" ====================");
        ResponseDTO response = new ResponseDTO();
        try
        {
            Exhibition data = this.exhibitionsService.getExhibitionById(id);
            response.setStatus("200");
            response.setData(data);
            response.setMessage("Data Fetching Completed Successfully for Id"+id);
            this.logger.info("====================Data Fetching Completed Successfully for Id"+id+" ====================");
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================Data Fetching Failed==================== \nError : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> addNewExhibition(@RequestBody ExhibitionRequestDTO exhibition)
    {
        this.logger.info("====================Request to Add New Exhibitions====================");
        ResponseDTO response = new ResponseDTO();
        try
        {
            if(exhibition.getTitle()==null || exhibition.getCategory()==null || exhibition.getImage()==null || exhibition.getDescription()==null)
            {
                response.setStatus("400");
                response.setMessage("Data Insertion Failed--- Provide Valid Payload..");
                this.logger.error("====================Data Insertion Failed====================");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            response = this.exhibitionsService.addNewExhibition(exhibition);
            return new ResponseEntity<>(response,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================Data Insertion Failed==================== \nError : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<ResponseDTO> updateExhibition(@RequestBody ExhibitionRequestDTO exhibition)
    {
        this.logger.info("====================Request to Update Exhibitions ====================");
        ResponseDTO response = new ResponseDTO();
        try
        {
            if(exhibition.getExhibition_id()==null)
            {
                response.setStatus("400");
                response.setMessage("Provide Valid Payload. Provide Exhibition Id to Update");
                this.logger.error("====================Data Update Failed====================");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            response = this.exhibitionsService.updateExhibition(exhibition);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================Data Update Failed==================== \nError : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteExhibition(@RequestBody ExhibitionRequestDTO exhibition)
    {
        this.logger.info("====================Request to Delete Exhibitions====================");
        ResponseDTO response = new ResponseDTO();
        try
        {
            if(exhibition.getExhibition_id()==null)
            {
                response.setStatus("400");
                response.setMessage("Provide Valid Payload. Provide Exhibition Id to Delete");
                this.logger.error("====================Data Delete Failed====================");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            response = this.exhibitionsService.deleteExhibition(exhibition);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================Data Delete Failed==================== \nError : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
