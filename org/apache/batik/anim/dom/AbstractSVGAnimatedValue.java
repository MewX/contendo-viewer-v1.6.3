/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.util.LinkedList;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGAnimatedValue
/*     */   implements AnimatedLiveAttributeValue
/*     */ {
/*     */   protected AbstractElement element;
/*     */   protected String namespaceURI;
/*     */   protected String localName;
/*     */   protected boolean hasAnimVal;
/*  58 */   protected LinkedList listeners = new LinkedList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSVGAnimatedValue(AbstractElement elt, String ns, String ln) {
/*  64 */     this.element = elt;
/*  65 */     this.namespaceURI = ns;
/*  66 */     this.localName = ln;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNamespaceURI() {
/*  73 */     return this.namespaceURI;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  80 */     return this.localName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSpecified() {
/*  89 */     return (this.hasAnimVal || this.element.hasAttributeNS(this.namespaceURI, this.localName));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract void updateAnimatedValue(AnimatableValue paramAnimatableValue);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addAnimatedAttributeListener(AnimatedAttributeListener aal) {
/* 101 */     if (!this.listeners.contains(aal)) {
/* 102 */       this.listeners.add(aal);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeAnimatedAttributeListener(AnimatedAttributeListener aal) {
/* 110 */     this.listeners.remove(aal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireBaseAttributeListeners() {
/* 117 */     if (this.element instanceof SVGOMElement) {
/* 118 */       ((SVGOMElement)this.element).fireBaseAttributeListeners(this.namespaceURI, this.localName);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void fireAnimatedAttributeListeners() {
/* 127 */     for (Object listener1 : this.listeners) {
/* 128 */       AnimatedAttributeListener listener = (AnimatedAttributeListener)listener1;
/*     */       
/* 130 */       listener.animatedAttributeChanged((Element)this.element, this);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AbstractSVGAnimatedValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */