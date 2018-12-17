package com.feng.util;

import com.feng.service.AvImageInfo;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.Validate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class PageAnalysis {

    private static final String baseUrl = "http://889seba.com";


    public String analysisHomePage() {
        final String[] href = new String[1];
        try {
            Document parse = Jsoup.parse(new URL(baseUrl), 5000);

            Elements as = parse.getElementsByTag("a");
            as.forEach(a -> {
                if ("亚洲色图".equals(a.html())) {
                    href[0] = a.attr("href");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }

        Validate.isTrue(StringUtils.isNotBlank(href[0]), "读取不到数据");

        return baseUrl + href[0];
    }


    public Collection<String> analysisImageList(String listPageUrl) {
        List<String> pageDetailsUrlList = new ArrayList<>();
        try {
            Document parse = Jsoup.parse(new URL(listPageUrl), 5000);
            Elements elements = parse.getElementsByClass("list");

            elements.forEach(element -> {
                Elements atags = element.getElementsByTag("a");
                atags.forEach(atag -> {
                    String title = atag.attr("title");
                    String href = atag.attr("href");
                    String date = atag.getElementsByTag("span").html();
                    pageDetailsUrlList.add(baseUrl + href);
                });
            });
            Thread.sleep(1000000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageDetailsUrlList;
    }



    public Collection<AvImageInfo> analysisImagePage(String imageDetailsUrl) {
            List<AvImageInfo> avImages = new ArrayList<>();
        try {
            Document parse = Jsoup.parse(new URL(imageDetailsUrl), 5000);

            Elements content = parse.getElementsByClass("content");
            String title = parse.getElementsByClass("title").html();

            Iterator<Element> iterator = content.iterator();

            iterator.forEachRemaining(s -> {
                Elements imgs = s.getElementsByTag("img");
                imgs.forEach(img -> {
                    String imageUrl = img.attr("src");
                    String[] split = imageUrl.split("/");
                    String imageName = split[split.length - 1];
                    avImages.add(AvImageInfo.builder().imageName(imageName).imageUrl(imageUrl).tittle(title).build());
                });
            });

        } catch (Exception e) {
            e.printStackTrace();
        }

        return avImages;
    }


    public void downloadImage(Collection<AvImageInfo> images){
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        images.stream().forEach(image -> executorService.execute(new DownLoadImages(image.getTittle(), image.getImageUrl(), image.getImageName())));
        executorService.shutdown();
    }

}
