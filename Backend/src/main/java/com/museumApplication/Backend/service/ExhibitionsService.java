package com.museumApplication.Backend.service;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.entities.Exhibition;
import com.museumApplication.Backend.model.ExhibitionRequestDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.repo.ExhibitionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ExhibitionsService {

    @Autowired
    private LoggerService logger;

    @Autowired
    private ExhibitionsRepository repo;

    public List<Exhibition> getAllExhibitions()
    {
        return this.repo.findAll();
    }

    public Exhibition getExhibitionById(UUID id)
    {
        return this.repo.findById(id).get();
    }

    public ResponseDTO addNewExhibition(ExhibitionRequestDTO exhibition) throws Exception {

        ResponseDTO response = new ResponseDTO();
        try
        {
            Exhibition newExhibition = new Exhibition();
            newExhibition.setTitle(exhibition.getTitle());
            newExhibition.setCategory(exhibition.getCategory());
            newExhibition.setDescription(exhibition.getDescription());
            newExhibition.setImage(exhibition.getImage());
            newExhibition.setCreated_date(new Date(System.currentTimeMillis()));
            newExhibition.setUpdated_date(new Date(System.currentTimeMillis()));

            response.setData(this.repo.save(newExhibition));
            response.setStatus("201");
            response.setMessage("New Exhibition Data is Inserted...");
            this.logger.info("=======================New Exhibition Data is Inserted=======================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("======================= Error Occurred======================= Error: "+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO updateExhibition(ExhibitionRequestDTO exhibition) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            Exhibition oldExhibition = this.repo.findById(exhibition.getExhibition_id()).get();
            if(oldExhibition==null)
            {
                response.setStatus("400");
                response.setMessage("Provide Valid Exhibition Id to Update");
                this.logger.error("======================= Provide Valid Exhibition Id to Update =======================");
                return response;
            }
            Exhibition newExhibition = new Exhibition();
            newExhibition.setExhibition_id(oldExhibition.getExhibition_id());
            newExhibition.setCreated_date(oldExhibition.getCreated_date());
            newExhibition.setUpdated_date(new Date(System.currentTimeMillis()));

            if(exhibition.getTitle()==null) newExhibition.setTitle(oldExhibition.getTitle());
            else newExhibition.setTitle(exhibition.getTitle());

            if(exhibition.getDescription()==null) newExhibition.setDescription(oldExhibition.getDescription());
            else newExhibition.setDescription(exhibition.getDescription());

            if(exhibition.getCategory()==null) newExhibition.setCategory(oldExhibition.getCategory());
            else newExhibition.setCategory(exhibition.getCategory());

            if(exhibition.getImage()==null) newExhibition.setImage(oldExhibition.getImage());
            else newExhibition.setImage(exhibition.getImage());

            response.setData(this.repo.save(newExhibition));
            response.setStatus("200");
            response.setMessage("The Exhibition Data is Updated...");
            this.logger.info("=======================The Exhibition Data is Updated...=======================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("======================= Error Occurred======================= Error: "+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO deleteExhibition(ExhibitionRequestDTO exhibition) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            Exhibition oldExhibition = this.repo.findById(exhibition.getExhibition_id()).get();
            if(oldExhibition==null)
            {
                response.setStatus((HttpStatus.NOT_FOUND).toString());
                response.setMessage("Provide Valid Exhibition Id to Delete");
                this.logger.error("======================= Provide Valid Exhibition Id to Delete =======================");
                return response;
            }
            this.repo.deleteById(exhibition.getExhibition_id());
            response.setStatus("200");
            response.setMessage("The Exhibition Data is Deleted...");
            this.logger.info("=======================Exhibition Data is Deleted=======================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("======================= Error Occurred======================= Error: "+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
