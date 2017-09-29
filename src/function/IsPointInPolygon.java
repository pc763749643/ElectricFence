package function;

import info.Point;

public class IsPointInPolygon {
	
	
	public String run(Point point) {
		boolean flag=false;
		Point []  points = new Point[4];
		points[0] = new Point(113.143601,29.332122);
		points[1] = new Point(113.150103,29.332983);
		points[2] = new Point(113.151154,29.328269);
		points[3] = new Point(113.144931,29.327913);
		
		for(int i = 0, l = points.length, j = l - 1; i < l; j = i, i++) {
		      double sx = points[i].lng,
		          sy = points[i].lat,
		          tx =points[j].lng,
		          ty = points[j].lat;

		      // 点与多边形顶点重合
		      if((sx == point.lng && sy == point.lat) || (tx == point.lng && ty == point.lat)) {
		        return "on";
		      }

		      // 判断线段两端点是否在射线两侧
		      if((sy < point.lat && ty >= point.lat) || (sy >= point.lat && ty < point.lat)) {
		        // 线段上与射线 Y 坐标相同的点的 X 坐标
		        double x = sx + (point.lat - sy) * (tx - sx) / (ty - sy);

		        // 点在多边形的边上
		        if(x == point.lng) {
		          return "on";
		        }

		        // 射线穿过多边形的边界
		        if(x > point.lng) {
		          flag = !flag;
		        }
		      }
		    }

		    // 射线穿过多边形边界的次数为奇数时点在多边形内
		    return flag ? "in" : "out";
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Points point = new Points(113.146142,29.336);
		
		isPointInPolygon test = new isPointInPolygon();
		System.out.println(test.run(point));

	}*/

}
