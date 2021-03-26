/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFESpecularLightingElement;
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
/*     */ public class SVGOMFESpecularLightingElement
/*     */   extends SVGOMFilterPrimitiveStandardAttributes
/*     */   implements SVGFESpecularLightingElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedString in;
/*     */   protected SVGOMAnimatedNumber surfaceScale;
/*     */   protected SVGOMAnimatedNumber specularConstant;
/*     */   protected SVGOMAnimatedNumber specularExponent;
/*     */   
/*     */   static {
/*  45 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMFilterPrimitiveStandardAttributes.xmlTraitInformation);
/*     */     
/*  47 */     t.put(null, "in", new TraitInformation(true, 16));
/*     */     
/*  49 */     t.put(null, "surfaceScale", new TraitInformation(true, 2));
/*     */     
/*  51 */     t.put(null, "specularConstant", new TraitInformation(true, 2));
/*     */     
/*  53 */     t.put(null, "specularExponent", new TraitInformation(true, 2));
/*     */     
/*  55 */     xmlTraitInformation = t;
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
/*     */ 
/*     */   
/*     */   protected SVGOMFESpecularLightingElement() {}
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
/*     */   public SVGOMFESpecularLightingElement(String prefix, AbstractDocument owner) {
/*  91 */     super(prefix, owner);
/*  92 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  99 */     super.initializeAllLiveAttributes();
/* 100 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 107 */     this.in = createLiveAnimatedString(null, "in");
/* 108 */     this.surfaceScale = createLiveAnimatedNumber(null, "surfaceScale", 1.0F);
/*     */     
/* 110 */     this.specularConstant = createLiveAnimatedNumber(null, "specularConstant", 1.0F);
/*     */     
/* 112 */     this.specularExponent = createLiveAnimatedNumber(null, "specularExponent", 1.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 120 */     return "feSpecularLighting";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn1() {
/* 127 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getSurfaceScale() {
/* 135 */     return this.surfaceScale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getSpecularConstant() {
/* 143 */     return this.specularConstant;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getSpecularExponent() {
/* 151 */     return this.specularExponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 158 */     return (Node)new SVGOMFESpecularLightingElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 165 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFESpecularLightingElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */