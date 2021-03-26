/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.Light;
/*     */ import org.apache.batik.ext.awt.image.SpotLight;
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
/*     */ 
/*     */ public class SpecularLightingRed
/*     */   extends AbstractTiledRed
/*     */ {
/*     */   private double ks;
/*     */   private double specularExponent;
/*     */   private Light light;
/*     */   private BumpMap bumpMap;
/*     */   private double scaleX;
/*     */   private double scaleY;
/*     */   private Rectangle litRegion;
/*     */   private boolean linear;
/*     */   
/*     */   public SpecularLightingRed(double ks, double specularExponent, Light light, BumpMap bumpMap, Rectangle litRegion, double scaleX, double scaleY, boolean linear) {
/*     */     ColorModel cm;
/*  82 */     this.ks = ks;
/*  83 */     this.specularExponent = specularExponent;
/*  84 */     this.light = light;
/*  85 */     this.bumpMap = bumpMap;
/*  86 */     this.litRegion = litRegion;
/*  87 */     this.scaleX = scaleX;
/*  88 */     this.scaleY = scaleY;
/*  89 */     this.linear = linear;
/*     */ 
/*     */     
/*  92 */     if (linear) {
/*  93 */       cm = GraphicsUtil.Linear_sRGB_Unpre;
/*     */     } else {
/*  95 */       cm = GraphicsUtil.sRGB_Unpre;
/*     */     } 
/*  97 */     int tw = litRegion.width;
/*  98 */     int th = litRegion.height;
/*  99 */     int defSz = AbstractTiledRed.getDefaultTileSize();
/* 100 */     if (tw > defSz) tw = defSz; 
/* 101 */     if (th > defSz) th = defSz; 
/* 102 */     SampleModel sm = cm.createCompatibleSampleModel(tw, th);
/*     */     
/* 104 */     init((CachableRed)null, litRegion, cm, sm, litRegion.x, litRegion.y, (Map)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 109 */     copyToRaster(wr);
/* 110 */     return wr;
/*     */   }
/*     */ 
/*     */   
/*     */   public void genRect(WritableRaster wr) {
/* 115 */     double scaleX = this.scaleX;
/* 116 */     double scaleY = this.scaleY;
/*     */     
/* 118 */     double[] lightColor = this.light.getColor(this.linear);
/*     */     
/* 120 */     int w = wr.getWidth();
/* 121 */     int h = wr.getHeight();
/* 122 */     int minX = wr.getMinX();
/* 123 */     int minY = wr.getMinY();
/*     */     
/* 125 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/* 126 */     int[] pixels = db.getBankData()[0];
/*     */ 
/*     */     
/* 129 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*     */     
/* 131 */     int offset = db.getOffset() + sppsm.getOffset(minX - wr.getSampleModelTranslateX(), minY - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 136 */     int scanStride = sppsm.getScanlineStride();
/* 137 */     int adjust = scanStride - w;
/* 138 */     int p = offset;
/* 139 */     int a = 0, i = 0, j = 0;
/*     */ 
/*     */     
/* 142 */     double x = scaleX * minX;
/* 143 */     double y = scaleY * minY;
/* 144 */     double norm = 0.0D;
/*     */     
/* 146 */     int pixel = 0;
/*     */     
/* 148 */     double mult = (lightColor[0] > lightColor[1]) ? lightColor[0] : lightColor[1];
/* 149 */     mult = (mult > lightColor[2]) ? mult : lightColor[2];
/*     */     
/* 151 */     double scale = 255.0D / mult;
/* 152 */     pixel = (int)(lightColor[0] * scale + 0.5D);
/* 153 */     int tmp = (int)(lightColor[1] * scale + 0.5D);
/* 154 */     pixel = pixel << 8 | tmp;
/* 155 */     tmp = (int)(lightColor[2] * scale + 0.5D);
/* 156 */     pixel = pixel << 8 | tmp;
/*     */     
/* 158 */     mult *= 255.0D * this.ks;
/*     */ 
/*     */ 
/*     */     
/* 162 */     double[][][] NA = this.bumpMap.getNormalArray(minX, minY, w, h);
/*     */ 
/*     */     
/* 165 */     if (this.light instanceof SpotLight) {
/* 166 */       SpotLight slight = (SpotLight)this.light;
/* 167 */       double[][] LA = new double[w][4];
/* 168 */       for (i = 0; i < h; i++) {
/*     */         
/* 170 */         double[][] NR = NA[i];
/* 171 */         slight.getLightRow4(x, y + i * scaleY, scaleX, w, NR, LA);
/* 172 */         for (j = 0; j < w; j++) {
/*     */           
/* 174 */           double[] N = NR[j];
/*     */ 
/*     */           
/* 177 */           double[] L = LA[j];
/* 178 */           double vs = L[3];
/* 179 */           if (vs == 0.0D) {
/* 180 */             a = 0;
/*     */           } else {
/* 182 */             L[2] = L[2] + 1.0D;
/* 183 */             norm = L[0] * L[0] + L[1] * L[1] + L[2] * L[2];
/* 184 */             norm = Math.sqrt(norm);
/* 185 */             double dot = N[0] * L[0] + N[1] * L[1] + N[2] * L[2];
/* 186 */             vs *= Math.pow(dot / norm, this.specularExponent);
/* 187 */             a = (int)(mult * vs + 0.5D);
/* 188 */             if ((a & 0xFFFFFF00) != 0)
/* 189 */               a = ((a & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/*     */           } 
/* 191 */           pixels[p++] = a << 24 | pixel;
/*     */         } 
/* 193 */         p += adjust;
/*     */       } 
/* 195 */     } else if (!this.light.isConstant()) {
/* 196 */       double[][] LA = new double[w][4];
/* 197 */       for (i = 0; i < h; i++)
/*     */       {
/* 199 */         double[][] NR = NA[i];
/* 200 */         this.light.getLightRow(x, y + i * scaleY, scaleX, w, NR, LA);
/* 201 */         for (j = 0; j < w; j++) {
/*     */           
/* 203 */           double[] N = NR[j];
/*     */ 
/*     */           
/* 206 */           double[] L = LA[j];
/* 207 */           L[2] = L[2] + 1.0D;
/* 208 */           norm = L[0] * L[0] + L[1] * L[1] + L[2] * L[2];
/* 209 */           norm = Math.sqrt(norm);
/* 210 */           double dot = N[0] * L[0] + N[1] * L[1] + N[2] * L[2];
/*     */           
/* 212 */           norm = Math.pow(dot / norm, this.specularExponent);
/* 213 */           a = (int)(mult * norm + 0.5D);
/* 214 */           if ((a & 0xFFFFFF00) != 0)
/* 215 */             a = ((a & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/* 216 */           pixels[p++] = a << 24 | pixel;
/*     */         } 
/* 218 */         p += adjust;
/*     */       }
/*     */     
/*     */     } else {
/*     */       
/* 223 */       double[] L = new double[3];
/* 224 */       this.light.getLight(0.0D, 0.0D, 0.0D, L);
/*     */ 
/*     */       
/* 227 */       L[2] = L[2] + 1.0D;
/* 228 */       norm = Math.sqrt(L[0] * L[0] + L[1] * L[1] + L[2] * L[2]);
/* 229 */       if (norm > 0.0D) {
/* 230 */         L[0] = L[0] / norm;
/* 231 */         L[1] = L[1] / norm;
/* 232 */         L[2] = L[2] / norm;
/*     */       } 
/*     */       
/* 235 */       for (i = 0; i < h; i++) {
/* 236 */         double[][] NR = NA[i];
/* 237 */         for (j = 0; j < w; j++) {
/*     */           
/* 239 */           double[] N = NR[j];
/*     */           
/* 241 */           a = (int)(mult * Math.pow(N[0] * L[0] + N[1] * L[1] + N[2] * L[2], this.specularExponent) + 0.5D);
/*     */ 
/*     */           
/* 244 */           if ((a & 0xFFFFFF00) != 0) {
/* 245 */             a = ((a & Integer.MIN_VALUE) != 0) ? 0 : 255;
/*     */           }
/* 247 */           pixels[p++] = a << 24 | pixel;
/*     */         } 
/* 249 */         p += adjust;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/SpecularLightingRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */