package com.feng.controller;

import com.feng.service.AvImageInfo;
import com.feng.util.PageAnalysis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/jsoup")
public class JsoupController {

    @Autowired
    private PageAnalysis pageAnalysis;


    @GetMapping
    public void getImages(@RequestParam(required = false) String date,
                          @RequestParam(required = false) String imageDetailsUrl,
                          @RequestParam(required = false) String listPageUrl,
                          @RequestParam(required = false, defaultValue = "false") Boolean downLoad) {

        Collection<AvImageInfo> avImageInfos = pageAnalysis.analysisImagePage(imageDetailsUrl);

        avImageInfos.forEach(avImageInfo -> log.info(avImageInfo.toString()));

        if(downLoad){
            pageAnalysis.downloadImage(avImageInfos);
        }

    }


}
