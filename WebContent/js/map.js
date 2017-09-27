$(function() {
	var map = new AMap.Map('container',{
		   zoom: 18,
		   center: [113.1491390000,29.3286780000]
		});
	var x,y;
	
    
    //setinterval(function(){
    	$.ajax({
    		type: "post",
    		url: "map",
    		dataType: "text",
    		data: "type=login",
    		success:function(data){
    			alert(data);
    			var str = eval("(" + data + ")");
    			x = str.lng;
    			y = str.lat;
    			var marker = new AMap.Marker({
    		        icon: "http://webapi.amap.com/theme/v1.3/markers/n/mark_b.png",
    		        position: [x,y]
    		    });
    		    marker.setMap(map);
    		}
    	});
    //},5000);
    
});



AMapUI.load(['ui/misc/PathSimplifier', 'lib/$'], function(PathSimplifier, $) {

    if (!PathSimplifier.supportCanvas) {
        alert('当前环境不支持 Canvas！');
        return;
    }

    var emptyLineStyle = {
        lineWidth: 0,
        fillStyle: null,
        strokeStyle: null,
        borderStyle: null
    };

    var pathSimplifierIns = new PathSimplifier({
        zIndex: 100,
        //autoSetFitView:false,
        map: map, //所属的地图实例

        getPath: function(pathData, pathIndex) {

            return pathData.path;
        },
        getHoverTitle: function(pathData, pathIndex, pointIndex) {

            return null;
        },
        renderOptions: {
            //将点、线相关的style全部置emptyLineStyle
            pathLineStyle: emptyLineStyle,
            pathLineSelectedStyle: emptyLineStyle,
            pathLineHoverStyle: emptyLineStyle,
            keyPointStyle: emptyLineStyle,
            startPointStyle: emptyLineStyle,
            endPointStyle: emptyLineStyle,
            keyPointHoverStyle: emptyLineStyle,
            keyPointOnSelectedPathLineStyle: emptyLineStyle
        }
    });

    window.pathSimplifierIns = pathSimplifierIns;

    pathSimplifierIns.setData([{
        path: [
            [113.1402980000,29.3293380000],
            [113.1409310000,29.3296380000],
            [113.1418750000,29.3300870000],
            [113.1434630000,29.3306100000],
            [113.1446860000,29.3313960000],
            [113.1458450000,29.3318260000],
            [113.1471110000,29.3324620000],
            [113.1481830000,29.3332480000],
            [113.1501360000,29.3331540000],
            [113.1514240000,29.3325370000],
            [113.1516170000,29.3312090000],
            [113.1520670000,29.3305540000]
        ]
    }]);

    //initRoutesContainer(d);

    function onload() {
        pathSimplifierIns.renderLater();
    }

    function onerror(e) {
        alert('图片加载失败！');
    }

    var navg1 = pathSimplifierIns.createPathNavigator(0, {
        loop: true,
        speed: 500,
        pathNavigatorStyle: {
            width: 24,
            height: 24,
            //使用图片
            content: PathSimplifier.Render.Canvas.getImageContent('./imgs/plane.png', onload, onerror),
            strokeStyle: null,
            fillStyle: null,
            //经过路径的样式
            pathLinePassedStyle: {
                lineWidth: 6,
                strokeStyle: 'black',
                dirArrowStyle: {
                    stepSpace: 15,
                    strokeStyle: 'red'
                }
            }
        }
    });

    navg1.start();

});