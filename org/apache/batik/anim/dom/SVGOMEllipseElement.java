/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGEllipseElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMEllipseElement
/*     */   extends SVGGraphicsElement
/*     */   implements SVGEllipseElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedLength cx;
/*     */   protected SVGOMAnimatedLength cy;
/*     */   protected SVGOMAnimatedLength rx;
/*     */   protected SVGOMAnimatedLength ry;
/*     */   
/*     */   static {
/*  44 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*     */     
/*  46 */     t.put(null, "cx", new TraitInformation(true, 3, (short)1));
/*     */     
/*  48 */     t.put(null, "cy", new TraitInformation(true, 3, (short)2));
/*     */     
/*  50 */     t.put(null, "rx", new TraitInformation(true, 3, (short)1));
/*     */     
/*  52 */     t.put(null, "ry", new TraitInformation(true, 3, (short)2));
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
/*     */   protected SVGOMEllipseElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMEllipseElement(String prefix, AbstractDocument owner) {
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
/* 105 */     this.cx = createLiveAnimatedLength(null, "cx", "0", (short)2, false);
/*     */ 
/*     */     
/* 108 */     this.cy = createLiveAnimatedLength(null, "cy", "0", (short)1, false);
/*     */ 
/*     */     
/* 111 */     this.rx = createLiveAnimatedLength(null, "rx", null, (short)2, true);
/*     */ 
/*     */     
/* 114 */     this.ry = createLiveAnimatedLength(null, "ry", null, (short)1, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 123 */     return "ellipse";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getCx() {
/* 130 */     return this.cx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getCy() {
/* 137 */     return this.cy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getRx() {
/* 144 */     return this.rx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getRy() {
/* 151 */     return this.ry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 158 */     return (Node)new SVGOMEllipseElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 165 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMEllipseElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */