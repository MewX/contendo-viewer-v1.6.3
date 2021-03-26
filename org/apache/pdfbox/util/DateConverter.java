/*     */ package org.apache.pdfbox.util;
/*     */ 
/*     */ import java.text.ParsePosition;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.GregorianCalendar;
/*     */ import java.util.Locale;
/*     */ import java.util.SimpleTimeZone;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.pdfbox.cos.COSString;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class DateConverter
/*     */ {
/*     */   private static final int MINUTES_PER_HOUR = 60;
/*     */   private static final int SECONDS_PER_MINUTE = 60;
/*     */   private static final int MILLIS_PER_MINUTE = 60000;
/*     */   private static final int MILLIS_PER_HOUR = 3600000;
/*     */   private static final int HALF_DAY = 43200000;
/*     */   private static final int DAY = 86400000;
/* 103 */   private static final String[] ALPHA_START_FORMATS = new String[] { "EEEE, dd MMM yy hh:mm:ss a", "EEEE, MMM dd, yy hh:mm:ss a", "EEEE, MMM dd, yy 'at' hh:mma", "EEEE, MMM dd, yy", "EEEE MMM dd, yy HH:mm:ss", "EEEE MMM dd HH:mm:ss z yy", "EEEE MMM dd HH:mm:ss yy" };
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
/* 114 */   private static final String[] DIGIT_START_FORMATS = new String[] { "dd MMM yy HH:mm:ss", "dd MMM yy HH:mm", "yyyy MMM d", "yyyymmddhh:mm:ss", "H:m M/d/yy", "M/d/yy HH:mm:ss", "M/d/yy HH:mm", "M/d/yy" };
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
/*     */   public static String toString(Calendar cal) {
/* 162 */     if (cal == null)
/*     */     {
/* 164 */       return null;
/*     */     }
/* 166 */     String offset = formatTZoffset((cal.get(15) + cal
/* 167 */         .get(16)), "'");
/* 168 */     return String.format(Locale.US, "D:%1$4tY%1$2tm%1$2td%1$2tH%1$2tM%1$2tS%2$s'", new Object[] { cal, offset });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String toISO8601(Calendar cal) {
/* 187 */     String offset = formatTZoffset((cal.get(15) + cal
/* 188 */         .get(16)), ":");
/* 189 */     return String.format(Locale.US, "%1$4tY-%1$2tm-%1$2tdT%1$2tH:%1$2tM:%1$2tS%2$s", new Object[] { cal, offset });
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
/*     */ 
/*     */ 
/*     */   
/*     */   private static int restrainTZoffset(long proposedOffset) {
/* 205 */     if (proposedOffset <= 50400000L && proposedOffset >= -50400000L)
/*     */     {
/*     */ 
/*     */       
/* 209 */       return (int)proposedOffset;
/*     */     }
/*     */     
/* 212 */     proposedOffset = ((proposedOffset + 43200000L) % 86400000L + 86400000L) % 86400000L;
/* 213 */     if (proposedOffset == 0L)
/*     */     {
/* 215 */       return 43200000;
/*     */     }
/*     */     
/* 218 */     proposedOffset = (proposedOffset - 43200000L) % 43200000L;
/*     */     
/* 220 */     return (int)proposedOffset;
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
/*     */   static String formatTZoffset(long millis, String sep) {
/* 283 */     SimpleDateFormat sdf = new SimpleDateFormat("Z");
/* 284 */     sdf.setTimeZone(new SimpleTimeZone(restrainTZoffset(millis), "unknown"));
/* 285 */     String tz = sdf.format(new Date());
/* 286 */     return tz.substring(0, 3) + sep + tz.substring(3);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static int parseTimeField(String text, ParsePosition where, int maxlen, int remedy) {
/* 304 */     if (text == null)
/*     */     {
/* 306 */       return remedy;
/*     */     }
/*     */ 
/*     */     
/* 310 */     int retval = 0;
/* 311 */     int index = where.getIndex();
/* 312 */     int limit = index + Math.min(maxlen, text.length() - index);
/* 313 */     for (; index < limit; index++) {
/*     */ 
/*     */       
/* 316 */       int cval = text.charAt(index) - 48;
/*     */       
/* 318 */       if (cval < 0 || cval > 9) {
/*     */         break;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 324 */       retval = retval * 10 + cval;
/*     */     } 
/* 326 */     if (index == where.getIndex())
/*     */     {
/* 328 */       return remedy;
/*     */     }
/* 330 */     where.setIndex(index);
/* 331 */     return retval;
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
/*     */ 
/*     */   
/*     */   private static char skipOptionals(String text, ParsePosition where, String optionals) {
/* 346 */     char retval = ' '; char currch;
/* 347 */     while (text != null && where.getIndex() < text.length() && optionals
/* 348 */       .indexOf(currch = text.charAt(where.getIndex())) >= 0) {
/*     */       
/* 350 */       retval = (currch != ' ') ? currch : retval;
/* 351 */       where.setIndex(where.getIndex() + 1);
/*     */     } 
/* 353 */     return retval;
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
/*     */   private static boolean skipString(String text, String victim, ParsePosition where) {
/* 366 */     if (text.startsWith(victim, where.getIndex())) {
/*     */       
/* 368 */       where.setIndex(where.getIndex() + victim.length());
/* 369 */       return true;
/*     */     } 
/* 371 */     return false;
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
/*     */   static GregorianCalendar newGreg() {
/* 384 */     GregorianCalendar retCal = new GregorianCalendar(Locale.ENGLISH);
/* 385 */     retCal.setTimeZone(new SimpleTimeZone(0, "UTC"));
/* 386 */     retCal.setLenient(false);
/* 387 */     retCal.set(14, 0);
/* 388 */     return retCal;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void adjustTimeZoneNicely(GregorianCalendar cal, TimeZone tz) {
/* 399 */     cal.setTimeZone(tz);
/* 400 */     int offset = (cal.get(15) + cal.get(16)) / 60000;
/*     */     
/* 402 */     cal.add(12, -offset);
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
/*     */   static boolean parseTZoffset(String text, GregorianCalendar cal, ParsePosition initialWhere) {
/* 427 */     ParsePosition where = new ParsePosition(initialWhere.getIndex());
/* 428 */     TimeZone tz = new SimpleTimeZone(0, "GMT");
/*     */     
/* 430 */     char sign = skipOptionals(text, where, "Z+- ");
/*     */     
/* 432 */     boolean hadGMT = (sign == 'Z' || skipString(text, "GMT", where) || skipString(text, "UTC", where));
/* 433 */     sign = !hadGMT ? sign : skipOptionals(text, where, "+- ");
/*     */     
/* 435 */     int tzHours = parseTimeField(text, where, 2, -999);
/* 436 */     skipOptionals(text, where, "': ");
/* 437 */     int tzMin = parseTimeField(text, where, 2, 0);
/* 438 */     skipOptionals(text, where, "' ");
/*     */     
/* 440 */     if (tzHours != -999) {
/*     */ 
/*     */       
/* 443 */       int hrSign = (sign == '-') ? -1 : 1;
/* 444 */       tz.setRawOffset(restrainTZoffset(hrSign * ((tzHours * 3600000) + tzMin * 60000L)));
/*     */       
/* 446 */       updateZoneId(tz);
/*     */     }
/* 448 */     else if (!hadGMT) {
/*     */ 
/*     */       
/* 451 */       String tzText = text.substring(initialWhere.getIndex()).trim();
/* 452 */       tz = TimeZone.getTimeZone(tzText);
/*     */       
/* 454 */       if ("GMT".equals(tz.getID()))
/*     */       {
/*     */         
/* 457 */         return false;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 462 */       where.setIndex(text.length());
/*     */     } 
/*     */     
/* 465 */     adjustTimeZoneNicely(cal, tz);
/* 466 */     initialWhere.setIndex(where.getIndex());
/* 467 */     return true;
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
/*     */   private static void updateZoneId(TimeZone tz) {
/* 479 */     int offset = tz.getRawOffset();
/* 480 */     char pm = '+';
/* 481 */     if (offset < 0) {
/*     */       
/* 483 */       pm = '-';
/* 484 */       offset = -offset;
/*     */     } 
/* 486 */     int hh = offset / 3600000;
/* 487 */     int mm = offset % 3600000 / 60000;
/* 488 */     if (offset == 0) {
/*     */       
/* 490 */       tz.setID("GMT");
/*     */     }
/* 492 */     else if (pm == '+' && hh <= 12) {
/*     */       
/* 494 */       tz.setID(String.format(Locale.US, "GMT+%02d:%02d", new Object[] { Integer.valueOf(hh), Integer.valueOf(mm) }));
/*     */     }
/* 496 */     else if (pm == '-' && hh <= 14) {
/*     */       
/* 498 */       tz.setID(String.format(Locale.US, "GMT-%02d:%02d", new Object[] { Integer.valueOf(hh), Integer.valueOf(mm) }));
/*     */     }
/*     */     else {
/*     */       
/* 502 */       tz.setID("unknown");
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static GregorianCalendar parseBigEndianDate(String text, ParsePosition initialWhere) {
/* 524 */     ParsePosition where = new ParsePosition(initialWhere.getIndex());
/* 525 */     int year = parseTimeField(text, where, 4, 0);
/* 526 */     if (where.getIndex() != 4 + initialWhere.getIndex())
/*     */     {
/* 528 */       return null;
/*     */     }
/* 530 */     skipOptionals(text, where, "/- ");
/* 531 */     int month = parseTimeField(text, where, 2, 1) - 1;
/* 532 */     skipOptionals(text, where, "/- ");
/* 533 */     int day = parseTimeField(text, where, 2, 1);
/* 534 */     skipOptionals(text, where, " T");
/* 535 */     int hour = parseTimeField(text, where, 2, 0);
/* 536 */     skipOptionals(text, where, ": ");
/* 537 */     int minute = parseTimeField(text, where, 2, 0);
/* 538 */     skipOptionals(text, where, ": ");
/* 539 */     int second = parseTimeField(text, where, 2, 0);
/* 540 */     char nextC = skipOptionals(text, where, ".");
/* 541 */     if (nextC == '.')
/*     */     {
/*     */       
/* 544 */       parseTimeField(text, where, 19, 0);
/*     */     }
/*     */     
/* 547 */     GregorianCalendar dest = newGreg();
/*     */     
/*     */     try {
/* 550 */       dest.set(year, month, day, hour, minute, second);
/*     */       
/* 552 */       dest.getTimeInMillis();
/*     */     }
/* 554 */     catch (IllegalArgumentException ill) {
/*     */       
/* 556 */       return null;
/*     */     } 
/* 558 */     initialWhere.setIndex(where.getIndex());
/* 559 */     skipOptionals(text, initialWhere, " ");
/*     */     
/* 561 */     return dest;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static GregorianCalendar parseSimpleDate(String text, String[] fmts, ParsePosition initialWhere) {
/* 581 */     for (String fmt : fmts) {
/*     */       
/* 583 */       ParsePosition where = new ParsePosition(initialWhere.getIndex());
/* 584 */       SimpleDateFormat sdf = new SimpleDateFormat(fmt, Locale.ENGLISH);
/* 585 */       GregorianCalendar retCal = newGreg();
/* 586 */       sdf.setCalendar(retCal);
/* 587 */       if (sdf.parse(text, where) != null) {
/*     */         
/* 589 */         initialWhere.setIndex(where.getIndex());
/* 590 */         skipOptionals(text, initialWhere, " ");
/* 591 */         return retCal;
/*     */       } 
/*     */     } 
/* 594 */     return null;
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
/*     */   private static Calendar parseDate(String text, ParsePosition initialWhere) {
/* 617 */     if (text == null || text.isEmpty())
/*     */     {
/* 619 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 623 */     int longestLen = -999999;
/*     */ 
/*     */ 
/*     */     
/* 627 */     GregorianCalendar longestDate = null;
/*     */ 
/*     */     
/* 630 */     ParsePosition where = new ParsePosition(initialWhere.getIndex());
/*     */     
/* 632 */     skipOptionals(text, where, " ");
/* 633 */     int startPosition = where.getIndex();
/*     */ 
/*     */     
/* 636 */     GregorianCalendar retCal = parseBigEndianDate(text, where);
/*     */     
/* 638 */     if (retCal != null && (where.getIndex() == text.length() || 
/* 639 */       parseTZoffset(text, retCal, where))) {
/*     */ 
/*     */       
/* 642 */       int whereLen = where.getIndex();
/* 643 */       if (whereLen == text.length()) {
/*     */         
/* 645 */         initialWhere.setIndex(whereLen);
/* 646 */         return retCal;
/*     */       } 
/* 648 */       longestLen = whereLen;
/* 649 */       longestDate = retCal;
/*     */     } 
/*     */ 
/*     */     
/* 653 */     where.setIndex(startPosition);
/*     */     
/* 655 */     String[] formats = Character.isDigit(text.charAt(startPosition)) ? DIGIT_START_FORMATS : ALPHA_START_FORMATS;
/*     */ 
/*     */     
/* 658 */     retCal = parseSimpleDate(text, formats, where);
/*     */     
/* 660 */     if (retCal != null && (where
/* 661 */       .getIndex() == text.length() || 
/* 662 */       parseTZoffset(text, retCal, where))) {
/*     */ 
/*     */       
/* 665 */       int whereLen = where.getIndex();
/* 666 */       if (whereLen == text.length()) {
/*     */         
/* 668 */         initialWhere.setIndex(whereLen);
/* 669 */         return retCal;
/*     */       } 
/* 671 */       if (whereLen > longestLen) {
/*     */         
/* 673 */         longestLen = whereLen;
/* 674 */         longestDate = retCal;
/*     */       } 
/*     */     } 
/*     */     
/* 678 */     if (longestDate != null) {
/*     */       
/* 680 */       initialWhere.setIndex(longestLen);
/* 681 */       return longestDate;
/*     */     } 
/* 683 */     return retCal;
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
/*     */   
/*     */   public static Calendar toCalendar(COSString text) {
/* 697 */     if (text == null)
/*     */     {
/* 699 */       return null;
/*     */     }
/* 701 */     return toCalendar(text.getString());
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
/*     */   
/*     */   public static Calendar toCalendar(String text) {
/* 715 */     if (text == null || text.trim().isEmpty())
/*     */     {
/* 717 */       return null;
/*     */     }
/*     */     
/* 720 */     ParsePosition where = new ParsePosition(0);
/* 721 */     skipOptionals(text, where, " ");
/* 722 */     skipString(text, "D:", where);
/* 723 */     Calendar calendar = parseDate(text, where);
/*     */     
/* 725 */     if (calendar == null || where.getIndex() != text.length())
/*     */     {
/*     */       
/* 728 */       return null;
/*     */     }
/* 730 */     return calendar;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/util/DateConverter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */