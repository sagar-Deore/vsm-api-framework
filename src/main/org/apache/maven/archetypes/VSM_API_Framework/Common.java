package org.apache.maven.archetypes.VSM_API_Framework;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Hello world!
 *
 */
public class Common 
{
	public static HttpClient httpclient;
	public static HttpContext context;
	public static String url="";
	public static String teacherUserName="";
	public static String teacherPassword="";
	public static String teacherUserType="";
	public static String studentUserName="";
	public static String studentPassword="";
	public static String studentUserType="";
	public static String value="";
	public static String dateTimeValue="";
	
	public static HashMap<String,Object> responseActual=new HashMap<String,Object>();
	public static HashMap<String,Object> responseTemplate=new HashMap<String,Object>();
	
	public enum API{session,sections,collections,books,student};
	
	public static final Logger logger =
	        Logger.getLogger(Common.class.getName());
	static FileHandler fh ;
	
	
	String[] strArr=new String[1000];
	
	
	@BeforeClass
	public static void init()
	{	
		try { 
				SimpleDateFormat format = new SimpleDateFormat("d-M-y_HHmmss");	
				dateTimeValue=format.format(Calendar.getInstance().getTime());
				fh = new FileHandler(System.getProperty("user.dir")+"\\logs\\LogFile_"
	                    + dateTimeValue + ".log");
				logger.addHandler(fh);
		        SimpleFormatter formatter = new SimpleFormatter();  
		        fh.setFormatter(formatter);  
		        
				assignData();
				httpclient = HttpClientBuilder.create().build();
				context = new BasicHttpContext();
								
		 } catch (SecurityException e) {  
			 	logger.info(e.getMessage());
		        e.printStackTrace();  
		 } catch (IOException e) {  
			 	logger.info(e.getMessage());
		        e.printStackTrace();  
		 }  
	}
	
	@AfterClass
	public static void end()
	{
		fnDisplayReasult();
	}
	
	private static void fnDisplayReasult() {
		
		logger.info("start fnDisplayReasult()");
		
		BufferedReader brString = null;
		try {				
			String testSuiteName = "";
			int testTotalCount = 0;
			int testFailCount = 0;
			String overallLog = "";
			String testCaseName = "";
			String testResult = "";
			String testLog = "";
			final File templateFile= new File(System.getProperty("user.dir")+"\\resources\\test-results.htm");
			
			String htmlReportTemplate=FileUtils.readFileToString(templateFile);
			
			htmlReportTemplate = htmlReportTemplate.replace(
					"{{DateAndTimeHere}}",
					dateTimeValue);
			
			File txtFile= new File(System.getProperty("user.dir")+"\\logs\\LogFile_"+dateTimeValue+".log");
			
			brString = new BufferedReader(new FileReader(txtFile));
			String line = null;
			
			boolean flag = true;
			boolean testFlag=false;
			while ((line = brString.readLine()) != null) {				
				if (line.contains("Test Suite to execute")) {
					testSuiteName = (line.split("-")[1]).split(" ")[1].trim();
				}
				if (line.contains("Test Execution Started")) {
					flag = false;
					testFlag=true;
					
					if (testResult != "") {
						testResult = testResult + ";";
					}
					if (testLog != "") {
						testLog = testLog + ";";
					}
					if (testCaseName != "") {						
						testCaseName = testCaseName
								+ ";"
								+ (line.split("-")[1]).trim();
					} else {						
						testCaseName = (line.split("-")[1]).trim();
					}
					testTotalCount++;
				}				
				if (flag) {
					overallLog = overallLog + "\n" + line.toString();				
				} else {
					if(testFlag)
					{
						if (line.contains("Test Execution End")) {					
							testLog = testLog + line.toString();
							flag = true;	
							testResult=testResult+"Pass";
						} else {
							if(line.contains("Error"))
							{
								testResult=testResult+"Fail";
								testLog = testLog + line.toString();
								testFlag=false;
								testFailCount++;
							}
							else
							{
								testLog = testLog + line.toString();
							}
						}
					}
					else
					{
						if (line.contains("Test Execution End")) {					
							testLog = testLog + line.toString();
							flag = true;											
						}
						else
						{
							testLog = testLog + line.toString();
						}
					}
				}
				
			}
			
			htmlReportTemplate = htmlReportTemplate.replace(
					"{{TestSuiteNameHere}}",
					testSuiteName);
			
			htmlReportTemplate = htmlReportTemplate.replace(
					"{{TotalNoOfTestCases}}",
					""+testTotalCount);
			
			int testPassCount=testTotalCount-testFailCount;
			htmlReportTemplate = htmlReportTemplate.replace(
					"{{TotalPassTestCount}}",
					""+testPassCount);
			
			htmlReportTemplate = htmlReportTemplate.replace(
					"{{TotalFailTestCount}}",
					""+testFailCount);
			
			if(testResult.contains("Fail"))
			{
				htmlReportTemplate = htmlReportTemplate.replace(
						"{{OverallResult}}",
						"<td class=Fail>Fail</td>\n");
			}
			else
			{
				htmlReportTemplate = htmlReportTemplate.replace(
						"{{OverallResult}}",
						"<td class=Pass>Pass</td>\n");
			}
			
			String[] arrTestName = testCaseName.split(";");
			String[] arrTestResult = testResult.split(";");			
			StringBuilder sbTestResult = new StringBuilder();
			
			for (int k = 0; k < arrTestName.length; k++) {
				sbTestResult.append("<tr>\n");
				sbTestResult.append("<td>" + arrTestName[k] + "</td>\n");
				if (arrTestResult[k].contains("Fail")) {
					sbTestResult.append("<td class=Fail>Fail</td>\n");					
				} else {
					sbTestResult.append("<td class=Pass>Pass</td>\n");
				}				
				sbTestResult.append("<tr>\n");
			}

			htmlReportTemplate = htmlReportTemplate.replace(
					"{{TestResultListHere}}",
					sbTestResult);
			
			htmlReportTemplate = htmlReportTemplate.replace(
					"{{LogLink}}", String.format("see <a href=\"file://%s\">Log File</a><br/>\n", txtFile));
			
			
			File outHtmlFileNew = new File(System.getProperty("user.dir")+"\\test_result"+"\\testResult_"+dateTimeValue+".html");
			if (outHtmlFileNew.exists()) {
				outHtmlFileNew.delete();
				outHtmlFileNew = new File(System.getProperty("user.dir")+"\\test_result"+"\\testResult_"+dateTimeValue+".html");
			}
			FileUtils.write(outHtmlFileNew, htmlReportTemplate);
				logger.info("end fnDisplayReasult()");
		} catch (Exception e) {	
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				brString.close();
				final File resultsFile = new File(System.getProperty("user.dir")+"\\test_result"+"\\testResult_"+dateTimeValue+".html");
				
				Desktop desktop = Desktop.getDesktop();
		    	try {
					java.net.URI uri = new java.net.URI(resultsFile.getAbsolutePath().replaceAll("\\\\", "/"));
					desktop.browse( uri );
				} catch (Exception e) {
					logger.log(Level.INFO, e.getMessage());
				}
				
			} catch (IOException e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	/*
	 * Function is used to initialize variables by reading inputdata.txt file.
	 */
	public static void assignData()
	{
		BufferedReader brString = null;
		try {
				logger.info("start assignData()");
				File txtFile= new File(System.getProperty("user.dir")+"\\inputdata.txt");
				
				brString = new BufferedReader(new FileReader(txtFile));
				String line = null;
				
				while ((line = brString.readLine()) != null) 
				{
					String str[]=line.split(":");
					switch(str[0].trim())
					{
					case "URL":
						url=str[1].trim()+":"+(str[2].trim().split(",")[0].trim());
						break;
					case "TeacherUserName":
						teacherUserName=str[1].trim().split(",")[0].trim();
						break;
					case "TeacherPassword":
						teacherPassword=str[1].trim().split(",")[0].trim();
						break;
					case "TeacherUserType":
						teacherUserType=str[1].trim().split(",")[0].trim();
						break;
					case "StudentUserName":
						studentUserName=str[1].trim().split(",")[0].trim();
						break;
					case "StudentPassword":
						studentPassword=str[1].trim().split(",")[0].trim();
						break;
					case "StudentUserType":
						studentUserType=str[1].trim().split(",")[0].trim();
						break;
					default:
						break;
					}										
				}
				logger.info("end assignData()");
		} catch (Exception e) {	
			logger.info(e.getMessage());
			e.printStackTrace();
		}
		finally
		{
			try {
				brString.close();
			} catch (IOException e) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
	} 
	
	/*
	 * This function is used to format input json, replace all variables with respective value from inputdata.txt
	 * Input- Input json file name
	 * Output- json file in the form of String after replacement of variables with value.
	 */
	public static String inputJson(String jsonFileName)
	{		
		try 
		{		
			logger.info("start inputJson function");
			FileReader reader = new FileReader(System.getProperty("user.dir")+"\\RequestFiles\\"+jsonFileName);						
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);			
			String requestJson=jsonObject.toJSONString();
			
			boolean flag=false;
			String str1="";
			for (int i = 0; i < requestJson.length(); i++){
				if((requestJson.charAt(i))=='<')
				{
					flag=true;
				}
				if(flag)
				{					
					str1=str1+requestJson.charAt(i);
				}
				if((requestJson.charAt(i))=='>')
				{
					String newJson="";
					flag=false;
					if(str1.contains("TeacherUserName"))
						newJson=requestJson.replace(str1, teacherUserName);
					else if(str1.contains("TeacherPassword"))
						newJson=requestJson.replace(str1, teacherPassword);
					else if(str1.contains("TeacherUserType"))
						newJson=requestJson.replace(str1, teacherUserType);
					else if(str1.contains("StudentUserName"))
						newJson=requestJson.replace(str1, studentUserName);
					else if(str1.contains("StudentPassword"))
						newJson=requestJson.replace(str1, studentPassword);
					else if(str1.contains("StudentUserType"))
						newJson=requestJson.replace(str1, studentUserType);
					str1="";
					requestJson=newJson;
					i=0;
				}
			}	
			logger.info("end inputJson function");
			return requestJson;
		} catch (Exception e) {	
			logger.info(e.getMessage());
			e.printStackTrace();
			return null;
		}			
	}
	
	/*
	 * This function is used to compare actual and expected response. 
	 * Input - Actual response in the format of String. 
	 * 		   Expected response in the format of String.
	 *         Boolean flag which sets flag to compare whole json or not [implementation is in progress]		   
	 * Return- Boolean value as result of comparison of two json files
	 */
	public static boolean fnCompaireJsonResponse(String actualResponse,String strResponseTemplate,boolean flagToCompair)
	{
		boolean flag=false;
		try {
			logger.info("Start fnCompaireJsonResponse function");
			if(flagToCompair)
			{					
				FileReader readerTemplateResponse = new FileReader(System.getProperty("user.dir")+"\\ResponseFile\\"+strResponseTemplate);								
				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObjResponseTemplate = (JSONObject) jsonParser.parse(readerTemplateResponse);	
				
				JSONObject jsonObjActualResponse  = (JSONObject) jsonParser.parse(actualResponse);	
				
				ObjectMapper mapper = new ObjectMapper();
				
				JsonNode tree1 = mapper.readTree(jsonObjActualResponse.toString());
				JsonNode tree2 = mapper.readTree(jsonObjResponseTemplate.toString());
								
				if(tree1.equals(tree2))	
				{
					flag=true;
					String str=formatJson(actualResponse);
					logger.info("Formated Actual Response:- \n");					
					logger.info(str);					
				}
				else
				{				
					String str=formatJson(actualResponse);
					logger.info("Formated Actual Response:- \n");				
					logger.info(str);
					
					FileWriter fileResponse = new FileWriter(System.getProperty("user.dir")+"\\ActualTestResponseFiles\\ActualResponse_"+strResponseTemplate);
			        
					fileResponse.write(str);
					fileResponse.flush();
					fileResponse.close();
					
					FileReader readerActualResponse = new FileReader(System.getProperty("user.dir")+"\\ActualTestResponseFiles\\ActualResponse_"+strResponseTemplate);	
					FileReader readerTResponse = new FileReader(System.getProperty("user.dir")+"\\ResponseFile\\"+strResponseTemplate);	
					
					flag=checkData(readerActualResponse,readerTResponse);
				}				
			}		
			else
			{
				flag=false;
			}
			logger.info("End fnCompaireJsonResponse function");
		} catch (Exception e) {	
			logger.info(e.getMessage());
			e.printStackTrace();			
		}		
		return flag;
	}
	
	/*
	 * This function is used to format actual response in the format of valid json.
	 * Input - Actual response in the format of String.   
	 * Return- Formated valid json in the form of String value.
	 */
	public static String formatJson(String json) {
		logger.info("Start formatJson function");
        int i           = 0;
            int il          = 0;      
            String newJson     = "";
            boolean inString    = false;
            char currentChar;

        for (i = 0, il = json.length(); i < il; i += 1) { 
            currentChar = json.charAt(i);

            switch (currentChar) {
            case '{': 
            case '[': 
                if (!inString) { 
                	if(json.charAt(i+1)==']')
                	{
                    newJson += currentChar;
                	}
                	else
                	{
                		newJson += currentChar + "\n";
                	}
                } else { 
                    newJson += currentChar; 
                }
                break; 
            case '}': 
            case ']': 
                if (!inString) { 
                	if(json.charAt(i-1)=='[')
                	{
                    newJson += currentChar;
                	}
                	else
                	{
                    newJson += "\n" + currentChar; 
                	}
                } else { 
                    newJson += currentChar; 
                } 
                break; 
            case ',': 
                if (!inString) { 
                    newJson += ",\n" ; 
                } else { 
                    newJson += currentChar; 
                } 
                break; 
            case ':': 
                if (!inString) { 
                    newJson += ": "; 
                } else { 
                    newJson += currentChar; 
                } 
                break; 
            case ' ':
            case '\n':
            case '\t':
                if (inString) {
                    newJson += currentChar;
                }
                break;
            case '"': 
                if (i > 0 && json.charAt(i - 1) != '\\') {
                    inString = !inString; 
                }
                newJson += currentChar; 
                break;
            default: 
                newJson += currentChar; 
                break;                    
            } 
        } 
        logger.info("End formatJson function");
        return newJson; 
    }
	
	/*
	 * This function is used to compare two json files
	 * Input - File Reader Instance of file which contains actual response
	 * 		   File Reader Instance of file which contains template response
	 * Return- Boolean value as result of comparison of two json files
	 */
	public static boolean checkData(FileReader readerActual,FileReader readerTemplate){ 
		logger.info("Start checkData function");
		boolean flag=true;
		
	    try {  	    
	        BufferedReader buffreaderA = new BufferedReader(readerActual);  
	        BufferedReader buffreaderT = new BufferedReader(readerTemplate);
	        String lineA = null;
	        String lineT=null;
	       
	        ArrayList<String> results = new ArrayList<String>();
	        ArrayList<String> errorMsg = new ArrayList<String>();
	        
	        while (((lineA = buffreaderA.readLine()) != null)&&((lineT = buffreaderT.readLine()) != null)) {	          
	        			if(lineA.contains("â")|| lineA.contains("â¢"))
	        			{
	        				if((lineT.split(":")[0].trim()).equalsIgnoreCase(lineA.split(":")[0].trim()))
	            			{	        					
	        					String strT=lineT.split(":")[1].trim();
	        					String strA=lineA.split(":")[1].trim();
	        					if(fncompaireSting(strT,strA)=="Fail")
	        					{	        						
	        						results.add("Fail");	        						
	        						errorMsg.add("Expected-"+lineT+" and Actual is-"+lineA);	        					
	        					}
	        					else
	        					{	        						
	        						results.add("Pass");	        						       					
	        					}	            			
	            			}
	            			else
	            			{	            				
	            				results.add("Fail");	            			
	            				errorMsg.add("Expected-"+lineT+" and Actual is-"+lineA);	            			
	            			}
	        			}
	        			else if(lineT.contains("$$*$$"))
	            		{
	            			if((lineT.split(":")[0].trim()).equalsIgnoreCase(lineA.split(":")[0].trim()))
	            			{	            				
	            				results.add("Pass");	            			
	            			}
	            			else
	            			{
	            				results.add("Fail");	            				
	            				errorMsg.add("Expected-"+lineT+" and Actual is-"+lineA);	            			
	            			}
	            		}
	            		else
	            		{
	            			if(lineA.trim().equalsIgnoreCase(lineT.trim()))
	            			{
	            				results.add("Pass");	            				
	            			}
	            			else
	            			{
	            				results.add("Fail");	            				
	            				errorMsg.add("Expected-"+lineT+" and Actual is-"+lineA);	            			
	            			}
	            		}	            	      
	        }
	        buffreaderA.close(); 
	        
	        for (int k=0; k<results.size(); k++)
	        {	        	
	        	if(results.get(k).equalsIgnoreCase("Fail"))
	        	{
	        		flag=false;	        		
	        	}
	        }
	        if(!flag)
	        {
	        	for(int l=0;l<errorMsg.size();l++)
        		{	   	        		
	        		logger.info("Error:"+errorMsg.get(l));        		
        		}
	        }
	        logger.info("End checkData function"); 
	    }   catch (IOException e) {
	    	logger.info(e.getMessage());
	    	e.printStackTrace();
	    }  
	    return flag;
	}  
	
	/*
	 * This function is used to compare two String
	 * Input - Two String value for comapare	 * 		   
	 * Return- String value as Pass/Fail
	 */
	public static String fncompaireSting(String strT,String strA)
	{
		logger.info("Start fncompaireSting function");
		String strResult="Pass";
		char[] charT  = strT.toLowerCase().toCharArray();
		char[] charA = strA.toLowerCase().toCharArray();

		int minLength = Math.min(charT.length, charA.length);

		for(int i = 0; i < minLength; i++)
		{
			String str="¢";
			if(charA[i]=='â' || charA[i]==str.charAt(0))
			{}
			else
			{
		        if (charT[i] != charA[i])
		        {		        
		        	strResult="Fail";    
		        }
			}
		}
		logger.info("End fncompaireSting function");
		return strResult;
	}	
}
