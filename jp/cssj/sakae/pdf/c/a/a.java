/*    */ package jp.cssj.sakae.pdf.c.a;
/*    */ 
/*    */ import jp.cssj.sakae.a.g;
/*    */ import jp.cssj.sakae.pdf.b;
/*    */ import jp.cssj.sakae.pdf.c.e;
/*    */ 
/*    */ 
/*    */ public abstract class a
/*    */   implements e
/*    */ {
/*    */   private static final long d = 0L;
/*    */   protected final g a;
/*    */   protected final String b;
/*    */   protected final b c;
/*    */   
/*    */   protected a(g metaFont, String name, b fontRef) {
/* 17 */     this.a = metaFont;
/* 18 */     this.b = name;
/* 19 */     this.c = fontRef;
/*    */   }
/*    */   
/*    */   public g a() {
/* 23 */     return this.a;
/*    */   }
/*    */   
/*    */   public String i() {
/* 27 */     return this.b;
/*    */   }
/*    */   
/*    */   public short a(int scid, int cid) {
/* 31 */     return 0;
/*    */   }
/*    */   
/*    */   public int b(int scid, int cid) {
/* 35 */     return -1;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 39 */     return super.toString() + ":[PDFName=" + i() + " source=" + a() + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */