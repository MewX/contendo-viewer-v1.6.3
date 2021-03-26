/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.svg.SVGAnimatedLengthList;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumberList;
/*     */ import org.w3c.dom.svg.SVGTextPositioningElement;
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
/*     */ public abstract class SVGOMTextPositioningElement
/*     */   extends SVGOMTextContentElement
/*     */   implements SVGTextPositioningElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedLengthList x;
/*     */   protected SVGOMAnimatedLengthList y;
/*     */   protected SVGOMAnimatedLengthList dx;
/*     */   protected SVGOMAnimatedLengthList dy;
/*     */   protected SVGOMAnimatedNumberList rotate;
/*     */   
/*     */   static {
/*  44 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMTextContentElement.xmlTraitInformation);
/*     */     
/*  46 */     t.put(null, "x", new TraitInformation(true, 14, (short)1));
/*     */     
/*  48 */     t.put(null, "y", new TraitInformation(true, 14, (short)2));
/*     */     
/*  50 */     t.put(null, "dx", new TraitInformation(true, 14, (short)1));
/*     */     
/*  52 */     t.put(null, "dy", new TraitInformation(true, 14, (short)2));
/*     */     
/*  54 */     t.put(null, "rotate", new TraitInformation(true, 13));
/*     */     
/*  56 */     xmlTraitInformation = t;
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
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMTextPositioningElement() {}
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
/*     */   protected SVGOMTextPositioningElement(String prefix, AbstractDocument owner) {
/*  97 */     super(prefix, owner);
/*  98 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 105 */     super.initializeAllLiveAttributes();
/* 106 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 113 */     this.x = createLiveAnimatedLengthList(null, "x", getDefaultXValue(), true, (short)2);
/*     */ 
/*     */     
/* 116 */     this.y = createLiveAnimatedLengthList(null, "y", getDefaultYValue(), true, (short)1);
/*     */ 
/*     */     
/* 119 */     this.dx = createLiveAnimatedLengthList(null, "dx", "", true, (short)2);
/*     */ 
/*     */     
/* 122 */     this.dy = createLiveAnimatedLengthList(null, "dy", "", true, (short)1);
/*     */ 
/*     */     
/* 125 */     this.rotate = createLiveAnimatedNumberList(null, "rotate", "", true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLengthList getX() {
/* 133 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLengthList getY() {
/* 140 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLengthList getDx() {
/* 147 */     return this.dx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLengthList getDy() {
/* 154 */     return this.dy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumberList getRotate() {
/* 161 */     return this.rotate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDefaultXValue() {
/* 168 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDefaultYValue() {
/* 175 */     return "";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 182 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMTextPositioningElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */