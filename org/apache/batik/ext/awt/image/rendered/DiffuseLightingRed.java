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
/*     */ public class DiffuseLightingRed
/*     */   extends AbstractRed
/*     */ {
/*     */   private double kd;
/*     */   private Light light;
/*     */   private BumpMap bumpMap;
/*     */   private double scaleX;
/*     */   private double scaleY;
/*     */   private Rectangle litRegion;
/*     */   private boolean linear;
/*     */   
/*     */   public DiffuseLightingRed(double kd, Light light, BumpMap bumpMap, Rectangle litRegion, double scaleX, double scaleY, boolean linear) {
/*     */     ColorModel cm;
/*  75 */     this.kd = kd;
/*  76 */     this.light = light;
/*  77 */     this.bumpMap = bumpMap;
/*  78 */     this.litRegion = litRegion;
/*  79 */     this.scaleX = scaleX;
/*  80 */     this.scaleY = scaleY;
/*  81 */     this.linear = linear;
/*     */ 
/*     */     
/*  84 */     if (linear) {
/*  85 */       cm = GraphicsUtil.Linear_sRGB_Pre;
/*     */     } else {
/*  87 */       cm = GraphicsUtil.sRGB_Pre;
/*     */     } 
/*  89 */     SampleModel sm = cm.createCompatibleSampleModel(litRegion.width, litRegion.height);
/*     */ 
/*     */ 
/*     */     
/*  93 */     init((CachableRed)null, litRegion, cm, sm, litRegion.x, litRegion.y, (Map)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/*  98 */     double[] lightColor = this.light.getColor(this.linear);
/*     */     
/* 100 */     int w = wr.getWidth();
/* 101 */     int h = wr.getHeight();
/* 102 */     int minX = wr.getMinX();
/* 103 */     int minY = wr.getMinY();
/*     */     
/* 105 */     DataBufferInt db = (DataBufferInt)wr.getDataBuffer();
/* 106 */     int[] pixels = db.getBankData()[0];
/*     */ 
/*     */     
/* 109 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/*     */     
/* 111 */     int offset = db.getOffset() + sppsm.getOffset(minX - wr.getSampleModelTranslateX(), minY - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     int scanStride = sppsm.getScanlineStride();
/* 117 */     int adjust = scanStride - w;
/* 118 */     int p = offset;
/* 119 */     int r = 0, g = 0, b = 0;
/* 120 */     int i = 0, j = 0;
/*     */ 
/*     */     
/* 123 */     double x = this.scaleX * minX;
/* 124 */     double y = this.scaleY * minY;
/* 125 */     double NL = 0.0D;
/*     */ 
/*     */     
/* 128 */     double[][][] NA = this.bumpMap.getNormalArray(minX, minY, w, h);
/* 129 */     if (!this.light.isConstant()) {
/* 130 */       double[][] LA = new double[w][3];
/*     */       
/* 132 */       for (i = 0; i < h; i++) {
/* 133 */         double[][] NR = NA[i];
/* 134 */         this.light.getLightRow(x, y + i * this.scaleY, this.scaleX, w, NR, LA);
/* 135 */         for (j = 0; j < w; j++) {
/*     */           
/* 137 */           double[] N = NR[j];
/*     */ 
/*     */           
/* 140 */           double[] L = LA[j];
/*     */           
/* 142 */           NL = 255.0D * this.kd * (N[0] * L[0] + N[1] * L[1] + N[2] * L[2]);
/*     */           
/* 144 */           r = (int)(NL * lightColor[0]);
/* 145 */           g = (int)(NL * lightColor[1]);
/* 146 */           b = (int)(NL * lightColor[2]);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 151 */           if ((r & 0xFFFFFF00) != 0)
/* 152 */             r = ((r & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/* 153 */           if ((g & 0xFFFFFF00) != 0)
/* 154 */             g = ((g & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/* 155 */           if ((b & 0xFFFFFF00) != 0) {
/* 156 */             b = ((b & Integer.MIN_VALUE) != 0) ? 0 : 255;
/*     */           }
/* 158 */           pixels[p++] = 0xFF000000 | r << 16 | g << 8 | b;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 167 */         p += adjust;
/*     */       }
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 173 */       double[] L = new double[3];
/* 174 */       this.light.getLight(0.0D, 0.0D, 0.0D, L);
/*     */       
/* 176 */       for (i = 0; i < h; i++) {
/* 177 */         double[][] NR = NA[i];
/* 178 */         for (j = 0; j < w; j++) {
/*     */           
/* 180 */           double[] N = NR[j];
/*     */           
/* 182 */           NL = 255.0D * this.kd * (N[0] * L[0] + N[1] * L[1] + N[2] * L[2]);
/*     */           
/* 184 */           r = (int)(NL * lightColor[0]);
/* 185 */           g = (int)(NL * lightColor[1]);
/* 186 */           b = (int)(NL * lightColor[2]);
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 191 */           if ((r & 0xFFFFFF00) != 0)
/* 192 */             r = ((r & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/* 193 */           if ((g & 0xFFFFFF00) != 0)
/* 194 */             g = ((g & Integer.MIN_VALUE) != 0) ? 0 : 255; 
/* 195 */           if ((b & 0xFFFFFF00) != 0) {
/* 196 */             b = ((b & Integer.MIN_VALUE) != 0) ? 0 : 255;
/*     */           }
/* 198 */           pixels[p++] = 0xFF000000 | r << 16 | g << 8 | b;
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 206 */         p += adjust;
/*     */       } 
/*     */     } 
/*     */     
/* 210 */     return wr;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/DiffuseLightingRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */