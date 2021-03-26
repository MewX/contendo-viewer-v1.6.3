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
/*     */ public class CSSParseException
/*     */   extends CSSException
/*     */ {
/*     */   private String g;
/*     */   private int h;
/*     */   private int i;
/*     */   
/*     */   public CSSParseException(String paramString, Locator paramLocator) {
/*  49 */     super(paramString);
/*  50 */     this.f = 2;
/*  51 */     this.g = paramLocator.getURI();
/*  52 */     this.h = paramLocator.getLineNumber();
/*  53 */     this.i = paramLocator.getColumnNumber();
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
/*     */   public CSSParseException(String paramString, Locator paramLocator, Exception paramException) {
/*  75 */     super((short)2, paramString, paramException);
/*  76 */     this.g = paramLocator.getURI();
/*  77 */     this.h = paramLocator.getLineNumber();
/*  78 */     this.i = paramLocator.getColumnNumber();
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
/*     */   public CSSParseException(String paramString1, String paramString2, int paramInt1, int paramInt2) {
/*  99 */     super(paramString1);
/* 100 */     this.f = 2;
/* 101 */     this.g = paramString2;
/* 102 */     this.h = paramInt1;
/* 103 */     this.i = paramInt2;
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
/*     */   public CSSParseException(String paramString1, String paramString2, int paramInt1, int paramInt2, Exception paramException) {
/* 129 */     super((short)2, paramString1, paramException);
/* 130 */     this.g = paramString2;
/* 131 */     this.h = paramInt1;
/* 132 */     this.i = paramInt2;
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
/*     */   public String getURI() {
/* 145 */     return this.g;
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
/*     */   public int getLineNumber() {
/* 157 */     return this.h;
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
/*     */   public int getColumnNumber() {
/* 171 */     return this.i;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/w3c/css/sac/CSSParseException.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */