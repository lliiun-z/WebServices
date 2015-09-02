package com.google.api.gmail.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.gmail.Gmail;
import com.google.api.services.gmail.GmailScopes;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lliu on 9/1/15.
 */
public class Utils {
    private static final List<String> SCOPES = Arrays.asList(GmailScopes.GMAIL_READONLY);
    private static final File DATA_STORE_DIR = new File (
            System.getProperty("user.home"), ".credentials/gmail-service");
    private static final String CLIENT_SECRET = "/client_secret.json";
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
    private static final String SERVICE_NAME = "GMAIL_SERVICE";
    private static FileDataStoreFactory DATA_STORE_FACTORY = null;
    private static HttpTransport HTTP_TRANSPORT = null;

    static {
        try {
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }

    public static GoogleAuthorizationCodeFlow generateFlow() throws IOException {
        InputStream in = Utils.class.getResourceAsStream(CLIENT_SECRET);
        GoogleClientSecrets clientSecrets =
                GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

        return new GoogleAuthorizationCodeFlow.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, clientSecrets, SCOPES)
                        .setDataStoreFactory(DATA_STORE_FACTORY)
                        .setAccessType("offline")
                        .build();
    }

    public static Gmail getGmailService(Credential credential) throws IOException {
        return new Gmail.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(SERVICE_NAME)
                .build();
    }
}
