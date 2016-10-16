import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class ThirdAdvancedApproach {

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
		String pwd = "---";
		String user = "---";
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
		String query = "INSERT INTO auth values(?, ?);";
		String[] cols = new String[2];
        PreparedStatement ps = con.prepareStatement(query);
        int count = 0;
        long start = System.currentTimeMillis();
        while(count < 1200000){
        	count++;
        	cols = line.split("\t");
        	ps.setString(1, cols[0]);
        	ps.setString(2, cols[1]);
        	ps.addBatch();
        	line = reader.readLine();
        }
        ps.executeBatch();
        long end = System.currentTimeMillis();
        System.out.println("Duration:" + (end - start)/1000 + "s");
        reader.close();    
    }
}