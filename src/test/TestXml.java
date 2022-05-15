package test;

import org.dom4j.Document;
import org.dom4j.Element;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * TODO
 *
 * @version 1.0
 * @author: nicholas
 * @createTime: 2022年05月15日 13:08
 */

public class TestXml {

    public static void main(String []args) throws IOException {
        testXml();
    }
    public static void testXml() throws IOException {
        String xml = readFile();
        Document document = XmlUtil.parserXml(xml);
        Element entity = XmlUtil.getChildElement(document.getRootElement(), XmlConstant.ENTITY);
        Element group = XmlUtil.getChildElement(entity, XmlConstant.GROUP);
        Element newGroup = group.createCopy();
        Map<String, String> groupMap = new HashMap<>();
        groupMap.put("Trstr_InsID", "123456");
        groupMap.put("Trstr_Nm", "56");
        XmlUtil.fillGroupText(newGroup, groupMap);
        entity.add(newGroup);
        System.out.println(document.asXML());

        //
    }

    public static String readFile() throws IOException {
        String path = "D:\\ideaProjects\\product\\myGithub\\xmlParser\\src\\resources\\1003.xml";

        InputStreamReader reader = new InputStreamReader(new FileInputStream(path));
        BufferedReader buffReader = new BufferedReader(reader);
        String strTmp = "";

        StringBuilder str = new StringBuilder();
        while((strTmp = buffReader.readLine())!=null){

            str.append(strTmp);

        }
        buffReader.close();
        return str.toString();
    }
}
