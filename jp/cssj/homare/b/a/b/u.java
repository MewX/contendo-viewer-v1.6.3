/*     */ package jp.cssj.homare.b.a.b;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.c.F;
/*     */ import jp.cssj.homare.b.a.c.s;
/*     */ import jp.cssj.homare.b.c.g;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.b.g.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class u
/*     */   extends t
/*     */ {
/*  22 */   private List<t> f = null;
/*     */   
/*     */   public u(s params, F pos) {
/*  25 */     super(params, pos);
/*     */   }
/*     */   
/*     */   public final byte a() {
/*  29 */     return 8;
/*     */   }
/*     */   
/*     */   public final void a(t column) {
/*  33 */     if (this.f == null) {
/*  34 */       this.f = new ArrayList<>();
/*     */     }
/*  36 */     this.f.add(column);
/*     */   }
/*     */   
/*     */   public final t a(int i) {
/*  40 */     return this.f.get(i);
/*     */   }
/*     */   
/*     */   public final int h() {
/*  44 */     return (this.f == null) ? 0 : this.f.size();
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, Shape clip, AffineTransform transform, double x, double y) {
/*  49 */     if (this.f == null) {
/*     */       return;
/*     */     }
/*  52 */     if (e.a(this.b.D)) {
/*  53 */       for (int i = 0; i < this.f.size(); i++) {
/*  54 */         t column = this.f.get(i);
/*  55 */         column.a(pageBox, drawer, clip, transform, x, y);
/*  56 */         y += column.e();
/*     */       } 
/*     */     } else {
/*  59 */       for (int i = 0; i < this.f.size(); i++) {
/*  60 */         t column = this.f.get(i);
/*  61 */         column.a(pageBox, drawer, clip, transform, x, y);
/*  62 */         x += column.e();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final void a(n pageBox, g drawer, a visitor, Shape clip, AffineTransform transform, double contextX, double contextY, double x, double y) {
/*  69 */     super.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*  70 */     if (this.f == null) {
/*     */       return;
/*     */     }
/*  73 */     if (e.a(this.b.D)) {
/*  74 */       for (int i = 0; i < this.f.size(); i++) {
/*  75 */         t column = this.f.get(i);
/*  76 */         column.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*  77 */         y += column.e();
/*     */       } 
/*     */     } else {
/*  80 */       for (int i = 0; i < this.f.size(); i++) {
/*  81 */         t column = this.f.get(i);
/*  82 */         column.a(pageBox, drawer, visitor, clip, transform, contextX, contextY, x, y);
/*  83 */         x += column.e();
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public final t a(double prevPageSize, double nextPageSize) {
/*  89 */     this.d = prevPageSize;
/*  90 */     u columnGroup = new u(this.a, this.e);
/*  91 */     columnGroup.a(this.b);
/*  92 */     columnGroup.c = this.c;
/*  93 */     columnGroup.d = nextPageSize;
/*  94 */     if (this.f != null) {
/*  95 */       for (int i = 0; i < this.f.size(); i++) {
/*  96 */         t column = this.f.get(i);
/*  97 */         columnGroup.a(column.a(prevPageSize, nextPageSize));
/*     */       } 
/*     */     }
/* 100 */     return columnGroup;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/b/a/b/u.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */