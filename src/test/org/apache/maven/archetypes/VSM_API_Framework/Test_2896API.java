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

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
/*
 * Pre-requisites to execute below test cases-
 * 1.Open inputdata.txt file from VSM_API_Framework->inputdata.txt
 * 2.Replace URL value with your setup details.
 * 3.Replace TeacherUserName value with "qa@test.com"
 * 4.Replace TeacherPassword value with "password1"
 */
public class Test_2896API extends Common{
	
	public Test_2896API()
	{
		logger.info("Test Suite to execute -"+Test_2896API.class);
	}
	
	@Test
	/*
	 * TeacherUserName: qa@test.com,
       TeacherPassword: password1,
	 */
	public void TC01_VerifyLogin() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC01_VerifyLogin");
			
			HttpPost request=new HttpPost(url+API.session);
	
			request.addHeader("content-type", "application/json");
					
			String strJson=inputJson("TeacherLoginTestRequest.json");
			
			StringEntity params =new StringEntity(strJson);
			request.setEntity(params);
			
			HttpResponse actualResponse = httpclient.execute(request, context);
					
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC01_Response_2896API.json",true));
		}
		finally
		{
			logger.info("Test Execution End- TC01_VerifyLogin");
		}
	}
		
	@Test		
	public void TC02_VerifyCollectionWhenClickOnEbooksforAutoTestclasswithPreKCollections () throws Exception {	
		try
		{
			logger.info("Test Execution Started- TC02_VerifyCollectionWhenClickOnEbooksforAutoTestclasswithPreKCollections");
			
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections);
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
		
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC02_Response_2896API.json",true));
		}
		finally
		{
			logger.info("Test Execution End- TC02_VerifyCollectionWhenClickOnEbooksforAutoTestclasswithPreKCollections");
		}
	}
	
	@Test	
	public void TC03_VerifyCollectionWhenClickOnAll() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC03_VerifyCollectionWhenClickOnAll");
			
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections);
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC03_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC03_VerifyCollectionWhenClickOnAll");
		}
	}
	
	@Test	
	public void TC04_VerifyCollectionWhenClickOnShared() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC04_VerifyCollectionWhenClickOnShared");
		
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"?shared=1");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC04_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC04_VerifyCollectionWhenClickOnShared");
		}
	}
	
	@Test	
	public void TC05_VerifyCollectionWhenClickOnMyCollections() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC05_VerifyCollectionWhenClickOnMyCollections");
			
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"?type=custom");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC05_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC05_VerifyCollectionWhenClickOnMyCollections");
		}
	}
	
	@Test	
	public void TC06_VerifyCollectionWhenClickOncustomcollectionCC1sharedwithentireclass() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC06_VerifyCollectionWhenClickOncustomcollectionCC1sharedwithentireclass");
			
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/1321");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC06_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC06_VerifyCollectionWhenClickOncustomcollectionCC1sharedwithentireclass");
		}
	}
	
	@Test	
	public void TC07_VerifyCollectionWhenClickoncustomcollectionCC2sharedwithindividual() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC07_VerifyCollectionWhenClickoncustomcollectionCC2sharedwithindividual");
						
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/1322");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC07_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC07_VerifyCollectionWhenClickoncustomcollectionCC2sharedwithindividual");
		}
	}
	
	@Test	
	public void TC08_VerifyCollectionWhenClickonCC3notsharedwithanyonebuthavingbooks() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC08_VerifyCollectionWhenClickonCC3notsharedwithanyonebuthavingbooks");
		
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/1323");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC08_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC08_VerifyCollectionWhenClickonCC3notsharedwithanyonebuthavingbooks");
		}
	}
	
	@Test	
	public void TC09_VerifyCollectionWhenClickonCC4notsharedwithanyonehavingzerobooks() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC09_VerifyCollectionWhenClickonCC4notsharedwithanyonehavingzerobooks");
		
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/1324");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC09_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC09_VerifyCollectionWhenClickonCC4notsharedwithanyonehavingzerobooks");
		}
	}
	
	@Test	
	public void TC10_VerifyCollectionWhenClickonClickonCC5sharedwithgroup() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC10_VerifyCollectionWhenClickonClickonCC5sharedwithgroup");
			
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/1325");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC10_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC10_VerifyCollectionWhenClickonClickonCC5sharedwithgroup");
		}
	}
	
	@Test	
	public void TC11_VerifyCollectionWhenClickonpreexistingcollectionnotsharedwithanyone() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC11_VerifyCollectionWhenClickonpreexistingcollectionnotsharedwithanyone");
		
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/25");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC11_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC11_VerifyCollectionWhenClickonpreexistingcollectionnotsharedwithanyone");
		}
	}
	
	@Test	
	public void TC12_VerifyCollectionWhenClickonpreexistingcollectionsharedwithgroup() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC12_VerifyCollectionWhenClickonpreexistingcollectionsharedwithgroup");
			
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/29");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC12_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC12_VerifyCollectionWhenClickonpreexistingcollectionsharedwithgroup");
		}
	}

	@Test	
	public void TC13_VerifyCollectionWhenClickonpreexistingcollectionsharedwithindividual() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC13_VerifyCollectionWhenClickonpreexistingcollectionsharedwithindividual");
			
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/28");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC13_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC13_VerifyCollectionWhenClickonpreexistingcollectionsharedwithindividual");
		}
	}	
	
	@Test	
	public void TC14_VerifyCollectionWhenClickonpreexistingcollectionsharedwithentireclass() throws Exception {
		try
		{
			logger.info("Test Execution Started- TC14_VerifyCollectionWhenClickonpreexistingcollectionsharedwithentireclass");
			
			HttpGet request=new HttpGet(url+API.sections+"/208/"+API.collections+"/23");
			
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Not valid json response", fnCompaireJsonResponse(strActualResponse,"TC14_Response_2896API.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC14_VerifyCollectionWhenClickonpreexistingcollectionsharedwithentireclass");
		}
	}
	
	@Test		
	public void TC15_TeacherLogout() throws Exception {	
		try
		{
			logger.info("Test Execution Started- TC15_TeacherLogout");
			
			HttpDelete request=new HttpDelete(url+API.session);
					
			request.addHeader("content-type", "application/json");
					
			HttpResponse actualResponse = httpclient.execute(request, context);
				
			String strActualResponse = EntityUtils.toString(actualResponse.getEntity());
			
			Assert.assertTrue("Logout_Response - Not valid json response", fnCompaireJsonResponse(strActualResponse,"Logout_Response.json",true));			
		}
		finally
		{
			logger.info("Test Execution End- TC15_TeacherLogout");
		}
	}
}
