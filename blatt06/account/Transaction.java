package acount;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Dummy transaction that prints a start message, waits for a random time
 * (up to 100ms) and finally prints a status message at termination.
 */
class Transaction extends Thread {

	// identifier of the transaction
	int id;
	int isolation;
	int whichQuery;

	Transaction(int id, int isolationLevel, int whichQuery) {
		this.id = id;
		this.isolation = isolationLevel;
		this.whichQuery = whichQuery;		
	}

	@Override
	public void run() {
		System.out.println("transaction " + id + " started");
		
		String query1 = "SET e = SELECT balance FROM Accounts WHERE account =" + this.id
					  + "UPDATE Accounts SET balance = e + 1 WHERE account =" + this.id
					  + "SET c = SELECT balance FROM Accounts WHERE account = 0"
					  + "UPDATE Accounts SET balance = c - 1 WHERE account = 0;";
		
		String query2 = "UPDATE Accounts set balance = balance + 1 where account = " + this.id
					  + "UPDATE Accounts set balance = balance - 1 where account = 0;";
		
		Connection con = null;
		
		try {
			con = getCon();
			con.setAutoCommit(false);
			con.setTransactionIsolation(isolation);
			con.createStatement().execute(whichQuery == 1 ? query1 : query2);
			con.commit();
				
		} catch (SQLException | IOException e) {
			run();
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












