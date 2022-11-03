package niall.assignment6.app.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import niall.assignment6.app.main.AppConstants;

public class FileService {

//	public List<String> readFile(String filePath) {
//
//		BufferedReader fileReader = null;
//		List<String> fileList = new ArrayList<String>();
//
//		try {
//			fileReader = new BufferedReader(new FileReader(filePath));
//			String fileLine;
//
//			fileReader.readLine();
//			while ((fileLine = fileReader.readLine()) != null) {
////				fileList.add(fileLine);
//
////				String[] lineArray = fileLine.split(",");
////				String date = lineArray[0];
////				String sales = lineArray[1];
////				fileList.add(date);
////				fileList.add(sales);
//
//				String[] lineArray = fileLine.split(",");
//				String date = lineArray[0] + " -> " + lineArray[1];
//				fileList.add(date);
//
//			}
//		} catch (IOException e) {
//			System.out.println("Error reading file!");
//			e.printStackTrace();
//		} finally {
//			try {
//				fileReader.close();
//			} catch (IOException e) {
//				System.out.println("I/O Exception Error!");
//				e.printStackTrace();
//			}
//		}
//		return fileList;
//	}

	public Map<YearMonth, Integer> readFile(String filePath) {

		BufferedReader fileReader = null;
		Map<YearMonth, Integer> csvAsMap = new LinkedHashMap<YearMonth, Integer>();

		try {
			fileReader = new BufferedReader(new FileReader(filePath));
			String fileLine;

			fileReader.readLine();
			while ((fileLine = fileReader.readLine()) != null) {
				String[] lineArray = fileLine.split(",");
//				LocalDate date = LocalDate.parse("28-" + lineArray[0], DateTimeFormatter.ofPattern("dd-MMM-yy"));
//				LocalDate date = LocalDate.parse("28-" + lineArray[0]);
				YearMonth month = YearMonth.parse(lineArray[0], DateTimeFormatter.ofPattern("LLL-yy", Locale.UK));
				Integer sales = Integer.parseInt(lineArray[1]);

				csvAsMap.put(month, sales);

//				fileList.add(fileLine);

//				String[] lineArray = fileLine.split(",");
//				String date = lineArray[0];
//				String sales = lineArray[1];
//				fileList.add(date);
//				fileList.add(sales);

				// Storing each line as formatted list element
//				String[] lineArray = fileLine.split(",");
//				String date = lineArray[0] + " -> " + lineArray[1];
//				fileList.add(date);

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

	public void outputFile() {

		String fileName = "";
//		if (courseName.equals("APMTH")) {
//			fileName = AppConstants.FILE_OUT1;
//		} else if (courseName.equals("COMPSCI")) {
//			fileName = AppConstants.FILE_OUT2;
//		} else if (courseName.equals("STAT")) {
//			fileName = AppConstants.FILE_OUT3;
//		} else {
//			System.out.println("Error printing file!");
//			System.out.println("Invalid course selection.");
//		}

		BufferedWriter writer = null;

		try {
			writer = new BufferedWriter(new FileWriter(fileName));

			writer.write(AppConstants.FILE_HEADER);

			{

			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				System.out.println("I/O Exception Error!");
				e.printStackTrace();
			}
		}
	}
}
