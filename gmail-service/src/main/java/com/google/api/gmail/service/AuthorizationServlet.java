package com.google.api.gmail.service;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Collections;

/**
 * Created by lliu on 9/1/15.
 */
public class AuthorizationServlet extends AbstractAuthorizationCodeServlet {
    private static final String USER_ID = "me";

    @Override
    protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
        GenericUrl url = new GenericUrl(req.getRequestURL().toString());
        url.setRawPath("/oauth2callback");
        return url.build();
    }

    @Override
    protected AuthorizationCodeFlow initializeFlow() throws IOException {
        Utils utils = new Utils();
        return Utils.generateFlow();
    }

    @Override
    protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
        // so far we just use one user;
        return USER_ID;
    }
}
