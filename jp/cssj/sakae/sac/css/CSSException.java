/*     */ package jp.cssj.sakae.sac.css;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   private static final long serialVersionUID = 1L;
/*     */   protected String s;
/*     */   public static final short SAC_UNSPECIFIED_ERR = 0;
/*     */   public static final short SAC_NOT_SUPPORTED_ERR = 1;
/*     */   public static final short SAC_SYNTAX_ERR = 2;
/*     */   protected static final String S_SAC_UNSPECIFIED_ERR = "unknown error";
/*     */   protected static final String S_SAC_NOT_SUPPORTED_ERR = "not supported";
/*     */   protected static final String S_SAC_SYNTAX_ERR = "syntax error";
/*     */   protected Exception e;
/*     */   protected short code;
/*     */   
/*     */   public CSSException() {}
/*     */   
/*     */   public CSSException(String s) {
/*  68 */     this.code = 0;
/*  69 */     this.s = s;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSException(Exception e) {
/*  79 */     this.code = 0;
/*  80 */     this.e = e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CSSException(short code) {
/*  90 */     this.code = code;
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
/*     */   public CSSException(short code, String s, Exception e) {
/* 102 */     this.code = code;
/* 103 */     this.s = s;
/* 104 */     this.e = e;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMessage() {
/* 114 */     if (this.s != null)
/* 115 */       return this.s; 
/* 116 */     if (this.e != null) {
/* 117 */       return this.e.getMessage();
/*     */     }
/* 119 */     switch (this.code) {
/*     */       case 0:
/* 121 */         return "unknown error";
/*     */       case 1:
/* 123 */         return "not supported";
/*     */       case 2:
/* 125 */         return "syntax error";
/*     */     } 
/* 127 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getCode() {
/* 136 */     return this.code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Exception getException() {
/* 143 */     return this.e;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/CSSException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */