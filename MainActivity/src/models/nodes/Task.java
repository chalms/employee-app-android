package models.nodes;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;

import views.ListItemContent;
import android.location.Location;

public class Task extends FireNode {
	private String testResult; // used below for json
	private String testNote; //note: (testResult.to_string().upcase() +': ' +  testNote)
	private String description; //name: 
	private Date completed_at; //datestamp for the completion method --> completed: 
	private String reportId;  //the way to find the correct report --> report_id: 
	private String reportIndex; // the order for display in a list --> report_index: 
	private Location geoLocation; //  geo_locations_id:
	private static String tag = "Leaf";

	public Task(String name, String id, String nodeID, Date timeCompleted, String desc, String repId, String repIndex, String geo, Boolean completed) {
		super(name, id, nodeID, tag);
		setCompleted(Boolean.valueOf(completed));
		setDescription(desc); 
		setCompleted_at(timeCompleted); 
		setReportId(repId); 
		setGeoLocation(geo); 
		
		this.testResult = getResult(testResult); 
		this.testNote = getResult(testNote);
	}
	
	public JSONObject upload () {
		JSONObject params = new JSONObject(); 
		try { 
			if (id == null) { return null; } else  params.put("id", id);
			if (reportIndex != null) params.put("report_index", reportIndex);
			if (reportId != null) params.put("report_id", reportId);
			if (completed_at != null) params.put("completed_at", completed_at.toString());
			if (description != null) params.put("description", description);
			if (testNote != null) params.put("note", testNote);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return params;
	}

	String getResult(String result){
		if (result == null){
			return new String("");
		} else {
			return result; 
		}
	}

	@Override
	public int getChecked(){
		return getNumericalResult();
	}

	int ensurePresent(){
		if (this.testResult == null){
			this.testResult = "";
		}
		return checked; 
	}

	public void clear(){ 
		this.testNote = "";
		this.testResult = "";
		this.completed = false;
		this.checked = 0; 
		return; 
	}

	public int getNumericalResult() {	
		System.out.println(this.testResult);
		if (this.testResult.equals("Good"))
			return 4;
		else if (this.testResult.equals("Poor"))
			return 3;
		else if (this.testResult.equals("N/A"))
			return 2;
		else if (this.testResult.equals("Pass"))
			return 1;
		else if (this.testResult.equals("Fail"))
			return -1;			
		else
			return ensurePresent(); 
	}

	public String getTestResult() {
		return testResult;
	}

	public void setTestResult(String testResult) {
		this.testResult = testResult;
		setCompleted(true);
	}

	public String getTestNote() {
		return testNote;
	}

	public void setTestNote(String testNote) {
		this.testNote = testNote;
		setCompleted(true);
	}

	public void setCompleted(boolean c) {
		this.completed = c;
	}

	@Override
	public boolean checkCompleted(boolean t) {
		if (!this.testResult.equals("")) {
			this.completed = true; 
		} 
		return this.completed; 
	}

	@Override
	public ListItemContent createRowContent () {
		ListItemContent item = new ListItemContent (this);
		item.setChecked(getNumericalResult());
		return item;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCompleted_at() {
		return completed_at;
	}

	public void setCompleted_at(Date completed_at) {
		this.completed_at = completed_at;
	}
	
	public String getReportIndex() {
		return reportIndex;
	}
	
	public void setReportIndex(String reportIndex) {
		this.reportIndex = reportIndex;
	}

	public String getReportId() {
		return reportId;
	}

	public void setReportId(String reportId) {
		this.reportId = reportId;
	}

	public Location getGeoLocation() {
		return geoLocation;
	}

	public void setGeoLocation(String geoLocation) {
		//this.geoLocation = geoLocation;
	}
}
