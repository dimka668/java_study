package com.klyshov.spring.mvc.validator_example;

/**
 * Created by 16688641 on 23.10.2019.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.validation.Valid;
import com.klyshov.spring.mvc.validator_example.User;
import com.klyshov.spring.mvc.validator_example.validators.EmailValidator;
import com.klyshov.spring.mvc.validator_example.validators.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
@Controller
@RequestMapping("/spring/mvc/validator_example")
public class MyWorldController {
    @Autowired
    private UserValidator userValidator;
    @Autowired
    private EmailValidator emailValidator;

    @RequestMapping(value="signup", method = RequestMethod.GET)
    public ModelAndView user(){
        return new ModelAndView("/spring/mvc/validator_example/user","user",new User());
    }

    @InitBinder
    public void dataBinding(WebDataBinder binder) {
        binder.addValidators(userValidator, emailValidator);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, "dob", new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value="save", method = RequestMethod.POST)
    public String createUser(@ModelAttribute("user") @Valid User user,BindingResult result, ModelMap model) {
        if(result.hasErrors()) {
            return "/spring/mvc/validator_example/user";
        }
        System.out.println("Name:"+ user.getName());
        System.out.println("Email:"+ user.getEmail());
        System.out.println("Date of Birth:"+ user.getDob());
        model.addAttribute("msg", "Welcome to My World!");
        return "/spring/mvc/validator_example/success";
    }
}
