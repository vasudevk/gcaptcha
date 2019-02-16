package io.captcha;

public class RecaptchaRequest {

    private String varA;
    private String varB;
    private String response;

    public String getVarA() {
        return varA;
    }

    public void setVarA(String varA) {
        this.varA = varA;
    }

    public String getVarB() {
        return varB;
    }

    public void setVarB(String varB) {
        this.varB = varB;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
