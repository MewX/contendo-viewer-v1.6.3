/*     */ package org.apache.batik.css.parser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ParseException
/*     */   extends RuntimeException
/*     */ {
/*     */   protected Exception exception;
/*     */   protected int lineNumber;
/*     */   protected int columnNumber;
/*     */   
/*     */   public ParseException(String message, int line, int column) {
/*  57 */     super(message);
/*  58 */     this.exception = null;
/*  59 */     this.lineNumber = line;
/*  60 */     this.columnNumber = column;
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
/*     */   public ParseException(Exception e) {
/*  72 */     this.exception = e;
/*  73 */     this.lineNumber = -1;
/*  74 */     this.columnNumber = -1;
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
/*     */   public ParseException(String message, Exception e) {
/*  86 */     super(message);
/*  87 */     this.exception = e;
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
/*     */   public String getMessage() {
/*  99 */     String message = super.getMessage();
/*     */     
/* 101 */     if (message == null && this.exception != null) {
/* 102 */       return this.exception.getMessage();
/*     */     }
/* 104 */     return message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 113 */     return this.exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineNumber() {
/* 120 */     return this.lineNumber;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getColumnNumber() {
/* 127 */     return this.columnNumber;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/parser/ParseException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */