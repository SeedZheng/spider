package tech.seedhk.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import tech.seedhk.enmus.SpiderEnmu;
import tech.seedhk.entity.Chapter;
import tech.seedhk.interfaces.ChapterSpider;
import tech.seedhk.utils.SpiderUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hasee on 2017/2/20.
 */
public class AbstractChapterSpider implements ChapterSpider {
    protected String get(String url)throws Exception{
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        HttpGet httpGet=new HttpGet(url);
        //String charsrt="UTF-8";
        CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
        String result= EntityUtils.toString(httpResponse.getEntity(), SpiderUtils.getContext(SpiderEnmu.getSpiderEnmuByUrl(url)).get("charset"));
        return result;
    }


    public List<Chapter> getChapterList(String url) {
        List<Chapter> chapterList=new ArrayList<Chapter>();
        System.out.println();
        try {
            String result=get(url);
            //System.out.println(result);
            Document doc= Jsoup.parse(result);
            //Elements elements=doc.select("table").select("tr").select("td").select("a");
            Elements elements=doc.select(SpiderUtils.getContext(SpiderEnmu.getSpiderEnmuByUrl(url)).get("selector"));//"ul li a"
            //System.out.println(elements);
            for (Element e:elements ) {
                Chapter chapter=new Chapter();
                chapter.setTitle(e.text());
                chapter.setUrl(e.attr("href"));
                if(chapter.getTitle().contains("章") ||chapter.getTitle().contains("节")){
                    chapterList.add(chapter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapterList;
    }
}
