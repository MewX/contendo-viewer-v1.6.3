/*     */ package org.apache.xmlgraphics.image.rendered;
/*     */ 
/*     */ import java.awt.Point;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.Map;
/*     */ import org.apache.xmlgraphics.image.GraphicsUtil;
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
/*     */ public class FormatRed
/*     */   extends AbstractRed
/*     */ {
/*     */   public static CachableRed construct(CachableRed src, ColorModel cm) {
/*  55 */     ColorModel srcCM = src.getColorModel();
/*  56 */     if (cm.hasAlpha() != srcCM.hasAlpha() || cm.isAlphaPremultiplied() != srcCM.isAlphaPremultiplied())
/*     */     {
/*  58 */       return new FormatRed(src, cm);
/*     */     }
/*     */     
/*  61 */     if (cm.getNumComponents() != srcCM.getNumComponents()) {
/*  62 */       throw new IllegalArgumentException("Incompatible ColorModel given");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  67 */     if (srcCM instanceof ComponentColorModel && cm instanceof ComponentColorModel)
/*     */     {
/*  69 */       return src;
/*     */     }
/*     */     
/*  72 */     if (srcCM instanceof DirectColorModel && cm instanceof DirectColorModel)
/*     */     {
/*  74 */       return src;
/*     */     }
/*     */     
/*  77 */     return new FormatRed(src, cm);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormatRed(CachableRed cr, SampleModel sm) {
/*  84 */     super(cr, cr.getBounds(), makeColorModel(cr, sm), sm, cr.getTileGridXOffset(), cr.getTileGridYOffset(), (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormatRed(CachableRed cr, ColorModel cm) {
/*  92 */     super(cr, cr.getBounds(), cm, makeSampleModel(cr, cm), cr.getTileGridXOffset(), cr.getTileGridYOffset(), (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CachableRed getSource() {
/* 103 */     return getSources().get(0);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/* 107 */     return getSource().getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 111 */     return getSource().getPropertyNames();
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 115 */     ColorModel cm = getColorModel();
/* 116 */     CachableRed cr = getSource();
/* 117 */     ColorModel srcCM = cr.getColorModel();
/* 118 */     SampleModel srcSM = cr.getSampleModel();
/* 119 */     srcSM = srcSM.createCompatibleSampleModel(wr.getWidth(), wr.getHeight());
/*     */ 
/*     */     
/* 122 */     WritableRaster srcWR = Raster.createWritableRaster(srcSM, new Point(wr.getMinX(), wr.getMinY()));
/*     */     
/* 124 */     getSource().copyData(srcWR);
/*     */     
/* 126 */     BufferedImage srcBI = new BufferedImage(srcCM, srcWR.createWritableTranslatedChild(0, 0), srcCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */     
/* 129 */     BufferedImage dstBI = new BufferedImage(cm, wr.createWritableTranslatedChild(0, 0), cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */     
/* 133 */     GraphicsUtil.copyData(srcBI, dstBI);
/*     */     
/* 135 */     return wr;
/*     */   }
/*     */   
/*     */   public static SampleModel makeSampleModel(CachableRed cr, ColorModel cm) {
/* 139 */     SampleModel srcSM = cr.getSampleModel();
/* 140 */     return cm.createCompatibleSampleModel(srcSM.getWidth(), srcSM.getHeight());
/*     */   }
/*     */   
/*     */   public static ColorModel makeColorModel(CachableRed cr, SampleModel sm) {
/*     */     int bits;
/* 145 */     ColorModel srcCM = cr.getColorModel();
/* 146 */     ColorSpace cs = srcCM.getColorSpace();
/*     */     
/* 148 */     int bands = sm.getNumBands();
/*     */ 
/*     */     
/* 151 */     int dt = sm.getDataType();
/* 152 */     switch (dt) { case 0:
/* 153 */         bits = 8; break;
/* 154 */       case 2: bits = 16; break;
/* 155 */       case 1: bits = 16; break;
/* 156 */       case 3: bits = 32; break;
/*     */       default:
/* 158 */         throw new IllegalArgumentException("Unsupported DataBuffer type: " + dt); }
/*     */ 
/*     */ 
/*     */     
/* 162 */     boolean hasAlpha = srcCM.hasAlpha();
/* 163 */     if (hasAlpha) {
/*     */ 
/*     */ 
/*     */       
/* 167 */       if (bands == srcCM.getNumComponents() - 1) {
/* 168 */         hasAlpha = false;
/* 169 */       } else if (bands != srcCM.getNumComponents()) {
/* 170 */         throw new IllegalArgumentException("Incompatible number of bands in and out");
/*     */       }
/*     */     
/*     */     }
/* 174 */     else if (bands == srcCM.getNumComponents() + 1) {
/* 175 */       hasAlpha = true;
/* 176 */     } else if (bands != srcCM.getNumComponents()) {
/* 177 */       throw new IllegalArgumentException("Incompatible number of bands in and out");
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 182 */     boolean preMult = srcCM.isAlphaPremultiplied();
/* 183 */     if (!hasAlpha) {
/* 184 */       preMult = false;
/*     */     }
/*     */     
/* 187 */     if (sm instanceof java.awt.image.ComponentSampleModel) {
/* 188 */       int[] bitsPer = new int[bands];
/* 189 */       for (int i = 0; i < bands; i++) {
/* 190 */         bitsPer[i] = bits;
/*     */       }
/*     */       
/* 193 */       return new ComponentColorModel(cs, bitsPer, hasAlpha, preMult, hasAlpha ? 3 : 1, dt);
/*     */     } 
/*     */ 
/*     */     
/* 197 */     if (sm instanceof SinglePixelPackedSampleModel) {
/*     */       
/* 199 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/* 200 */       int[] masks = sppsm.getBitMasks();
/* 201 */       if (bands == 4) {
/* 202 */         return new DirectColorModel(cs, bits, masks[0], masks[1], masks[2], masks[3], preMult, dt);
/*     */       }
/*     */       
/* 205 */       if (bands == 3) {
/* 206 */         return new DirectColorModel(cs, bits, masks[0], masks[1], masks[2], 0, preMult, dt);
/*     */       }
/*     */ 
/*     */       
/* 210 */       throw new IllegalArgumentException("Incompatible number of bands out for ColorModel");
/*     */     } 
/*     */ 
/*     */     
/* 214 */     throw new IllegalArgumentException("Unsupported SampleModel Type");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/rendered/FormatRed.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */