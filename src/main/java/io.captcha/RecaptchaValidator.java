package io.captcha;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@Component
public class RecaptchaValidator {

    private final RestTemplate restTemplate;

    private String recaptchaSecret = "6LdB8pEUAAAAAAVxfjb36H60yWTpGDk2YcXmJmr_";
    private String recaptchaVerifyUrl = "https://www.google.com/recaptcha/api/siteverify";

    public RecaptchaValidator(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    RecaptchaResponse recaptchaResponse(RecaptchaRequest recaptchaRequest) {
        RecaptchaResponse recaptchaResponse = new RecaptchaResponse();
        recaptchaResponse.setValid(verify(recaptchaRequest.getResponse()));
        return recaptchaResponse;
    }

    /**
     * This method verifies the Google reCAPTCHA response from client
     *
     * @param response
     * @return
     */
    private boolean verify(String response) {
        MultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        map.add("secret", recaptchaSecret);
        map.add("response", response);

        VerifyResponse verifyResponse = new VerifyResponse();
        try {
            verifyResponse = this.restTemplate.postForObject(recaptchaVerifyUrl, map, VerifyResponse.class);
        } catch (RestClientException e) {
            System.out.print(e.getMessage());
        }

        return verifyResponse.isSuccess();
    }
}