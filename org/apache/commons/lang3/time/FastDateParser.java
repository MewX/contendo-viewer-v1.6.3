/*     */ package org.apache.commons.lang3.time;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.ObjectInputStream;
/*     */ import java.io.Serializable;
/*     */ import java.text.DateFormatSymbols;
/*     */ import java.text.ParseException;
/*     */ import java.text.ParsePosition;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Calendar;
/*     */ import java.util.Comparator;
/*     */ import java.util.Date;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.ListIterator;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TimeZone;
/*     */ import java.util.TreeSet;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
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
/*     */ public class FastDateParser
/*     */   implements Serializable, DateParser
/*     */ {
/*     */   private static final long serialVersionUID = 3L;
/*  82 */   static final Locale JAPANESE_IMPERIAL = new Locale("ja", "JP", "JP");
/*     */   
/*     */   private final String pattern;
/*     */   
/*     */   private final TimeZone timeZone;
/*     */   
/*     */   private final Locale locale;
/*     */   
/*     */   private final int century;
/*     */   
/*     */   private final int startYear;
/*     */   
/*     */   private transient List<StrategyAndWidth> patterns;
/*     */ 
/*     */   
/*  97 */   private static final Comparator<String> LONGER_FIRST_LOWERCASE = new Comparator<String>()
/*     */     {
/*     */       public int compare(String left, String right) {
/* 100 */         return right.compareTo(left);
/*     */       }
/*     */     };
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
/*     */   protected FastDateParser(String pattern, TimeZone timeZone, Locale locale) {
/* 116 */     this(pattern, timeZone, locale, null);
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
/*     */   protected FastDateParser(String pattern, TimeZone timeZone, Locale locale, Date centuryStart) {
/*     */     int centuryStartYear;
/* 131 */     this.pattern = pattern;
/* 132 */     this.timeZone = timeZone;
/* 133 */     this.locale = locale;
/*     */     
/* 135 */     Calendar definingCalendar = Calendar.getInstance(timeZone, locale);
/*     */ 
/*     */     
/* 138 */     if (centuryStart != null) {
/* 139 */       definingCalendar.setTime(centuryStart);
/* 140 */       centuryStartYear = definingCalendar.get(1);
/* 141 */     } else if (locale.equals(JAPANESE_IMPERIAL)) {
/* 142 */       centuryStartYear = 0;
/*     */     } else {
/*     */       
/* 145 */       definingCalendar.setTime(new Date());
/* 146 */       centuryStartYear = definingCalendar.get(1) - 80;
/*     */     } 
/* 148 */     this.century = centuryStartYear / 100 * 100;
/* 149 */     this.startYear = centuryStartYear - this.century;
/*     */     
/* 151 */     init(definingCalendar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void init(Calendar definingCalendar) {
/* 161 */     this.patterns = new ArrayList<>();
/*     */     
/* 163 */     StrategyParser fm = new StrategyParser(definingCalendar);
/*     */     while (true) {
/* 165 */       StrategyAndWidth field = fm.getNextStrategy();
/* 166 */       if (field == null) {
/*     */         break;
/*     */       }
/* 169 */       this.patterns.add(field);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class StrategyAndWidth
/*     */   {
/*     */     final FastDateParser.Strategy strategy;
/*     */ 
/*     */     
/*     */     final int width;
/*     */ 
/*     */     
/*     */     StrategyAndWidth(FastDateParser.Strategy strategy, int width) {
/* 184 */       this.strategy = strategy;
/* 185 */       this.width = width;
/*     */     }
/*     */     
/*     */     int getMaxWidth(ListIterator<StrategyAndWidth> lt) {
/* 189 */       if (!this.strategy.isNumber() || !lt.hasNext()) {
/* 190 */         return 0;
/*     */       }
/* 192 */       FastDateParser.Strategy nextStrategy = ((StrategyAndWidth)lt.next()).strategy;
/* 193 */       lt.previous();
/* 194 */       return nextStrategy.isNumber() ? this.width : 0;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private class StrategyParser
/*     */   {
/*     */     private final Calendar definingCalendar;
/*     */     
/*     */     private int currentIdx;
/*     */     
/*     */     StrategyParser(Calendar definingCalendar) {
/* 206 */       this.definingCalendar = definingCalendar;
/*     */     }
/*     */     
/*     */     FastDateParser.StrategyAndWidth getNextStrategy() {
/* 210 */       if (this.currentIdx >= FastDateParser.this.pattern.length()) {
/* 211 */         return null;
/*     */       }
/*     */       
/* 214 */       char c = FastDateParser.this.pattern.charAt(this.currentIdx);
/* 215 */       if (FastDateParser.isFormatLetter(c)) {
/* 216 */         return letterPattern(c);
/*     */       }
/* 218 */       return literal();
/*     */     }
/*     */     
/*     */     private FastDateParser.StrategyAndWidth letterPattern(char c) {
/* 222 */       int begin = this.currentIdx; do {  }
/* 223 */       while (++this.currentIdx < FastDateParser.this.pattern.length() && 
/* 224 */         FastDateParser.this.pattern.charAt(this.currentIdx) == c);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 229 */       int width = this.currentIdx - begin;
/* 230 */       return new FastDateParser.StrategyAndWidth(FastDateParser.this.getStrategy(c, width, this.definingCalendar), width);
/*     */     }
/*     */     
/*     */     private FastDateParser.StrategyAndWidth literal() {
/* 234 */       boolean activeQuote = false;
/*     */       
/* 236 */       StringBuilder sb = new StringBuilder();
/* 237 */       while (this.currentIdx < FastDateParser.this.pattern.length()) {
/* 238 */         char c = FastDateParser.this.pattern.charAt(this.currentIdx);
/* 239 */         if (!activeQuote && FastDateParser.isFormatLetter(c))
/*     */           break; 
/* 241 */         if (c == '\'' && (++this.currentIdx == FastDateParser.this.pattern.length() || FastDateParser.this.pattern.charAt(this.currentIdx) != '\'')) {
/* 242 */           activeQuote = !activeQuote;
/*     */           continue;
/*     */         } 
/* 245 */         this.currentIdx++;
/* 246 */         sb.append(c);
/*     */       } 
/*     */       
/* 249 */       if (activeQuote) {
/* 250 */         throw new IllegalArgumentException("Unterminated quote");
/*     */       }
/*     */       
/* 253 */       String formatField = sb.toString();
/* 254 */       return new FastDateParser.StrategyAndWidth(new FastDateParser.CopyQuotedStrategy(formatField), formatField.length());
/*     */     }
/*     */   }
/*     */   
/*     */   private static boolean isFormatLetter(char c) {
/* 259 */     return ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z'));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPattern() {
/* 269 */     return this.pattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TimeZone getTimeZone() {
/* 277 */     return this.timeZone;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Locale getLocale() {
/* 285 */     return this.locale;
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
/*     */   public boolean equals(Object obj) {
/* 299 */     if (!(obj instanceof FastDateParser)) {
/* 300 */       return false;
/*     */     }
/* 302 */     FastDateParser other = (FastDateParser)obj;
/* 303 */     return (this.pattern.equals(other.pattern) && this.timeZone
/* 304 */       .equals(other.timeZone) && this.locale
/* 305 */       .equals(other.locale));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 315 */     return this.pattern.hashCode() + 13 * (this.timeZone.hashCode() + 13 * this.locale.hashCode());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 325 */     return "FastDateParser[" + this.pattern + "," + this.locale + "," + this.timeZone.getID() + "]";
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
/*     */   private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
/* 339 */     in.defaultReadObject();
/*     */     
/* 341 */     Calendar definingCalendar = Calendar.getInstance(this.timeZone, this.locale);
/* 342 */     init(definingCalendar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object parseObject(String source) throws ParseException {
/* 350 */     return parse(source);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Date parse(String source) throws ParseException {
/* 358 */     ParsePosition pp = new ParsePosition(0);
/* 359 */     Date date = parse(source, pp);
/* 360 */     if (date == null) {
/*     */       
/* 362 */       if (this.locale.equals(JAPANESE_IMPERIAL)) {
/* 363 */         throw new ParseException("(The " + this.locale + " locale does not support dates before 1868 AD)\nUnparseable date: \"" + source, pp
/*     */             
/* 365 */             .getErrorIndex());
/*     */       }
/* 367 */       throw new ParseException("Unparseable date: " + source, pp.getErrorIndex());
/*     */     } 
/* 369 */     return date;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object parseObject(String source, ParsePosition pos) {
/* 377 */     return parse(source, pos);
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
/*     */   public Date parse(String source, ParsePosition pos) {
/* 395 */     Calendar cal = Calendar.getInstance(this.timeZone, this.locale);
/* 396 */     cal.clear();
/*     */     
/* 398 */     return parse(source, pos, cal) ? cal.getTime() : null;
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
/*     */   public boolean parse(String source, ParsePosition pos, Calendar calendar) {
/* 416 */     ListIterator<StrategyAndWidth> lt = this.patterns.listIterator();
/* 417 */     while (lt.hasNext()) {
/* 418 */       StrategyAndWidth strategyAndWidth = lt.next();
/* 419 */       int maxWidth = strategyAndWidth.getMaxWidth(lt);
/* 420 */       if (!strategyAndWidth.strategy.parse(this, calendar, source, pos, maxWidth)) {
/* 421 */         return false;
/*     */       }
/*     */     } 
/* 424 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static StringBuilder simpleQuote(StringBuilder sb, String value) {
/* 431 */     for (int i = 0; i < value.length(); i++) {
/* 432 */       char c = value.charAt(i);
/* 433 */       switch (c) {
/*     */         case '$':
/*     */         case '(':
/*     */         case ')':
/*     */         case '*':
/*     */         case '+':
/*     */         case '.':
/*     */         case '?':
/*     */         case '[':
/*     */         case '\\':
/*     */         case '^':
/*     */         case '{':
/*     */         case '|':
/* 446 */           sb.append('\\'); break;
/*     */       } 
/* 448 */       sb.append(c);
/*     */     } 
/*     */     
/* 451 */     if (sb.charAt(sb.length() - 1) == '.')
/*     */     {
/* 453 */       sb.append('?');
/*     */     }
/* 455 */     return sb;
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
/*     */   private static Map<String, Integer> appendDisplayNames(Calendar cal, Locale locale, int field, StringBuilder regex) {
/* 467 */     Map<String, Integer> values = new HashMap<>();
/*     */     
/* 469 */     Map<String, Integer> displayNames = cal.getDisplayNames(field, 0, locale);
/* 470 */     TreeSet<String> sorted = new TreeSet<>(LONGER_FIRST_LOWERCASE);
/* 471 */     for (Map.Entry<String, Integer> displayName : displayNames.entrySet()) {
/* 472 */       String key = ((String)displayName.getKey()).toLowerCase(locale);
/* 473 */       if (sorted.add(key)) {
/* 474 */         values.put(key, displayName.getValue());
/*     */       }
/*     */     } 
/* 477 */     for (String symbol : sorted) {
/* 478 */       simpleQuote(regex, symbol).append('|');
/*     */     }
/* 480 */     return values;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int adjustYear(int twoDigitYear) {
/* 489 */     int trial = this.century + twoDigitYear;
/* 490 */     return (twoDigitYear >= this.startYear) ? trial : (trial + 100);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static abstract class Strategy
/*     */   {
/*     */     private Strategy() {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean isNumber() {
/* 504 */       return false;
/*     */     }
/*     */     
/*     */     abstract boolean parse(FastDateParser param1FastDateParser, Calendar param1Calendar, String param1String, ParsePosition param1ParsePosition, int param1Int);
/*     */   }
/*     */   
/*     */   private static abstract class PatternStrategy
/*     */     extends Strategy
/*     */   {
/*     */     private Pattern pattern;
/*     */     
/*     */     private PatternStrategy() {}
/*     */     
/*     */     void createPattern(StringBuilder regex) {
/* 518 */       createPattern(regex.toString());
/*     */     }
/*     */     
/*     */     void createPattern(String regex) {
/* 522 */       this.pattern = Pattern.compile(regex);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean isNumber() {
/* 533 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
/* 538 */       Matcher matcher = this.pattern.matcher(source.substring(pos.getIndex()));
/* 539 */       if (!matcher.lookingAt()) {
/* 540 */         pos.setErrorIndex(pos.getIndex());
/* 541 */         return false;
/*     */       } 
/* 543 */       pos.setIndex(pos.getIndex() + matcher.end(1));
/* 544 */       setCalendar(parser, calendar, matcher.group(1));
/* 545 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     abstract void setCalendar(FastDateParser param1FastDateParser, Calendar param1Calendar, String param1String);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Strategy getStrategy(char f, int width, Calendar definingCalendar) {
/* 558 */     switch (f) {
/*     */       default:
/* 560 */         throw new IllegalArgumentException("Format '" + f + "' not supported");
/*     */       case 'D':
/* 562 */         return DAY_OF_YEAR_STRATEGY;
/*     */       case 'E':
/* 564 */         return getLocaleSpecificStrategy(7, definingCalendar);
/*     */       case 'F':
/* 566 */         return DAY_OF_WEEK_IN_MONTH_STRATEGY;
/*     */       case 'G':
/* 568 */         return getLocaleSpecificStrategy(0, definingCalendar);
/*     */       case 'H':
/* 570 */         return HOUR_OF_DAY_STRATEGY;
/*     */       case 'K':
/* 572 */         return HOUR_STRATEGY;
/*     */       case 'M':
/* 574 */         return (width >= 3) ? getLocaleSpecificStrategy(2, definingCalendar) : NUMBER_MONTH_STRATEGY;
/*     */       case 'S':
/* 576 */         return MILLISECOND_STRATEGY;
/*     */       case 'W':
/* 578 */         return WEEK_OF_MONTH_STRATEGY;
/*     */       case 'a':
/* 580 */         return getLocaleSpecificStrategy(9, definingCalendar);
/*     */       case 'd':
/* 582 */         return DAY_OF_MONTH_STRATEGY;
/*     */       case 'h':
/* 584 */         return HOUR12_STRATEGY;
/*     */       case 'k':
/* 586 */         return HOUR24_OF_DAY_STRATEGY;
/*     */       case 'm':
/* 588 */         return MINUTE_STRATEGY;
/*     */       case 's':
/* 590 */         return SECOND_STRATEGY;
/*     */       case 'u':
/* 592 */         return DAY_OF_WEEK_STRATEGY;
/*     */       case 'w':
/* 594 */         return WEEK_OF_YEAR_STRATEGY;
/*     */       case 'Y':
/*     */       case 'y':
/* 597 */         return (width > 2) ? LITERAL_YEAR_STRATEGY : ABBREVIATED_YEAR_STRATEGY;
/*     */       case 'X':
/* 599 */         return ISO8601TimeZoneStrategy.getStrategy(width);
/*     */       case 'Z':
/* 601 */         if (width == 2)
/* 602 */           return ISO8601TimeZoneStrategy.ISO_8601_3_STRATEGY;  break;
/*     */       case 'z':
/*     */         break;
/*     */     } 
/* 606 */     return getLocaleSpecificStrategy(15, definingCalendar);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/* 611 */   private static final ConcurrentMap<Locale, Strategy>[] caches = (ConcurrentMap<Locale, Strategy>[])new ConcurrentMap[17];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static ConcurrentMap<Locale, Strategy> getCache(int field) {
/* 619 */     synchronized (caches) {
/* 620 */       if (caches[field] == null) {
/* 621 */         caches[field] = new ConcurrentHashMap<>(3);
/*     */       }
/* 623 */       return caches[field];
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Strategy getLocaleSpecificStrategy(int field, Calendar definingCalendar) {
/* 634 */     ConcurrentMap<Locale, Strategy> cache = getCache(field);
/* 635 */     Strategy strategy = cache.get(this.locale);
/* 636 */     if (strategy == null) {
/*     */ 
/*     */       
/* 639 */       strategy = (field == 15) ? new TimeZoneStrategy(this.locale) : new CaseInsensitiveTextStrategy(field, definingCalendar, this.locale);
/* 640 */       Strategy inCache = cache.putIfAbsent(this.locale, strategy);
/* 641 */       if (inCache != null) {
/* 642 */         return inCache;
/*     */       }
/*     */     } 
/* 645 */     return strategy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class CopyQuotedStrategy
/*     */     extends Strategy
/*     */   {
/*     */     private final String formatField;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     CopyQuotedStrategy(String formatField) {
/* 660 */       this.formatField = formatField;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean isNumber() {
/* 668 */       return false;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
/* 673 */       for (int idx = 0; idx < this.formatField.length(); idx++) {
/* 674 */         int sIdx = idx + pos.getIndex();
/* 675 */         if (sIdx == source.length()) {
/* 676 */           pos.setErrorIndex(sIdx);
/* 677 */           return false;
/*     */         } 
/* 679 */         if (this.formatField.charAt(idx) != source.charAt(sIdx)) {
/* 680 */           pos.setErrorIndex(sIdx);
/* 681 */           return false;
/*     */         } 
/*     */       } 
/* 684 */       pos.setIndex(this.formatField.length() + pos.getIndex());
/* 685 */       return true;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static class CaseInsensitiveTextStrategy
/*     */     extends PatternStrategy
/*     */   {
/*     */     private final int field;
/*     */ 
/*     */     
/*     */     final Locale locale;
/*     */ 
/*     */     
/*     */     private final Map<String, Integer> lKeyValues;
/*     */ 
/*     */     
/*     */     CaseInsensitiveTextStrategy(int field, Calendar definingCalendar, Locale locale) {
/* 704 */       this.field = field;
/* 705 */       this.locale = locale;
/*     */       
/* 707 */       StringBuilder regex = new StringBuilder();
/* 708 */       regex.append("((?iu)");
/* 709 */       this.lKeyValues = FastDateParser.appendDisplayNames(definingCalendar, locale, field, regex);
/* 710 */       regex.setLength(regex.length() - 1);
/* 711 */       regex.append(")");
/* 712 */       createPattern(regex);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void setCalendar(FastDateParser parser, Calendar cal, String value) {
/* 720 */       String lowerCase = value.toLowerCase(this.locale);
/* 721 */       Integer iVal = this.lKeyValues.get(lowerCase);
/* 722 */       if (iVal == null)
/*     */       {
/* 724 */         iVal = this.lKeyValues.get(lowerCase + '.');
/*     */       }
/* 726 */       cal.set(this.field, iVal.intValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class NumberStrategy
/*     */     extends Strategy
/*     */   {
/*     */     private final int field;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     NumberStrategy(int field) {
/* 742 */       this.field = field;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean isNumber() {
/* 750 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     boolean parse(FastDateParser parser, Calendar calendar, String source, ParsePosition pos, int maxWidth) {
/* 755 */       int idx = pos.getIndex();
/* 756 */       int last = source.length();
/*     */       
/* 758 */       if (maxWidth == 0) {
/*     */         
/* 760 */         for (; idx < last; idx++) {
/* 761 */           char c = source.charAt(idx);
/* 762 */           if (!Character.isWhitespace(c)) {
/*     */             break;
/*     */           }
/*     */         } 
/* 766 */         pos.setIndex(idx);
/*     */       } else {
/* 768 */         int end = idx + maxWidth;
/* 769 */         if (last > end) {
/* 770 */           last = end;
/*     */         }
/*     */       } 
/*     */       
/* 774 */       for (; idx < last; idx++) {
/* 775 */         char c = source.charAt(idx);
/* 776 */         if (!Character.isDigit(c)) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 781 */       if (pos.getIndex() == idx) {
/* 782 */         pos.setErrorIndex(idx);
/* 783 */         return false;
/*     */       } 
/*     */       
/* 786 */       int value = Integer.parseInt(source.substring(pos.getIndex(), idx));
/* 787 */       pos.setIndex(idx);
/*     */       
/* 789 */       calendar.set(this.field, modify(parser, value));
/* 790 */       return true;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int modify(FastDateParser parser, int iValue) {
/* 800 */       return iValue;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 805 */   private static final Strategy ABBREVIATED_YEAR_STRATEGY = new NumberStrategy(1)
/*     */     {
/*     */ 
/*     */       
/*     */       int modify(FastDateParser parser, int iValue)
/*     */       {
/* 811 */         return (iValue < 100) ? parser.adjustYear(iValue) : iValue;
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   static class TimeZoneStrategy
/*     */     extends PatternStrategy
/*     */   {
/*     */     private static final String RFC_822_TIME_ZONE = "[+-]\\d{4}";
/*     */     
/*     */     private static final String GMT_OPTION = "GMT[+-]\\d{1,2}:\\d{2}";
/*     */     private final Locale locale;
/* 823 */     private final Map<String, TzInfo> tzNames = new HashMap<>();
/*     */     private static final int ID = 0;
/*     */     
/*     */     private static class TzInfo { TimeZone zone;
/*     */       int dstOffset;
/*     */       
/*     */       TzInfo(TimeZone tz, boolean useDst) {
/* 830 */         this.zone = tz;
/* 831 */         this.dstOffset = useDst ? tz.getDSTSavings() : 0;
/*     */       } }
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
/*     */     TimeZoneStrategy(Locale locale) {
/* 845 */       this.locale = locale;
/*     */       
/* 847 */       StringBuilder sb = new StringBuilder();
/* 848 */       sb.append("((?iu)[+-]\\d{4}|GMT[+-]\\d{1,2}:\\d{2}");
/*     */       
/* 850 */       Set<String> sorted = new TreeSet<>(FastDateParser.LONGER_FIRST_LOWERCASE);
/*     */       
/* 852 */       String[][] zones = DateFormatSymbols.getInstance(locale).getZoneStrings();
/* 853 */       for (String[] zoneNames : zones) {
/*     */         
/* 855 */         String tzId = zoneNames[0];
/* 856 */         if (!tzId.equalsIgnoreCase("GMT")) {
/*     */ 
/*     */           
/* 859 */           TimeZone tz = TimeZone.getTimeZone(tzId);
/*     */ 
/*     */           
/* 862 */           TzInfo standard = new TzInfo(tz, false);
/* 863 */           TzInfo tzInfo = standard;
/* 864 */           for (int i = 1; i < zoneNames.length; i++) {
/* 865 */             switch (i) {
/*     */               
/*     */               case 3:
/* 868 */                 tzInfo = new TzInfo(tz, true);
/*     */                 break;
/*     */               case 5:
/* 871 */                 tzInfo = standard;
/*     */                 break;
/*     */             } 
/*     */ 
/*     */             
/* 876 */             if (zoneNames[i] != null) {
/* 877 */               String key = zoneNames[i].toLowerCase(locale);
/*     */ 
/*     */               
/* 880 */               if (sorted.add(key)) {
/* 881 */                 this.tzNames.put(key, tzInfo);
/*     */               }
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 888 */       for (String zoneName : sorted) {
/* 889 */         FastDateParser.simpleQuote(sb.append('|'), zoneName);
/*     */       }
/* 891 */       sb.append(")");
/* 892 */       createPattern(sb);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void setCalendar(FastDateParser parser, Calendar cal, String timeZone) {
/* 900 */       TimeZone tz = FastTimeZone.getGmtTimeZone(timeZone);
/* 901 */       if (tz != null) {
/* 902 */         cal.setTimeZone(tz);
/*     */       } else {
/* 904 */         String lowerCase = timeZone.toLowerCase(this.locale);
/* 905 */         TzInfo tzInfo = this.tzNames.get(lowerCase);
/* 906 */         if (tzInfo == null)
/*     */         {
/* 908 */           tzInfo = this.tzNames.get(lowerCase + '.');
/*     */         }
/* 910 */         cal.set(16, tzInfo.dstOffset);
/* 911 */         cal.set(15, tzInfo.zone.getRawOffset());
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static class ISO8601TimeZoneStrategy
/*     */     extends PatternStrategy
/*     */   {
/*     */     ISO8601TimeZoneStrategy(String pattern) {
/* 924 */       createPattern(pattern);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void setCalendar(FastDateParser parser, Calendar cal, String value) {
/* 932 */       cal.setTimeZone(FastTimeZone.getGmtTimeZone(value));
/*     */     }
/*     */     
/* 935 */     private static final FastDateParser.Strategy ISO_8601_1_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}))");
/* 936 */     private static final FastDateParser.Strategy ISO_8601_2_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}\\d{2}))");
/* 937 */     private static final FastDateParser.Strategy ISO_8601_3_STRATEGY = new ISO8601TimeZoneStrategy("(Z|(?:[+-]\\d{2}(?::)\\d{2}))");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static FastDateParser.Strategy getStrategy(int tokenLen) {
/* 947 */       switch (tokenLen) {
/*     */         case 1:
/* 949 */           return ISO_8601_1_STRATEGY;
/*     */         case 2:
/* 951 */           return ISO_8601_2_STRATEGY;
/*     */         case 3:
/* 953 */           return ISO_8601_3_STRATEGY;
/*     */       } 
/* 955 */       throw new IllegalArgumentException("invalid number of X");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/* 960 */   private static final Strategy NUMBER_MONTH_STRATEGY = new NumberStrategy(2)
/*     */     {
/*     */       int modify(FastDateParser parser, int iValue) {
/* 963 */         return iValue - 1;
/*     */       }
/*     */     };
/*     */   
/* 967 */   private static final Strategy LITERAL_YEAR_STRATEGY = new NumberStrategy(1);
/* 968 */   private static final Strategy WEEK_OF_YEAR_STRATEGY = new NumberStrategy(3);
/* 969 */   private static final Strategy WEEK_OF_MONTH_STRATEGY = new NumberStrategy(4);
/* 970 */   private static final Strategy DAY_OF_YEAR_STRATEGY = new NumberStrategy(6);
/* 971 */   private static final Strategy DAY_OF_MONTH_STRATEGY = new NumberStrategy(5);
/* 972 */   private static final Strategy DAY_OF_WEEK_STRATEGY = new NumberStrategy(7)
/*     */     {
/*     */       int modify(FastDateParser parser, int iValue) {
/* 975 */         return (iValue == 7) ? 1 : (iValue + 1);
/*     */       }
/*     */     };
/*     */   
/* 979 */   private static final Strategy DAY_OF_WEEK_IN_MONTH_STRATEGY = new NumberStrategy(8);
/* 980 */   private static final Strategy HOUR_OF_DAY_STRATEGY = new NumberStrategy(11);
/* 981 */   private static final Strategy HOUR24_OF_DAY_STRATEGY = new NumberStrategy(11)
/*     */     {
/*     */       int modify(FastDateParser parser, int iValue) {
/* 984 */         return (iValue == 24) ? 0 : iValue;
/*     */       }
/*     */     };
/*     */   
/* 988 */   private static final Strategy HOUR12_STRATEGY = new NumberStrategy(10)
/*     */     {
/*     */       int modify(FastDateParser parser, int iValue) {
/* 991 */         return (iValue == 12) ? 0 : iValue;
/*     */       }
/*     */     };
/*     */   
/* 995 */   private static final Strategy HOUR_STRATEGY = new NumberStrategy(10);
/* 996 */   private static final Strategy MINUTE_STRATEGY = new NumberStrategy(12);
/* 997 */   private static final Strategy SECOND_STRATEGY = new NumberStrategy(13);
/* 998 */   private static final Strategy MILLISECOND_STRATEGY = new NumberStrategy(14);
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/time/FastDateParser.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */