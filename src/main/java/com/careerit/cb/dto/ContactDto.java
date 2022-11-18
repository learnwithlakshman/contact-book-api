package com.careerit.cb.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ContactDto {

    private UUID id;
    private String name;
    private String email;
    private String mobile;
    private String city;

}
