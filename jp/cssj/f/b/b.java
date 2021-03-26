/*    */ package jp.cssj.f.b;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.FileOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import jp.cssj.f.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends a
/*    */   implements b
/*    */ {
/*    */   protected final File j;
/* 19 */   protected OutputStream k = null;
/*    */   
/*    */   public b(File file, int fragmentBufferSize, int totalBufferSize, int threshold) {
/* 22 */     super(fragmentBufferSize, totalBufferSize, threshold);
/* 23 */     this.j = file;
/*    */   }
/*    */ 
/*    */   
/*    */   public b(File file) {
/* 28 */     this.j = file;
/*    */   }
/*    */   
/*    */   public void a(byte[] arrayOfByte, int off, int len) throws IOException {
/* 32 */     if (this.k == null) {
/* 33 */       this.k = new FileOutputStream(this.j);
/*    */     }
/* 35 */     this.k.write(arrayOfByte, off, len);
/*    */   }
/*    */   
/*    */   public void a() throws IOException {
/* 39 */     if (this.k != null) {
/* 40 */       this.k.close();
/* 41 */       this.k = null;
/*    */       return;
/*    */     } 
/* 44 */     try (OutputStream out = new FileOutputStream(this.j)) {
/* 45 */       a(out);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/f/b/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */