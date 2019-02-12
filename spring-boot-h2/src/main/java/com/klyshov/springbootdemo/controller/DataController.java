package com.klyshov.springbootdemo.controller;

import com.klyshov.springbootdemo.exception.RestException;
import com.klyshov.springbootdemo.service.DataService;
import com.klyshov.springbootdemo.utils.Ajax;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;
import java.util.Set;

@Controller
public class DataController extends ExceptionHandlerController {

    private static final Logger LOG = Logger.getLogger(DataController.class);

    @Autowired
    @Qualifier("dataService")
    private DataService dataService;

    @RequestMapping(value = "/persist", method = RequestMethod.POST)
    public @ResponseBody
    Map<String, Object> persist(@RequestParam("data") String data) throws RestException {
        System.out.println("/persist");
        try {
            if (data == null || data.equals("")) {
                return Ajax.emptyResponse();
            }
            dataService.persist(data);
            return Ajax.emptyResponse();
        } catch (Exception e) {
            throw new RestException(e);
        }
    }

    @RequestMapping(value = "/getRandomData", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, Object> getRandomData() throws RestException {
        System.out.println("/getRandomData");
        try {
            Set<String> result = dataService.getRandomData();
            return Ajax.successResponse(result);
        } catch (Exception e) {
            throw new RestException(e);
        }
    }


    @Scheduled(fixedRate = 5000)
    public void reportCurrentTime() {
        System.out.println("Scheduled task");
    }
}
