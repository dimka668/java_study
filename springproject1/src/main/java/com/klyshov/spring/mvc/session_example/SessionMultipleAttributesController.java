package com.klyshov.spring.mvc.session_example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import com.klyshov.spring.mvc.session_example.domain.*;

/**
 * Created by 16688641 on 06.10.2019.
 */
@Controller
@RequestMapping("/spring/mvc/session_example")
@SessionAttributes({"person", "sheldon"})
public class SessionMultipleAttributesController {

    @RequestMapping(value = "/session-multiattr", method = RequestMethod.GET)
    public ModelAndView sessionMultiAttributes(@ModelAttribute Person person, @ModelAttribute Sheldon sheldon ) {
        ModelAndView modelAndView = new ModelAndView("/spring/mvc/session_example/session_multiple_attr");
        modelAndView.addObject( person );
        modelAndView.addObject( sheldon );
        return modelAndView;
    }

    @ModelAttribute("person")
    public Person populatePerson() {
        return new Person("empty");
    }

    @ModelAttribute("sheldon")
    public Sheldon populateSheldon() {
        return new Sheldon();
    }


}

