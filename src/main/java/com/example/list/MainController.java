package com.example.list;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.expression.Lists;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

@Controller
public class MainController {
    @Autowired
    LinkRepository links;

    @RequestMapping("/")
    public String displayHome() {
        return "index";
    }

    @GetMapping("/addlink")
    public String addLink(Model model) {
        model.addAttribute("link", new Link());
        return "addlink";
    }

    @PostMapping("/process")
    public String processForm(@Valid Link link, BindingResult result) {
        if (result.hasErrors()) {
            return "addlink";
        }
        links.save(link);
        return "redirect:/showlinks";
    }

    @RequestMapping("/showlinks")
    public String showLinks (Model model) {
        /*List<Link> list = new ArrayList<>();
        links.findAllByNameContainsOrderByDayEntered("Google").forEachRemaining(list::add);*/
        model.addAttribute("links", links.findAllByOrderByDayEnteredDesc());
        return "listlinks";
    }

    @RequestMapping("/detail/{id}")
    public String showLink(@PathVariable("id") long id, Model model) {
        model.addAttribute("link", links.findById(id).get());
        return "show";
    }

    @RequestMapping("/redirect/{id}")
    public String redirectLink(@PathVariable("id") long id) {
        Link url = links.findById(id).get();
        return "redirect:" + "http://" + url.getUrl();
    }

    @RequestMapping("/update/{id}")
    public String updateLink(@PathVariable("id") long id, Model model) {
        model.addAttribute("link", links.findById(id).get());
        return "addlink";
    }

    @RequestMapping("/delete/{id}")
    public String delLink(@PathVariable("id") long id) {
        links.deleteById(id);
        return "listlinks";
    }

    @RequestMapping("/redirect")
    public String redirect(long id)
    {
        String redirectUrl = links.findById(id).get().getUrl();
        return "redirect:" + redirectUrl;
    }

    @RequestMapping("/showresults")
    public String showResults(Model model, HttpServletRequest request)  {
        String q = request.getParameter("query");
        Iterable <Link> results = links.findAllByResourseNameContaining(q);
        model.addAttribute("results", results);
        return "listlinks";
    }
}


