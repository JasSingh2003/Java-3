package com.example.assignment3_jaskeeratsingh.beans;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
public class User {

    Long userId;
    String email;
    String username;
    String password;
    @NonNull
    private String encryptedPassword;

}
