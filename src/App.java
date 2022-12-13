import javax.swing.*;

public class App {
	public static String[] monthsOpt = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sept", "Oct", "Nov",
			"Dec" };
	public static JOptionPane window = new JOptionPane();
	public static int day;

	public static Integer[] daysOnMonth(int range) {
		Integer[] arr = new Integer[range];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i + 1;
		}

		return arr;
	}

	public static int chosenDay(int month, int year) {
		if (month == 12) {
			if (year % 4 == 0 || year % 100 == 0 && year % 400 == 0) {
				day = window.showOptionDialog(null, "Choose the Day", "Day ", window.DEFAULT_OPTION,
						window.INFORMATION_MESSAGE, null, daysOnMonth(28), null);
			} else {
				day = window.showOptionDialog(null, "Choose the Day", "Day ", window.DEFAULT_OPTION,
						window.INFORMATION_MESSAGE, null, daysOnMonth(29), null);
			}

		} else if (month % 2 != 0) {
			day = window.showOptionDialog(null, "Choose the Day", "Day ", window.DEFAULT_OPTION,
					window.INFORMATION_MESSAGE, null, daysOnMonth(31), null);

		} else {
			day = window.showOptionDialog(null, "Choose the Day", "Day ", window.DEFAULT_OPTION,
					window.INFORMATION_MESSAGE, null, daysOnMonth(30), null);

		}
		return day + 1;
	}

	public static int solution(int day, int month, int year) {
		String tempYear = Integer.toString(year) + "a";
		int prefixYear = Integer.parseInt(tempYear.substring(0, 2));
		int suffixYear = Integer.parseInt(tempYear.substring(2, 4));
		if (month >= 11) {
			suffixYear -= 1;
		}
		int firstVal = ((13 * month) - 1) / 5;
		int secondVal = suffixYear / 4;
		int thirdVal = prefixYear / 4;
		int lastVal = prefixYear * 2;
		int answer = firstVal + secondVal + thirdVal + day + suffixYear - lastVal;
		return answer % 7;
	}

	public static String finalAnswer(int answer) {
		String[] dayOfWeek = { "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday" };
		if (answer <= -1) {
			answer += 7;
		}
		return dayOfWeek[answer];
	}

	public static int monthConf(int month) {
		return month >= 2 ? month - 1 : month + 11;
	}

	public static void main(String[] args) throws Exception {
		boolean flag1 = true;
		boolean flag2 = true;
		int year = 0;

		int startApp = window.showConfirmDialog(null, "Do you awant to proceed? ", "Day Calculator ",
				window.YES_NO_OPTION);
		if (startApp == window.YES_OPTION) {

			while (flag1) {
				try {
					year = Integer.parseInt(window.showInputDialog("Choose a Year from 1900 - 2030"));
				} catch (Exception e) {
					window.showMessageDialog(null, "Your input is not a number!! ", "ERROR", window.ERROR_MESSAGE);
				}

				if (year >= 1900 && year <= 2030) {
					int month = window.showOptionDialog(null, "Choose the Month", "Month ", window.DEFAULT_OPTION,
							window.INFORMATION_MESSAGE, null, monthsOpt, monthsOpt[10]);
					int useMonth = month;

					month = monthConf(month);
					day = chosenDay(month, year);
					String finalDayOfWeek = finalAnswer(solution(day, month, year)); // Used zellers congruence for
																						// solution

					window.showMessageDialog(null,
							monthsOpt[useMonth] + " " + day + ", " + year + ": " + finalDayOfWeek.toUpperCase(),
							"Day of the week",
							window.INFORMATION_MESSAGE);

					flag1 = false;
				} else {
					window.showMessageDialog(null, "Year is not with in the range!! ", "WARNING",
							window.WARNING_MESSAGE);

				}

			}

		} else {
			window.showMessageDialog(null, "You quitted out of the program!! ", "QUIT", window.INFORMATION_MESSAGE);
		}
	}
}
