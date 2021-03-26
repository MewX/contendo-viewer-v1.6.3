/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import java.util.Calendar;
/*     */ import org.apache.batik.anim.AbstractAnimation;
/*     */ import org.apache.batik.anim.dom.AnimatableElement;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.AnimationTarget;
/*     */ import org.apache.batik.anim.dom.AnimationTargetListener;
/*     */ import org.apache.batik.anim.dom.SVGOMElement;
/*     */ import org.apache.batik.anim.timing.TimedElement;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.css.engine.CSSEngineEvent;
/*     */ import org.apache.batik.dom.svg.SVGAnimationContext;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.dom.util.XLinkSupport;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.events.EventTarget;
/*     */ import org.w3c.dom.events.MutationEvent;
/*     */ import org.w3c.dom.svg.SVGElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGAnimationElementBridge
/*     */   extends AbstractSVGBridge
/*     */   implements AnimatableElement, BridgeUpdateHandler, GenericBridge, SVGAnimationContext
/*     */ {
/*     */   protected SVGOMElement element;
/*     */   protected BridgeContext ctx;
/*     */   protected SVGAnimationEngine eng;
/*     */   protected TimedElement timedElement;
/*     */   protected AbstractAnimation animation;
/*     */   protected String attributeNamespaceURI;
/*     */   protected String attributeLocalName;
/*     */   protected short animationType;
/*     */   protected SVGOMElement targetElement;
/*     */   protected AnimationTarget animationTarget;
/*     */   
/*     */   public TimedElement getTimedElement() {
/* 115 */     return this.timedElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getUnderlyingValue() {
/* 126 */     if (this.animationType == 0) {
/* 127 */       return this.animationTarget.getUnderlyingValue(this.attributeNamespaceURI, this.attributeLocalName);
/*     */     }
/*     */     
/* 130 */     return this.eng.getUnderlyingCSSValue((Element)this.element, this.animationTarget, this.attributeLocalName);
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
/*     */   public void handleElement(BridgeContext ctx, Element e) {
/* 145 */     if (ctx.isDynamic() && BridgeContext.getSVGContext(e) == null) {
/* 146 */       SVGAnimationElementBridge b = (SVGAnimationElementBridge)getInstance();
/*     */       
/* 148 */       b.element = (SVGOMElement)e;
/* 149 */       b.ctx = ctx;
/* 150 */       b.eng = ctx.getAnimationEngine();
/* 151 */       b.element.setSVGContext((SVGContext)b);
/* 152 */       if (b.eng.hasStarted()) {
/* 153 */         b.initializeAnimation();
/* 154 */         b.initializeTimedElement();
/*     */       } else {
/* 156 */         b.eng.addInitialBridge(b);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAnimation() {
/*     */     Node t;
/*     */     int type;
/* 167 */     String uri = XLinkSupport.getXLinkHref((Element)this.element);
/*     */     
/* 169 */     if (uri.length() == 0) {
/* 170 */       t = this.element.getParentNode();
/*     */     } else {
/* 172 */       t = this.ctx.getReferencedElement((Element)this.element, uri);
/* 173 */       if (t.getOwnerDocument() != this.element.getOwnerDocument()) {
/* 174 */         throw new BridgeException(this.ctx, this.element, "uri.badTarget", new Object[] { uri });
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 179 */     this.animationTarget = null;
/* 180 */     if (t instanceof SVGOMElement) {
/* 181 */       this.targetElement = (SVGOMElement)t;
/* 182 */       this.animationTarget = (AnimationTarget)this.targetElement;
/*     */     } 
/* 184 */     if (this.animationTarget == null) {
/* 185 */       throw new BridgeException(this.ctx, this.element, "uri.badTarget", new Object[] { uri });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 191 */     String an = this.element.getAttributeNS(null, "attributeName");
/* 192 */     int ci = an.indexOf(':');
/* 193 */     if (ci == -1) {
/* 194 */       if (this.element.hasProperty(an)) {
/* 195 */         this.animationType = 1;
/* 196 */         this.attributeLocalName = an;
/*     */       } else {
/* 198 */         this.animationType = 0;
/* 199 */         this.attributeLocalName = an;
/*     */       } 
/*     */     } else {
/* 202 */       this.animationType = 0;
/* 203 */       String prefix = an.substring(0, ci);
/* 204 */       this.attributeNamespaceURI = this.element.lookupNamespaceURI(prefix);
/* 205 */       this.attributeLocalName = an.substring(ci + 1);
/*     */     } 
/* 207 */     if ((this.animationType == 1 && !this.targetElement.isPropertyAnimatable(this.attributeLocalName)) || (this.animationType == 0 && !this.targetElement.isAttributeAnimatable(this.attributeNamespaceURI, this.attributeLocalName)))
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 212 */       throw new BridgeException(this.ctx, this.element, "attribute.not.animatable", new Object[] { this.targetElement.getNodeName(), an });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 220 */     if (this.animationType == 1) {
/* 221 */       type = this.targetElement.getPropertyType(this.attributeLocalName);
/*     */     } else {
/* 223 */       type = this.targetElement.getAttributeType(this.attributeNamespaceURI, this.attributeLocalName);
/*     */     } 
/*     */     
/* 226 */     if (!canAnimateType(type)) {
/* 227 */       throw new BridgeException(this.ctx, this.element, "type.not.animatable", new Object[] { this.targetElement.getNodeName(), an, this.element.getNodeName() });
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 234 */     this.timedElement = createTimedElement();
/* 235 */     this.animation = createAnimation(this.animationTarget);
/* 236 */     this.eng.addAnimation(this.animationTarget, this.animationType, this.attributeNamespaceURI, this.attributeLocalName, this.animation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract boolean canAnimateType(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean checkValueType(AnimatableValue v) {
/* 252 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeTimedElement() {
/* 260 */     initializeTimedElement(this.timedElement);
/* 261 */     this.timedElement.initialize();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected TimedElement createTimedElement() {
/* 268 */     return new SVGTimedElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract AbstractAnimation createAnimation(AnimationTarget paramAnimationTarget);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AnimatableValue parseAnimatableValue(String an) {
/* 280 */     if (!this.element.hasAttributeNS(null, an)) {
/* 281 */       return null;
/*     */     }
/* 283 */     String s = this.element.getAttributeNS(null, an);
/* 284 */     AnimatableValue val = this.eng.parseAnimatableValue((Element)this.element, this.animationTarget, this.attributeNamespaceURI, this.attributeLocalName, (this.animationType == 1), s);
/*     */ 
/*     */ 
/*     */     
/* 288 */     if (!checkValueType(val)) {
/* 289 */       throw new BridgeException(this.ctx, this.element, "attribute.malformed", new Object[] { an, s });
/*     */     }
/*     */ 
/*     */     
/* 293 */     return val;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeTimedElement(TimedElement timedElement) {
/* 300 */     timedElement.parseAttributes(this.element.getAttributeNS(null, "begin"), this.element.getAttributeNS(null, "dur"), this.element.getAttributeNS(null, "end"), this.element.getAttributeNS(null, "min"), this.element.getAttributeNS(null, "max"), this.element.getAttributeNS(null, "repeatCount"), this.element.getAttributeNS(null, "repeatDur"), this.element.getAttributeNS(null, "fill"), this.element.getAttributeNS(null, "restart"));
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
/*     */   public void handleDOMAttrModifiedEvent(MutationEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMNodeRemovedEvent(MutationEvent evt) {
/* 330 */     this.element.setSVGContext(null);
/* 331 */     dispose();
/*     */   }
/*     */ 
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
/*     */   public void handleCSSEngineEvent(CSSEngineEvent evt) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleOtherAnimationChanged(String type) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 364 */     if (this.element.getSVGContext() == null) {
/*     */       
/* 366 */       this.eng.removeAnimation(this.animation);
/* 367 */       this.timedElement.deinitialize();
/* 368 */       this.timedElement = null;
/* 369 */       this.element = null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelUnitToMillimeter() {
/* 379 */     return this.ctx.getUserAgent().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelToMM() {
/* 388 */     return getPixelUnitToMillimeter();
/*     */   }
/*     */   
/*     */   public Rectangle2D getBBox() {
/* 392 */     return null;
/*     */   } public AffineTransform getScreenTransform() {
/* 394 */     return this.ctx.getUserAgent().getTransform();
/*     */   }
/*     */   public void setScreenTransform(AffineTransform at) {
/* 397 */     this.ctx.getUserAgent().setTransform(at);
/*     */   }
/* 399 */   public AffineTransform getCTM() { return null; } public AffineTransform getGlobalTransform() {
/* 400 */     return null;
/*     */   } public float getViewportWidth() {
/* 402 */     return this.ctx.getBlockWidth((Element)this.element);
/*     */   }
/*     */   public float getViewportHeight() {
/* 405 */     return this.ctx.getBlockHeight((Element)this.element);
/*     */   } public float getFontSize() {
/* 407 */     return 0.0F;
/*     */   } public float svgToUserSpace(float v, int type, int pcInterp) {
/* 409 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTargetListener(String pn, AnimationTargetListener l) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeTargetListener(String pn, AnimationTargetListener l) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getTargetElement() {
/* 431 */     return (SVGElement)this.targetElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStartTime() {
/* 439 */     return this.timedElement.getCurrentBeginTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCurrentTime() {
/* 447 */     return this.timedElement.getLastSampleTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSimpleDuration() {
/* 457 */     return this.timedElement.getSimpleDur();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHyperlinkBeginTime() {
/* 466 */     return this.timedElement.getHyperlinkBeginTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean beginElement() throws DOMException {
/* 476 */     this.timedElement.beginElement();
/* 477 */     return this.timedElement.canBegin();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean beginElementAt(float offset) throws DOMException {
/* 485 */     this.timedElement.beginElement(offset);
/*     */ 
/*     */     
/* 488 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean endElement() throws DOMException {
/* 496 */     this.timedElement.endElement();
/* 497 */     return this.timedElement.canEnd();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean endElementAt(float offset) throws DOMException {
/* 505 */     this.timedElement.endElement(offset);
/*     */ 
/*     */     
/* 508 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isConstantAnimation() {
/* 515 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class SVGTimedElement
/*     */     extends TimedElement
/*     */   {
/*     */     public Element getElement() {
/* 527 */       return (Element)SVGAnimationElementBridge.this.element;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void fireTimeEvent(String eventType, Calendar time, int detail) {
/* 538 */       AnimationSupport.fireTimeEvent((EventTarget)SVGAnimationElementBridge.this.element, eventType, time, detail);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void toActive(float begin) {
/* 548 */       SVGAnimationElementBridge.this.eng.toActive(SVGAnimationElementBridge.this.animation, begin);
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
/*     */     protected void toInactive(boolean stillActive, boolean isFrozen) {
/* 560 */       SVGAnimationElementBridge.this.eng.toInactive(SVGAnimationElementBridge.this.animation, isFrozen);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void removeFill() {
/* 567 */       SVGAnimationElementBridge.this.eng.removeFill(SVGAnimationElementBridge.this.animation);
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
/*     */     protected void sampledAt(float simpleTime, float simpleDur, int repeatIteration) {
/* 580 */       SVGAnimationElementBridge.this.eng.sampledAt(SVGAnimationElementBridge.this.animation, simpleTime, simpleDur, repeatIteration);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void sampledLastValue(int repeatIteration) {
/* 590 */       SVGAnimationElementBridge.this.eng.sampledLastValue(SVGAnimationElementBridge.this.animation, repeatIteration);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected TimedElement getTimedElementById(String id) {
/* 597 */       return AnimationSupport.getTimedElementById(id, (Node)SVGAnimationElementBridge.this.element);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected EventTarget getEventTargetById(String id) {
/* 604 */       return AnimationSupport.getEventTargetById(id, (Node)SVGAnimationElementBridge.this.element);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected EventTarget getRootEventTarget() {
/* 612 */       return (EventTarget)SVGAnimationElementBridge.this.element.getOwnerDocument();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected EventTarget getAnimationEventTarget() {
/* 620 */       return (EventTarget)SVGAnimationElementBridge.this.targetElement;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isBefore(TimedElement other) {
/* 628 */       Element e = ((SVGTimedElement)other).getElement();
/* 629 */       int pos = SVGAnimationElementBridge.this.element.compareDocumentPosition(e);
/* 630 */       return ((pos & 0x2) != 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 637 */       if (SVGAnimationElementBridge.this.element != null) {
/* 638 */         String id = SVGAnimationElementBridge.this.element.getAttributeNS(null, "id");
/* 639 */         if (id.length() != 0) {
/* 640 */           return id;
/*     */         }
/*     */       } 
/* 643 */       return super.toString();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean isConstantAnimation() {
/* 651 */       return SVGAnimationElementBridge.this.isConstantAnimation();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGAnimationElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */