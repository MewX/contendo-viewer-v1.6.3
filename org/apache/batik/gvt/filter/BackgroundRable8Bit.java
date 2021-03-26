/*     */ package org.apache.batik.gvt.filter;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.awt.image.RenderedImage;
/*     */ import java.awt.image.renderable.RenderContext;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.batik.ext.awt.image.CompositeRule;
/*     */ import org.apache.batik.ext.awt.image.PadMode;
/*     */ import org.apache.batik.ext.awt.image.renderable.AbstractRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.AffineRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.CompositeRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.ext.awt.image.renderable.PadRable8Bit;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BackgroundRable8Bit
/*     */   extends AbstractRable
/*     */ {
/*     */   private GraphicsNode node;
/*     */   
/*     */   public GraphicsNode getGraphicsNode() {
/*  61 */     return this.node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setGraphicsNode(GraphicsNode node) {
/*  68 */     if (node == null) {
/*  69 */       throw new IllegalArgumentException();
/*     */     }
/*     */     
/*  72 */     this.node = node;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BackgroundRable8Bit(GraphicsNode node) {
/*  79 */     if (node == null) {
/*  80 */       throw new IllegalArgumentException();
/*     */     }
/*  82 */     this.node = node;
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
/*     */   static Rectangle2D addBounds(CompositeGraphicsNode cgn, GraphicsNode child, Rectangle2D init) {
/*  95 */     List children = cgn.getChildren();
/*  96 */     Iterator<GraphicsNode> i = children.iterator();
/*  97 */     Rectangle2D r2d = null;
/*  98 */     while (i.hasNext()) {
/*  99 */       GraphicsNode gn = i.next();
/* 100 */       if (gn == child) {
/*     */         break;
/*     */       }
/*     */       
/* 104 */       Rectangle2D cr2d = gn.getBounds();
/* 105 */       if (cr2d == null)
/* 106 */         continue;  AffineTransform at = gn.getTransform();
/* 107 */       if (at != null) {
/* 108 */         cr2d = at.createTransformedShape(cr2d).getBounds2D();
/*     */       }
/* 110 */       if (r2d == null) { r2d = (Rectangle2D)cr2d.clone(); continue; }
/* 111 */        r2d.add(cr2d);
/*     */     } 
/*     */     
/* 114 */     if (r2d == null) {
/* 115 */       if (init == null) {
/* 116 */         return CompositeGraphicsNode.VIEWPORT;
/*     */       }
/* 118 */       return init;
/*     */     } 
/*     */     
/* 121 */     if (init == null) {
/* 122 */       return r2d;
/*     */     }
/* 124 */     init.add(r2d);
/*     */     
/* 126 */     return init;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Rectangle2D getViewportBounds(GraphicsNode gn, GraphicsNode child) {
/* 133 */     Rectangle2D r2d = null;
/* 134 */     if (gn instanceof CompositeGraphicsNode) {
/* 135 */       CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/* 136 */       r2d = cgn.getBackgroundEnable();
/*     */     } 
/*     */     
/* 139 */     if (r2d == null)
/*     */     {
/* 141 */       r2d = getViewportBounds((GraphicsNode)gn.getParent(), gn);
/*     */     }
/*     */     
/* 144 */     if (r2d == null) {
/* 145 */       return null;
/*     */     }
/*     */     
/* 148 */     if (r2d == CompositeGraphicsNode.VIEWPORT) {
/*     */       
/* 150 */       if (child == null) {
/* 151 */         return (Rectangle2D)gn.getPrimitiveBounds().clone();
/*     */       }
/*     */ 
/*     */       
/* 155 */       CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/* 156 */       return addBounds(cgn, child, null);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 161 */     AffineTransform at = gn.getTransform();
/* 162 */     if (at != null) {
/*     */       try {
/* 164 */         at = at.createInverse();
/* 165 */         r2d = at.createTransformedShape(r2d).getBounds2D();
/* 166 */       } catch (NoninvertibleTransformException nte) {
/*     */         
/* 168 */         r2d = null;
/*     */       } 
/*     */     }
/*     */     
/* 172 */     if (child != null) {
/*     */       
/* 174 */       CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/* 175 */       r2d = addBounds(cgn, child, r2d);
/*     */     } else {
/* 177 */       Rectangle2D gnb = gn.getPrimitiveBounds();
/* 178 */       if (gnb != null) {
/* 179 */         r2d.add(gnb);
/*     */       }
/*     */     } 
/* 182 */     return r2d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static Rectangle2D getBoundsRecursive(GraphicsNode gn, GraphicsNode child) {
/* 191 */     Rectangle2D r2d = null;
/* 192 */     if (gn == null)
/*     */     {
/* 194 */       return null;
/*     */     }
/*     */     
/* 197 */     if (gn instanceof CompositeGraphicsNode) {
/* 198 */       CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/*     */       
/* 200 */       r2d = cgn.getBackgroundEnable();
/*     */     } 
/*     */ 
/*     */     
/* 204 */     if (r2d != null) {
/* 205 */       return r2d;
/*     */     }
/*     */     
/* 208 */     r2d = getBoundsRecursive((GraphicsNode)gn.getParent(), gn);
/*     */ 
/*     */     
/* 211 */     if (r2d == null)
/*     */     {
/* 213 */       return new Rectangle2D.Float(0.0F, 0.0F, 0.0F, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 218 */     if (r2d == CompositeGraphicsNode.VIEWPORT) {
/* 219 */       return r2d;
/*     */     }
/* 221 */     AffineTransform at = gn.getTransform();
/* 222 */     if (at != null) {
/*     */       
/*     */       try {
/*     */         
/* 226 */         at = at.createInverse();
/* 227 */         r2d = at.createTransformedShape(r2d).getBounds2D();
/* 228 */       } catch (NoninvertibleTransformException nte) {
/*     */         
/* 230 */         r2d = null;
/*     */       } 
/*     */     }
/*     */     
/* 234 */     return r2d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBounds2D() {
/* 242 */     Rectangle2D r2d = getBoundsRecursive(this.node, null);
/*     */ 
/*     */ 
/*     */     
/* 246 */     if (r2d == CompositeGraphicsNode.VIEWPORT) {
/* 247 */       r2d = getViewportBounds(this.node, null);
/*     */     }
/*     */ 
/*     */     
/* 251 */     return r2d;
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
/*     */   public Filter getBackground(GraphicsNode gn, GraphicsNode child, Rectangle2D aoi) {
/*     */     CompositeRable8Bit compositeRable8Bit;
/* 266 */     if (gn == null) {
/* 267 */       throw new IllegalArgumentException("BackgroundImage requested yet no parent has 'enable-background:new'");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 272 */     Rectangle2D r2d = null;
/* 273 */     if (gn instanceof CompositeGraphicsNode) {
/* 274 */       CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/* 275 */       r2d = cgn.getBackgroundEnable();
/*     */     } 
/*     */     
/* 278 */     List<Filter> srcs = new ArrayList();
/* 279 */     if (r2d == null) {
/* 280 */       Rectangle2D paoi = aoi;
/* 281 */       AffineTransform at = gn.getTransform();
/* 282 */       if (at != null)
/* 283 */         paoi = at.createTransformedShape(aoi).getBounds2D(); 
/* 284 */       Filter f = getBackground((GraphicsNode)gn.getParent(), gn, paoi);
/*     */ 
/*     */       
/* 287 */       if (f != null && f.getBounds2D().intersects(aoi)) {
/* 288 */         srcs.add(f);
/*     */       }
/*     */     } 
/*     */     
/* 292 */     if (child != null) {
/* 293 */       CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/* 294 */       List children = cgn.getChildren();
/* 295 */       for (Object aChildren : children) {
/* 296 */         GraphicsNode childGN = (GraphicsNode)aChildren;
/*     */ 
/*     */ 
/*     */         
/* 300 */         if (childGN == child) {
/*     */           break;
/*     */         }
/* 303 */         Rectangle2D cbounds = childGN.getBounds();
/* 304 */         if (cbounds == null) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/* 309 */         AffineTransform at = childGN.getTransform();
/* 310 */         if (at != null) {
/* 311 */           cbounds = at.createTransformedShape(cbounds).getBounds2D();
/*     */         }
/*     */         
/* 314 */         if (aoi.intersects(cbounds)) {
/* 315 */           srcs.add(childGN.getEnableBackgroundGraphicsNodeRable(true));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 321 */     if (srcs.size() == 0) {
/* 322 */       return null;
/*     */     }
/* 324 */     Filter ret = null;
/* 325 */     if (srcs.size() == 1) {
/* 326 */       ret = srcs.get(0);
/*     */     } else {
/* 328 */       compositeRable8Bit = new CompositeRable8Bit(srcs, CompositeRule.OVER, false);
/*     */     } 
/* 330 */     if (child != null) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 335 */       AffineTransform at = child.getTransform();
/* 336 */       if (at != null) {
/*     */         try {
/* 338 */           at = at.createInverse();
/* 339 */           AffineRable8Bit affineRable8Bit = new AffineRable8Bit((Filter)compositeRable8Bit, at);
/* 340 */         } catch (NoninvertibleTransformException nte) {
/* 341 */           compositeRable8Bit = null;
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 346 */     return (Filter)compositeRable8Bit;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDynamic() {
/* 357 */     return false;
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
/*     */   public RenderedImage createRendering(RenderContext renderContext) {
/* 377 */     Rectangle2D r2d = getBounds2D();
/*     */ 
/*     */ 
/*     */     
/* 381 */     Shape aoi = renderContext.getAreaOfInterest();
/* 382 */     if (aoi != null) {
/* 383 */       Rectangle2D aoiR2d = aoi.getBounds2D();
/*     */ 
/*     */ 
/*     */       
/* 387 */       if (!r2d.intersects(aoiR2d)) {
/* 388 */         return null;
/*     */       }
/* 390 */       Rectangle2D.intersect(r2d, aoiR2d, r2d);
/*     */     } 
/*     */     
/* 393 */     Filter background = getBackground(this.node, null, r2d);
/*     */     
/* 395 */     if (background == null) {
/* 396 */       return null;
/*     */     }
/* 398 */     PadRable8Bit padRable8Bit = new PadRable8Bit(background, r2d, PadMode.ZERO_PAD);
/*     */ 
/*     */     
/* 401 */     RenderedImage ri = padRable8Bit.createRendering(new RenderContext(renderContext.getTransform(), r2d, renderContext.getRenderingHints()));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 409 */     return ri;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/filter/BackgroundRable8Bit.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */