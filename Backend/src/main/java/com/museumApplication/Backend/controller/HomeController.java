package com.museumApplication.Backend.controller;

import com.museumApplication.Backend.entities.Users;
import com.museumApplication.Backend.model.EventBookingDTO;
import com.museumApplication.Backend.model.ResponseDTO;
import com.museumApplication.Backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Controller
@RestController
@RequestMapping("/api")
public class HomeController {


    private UserService userService ;

    List<String> u = new ArrayList<>();

    @Autowired
    public HomeController(UserService userService)
    {
        this.userService = userService;
    }


    @GetMapping("/")
    public static String welcomePage()
    {
        return "Hello World...";
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllUsers()
    {

        try
        {
            ResponseEntity<List<Users>> response = null;
            List<Users> allUsers = userService.getAllUsers();
            return response.status(HttpStatus.OK).body(allUsers);
        }
        catch (Exception e)
        {
            System.out.println("Error Occurred , Error: "+e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/test")
    public ResponseEntity<ResponseDTO> testController(@RequestBody EventBookingDTO requestDTO) throws Exception {

        try
        {
            ResponseDTO response =  new ResponseDTO();
            return new ResponseEntity<>(response,HttpStatus.OK);
        }
        catch (Exception e)
        {
            System.out.println("Error Occurred , Error: "+e.toString());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
