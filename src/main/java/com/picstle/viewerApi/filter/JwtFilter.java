package com.picstle.viewerApi.filter;

import com.picstle.viewerApi.Utility.JWTUtility;
import com.picstle.viewerApi.model.CustomUserDetailService;
import com.picstle.viewerApi.model.CustomUserDetails;
import com.picstle.viewerApi.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
                String authorization = request.getHeader("Authenticate");
                System.out.println("in filter"+authorization);
                String token = null;
                String userName = null;

                if(null != authorization && authorization.startsWith("Bearer")){
                    token = authorization.substring(7);
                    userName = jwtUtility.getUsernameFromToken(token);
                }

                if(null != userName && SecurityContextHolder.getContext().getAuthentication() == null){
                    UserDetails userDetails = customUserDetailService.loadUserByUsername(userName);

                    if(jwtUtility.validateToken(token,userDetails)){
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken
                                = new UsernamePasswordAuthenticationToken(userDetails,
                                null,userDetails.getAuthorities());

                        usernamePasswordAuthenticationToken.setDetails(
                                new WebAuthenticationDetailsSource().buildDetails(request)
                        );

                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                    filterChain.doFilter(request,response);
                }
    }


    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String url = request.getRequestURL().toString();
        return isResourceUrl(url);
    }

    private boolean isResourceUrl(String url) {
        boolean isResourceUrl = false;
        List<String> resourceRequests = Arrays.asList("/register","/auth");
        for (String resourceRequest : resourceRequests) {
            if (url.contains(resourceRequest)) {
                isResourceUrl = true;
            }
        }
        return isResourceUrl;
    }
}

















