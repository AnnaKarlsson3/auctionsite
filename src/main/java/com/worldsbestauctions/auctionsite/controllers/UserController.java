package com.worldsbestauctions.auctionsite.controllers;

import com.worldsbestauctions.auctionsite.entities.Users;
import com.worldsbestauctions.auctionsite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/whoami")
    Users whoAmI(HttpServletRequest request){
        Users u = null;
        try{
            u = userService.getUserByEmail(request.getUserPrincipal().getName());
        }catch (Exception e){}
        return u;
    }
}
