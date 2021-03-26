/*     */ package org.apache.batik.xml;
/*     */ 
/*     */ import java.io.PrintStream;
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLException
/*     */   extends RuntimeException
/*     */ {
/*     */   protected Exception exception;
/*     */   
/*     */   public XMLException(String message) {
/*  45 */     super(message);
/*  46 */     this.exception = null;
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
/*     */   public XMLException(Exception e) {
/*  58 */     this.exception = e;
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
/*     */   public XMLException(String message, Exception e) {
/*  70 */     super(message);
/*  71 */     this.exception = e;
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
/*  83 */     String message = super.getMessage();
/*     */     
/*  85 */     if (message == null && this.exception != null) {
/*  86 */       return this.exception.getMessage();
/*     */     }
/*  88 */     return message;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Exception getException() {
/*  97 */     return this.exception;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace() {
/* 105 */     if (this.exception == null) {
/* 106 */       super.printStackTrace();
/*     */     } else {
/* 108 */       synchronized (System.err) {
/* 109 */         System.err.println(this);
/* 110 */         super.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace(PrintStream s) {
/* 122 */     if (this.exception == null) {
/* 123 */       super.printStackTrace(s);
/*     */     } else {
/* 125 */       synchronized (s) {
/* 126 */         s.println(this);
/* 127 */         super.printStackTrace();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void printStackTrace(PrintWriter s) {
/* 139 */     if (this.exception == null) {
/* 140 */       super.printStackTrace(s);
/*     */     } else {
/* 142 */       synchronized (s) {
/* 143 */         s.println(this);
/* 144 */         super.printStackTrace(s);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/xml/XMLException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */