/*     */ package org.apache.pdfbox.rendering;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Composite;
/*     */ import java.awt.Font;
/*     */ import java.awt.FontMetrics;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.GraphicsConfiguration;
/*     */ import java.awt.Image;
/*     */ import java.awt.Paint;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.Stroke;
/*     */ import java.awt.font.FontRenderContext;
/*     */ import java.awt.font.GlyphVector;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.BufferedImageOp;
/*     */ import java.awt.image.DataBuffer;
/*     */ import java.awt.image.DataBufferInt;
/*     */ import java.awt.image.ImageObserver;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderableImage;
/*     */ import java.text.AttributedCharacterIterator;
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
/*     */ 
/*     */ class GroupGraphics
/*     */   extends Graphics2D
/*     */ {
/*     */   private final BufferedImage groupImage;
/*     */   private final BufferedImage groupAlphaImage;
/*     */   private final Graphics2D groupG2D;
/*     */   private final Graphics2D alphaG2D;
/*     */   
/*     */   GroupGraphics(BufferedImage groupImage, Graphics2D groupGraphics) {
/*  74 */     this.groupImage = groupImage;
/*  75 */     this.groupG2D = groupGraphics;
/*  76 */     this.groupAlphaImage = new BufferedImage(groupImage.getWidth(), groupImage.getHeight(), 2);
/*     */     
/*  78 */     this.alphaG2D = this.groupAlphaImage.createGraphics();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private GroupGraphics(BufferedImage groupImage, Graphics2D groupGraphics, BufferedImage groupAlphaImage, Graphics2D alphaGraphics) {
/*  84 */     this.groupImage = groupImage;
/*  85 */     this.groupG2D = groupGraphics;
/*  86 */     this.groupAlphaImage = groupAlphaImage;
/*  87 */     this.alphaG2D = alphaGraphics;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearRect(int x, int y, int width, int height) {
/*  93 */     this.groupG2D.clearRect(x, y, width, height);
/*  94 */     this.alphaG2D.clearRect(x, y, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clipRect(int x, int y, int width, int height) {
/* 100 */     this.groupG2D.clipRect(x, y, width, height);
/* 101 */     this.alphaG2D.clipRect(x, y, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void copyArea(int x, int y, int width, int height, int dx, int dy) {
/* 107 */     this.groupG2D.copyArea(x, y, width, height, dx, dy);
/* 108 */     this.alphaG2D.copyArea(x, y, width, height, dx, dy);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Graphics create() {
/* 114 */     Graphics g = this.groupG2D.create();
/* 115 */     Graphics a = this.alphaG2D.create();
/* 116 */     if (g instanceof Graphics2D && a instanceof Graphics2D)
/*     */     {
/* 118 */       return new GroupGraphics(this.groupImage, (Graphics2D)g, this.groupAlphaImage, (Graphics2D)a);
/*     */     }
/* 120 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 126 */     this.groupG2D.dispose();
/* 127 */     this.alphaG2D.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/* 133 */     this.groupG2D.drawArc(x, y, width, height, startAngle, arcAngle);
/* 134 */     this.alphaG2D.drawArc(x, y, width, height, startAngle, arcAngle);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, Color bgcolor, ImageObserver observer) {
/* 140 */     this.groupG2D.drawImage(img, x, y, bgcolor, observer);
/* 141 */     return this.alphaG2D.drawImage(img, x, y, bgcolor, observer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, ImageObserver observer) {
/* 147 */     this.groupG2D.drawImage(img, x, y, observer);
/* 148 */     return this.alphaG2D.drawImage(img, x, y, observer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, int width, int height, Color bgcolor, ImageObserver observer) {
/* 155 */     this.groupG2D.drawImage(img, x, y, width, height, bgcolor, observer);
/* 156 */     return this.alphaG2D.drawImage(img, x, y, width, height, bgcolor, observer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image img, int x, int y, int width, int height, ImageObserver observer) {
/* 162 */     this.groupG2D.drawImage(img, x, y, width, height, observer);
/* 163 */     return this.alphaG2D.drawImage(img, x, y, width, height, observer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, Color bgcolor, ImageObserver observer) {
/* 170 */     this.groupG2D.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
/* 171 */     return this.alphaG2D.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, bgcolor, observer);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image img, int dx1, int dy1, int dx2, int dy2, int sx1, int sy1, int sx2, int sy2, ImageObserver observer) {
/* 178 */     this.groupG2D.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
/* 179 */     return this.alphaG2D.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawLine(int x1, int y1, int x2, int y2) {
/* 185 */     this.groupG2D.drawLine(x1, y1, x2, y2);
/* 186 */     this.alphaG2D.drawLine(x1, y1, x2, y2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawOval(int x, int y, int width, int height) {
/* 192 */     this.groupG2D.drawOval(x, y, width, height);
/* 193 */     this.alphaG2D.drawOval(x, y, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawPolygon(int[] xPoints, int[] yPoints, int nPoints) {
/* 199 */     this.groupG2D.drawPolygon(xPoints, yPoints, nPoints);
/* 200 */     this.alphaG2D.drawPolygon(xPoints, yPoints, nPoints);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawPolyline(int[] xPoints, int[] yPoints, int nPoints) {
/* 206 */     this.groupG2D.drawPolyline(xPoints, yPoints, nPoints);
/* 207 */     this.alphaG2D.drawPolyline(xPoints, yPoints, nPoints);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
/* 213 */     this.groupG2D.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
/* 214 */     this.alphaG2D.drawRoundRect(x, y, width, height, arcWidth, arcHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawString(AttributedCharacterIterator iterator, int x, int y) {
/* 220 */     this.groupG2D.drawString(iterator, x, y);
/* 221 */     this.alphaG2D.drawString(iterator, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawString(String str, int x, int y) {
/* 227 */     this.groupG2D.drawString(str, x, y);
/* 228 */     this.alphaG2D.drawString(str, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillArc(int x, int y, int width, int height, int startAngle, int arcAngle) {
/* 234 */     this.groupG2D.fillArc(x, y, width, height, startAngle, arcAngle);
/* 235 */     this.alphaG2D.fillArc(x, y, width, height, startAngle, arcAngle);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillOval(int x, int y, int width, int height) {
/* 241 */     this.groupG2D.fillOval(x, y, width, height);
/* 242 */     this.alphaG2D.fillOval(x, y, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillPolygon(int[] xPoints, int[] yPoints, int nPoints) {
/* 248 */     this.groupG2D.fillPolygon(xPoints, yPoints, nPoints);
/* 249 */     this.alphaG2D.fillPolygon(xPoints, yPoints, nPoints);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillRect(int x, int y, int width, int height) {
/* 255 */     this.groupG2D.fillRect(x, y, width, height);
/* 256 */     this.alphaG2D.fillRect(x, y, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fillRoundRect(int x, int y, int width, int height, int arcWidth, int arcHeight) {
/* 262 */     this.groupG2D.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
/* 263 */     this.alphaG2D.fillRoundRect(x, y, width, height, arcWidth, arcHeight);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getClip() {
/* 269 */     return this.groupG2D.getClip();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle getClipBounds() {
/* 275 */     return this.groupG2D.getClipBounds();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getColor() {
/* 281 */     return this.groupG2D.getColor();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Font getFont() {
/* 287 */     return this.groupG2D.getFont();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FontMetrics getFontMetrics(Font f) {
/* 293 */     return this.groupG2D.getFontMetrics(f);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClip(int x, int y, int width, int height) {
/* 299 */     this.groupG2D.setClip(x, y, width, height);
/* 300 */     this.alphaG2D.setClip(x, y, width, height);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setClip(Shape clip) {
/* 306 */     this.groupG2D.setClip(clip);
/* 307 */     this.alphaG2D.setClip(clip);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setColor(Color c) {
/* 313 */     this.groupG2D.setColor(c);
/* 314 */     this.alphaG2D.setColor(c);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFont(Font font) {
/* 320 */     this.groupG2D.setFont(font);
/* 321 */     this.alphaG2D.setFont(font);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaintMode() {
/* 327 */     this.groupG2D.setPaintMode();
/* 328 */     this.alphaG2D.setPaintMode();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXORMode(Color c1) {
/* 334 */     this.groupG2D.setXORMode(c1);
/* 335 */     this.alphaG2D.setXORMode(c1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(int x, int y) {
/* 341 */     this.groupG2D.translate(x, y);
/* 342 */     this.alphaG2D.translate(x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void addRenderingHints(Map<?, ?> hints) {
/* 348 */     this.groupG2D.addRenderingHints(hints);
/* 349 */     this.alphaG2D.addRenderingHints(hints);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clip(Shape s) {
/* 355 */     this.groupG2D.clip(s);
/* 356 */     this.alphaG2D.clip(s);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Shape s) {
/* 362 */     this.groupG2D.draw(s);
/* 363 */     this.alphaG2D.draw(s);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawGlyphVector(GlyphVector g, float x, float y) {
/* 369 */     this.groupG2D.drawGlyphVector(g, x, y);
/* 370 */     this.alphaG2D.drawGlyphVector(g, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawImage(BufferedImage img, BufferedImageOp op, int x, int y) {
/* 376 */     this.groupG2D.drawImage(img, op, x, y);
/* 377 */     this.alphaG2D.drawImage(img, op, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean drawImage(Image img, AffineTransform xform, ImageObserver obs) {
/* 383 */     this.groupG2D.drawImage(img, xform, obs);
/* 384 */     return this.alphaG2D.drawImage(img, xform, obs);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawRenderableImage(RenderableImage img, AffineTransform xform) {
/* 390 */     this.groupG2D.drawRenderableImage(img, xform);
/* 391 */     this.alphaG2D.drawRenderableImage(img, xform);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawRenderedImage(RenderedImage img, AffineTransform xform) {
/* 397 */     this.groupG2D.drawRenderedImage(img, xform);
/* 398 */     this.alphaG2D.drawRenderedImage(img, xform);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawString(AttributedCharacterIterator iterator, float x, float y) {
/* 404 */     this.groupG2D.drawString(iterator, x, y);
/* 405 */     this.alphaG2D.drawString(iterator, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void drawString(String str, float x, float y) {
/* 411 */     this.groupG2D.drawString(str, x, y);
/* 412 */     this.alphaG2D.drawString(str, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void fill(Shape s) {
/* 418 */     this.groupG2D.fill(s);
/* 419 */     this.alphaG2D.fill(s);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Color getBackground() {
/* 425 */     return this.groupG2D.getBackground();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Composite getComposite() {
/* 431 */     return this.groupG2D.getComposite();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GraphicsConfiguration getDeviceConfiguration() {
/* 437 */     return this.groupG2D.getDeviceConfiguration();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public FontRenderContext getFontRenderContext() {
/* 443 */     return this.groupG2D.getFontRenderContext();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Paint getPaint() {
/* 449 */     return this.groupG2D.getPaint();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object getRenderingHint(RenderingHints.Key hintKey) {
/* 455 */     return this.groupG2D.getRenderingHint(hintKey);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public RenderingHints getRenderingHints() {
/* 461 */     return this.groupG2D.getRenderingHints();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Stroke getStroke() {
/* 467 */     return this.groupG2D.getStroke();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/* 473 */     return this.groupG2D.getTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hit(Rectangle rect, Shape s, boolean onStroke) {
/* 479 */     return this.groupG2D.hit(rect, s, onStroke);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotate(double theta) {
/* 485 */     this.groupG2D.rotate(theta);
/* 486 */     this.alphaG2D.rotate(theta);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void rotate(double theta, double x, double y) {
/* 492 */     this.groupG2D.rotate(theta, x, y);
/* 493 */     this.alphaG2D.rotate(theta, x, y);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void scale(double sx, double sy) {
/* 499 */     this.groupG2D.scale(sx, sy);
/* 500 */     this.alphaG2D.scale(sx, sy);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackground(Color color) {
/* 506 */     this.groupG2D.setBackground(color);
/* 507 */     this.alphaG2D.setBackground(color);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setComposite(Composite comp) {
/* 513 */     this.groupG2D.setComposite(comp);
/* 514 */     this.alphaG2D.setComposite(comp);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaint(Paint paint) {
/* 520 */     this.groupG2D.setPaint(paint);
/* 521 */     this.alphaG2D.setPaint(paint);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingHint(RenderingHints.Key hintKey, Object hintValue) {
/* 527 */     this.groupG2D.setRenderingHint(hintKey, hintValue);
/* 528 */     this.alphaG2D.setRenderingHint(hintKey, hintValue);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRenderingHints(Map<?, ?> hints) {
/* 534 */     this.groupG2D.setRenderingHints(hints);
/* 535 */     this.alphaG2D.setRenderingHints(hints);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStroke(Stroke s) {
/* 541 */     this.groupG2D.setStroke(s);
/* 542 */     this.alphaG2D.setStroke(s);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransform(AffineTransform tx) {
/* 548 */     this.groupG2D.setTransform(tx);
/* 549 */     this.alphaG2D.setTransform(tx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shear(double shx, double shy) {
/* 555 */     this.groupG2D.shear(shx, shy);
/* 556 */     this.alphaG2D.shear(shx, shy);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void transform(AffineTransform tx) {
/* 562 */     this.groupG2D.transform(tx);
/* 563 */     this.alphaG2D.transform(tx);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void translate(double tx, double ty) {
/* 569 */     this.groupG2D.translate(tx, ty);
/* 570 */     this.alphaG2D.translate(tx, ty);
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
/*     */   void removeBackdrop(BufferedImage backdrop, int offsetX, int offsetY) {
/* 600 */     int groupWidth = this.groupImage.getWidth();
/* 601 */     int groupHeight = this.groupImage.getHeight();
/* 602 */     int backdropWidth = backdrop.getWidth();
/* 603 */     int backdropHeight = backdrop.getHeight();
/* 604 */     int groupType = this.groupImage.getType();
/* 605 */     int groupAlphaType = this.groupAlphaImage.getType();
/* 606 */     int backdropType = backdrop.getType();
/* 607 */     DataBuffer groupDataBuffer = this.groupImage.getRaster().getDataBuffer();
/* 608 */     DataBuffer groupAlphaDataBuffer = this.groupAlphaImage.getRaster().getDataBuffer();
/* 609 */     DataBuffer backdropDataBuffer = backdrop.getRaster().getDataBuffer();
/*     */     
/* 611 */     if (groupType == 2 && groupAlphaType == 2 && (backdropType == 2 || backdropType == 1) && groupDataBuffer instanceof DataBufferInt && groupAlphaDataBuffer instanceof DataBufferInt && backdropDataBuffer instanceof DataBufferInt) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 620 */       int[] groupData = ((DataBufferInt)groupDataBuffer).getData();
/* 621 */       int[] groupAlphaData = ((DataBufferInt)groupAlphaDataBuffer).getData();
/* 622 */       int[] backdropData = ((DataBufferInt)backdropDataBuffer).getData();
/* 623 */       boolean backdropHasAlpha = (backdropType == 2);
/*     */       
/* 625 */       for (int y = 0; y < groupHeight; y++) {
/*     */         
/* 627 */         for (int x = 0; x < groupWidth; x++)
/*     */         {
/* 629 */           int index = x + y * groupWidth;
/*     */ 
/*     */           
/* 632 */           int alphagn = groupAlphaData[index] >> 24 & 0xFF;
/* 633 */           if (alphagn == 0) {
/*     */ 
/*     */             
/* 636 */             groupData[index] = 0;
/*     */           } else {
/*     */             int backdropRGB;
/*     */             float alpha0;
/* 640 */             int backdropX = x + offsetX;
/* 641 */             int backdropY = y + offsetY;
/*     */ 
/*     */ 
/*     */             
/* 645 */             if (backdropX >= 0 && backdropX < backdropWidth && backdropY >= 0 && backdropY < backdropHeight) {
/*     */ 
/*     */               
/* 648 */               backdropRGB = backdropData[backdropX + backdropY * backdropWidth];
/* 649 */               alpha0 = backdropHasAlpha ? (backdropRGB >> 24 & 0xFF) : 255.0F;
/*     */             
/*     */             }
/*     */             else {
/*     */               
/* 654 */               backdropRGB = 0;
/* 655 */               alpha0 = 0.0F;
/*     */             } 
/*     */ 
/*     */             
/* 659 */             float alphaFactor = alpha0 / alphagn - alpha0 / 255.0F;
/* 660 */             int groupRGB = groupData[index];
/*     */ 
/*     */             
/* 663 */             int r = backdropRemoval(groupRGB, backdropRGB, 16, alphaFactor);
/* 664 */             int g = backdropRemoval(groupRGB, backdropRGB, 8, alphaFactor);
/* 665 */             int b = backdropRemoval(groupRGB, backdropRGB, 0, alphaFactor);
/*     */ 
/*     */ 
/*     */             
/* 669 */             groupData[index] = alphagn << 24 | r << 16 | g << 8 | b;
/*     */           }
/*     */         
/*     */         }
/*     */       
/*     */       } 
/*     */     } else {
/*     */       
/* 677 */       for (int y = 0; y < groupHeight; y++) {
/*     */         
/* 679 */         for (int x = 0; x < groupWidth; x++) {
/*     */           
/* 681 */           int alphagn = this.groupAlphaImage.getRGB(x, y) >> 24 & 0xFF;
/* 682 */           if (alphagn == 0) {
/*     */             
/* 684 */             this.groupImage.setRGB(x, y, 0);
/*     */           } else {
/*     */             int backdropRGB;
/*     */             float alpha0;
/* 688 */             int backdropX = x + offsetX;
/* 689 */             int backdropY = y + offsetY;
/*     */ 
/*     */             
/* 692 */             if (backdropX >= 0 && backdropX < backdropWidth && backdropY >= 0 && backdropY < backdropHeight) {
/*     */ 
/*     */               
/* 695 */               backdropRGB = backdrop.getRGB(backdropX, backdropY);
/* 696 */               alpha0 = (backdropRGB >> 24 & 0xFF);
/*     */             }
/*     */             else {
/*     */               
/* 700 */               backdropRGB = 0;
/* 701 */               alpha0 = 0.0F;
/*     */             } 
/*     */             
/* 704 */             int groupRGB = this.groupImage.getRGB(x, y);
/* 705 */             float alphaFactor = alpha0 / alphagn - alpha0 / 255.0F;
/*     */             
/* 707 */             int r = backdropRemoval(groupRGB, backdropRGB, 16, alphaFactor);
/* 708 */             int g = backdropRemoval(groupRGB, backdropRGB, 8, alphaFactor);
/* 709 */             int b = backdropRemoval(groupRGB, backdropRGB, 0, alphaFactor);
/*     */             
/* 711 */             this.groupImage.setRGB(x, y, alphagn << 24 | r << 16 | g << 8 | b);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int backdropRemoval(int groupRGB, int backdropRGB, int shift, float alphaFactor) {
/* 723 */     float cn = (groupRGB >> shift & 0xFF);
/* 724 */     float c0 = (backdropRGB >> shift & 0xFF);
/* 725 */     int c = Math.round(cn + (cn - c0) * alphaFactor);
/* 726 */     return (c < 0) ? 0 : ((c > 255) ? 255 : c);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/rendering/GroupGraphics.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */