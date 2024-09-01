package com.jrm.perfimeEcommerce.infra.security;

import com.jrm.perfimeEcommerce.infra.exceptions.UserDoesntExists;
import com.jrm.perfimeEcommerce.models.Client;
import com.jrm.perfimeEcommerce.repository.ClientRepository;
import com.jrm.perfimeEcommerce.services.TokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
public class SecurityFilter extends OncePerRequestFilter {
    @Autowired
    private TokenService tokenService;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var JwtToken = recoverToken(request);
        if(JwtToken!=null){
            var subject = tokenService.getSubject(JwtToken);
            Optional<Client> client = clientRepository.findByEmail(subject);
            if(client.isPresent()) {
                var authentication = new UsernamePasswordAuthenticationToken(client.get(), null, client.get().getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }else{
                throw new UserDoesntExists("User does not exists");
            }
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request){
        var authorizationHeader = request.getHeader("Authorization");

        if(authorizationHeader!=null){
            return authorizationHeader.replace("Bearer ", "");
        }

        return null;
    }
}
