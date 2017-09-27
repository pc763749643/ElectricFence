package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.Point;
import lg0v0.GetInfo;
import lg0v0.IsPointInPolygon;

/**
 * Servlet implementation class map
 */
@WebServlet(name = "map" , urlPatterns = {"/map"})
public class Map extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Map() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");
		String username="861842030027687",password="123456",grant_type="password",scope="single";
		username = request.getParameter("username");
		password = request.getParameter("password");
		String result;
		String Token;
		GetInfo getinfo = new GetInfo();
		
		if(type.equals("login")){
			Token = getinfo.getToken(username, password, grant_type, scope);
			Point point = getinfo.getPoint(username, Token);
			IsPointInPolygon is = new IsPointInPolygon();
			result = "{'result':'"+is.run(point)+"','lng':'"+point.lng+"','lat':'"+point.lat+"'}";
			response.getWriter().print(result);
		}
		else {
			Token = getinfo.getToken(username, password, grant_type, scope);
			result = getinfo.getTrail(username, Token, "2017-08-25", "2017-09-25");
			response.getWriter().print(result);
		}
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
