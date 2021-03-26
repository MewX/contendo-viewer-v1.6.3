/*     */ package org.apache.batik.transcoder.wmf.tosvg;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.TexturePaint;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TextureFactory
/*     */ {
/*  40 */   private static TextureFactory fac = null;
/*  41 */   private Map textures = new HashMap<Object, Object>(1);
/*     */   private static final int SIZE = 10;
/*  43 */   private float scale = 1.0F;
/*     */ 
/*     */ 
/*     */   
/*     */   private TextureFactory(float scale) {}
/*     */ 
/*     */   
/*     */   public static TextureFactory getInstance() {
/*  51 */     if (fac == null) fac = new TextureFactory(1.0F); 
/*  52 */     return fac;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static TextureFactory getInstance(float scale) {
/*  59 */     if (fac == null) fac = new TextureFactory(scale); 
/*  60 */     return fac;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void reset() {
/*  67 */     this.textures.clear();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint getTexture(int textureId) {
/*  74 */     Integer _itexture = Integer.valueOf(textureId);
/*  75 */     if (this.textures.containsKey(_itexture)) {
/*  76 */       Paint paint1 = (Paint)this.textures.get(_itexture);
/*  77 */       return paint1;
/*     */     } 
/*  79 */     Paint paint = createTexture(textureId, null, null);
/*  80 */     if (paint != null) this.textures.put(_itexture, paint); 
/*  81 */     return paint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint getTexture(int textureId, Color foreground) {
/*  89 */     ColoredTexture _ctexture = new ColoredTexture(textureId, foreground, null);
/*  90 */     if (this.textures.containsKey(_ctexture)) {
/*  91 */       Paint paint1 = (Paint)this.textures.get(_ctexture);
/*  92 */       return paint1;
/*     */     } 
/*  94 */     Paint paint = createTexture(textureId, foreground, null);
/*  95 */     if (paint != null) this.textures.put(_ctexture, paint); 
/*  96 */     return paint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint getTexture(int textureId, Color foreground, Color background) {
/* 105 */     ColoredTexture _ctexture = new ColoredTexture(textureId, foreground, background);
/* 106 */     if (this.textures.containsKey(_ctexture)) {
/* 107 */       Paint paint1 = (Paint)this.textures.get(_ctexture);
/* 108 */       return paint1;
/*     */     } 
/* 110 */     Paint paint = createTexture(textureId, foreground, background);
/* 111 */     if (paint != null) this.textures.put(_ctexture, paint); 
/* 112 */     return paint;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private Paint createTexture(int textureId, Color foreground, Color background) {
/* 120 */     BufferedImage img = new BufferedImage(10, 10, 2);
/* 121 */     Graphics2D g2d = img.createGraphics();
/* 122 */     Rectangle2D rec = new Rectangle2D.Float(0.0F, 0.0F, 10.0F, 10.0F);
/* 123 */     Paint paint = null;
/* 124 */     boolean ok = false;
/* 125 */     if (background != null) {
/* 126 */       g2d.setColor(background);
/* 127 */       g2d.fillRect(0, 0, 10, 10);
/*     */     } 
/* 129 */     if (foreground == null) { g2d.setColor(Color.black); }
/* 130 */     else { g2d.setColor(foreground); }
/*     */     
/* 132 */     if (textureId == 1) {
/* 133 */       for (int i = 0; i < 5; i++) {
/* 134 */         g2d.drawLine(i * 10, 0, i * 10, 10);
/*     */       }
/* 136 */       ok = true;
/* 137 */     } else if (textureId == 0) {
/* 138 */       for (int i = 0; i < 5; i++) {
/* 139 */         g2d.drawLine(0, i * 10, 10, i * 10);
/*     */       }
/* 141 */       ok = true;
/* 142 */     } else if (textureId == 3) {
/* 143 */       for (int i = 0; i < 5; i++) {
/* 144 */         g2d.drawLine(0, i * 10, i * 10, 0);
/*     */       }
/* 146 */       ok = true;
/* 147 */     } else if (textureId == 2) {
/* 148 */       for (int i = 0; i < 5; i++) {
/* 149 */         g2d.drawLine(0, i * 10, 10 - i * 10, 10);
/*     */       }
/* 151 */       ok = true;
/* 152 */     } else if (textureId == 5) {
/* 153 */       for (int i = 0; i < 5; i++) {
/* 154 */         g2d.drawLine(0, i * 10, i * 10, 0);
/* 155 */         g2d.drawLine(0, i * 10, 10 - i * 10, 10);
/*     */       } 
/* 157 */       ok = true;
/* 158 */     } else if (textureId == 4) {
/* 159 */       for (int i = 0; i < 5; i++) {
/* 160 */         g2d.drawLine(i * 10, 0, i * 10, 10);
/* 161 */         g2d.drawLine(0, i * 10, 10, i * 10);
/*     */       } 
/* 163 */       ok = true;
/*     */     } 
/* 165 */     img.flush();
/* 166 */     if (ok) paint = new TexturePaint(img, rec); 
/* 167 */     return paint;
/*     */   }
/*     */ 
/*     */   
/*     */   private class ColoredTexture
/*     */   {
/*     */     final int textureId;
/*     */     
/*     */     final Color foreground;
/*     */     
/*     */     final Color background;
/*     */     
/*     */     ColoredTexture(int textureId, Color foreground, Color background) {
/* 180 */       this.textureId = textureId;
/* 181 */       this.foreground = foreground;
/* 182 */       this.background = background;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/TextureFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */