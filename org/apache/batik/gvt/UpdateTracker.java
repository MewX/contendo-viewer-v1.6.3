/*     */ package org.apache.batik.gvt;
/*     */ 
/*     */ import java.awt.Rectangle;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeChangeAdapter;
/*     */ import org.apache.batik.gvt.event.GraphicsNodeChangeEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class UpdateTracker
/*     */   extends GraphicsNodeChangeAdapter
/*     */ {
/*  45 */   Map dirtyNodes = null;
/*  46 */   Map fromBounds = new HashMap<Object, Object>();
/*  47 */   protected static Rectangle2D NULL_RECT = new Rectangle();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasChanged() {
/*  56 */     return (this.dirtyNodes != null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List getDirtyAreas() {
/*  63 */     if (this.dirtyNodes == null) {
/*  64 */       return null;
/*     */     }
/*  66 */     List<Shape> ret = new LinkedList();
/*  67 */     Set keys = this.dirtyNodes.keySet();
/*  68 */     for (Object key : keys) {
/*  69 */       WeakReference<GraphicsNode> gnWRef = (WeakReference)key;
/*  70 */       GraphicsNode gn = gnWRef.get();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  77 */       if (gn == null) {
/*     */         continue;
/*     */       }
/*  80 */       AffineTransform oat = (AffineTransform)this.dirtyNodes.get(gnWRef);
/*  81 */       if (oat != null) {
/*  82 */         oat = new AffineTransform(oat);
/*     */       }
/*     */       
/*  85 */       Rectangle2D srcORgn = (Rectangle2D)this.fromBounds.remove(gnWRef);
/*     */       
/*  87 */       Rectangle2D srcNRgn = null;
/*  88 */       AffineTransform nat = null;
/*  89 */       if (!(srcORgn instanceof ChngSrcRect)) {
/*     */         
/*  91 */         srcNRgn = gn.getBounds();
/*  92 */         nat = gn.getTransform();
/*  93 */         if (nat != null) {
/*  94 */           nat = new AffineTransform(nat);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/* 109 */         gn = gn.getParent();
/* 110 */         if (gn == null) {
/*     */           break;
/*     */         }
/* 113 */         Filter f = gn.getFilter();
/* 114 */         if (f != null) {
/* 115 */           srcNRgn = f.getBounds2D();
/* 116 */           nat = null;
/*     */         } 
/*     */ 
/*     */         
/* 120 */         AffineTransform at = gn.getTransform();
/*     */         
/* 122 */         gnWRef = gn.getWeakReference();
/* 123 */         AffineTransform poat = (AffineTransform)this.dirtyNodes.get(gnWRef);
/* 124 */         if (poat == null) poat = at; 
/* 125 */         if (poat != null) {
/* 126 */           if (oat != null) {
/* 127 */             oat.preConcatenate(poat);
/*     */           } else {
/* 129 */             oat = new AffineTransform(poat);
/*     */           } 
/*     */         }
/* 132 */         if (at != null) {
/* 133 */           if (nat != null) {
/* 134 */             nat.preConcatenate(at); continue;
/*     */           } 
/* 136 */           nat = new AffineTransform(at);
/*     */         } 
/*     */       } 
/*     */       
/* 140 */       if (gn == null) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 146 */         Shape oRgn = srcORgn;
/* 147 */         if (oRgn != null && oRgn != NULL_RECT) {
/* 148 */           if (oat != null) {
/* 149 */             oRgn = oat.createTransformedShape(srcORgn);
/*     */           }
/*     */           
/* 152 */           ret.add(oRgn);
/*     */         } 
/*     */         
/* 155 */         if (srcNRgn != null) {
/* 156 */           Shape nRgn = srcNRgn;
/* 157 */           if (nat != null)
/* 158 */             nRgn = nat.createTransformedShape(srcNRgn); 
/* 159 */           if (nRgn != null) {
/* 160 */             ret.add(nRgn);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/* 165 */     this.fromBounds.clear();
/* 166 */     this.dirtyNodes.clear();
/* 167 */     return ret;
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
/*     */   public Rectangle2D getNodeDirtyRegion(GraphicsNode gn, AffineTransform at) {
/* 179 */     WeakReference gnWRef = gn.getWeakReference();
/* 180 */     AffineTransform nat = (AffineTransform)this.dirtyNodes.get(gnWRef);
/* 181 */     if (nat == null) nat = gn.getTransform(); 
/* 182 */     if (nat != null) {
/* 183 */       at = new AffineTransform(at);
/* 184 */       at.concatenate(nat);
/*     */     } 
/*     */     
/* 187 */     Filter f = gn.getFilter();
/* 188 */     Rectangle2D ret = null;
/* 189 */     if (gn instanceof CompositeGraphicsNode) {
/* 190 */       CompositeGraphicsNode cgn = (CompositeGraphicsNode)gn;
/*     */       
/* 192 */       for (Object aCgn : cgn) {
/* 193 */         GraphicsNode childGN = (GraphicsNode)aCgn;
/* 194 */         Rectangle2D r2d = getNodeDirtyRegion(childGN, at);
/* 195 */         if (r2d != null) {
/* 196 */           if (f != null) {
/*     */ 
/*     */             
/* 199 */             Shape s = at.createTransformedShape(f.getBounds2D());
/* 200 */             ret = s.getBounds2D();
/*     */             break;
/*     */           } 
/* 203 */           if (ret == null || ret == NULL_RECT) { ret = r2d; continue; }
/*     */           
/* 205 */           ret.add(r2d);
/*     */         } 
/*     */       } 
/*     */     } else {
/* 209 */       ret = (Rectangle2D)this.fromBounds.remove(gnWRef);
/* 210 */       if (ret == null) {
/* 211 */         if (f != null) { ret = f.getBounds2D(); }
/* 212 */         else { ret = gn.getBounds(); } 
/* 213 */       } else if (ret == NULL_RECT) {
/* 214 */         ret = null;
/* 215 */       }  if (ret != null)
/* 216 */         ret = at.createTransformedShape(ret).getBounds2D(); 
/*     */     } 
/* 218 */     return ret;
/*     */   }
/*     */   
/*     */   public Rectangle2D getNodeDirtyRegion(GraphicsNode gn) {
/* 222 */     return getNodeDirtyRegion(gn, new AffineTransform());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void changeStarted(GraphicsNodeChangeEvent gnce) {
/* 231 */     GraphicsNode gn = gnce.getGraphicsNode();
/* 232 */     WeakReference gnWRef = gn.getWeakReference();
/*     */     
/* 234 */     boolean doPut = false;
/* 235 */     if (this.dirtyNodes == null) {
/* 236 */       this.dirtyNodes = new HashMap<Object, Object>();
/* 237 */       doPut = true;
/* 238 */     } else if (!this.dirtyNodes.containsKey(gnWRef)) {
/* 239 */       doPut = true;
/*     */     } 
/* 241 */     if (doPut) {
/* 242 */       AffineTransform at = gn.getTransform();
/* 243 */       if (at != null) { at = (AffineTransform)at.clone(); }
/* 244 */       else { at = new AffineTransform(); }
/* 245 */        this.dirtyNodes.put(gnWRef, at);
/*     */     } 
/*     */     
/* 248 */     GraphicsNode chngSrc = gnce.getChangeSrc();
/* 249 */     Rectangle2D rgn = null;
/* 250 */     if (chngSrc != null) {
/*     */ 
/*     */       
/* 253 */       Rectangle2D drgn = getNodeDirtyRegion(chngSrc);
/* 254 */       if (drgn != null) {
/* 255 */         rgn = new ChngSrcRect(drgn);
/*     */       }
/*     */     } else {
/* 258 */       rgn = gn.getBounds();
/*     */     } 
/*     */     
/* 261 */     Rectangle2D r2d = (Rectangle2D)this.fromBounds.remove(gnWRef);
/* 262 */     if (rgn != null) {
/* 263 */       if (r2d != null && r2d != NULL_RECT) {
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 268 */         r2d.add(rgn);
/*     */       } else {
/*     */         
/* 271 */         r2d = rgn;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 280 */     if (r2d == null)
/* 281 */       r2d = NULL_RECT; 
/* 282 */     this.fromBounds.put(gnWRef, r2d);
/*     */   }
/*     */   
/*     */   class ChngSrcRect extends Rectangle2D.Float {
/*     */     ChngSrcRect(Rectangle2D r2d) {
/* 287 */       super((float)r2d.getX(), (float)r2d.getY(), (float)r2d.getWidth(), (float)r2d.getHeight());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 296 */     this.dirtyNodes = null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/UpdateTracker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */