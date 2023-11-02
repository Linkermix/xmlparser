package com.example.xmlparser.controller;

import com.example.xmlparser.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("address")
@Tag(name = "Address controller")
public class AddressController {

    @Autowired
    AddressService addressService;

    @Operation(summary = "Получить адреса")
    @GetMapping("list")
    public ResponseEntity list(@Parameter(example = "2010-01-01") @RequestParam(name = "date", required = true) String date,
                               @Parameter(example = "1422396, 1450759, 1449192, 1451562") @RequestParam(name = "objectid", required = true) String objectid) {
        return ResponseEntity.ok(addressService.list(date, objectid));
    }

    @GetMapping("hierarchy")
    public ResponseEntity hierarchy(@Parameter(example = "проезд") @RequestParam(name = "typetid", required = true) String typeid) {
        return ResponseEntity.ok(addressService.getByType(typeid));
    }

}
