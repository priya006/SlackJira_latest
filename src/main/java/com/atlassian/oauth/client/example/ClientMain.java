package com.atlassian.oauth.client.example;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClientMain {

    public static void main(String[] args) throws Exception {

        ClientMain.executeClient(Command.REQUEST_TOKEN,Command.ACCESS_TOKEN, Command.REQUEST);
    }


    public static void  executeClient(Command requestToken, Command accessToken, Command request) throws Exception {
        System.out.println(Command.names());


        PropertiesClient propertiesClient = new PropertiesClient();
        JiraOAuthClient jiraOAuthClient = new JiraOAuthClient(propertiesClient);
        List<String> argumentsWithoutFirst = Collections.singletonList(Command.REQUEST_TOKEN.getName());
         Collections.singletonList(Command.ACCESS_TOKEN.getName());
         Collections.singletonList(Command.REQUEST.getName());

        new OAuthClient(propertiesClient, jiraOAuthClient).execute(Command.fromString(argumentsWithoutFirst.get(0)), argumentsWithoutFirst);

    }
}

//Command.REQUEST.name