/*     */ package org.w3c.css.sac;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSSException
/*     */   extends RuntimeException
/*     */ {
/*     */   protected String a;
/*     */   public static final short SAC_UNSPECIFIED_ERR = 0;
/*     */   public static final short SAC_NOT_SUPPORTED_ERR = 1;
/*     */   public static final short SAC_SYNTAX_ERR = 2;
/*     */   protected static final String b = "unknown error";
/*     */   protected static final String c = "not supported";
/*     */   protected static final String d = "syntax error";
/*     */   protected Exception e;
/*     */   protected short f;
/*     */   
/*     */   public CSSException() {}
/*     */   
/*     */   public CSSException(String paramString) {
/*  70 */     this.f = 0;
/*  71 */     this.a = paramString;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSException(Exception paramException) {
/*  79 */     this.f = 0;
/*  80 */     this.e = paramException;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSException(short paramShort) {
/*  88 */     this.f = paramShort;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSException(short paramShort, String paramString, Exception paramException) {
/*  98 */     this.f = paramShort;
/*  99 */     this.a = paramString;
/* 100 */     this.e = paramException;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 110 */     if (this.a != null)
/* 111 */       return this.a; 
/* 112 */     if (this.e != null) {
/* 113 */       return this.e.getMessage();
/*     */     }
/* 115 */     switch (this.f) {
/*     */       case 0:
/* 117 */         return "unknown error";
/*     */       case 1:
/* 119 */         return "not supported";
/*     */       case 2:
/* 121 */         return "syntax error";
/*     */     } 
/* 123 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCode() {
/* 132 */     return this.f;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 139 */     return this.e;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/CSSException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */