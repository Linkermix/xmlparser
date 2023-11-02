package com.example.xmlparser.dto;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;

@Data
public class HierarchyDto {
    private String id;
    private String objectId;
    private String parentObjId;
    private String changeId;
    private String prevId;
    private String nextId;
    private String updateDate;
    private String startDate;
    private String endDate;
    private String isActive;
}
