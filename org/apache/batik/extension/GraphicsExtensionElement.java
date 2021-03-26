/*     */ package org.apache.batik.extension;
/*     */ 
/*     */ import org.apache.batik.anim.dom.SVGLocatableSupport;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimatedBoolean;
/*     */ import org.apache.batik.anim.dom.SVGOMAnimatedTransformList;
/*     */ import org.apache.batik.anim.dom.TraitInformation;
/*     */ import org.apache.batik.dom.AbstractDocument;
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
/*     */ public abstract class GraphicsExtensionElement
/*     */   extends StylableExtensionElement
/*     */   implements SVGTransformable
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  63 */     DoublyIndexedTable t = new DoublyIndexedTable(StylableExtensionElement.xmlTraitInformation);
/*     */     
/*  65 */     t.put(null, "transform", new TraitInformation(true, 9));
/*     */     
/*  67 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */     
/*  69 */     t.put(null, "requiredExtensions", new TraitInformation(false, 33));
/*     */     
/*  71 */     t.put(null, "requiredFeatures", new TraitInformation(false, 33));
/*     */     
/*  73 */     t.put(null, "systemLanguage", new TraitInformation(false, 46));
/*     */     
/*  75 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   protected SVGOMAnimatedTransformList transform = createLiveAnimatedTransformList(null, "transform", "");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   protected SVGOMAnimatedBoolean externalResourcesRequired = createLiveAnimatedBoolean(null, "externalResourcesRequired", false);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected GraphicsExtensionElement(String name, AbstractDocument owner) {
/* 103 */     super(name, owner);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getNearestViewportElement() {
/* 113 */     return SVGLocatableSupport.getNearestViewportElement((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getFarthestViewportElement() {
/* 121 */     return SVGLocatableSupport.getFarthestViewportElement((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect getBBox() {
/* 129 */     return SVGLocatableSupport.getBBox((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getCTM() {
/* 137 */     return SVGLocatableSupport.getCTM((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getScreenCTM() {
/* 145 */     return SVGLocatableSupport.getScreenCTM((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getTransformToElement(SVGElement element) throws SVGException {
/* 154 */     return SVGLocatableSupport.getTransformToElement((Element)this, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedTransformList getTransform() {
/* 164 */     return (SVGAnimatedTransformList)this.transform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 174 */     return (SVGAnimatedBoolean)this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLlang() {
/* 183 */     return XMLSupport.getXMLLang((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLlang(String lang) {
/* 190 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 197 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) {
/* 204 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredFeatures() {
/* 214 */     return SVGTestsSupport.getRequiredFeatures((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredExtensions() {
/* 222 */     return SVGTestsSupport.getRequiredExtensions((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getSystemLanguage() {
/* 230 */     return SVGTestsSupport.getSystemLanguage((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtension(String extension) {
/* 238 */     return SVGTestsSupport.hasExtension((Element)this, extension);
/*     */   }
/*     */   
/*     */   protected GraphicsExtensionElement() {}
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/extension/GraphicsExtensionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */