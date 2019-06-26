package com.zhuhao.eshop.serviceImpl;

import com.zhuhao.eshop.entity.User;
import com.zhuhao.eshop.mapper.UserMapper;
import com.zhuhao.eshop.service.UserCenterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class UserCenterServImpl implements UserCenterService {

    private static final Logger log = LoggerFactory.getLogger(UserCenterServImpl.class);

    @Autowired
    UserMapper userMapper;

    @Autowired
    JavaMailSender mailSender;
    @Value("${spring.mail.username}")
    private String from;

    @Override
    public int updateUserpassword(User user) {
        return userMapper.updateUserpassword(user);
    }

    @Override
    public int forgetmailpassword(User user) {
        return userMapper.forgetmailpassword(user);
    }

    @Override
    public User checkemail(String email) {
        return userMapper.checkemail(email);
    }

    public boolean sendEmailByzh(String checkcode,String emailstr)  {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setFrom(from);
            helper.setTo(emailstr);
            helper.setSubject("【eshop】找回密码");
            helper.setText("<html><head></head><body><h3>尊敬的用户您好</h3><br/><p>你本次的验证码为"+checkcode+"，请在60s内填写，过期则作废，本邮件为系统自动发送，请勿回复！</p></body></html>",true);
            mailSender.send(message);
            log.info("发送邮件值至"+emailstr+" 成功");
        }catch (Exception e){
            log.error("发送邮件值至"+emailstr+" 失败");
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
