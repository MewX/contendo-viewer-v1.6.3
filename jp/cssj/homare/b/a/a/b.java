/*     */ package jp.cssj.homare.b.a.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.b.n;
/*     */ import jp.cssj.homare.b.a.i;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */ {
/*     */   public static class a
/*     */   {
/*     */     public final i a;
/*     */     public final double b;
/*     */     public final double c;
/*     */     
/*     */     public a(i box, double x, double y) {
/*  35 */       this.a = box;
/*  36 */       this.b = x;
/*  37 */       this.c = y;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  44 */   private List<a> b = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(i box, double staticX, double staticY) {
/*  58 */     if (!a && e.a(staticX)) throw new AssertionError("Undefined x"); 
/*  59 */     if (!a && e.a(staticY)) throw new AssertionError("Undefined y"); 
/*  60 */     jp.cssj.homare.b.a.c.a pos = box.c();
/*  61 */     if (pos.a.d() != 3 || pos.a.b() != 3) {
/*  62 */       staticX = 1.722773839210782E308D;
/*     */     }
/*  64 */     if (pos.a.a() != 3 || pos.a.c() != 3) {
/*  65 */       staticY = 1.722773839210782E308D;
/*     */     }
/*  67 */     a absolute = new a(box, staticX, staticY);
/*  68 */     if (this.b == null) {
/*  69 */       this.b = new ArrayList<>();
/*     */     }
/*  71 */     this.b.add(absolute);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(n pageBox, g drawer, jp.cssj.homare.b.g.a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/*  76 */     if (!a && e.a(x)) throw new AssertionError("Undefined x"); 
/*  77 */     if (!a && e.a(y)) throw new AssertionError("Undefined y"); 
/*  78 */     if (this.b == null) {
/*     */       return;
/*     */     }
/*  81 */     for (int i = 0; i < this.b.size(); i++) {
/*  82 */       a c = this.b.get(i);
/*  83 */       double xx = e.a(c.b) ? contextX : (x + c.b);
/*  84 */       double yy = e.a(c.c) ? contextY : (y + c.c);
/*  85 */       if ((c.a.c()).c != 1) {
/*     */         
/*  87 */         pageBox.a(drawer, visitor, c.a, xx, yy);
/*  88 */         this.b.remove(i);
/*  89 */         i--;
/*     */       } else {
/*  91 */         c.a.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, xx, yy);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public int a() {
/*  97 */     if (this.b == null) {
/*  98 */       return 0;
/*     */     }
/* 100 */     return this.b.size();
/*     */   }
/*     */   
/*     */   public a a(int i) {
/* 104 */     return this.b.get(i);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */