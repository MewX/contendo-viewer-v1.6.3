/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMElement;
/*     */ import org.apache.batik.css.engine.CSSEngineEvent;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.events.MutationEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGDescriptiveElementBridge
/*     */   extends AbstractSVGBridge
/*     */   implements BridgeUpdateHandler, GenericBridge, SVGContext
/*     */ {
/*     */   Element theElt;
/*     */   Element parent;
/*     */   BridgeContext theCtx;
/*     */   
/*     */   public void handleElement(BridgeContext ctx, Element e) {
/*  59 */     UserAgent ua = ctx.getUserAgent();
/*  60 */     ua.handleElement(e, Boolean.TRUE);
/*     */     
/*  62 */     if (ctx.isDynamic()) {
/*     */       
/*  64 */       SVGDescriptiveElementBridge b = (SVGDescriptiveElementBridge)getInstance();
/*  65 */       b.theElt = e;
/*  66 */       b.parent = (Element)e.getParentNode();
/*  67 */       b.theCtx = ctx;
/*  68 */       ((SVGOMElement)e).setSVGContext(b);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/*  76 */     UserAgent ua = this.theCtx.getUserAgent();
/*  77 */     ((SVGOMElement)this.theElt).setSVGContext(null);
/*  78 */     ua.handleElement(this.theElt, this.parent);
/*  79 */     this.theElt = null;
/*  80 */     this.parent = null;
/*     */   }
/*     */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {
/*  83 */     UserAgent ua = this.theCtx.getUserAgent();
/*  84 */     ua.handleElement(this.theElt, Boolean.TRUE);
/*     */   }
/*     */   public void handleDOMCharacterDataModified(MutationEvent evt) {
/*  87 */     UserAgent ua = this.theCtx.getUserAgent();
/*  88 */     ua.handleElement(this.theElt, Boolean.TRUE);
/*     */   }
/*     */   
/*     */   public void handleDOMNodeRemovedEvent(MutationEvent evt) {
/*  92 */     dispose();
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleDOMAttrModifiedEvent(MutationEvent evt) {}
/*     */ 
/*     */   
/*     */   public void handleCSSEngineEvent(CSSEngineEvent evt) {}
/*     */ 
/*     */   
/*     */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {}
/*     */ 
/*     */   
/*     */   public void handleOtherAnimationChanged(String type) {}
/*     */   
/*     */   public float getPixelUnitToMillimeter() {
/* 108 */     return this.theCtx.getUserAgent().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelToMM() {
/* 117 */     return getPixelUnitToMillimeter();
/*     */   }
/*     */   
/*     */   public Rectangle2D getBBox() {
/* 121 */     return null;
/*     */   } public AffineTransform getScreenTransform() {
/* 123 */     return this.theCtx.getUserAgent().getTransform();
/*     */   }
/*     */   public void setScreenTransform(AffineTransform at) {
/* 126 */     this.theCtx.getUserAgent().setTransform(at);
/*     */   }
/* 128 */   public AffineTransform getCTM() { return null; } public AffineTransform getGlobalTransform() {
/* 129 */     return null;
/*     */   } public float getViewportWidth() {
/* 131 */     return this.theCtx.getBlockWidth(this.theElt);
/*     */   }
/*     */   public float getViewportHeight() {
/* 134 */     return this.theCtx.getBlockHeight(this.theElt);
/*     */   } public float getFontSize() {
/* 136 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGDescriptiveElementBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */