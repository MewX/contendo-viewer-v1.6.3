/*     */ package org.apache.batik.transcoder.image;
/*     */ 
/*     */ import java.awt.AlphaComposite;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.SinglePixelPackedSampleModel;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.gvt.renderer.ConcreteImageRendererFactory;
/*     */ import org.apache.batik.gvt.renderer.ImageRenderer;
/*     */ import org.apache.batik.transcoder.SVGAbstractTranscoder;
/*     */ import org.apache.batik.transcoder.TranscoderException;
/*     */ import org.apache.batik.transcoder.TranscoderOutput;
/*     */ import org.apache.batik.transcoder.TranscodingHints;
/*     */ import org.apache.batik.transcoder.keys.BooleanKey;
/*     */ import org.apache.batik.transcoder.keys.PaintKey;
/*     */ import org.w3c.dom.Document;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class ImageTranscoder
/*     */   extends SVGAbstractTranscoder
/*     */ {
/*     */   protected void transcode(Document document, String uri, TranscoderOutput output) throws TranscoderException {
/*  92 */     super.transcode(document, uri, output);
/*     */ 
/*     */     
/*  95 */     int w = (int)(this.width + 0.5D);
/*  96 */     int h = (int)(this.height + 0.5D);
/*     */ 
/*     */ 
/*     */     
/* 100 */     ImageRenderer renderer = createRenderer();
/* 101 */     renderer.updateOffScreen(w, h);
/*     */     
/* 103 */     renderer.setTransform(this.curTxf);
/* 104 */     renderer.setTree(this.root);
/* 105 */     this.root = null;
/*     */ 
/*     */     
/*     */     try {
/* 109 */       Shape raoi = new Rectangle2D.Float(0.0F, 0.0F, this.width, this.height);
/*     */       
/* 111 */       renderer.repaint(this.curTxf.createInverse().createTransformedShape(raoi));
/*     */       
/* 113 */       BufferedImage rend = renderer.getOffScreen();
/* 114 */       renderer = null;
/*     */       
/* 116 */       BufferedImage dest = createImage(w, h);
/*     */       
/* 118 */       Graphics2D g2d = GraphicsUtil.createGraphics(dest);
/* 119 */       if (this.hints.containsKey(KEY_BACKGROUND_COLOR)) {
/* 120 */         Paint bgcolor = (Paint)this.hints.get(KEY_BACKGROUND_COLOR);
/* 121 */         g2d.setComposite(AlphaComposite.SrcOver);
/* 122 */         g2d.setPaint(bgcolor);
/* 123 */         g2d.fillRect(0, 0, w, h);
/*     */       } 
/* 125 */       if (rend != null) {
/* 126 */         g2d.drawRenderedImage(rend, new AffineTransform());
/*     */       }
/* 128 */       g2d.dispose();
/* 129 */       rend = null;
/* 130 */       writeImage(dest, output);
/* 131 */     } catch (Exception ex) {
/* 132 */       throw new TranscoderException(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected ImageRenderer createRenderer() {
/* 140 */     ConcreteImageRendererFactory concreteImageRendererFactory = new ConcreteImageRendererFactory();
/*     */     
/* 142 */     return concreteImageRendererFactory.createStaticImageRenderer();
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
/*     */   protected void forceTransparentWhite(BufferedImage img, SinglePixelPackedSampleModel sppsm) {
/* 158 */     int w = img.getWidth();
/* 159 */     int h = img.getHeight();
/* 160 */     DataBufferInt biDB = (DataBufferInt)img.getRaster().getDataBuffer();
/* 161 */     int scanStride = sppsm.getScanlineStride();
/* 162 */     int dbOffset = biDB.getOffset();
/* 163 */     int[] pixels = biDB.getBankData()[0];
/* 164 */     int p = dbOffset;
/* 165 */     int adjust = scanStride - w;
/* 166 */     int a = 0, r = 0, g = 0, b = 0, pel = 0;
/* 167 */     for (int i = 0; i < h; i++) {
/* 168 */       for (int j = 0; j < w; j++) {
/* 169 */         pel = pixels[p];
/* 170 */         a = pel >> 24 & 0xFF;
/* 171 */         r = pel >> 16 & 0xFF;
/* 172 */         g = pel >> 8 & 0xFF;
/* 173 */         b = pel & 0xFF;
/* 174 */         r = (255 * (255 - a) + a * r) / 255;
/* 175 */         g = (255 * (255 - a) + a * g) / 255;
/* 176 */         b = (255 * (255 - a) + a * b) / 255;
/* 177 */         pixels[p++] = a << 24 & 0xFF000000 | r << 16 & 0xFF0000 | g << 8 & 0xFF00 | b & 0xFF;
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 183 */       p += adjust;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 234 */   public static final TranscodingHints.Key KEY_BACKGROUND_COLOR = (TranscodingHints.Key)new PaintKey();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 276 */   public static final TranscodingHints.Key KEY_FORCE_TRANSPARENT_WHITE = (TranscodingHints.Key)new BooleanKey();
/*     */   
/*     */   public abstract BufferedImage createImage(int paramInt1, int paramInt2);
/*     */   
/*     */   public abstract void writeImage(BufferedImage paramBufferedImage, TranscoderOutput paramTranscoderOutput) throws TranscoderException;
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/image/ImageTranscoder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */