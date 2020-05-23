package jp.co.bizrefine.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ajd4jp.AJD;
import ajd4jp.AJDException;
import ajd4jp.Holiday;
import ajd4jp.Month;
import jp.co.bizrefine.domain.model.Event;

public class MakeCalendarUtil {

	public static List<Event> getHoliDay(String targetYear) throws AJDException {
		List<Date> dateList = getDateList(targetYear);
		List<Month> monthList = new ArrayList<Month>();
		for (Date date : dateList) {
			String StringDate = new SimpleDateFormat("yyyyMMdd").format(date);
			int year = Integer.parseInt(StringDate.substring(0, 4));
			int month = Integer.parseInt(StringDate.substring(4, 6));
			monthList.add(new Month(year, month));
		}

		List<Event> events = new ArrayList<Event>();
		for (Month month : monthList) {
			for (AJD ajd : month.getDays()) {
				Event event = new Event();
				int day = ajd.getDay();
				Holiday holiday = Holiday.getHoliday(ajd);
				String offName = "";
				if (holiday != null) {
					offName = holiday.getName(ajd);
					String offYmd = String.valueOf(month.getYear()) + "-" + String.format("%02d", month.getMonth())
							+ "-" + String.format("%02d", day);

					event.setTitle(offName);
					event.setStart(offYmd);
					event.setAllDay(true);
					event.setResourceId("1:0:0");
					events.add(event);
				}
			}
		}
		return events;
	}

	public static List<Date> getDateList(String targetYear) {
		Date date = getDateFormat("yyyyMMdd", targetYear + "0101");
		List<Date> dateList = new ArrayList<Date>();

		while (dateList.size() < 12) {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			calendar.add(Calendar.MONTH, dateList.size() + 1);
			dateList.add(calendar.getTime());
		}

		return dateList;
	}

	public static Date getDateFormat(String format, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Date dateFormat = new Date();
		try {
			dateFormat = sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateFormat;
	}

	public static Calendar getCalrndarFormat(String format, String date) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		Calendar calendarFormat = Calendar.getInstance();
		try {
			calendarFormat.setTime(sdf.parse(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return calendarFormat;
	}

}
