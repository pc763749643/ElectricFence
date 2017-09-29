package function;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SaveToken {
	public void WriteStringToFile(String Token) {  
        try {
            PrintStream ps = new PrintStream(new FileOutputStream("Token.txt"));  
            ps.print(Token);// 往文件里写入字符串
            ps.close();
        } catch (FileNotFoundException e) {  
            // TODO Auto-generated catch block  
            e.printStackTrace();  
        }
    }
	
	public String readString(){
	    String str="";
	    try {
	        FileInputStream in=new FileInputStream("Token.txt");
	        // size  为字串的长度 ，这里一次性读完  
	        int size=in.available();  
	        byte[] buffer=new byte[size];  
	        in.read(buffer);  
	        in.close();  
	        str=new String(buffer,"UTF-8");
	    } catch (IOException e) {  
	        // TODO Auto-generated catch block  
	        return null;  
	    }  
	    return str;  
	}  

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		SaveToken file = new SaveToken();
		file.WriteStringToFile("123456789");
		System.out.println(file.readString());
	}*/

}
