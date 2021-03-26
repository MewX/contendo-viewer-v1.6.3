/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGForeignObjectElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMForeignObjectElement
/*     */   extends SVGGraphicsElement
/*     */   implements SVGForeignObjectElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedLength x;
/*     */   protected SVGOMAnimatedLength y;
/*     */   protected SVGOMAnimatedLength width;
/*     */   protected SVGOMAnimatedLength height;
/*     */   protected SVGOMAnimatedPreserveAspectRatio preserveAspectRatio;
/*     */   
/*     */   static {
/*  44 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*     */     
/*  46 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  48 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  50 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  52 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
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
/*     */ 
/*     */   
/*     */   protected SVGOMForeignObjectElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMForeignObjectElement(String prefix, AbstractDocument owner) {
/*  94 */     super(prefix, owner);
/*  95 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 102 */     super.initializeAllLiveAttributes();
/* 103 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 110 */     this.x = createLiveAnimatedLength(null, "x", "0", (short)2, false);
/*     */ 
/*     */     
/* 113 */     this.y = createLiveAnimatedLength(null, "y", "0", (short)1, false);
/*     */ 
/*     */     
/* 116 */     this.width = createLiveAnimatedLength(null, "width", null, (short)2, true);
/*     */ 
/*     */ 
/*     */     
/* 120 */     this.height = createLiveAnimatedLength(null, "height", null, (short)1, true);
/*     */ 
/*     */ 
/*     */     
/* 124 */     this.preserveAspectRatio = createLiveAnimatedPreserveAspectRatio();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 131 */     return "foreignObject";
/*     */   }
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
/*     */   public SVGAnimatedLength getY() {
/* 145 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 152 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 159 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 166 */     return (Node)new SVGOMForeignObjectElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 173 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMForeignObjectElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */