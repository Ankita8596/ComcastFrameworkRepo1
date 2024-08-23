package com.comcast.crm.generic.webdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	public int getRandomNumer() {
		Random ram = new Random();
	int randomNum=ram.nextInt(5000);
	return randomNum;
	}
	
	public String getSystemDateyyyyMMdd() {
		Date dobj=new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		String date = sim.format(dobj);
		return date;
	}
	public String getRequiredDateyyyyMMdd(int days) {
		//Date dobj = new Date();
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		   cal.add(Calendar.DAY_OF_MONTH,days);
		   String reqDate = sim.format(cal.getTime());
		   return reqDate;
	}
}
