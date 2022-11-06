package com.separate.fruit.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class RandomCode {

    //生成6位随机数
    public char[] verifyCode() {
        String chars = "1234567890qwertyuiopasdfghjklzxcvbnm";
        char[] rands = new char[6];
        for (int i = 0; i < 6; i++) {
            int rand = (int) (Math.random() * 35);
            rands[i] = chars.charAt(rand);
        }
        return rands;
    }


    //实现邮箱验证码的发送
    @Autowired
    private JavaMailSender javaMailSender;

    public String SendEmailVerifyCode(String email) {
        String resultCode = String.valueOf(verifyCode());
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("luoquan327@foxmail.com");
            simpleMailMessage.setTo(email);
            simpleMailMessage.setSubject("验证码");
            simpleMailMessage.setText("尊敬的用户:<br/><hr>" +
                    "你的邮箱验证代码为:<h3>" + resultCode + "</h3>请在60s内完成验证!请保护好您");
            javaMailSender.send(simpleMailMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultCode;
    }

}
