/*      */ package org.apache.xalan.lib;
/*      */ 
/*      */ import java.text.ParseException;
/*      */ import java.text.SimpleDateFormat;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.TimeZone;
/*      */ import org.apache.xpath.objects.XBoolean;
/*      */ import org.apache.xpath.objects.XNumber;
/*      */ import org.apache.xpath.objects.XObject;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class ExsltDatetime
/*      */ {
/*      */   static final String dt = "yyyy-MM-dd'T'HH:mm:ss";
/*      */   static final String d = "yyyy-MM-dd";
/*      */   static final String gym = "yyyy-MM";
/*      */   static final String gy = "yyyy";
/*      */   static final String gmd = "--MM-dd";
/*      */   static final String gm = "--MM--";
/*      */   static final String gd = "---dd";
/*      */   static final String t = "HH:mm:ss";
/*      */   static final String EMPTY_STR = "";
/*      */   
/*      */   public static String dateTime() {
/*   75 */     Calendar cal = Calendar.getInstance();
/*   76 */     Date datetime = cal.getTime();
/*      */     
/*   78 */     SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
/*      */     
/*   80 */     StringBuffer buff = new StringBuffer(dateFormat.format(datetime));
/*      */ 
/*      */     
/*   83 */     int offset = cal.get(15) + cal.get(16);
/*      */ 
/*      */     
/*   86 */     if (offset == 0) {
/*   87 */       buff.append("Z");
/*      */     }
/*      */     else {
/*      */       
/*   91 */       int hrs = offset / 3600000;
/*      */       
/*   93 */       int min = offset % 3600000;
/*   94 */       char posneg = (hrs < 0) ? '-' : '+';
/*   95 */       buff.append(posneg + formatDigits(hrs) + ':' + formatDigits(min));
/*      */     } 
/*   97 */     return buff.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String formatDigits(int q) {
/*  107 */     String dd = String.valueOf(Math.abs(q));
/*  108 */     return (dd.length() == 1) ? ('0' + dd) : dd;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String date(String datetimeIn) throws ParseException {
/*  135 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  136 */     String leader = edz[0];
/*  137 */     String datetime = edz[1];
/*  138 */     String zone = edz[2];
/*  139 */     if (datetime == null || zone == null) {
/*  140 */       return "";
/*      */     }
/*  142 */     String[] formatsIn = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
/*  143 */     String formatOut = "yyyy-MM-dd";
/*  144 */     Date date = testFormats(datetime, formatsIn);
/*  145 */     if (date == null) return "";
/*      */     
/*  147 */     SimpleDateFormat dateFormat = new SimpleDateFormat(formatOut);
/*  148 */     dateFormat.setLenient(false);
/*  149 */     String dateOut = dateFormat.format(date);
/*  150 */     if (dateOut.length() == 0) {
/*  151 */       return "";
/*      */     }
/*  153 */     return leader + dateOut + zone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String date() {
/*  162 */     String datetime = dateTime().toString();
/*  163 */     String date = datetime.substring(0, datetime.indexOf("T"));
/*  164 */     String zone = datetime.substring(getZoneStart(datetime));
/*  165 */     return date + zone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String time(String timeIn) throws ParseException {
/*  193 */     String[] edz = getEraDatetimeZone(timeIn);
/*  194 */     String time = edz[1];
/*  195 */     String zone = edz[2];
/*  196 */     if (time == null || zone == null) {
/*  197 */       return "";
/*      */     }
/*  199 */     String[] formatsIn = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
/*  200 */     String formatOut = "HH:mm:ss";
/*  201 */     Date date = testFormats(time, formatsIn);
/*  202 */     if (date == null) return ""; 
/*  203 */     SimpleDateFormat dateFormat = new SimpleDateFormat(formatOut);
/*  204 */     String out = dateFormat.format(date);
/*  205 */     return out + zone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String time() {
/*  213 */     String datetime = dateTime().toString();
/*  214 */     String time = datetime.substring(datetime.indexOf("T") + 1);
/*  215 */     String zone = datetime.substring(getZoneStart(datetime));
/*  216 */     return time + zone;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double year(String datetimeIn) throws ParseException {
/*  237 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  238 */     boolean ad = (edz[0].length() == 0);
/*  239 */     String datetime = edz[1];
/*  240 */     if (datetime == null) {
/*  241 */       return Double.NaN;
/*      */     }
/*  243 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy-MM", "yyyy" };
/*  244 */     double yr = getNumber(datetime, formats, 1);
/*  245 */     if (ad || yr == Double.NaN) {
/*  246 */       return yr;
/*      */     }
/*  248 */     return -yr;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double year() {
/*  256 */     Calendar cal = Calendar.getInstance();
/*  257 */     return cal.get(1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double monthInYear(String datetimeIn) throws ParseException {
/*  279 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  280 */     String datetime = edz[1];
/*  281 */     if (datetime == null) {
/*  282 */       return Double.NaN;
/*      */     }
/*  284 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy-MM", "--MM--", "--MM-dd" };
/*  285 */     return getNumber(datetime, formats, 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double monthInYear() {
/*  293 */     Calendar cal = Calendar.getInstance();
/*  294 */     return cal.get(2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double weekInYear(String datetimeIn) throws ParseException {
/*  313 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  314 */     String datetime = edz[1];
/*  315 */     if (datetime == null) {
/*  316 */       return Double.NaN;
/*      */     }
/*  318 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
/*  319 */     return getNumber(datetime, formats, 3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double weekInYear() {
/*  327 */     Calendar cal = Calendar.getInstance();
/*  328 */     return cal.get(3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double dayInYear(String datetimeIn) throws ParseException {
/*  347 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  348 */     String datetime = edz[1];
/*  349 */     if (datetime == null) {
/*  350 */       return Double.NaN;
/*      */     }
/*  352 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
/*  353 */     return getNumber(datetime, formats, 6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double dayInYear() {
/*  361 */     Calendar cal = Calendar.getInstance();
/*  362 */     return cal.get(6);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double dayInMonth(String datetimeIn) throws ParseException {
/*  384 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  385 */     String datetime = edz[1];
/*  386 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "--MM-dd", "---dd" };
/*  387 */     double day = getNumber(datetime, formats, 5);
/*  388 */     return day;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double dayInMonth() {
/*  396 */     Calendar cal = Calendar.getInstance();
/*  397 */     return cal.get(5);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double dayOfWeekInMonth(String datetimeIn) throws ParseException {
/*  417 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  418 */     String datetime = edz[1];
/*  419 */     if (datetime == null) {
/*  420 */       return Double.NaN;
/*      */     }
/*  422 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
/*  423 */     return getNumber(datetime, formats, 8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double dayOfWeekInMonth() {
/*  431 */     Calendar cal = Calendar.getInstance();
/*  432 */     return cal.get(8);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double dayInWeek(String datetimeIn) throws ParseException {
/*  453 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  454 */     String datetime = edz[1];
/*  455 */     if (datetime == null) {
/*  456 */       return Double.NaN;
/*      */     }
/*  458 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
/*  459 */     return getNumber(datetime, formats, 7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double dayInWeek() {
/*  467 */     Calendar cal = Calendar.getInstance();
/*  468 */     return cal.get(7);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double hourInDay(String datetimeIn) throws ParseException {
/*  487 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  488 */     String datetime = edz[1];
/*  489 */     if (datetime == null) {
/*  490 */       return Double.NaN;
/*      */     }
/*  492 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "HH:mm:ss" };
/*  493 */     return getNumber(datetime, formats, 11);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double hourInDay() {
/*  501 */     Calendar cal = Calendar.getInstance();
/*  502 */     return cal.get(11);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double minuteInHour(String datetimeIn) throws ParseException {
/*  521 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  522 */     String datetime = edz[1];
/*  523 */     if (datetime == null) {
/*  524 */       return Double.NaN;
/*      */     }
/*  526 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "HH:mm:ss" };
/*  527 */     return getNumber(datetime, formats, 12);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double minuteInHour() {
/*  535 */     Calendar cal = Calendar.getInstance();
/*  536 */     return cal.get(12);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double secondInMinute(String datetimeIn) throws ParseException {
/*  555 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  556 */     String datetime = edz[1];
/*  557 */     if (datetime == null) {
/*  558 */       return Double.NaN;
/*      */     }
/*  560 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "HH:mm:ss" };
/*  561 */     return getNumber(datetime, formats, 13);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static double secondInMinute() {
/*  569 */     Calendar cal = Calendar.getInstance();
/*  570 */     return cal.get(13);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static XObject leapYear(String datetimeIn) throws ParseException {
/*  591 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  592 */     String datetime = edz[1];
/*  593 */     if (datetime == null) {
/*  594 */       return (XObject)new XNumber(Double.NaN);
/*      */     }
/*  596 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy-MM", "yyyy" };
/*  597 */     double dbl = getNumber(datetime, formats, 1);
/*  598 */     if (dbl == Double.NaN)
/*  599 */       return (XObject)new XNumber(Double.NaN); 
/*  600 */     int yr = (int)dbl;
/*  601 */     return (XObject)new XBoolean((yr % 400 == 0 || (yr % 100 != 0 && yr % 4 == 0)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static boolean leapYear() {
/*  609 */     Calendar cal = Calendar.getInstance();
/*  610 */     int yr = cal.get(1);
/*  611 */     return (yr % 400 == 0 || (yr % 100 != 0 && yr % 4 == 0));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String monthName(String datetimeIn) throws ParseException {
/*  636 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  637 */     String datetime = edz[1];
/*  638 */     if (datetime == null) {
/*  639 */       return "";
/*      */     }
/*  641 */     String[] formatsIn = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy-MM", "--MM--" };
/*  642 */     String formatOut = "MMMM";
/*  643 */     return getNameOrAbbrev(datetimeIn, formatsIn, formatOut);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String monthName() {
/*  651 */     Calendar cal = Calendar.getInstance();
/*  652 */     String format = "MMMM";
/*  653 */     return getNameOrAbbrev(format);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String monthAbbreviation(String datetimeIn) throws ParseException {
/*  679 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  680 */     String datetime = edz[1];
/*  681 */     if (datetime == null) {
/*  682 */       return "";
/*      */     }
/*  684 */     String[] formatsIn = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd", "yyyy-MM", "--MM--" };
/*  685 */     String formatOut = "MMM";
/*  686 */     return getNameOrAbbrev(datetimeIn, formatsIn, formatOut);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String monthAbbreviation() {
/*  694 */     String format = "MMM";
/*  695 */     return getNameOrAbbrev(format);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String dayName(String datetimeIn) throws ParseException {
/*  719 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  720 */     String datetime = edz[1];
/*  721 */     if (datetime == null) {
/*  722 */       return "";
/*      */     }
/*  724 */     String[] formatsIn = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
/*  725 */     String formatOut = "EEEE";
/*  726 */     return getNameOrAbbrev(datetimeIn, formatsIn, formatOut);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String dayName() {
/*  734 */     String format = "EEEE";
/*  735 */     return getNameOrAbbrev(format);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String dayAbbreviation(String datetimeIn) throws ParseException {
/*  759 */     String[] edz = getEraDatetimeZone(datetimeIn);
/*  760 */     String datetime = edz[1];
/*  761 */     if (datetime == null) {
/*  762 */       return "";
/*      */     }
/*  764 */     String[] formatsIn = { "yyyy-MM-dd'T'HH:mm:ss", "yyyy-MM-dd" };
/*  765 */     String formatOut = "EEE";
/*  766 */     return getNameOrAbbrev(datetimeIn, formatsIn, formatOut);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String dayAbbreviation() {
/*  774 */     String format = "EEE";
/*  775 */     return getNameOrAbbrev(format);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String[] getEraDatetimeZone(String in) {
/*  785 */     String leader = "";
/*  786 */     String datetime = in;
/*  787 */     String zone = "";
/*  788 */     if (in.charAt(0) == '-' && !in.startsWith("--")) {
/*      */       
/*  790 */       leader = "-";
/*  791 */       datetime = in.substring(1);
/*      */     } 
/*  793 */     int z = getZoneStart(datetime);
/*  794 */     if (z > 0) {
/*      */       
/*  796 */       zone = datetime.substring(z);
/*  797 */       datetime = datetime.substring(0, z);
/*      */     }
/*  799 */     else if (z == -2) {
/*  800 */       zone = null;
/*      */     } 
/*  802 */     return new String[] { leader, datetime, zone };
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static int getZoneStart(String datetime) {
/*  813 */     if (datetime.indexOf("Z") == datetime.length() - 1)
/*  814 */       return datetime.length() - 1; 
/*  815 */     if (datetime.length() >= 6 && datetime.charAt(datetime.length() - 3) == ':' && (datetime.charAt(datetime.length() - 6) == '+' || datetime.charAt(datetime.length() - 6) == '-')) {
/*      */ 
/*      */       
/*      */       try { 
/*      */ 
/*      */ 
/*      */         
/*  822 */         SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
/*  823 */         dateFormat.setLenient(false);
/*  824 */         Date d = dateFormat.parse(datetime.substring(datetime.length() - 5));
/*  825 */         return datetime.length() - 6; } catch (ParseException pe)
/*      */       
/*      */       { 
/*      */         
/*  829 */         System.out.println("ParseException " + pe.getErrorOffset());
/*  830 */         return -2; }
/*      */     
/*      */     }
/*      */     
/*  834 */     return -1;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static Date testFormats(String in, String[] formats) throws ParseException {
/*  844 */     for (int i = 0; i < formats.length; i++) {
/*      */ 
/*      */ 
/*      */       
/*  848 */       try { SimpleDateFormat dateFormat = new SimpleDateFormat(formats[i]);
/*  849 */         dateFormat.setLenient(false);
/*  850 */         return dateFormat.parse(in); } catch (ParseException parseException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  856 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static double getNumber(String in, String[] formats, int calField) throws ParseException {
/*  867 */     Calendar cal = Calendar.getInstance();
/*  868 */     cal.setLenient(false);
/*      */     
/*  870 */     Date date = testFormats(in, formats);
/*  871 */     if (date == null) return Double.NaN; 
/*  872 */     cal.setTime(date);
/*  873 */     return cal.get(calField);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getNameOrAbbrev(String in, String[] formatsIn, String formatOut) throws ParseException {
/*  884 */     for (int i = 0; i < formatsIn.length; i++) {
/*      */ 
/*      */ 
/*      */       
/*  888 */       try { SimpleDateFormat dateFormat = new SimpleDateFormat(formatsIn[i]);
/*  889 */         dateFormat.setLenient(false);
/*  890 */         Date dt = dateFormat.parse(in);
/*  891 */         dateFormat.applyPattern(formatOut);
/*  892 */         return dateFormat.format(dt); } catch (ParseException parseException) {}
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  898 */     return "";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String getNameOrAbbrev(String format) {
/*  906 */     Calendar cal = Calendar.getInstance();
/*  907 */     SimpleDateFormat dateFormat = new SimpleDateFormat(format);
/*  908 */     return dateFormat.format(cal.getTime());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static String formatDate(String dateTime, String pattern) {
/*      */     TimeZone timeZone;
/*  949 */     String str1, yearSymbols = "Gy";
/*  950 */     String monthSymbols = "M";
/*  951 */     String daySymbols = "dDEFwW";
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  957 */     if (dateTime.endsWith("Z") || dateTime.endsWith("z")) {
/*      */       
/*  959 */       timeZone = TimeZone.getTimeZone("GMT");
/*  960 */       dateTime = dateTime.substring(0, dateTime.length() - 1) + "GMT";
/*  961 */       str1 = "z";
/*      */     }
/*  963 */     else if (dateTime.length() >= 6 && dateTime.charAt(dateTime.length() - 3) == ':' && (dateTime.charAt(dateTime.length() - 6) == '+' || dateTime.charAt(dateTime.length() - 6) == '-')) {
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  968 */       String offset = dateTime.substring(dateTime.length() - 6);
/*      */       
/*  970 */       if ("+00:00".equals(offset) || "-00:00".equals(offset)) {
/*      */         
/*  972 */         timeZone = TimeZone.getTimeZone("GMT");
/*      */       }
/*      */       else {
/*      */         
/*  976 */         timeZone = TimeZone.getTimeZone("GMT" + offset);
/*      */       } 
/*  978 */       str1 = "z";
/*      */ 
/*      */       
/*  981 */       dateTime = dateTime.substring(0, dateTime.length() - 6) + "GMT" + offset;
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  986 */       timeZone = TimeZone.getDefault();
/*  987 */       str1 = "";
/*      */     } 
/*      */ 
/*      */     
/*  991 */     String[] formats = { "yyyy-MM-dd'T'HH:mm:ss" + str1, "yyyy-MM-dd", "yyyy-MM", "yyyy" };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  998 */     try { SimpleDateFormat inFormat = new SimpleDateFormat("HH:mm:ss" + str1);
/*  999 */       inFormat.setLenient(false);
/* 1000 */       Date d = inFormat.parse(dateTime);
/* 1001 */       SimpleDateFormat outFormat = new SimpleDateFormat(strip("GyMdDEFwW", pattern));
/*      */       
/* 1003 */       outFormat.setTimeZone(timeZone);
/* 1004 */       return outFormat.format(d); } catch (ParseException parseException)
/*      */     
/*      */     { 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1011 */       for (int i = 0; i < formats.length; i++) {
/*      */ 
/*      */ 
/*      */         
/* 1015 */         try { SimpleDateFormat inFormat = new SimpleDateFormat(formats[i]);
/* 1016 */           inFormat.setLenient(false);
/* 1017 */           Date d = inFormat.parse(dateTime);
/* 1018 */           SimpleDateFormat outFormat = new SimpleDateFormat(pattern);
/* 1019 */           outFormat.setTimeZone(timeZone);
/* 1020 */           return outFormat.format(d); } catch (ParseException parseException1) {}
/*      */       } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1033 */       try { SimpleDateFormat inFormat = new SimpleDateFormat("--MM-dd");
/* 1034 */         inFormat.setLenient(false);
/* 1035 */         Date d = inFormat.parse(dateTime);
/* 1036 */         SimpleDateFormat outFormat = new SimpleDateFormat(strip("Gy", pattern));
/* 1037 */         outFormat.setTimeZone(timeZone);
/* 1038 */         return outFormat.format(d); } catch (ParseException parseException1)
/*      */       
/*      */       { 
/*      */         
/*      */         try { 
/*      */ 
/*      */           
/* 1045 */           SimpleDateFormat inFormat = new SimpleDateFormat("--MM--");
/* 1046 */           inFormat.setLenient(false);
/* 1047 */           Date d = inFormat.parse(dateTime);
/* 1048 */           SimpleDateFormat outFormat = new SimpleDateFormat(strip("Gy", pattern));
/* 1049 */           outFormat.setTimeZone(timeZone);
/* 1050 */           return outFormat.format(d); } catch (ParseException parseException2)
/*      */         
/*      */         { 
/*      */           
/*      */           try { 
/*      */ 
/*      */             
/* 1057 */             SimpleDateFormat inFormat = new SimpleDateFormat("---dd");
/* 1058 */             inFormat.setLenient(false);
/* 1059 */             Date d = inFormat.parse(dateTime);
/* 1060 */             SimpleDateFormat outFormat = new SimpleDateFormat(strip("GyM", pattern));
/* 1061 */             outFormat.setTimeZone(timeZone);
/* 1062 */             return outFormat.format(d); } catch (ParseException parseException3)
/*      */           
/*      */           { 
/*      */ 
/*      */             
/* 1067 */             return ""; }
/*      */            }
/*      */          }
/*      */        }
/*      */   
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static String strip(String symbols, String pattern) {
/* 1078 */     int quoteSemaphore = 0;
/* 1079 */     int i = 0;
/* 1080 */     StringBuffer result = new StringBuffer(pattern.length());
/*      */     
/* 1082 */     while (i < pattern.length()) {
/*      */       
/* 1084 */       char ch = pattern.charAt(i);
/* 1085 */       if (ch == '\'') {
/*      */ 
/*      */ 
/*      */         
/* 1089 */         int endQuote = pattern.indexOf('\'', i + 1);
/* 1090 */         if (endQuote == -1)
/*      */         {
/* 1092 */           endQuote = pattern.length();
/*      */         }
/* 1094 */         result.append(pattern.substring(i, endQuote));
/* 1095 */         i = endQuote++; continue;
/*      */       } 
/* 1097 */       if (symbols.indexOf(ch) > -1) {
/*      */ 
/*      */         
/* 1100 */         i++;
/*      */         
/*      */         continue;
/*      */       } 
/* 1104 */       result.append(ch);
/* 1105 */       i++;
/*      */     } 
/*      */     
/* 1108 */     return result.toString();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/lib/ExsltDatetime.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */