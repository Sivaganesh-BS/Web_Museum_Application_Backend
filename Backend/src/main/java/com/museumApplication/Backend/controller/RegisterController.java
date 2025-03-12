package com.museumApplication.Backend.controller;

import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.entities.Users;
import com.museumApplication.Backend.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Controller
@RestController
@RequestMapping("/api/register")
public class RegisterController {

    @Autowired
    private LoggerService logger;

    @Autowired
    RegisterService registerService;

    @PostMapping("/newuser")
    public ResponseEntity<ResponseDTO> addNewUser(@RequestBody Users users)
    {
        try
        {
            logger.info("=======================Request is for Register New User=======================");
            ResponseDTO responseDTO =new ResponseDTO();
            if(users.getName()==null || users.getPassword()==null || users.getEmail()==null ||
                users.getMembership_tier()==null || users.getRole()==null) {
                responseDTO.setStatus("400");
                responseDTO.setMessage("One of Name, Password, Email, Membership_tier or Role is Missing...");
                logger.error("=======================One of Id, Name or Password is Missing...=======================");
                return new ResponseEntity<>(responseDTO,HttpStatus.BAD_REQUEST);
            }
            users.setRegistration_date(new Date(System.currentTimeMillis()));
            users.setStatus(true);
            Users newuser= registerService.addNewUser(users);
            responseDTO.setStatus("201");
            responseDTO.setMessage("New User Created...");
            responseDTO.setData(newuser);
            logger.info("=======================New User Created...=======================");
            return new ResponseEntity<>(responseDTO,HttpStatus.CREATED);
        }
        catch (Exception e)
        {
            ResponseDTO responseDTO =new ResponseDTO();
            responseDTO.setStatus("500");
            responseDTO.setMessage(e.getMessage());
            logger.error("=======================Error Occurred=======================\n"+e.getMessage());
            return new ResponseEntity<>(responseDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
