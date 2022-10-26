package com.bilgeadam.service;

import com.bilgeadam.dto.request.UserProfileSaveRequestDto;
import com.bilgeadam.repository.IUserProfileRepository;
import com.bilgeadam.repository.entity.UserProfile;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService extends ServiceManager<UserProfile,Long> {
    private final IUserProfileRepository iUserProfileRepository;

    public UserProfileService(IUserProfileRepository iUserProfileRepository) {
        super(iUserProfileRepository);
        this.iUserProfileRepository = iUserProfileRepository;
    }

    public Boolean save(UserProfileSaveRequestDto dto){
        save(UserProfile.builder().authid(dto.getAuthid()).username(dto.getUsername()).email(dto.getEmail()).build());
        return true;
    }
}
