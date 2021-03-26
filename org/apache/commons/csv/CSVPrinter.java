/*     */ package org.apache.commons.csv;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.Flushable;
/*     */ import java.io.IOException;
/*     */ import java.sql.ResultSet;
/*     */ import java.sql.SQLException;
/*     */ import java.util.Arrays;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CSVPrinter
/*     */   implements Closeable, Flushable
/*     */ {
/*     */   private final Appendable out;
/*     */   private final CSVFormat format;
/*     */   private boolean newRecord = true;
/*     */   
/*     */   public CSVPrinter(Appendable out, CSVFormat format) throws IOException {
/*  94 */     Assertions.notNull(out, "out");
/*  95 */     Assertions.notNull(format, "format");
/*     */     
/*  97 */     this.out = out;
/*  98 */     this.format = format;
/*     */ 
/*     */     
/* 101 */     if (format.getHeaderComments() != null) {
/* 102 */       for (String line : format.getHeaderComments()) {
/* 103 */         if (line != null) {
/* 104 */           printComment(line);
/*     */         }
/*     */       } 
/*     */     }
/* 108 */     if (format.getHeader() != null && !format.getSkipHeaderRecord()) {
/* 109 */       printRecord((Object[])format.getHeader());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() throws IOException {
/* 119 */     close(false);
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
/*     */   public void close(boolean flush) throws IOException {
/* 131 */     if (flush || this.format.getAutoFlush()) {
/* 132 */       flush();
/*     */     }
/* 134 */     if (this.out instanceof Closeable) {
/* 135 */       ((Closeable)this.out).close();
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
/*     */   public void flush() throws IOException {
/* 147 */     if (this.out instanceof Flushable) {
/* 148 */       ((Flushable)this.out).flush();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Appendable getOut() {
/* 158 */     return this.out;
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
/*     */   public void print(Object value) throws IOException {
/* 170 */     this.format.print(value, this.out, this.newRecord);
/* 171 */     this.newRecord = false;
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
/*     */   public void printComment(String comment) throws IOException {
/* 196 */     if (!this.format.isCommentMarkerSet()) {
/*     */       return;
/*     */     }
/* 199 */     if (!this.newRecord) {
/* 200 */       println();
/*     */     }
/* 202 */     this.out.append(this.format.getCommentMarker().charValue());
/* 203 */     this.out.append(' ');
/* 204 */     for (int i = 0; i < comment.length(); i++) {
/* 205 */       char c = comment.charAt(i);
/* 206 */       switch (c) {
/*     */         case '\r':
/* 208 */           if (i + 1 < comment.length() && comment.charAt(i + 1) == '\n') {
/* 209 */             i++;
/*     */           }
/*     */         
/*     */         case '\n':
/* 213 */           println();
/* 214 */           this.out.append(this.format.getCommentMarker().charValue());
/* 215 */           this.out.append(' ');
/*     */           break;
/*     */         default:
/* 218 */           this.out.append(c);
/*     */           break;
/*     */       } 
/*     */     } 
/* 222 */     println();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void println() throws IOException {
/* 232 */     this.format.println(this.out);
/* 233 */     this.newRecord = true;
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
/*     */   public void printRecord(Iterable<?> values) throws IOException {
/* 250 */     for (Object value : values) {
/* 251 */       print(value);
/*     */     }
/* 253 */     println();
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
/*     */   public void printRecord(Object... values) throws IOException {
/* 270 */     this.format.printRecord(this.out, values);
/* 271 */     this.newRecord = true;
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
/*     */   public void printRecords(Iterable<?> values) throws IOException {
/* 314 */     for (Object value : values) {
/* 315 */       if (value instanceof Object[]) {
/* 316 */         printRecord((Object[])value); continue;
/* 317 */       }  if (value instanceof Iterable) {
/* 318 */         printRecord((Iterable)value); continue;
/*     */       } 
/* 320 */       printRecord(new Object[] { value });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printRecords(Object... values) throws IOException {
/* 365 */     printRecords(Arrays.asList(values));
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
/*     */   public void printRecords(ResultSet resultSet) throws SQLException, IOException {
/* 379 */     int columnCount = resultSet.getMetaData().getColumnCount();
/* 380 */     while (resultSet.next()) {
/* 381 */       for (int i = 1; i <= columnCount; i++) {
/* 382 */         print(resultSet.getObject(i));
/*     */       }
/* 384 */       println();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/csv/CSVPrinter.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */