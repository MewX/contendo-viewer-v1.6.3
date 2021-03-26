/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFEOffsetElement;
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
/*     */ public class SVGOMFEOffsetElement
/*     */   extends SVGOMFilterPrimitiveStandardAttributes
/*     */   implements SVGFEOffsetElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedString in;
/*     */   protected SVGOMAnimatedNumber dx;
/*     */   protected SVGOMAnimatedNumber dy;
/*     */   
/*     */   static {
/*  45 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMFilterPrimitiveStandardAttributes.xmlTraitInformation);
/*     */     
/*  47 */     t.put(null, "in", new TraitInformation(true, 16));
/*     */     
/*  49 */     t.put(null, "dx", new TraitInformation(true, 2));
/*     */     
/*  51 */     t.put(null, "dy", new TraitInformation(true, 2));
/*     */     
/*  53 */     xmlTraitInformation = t;
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
/*     */   protected SVGOMFEOffsetElement() {}
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
/*     */   public SVGOMFEOffsetElement(String prefix, AbstractDocument owner) {
/*  83 */     super(prefix, owner);
/*  84 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  91 */     super.initializeAllLiveAttributes();
/*  92 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/*  99 */     this.in = createLiveAnimatedString(null, "in");
/* 100 */     this.dx = createLiveAnimatedNumber(null, "dx", 0.0F);
/* 101 */     this.dy = createLiveAnimatedNumber(null, "dy", 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 108 */     return "feOffset";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn1() {
/* 116 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getDx() {
/* 124 */     return this.dx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getDy() {
/* 132 */     return this.dy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 139 */     return (Node)new SVGOMFEOffsetElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 146 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFEOffsetElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */