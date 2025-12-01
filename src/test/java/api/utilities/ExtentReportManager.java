package api.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentReportManager implements ITestListener {
	
	public String report_name;
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent;
	public ExtentTest test;
	private static boolean systemInfoSet = false;
	
	
	
	 public void onStart(ITestContext context) {
		 
		 
		 
		// Report name with timestamp
			SimpleDateFormat date_format =new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
			Date date = new Date();
			String currentdatetimestamp =date_format.format(date);
			report_name = "Test-Report-"+currentdatetimestamp+".html";
			
			// SparkReporter object with path
			sparkReporter=new ExtentSparkReporter(".\\reports\\"+report_name);
			
			
			sparkReporter.config().setDocumentTitle("Opencart Automation Project"); //Title of Report
			sparkReporter.config().setReportName("Opencart Functional Testing"); // Name of the Report
			sparkReporter.config().setTheme(Theme.DARK);
			
			
			extent = new ExtentReports();
			extent.attachReporter(sparkReporter);
			
			if (!systemInfoSet) {
				
			extent.setSystemInfo("Application", "Opencart");
			extent.setSystemInfo("User Name", System.getProperty("user.name"));
			extent.setSystemInfo("Environment", "QA");
			
			String os=context.getCurrentXmlTest().getParameter("os"); // from xml
			extent.setSystemInfo("Operating System", os);
			
			systemInfoSet = true;
			
			}
			
			String browser=context.getCurrentXmlTest().getParameter("browser"); // from xml
			extent.setSystemInfo("Browser", browser);
			
			
			List<String> includedGroups =context.getCurrentXmlTest().getIncludedGroups();
			if(!includedGroups.isEmpty()) {
				
				extent.setSystemInfo("Groups", includedGroups.toString());
				
			}
			
		    
		  }
	 
	 
	 
	 public void onTestSuccess(ITestResult result) {
		    
		 test =extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.PASS, result.getName()+" got successfully executed");
		 
		  }


	
	 public void onTestFailure(ITestResult result) {
		 
		 
		 test =extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.FAIL, result.getName()+" got failed");
		 test.log(Status.INFO, result.getThrowable().getMessage());
		 
		 
		
		 
		 
		    
		  }
	 
	 
	 
	 public void onTestSkipped(ITestResult result) {
		 
		 
		 test =extent.createTest(result.getTestClass().getName());
		 test.assignCategory(result.getMethod().getGroups());
		 
		 test.log(Status.SKIP, result.getName()+" got skipped");
		 test.log(Status.INFO, result.getThrowable().getMessage());
		  }

	 
	 public void onFinish(ITestContext context) {
		 
		 	extent.flush(); //writes the report
		 	
		 	String pathOfExtentReport = System.getProperty("user.dir")+"\\reports\\"+report_name;
		 	File extentReport = new File(pathOfExtentReport);
		 	
		 	
		    try {
		    	Desktop.getDesktop().browse(extentReport.toURI()); // open the report automatically
		    	
		    }
		    catch(IOException e) {
		    	
		    	e.printStackTrace();
		    	
		    }
		    
		    
		    
		    //create the email message
		    
		    /*
		    
		    try {
		    	
		    	@SuppressWarnings("deprecation")
				URL url = new URL("file:///"+System.getProperty("user.dir")+"\\reports\\"+report_name);
		    	
		    	ImageHtmlEmail email = new ImageHtmlEmail();
		    	email.setDataSourceResolver(new DataSourceUrlResolver(url));
		    	email.setHostName("smtp.googlemail.com");
		    	email.setSmtpPort(465);
		    	email.setAuthenticator(new DefaultAuthenticator("qatsc123@gmail.com","mvlm siti fibl oisp"));
		    	email.setSSLOnConnect(true);
		    	email.setFrom("qatsc123@gmail.com"); // sender
		    	email.setSubject("Test Results");
		    	email.setMsg("Please Find Attached Report...");
		    	email.addTo("12345georgegeorge@gmail.com"); //receiver
		    	email.attach(url,"extent report","please check report..."); // attachment with name & msg
		    	email.send();
		    	
		    	
		    }
		    catch(Exception e) {
		    	
		    	e.printStackTrace();
		    	
		    }
		    
		    
		    */
		    
		    
		    
		    
		 	
		  }

	 
	 


	 
	 

}
