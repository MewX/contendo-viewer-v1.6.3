/*     */ package net.zamasoft.a.c;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.lang.ref.SoftReference;
/*     */ import net.zamasoft.a.b;
/*     */ import net.zamasoft.a.b.K;
/*     */ import net.zamasoft.a.b.o;
/*     */ import net.zamasoft.a.b.r;
/*     */ import net.zamasoft.a.c;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class e
/*     */   implements c
/*     */ {
/*     */   private final r a;
/*     */   private final o b;
/*     */   private SoftReference<b>[] c;
/*     */   
/*     */   public e(o glyf, r head, K maxp) {
/*  22 */     this.a = head;
/*  23 */     this.b = glyf;
/*  24 */     this.c = (SoftReference<b>[])new SoftReference[maxp.o()];
/*     */   }
/*     */   
/*     */   public synchronized b a(int ix) {
/*  28 */     if (ix >= this.c.length) {
/*  29 */       return null;
/*     */     }
/*  31 */     b glyph = (this.c[ix] == null) ? null : this.c[ix].get();
/*  32 */     if (glyph != null) {
/*  33 */       return glyph;
/*     */     }
/*     */     
/*  36 */     c gd = this.b.a(ix);
/*  37 */     if (gd == null) {
/*  38 */       return null;
/*     */     }
/*  40 */     short upm = this.a.l();
/*  41 */     GeneralPath path = new GeneralPath();
/*  42 */     int count = 0, endIndex = 0;
/*  43 */     float[] x = new float[5], y = new float[5];
/*  44 */     boolean[] onCurve = new boolean[5];
/*  45 */     int scount = 0;
/*  46 */     float[] sx = new float[2], sy = new float[2];
/*  47 */     boolean[] sonCurve = new boolean[2];
/*     */     
/*  49 */     for (int i = 0; i < gd.c(); i++) {
/*  50 */       if (count < 0) {
/*  51 */         count = 0;
/*     */       }
/*     */       
/*  54 */       x[count] = gd.c(i) * 1000.0F / upm;
/*  55 */       y[count] = -(gd.d(i) * 1000.0F / upm);
/*  56 */       onCurve[count] = ((gd.b(i) & 0x1) != 0);
/*  57 */       if (scount <= 1) {
/*  58 */         sx[scount] = x[count];
/*  59 */         sy[scount] = y[count];
/*  60 */         sonCurve[scount] = onCurve[count];
/*  61 */         scount++;
/*     */       } 
/*  63 */       count++;
/*     */ 
/*     */       
/*  66 */       boolean end = (gd.a(endIndex) == i);
/*  67 */       if (end) {
/*  68 */         for (int j = 0; j < scount; j++) {
/*  69 */           x[count + j] = sx[j];
/*  70 */           y[count + j] = sy[j];
/*  71 */           onCurve[count + j] = sonCurve[j];
/*     */         } 
/*  73 */         scount = 0;
/*     */       } 
/*     */       
/*     */       do {
/*  77 */         if (!end && count < 2) {
/*     */           
/*  79 */           path.moveTo(x[0], y[0]);
/*     */           
/*     */           break;
/*     */         } 
/*     */         
/*  84 */         boolean plot2 = false;
/*  85 */         if (onCurve[0] && onCurve[1]) {
/*  86 */           path.lineTo(x[1], y[1]);
/*  87 */           plot2 = true;
/*  88 */         } else if (!onCurve[0] && !onCurve[1]) {
/*  89 */           path.quadTo(x[0], y[0], a(x[0], x[1]), a(y[0], y[1]));
/*  90 */           plot2 = true;
/*  91 */         } else if (!onCurve[0] && onCurve[1]) {
/*  92 */           path.quadTo(x[0], y[0], x[1], y[1]);
/*  93 */           plot2 = true;
/*     */         } 
/*  95 */         if (plot2)
/*  96 */         { count--;
/*  97 */           System.arraycopy(x, 1, x, 0, x.length - 1);
/*  98 */           System.arraycopy(y, 1, y, 0, y.length - 1);
/*  99 */           System.arraycopy(onCurve, 1, onCurve, 0, onCurve.length - 1); }
/*     */         
/*     */         else
/*     */         
/* 103 */         { if (!end && count < 3) {
/*     */             break;
/*     */           }
/*     */ 
/*     */           
/* 108 */           if (onCurve[0] && !onCurve[1] && onCurve[2]) {
/* 109 */             path.quadTo(x[1], y[1], x[2], y[2]);
/* 110 */           } else if (onCurve[0] && !onCurve[1] && !onCurve[2]) {
/* 111 */             path.quadTo(x[1], y[1], a(x[1], x[2]), a(y[1], y[2]));
/*     */           } else {
/* 113 */             throw new IllegalStateException();
/*     */           } 
/* 115 */           count -= 2;
/* 116 */           System.arraycopy(x, 2, x, 0, x.length - 2);
/* 117 */           System.arraycopy(y, 2, y, 0, y.length - 2);
/* 118 */           System.arraycopy(onCurve, 2, onCurve, 0, onCurve.length - 2); } 
/* 119 */       } while (end && count > 0);
/* 120 */       if (end) {
/* 121 */         endIndex++;
/* 122 */         path.closePath();
/*     */       } 
/*     */     } 
/*     */     
/* 126 */     glyph = new b(path, null);
/* 127 */     this.c[ix] = new SoftReference<>(glyph);
/* 128 */     return glyph;
/*     */   }
/*     */   
/*     */   private static float a(float a, float b) {
/* 132 */     return a + (b - a) / 2.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/zamasoft/a/c/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */