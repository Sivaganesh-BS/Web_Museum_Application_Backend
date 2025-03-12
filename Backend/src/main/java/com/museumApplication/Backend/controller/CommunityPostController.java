package com.museumApplication.Backend.controller;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.model.CommunityPostRequestDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.service.CommunityPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/community")
public class CommunityPostController {

    @Autowired
    private LoggerService logger;

    @Autowired
    private CommunityPostService communityPostService;

    @RequestMapping("/post/all")
    public ResponseEntity<ResponseDTO> getAllCommunityPosts()
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("========================== Request to Get All the Post ==========================");
            response = this.communityPostService.getAllCommunityPost();

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("===========================Error Occurred while Fetching all Posts Data..===========================");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @RequestMapping("/post/id")
    public ResponseEntity<ResponseDTO> getCommunityPostById(@RequestParam UUID id)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("========================== Request to Get the Post ==========================");
            response = this.communityPostService.getCommunityPostById(id);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("===========================Error Occurred while Fetching Community Posts Data..===========================");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post/create")
    public ResponseEntity<ResponseDTO> createNewCommunityPost(@RequestBody CommunityPostRequestDTO requestDTO)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("========================== Request to Create a New Post ==========================");
            response = this.communityPostService.createNewCommunityPost(requestDTO);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("===========================Error Occurred while Creating a New Community Posts..===========================");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post/update")
    public ResponseEntity<ResponseDTO> updateCommunityPost(@RequestBody CommunityPostRequestDTO requestDTO)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("========================== Request to Update the Community Post ==========================");
            response = this.communityPostService.updateCommunityPost(requestDTO);

            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("===========================Error Occurred while Updating the Community Posts Data..===========================");
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/post/delete")
    public ResponseEntity<ResponseDTO> deleteCommunityPost(@RequestParam UUID id)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("====================== Request to Delete the Post ======================");
            response = this.communityPostService.deletePost(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            this.logger.error("====================== Error Occurred while Deleting the Post...====================== Error : "+e.getMessage());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
