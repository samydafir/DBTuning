import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileNotFoundException;

public class ThroughputMeasurement {

	public static void main(String[] args) throws Exception {
		
		Class.forName("org.postgresql.Driver");

		String host = "biber.cosy.sbg.ac.at";
		String port = "5432";
		String database = "dbtuning_ws2016";
		String pwd = "";
		String user = "";
		String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;

		String[] tables = {"Publ_S", "Publ_CB", "Publ_B", "Publ_H"};
		String[] values = {"1996"}; //eintragen
		String attribute = "year"; //eintragen

		BufferedReader credentials = new BufferedReader(new FileReader("credentials.txt"));
		user = credentials.readLine();
		pwd = credentials.readLine();
		path = credentials.readLine();
		credentials.close();

		Connection con = null;
		con = DriverManager.getConnection(url, user, pwd);
		long starttime;
		long endtime;
		HashMap<String, long> runtimes = new HashMap<>();

		for (String table: tables) {
			starttime = System.currentTimeMillis();
			for (String value: values) {
				query = "SELECT * FROM " + table + " WHERE " + attribute + " = " + value;
				con.createStatement().execute(query);
			}
			endtime = System.currentTimeMillis();
			runtimes.put(table, endtime - starttime);
		}
		
		for(String table: tables) {
			System.out.print(table + ": " + ( (double) (values.length) / runtimes.get(table) ) * 1000);
		}
	}		
}
