package org.apache.maven.archetypes.VSM_API_Framework;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/*
 * Pre-requisites to execute below test cases-
 * 1.Open inputdata.txt file from VSM_API_Framework->inputdata.txt
 * 2.Replace URL value with your setup details.
 * 3.Replace TeacherUserName value with "k@test1.com"
 * 4.Replace TeacherPassword value with "pass@123"
 * 5.Replace StudentUserName value with "s_1-kr4"
 * 6.Replace StudentPassword value with "pass"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_PerformanceTunedEbooksAPI extends Common{
	
	public Test_PerformanceTunedEbooksAPI()
	{
		logger.info("Test Suite to execute -"+Test_PerformanceTunedEbooksAPI.class);
	}

	@Test
	/*
	 * TeacherUserName: k@test1.com,
       TeacherPassword: pass@123,
	 */
	public void TC01_GetTeacherSession() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC01_GetTeacherSession");			
			
			HttpPost request=new HttpPost(url+API.session);
	
			request.addHeader("content-type", "application/json");
					
			String strJson=inputJson("TeacherLoginTestRequest.json");
			
			StringEntity params =new StringEntity(strJson);
			request.setEntity(params);
			
			HttpResponse actualResponse = httpclient.execute(request, context);
			
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC01_Response_PTEbookAPI - Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC01_Response_PTEbookAPI.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC01_GetTeacherSession");
		}
	}
	
	@Test		
	public void TC02_VerifyAsTeacherClickonebookstabClickonebookleftmenuClickonAll () throws Exception {	
		try
		{			
			logger.info("Test Execution Started- TC02_VerifyAsTeacherClickonebookstabClickonebookleftmenuClickonAll");	
		
			HttpGet request=new HttpGet(url+API.sections+"/249/"+API.books+"?level=%5B400,1100%5D&load=Part&offset=0&orderby=title");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC02_Response_PTEbookAPI-Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC02_Response_PTEbookAPI.json",true));	
		}
		finally
		{
			logger.info("Test Execution End- TC02_VerifyAsTeacherClickonebookstabClickonebookleftmenuClickonAll");
		}
	}
	
	@Test		
	public void TC03_VerifyAsTeacherclickonebookstabClickonebookleftmenuClickonAllPutfilterparametershoranintextfieldbelowlabelNarrowMyResults() throws Exception {	
		try
		{				
			logger.info("Test Execution Started- TC03_VerifyAsTeacherclickonebookstabClickonebookleftmenuClickonAllPutfilterparametershoranintextfieldbelowlabelNarrowMyResults");
			
			HttpGet request=new HttpGet(url+API.sections+"/249/"+API.books+"?level=%5B400,1100%5D&load=Part&offset=0&orderby=title&search=hor+an&view=all");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC03_Response_PTEbookAPI- Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC03_Response_PTEbookAPI.json",true));	
		}
		finally
		{
			logger.info("Test Execution End- TC03_VerifyAsTeacherclickonebookstabClickonebookleftmenuClickonAllPutfilterparametershoranintextfieldbelowlabelNarrowMyResults");
		}
	}
	
	@Test		
	public void TC04_VerifyAsTeachercreatecollectionandclickcontinue() throws Exception {	
		try
		{				
			logger.info("Test Execution Started- TC04_VerifyAsTeachercreatecollectionandclickcontinue");	
			
			HttpGet request=new HttpGet(url+API.sections+"/249/"+API.books+"?level=%5B400,1100%5D&offset=0&orderby=title");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC04_Response_PTEbookAPI- Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC04_Response_PTEbookAPI.json",true));	
		}
		finally
		{
			logger.info("Test Execution End- TC04_VerifyAsTeachercreatecollectionandclickcontinue");
		}
	}
	
	@Test		
	public void TC05_TeacherLogout() throws Exception {	
		try
		{			
			logger.info("Test Execution Started- TC05_TeacherLogout");
			
			HttpDelete request=new HttpDelete(url+API.session);
					
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Logout_Response - Not valid json response", fnCompaireJsonResponse(strActualResponse,"Logout_Response.json",true));	
		}
		finally
		{
			logger.info("Test Execution End- TC05_TeacherLogout");
		}
	}
	
	@Test
	/*
	 * StudentUserName: s_1-kr4,
	   StudentPassword: pass,
	 */
	public void TC06_GetStudentSession() throws Exception {
		try
		{			
			logger.info("Test Execution Started- TC06_GetStudentSession");
			
			HttpPost request=new HttpPost(url+API.session);
	
			request.addHeader("content-type", "application/json");
					
			String strJson=inputJson("StudentLoginTestRequest.json");
			
			StringEntity params =new StringEntity(strJson);
			request.setEntity(params);
			
			HttpResponse actualResponse = httpclient.execute(request, context);
			
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
				
			Assert.assertTrue("TC06_Response_PTEbookAPI- Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC06_Response_PTEbookAPI.json",true));	
		}
		finally
		{
			logger.info("Test Execution End- TC06_GetStudentSession");
		}
	}
	
	@Test		
	public void TC07_VerifyAsStudentCheckBook() throws Exception {	
		try
		{			
			logger.info("Test Execution Started- TC07_VerifyAsStudentCheckBook");
			
			HttpGet request=new HttpGet(url+API.sections+"/250/"+API.books+"?limit=20&offset=0");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC07_Response_PTEbookAPI- Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC07_Response_PTEbookAPI.json",true));	
		}
		finally
		{
			logger.info("Test Execution End- TC07_VerifyAsStudentCheckBook");
		}
	}
	
	@Test		
	public void TC08_StudentLogout() throws Exception {	
		try
		{				
			logger.info("Test Execution Started- TC08_StudentLogout");	
			
			HttpDelete request=new HttpDelete(url+API.session);
					
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Logout_Response - Not valid json response", fnCompaireJsonResponse(strActualResponse,"Logout_Response.json",true));				
		}
		finally
		{
			logger.info("Test Execution End- TC08_StudentLogout");
		}
	}
		
}
