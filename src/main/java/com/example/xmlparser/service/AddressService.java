package com.example.xmlparser.service;

import com.example.xmlparser.dto.AddressDto;
import com.example.xmlparser.dto.HierarchyDto;
import com.example.xmlparser.util.ReadAddrHierarchyParser;
import com.example.xmlparser.util.ReadAddrObjParser;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @SneakyThrows
    public StringBuilder list(String date, String ids) {

        List idsList = List.of(ids.trim().split("\\s*,\\s*"));
        Integer parsedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date).getMinutes();
        List<AddressDto> fullAddressList = ReadAddrObjParser.parseAddr();

        List<AddressDto> filteredAddressList = fullAddressList.stream().filter(it ->
                it.getStartDate().getMinutes() <= parsedDate &&
                        it.getEndDate().getMinutes() >= parsedDate &&
//                        it.getIsActive().equals("1") &&
                        it.getIsActual().equals("1") &&
                        idsList.contains(it.getObjectId())).collect(Collectors.toList());
        StringBuilder res = new StringBuilder("");
        filteredAddressList.stream().forEach(it -> res.append(it.getObjectId() + " " + it.getTypeName() + " " + it.getName() + "\n"));
        return res;
    }

    @SneakyThrows
    public StringBuilder getByType(String type) {

        List<AddressDto> fullAddressList = ReadAddrObjParser.parseAddr();
        List<HierarchyDto> fullHierarchyList = ReadAddrHierarchyParser.parseAddr();

        List<AddressDto> filteredAddressList = fullAddressList.stream().filter(it ->
                it.getIsActual().equals("1") &&
                        it.getTypeName().equals(type)).collect(Collectors.toList());

        StringBuilder res = new StringBuilder("");
        filteredAddressList.stream().forEach(it -> res.append(makeHierarchy(it, fullAddressList, fullHierarchyList) + "\n"));
        return res;
    }

    @SneakyThrows
    String makeHierarchy(AddressDto adr, List<AddressDto> adrList, List<HierarchyDto> hierList) {
        String res = adr.getTypeName() + " " + adr.getName();
        HierarchyDto hier = hierList.stream().filter(it -> it.getObjectId().equals(adr.getObjectId())).findAny().orElse(null);
        while (!hier.getParentObjId().equals("0")) {
            HierarchyDto hierInLoop = hier;
            AddressDto adrInLoop = adrList.stream().filter(it -> it.getObjectId().equals(hierInLoop.getParentObjId())).findAny().orElse(null);
            res = adrInLoop.getTypeName() + " " + adrInLoop.getName() + ", " + res;
            hier = hierList.stream().filter(it -> it.getObjectId().equals(adrInLoop.getObjectId()) && it.getIsActive().equals("1")).findAny().orElse(null);
        }
        return res;
    }

}

