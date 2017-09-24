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
        String awsAccess = "";
        String awsSecret = "";
        String cognitoAppCientId = "";
        String cognitoPoolId = "";

        BasicAWSCredentials awsCreds = new BasicAWSCredentials(awsAccess, awsSecret);
        AWSCognitoIdentityProvider provider = AWSCognitoIdentityProviderClientBuilder.standard().withRegion(Regions.US_EAST_1).withCredentials(new AWSStaticCredentialsProvider(awsCreds)).build();

        Map<String, String> params = new HashMap<String, String>();
        params.put("USERNAME", "andersonkmi");
        params.put("PASSWORD", "");
        AdminInitiateAuthRequest request = new AdminInitiateAuthRequest().withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withClientId(cognitoAppCientId).withUserPoolId(cognitoPoolId).withAuthParameters(params);

        AdminInitiateAuthResult result = provider.adminInitiateAuth(request);
        System.out.println(result.getAuthenticationResult());
        System.out.println(result.getAuthenticationResult().getTokenType());
        System.out.println(result.getAuthenticationResult().getIdToken());
        System.out.println(result.getAuthenticationResult().getAccessToken());
        System.out.println(result.getAuthenticationResult().getRefreshToken());
        System.out.println(result.getAuthenticationResult().getExpiresIn());
        System.out.println(result.getSession());


        // Refresh token
        /*Map<String, String> refreshParams = new HashMap<>();
        refreshParams.put("REFRESH_TOKEN", result.getAuthenticationResult().getRefreshToken());
        AdminInitiateAuthRequest refreshReq = new AdminInitiateAuthRequest().withAuthFlow(AuthFlowType.REFRESH_TOKEN).withClientId(cognitoAppCientId).withUserPoolId(cognitoPoolId).withAuthParameters(refreshParams);
        AdminInitiateAuthResult refreshResult = provider.adminInitiateAuth(refreshReq);
        System.out.println(refreshResult.getAuthenticationResult().getIdToken());
        System.out.println(refreshResult.getAuthenticationResult().getAccessToken());*/

        // password confirm
        /*Map<String,String> challengeResponses = new HashMap<>();
        challengeResponses.put("USERNAME", "andersonkmi");
        challengeResponses.put("PASSWORD", "Sh@rp1425");
        challengeResponses.put("NEW_PASSWORD", "CoD1425!!");

        AdminRespondToAuthChallengeRequest finalRequest = new AdminRespondToAuthChallengeRequest()
                .withChallengeName(ChallengeNameType.NEW_PASSWORD_REQUIRED)
                .withChallengeResponses(challengeResponses)
                .withClientId(cognitoAppCientId)
                .withUserPoolId(cognitoPoolId)
                .withSession(result.getSession());

        AdminRespondToAuthChallengeResult challengeResponse = provider.adminRespondToAuthChallenge(finalRequest);
        System.out.println(challengeResponse.getAuthenticationResult());*/
    }
}
