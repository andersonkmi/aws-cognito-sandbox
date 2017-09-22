package org.sharpsw;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentity;
import com.amazonaws.services.cognitoidentity.AmazonCognitoIdentityClient;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClient;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.*;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        BasicAWSCredentials awsCreds = new BasicAWSCredentials("AKIAII7V4CHRQHEZPNTA", "mgwGa13lffddIuJ/EEhrOOd5H/tyF1WLdqft/iZ2");
        AWSCognitoIdentityProvider provider = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

        Map<String, String> params = new HashMap<String, String>();
        params.put("USERNAME", "andersonkmi");
        params.put("PASSWORD", "Sh@rp1425");
        AdminInitiateAuthRequest request = new AdminInitiateAuthRequest().withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId("35r4cbbh3a7lpa7rid63575hag").withUserPoolId("us-east-1_rzjoS7Zx9").withAuthParameters(params);

        AdminInitiateAuthResult result = provider.adminInitiateAuth(request);
        //System.out.println(result.getAuthenticationResult());
        //System.out.println(result.getAuthenticationResult().getTokenType());
        System.out.println(result.getAuthenticationResult().getIdToken());
        System.out.println(result.getAuthenticationResult().getAccessToken());
        System.out.println(result.getAuthenticationResult().getRefreshToken());
        System.out.println(result.getAuthenticationResult().getExpiresIn());
        //System.out.println(result.getSession());


        Map<String, String> refreshParams = new HashMap<String, String>();
        refreshParams.put("REFRESH_TOKEN", result.getAuthenticationResult().getRefreshToken());
        AdminInitiateAuthRequest refreshReq = new AdminInitiateAuthRequest().withAuthFlow(AuthFlowType.REFRESH_TOKEN).withClientId("35r4cbbh3a7lpa7rid63575hag").withUserPoolId("us-east-1_rzjoS7Zx9").withAuthParameters(refreshParams);
        AdminInitiateAuthResult refreshResult = provider.adminInitiateAuth(refreshReq);
        System.out.println(refreshResult.getAuthenticationResult().getIdToken());
        System.out.println(refreshResult.getAuthenticationResult().getAccessToken());

        /*Map<String,String> challengeResponses = new HashMap<String,String>();
        challengeResponses.put("USERNAME", "andersonkmi");
        challengeResponses.put("PASSWORD", "Sh@rp1425");
        challengeResponses.put("NEW_PASSWORD", "Sh@rp1425");

        AdminRespondToAuthChallengeRequest finalRequest = new AdminRespondToAuthChallengeRequest()
                .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                .withChallengeResponses(challengeResponses)
                .withClientId("35r4cbbh3a7lpa7rid63575hag")
                .withUserPoolId("us-east-1_rzjoS7Zx9")
                .withSession(result.getSession());

        AdminRespondToAuthChallengeResult challengeResponse = provider.adminRespondToAuthChallenge(finalRequest);
        System.out.println(challengeResponse.getAuthenticationResult());
        */
    }
}
