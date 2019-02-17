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
        VerifyResponse verifyResponse = verify(recaptchaRequest.getResponse());

        if (Double.valueOf(verifyResponse.getScore()) >= 0.9)
            recaptchaResponse.setValid(verifyResponse.isSuccess());
        else
            recaptchaResponse.setValid(false);

        return recaptchaResponse;
    }

    /**
     * This method verifies the Google reCAPTCHA response from client
     *
     * @param response
     * @return
     */
    private VerifyResponse verify(String response) {
        MultiValueMap<Object, Object> map = new LinkedMultiValueMap<>();
        map.add("secret", recaptchaSecret);
        map.add("response", response);

        VerifyResponse verifyResponse = new VerifyResponse();
        try {
            verifyResponse = this.restTemplate.postForObject(recaptchaVerifyUrl, map, VerifyResponse.class);
        } catch (RestClientException e) {
            System.out.print(e.getMessage());
        }

        return verifyResponse;
    }
}