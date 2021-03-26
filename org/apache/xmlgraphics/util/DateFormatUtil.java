/*     */ package org.apache.xmlgraphics.util;
/*     */ 
/*     */ import java.text.DateFormat;
/*     */ import java.text.ParseException;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.Locale;
/*     */ import java.util.TimeZone;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DateFormatUtil
/*     */ {
/*     */   private static final String ISO_8601_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss";
/*     */   
/*     */   public static String formatPDFDate(Date date, TimeZone timeZone) {
/*  41 */     DateFormat dateFormat = createDateFormat("'D:'yyyyMMddHHmmss", timeZone);
/*  42 */     return formatDate(date, dateFormat, '\'', true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatISO8601(Date date, TimeZone timeZone) {
/*  52 */     DateFormat dateFormat = createDateFormat("yyyy-MM-dd'T'HH:mm:ss", timeZone);
/*  53 */     return formatDate(date, dateFormat, ':', false);
/*     */   }
/*     */   
/*     */   private static DateFormat createDateFormat(String format, TimeZone timeZone) {
/*  57 */     DateFormat dateFormat = new SimpleDateFormat(format, Locale.ENGLISH);
/*  58 */     dateFormat.setTimeZone(timeZone);
/*  59 */     return dateFormat;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String formatDate(Date date, DateFormat dateFormat, char delimiter, boolean endWithDelimiter) {
/*  72 */     Calendar cal = Calendar.getInstance(dateFormat.getTimeZone(), Locale.ENGLISH);
/*  73 */     cal.setTime(date);
/*  74 */     int offset = getOffsetInMinutes(cal);
/*  75 */     StringBuilder sb = new StringBuilder(dateFormat.format(date));
/*  76 */     appendOffset(sb, delimiter, offset, endWithDelimiter);
/*  77 */     return sb.toString();
/*     */   }
/*     */   
/*     */   private static int getOffsetInMinutes(Calendar cal) {
/*  81 */     int offset = cal.get(15);
/*  82 */     offset += cal.get(16);
/*  83 */     offset /= 60000;
/*  84 */     return offset;
/*     */   }
/*     */   
/*     */   private static void appendOffset(StringBuilder sb, char delimiter, int offset, boolean endWithDelimiter) {
/*  88 */     if (offset == 0) {
/*  89 */       appendOffsetUTC(sb);
/*     */     } else {
/*  91 */       appendOffsetNoUTC(sb, delimiter, offset, endWithDelimiter);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void appendOffsetUTC(StringBuilder sb) {
/*  96 */     sb.append('Z');
/*     */   }
/*     */ 
/*     */   
/*     */   private static void appendOffsetNoUTC(StringBuilder sb, char delimiter, int offset, boolean endWithDelimiter) {
/* 101 */     int zoneOffsetHours = offset / 60;
/* 102 */     appendOffsetSign(sb, zoneOffsetHours);
/* 103 */     appendPaddedNumber(sb, Math.abs(zoneOffsetHours));
/* 104 */     sb.append(delimiter);
/* 105 */     appendPaddedNumber(sb, Math.abs(offset % 60));
/* 106 */     if (endWithDelimiter) {
/* 107 */       sb.append(delimiter);
/*     */     }
/*     */   }
/*     */   
/*     */   private static void appendOffsetSign(StringBuilder sb, int zoneOffsetHours) {
/* 112 */     if (zoneOffsetHours >= 0) {
/* 113 */       sb.append('+');
/*     */     } else {
/* 115 */       sb.append('-');
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void appendPaddedNumber(StringBuilder sb, int number) {
/* 120 */     if (number < 10) {
/* 121 */       sb.append('0');
/*     */     }
/* 123 */     sb.append(number);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Date parseISO8601Date(String date) {
/* 132 */     String errorMessage = "Invalid ISO 8601 date format: ";
/* 133 */     date = formatDateToParse(date, "Invalid ISO 8601 date format: ");
/* 134 */     DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
/*     */     try {
/* 136 */       return dateFormat.parse(date);
/* 137 */     } catch (ParseException ex) {
/* 138 */       throw new IllegalArgumentException("Invalid ISO 8601 date format: " + date);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String formatDateToParse(String date, String errorMessage) {
/* 146 */     if (!date.contains("Z")) {
/* 147 */       int lastColonIndex = date.lastIndexOf(":");
/* 148 */       if (lastColonIndex < 0) {
/* 149 */         throw new IllegalArgumentException(errorMessage + date);
/*     */       }
/* 151 */       date = date.substring(0, lastColonIndex) + date.substring(lastColonIndex + 1, date.length());
/*     */     } else {
/* 153 */       date = date.replace("Z", "+0000");
/*     */     } 
/* 155 */     return date;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/DateFormatUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */