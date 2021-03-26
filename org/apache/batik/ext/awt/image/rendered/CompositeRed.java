/*     */ package org.apache.batik.ext.awt.image.rendered;
/*     */ 
/*     */ import java.awt.CompositeContext;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Point;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.color.ColorSpace;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.ColorModel;
/*     */ import java.awt.image.DirectColorModel;
/*     */ import java.awt.image.Raster;
/*     */ import java.awt.image.SampleModel;
/*     */ import java.awt.image.WritableRaster;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.batik.ext.awt.image.CompositeRule;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.SVGComposite;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CompositeRed
/*     */   extends AbstractRed
/*     */ {
/*     */   CompositeRule rule;
/*     */   CompositeContext[] contexts;
/*     */   
/*     */   public CompositeRed(List<CachableRed> srcs, CompositeRule rule) {
/*  58 */     CachableRed src = srcs.get(0);
/*     */     
/*  60 */     ColorModel cm = fixColorModel(src);
/*     */     
/*  62 */     this.rule = rule;
/*     */     
/*  64 */     SVGComposite comp = new SVGComposite(rule);
/*  65 */     this.contexts = new CompositeContext[srcs.size()];
/*     */     
/*  67 */     int idx = 0;
/*  68 */     Iterator<CachableRed> i = srcs.iterator();
/*  69 */     Rectangle myBounds = null;
/*  70 */     while (i.hasNext()) {
/*  71 */       CachableRed cr = i.next();
/*     */       
/*  73 */       this.contexts[idx++] = comp.createContext(cr.getColorModel(), cm, null);
/*     */       
/*  75 */       Rectangle newBound = cr.getBounds();
/*  76 */       if (myBounds == null) {
/*  77 */         myBounds = newBound;
/*     */         
/*     */         continue;
/*     */       } 
/*  81 */       switch (rule.getRule()) {
/*     */         case 2:
/*  83 */           if (myBounds.intersects(newBound)) {
/*  84 */             myBounds = myBounds.intersection(newBound); continue;
/*     */           } 
/*  86 */           myBounds.width = 0;
/*  87 */           myBounds.height = 0;
/*     */           continue;
/*     */ 
/*     */         
/*     */         case 3:
/*  92 */           myBounds = newBound;
/*     */           continue;
/*     */       } 
/*     */       
/*  96 */       myBounds.add(newBound);
/*     */     } 
/*     */ 
/*     */     
/* 100 */     if (myBounds == null) {
/* 101 */       throw new IllegalArgumentException("Composite Operation Must have some source!");
/*     */     }
/*     */     
/* 104 */     if (rule.getRule() == 6) {
/* 105 */       List<CachableRed> vec = new ArrayList(srcs.size());
/* 106 */       i = srcs.iterator();
/* 107 */       while (i.hasNext()) {
/* 108 */         CachableRed cr = i.next();
/* 109 */         Rectangle r = cr.getBounds();
/*     */         
/* 111 */         if (r.x != myBounds.x || r.y != myBounds.y || r.width != myBounds.width || r.height != myBounds.height)
/*     */         {
/*     */ 
/*     */           
/* 115 */           cr = new PadRed(cr, myBounds, PadMode.ZERO_PAD, null); } 
/* 116 */         vec.add(cr);
/*     */       } 
/* 118 */       srcs = vec;
/*     */     } 
/*     */ 
/*     */     
/* 122 */     SampleModel sm = fixSampleModel(src, cm, myBounds);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     int defSz = AbstractTiledRed.getDefaultTileSize();
/*     */ 
/*     */     
/* 130 */     int tgX = defSz * (int)Math.floor((myBounds.x / defSz));
/* 131 */     int tgY = defSz * (int)Math.floor((myBounds.y / defSz));
/*     */ 
/*     */     
/* 134 */     init(srcs, myBounds, cm, sm, tgX, tgY, (Map)null);
/*     */   }
/*     */ 
/*     */   
/*     */   public WritableRaster copyData(WritableRaster wr) {
/* 139 */     genRect(wr);
/* 140 */     return wr;
/*     */   }
/*     */   
/*     */   public Raster getTile(int x, int y) {
/* 144 */     int tx = this.tileGridXOff + x * this.tileWidth;
/* 145 */     int ty = this.tileGridYOff + y * this.tileHeight;
/* 146 */     Point pt = new Point(tx, ty);
/* 147 */     WritableRaster wr = Raster.createWritableRaster(this.sm, pt);
/* 148 */     genRect(wr);
/*     */     
/* 150 */     return wr;
/*     */   }
/*     */   
/*     */   public void emptyRect(WritableRaster wr) {
/* 154 */     PadRed.ZeroRecter zr = PadRed.ZeroRecter.getZeroRecter(wr);
/* 155 */     zr.zeroRect(new Rectangle(wr.getMinX(), wr.getMinY(), wr.getWidth(), wr.getHeight()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void genRect(WritableRaster wr) {
/* 162 */     Rectangle r = wr.getBounds();
/*     */     
/* 164 */     int idx = 0;
/* 165 */     Iterator<CachableRed> i = this.srcs.iterator();
/* 166 */     boolean first = true;
/* 167 */     while (i.hasNext()) {
/* 168 */       CachableRed cr = i.next();
/* 169 */       if (first) {
/* 170 */         Rectangle crR = cr.getBounds();
/* 171 */         if (r.x < crR.x || r.y < crR.y || r.x + r.width > crR.x + crR.width || r.y + r.height > crR.y + crR.height)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 176 */           emptyRect(wr);
/*     */         }
/*     */         
/* 179 */         cr.copyData(wr);
/*     */         
/* 181 */         if (!cr.getColorModel().isAlphaPremultiplied())
/* 182 */           GraphicsUtil.coerceData(wr, cr.getColorModel(), true); 
/* 183 */         first = false;
/*     */       } else {
/* 185 */         Rectangle crR = cr.getBounds();
/* 186 */         if (crR.intersects(r)) {
/* 187 */           Rectangle smR = crR.intersection(r);
/* 188 */           Raster ras = cr.getData(smR);
/* 189 */           WritableRaster smWR = wr.createWritableChild(smR.x, smR.y, smR.width, smR.height, smR.x, smR.y, (int[])null);
/*     */ 
/*     */ 
/*     */           
/* 193 */           this.contexts[idx].compose(ras, smWR, smWR);
/*     */         } 
/*     */       } 
/*     */       
/* 197 */       idx++;
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
/*     */   public void genRect_OVER(WritableRaster wr) {
/* 209 */     Rectangle r = wr.getBounds();
/*     */     
/* 211 */     ColorModel cm = getColorModel();
/*     */     
/* 213 */     BufferedImage bi = new BufferedImage(cm, wr.createWritableTranslatedChild(0, 0), cm.isAlphaPremultiplied(), null);
/*     */ 
/*     */ 
/*     */     
/* 217 */     Graphics2D g2d = GraphicsUtil.createGraphics(bi);
/* 218 */     g2d.translate(-r.x, -r.y);
/*     */     
/* 220 */     Iterator<CachableRed> i = this.srcs.iterator();
/* 221 */     boolean first = true;
/* 222 */     while (i.hasNext()) {
/* 223 */       CachableRed cr = i.next();
/* 224 */       if (first) {
/* 225 */         Rectangle crR = cr.getBounds();
/* 226 */         if (r.x < crR.x || r.y < crR.y || r.x + r.width > crR.x + crR.width || r.y + r.height > crR.y + crR.height)
/*     */         {
/*     */ 
/*     */ 
/*     */           
/* 231 */           emptyRect(wr);
/*     */         }
/*     */         
/* 234 */         cr.copyData(wr);
/*     */         
/* 236 */         GraphicsUtil.coerceData(wr, cr.getColorModel(), cm.isAlphaPremultiplied());
/*     */         
/* 238 */         first = false; continue;
/*     */       } 
/* 240 */       GraphicsUtil.drawImage(g2d, cr);
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
/*     */   protected static SampleModel fixSampleModel(CachableRed src, ColorModel cm, Rectangle bounds) {
/* 255 */     int defSz = AbstractTiledRed.getDefaultTileSize();
/*     */ 
/*     */     
/* 258 */     int tgX = defSz * (int)Math.floor((bounds.x / defSz));
/* 259 */     int tgY = defSz * (int)Math.floor((bounds.y / defSz));
/*     */     
/* 261 */     int tw = bounds.x + bounds.width - tgX;
/* 262 */     int th = bounds.y + bounds.height - tgY;
/*     */     
/* 264 */     SampleModel sm = src.getSampleModel();
/*     */     
/* 266 */     int w = sm.getWidth();
/* 267 */     if (w < defSz) w = defSz; 
/* 268 */     if (w > tw) w = tw;
/*     */     
/* 270 */     int h = sm.getHeight();
/* 271 */     if (h < defSz) h = defSz; 
/* 272 */     if (h > th) h = th;
/*     */     
/* 274 */     if (w <= 0 || h <= 0) {
/* 275 */       w = 1;
/* 276 */       h = 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 283 */     return cm.createCompatibleSampleModel(w, h);
/*     */   }
/*     */   
/*     */   protected static ColorModel fixColorModel(CachableRed src) {
/* 287 */     ColorModel cm = src.getColorModel();
/*     */     
/* 289 */     if (cm.hasAlpha()) {
/* 290 */       if (!cm.isAlphaPremultiplied())
/* 291 */         cm = GraphicsUtil.coerceColorModel(cm, true); 
/* 292 */       return cm;
/*     */     } 
/*     */     
/* 295 */     int b = src.getSampleModel().getNumBands() + 1;
/* 296 */     if (b > 4) {
/* 297 */       throw new IllegalArgumentException("CompositeRed can only handle up to three band images");
/*     */     }
/*     */     
/* 300 */     int[] masks = new int[4];
/* 301 */     for (int i = 0; i < b - 1; i++)
/* 302 */       masks[i] = 16711680 >> 8 * i; 
/* 303 */     masks[3] = 255 << 8 * (b - 1);
/* 304 */     ColorSpace cs = cm.getColorSpace();
/*     */     
/* 306 */     return new DirectColorModel(cs, 8 * b, masks[0], masks[1], masks[2], masks[3], true, 3);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/rendered/CompositeRed.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */