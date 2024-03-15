package com.dedagroup.springboottraining.config;

import java.util.Arrays;

public interface Roles {

    String ROLE_CREATE = "CREATE"; //users in group creator, admin
    String ROLE_READ = "READ"; //users in all groups
    String ROLE_UPDATE = "UPDATE"; //users in groups auditor, creator, admin
    String ROLE_DELETE = "DELETE"; //users in groups auditor, admin
    String ROLE_ADMIN = "ADMIN"; //users in group admin


    static String concat(String... roles) {
        return String.join(",", Arrays.toString(roles));
    }




}
