package com.example.batch.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.batch.operations.JobOperator;
import javax.batch.operations.JobSecurityException;
import javax.batch.operations.JobStartException;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.batch.loan.service.BatchService;
import com.example.model.Job;

/**
 * Servlet implementation class BatchServlet
 */
@WebServlet("/BatchServlet")
public class BatchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private BatchService service;

    public BatchServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
        
		try (PrintWriter out = response.getWriter()) {
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Batch Job Sample</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Batch Processor</h1>");
            JobOperator jo = BatchRuntime.getJobOperator();
            Properties props = new Properties();
            long jid = 0;
            
            String type = request.getParameter("type");
            
            if(type!=null && type.equalsIgnoreCase("resume")) {
            	long executionId = Long.parseLong(request.getParameter("executionId"));
            	System.out.println("Resuming Job: "+executionId);
            	Job job = service.findJobByExecutionId(executionId);
            	JobExecution exec = jo.getJobExecution(executionId);
            	props = exec.getJobParameters();
            	props.setProperty("job-id", String.valueOf(job.getId()));
            	jid = jo.restart(executionId, props);
            	job.setExecutionId(jid);
            	service.updateJob(job);
            
            } else {
            	System.out.println("Starting Job ");
                props.setProperty("job-uuid", UUID.randomUUID().toString());
                jid = jo.start("advanceDelinquencyJob", props);
            }
            
            out.println("Delinquency Job submitted: " + jid + "<br>");
            out.println("<br><br>Check server.log for output, also look at \"advanceDelinquencyJob.xml\" for Job XML.");
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
