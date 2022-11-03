package niall.assignment6.app.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class FileService {

	public Map<YearMonth, Integer> readFile(String filePath) {

		BufferedReader fileReader = null;
		Map<YearMonth, Integer> csvAsMap = new LinkedHashMap<YearMonth, Integer>();

		try {
			fileReader = new BufferedReader(new FileReader(filePath));
			String fileLine;

			fileReader.readLine();
			while ((fileLine = fileReader.readLine()) != null) {
				String[] lineArray = fileLine.split(",");
				YearMonth month = YearMonth.parse(lineArray[0], DateTimeFormatter.ofPattern("LLL-yy", Locale.UK));
				Integer sales = Integer.parseInt(lineArray[1]);

				csvAsMap.put(month, sales);
			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("I/O Exception Error!");
				e.printStackTrace();
			}
		}
		return csvAsMap;
	}

}
