/*    */ package jp.cssj.sakae.pdf.g.a;
/*    */ 
/*    */ import java.io.FilterOutputStream;
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import jp.cssj.sakae.pdf.h;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends FilterOutputStream
/*    */ {
/* 17 */   private int a = 0;
/*    */   
/*    */   public b(OutputStream out) {
/* 20 */     super(out);
/*    */   }
/*    */   
/* 23 */   private static final char[] b = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
/*    */ 
/*    */   
/*    */   public void write(int i) throws IOException {
/* 27 */     this.out.write(b.b[i >> 4 & 0xF]);
/* 28 */     this.out.write(b.b[i & 0xF]);
/* 29 */     if (++this.a > 40) {
/* 30 */       this.out.write(h.d);
/* 31 */       this.a = 0;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/g/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */