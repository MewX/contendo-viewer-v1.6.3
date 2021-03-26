/*      */ package org.apache.commons.csv;
/*      */ 
/*      */ import java.io.File;
/*      */ import java.io.FileOutputStream;
/*      */ import java.io.IOException;
/*      */ import java.io.OutputStreamWriter;
/*      */ import java.io.Reader;
/*      */ import java.io.Serializable;
/*      */ import java.io.StringWriter;
/*      */ import java.nio.charset.Charset;
/*      */ import java.nio.file.Files;
/*      */ import java.nio.file.Path;
/*      */ import java.sql.ResultSet;
/*      */ import java.sql.ResultSetMetaData;
/*      */ import java.sql.SQLException;
/*      */ import java.util.Arrays;
/*      */ import java.util.HashSet;
/*      */ import java.util.Set;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class CSVFormat
/*      */   implements Serializable
/*      */ {
/*      */   public enum Predefined
/*      */   {
/*  173 */     Default((String)CSVFormat.DEFAULT),
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  178 */     Excel((String)CSVFormat.EXCEL),
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  184 */     InformixUnload((String)CSVFormat.INFORMIX_UNLOAD),
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  190 */     InformixUnloadCsv((String)CSVFormat.INFORMIX_UNLOAD_CSV),
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  195 */     MySQL((String)CSVFormat.MYSQL),
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  200 */     Oracle((String)CSVFormat.ORACLE),
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  206 */     PostgreSQLCsv((String)CSVFormat.POSTGRESQL_CSV),
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  211 */     PostgreSQLText((String)CSVFormat.POSTGRESQL_TEXT),
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  216 */     RFC4180((String)CSVFormat.RFC4180),
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  221 */     TDF((String)CSVFormat.TDF);
/*      */     
/*      */     private final CSVFormat format;
/*      */     
/*      */     Predefined(CSVFormat format) {
/*  226 */       this.format = format;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public CSVFormat getFormat() {
/*  235 */       return this.format;
/*      */     }
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
/*  254 */   public static final CSVFormat DEFAULT = new CSVFormat(',', Constants.DOUBLE_QUOTE_CHAR, null, null, null, false, true, "\r\n", null, null, null, false, false, false, false, false, false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  287 */   public static final CSVFormat EXCEL = DEFAULT
/*  288 */     .withIgnoreEmptyLines(false)
/*  289 */     .withAllowMissingColumnNames();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  317 */   public static final CSVFormat INFORMIX_UNLOAD = DEFAULT
/*  318 */     .withDelimiter('|')
/*  319 */     .withEscape('\\')
/*  320 */     .withQuote(Constants.DOUBLE_QUOTE_CHAR)
/*  321 */     .withRecordSeparator('\n');
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  348 */   public static final CSVFormat INFORMIX_UNLOAD_CSV = DEFAULT
/*  349 */     .withDelimiter(',')
/*  350 */     .withQuote(Constants.DOUBLE_QUOTE_CHAR)
/*  351 */     .withRecordSeparator('\n');
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  380 */   public static final CSVFormat MYSQL = DEFAULT
/*  381 */     .withDelimiter('\t')
/*  382 */     .withEscape('\\')
/*  383 */     .withIgnoreEmptyLines(false)
/*  384 */     .withQuote((Character)null)
/*  385 */     .withRecordSeparator('\n')
/*  386 */     .withNullString("\\N")
/*  387 */     .withQuoteMode(QuoteMode.ALL_NON_NULL);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  418 */   public static final CSVFormat ORACLE = DEFAULT
/*  419 */     .withDelimiter(',')
/*  420 */     .withEscape('\\')
/*  421 */     .withIgnoreEmptyLines(false)
/*  422 */     .withQuote(Constants.DOUBLE_QUOTE_CHAR)
/*  423 */     .withNullString("\\N")
/*  424 */     .withTrim()
/*  425 */     .withSystemRecordSeparator()
/*  426 */     .withQuoteMode(QuoteMode.MINIMAL);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  456 */   public static final CSVFormat POSTGRESQL_CSV = DEFAULT
/*  457 */     .withDelimiter(',')
/*  458 */     .withEscape(Constants.DOUBLE_QUOTE_CHAR)
/*  459 */     .withIgnoreEmptyLines(false)
/*  460 */     .withQuote(Constants.DOUBLE_QUOTE_CHAR)
/*  461 */     .withRecordSeparator('\n')
/*  462 */     .withNullString("")
/*  463 */     .withQuoteMode(QuoteMode.ALL_NON_NULL);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  493 */   public static final CSVFormat POSTGRESQL_TEXT = DEFAULT
/*  494 */     .withDelimiter('\t')
/*  495 */     .withEscape(Constants.DOUBLE_QUOTE_CHAR)
/*  496 */     .withIgnoreEmptyLines(false)
/*  497 */     .withQuote(Constants.DOUBLE_QUOTE_CHAR)
/*  498 */     .withRecordSeparator('\n')
/*  499 */     .withNullString("\\N")
/*  500 */     .withQuoteMode(QuoteMode.ALL_NON_NULL);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  518 */   public static final CSVFormat RFC4180 = DEFAULT.withIgnoreEmptyLines(false);
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private static final long serialVersionUID = 1L;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  538 */   public static final CSVFormat TDF = DEFAULT
/*  539 */     .withDelimiter('\t')
/*  540 */     .withIgnoreSurroundingSpaces();
/*      */   
/*      */   private final boolean allowMissingColumnNames;
/*      */   private final Character commentMarker;
/*      */   private final char delimiter;
/*      */   private final Character escapeCharacter;
/*      */   private final String[] header;
/*      */   private final String[] headerComments;
/*      */   private final boolean ignoreEmptyLines;
/*      */   private final boolean ignoreHeaderCase;
/*      */   
/*      */   private static boolean isLineBreak(char c) {
/*  552 */     return (c == '\n' || c == '\r');
/*      */   }
/*      */ 
/*      */   
/*      */   private final boolean ignoreSurroundingSpaces;
/*      */   
/*      */   private final String nullString;
/*      */   
/*      */   private final Character quoteCharacter;
/*      */   private final QuoteMode quoteMode;
/*      */   
/*      */   private static boolean isLineBreak(Character c) {
/*  564 */     return (c != null && isLineBreak(c.charValue()));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final String recordSeparator;
/*      */ 
/*      */ 
/*      */   
/*      */   private final boolean skipHeaderRecord;
/*      */ 
/*      */   
/*      */   private final boolean trailingDelimiter;
/*      */ 
/*      */   
/*      */   private final boolean trim;
/*      */ 
/*      */   
/*      */   private final boolean autoFlush;
/*      */ 
/*      */ 
/*      */   
/*      */   public static CSVFormat newFormat(char delimiter) {
/*  588 */     return new CSVFormat(delimiter, null, null, null, null, false, false, null, null, null, null, false, false, false, false, false, false);
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
/*      */   public static CSVFormat valueOf(String format) {
/*  601 */     return Predefined.valueOf(format).getFormat();
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
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private CSVFormat(char delimiter, Character quoteChar, QuoteMode quoteMode, Character commentStart, Character escape, boolean ignoreSurroundingSpaces, boolean ignoreEmptyLines, String recordSeparator, String nullString, Object[] headerComments, String[] header, boolean skipHeaderRecord, boolean allowMissingColumnNames, boolean ignoreHeaderCase, boolean trim, boolean trailingDelimiter, boolean autoFlush) {
/*  683 */     this.delimiter = delimiter;
/*  684 */     this.quoteCharacter = quoteChar;
/*  685 */     this.quoteMode = quoteMode;
/*  686 */     this.commentMarker = commentStart;
/*  687 */     this.escapeCharacter = escape;
/*  688 */     this.ignoreSurroundingSpaces = ignoreSurroundingSpaces;
/*  689 */     this.allowMissingColumnNames = allowMissingColumnNames;
/*  690 */     this.ignoreEmptyLines = ignoreEmptyLines;
/*  691 */     this.recordSeparator = recordSeparator;
/*  692 */     this.nullString = nullString;
/*  693 */     this.headerComments = toStringArray(headerComments);
/*  694 */     this.header = (header == null) ? null : (String[])header.clone();
/*  695 */     this.skipHeaderRecord = skipHeaderRecord;
/*  696 */     this.ignoreHeaderCase = ignoreHeaderCase;
/*  697 */     this.trailingDelimiter = trailingDelimiter;
/*  698 */     this.trim = trim;
/*  699 */     this.autoFlush = autoFlush;
/*  700 */     validate();
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  705 */     if (this == obj) {
/*  706 */       return true;
/*      */     }
/*  708 */     if (obj == null) {
/*  709 */       return false;
/*      */     }
/*  711 */     if (getClass() != obj.getClass()) {
/*  712 */       return false;
/*      */     }
/*      */     
/*  715 */     CSVFormat other = (CSVFormat)obj;
/*  716 */     if (this.delimiter != other.delimiter) {
/*  717 */       return false;
/*      */     }
/*  719 */     if (this.quoteMode != other.quoteMode) {
/*  720 */       return false;
/*      */     }
/*  722 */     if (this.quoteCharacter == null) {
/*  723 */       if (other.quoteCharacter != null) {
/*  724 */         return false;
/*      */       }
/*  726 */     } else if (!this.quoteCharacter.equals(other.quoteCharacter)) {
/*  727 */       return false;
/*      */     } 
/*  729 */     if (this.commentMarker == null) {
/*  730 */       if (other.commentMarker != null) {
/*  731 */         return false;
/*      */       }
/*  733 */     } else if (!this.commentMarker.equals(other.commentMarker)) {
/*  734 */       return false;
/*      */     } 
/*  736 */     if (this.escapeCharacter == null) {
/*  737 */       if (other.escapeCharacter != null) {
/*  738 */         return false;
/*      */       }
/*  740 */     } else if (!this.escapeCharacter.equals(other.escapeCharacter)) {
/*  741 */       return false;
/*      */     } 
/*  743 */     if (this.nullString == null) {
/*  744 */       if (other.nullString != null) {
/*  745 */         return false;
/*      */       }
/*  747 */     } else if (!this.nullString.equals(other.nullString)) {
/*  748 */       return false;
/*      */     } 
/*  750 */     if (!Arrays.equals((Object[])this.header, (Object[])other.header)) {
/*  751 */       return false;
/*      */     }
/*  753 */     if (this.ignoreSurroundingSpaces != other.ignoreSurroundingSpaces) {
/*  754 */       return false;
/*      */     }
/*  756 */     if (this.ignoreEmptyLines != other.ignoreEmptyLines) {
/*  757 */       return false;
/*      */     }
/*  759 */     if (this.skipHeaderRecord != other.skipHeaderRecord) {
/*  760 */       return false;
/*      */     }
/*  762 */     if (this.recordSeparator == null) {
/*  763 */       if (other.recordSeparator != null) {
/*  764 */         return false;
/*      */       }
/*  766 */     } else if (!this.recordSeparator.equals(other.recordSeparator)) {
/*  767 */       return false;
/*      */     } 
/*  769 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String format(Object... values) {
/*  780 */     StringWriter out = new StringWriter();
/*  781 */     try (CSVPrinter csvPrinter = new CSVPrinter(out, this)) {
/*  782 */       csvPrinter.printRecord(values);
/*  783 */       return out.toString().trim();
/*  784 */     } catch (IOException e) {
/*      */       
/*  786 */       throw new IllegalStateException(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAllowMissingColumnNames() {
/*  797 */     return this.allowMissingColumnNames;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getAutoFlush() {
/*  807 */     return this.autoFlush;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Character getCommentMarker() {
/*  816 */     return this.commentMarker;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char getDelimiter() {
/*  825 */     return this.delimiter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Character getEscapeCharacter() {
/*  834 */     return this.escapeCharacter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getHeader() {
/*  843 */     return (this.header != null) ? (String[])this.header.clone() : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String[] getHeaderComments() {
/*  852 */     return (this.headerComments != null) ? (String[])this.headerComments.clone() : null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIgnoreEmptyLines() {
/*  862 */     return this.ignoreEmptyLines;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIgnoreHeaderCase() {
/*  872 */     return this.ignoreHeaderCase;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getIgnoreSurroundingSpaces() {
/*  881 */     return this.ignoreSurroundingSpaces;
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
/*      */   public String getNullString() {
/*  895 */     return this.nullString;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Character getQuoteCharacter() {
/*  904 */     return this.quoteCharacter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public QuoteMode getQuoteMode() {
/*  913 */     return this.quoteMode;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getRecordSeparator() {
/*  922 */     return this.recordSeparator;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getSkipHeaderRecord() {
/*  931 */     return this.skipHeaderRecord;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTrailingDelimiter() {
/*  941 */     return this.trailingDelimiter;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getTrim() {
/*  950 */     return this.trim;
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  955 */     int prime = 31;
/*  956 */     int result = 1;
/*      */     
/*  958 */     result = 31 * result + this.delimiter;
/*  959 */     result = 31 * result + ((this.quoteMode == null) ? 0 : this.quoteMode.hashCode());
/*  960 */     result = 31 * result + ((this.quoteCharacter == null) ? 0 : this.quoteCharacter.hashCode());
/*  961 */     result = 31 * result + ((this.commentMarker == null) ? 0 : this.commentMarker.hashCode());
/*  962 */     result = 31 * result + ((this.escapeCharacter == null) ? 0 : this.escapeCharacter.hashCode());
/*  963 */     result = 31 * result + ((this.nullString == null) ? 0 : this.nullString.hashCode());
/*  964 */     result = 31 * result + (this.ignoreSurroundingSpaces ? 1231 : 1237);
/*  965 */     result = 31 * result + (this.ignoreHeaderCase ? 1231 : 1237);
/*  966 */     result = 31 * result + (this.ignoreEmptyLines ? 1231 : 1237);
/*  967 */     result = 31 * result + (this.skipHeaderRecord ? 1231 : 1237);
/*  968 */     result = 31 * result + ((this.recordSeparator == null) ? 0 : this.recordSeparator.hashCode());
/*  969 */     result = 31 * result + Arrays.hashCode((Object[])this.header);
/*  970 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isCommentMarkerSet() {
/*  981 */     return (this.commentMarker != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isEscapeCharacterSet() {
/*  990 */     return (this.escapeCharacter != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNullStringSet() {
/*  999 */     return (this.nullString != null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isQuoteCharacterSet() {
/* 1008 */     return (this.quoteCharacter != null);
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
/*      */   public CSVParser parse(Reader in) throws IOException {
/* 1025 */     return new CSVParser(in, this);
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
/*      */   public CSVPrinter print(Appendable out) throws IOException {
/* 1042 */     return new CSVPrinter(out, this);
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
/*      */   public CSVPrinter print(File out, Charset charset) throws IOException {
/* 1064 */     return new CSVPrinter(new OutputStreamWriter(new FileOutputStream(out), charset), this);
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
/*      */   public void print(Object value, Appendable out, boolean newRecord) throws IOException {
/* 1085 */     if (value == null) {
/*      */       
/* 1087 */       if (null == this.nullString) {
/* 1088 */         charSequence = "";
/*      */       }
/* 1090 */       else if (QuoteMode.ALL == this.quoteMode) {
/* 1091 */         charSequence = this.quoteCharacter + this.nullString + this.quoteCharacter;
/*      */       } else {
/* 1093 */         charSequence = this.nullString;
/*      */       } 
/*      */     } else {
/*      */       
/* 1097 */       charSequence = (value instanceof CharSequence) ? (CharSequence)value : value.toString();
/*      */     } 
/* 1099 */     CharSequence charSequence = getTrim() ? trim(charSequence) : charSequence;
/* 1100 */     print(value, charSequence, 0, charSequence.length(), out, newRecord);
/*      */   }
/*      */ 
/*      */   
/*      */   private void print(Object object, CharSequence value, int offset, int len, Appendable out, boolean newRecord) throws IOException {
/* 1105 */     if (!newRecord) {
/* 1106 */       out.append(getDelimiter());
/*      */     }
/* 1108 */     if (object == null) {
/* 1109 */       out.append(value);
/* 1110 */     } else if (isQuoteCharacterSet()) {
/*      */       
/* 1112 */       printAndQuote(object, value, offset, len, out, newRecord);
/* 1113 */     } else if (isEscapeCharacterSet()) {
/* 1114 */       printAndEscape(value, offset, len, out);
/*      */     } else {
/* 1116 */       out.append(value, offset, offset + len);
/*      */     } 
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
/*      */   public CSVPrinter print(Path out, Charset charset) throws IOException {
/* 1137 */     return print(Files.newBufferedWriter(out, charset, new java.nio.file.OpenOption[0]));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void printAndEscape(CharSequence value, int offset, int len, Appendable out) throws IOException {
/* 1145 */     int start = offset;
/* 1146 */     int pos = offset;
/* 1147 */     int end = offset + len;
/*      */     
/* 1149 */     char delim = getDelimiter();
/* 1150 */     char escape = getEscapeCharacter().charValue();
/*      */     
/* 1152 */     while (pos < end) {
/* 1153 */       char c = value.charAt(pos);
/* 1154 */       if (c == '\r' || c == '\n' || c == delim || c == escape) {
/*      */         
/* 1156 */         if (pos > start) {
/* 1157 */           out.append(value, start, pos);
/*      */         }
/* 1159 */         if (c == '\n') {
/* 1160 */           c = 'n';
/* 1161 */         } else if (c == '\r') {
/* 1162 */           c = 'r';
/*      */         } 
/*      */         
/* 1165 */         out.append(escape);
/* 1166 */         out.append(c);
/*      */         
/* 1168 */         start = pos + 1;
/*      */       } 
/*      */       
/* 1171 */       pos++;
/*      */     } 
/*      */ 
/*      */     
/* 1175 */     if (pos > start) {
/* 1176 */       out.append(value, start, pos);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void printAndQuote(Object object, CharSequence value, int offset, int len, Appendable out, boolean newRecord) throws IOException {
/* 1186 */     boolean quote = false;
/* 1187 */     int start = offset;
/* 1188 */     int pos = offset;
/* 1189 */     int end = offset + len;
/*      */     
/* 1191 */     char delimChar = getDelimiter();
/* 1192 */     char quoteChar = getQuoteCharacter().charValue();
/*      */     
/* 1194 */     QuoteMode quoteModePolicy = getQuoteMode();
/* 1195 */     if (quoteModePolicy == null) {
/* 1196 */       quoteModePolicy = QuoteMode.MINIMAL;
/*      */     }
/* 1198 */     switch (quoteModePolicy) {
/*      */       case ALL:
/*      */       case ALL_NON_NULL:
/* 1201 */         quote = true;
/*      */         break;
/*      */       case NON_NUMERIC:
/* 1204 */         quote = !(object instanceof Number);
/*      */         break;
/*      */       
/*      */       case NONE:
/* 1208 */         printAndEscape(value, offset, len, out);
/*      */         return;
/*      */       case MINIMAL:
/* 1211 */         if (len <= 0) {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/* 1216 */           if (newRecord) {
/* 1217 */             quote = true;
/*      */           }
/*      */         } else {
/* 1220 */           char c = value.charAt(pos);
/*      */           
/* 1222 */           if (c <= '#') {
/*      */ 
/*      */ 
/*      */             
/* 1226 */             quote = true;
/*      */           } else {
/* 1228 */             while (pos < end) {
/* 1229 */               c = value.charAt(pos);
/* 1230 */               if (c == '\n' || c == '\r' || c == quoteChar || c == delimChar) {
/* 1231 */                 quote = true;
/*      */                 break;
/*      */               } 
/* 1234 */               pos++;
/*      */             } 
/*      */             
/* 1237 */             if (!quote) {
/* 1238 */               pos = end - 1;
/* 1239 */               c = value.charAt(pos);
/*      */ 
/*      */               
/* 1242 */               if (c <= ' ') {
/* 1243 */                 quote = true;
/*      */               }
/*      */             } 
/*      */           } 
/*      */         } 
/*      */         
/* 1249 */         if (!quote) {
/*      */           
/* 1251 */           out.append(value, start, end);
/*      */           return;
/*      */         } 
/*      */         break;
/*      */       default:
/* 1256 */         throw new IllegalStateException("Unexpected Quote value: " + quoteModePolicy);
/*      */     } 
/*      */     
/* 1259 */     if (!quote) {
/*      */       
/* 1261 */       out.append(value, start, end);
/*      */       
/*      */       return;
/*      */     } 
/*      */     
/* 1266 */     out.append(quoteChar);
/*      */ 
/*      */ 
/*      */     
/* 1270 */     while (pos < end) {
/* 1271 */       char c = value.charAt(pos);
/* 1272 */       if (c == quoteChar) {
/*      */ 
/*      */ 
/*      */         
/* 1276 */         out.append(value, start, pos + 1);
/*      */ 
/*      */         
/* 1279 */         start = pos;
/*      */       } 
/* 1281 */       pos++;
/*      */     } 
/*      */ 
/*      */     
/* 1285 */     out.append(value, start, pos);
/* 1286 */     out.append(quoteChar);
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
/*      */   public CSVPrinter printer() throws IOException {
/* 1302 */     return new CSVPrinter(System.out, this);
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
/*      */   public void println(Appendable out) throws IOException {
/* 1315 */     if (getTrailingDelimiter()) {
/* 1316 */       out.append(getDelimiter());
/*      */     }
/* 1318 */     if (this.recordSeparator != null) {
/* 1319 */       out.append(this.recordSeparator);
/*      */     }
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
/*      */   public void printRecord(Appendable out, Object... values) throws IOException {
/* 1341 */     for (int i = 0; i < values.length; i++) {
/* 1342 */       print(values[i], out, (i == 0));
/*      */     }
/* 1344 */     println(out);
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/* 1349 */     StringBuilder sb = new StringBuilder();
/* 1350 */     sb.append("Delimiter=<").append(this.delimiter).append('>');
/* 1351 */     if (isEscapeCharacterSet()) {
/* 1352 */       sb.append(' ');
/* 1353 */       sb.append("Escape=<").append(this.escapeCharacter).append('>');
/*      */     } 
/* 1355 */     if (isQuoteCharacterSet()) {
/* 1356 */       sb.append(' ');
/* 1357 */       sb.append("QuoteChar=<").append(this.quoteCharacter).append('>');
/*      */     } 
/* 1359 */     if (isCommentMarkerSet()) {
/* 1360 */       sb.append(' ');
/* 1361 */       sb.append("CommentStart=<").append(this.commentMarker).append('>');
/*      */     } 
/* 1363 */     if (isNullStringSet()) {
/* 1364 */       sb.append(' ');
/* 1365 */       sb.append("NullString=<").append(this.nullString).append('>');
/*      */     } 
/* 1367 */     if (this.recordSeparator != null) {
/* 1368 */       sb.append(' ');
/* 1369 */       sb.append("RecordSeparator=<").append(this.recordSeparator).append('>');
/*      */     } 
/* 1371 */     if (getIgnoreEmptyLines()) {
/* 1372 */       sb.append(" EmptyLines:ignored");
/*      */     }
/* 1374 */     if (getIgnoreSurroundingSpaces()) {
/* 1375 */       sb.append(" SurroundingSpaces:ignored");
/*      */     }
/* 1377 */     if (getIgnoreHeaderCase()) {
/* 1378 */       sb.append(" IgnoreHeaderCase:ignored");
/*      */     }
/* 1380 */     sb.append(" SkipHeaderRecord:").append(this.skipHeaderRecord);
/* 1381 */     if (this.headerComments != null) {
/* 1382 */       sb.append(' ');
/* 1383 */       sb.append("HeaderComments:").append(Arrays.toString((Object[])this.headerComments));
/*      */     } 
/* 1385 */     if (this.header != null) {
/* 1386 */       sb.append(' ');
/* 1387 */       sb.append("Header:").append(Arrays.toString((Object[])this.header));
/*      */     } 
/* 1389 */     return sb.toString();
/*      */   }
/*      */   
/*      */   private String[] toStringArray(Object[] values) {
/* 1393 */     if (values == null) {
/* 1394 */       return null;
/*      */     }
/* 1396 */     String[] strings = new String[values.length];
/* 1397 */     for (int i = 0; i < values.length; i++) {
/* 1398 */       Object value = values[i];
/* 1399 */       strings[i] = (value == null) ? null : value.toString();
/*      */     } 
/* 1401 */     return strings;
/*      */   }
/*      */   
/*      */   private CharSequence trim(CharSequence charSequence) {
/* 1405 */     if (charSequence instanceof String) {
/* 1406 */       return ((String)charSequence).trim();
/*      */     }
/* 1408 */     int count = charSequence.length();
/* 1409 */     int len = count;
/* 1410 */     int pos = 0;
/*      */     
/* 1412 */     while (pos < len && charSequence.charAt(pos) <= ' ') {
/* 1413 */       pos++;
/*      */     }
/* 1415 */     while (pos < len && charSequence.charAt(len - 1) <= ' ') {
/* 1416 */       len--;
/*      */     }
/* 1418 */     return (pos > 0 || len < count) ? charSequence.subSequence(pos, len) : charSequence;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void validate() throws IllegalArgumentException {
/* 1427 */     if (isLineBreak(this.delimiter)) {
/* 1428 */       throw new IllegalArgumentException("The delimiter cannot be a line break");
/*      */     }
/*      */     
/* 1431 */     if (this.quoteCharacter != null && this.delimiter == this.quoteCharacter.charValue()) {
/* 1432 */       throw new IllegalArgumentException("The quoteChar character and the delimiter cannot be the same ('" + this.quoteCharacter + "')");
/*      */     }
/*      */ 
/*      */     
/* 1436 */     if (this.escapeCharacter != null && this.delimiter == this.escapeCharacter.charValue()) {
/* 1437 */       throw new IllegalArgumentException("The escape character and the delimiter cannot be the same ('" + this.escapeCharacter + "')");
/*      */     }
/*      */ 
/*      */     
/* 1441 */     if (this.commentMarker != null && this.delimiter == this.commentMarker.charValue()) {
/* 1442 */       throw new IllegalArgumentException("The comment start character and the delimiter cannot be the same ('" + this.commentMarker + "')");
/*      */     }
/*      */ 
/*      */     
/* 1446 */     if (this.quoteCharacter != null && this.quoteCharacter.equals(this.commentMarker)) {
/* 1447 */       throw new IllegalArgumentException("The comment start character and the quoteChar cannot be the same ('" + this.commentMarker + "')");
/*      */     }
/*      */ 
/*      */     
/* 1451 */     if (this.escapeCharacter != null && this.escapeCharacter.equals(this.commentMarker)) {
/* 1452 */       throw new IllegalArgumentException("The comment start and the escape character cannot be the same ('" + this.commentMarker + "')");
/*      */     }
/*      */ 
/*      */     
/* 1456 */     if (this.escapeCharacter == null && this.quoteMode == QuoteMode.NONE) {
/* 1457 */       throw new IllegalArgumentException("No quotes mode set but no escape character is set");
/*      */     }
/*      */ 
/*      */     
/* 1461 */     if (this.header != null) {
/* 1462 */       Set<String> dupCheck = new HashSet<>();
/* 1463 */       for (String hdr : this.header) {
/* 1464 */         if (!dupCheck.add(hdr)) {
/* 1465 */           throw new IllegalArgumentException("The header contains a duplicate entry: '" + hdr + "' in " + 
/* 1466 */               Arrays.toString(this.header));
/*      */         }
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSVFormat withAllowMissingColumnNames() {
/* 1480 */     return withAllowMissingColumnNames(true);
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
/*      */   public CSVFormat withAllowMissingColumnNames(boolean allowMissingColumnNames) {
/* 1492 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withAutoFlush(boolean autoFlush) {
/* 1507 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, autoFlush);
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
/*      */   public CSVFormat withCommentMarker(char commentMarker) {
/* 1524 */     return withCommentMarker(Character.valueOf(commentMarker));
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
/*      */   public CSVFormat withCommentMarker(Character commentMarker) {
/* 1539 */     if (isLineBreak(commentMarker)) {
/* 1540 */       throw new IllegalArgumentException("The comment start marker character cannot be a line break");
/*      */     }
/* 1542 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withDelimiter(char delimiter) {
/* 1557 */     if (isLineBreak(delimiter)) {
/* 1558 */       throw new IllegalArgumentException("The delimiter cannot be a line break");
/*      */     }
/* 1560 */     return new CSVFormat(delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withEscape(char escape) {
/* 1575 */     return withEscape(Character.valueOf(escape));
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
/*      */   public CSVFormat withEscape(Character escape) {
/* 1588 */     if (isLineBreak(escape)) {
/* 1589 */       throw new IllegalArgumentException("The escape character cannot be a line break");
/*      */     }
/* 1591 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, escape, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withFirstRecordAsHeader() {
/* 1613 */     return withHeader(new String[0]).withSkipHeaderRecord();
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
/*      */   public CSVFormat withHeader(Class<? extends Enum<?>> headerEnum) {
/* 1643 */     String[] header = null;
/* 1644 */     if (headerEnum != null) {
/* 1645 */       Enum[] arrayOfEnum = (Enum[])headerEnum.getEnumConstants();
/* 1646 */       header = new String[arrayOfEnum.length];
/* 1647 */       for (int i = 0; i < arrayOfEnum.length; i++) {
/* 1648 */         header[i] = arrayOfEnum[i].name();
/*      */       }
/*      */     } 
/* 1651 */     return withHeader(header);
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
/*      */   public CSVFormat withHeader(ResultSet resultSet) throws SQLException {
/* 1681 */     return withHeader((resultSet != null) ? resultSet.getMetaData() : null);
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
/*      */   public CSVFormat withHeader(ResultSetMetaData metaData) throws SQLException {
/* 1711 */     String[] labels = null;
/* 1712 */     if (metaData != null) {
/* 1713 */       int columnCount = metaData.getColumnCount();
/* 1714 */       labels = new String[columnCount];
/* 1715 */       for (int i = 0; i < columnCount; i++) {
/* 1716 */         labels[i] = metaData.getColumnLabel(i + 1);
/*      */       }
/*      */     } 
/* 1719 */     return withHeader(labels);
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
/*      */   public CSVFormat withHeader(String... header) {
/* 1746 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withHeaderComments(Object... headerComments) {
/* 1767 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withIgnoreEmptyLines() {
/* 1780 */     return withIgnoreEmptyLines(true);
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
/*      */   public CSVFormat withIgnoreEmptyLines(boolean ignoreEmptyLines) {
/* 1792 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withIgnoreHeaderCase() {
/* 1805 */     return withIgnoreHeaderCase(true);
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
/*      */   public CSVFormat withIgnoreHeaderCase(boolean ignoreHeaderCase) {
/* 1818 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withIgnoreSurroundingSpaces() {
/* 1831 */     return withIgnoreSurroundingSpaces(true);
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
/*      */   public CSVFormat withIgnoreSurroundingSpaces(boolean ignoreSurroundingSpaces) {
/* 1843 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withNullString(String nullString) {
/* 1862 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withQuote(char quoteChar) {
/* 1877 */     return withQuote(Character.valueOf(quoteChar));
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
/*      */   public CSVFormat withQuote(Character quoteChar) {
/* 1890 */     if (isLineBreak(quoteChar)) {
/* 1891 */       throw new IllegalArgumentException("The quoteChar cannot be a line break");
/*      */     }
/* 1893 */     return new CSVFormat(this.delimiter, quoteChar, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withQuoteMode(QuoteMode quoteModePolicy) {
/* 1907 */     return new CSVFormat(this.delimiter, this.quoteCharacter, quoteModePolicy, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withRecordSeparator(char recordSeparator) {
/* 1926 */     return withRecordSeparator(String.valueOf(recordSeparator));
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
/*      */   public CSVFormat withRecordSeparator(String recordSeparator) {
/* 1945 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withSkipHeaderRecord() {
/* 1959 */     return withSkipHeaderRecord(true);
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
/*      */   public CSVFormat withSkipHeaderRecord(boolean skipHeaderRecord) {
/* 1972 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, this.trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withSystemRecordSeparator() {
/* 1990 */     return withRecordSeparator(System.getProperty("line.separator"));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSVFormat withTrailingDelimiter() {
/* 2000 */     return withTrailingDelimiter(true);
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
/*      */   public CSVFormat withTrailingDelimiter(boolean trailingDelimiter) {
/* 2013 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, this.trim, trailingDelimiter, this.autoFlush);
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
/*      */   public CSVFormat withTrim() {
/* 2025 */     return withTrim(true);
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
/*      */   public CSVFormat withTrim(boolean trim) {
/* 2038 */     return new CSVFormat(this.delimiter, this.quoteCharacter, this.quoteMode, this.commentMarker, this.escapeCharacter, this.ignoreSurroundingSpaces, this.ignoreEmptyLines, this.recordSeparator, this.nullString, (Object[])this.headerComments, this.header, this.skipHeaderRecord, this.allowMissingColumnNames, this.ignoreHeaderCase, trim, this.trailingDelimiter, this.autoFlush);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/csv/CSVFormat.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */