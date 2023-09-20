package com.ms.tourist_app.application.output.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDataOutput {

    private Long id;
    private String name;
    private String email;
    private String password;

}

