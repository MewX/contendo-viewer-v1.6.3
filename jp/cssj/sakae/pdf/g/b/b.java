/*    */ package jp.cssj.sakae.pdf.g.b;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   implements e
/*    */ {
/* 14 */   private final int[] a = new int[256];
/*    */   
/*    */   class a
/*    */     extends FilterOutputStream {
/*    */     private final int[] b;
/* 19 */     private final int[] c = new int[] { 0, 0 };
/*    */     
/*    */     public a(b this$0, OutputStream out) {
/* 22 */       super(out);
/* 23 */       this.b = (int[])b.a(this$0).clone();
/*    */     }
/*    */     
/*    */     public void write(int x) throws IOException {
/* 27 */       this.out.write(b.a(this.a, this.b, this.c, (byte)x));
/*    */     }
/*    */     
/*    */     public void write(byte[] bytes) throws IOException {
/* 31 */       for (int i = 0; i < bytes.length; i++) {
/* 32 */         write(bytes[i]);
/*    */       }
/*    */     }
/*    */     
/*    */     public void write(byte[] bytes, int off, int len) throws IOException {
/* 37 */       for (int i = 0; i < len; i++) {
/* 38 */         write(bytes[i + off]);
/*    */       }
/*    */     }
/*    */   }
/*    */   
/*    */   public b(byte[] key, int len) {
/* 44 */     if (len < 0 || len > 32) {
/* 45 */       throw new IllegalArgumentException("The key length is limited to 1 to 32.");
/*    */     }
/* 47 */     for (int i = 0; i < this.a.length; i++) {
/* 48 */       this.a[i] = i;
/*    */     }
/*    */     
/* 51 */     int keyIndex = 0;
/* 52 */     int saltIndex = 0;
/* 53 */     for (int j = 0; j < this.a.length; j++) {
/* 54 */       byte x = key[keyIndex];
/* 55 */       saltIndex = (((x < 0) ? (256 + x) : x) + this.a[j] + saltIndex) % 256;
/* 56 */       a(this.a, j, saltIndex);
/* 57 */       keyIndex = (keyIndex + 1) % len;
/*    */     } 
/*    */   }
/*    */   
/*    */   public final void b(byte[] data, int off, int len) {
/* 62 */     int[] salt = (int[])this.a.clone();
/* 63 */     int[] bc = { 0, 0 };
/* 64 */     for (int i = 0; i < len; i++) {
/* 65 */       data[i + off] = a(salt, bc, data[i + off]);
/*    */     }
/*    */   }
/*    */   
/*    */   private final void a(int[] salt, int firstIndex, int secondIndex) {
/* 70 */     int tmp = salt[firstIndex];
/* 71 */     salt[firstIndex] = salt[secondIndex];
/* 72 */     salt[secondIndex] = tmp;
/*    */   }
/*    */   
/*    */   private byte a(int[] salt, int[] bc, byte x) {
/* 76 */     bc[0] = (bc[0] + 1) % 256;
/* 77 */     bc[1] = (salt[bc[0]] + bc[1]) % 256;
/* 78 */     a(salt, bc[0], bc[1]);
/* 79 */     int saltIndex = (salt[bc[0]] + salt[bc[1]]) % 256;
/* 80 */     return (byte)(x ^ (byte)salt[saltIndex]);
/*    */   }
/*    */   
/*    */   public OutputStream a(OutputStream out) {
/* 84 */     return new a(this, out);
/*    */   }
/*    */   
/*    */   public byte[] a(byte[] data) {
/* 88 */     b(data, 0, data.length);
/* 89 */     return data;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 93 */     return false;
/*    */   }
/*    */   
/*    */   public byte[] a(byte[] data, int off, int len) {
/* 97 */     throw new UnsupportedOperationException();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/g/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */