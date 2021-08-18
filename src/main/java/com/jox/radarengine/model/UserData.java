package com.jox.radarengine.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserData {

    private String name;
    private String npwp;
    private String address;
    private String siup;
    private String id;
    private LinkageData linkageData;

}
