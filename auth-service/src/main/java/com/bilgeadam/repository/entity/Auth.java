package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.Activated;
import com.bilgeadam.repository.enums.Roles;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "tblauth")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Auth {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String username;
    String password;
    String isim;
    String soyisim;
    String email;
    @Enumerated(EnumType.STRING)
    Activated activated;
    @Enumerated(EnumType.STRING)
    Roles roles;
}
