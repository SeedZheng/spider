package tech.seedhk.junit;

import org.junit.Test;
import tech.seedhk.enmus.SpiderEnmu;
import tech.seedhk.entity.Chapter;
import tech.seedhk.impl.DefaultChapterSpider;
import tech.seedhk.interfaces.ChapterSpider;
import tech.seedhk.utils.SpiderUtils;

import java.util.List;

/**
 * Created by hasee on 2017/2/20.
 */
public class TestDemo1 {

    @Test
    public void test(){
        ChapterSpider chapterSpider=new DefaultChapterSpider();
        List<Chapter> chapterList= chapterSpider.getChapterList("http://book.zongheng.com/showchapter/646420.html");
        for (Chapter c:chapterList ) {
            System.out.println(c);
        }
    }


    @Test
    public  void  testContext(){
        System.out.println(SpiderUtils.getContext(SpiderEnmu.getSpiderEnmuByUrl("http://book.zongheng.com/showchapter/628896.html")));
    }

}
