import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class SecondAdvancedApproach {

	public static void main(String[] args) throws Exception {
        
        String path = "/home/stud3/tdafir/Documents/dbtuning/blatt01/auth.tsv";
        
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
		String pwd = "Ohx1IngahJ1x";
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
