package io.captcha;

public class RecaptchaResponse {

    private boolean isValid;

    public boolean isValid() {
        return isValid;
    }

    void setValid(boolean valid) {
        isValid = valid;
    }

}
