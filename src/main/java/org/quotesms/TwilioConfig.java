package org.quotesms;

//objet pour la config twilio

public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String twilioPhone;
    private String myPhone;

    public TwilioConfig(String accountSid, String authToken, String twilioPhone, String myPhone) {
        this.accountSid = accountSid;
        this.authToken = authToken;
        this.twilioPhone = twilioPhone;
        this.myPhone = myPhone;
    }

    public String getAccoundSid(){
        return accountSid;
    }

    public String getAuthToken(){
        return authToken;
    }

    public String getTwilioPhone(){
        return twilioPhone;
    }

    public String getMyPhone() {
        return myPhone;
    }
} 