/*     */ package jp.cssj.sakae.c.a.a;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import jp.cssj.sakae.a.b;
/*     */ import jp.cssj.sakae.a.d;
/*     */ import jp.cssj.sakae.a.k;
/*     */ import jp.cssj.sakae.a.l;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.b;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.h;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class a
/*     */ {
/*     */   public static String a(String fontName) {
/*  36 */     StringBuffer buff = new StringBuffer();
/*  37 */     for (int i = 0; i < fontName.length(); i++) {
/*  38 */       char ch = fontName.charAt(i);
/*  39 */       if (!Character.isWhitespace(ch) && ch != '-' && ch != '_')
/*     */       {
/*     */         
/*  42 */         buff.append(Character.toUpperCase(ch)); } 
/*     */     } 
/*  44 */     return buff.toString();
/*     */   }
/*     */   
/*     */   public static boolean a(h h1, h b) {
/*  48 */     return (h1.d().equals(b.d()) && h1.e() == b.e() && h1.c() == b.c() && h1
/*  49 */       .b() == b.b() && h1.a() == b.a() && h1
/*  50 */       .f().equals(b.f()));
/*     */   }
/*     */   
/*     */   public static int a(h fontStyle) {
/*  54 */     int i = fontStyle.d().hashCode();
/*  55 */     long l = Double.doubleToLongBits(fontStyle.e());
/*  56 */     i = 31 * i + (int)(l ^ l >>> 32L);
/*  57 */     i = 31 * i + fontStyle.c();
/*  58 */     i = 31 * i + fontStyle.b();
/*  59 */     i = 31 * i + fontStyle.a();
/*  60 */     i = 31 * i + fontStyle.f().hashCode();
/*  61 */     return i;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(b gc, d font, h text) throws c {
/*     */     double enlargement;
/*  72 */     gc.d();
/*  73 */     h fontStyle = text.b();
/*  74 */     byte direction = fontStyle.a();
/*  75 */     double fontSize = fontStyle.e();
/*  76 */     int glen = text.l();
/*  77 */     int[] gids = text.j();
/*  78 */     double letterSpacing = text.m();
/*  79 */     double[] xadvances = text.a(false);
/*  80 */     f fm = text.d();
/*     */ 
/*     */     
/*  83 */     double s = fontSize / 1000.0D;
/*  84 */     AffineTransform at = AffineTransform.getScaleInstance(s, s);
/*     */ 
/*     */     
/*  87 */     short textMode = gc.n();
/*     */     
/*  89 */     short weight = fontStyle.b();
/*  90 */     double xlineWidth = 0.0D;
/*  91 */     Object xstrokePaint = null;
/*  92 */     if (textMode == 0 && weight >= 500 && font.a().c() < 500) {
/*     */       
/*  94 */       switch (weight) {
/*     */         case 500:
/*  96 */           enlargement = fontSize / 28.0D;
/*     */           break;
/*     */         case 600:
/*  99 */           enlargement = fontSize / 24.0D;
/*     */           break;
/*     */         case 700:
/* 102 */           enlargement = fontSize / 20.0D;
/*     */           break;
/*     */         case 800:
/* 105 */           enlargement = fontSize / 16.0D;
/*     */           break;
/*     */         case 900:
/* 108 */           enlargement = fontSize / 12.0D;
/*     */           break;
/*     */         default:
/* 111 */           throw new IllegalStateException();
/*     */       } 
/* 113 */       if (enlargement > 0.0D) {
/* 114 */         textMode = 2;
/* 115 */         xlineWidth = gc.f();
/* 116 */         gc.a(enlargement);
/* 117 */         gc.a(gc.k());
/* 118 */         xstrokePaint = gc.j();
/*     */       } 
/*     */     } else {
/* 121 */       enlargement = 0.0D;
/*     */     } 
/*     */     
/* 124 */     boolean verticalFont = (direction == 3 && font.a().e() == direction);
/* 125 */     AffineTransform oblique = null;
/* 126 */     short style = (short)fontStyle.c();
/* 127 */     if (style != 1 && !font.a().b())
/*     */     {
/* 129 */       if (verticalFont) {
/* 130 */         oblique = AffineTransform.getShearInstance(0.0D, 0.25D);
/*     */       } else {
/* 132 */         oblique = AffineTransform.getShearInstance(-0.25D, 0.0D);
/*     */       } 
/*     */     }
/*     */     
/* 136 */     GeneralPath path = null;
/* 137 */     if (verticalFont) {
/*     */ 
/*     */       
/* 140 */       gc.a(AffineTransform.getTranslateInstance(-fontSize / 2.0D, fontSize * 0.88D));
/* 141 */       int pgid = 0;
/* 142 */       for (int i = 0; i < glen; i++) {
/* 143 */         AffineTransform at2 = at;
/* 144 */         int gid = gids[i];
/* 145 */         if (i > 0) {
/* 146 */           double dy = fm.a(pgid) + letterSpacing;
/* 147 */           dy -= fm.a(pgid, gid);
/* 148 */           if (xadvances != null) {
/* 149 */             dy += xadvances[i];
/*     */           }
/* 151 */           at.preConcatenate(AffineTransform.getTranslateInstance(0.0D, dy));
/*     */         } 
/* 153 */         pgid = gid;
/* 154 */         if (font instanceof l) {
/* 155 */           Shape shape = ((l)font).d(gid);
/* 156 */           if (shape != null) {
/* 157 */             double width = (fontSize - fm.b(gid)) / 2.0D;
/* 158 */             if (width != 0.0D) {
/* 159 */               at2 = AffineTransform.getTranslateInstance(width, 0.0D);
/* 160 */               at2.concatenate(at);
/*     */             } 
/* 162 */             if (oblique != null) {
/* 163 */               shape = oblique.createTransformedShape(shape);
/*     */             }
/* 165 */             if (path == null) {
/* 166 */               path = new GeneralPath();
/*     */             }
/* 168 */             path.append(shape.getPathIterator(at2), false);
/*     */           } 
/*     */         } else {
/*     */           
/* 172 */           ((k)font).a(gc, gid, at2);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 176 */       if (direction == 3) {
/*     */         
/* 178 */         gc.a(AffineTransform.getRotateInstance(1.5707963267948966D));
/* 179 */         b bbox = font.a().f();
/* 180 */         double dy = (bbox.b + bbox.d) * fontSize / 1000.0D / 2.0D;
/* 181 */         gc.a(AffineTransform.getTranslateInstance(0.0D, dy));
/*     */       } 
/*     */       
/* 184 */       int pgid = 0;
/* 185 */       for (int i = 0; i < glen; i++) {
/* 186 */         int gid = gids[i];
/* 187 */         if (i > 0) {
/* 188 */           double dx = fm.a(pgid) + letterSpacing;
/* 189 */           if (i > 0) {
/* 190 */             dx -= fm.a(pgid, gid);
/*     */           }
/* 192 */           if (xadvances != null) {
/* 193 */             dx += xadvances[i];
/*     */           }
/* 195 */           at.preConcatenate(AffineTransform.getTranslateInstance(dx, 0.0D));
/*     */         } 
/* 197 */         if (font instanceof l) {
/* 198 */           Shape shape = ((l)font).d(gid);
/* 199 */           if (shape != null) {
/* 200 */             if (oblique != null) {
/* 201 */               shape = oblique.createTransformedShape(shape);
/*     */             }
/* 203 */             if (path == null) {
/* 204 */               path = new GeneralPath();
/*     */             }
/* 206 */             path.append(shape.getPathIterator(at), false);
/*     */           } 
/*     */         } else {
/*     */           
/* 210 */           ((k)font).a(gc, gid, at);
/*     */         } 
/* 212 */         pgid = gid;
/*     */       } 
/*     */     } 
/* 215 */     if (path != null) {
/* 216 */       a(gc, path, textMode);
/*     */     }
/*     */     
/* 219 */     if (enlargement > 0.0D) {
/* 220 */       gc.a(xlineWidth);
/* 221 */       gc.a(xstrokePaint);
/*     */     } 
/* 223 */     gc.e();
/*     */   }
/*     */   
/*     */   private static void a(b gc, GeneralPath path, short textMode) {
/* 227 */     switch (textMode) {
/*     */       case 0:
/* 229 */         gc.b(path);
/*     */         return;
/*     */       case 1:
/* 232 */         gc.c(path);
/*     */         return;
/*     */       case 2:
/* 235 */         gc.d(path);
/*     */         return;
/*     */     } 
/* 238 */     throw new IllegalStateException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/a/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */