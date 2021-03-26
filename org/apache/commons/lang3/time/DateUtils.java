/*      */ package org.apache.commons.lang3.time;
/*      */ 
/*      */ import java.text.ParseException;
/*      */ import java.text.ParsePosition;
/*      */ import java.util.Calendar;
/*      */ import java.util.Date;
/*      */ import java.util.Iterator;
/*      */ import java.util.Locale;
/*      */ import java.util.NoSuchElementException;
/*      */ import java.util.TimeZone;
/*      */ import java.util.concurrent.TimeUnit;
/*      */ import org.apache.commons.lang3.Validate;
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
/*      */ public class DateUtils
/*      */ {
/*      */   public static final long MILLIS_PER_SECOND = 1000L;
/*      */   public static final long MILLIS_PER_MINUTE = 60000L;
/*      */   public static final long MILLIS_PER_HOUR = 3600000L;
/*      */   public static final long MILLIS_PER_DAY = 86400000L;
/*      */   public static final int SEMI_MONTH = 1001;
/*   83 */   private static final int[][] fields = new int[][] { { 14 }, { 13 }, { 12 }, { 11, 10 }, { 5, 5, 9 }, { 2, 1001 }, { 1 }, { 0 } };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int RANGE_WEEK_SUNDAY = 1;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int RANGE_WEEK_MONDAY = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int RANGE_WEEK_RELATIVE = 3;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int RANGE_WEEK_CENTER = 4;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int RANGE_MONTH_SUNDAY = 5;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final int RANGE_MONTH_MONDAY = 6;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private enum ModifyType
/*      */   {
/*  127 */     TRUNCATE,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  132 */     ROUND,
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  137 */     CEILING;
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
/*      */   public static boolean isSameDay(Date date1, Date date2) {
/*  167 */     if (date1 == null || date2 == null) {
/*  168 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  170 */     Calendar cal1 = Calendar.getInstance();
/*  171 */     cal1.setTime(date1);
/*  172 */     Calendar cal2 = Calendar.getInstance();
/*  173 */     cal2.setTime(date2);
/*  174 */     return isSameDay(cal1, cal2);
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
/*      */   public static boolean isSameDay(Calendar cal1, Calendar cal2) {
/*  191 */     if (cal1 == null || cal2 == null) {
/*  192 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  194 */     return (cal1.get(0) == cal2.get(0) && cal1
/*  195 */       .get(1) == cal2.get(1) && cal1
/*  196 */       .get(6) == cal2.get(6));
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
/*      */   public static boolean isSameInstant(Date date1, Date date2) {
/*  212 */     if (date1 == null || date2 == null) {
/*  213 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  215 */     return (date1.getTime() == date2.getTime());
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
/*      */   public static boolean isSameInstant(Calendar cal1, Calendar cal2) {
/*  230 */     if (cal1 == null || cal2 == null) {
/*  231 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  233 */     return (cal1.getTime().getTime() == cal2.getTime().getTime());
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
/*      */   public static boolean isSameLocalTime(Calendar cal1, Calendar cal2) {
/*  250 */     if (cal1 == null || cal2 == null) {
/*  251 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  253 */     return (cal1.get(14) == cal2.get(14) && cal1
/*  254 */       .get(13) == cal2.get(13) && cal1
/*  255 */       .get(12) == cal2.get(12) && cal1
/*  256 */       .get(11) == cal2.get(11) && cal1
/*  257 */       .get(6) == cal2.get(6) && cal1
/*  258 */       .get(1) == cal2.get(1) && cal1
/*  259 */       .get(0) == cal2.get(0) && cal1
/*  260 */       .getClass() == cal2.getClass());
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
/*      */   public static Date parseDate(String str, String... parsePatterns) throws ParseException {
/*  279 */     return parseDate(str, null, parsePatterns);
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
/*      */   public static Date parseDate(String str, Locale locale, String... parsePatterns) throws ParseException {
/*  302 */     return parseDateWithLeniency(str, locale, parsePatterns, true);
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
/*      */   public static Date parseDateStrictly(String str, String... parsePatterns) throws ParseException {
/*  322 */     return parseDateStrictly(str, null, parsePatterns);
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
/*      */   public static Date parseDateStrictly(String str, Locale locale, String... parsePatterns) throws ParseException {
/*  344 */     return parseDateWithLeniency(str, locale, parsePatterns, false);
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
/*      */   private static Date parseDateWithLeniency(String str, Locale locale, String[] parsePatterns, boolean lenient) throws ParseException {
/*  366 */     if (str == null || parsePatterns == null) {
/*  367 */       throw new IllegalArgumentException("Date and Patterns must not be null");
/*      */     }
/*      */     
/*  370 */     TimeZone tz = TimeZone.getDefault();
/*  371 */     Locale lcl = (locale == null) ? Locale.getDefault() : locale;
/*  372 */     ParsePosition pos = new ParsePosition(0);
/*  373 */     Calendar calendar = Calendar.getInstance(tz, lcl);
/*  374 */     calendar.setLenient(lenient);
/*      */     
/*  376 */     for (String parsePattern : parsePatterns) {
/*  377 */       FastDateParser fdp = new FastDateParser(parsePattern, tz, lcl);
/*  378 */       calendar.clear();
/*      */       try {
/*  380 */         if (fdp.parse(str, pos, calendar) && pos.getIndex() == str.length()) {
/*  381 */           return calendar.getTime();
/*      */         }
/*  383 */       } catch (IllegalArgumentException illegalArgumentException) {}
/*      */ 
/*      */       
/*  386 */       pos.setIndex(0);
/*      */     } 
/*  388 */     throw new ParseException("Unable to parse the date: " + str, -1);
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
/*      */   public static Date addYears(Date date, int amount) {
/*  402 */     return add(date, 1, amount);
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
/*      */   public static Date addMonths(Date date, int amount) {
/*  416 */     return add(date, 2, amount);
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
/*      */   public static Date addWeeks(Date date, int amount) {
/*  430 */     return add(date, 3, amount);
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
/*      */   public static Date addDays(Date date, int amount) {
/*  444 */     return add(date, 5, amount);
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
/*      */   public static Date addHours(Date date, int amount) {
/*  458 */     return add(date, 11, amount);
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
/*      */   public static Date addMinutes(Date date, int amount) {
/*  472 */     return add(date, 12, amount);
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
/*      */   public static Date addSeconds(Date date, int amount) {
/*  486 */     return add(date, 13, amount);
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
/*      */   public static Date addMilliseconds(Date date, int amount) {
/*  500 */     return add(date, 14, amount);
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
/*      */   private static Date add(Date date, int calendarField, int amount) {
/*  515 */     validateDateNotNull(date);
/*  516 */     Calendar c = Calendar.getInstance();
/*  517 */     c.setTime(date);
/*  518 */     c.add(calendarField, amount);
/*  519 */     return c.getTime();
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
/*      */   public static Date setYears(Date date, int amount) {
/*  534 */     return set(date, 1, amount);
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
/*      */   public static Date setMonths(Date date, int amount) {
/*  549 */     return set(date, 2, amount);
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
/*      */   public static Date setDays(Date date, int amount) {
/*  564 */     return set(date, 5, amount);
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
/*      */   public static Date setHours(Date date, int amount) {
/*  580 */     return set(date, 11, amount);
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
/*      */   public static Date setMinutes(Date date, int amount) {
/*  595 */     return set(date, 12, amount);
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
/*      */   public static Date setSeconds(Date date, int amount) {
/*  610 */     return set(date, 13, amount);
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
/*      */   public static Date setMilliseconds(Date date, int amount) {
/*  625 */     return set(date, 14, amount);
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
/*      */   private static Date set(Date date, int calendarField, int amount) {
/*  642 */     validateDateNotNull(date);
/*      */     
/*  644 */     Calendar c = Calendar.getInstance();
/*  645 */     c.setLenient(false);
/*  646 */     c.setTime(date);
/*  647 */     c.set(calendarField, amount);
/*  648 */     return c.getTime();
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
/*      */   public static Calendar toCalendar(Date date) {
/*  661 */     Calendar c = Calendar.getInstance();
/*  662 */     c.setTime(date);
/*  663 */     return c;
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
/*      */   public static Calendar toCalendar(Date date, TimeZone tz) {
/*  675 */     Calendar c = Calendar.getInstance(tz);
/*  676 */     c.setTime(date);
/*  677 */     return c;
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
/*      */   public static Date round(Date date, int field) {
/*  708 */     validateDateNotNull(date);
/*  709 */     Calendar gval = Calendar.getInstance();
/*  710 */     gval.setTime(date);
/*  711 */     modify(gval, field, ModifyType.ROUND);
/*  712 */     return gval.getTime();
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
/*      */   public static Calendar round(Calendar date, int field) {
/*  743 */     if (date == null) {
/*  744 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  746 */     Calendar rounded = (Calendar)date.clone();
/*  747 */     modify(rounded, field, ModifyType.ROUND);
/*  748 */     return rounded;
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
/*      */   public static Date round(Object date, int field) {
/*  780 */     if (date == null) {
/*  781 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  783 */     if (date instanceof Date)
/*  784 */       return round((Date)date, field); 
/*  785 */     if (date instanceof Calendar) {
/*  786 */       return round((Calendar)date, field).getTime();
/*      */     }
/*  788 */     throw new ClassCastException("Could not round " + date);
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
/*      */   public static Date truncate(Date date, int field) {
/*  809 */     validateDateNotNull(date);
/*  810 */     Calendar gval = Calendar.getInstance();
/*  811 */     gval.setTime(date);
/*  812 */     modify(gval, field, ModifyType.TRUNCATE);
/*  813 */     return gval.getTime();
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
/*      */   public static Calendar truncate(Calendar date, int field) {
/*  832 */     if (date == null) {
/*  833 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  835 */     Calendar truncated = (Calendar)date.clone();
/*  836 */     modify(truncated, field, ModifyType.TRUNCATE);
/*  837 */     return truncated;
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
/*      */   public static Date truncate(Object date, int field) {
/*  857 */     if (date == null) {
/*  858 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  860 */     if (date instanceof Date)
/*  861 */       return truncate((Date)date, field); 
/*  862 */     if (date instanceof Calendar) {
/*  863 */       return truncate((Calendar)date, field).getTime();
/*      */     }
/*  865 */     throw new ClassCastException("Could not truncate " + date);
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
/*      */   public static Date ceiling(Date date, int field) {
/*  887 */     validateDateNotNull(date);
/*  888 */     Calendar gval = Calendar.getInstance();
/*  889 */     gval.setTime(date);
/*  890 */     modify(gval, field, ModifyType.CEILING);
/*  891 */     return gval.getTime();
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
/*      */   public static Calendar ceiling(Calendar date, int field) {
/*  911 */     if (date == null) {
/*  912 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  914 */     Calendar ceiled = (Calendar)date.clone();
/*  915 */     modify(ceiled, field, ModifyType.CEILING);
/*  916 */     return ceiled;
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
/*      */   public static Date ceiling(Object date, int field) {
/*  937 */     if (date == null) {
/*  938 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*  940 */     if (date instanceof Date)
/*  941 */       return ceiling((Date)date, field); 
/*  942 */     if (date instanceof Calendar) {
/*  943 */       return ceiling((Calendar)date, field).getTime();
/*      */     }
/*  945 */     throw new ClassCastException("Could not find ceiling of for type: " + date.getClass());
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
/*      */   private static void modify(Calendar val, int field, ModifyType modType) {
/*  959 */     if (val.get(1) > 280000000) {
/*  960 */       throw new ArithmeticException("Calendar value too large for accurate calculations");
/*      */     }
/*      */     
/*  963 */     if (field == 14) {
/*      */       return;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  973 */     Date date = val.getTime();
/*  974 */     long time = date.getTime();
/*  975 */     boolean done = false;
/*      */ 
/*      */     
/*  978 */     int millisecs = val.get(14);
/*  979 */     if (ModifyType.TRUNCATE == modType || millisecs < 500) {
/*  980 */       time -= millisecs;
/*      */     }
/*  982 */     if (field == 13) {
/*  983 */       done = true;
/*      */     }
/*      */ 
/*      */     
/*  987 */     int seconds = val.get(13);
/*  988 */     if (!done && (ModifyType.TRUNCATE == modType || seconds < 30)) {
/*  989 */       time -= seconds * 1000L;
/*      */     }
/*  991 */     if (field == 12) {
/*  992 */       done = true;
/*      */     }
/*      */ 
/*      */     
/*  996 */     int minutes = val.get(12);
/*  997 */     if (!done && (ModifyType.TRUNCATE == modType || minutes < 30)) {
/*  998 */       time -= minutes * 60000L;
/*      */     }
/*      */ 
/*      */     
/* 1002 */     if (date.getTime() != time) {
/* 1003 */       date.setTime(time);
/* 1004 */       val.setTime(date);
/*      */     } 
/*      */ 
/*      */     
/* 1008 */     boolean roundUp = false;
/* 1009 */     for (int[] aField : fields) {
/* 1010 */       for (int element : aField) {
/* 1011 */         if (element == field) {
/*      */           
/* 1013 */           if (modType == ModifyType.CEILING || (modType == ModifyType.ROUND && roundUp)) {
/* 1014 */             if (field == 1001) {
/*      */ 
/*      */ 
/*      */               
/* 1018 */               if (val.get(5) == 1) {
/* 1019 */                 val.add(5, 15);
/*      */               } else {
/* 1021 */                 val.add(5, -15);
/* 1022 */                 val.add(2, 1);
/*      */               }
/*      */             
/* 1025 */             } else if (field == 9) {
/*      */ 
/*      */ 
/*      */               
/* 1029 */               if (val.get(11) == 0) {
/* 1030 */                 val.add(11, 12);
/*      */               } else {
/* 1032 */                 val.add(11, -12);
/* 1033 */                 val.add(5, 1);
/*      */               }
/*      */             
/*      */             }
/*      */             else {
/*      */               
/* 1039 */               val.add(aField[0], 1);
/*      */             } 
/*      */           }
/*      */           
/*      */           return;
/*      */         } 
/*      */       } 
/* 1046 */       int offset = 0;
/* 1047 */       boolean offsetSet = false;
/*      */       
/* 1049 */       switch (field) {
/*      */         case 1001:
/* 1051 */           if (aField[0] == 5) {
/*      */ 
/*      */ 
/*      */             
/* 1055 */             offset = val.get(5) - 1;
/*      */ 
/*      */             
/* 1058 */             if (offset >= 15) {
/* 1059 */               offset -= 15;
/*      */             }
/*      */             
/* 1062 */             roundUp = (offset > 7);
/* 1063 */             offsetSet = true;
/*      */           } 
/*      */           break;
/*      */         case 9:
/* 1067 */           if (aField[0] == 11) {
/*      */ 
/*      */             
/* 1070 */             offset = val.get(11);
/* 1071 */             if (offset >= 12) {
/* 1072 */               offset -= 12;
/*      */             }
/* 1074 */             roundUp = (offset >= 6);
/* 1075 */             offsetSet = true;
/*      */           } 
/*      */           break;
/*      */       } 
/*      */ 
/*      */       
/* 1081 */       if (!offsetSet) {
/* 1082 */         int min = val.getActualMinimum(aField[0]);
/* 1083 */         int max = val.getActualMaximum(aField[0]);
/*      */         
/* 1085 */         offset = val.get(aField[0]) - min;
/*      */         
/* 1087 */         roundUp = (offset > (max - min) / 2);
/*      */       } 
/*      */       
/* 1090 */       if (offset != 0) {
/* 1091 */         val.set(aField[0], val.get(aField[0]) - offset);
/*      */       }
/*      */     } 
/* 1094 */     throw new IllegalArgumentException("The field " + field + " is not supported");
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
/*      */   public static Iterator<Calendar> iterator(Date focus, int rangeStyle) {
/* 1124 */     validateDateNotNull(focus);
/* 1125 */     Calendar gval = Calendar.getInstance();
/* 1126 */     gval.setTime(focus);
/* 1127 */     return iterator(gval, rangeStyle);
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
/*      */   public static Iterator<Calendar> iterator(Calendar focus, int rangeStyle) {
/* 1155 */     if (focus == null) {
/* 1156 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/* 1158 */     Calendar start = null;
/* 1159 */     Calendar end = null;
/* 1160 */     int startCutoff = 1;
/* 1161 */     int endCutoff = 7;
/* 1162 */     switch (rangeStyle) {
/*      */       
/*      */       case 5:
/*      */       case 6:
/* 1166 */         start = truncate(focus, 2);
/*      */         
/* 1168 */         end = (Calendar)start.clone();
/* 1169 */         end.add(2, 1);
/* 1170 */         end.add(5, -1);
/*      */         
/* 1172 */         if (rangeStyle == 6) {
/* 1173 */           startCutoff = 2;
/* 1174 */           endCutoff = 1;
/*      */         } 
/*      */         break;
/*      */       
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/* 1182 */         start = truncate(focus, 5);
/* 1183 */         end = truncate(focus, 5);
/* 1184 */         switch (rangeStyle) {
/*      */ 
/*      */ 
/*      */           
/*      */           case 2:
/* 1189 */             startCutoff = 2;
/* 1190 */             endCutoff = 1;
/*      */             break;
/*      */           case 3:
/* 1193 */             startCutoff = focus.get(7);
/* 1194 */             endCutoff = startCutoff - 1;
/*      */             break;
/*      */           case 4:
/* 1197 */             startCutoff = focus.get(7) - 3;
/* 1198 */             endCutoff = focus.get(7) + 3;
/*      */             break;
/*      */         } 
/*      */         
/*      */         break;
/*      */       
/*      */       default:
/* 1205 */         throw new IllegalArgumentException("The range style " + rangeStyle + " is not valid.");
/*      */     } 
/* 1207 */     if (startCutoff < 1) {
/* 1208 */       startCutoff += 7;
/*      */     }
/* 1210 */     if (startCutoff > 7) {
/* 1211 */       startCutoff -= 7;
/*      */     }
/* 1213 */     if (endCutoff < 1) {
/* 1214 */       endCutoff += 7;
/*      */     }
/* 1216 */     if (endCutoff > 7) {
/* 1217 */       endCutoff -= 7;
/*      */     }
/* 1219 */     while (start.get(7) != startCutoff) {
/* 1220 */       start.add(5, -1);
/*      */     }
/* 1222 */     while (end.get(7) != endCutoff) {
/* 1223 */       end.add(5, 1);
/*      */     }
/* 1225 */     return new DateIterator(start, end);
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
/*      */   public static Iterator<?> iterator(Object focus, int rangeStyle) {
/* 1245 */     if (focus == null) {
/* 1246 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/* 1248 */     if (focus instanceof Date)
/* 1249 */       return iterator((Date)focus, rangeStyle); 
/* 1250 */     if (focus instanceof Calendar) {
/* 1251 */       return iterator((Calendar)focus, rangeStyle);
/*      */     }
/* 1253 */     throw new ClassCastException("Could not iterate based on " + focus);
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
/*      */   public static long getFragmentInMilliseconds(Date date, int fragment) {
/* 1289 */     return getFragment(date, fragment, TimeUnit.MILLISECONDS);
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
/*      */   public static long getFragmentInSeconds(Date date, int fragment) {
/* 1327 */     return getFragment(date, fragment, TimeUnit.SECONDS);
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
/*      */   public static long getFragmentInMinutes(Date date, int fragment) {
/* 1365 */     return getFragment(date, fragment, TimeUnit.MINUTES);
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
/*      */   public static long getFragmentInHours(Date date, int fragment) {
/* 1403 */     return getFragment(date, fragment, TimeUnit.HOURS);
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
/*      */   public static long getFragmentInDays(Date date, int fragment) {
/* 1441 */     return getFragment(date, fragment, TimeUnit.DAYS);
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
/*      */   public static long getFragmentInMilliseconds(Calendar calendar, int fragment) {
/* 1479 */     return getFragment(calendar, fragment, TimeUnit.MILLISECONDS);
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
/*      */   public static long getFragmentInSeconds(Calendar calendar, int fragment) {
/* 1516 */     return getFragment(calendar, fragment, TimeUnit.SECONDS);
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
/*      */   public static long getFragmentInMinutes(Calendar calendar, int fragment) {
/* 1554 */     return getFragment(calendar, fragment, TimeUnit.MINUTES);
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
/*      */   public static long getFragmentInHours(Calendar calendar, int fragment) {
/* 1592 */     return getFragment(calendar, fragment, TimeUnit.HOURS);
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
/*      */   public static long getFragmentInDays(Calendar calendar, int fragment) {
/* 1632 */     return getFragment(calendar, fragment, TimeUnit.DAYS);
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
/*      */   private static long getFragment(Date date, int fragment, TimeUnit unit) {
/* 1647 */     validateDateNotNull(date);
/* 1648 */     Calendar calendar = Calendar.getInstance();
/* 1649 */     calendar.setTime(date);
/* 1650 */     return getFragment(calendar, fragment, unit);
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
/*      */   private static long getFragment(Calendar calendar, int fragment, TimeUnit unit) {
/* 1665 */     if (calendar == null) {
/* 1666 */       throw new IllegalArgumentException("The date must not be null");
/*      */     }
/*      */     
/* 1669 */     long result = 0L;
/*      */     
/* 1671 */     int offset = (unit == TimeUnit.DAYS) ? 0 : 1;
/*      */ 
/*      */     
/* 1674 */     switch (fragment) {
/*      */       case 1:
/* 1676 */         result += unit.convert((calendar.get(6) - offset), TimeUnit.DAYS);
/*      */         break;
/*      */       case 2:
/* 1679 */         result += unit.convert((calendar.get(5) - offset), TimeUnit.DAYS);
/*      */         break;
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1685 */     switch (fragment) {
/*      */ 
/*      */ 
/*      */       
/*      */       case 1:
/*      */       case 2:
/*      */       case 5:
/*      */       case 6:
/* 1693 */         result += unit.convert(calendar.get(11), TimeUnit.HOURS);
/*      */       
/*      */       case 11:
/* 1696 */         result += unit.convert(calendar.get(12), TimeUnit.MINUTES);
/*      */       
/*      */       case 12:
/* 1699 */         result += unit.convert(calendar.get(13), TimeUnit.SECONDS);
/*      */       
/*      */       case 13:
/* 1702 */         result += unit.convert(calendar.get(14), TimeUnit.MILLISECONDS);
/*      */ 
/*      */ 
/*      */       
/*      */       case 14:
/* 1707 */         return result;
/*      */     } 
/*      */     throw new IllegalArgumentException("The fragment " + fragment + " is not supported");
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
/*      */   public static boolean truncatedEquals(Calendar cal1, Calendar cal2, int field) {
/* 1724 */     return (truncatedCompareTo(cal1, cal2, field) == 0);
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
/*      */   public static boolean truncatedEquals(Date date1, Date date2, int field) {
/* 1741 */     return (truncatedCompareTo(date1, date2, field) == 0);
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
/*      */   public static int truncatedCompareTo(Calendar cal1, Calendar cal2, int field) {
/* 1759 */     Calendar truncatedCal1 = truncate(cal1, field);
/* 1760 */     Calendar truncatedCal2 = truncate(cal2, field);
/* 1761 */     return truncatedCal1.compareTo(truncatedCal2);
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
/*      */   public static int truncatedCompareTo(Date date1, Date date2, int field) {
/* 1779 */     Date truncatedDate1 = truncate(date1, field);
/* 1780 */     Date truncatedDate2 = truncate(date2, field);
/* 1781 */     return truncatedDate1.compareTo(truncatedDate2);
/*      */   }
/*      */   
/*      */   private static void validateDateNotNull(Date date) {
/* 1785 */     Validate.isTrue((date != null), "The date must not be null", new Object[0]);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static class DateIterator
/*      */     implements Iterator<Calendar>
/*      */   {
/*      */     private final Calendar endFinal;
/*      */ 
/*      */ 
/*      */     
/*      */     private final Calendar spot;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     DateIterator(Calendar startFinal, Calendar endFinal) {
/* 1804 */       this.endFinal = endFinal;
/* 1805 */       this.spot = startFinal;
/* 1806 */       this.spot.add(5, -1);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public boolean hasNext() {
/* 1816 */       return this.spot.before(this.endFinal);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Calendar next() {
/* 1826 */       if (this.spot.equals(this.endFinal)) {
/* 1827 */         throw new NoSuchElementException();
/*      */       }
/* 1829 */       this.spot.add(5, 1);
/* 1830 */       return (Calendar)this.spot.clone();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void remove() {
/* 1841 */       throw new UnsupportedOperationException();
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/time/DateUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */