/*    */ package jp.cssj.b.d;
/*    */ 
/*    */ import java.io.File;
/*    */ import jp.cssj.f.b.b;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class a
/*    */   implements c
/*    */ {
/*    */   protected final File a;
/*    */   protected final String b;
/*    */   protected final String c;
/* 18 */   protected int d = 0;
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
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public a(File dir, String prefix, String suffix) {
/* 34 */     this.a = dir;
/* 35 */     this.b = prefix;
/* 36 */     this.c = suffix;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 40 */     return true;
/*    */   }
/*    */   
/*    */   public jp.cssj.f.a a(jp.cssj.e.a metaSource) {
/* 44 */     this.d++;
/* 45 */     File file = new File(this.a, this.b + this.d + this.c);
/* 46 */     return (jp.cssj.f.a)new b(file);
/*    */   }
/*    */   
/*    */   public void c() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/d/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */