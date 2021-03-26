/*     */ package jp.cssj.sakae.c;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.b.a;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */   extends d
/*     */ {
/*  18 */   protected final List<Object> m = new ArrayList();
/*     */   
/*     */   public e(e fm) {
/*  21 */     super(fm);
/*     */   }
/*     */   
/*     */   public void d() {
/*  25 */     super.d();
/*  26 */     this.m.add(a.a);
/*     */   }
/*     */   
/*     */   public void e() {
/*  30 */     super.e();
/*  31 */     this.m.add(a.b);
/*     */   }
/*     */   
/*     */   public void a(double lineWidth) {
/*  35 */     super.a(lineWidth);
/*  36 */     this.m.add(a.c);
/*  37 */     this.m.add(Double.valueOf(lineWidth));
/*     */   }
/*     */   
/*     */   public void a(double[] linePattern) {
/*  41 */     super.a(linePattern);
/*  42 */     this.m.add(a.d);
/*  43 */     this.m.add(linePattern);
/*     */   }
/*     */   
/*     */   public void a(short lineJoin) {
/*  47 */     super.a(lineJoin);
/*  48 */     this.m.add(a.f);
/*  49 */     this.m.add(Short.valueOf(lineJoin));
/*     */   }
/*     */   
/*     */   public void b(short lineCap) {
/*  53 */     super.b(lineCap);
/*  54 */     this.m.add(a.e);
/*  55 */     this.m.add(Short.valueOf(lineCap));
/*     */   }
/*     */   
/*     */   public void a(Object paint) throws c {
/*  59 */     super.a(paint);
/*  60 */     this.m.add(a.h);
/*  61 */     this.m.add(paint);
/*     */   }
/*     */   
/*     */   public void b(Object paint) throws c {
/*  65 */     super.b(paint);
/*  66 */     this.m.add(a.i);
/*  67 */     this.m.add(paint);
/*     */   }
/*     */   
/*     */   public void a(float alpha) {
/*  71 */     super.a(alpha);
/*  72 */     this.m.add(a.j);
/*  73 */     this.m.add(Float.valueOf(alpha));
/*     */   }
/*     */   
/*     */   public void b(float alpha) {
/*  77 */     super.b(alpha);
/*  78 */     this.m.add(a.k);
/*  79 */     this.m.add(Float.valueOf(alpha));
/*     */   }
/*     */   
/*     */   public void c(short textMode) {
/*  83 */     super.c(textMode);
/*  84 */     this.m.add(a.g);
/*  85 */     this.m.add(Short.valueOf(textMode));
/*     */   }
/*     */   
/*     */   public void a(AffineTransform at) {
/*  89 */     super.a(at);
/*  90 */     this.m.add(a.l);
/*  91 */     this.m.add(at);
/*     */   }
/*     */   
/*     */   public void a(Shape clip) {
/*  95 */     super.a(clip);
/*  96 */     this.m.add(a.m);
/*  97 */     this.m.add(clip);
/*     */   }
/*     */   
/*     */   public void p() {
/* 101 */     super.p();
/* 102 */     this.m.add(a.n);
/*     */   }
/*     */   
/*     */   public void a(b image) throws c {
/* 106 */     super.a(image);
/* 107 */     this.m.add(a.o);
/* 108 */     this.m.add(image);
/*     */   }
/*     */   
/*     */   public void b(Shape shape) {
/* 112 */     super.b(shape);
/* 113 */     this.m.add(a.p);
/* 114 */     this.m.add(shape);
/*     */   }
/*     */   
/*     */   public void c(Shape shape) {
/* 118 */     super.c(shape);
/* 119 */     this.m.add(a.q);
/* 120 */     this.m.add(shape);
/*     */   }
/*     */   
/*     */   public void d(Shape shape) {
/* 124 */     super.d(shape);
/* 125 */     this.m.add(a.r);
/* 126 */     this.m.add(shape);
/*     */   }
/*     */   
/*     */   public void a(h text, double x, double y) throws c {
/* 130 */     super.a(text, x, y);
/* 131 */     this.m.add(a.s);
/* 132 */     this.m.add(text);
/* 133 */     this.m.add(Double.valueOf(x));
/* 134 */     this.m.add(Double.valueOf(y));
/*     */   }
/*     */   
/*     */   public static class c extends d.c {
/*     */     protected final e.a c;
/*     */     
/*     */     public c(double width, double height, e.a page) {
/* 141 */       super(width, height);
/* 142 */       this.c = page;
/*     */     }
/*     */     
/*     */     public void a(b gc) throws c {
/* 146 */       this.c.a(gc);
/*     */     } }
/*     */   
/*     */   public static class b extends e implements a {
/*     */     private final double x;
/*     */     private final double y;
/*     */     
/*     */     public b(e fm, double width, double height) {
/* 154 */       super(fm);
/* 155 */       this.x = width;
/* 156 */       this.y = height;
/*     */     }
/*     */     
/*     */     public b q() throws c {
/* 160 */       e.a page = b();
/* 161 */       return new e.c(this.x, this.y, page);
/*     */     }
/*     */   }
/*     */   
/*     */   public a a(double width, double height) throws c {
/* 166 */     return new b(a(), width, height);
/*     */   }
/*     */   
/*     */   public a b() {
/* 170 */     return new a(this.m.toArray(new Object[this.m.size()]));
/*     */   }
/*     */   
/*     */   public static class a {
/*     */     protected Object[] a;
/*     */     
/*     */     protected a(Object[] data) {
/* 177 */       this.a = data;
/*     */     }
/*     */     
/*     */     public void a(b gc) {
/* 181 */       for (int i = 0; i < this.a.length; i++) {
/* 182 */         Double width; double[] pattern; Short style, textMode; Object object; float paint; AffineTransform at; Shape shape1; b image; Shape shape; h text; Double x, y; a e = (a)this.a[i];
/* 183 */         switch (e.null.a[e.ordinal()]) {
/*     */           case 1:
/* 185 */             gc.d();
/*     */             break;
/*     */           case 2:
/* 188 */             gc.e();
/*     */             break;
/*     */           case 3:
/* 191 */             width = (Double)this.a[++i];
/* 192 */             gc.a(width.doubleValue());
/*     */             break;
/*     */           
/*     */           case 4:
/* 196 */             pattern = (double[])this.a[++i];
/* 197 */             gc.a(pattern);
/*     */             break;
/*     */           
/*     */           case 5:
/* 201 */             style = (Short)this.a[++i];
/* 202 */             gc.b(style.shortValue());
/*     */             break;
/*     */           
/*     */           case 6:
/* 206 */             style = (Short)this.a[++i];
/* 207 */             gc.a(style.shortValue());
/*     */             break;
/*     */           
/*     */           case 7:
/* 211 */             textMode = (Short)this.a[++i];
/* 212 */             gc.a(textMode.shortValue());
/*     */             break;
/*     */           
/*     */           case 8:
/* 216 */             object = this.a[++i];
/* 217 */             gc.a(object);
/*     */             break;
/*     */           
/*     */           case 9:
/* 221 */             object = this.a[++i];
/* 222 */             gc.b(object);
/*     */             break;
/*     */           
/*     */           case 10:
/* 226 */             paint = ((Float)this.a[++i]).floatValue();
/* 227 */             gc.a(paint);
/*     */             break;
/*     */           
/*     */           case 11:
/* 231 */             paint = ((Float)this.a[++i]).floatValue();
/* 232 */             gc.b(paint);
/*     */             break;
/*     */           
/*     */           case 12:
/* 236 */             at = (AffineTransform)this.a[++i];
/* 237 */             gc.a(at);
/*     */             break;
/*     */           
/*     */           case 13:
/* 241 */             shape1 = (Shape)this.a[++i];
/* 242 */             gc.a(shape1);
/*     */             break;
/*     */           
/*     */           case 14:
/* 246 */             gc.p();
/*     */             break;
/*     */           case 15:
/* 249 */             image = (b)this.a[++i];
/* 250 */             gc.a(image);
/*     */             break;
/*     */           
/*     */           case 16:
/* 254 */             shape = (Shape)this.a[++i];
/* 255 */             gc.b(shape);
/*     */             break;
/*     */           
/*     */           case 17:
/* 259 */             shape = (Shape)this.a[++i];
/* 260 */             gc.c(shape);
/*     */             break;
/*     */           
/*     */           case 18:
/* 264 */             shape = (Shape)this.a[++i];
/* 265 */             gc.d(shape);
/*     */             break;
/*     */           
/*     */           case 19:
/* 269 */             text = (h)this.a[++i];
/* 270 */             x = (Double)this.a[++i];
/* 271 */             y = (Double)this.a[++i];
/* 272 */             gc.a(text, x.doubleValue(), y.doubleValue());
/*     */             break;
/*     */           
/*     */           default:
/* 276 */             throw new IllegalStateException(String.valueOf(e));
/*     */         } 
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */