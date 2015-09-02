package com.google.api.gmail.service;

import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by lliu on 9/1/15.
 */
public class SelfAuthorizationServlet extends HttpServlet {
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter writer = response.getWriter();

        // parse the user id from the request url
        String queryString = request.getQueryString();
        writer.println(request.getPathInfo());
        writer.println(queryString);
        writer.println("DONE");
    }
}
