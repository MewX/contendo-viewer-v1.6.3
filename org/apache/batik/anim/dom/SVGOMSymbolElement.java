/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGZoomAndPanSupport;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGAnimatedRect;
/*     */ import org.w3c.dom.svg.SVGSymbolElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMSymbolElement
/*     */   extends SVGStylableElement
/*     */   implements SVGSymbolElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedPreserveAspectRatio preserveAspectRatio;
/*     */   
/*     */   static {
/*  48 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGStylableElement.xmlTraitInformation);
/*     */     
/*  50 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */     
/*  52 */     t.put(null, "preserveAspectRatio", new TraitInformation(true, 32));
/*     */     
/*  54 */     t.put(null, "viewBox", new TraitInformation(true, 13));
/*     */     
/*  56 */     xmlTraitInformation = t;
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
/*  69 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(1); static {
/*  70 */     attributeInitializer.addAttribute(null, null, "preserveAspectRatio", "xMidYMid meet");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedBoolean externalResourcesRequired;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMSymbolElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMSymbolElement(String prefix, AbstractDocument owner) {
/*  93 */     super(prefix, owner);
/*  94 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 101 */     super.initializeAllLiveAttributes();
/* 102 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 109 */     this.preserveAspectRatio = createLiveAnimatedPreserveAspectRatio();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 116 */     return "symbol";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLlang() {
/* 125 */     return XMLSupport.getXMLLang((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLlang(String lang) {
/* 132 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 139 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) {
/* 146 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getZoomAndPan() {
/* 156 */     return SVGZoomAndPanSupport.getZoomAndPan((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setZoomAndPan(short val) {
/* 164 */     SVGZoomAndPanSupport.setZoomAndPan((Element)this, val);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedRect getViewBox() {
/* 173 */     throw new UnsupportedOperationException("SVGFitToViewBox.getViewBox is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedPreserveAspectRatio getPreserveAspectRatio() {
/* 182 */     return this.preserveAspectRatio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 192 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 200 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 207 */     return (Node)new SVGOMSymbolElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 214 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMSymbolElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */