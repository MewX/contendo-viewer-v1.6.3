/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedInteger;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFilterElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMFilterElement
/*     */   extends SVGStylableElement
/*     */   implements SVGFilterElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  50 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGStylableElement.xmlTraitInformation);
/*     */     
/*  52 */     t.put(null, "filterUnits", new TraitInformation(true, 15));
/*     */     
/*  54 */     t.put(null, "primitiveUnits", new TraitInformation(true, 15));
/*     */     
/*  56 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  58 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  60 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  62 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
/*     */     
/*  64 */     t.put(null, "filterRes", new TraitInformation(true, 4));
/*     */     
/*  66 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  74 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(4); static {
/*  75 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  78 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  80 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "other");
/*     */     
/*  82 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  89 */   protected static final String[] UNITS_VALUES = new String[] { "", "userSpaceOnUse", "objectBoundingBox" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration filterUnits;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration primitiveUnits;
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
/*     */   protected SVGOMAnimatedLength height;
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
/*     */   
/*     */   protected SVGOMFilterElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMFilterElement(String prefix, AbstractDocument owner) {
/* 147 */     super(prefix, owner);
/* 148 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 155 */     super.initializeAllLiveAttributes();
/* 156 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 163 */     this.filterUnits = createLiveAnimatedEnumeration((String)null, "filterUnits", UNITS_VALUES, (short)2);
/*     */ 
/*     */     
/* 166 */     this.primitiveUnits = createLiveAnimatedEnumeration((String)null, "primitiveUnits", UNITS_VALUES, (short)1);
/*     */ 
/*     */     
/* 169 */     this.x = createLiveAnimatedLength((String)null, "x", "-10%", (short)2, false);
/*     */ 
/*     */     
/* 172 */     this.y = createLiveAnimatedLength((String)null, "y", "-10%", (short)1, false);
/*     */ 
/*     */     
/* 175 */     this.width = createLiveAnimatedLength((String)null, "width", "120%", (short)2, true);
/*     */ 
/*     */ 
/*     */     
/* 179 */     this.height = createLiveAnimatedLength((String)null, "height", "120%", (short)1, true);
/*     */ 
/*     */ 
/*     */     
/* 183 */     this.href = createLiveAnimatedString("http://www.w3.org/1999/xlink", "href");
/*     */     
/* 185 */     this.externalResourcesRequired = createLiveAnimatedBoolean((String)null, "externalResourcesRequired", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 194 */     return "filter";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getFilterUnits() {
/* 201 */     return this.filterUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getPrimitiveUnits() {
/* 208 */     return this.primitiveUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 215 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 222 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 229 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 236 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedInteger getFilterResX() {
/* 243 */     throw new UnsupportedOperationException("SVGFilterElement.getFilterResX is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedInteger getFilterResY() {
/* 251 */     throw new UnsupportedOperationException("SVGFilterElement.getFilterResY is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterRes(int filterResX, int filterResY) {
/* 259 */     throw new UnsupportedOperationException("SVGFilterElement.setFilterRes is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getHref() {
/* 269 */     return this.href;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 279 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 286 */     return xmlTraitInformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLlang() {
/* 295 */     return XMLSupport.getXMLLang((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLlang(String lang) {
/* 302 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 309 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) {
/* 316 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 324 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 331 */     return (Node)new SVGOMFilterElement();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFilterElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */