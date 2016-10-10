import java.sql.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class StraightForward {
    
    public static void main(String[] args) throws Exception {
	try {
	    Class.forName ( "org.postgresql.Driver" );
	    System.err.println("Driver found.");
	} catch ( java.lang.ClassNotFoundException e ) {
	    System.err.println("PostgreSQL JDBC Driver not found ... ");
	    e.printStackTrace();

	    return;
	}
	
	String host = "biber.cosy.sbg.ac.at";
	String port = "5432";
	String database = "dbtuning_ws2016";
	String pwd = "";
	String user = "tdafir";
	String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
	Connection con = null;
	try {
	    con = DriverManager.getConnection(url, user, pwd);
	    System.err.println("Connection established.");
	} catch (Exception e) {
	    System.err.println("Could not establish connection.");
	    e.printStackTrace();
	    return;
	}
	
	BufferedReader reader = new BufferedReader(new FileReader("auth.tsv"));
	String line = reader.readLine();
	String values = "";
	String query = "";
	String[] lineArray;
	int count = 0;
	while(line != null){
		count++;
		values = "";
		values += "('";
		lineArray = line.split("\t");		
		for(int i = 0; i < lineArray.length; i++){
			if(i < lineArray.length - 1){
				values += lineArray[i].replace("'","''") + "', '";
			}else{
				values += lineArray[i].replace("'","''") + "');";
			}		
		}
		query = "INSERT INTO auth VALUES" + values;
		try {
			con.createStatement().execute(query);
			if(count % 10000 == 0) System.out.println(count);
		} catch (Exception e) {
    		System.err.println("Query was not successful.");
			e.printStackTrace();
		}
	
	line = reader.readLine();
	}
	reader.close();
    }	
}
