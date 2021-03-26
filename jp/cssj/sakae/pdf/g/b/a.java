/*    */ package jp.cssj.sakae.pdf.g.b;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import java.security.Key;
/*    */ import javax.crypto.Cipher;
/*    */ import javax.crypto.spec.SecretKeySpec;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   implements e
/*    */ {
/*    */   private final Key a;
/*    */   
/*    */   public a(byte[] key, int len) {
/* 20 */     if (len < 0 || len > 32) {
/* 21 */       throw new IllegalArgumentException("The key length is limited to 1 to 32.");
/*    */     }
/* 23 */     this.a = new SecretKeySpec(key, 0, len, "AES");
/*    */   }
/*    */ 
/*    */   
/*    */   private Cipher b() {
/*    */     try {
/* 29 */       Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
/* 30 */       cipher.init(1, this.a);
/* 31 */       return cipher;
/* 32 */     } catch (Exception exception) {
/* 33 */       throw new RuntimeException(exception);
/*    */     } 
/*    */   }
/*    */   
/*    */   public final byte[] a(byte[] data, int off, int len) {
/*    */     try {
/* 39 */       Cipher cipher = b();
/* 40 */       byte[] iv = cipher.getIV();
/* 41 */       byte[] code = cipher.doFinal(data, off, len);
/* 42 */       byte[] result = new byte[iv.length + code.length];
/* 43 */       System.arraycopy(iv, 0, result, 0, iv.length);
/* 44 */       System.arraycopy(code, 0, result, iv.length, code.length);
/* 45 */       return result;
/* 46 */     } catch (Exception exception) {
/* 47 */       throw new RuntimeException(exception);
/*    */     } 
/*    */   }
/*    */   
/*    */   public OutputStream a(OutputStream out) throws IOException {
/* 52 */     Cipher cipher = b();
/* 53 */     byte[] iv = cipher.getIV();
/* 54 */     out.write(iv);
/* 55 */     return new c(out, cipher);
/*    */   }
/*    */   
/*    */   public byte[] a(byte[] data) {
/* 59 */     return a(data, 0, data.length);
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 63 */     return true;
/*    */   }
/*    */   
/*    */   public void b(byte[] data, int off, int len) {
/* 67 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/g/b/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */