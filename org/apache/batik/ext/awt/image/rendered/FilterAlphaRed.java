/*    */ package org.apache.batik.ext.awt.image.rendered;
/*    */ 
/*    */ import java.awt.image.Raster;
/*    */ import java.awt.image.SampleModel;
/*    */ import java.awt.image.WritableRaster;
/*    */ import java.util.Map;
/*    */ import org.apache.batik.ext.awt.ColorSpaceHintKey;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class FilterAlphaRed
/*    */   extends AbstractRed
/*    */ {
/*    */   public FilterAlphaRed(CachableRed src) {
/* 41 */     super(src, src.getBounds(), src.getColorModel(), src.getSampleModel(), src.getTileGridXOffset(), src.getTileGridYOffset(), (Map)null);
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 48 */     this.props.put("org.apache.batik.gvt.filter.Colorspace", ColorSpaceHintKey.VALUE_COLORSPACE_ALPHA);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public WritableRaster copyData(WritableRaster wr) {
/* 55 */     CachableRed srcRed = getSources().get(0);
/*    */     
/* 57 */     SampleModel sm = srcRed.getSampleModel();
/* 58 */     if (sm.getNumBands() == 1)
/*    */     {
/* 60 */       return srcRed.copyData(wr);
/*    */     }
/* 62 */     PadRed.ZeroRecter.zeroRect(wr);
/* 63 */     Raster srcRas = srcRed.getData(wr.getBounds());
/* 64 */     AbstractRed.copyBand(srcRas, srcRas.getNumBands() - 1, wr, wr.getNumBands() - 1);
/*    */     
/* 66 */     return wr;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/FilterAlphaRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */