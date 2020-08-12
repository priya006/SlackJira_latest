package com.atlassian.oauth.client.example;

import java.util.Collections;
import java.util.List;

public class ClientMain {
   private static   String authorizationUrl;
    public static void main(String[] args) throws Exception {

        ClientMain.executeClient(Command.REQUEST_TOKEN,Command.ACCESS_TOKEN, Command.REQUEST);
    }

    public static void  executeClient(Command requestToken, Command accessToken, Command request) throws Exception {
        PropertiesClient propertiesClient = new PropertiesClient();
        JiraOAuthClient jiraOAuthClient = new JiraOAuthClient(propertiesClient);
        List<String> argumentsWithoutFirst = Collections.singletonList(Command.REQUEST_TOKEN.getName());
        argumentsWithoutFirst =  Collections.singletonList(Command.ACCESS_TOKEN.getName());
        argumentsWithoutFirst =  Collections.singletonList(Command.REQUEST.getName());
        new OAuthClient(propertiesClient, jiraOAuthClient).execute(Command.fromString(argumentsWithoutFirst.get(0)), argumentsWithoutFirst);
      //   authorizationUrl =  jiraOAuthClient.getAuthorizationUrl ();
        //OAuthParameters parameters = jiraOAuthClient.getParameters(tmpToken, secret, properties.get(CONSUMER_KEY), properties.get(PRIVATE_KEY));
       //new OAuthClient(propertiesClient, jiraOAuthClient).getVerificationCode( new OAuthClient(propertiesClient, jiraOAuthClient).getParameters(argumentsWithoutFirst.get(1),"Allow"),new GenericUrl(authorizationUrl));
    }
}
