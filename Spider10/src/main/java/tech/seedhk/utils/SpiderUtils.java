package tech.seedhk.utils;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.XMLReader;
import tech.seedhk.enmus.SpiderEnmu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Seed on 2017/2/24.
 */
public class SpiderUtils {

    private static final Map<SpiderEnmu,Map<String,String>> spiderMap=new HashMap<SpiderEnmu, Map<String, String>>();
    private SpiderUtils(){};
    static {
        init();
    }

    private static void init(){
        SAXReader saxReader=new SAXReader();
        try {
            Document doc= saxReader.read(SpiderUtils.class.getClassLoader().getResourceAsStream("SpiderRule.xml"));
            Element element= doc.getRootElement();
            List<Element> sites=element.elements("site");
            for (Element e : sites) {
                Map<String,String> map=new HashMap<String, String>();
                List<Element> subList=e.elements();
                for (Element subE:subList) {
                    String name=subE.getName();
                    String text=subE.getTextTrim();
                    map.put(name,text);
                }
                spiderMap.put(SpiderEnmu.getSpiderEnmuByUrl(map.get("url")),map);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }


    public static Map<String,String> getContext(SpiderEnmu spiderEnmu){
        return spiderMap.get(spiderEnmu);
    }
}

