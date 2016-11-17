import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.HashMap;

public class ThroughputMeasurement {

	public static void main(String[] args) throws Exception {

		Class.forName("org.postgresql.Driver");

		String host = "biber.cosy.sbg.ac.at";
		String port = "5432";
		String database = "dbtuning_ws2016";
		String pwd = "";
		String user = "";
		String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;

		final int QUERY_TYPE = 1; // ändern

		HashMap<Integer, String[]> values_all = new HashMap<>();
		String[] tables = { "Publ_S", "Publ_CB", "Publ_B", "Publ_H" };
		String[] values_pubid = { "'example'" }; // TODO:eintragen
		String[] values_booktitle = { "'example'" }; // TODO:eintragen
		String[] values_year = { "'1996'" }; // TODO:eintragen
		String[] attributes = { "pubID", "booktitle", "year" };
		values_all.put(1, values_pubid);
		values_all.put(2, values_booktitle);
		values_all.put(3, values_year);

		BufferedReader credentials = new BufferedReader(new FileReader("credentials.txt"));
		user = credentials.readLine();
		pwd = credentials.readLine();
		credentials.close();

		Connection con = null;
		con = DriverManager.getConnection(url, user, pwd);
		Long starttime;
		Long endtime;
		String query = "";
		HashMap<String, Long> runtimes = new HashMap<>();

		for (String table : tables) {
			starttime = System.currentTimeMillis();
			for (String value : values_all.get(QUERY_TYPE)) {
				query = "SELECT * FROM " + table + " WHERE " + attributes[QUERY_TYPE - 1] + " = " + value;
				con.createStatement().execute(query);
			}
			endtime = System.currentTimeMillis();
			runtimes.put(table, endtime - starttime);
		}

		for (String table : tables) {
			System.out.println(table + " :" + runtimes.get(table));
			System.out.println(
					table + ": " + ((double) (values_all.get(QUERY_TYPE).length) / runtimes.get(table)) * 1000);
		}
	}
}
