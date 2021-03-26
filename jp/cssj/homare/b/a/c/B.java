/*    */ package jp.cssj.homare.b.a.c;
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
/*    */ public class B
/*    */ {
/* 14 */   public static final B a = new B(t.d, A.a, g.a, t.d);
/*    */   
/*    */   public final t b;
/*    */   
/*    */   public final A c;
/*    */   
/*    */   public final g d;
/*    */   
/*    */   public final t e;
/*    */   
/*    */   public static B a(t margin, A border, g background, t padding) {
/* 25 */     margin = (margin == null) ? t.d : margin;
/* 26 */     border = (border == null) ? A.a : border;
/* 27 */     background = (background == null) ? g.a : background;
/* 28 */     padding = (padding == null) ? t.d : padding;
/* 29 */     if (margin.e() && border.l() && ((!background.c() ? 1 : 0) & padding.e()) != 0) {
/* 30 */       return a;
/*    */     }
/* 32 */     return new B(margin, border, background, padding);
/*    */   }
/*    */   
/*    */   private B(t margin, A border, g background, t padding) {
/* 36 */     this.b = margin;
/* 37 */     this.c = border;
/* 38 */     this.d = background;
/* 39 */     this.e = padding;
/*    */   }
/*    */   
/*    */   public boolean a() {
/* 43 */     return (this.d.c() || this.c.k());
/*    */   }
/*    */   
/*    */   public boolean b() {
/* 47 */     return (this.b.e() && this.c.l() && this.e.e() && !this.d.c());
/*    */   }
/*    */   
/*    */   public B a(boolean top, boolean right, boolean bottom, boolean left) {
/* 51 */     t newMargin = this.b.a(top, right, bottom, left);
/* 52 */     A newBorder = this.c.a(top, right, bottom, left);
/* 53 */     t newPadding = this.e.a(top, right, bottom, left);
/*    */     
/* 55 */     return a(newMargin, newBorder, this.d, newPadding);
/*    */   }
/*    */   
/*    */   public String toString() {
/* 59 */     return "[margin=" + this.b + ",border=" + this.c + ",background=" + this.d + ",padding=" + this.e + "]";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/c/B.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */