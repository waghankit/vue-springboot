package com.vue.vuebackend;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.json.JSONObject;


import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
public class GoogleSignInController {

    private static final String CLIENT_ID = "314273525069-dbeumrev25hv56181ai4iqql3c4dern6.apps.googleusercontent.com"; // Replace with your client ID

    private final HttpTransport transport;

    private final JsonFactory jsonFactory;

    public GoogleSignInController(HttpTransport transport, JsonFactory jsonFactory) {
        this.transport = transport;
        this.jsonFactory = jsonFactory;
    }

    @PostMapping("/verifyToken")
    public String verifyGoogleIdToken(@RequestBody String payload) {
        System.out.println(payload);
        String value = "";
        try{
            JSONObject jsonObject = new JSONObject(payload);
            value = (String) jsonObject.get("idToken");
        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(value);
        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(CLIENT_ID))
                .setIssuers(Collections.singletonList("accounts.google.com"))
                .build();
        System.out.println("check1");
        try {
            System.out.println("check2");
            GoogleIdToken idToken = verifier.verify(value);
            System.out.println("check3");
            if (idToken != null) {
                System.out.println("check4");

                String userId = idToken.getPayload().getSubject();
                System.out.println("check5");

                System.out.println(userId);
                return "verified";
            } else {
                return "Invalid Token";
            }
        } catch (GeneralSecurityException | IOException e) {
            e.printStackTrace();
            return "ID token verification failed";
        }
    }
}
