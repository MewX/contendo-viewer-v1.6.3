/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import org.apache.batik.anim.values.AnimatableMotionPointValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGMotionAnimatableElement;
/*     */ import org.apache.batik.dom.svg.SVGTestsSupport;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedTransformList;
/*     */ import org.w3c.dom.svg.SVGElement;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGMatrix;
/*     */ import org.w3c.dom.svg.SVGRect;
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
/*     */ public abstract class SVGGraphicsElement
/*     */   extends SVGStylableElement
/*     */   implements SVGMotionAnimatableElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedTransformList transform;
/*     */   protected SVGOMAnimatedBoolean externalResourcesRequired;
/*     */   protected AffineTransform motionTransform;
/*     */   
/*     */   static {
/*  55 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGStylableElement.xmlTraitInformation);
/*     */     
/*  57 */     t.put(null, "transform", new TraitInformation(true, 9));
/*     */     
/*  59 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  67 */     xmlTraitInformation = t;
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
/*     */   protected SVGGraphicsElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGGraphicsElement(String prefix, AbstractDocument owner) {
/*  98 */     super(prefix, owner);
/*  99 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 106 */     super.initializeAllLiveAttributes();
/* 107 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 114 */     this.transform = createLiveAnimatedTransformList((String)null, "transform", "");
/*     */     
/* 116 */     this.externalResourcesRequired = createLiveAnimatedBoolean((String)null, "externalResourcesRequired", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 125 */     return xmlTraitInformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getNearestViewportElement() {
/* 135 */     return SVGLocatableSupport.getNearestViewportElement((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getFarthestViewportElement() {
/* 143 */     return SVGLocatableSupport.getFarthestViewportElement((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect getBBox() {
/* 151 */     return SVGLocatableSupport.getBBox((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getCTM() {
/* 159 */     return SVGLocatableSupport.getCTM((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getScreenCTM() {
/* 167 */     return SVGLocatableSupport.getScreenCTM((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getTransformToElement(SVGElement element) throws SVGException {
/* 176 */     return SVGLocatableSupport.getTransformToElement((Element)this, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedTransformList getTransform() {
/* 186 */     return this.transform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 196 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLlang() {
/* 205 */     return XMLSupport.getXMLLang((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLlang(String lang) {
/* 212 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 219 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) {
/* 226 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredFeatures() {
/* 236 */     return SVGTestsSupport.getRequiredFeatures((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredExtensions() {
/* 244 */     return SVGTestsSupport.getRequiredExtensions((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getSystemLanguage() {
/* 252 */     return SVGTestsSupport.getSystemLanguage((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtension(String extension) {
/* 260 */     return SVGTestsSupport.hasExtension((Element)this, extension);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getMotionTransform() {
/* 270 */     return this.motionTransform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateOtherValue(String type, AnimatableValue val) {
/* 279 */     if (type.equals("motion")) {
/* 280 */       if (this.motionTransform == null) {
/* 281 */         this.motionTransform = new AffineTransform();
/*     */       }
/* 283 */       if (val == null) {
/* 284 */         this.motionTransform.setToIdentity();
/*     */       } else {
/* 286 */         AnimatableMotionPointValue p = (AnimatableMotionPointValue)val;
/* 287 */         this.motionTransform.setToTranslation(p.getX(), p.getY());
/* 288 */         this.motionTransform.rotate(p.getAngle());
/*     */       } 
/* 290 */       SVGOMDocument d = (SVGOMDocument)this.ownerDocument;
/* 291 */       d.getAnimatedAttributeListener().otherAnimationChanged((Element)this, type);
/*     */     } else {
/* 293 */       super.updateOtherValue(type, val);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGGraphicsElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */