/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.util.XMLSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAngle;
/*     */ import org.w3c.dom.svg.SVGAnimatedAngle;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGAnimatedPreserveAspectRatio;
/*     */ import org.w3c.dom.svg.SVGAnimatedRect;
/*     */ import org.w3c.dom.svg.SVGMarkerElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMMarkerElement
/*     */   extends SVGStylableElement
/*     */   implements SVGMarkerElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  51 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGStylableElement.xmlTraitInformation);
/*     */     
/*  53 */     t.put(null, "refX", new TraitInformation(true, 3, (short)1));
/*     */     
/*  55 */     t.put(null, "refY", new TraitInformation(true, 3, (short)2));
/*     */     
/*  57 */     t.put(null, "markerWidth", new TraitInformation(true, 3, (short)1));
/*     */     
/*  59 */     t.put(null, "markerHeight", new TraitInformation(true, 3, (short)2));
/*     */     
/*  61 */     t.put(null, "markerUnits", new TraitInformation(true, 15));
/*     */     
/*  63 */     t.put(null, "orient", new TraitInformation(true, 15));
/*     */     
/*  65 */     t.put(null, "preserveAspectRatio", new TraitInformation(true, 32));
/*     */     
/*  67 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */     
/*  69 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(1); static {
/*  78 */     attributeInitializer.addAttribute(null, null, "preserveAspectRatio", "xMidYMid meet");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  87 */   protected static final String[] UNITS_VALUES = new String[] { "", "userSpaceOnUse", "stroke-width" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  96 */   protected static final String[] ORIENT_TYPE_VALUES = new String[] { "", "auto", "" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength refX;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength refY;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength markerWidth;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength markerHeight;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedMarkerOrientValue orient;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration markerUnits;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedPreserveAspectRatio preserveAspectRatio;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedRect viewBox;
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
/*     */   protected SVGOMMarkerElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMMarkerElement(String prefix, AbstractDocument owner) {
/* 159 */     super(prefix, owner);
/* 160 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 167 */     super.initializeAllLiveAttributes();
/* 168 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 175 */     this.refX = createLiveAnimatedLength((String)null, "refX", "0", (short)2, false);
/*     */ 
/*     */ 
/*     */     
/* 179 */     this.refY = createLiveAnimatedLength((String)null, "refY", "0", (short)1, false);
/*     */ 
/*     */ 
/*     */     
/* 183 */     this.markerWidth = createLiveAnimatedLength((String)null, "markerWidth", "3", (short)2, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 188 */     this.markerHeight = createLiveAnimatedLength((String)null, "markerHeight", "3", (short)1, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 193 */     this.orient = createLiveAnimatedMarkerOrientValue((String)null, "orient");
/*     */     
/* 195 */     this.markerUnits = createLiveAnimatedEnumeration((String)null, "markerUnits", UNITS_VALUES, (short)2);
/*     */ 
/*     */     
/* 198 */     this.preserveAspectRatio = createLiveAnimatedPreserveAspectRatio();
/*     */     
/* 200 */     this.viewBox = createLiveAnimatedRect((String)null, "viewBox", (String)null);
/* 201 */     this.externalResourcesRequired = createLiveAnimatedBoolean((String)null, "externalResourcesRequired", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 210 */     return "marker";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getRefX() {
/* 217 */     return this.refX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getRefY() {
/* 224 */     return this.refY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getMarkerUnits() {
/* 231 */     return this.markerUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getMarkerWidth() {
/* 238 */     return this.markerWidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getMarkerHeight() {
/* 245 */     return this.markerHeight;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getOrientType() {
/* 252 */     return this.orient.getAnimatedEnumeration();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedAngle getOrientAngle() {
/* 259 */     return this.orient.getAnimatedAngle();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOrientToAuto() {
/* 266 */     setAttributeNS(null, "orient", "auto");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOrientToAngle(SVGAngle angle) {
/* 274 */     setAttributeNS(null, "orient", angle.getValueAsString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedRect getViewBox() {
/* 284 */     return this.viewBox;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedPreserveAspectRatio getPreserveAspectRatio() {
/* 292 */     return this.preserveAspectRatio;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 302 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLlang() {
/* 311 */     return XMLSupport.getXMLLang((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLlang(String lang) {
/* 318 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:lang", lang);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getXMLspace() {
/* 325 */     return XMLSupport.getXMLSpace((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setXMLspace(String space) {
/* 332 */     setAttributeNS("http://www.w3.org/XML/1998/namespace", "xml:space", space);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 340 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 347 */     return (Node)new SVGOMMarkerElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 354 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMMarkerElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */