import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class SecondAdvancedApproach {

	public static void main(String[] args) throws Exception {
        
		try {
			Class.forName("org.postgresql.Driver");
			System.err.println("Driver found.");
		} catch (java.lang.ClassNotFoundException e) {
			System.err.println("PostgreSQL JDBC Driver not found ... ");
			e.printStackTrace();
			return;
		}

		String host = "biber.cosy.sbg.ac.at";
		String port = "5432";
		String database = "dbtuning_ws2016";
		String pwd = "";
		String user = "";
		String path = "/home/stud3/tdafir/Documents/dbtuning/blatt01/auth.tsv";
		String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
		
		try {
			BufferedReader credentials = new BufferedReader(new FileReader("credentials.txt"));
			user = credentials.readLine();
			pwd = credentials.readLine();
			//using this approach it's probably better not to read the path from the credentials file
			//path = credentials.readLine();
			credentials.close();
		} catch (FileNotFoundException fnfe) {
			System.out.println("Unable to find file with credentials.");
		}
		
		Connection con = null;
		try {
			con = DriverManager.getConnection(url, user, pwd);
			System.err.println("Connection established.");
		} catch (Exception e) {
			System.err.println("Could not establish connection.");
			e.printStackTrace();
			return;
		}
		try {
			String qry = "COPY  auth FROM '" + path + "' DELIMITER '\tab';";
			con.createStatement().execute(qry);
			System.out.println("Query sucessful.\n---\n" + "List of tables in database '" + database + "':");
		} catch (Exception e) {
			System.err.println("Query was not successful.");
			e.printStackTrace();
		}
	}
}
