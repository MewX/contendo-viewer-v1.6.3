/*    */ package jp.cssj.sakae.a;
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
/*    */ public abstract class a
/*    */   implements g
/*    */ {
/*    */   private static final long i = 1L;
/*    */   protected static final short a = 860;
/*    */   protected static final short b = 140;
/*    */   protected static final short c = 500;
/*    */   protected static final short d = 700;
/* 25 */   private static final String[] j = new String[0];
/*    */   
/* 27 */   protected String[] X_ = j;
/*    */   
/* 29 */   protected short f = 400;
/*    */   
/*    */   protected boolean g = false;
/*    */   
/*    */   public String[] a() {
/* 34 */     return this.X_;
/*    */   }
/*    */   
/*    */   public final void a(boolean isItalic) {
/* 38 */     this.g = isItalic;
/*    */   }
/*    */   
/*    */   public final boolean b() {
/* 42 */     return this.g;
/*    */   }
/*    */   
/*    */   public final void a(short weight) {
/* 46 */     this.f = weight;
/*    */   }
/*    */   
/*    */   public final short c() {
/* 50 */     return this.f;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 54 */     StringBuffer buff = new StringBuffer(d());
/* 55 */     String[] aliases = a();
/* 56 */     for (int i = 0; i < aliases.length; i++) {
/* 57 */       buff.append("; ").append(aliases[i]);
/*    */     }
/* 59 */     buff.append(getClass());
/* 60 */     return buff.toString();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */