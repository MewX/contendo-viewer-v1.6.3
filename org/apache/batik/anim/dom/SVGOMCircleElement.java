/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGCircleElement;
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
/*     */ public class SVGOMCircleElement
/*     */   extends SVGGraphicsElement
/*     */   implements SVGCircleElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedLength cx;
/*     */   protected SVGOMAnimatedLength cy;
/*     */   protected SVGOMAnimatedLength r;
/*     */   
/*     */   static {
/*  44 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*     */     
/*  46 */     t.put(null, "cx", new TraitInformation(true, 3, (short)1));
/*     */     
/*  48 */     t.put(null, "cy", new TraitInformation(true, 3, (short)2));
/*     */     
/*  50 */     t.put(null, "r", new TraitInformation(true, 3, (short)3));
/*     */     
/*  52 */     xmlTraitInformation = t;
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
/*     */   protected SVGOMCircleElement() {}
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
/*     */   public SVGOMCircleElement(String prefix, AbstractDocument owner) {
/*  82 */     super(prefix, owner);
/*  83 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  90 */     super.initializeAllLiveAttributes();
/*  91 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/*  98 */     this.cx = createLiveAnimatedLength(null, "cx", "0", (short)2, false);
/*     */ 
/*     */     
/* 101 */     this.cy = createLiveAnimatedLength(null, "cy", "0", (short)1, false);
/*     */ 
/*     */     
/* 104 */     this.r = createLiveAnimatedLength(null, "r", null, (short)0, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 113 */     return "circle";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getCx() {
/* 120 */     return this.cx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getCy() {
/* 127 */     return this.cy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getR() {
/* 134 */     return this.r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 141 */     return (Node)new SVGOMCircleElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 148 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMCircleElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */