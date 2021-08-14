package com.jox.radarengine.Controller;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class userSearchController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Hello";
    }

    @PostMapping("/userSearch")
    public void addUserData(){

    }

    @GetMapping("/userSearch/{id}")
    public void getUserData(){

    }

    @PutMapping("/userSearch")
    public void updateUserData(){

    }

    @DeleteMapping("/userSearch/{id}")
    public void deleteUserData(){

    }

}
