import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Dummy transaction that prints a start message, waits for a random time
 * (up to 100ms) and finally prints a status message at termination.
 */
class Transaction extends Thread {

	// identifier of the transaction
	int id;
	int whichQuery;
	Connection con = null;
	Statement stmt = null;
	String query1;
	String query2;

	Transaction(int id, int isolationLevel, int whichQuery) throws SQLException, IOException, ClassNotFoundException {
		this.id = id;
		this.whichQuery = whichQuery;	
	}

	@Override
	public void run() {
		System.out.println("transaction " + this.id + " started");
		try {
			con = getCon();
			con.setAutoCommit(false);
			stmt = con.createStatement();

			if(whichQuery == 1){
				query1 = "START TRANSACTION ISOLATION LEVEL READ COMMITTED;";
				stmt.executeUpdate(query1);

				query1 = "SET e = SELECT balance FROM accounts WHERE account =" + this.id + ";";
				stmt.executeUpdate(query1);

				query1 = "UPDATE accounts SET balance = e + 1 WHERE account =" + this.id + ";";
				stmt.executeUpdate(query1);

				query1 = "SET c = SELECT balance FROM accounts WHERE account = 0;";
				stmt.executeUpdate(query1);

				query1 = "UPDATE accounts SET balance = c - 1 WHERE account = 0;";
				stmt.executeUpdate(query1);

			}else if(whichQuery == 2){
				query2 = "START TRANSACTION ISOLATION LEVEL SERIALIZABLE;";
				stmt.executeUpdate(query2);

				query2 = "UPDATE accounts set balance = balance + 1 where account = " + this.id + ";";
				stmt.executeUpdate(query2);

				query2 = "UPDATE accounts set balance = balance - 1 where account = 0;";
				stmt.executeUpdate(query2);
			}
			stmt.close();
			con.commit();
			con.close();
		} catch (IOException | SQLException e) {			
			e.printStackTrace();
			try{
				stmt.close();
				con.rollback();
				con.close();
				run();
			}catch (SQLException f) {
				f.printStackTrace();
			}
		}
	}		
	
	public Connection getCon() throws SQLException, IOException {
		
		BufferedReader credentials = new BufferedReader(new FileReader("../credentials.txt"));
		String host = "biber.cosy.sbg.ac.at";
		String port = "5432";
		String database = "dbtuning_ws2016";
		String user = credentials.readLine();
		String pwd = credentials.readLine();
		credentials.close();
		String url = "jdbc:postgresql://" + host + ":" + port + "/" + database;
		
		return DriverManager.getConnection(url, user, pwd);
	}
}
