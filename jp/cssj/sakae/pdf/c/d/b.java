/*     */ package jp.cssj.sakae.pdf.c.d;
/*     */ 
/*     */ import java.awt.Font;
/*     */ import java.awt.Shape;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.font.TextAttribute;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.io.IOException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import jp.cssj.sakae.a.g;
/*     */ import jp.cssj.sakae.b.c.a;
/*     */ import jp.cssj.sakae.c.a.f;
/*     */ import jp.cssj.sakae.c.c;
/*     */ import jp.cssj.sakae.c.d.h;
/*     */ import jp.cssj.sakae.pdf.d;
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
/*  32 */   private static final Logger a = Logger.getLogger(b.class.getName());
/*  33 */   private static final FontRenderContext b = new FontRenderContext(null, true, true);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  38 */   private static final boolean c = "1.5".equals(System.getProperty("java.specification.version"));
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
/*     */   public static Font a(g source) {
/*  52 */     Map<TextAttribute, String> atts = new HashMap<>();
/*  53 */     String fontName = source.d();
/*  54 */     String awtName = null;
/*  55 */     if (!a.a(fontName)) {
/*  56 */       String[] aliases = source.a();
/*  57 */       for (int i = 0; i < aliases.length; i++) {
/*  58 */         String alias = aliases[i];
/*  59 */         if (a.a(alias)) {
/*  60 */           awtName = a.b(alias);
/*     */           break;
/*     */         } 
/*     */       } 
/*  64 */       if (awtName == null) {
/*  65 */         awtName = fontName;
/*     */       }
/*     */     } else {
/*  68 */       awtName = a.b(fontName);
/*     */     } 
/*  70 */     atts.put(TextAttribute.FAMILY, awtName);
/*  71 */     return new Font((Map)atts);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(d out, h text, boolean verticalFont) throws IOException {
/*  82 */     int[] gids = text.j();
/*  83 */     int glen = text.l();
/*  84 */     double[] xadvances = text.a(false);
/*  85 */     f fm = text.d();
/*  86 */     out.i();
/*  87 */     int len = 0;
/*  88 */     int off = 0;
/*  89 */     double size = fm.e();
/*  90 */     for (int i = 0; i < glen; i++) {
/*  91 */       double xadvance = (xadvances == null) ? 0.0D : xadvances[i];
/*  92 */       if (i > 0) {
/*  93 */         xadvance -= fm.a(gids[i - 1], gids[i]);
/*     */       }
/*  95 */       if (xadvance != 0.0D) {
/*  96 */         if (verticalFont) {
/*  97 */           xadvance = -xadvance;
/*     */         }
/*  99 */         if (len > 0) {
/* 100 */           out.a(gids, off, len);
/* 101 */           off += len;
/* 102 */           len = 0;
/*     */         } 
/* 104 */         double kerning = -xadvance * 1000.0D / size;
/* 105 */         out.a(kerning);
/*     */       } 
/* 107 */       len++;
/*     */     } 
/* 109 */     if (len > 0) {
/* 110 */       out.a(gids, off, len);
/*     */     }
/* 112 */     out.j();
/* 113 */     out.b("TJ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(jp.cssj.sakae.c.b gc, g fontSource, Font awtFont, h text) throws c {
/* 125 */     byte direction = text.b().a();
/* 126 */     double fontSize = text.b().e();
/* 127 */     Map<TextAttribute, Object> atts = new HashMap<>();
/* 128 */     a.a(atts, text.b());
/* 129 */     awtFont = awtFont.deriveFont((Map)atts);
/* 130 */     int glen = text.l();
/* 131 */     int[] gids = text.j();
/* 132 */     byte[] clens = text.k();
/* 133 */     char[] chars = text.h();
/* 134 */     double letterSpacing = text.m();
/* 135 */     double[] xadvances = text.a(false);
/* 136 */     f fm = text.d();
/* 137 */     short textMode = gc.n();
/*     */     
/* 139 */     if (direction == 3) {
/*     */       
/* 141 */       gc.a(AffineTransform.getRotateInstance(1.5707963267948966D));
/* 142 */       jp.cssj.sakae.a.b bbox = fontSource.f();
/* 143 */       gc.a(AffineTransform.getTranslateInstance(0.0D, (bbox.b + bbox.d) * fontSize / 1000.0D / 2.0D));
/*     */     } 
/*     */ 
/*     */     
/* 147 */     int pgid = 0;
/* 148 */     for (int i = 0, k = 0; i < glen; i++) {
/* 149 */       int gid = gids[i];
/* 150 */       byte gclen = clens[i];
/*     */       try {
/*     */         GlyphVector gv;
/* 153 */         if (c) {
/*     */           
/* 155 */           gv = awtFont.layoutGlyphVector(b, chars, k, gclen, 0);
/*     */         } else {
/* 157 */           gv = awtFont.layoutGlyphVector(b, chars, k, k + gclen, 0);
/*     */         } 
/* 159 */         Shape s = gv.getOutline();
/* 160 */         switch (textMode) {
/*     */           case 0:
/* 162 */             gc.b(s);
/*     */             break;
/*     */           case 1:
/* 165 */             gc.c(s);
/*     */             break;
/*     */           case 2:
/* 168 */             gc.b(s);
/* 169 */             gc.c(s);
/*     */             break;
/*     */           default:
/* 172 */             throw new IllegalStateException();
/*     */         } 
/* 174 */       } catch (Exception e) {
/* 175 */         a.log(Level.WARNING, new String(chars) + "/k=" + k + "/gclen=" + gclen + "/clen=" + text.i(), e);
/*     */       } 
/*     */       
/* 178 */       double dx = fm.a(gid) + letterSpacing;
/* 179 */       if (i > 0) {
/* 180 */         dx -= fm.a(pgid, gid);
/*     */       }
/* 182 */       if (xadvances != null) {
/* 183 */         dx += xadvances[i];
/*     */       }
/* 185 */       gc.a(AffineTransform.getTranslateInstance(dx, 0.0D));
/* 186 */       k += gclen;
/* 187 */       pgid = gid;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/c/d/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */