package com.team1.assessment;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SystemLog {

    public void info(String string){
        System.out.println("info: "+ string);
    }

    public void error(String string){
        System.out.println("error: "+ string);
    }
}
