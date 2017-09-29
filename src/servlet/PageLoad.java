package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import function.GetInfo;
import function.IsPointInPolygon;
import info.Point;

/**
 * Servlet implementation class map
 */
@WebServlet(name = "PageLoad" , urlPatterns = {"/PageLoad"})
public class PageLoad extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageLoad() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final HttpSession session = request.getSession();
		String username,token;
		String result;
		GetInfo getinfo = new GetInfo();
		
		if(session.getAttribute("token")==null) {
			response.getWriter().print("no");
		}
		else {
			username = (String) session.getAttribute("username");
			token = (String) session.getAttribute("token");
			Point point = getinfo.getPoint(username, token);
			IsPointInPolygon is = new IsPointInPolygon();
			result = "{'result':'"+is.run(point)+"','lng':'"+point.lng+"','lat':'"+point.lat+"'}";
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
