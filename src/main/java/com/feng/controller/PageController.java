package com.feng.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.feng.service.Day;
import com.feng.service.OceanFreightView;
import com.feng.service.View;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;

/**
 * 此controller用作页面间的调转
 */
@Controller
public class PageController {

    @RequestMapping("/")
    public ModelAndView toIndex() {
        return new ModelAndView("index");
    }

    @RequestMapping("/order/order")
    public ModelAndView toOrder() {
        return new ModelAndView("order/order");
    }

    @RequestMapping("/user/hello")
    public ModelAndView toHello() {
        return new ModelAndView("user/hello");
    }

    @RequestMapping("/chat/chat")
    public ModelAndView toChat() {
        return new ModelAndView("chat/chat");
    }

    @GetMapping("/test1")
    @ResponseBody
    @JsonView(View.Client.class)
    public Page<OceanFreightView> getDate1(@RequestParam(required = false) Day day, Pageable pageable) {
        List<OceanFreightView> oceanFreightViews = Arrays.asList(new OceanFreightView("USD", "REMARKS", Arrays.asList(
                new OceanFreightView.Price(0.3, 0.4, 0.6, 0.9),
                new OceanFreightView.Price(1.3, 1.4, 1.6, 1.9)))
        );

        return new PageImpl<>(oceanFreightViews, pageable, 12);
    }

    @GetMapping("/test2")
    @ResponseBody
    @JsonView(View.Client.class)
    public OceanFreightView getDate2() {
        return new OceanFreightView("USD", "REMARKS", Arrays.asList(
                new OceanFreightView.Price(0.3, 0.4, 0.6, 0.9),
                new OceanFreightView.Price(1.3, 1.4, 1.6, 1.9)));
    }
}
