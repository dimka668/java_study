package com.klyshov.BookTicket;

/**
 * Created by 16688641 on 06.10.2019.
 */
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Map;

@Controller
@RequestMapping(value = "/booking")
//@SessionAttributes(types = TicketForm.class)
@SessionAttributes("ticketForm")
public class BookTicketController {

    @RequestMapping(method = RequestMethod.GET)
    //public ModelAndView start(Model model) {
    public ModelAndView start(@ModelAttribute("ticketForm") TicketForm ticketForm) {
        // после выхода из start() форма будет скопирована в http session атрибуты
        // благодаря @SessionAttributes(types = TicketForm.class)
        //model.addAttribute(new TicketForm());
        return new ModelAndView("booking/booking","ticketForm", ticketForm);
//        return "booking/booking";
    }

    @RequestMapping(value = "/multi")
    public ModelAndView multi(
            @RequestParam() Map<String,String> map,
            @RequestHeader(value = "Accept-Encoding", required = false, defaultValue = "none") String encoding,
            @RequestHeader(value = "Keep-Alive",required = false, defaultValue = "1") long keepAlive,
            @CookieValue() Map<String,String> cookiesMap
    ) {
        System.out.println(cookiesMap);
        //map.keySet().stream().forEach(System.out::println);
        return new ModelAndView("booking/booking");
    }

    @RequestMapping("/owners/{ownerId}/pets/{petId}/edit")
    public String processSubmit(@ModelAttribute("pet") Pet pet, BindingResult result, RedirectAttributes rm, Model model) {
        if (result.hasErrors()) {
            return "petForm";
        }
        //System.out.println(pet.getPetId());
        //System.out.println(pet.getOwnerId());

        //rm.addFlashAttribute("pet", pet);
        rm.addFlashAttribute("petId", pet.getPetId());
        rm.addFlashAttribute("ownerId", pet.getOwnerId());
        rm.addFlashAttribute("modelkey", "modelvalue");
        rm.addAttribute("nonflash", "nonflashvalue");
        model.addAttribute("modelkey", "modelvalue");
//        rm.addAttribute("nonflash", "nonflashvalue");
//        model.addAttribute("modelkey", "modelvalue");
        return "redirect:/booking/pet-info";
    }

    @RequestMapping("/new-pet")
    @ModelAttribute("newPet")
    public Pet getNewPet() {
        return new Pet("1","2");
    }

    @RequestMapping("/exception")
    public String throwException() {
        throw new IllegalArgumentException();
    }


    @RequestMapping("/exception-local")
    public String throwExceptionLocal() {
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Actor Not Found", new Exception());
    }


    @RequestMapping("/owners1/{ownerId}/pets/{petId}/edit")
    public String processSubmit1(
            @ModelAttribute("ownerId") String ownerId,
            @ModelAttribute("petId") String petId,
            RedirectAttributes rm,
            Model model
    ) {
        //System.out.println(pet.getPetId());
        //System.out.println(pet.getOwnerId());
        System.out.println("from - petId: " + petId);
        //rm.addFlashAttribute("pet", pet);
        rm.addFlashAttribute("petId", petId);
        rm.addFlashAttribute("ownerId", ownerId);
        rm.addFlashAttribute("modelkey", "modelvalue");
        rm.addAttribute("nonflash", "nonflashvalue");
        model.addAttribute("modelkey", "modelvalue");
//        rm.addAttribute("nonflash", "nonflashvalue");
//        model.addAttribute("modelkey", "modelvalue");
        return "redirect:/booking/pet-info";
    }


    @RequestMapping("/pet-info")
    public String petInfo(
            Model model,
            @RequestParam() Map<String,String> map
    ) {
        System.out.println("Entered rm2 method ");

        Map md = model.asMap();
        for (Object modelKey : md.keySet()) {
            Object modelValue = md.get(modelKey);
            System.out.println(modelKey + " -- " + modelValue);
        }

        System.out.println("=== Request data ===");

        map.forEach((x,y) -> {
            System.out.println(x + "=" + y);
        });

        return "petInfo";
    }

//    @ModelAttribute
//    public Pet createPet(@PathVariable String petId, @PathVariable String ownerId) {
//        return new Pet(petId,ownerId);
//    }

    @ModelAttribute("ticketForm")
    public TicketForm createTicketForm(){
        return new TicketForm();
    }

    @RequestMapping(value = "/movie", method = RequestMethod.POST)
    //public String selectMovie(TicketForm ticketForm) {
    public ModelAndView selectMovie(@ModelAttribute("ticketForm") TicketForm ticketForm) {

        Assert.notNull(ticketForm);
        Assert.notNull(ticketForm.getMovieId());

        return new ModelAndView("booking/customer","ticketForm", ticketForm);
        //return "booking/customer";
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    //public String enterCustomerData(TicketForm ticketForm) {
    public ModelAndView enterCustomerData(@ModelAttribute("ticketForm") TicketForm ticketForm) {

        Assert.notNull(ticketForm);
        // movieId не передавался с customer.jsp, но он был сохранен в сессии во время selectMovie()
        Assert.notNull(ticketForm.getMovieId());
        Assert.notNull(ticketForm.getLastName());

        return new ModelAndView("booking/payment","ticketForm", ticketForm);
        //return "booking/payment";
    }

    @RequestMapping(value = "/payment", method = RequestMethod.POST)
    public ModelAndView enterPaymentDetails(@ModelAttribute("ticketForm") TicketForm ticketForm) {

        // movieId не передавался с customer.jsp, но он был сохранен в сессии во время selectMovie()
        Assert.notNull(ticketForm.getMovieId());
        // lastName не передавался с payment.jsp, но он был сохранен в сессии во время enterCustomerData()
        Assert.notNull(ticketForm.getLastName());
        Assert.notNull(ticketForm.getCreditCardNumber());

        return new ModelAndView("redirect:/booking/confirmation","ticketForm", ticketForm);
        //return "redirect:/booking/confirmation";
    }

    @RequestMapping(value = "/confirmation", method = RequestMethod.GET)
    public String confirmation(SessionStatus status) {
        status.setComplete(); // очищаем Spring Session в целях безопасности личных данных
        return "booking/confirmation";
    }

}
