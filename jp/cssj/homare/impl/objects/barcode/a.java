/*     */ package jp.cssj.homare.impl.objects.barcode;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.List;
/*     */ import jp.cssj.homare.b.a.a.l;
/*     */ import jp.cssj.homare.b.a.f;
/*     */ import jp.cssj.homare.b.f.e;
/*     */ import jp.cssj.homare.css.e.f;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.c.a.e;
/*     */ import jp.cssj.sakae.c.a.g;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.a.i;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.c.c.b;
/*     */ import jp.cssj.sakae.c.d.c;
/*     */ import jp.cssj.sakae.c.d.f;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.c.d.i;
/*     */ import org.krysalis.barcode4j.BarcodeDimension;
/*     */ import org.krysalis.barcode4j.BarcodeGenerator;
/*     */ import org.krysalis.barcode4j.TextAlignment;
/*     */ import org.krysalis.barcode4j.output.AbstractCanvasProvider;
/*     */ import org.krysalis.barcode4j.output.CanvasProvider;
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
/*     */ public class a
/*     */   implements l, b
/*     */ {
/*     */   protected final m a;
/*     */   protected final BarcodeGenerator b;
/*     */   protected final String c;
/*     */   protected final double d;
/*     */   protected final double e;
/*     */   protected final double f;
/*     */   protected h g;
/*     */   protected b h;
/*     */   
/*     */   public a(m ua, BarcodeGenerator bg, String message) {
/*     */     double width, height;
/*  55 */     this.a = ua;
/*  56 */     this.b = bg;
/*  57 */     this.c = message;
/*  58 */     this.d = f.a(ua, 1.0D, (short)20, (short)21);
/*     */     
/*     */     try {
/*  61 */       BarcodeDimension dim = this.b.calcDimensions(message);
/*  62 */       width = dim.getWidthPlusQuiet() * this.d;
/*  63 */       height = dim.getHeightPlusQuiet() * this.d;
/*  64 */     } catch (Exception e) {
/*  65 */       width = 40.0D;
/*  66 */       height = 40.0D;
/*     */     } 
/*  68 */     this.e = width;
/*  69 */     this.f = height;
/*     */   }
/*     */   
/*     */   public void a(f box, double width, double height) {
/*  73 */     this.g = (box.d_()).C;
/*  74 */     this.h = (box.d_()).M;
/*     */   }
/*     */   
/*     */   public double a() {
/*  78 */     return this.e;
/*     */   }
/*     */   
/*     */   public double b() {
/*  82 */     return this.f;
/*     */   }
/*     */   
/*     */   public String c() {
/*  86 */     return this.c;
/*     */   }
/*     */   
/*     */   public void a(b gc) {
/*  90 */     gc.d();
/*  91 */     gc.a(AffineTransform.getScaleInstance(this.d, this.d));
/*  92 */     gc.b(this.h);
/*  93 */     gc.a(this.h);
/*  94 */     a a1 = new a(this, gc, 0);
/*     */     try {
/*  96 */       this.b.generateBarcode((CanvasProvider)a1, this.c);
/*  97 */     } catch (Exception e) {
/*  98 */       this.a.a((short)10495, "jp.cssj.homare.impl.objects.barcode", e.getLocalizedMessage());
/*  99 */       e.a(gc, (g)this.a.j(), 5.0D, e.getLocalizedMessage(), 3.0D, 3.0D, this.e - 6.0D);
/*     */     } 
/* 101 */     gc.e();
/*     */   }
/*     */   
/*     */   private class a extends AbstractCanvasProvider {
/*     */     private final b b;
/*     */     
/*     */     public a(a this$0, b gc, int orientation) {
/* 108 */       super(orientation);
/* 109 */       this.b = gc;
/*     */     }
/*     */ 
/*     */     
/*     */     public void a(String text, double x, double xx, double y, String fontName, double fontSize, TextAlignment textAlign) {
/* 114 */       e fm = this.a.a.q();
/*     */       
/* 116 */       i i1 = new i(this.a.g.d(), fontSize, this.a.g.c(), this.a.g.b(), (byte)1, (g)this.a.a.j());
/* 117 */       this.b.d();
/* 118 */       this.b.a(AffineTransform.getTranslateInstance(x, y));
/*     */       
/* 120 */       f glypher = fm.a();
/* 121 */       c gh = new c();
/* 122 */       glypher.a(gh);
/* 123 */       glypher.a((h)i1);
/* 124 */       char[] ch = text.toCharArray();
/* 125 */       glypher.a(-1, ch, 0, ch.length);
/* 126 */       glypher.a();
/*     */       
/* 128 */       double width = xx - x;
/* 129 */       double d1 = 0.0D, xs = 0.0D;
/* 130 */       List<c> list = gh.a;
/* 131 */       if (textAlign == TextAlignment.TA_RIGHT) {
/* 132 */         d1 = width - gh.b;
/* 133 */       } else if (textAlign == TextAlignment.TA_CENTER) {
/* 134 */         d1 = (width - gh.b) / 2.0D;
/* 135 */       } else if (textAlign == TextAlignment.TA_JUSTIFY) {
/* 136 */         int count = -1;
/* 137 */         for (int j = 0; j < list.size(); j++) {
/* 138 */           h t; c e = list.get(j);
/* 139 */           switch (e.f_()) {
/*     */             case 1:
/* 141 */               t = (h)e;
/* 142 */               count += t.l();
/*     */               break;
/*     */             case 2:
/*     */               break;
/*     */             default:
/* 147 */               throw new IllegalStateException();
/*     */           } 
/*     */         } 
/* 150 */         if (count >= 2) {
/* 151 */           xs = (width - gh.b) / count;
/*     */         }
/*     */       } 
/* 154 */       for (int i = 0; i < list.size(); i++) {
/* 155 */         i t; c e = list.get(i);
/* 156 */         switch (e.f_()) {
/*     */           case 1:
/* 158 */             t = (i)e;
/* 159 */             t.a(xs);
/* 160 */             this.b.a((h)t, d1, 0.0D);
/*     */             break;
/*     */           case 2:
/*     */             break;
/*     */           default:
/* 165 */             throw new IllegalStateException();
/*     */         } 
/* 167 */         d1 += e.c();
/*     */       } 
/*     */       
/* 170 */       this.b.e();
/*     */     }
/*     */     
/*     */     public void a(double x, double y, double w, double h) {
/* 174 */       Rectangle2D rect = new Rectangle2D.Double(x, y, w, h);
/* 175 */       this.b.b(rect);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/objects/barcode/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */