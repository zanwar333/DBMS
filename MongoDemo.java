import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.bson.Document;
public class MongoDemo 
{

	public static void main(String[] args) throws Exception
	{
		// TODO Auto-generated method stub
		Scanner sc= new Scanner(System.in);
		try
		{
			MongoClient mongoClient = new MongoClient("localhost", 27017);
			// enlist the databases
			List<String>database=mongoClient.getDatabaseNames();  
			System.out.println(database);
						
			// create new database
			MongoDatabase db= mongoClient.getDatabase("test");  
			System.out.println("Connected");
			
		    // create new collection
			MongoCollection<Document> collection = db.getCollection("sample1"); 
			System.out.println("Collection myCollection selected successfully");
			
			List<Document>doclist= new ArrayList<Document>();
			String name;
			int id;
			System.out.println("Enter your name");
			name= sc.nextLine();
			System.out.println("Enter your id");
			id= sc.nextInt();
			Document doc1 = new Document().append("id", id).append("Name", name);
			doclist.add(doc1);
			
			System.out.println("Enter your name");
			sc.nextLine();
			name= sc.nextLine();
			System.out.println("Enter your id");
			id= sc.nextInt();
			Document doc2 = new Document().append("id", id).append("Name", name);
			doclist.add(doc2);
			
			
			collection.insertMany(doclist);
			System.out.println("Document inserted successfully");
			// enter the data 
			/*Document doc= new Document().append("id", 1).append("Name", "Kunjal");  
			collection.insertOne(doc);
			System.out.println("Document inserted successfully");*/  
			
			// update the entry
			collection.updateOne(Filters.eq("id",1), Updates.set("Name","Aditya"));
			System.out.println("Document updated successfully");
			
			// deleting the document
			collection.deleteOne(Filters.eq("id",4));
			System.out.println("Document deleted successfully");
			
			// retrieving getting the iterable objet
			FindIterable<Document> iterdoc =  collection.find();
			int i=1;			  
			Iterator it= iterdoc.iterator();// getting the iterator
			while(it.hasNext())
			{
				System.out.println(it.next());
				i++;
			}                                
			 
			
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		System.out.println("server is ready");

	}

}
