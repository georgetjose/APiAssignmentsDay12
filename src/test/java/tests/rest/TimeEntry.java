package tests.rest;

import java.io.File;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.response.Response;
import lib.rest.RESTAssuredBase;

public class TimeEntry extends RESTAssuredBase
{
	String timeEntryId;
	@BeforeTest
	public void setValues() {
		testCaseName = "Create a new Incident (REST)";
		testDescription = "Create a new Incident and Verify";
		nodes = "Incident";
		authors = "George";
		category = "API";
		dataFileName = "createTimeEntry";
		dataFileType = "JSON";
	}
	
	@Test(priority=1,dataProvider = "fetchData",enabled=false)
	public void createTimeEntry(File file) 
	{
		Response postTimeEntryResponse = postWithBodyAsFileAndUrl(file,"timetracking/business/3584465/time_entries");
		System.out.println("--->"+postTimeEntryResponse.getStatusCode());
		timeEntryId = postTimeEntryResponse.jsonPath().get("timeentry.id");
	}
	
	@Test(priority=2,enabled=true)
	public void getTimeEntries() 
	{
		Response getTimeEntriesResponse = get("timetracking/business/3584465/time_entries");
		System.out.println("--->"+getTimeEntriesResponse.getStatusCode());
		dataFileName = "updateTimeEntry";
		dataFileType = "JSON";
	}
	
	@Test(priority=3,dataProvider = "fetchData",enabled=false)
	public void updateTimeEntry(File file) 
	{
		Response putTimeEntryResponse = putWithBodyAsFileAndUrl(file,"timetracking/business/3584465/time_entries/"+timeEntryId);
		System.out.println("--->"+putTimeEntryResponse.getStatusCode());
	}
	
	@Test(priority=4,enabled=false)
	public void deleteTimeEntry() 
	{
		Response deleteTimeEntryResponse = delete("timetracking/business/3584465/time_entries"+timeEntryId);
		System.out.println("--->"+deleteTimeEntryResponse.getStatusCode());
	}
}
