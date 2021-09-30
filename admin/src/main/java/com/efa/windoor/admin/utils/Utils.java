package com.efa.windoor.admin.utils;


import com.efa.windoor.admin.config.BackendUserDetails;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

public class Utils {
    public static boolean empty( final String s ) {
        // Null-safe, short-circuit evaluation.
        return s == null || s.trim().isEmpty();
    }

    public static String getRootPath() {
        return ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
    }

    public static BackendUserDetails getUserDetails() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (token.getPrincipal() instanceof BackendUserDetails) {
                return (BackendUserDetails) token.getPrincipal();
            }
        }
        return null;
    }

    public static String getUsername(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static Long getUserId() {
        if (SecurityContextHolder.getContext().getAuthentication() instanceof UsernamePasswordAuthenticationToken) {
            UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
            if (token.getPrincipal() instanceof BackendUserDetails) {
                BackendUserDetails userDetails = (BackendUserDetails) token.getPrincipal();
                return userDetails.getUserId();
            }
        }
        return null;
    }
}
