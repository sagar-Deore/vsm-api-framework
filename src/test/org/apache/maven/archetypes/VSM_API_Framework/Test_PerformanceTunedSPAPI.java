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
 * 3.Replace TeacherUserName value with "k@test.com"
 * 4.Replace TeacherPassword value with "pass@123"
 * 5.Replace StudentUserName value with "kk_1-kr2"
 * 6.Replace StudentPassword value with "pass"
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test_PerformanceTunedSPAPI extends Common{
	
	public Test_PerformanceTunedSPAPI()
	{
		logger.info("Test Suite to execute -"+Test_PerformanceTunedSPAPI.class);
	}
	
	/*
	 * TeacherUserName: k@test.com,
       TeacherPassword: pass@123,
	 */
	@Test
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
							
			Assert.assertTrue("TC01_Response_PTSPAPI - Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC01_Response_PTSPAPI.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC01_GetTeacherSession");
		}
	}
	
	@Test		
	public void TC02_VerifyAsTeacherClickOneBooksLink () throws Exception {	
		try
		{			
			logger.info("Test Execution Started- TC02_VerifyAsTeacherClickOneBooksLink");	
			
			HttpGet request=new HttpGet(url+API.sections+"/248/"+API.collections);
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC02_Response_PTSPAPI- Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC02_Response_PTSPAPI.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC02_VerifyAsTeacherClickOneBooksLink");
		}
	}
	
	@Test		
	public void TC03_VerifyAsTeacherClickOneBooksLinkAndClickOnTheCollecionAllkindsofPets () throws Exception {	
		try
		{			
			logger.info("Test Execution Started- TC03_VerifyAsTeacherClickOneBooksLinkAndClickOnTheCollecionAllkindsofPets");	
			
			HttpGet request=new HttpGet(url+API.sections+"/248/"+API.collections+"/218");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC03_Response_PTSPAPI - Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC03_Response_PTSPAPI.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC03_VerifyAsTeacherClickOneBooksLinkAndClickOnTheCollecionAllkindsofPets");
		}
	}
	
	@Test		
	public void TC04_VerifyAsTeacherClickOneBooksLinkAndClickOnTheCollecion_MyCollection_1 () throws Exception {	
		try
		{			
			logger.info("Test Execution Started- TC04_VerifyAsTeacherClickOneBooksLinkAndClickOnTheCollecion_MyCollection_1");
			
			HttpGet request=new HttpGet(url+API.sections+"/248/"+API.collections+"/1371");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC04_Response_PTSPAPI- Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC04_Response_PTSPAPI.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC04_VerifyAsTeacherClickOneBooksLinkAndClickOnTheCollecion_MyCollection_1");
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
		
			Assert.assertTrue("Logout_Response- Not valid json response", fnCompaireJsonResponse(strActualResponse,"Logout_Response.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC05_TeacherLogout");
		}
	}
	
	@Test
	/*
	 * StudentUserName: kk_1-kr2,
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
			
			Assert.assertTrue("TC06_Response_PTSPAPI - Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC06_Response_PTSPAPI.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC06_GetStudentSession");
		}
	}
	
	@Test		
	public void TC07_VerifyAsStudentClickOnTheCollectionNamed_HelpingOut () throws Exception {
		try
		{			
			logger.info("Test Execution Started- TC07_VerifyAsStudentClickOnTheCollectionNamed_HelpingOut");	
			
			HttpGet request=new HttpGet(url+API.sections+"/248/"+API.collections+"/222?orderby=title");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC07_Response_PTSPAPI- Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC07_Response_PTSPAPI.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC07_VerifyAsStudentClickOnTheCollectionNamed_HelpingOut");
		}
	}
	
	@Test		
	public void TC08_VerifyAsStudentClickOnTheCollectionNamed_MyCollection_1 () throws Exception {	
		try
		{			
			logger.info("Test Execution Started- TC08_VerifyAsStudentClickOnTheCollectionNamed_MyCollection_1");	
			
			HttpGet request=new HttpGet(url+API.sections+"/248/"+API.collections+"/1371?orderby=title");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("TC08_Response_PTSPAPI- Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC08_Response_PTSPAPI.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC08_VerifyAsStudentClickOnTheCollectionNamed_MyCollection_1");
		}
	}
	
	@Test		
	public void TC09_StudentLogout() throws Exception {	
		try
		{			
			logger.info("Test Execution Started- TC09_StudentLogout");
			
			HttpDelete request=new HttpDelete(url+API.session);
					
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Logout_Response - Not valid json response", fnCompaireJsonResponse(strActualResponse,"Logout_Response.json",true));	
		}
		finally
		{
			logger.info("Test Execution End- TC09_StudentLogout");
		}
	}

}
