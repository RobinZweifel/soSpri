package ch.bbw.pr.sospri.service;

import ch.bbw.pr.sospri.controller.MembersController;
import ch.bbw.pr.sospri.model.ReCaptchResponseType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class ReCaptchaValidationService {

    Logger logger = LoggerFactory.getLogger(MembersController.class);

    private static final String GOOGLE_RECAPTCHA_ENDPOINT = "https://www.google.com/recaptcha/api/siteverify";

    public boolean validateCaptcha(String captchaResponse){
        RestTemplate restTemplate = new RestTemplate();

        MultiValueMap<String, String> requestMap = new LinkedMultiValueMap<>();
        String RECAPTCHA_SECRET = "6LcF0KggAAAAAPLBVc4PmmXANHPryZHtwBVQXVxg";
        requestMap.add("secret", RECAPTCHA_SECRET);
        requestMap.add("response", captchaResponse);

        ReCaptchResponseType apiResponse = restTemplate.postForObject(GOOGLE_RECAPTCHA_ENDPOINT, requestMap, ReCaptchResponseType.class);
        if(apiResponse == null){
            logger.error("ReCaptcha API response is null");
            return false;
        }

        logger.debug("ReCaptcha API response: " + apiResponse);
        return Boolean.TRUE.equals(apiResponse.isSuccess());
    }
}
