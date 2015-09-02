package com.google.api.gmail.service;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lliu on 9/1/15.
 */
public class AddressCounter extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        System.out.println("Finally");
    }
}
