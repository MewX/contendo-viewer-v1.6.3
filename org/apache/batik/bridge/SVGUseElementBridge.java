/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.Cursor;
/*     */ import java.awt.RenderingHints;
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.anim.dom.AbstractSVGAnimatedLength;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimatedLength;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.anim.dom.SVGOMUseElement;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.events.NodeEventTarget;
/*     */ import org.apache.batik.dom.svg.LiveAttributeException;
/*     */ import org.apache.batik.dom.svg.SVGOMUseShadowRoot;
/*     */ import org.apache.batik.gvt.CompositeGraphicsNode;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.Event;
/*     */ import org.w3c.dom.events.EventListener;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.svg.SVGTransformable;
/*     */ import org.w3c.dom.svg.SVGUseElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGUseElementBridge
/*     */   extends AbstractGraphicsNodeBridge
/*     */ {
/*     */   protected ReferencedElementMutationListener l;
/*     */   protected BridgeContext subCtx;
/*     */   
/*     */   public String getLocalName() {
/*  76 */     return "use";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  83 */     return new SVGUseElementBridge();
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
/*  95 */     if (!SVGUtilities.matchUserAgent(e, ctx.getUserAgent())) {
/*  96 */       return null;
/*     */     }
/*  98 */     CompositeGraphicsNode gn = buildCompositeGraphicsNode(ctx, e, (CompositeGraphicsNode)null);
/*  99 */     associateSVGContext(ctx, e, (GraphicsNode)gn);
/*     */     
/* 101 */     return (GraphicsNode)gn;
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
/*     */   public CompositeGraphicsNode buildCompositeGraphicsNode(BridgeContext ctx, Element e, CompositeGraphicsNode gn) {
/* 117 */     SVGOMUseElement ue = (SVGOMUseElement)e;
/* 118 */     String uri = ue.getHref().getAnimVal();
/* 119 */     if (uri.length() == 0) {
/* 120 */       throw new BridgeException(ctx, e, "attribute.missing", new Object[] { "xlink:href" });
/*     */     }
/*     */ 
/*     */     
/* 124 */     Element refElement = ctx.getReferencedElement(e, uri);
/*     */ 
/*     */     
/* 127 */     SVGOMDocument document = (SVGOMDocument)e.getOwnerDocument();
/* 128 */     SVGOMDocument refDocument = (SVGOMDocument)refElement.getOwnerDocument();
/* 129 */     boolean isLocal = (refDocument == document);
/*     */     
/* 131 */     BridgeContext theCtx = ctx;
/* 132 */     this.subCtx = null;
/* 133 */     if (!isLocal) {
/* 134 */       this.subCtx = (BridgeContext)refDocument.getCSSEngine().getCSSContext();
/* 135 */       theCtx = this.subCtx;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 140 */     Element localRefElement = (Element)document.importNode(refElement, true, true);
/*     */     
/* 142 */     if ("symbol".equals(localRefElement.getLocalName())) {
/*     */ 
/*     */ 
/*     */       
/* 146 */       Element svgElement = document.createElementNS("http://www.w3.org/2000/svg", "svg");
/*     */ 
/*     */ 
/*     */       
/* 150 */       NamedNodeMap attrs = localRefElement.getAttributes();
/* 151 */       int len = attrs.getLength();
/* 152 */       for (int i = 0; i < len; i++) {
/* 153 */         Attr attr = (Attr)attrs.item(i);
/* 154 */         svgElement.setAttributeNS(attr.getNamespaceURI(), attr.getName(), attr.getValue());
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 159 */       Node n = localRefElement.getFirstChild();
/* 160 */       for (; n != null; 
/* 161 */         n = localRefElement.getFirstChild()) {
/* 162 */         svgElement.appendChild(n);
/*     */       }
/* 164 */       localRefElement = svgElement;
/*     */     } 
/*     */     
/* 167 */     if ("svg".equals(localRefElement.getLocalName())) {
/*     */       
/*     */       try {
/*     */ 
/*     */ 
/*     */         
/* 173 */         SVGOMAnimatedLength al = (SVGOMAnimatedLength)ue.getWidth();
/* 174 */         if (al.isSpecified()) {
/* 175 */           localRefElement.setAttributeNS((String)null, "width", al.getAnimVal().getValueAsString());
/*     */         }
/*     */ 
/*     */         
/* 179 */         al = (SVGOMAnimatedLength)ue.getHeight();
/* 180 */         if (al.isSpecified()) {
/* 181 */           localRefElement.setAttributeNS((String)null, "height", al.getAnimVal().getValueAsString());
/*     */         
/*     */         }
/*     */       }
/* 185 */       catch (LiveAttributeException ex) {
/* 186 */         throw new BridgeException(ctx, ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 192 */     SVGOMUseShadowRoot root = new SVGOMUseShadowRoot((AbstractDocument)document, e, isLocal);
/* 193 */     root.appendChild(localRefElement);
/*     */     
/* 195 */     if (gn == null) {
/* 196 */       gn = new CompositeGraphicsNode();
/* 197 */       associateSVGContext(ctx, e, this.node);
/*     */     } else {
/* 199 */       int s = gn.size();
/* 200 */       for (int i = 0; i < s; i++) {
/* 201 */         gn.remove(0);
/*     */       }
/*     */     } 
/* 204 */     Node oldRoot = ue.getCSSFirstChild();
/* 205 */     if (oldRoot != null) {
/* 206 */       disposeTree(oldRoot);
/*     */     }
/* 208 */     ue.setUseShadowTree(root);
/*     */     
/* 210 */     Element g = localRefElement;
/*     */ 
/*     */     
/* 213 */     CSSUtilities.computeStyleAndURIs(refElement, localRefElement, uri);
/*     */     
/* 215 */     GVTBuilder builder = ctx.getGVTBuilder();
/* 216 */     GraphicsNode refNode = builder.build(ctx, g);
/*     */ 
/*     */ 
/*     */     
/* 220 */     gn.getChildren().add(refNode);
/*     */     
/* 222 */     gn.setTransform(computeTransform((SVGTransformable)e, ctx));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 228 */     gn.setVisible(CSSUtilities.convertVisibility(e));
/*     */     
/* 230 */     RenderingHints hints = null;
/* 231 */     hints = CSSUtilities.convertColorRendering(e, hints);
/* 232 */     if (hints != null) {
/* 233 */       gn.setRenderingHints(hints);
/*     */     }
/*     */     
/* 236 */     Rectangle2D r = CSSUtilities.convertEnableBackground(e);
/* 237 */     if (r != null) {
/* 238 */       gn.setBackgroundEnable(r);
/*     */     }
/* 240 */     if (this.l != null) {
/*     */       
/* 242 */       NodeEventTarget target = this.l.target;
/* 243 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.l, true);
/*     */ 
/*     */       
/* 246 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.l, true);
/*     */ 
/*     */       
/* 249 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.l, true);
/*     */ 
/*     */       
/* 252 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.l, true);
/*     */ 
/*     */       
/* 255 */       this.l = null;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 262 */     if (isLocal && ctx.isDynamic()) {
/* 263 */       this.l = new ReferencedElementMutationListener();
/*     */       
/* 265 */       NodeEventTarget target = (NodeEventTarget)refElement;
/* 266 */       this.l.target = target;
/*     */       
/* 268 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.l, true, null);
/*     */ 
/*     */       
/* 271 */       theCtx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "DOMAttrModified", this.l, true);
/*     */ 
/*     */ 
/*     */       
/* 275 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.l, true, null);
/*     */ 
/*     */       
/* 278 */       theCtx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.l, true);
/*     */ 
/*     */ 
/*     */       
/* 282 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.l, true, null);
/*     */ 
/*     */       
/* 285 */       theCtx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.l, true);
/*     */ 
/*     */ 
/*     */       
/* 289 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.l, true, null);
/*     */ 
/*     */       
/* 292 */       theCtx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.l, true);
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 297 */     return gn;
/*     */   }
/*     */   
/*     */   public void dispose() {
/* 301 */     if (this.l != null) {
/*     */       
/* 303 */       NodeEventTarget target = this.l.target;
/* 304 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMAttrModified", this.l, true);
/*     */ 
/*     */       
/* 307 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeInserted", this.l, true);
/*     */ 
/*     */       
/* 310 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMNodeRemoved", this.l, true);
/*     */ 
/*     */       
/* 313 */       target.removeEventListenerNS("http://www.w3.org/2001/xml-events", "DOMCharacterDataModified", this.l, true);
/*     */ 
/*     */       
/* 316 */       this.l = null;
/*     */     } 
/*     */     
/* 319 */     SVGOMUseElement ue = (SVGOMUseElement)this.e;
/* 320 */     if (ue != null && ue.getCSSFirstChild() != null) {
/* 321 */       disposeTree(ue.getCSSFirstChild());
/*     */     }
/*     */     
/* 324 */     super.dispose();
/*     */     
/* 326 */     this.subCtx = null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AffineTransform computeTransform(SVGTransformable e, BridgeContext ctx) {
/* 335 */     AffineTransform at = super.computeTransform(e, ctx);
/* 336 */     SVGUseElement ue = (SVGUseElement)e;
/*     */     
/*     */     try {
/* 339 */       AbstractSVGAnimatedLength _x = (AbstractSVGAnimatedLength)ue.getX();
/*     */       
/* 341 */       float x = _x.getCheckedValue();
/*     */ 
/*     */       
/* 344 */       AbstractSVGAnimatedLength _y = (AbstractSVGAnimatedLength)ue.getY();
/*     */       
/* 346 */       float y = _y.getCheckedValue();
/*     */       
/* 348 */       AffineTransform xy = AffineTransform.getTranslateInstance(x, y);
/* 349 */       xy.preConcatenate(at);
/* 350 */       return xy;
/* 351 */     } catch (LiveAttributeException ex) {
/* 352 */       throw new BridgeException(ctx, ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsNode instantiateGraphicsNode() {
/* 361 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComposite() {
/* 368 */     return false;
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
/*     */   public void buildGraphicsNode(BridgeContext ctx, Element e, GraphicsNode node) {
/* 383 */     super.buildGraphicsNode(ctx, e, node);
/*     */     
/* 385 */     if (ctx.isInteractive()) {
/* 386 */       NodeEventTarget target = (NodeEventTarget)e;
/* 387 */       EventListener l = new CursorMouseOverListener(ctx);
/* 388 */       target.addEventListenerNS("http://www.w3.org/2001/xml-events", "mouseover", l, false, null);
/*     */ 
/*     */       
/* 391 */       ctx.storeEventListenerNS((EventTarget)target, "http://www.w3.org/2001/xml-events", "mouseover", l, false);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class CursorMouseOverListener
/*     */     implements EventListener
/*     */   {
/*     */     protected BridgeContext ctx;
/*     */ 
/*     */     
/*     */     public CursorMouseOverListener(BridgeContext ctx) {
/* 404 */       this.ctx = ctx;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 412 */       Element currentTarget = (Element)evt.getCurrentTarget();
/*     */       
/* 414 */       if (!CSSUtilities.isAutoCursor(currentTarget)) {
/*     */         
/* 416 */         Cursor cursor = CSSUtilities.convertCursor(currentTarget, this.ctx);
/* 417 */         if (cursor != null) {
/* 418 */           this.ctx.getUserAgent().setSVGCursor(cursor);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class ReferencedElementMutationListener
/*     */     implements EventListener
/*     */   {
/*     */     protected NodeEventTarget target;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void handleEvent(Event evt) {
/* 436 */       SVGUseElementBridge.this.buildCompositeGraphicsNode(SVGUseElementBridge.this.ctx, SVGUseElementBridge.this.e, (CompositeGraphicsNode)SVGUseElementBridge.this.node);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {
/*     */     try {
/* 448 */       String ns = alav.getNamespaceURI();
/* 449 */       String ln = alav.getLocalName();
/* 450 */       if (ns == null) {
/* 451 */         if (ln.equals("x") || ln.equals("y") || ln.equals("transform"))
/*     */         {
/*     */           
/* 454 */           this.node.setTransform(computeTransform((SVGTransformable)this.e, this.ctx));
/*     */           
/* 456 */           handleGeometryChanged();
/*     */         }
/* 458 */         else if (ln.equals("width") || ln.equals("height"))
/*     */         {
/* 460 */           buildCompositeGraphicsNode(this.ctx, this.e, (CompositeGraphicsNode)this.node);
/*     */         }
/*     */       
/* 463 */       } else if (ns.equals("http://www.w3.org/1999/xlink") && ln.equals("href")) {
/*     */         
/* 465 */         buildCompositeGraphicsNode(this.ctx, this.e, (CompositeGraphicsNode)this.node);
/*     */       }
/*     */     
/* 468 */     } catch (LiveAttributeException ex) {
/* 469 */       throw new BridgeException(this.ctx, ex);
/*     */     } 
/* 471 */     super.handleAnimatedAttributeChanged(alav);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGUseElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */