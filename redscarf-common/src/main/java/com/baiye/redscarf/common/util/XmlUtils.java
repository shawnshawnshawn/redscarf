package com.baiye.redscarf.common.util;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * XML工具类.
 *
 *
 * @author 白也
 * @date 2021/1/19 5:25 下午
 */
public class XmlUtils {

    /**
     * 将XML字符串转为Map.
     *
     * @param xml
     * @return
     */
    public static Map<String, String> parse2Map(String xml) {
        if (StringUtils.isBlank(xml)) {
            return new HashMap<>();
        }

        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(IOUtils.toInputStream(xml, StandardCharsets.UTF_8));
            NodeList allNodes = document.getFirstChild().getChildNodes();

            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < allNodes.getLength(); i++) {
                Node node = allNodes.item(i);
                if (node instanceof Element) {
                    map.put(node.getNodeName(), node.getTextContent());
                }
            }

            return map;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
