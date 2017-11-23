package com.dianping.dzopen.utils;

import com.dianping.dzopen.valuebuilder.ParamValueBuilder;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpParamLoader {

    private static final XPath XPATH = XPathFactory.newInstance().newXPath();

    public static Map<String, String> loadRequest(String paramPath) {
        ParamXmlModel paramXmlModel = parseXml(paramPath);
        Map<String, String> request = new HashMap<>();
        for (FieldXmlModel field : paramXmlModel.fields) {
            if (StringUtils.isNotEmpty(field.getValueBuilder())) {
                try {
                    ParamValueBuilder builder = (ParamValueBuilder) Class.forName(field.valueBuilder).newInstance();
                    field.setValue(String.valueOf(builder.build()));
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
            if (field.nullable) {
                field.setValue("");
            }
            request.put(field.name, field.value);
        }
        return request;
    }

    private static ParamXmlModel parseXml(String xmlPath) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(false);
            factory.setIgnoringElementContentWhitespace(false);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document =
                    builder.parse(HttpParamLoader.class.getClassLoader().getResourceAsStream(xmlPath));
            Element paramRoot = document.getDocumentElement();

            ParamXmlModel paramModel = new ParamXmlModel();
            paramModel.setName(paramRoot.getAttribute("name"));
            parseParamFields(paramModel, paramRoot);
            return paramModel;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            //
            throw new RuntimeException(e);
        }
    }

    private static void parseParamFields(ParamXmlModel paramModel, Element paramRoot) {
        try {
            NodeList fieldList = (NodeList) XPATH.evaluate("field", paramRoot, XPathConstants.NODESET);
            if (null == fieldList || fieldList.getLength() == 0) {
                throw new RuntimeException("No fields defined in " + paramModel.getName());
            }
            int fieldLength = fieldList.getLength();
            for (int i = 0; i < fieldLength; i++) {
                Node node = fieldList.item(i);
                NamedNodeMap nodeMap = node.getAttributes();
                FieldXmlModel field = new FieldXmlModel();
                field.setName(null == nodeMap.getNamedItem("name") ? null : nodeMap.getNamedItem("name").getNodeValue());
                field.setValue(null == nodeMap.getNamedItem("value") ? null : nodeMap.getNamedItem("value").getNodeValue());
                field.setValueBuilder(null == nodeMap.getNamedItem("valuebuilder") ? null : nodeMap.getNamedItem("valuebuilder").getNodeValue());
                field.setNullable(null != nodeMap.getNamedItem("valuebuilder") &&
                        StringUtils.isNotEmpty(nodeMap.getNamedItem("valuebuilder").getNodeValue())
                        && "true".equalsIgnoreCase(nodeMap.getNamedItem("valuebuilder").getNodeValue()));
                paramModel.addField(field);
            }
        } catch (XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }


    private static class ParamXmlModel {
        private String name;
        private List<FieldXmlModel> fields = new ArrayList<>();

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addField(FieldXmlModel field) {
            this.fields.add(field);
        }
    }

    private static class FieldXmlModel {
        private String name;
        private String value;
        private String valueBuilder;
        private boolean nullable = false;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValueBuilder() {
            return valueBuilder;
        }

        public void setValueBuilder(String valueBuilder) {
            this.valueBuilder = valueBuilder;
        }

        public boolean isNullable() {
            return nullable;
        }

        public void setNullable(boolean nullable) {
            this.nullable = nullable;
        }
    }
}
