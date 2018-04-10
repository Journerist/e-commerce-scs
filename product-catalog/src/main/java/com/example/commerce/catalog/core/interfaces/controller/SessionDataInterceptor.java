package com.example.commerce.catalog.core.interfaces.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.commerce.catalog.core.domain.entity.customer.Customer;
import com.example.commerce.catalog.core.domain.entity.customer.CustomerFactory;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SessionDataInterceptor extends HandlerInterceptorAdapter {

    private final CustomerFactory customerFactory;

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        HttpSession session = request.getSession();
        Optional<Object> sessionCustomerId = Optional.ofNullable(session.getAttribute("customerId"));

        if (!sessionCustomerId.isPresent()) {
            Customer customer = customerFactory.createNew();
            long customerId = customer.getId();
            session.setAttribute("customerId", customerId);
        } else {
            session.setAttribute("customerId", (long) sessionCustomerId.get());
        }
        modelAndView.getModel().put("sessionId", session.getId());
        modelAndView.getModel().put("customerId", session.getAttribute("customerId"));

        super.postHandle(request, response, handler, modelAndView);
    }

}
