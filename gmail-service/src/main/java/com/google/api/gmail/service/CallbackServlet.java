package com.google.api.gmail.service;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.http.GenericUrl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lliu on 9/1/15.
 */
public class CallbackServlet extends AbstractAuthorizationCodeCallbackServlet {
    private static final String USER_ID = "me";

    @Override
    protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
            throws ServletException, IOException {
        resp.sendRedirect("/counter");
    }

    @Override
    protected void onError(
            HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
            throws ServletException, IOException {
        System.out.println("Error");
    }

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
        return USER_ID;
}
}
