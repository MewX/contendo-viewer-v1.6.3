/*    */ package jp.cssj.b.d;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.io.OutputStream;
/*    */ import jp.cssj.e.a;
/*    */ import jp.cssj.f.a;
/*    */ import jp.cssj.f.b.b;
/*    */ import jp.cssj.f.b.e;
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
/*    */ 
/*    */ public class d
/*    */   implements c
/*    */ {
/*    */   protected a a;
/*    */   
/*    */   public d(a builder) {
/* 30 */     this.a = builder;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public d(OutputStream out) {
/* 39 */     this((a)new e(out));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public d(File file) {
/* 48 */     this((a)new b(file));
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 52 */     return (this.a != null);
/*    */   }
/*    */   
/*    */   public a a(a metaSource) {
/* 56 */     if (this.a == null) {
/* 57 */       return (a)jp.cssj.f.b.d.a;
/*    */     }
/*    */     try {
/* 60 */       return this.a;
/*    */     } finally {
/* 62 */       this.a = null;
/*    */     } 
/*    */   }
/*    */   
/*    */   public void c() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/d/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */