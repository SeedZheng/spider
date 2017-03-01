package tech.seedhk.interfaces;

import tech.seedhk.entity.Chapter;

import java.util.List;

/**
 * Created by hasee on 2017/2/20.
 */
public interface ChapterSpider {

    public List<Chapter> getChapterList(String url);
}
