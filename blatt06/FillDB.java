import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class FillDB {

	public static void main(String[] args) throws SQLException, IOException{
		Connection con = getCon();
	
		String create = "CREATE TABLE Accounts (account integer, balance integer);";
		
		con = getCon();
		con.createStatement().executeQuery(create);
		con.createStatement().executeQuery("INSERT INTO Accounts VALUES(0,0);");
		for(int i = 1; i <= 100; i++){
			con.createStatement().executeQuery("INSERT INTO Accounts VALUES(" + i + "," + 0 + ");");
		}
		
		
		
	}
	
	
	public static Connection getCon() throws SQLException, IOException {
		
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
