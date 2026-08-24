package com.cognodb.relations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.Record;

import com.google.gson.Gson;

public class CypherToJson {
	
	private static Driver driver = CognoDBConnection.getDriver();
	
	public static String getNodeJsonString() {
        List<Map<String, Object>> resultList = new ArrayList<>();
        Gson gson = new Gson();

        try {
            var result = driver.executableQuery("MATCH (n) RETURN labels(n)[0] AS type, n.name AS name ORDER BY type, name").execute();
            
            ArrayList<Record> arr = (ArrayList<Record>) result.records();
            
            for(Record record : arr) {
            	resultList.add(Map.of(
					"type", record.get("type").asString(),
					"name", record.get("name").asString()
				));
            }
            
        } catch (Exception e) {
            System.err.println("Error executing query: " + e.getMessage());
        }
        
        System.out.println("Result List: " + resultList);

        // Wrap the result list in a "nodes" key
        Map<String, Object> response = new HashMap<>();
        response.put("nodes", resultList);

        return gson.toJson(response);
	}
}