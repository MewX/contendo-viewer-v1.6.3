/*    */ package org.apache.batik.ext.awt.image.codec.png;
/*    */ 
/*    */ import java.awt.image.RenderedImage;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import org.apache.batik.ext.awt.image.codec.util.ImageDecoderImpl;
/*    */ import org.apache.batik.ext.awt.image.codec.util.PropertyUtil;
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
/*    */ public class PNGImageDecoder
/*    */   extends ImageDecoderImpl
/*    */ {
/*    */   public PNGImageDecoder(InputStream input, PNGDecodeParam param) {
/* 61 */     super(input, param);
/*    */   }
/*    */   
/*    */   public RenderedImage decodeAsRenderedImage(int page) throws IOException {
/* 65 */     if (page != 0) {
/* 66 */       throw new IOException(PropertyUtil.getString("PNGImageDecoder19"));
/*    */     }
/* 68 */     return (RenderedImage)new PNGImage((InputStream)this.input, (PNGDecodeParam)this.param);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/png/PNGImageDecoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */