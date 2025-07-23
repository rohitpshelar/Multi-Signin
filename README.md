# Multi-Signin OAuth 2

## Google OAuth 2.0
<img width="1360" height="686" alt="WEB" src="https://github.com/user-attachments/assets/3891e68b-4be4-40a2-a719-4574aca7a657" />

   1. https://console.cloud.google.com/
   2. then Create new Project >  Search Product -> APIs and services > OAuth consent screen > Get Started
   3. fill form and create
   4. Then create OAUTH Client 
   5. Then Click Audience and Click Publish APP
   6. Then Click Data Access to Add scopes
   7. Use https://developers.google.com/oauthplayground/ - if you dont have UI, to send request to google oAuth2
   8. add https://www.googleapis.com/auth/userinfo.email and click setting and enable `Use your own OAuth credentials` and add Client ID and Secret of Google
   9. and Click Authorize APIs ( Make sure https://developers.google.com/oauthplayground is added in https://console.cloud.google.com/ > APIs and services > OAuth consent screen > Client > Authorized redirect URIs )
   10. Authentication code is generated then send that code in our API[GoogleAuthController.java](src/main/java/com/example/multi_signin/GoogleAuthController.java)

## Facebook OAuth 2.0
  1. Goto https://developers.facebook.com/
  2. Login > Create App > Cases (Other) > App Type (Consumer) > Create App
  3. Select product (Facebook Login) >  click setup >  Other 
  4. Click Settings >  Save changes
  5. Goto > App Settings > Basics > get Client ID/Secret 

## GitHub OAuth 2.0
    
  1. Click on Profile > Settings > Developer Settings >  OAuth Apps
