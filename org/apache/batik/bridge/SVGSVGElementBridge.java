/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Dimension2D;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimatedRect;
/*     */ import org.apache.batik.anim.dom.SVGOMElement;
/*     */ import org.apache.batik.anim.dom.SVGOMSVGElement;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.dom.svg.SVGSVGContext;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable;
/*     */ import org.apache.batik.ext.awt.image.renderable.ClipRable8Bit;
/*     */ import org.apache.batik.ext.awt.image.renderable.Filter;
/*     */ import org.apache.batik.gvt.CanvasGraphicsNode;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.ShapeNode;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGAnimatedRect;
/*     */ import org.w3c.dom.svg.SVGDocument;
/*     */ import org.w3c.dom.svg.SVGRect;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGSVGElementBridge
/*     */   extends SVGGElementBridge
/*     */   implements SVGSVGContext
/*     */ {
/*     */   public String getLocalName() {
/*  72 */     return "svg";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  79 */     return new SVGSVGElementBridge();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/*  86 */     return (GraphicsNode)new CanvasGraphicsNode();
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
/*     */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/*  98 */     if (!SVGUtilities.matchUserAgent(e, ctx.getUserAgent())) {
/*  99 */       return null;
/*     */     }
/*     */ 
/*     */     
/* 103 */     CanvasGraphicsNode cgn = (CanvasGraphicsNode)instantiateGraphicsNode();
/*     */     
/* 105 */     associateSVGContext(ctx, e, (GraphicsNode)cgn);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 112 */       SVGDocument doc = (SVGDocument)e.getOwnerDocument();
/* 113 */       SVGOMSVGElement se = (SVGOMSVGElement)e;
/* 114 */       boolean isOutermost = (doc.getRootElement() == e);
/* 115 */       float x = 0.0F;
/* 116 */       float y = 0.0F;
/*     */       
/* 118 */       if (!isOutermost) {
/*     */         
/* 120 */         AbstractSVGAnimatedLength _x = (AbstractSVGAnimatedLength)se.getX();
/*     */         
/* 122 */         x = _x.getCheckedValue();
/*     */ 
/*     */         
/* 125 */         AbstractSVGAnimatedLength _y = (AbstractSVGAnimatedLength)se.getY();
/*     */         
/* 127 */         y = _y.getCheckedValue();
/*     */       } 
/*     */ 
/*     */       
/* 131 */       AbstractSVGAnimatedLength _width = (AbstractSVGAnimatedLength)se.getWidth();
/*     */       
/* 133 */       float w = _width.getCheckedValue();
/*     */ 
/*     */       
/* 136 */       AbstractSVGAnimatedLength _height = (AbstractSVGAnimatedLength)se.getHeight();
/*     */       
/* 138 */       float h = _height.getCheckedValue();
/*     */ 
/*     */       
/* 141 */       cgn.setVisible(CSSUtilities.convertVisibility(e));
/*     */ 
/*     */       
/* 144 */       SVGOMAnimatedRect vb = (SVGOMAnimatedRect)se.getViewBox();
/* 145 */       SVGAnimatedPreserveAspectRatio par = se.getPreserveAspectRatio();
/* 146 */       AffineTransform viewingTransform = ViewBox.getPreserveAspectRatioTransform(e, (SVGAnimatedRect)vb, par, w, h, ctx);
/*     */ 
/*     */       
/* 149 */       float actualWidth = w;
/* 150 */       float actualHeight = h;
/*     */       try {
/* 152 */         AffineTransform vtInv = viewingTransform.createInverse();
/* 153 */         actualWidth = (float)(w * vtInv.getScaleX());
/* 154 */         actualHeight = (float)(h * vtInv.getScaleY());
/* 155 */       } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */       
/* 157 */       AffineTransform positionTransform = AffineTransform.getTranslateInstance(x, y);
/*     */ 
/*     */ 
/*     */       
/* 161 */       if (!isOutermost) {
/*     */         
/* 163 */         cgn.setPositionTransform(positionTransform);
/* 164 */       } else if (doc == ctx.getDocument()) {
/* 165 */         final double dw = w;
/* 166 */         final double dh = h;
/*     */         
/* 168 */         ctx.setDocumentSize(new Dimension2D() {
/* 169 */               double w = dw;
/* 170 */               double h = dh;
/*     */               
/* 172 */               public double getWidth() { return this.w; } public double getHeight() {
/* 173 */                 return this.h;
/*     */               } public void setSize(double w, double h) {
/* 175 */                 this.w = w;
/* 176 */                 this.h = h;
/*     */               }
/*     */             });
/*     */       } 
/*     */ 
/*     */       
/* 182 */       cgn.setViewingTransform(viewingTransform);
/*     */ 
/*     */       
/* 185 */       Shape clip = null;
/* 186 */       if (CSSUtilities.convertOverflow(e)) {
/* 187 */         float[] offsets = CSSUtilities.convertClip(e);
/* 188 */         if (offsets == null) {
/* 189 */           clip = new Rectangle2D.Float(x, y, w, h);
/*     */         
/*     */         }
/*     */         else {
/*     */ 
/*     */           
/* 195 */           clip = new Rectangle2D.Float(x + offsets[3], y + offsets[0], w - offsets[1] - offsets[3], h - offsets[2] - offsets[0]);
/*     */         } 
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 202 */       if (clip != null) {
/*     */         try {
/* 204 */           AffineTransform at = new AffineTransform(positionTransform);
/* 205 */           at.concatenate(viewingTransform);
/* 206 */           at = at.createInverse();
/* 207 */           clip = at.createTransformedShape(clip);
/* 208 */           Filter filter = cgn.getGraphicsNodeRable(true);
/* 209 */           cgn.setClip((ClipRable)new ClipRable8Bit(filter, clip));
/* 210 */         } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */       }
/* 212 */       RenderingHints hints = null;
/* 213 */       hints = CSSUtilities.convertColorRendering(e, hints);
/* 214 */       if (hints != null) {
/* 215 */         cgn.setRenderingHints(hints);
/*     */       }
/*     */       
/* 218 */       Rectangle2D r = CSSUtilities.convertEnableBackground(e);
/* 219 */       if (r != null) {
/* 220 */         cgn.setBackgroundEnable(r);
/*     */       }
/*     */       
/* 223 */       if (vb.isSpecified()) {
/* 224 */         SVGRect vbr = vb.getAnimVal();
/* 225 */         actualWidth = vbr.getWidth();
/* 226 */         actualHeight = vbr.getHeight();
/*     */       } 
/* 228 */       ctx.openViewport(e, new SVGSVGElementViewport(actualWidth, actualHeight));
/*     */ 
/*     */       
/* 231 */       return (GraphicsNode)cgn;
/* 232 */     } catch (LiveAttributeException ex) {
/* 233 */       throw new BridgeException(ctx, ex);
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
/*     */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/* 250 */     node.setComposite(CSSUtilities.convertOpacity(e));
/*     */     
/* 252 */     node.setFilter(CSSUtilities.convertFilter(e, node, ctx));
/*     */     
/* 254 */     node.setMask(CSSUtilities.convertMask(e, node, ctx));
/*     */     
/* 256 */     node.setPointerEventType(CSSUtilities.convertPointerEvents(e));
/*     */     
/* 258 */     initializeDynamicSupport(ctx, e, node);
/*     */     
/* 260 */     ctx.closeViewport(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 269 */     this.ctx.removeViewport(this.e);
/* 270 */     super.dispose();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {
/*     */     try {
/* 279 */       boolean rebuild = false;
/* 280 */       if (alav.getNamespaceURI() == null) {
/* 281 */         String ln = alav.getLocalName();
/* 282 */         if (ln.equals("width") || ln.equals("height")) {
/*     */           
/* 284 */           rebuild = true;
/* 285 */         } else if (ln.equals("x") || ln.equals("y")) {
/*     */           
/* 287 */           SVGDocument doc = (SVGDocument)this.e.getOwnerDocument();
/* 288 */           SVGOMSVGElement se = (SVGOMSVGElement)this.e;
/*     */           
/* 290 */           boolean isOutermost = (doc.getRootElement() == this.e);
/* 291 */           if (!isOutermost) {
/*     */             
/* 293 */             AbstractSVGAnimatedLength _x = (AbstractSVGAnimatedLength)se.getX();
/*     */             
/* 295 */             float x = _x.getCheckedValue();
/*     */ 
/*     */             
/* 298 */             AbstractSVGAnimatedLength _y = (AbstractSVGAnimatedLength)se.getY();
/*     */             
/* 300 */             float y = _y.getCheckedValue();
/*     */             
/* 302 */             AffineTransform positionTransform = AffineTransform.getTranslateInstance(x, y);
/*     */ 
/*     */             
/* 305 */             CanvasGraphicsNode cgn = (CanvasGraphicsNode)this.node;
/*     */             
/* 307 */             cgn.setPositionTransform(positionTransform);
/*     */             return;
/*     */           } 
/* 310 */         } else if (ln.equals("viewBox") || ln.equals("preserveAspectRatio")) {
/*     */           
/* 312 */           SVGDocument doc = (SVGDocument)this.e.getOwnerDocument();
/* 313 */           SVGOMSVGElement se = (SVGOMSVGElement)this.e;
/* 314 */           boolean isOutermost = (doc.getRootElement() == this.e);
/*     */ 
/*     */           
/* 317 */           float x = 0.0F;
/* 318 */           float y = 0.0F;
/* 319 */           if (!isOutermost) {
/*     */             
/* 321 */             AbstractSVGAnimatedLength _x = (AbstractSVGAnimatedLength)se.getX();
/*     */             
/* 323 */             x = _x.getCheckedValue();
/*     */ 
/*     */             
/* 326 */             AbstractSVGAnimatedLength _y = (AbstractSVGAnimatedLength)se.getY();
/*     */             
/* 328 */             y = _y.getCheckedValue();
/*     */           } 
/*     */ 
/*     */           
/* 332 */           AbstractSVGAnimatedLength _width = (AbstractSVGAnimatedLength)se.getWidth();
/*     */           
/* 334 */           float w = _width.getCheckedValue();
/*     */ 
/*     */           
/* 337 */           AbstractSVGAnimatedLength _height = (AbstractSVGAnimatedLength)se.getHeight();
/*     */           
/* 339 */           float h = _height.getCheckedValue();
/*     */ 
/*     */           
/* 342 */           CanvasGraphicsNode cgn = (CanvasGraphicsNode)this.node;
/*     */ 
/*     */           
/* 345 */           SVGOMAnimatedRect vb = (SVGOMAnimatedRect)se.getViewBox();
/* 346 */           SVGAnimatedPreserveAspectRatio par = se.getPreserveAspectRatio();
/* 347 */           AffineTransform newVT = ViewBox.getPreserveAspectRatioTransform(this.e, (SVGAnimatedRect)vb, par, w, h, this.ctx);
/*     */ 
/*     */           
/* 350 */           AffineTransform oldVT = cgn.getViewingTransform();
/* 351 */           if (newVT.getScaleX() != oldVT.getScaleX() || newVT.getScaleY() != oldVT.getScaleY() || newVT.getShearX() != oldVT.getShearX() || newVT.getShearY() != oldVT.getShearY()) {
/*     */ 
/*     */ 
/*     */             
/* 355 */             rebuild = true;
/*     */           } else {
/*     */             
/* 358 */             cgn.setViewingTransform(newVT);
/*     */ 
/*     */             
/* 361 */             Shape clip = null;
/* 362 */             if (CSSUtilities.convertOverflow(this.e)) {
/* 363 */               float[] offsets = CSSUtilities.convertClip(this.e);
/* 364 */               if (offsets == null) {
/* 365 */                 clip = new Rectangle2D.Float(x, y, w, h);
/*     */               
/*     */               }
/*     */               else {
/*     */ 
/*     */                 
/* 371 */                 clip = new Rectangle2D.Float(x + offsets[3], y + offsets[0], w - offsets[1] - offsets[3], h - offsets[2] - offsets[0]);
/*     */               } 
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 378 */             if (clip != null) {
/*     */               
/*     */               try {
/* 381 */                 AffineTransform at = cgn.getPositionTransform();
/* 382 */                 if (at == null) { at = new AffineTransform(); }
/* 383 */                 else { at = new AffineTransform(at); }
/* 384 */                  at.concatenate(newVT);
/* 385 */                 at = at.createInverse();
/* 386 */                 clip = at.createTransformedShape(clip);
/* 387 */                 Filter filter = cgn.getGraphicsNodeRable(true);
/* 388 */                 cgn.setClip((ClipRable)new ClipRable8Bit(filter, clip));
/* 389 */               } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */             }
/*     */           } 
/*     */         } 
/*     */         
/* 394 */         if (rebuild) {
/* 395 */           CompositeGraphicsNode gn = this.node.getParent();
/* 396 */           gn.remove(this.node);
/* 397 */           disposeTree(this.e, false);
/*     */           
/* 399 */           handleElementAdded(gn, this.e.getParentNode(), this.e);
/*     */           return;
/*     */         } 
/*     */       } 
/* 403 */     } catch (LiveAttributeException ex) {
/* 404 */       throw new BridgeException(this.ctx, ex);
/*     */     } 
/* 406 */     super.handleAnimatedAttributeChanged(alav);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class SVGSVGElementViewport
/*     */     implements Viewport
/*     */   {
/*     */     private float width;
/*     */ 
/*     */     
/*     */     private float height;
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGSVGElementViewport(float w, float h) {
/* 422 */       this.width = w;
/* 423 */       this.height = h;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getWidth() {
/* 430 */       return this.width;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getHeight() {
/* 437 */       return this.height;
/*     */     }
/*     */   }
/*     */   
/*     */   public List getIntersectionList(SVGRect svgRect, Element end) {
/* 442 */     List<Element> ret = new ArrayList();
/* 443 */     Rectangle2D rect = new Rectangle2D.Float(svgRect.getX(), svgRect.getY(), svgRect.getWidth(), svgRect.getHeight());
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 448 */     GraphicsNode svgGN = this.ctx.getGraphicsNode(this.e);
/* 449 */     if (svgGN == null) return ret;
/*     */     
/* 451 */     Rectangle2D svgBounds = svgGN.getSensitiveBounds();
/* 452 */     if (svgBounds == null) {
/* 453 */       return ret;
/*     */     }
/*     */ 
/*     */     
/* 457 */     if (!rect.intersects(svgBounds)) {
/* 458 */       return ret;
/*     */     }
/* 460 */     Element base = this.e;
/* 461 */     AffineTransform ati = svgGN.getGlobalTransform();
/*     */     try {
/* 463 */       ati = ati.createInverse();
/* 464 */     } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */ 
/*     */ 
/*     */     
/* 468 */     Node next = base.getFirstChild();
/* 469 */     while (next != null && 
/* 470 */       !(next instanceof Element))
/*     */     {
/* 472 */       next = next.getNextSibling();
/*     */     }
/* 474 */     if (next == null) return ret; 
/* 475 */     Element curr = (Element)next;
/*     */     
/* 477 */     Set ancestors = null;
/* 478 */     if (end != null) {
/* 479 */       ancestors = getAncestors(end, base);
/* 480 */       if (ancestors == null)
/* 481 */         end = null; 
/*     */     } 
/* 483 */     while (curr != null) {
/* 484 */       String nsURI = curr.getNamespaceURI();
/* 485 */       String tag = curr.getLocalName();
/*     */       
/* 487 */       boolean isGroup = ("http://www.w3.org/2000/svg".equals(nsURI) && ("g".equals(tag) || "svg".equals(tag) || "a".equals(tag)));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 492 */       GraphicsNode gn = this.ctx.getGraphicsNode(curr);
/* 493 */       if (gn == null) {
/*     */ 
/*     */         
/* 496 */         if (ancestors != null && ancestors.contains(curr))
/*     */           break; 
/* 498 */         curr = getNext(curr, base, end);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 503 */       AffineTransform at = gn.getGlobalTransform();
/* 504 */       Rectangle2D gnBounds = gn.getSensitiveBounds();
/* 505 */       at.preConcatenate(ati);
/* 506 */       if (gnBounds != null) {
/* 507 */         gnBounds = at.createTransformedShape(gnBounds).getBounds2D();
/*     */       }
/* 509 */       if (gnBounds == null || !rect.intersects(gnBounds)) {
/*     */ 
/*     */ 
/*     */         
/* 513 */         if (ancestors != null && ancestors.contains(curr))
/*     */           break; 
/* 515 */         curr = getNext(curr, base, end);
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */ 
/*     */       
/* 522 */       if (isGroup) {
/*     */         
/* 524 */         next = curr.getFirstChild();
/* 525 */         while (next != null && 
/* 526 */           !(next instanceof Element))
/*     */         {
/* 528 */           next = next.getNextSibling();
/*     */         }
/* 530 */         if (next != null) {
/* 531 */           curr = (Element)next;
/*     */           continue;
/*     */         } 
/*     */       } else {
/* 535 */         if (curr == end) {
/*     */           break;
/*     */         }
/* 538 */         if ("http://www.w3.org/2000/svg".equals(nsURI) && "use".equals(tag))
/*     */         {
/*     */ 
/*     */           
/* 542 */           if (rect.contains(gnBounds))
/* 543 */             ret.add(curr);  } 
/* 544 */         if (gn instanceof ShapeNode) {
/* 545 */           ShapeNode sn = (ShapeNode)gn;
/* 546 */           Shape sensitive = sn.getSensitiveArea();
/* 547 */           if (sensitive != null) {
/* 548 */             sensitive = at.createTransformedShape(sensitive);
/* 549 */             if (sensitive.intersects(rect))
/* 550 */               ret.add(curr); 
/*     */           } 
/* 552 */         } else if (gn instanceof TextNode) {
/* 553 */           SVGOMElement svgElem = (SVGOMElement)curr;
/*     */           
/* 555 */           SVGTextElementBridge txtBridge = (SVGTextElementBridge)svgElem.getSVGContext();
/* 556 */           Set<? extends Element> elems = txtBridge.getTextIntersectionSet(at, rect);
/*     */ 
/*     */ 
/*     */           
/* 560 */           if (ancestors != null && ancestors.contains(curr)) {
/* 561 */             filterChildren(curr, end, elems, ret);
/*     */           } else {
/* 563 */             ret.addAll(elems);
/*     */           } 
/*     */         } else {
/* 566 */           ret.add(curr);
/*     */         } 
/*     */       } 
/*     */       
/* 570 */       curr = getNext(curr, base, end);
/*     */     } 
/*     */     
/* 573 */     return ret;
/*     */   }
/*     */   
/*     */   public List getEnclosureList(SVGRect svgRect, Element end) {
/* 577 */     List<Element> ret = new ArrayList();
/* 578 */     Rectangle2D rect = new Rectangle2D.Float(svgRect.getX(), svgRect.getY(), svgRect.getWidth(), svgRect.getHeight());
/*     */ 
/*     */ 
/*     */     
/* 582 */     GraphicsNode svgGN = this.ctx.getGraphicsNode(this.e);
/* 583 */     if (svgGN == null) return ret;
/*     */     
/* 585 */     Rectangle2D svgBounds = svgGN.getSensitiveBounds();
/* 586 */     if (svgBounds == null) {
/* 587 */       return ret;
/*     */     }
/*     */ 
/*     */     
/* 591 */     if (!rect.intersects(svgBounds)) {
/* 592 */       return ret;
/*     */     }
/* 594 */     Element base = this.e;
/* 595 */     AffineTransform ati = svgGN.getGlobalTransform();
/*     */     try {
/* 597 */       ati = ati.createInverse();
/* 598 */     } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */ 
/*     */ 
/*     */     
/* 602 */     Node next = base.getFirstChild();
/* 603 */     while (next != null && 
/* 604 */       !(next instanceof Element))
/*     */     {
/* 606 */       next = next.getNextSibling();
/*     */     }
/*     */     
/* 609 */     if (next == null) return ret; 
/* 610 */     Element curr = (Element)next;
/*     */     
/* 612 */     Set ancestors = null;
/* 613 */     if (end != null) {
/* 614 */       ancestors = getAncestors(end, base);
/* 615 */       if (ancestors == null) {
/* 616 */         end = null;
/*     */       }
/*     */     } 
/* 619 */     while (curr != null) {
/* 620 */       String nsURI = curr.getNamespaceURI();
/* 621 */       String tag = curr.getLocalName();
/*     */       
/* 623 */       boolean isGroup = ("http://www.w3.org/2000/svg".equals(nsURI) && ("g".equals(tag) || "svg".equals(tag) || "a".equals(tag)));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 628 */       GraphicsNode gn = this.ctx.getGraphicsNode(curr);
/* 629 */       if (gn == null) {
/*     */ 
/*     */         
/* 632 */         if (ancestors != null && ancestors.contains(curr))
/*     */           break; 
/* 634 */         curr = getNext(curr, base, end);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 639 */       AffineTransform at = gn.getGlobalTransform();
/* 640 */       Rectangle2D gnBounds = gn.getSensitiveBounds();
/* 641 */       at.preConcatenate(ati);
/* 642 */       if (gnBounds != null) {
/* 643 */         gnBounds = at.createTransformedShape(gnBounds).getBounds2D();
/*     */       }
/* 645 */       if (gnBounds == null || !rect.intersects(gnBounds)) {
/*     */ 
/*     */ 
/*     */         
/* 649 */         if (ancestors != null && ancestors.contains(curr))
/*     */           break; 
/* 651 */         curr = getNext(curr, base, end);
/*     */ 
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 657 */       if (isGroup) {
/*     */         
/* 659 */         next = curr.getFirstChild();
/* 660 */         while (next != null && 
/* 661 */           !(next instanceof Element))
/*     */         {
/* 663 */           next = next.getNextSibling();
/*     */         }
/* 665 */         if (next != null) {
/* 666 */           curr = (Element)next;
/*     */           continue;
/*     */         } 
/*     */       } else {
/* 670 */         if (curr == end)
/* 671 */           break;  if ("http://www.w3.org/2000/svg".equals(nsURI) && "use".equals(tag)) {
/*     */ 
/*     */ 
/*     */           
/* 675 */           if (rect.contains(gnBounds))
/* 676 */             ret.add(curr); 
/* 677 */         } else if (gn instanceof TextNode) {
/*     */ 
/*     */           
/* 680 */           SVGOMElement svgElem = (SVGOMElement)curr;
/*     */           
/* 682 */           SVGTextElementBridge txtBridge = (SVGTextElementBridge)svgElem.getSVGContext();
/* 683 */           Set<? extends Element> elems = txtBridge.getTextEnclosureSet(at, rect);
/*     */ 
/*     */ 
/*     */           
/* 687 */           if (ancestors != null && ancestors.contains(curr))
/* 688 */           { filterChildren(curr, end, elems, ret); }
/*     */           else
/* 690 */           { ret.addAll(elems); } 
/* 691 */         } else if (rect.contains(gnBounds)) {
/*     */           
/* 693 */           ret.add(curr);
/*     */         } 
/*     */       } 
/*     */       
/* 697 */       curr = getNext(curr, base, end);
/*     */     } 
/* 699 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean checkIntersection(Element element, SVGRect svgRect) {
/* 704 */     GraphicsNode svgGN = this.ctx.getGraphicsNode(this.e);
/* 705 */     if (svgGN == null) return false;
/*     */     
/* 707 */     Rectangle2D rect = new Rectangle2D.Float(svgRect.getX(), svgRect.getY(), svgRect.getWidth(), svgRect.getHeight());
/*     */ 
/*     */     
/* 710 */     AffineTransform ati = svgGN.getGlobalTransform();
/*     */     
/*     */     try {
/* 713 */       ati = ati.createInverse();
/* 714 */     } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */     
/* 716 */     SVGContext svgctx = null;
/* 717 */     if (element instanceof SVGOMElement) {
/* 718 */       svgctx = ((SVGOMElement)element).getSVGContext();
/* 719 */       if (svgctx instanceof SVGTextElementBridge || svgctx instanceof SVGTextElementBridge.AbstractTextChildSVGContext)
/*     */       {
/*     */         
/* 722 */         return SVGTextElementBridge.getTextIntersection(this.ctx, element, ati, rect, true);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 727 */     Rectangle2D gnBounds = null;
/* 728 */     GraphicsNode gn = this.ctx.getGraphicsNode(element);
/* 729 */     if (gn != null) {
/* 730 */       gnBounds = gn.getSensitiveBounds();
/*     */     }
/* 732 */     if (gnBounds == null) return false;
/*     */ 
/*     */     
/* 735 */     AffineTransform at = gn.getGlobalTransform();
/* 736 */     at.preConcatenate(ati);
/*     */     
/* 738 */     gnBounds = at.createTransformedShape(gnBounds).getBounds2D();
/* 739 */     if (!rect.intersects(gnBounds)) {
/* 740 */       return false;
/*     */     }
/*     */     
/* 743 */     if (!(gn instanceof ShapeNode)) {
/* 744 */       return true;
/*     */     }
/* 746 */     ShapeNode sn = (ShapeNode)gn;
/* 747 */     Shape sensitive = sn.getSensitiveArea();
/* 748 */     if (sensitive == null) return false;
/*     */     
/* 750 */     sensitive = at.createTransformedShape(sensitive);
/* 751 */     if (sensitive.intersects(rect)) {
/* 752 */       return true;
/*     */     }
/* 754 */     return false;
/*     */   }
/*     */   
/*     */   public boolean checkEnclosure(Element element, SVGRect svgRect) {
/* 758 */     GraphicsNode gn = this.ctx.getGraphicsNode(element);
/* 759 */     Rectangle2D gnBounds = null;
/* 760 */     SVGContext svgctx = null;
/* 761 */     if (element instanceof SVGOMElement) {
/* 762 */       svgctx = ((SVGOMElement)element).getSVGContext();
/* 763 */       if (svgctx instanceof SVGTextElementBridge || svgctx instanceof SVGTextElementBridge.AbstractTextChildSVGContext)
/*     */       
/*     */       { 
/* 766 */         gnBounds = SVGTextElementBridge.getTextBounds(this.ctx, element, true);
/*     */         
/* 768 */         Element p = (Element)element.getParentNode();
/*     */         
/* 770 */         while (p != null && gn == null) {
/* 771 */           gn = this.ctx.getGraphicsNode(p);
/* 772 */           p = (Element)p.getParentNode();
/*     */         }  }
/* 774 */       else if (gn != null)
/* 775 */       { gnBounds = gn.getSensitiveBounds(); } 
/* 776 */     } else if (gn != null) {
/* 777 */       gnBounds = gn.getSensitiveBounds();
/*     */     } 
/* 779 */     if (gnBounds == null) return false;
/*     */     
/* 781 */     GraphicsNode svgGN = this.ctx.getGraphicsNode(this.e);
/* 782 */     if (svgGN == null) return false;
/*     */     
/* 784 */     Rectangle2D rect = new Rectangle2D.Float(svgRect.getX(), svgRect.getY(), svgRect.getWidth(), svgRect.getHeight());
/*     */ 
/*     */     
/* 787 */     AffineTransform ati = svgGN.getGlobalTransform();
/*     */     try {
/* 789 */       ati = ati.createInverse();
/* 790 */     } catch (NoninvertibleTransformException noninvertibleTransformException) {}
/*     */     
/* 792 */     AffineTransform at = gn.getGlobalTransform();
/* 793 */     at.preConcatenate(ati);
/*     */     
/* 795 */     gnBounds = at.createTransformedShape(gnBounds).getBounds2D();
/*     */     
/* 797 */     return rect.contains(gnBounds);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean filterChildren(Element curr, Element end, Set elems, List<Element> ret) {
/* 802 */     Node child = curr.getFirstChild();
/* 803 */     while (child != null) {
/* 804 */       if (child instanceof Element && filterChildren((Element)child, end, elems, ret))
/*     */       {
/* 806 */         return true; } 
/* 807 */       child = child.getNextSibling();
/*     */     } 
/*     */     
/* 810 */     if (curr == end) return true;
/*     */     
/* 812 */     if (elems.contains(curr)) {
/* 813 */       ret.add(curr);
/*     */     }
/* 815 */     return false;
/*     */   }
/*     */   
/*     */   protected Set getAncestors(Element end, Element base) {
/* 819 */     Set<Element> ret = new HashSet();
/* 820 */     Element p = end;
/*     */     do {
/* 822 */       ret.add(p);
/* 823 */       p = (Element)p.getParentNode();
/* 824 */     } while (p != null && p != base);
/*     */     
/* 826 */     if (p == null) {
/* 827 */       return null;
/*     */     }
/* 829 */     return ret;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Element getNext(Element curr, Element base, Element end) {
/* 835 */     Node next = curr.getNextSibling();
/* 836 */     while (next != null && 
/* 837 */       !(next instanceof Element))
/*     */     {
/* 839 */       next = next.getNextSibling();
/*     */     }
/* 841 */     while (next == null) {
/*     */       
/* 843 */       curr = (Element)curr.getParentNode();
/* 844 */       if (curr == end || curr == base) {
/* 845 */         next = null;
/*     */         break;
/*     */       } 
/* 848 */       next = curr.getNextSibling();
/* 849 */       while (next != null && 
/* 850 */         !(next instanceof Element))
/*     */       {
/* 852 */         next = next.getNextSibling();
/*     */       }
/*     */     } 
/*     */     
/* 856 */     return (Element)next;
/*     */   }
/*     */   
/*     */   public void deselectAll() {
/* 860 */     this.ctx.getUserAgent().deselectAll();
/*     */   }
/*     */   
/*     */   public int suspendRedraw(int max_wait_milliseconds) {
/* 864 */     UpdateManager um = this.ctx.getUpdateManager();
/* 865 */     if (um != null)
/* 866 */       return um.addRedrawSuspension(max_wait_milliseconds); 
/* 867 */     return -1;
/*     */   }
/*     */   public boolean unsuspendRedraw(int suspend_handle_id) {
/* 870 */     UpdateManager um = this.ctx.getUpdateManager();
/* 871 */     if (um != null)
/* 872 */       return um.releaseRedrawSuspension(suspend_handle_id); 
/* 873 */     return false;
/*     */   }
/*     */   public void unsuspendRedrawAll() {
/* 876 */     UpdateManager um = this.ctx.getUpdateManager();
/* 877 */     if (um != null)
/* 878 */       um.releaseAllRedrawSuspension(); 
/*     */   }
/*     */   
/*     */   public void forceRedraw() {
/* 882 */     UpdateManager um = this.ctx.getUpdateManager();
/* 883 */     if (um != null) {
/* 884 */       um.forceRepaint();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pauseAnimations() {
/* 891 */     this.ctx.getAnimationEngine().pause();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unpauseAnimations() {
/* 898 */     this.ctx.getAnimationEngine().unpause();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean animationsPaused() {
/* 905 */     return this.ctx.getAnimationEngine().isPaused();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCurrentTime() {
/* 912 */     return this.ctx.getAnimationEngine().getCurrentTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCurrentTime(float t) {
/* 919 */     this.ctx.getAnimationEngine().setCurrentTime(t);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGSVGElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */