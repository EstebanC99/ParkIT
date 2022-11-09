package global;

import java.time.LocalDate;
import java.time.LocalTime;

public final class DateFormatter {

	public static String getFormattedDate(LocalDate date) {
		int dia = date.getDayOfMonth();
		int mes = date.getMonthValue();
		int anio = date.getYear();
		
		return String.join("-", String.valueOf(dia), String.valueOf(mes), String.valueOf(anio));
	}
	
	public static String getFormattedHour(LocalTime time) {
		int hora = time.getHour();
		int minutos = time.getMinute();
		
		return String.join(":", String.valueOf(hora), String.valueOf(minutos));
	}
}
