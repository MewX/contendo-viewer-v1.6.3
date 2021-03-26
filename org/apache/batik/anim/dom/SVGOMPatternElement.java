/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGTestsSupport;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGAnimatedRect;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGAnimatedTransformList;
/*     */ import org.w3c.dom.svg.SVGPatternElement;
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
/*     */ 
/*     */ public class SVGOMPatternElement
/*     */   extends SVGStylableElement
/*     */   implements SVGPatternElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  54 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGStylableElement.xmlTraitInformation);
/*     */     
/*  56 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  58 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  60 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  62 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
/*     */     
/*  64 */     t.put(null, "patternUnits", new TraitInformation(true, 15));
/*     */     
/*  66 */     t.put(null, "patternContentUnits", new TraitInformation(true, 15));
/*     */     
/*  68 */     t.put(null, "patternTransform", new TraitInformation(true, 9));
/*     */     
/*  70 */     t.put(null, "viewBox", new TraitInformation(true, 13));
/*     */     
/*  72 */     t.put(null, "preserveAspectRatio", new TraitInformation(true, 32));
/*     */     
/*  74 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */     
/*  76 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  84 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(5); static {
/*  85 */     attributeInitializer.addAttribute(null, null, "preserveAspectRatio", "xMidYMid meet");
/*     */ 
/*     */     
/*  88 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  91 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  93 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "other");
/*     */     
/*  95 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 102 */   protected static final String[] UNITS_VALUES = new String[] { "", "userSpaceOnUse", "objectBoundingBox" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength x;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength y;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength width;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength height;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration patternUnits;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration patternContentUnits;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedString href;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedBoolean externalResourcesRequired;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedPreserveAspectRatio preserveAspectRatio;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMPatternElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMPatternElement(String prefix, AbstractDocument owner) {
/* 166 */     super(prefix, owner);
/* 167 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 174 */     super.initializeAllLiveAttributes();
/* 175 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 182 */     this.x = createLiveAnimatedLength((String)null, "x", "0", (short)2, false);
/*     */ 
/*     */     
/* 185 */     this.y = createLiveAnimatedLength((String)null, "y", "0", (short)1, false);
/*     */ 
/*     */     
/* 188 */     this.width = createLiveAnimatedLength((String)null, "width", "0", (short)2, true);
/*     */ 
/*     */ 
/*     */     
/* 192 */     this.height = createLiveAnimatedLength((String)null, "height", "0", (short)1, true);
/*     */ 
/*     */ 
/*     */     
/* 196 */     this.patternUnits = createLiveAnimatedEnumeration((String)null, "patternUnits", UNITS_VALUES, (short)2);
/*     */ 
/*     */     
/* 199 */     this.patternContentUnits = createLiveAnimatedEnumeration((String)null, "patternContentUnits", UNITS_VALUES, (short)1);
/*     */ 
/*     */ 
/*     */     
/* 203 */     this.href = createLiveAnimatedString("http://www.w3.org/1999/xlink", "href");
/*     */     
/* 205 */     this.externalResourcesRequired = createLiveAnimatedBoolean((String)null, "externalResourcesRequired", false);
/*     */ 
/*     */     
/* 208 */     this.preserveAspectRatio = createLiveAnimatedPreserveAspectRatio();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 215 */     return "pattern";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedTransformList getPatternTransform() {
/* 222 */     throw new UnsupportedOperationException("SVGPatternElement.getPatternTransform is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getPatternUnits() {
/* 230 */     return this.patternUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getPatternContentUnits() {
/* 238 */     return this.patternContentUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 245 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 252 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 259 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 267 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 274 */     return xmlTraitInformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getHref() {
/* 284 */     return this.href;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedRect getViewBox() {
/* 294 */     throw new UnsupportedOperationException("SVGFitToViewBox.getViewBox is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedPreserveAspectRatio getPreserveAspectRatio() {
/* 303 */     return this.preserveAspectRatio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 313 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLlang() {
/* 322 */     return XMLSupport.getXMLLang((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLlang(String lang) {
/* 329 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 336 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) {
/* 343 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredFeatures() {
/* 353 */     return SVGTestsSupport.getRequiredFeatures((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredExtensions() {
/* 361 */     return SVGTestsSupport.getRequiredExtensions((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getSystemLanguage() {
/* 369 */     return SVGTestsSupport.getSystemLanguage((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtension(String extension) {
/* 377 */     return SVGTestsSupport.hasExtension((Element)this, extension);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 385 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 392 */     return (Node)new SVGOMPatternElement();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMPatternElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */