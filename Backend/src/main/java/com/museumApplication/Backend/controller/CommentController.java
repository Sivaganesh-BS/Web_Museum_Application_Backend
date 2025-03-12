package com.museumApplication.Backend.controller;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.model.CommentRequestDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private LoggerService logger;

    @Autowired
    private CommentsService commentsService;

    @RequestMapping("/post/all")
    public ResponseEntity<ResponseDTO> getAllComments()
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("===============The Request to Get All the Comments Data ====================");
            response = this.commentsService.getAllComments();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            this.logger.error("================= Error Occurred =================");
            response.setStatus("500");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping("/post/id")
    public ResponseEntity<ResponseDTO> getCommentById(@RequestParam UUID id)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("===============The Request to Get the Comments Data ====================");
            response = this.commentsService.getCommentsById(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            this.logger.error("================= Error Occurred =================");
            response.setStatus("500");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/post/delete")
    public ResponseEntity<ResponseDTO> deleteCommentById(@RequestParam UUID id)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("===============The Request to Delete the Comments Data ====================");
            response = this.commentsService.deleteCommentsById(id);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            this.logger.error("================= Error Occurred =================");
            response.setStatus("500");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/post/create")
    public ResponseEntity<ResponseDTO> createNewComment(@RequestBody CommentRequestDTO requestDTO)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            this.logger.info("===============The Request to Create the New Comments Data ====================");
            response = this.commentsService.createNewComment(requestDTO);
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            this.logger.error("================= Error Occurred =================");
            response.setStatus("500");
            response.setMessage(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
