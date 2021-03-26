/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGAnimationContext;
/*     */ import org.apache.batik.dom.svg.SVGTestsSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimationElement;
/*     */ import org.w3c.dom.svg.SVGElement;
/*     */ import org.w3c.dom.svg.SVGStringList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGOMAnimationElement
/*     */   extends SVGOMElement
/*     */   implements SVGAnimationElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedBoolean externalResourcesRequired;
/*     */   
/*     */   static {
/*  49 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMElement.xmlTraitInformation);
/*     */     
/*  51 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */     
/*  53 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimationElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimationElement(String prefix, AbstractDocument owner) {
/*  73 */     super(prefix, owner);
/*  74 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  81 */     super.initializeAllLiveAttributes();
/*  82 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/*  89 */     this.externalResourcesRequired = createLiveAnimatedBoolean((String)null, "externalResourcesRequired", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getTargetElement() {
/*  98 */     return ((SVGAnimationContext)getSVGContext()).getTargetElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getStartTime() {
/* 105 */     return ((SVGAnimationContext)getSVGContext()).getStartTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getCurrentTime() {
/* 112 */     return ((SVGAnimationContext)getSVGContext()).getCurrentTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getSimpleDuration() throws DOMException {
/* 119 */     float dur = ((SVGAnimationContext)getSVGContext()).getSimpleDuration();
/* 120 */     if (dur == Float.POSITIVE_INFINITY) {
/* 121 */       throw createDOMException((short)9, "animation.dur.indefinite", null);
/*     */     }
/*     */ 
/*     */     
/* 125 */     return dur;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getHyperlinkBeginTime() {
/* 134 */     return ((SVGAnimationContext)getSVGContext()).getHyperlinkBeginTime();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean beginElement() throws DOMException {
/* 144 */     return ((SVGAnimationContext)getSVGContext()).beginElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean beginElementAt(float offset) throws DOMException {
/* 152 */     return ((SVGAnimationContext)getSVGContext()).beginElementAt(offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean endElement() throws DOMException {
/* 160 */     return ((SVGAnimationContext)getSVGContext()).endElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean endElementAt(float offset) throws DOMException {
/* 168 */     return ((SVGAnimationContext)getSVGContext()).endElementAt(offset);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 178 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredFeatures() {
/* 188 */     return SVGTestsSupport.getRequiredFeatures((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredExtensions() {
/* 196 */     return SVGTestsSupport.getRequiredExtensions((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getSystemLanguage() {
/* 204 */     return SVGTestsSupport.getSystemLanguage((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtension(String extension) {
/* 212 */     return SVGTestsSupport.hasExtension((Element)this, extension);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 219 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMAnimationElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */