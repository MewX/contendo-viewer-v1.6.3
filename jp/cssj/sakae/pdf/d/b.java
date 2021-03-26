/*    */ package jp.cssj.sakae.pdf.d;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import java.io.OutputStream;
/*    */ import jp.cssj.sakae.c.b.b;
/*    */ import jp.cssj.sakae.c.c;
/*    */ import jp.cssj.sakae.pdf.f;
/*    */ import jp.cssj.sakae.pdf.j;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class b
/*    */   extends f
/*    */   implements b
/*    */ {
/*    */   private final String l;
/*    */   private final jp.cssj.sakae.pdf.b m;
/*    */   public static final int i = 1;
/*    */   public static final int j = 2;
/* 27 */   protected int k = 0;
/*    */ 
/*    */   
/*    */   protected b(j pdfWriter, OutputStream out, double width, double height, String name, jp.cssj.sakae.pdf.b objectRef) throws IOException {
/* 31 */     super(pdfWriter, out, width, height);
/* 32 */     this.l = name;
/* 33 */     this.m = objectRef;
/*    */   }
/*    */   
/*    */   public void c(int ocgFlags) {
/* 37 */     this.k = ocgFlags;
/*    */   }
/*    */   
/*    */   public void a(jp.cssj.sakae.c.b _gc) throws c {
/* 41 */     a gc = (a)_gc;
/* 42 */     gc.a(this.l, this.a, this.b);
/*    */   }
/*    */   
/*    */   public String e() {
/* 46 */     return this.l;
/*    */   }
/*    */   
/*    */   public jp.cssj.sakae.pdf.b n() {
/* 50 */     return this.m;
/*    */   }
/*    */   
/*    */   public String c() {
/* 54 */     return null;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 58 */     return this.l;
/*    */   }
/*    */   
/*    */   public boolean equals(Object o) {
/* 62 */     if (o instanceof b) {
/* 63 */       return ((b)o).l.equals(this.l);
/*    */     }
/* 65 */     return false;
/*    */   }
/*    */   
/*    */   public int hashCode() {
/* 69 */     return this.l.hashCode();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/d/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */