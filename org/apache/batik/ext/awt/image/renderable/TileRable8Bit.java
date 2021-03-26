/*     */ package org.apache.batik.ext.awt.image.renderable;
/*     */ 
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.BufferedImage;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import org.apache.batik.ext.awt.RenderingHintsKeyExt;
/*     */ import org.apache.batik.ext.awt.image.GraphicsUtil;
/*     */ import org.apache.batik.ext.awt.image.rendered.AffineRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.BufferedImageCachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.CachableRed;
/*     */ import org.apache.batik.ext.awt.image.rendered.TileRed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileRable8Bit
/*     */   extends AbstractColorInterpolationRable
/*     */   implements TileRable
/*     */ {
/*     */   private Rectangle2D tileRegion;
/*     */   private Rectangle2D tiledRegion;
/*     */   private boolean overflow;
/*     */   
/*     */   public Rectangle2D getTileRegion() {
/*  68 */     return this.tileRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTileRegion(Rectangle2D tileRegion) {
/*  75 */     if (tileRegion == null) {
/*  76 */       throw new IllegalArgumentException();
/*     */     }
/*  78 */     touch();
/*  79 */     this.tileRegion = tileRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getTiledRegion() {
/*  86 */     return this.tiledRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTiledRegion(Rectangle2D tiledRegion) {
/*  93 */     if (tiledRegion == null) {
/*  94 */       throw new IllegalArgumentException();
/*     */     }
/*  96 */     touch();
/*  97 */     this.tiledRegion = tiledRegion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOverflow() {
/* 104 */     return this.overflow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOverflow(boolean overflow) {
/* 111 */     touch();
/* 112 */     this.overflow = overflow;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public TileRable8Bit(Filter source, Rectangle2D tiledRegion, Rectangle2D tileRegion, boolean overflow) {
/* 122 */     super(source);
/*     */     
/* 124 */     setTileRegion(tileRegion);
/* 125 */     setTiledRegion(tiledRegion);
/* 126 */     setOverflow(overflow);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSource(Filter src) {
/* 133 */     init(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Filter getSource() {
/* 140 */     return this.srcs.get(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 147 */     return (Rectangle2D)this.tiledRegion.clone();
/*     */   }
/*     */   public RenderedImage createRendering(RenderContext rc) {
/*     */     Rectangle2D aoiRect, srcRect;
/*     */     AffineRed affineRed;
/* 152 */     RenderingHints rh = rc.getRenderingHints();
/* 153 */     if (rh == null) rh = new RenderingHints(null);
/*     */ 
/*     */     
/* 156 */     AffineTransform at = rc.getTransform();
/*     */     
/* 158 */     double sx = at.getScaleX();
/* 159 */     double sy = at.getScaleY();
/*     */     
/* 161 */     double shx = at.getShearX();
/* 162 */     double shy = at.getShearY();
/*     */     
/* 164 */     double tx = at.getTranslateX();
/* 165 */     double ty = at.getTranslateY();
/*     */ 
/*     */     
/* 168 */     double scaleX = Math.sqrt(sx * sx + shy * shy);
/* 169 */     double scaleY = Math.sqrt(sy * sy + shx * shx);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 181 */     Rectangle2D tiledRect = getBounds2D();
/*     */     
/* 183 */     Shape aoiShape = rc.getAreaOfInterest();
/* 184 */     if (aoiShape == null) {
/* 185 */       aoiRect = tiledRect;
/*     */     } else {
/* 187 */       aoiRect = aoiShape.getBounds2D();
/*     */       
/* 189 */       if (!tiledRect.intersects(aoiRect))
/* 190 */         return null; 
/* 191 */       Rectangle2D.intersect(tiledRect, aoiRect, tiledRect);
/*     */     } 
/*     */ 
/*     */     
/* 195 */     Rectangle2D tileRect = this.tileRegion;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 200 */     int dw = (int)Math.ceil(tileRect.getWidth() * scaleX);
/* 201 */     int dh = (int)Math.ceil(tileRect.getHeight() * scaleY);
/*     */     
/* 203 */     double tileScaleX = dw / tileRect.getWidth();
/* 204 */     double tileScaleY = dh / tileRect.getHeight();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 211 */     int dx = (int)Math.floor(tileRect.getX() * tileScaleX);
/* 212 */     int dy = (int)Math.floor(tileRect.getY() * tileScaleY);
/*     */     
/* 214 */     double ttx = dx - tileRect.getX() * tileScaleX;
/* 215 */     double tty = dy - tileRect.getY() * tileScaleY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     AffineTransform tileAt = AffineTransform.getTranslateInstance(ttx, tty);
/* 229 */     tileAt.scale(tileScaleX, tileScaleY);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 239 */     Filter source = getSource();
/*     */ 
/*     */     
/* 242 */     if (this.overflow) {
/* 243 */       srcRect = source.getBounds2D();
/*     */     } else {
/* 245 */       srcRect = tileRect;
/*     */     } 
/*     */ 
/*     */     
/* 249 */     RenderContext tileRc = new RenderContext(tileAt, srcRect, rh);
/*     */     
/* 251 */     RenderedImage tileRed = source.createRendering(tileRc);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 259 */     if (tileRed == null) {
/* 260 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 265 */     Rectangle tiledArea = tileAt.createTransformedShape(aoiRect).getBounds();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 278 */     if (tiledArea.width == Integer.MAX_VALUE || tiledArea.height == Integer.MAX_VALUE)
/*     */     {
/* 280 */       tiledArea = new Rectangle(-536870912, -536870912, 1073741823, 1073741823);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 286 */     CachableRed cachableRed = convertSourceCS(tileRed);
/* 287 */     TileRed tiledRed = new TileRed((RenderedImage)cachableRed, tiledArea, dw, dh);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 293 */     AffineTransform shearAt = new AffineTransform(sx / scaleX, shy / scaleX, shx / scaleY, sy / scaleY, tx, ty);
/*     */ 
/*     */ 
/*     */     
/* 297 */     shearAt.scale(scaleX / tileScaleX, scaleY / tileScaleY);
/*     */     
/* 299 */     shearAt.translate(-ttx, -tty);
/*     */     
/* 301 */     TileRed tileRed1 = tiledRed;
/* 302 */     if (!shearAt.isIdentity()) {
/* 303 */       affineRed = new AffineRed((CachableRed)tiledRed, shearAt, rh);
/*     */     }
/*     */ 
/*     */     
/* 307 */     return (RenderedImage)affineRed;
/*     */   }
/*     */ 
/*     */   
/*     */   public Rectangle2D getActualTileBounds(Rectangle2D tiledRect) {
/* 312 */     Rectangle2D tileRect = (Rectangle2D)this.tileRegion.clone();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 317 */     if (tileRect.getWidth() <= 0.0D || tileRect.getHeight() <= 0.0D || tiledRect.getWidth() <= 0.0D || tiledRect.getHeight() <= 0.0D)
/*     */     {
/*     */ 
/*     */       
/* 321 */       return null;
/*     */     }
/*     */     
/* 324 */     double tileWidth = tileRect.getWidth();
/* 325 */     double tileHeight = tileRect.getHeight();
/*     */     
/* 327 */     double tiledWidth = tiledRect.getWidth();
/* 328 */     double tiledHeight = tiledRect.getHeight();
/*     */     
/* 330 */     double w = Math.min(tileWidth, tiledWidth);
/* 331 */     double h = Math.min(tileHeight, tiledHeight);
/*     */     
/* 333 */     Rectangle2D realTileRect = new Rectangle2D.Double(tileRect.getX(), tileRect.getY(), w, h);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 338 */     return realTileRect;
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
/*     */   public RenderedImage createTile(RenderContext rc) {
/* 357 */     AffineTransform usr2dev = rc.getTransform();
/*     */ 
/*     */     
/* 360 */     RenderingHints rcHints = rc.getRenderingHints();
/* 361 */     RenderingHints hints = new RenderingHints(null);
/* 362 */     if (rcHints != null) {
/* 363 */       hints.add(rcHints);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 368 */     Rectangle2D tiledRect = getBounds2D();
/* 369 */     Shape aoiShape = rc.getAreaOfInterest();
/* 370 */     Rectangle2D aoiRect = aoiShape.getBounds2D();
/* 371 */     if (!tiledRect.intersects(aoiRect))
/* 372 */       return null; 
/* 373 */     Rectangle2D.intersect(tiledRect, aoiRect, tiledRect);
/*     */ 
/*     */     
/* 376 */     Rectangle2D tileRect = (Rectangle2D)this.tileRegion.clone();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 381 */     if (tileRect.getWidth() <= 0.0D || tileRect.getHeight() <= 0.0D || tiledRect.getWidth() <= 0.0D || tiledRect.getHeight() <= 0.0D)
/*     */     {
/*     */ 
/*     */       
/* 385 */       return null;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 426 */     double tileX = tileRect.getX();
/* 427 */     double tileY = tileRect.getY();
/* 428 */     double tileWidth = tileRect.getWidth();
/* 429 */     double tileHeight = tileRect.getHeight();
/*     */     
/* 431 */     double tiledX = tiledRect.getX();
/* 432 */     double tiledY = tiledRect.getY();
/* 433 */     double tiledWidth = tiledRect.getWidth();
/* 434 */     double tiledHeight = tiledRect.getHeight();
/*     */     
/* 436 */     double w = Math.min(tileWidth, tiledWidth);
/* 437 */     double h = Math.min(tileHeight, tiledHeight);
/* 438 */     double dx = (tiledX - tileX) % tileWidth;
/* 439 */     double dy = (tiledY - tileY) % tileHeight;
/*     */     
/* 441 */     if (dx > 0.0D) {
/* 442 */       dx = tileWidth - dx;
/*     */     } else {
/*     */       
/* 445 */       dx *= -1.0D;
/*     */     } 
/*     */     
/* 448 */     if (dy > 0.0D) {
/* 449 */       dy = tileHeight - dy;
/*     */     } else {
/*     */       
/* 452 */       dy *= -1.0D;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 458 */     double scaleX = usr2dev.getScaleX();
/* 459 */     double scaleY = usr2dev.getScaleY();
/* 460 */     double tdx = Math.floor(scaleX * dx);
/* 461 */     double tdy = Math.floor(scaleY * dy);
/*     */     
/* 463 */     dx = tdx / scaleX;
/* 464 */     dy = tdy / scaleY;
/*     */ 
/*     */ 
/*     */     
/* 468 */     Rectangle2D.Double A = new Rectangle2D.Double(tileX + tileWidth - dx, tileY + tileHeight - dy, dx, dy);
/*     */     
/* 470 */     Rectangle2D.Double B = new Rectangle2D.Double(tileX, tileY + tileHeight - dy, w - dx, dy);
/*     */     
/* 472 */     Rectangle2D.Double C = new Rectangle2D.Double(tileX + tileWidth - dx, tileY, dx, h - dy);
/*     */     
/* 474 */     Rectangle2D.Double D = new Rectangle2D.Double(tileX, tileY, w - dx, h - dy);
/*     */ 
/*     */     
/* 477 */     Rectangle2D realTileRect = new Rectangle2D.Double(tiledRect.getX(), tiledRect.getY(), w, h);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 492 */     RenderedImage ARed = null, BRed = null, CRed = null, DRed = null;
/* 493 */     Filter source = getSource();
/*     */     
/* 495 */     if (A.getWidth() > 0.0D && A.getHeight() > 0.0D) {
/*     */       
/* 497 */       Rectangle devA = usr2dev.createTransformedShape(A).getBounds();
/* 498 */       if (devA.width > 0 && devA.height > 0) {
/* 499 */         AffineTransform ATxf = new AffineTransform(usr2dev);
/* 500 */         ATxf.translate(-A.x + tiledX, -A.y + tiledY);
/*     */ 
/*     */         
/* 503 */         Shape aoi = A;
/* 504 */         if (this.overflow) {
/* 505 */           aoi = new Rectangle2D.Double(A.x, A.y, tiledWidth, tiledHeight);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 511 */         hints.put(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, aoi);
/*     */ 
/*     */         
/* 514 */         RenderContext arc = new RenderContext(ATxf, aoi, hints);
/*     */ 
/*     */         
/* 517 */         ARed = source.createRendering(arc);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 526 */     if (B.getWidth() > 0.0D && B.getHeight() > 0.0D) {
/*     */       
/* 528 */       Rectangle devB = usr2dev.createTransformedShape(B).getBounds();
/* 529 */       if (devB.width > 0 && devB.height > 0) {
/* 530 */         AffineTransform BTxf = new AffineTransform(usr2dev);
/* 531 */         BTxf.translate(-B.x + tiledX + dx, -B.y + tiledY);
/*     */ 
/*     */         
/* 534 */         Shape aoi = B;
/* 535 */         if (this.overflow) {
/* 536 */           aoi = new Rectangle2D.Double(B.x - tiledWidth + w - dx, B.y, tiledWidth, tiledHeight);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 542 */         hints.put(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, aoi);
/*     */ 
/*     */         
/* 545 */         RenderContext brc = new RenderContext(BTxf, aoi, hints);
/*     */ 
/*     */         
/* 548 */         BRed = source.createRendering(brc);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 553 */     if (C.getWidth() > 0.0D && C.getHeight() > 0.0D) {
/*     */       
/* 555 */       Rectangle devC = usr2dev.createTransformedShape(C).getBounds();
/* 556 */       if (devC.width > 0 && devC.height > 0) {
/* 557 */         AffineTransform CTxf = new AffineTransform(usr2dev);
/* 558 */         CTxf.translate(-C.x + tiledX, -C.y + tiledY + dy);
/*     */ 
/*     */         
/* 561 */         Shape aoi = C;
/* 562 */         if (this.overflow) {
/* 563 */           aoi = new Rectangle2D.Double(C.x, C.y - tileHeight + h - dy, tiledWidth, tiledHeight);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 569 */         hints.put(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, aoi);
/*     */ 
/*     */         
/* 572 */         RenderContext crc = new RenderContext(CTxf, aoi, hints);
/*     */ 
/*     */         
/* 575 */         CRed = source.createRendering(crc);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 580 */     if (D.getWidth() > 0.0D && D.getHeight() > 0.0D) {
/*     */       
/* 582 */       Rectangle devD = usr2dev.createTransformedShape(D).getBounds();
/* 583 */       if (devD.width > 0 && devD.height > 0) {
/* 584 */         AffineTransform DTxf = new AffineTransform(usr2dev);
/* 585 */         DTxf.translate(-D.x + tiledX + dx, -D.y + tiledY + dy);
/*     */ 
/*     */         
/* 588 */         Shape aoi = D;
/* 589 */         if (this.overflow) {
/* 590 */           aoi = new Rectangle2D.Double(D.x - tileWidth + w - dx, D.y - tileHeight + h - dy, tiledWidth, tiledHeight);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 596 */         hints.put(RenderingHintsKeyExt.KEY_AREA_OF_INTEREST, aoi);
/*     */ 
/*     */         
/* 599 */         RenderContext drc = new RenderContext(DTxf, aoi, hints);
/*     */ 
/*     */         
/* 602 */         DRed = source.createRendering(drc);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 611 */     Rectangle realTileRectDev = usr2dev.createTransformedShape(realTileRect).getBounds();
/*     */ 
/*     */     
/* 614 */     if (realTileRectDev.width == 0 || realTileRectDev.height == 0) {
/* 615 */       return null;
/*     */     }
/*     */     
/* 618 */     BufferedImage realTileBI = new BufferedImage(realTileRectDev.width, realTileRectDev.height, 2);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 623 */     Graphics2D g = GraphicsUtil.createGraphics(realTileBI, rc.getRenderingHints());
/*     */ 
/*     */ 
/*     */     
/* 627 */     g.translate(-realTileRectDev.x, -realTileRectDev.y);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 632 */     AffineTransform redTxf = new AffineTransform();
/* 633 */     Point2D.Double redVec = new Point2D.Double();
/* 634 */     RenderedImage refRed = null;
/* 635 */     if (ARed != null) {
/*     */       
/* 637 */       g.drawRenderedImage(ARed, redTxf);
/* 638 */       refRed = ARed;
/*     */     } 
/* 640 */     if (BRed != null) {
/*     */ 
/*     */       
/* 643 */       if (refRed == null) {
/* 644 */         refRed = BRed;
/*     */       }
/*     */ 
/*     */       
/* 648 */       redVec.x = dx;
/* 649 */       redVec.y = 0.0D;
/* 650 */       usr2dev.deltaTransform(redVec, redVec);
/* 651 */       redVec.x = Math.floor(redVec.x) - (BRed.getMinX() - refRed.getMinX());
/* 652 */       redVec.y = Math.floor(redVec.y) - (BRed.getMinY() - refRed.getMinY());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 657 */       g.drawRenderedImage(BRed, redTxf);
/*     */     } 
/* 659 */     if (CRed != null) {
/*     */ 
/*     */       
/* 662 */       if (refRed == null) {
/* 663 */         refRed = CRed;
/*     */       }
/*     */ 
/*     */       
/* 667 */       redVec.x = 0.0D;
/* 668 */       redVec.y = dy;
/* 669 */       usr2dev.deltaTransform(redVec, redVec);
/* 670 */       redVec.x = Math.floor(redVec.x) - (CRed.getMinX() - refRed.getMinX());
/* 671 */       redVec.y = Math.floor(redVec.y) - (CRed.getMinY() - refRed.getMinY());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 676 */       g.drawRenderedImage(CRed, redTxf);
/*     */     } 
/* 678 */     if (DRed != null) {
/*     */ 
/*     */       
/* 681 */       if (refRed == null) {
/* 682 */         refRed = DRed;
/*     */       }
/*     */ 
/*     */       
/* 686 */       redVec.x = dx;
/* 687 */       redVec.y = dy;
/* 688 */       usr2dev.deltaTransform(redVec, redVec);
/* 689 */       redVec.x = Math.floor(redVec.x) - (DRed.getMinX() - refRed.getMinX());
/* 690 */       redVec.y = Math.floor(redVec.y) - (DRed.getMinY() - refRed.getMinY());
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 695 */       g.drawRenderedImage(DRed, redTxf);
/*     */     } 
/*     */ 
/*     */     
/* 699 */     return (RenderedImage)new BufferedImageCachableRed(realTileBI, realTileRectDev.x, realTileRectDev.y);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/renderable/TileRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */