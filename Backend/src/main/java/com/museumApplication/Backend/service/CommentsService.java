package com.museumApplication.Backend.service;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.entities.Comment;
import com.museumApplication.Backend.entities.CommunityPost;
import com.museumApplication.Backend.entities.Users;
import com.museumApplication.Backend.model.CommentRequestDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.repo.CommentsRepository;
import com.museumApplication.Backend.repo.CommunityPostRepository;
import com.museumApplication.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentsService {

    @Autowired
    private LoggerService logger;

    @Autowired
    private CommentsRepository commentsRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private CommunityPostRepository communityPostRepo;


    public ResponseDTO getAllComments() throws Exception {
        ResponseDTO response = new ResponseDTO();
        try
        {
            Object data = this.commentsRepo.findAll();

            response.setMessage("The Data Fetching for the Retrieval of All Comments Done Successfully..");
            response.setStatus("200");
            response.setData(data);
            this.logger.info("===============The Data Fetching for the Retrieval of All Comments Done Successfully..================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("================== Error Occurred ==================");
            throw  new Exception(e.getMessage());
        }
    }

    public ResponseDTO getCommentsById(UUID id) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            Object data = this.commentsRepo.findById(id);
            response.setMessage("The Data Fetching for the Retrieval of Comments Done Successfully..");
            response.setStatus("200");
            response.setData(data);
            this.logger.info("===============The Data Fetching for the Retrieval of Comments Done Successfully..================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("=========================Error Occurred==========================");
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO deleteCommentsById(UUID id) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            this.commentsRepo.deleteById(id);
            response.setMessage("The Comment Deletion Done Successfully..");
            response.setStatus("200");
            this.logger.info("===============The Comment Deletion Done Successfully..================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("=========================Error Occurred==========================");
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO createNewComment(CommentRequestDTO requestDTO) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            Users user =  this.userRepo.findById(requestDTO.getUserid()).get();

            CommunityPost communityPost = this.communityPostRepo.findById(requestDTO.getCommunitypostid()).get();

            if(user==null || communityPost==null || requestDTO.getContent()==null)
            {
                this.logger.error("=============Provide Valid Payload Data...==================");
                throw new Exception("Provide Valid Payload Data...");
            }

            Comment newComment = new Comment(user,requestDTO.getContent(),communityPost);

            Object data = this.commentsRepo.save(newComment);

            response.setData(data);
            response.setMessage("The Comment Creation Done Successfully..");
            response.setStatus("200");
            this.logger.info("===============The Comment Creation Done Successfully..================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("=========================Error Occurred==========================");
            throw new Exception(e.getMessage());
        }
    }
}
