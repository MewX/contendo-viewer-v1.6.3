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
/*     */ public abstract class AnimatableGenericSVGBridge
/*     */   extends AnimatableSVGBridge
/*     */   implements BridgeUpdateHandler, GenericBridge, SVGContext
/*     */ {
/*     */   public void handleElement(BridgeContext ctx, Element e) {
/*  51 */     if (ctx.isDynamic()) {
/*  52 */       this.e = e;
/*  53 */       this.ctx = ctx;
/*  54 */       ((SVGOMElement)e).setSVGContext(this);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelUnitToMillimeter() {
/*  64 */     return this.ctx.getUserAgent().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelToMM() {
/*  73 */     return getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Rectangle2D getBBox() {
/*  83 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getScreenTransform() {
/*  90 */     return this.ctx.getUserAgent().getTransform();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScreenTransform(AffineTransform at) {
/*  97 */     this.ctx.getUserAgent().setTransform(at);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getCTM() {
/* 106 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getGlobalTransform() {
/* 114 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getViewportWidth() {
/* 122 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getViewportHeight() {
/* 130 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getFontSize() {
/* 137 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void dispose() {
/* 143 */     ((SVGOMElement)this.e).setSVGContext(null);
/*     */   }
/*     */ 
/*     */   
/*     */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {}
/*     */ 
/*     */   
/*     */   public void handleDOMCharacterDataModified(MutationEvent evt) {}
/*     */   
/*     */   public void handleDOMNodeRemovedEvent(MutationEvent evt) {
/* 153 */     dispose();
/*     */   }
/*     */   
/*     */   public void handleDOMAttrModifiedEvent(MutationEvent evt) {}
/*     */   
/*     */   public void handleCSSEngineEvent(CSSEngineEvent evt) {}
/*     */   
/*     */   public void handleAnimatedAttributeChanged(AnimatedLiveAttributeValue alav) {}
/*     */   
/*     */   public void handleOtherAnimationChanged(String type) {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/AnimatableGenericSVGBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */