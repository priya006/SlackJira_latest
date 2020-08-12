package com.atlassian.oauth.client.example;

import com.google.api.client.auth.oauth.*;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import static com.atlassian.oauth.client.example.PropertiesClient.JIRA_HOME;

public class JiraOAuthClient {

    public final String jiraBaseUrl;
    private final JiraOAuthTokenFactory oAuthGetAccessTokenFactory;
    private final String authorizationUrl;

    public JiraOAuthClient(PropertiesClient propertiesClient) throws Exception {
        jiraBaseUrl = propertiesClient.getPropertiesOrDefaults().get(JIRA_HOME);
        this.oAuthGetAccessTokenFactory = new JiraOAuthTokenFactory(this.jiraBaseUrl);
        authorizationUrl = jiraBaseUrl + "/plugins/servlet/oauth/authorize";
    }

    /**
     * Gets temporary request token and creates url to authorize it
     *
     * @param consumerKey consumer key
     * @param privateKey  private key in PKCS8 format
     * @return request token value
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public String getAndAuthorizeTemporaryToken(String consumerKey, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        JiraOAuthGetTemporaryToken temporaryToken = oAuthGetAccessTokenFactory.getTemporaryToken(consumerKey, privateKey);
        OAuthCredentialsResponse response = temporaryToken.execute();
        System.out.println("OAuthCredentialsResponse:"+response);

/*
        System.out.println("Token:\t\t\t" + response.token);
        System.out.println("Token secret:\t" + response.tokenSecret);
*/

        OAuthAuthorizeTemporaryTokenUrl authorizationURL = new OAuthAuthorizeTemporaryTokenUrl(authorizationUrl);
        authorizationURL.temporaryToken = response.token;
       /* System.out.println("what is this response token" + response.token);
        System.out.println("what is this temporary token" + authorizationURL.temporaryToken);

        System.out.println("Retrieve request token. Go to " + authorizationURL.toString() + " to authorize it.");*/

      //  JiraOAuthGetTemporaryToken getTemporaryToken = new JiraOAuthGetTemporaryToken(authorizationUrl);

    //    OAuthCredentialsResponse verificationCode =  getTemporaryToken.execute();

        JiraOAuthGetAccessToken accessToken = new JiraOAuthGetAccessToken(authorizationURL.toString());
        System.out.println("accessToken: signer"+ accessToken.signer);


        return response.token;
    }

    public OAuthGetAccessToken VerificationCode(String authorizationURL){
        OAuthGetAccessToken verificationCode =   oAuthGetAccessTokenFactory.getVerificationCode();
        System.out.println("plan" +verificationCode);
        return verificationCode;

    }

    public String getAuthorizationUrl (){
        return authorizationUrl;
    }

    /**
     * Gets acces token from JIRA
     *
     * @param tmpToken    temporary request token
     * @param secret      secret (verification code provided by JIRA after request token authorization)
     * @param consumerKey consumer ey
     * @param privateKey  private key in PKCS8 format
     * @return access token value
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     * @throws IOException
     */
    public String getAccessToken(String tmpToken, String secret, String consumerKey, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException, IOException {
        JiraOAuthGetAccessToken oAuthAccessToken = oAuthGetAccessTokenFactory.getJiraOAuthGetAccessToken(tmpToken, secret, consumerKey, privateKey);
        OAuthCredentialsResponse response = oAuthAccessToken.execute();

        System.out.println("Access token:\t\t\t" + response.token);

        return response.token;
    }

    /**
     * Creates OAuthParameters used to make authorized request to JIRA
     *
     * @param tmpToken
     * @param secret
     * @param consumerKey
     * @param privateKey
     * @return
     * @throws NoSuchAlgorithmException
     * @throws InvalidKeySpecException
     */
    public OAuthParameters getParameters(String tmpToken, String secret, String consumerKey, String privateKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        JiraOAuthGetAccessToken oAuthAccessToken = oAuthGetAccessTokenFactory.getJiraOAuthGetAccessToken(tmpToken, secret, consumerKey, privateKey);
        oAuthAccessToken.verifier = secret;
        return oAuthAccessToken.createParameters();
    }
}
