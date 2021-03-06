/*
 * Example code for Assignment 6 (concurrency tuning) of the course:
 *
 * Database Tuning
 * Department of Computer Science
 * University of Salzburg, Austria
 *
 * Lecturer: Nikolaus Augsten
 */

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * <p>
 * Run numThreads transactions, where at most maxConcurrent transactions
 * can run in parallel.
 *
 * <p>params: numThreads maxConcurrent
 *
 */
public class ConcurrentTransactions {

	public static void main(String[] args) throws SQLException, IOException, ClassNotFoundException {

		Class.forName("org.postgresql.Driver");

		
		// read command line parameters
		if (args.length != 3) {
			System.err.println("params: numThreads maxConcurrent whichQuery (1 | 2)");
			System.exit(-1);
		}
		int numThreads = Integer.parseInt(args[0]);
		int maxConcurrent = Integer.parseInt(args[1]);
		int whichQuery = Integer.parseInt(args[2]);
		int isolationLevel = Connection.TRANSACTION_READ_COMMITTED;

		// create numThreads transactions
		Transaction[] trans = new Transaction[numThreads];
		for (int i = 0; i < trans.length; i++) {
			trans[i] = new Transaction(i + 1, isolationLevel, whichQuery);
		}

		// start all transactions using a thread pool
		ExecutorService pool = Executors.newFixedThreadPool(maxConcurrent);
		//System.out.println("Starttime: " + System.currentTimeMillis());		
		for (int i = 0; i < trans.length; i++) {
			pool.execute(trans[i]);
		}
		pool.shutdown(); // end program after all transactions are done
		

	}


}
