/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFilterPrimitiveStandardAttributes;
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
/*     */ public abstract class SVGOMFilterPrimitiveStandardAttributes
/*     */   extends SVGStylableElement
/*     */   implements SVGFilterPrimitiveStandardAttributes
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedLength x;
/*     */   protected SVGOMAnimatedLength y;
/*     */   protected SVGOMAnimatedLength width;
/*     */   protected SVGOMAnimatedLength height;
/*     */   protected SVGOMAnimatedString result;
/*     */   
/*     */   static {
/*  45 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGStylableElement.xmlTraitInformation);
/*     */     
/*  47 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  49 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  51 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  53 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
/*     */     
/*  55 */     t.put(null, "result", new TraitInformation(true, 16));
/*     */     
/*  57 */     xmlTraitInformation = t;
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
/*     */   protected SVGOMFilterPrimitiveStandardAttributes() {}
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
/*     */   protected SVGOMFilterPrimitiveStandardAttributes(String prefix, AbstractDocument owner) {
/*  98 */     super(prefix, owner);
/*  99 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 106 */     super.initializeAllLiveAttributes();
/* 107 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 114 */     this.x = createLiveAnimatedLength(null, "x", "0%", (short)2, false);
/*     */ 
/*     */     
/* 117 */     this.y = createLiveAnimatedLength(null, "y", "0%", (short)1, false);
/*     */ 
/*     */     
/* 120 */     this.width = createLiveAnimatedLength(null, "width", "100%", (short)2, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 125 */     this.height = createLiveAnimatedLength(null, "height", "100%", (short)1, true);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 130 */     this.result = createLiveAnimatedString(null, "result");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 138 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 146 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 154 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 162 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getResult() {
/* 170 */     return this.result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 177 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFilterPrimitiveStandardAttributes.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */