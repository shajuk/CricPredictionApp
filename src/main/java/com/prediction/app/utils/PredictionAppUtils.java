/**
 * 
 */
package com.prediction.app.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

/**
 * @author Shaju K
 *
 */

@Component
public class PredictionAppUtils {
	SimpleDateFormat formatter=new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
	SimpleDateFormat dateFormatter=new SimpleDateFormat("dd/MM/yyyy");
	
	private Date yesterday() {
	    final Calendar cal = Calendar.getInstance();
	    cal.add(Calendar.DATE, -1);
	    return cal.getTime();
	}
	
	private String getYesterdayDateString() {
		 return dateFormatter.format(yesterday());
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
		String dateBegin=getYesterdayDateString()+" 23:59:59";
		try {
			return formatter.parse(dateBegin);
		} catch (ParseException e) {
			dateBegin=null;
		}
		return null;
	}
	
	public boolean isNullOrEmpty(String string) {
		return null==string || string.trim().isEmpty();
	}
}
