


package blatt02;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GenerateData {

	public static void main(String[] args) {
		try {
			employees();
			students();
		} catch (IOException e) {}
	}


	public static void employees() throws IOException{
		String[][] depts ={{"CS","Franz"}, {"KT","Hermann"}, {"ET", "Sepp"}, {"ST","Herbert"}, {"HB","Herbert"}, {"TB","Herbert"},
				{"MB","Dominik"}, {"AR","Kevin"}, {"TC", "Samy"}, {"TP","Samy"}, {"TH", "Martin"}, {"SW", "Kurt"}, {"KW", "Franziska"}, {"GEO","Christine"}, {"BIO","Barbara"}, 
				{"HW","Michaela"}, {"GESW","Stephanie"}, {"RW", "Gerhard"}, {"M","Dominik"}};
		BufferedWriter writer = new BufferedWriter(new FileWriter("employees.tsv"));
		
		int manDept;
		for (int i = 0; i < 100000; i++){
			writer.write(new Integer(i).toString());
			writer.write(";");
			writer.write(randomString(20));
			writer.write(";");
			manDept = (int)(Math.random()*19);
			writer.write(depts[manDept][1]);
			writer.write(";");
			writer.write(depts[manDept][0]);
			writer.write(";");
			writer.write(new Integer((int)(1000 + Math.random()*10000)).toString());
			writer.write(";");
			writer.write(new Integer((int) (20 + Math.random() * 500)).toString());
			writer.write("\n");
		}
		writer.close();
	}
	
	public static void students() throws IOException{
		BufferedWriter writer = new BufferedWriter(new FileWriter("students.tsv"));
		
		for(int i = 0; i < 100000; i++){
			writer.write(new Integer(i + 100000).toString());
			writer.write(";");
			writer.write(randomString(20));
			writer.write(";");
			writer.write(randomString(1) + new Integer((int)(Math.random()*10)).toString());
			writer.write(";");
			writer.write(new Integer((int)(1 + Math.random() * 5)).toString());
			writer.write("\n");
		}
		writer.close();
		
	}
	
	
	
	public static String randomString(int length){
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < length; i++){
			sb.append((char)(int)(97 + Math.random()*26));
		}
		return sb.toString();
	}
	
}
