package com.example.koeco_api.module.contact.service.impl;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CaptchaService {

    public String generateCaptcha() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            captcha.append(characters.charAt(random.nextInt(characters.length())));
        }
        return captcha.toString();
    }
    public void storeCaptcha(HttpSession session) {
        String captcha = generateCaptcha();
        session.setAttribute("captcha", captcha);
    }

    public boolean verifyCaptcha(String userCaptcha, HttpSession session) {
        String storedCaptcha = (String) session.getAttribute("captcha");
        return storedCaptcha != null && storedCaptcha.equals(userCaptcha);
    }

    public String generateAndStoreCaptcha(HttpSession session) {
        String captcha = generateCaptcha();
        session.setAttribute("captcha", captcha);
        return captcha;
    }
}
