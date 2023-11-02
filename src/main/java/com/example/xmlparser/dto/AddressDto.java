package com.example.xmlparser.dto;

import lombok.Data;

import java.util.Date;

@Data
public class AddressDto {
    private String id;
    private String objectId;
    private String changeId;
    private String name;
    private String typeName;
    private String level;
    private String operTypeId;
    private String prevId;
    private String nextId;
    private Date updateDate;
    private Date startDate;
    private Date endDate;
    private String isActual;
    private String isActive;
}
