import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class CreateData {

	//arg[0]: ungefaehre Anzahl an Eintraegen
	public static void main(String[] args) throws Exception {

				final int queryAttrCol = 0; //Zu wählende Spalte
				final double numOfRows = 1233214;
				final String inputFile = "publ.tsv";
				final String outputFile = "ids.txt";
				BufferedReader reader = new BufferedReader(new FileReader(inputFile));
				BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));
				double numOfDataPoints = Integer.parseInt(args[0]);
				double selectProb = numOfDataPoints / numOfRows;
				String line;
				String[] wordsInLine;

				line = reader.readLine();
				while(line != null){
					wordsInLine = line.split("\t");
					if(Math.random() <= selectProb){
						writer.write(wordsInLine[queryAttrCol] + "\n");
					}
					line = reader.readLine();
				}

				writer.flush();
				writer.close();
				reader.close();
		}
}
