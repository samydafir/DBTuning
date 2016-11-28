import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
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

		int queryType = 1;

		if (args.length > 0) {
			queryType = Integer.parseInt(args[0]);
		}

		assert queryType > 0;
		assert queryType < 4;

		System.out.print("Attribute used for searching: ");
		switch (queryType) {
		case 1:
			System.out.println("pubID");
			break;
		case 2:
			System.out.println("booktitle");
			break;
		case 3:
			System.out.println("years");
			break;
		}

		HashMap<Integer, String[]> values_all = new HashMap<>();
		String[] tables = { "Publ_S", "Publ_CB", "Publ_B", "Publ_H" };
		String[] values_pubid = { "'example'" }; // TODO:eintragen
		String[] values_booktitle = { "'example'" }; // TODO:eintragen
		String[] values_year = getData("./years.txt");
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
		Long runtime;
		String query = "";

		System.out.println("Number of searched values: " + values_all.get(queryType).length);

		for (String table : tables) {
			starttime = System.currentTimeMillis();
			for (String value : values_all.get(queryType)) {
				query = "SELECT * FROM " + table + " WHERE " + attributes[queryType - 1] + " = " + value;
				con.createStatement().execute(query);
			}
			endtime = System.currentTimeMillis();
			runtime = endtime - starttime;
			System.out.println("Runtime " + table + ": " + runtime);
			System.out.println(
					"Throughput " + table + ": " + ((double) (values_all.get(queryType).length) / runtime) * 1000);
		}
	}

	private static String[] getData(String path) throws Exception {

		ArrayList<String> data_list = new ArrayList<>();
		BufferedReader TextFile = new BufferedReader(new FileReader(path));

		String dataRow = TextFile.readLine();
		while (dataRow != null) {
			data_list.add("'" + dataRow + "'");
			dataRow = TextFile.readLine(); // Read next line of data.
		}

		TextFile.close();

		String[] data = new String[data_list.size()];
		data = data_list.toArray(data);

		return data;
	}
}
