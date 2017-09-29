package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import function.GetInfo;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		final HttpSession session = request.getSession();
		String username,password,grant_type="password",scope="single";
		String token;
		GetInfo getinfo = new GetInfo();
		
		username = request.getParameter("username");
		password = request.getParameter("password");
		
		token = getinfo.getToken(username, password, grant_type, scope);
		
		if(token==null) {
			response.getWriter().print("no");
		}
		else {
			session.setAttribute("username", username);
			session.setAttribute("password", password);
			session.setAttribute("grant_type", grant_type);
			session.setAttribute("scope", scope);
			session.setAttribute("token", token);
			response.getWriter().print("yes");
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
