/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.ComponentColorModel;
/*     */ import java.awt.image.ComponentSampleModel;
/*     */ import java.awt.image.DataBufferByte;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.PixelInterleavedSampleModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MultiplyAlphaRed
/*     */   extends AbstractRed
/*     */ {
/*     */   public MultiplyAlphaRed(CachableRed src, CachableRed alpha) {
/*  58 */     super(makeList(src, alpha), makeBounds(src, alpha), fixColorModel(src), fixSampleModel(src), src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
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
/*     */   public boolean is_INT_PACK_BYTE_COMP(SampleModel srcSM, SampleModel alpSM) {
/*  70 */     if (!(srcSM instanceof SinglePixelPackedSampleModel)) return false; 
/*  71 */     if (!(alpSM instanceof ComponentSampleModel)) return false;
/*     */ 
/*     */     
/*  74 */     if (srcSM.getDataType() != 3) return false; 
/*  75 */     if (alpSM.getDataType() != 0) return false;
/*     */ 
/*     */ 
/*     */     
/*  79 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)srcSM;
/*     */     
/*  81 */     int[] masks = sppsm.getBitMasks();
/*  82 */     if (masks.length != 4) return false; 
/*  83 */     if (masks[0] != 16711680) return false; 
/*  84 */     if (masks[1] != 65280) return false; 
/*  85 */     if (masks[2] != 255) return false; 
/*  86 */     if (masks[3] != -16777216) return false;
/*     */ 
/*     */     
/*  89 */     ComponentSampleModel csm = (ComponentSampleModel)alpSM;
/*  90 */     if (csm.getNumBands() != 1) return false; 
/*  91 */     if (csm.getPixelStride() != 1) return false;
/*     */     
/*  93 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public WritableRaster INT_PACK_BYTE_COMP_Impl(WritableRaster wr) {
/*  98 */     CachableRed srcRed = getSources().get(0);
/*  99 */     CachableRed alphaRed = getSources().get(1);
/*     */ 
/*     */     
/* 102 */     srcRed.copyData(wr);
/*     */     
/* 104 */     Rectangle rgn = wr.getBounds();
/* 105 */     rgn = rgn.intersection(alphaRed.getBounds());
/*     */     
/* 107 */     Raster r = alphaRed.getData(rgn);
/*     */ 
/*     */     
/* 110 */     ComponentSampleModel csm = (ComponentSampleModel)r.getSampleModel();
/* 111 */     int alpScanStride = csm.getScanlineStride();
/*     */     
/* 113 */     DataBufferByte alpDB = (DataBufferByte)r.getDataBuffer();
/* 114 */     int alpBase = alpDB.getOffset() + csm.getOffset(rgn.x - r.getSampleModelTranslateX(), rgn.y - r.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 121 */     byte[] alpPixels = alpDB.getBankData()[0];
/*     */ 
/*     */     
/* 124 */     SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)wr.getSampleModel();
/* 125 */     int srcScanStride = sppsm.getScanlineStride();
/*     */     
/* 127 */     DataBufferInt srcDB = (DataBufferInt)wr.getDataBuffer();
/* 128 */     int srcBase = srcDB.getOffset() + sppsm.getOffset(rgn.x - wr.getSampleModelTranslateX(), rgn.y - wr.getSampleModelTranslateY());
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 134 */     int[] srcPixels = srcDB.getBankData()[0];
/*     */     
/* 136 */     ColorModel cm = srcRed.getColorModel();
/*     */     
/* 138 */     if (cm.isAlphaPremultiplied()) {
/*     */       
/* 140 */       for (int y = 0; y < rgn.height; y++) {
/* 141 */         int sp = srcBase + y * srcScanStride;
/* 142 */         int ap = alpBase + y * alpScanStride;
/* 143 */         int end = sp + rgn.width;
/*     */         
/* 145 */         while (sp < end) {
/* 146 */           int a = alpPixels[ap++] & 0xFF;
/* 147 */           int pix = srcPixels[sp];
/* 148 */           srcPixels[sp] = ((pix >>> 24) * a & 0xFF00) << 16 | ((pix >>> 16 & 0xFF) * a & 0xFF00) << 8 | (pix >>> 8 & 0xFF) * a & 0xFF00 | ((pix & 0xFF) * a & 0xFF00) >> 8;
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 153 */           sp++;
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 159 */       for (int y = 0; y < rgn.height; y++) {
/* 160 */         int sp = srcBase + y * srcScanStride;
/* 161 */         int ap = alpBase + y * alpScanStride;
/* 162 */         int end = sp + rgn.width;
/* 163 */         while (sp < end) {
/* 164 */           int a = alpPixels[ap++] & 0xFF;
/* 165 */           int sa = srcPixels[sp] >>> 24;
/* 166 */           srcPixels[sp] = (sa * a & 0xFF00) << 16 | srcPixels[sp] & 0xFFFFFF;
/*     */           
/* 168 */           sp++;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 173 */     return wr;
/*     */   }
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 178 */     CachableRed srcRed = getSources().get(0);
/* 179 */     CachableRed alphaRed = getSources().get(1);
/*     */     
/* 181 */     if (is_INT_PACK_BYTE_COMP(srcRed.getSampleModel(), alphaRed.getSampleModel()))
/*     */     {
/* 183 */       return INT_PACK_BYTE_COMP_Impl(wr);
/*     */     }
/* 185 */     ColorModel cm = srcRed.getColorModel();
/* 186 */     if (cm.hasAlpha()) {
/*     */       
/* 188 */       srcRed.copyData(wr);
/*     */       
/* 190 */       Rectangle rectangle = wr.getBounds();
/* 191 */       if (rectangle.intersects(alphaRed.getBounds())) {
/* 192 */         rectangle = rectangle.intersection(alphaRed.getBounds());
/*     */       } else {
/* 194 */         return wr;
/*     */       } 
/* 196 */       int[] wrData = null;
/* 197 */       int[] alphaData = null;
/*     */       
/* 199 */       Raster r = alphaRed.getData(rectangle);
/* 200 */       int w = rectangle.width;
/*     */       
/* 202 */       int j = wr.getSampleModel().getNumBands();
/*     */       
/* 204 */       if (cm.isAlphaPremultiplied()) {
/* 205 */         for (int y = rectangle.y; y < rectangle.y + rectangle.height; y++) {
/* 206 */           wrData = wr.getPixels(rectangle.x, y, w, 1, wrData);
/* 207 */           alphaData = r.getSamples(rectangle.x, y, w, 1, 0, alphaData);
/* 208 */           int k = 0;
/*     */ 
/*     */           
/* 211 */           switch (j) {
/*     */             case 2:
/* 213 */               for (int anAlphaData2 : alphaData) {
/* 214 */                 int a = anAlphaData2 & 0xFF;
/* 215 */                 wrData[k] = (wrData[k] & 0xFF) * a >> 8;
/* 216 */                 k++;
/* 217 */                 wrData[k] = (wrData[k] & 0xFF) * a >> 8;
/* 218 */                 k++;
/*     */               } 
/*     */               break;
/*     */             case 4:
/* 222 */               for (int anAlphaData1 : alphaData) {
/* 223 */                 int a = anAlphaData1 & 0xFF;
/* 224 */                 wrData[k] = (wrData[k] & 0xFF) * a >> 8;
/* 225 */                 k++;
/* 226 */                 wrData[k] = (wrData[k] & 0xFF) * a >> 8;
/* 227 */                 k++;
/* 228 */                 wrData[k] = (wrData[k] & 0xFF) * a >> 8;
/* 229 */                 k++;
/* 230 */                 wrData[k] = (wrData[k] & 0xFF) * a >> 8;
/* 231 */                 k++;
/*     */               } 
/*     */               break;
/*     */             default:
/* 235 */               for (int anAlphaData : alphaData) {
/* 236 */                 int a = anAlphaData & 0xFF;
/* 237 */                 for (int b = 0; b < j; b++) {
/* 238 */                   wrData[k] = (wrData[k] & 0xFF) * a >> 8;
/* 239 */                   k++;
/*     */                 } 
/*     */               }  break;
/*     */           } 
/* 243 */           wr.setPixels(rectangle.x, y, w, 1, wrData);
/*     */         } 
/*     */       } else {
/* 246 */         int b = srcRed.getSampleModel().getNumBands() - 1;
/* 247 */         for (int y = rectangle.y; y < rectangle.y + rectangle.height; y++) {
/* 248 */           wrData = wr.getSamples(rectangle.x, y, w, 1, b, wrData);
/* 249 */           alphaData = r.getSamples(rectangle.x, y, w, 1, 0, alphaData);
/* 250 */           for (int k = 0; k < wrData.length; k++) {
/* 251 */             wrData[k] = (wrData[k] & 0xFF) * (alphaData[k] & 0xFF) >> 8;
/*     */           }
/* 253 */           wr.setSamples(rectangle.x, y, w, 1, b, wrData);
/*     */         } 
/*     */       } 
/*     */       
/* 257 */       return wr;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 262 */     int[] bands = new int[wr.getNumBands() - 1];
/* 263 */     for (int i = 0; i < bands.length; i++) {
/* 264 */       bands[i] = i;
/*     */     }
/*     */     
/* 267 */     WritableRaster subWr = wr.createWritableChild(wr.getMinX(), wr.getMinY(), wr.getWidth(), wr.getHeight(), wr.getMinX(), wr.getMinY(), bands);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 272 */     srcRed.copyData(subWr);
/*     */     
/* 274 */     Rectangle rgn = wr.getBounds();
/* 275 */     rgn = rgn.intersection(alphaRed.getBounds());
/*     */ 
/*     */     
/* 278 */     bands = new int[] { wr.getNumBands() - 1 };
/* 279 */     subWr = wr.createWritableChild(rgn.x, rgn.y, rgn.width, rgn.height, rgn.x, rgn.y, bands);
/*     */ 
/*     */ 
/*     */     
/* 283 */     alphaRed.copyData(subWr);
/*     */     
/* 285 */     return wr;
/*     */   }
/*     */   
/*     */   public static List makeList(CachableRed src1, CachableRed src2) {
/* 289 */     List<CachableRed> ret = new ArrayList(2);
/* 290 */     ret.add(src1);
/* 291 */     ret.add(src2);
/* 292 */     return ret;
/*     */   }
/*     */   
/*     */   public static Rectangle makeBounds(CachableRed src1, CachableRed src2) {
/* 296 */     Rectangle r1 = src1.getBounds();
/* 297 */     Rectangle r2 = src2.getBounds();
/* 298 */     return r1.intersection(r2);
/*     */   }
/*     */   
/*     */   public static SampleModel fixSampleModel(CachableRed src) {
/* 302 */     ColorModel cm = src.getColorModel();
/* 303 */     SampleModel srcSM = src.getSampleModel();
/*     */     
/* 305 */     if (cm.hasAlpha()) {
/* 306 */       return srcSM;
/*     */     }
/* 308 */     int w = srcSM.getWidth();
/* 309 */     int h = srcSM.getHeight();
/* 310 */     int b = srcSM.getNumBands() + 1;
/* 311 */     int[] offsets = new int[b];
/* 312 */     for (int i = 0; i < b; i++) {
/* 313 */       offsets[i] = i;
/*     */     }
/*     */     
/* 316 */     return new PixelInterleavedSampleModel(0, w, h, b, w * b, offsets);
/*     */   }
/*     */ 
/*     */   
/*     */   public static ColorModel fixColorModel(CachableRed src) {
/* 321 */     ColorModel cm = src.getColorModel();
/*     */     
/* 323 */     if (cm.hasAlpha()) {
/* 324 */       return cm;
/*     */     }
/* 326 */     int b = src.getSampleModel().getNumBands() + 1;
/* 327 */     int[] bits = new int[b];
/* 328 */     for (int i = 0; i < b; i++) {
/* 329 */       bits[i] = 8;
/*     */     }
/* 331 */     ColorSpace cs = cm.getColorSpace();
/*     */     
/* 333 */     return new ComponentColorModel(cs, bits, true, false, 3, 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/MultiplyAlphaRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */