
import java.sql.* ;
import java.util.*;

public class AA 
{

	public static void main(String[] args) 
	{
		String url = "jdbc:mysql://10.10.11.109:3306/";
		String dbName="t31244db";
		String driver = "com.mysql.jdbc.Driver";
		String userName = "t31244"; 
		String password = "t31244";
		Scanner sc=new Scanner(System.in);
		int n;
		String index_column,index_column1,index_column2,index_name;
		try
		{
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url+dbName,userName,password);
			Statement st = conn.createStatement();
			System.out.println("Database connected successfully");
			do
			{
			System.out.println("Choose \n1.create view\n2.Display view\n3.create index\n4.show index\n5.create sequence\n6.Drop view\n7.drop index\n8.Exit");
			 n=sc.nextInt();
			switch(n)
			{
			case 1:
				{
					System.out.println(" Enter the view name and tablename ");
					String view_name= sc.next();
					String table_name=sc.next();
					String sql="CREATE view "+ view_name+" as select  prof_id, prof_fname, dept_id , salary, city from "+table_name+";";
					
					st.executeUpdate(sql);
					System.out.println("View is created");
					break;
				}
		
			case 2:
				{
					ResultSet res = st.executeQuery("SELECT * FROM Professor1view");
					while (res.next()) 
					{
						int pid = res.getInt("prof_id");
						int did = res.getInt("dept_id");
						String pname = res.getString("prof_fname");
						String salary = res.getString("salary");
						String city = res.getString("city");
						System.out.println(pid + "\t" + pname + "\t" + did + "\t" + salary + "\t" + city);
					}
					break;
				}
			case 3:
				{
					int ch;
					do
					{
					
					System.out.println("choose \n1.simple index\n2.unique index\n3.Composite index\n4.Exit");
					ch=sc.nextInt();
					switch(ch)
					{
					case 1:{
							System.out.println("Enter the column on which simple index is to be created");
							index_column=sc.next();
							System.out.println("Enter the name of index");
							index_name=sc.next();
						   st.executeUpdate("Create index "+ index_name +" on Professors(" +index_column+")");
						   System.out.println("Simple index is created");
						   break;
							}
					case 2:{
							System.out.println("Enter the column on which unique index is to be created");
							index_column1=sc.next();
							System.out.println("Enter the name of index");
							index_name=sc.next();
							st.executeUpdate("Create unique index "+index_name+" on Professors("+index_column1+")");
							System.out.println("Unique index is created");
							break;
							}
					case 3:st.executeUpdate("Create index name on Professors(prof_fname,prof_lname)");
						 System.out.println("Composite index is created");
						 break;
					}
					}while(ch!=4);
					
					
					break;
				}
			case 4:
				{
					ResultSet res1=st.executeQuery("show index from Professors");
					System.out.println("Key_name\tIndex_type");
					while(res1.next())
					{
						String C3_name=res1.getString("Key_name");
						String C4_name=res1.getString("Index_type");
						System.out.println( C3_name+"\t\t"+C4_name);
					}
					break;
				}
			case 5:
				{
					System.out.println("Enter the name of table");
					String t_name=sc.next();
					System.out.println("Enter the name of auto-inremented column");
					String col1=sc.next();
					System.out.println("Enter the name of other column");
					String col2=sc.next();
					String sql="Create table "+t_name+"("+col1+" integer auto_increment primary key , "+col2+" varchar(10) );";
					st.executeUpdate(sql);
					int k=1;
					while(k==1)
					{
						System.out.println("Enter the name");
						String name=sc.next();
						st.executeUpdate("Insert into "+t_name+" ("+col2+") values ('"+name+"')");
						System.out.println("Insert 1 for more entries");
						k=sc.nextInt();
					}
						break;	
				}
			case 6:
				{
					System.out.println("Enter the name of view to be dropped");
					String v_name=sc.next();
					st.executeUpdate("Drop view "+v_name);
					break;
				}
			case 7:
			{
				System.out.println("Enter the name of index and table to be dropped");
				String i_name=sc.next();
				String t_name=sc.next();
				st.executeUpdate("Drop index "+i_name+" on "+t_name+";");
				break;
			}
			
			}
			}while(n!=8);
			 conn.close();
		} 
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	

}