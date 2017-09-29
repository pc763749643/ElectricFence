function getpoint(map,marker){
	$.ajax({
		type: "post",
		url: "PageLoad",
		success:function(data){
			if(data=="no"){
				alert("请登录！");
			}
			else{
				var str = eval("(" + data + ")");
    			x = str.lng;
    			y = str.lat;
    			marker.setPosition([x,y]);
        	    marker.setMap(map);
        	    marker.setAnimation("AMAP_ANIMATION_BOUNCE");
        	    if(str.result=="out"){
        	    	alert("目标在围栏外！");
        	    }
			}
		}
	});
}



$(function() {
	var map = new AMap.Map('container',{
		   zoom: 17,
		   center: [113.1491390000,29.3286780000]
		});
	var x = 113.1491390000,y = 29.3286780000;
	var marker = new AMap.Marker({
        icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
        position:[113.144931,29.327913]
    });
    
	var polygonArr = [
				[113.143601,29.332122],
		        [113.150103,29.332983],
		        [113.151154,29.328269],
		        [113.144931,29.327913],
				]
	var  polygon = new AMap.Polygon({
        path: polygonArr,//设置多边形边界路径
        strokeColor: "#F33", //线颜色
        strokeOpacity: 0.2, //线透明度
        strokeWeight: 3,    //线宽
        fillColor: "#ee2200", //填充色
        fillOpacity: 0.35//填充透明度
    });
    polygon.setMap(map);
    
    getpoint(map,marker);
    
    $("#container").css({"height":$(window).height(),"width":$(window).width()});
    $("#log").css({"top":($(window).height()-310)/2,"left":($(window).width()-330)/2});
    
    if($("#session").text()!="null"){
    	$("#login").text($("#session").text());
		$("#login").css({"disabled":"true","color":"#aaa"});
		$("#gettrail").css({"display":"inline"});
    }
    
    $("#login").click(function(){
    	$("#username").val("");
    	$("#password").val("");
    	$("#log").css({"display":"block"});
    });
    
    $("#close").click(function(){
    	$("#log").css({"display":"none"});
    });
    
    $("#username").blur(function(){
    	$("#user").text("");
        var name = $('#username').val();
        if (name.length > 20 || name.length < 1)
            $("#user").text("用户名长度在1-20之间！");
    });
    
    $("#password").blur(function(){
    	$("#psword").text("");
        var name = $('#password').val();
        if (name.length > 12 || name.length < 6)
            $("#psword").text("密码长度在6-12之间！");
    });
    
    $("#log button").click(function(){
    		$.ajax({
        		type: "post",
        		url: "Login",
        		data: "username="+$("#username").val()+"&password="+$("#password").val(),
        		success:function(data){
        			if(data=="no"){
        				alert("用户名或密码不正确！");
        			}
        			else{
        				getpoint(map,marker);
        				$("#login").text($("#username").val());
        	    		$("#login").css({"disabled":"true","color":"#aaa"});
        	    		$("#log").css({"display":"none"});
        	    		$("#gettrail").css({"display":"inline"});
        			}
        		}
        	});
    });
    
    var trail;
    var pathSimplifierIns=null;
    var navg1=null;
    AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(PathSimplifier, $) {

        if (!PathSimplifier.supportCanvas) {
            alert('当前环境不支持 Canvas！');
            return;
        }
        pathSimplifierIns = new PathSimplifier({
            zIndex: 100,
            autoSetFitView:false,
            map: map, //所属的地图实例

            getPath: function(pathData, pathIndex) {

                return pathData.path;
            },
            getHoverTitle: function(pathData, pathIndex, pointIndex) {

                if (pointIndex >= 0) {
                    //point 
                    return pathData.name + '，点：' + pointIndex + '/' + pathData.path.length;
                }

                return pathData.name + '，点数量' + pathData.path.length;
            },
            renderOptions: {

                renderAllPointsIfNumberBelow: 100 //绘制路线节点，如不需要可设置为-1
            }
        });
        window.pathSimplifierIns = pathSimplifierIns;
        pathSimplifierIns.hide();
        
    
	    $("#gettrail").click(function(){
	    	if(pathSimplifierIns.isHidden())
	    	{
	    		$.ajax({
	        		type: "post",
	        		url: "GetTrail",
	        		success:function(data){
	        			if(data=="none"){
	        				alert("没有历史轨迹数据！");
	        			}
	        			else{
	        		      //设置数据
	        		        trail = new Array();
	        		        var str = eval("(" + data + ")");
	            			for(var e in str){
	            				x=str[e].lng;
	            				y=str[e].lat;
	            				trail.push([x,y]);
	            			}
	        		        pathSimplifierIns.setData([{
	        		            name: '运动轨迹',
	        		            path: trail
	        		        }]);

	        		        //对第一条线路（即索引 0）创建一个巡航器
	        		        navg1 = pathSimplifierIns.createPathNavigator(0, {
	        		            loop: true, //循环播放
	        		            speed: 500 //巡航速度，单位千米/小时
	        		        });
	        		        navg1.start();
	        	    		pathSimplifierIns.show();
	        	    		pathSimplifierIns.setFitView();
	        			}
	        		}
	        	});
	    	}
	    	else
	    	{
	    		navg1.stop();
	    		pathSimplifierIns.hide();
	    	}
	    });
    });
    
    $("#exit").click(function(){
		$("#login").text("登录");
		$("#login").css({"disabled":"false","color":"#08c"});
		$("#gettrail").css({"display":"none"});
		if(navg1!=null){
			navg1.stop();
			pathSimplifierIns.hide();
		}
		map.remove(marker);
		$.ajax({
			type: "get",
			url: "Exit",
			success:function(data){}
		});
    });
});