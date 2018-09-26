package Framework.TestingFramework.utils;

import  java.sql.Connection;		
import  java.sql.Statement;		
import  java.sql.ResultSet;		
import  java.sql.DriverManager;		
import  java.sql.SQLException;

public class  DatabaseUtil 
{		
	    Connection con = null; 
	    
	    public void makeConnection() throws ClassNotFoundException, SQLException
	    {
	    	//Connection URL Syntax: "jdbc:mysql://ipaddress:portnumber/db_name"		
            String dbUrl = "jdbc:mysql://139.162.47.185:3306/fusion";					
            
			//Database Username		
			String username = "fusiontest";	
            
			//Database Password		
			String password = "test@123";				
			         
     	    //Load mysql jdbc driver		
       	    Class.forName("com.mysql.jdbc.Driver");			
       
       		//Create Connection to DB		
            con = DriverManager.getConnection(dbUrl,username,password);
	    }
		public ResultSet runQuery(String queryy) throws  ClassNotFoundException, SQLException 
    	{													
			//Query to Execute		
			String query = queryy;
          
          		//Create Statement Object		
        	   Statement stmt = con.createStatement();					
       
       			// Execute the SQL Query. Store results in ResultSet		
         		ResultSet rs= stmt.executeQuery(query);		
				return rs;			
		}
		public void ConnectionClose() throws SQLException
		{
			con.close();
		}
	    
}

