package task;

import java.util.Timer;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class TaskManager implements ServletContextListener {   
	 /**  
	  * 定时器  
	  */  
	 private Timer timer;   
	 /**  
	  * 在Web应用启动时初始化任务  
	  */  
	 public void contextInitialized(ServletContextEvent event) {   
	        //定义定时器   
	  timer = new Timer(true);    
	  //3秒运行一次
	  timer.schedule(new Task(),0,10000);   
	 }   
	 /**  
	  * 在Web应用结束时停止任务  
	  */  
	 public void contextDestroyed(ServletContextEvent event) {   
	  timer.cancel(); // 定时器销毁   
	 }   
	}  
