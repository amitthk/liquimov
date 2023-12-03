package com.amitthk.liquimov.model;

import lombok.Data;
import lombok.Getter;

@Data
public class ConnectionDetails {
    private String driverClassName;
    private String url;
    private String username;
    private String password;
}
