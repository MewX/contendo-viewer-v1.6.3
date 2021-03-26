/*    */ package jp.cssj.sakae.pdf.c.a.a;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.IOException;
/*    */ import jp.cssj.sakae.a.b.c;
/*    */ import jp.cssj.sakae.a.e;
/*    */ import jp.cssj.sakae.pdf.c.a.b;
/*    */ import jp.cssj.sakae.pdf.c.e;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class b
/*    */   extends c
/*    */   implements b
/*    */ {
/*    */   private static final long y = 1L;
/*    */   
/*    */   public b(File otfFont, int index, byte direction) throws IOException {
/* 21 */     super(otfFont, index, direction);
/*    */   }
/*    */   
/*    */   public e a(String name, jp.cssj.sakae.pdf.b fontRef) {
/* 25 */     return (e)new a(this, name, fontRef);
/*    */   }
/*    */   
/*    */   public e n() {
/* 29 */     return (e)a(null, null);
/*    */   }
/*    */   
/*    */   public byte h_() {
/* 33 */     return 2;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */