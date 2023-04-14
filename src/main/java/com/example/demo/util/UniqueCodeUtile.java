package com.example.demo.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Locale;
import java.util.Random;

public class UniqueCodeUtile {

    public String getUniqueCode() {
        return Long.toString(System.currentTimeMillis());
    }
    public static String getRandom(int ii) {
        String sRandom = "";
        int count = ii; // 난수 생성 갯수
        int a[] = new int[count];
        Random r = new Random();

        for(int i=0; i<count; i++){
            a[i] = r.nextInt(99) + 1; // 1 ~ 99까지의 난수
            for(int j=0; j<i; j++){
                if(a[i] == a[j]){
                    i--;
                }
            }
        }
        for(int i=0; i<count; i++){
            String sTemp = Integer.toString(a[i]);
            String sTemp2 = "";
            if(sTemp.length() == 1) {
                sTemp2 = String.format("%02d",a[i]); //1자리 자동으로 채움
            }else {
                sTemp2 = sTemp;
            }
            sRandom +=sTemp2;
        }
        return sRandom;
    }

    public static String getUniqueNumber(){
        String sUniqueNumber = null;
        Calendar cal = Calendar.getInstance();
        LocalDateTime current = LocalDateTime.now();
        WeekFields weeks = WeekFields.of(Locale.getDefault());
        String sYear = String.valueOf(current.get(weeks.weekBasedYear())).substring(2); // yy
        String sWeek = String.format("%02d", current.get(weeks.weekOfWeekBasedYear())); // week of year
        String sDayOfWeek = String.valueOf(cal.get(Calendar.DAY_OF_WEEK));
//        String randomStr  = getRandom(4);
//        String randomStr  = String.format("%04d", current.getNano());
//        sUniqueNumber = sYear + sWeek + "0" + sDayOfWeek + randomStr;

        sUniqueNumber = String.valueOf(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy"+sWeek+"0"+"HHmmssSSSS")));


        return sUniqueNumber;
    }

}
