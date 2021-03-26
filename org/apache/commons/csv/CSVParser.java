/*     */ package org.apache.commons.csv;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.Reader;
/*     */ import java.io.StringReader;
/*     */ import java.net.URL;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.NoSuchElementException;
/*     */ import java.util.TreeMap;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CSVParser
/*     */   implements Closeable, Iterable<CSVRecord>
/*     */ {
/*     */   private final CSVFormat format;
/*     */   private final Map<String, Integer> headerMap;
/*     */   private final Lexer lexer;
/*     */   private final CSVRecordIterator csvRecordIterator;
/*     */   
/*     */   public static CSVParser parse(File file, Charset charset, CSVFormat format) throws IOException {
/* 153 */     Assertions.notNull(file, "file");
/* 154 */     Assertions.notNull(format, "format");
/* 155 */     return new CSVParser(new InputStreamReader(new FileInputStream(file), charset), format);
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
/*     */   public static CSVParser parse(InputStream inputStream, Charset charset, CSVFormat format) throws IOException {
/* 182 */     Assertions.notNull(inputStream, "inputStream");
/* 183 */     Assertions.notNull(format, "format");
/* 184 */     return parse(new InputStreamReader(inputStream, charset), format);
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
/*     */   public static CSVParser parse(Path path, Charset charset, CSVFormat format) throws IOException {
/* 204 */     Assertions.notNull(path, "path");
/* 205 */     Assertions.notNull(format, "format");
/* 206 */     return parse(Files.newInputStream(path, new java.nio.file.OpenOption[0]), charset, format);
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
/*     */   public static CSVParser parse(Reader reader, CSVFormat format) throws IOException {
/* 229 */     return new CSVParser(reader, format);
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
/*     */   public static CSVParser parse(String string, CSVFormat format) throws IOException {
/* 246 */     Assertions.notNull(string, "string");
/* 247 */     Assertions.notNull(format, "format");
/*     */     
/* 249 */     return new CSVParser(new StringReader(string), format);
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
/*     */   public static CSVParser parse(URL url, Charset charset, CSVFormat format) throws IOException {
/* 273 */     Assertions.notNull(url, "url");
/* 274 */     Assertions.notNull(charset, "charset");
/* 275 */     Assertions.notNull(format, "format");
/*     */     
/* 277 */     return new CSVParser(new InputStreamReader(url.openStream(), charset), format);
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
/* 292 */   private final List<String> recordList = new ArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private long recordNumber;
/*     */ 
/*     */ 
/*     */   
/*     */   private final long characterOffset;
/*     */ 
/*     */ 
/*     */   
/* 305 */   private final Token reusableToken = new Token();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSVParser(Reader reader, CSVFormat format) throws IOException {
/* 325 */     this(reader, format, 0L, 1L);
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
/*     */   public CSVParser(Reader reader, CSVFormat format, long characterOffset, long recordNumber) throws IOException {
/* 353 */     Assertions.notNull(reader, "reader");
/* 354 */     Assertions.notNull(format, "format");
/*     */     
/* 356 */     this.format = format;
/* 357 */     this.lexer = new Lexer(format, new ExtendedBufferedReader(reader));
/* 358 */     this.csvRecordIterator = new CSVRecordIterator();
/* 359 */     this.headerMap = initializeHeader();
/* 360 */     this.characterOffset = characterOffset;
/* 361 */     this.recordNumber = recordNumber - 1L;
/*     */   }
/*     */   
/*     */   private void addRecordValue(boolean lastRecord) {
/* 365 */     String input = this.reusableToken.content.toString();
/* 366 */     String inputClean = this.format.getTrim() ? input.trim() : input;
/* 367 */     if (lastRecord && inputClean.isEmpty() && this.format.getTrailingDelimiter()) {
/*     */       return;
/*     */     }
/* 370 */     String nullString = this.format.getNullString();
/* 371 */     this.recordList.add(inputClean.equals(nullString) ? null : inputClean);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 382 */     if (this.lexer != null) {
/* 383 */       this.lexer.close();
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
/*     */   public long getCurrentLineNumber() {
/* 398 */     return this.lexer.getCurrentLineNumber();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFirstEndOfLine() {
/* 408 */     return this.lexer.getFirstEol();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Integer> getHeaderMap() {
/* 419 */     return (this.headerMap == null) ? null : new LinkedHashMap<>(this.headerMap);
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
/*     */   public long getRecordNumber() {
/* 433 */     return this.recordNumber;
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
/*     */   public List<CSVRecord> getRecords() throws IOException {
/* 450 */     List<CSVRecord> records = new ArrayList<>(); CSVRecord rec;
/* 451 */     while ((rec = nextRecord()) != null) {
/* 452 */       records.add(rec);
/*     */     }
/* 454 */     return records;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Map<String, Integer> initializeHeader() throws IOException {
/* 464 */     Map<String, Integer> hdrMap = null;
/* 465 */     String[] formatHeader = this.format.getHeader();
/* 466 */     if (formatHeader != null) {
/* 467 */       hdrMap = this.format.getIgnoreHeaderCase() ? new TreeMap<>(String.CASE_INSENSITIVE_ORDER) : new LinkedHashMap<>();
/*     */ 
/*     */ 
/*     */       
/* 471 */       String[] headerRecord = null;
/* 472 */       if (formatHeader.length == 0) {
/*     */         
/* 474 */         CSVRecord nextRecord = nextRecord();
/* 475 */         if (nextRecord != null) {
/* 476 */           headerRecord = nextRecord.values();
/*     */         }
/*     */       } else {
/* 479 */         if (this.format.getSkipHeaderRecord()) {
/* 480 */           nextRecord();
/*     */         }
/* 482 */         headerRecord = formatHeader;
/*     */       } 
/*     */ 
/*     */       
/* 486 */       if (headerRecord != null) {
/* 487 */         for (int i = 0; i < headerRecord.length; i++) {
/* 488 */           String header = headerRecord[i];
/* 489 */           boolean containsHeader = hdrMap.containsKey(header);
/* 490 */           boolean emptyHeader = (header == null || header.trim().isEmpty());
/* 491 */           if (containsHeader && (!emptyHeader || !this.format.getAllowMissingColumnNames())) {
/* 492 */             throw new IllegalArgumentException("The header contains a duplicate name: \"" + header + "\" in " + 
/* 493 */                 Arrays.toString(headerRecord));
/*     */           }
/* 495 */           hdrMap.put(header, Integer.valueOf(i));
/*     */         } 
/*     */       }
/*     */     } 
/* 499 */     return hdrMap;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isClosed() {
/* 508 */     return this.lexer.isClosed();
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
/*     */   public Iterator<CSVRecord> iterator() {
/* 525 */     return this.csvRecordIterator;
/*     */   }
/*     */   
/*     */   class CSVRecordIterator implements Iterator<CSVRecord> {
/*     */     private CSVRecord current;
/*     */     
/*     */     private CSVRecord getNextRecord() {
/*     */       try {
/* 533 */         return CSVParser.this.nextRecord();
/* 534 */       } catch (IOException e) {
/* 535 */         throw new IllegalStateException(e
/* 536 */             .getClass().getSimpleName() + " reading next record: " + e.toString(), e);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean hasNext() {
/* 542 */       if (CSVParser.this.isClosed()) {
/* 543 */         return false;
/*     */       }
/* 545 */       if (this.current == null) {
/* 546 */         this.current = getNextRecord();
/*     */       }
/*     */       
/* 549 */       return (this.current != null);
/*     */     }
/*     */ 
/*     */     
/*     */     public CSVRecord next() {
/* 554 */       if (CSVParser.this.isClosed()) {
/* 555 */         throw new NoSuchElementException("CSVParser has been closed");
/*     */       }
/* 557 */       CSVRecord next = this.current;
/* 558 */       this.current = null;
/*     */       
/* 560 */       if (next == null) {
/*     */         
/* 562 */         next = getNextRecord();
/* 563 */         if (next == null) {
/* 564 */           throw new NoSuchElementException("No more CSV records available");
/*     */         }
/*     */       } 
/*     */       
/* 568 */       return next;
/*     */     }
/*     */ 
/*     */     
/*     */     public void remove() {
/* 573 */       throw new UnsupportedOperationException();
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
/*     */   CSVRecord nextRecord() throws IOException {
/* 585 */     CSVRecord result = null;
/* 586 */     this.recordList.clear();
/* 587 */     StringBuilder sb = null;
/* 588 */     long startCharPosition = this.lexer.getCharacterPosition() + this.characterOffset;
/*     */     do {
/* 590 */       this.reusableToken.reset();
/* 591 */       this.lexer.nextToken(this.reusableToken);
/* 592 */       switch (this.reusableToken.type) {
/*     */         case TOKEN:
/* 594 */           addRecordValue(false);
/*     */           break;
/*     */         case EORECORD:
/* 597 */           addRecordValue(true);
/*     */           break;
/*     */         case EOF:
/* 600 */           if (this.reusableToken.isReady) {
/* 601 */             addRecordValue(true);
/*     */           }
/*     */           break;
/*     */         case INVALID:
/* 605 */           throw new IOException("(line " + getCurrentLineNumber() + ") invalid parse sequence");
/*     */         case COMMENT:
/* 607 */           if (sb == null) {
/* 608 */             sb = new StringBuilder();
/*     */           } else {
/* 610 */             sb.append('\n');
/*     */           } 
/* 612 */           sb.append(this.reusableToken.content);
/* 613 */           this.reusableToken.type = Token.Type.TOKEN;
/*     */           break;
/*     */         default:
/* 616 */           throw new IllegalStateException("Unexpected Token type: " + this.reusableToken.type);
/*     */       } 
/* 618 */     } while (this.reusableToken.type == Token.Type.TOKEN);
/*     */     
/* 620 */     if (!this.recordList.isEmpty()) {
/* 621 */       this.recordNumber++;
/* 622 */       String comment = (sb == null) ? null : sb.toString();
/* 623 */       result = new CSVRecord(this.recordList.<String>toArray(new String[this.recordList.size()]), this.headerMap, comment, this.recordNumber, startCharPosition);
/*     */     } 
/*     */     
/* 626 */     return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/csv/CSVParser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */