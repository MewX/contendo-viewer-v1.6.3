/*     */ package org.apache.batik.bridge;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.anim.dom.AnimatedLiveAttributeValue;
/*     */ import org.apache.batik.anim.dom.SVGOMDocument;
/*     */ import org.apache.batik.css.engine.CSSEngineEvent;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.apache.batik.gvt.GraphicsNode;
/*     */ import org.apache.batik.gvt.RootGraphicsNode;
/*     */ import org.w3c.dom.Document;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGDocumentBridge
/*     */   implements BridgeUpdateHandler, DocumentBridge, SVGContext
/*     */ {
/*     */   protected Document document;
/*     */   protected RootGraphicsNode node;
/*     */   protected BridgeContext ctx;
/*     */   
/*     */   public String getNamespaceURI() {
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  81 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Bridge getInstance() {
/*  88 */     return new SVGDocumentBridge();
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
/*     */   public RootGraphicsNode createGraphicsNode(BridgeContext ctx, Document doc) {
/* 104 */     RootGraphicsNode gn = new RootGraphicsNode();
/* 105 */     this.document = doc;
/* 106 */     this.node = gn;
/* 107 */     this.ctx = ctx;
/* 108 */     ((SVGOMDocument)doc).setSVGContext(this);
/* 109 */     return gn;
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
/*     */   public void buildGraphicsNode(BridgeContext ctx, Document doc, RootGraphicsNode node) {
/* 126 */     if (ctx.isDynamic()) {
/* 127 */       ctx.bind(doc, (GraphicsNode)node);
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
/*     */   public void handleDOMNodeInsertedEvent(MutationEvent evt) {
/* 143 */     if (evt.getTarget() instanceof Element) {
/* 144 */       Element childElt = (Element)evt.getTarget();
/*     */       
/* 146 */       GVTBuilder builder = this.ctx.getGVTBuilder();
/* 147 */       GraphicsNode childNode = builder.build(this.ctx, childElt);
/* 148 */       if (childNode == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/* 153 */       this.node.add(childNode);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void handleDOMNodeRemovedEvent(MutationEvent evt) {}
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
/* 192 */     ((SVGOMDocument)this.document).setSVGContext(null);
/* 193 */     this.ctx.unbind(this.document);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelUnitToMillimeter() {
/* 202 */     return this.ctx.getUserAgent().getPixelUnitToMillimeter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getPixelToMM() {
/* 211 */     return getPixelUnitToMillimeter();
/*     */   }
/*     */   public Rectangle2D getBBox() {
/* 214 */     return null;
/*     */   } public AffineTransform getScreenTransform() {
/* 216 */     return this.ctx.getUserAgent().getTransform();
/*     */   }
/*     */   public void setScreenTransform(AffineTransform at) {
/* 219 */     this.ctx.getUserAgent().setTransform(at);
/*     */   }
/* 221 */   public AffineTransform getCTM() { return null; }
/* 222 */   public AffineTransform getGlobalTransform() { return null; }
/* 223 */   public float getViewportWidth() { return 0.0F; }
/* 224 */   public float getViewportHeight() { return 0.0F; } public float getFontSize() {
/* 225 */     return 0.0F;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/SVGDocumentBridge.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */