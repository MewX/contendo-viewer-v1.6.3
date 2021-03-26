/*    */ package jp.cssj.homare.ua;
/*    */ 
/*    */ import java.io.UnsupportedEncodingException;
/*    */ import java.net.URI;
/*    */ import java.nio.charset.Charset;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class f
/*    */ {
/*    */   public static final byte a = 1;
/*    */   public static final byte b = 2;
/*    */   private URI c;
/* 14 */   private String d = "ISO-8859-1";
/*    */   
/* 16 */   private byte e = 2;
/*    */   
/*    */   public void a(URI baseURI) {
/* 19 */     this.c = baseURI;
/*    */   }
/*    */   
/*    */   public URI a() {
/* 23 */     return this.c;
/*    */   }
/*    */   
/*    */   public byte b() {
/* 27 */     return this.e;
/*    */   }
/*    */   
/*    */   public void a(byte compatibleMode) {
/* 31 */     this.e = compatibleMode;
/*    */   }
/*    */   
/*    */   public String c() {
/* 35 */     return this.d;
/*    */   }
/*    */   
/*    */   public void a(String encoding) throws UnsupportedEncodingException {
/* 39 */     encoding = encoding.trim();
/*    */     try {
/* 41 */       if (!Charset.isSupported(encoding)) {
/* 42 */         throw new UnsupportedEncodingException(encoding);
/*    */       }
/* 44 */     } catch (Exception e) {
/* 45 */       throw new UnsupportedEncodingException(encoding);
/*    */     } 
/* 47 */     this.d = encoding;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/ua/f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */