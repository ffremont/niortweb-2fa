package com.github.ffremont.mfa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Signin {
    private final String password;
    private final String code;
}
