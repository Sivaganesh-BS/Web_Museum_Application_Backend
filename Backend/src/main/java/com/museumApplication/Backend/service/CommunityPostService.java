package com.museumApplication.Backend.service;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.entities.CommunityPost;
import com.museumApplication.Backend.entities.Event;
import com.museumApplication.Backend.entities.Exhibition;
import com.museumApplication.Backend.entities.Users;
import com.museumApplication.Backend.model.CommunityPostRequestDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.repo.CommunityPostRepository;
import com.museumApplication.Backend.repo.ExhibitionsRepository;
import com.museumApplication.Backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class CommunityPostService {


    @Autowired
    private LoggerService logger;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ExhibitionsRepository exhibitionsRepo;

    @Autowired
    private CommunityPostRepository communityPostRepo;

    public ResponseDTO getAllCommunityPost() throws Exception {

        ResponseDTO response = new ResponseDTO();
        try
        {
            Object data = this.communityPostRepo.findAll();
            response.setData(data);
            response.setStatus("200");
            response.setMessage("Data Fetching for All Post Completed Successfully...");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("========================== Error Occurred ==========================");
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO getCommunityPostById(UUID id) throws Exception {
        ResponseDTO response = new ResponseDTO();
        try
        {
            Object data = this.communityPostRepo.findById(id);
            if(data==null)
            {
                this.logger.error("========================== Provide Valid Id Value... ==========================");
                throw new Exception("Provide Valid Id Value...");
            }
            response.setData(data);
            response.setStatus("200");
            response.setMessage("Data Fetching for Community Post Completed Successfully...");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("========================== Error Occurred ==========================");
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO createNewCommunityPost(CommunityPostRequestDTO requestDTO) throws Exception {
        ResponseDTO response = new ResponseDTO();
        try
        {
            boolean isNull = Stream.of(requestDTO.getContent() , requestDTO.getExhibitionId(), requestDTO.getUserId()==0 ? null : "NA")
                    .anyMatch(any-> any==null);
            if(isNull)
            {
                this.logger.error("========================== Provide Valid Payload Value... ==========================");
                throw new Exception("Provide Valid Payload Value...");
            }

            Users user = this.userRepo.findById(requestDTO.getUserId()).get();
            if(user==null)
            {
                this.logger.error("========================== Provide Valid User Id... ==========================");
                throw new Exception("Provide Valid User Id Value...");
            }

            Exhibition exhibition = this.exhibitionsRepo.findById(requestDTO.getExhibitionId()).get();
            if(exhibition==null)
            {
                this.logger.error("========================== Provide Valid Exhibition Id... ==========================");
                throw new Exception("Provide Valid Exhibition Id Value...");
            }

            CommunityPost newPost = new CommunityPost(user,requestDTO.getContent(),exhibition);

            Object data = this.communityPostRepo.save(newPost);

            response.setData(data);
            response.setStatus("200");
            response.setMessage("New Community Post is Insert Completed Successfully...");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("========================== Error Occurred ==========================");
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO updateCommunityPost(CommunityPostRequestDTO requestDTO) throws Exception {

        ResponseDTO response = new ResponseDTO();
        try
        {
            if(requestDTO.getContent()==null)
            {
                this.logger.error("========================== Provide Valid Content... ==========================");
                throw  new Exception("Provide Valid Content...");
            }
            CommunityPost post = this.communityPostRepo.findById(requestDTO.getCommunity_post_id()).get();
            if(post==null)
            {
                this.logger.error("========================== Provide Valid Community Post Id... ==========================");
                throw  new Exception("Provide Valid Community Post Id...");
            }
            post.setContent(requestDTO.getContent());
            post.setUpdated_at(new Date(System.currentTimeMillis()));

            Object data =this.communityPostRepo.save(post);
            response.setData(data);
            response.setStatus("200");
            response.setMessage("The Community Post is Updated Successfully...");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("========================== Error Occurred ==========================");
            throw new Exception(e.getMessage());
        }
    }

    public ResponseDTO deletePost(UUID id) throws Exception {
        ResponseDTO response = new ResponseDTO();

        try
        {
            CommunityPost Oldpost = this.communityPostRepo.findById(id).get();
            if(Oldpost==null)
            {
                response.setStatus((HttpStatus.NOT_FOUND).toString());
                response.setMessage("Provide Valid Post Id to Delete");
                this.logger.error("======================= Provide Valid Post Id to Delete =======================");
                return response;
            }
            this.communityPostRepo.deleteById(Oldpost.getPost_id());
            response.setStatus("200");
            response.setMessage("The Post Data is Deleted...");
            this.logger.info("=======================Post Data is Deleted...=======================");
            return response;
        }
        catch (Exception e)
        {
            this.logger.error("======================= Error Occurred======================= Error: "+e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
}
