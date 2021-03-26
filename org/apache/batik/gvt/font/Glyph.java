/*     */ package org.apache.batik.gvt.font;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.text.TextPaintInfo;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Glyph
/*     */ {
/*     */   private String unicode;
/*     */   private Vector names;
/*     */   private String orientation;
/*     */   private String arabicForm;
/*     */   private String lang;
/*     */   private Point2D horizOrigin;
/*     */   private Point2D vertOrigin;
/*     */   private float horizAdvX;
/*     */   private float vertAdvY;
/*     */   private int glyphCode;
/*     */   private AffineTransform transform;
/*     */   private Point2D.Float position;
/*     */   private GVTGlyphMetrics metrics;
/*     */   private Shape outline;
/*     */   private Rectangle2D bounds;
/*     */   private TextPaintInfo tpi;
/*     */   private TextPaintInfo cacheTPI;
/*     */   private Shape dShape;
/*     */   private GraphicsNode glyphChildrenNode;
/*     */   
/*     */   public Glyph(String unicode, List<?> names, String orientation, String arabicForm, String lang, Point2D horizOrigin, Point2D vertOrigin, float horizAdvX, float vertAdvY, int glyphCode, TextPaintInfo tpi, Shape dShape, GraphicsNode glyphChildrenNode) {
/*  77 */     if (unicode == null) {
/*  78 */       throw new IllegalArgumentException();
/*     */     }
/*  80 */     if (horizOrigin == null) {
/*  81 */       throw new IllegalArgumentException();
/*     */     }
/*  83 */     if (vertOrigin == null) {
/*  84 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  87 */     this.unicode = unicode;
/*  88 */     this.names = new Vector(names);
/*  89 */     this.orientation = orientation;
/*  90 */     this.arabicForm = arabicForm;
/*  91 */     this.lang = lang;
/*  92 */     this.horizOrigin = horizOrigin;
/*  93 */     this.vertOrigin = vertOrigin;
/*  94 */     this.horizAdvX = horizAdvX;
/*  95 */     this.vertAdvY = vertAdvY;
/*  96 */     this.glyphCode = glyphCode;
/*  97 */     this.position = new Point2D.Float(0.0F, 0.0F);
/*  98 */     this.outline = null;
/*  99 */     this.bounds = null;
/*     */ 
/*     */     
/* 102 */     this.tpi = tpi;
/* 103 */     this.dShape = dShape;
/* 104 */     this.glyphChildrenNode = glyphChildrenNode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUnicode() {
/* 113 */     return this.unicode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Vector getNames() {
/* 122 */     return this.names;
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
/*     */   public String getOrientation() {
/* 134 */     return this.orientation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getArabicForm() {
/* 144 */     return this.arabicForm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLang() {
/* 153 */     return this.lang;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getHorizOrigin() {
/* 162 */     return this.horizOrigin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getVertOrigin() {
/* 171 */     return this.vertOrigin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHorizAdvX() {
/* 180 */     return this.horizAdvX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getVertAdvY() {
/* 189 */     return this.vertAdvY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getGlyphCode() {
/* 199 */     return this.glyphCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getTransform() {
/* 208 */     return this.transform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTransform(AffineTransform transform) {
/* 217 */     this.transform = transform;
/* 218 */     this.outline = null;
/* 219 */     this.bounds = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Point2D getPosition() {
/* 228 */     return this.position;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPosition(Point2D position) {
/* 237 */     this.position.x = (float)position.getX();
/* 238 */     this.position.y = (float)position.getY();
/* 239 */     this.outline = null;
/* 240 */     this.bounds = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GVTGlyphMetrics getGlyphMetrics() {
/* 249 */     if (this.metrics == null) {
/* 250 */       Rectangle2D gb = getGeometryBounds();
/*     */       
/* 252 */       this.metrics = new GVTGlyphMetrics(getHorizAdvX(), getVertAdvY(), new Rectangle2D.Double(gb.getX() - this.position.getX(), gb.getY() - this.position.getY(), gb.getWidth(), gb.getHeight()), (byte)3);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     return this.metrics;
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
/*     */   public GVTGlyphMetrics getGlyphMetrics(float hkern, float vkern) {
/* 274 */     return new GVTGlyphMetrics(getHorizAdvX() - hkern, getVertAdvY() - vkern, getGeometryBounds(), (byte)3);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getGeometryBounds() {
/* 282 */     return getOutline().getBounds2D();
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 287 */     if (this.bounds != null && TextPaintInfo.equivilent(this.tpi, this.cacheTPI))
/*     */     {
/* 289 */       return this.bounds;
/*     */     }
/* 291 */     AffineTransform tr = AffineTransform.getTranslateInstance(this.position.getX(), this.position.getY());
/*     */ 
/*     */     
/* 294 */     if (this.transform != null) {
/* 295 */       tr.concatenate(this.transform);
/*     */     }
/*     */     
/* 298 */     Rectangle2D bounds = null;
/* 299 */     if (this.dShape != null && this.tpi != null) {
/* 300 */       if (this.tpi.fillPaint != null) {
/* 301 */         bounds = tr.createTransformedShape(this.dShape).getBounds2D();
/*     */       }
/* 303 */       if (this.tpi.strokeStroke != null && this.tpi.strokePaint != null) {
/* 304 */         Shape s = this.tpi.strokeStroke.createStrokedShape(this.dShape);
/* 305 */         Rectangle2D r = tr.createTransformedShape(s).getBounds2D();
/* 306 */         if (bounds == null) { bounds = r; }
/*     */         else
/* 308 */         { bounds.add(r); }
/*     */       
/*     */       } 
/*     */     } 
/* 312 */     if (this.glyphChildrenNode != null) {
/* 313 */       Rectangle2D r = this.glyphChildrenNode.getTransformedBounds(tr);
/* 314 */       if (bounds == null) { bounds = r; }
/*     */       else
/* 316 */       { bounds.add(r); }
/*     */     
/* 318 */     }  if (bounds == null) {
/* 319 */       bounds = new Rectangle2D.Double(this.position.getX(), this.position.getY(), 0.0D, 0.0D);
/*     */     }
/*     */     
/* 322 */     this.cacheTPI = new TextPaintInfo(this.tpi);
/* 323 */     return bounds;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Shape getOutline() {
/* 333 */     if (this.outline == null) {
/* 334 */       AffineTransform tr = AffineTransform.getTranslateInstance(this.position.getX(), this.position.getY());
/*     */ 
/*     */       
/* 337 */       if (this.transform != null) {
/* 338 */         tr.concatenate(this.transform);
/*     */       }
/* 340 */       Shape glyphChildrenOutline = null;
/* 341 */       if (this.glyphChildrenNode != null) {
/* 342 */         glyphChildrenOutline = this.glyphChildrenNode.getOutline();
/*     */       }
/* 344 */       GeneralPath glyphOutline = null;
/* 345 */       if (this.dShape != null && glyphChildrenOutline != null) {
/* 346 */         glyphOutline = new GeneralPath(this.dShape);
/* 347 */         glyphOutline.append(glyphChildrenOutline, false);
/* 348 */       } else if (this.dShape != null && glyphChildrenOutline == null) {
/* 349 */         glyphOutline = new GeneralPath(this.dShape);
/* 350 */       } else if (this.dShape == null && glyphChildrenOutline != null) {
/* 351 */         glyphOutline = new GeneralPath(glyphChildrenOutline);
/*     */       } else {
/*     */         
/* 354 */         glyphOutline = new GeneralPath();
/*     */       } 
/* 356 */       this.outline = tr.createTransformedShape(glyphOutline);
/*     */     } 
/* 358 */     return this.outline;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void draw(Graphics2D graphics2D) {
/* 367 */     AffineTransform tr = AffineTransform.getTranslateInstance(this.position.getX(), this.position.getY());
/*     */ 
/*     */     
/* 370 */     if (this.transform != null) {
/* 371 */       tr.concatenate(this.transform);
/*     */     }
/*     */ 
/*     */     
/* 375 */     if (this.dShape != null && this.tpi != null) {
/* 376 */       Shape tShape = tr.createTransformedShape(this.dShape);
/* 377 */       if (this.tpi.fillPaint != null) {
/* 378 */         graphics2D.setPaint(this.tpi.fillPaint);
/* 379 */         graphics2D.fill(tShape);
/*     */       } 
/*     */ 
/*     */       
/* 383 */       if (this.tpi.strokeStroke != null && this.tpi.strokePaint != null) {
/* 384 */         graphics2D.setStroke(this.tpi.strokeStroke);
/* 385 */         graphics2D.setPaint(this.tpi.strokePaint);
/* 386 */         graphics2D.draw(tShape);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 391 */     if (this.glyphChildrenNode != null) {
/* 392 */       this.glyphChildrenNode.setTransform(tr);
/* 393 */       this.glyphChildrenNode.paint(graphics2D);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/font/Glyph.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */