package it.ifoa.servlets.prima;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Form/*")
public class Form extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(request.getContextPath() +"/" + "Auth/");
			return;
		}
		
		String nome = (String) session.getAttribute("nome");
		if(nome == null) {
			response.sendRedirect(request.getContextPath() +"/" + "Auth/");
			return;
		}
		

		

		PrintWriter writer = response.getWriter();

		writer.append("<html><head></head><body> " + nome)
		.append("<form method=\"POST\">")
		.append("Genere: <input type=\"text\" name=\"genere\"/>")
		.append("Indirizzo: <input type=\"text\" name=\"indirizzo\"/>")
		.append("<input type=\"submit\" name=\"go\"/>")
		.append("</form>");

		//printInfo(request, writer);

		writer.append("</body></html>");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(false);
		if(session == null) {
			response.sendRedirect(request.getServletContext() +"/" + "/Auth/");
			return;
		}
		
		String nome = (String) session.getAttribute("nome");
		if(nome == null) {
			response.sendRedirect(request.getServletContext() +"/" + "/Auth/");
			return;
		}
		

	
		
		String genere = request.getParameter("genere");
		String indirizzo = request.getParameter("indirizzo");

		//		if(indirizzo == null || indirizzo.length() < 11) {
		//			//azione
		//		}

		String confirm = request.getParameter("confirm");

		
		if(confirm != null) { //siamo nella conferma
			PrintWriter writer = response.getWriter();

			writer.append("<html><head></head><body> " + nome);
			writer.append("Salvato !! genere:"  + genere + " indirizzo: " +indirizzo );
			writer.append("</body></html>");
			
			
		} else {
			PrintWriter writer = response.getWriter();
			writer.append("<html><head></head><body>")
			.append("<form method=\"POST\">")
			.append("Genere: " + genere  +"<input type=\"hidden\" name=\"genere\" value=\""+ genere +"\"/>")
			.append("<br/>")
			.append("Indirizzo: "+ indirizzo +"<input type=\"hidden\" name=\"indirizzo\" value=\""+ indirizzo+"\"/>")
			.append("<input type=\"submit\" name=\"confirm\" value=\"confirm\"/>")
			.append("</form>");

			//printInfo(request, writer);
			writer.append("</body></html>");

		}		

	}



	private static void printInfo(HttpServletRequest request, PrintWriter writer) {

		writer.append("<pre>")
		.append("ContextPath: " + request.getContextPath())
		.append("\r\nMethod: " + request.getMethod())
		.append("\r\nPathInfo: " + request.getPathInfo())
		.append("\r\nQueryString: " + request.getQueryString())
		.append("\r\nRemoteHost: " + request.getRemoteHost())
		.append("\r\nHEADERS ");

		Enumeration<String> hs = request.getHeaderNames();

		while( hs.hasMoreElements() ) {
			String hn = hs.nextElement();
			writer.append("\r\n"+ hn + ": " + request.getHeader(hn) );
		}	
		writer.append("\r\nPARAMS ");

		Enumeration<String> ps = request.getParameterNames();	
		while( ps.hasMoreElements() ) {
			String pn = ps.nextElement();
			writer.append("\r\n"+ pn + ": " + request.getParameter(pn) );
		}

		writer.append("</pre>");

	}

}
