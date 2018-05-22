package Framework.TestingFramework.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;

public class CSVUtil {
	
	static String data[][];
	
	public static String[][] getDataFromCSV(String fileName){
		try {
			FileInputStream file = new FileInputStream(new File("src//main//java//Framework//TestingFramework//testInput//"+fileName+".csv"));
			FileInputStream file1 = new FileInputStream(new File("src//main//java//Framework//TestingFramework//testInput//"+fileName+".csv"));
			BufferedReader reader = new BufferedReader(new InputStreamReader(file));
			BufferedReader reader1 = new BufferedReader(new InputStreamReader(file1));
			String line,line1;
			int row=0;
			int rowCount = 0; 
			int colCount=0;
			while ((line1 = reader1.readLine()) != null) {
				String[] str = line1.split(","); 
				if(!str[0].equals(null)){
				for(int i=0;i<str.length;i++){
				colCount=str.length;
				}
				}
				row++;
			}			
			rowCount = row;
			row=1;		
			data = new String[rowCount][colCount];
			while ((line = reader.readLine()) != null) {
				String[] str = line.split(",");
				if(!str[0].equals(null)){
				for(int i=0;i<str.length;i++){
					if(row!=0){
					data[row-1][i] = str[i];
					}
				}
				
				}
				row++;
			}
			reader.close();
			reader1.close();
		}
		catch(Exception e){
			System.out.println(e);
		}
		return data;
	}

}
