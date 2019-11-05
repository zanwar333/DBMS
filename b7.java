import java.util.List;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.DBObject;

public class b7 
{

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		Mongo mongo = new Mongo("localhost",27017);
		DB db = mongo.getDB("test");
		DBCollection collection = db.getCollection("sample2");
		System.out.println("connected");
		
		System.out.println("Encoding");
		JSONArray array = new JSONArray();
		JSONObject obj = new JSONObject();
		JSONObject nobj = new JSONObject();
		array.add("DBMS");
		array.add("SDL");
		nobj.put("city","Pune");
		nobj.put("state", "MAharashtra");
		 nobj.put("pincode", new Integer(44));
		 obj.put("Roll", "User");
		  obj.put("Name", "Sahil Patil");
		  obj.put("Status", "C");
		  obj.put("Projects", "App");
		  obj.put("Subjects", array);
		  obj.put("Address", nobj);		  
		  System.out.println(obj);
		  collection.insert((DBObject) new BasicDBObject(obj));
		  
		  
		  
		  /*JSONObject json = new JSONObject();
			Scanner sc=new Scanner(System.in);
			int marks;
			JSONArray builder = new JSONArray();
			String role,hobby;
			String name;
			System.out.println("enter the name of project: ");
			name=sc.next();
			json.put("name", name);
			System.out.println("enter the role : ");
			role=sc.next();
			json.put("role", role);
			System.out.println("enter the marks ");
			marks=sc.nextInt();
			json.put("marks", marks);
			json.put("hobby", builder);
			
			collection.insert((DBObject) new BasicDBObject(json));*/
			
			DBCursor c= collection.find();
			//System.out.println(c.next());
			while(c.hasNext())
			{
				String s1 = String.format("%s", c.next());
				Object oj = JSONValue.parse(s1);
				JSONObject jo = (JSONObject) oj;
				System.out.println("name"+ jo.get("Name"));
				System.out.println("roll"+ jo.get("Roll"));
				System.out.println("address"+ jo.get("Address"));
				JSONObject addr = (JSONObject) jo.get("Address");
				System.out.println("City"+ addr.get("city"));
				
				System.out.println("subjects"+ jo.get("Subjects"));
				JSONArray sub = (JSONArray) jo.get("Subjects");
				System.out.println("array retrieved"+ sub.get(1));
				
			}
			 
	}

}
