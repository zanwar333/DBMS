import com.mongodb.MongoCredential;

import org.bson.Document;

import com.mongodb.MongoClient;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		// ESTABLISHING CONNECTION 
		MongoClient mongo = new MongoClient( "localhost" , 27017 );
		MongoCredential credential;
		credential = MongoCredential.createCredential("Mohan", "examdb","password".toCharArray());
		
		
		//ACCESSING DATEBASE
		MongoDatabase db = mongo.getDatabase("examdb");
		System.out.println("DATAEBASE ACCESSED");
		
		//CREATING COLLECTION
		db.createCollection("t1t1");
		
		//ACCESSING COLLECTION
		MongoCollection <Document> col = db.getCollection("t1t1");
		
		//CREATING A DOCUMENT AND INSERTING INTO COLLECTION
		Document d1 = new Document("title","mongobd").append("type", "json").append("schema", "less");
		col.insertOne(d1);
		
		//ACCESSING A DOCUMENT
		FindIterable <Document> it1 = col.find();
		Iterator<Document> it =it1.iterator();
		
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		
		//UPADING A DOCUMENT
		col.updateOne(Filters.eq("title", "mongobd"),Updates.set("title","mongodb"));
		System.out.println("UPDATED SUCCESSFULLY");
		
		it1 = col.find();
		it =it1.iterator();
		
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		
		//DELETING A DOCUMNET
		col.deleteOne(Filters.eq("title", "mongobd"));
		it1 = col.find();
		it =it1.iterator();
		
		while(it.hasNext())
		{
			System.out.println(it.next());
		}
		
		//LISTING OF COLLECTIONS
		 for(String name : db.listCollectionNames())
		 {
			 System.out.println(name);
		 }
		 
		 //COLLECTION DROPING
		col.drop();
		for(String name : db.listCollectionNames())
		 {
			 System.out.println(name);
		 }
		
		
		mongo.close();
		
	}

}
