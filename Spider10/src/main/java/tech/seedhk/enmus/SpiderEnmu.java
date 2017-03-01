package tech.seedhk.enmus;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;

/**
 * Created by Seed on 2017/2/22.
 * Package #{PACKAGE_NAME}
 */
public enum SpiderEnmu {


    ZONGHENGXIAOSHUO(1,"http://book.zongheng.com/"),
    XIAOSHUOYUEDUWANG(2,"https://www.readnovel.com/");
    /*static {
        init();
    }*/
    private int id;
    private String url;

    SpiderEnmu(int id, String url) {
        this.id = id;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static SpiderEnmu getSpiderEnmuById(int id){
        switch(id){
            case 1:return ZONGHENGXIAOSHUO;
            case 2:return XIAOSHUOYUEDUWANG;
            default: throw new RuntimeException("id : "+id +" 是不被支持的网站");
        }
    }
    public static SpiderEnmu getSpiderEnmuByUrl(String url){

        for (SpiderEnmu sprderEnmu:values()) {
            if(url.contains(sprderEnmu.url)){
                return sprderEnmu;
            }
        }
        throw new RuntimeException("url : "+url +" 是不被支持的网站");
    }

    private static void init()  {

        SAXReader saxReader=new SAXReader();
        Document document= null;
        try {
            document = saxReader.read(SpiderEnmu.class.getClassLoader().getResourceAsStream("SpiderRule.xml"));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Element element=document.getRootElement();//这是根节点
        List<Element> elementList=element.elements("site");//这是获取根节点下的所有子节点
        for (Element e:elementList) {
            int id=1;
            List<Element> subList=e.elements();
            Element specialElement=e.element("special");//获取指定子节点
            String specialName=specialElement.getText();

            Element urlElement=e.element("url");//获取指定子节点
            String specialUrl=urlElement.getText();

            for (SpiderEnmu se:values()) {
                if(se.toString().equals(specialName)){
                    se.setId(id);
                    se.setUrl(specialUrl);
                }
                id++;
                System.out.println(se.getId());
                System.out.println(se.getUrl());
            }

           // for (Element subE:subList) {
                //String name=subE.getName();//获取子节点的名称
               // String text=subE.getText();//获取子节点的内容
               // Attribute attribute=subE.attribute("selector"); //可用于获取某个节点的属性  如ID
           // }
            //System.out.println(" = "+specialUrl);
        }

    }

    public static void main(String[] args) {


    }
}
