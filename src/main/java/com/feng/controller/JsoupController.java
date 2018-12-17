package com.feng.controller;

import com.feng.service.AvImageInfo;
import com.feng.util.PageAnalysis;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/jsoup")
@RequiredArgsConstructor
public class JsoupController {

    private final PageAnalysis pageAnalysis;

    @GetMapping
    public void getImages(@RequestParam(required = false) String date,
                          @RequestParam(required = false) String imageDetailsUrl,
                          @RequestParam(required = false) String listPageUrl,
                          @RequestParam(required = false, defaultValue = "false") Boolean downLoad) {

        if (StringUtils.isNotBlank(listPageUrl)) {
            Collection<String> pageDetailsUrlList = pageAnalysis.analysisImageList(listPageUrl);
            pageDetailsUrlList.forEach(pageDetailsUrl -> {
                Collection<AvImageInfo> avImageInfos = pageAnalysis.analysisImagePage(pageDetailsUrl);
                if (downLoad) {
                    pageAnalysis.downloadImage(avImageInfos);
                }
            });
        }

        if (StringUtils.isNotBlank(imageDetailsUrl)) {
            Collection<AvImageInfo> avImageInfos = pageAnalysis.analysisImagePage(imageDetailsUrl);
            avImageInfos.forEach(avImageInfo -> log.info(avImageInfo.toString()));
            if (downLoad) {
                pageAnalysis.downloadImage(avImageInfos);
            }
        }

    }

}
