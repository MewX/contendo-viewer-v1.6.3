/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGClipPathElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMClipPathElement
/*     */   extends SVGGraphicsElement
/*     */   implements SVGClipPathElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  44 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*     */     
/*  46 */     t.put(null, "clipPathUnits", new TraitInformation(true, 15));
/*     */     
/*  48 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  54 */   protected static final String[] CLIP_PATH_UNITS_VALUES = new String[] { "", "userSpaceOnUse", "objectBoundingBox" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration clipPathUnits;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMClipPathElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMClipPathElement(String prefix, AbstractDocument owner) {
/*  77 */     super(prefix, owner);
/*  78 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  85 */     super.initializeAllLiveAttributes();
/*  86 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/*  93 */     this.clipPathUnits = createLiveAnimatedEnumeration(null, "clipPathUnits", CLIP_PATH_UNITS_VALUES, (short)1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 103 */     return "clipPath";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getClipPathUnits() {
/* 110 */     return this.clipPathUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 117 */     return (Node)new SVGOMClipPathElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 124 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMClipPathElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */