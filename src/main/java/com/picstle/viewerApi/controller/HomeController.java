package com.picstle.viewerApi.controller;


import com.picstle.viewerApi.UserRepo.AppuserRepo;
import com.picstle.viewerApi.Utility.JWTUtility;
import com.picstle.viewerApi.model.*;
import com.picstle.viewerApi.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    private AuthenticationManager authenticationManager;

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

        return new JwtResponse(token);
    }



}
