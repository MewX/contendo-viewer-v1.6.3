/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGTestsSupport;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGCursorElement;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMCursorElement
/*     */   extends SVGOMURIReferenceElement
/*     */   implements SVGCursorElement
/*     */ {
/*  49 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(4); static {
/*  50 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  53 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  55 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "other");
/*     */     
/*  57 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  66 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMURIReferenceElement.xmlTraitInformation);
/*     */     
/*  68 */     t.put(null, "externalResourcesRequired", new TraitInformation(true, 49));
/*     */     
/*  70 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  72 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  74 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength x;
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength y;
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedBoolean externalResourcesRequired;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMCursorElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMCursorElement(String prefix, AbstractDocument owner) {
/* 104 */     super(prefix, owner);
/* 105 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 112 */     super.initializeAllLiveAttributes();
/* 113 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 120 */     this.x = createLiveAnimatedLength(null, "x", "0", (short)2, false);
/*     */ 
/*     */     
/* 123 */     this.y = createLiveAnimatedLength(null, "y", "0", (short)1, false);
/*     */ 
/*     */     
/* 126 */     this.externalResourcesRequired = createLiveAnimatedBoolean(null, "externalResourcesRequired", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 135 */     return "cursor";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 142 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 149 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getExternalResourcesRequired() {
/* 159 */     return this.externalResourcesRequired;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredFeatures() {
/* 169 */     return SVGTestsSupport.getRequiredFeatures((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getRequiredExtensions() {
/* 177 */     return SVGTestsSupport.getRequiredExtensions((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGStringList getSystemLanguage() {
/* 185 */     return SVGTestsSupport.getSystemLanguage((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasExtension(String extension) {
/* 193 */     return SVGTestsSupport.hasExtension((Element)this, extension);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 201 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 208 */     return (Node)new SVGOMCursorElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 215 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMCursorElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */