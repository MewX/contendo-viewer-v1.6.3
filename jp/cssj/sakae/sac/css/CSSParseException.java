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
/*     */ 
/*     */ public class CSSParseException
/*     */   extends CSSException
/*     */ {
/*     */   private static final long serialVersionUID = 1L;
/*     */   private String uri;
/*     */   private int lineNumber;
/*     */   private int columnNumber;
/*     */   
/*     */   public CSSParseException(String message, Locator locator) {
/*  61 */     super(message);
/*  62 */     this.code = 2;
/*  63 */     this.uri = locator.getURI();
/*  64 */     this.lineNumber = locator.getLineNumber();
/*  65 */     this.columnNumber = locator.getColumnNumber();
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
/*     */   public CSSParseException(String message, Locator locator, Exception e) {
/*  89 */     super((short)2, message, e);
/*  90 */     this.uri = locator.getURI();
/*  91 */     this.lineNumber = locator.getLineNumber();
/*  92 */     this.columnNumber = locator.getColumnNumber();
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
/*     */   public CSSParseException(String message, String uri, int lineNumber, int columnNumber) {
/* 119 */     super(message);
/* 120 */     this.code = 2;
/* 121 */     this.uri = uri;
/* 122 */     this.lineNumber = lineNumber;
/* 123 */     this.columnNumber = columnNumber;
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
/*     */   public CSSParseException(String message, String uri, int lineNumber, int columnNumber, Exception e) {
/* 154 */     super((short)2, message, e);
/* 155 */     this.uri = uri;
/* 156 */     this.lineNumber = lineNumber;
/* 157 */     this.columnNumber = columnNumber;
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
/*     */   public String getURI() {
/* 171 */     return this.uri;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLineNumber() {
/* 181 */     return this.lineNumber;
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
/*     */   public int getColumnNumber() {
/* 196 */     return this.columnNumber;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/sac/css/CSSParseException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */