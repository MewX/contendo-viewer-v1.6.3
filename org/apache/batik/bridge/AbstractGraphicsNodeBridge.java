/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Shape;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.lang.ref.SoftReference;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimatedTransformList;
/*     */ import org.apache.batik.anim.dom.SVGOMElement;
/*     */ import org.apache.batik.css.engine.CSSEngineEvent;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.SVGCSSEngine;
/*     */ import org.apache.batik.dom.events.AbstractEvent;
/*     */ import org.apache.batik.dom.svg.AbstractSVGTransformList;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.dom.svg.SVGMotionAnimatableElement;
/*     */ import org.apache.batik.ext.awt.geom.SegmentList;
/*     */ import org.apache.batik.gvt.CanvasGraphicsNode;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.parser.UnitProcessor;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.DocumentEvent;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.events.MutationEvent;
/*     */ import org.w3c.dom.svg.SVGTransformable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractGraphicsNodeBridge
/*     */   extends AnimatableSVGBridge
/*     */   implements BridgeUpdateHandler, ErrorConstants, GraphicsNodeBridge, SVGContext
/*     */ {
/*     */   protected GraphicsNode node;
/*     */   protected boolean isSVG12;
/*     */   protected UnitProcessor.Context unitContext;
/*     */   
/*     */   public GraphicsNode createGraphicsNode(BridgeContext ctx, Element e) {
/* 105 */     if (!SVGUtilities.matchUserAgent(e, ctx.getUserAgent())) {
/* 106 */       return null;
/*     */     }
/*     */     
/* 109 */     GraphicsNode node = instantiateGraphicsNode();
/*     */ 
/*     */     
/* 112 */     setTransform(node, e, ctx);
/*     */ 
/*     */     
/* 115 */     node.setVisible(CSSUtilities.convertVisibility(e));
/*     */     
/* 117 */     associateSVGContext(ctx, e, node);
/*     */     
/* 119 */     return node;
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
/*     */   protected abstract GraphicsNode instantiateGraphicsNode();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/* 140 */     node.setComposite(CSSUtilities.convertOpacity(e));
/*     */     
/* 142 */     node.setFilter(CSSUtilities.convertFilter(e, node, ctx));
/*     */     
/* 144 */     node.setMask(CSSUtilities.convertMask(e, node, ctx));
/*     */     
/* 146 */     node.setClip(CSSUtilities.convertClipPath(e, node, ctx));
/*     */     
/* 148 */     node.setPointerEventType(CSSUtilities.convertPointerEvents(e));
/*     */     
/* 150 */     initializeDynamicSupport(ctx, e, node);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getDisplay(Element e) {
/* 158 */     return CSSUtilities.convertDisplay(e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AffineTransform computeTransform(SVGTransformable te, BridgeContext ctx) {
/*     */     try {
/* 168 */       AffineTransform at = new AffineTransform();
/*     */ 
/*     */       
/* 171 */       SVGOMAnimatedTransformList atl = (SVGOMAnimatedTransformList)te.getTransform();
/*     */       
/* 173 */       if (atl.isSpecified()) {
/* 174 */         atl.check();
/* 175 */         AbstractSVGTransformList tl = (AbstractSVGTransformList)te.getTransform().getAnimVal();
/*     */         
/* 177 */         at.concatenate(tl.getAffineTransform());
/*     */       } 
/*     */ 
/*     */       
/* 181 */       if (this.e instanceof SVGMotionAnimatableElement) {
/* 182 */         SVGMotionAnimatableElement mae = (SVGMotionAnimatableElement)this.e;
/* 183 */         AffineTransform mat = mae.getMotionTransform();
/* 184 */         if (mat != null) {
/* 185 */           at.concatenate(mat);
/*     */         }
/*     */       } 
/*     */       
/* 189 */       return at;
/* 190 */     } catch (LiveAttributeException ex) {
/* 191 */       throw new BridgeException(ctx, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setTransform(GraphicsNode n, Element e, BridgeContext ctx) {
/* 200 */     n.setTransform(computeTransform((SVGTransformable)e, ctx));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void associateSVGContext(BridgeContext ctx, Element e, GraphicsNode node) {
/* 211 */     this.e = e;
/* 212 */     this.node = node;
/* 213 */     this.ctx = ctx;
/* 214 */     this.unitContext = UnitProcessor.createContext(ctx, e);
/* 215 */     this.isSVG12 = ctx.isSVG12();
/* 216 */     ((SVGOMElement)e).setSVGContext(this);
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
/*     */   protected void initializeDynamicSupport(BridgeContext ctx, Element e, GraphicsNode node) {
/* 228 */     if (ctx.isInteractive())
/*     */     {
/* 230 */       ctx.bind(e, node);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMAttrModifiedEvent(MutationEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleGeometryChanged() {
/* 246 */     this.node.setFilter(CSSUtilities.convertFilter(this.e, this.node, this.ctx));
/* 247 */     this.node.setMask(CSSUtilities.convertMask(this.e, this.node, this.ctx));
/* 248 */     this.node.setClip(CSSUtilities.convertClipPath(this.e, this.node, this.ctx));
/* 249 */     if (this.isSVG12) {
/* 250 */       if (!"use".equals(this.e.getLocalName()))
/*     */       {
/* 252 */         fireShapeChangeEvent();
/*     */       }
/* 254 */       fireBBoxChangeEvent();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireShapeChangeEvent() {
/* 262 */     DocumentEvent d = (DocumentEvent)this.e.getOwnerDocument();
/* 263 */     AbstractEvent evt = (AbstractEvent)d.createEvent("SVGEvents");
/* 264 */     evt.initEventNS("http://www.w3.org/2000/svg", "shapechange", true, false);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 269 */       ((EventTarget)this.e).dispatchEvent((Event)evt);
/* 270 */     } catch (RuntimeException ex) {
/* 271 */       this.ctx.getUserAgent().displayError(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {
/* 279 */     if (evt.getTarget() instanceof Element) {
/*     */       
/* 281 */       Element e2 = (Element)evt.getTarget();
/* 282 */       Bridge b = this.ctx.getBridge(e2);
/* 283 */       if (b instanceof GenericBridge) {
/* 284 */         ((GenericBridge)b).handleElement(this.ctx, e2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMNodeRemovedEvent(MutationEvent evt) {
/* 293 */     Node parent = this.e.getParentNode();
/* 294 */     if (parent instanceof SVGOMElement) {
/* 295 */       SVGContext bridge = ((SVGOMElement)parent).getSVGContext();
/* 296 */       if (bridge instanceof SVGSwitchElementBridge) {
/* 297 */         ((SVGSwitchElementBridge)bridge).handleChildElementRemoved(this.e);
/*     */         return;
/*     */       } 
/*     */     } 
/* 301 */     CompositeGraphicsNode gn = this.node.getParent();
/* 302 */     gn.remove(this.node);
/* 303 */     disposeTree(this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMCharacterDataModified(MutationEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 317 */     SVGOMElement elt = (SVGOMElement)this.e;
/* 318 */     elt.setSVGContext(null);
/* 319 */     this.ctx.unbind(this.e);
/*     */     
/* 321 */     this.bboxShape = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void disposeTree(Node node) {
/* 328 */     disposeTree(node, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static void disposeTree(Node node, boolean removeContext) {
/* 336 */     if (node instanceof SVGOMElement) {
/* 337 */       SVGOMElement elt = (SVGOMElement)node;
/* 338 */       SVGContext ctx = elt.getSVGContext();
/* 339 */       if (ctx instanceof BridgeUpdateHandler) {
/* 340 */         BridgeUpdateHandler h = (BridgeUpdateHandler)ctx;
/* 341 */         if (removeContext) {
/* 342 */           elt.setSVGContext(null);
/*     */         }
/* 344 */         h.dispose();
/*     */       } 
/*     */     } 
/* 347 */     for (Node n = node.getFirstChild(); n != null; n = n.getNextSibling()) {
/* 348 */       disposeTree(n, removeContext);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleCSSEngineEvent(CSSEngineEvent evt) {
/*     */     try {
/* 357 */       SVGCSSEngine eng = (SVGCSSEngine)evt.getSource();
/* 358 */       int[] properties = evt.getProperties();
/* 359 */       for (int idx : properties) {
/* 360 */         handleCSSPropertyChanged(idx);
/* 361 */         String pn = eng.getPropertyName(idx);
/* 362 */         fireBaseAttributeListeners(pn);
/*     */       } 
/* 364 */     } catch (Exception ex) {
/* 365 */       this.ctx.getUserAgent().displayError(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void handleCSSPropertyChanged(int property) {
/* 373 */     switch (property) {
/*     */       case 57:
/* 375 */         this.node.setVisible(CSSUtilities.convertVisibility(this.e));
/*     */         break;
/*     */       case 38:
/* 378 */         this.node.setComposite(CSSUtilities.convertOpacity(this.e));
/*     */         break;
/*     */       case 18:
/* 381 */         this.node.setFilter(CSSUtilities.convertFilter(this.e, this.node, this.ctx));
/*     */         break;
/*     */       case 37:
/* 384 */         this.node.setMask(CSSUtilities.convertMask(this.e, this.node, this.ctx));
/*     */         break;
/*     */       case 3:
/* 387 */         this.node.setClip(CSSUtilities.convertClipPath(this.e, this.node, this.ctx));
/*     */         break;
/*     */       case 40:
/* 390 */         this.node.setPointerEventType(CSSUtilities.convertPointerEvents(this.e));
/*     */         break;
/*     */       case 12:
/* 393 */         if (!getDisplay(this.e)) {
/*     */           
/* 395 */           CompositeGraphicsNode parent = this.node.getParent();
/* 396 */           parent.remove(this.node);
/* 397 */           disposeTree(this.e, false);
/*     */         } 
/*     */         break;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {
/* 408 */     if (alav.getNamespaceURI() == null && alav.getLocalName().equals("transform")) {
/*     */       
/* 410 */       setTransform(this.node, this.e, this.ctx);
/* 411 */       handleGeometryChanged();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleOtherAnimationChanged(String type) {
/* 419 */     if (type.equals("motion")) {
/* 420 */       setTransform(this.node, this.e, this.ctx);
/* 421 */       handleGeometryChanged();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkBBoxChange() {
/* 430 */     if (this.e != null)
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 439 */       fireBBoxChangeEvent();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireBBoxChangeEvent() {
/* 449 */     DocumentEvent d = (DocumentEvent)this.e.getOwnerDocument();
/* 450 */     AbstractEvent evt = (AbstractEvent)d.createEvent("SVGEvents");
/* 451 */     evt.initEventNS("http://www.w3.org/2000/svg", "RenderedBBoxChange", true, false);
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 456 */       ((EventTarget)this.e).dispatchEvent((Event)evt);
/* 457 */     } catch (RuntimeException ex) {
/* 458 */       this.ctx.getUserAgent().displayError(ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelUnitToMillimeter() {
/* 468 */     return this.ctx.getUserAgent().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelToMM() {
/* 477 */     return getPixelUnitToMillimeter();
/*     */   }
/*     */   
/* 480 */   protected SoftReference bboxShape = null;
/* 481 */   protected Rectangle2D bbox = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBBox() {
/* 490 */     if (this.node == null) {
/* 491 */       return null;
/*     */     }
/* 493 */     Shape s = this.node.getOutline();
/*     */     
/* 495 */     if (this.bboxShape != null && s == this.bboxShape.get()) return this.bbox; 
/* 496 */     this.bboxShape = new SoftReference<Shape>(s);
/* 497 */     this.bbox = null;
/* 498 */     if (s == null) return this.bbox;
/*     */ 
/*     */     
/* 501 */     SegmentList sl = new SegmentList(s);
/* 502 */     this.bbox = sl.getBounds2D();
/* 503 */     return this.bbox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getCTM() {
/* 512 */     GraphicsNode gn = this.node;
/* 513 */     AffineTransform ctm = new AffineTransform();
/* 514 */     Element elt = this.e;
/* 515 */     while (elt != null) {
/* 516 */       if (elt instanceof org.w3c.dom.svg.SVGFitToViewBox) {
/*     */         AffineTransform affineTransform;
/* 518 */         if (gn instanceof CanvasGraphicsNode) {
/* 519 */           affineTransform = ((CanvasGraphicsNode)gn).getViewingTransform();
/*     */         } else {
/* 521 */           affineTransform = gn.getTransform();
/*     */         } 
/* 523 */         if (affineTransform != null) {
/* 524 */           ctm.preConcatenate(affineTransform);
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/* 529 */       AffineTransform at = gn.getTransform();
/* 530 */       if (at != null) {
/* 531 */         ctm.preConcatenate(at);
/*     */       }
/* 533 */       CSSStylableElement cSSStylableElement = SVGCSSEngine.getParentCSSStylableElement(elt);
/* 534 */       CompositeGraphicsNode compositeGraphicsNode = gn.getParent();
/*     */     } 
/* 536 */     return ctm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getScreenTransform() {
/* 543 */     return this.ctx.getUserAgent().getTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScreenTransform(AffineTransform at) {
/* 550 */     this.ctx.getUserAgent().setTransform(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getGlobalTransform() {
/* 558 */     return this.node.getGlobalTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getViewportWidth() {
/* 566 */     return this.ctx.getBlockWidth(this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getViewportHeight() {
/* 574 */     return this.ctx.getBlockHeight(this.e);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFontSize() {
/* 581 */     return CSSUtilities.getComputedStyle(this.e, 22).getFloatValue();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/AbstractGraphicsNodeBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */