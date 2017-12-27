package com.example.batch.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class BatchServlet
 */
@WebServlet("/BatchServlet")
public class BatchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
    public BatchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
        
		try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Simple Batch Job</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Java EE Batch Processor</h1>");
            
            JobOperator jo = BatchRuntime.getJobOperator();
            long jid = jo.start("sendNotification", new Properties());
            
            out.println("sendNotification Batch Job submitted: " + jid + "<br>");
            out.println("</body>");
            out.println("</html>");
            
        } catch (JobStartException | JobSecurityException ex) {
            Logger.getLogger(BatchServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
