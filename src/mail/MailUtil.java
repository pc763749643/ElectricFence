package mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailUtil {
    /**
     * 发送邮件
     * @param to  收件人
     */
    public static void sendMail(String to){
        /**
         * 获得session对象
         * 创建一个代表邮件的对象Message
         * 发送邮件Transport
         * 
         */
        //获取连接信息
        Properties props=new Properties();
        //设置邮件服务器
        props.setProperty("mail.smtp.host", "smtp.qq.com");
        //设置邮件服务器端口
        props.setProperty("mail.smtp.port", "465");
        //开启认证
        props.setProperty("mail.smtp.auth", "true");
        //开启ssl 
        props.setProperty("mail.smtp.ssl.enable", "true");

        Session session =Session.getInstance(props, new Authenticator() {

            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("841418957@qq.com", "qfkuqsywtjzmbfgf");
            }
        });
        //创建邮件对象
        Message message = new MimeMessage(session);
        try {
            //设置发送人
            message.setFrom(new InternetAddress("841418957@qq.com"));
            //设置收件人
            message.addRecipient(RecipientType.TO, new InternetAddress(to));
            //收信人
            //Message.RecipientType.TO 
            //抄送人
            //Message.RecipientType.CC 
            //暗送人
            //Message.RecipientType.BCC

            //标题
            message.setSubject("提醒");
            //设置邮件内容
            message.setContent("设备已超出规定范围！", "text/html;charset=UTF-8");
            //发送
            Transport.send(message);
        } catch (AddressException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (MessagingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        sendMail("lg1009724327@163.com");
        System.out.println("成功！.......");
    }
}