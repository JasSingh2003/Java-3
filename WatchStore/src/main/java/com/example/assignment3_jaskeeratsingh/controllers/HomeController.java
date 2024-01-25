package com.example.assignment3_jaskeeratsingh.controllers;

import com.example.assignment3_jaskeeratsingh.Database.DatabaseAccess;
import com.example.assignment3_jaskeeratsingh.beans.Watch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Controller
public class HomeController {
    List<Watch> watchList = new CopyOnWriteArrayList<Watch>();
    List<Watch> cartList = new CopyOnWriteArrayList<Watch>();
    @Autowired
    private DatabaseAccess da;

    @GetMapping("/")
    public String index() {
        return "index";
    }


    @GetMapping("/login")
    public String login(Model model) {

        return "login";
    }

    @PostMapping("/register")
    public String postRegister(@RequestParam String username, @RequestParam String password) {
        da.addUser(username, password);
        Long userId = da.findUserAccount(username).getUserId();
// this next line is dangerous! For extra credit, try making a DatabaseAccess method to find a roleIdassociate with a role of “ROLE_USER”and add the “correct”id every time
        da.addRole(userId, Long.valueOf(1));
        return "login";
    }

        @GetMapping("/register")
    public String getRegister () {
        return "register";
    }

    @GetMapping("/secure")
    public String Store(Model model) {

        model.addAttribute("watch", new Watch());
        model.addAttribute("watchList", da.getwatchList());
        return "secure/store";
    }

    @GetMapping("/addWatches")
    public String showForm(Model model) {
        model.addAttribute("watch", new Watch()); // assuming you have a Watch class
        return "secure/addWatches"; // the name of your Thymeleaf template
    }
    @PostMapping("/cart")
    public String cart(Model model,@ModelAttribute Watch watch){
        cartList.add(watch);
        model.addAttribute("watch", new Watch());
        model.addAttribute("cartList", da.getcartList());
        return "secure/Cart";
    }
    @GetMapping("/cart")
    public String getCart(Model model,@ModelAttribute Watch watch){
        model.addAttribute("watch", new Watch());
        model.addAttribute("cartList", da.getcartList());
        return "secure/Cart";
    }
    @GetMapping("/secure/deleteWatchByBrand/{brand}")
    public String deleteWatchByBrand(Model model, @PathVariable String brand) {
        Watch watch = da.getcartList(brand).get(0);
        da.deleteWatchbyBrand(brand);
        model.addAttribute("cartList", da.getcartList());
        return "secure/Cart";
    }
    @GetMapping("/GoToCart")
    public String GoToCart(Model model,@ModelAttribute Watch watch) {
        model.addAttribute("watch", new Watch());
        //model.addAttribute("cartList", da.getcartList());
        return "secure/Cart";
    }
    @PostMapping("/insertWatch")
    public String addWatch(Model model, @ModelAttribute Watch watch,
                           @RequestParam String brand,
                           @RequestParam double price,
                           @RequestParam double rating) {
        watch.setBrand(brand);
        watch.setPrice(price);
        watch.setRating(rating);
        cartList.add(watch);
        da.insertWatch(watch.getBrand(), watch.getPrice(),watch.getRating());
        model.addAttribute("watchList", da.getwatchList());
        return "secure/store";
    }

    @PostMapping("/insertWatchAdmin")
    public String addWatchAdmin(Model model, @ModelAttribute Watch watch){
        watchList.add(watch);
        da.insertWatchAdmin(watch.getWatchId(),watch.getBrand(), watch.getPrice(),watch.getStockQuantity(),watch.getDescription(),watch.getRating());
        model.addAttribute("watchList", da.getwatchList());
        return "secure/store";
    }
}