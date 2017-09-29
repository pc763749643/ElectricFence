package function;

import java.io.IOException;
import com.google.gson.Gson;

import info.Point;
import info.Token;
import info.Trail;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetInfo {
	private final OkHttpClient client = new OkHttpClient();
	String url="http://capi.gpslink.cn";
	
	//验证Token是否有效
		public int getResponseCode(String Authorization) {
			Request request = new Request.Builder()
				      .url(url+"/api/Users")
				      .header("Accept", "application/json")
				      .header("Authorization", Authorization)
				      .build();
			try {
				Response response = client.newCall(request).execute();
				return response.code();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return 0;
		}
	
	//获取Token的函数，返回Token的字符串
	public String getToken(String username,String password,String grant_type,String scope) {
		String str="",file="";
		SaveToken savetoken = new SaveToken();
		final Gson gson = new Gson();
		 RequestBody body = new FormBody.Builder()
														.add("username", username)
														.add("password", password)
														.add("grant_type", grant_type)
														.add("scope", scope)
														.build();
		Request request = new Request.Builder()
			      .url(url+"/Token")
			      .post(body)
			      .build();
		try {
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful()) {
				savetoken.WriteStringToFile("{'username':'233','password':'233','token':'233'}");
				return null;
			}
			Token token = gson.fromJson(response.body().charStream(), Token.class);
			str="bearer "+token.access_token;
			file  = "{'username':'"+username+"','password':'"+password+"','token':'"+str+"'}";
			savetoken.WriteStringToFile(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return str;
	}
	
	//获取坐标的函数，返回坐标MapPoints对象
	public Point getPoint(String cid,String Authorization) {
		final Gson gson = new Gson();
		Point point = null;
		Request request = new Request.Builder()
			      .url(url+"/api/Point/Last?cid="+cid)
			      .header("Accept", "application/json")
			      .header("Authorization", Authorization)
			      .build();
		try {
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			point = gson.fromJson(response.body().charStream(), Point.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return point;
	}
	
	//获取历史轨迹
	public String getTrail(String cid,String Authorization,String beg,String end) {
		final Gson gson = new Gson();
		Request request = new Request.Builder()
			      .url(url+"/api/Point/Trail?cid="+cid+"&beg="+beg+"&end="+end)
			      .header("Accept", "application/json")
			      .header("Authorization", Authorization)
			      .build();
		try {
			Response response = client.newCall(request).execute();
			if (!response.isSuccessful())
				throw new IOException("Unexpected code " + response);
			Trail trail = gson.fromJson(response.body().charStream(), Trail.class);
			if(trail.items.length!=0) {
				return gson.toJson(trail.items);
			}
			else {
				return "none";
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "none";
	}


	
	/*public static void main(String[] args) {
		GetInfo test = new GetInfo();
		String token="";
		token = test.getToken("861842030027687", "123456", "password", "single");
		String str = test.getTrail("861842030027687",  token,"2017-08-24","2017-08-25");
		System.out.println(str);
	}*/
	
}
