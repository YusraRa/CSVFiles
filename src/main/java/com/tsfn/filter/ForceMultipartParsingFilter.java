//package com.tsfn.filter;
//
//import org.springframework.core.Ordered;
//import org.springframework.web.filter.OncePerRequestFilter;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//public class ForceMultipartParsingFilter extends OncePerRequestFilter {
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
//        // Force parsing of multipart content by accessing request parts
//        request.getParts();
//        // Continue with the filter chain
//        filterChain.doFilter(request, response);
//    }
//}