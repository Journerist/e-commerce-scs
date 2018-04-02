package com.example.commerce.catalog.core.interfaces.controller;

import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.commerce.catalog.core.application.ProductApplicationService;
import com.example.commerce.catalog.core.domain.entity.customer.Customer;
import com.example.commerce.catalog.core.domain.entity.customer.CustomerFactory;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class GreetingController {

    private final ProductApplicationService productAS;

    @GetMapping("/greeting")
    public String greeting(@RequestParam(name = "name", required = false, defaultValue = "World") String name,
            Model model, HttpSession session) {

        model.addAttribute("customerId", String.valueOf(session.getAttribute("customerId")));
        model.addAttribute("products", productAS.getAllAvailableProducts());
        model.addAttribute("name", name);

        return "greeting";
    }

}