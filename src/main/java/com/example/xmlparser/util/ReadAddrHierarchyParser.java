package com.example.xmlparser.util;

import com.example.xmlparser.dto.AddressDto;
import com.example.xmlparser.dto.HierarchyDto;
import lombok.SneakyThrows;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ReadAddrHierarchyParser {

    private static final String FILENAME = "AS_ADM_HIERARCHY.xml";

    @SneakyThrows
    public static List parseAddr() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


        dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

        DocumentBuilder db = dbf.newDocumentBuilder();

        Document doc = db.parse(new File(FILENAME));

        doc.getDocumentElement().normalize();

        NodeList list = doc.getElementsByTagName("ITEM");
        List addressList = new ArrayList<>();

        for (int temp = 0; temp < list.getLength(); temp++) {

            Node node = list.item(temp);

            if (node.getNodeType() == Node.ELEMENT_NODE) {

                Element element = (Element) node;

                HierarchyDto item = new HierarchyDto();
                item.setId(element.getAttribute("ID"));
                item.setObjectId(element.getAttribute("OBJECTID"));
                item.setChangeId(element.getAttribute("CHANGEID"));
                item.setPrevId(element.getAttribute("PREVID"));
                item.setNextId(element.getAttribute("NEXTID"));
                item.setUpdateDate(element.getAttribute("UPDATEDATE"));
                item.setStartDate(element.getAttribute("STARTDATE"));
                item.setEndDate(element.getAttribute("ENDDATE"));
                item.setParentObjId(element.getAttribute("PARENTOBJID"));
                item.setIsActive(element.getAttribute("ISACTIVE"));

                addressList.add(item);
            }

        }
        return addressList;
    }
}