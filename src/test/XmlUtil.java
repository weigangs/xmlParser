package test;

import com.alibaba.fastjson.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import utils.StringUtils;

import java.io.StringReader;
import java.util.List;
import java.util.Map;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月15日 12:44
 */
public class XmlUtil {

    public static Document parserXml(String xml) {
        if(StringUtils.isEmpty(xml))
            return null;
        SAXReader reader = new SAXReader();
        StringReader sr = new StringReader(xml);
        try {
            Document document = reader.read(sr);
            return document;
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static Element getChildElement(Element root, String attName) {
        Element element = null;
        if(null != root) {
            element = root.element(attName);
            if(null != element){
                return element;
            }else{
                List<Element> elements = root.elements();
                for(Element el : elements) {
                    element = getChildElement(el, attName);
                    if (null != element)
                        return element;
                }
            }
        }
        return null;
    }

    public static void fillGroupText(Element group, Map<String, String> map) {
        if(null == group)
            return;
        List<Element> elements = group.elements();
        String text;
        for(Element el : elements) {
            String type = el.attributeValue(XmlConstant.TYPE);
            if(XmlConstant.TYPE_SPEL.equals(type)){
                text = map.get(el.getName());
                if(StringUtils.isNotEmpty(text))
                    el.setText(text);
            }
        }
    }


    public static JSONObject document2Json(Element root) {
        if(null == root)
            return null;
        JSONObject request = new JSONObject();
        request.put(root.getName(), request);
        format(request, root);
        return request;
    }


    public static void format(Object json, Element element){
        if(json instanceof JSONObject) {
            List<Element> list = element.elements();
            JSONObject request = (JSONObject) json;
            for (Element e : list) {
                String type = e.attributeValue(XmlConstant.TYPE);
                if (XmlConstant.TYPE_OBJ.equals(type)) {
                    JSONObject obj = new JSONObject();
                    request.put(e.getName(), obj);
                    format(obj, e);
                }else {
                    request.put(e.getName(), e.getText());
                }
            }
        }
    }

}
