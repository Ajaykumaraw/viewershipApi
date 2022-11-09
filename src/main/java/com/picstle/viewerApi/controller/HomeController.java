package com.picstle.viewerApi.controller;


import com.picstle.viewerApi.UserChData.UserChData;
import com.picstle.viewerApi.UserChData.UserChServiceImp;
import com.picstle.viewerApi.UserRepo.AppuserRepo;
import com.picstle.viewerApi.Utility.JWTUtility;
import com.picstle.viewerApi.YoutubeApiRequests.YoutubeApiRequest;
import com.picstle.viewerApi.model.*;
import com.picstle.viewerApi.service.UserServiceImp;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController()
@CrossOrigin("http://localhost:4200")
public class HomeController {

    @Autowired
    private UserServiceImp userServiceImp;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AppuserRepo appuserRepo;

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private UserChServiceImp userChServiceImp;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private YoutubeApiRequest youtubeApiRequest;

    @GetMapping(path = "/home/{reuestdata}")
    public String home(@PathVariable("reuestdata") String reuestdata) {

        Reuestdata reuestdata1 = new Reuestdata();
        reuestdata1.setUserName(reuestdata);
        appuserRepo.findByuserName(reuestdata1.getUserName());
        return "this is home page " + appuserRepo.findByuserName(reuestdata1.getUserName());
    }

    @GetMapping(path = "/admin")
    public String admin(){
        return "this is admin page";
    }

    @PostMapping(path = "/register")
    public AppUser register(@RequestBody AppUser appUser){
        System.out.println("in websecurity"+appUser);
      //  appUser.setPassword(this.passwordEncoder.encode(appUser.getPassword()));
        return userServiceImp.registerUser(appUser);
    }

    @PostMapping(path = "/auth")
    public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception{

        System.out.println("in authenticate"+jwtRequest);
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(),
                            jwtRequest.getPassword()
                    )
            );
        }catch (BadCredentialsException exception){
            throw new Exception("INVALID CREDENTIAL");
        }

        final UserDetails userDetails = customUserDetailService.loadUserByUsername(jwtRequest.getUserName());
        final String token = jwtUtility.generateToken(userDetails);

        return new JwtResponse(token, jwtRequest.getUserName());
    }
	
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping(path = "/user/postUserChDetails")
    public ResponseEntity getUserChData(@RequestBody UserChData userChData) throws IOException {
        System.out.println("in user ch data "+userChData);
        userChServiceImp.saveUserChData(userChData);
        String result =  youtubeApiRequest.getChData(userChData.getChannelId());
        return buildResponseEntitySuccess(result);

    }
        private ResponseEntity buildResponseEntitySuccess(String result){
            HttpHeaders responseHeader = new HttpHeaders();
            responseHeader.set("Access-Control-Allow-Origin","http://localhost:4200");
            return new ResponseEntity(result,responseHeader, HttpStatus.OK);
        }
}
