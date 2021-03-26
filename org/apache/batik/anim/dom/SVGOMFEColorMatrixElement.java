/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumberList;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFEColorMatrixElement;
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
/*     */ public class SVGOMFEColorMatrixElement
/*     */   extends SVGOMFilterPrimitiveStandardAttributes
/*     */   implements SVGFEColorMatrixElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  46 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMFilterPrimitiveStandardAttributes.xmlTraitInformation);
/*     */     
/*  48 */     t.put(null, "in", new TraitInformation(true, 16));
/*     */     
/*  50 */     t.put(null, "type", new TraitInformation(true, 15));
/*     */     
/*  52 */     t.put(null, "values", new TraitInformation(true, 13));
/*     */     
/*  54 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  60 */   protected static final String[] TYPE_VALUES = new String[] { "", "matrix", "saturate", "hueRotate", "luminanceToAlpha" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedString in;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMFEColorMatrixElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMFEColorMatrixElement(String prefix, AbstractDocument owner) {
/*  95 */     super(prefix, owner);
/*  96 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 103 */     super.initializeAllLiveAttributes();
/* 104 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 111 */     this.in = createLiveAnimatedString(null, "in");
/* 112 */     this.type = createLiveAnimatedEnumeration(null, "type", TYPE_VALUES, (short)1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 121 */     return "feColorMatrix";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn1() {
/* 128 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getType() {
/* 135 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumberList getValues() {
/* 142 */     throw new UnsupportedOperationException("SVGFEColorMatrixElement.getValues is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 151 */     return (Node)new SVGOMFEColorMatrixElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 158 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFEColorMatrixElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */