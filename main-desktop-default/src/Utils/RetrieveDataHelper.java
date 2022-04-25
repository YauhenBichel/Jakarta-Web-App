package Utils;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RetrieveDataHelper {

	public ArrayList getAllRequests() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
				"http://localhost:8080/holidaysystem/api/holiday-request/all");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			
			String json = EntityUtils.toString(response.getEntity());
			JSONParser parser = new JSONParser();
			Object resultObject  = parser.parse(json);
			
			JSONArray array = (JSONArray) resultObject;
			ArrayList list = new ArrayList();
			for (Object object : array) {
                JSONObject obj = (JSONObject) object;
                list.add(obj.get("employeeId"));
                list.add(obj.get("status"));
                list.add(obj.get("startDate"));
                list.add(obj.get("endDate"));
            }
			return list;
			
		}catch(IOException | ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
	
	public ArrayList getAllEmployees() {
		try {
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
				"http://localhost:8080/holidaysystem/api/employee/all");
			getRequest.addHeader("accept", "application/json");
			HttpResponse response = httpClient.execute(getRequest);
			
			String json = EntityUtils.toString(response.getEntity());
			JSONParser parser = new JSONParser();
			Object resultObject  = parser.parse(json);
			
			JSONArray array = (JSONArray) resultObject;
			ArrayList list = new ArrayList();
			for (Object object : array) {
                JSONObject obj = (JSONObject) object;
                list.add(obj.get("firstName"));
                list.add(obj.get("lastName"));
                list.add(obj.get("role"));
                list.add(obj.get("department"));
                list.add(obj.get("email"));            
            }
			return list;
			
		}catch(IOException | ParseException ex) {
			ex.printStackTrace();
		}
		return null;
	}
}
