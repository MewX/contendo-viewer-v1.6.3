/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFETileElement;
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
/*     */ public class SVGOMFETileElement
/*     */   extends SVGOMFilterPrimitiveStandardAttributes
/*     */   implements SVGFETileElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedString in;
/*     */   
/*     */   static {
/*  44 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMFilterPrimitiveStandardAttributes.xmlTraitInformation);
/*     */     
/*  46 */     t.put(null, "in", new TraitInformation(true, 16));
/*     */     
/*  48 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMFETileElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMFETileElement(String prefix, AbstractDocument owner) {
/*  68 */     super(prefix, owner);
/*  69 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  76 */     super.initializeAllLiveAttributes();
/*  77 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/*  84 */     this.in = createLiveAnimatedString(null, "in");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/*  91 */     return "feTile";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn1() {
/*  98 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 105 */     return (Node)new SVGOMFETileElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 112 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFETileElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */