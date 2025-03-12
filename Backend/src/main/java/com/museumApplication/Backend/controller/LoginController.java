package com.museumApplication.Backend.controller;


import com.museumApplication.Backend.config.LoggerService;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.entities.Users;
import com.museumApplication.Backend.service.JWTService;
import com.museumApplication.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    JWTService jwtService;

    @Autowired
    private LoggerService logger;

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> userLogin(@RequestBody Users user)
    {
        ResponseDTO response = new ResponseDTO();
        try
        {
            logger.info("=======================Request is for Login=======================");
            if(user.getName()==null || user.getPassword()==null)
            {
                response.setStatus("400");
                response.setMessage("Details :Name or Password is Missing...");
                logger.error("=======================Login Failed=======================");
                return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
            }
            if(userService.verify(user))
            {
                response.setStatus("200");
                response.setMessage("Login Success...");
                response.setData(jwtService.generateJWTToken(user.getName()));
                logger.info("=======================Login Success=======================");
                return new ResponseEntity<>(response, HttpStatus.OK);
            }
            response.setStatus("404");
            response.setMessage("User Not found Login Failed...");
            logger.error("=======================Login Failed=======================");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
        catch (Exception e)
        {
            response.setStatus("500");
            response.setMessage(e.getMessage());
            logger.error("=======================Login Failed=======================\n"+e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
