package Visual_Calendar;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Visual_Calendar {
	public static void printVisualCalendar() throws ParseException {
		Date d1 = new Date();
		//星期五：6-----星期一：2
		Calendar d = new GregorianCalendar();
		d.setTime(d1);
		Calendar ca = new GregorianCalendar();
		ca.set(Calendar.YEAR, d.get(Calendar.YEAR));
		ca.set(Calendar.MONTH, d.get(Calendar.MONTH));
		ca.set(Calendar.DAY_OF_MONTH, 1);
		System.out.println("\t\t"+d.get(Calendar.YEAR)+"年\t"+d.get(Calendar.MONTH)+"月");
		System.out.print("日\t一\t二\t三\t四\t五\t六\n");
		for(int i=0;ca.get(Calendar.MONTH)==d.get(Calendar.MONTH);++i) {//0是星期天 
			if(i%7+1 != ca.get(Calendar.DAY_OF_WEEK)) {
				System.out.print("\t");
			} else {
				System.out.print(ca.get(Calendar.DAY_OF_MONTH)+(d.get(Calendar.DAY_OF_MONTH)==ca.get(Calendar.DAY_OF_MONTH) ? "*":"")+"\t");
				ca.add(Calendar.DAY_OF_MONTH, 1);
			}
			if(i>0&&i%7==6) {
				System.out.print("\n");
			}
		}
	}
	public static void main(String[] args) throws ParseException {
		printVisualCalendar();
	}
}
