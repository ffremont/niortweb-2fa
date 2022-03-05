package com.github.ffremont.mfa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private final String login;
    private final String hashPass;
    private final String name;
    private final String secretTotp;
}
