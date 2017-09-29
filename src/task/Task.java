package task;

import java.util.TimerTask;

import com.google.gson.Gson;

import function.GetInfo;
import function.IsPointInPolygon;
import function.SaveToken;
import info.Point;
import info.TokenFile;
import mail.MailUtil;

public class Task extends TimerTask {
	private SaveToken savetoken = new SaveToken();
	final Gson gson = new Gson();
	TokenFile tokenfile = new TokenFile();
	GetInfo getinfo = new GetInfo();
   public void run() {
		tokenfile = gson.fromJson(savetoken.readString(), TokenFile.class);
		System.out.println(tokenfile.username);
		System.out.println(tokenfile.password);
		System.out.println(tokenfile.token);
		System.out.println(getinfo.getResponseCode(tokenfile.token));
		if(getinfo.getResponseCode(tokenfile.token)==200) {
			Point point = getinfo.getPoint(tokenfile.username, tokenfile.token);
			IsPointInPolygon is = new IsPointInPolygon();
			if(is.run(point)=="out") {
				MailUtil.sendMail("lg1009724327@163.com");
			}
			
		}
   }    
}
