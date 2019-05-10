/**
 * 
 */
package com.prediction.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

import com.prediction.app.model.Prediction;

/**
 * @author Shaju K
 *
 */

@Component
public class PredictionAppUtils {
	SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
	DateTimeFormatter dateTimeFormatter =
			DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	
	private Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
	
	private Date today() {
	    final Calendar cal = Calendar.getInstance();
	    return cal.getTime();
	}
	
	private String getYesterdayDateString() {
		 return dateFormatter.format(yesterday());
	}
	
	private String getTodayDateString() {
		 return dateFormatter.format(today());
	}
	
	public Date getBeginDateTimeToday(){
		String dateBegin=getTodayDateString()+" 00:00:00";
		try {
			return formatter.parse(dateBegin);
		} catch (ParseException e) {
			dateBegin=null;
		}
		return null;
	}
	
	public Date getEndDateTimeToday(){
		String dateEnd=getTodayDateString()+" 23:59:59";
		try {
			return formatter.parse(dateEnd);
		} catch (ParseException e) {
			dateEnd=null;
		}
		return null;
	}
	
	public Date getYesterdayBeginDateTime(){
		String dateBegin=getYesterdayDateString()+" 00:00:00";
		try {
			return formatter.parse(dateBegin);
		} catch (ParseException e) {
			dateBegin=null;
		}
		return null;
	}
	
	public Date getYesterdayEndDateTime(){
		String dateEnd=getYesterdayDateString()+" 23:59:59";
		try {
			return formatter.parse(dateEnd);
		} catch (ParseException e) {
			dateEnd=null;
		}
		return null;
	}
	
	public boolean isNullOrEmpty(String string) {
		return null==string || string.trim().isEmpty();
	}
	
	public String getStringFromDate(Date date){
		return formatter.format(date);
	}

	/**
	 * @param prediction
	 * @return
	 */
	public boolean isPredictionFreezed(Date matchDateTime) {
		Date oneHourBackDateTime=getTimeOneHourBack(matchDateTime);
		LocalDateTime oneHourBackLocalDateTime = LocalDateTime.parse(getStringFromDate(oneHourBackDateTime), dateTimeFormatter);
		return (LocalDateTime.now().isAfter(oneHourBackLocalDateTime));
	}

	/**
	 * @param matchDateTime
	 * @return
	 */
	private Date getTimeOneHourBack(Date matchDateTime) {
		 final Calendar cal = Calendar.getInstance();
		 cal.setTime(matchDateTime);
		 cal.add(Calendar.HOUR, -1);
		 Date oneHourBack = cal.getTime();
		return oneHourBack;
	}
}
