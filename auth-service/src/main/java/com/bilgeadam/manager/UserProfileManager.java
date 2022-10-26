package com.bilgeadam.manager;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@FeignClient(name="user-profile-service",url = "http://localhost:9092/user",decode404 = true)
public interface UserProfileManager {

    @PostMapping("/save")
    public ResponseEntity<Boolean> save(@RequestBody UserProfileSaveRequestDto dto);
}
