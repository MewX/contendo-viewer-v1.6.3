/*     */ package org.apache.batik.transcoder.image;
/*     */ 
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import java.io.OutputStream;
/*     */ import java.lang.reflect.InvocationTargetException;
/*     */ import org.apache.batik.bridge.UserAgent;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.image.resources.Messages;
/*     */ import org.apache.batik.transcoder.keys.FloatKey;
/*     */ import org.apache.batik.transcoder.keys.IntegerKey;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PNGTranscoder
/*     */   extends ImageTranscoder
/*     */ {
/*     */   public PNGTranscoder() {
/*  46 */     this.hints.put(KEY_FORCE_TRANSPARENT_WHITE, Boolean.FALSE);
/*     */   }
/*     */ 
/*     */   
/*     */   public UserAgent getUserAgent() {
/*  51 */     return this.userAgent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BufferedImage createImage(int width, int height) {
/*  60 */     return new BufferedImage(width, height, 2);
/*     */   }
/*     */ 
/*     */   
/*     */   private WriteAdapter getWriteAdapter(String className) {
/*     */     try {
/*  66 */       Class<?> clazz = Class.forName(className);
/*  67 */       WriteAdapter adapter = clazz.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
/*  68 */       return adapter;
/*  69 */     } catch (ClassNotFoundException e) {
/*  70 */       return null;
/*  71 */     } catch (InstantiationException e) {
/*  72 */       return null;
/*  73 */     } catch (IllegalAccessException e) {
/*  74 */       return null;
/*  75 */     } catch (NoSuchMethodException e) {
/*  76 */       return null;
/*  77 */     } catch (InvocationTargetException e) {
/*  78 */       return null;
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
/*     */   public void writeImage(BufferedImage img, TranscoderOutput output) throws TranscoderException {
/*  91 */     OutputStream ostream = output.getOutputStream();
/*  92 */     if (ostream == null) {
/*  93 */       throw new TranscoderException(Messages.formatMessage("png.badoutput", null));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     boolean forceTransparentWhite = false;
/*     */     
/* 103 */     if (this.hints.containsKey(KEY_FORCE_TRANSPARENT_WHITE)) {
/* 104 */       forceTransparentWhite = ((Boolean)this.hints.get(KEY_FORCE_TRANSPARENT_WHITE)).booleanValue();
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 109 */     if (forceTransparentWhite) {
/*     */       
/* 111 */       SinglePixelPackedSampleModel sppsm = (SinglePixelPackedSampleModel)img.getSampleModel();
/* 112 */       forceTransparentWhite(img, sppsm);
/*     */     } 
/*     */     
/* 115 */     WriteAdapter adapter = getWriteAdapter("org.apache.batik.ext.awt.image.codec.png.PNGTranscoderInternalCodecWriteAdapter");
/*     */     
/* 117 */     if (adapter == null) {
/* 118 */       adapter = getWriteAdapter("org.apache.batik.transcoder.image.PNGTranscoderImageIOWriteAdapter");
/*     */     }
/*     */     
/* 121 */     if (adapter == null) {
/* 122 */       throw new TranscoderException("Could not write PNG file because no WriteAdapter is availble");
/*     */     }
/*     */     
/* 125 */     adapter.writeImage(this, img, output);
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
/* 186 */   public static final TranscodingHints.Key KEY_GAMMA = (TranscodingHints.Key)new FloatKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 192 */   public static final float[] DEFAULT_CHROMA = new float[] { 0.3127F, 0.329F, 0.64F, 0.33F, 0.3F, 0.6F, 0.15F, 0.06F };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 226 */   public static final TranscodingHints.Key KEY_INDEXED = (TranscodingHints.Key)new IntegerKey();
/*     */   
/*     */   public static interface WriteAdapter {
/*     */     void writeImage(PNGTranscoder param1PNGTranscoder, BufferedImage param1BufferedImage, TranscoderOutput param1TranscoderOutput) throws TranscoderException;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/image/PNGTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */