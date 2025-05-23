package com.khs.shop.thymeleaf.controller;

import com.khs.shop.dto.ItemDto;
import com.khs.shop.entity.Item;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@Log4j2
@RequestMapping(value = "/thymeleaf")
public class ThymeleafController {

    @GetMapping(value = "/ex1")
    public String ex1(Model model){
        Point p=new Point(10,20);
        model.addAttribute("data",p);
        return "thymeleaf/ex1";
    }

    @GetMapping(value = "/ex6")
    public String ex6(Model model){

        return "thymeleaf/ex6";
    }

    @GetMapping(value = "/ex7")
    public String ex7(Model model){

        return "thymeleaf/ex7";
    }


    @GetMapping(value = "/ex5")
    public String ex5(@RequestParam("param1") String p1, String param2,Model model){
        log.info("===================>"+p1+","+param2);
        model.addAttribute("param1",p1);
        model.addAttribute("param2",param2);


        return "thymeleaf/ex5";
    }

    @GetMapping(value = "/ex2")
    public String ex2(Model model){
        ItemDto itemDto=new ItemDto();
        itemDto.setItemDetail("상품 상세 설명");
        itemDto.setItemNm("테스트 상품 1");
        itemDto.setPrice(10000);
        itemDto.setRegTime(LocalDateTime.now());

        model.addAttribute("itemDto",itemDto);

        return "thymeleaf/ex2";
    }



    @GetMapping(value = {"/ex3","/ex4"})
    public void ex3(Model model) {

        List<ItemDto> list = new ArrayList<>();

        ItemDto itemDto = null;
        for (int i = 1; i <= 10; i++) {

            itemDto = new ItemDto();
            itemDto.setItemDetail("상품 상세 설명"+ i);
            itemDto.setItemNm("테스트 상품 "+i);
            itemDto.setPrice(10000*i);
            itemDto.setRegTime(LocalDateTime.now());
            list.add(itemDto);
        }


        model.addAttribute("list", list);


    }
}
