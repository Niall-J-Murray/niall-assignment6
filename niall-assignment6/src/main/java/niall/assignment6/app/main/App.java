package niall.assignment6.app.main;

import java.time.YearMonth;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import niall.assignment6.app.service.FileService;

public class App {

	public static void main(String[] args) {
		new App().execute();
	}

	private void execute() {

		FileService fileService = new FileService();

		String modelName = "Model 3";
		Map<YearMonth, Integer> model3Sales = fileService.readFile(AppConstants.FILE1_IN);
		salesByModelToConsole(modelName, model3Sales);

		modelName = "Model S";
		Map<YearMonth, Integer> modelSSales = fileService.readFile(AppConstants.FILE2_IN);
		salesByModelToConsole(modelName, modelSSales);

		modelName = "Model X";
		Map<YearMonth, Integer> modelXSales = fileService.readFile(AppConstants.FILE3_IN);
		salesByModelToConsole(modelName, modelXSales);
	}

	private void salesByModelToConsole(String modelName, Map<YearMonth, Integer> modelSales) {
		System.out.println("\n"
				+ modelName + " Yearly Sales Report"
				+ "\n---------------------------");
		salesByYear(modelSales, 2016, 2019);
		System.out.println();
		bestAndWorstMonths(modelName, modelSales);
	}

	private void salesByYear(Map<YearMonth, Integer> report, int firstYear, int lastYear) {

		while (firstYear <= lastYear) {
			final int currentYear = firstYear;
			Integer yearlySales = report.entrySet()
					.stream()
					.filter(entry -> entry != null)
					.filter(year -> year.getKey()
							.getYear() == currentYear)
					.map(sales -> sales.getValue())
					.collect(Collectors.summingInt(Integer::intValue));

			if (yearlySales > 0) {
				System.out.println(firstYear + " -> " + yearlySales);
			}
			firstYear++;
		}
	}

	private void bestAndWorstMonths(String modelName, Map<YearMonth, Integer> report) {

		Entry<YearMonth, Integer> bestMonth = report.entrySet()
				.stream()
				.filter(entry -> entry != null)
				.sorted((o1, o2) -> o2.getValue()
						.compareTo(o1.getValue()))
				.findFirst()
				.orElse(null);

		Entry<YearMonth, Integer> worstMonth = report.entrySet()
				.stream()
				.filter(entry -> entry != null)
				.sorted((o1, o2) -> o1.getValue()
						.compareTo(o2.getValue()))
				.findFirst()
				.orElse(null);

		System.out.println("The best month for " + modelName + " was: "
				+ bestMonth.getKey() + " (" + bestMonth.getValue() + " units sold)"
				+ "\nThe worst month for " + modelName + " was: "
				+ worstMonth.getKey() + " (" + worstMonth.getValue() + " units sold)");
	}
}
