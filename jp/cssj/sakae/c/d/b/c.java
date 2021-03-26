/*    */ package jp.cssj.sakae.c.d.b;
/*    */ 
/*    */ import jp.cssj.sakae.c.a.f;
/*    */ import jp.cssj.sakae.c.a.h;
/*    */ import jp.cssj.sakae.c.b;
/*    */ import jp.cssj.sakae.c.d.b.a.a;
/*    */ import jp.cssj.sakae.c.d.b.a.c;
/*    */ import jp.cssj.sakae.c.d.e;
/*    */ import jp.cssj.sakae.c.d.g;
/*    */ import jp.cssj.sakae.c.d.h;
/*    */ import jp.cssj.sakae.c.d.i;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class c
/*    */   implements e
/*    */ {
/* 21 */   private i d = null;
/*    */   
/* 23 */   private double e = 0.0D;
/*    */   
/* 25 */   private double f = 0.0D; private double g = 0.0D; private double h = 0.0D; private static final double b = 24.0D;
/*    */   
/*    */   public b c() {
/* 28 */     return this.c;
/*    */   }
/*    */   private b c;
/*    */   public void a(b gc) {
/* 32 */     this.c = gc;
/*    */   }
/*    */   
/*    */   public double d() {
/* 36 */     return this.f;
/*    */   }
/*    */   
/*    */   public double e() {
/* 40 */     return this.e;
/*    */   }
/*    */   
/*    */   public void a(double letterSpacing) {
/* 44 */     this.e = letterSpacing;
/*    */   }
/*    */   
/*    */   public void a(int charOffset, h fontStyle, f fontMetrics) {
/* 48 */     this.d = new i(charOffset, fontStyle, fontMetrics);
/* 49 */     this.d.a(this.e);
/*    */   }
/*    */   
/*    */   public void a(int charOffset, char[] ch, int coff, byte clen, int gid) {
/* 53 */     this.d.a(ch, coff, clen, gid);
/*    */   }
/*    */   
/*    */   public void a() {
/* 57 */     if (!a && this.d.l() <= 0) throw new AssertionError(); 
/* 58 */     if (this.c != null) {
/* 59 */       switch (this.d.b().a()) {
/*    */         
/*    */         case 1:
/*    */         case 2:
/* 63 */           this.c.a((h)this.d, this.f, this.g);
/*    */           break;
/*    */         
/*    */         case 3:
/* 67 */           this.c.a((h)this.d, -this.g, this.f);
/*    */           break;
/*    */         default:
/* 70 */           throw new IllegalArgumentException();
/*    */       } 
/*    */     }
/* 73 */     this.f += this.d.c();
/* 74 */     this.h = Math.max(this.h, this.d.a.e());
/*    */   }
/*    */   public void a(g quad) {
/*    */     c tab;
/* 78 */     a control = (a)quad;
/* 79 */     this.h = Math.max(this.h, control.f() + control.g());
/* 80 */     switch (control.e()) {
/*    */       case '\n':
/* 82 */         this.g += this.h;
/* 83 */         this.h = 0.0D;
/* 84 */         this.f = 0.0D;
/*    */         return;
/*    */ 
/*    */       
/*    */       case '\t':
/* 89 */         tab = (c)control;
/* 90 */         tab.a = 24.0D - this.f % 24.0D;
/*    */         break;
/*    */     } 
/* 93 */     this.f += quad.c();
/*    */   }
/*    */   
/*    */   public void b() {}
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/b/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */