package com.example.xmlparser.util;

import com.example.xmlparser.dto.AddressDto;
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
import java.util.ArrayList;
import java.util.List;

public class ReadAddrObjParser {

    private static final String FILENAME = "AS_ADDR_OBJ.xml";

    @SneakyThrows
    public static List parseAddr() {

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();


            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(FILENAME));

            doc.getDocumentElement().normalize();

            NodeList list = doc.getElementsByTagName("OBJECT");
            List addressList = new ArrayList<>();

            for (int temp = 0; temp < list.getLength(); temp++) {

                Node node = list.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    AddressDto item = new AddressDto();

                    item.setId(element.getAttribute("ID"));
                    item.setObjectId(element.getAttribute("OBJECTID"));
                    item.setChangeId(element.getAttribute("CHANGEID"));
                    item.setName(element.getAttribute("NAME"));
                    item.setTypeName(element.getAttribute("TYPENAME"));
                    item.setLevel(element.getAttribute("LEVEL"));
                    item.setOperTypeId(element.getAttribute("OPERTYPEID"));
                    item.setPrevId(element.getAttribute("PREVID"));
                    item.setNextId(element.getAttribute("NEXTID"));
                    item.setUpdateDate(new SimpleDateFormat("yyyy-MM-dd").parse(element.getAttribute("UPDATEDATE")));;
                    item.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(element.getAttribute("STARTDATE")));
                    item.setEndDate(new SimpleDateFormat("yyyy-MM-dd").parse(element.getAttribute("ENDDATE")));
                    item.setIsActual(element.getAttribute("ISACTUAL"));
                    item.setIsActive(element.getAttribute("ISACTIVE"));

                    addressList.add(item);
                }
            }
            return addressList;
    }
}