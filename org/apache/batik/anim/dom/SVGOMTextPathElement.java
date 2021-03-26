/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGTextPathElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMTextPathElement
/*     */   extends SVGOMTextContentElement
/*     */   implements SVGTextPathElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  48 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMTextContentElement.xmlTraitInformation);
/*     */     
/*  50 */     t.put(null, "method", new TraitInformation(true, 15));
/*     */     
/*  52 */     t.put(null, "spacing", new TraitInformation(true, 15));
/*     */     
/*  54 */     t.put(null, "startOffset", new TraitInformation(true, 3));
/*     */     
/*  56 */     t.put("http://www.w3.org/1999/xlink", "href", new TraitInformation(true, 10));
/*     */     
/*  58 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   protected static final AttributeInitializer attributeInitializer = new AttributeInitializer(4); static {
/*  67 */     attributeInitializer.addAttribute("http://www.w3.org/2000/xmlns/", null, "xmlns:xlink", "http://www.w3.org/1999/xlink");
/*     */ 
/*     */     
/*  70 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "type", "simple");
/*     */     
/*  72 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "show", "other");
/*     */     
/*  74 */     attributeInitializer.addAttribute("http://www.w3.org/1999/xlink", "xlink", "actuate", "onLoad");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   protected static final String[] METHOD_VALUES = new String[] { "", "align", "stretch" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  90 */   protected static final String[] SPACING_VALUES = new String[] { "", "auto", "exact" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration method;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration spacing;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength startOffset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedString href;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMTextPathElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMTextPathElement(String prefix, AbstractDocument owner) {
/* 128 */     super(prefix, owner);
/* 129 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 136 */     super.initializeAllLiveAttributes();
/* 137 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 144 */     this.method = createLiveAnimatedEnumeration(null, "method", METHOD_VALUES, (short)1);
/*     */ 
/*     */     
/* 147 */     this.spacing = createLiveAnimatedEnumeration(null, "spacing", SPACING_VALUES, (short)2);
/*     */ 
/*     */     
/* 150 */     this.startOffset = createLiveAnimatedLength(null, "startOffset", "0", (short)0, false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 155 */     this.href = createLiveAnimatedString("http://www.w3.org/1999/xlink", "href");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 163 */     return "textPath";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getStartOffset() {
/* 170 */     return this.startOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getMethod() {
/* 177 */     return this.method;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getSpacing() {
/* 184 */     return this.spacing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getHref() {
/* 194 */     return this.href;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AttributeInitializer getAttributeInitializer() {
/* 202 */     return attributeInitializer;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 209 */     return (Node)new SVGOMTextPathElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 216 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMTextPathElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */