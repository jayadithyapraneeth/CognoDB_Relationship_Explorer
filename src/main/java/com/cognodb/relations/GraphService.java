package com.cognodb.relations;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.neo4j.driver.Driver;
import org.neo4j.driver.types.Path;
import org.neo4j.driver.types.Path.Segment;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class GraphService {
	
	private static Driver driver = CognoDBConnection.getDriver();
	
	public static String findPathString(String starttype, String startnode,String endtype, String endnode){
		
		String query = "MATCH path = (start:%s {name: $startnode})-[*1..5]-(end:%s {name: $endnode}) RETURN path LIMIT 1".formatted(starttype, endtype);
		
		var p = driver.executableQuery(query).withParameters(Map.of("startnode", startnode, "endnode", endnode)).execute();
        
        System.out.println("Executed query successfully!");
        
        StringBuilder pathString = new StringBuilder();
        
        if(p.records().isEmpty()) {
        	System.out.println("No path found between " + startnode + " and " + endnode);
        	pathString.append("No path found between " + startnode + " and " + endnode);
        	//return pathString.append("FALSE").toString();
        	return pathString.toString();
        }
                
        Path path = p.records().get(0).get("path").asPath();
        
        String direction = null;
        
        
        int i = 0;
        if(path.length() > 0) {
        	for(Segment segment : path) {
        		
        		switch (segment.relationship().type().toString()) {
        			case "NEEDS": direction = " <- "; break;
        			case "REQUIRES": direction = " -> "; break;
        			case "USES": direction = " -> "; break;
        		}
        		System.out.print(segment.start().get("name").asString());//Node 0
        		System.out.println(segment.relationship().type() + direction); //Relationship
        		System.out.println(segment.end().get("name").asString()); // Node 1
        		
        		
        		if(pathString.indexOf(segment.start().get("name").asString()) >= 0) {
        			pathString.append(direction + segment.relationship().type() + direction + segment.end().get("name").asString());
        		}else {
        			pathString.append(segment.start().get("name").asString() + direction + segment.relationship().type() + direction + segment.end().get("name").asString());
        		}
        	}
        }
        
        //return pathString.append("TRUE").toString();
        return pathString.toString();
        
	}
	
	public static String findRelations(String selectednodename, String selectednodetype) {
		String Query = "MATCH (selected {name: $selectednodename})-[r]-(related) WHERE $selectednodetype IN labels(selected) RETURN labels(related)[0] AS relatedType, related.name AS relatedName";

        var relations = driver.executableQuery(Query).withParameters(Map.of("selectednodename", selectednodename, "selectednodetype", selectednodetype)).execute();

        JsonObject jsonobject = new JsonObject(); //a JsonObject to hold two other json objects

        JsonObject selectedNode = new JsonObject();
        selectedNode.addProperty("type", selectednodetype);
        selectedNode.addProperty("name", selectednodename);

        jsonobject.add("selectedNode", selectedNode);

        JsonObject relationsObject = new JsonObject(); //json object to hold four json arrays

        JsonArray technologiesArray = new JsonArray();
        JsonArray skillsArray = new JsonArray();
        JsonArray rolesArray = new JsonArray();
        JsonArray projectsArray = new JsonArray();

        Iterator<org.neo4j.driver.Record> relatedNodes = relations.records().iterator();

        if (!relatedNodes.hasNext()) {
            System.out.println("No related nodes found for the given node.");
        }

        String nodetype = null;
        String nodename = null;
        org.neo4j.driver.Record record = null;

        while (relatedNodes.hasNext()) {

            record = relatedNodes.next();
            nodetype = record.get("relatedType").asString();
            nodename = record.get("relatedName").asString();

            System.out.println("Related Node Type: " + nodetype);
            System.out.println("Related Node Name: " + nodename);

            switch (nodetype) {
                case "Technology":
                    technologiesArray.add(nodename);
                    break;
                case "Skill":
                    skillsArray.add(nodename);
                    break;
                case "Role":
                    rolesArray.add(nodename);
                    break;
                case "Project":
                    projectsArray.add(nodename);
                    break;
            }
        }

        System.out.println("Technologies: " + technologiesArray.toString());

        relationsObject.add("technologies", technologiesArray);
        relationsObject.add("skills", skillsArray);
        relationsObject.add("roles", rolesArray);
        relationsObject.add("projects", projectsArray);

        jsonobject.add("relations", relationsObject);

        return jsonobject.toString();
	}

}