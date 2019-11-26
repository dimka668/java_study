package com.klyshov.controller;

/**
 * Created by 16688641 on 21.11.2019.
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.klyshov.model.ZShop;
import com.klyshov.service.MessageService;

import java.text.SimpleDateFormat;
import java.util.Date;
//import com.klyshov.service.ZShopService;

@RestController
public class MessageController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }


    private MessageService messageService;

//    private ZShopService zshopService;

    @Autowired
    public void setMessageService(MessageService service) {
        this.messageService = service;
    }

//    @Autowired
//    public void setZShopService(ZShopService service) {
//        this.zshopService = service;
//    }

    @RequestMapping(path = "/message", method = RequestMethod.GET,
            produces = "text/plain")
    public String textMessage() {
        return messageService.textMessage();
    }

//    @RequestMapping(path = "/message", method = RequestMethod.GET,
//            produces = "application/json")
//    public ZShop jsonMessage() {
//        return zshopService.getRandomShop();
//    }

//    @RequestMapping(path = "/message", method = RequestMethod.GET,
//            produces = "text/xml")
//    public ZShop xmlMessage() {
//        return zshopService.getRandomShop();
//    }
}
