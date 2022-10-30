package com.bilgeadam.service;

import com.bilgeadam.dto.request.LoginRequestDto;
import com.bilgeadam.exception.AuthServiceException;
import com.bilgeadam.exception.ErrorType;
import com.bilgeadam.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.dto.request.UserProfileSaveRequestDto;

import com.bilgeadam.manager.UserProfileManager;
import com.bilgeadam.repository.IAuthRepository;
import com.bilgeadam.repository.entity.Auth;
import com.bilgeadam.repository.enums.Roles;

import com.bilgeadam.utility.ServiceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Optional;

@Service
public class AuthService extends ServiceManager<Auth,Long> {
    private final IAuthRepository repository;
    private final JwtTokenManager tokenManager;

    private final UserProfileManager userProfileManager;


    public  AuthService(IAuthRepository repository,UserProfileManager userProfileManager,JwtTokenManager tokenManager){
        super(repository);
        this.repository=repository;
        this.userProfileManager=userProfileManager;
        this.tokenManager=tokenManager;

    }

    public Boolean save(RegisterRequestDto dto){
        Auth auth = Auth.builder().username(dto.getUsername()).password(dto.getPassword()).email(dto.getEmail()).roles(Roles.ROLE_USER).build();


        save(auth);


        if(auth.getId()!=null){
            userProfileManager.save(UserProfileSaveRequestDto.builder()
                    .authid(auth.getId())
                    .email(dto.getEmail())
                    .username(dto.getUsername()).build());
        }
        return false;
    }

    public String doLogin(LoginRequestDto dto){
        Optional<Auth> auth = repository.findOptionalByUsernameAndPassword(
                dto.getUsername(),dto.getPassword());
        if(auth.isEmpty()) throw new AuthServiceException(ErrorType.LOGIN_ERROR_001);
        return tokenManager.createToken(auth.get().getId());
    }



}
