package com.team1.assessment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String username;
    private UserPlan plan;

    public enum UserPlan{
        FREE,
        PREMIUM
    }
}