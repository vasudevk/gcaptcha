package io.captcha;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class RecaptchaController {

    @Autowired
    private RecaptchaValidator recaptchaService;

    @PostMapping("/verify-captcha")
    public RecaptchaResponse verifyCaptcha(@RequestBody RecaptchaRequest recaptchaRequest) {
        return recaptchaService.recaptchaResponse(recaptchaRequest);
    }
}