<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.8.1.min.js"></script>
<script src="js/map.js"></script>
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<script src="bootstrap/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<script type="text/javascript" src="http://webapi.amap.com/maps?v=1.4.0&key=ff031e1227bc730e60d7d807012fa1fd"></script> 
<script src="//webapi.amap.com/ui/1.0/main.js?v=1.0.11"></script>


<title>电子围栏</title>
</head>

<style>
	html,body{padding:0;margin:0;z-index:0;overflow:hidden;}
	#head{height:25px;line-height:25px;margin-right:5px;}
	#log{display:none;width:250px;height:250px;padding:30px 40px;background-color: #F3F5F6;position: absolute;top:450px;left:100px;z-index:100;}
	#close{position:absolute;top:4px;right:0px;}
    #close span{padding:3px 10px;background-color: #999;font-size:20px;color:white;cursor:pointer;}
    .error{display:inline-block;color:red;font-size:5px;}
    #login-exit{display:inline-block;float:right;}
    #login{border-right:1px #aaa solid;padding-right:5px;}
    #login,#exit,#gettrail{cursor:pointer;}
    #gettrail{margin-left:5px;display:none;}
    #session{display:none;}
</style>

<body>
<div id="head">
	<a href="javascript:" id="gettrail">显示/隐藏历史轨迹</a>
	<a href="javascript:" id="session" ><%=session.getAttribute("username")%></a>
	<div id="login-exit">
		<a href="javascript:" id="login">登录</a>
		<a href="javascript:" id="exit">退出</a>
	</div>
</div>
<div id="log">
	<div style="text-align:center;">
		<h3 >请登录</h3>
	</div>
    <div class="form-group">
        <label for="username">用户名*</label>
        <input type="text" class="form-control"  name="username"
               id="username" placeholder="用户名" required />
        <span id="user" class="error"></span>
    </div>
    <div class="form-group">
        <label for="password">密码*</label>
        <input type="password" class="form-control" name="password"
               id="password" placeholder="密码" required />
        <span id="psword" class="error"></span>
    </div>
    <div style="text-align:center;">
    	<button type="button" class="btn btn-primary login-button" style="width:70px;height:40px;" />登录</button>
    </div>
    <div id="close" >
        <span>关闭</span>
    </div>
</div>
<div id="container"></div>
</body>
</html>