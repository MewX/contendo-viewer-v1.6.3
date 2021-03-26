/*     */ package org.apache.batik.transcoder.image;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.batik.bridge.UserAgent;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.keys.StringKey;
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
/*     */ public class TIFFTranscoder
/*     */   extends ImageTranscoder
/*     */ {
/*     */   public TIFFTranscoder() {
/*  45 */     this.hints.put(KEY_FORCE_TRANSPARENT_WHITE, Boolean.FALSE);
/*     */   }
/*     */ 
/*     */   
/*     */   public UserAgent getUserAgent() {
/*  50 */     return this.userAgent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage createImage(int width, int height) {
/*  59 */     return new BufferedImage(width, height, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private WriteAdapter getWriteAdapter(String className) {
/*     */     try {
/*  65 */       Class<?> clazz = Class.forName(className);
/*  66 */       WriteAdapter adapter = clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*  67 */       return adapter;
/*  68 */     } catch (ClassNotFoundException e) {
/*  69 */       return null;
/*  70 */     } catch (InstantiationException e) {
/*  71 */       return null;
/*  72 */     } catch (IllegalAccessException e) {
/*  73 */       return null;
/*  74 */     } catch (NoSuchMethodException e) {
/*  75 */       return null;
/*  76 */     } catch (InvocationTargetException e) {
/*  77 */       return null;
/*     */     } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void writeImage(BufferedImage img, TranscoderOutput output) throws TranscoderException {
/*  94 */     boolean forceTransparentWhite = false;
/*     */     
/*  96 */     if (this.hints.containsKey(PNGTranscoder.KEY_FORCE_TRANSPARENT_WHITE)) {
/*  97 */       forceTransparentWhite = ((Boolean)this.hints.get(PNGTranscoder.KEY_FORCE_TRANSPARENT_WHITE)).booleanValue();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 102 */     if (forceTransparentWhite) {
/*     */       
/* 104 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)img.getSampleModel();
/* 105 */       forceTransparentWhite(img, sppsm);
/*     */     } 
/*     */     
/* 108 */     WriteAdapter adapter = getWriteAdapter("org.apache.batik.ext.awt.image.codec.tiff.TIFFTranscoderInternalCodecWriteAdapter");
/*     */     
/* 110 */     if (adapter == null) {
/* 111 */       adapter = getWriteAdapter("org.apache.batik.ext.awt.image.codec.imageio.TIFFTranscoderImageIOWriteAdapter");
/*     */     }
/*     */     
/* 114 */     if (adapter == null) {
/* 115 */       throw new TranscoderException("Could not write TIFF file because no WriteAdapter is availble");
/*     */     }
/*     */     
/* 118 */     adapter.writeImage(this, img, output);
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
/*     */   
/* 185 */   public static final TranscodingHints.Key KEY_FORCE_TRANSPARENT_WHITE = ImageTranscoder.KEY_FORCE_TRANSPARENT_WHITE;
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
/* 213 */   public static final TranscodingHints.Key KEY_COMPRESSION_METHOD = (TranscodingHints.Key)new StringKey();
/*     */   
/*     */   public static interface WriteAdapter {
/*     */     void writeImage(TIFFTranscoder param1TIFFTranscoder, BufferedImage param1BufferedImage, TranscoderOutput param1TranscoderOutput) throws TranscoderException;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/image/TIFFTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */