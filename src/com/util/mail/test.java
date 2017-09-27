package com.util.mail;

public class test {

	public static void main(String[] args){   
        //这个类主要是设置邮件   
     MailSenderInfo mailInfo = new MailSenderInfo();    
     mailInfo.setMailServerHost("smtp.qq.com");    
     mailInfo.setMailServerPort("465");    
     mailInfo.setValidate(true);    
     mailInfo.setUserName("841418957@qq.com");    
     mailInfo.setPassword("qfkuqsywtjzmbfgf");//您的邮箱密码    
     mailInfo.setFromAddress("841418957@qq.com");    
     mailInfo.setToAddress("2191941011@qq.com");    
     mailInfo.setSubject("提醒");    
     mailInfo.setContent("已超出预先设置好的范围");    
        //这个类主要来发送邮件   
     SimpleMailSender sms = new SimpleMailSender();   
         sms.sendTextMail(mailInfo);//发送文体格式    
         //sms.sendHtmlMail(mailInfo);//发送html格式   
   }

}
