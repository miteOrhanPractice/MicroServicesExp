package com.bilgeadam.controller;

import com.bilgeadam.dto.request.RegisterRequestDto;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@RequestBody @Valid RegisterRequestDto dto){
        if(authService.save(dto)){
            return ResponseEntity.ok().build();}
        return ResponseEntity.badRequest().build();
    }
}
