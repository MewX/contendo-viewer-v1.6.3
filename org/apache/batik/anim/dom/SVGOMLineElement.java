/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGLineElement;
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
/*     */ public class SVGOMLineElement
/*     */   extends SVGGraphicsElement
/*     */   implements SVGLineElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedLength x1;
/*     */   protected SVGOMAnimatedLength y1;
/*     */   protected SVGOMAnimatedLength x2;
/*     */   protected SVGOMAnimatedLength y2;
/*     */   
/*     */   static {
/*  44 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*     */     
/*  46 */     t.put(null, "x1", new TraitInformation(true, 3, (short)1));
/*     */     
/*  48 */     t.put(null, "y1", new TraitInformation(true, 3, (short)2));
/*     */     
/*  50 */     t.put(null, "x2", new TraitInformation(true, 3, (short)1));
/*     */     
/*  52 */     t.put(null, "y2", new TraitInformation(true, 3, (short)2));
/*     */     
/*  54 */     xmlTraitInformation = t;
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
/*     */   protected SVGOMLineElement() {}
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
/*     */   public SVGOMLineElement(String prefix, AbstractDocument owner) {
/*  89 */     super(prefix, owner);
/*  90 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  97 */     super.initializeAllLiveAttributes();
/*  98 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 105 */     this.x1 = createLiveAnimatedLength(null, "x1", "0", (short)2, false);
/*     */ 
/*     */     
/* 108 */     this.y1 = createLiveAnimatedLength(null, "y1", "0", (short)1, false);
/*     */ 
/*     */     
/* 111 */     this.x2 = createLiveAnimatedLength(null, "x2", "0", (short)2, false);
/*     */ 
/*     */     
/* 114 */     this.y2 = createLiveAnimatedLength(null, "y2", "0", (short)1, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 123 */     return "line";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX1() {
/* 130 */     return this.x1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY1() {
/* 137 */     return this.y1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX2() {
/* 144 */     return this.x2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY2() {
/* 151 */     return this.y2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 158 */     return (Node)new SVGOMLineElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 165 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMLineElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */