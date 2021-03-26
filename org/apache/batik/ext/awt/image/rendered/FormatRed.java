/*     */ package org.apache.batik.ext.awt.image.rendered;
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
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
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
/*  51 */     ColorModel srcCM = src.getColorModel();
/*  52 */     if (cm.hasAlpha() != srcCM.hasAlpha() || cm.isAlphaPremultiplied() != srcCM.isAlphaPremultiplied())
/*     */     {
/*  54 */       return new FormatRed(src, cm);
/*     */     }
/*  56 */     if (cm.getNumComponents() != srcCM.getNumComponents()) {
/*  57 */       throw new IllegalArgumentException("Incompatible ColorModel given");
/*     */     }
/*     */ 
/*     */     
/*  61 */     if (srcCM instanceof ComponentColorModel && cm instanceof ComponentColorModel)
/*     */     {
/*  63 */       return src;
/*     */     }
/*  65 */     if (srcCM instanceof DirectColorModel && cm instanceof DirectColorModel)
/*     */     {
/*  67 */       return src;
/*     */     }
/*  69 */     return new FormatRed(src, cm);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormatRed(CachableRed cr, SampleModel sm) {
/*  76 */     super(cr, cr.getBounds(), makeColorModel(cr, sm), sm, cr.getTileGridXOffset(), cr.getTileGridYOffset(), (Map)null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FormatRed(CachableRed cr, ColorModel cm) {
/*  84 */     super(cr, cr.getBounds(), cm, makeSampleModel(cr, cm), cr.getTileGridXOffset(), cr.getTileGridYOffset(), (Map)null);
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
/*  95 */     return getSources().get(0);
/*     */   }
/*     */   
/*     */   public Object getProperty(String name) {
/*  99 */     return getSource().getProperty(name);
/*     */   }
/*     */   
/*     */   public String[] getPropertyNames() {
/* 103 */     return getSource().getPropertyNames();
/*     */   }
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 107 */     ColorModel cm = getColorModel();
/* 108 */     CachableRed cr = getSource();
/* 109 */     ColorModel srcCM = cr.getColorModel();
/* 110 */     SampleModel srcSM = cr.getSampleModel();
/* 111 */     srcSM = srcSM.createCompatibleSampleModel(wr.getWidth(), wr.getHeight());
/*     */ 
/*     */     
/* 114 */     WritableRaster srcWR = Raster.createWritableRaster(srcSM, new Point(wr.getMinX(), wr.getMinY()));
/*     */     
/* 116 */     getSource().copyData(srcWR);
/*     */     
/* 118 */     BufferedImage srcBI = new BufferedImage(srcCM, srcWR.createWritableTranslatedChild(0, 0), srcCM.isAlphaPremultiplied(), null);
/*     */ 
/*     */     
/* 121 */     BufferedImage dstBI = new BufferedImage(cm, wr.createWritableTranslatedChild(0, 0), cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */     
/* 125 */     GraphicsUtil.copyData(srcBI, dstBI);
/*     */     
/* 127 */     return wr;
/*     */   }
/*     */   
/*     */   public static SampleModel makeSampleModel(CachableRed cr, ColorModel cm) {
/* 131 */     SampleModel srcSM = cr.getSampleModel();
/* 132 */     return cm.createCompatibleSampleModel(srcSM.getWidth(), srcSM.getHeight());
/*     */   }
/*     */   
/*     */   public static ColorModel makeColorModel(CachableRed cr, SampleModel sm) {
/*     */     int bits;
/* 137 */     ColorModel srcCM = cr.getColorModel();
/* 138 */     ColorSpace cs = srcCM.getColorSpace();
/*     */     
/* 140 */     int bands = sm.getNumBands();
/*     */ 
/*     */     
/* 143 */     int dt = sm.getDataType();
/* 144 */     switch (dt) { case 0:
/* 145 */         bits = 8; break;
/* 146 */       case 2: bits = 16; break;
/* 147 */       case 1: bits = 16; break;
/* 148 */       case 3: bits = 32; break;
/*     */       default:
/* 150 */         throw new IllegalArgumentException("Unsupported DataBuffer type: " + dt); }
/*     */ 
/*     */ 
/*     */     
/* 154 */     boolean hasAlpha = srcCM.hasAlpha();
/* 155 */     if (hasAlpha) {
/*     */ 
/*     */ 
/*     */       
/* 159 */       if (bands == srcCM.getNumComponents() - 1) {
/* 160 */         hasAlpha = false;
/* 161 */       } else if (bands != srcCM.getNumComponents()) {
/* 162 */         throw new IllegalArgumentException("Incompatible number of bands in and out");
/*     */       }
/*     */     
/* 165 */     } else if (bands == srcCM.getNumComponents() + 1) {
/* 166 */       hasAlpha = true;
/* 167 */     } else if (bands != srcCM.getNumComponents()) {
/* 168 */       throw new IllegalArgumentException("Incompatible number of bands in and out");
/*     */     } 
/*     */ 
/*     */     
/* 172 */     boolean preMult = srcCM.isAlphaPremultiplied();
/* 173 */     if (!hasAlpha) {
/* 174 */       preMult = false;
/*     */     }
/* 176 */     if (sm instanceof java.awt.image.ComponentSampleModel) {
/* 177 */       int[] bitsPer = new int[bands];
/* 178 */       for (int i = 0; i < bands; i++) {
/* 179 */         bitsPer[i] = bits;
/*     */       }
/* 181 */       return new ComponentColorModel(cs, bitsPer, hasAlpha, preMult, hasAlpha ? 3 : 1, dt);
/*     */     } 
/*     */ 
/*     */     
/* 185 */     if (sm instanceof SinglePixelPackedSampleModel) {
/*     */       
/* 187 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)sm;
/* 188 */       int[] masks = sppsm.getBitMasks();
/* 189 */       if (bands == 4) {
/* 190 */         return new DirectColorModel(cs, bits, masks[0], masks[1], masks[2], masks[3], preMult, dt);
/*     */       }
/*     */       
/* 193 */       if (bands == 3) {
/* 194 */         return new DirectColorModel(cs, bits, masks[0], masks[1], masks[2], 0, preMult, dt);
/*     */       }
/*     */ 
/*     */       
/* 198 */       throw new IllegalArgumentException("Incompatible number of bands out for ColorModel");
/*     */     } 
/*     */     
/* 201 */     throw new IllegalArgumentException("Unsupported SampleModel Type");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/FormatRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */