import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.io.FileNotFoundException;

public class FirstAdvancedApproach {

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
		String path = "auth.tsv";
		String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
		
		try {
			BufferedReader credentials = new BufferedReader(new FileReader("credentials.txt"));
			user = credentials.readLine();
			pwd = credentials.readLine();
			path = credentials.readLine();
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

		long starttime = System.currentTimeMillis();

		try {
			String qry = "INSERT INTO auth VALUES " + getValues(path) + ";";
			System.out.println("Begin transmit");
			con.createStatement().execute(qry);
			System.out.println("Query sucessful.");
		} catch (Exception e) {
			System.err.println("Query was not successful.");
			e.printStackTrace();
		}
		
		long endtime = System.currentTimeMillis();
		long runtime = endtime - starttime;
		System.out.println("Runtime in milliseconds: " + runtime);
		System.out.println("Runtime in seconds: " + runtime / 1000);
		System.out.println("Runtime in minutes: " + runtime / (1000 * 60));
	}

	private static String getValues(String path) throws Exception {

		StringBuilder dataBuilder = new StringBuilder();
		BufferedReader TSVFile = new BufferedReader(new FileReader(path));

		String dataRow = TSVFile.readLine();
		int count = 0;
		while (count < 1547601) {
			dataRow = dataRow.replace("'", "''");
			dataRow = dataRow.replace("\t", "','");
			dataBuilder.append("('" + dataRow + "'),");
			dataRow = TSVFile.readLine(); // Read next line of data.
			count++;
		}
		TSVFile.close();

		String data = dataBuilder.toString();
		data = data.substring(0, data.length() - 1);

		return data;
	}
}
