/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGMaskElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMMaskElement
/*     */   extends SVGGraphicsElement
/*     */   implements SVGMaskElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  45 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*     */     
/*  47 */     t.put(null, "x", new TraitInformation(true, 3, (short)1));
/*     */     
/*  49 */     t.put(null, "y", new TraitInformation(true, 3, (short)2));
/*     */     
/*  51 */     t.put(null, "width", new TraitInformation(true, 3, (short)1));
/*     */     
/*  53 */     t.put(null, "height", new TraitInformation(true, 3, (short)2));
/*     */     
/*  55 */     t.put(null, "maskUnits", new TraitInformation(true, 15));
/*     */     
/*  57 */     t.put(null, "maskContentUnits", new TraitInformation(true, 15));
/*     */     
/*  59 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  65 */   protected static final String[] UNITS_VALUES = new String[] { "", "userSpaceOnUse", "objectBoundingBox" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength x;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength y;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength width;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedLength height;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration maskUnits;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration maskContentUnits;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMMaskElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMMaskElement(String prefix, AbstractDocument owner) {
/* 113 */     super(prefix, owner);
/* 114 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 121 */     super.initializeAllLiveAttributes();
/* 122 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 129 */     this.x = createLiveAnimatedLength(null, "x", "-10%", (short)2, false);
/*     */ 
/*     */     
/* 132 */     this.y = createLiveAnimatedLength(null, "y", "-10%", (short)1, false);
/*     */ 
/*     */     
/* 135 */     this.width = createLiveAnimatedLength(null, "width", "120%", (short)2, true);
/*     */ 
/*     */ 
/*     */     
/* 139 */     this.height = createLiveAnimatedLength(null, "height", "120%", (short)1, true);
/*     */ 
/*     */ 
/*     */     
/* 143 */     this.maskUnits = createLiveAnimatedEnumeration(null, "maskUnits", UNITS_VALUES, (short)2);
/*     */ 
/*     */     
/* 146 */     this.maskContentUnits = createLiveAnimatedEnumeration(null, "maskContentUnits", UNITS_VALUES, (short)1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 156 */     return "mask";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getMaskUnits() {
/* 163 */     return this.maskUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getMaskContentUnits() {
/* 170 */     return this.maskContentUnits;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getX() {
/* 177 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getY() {
/* 184 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getWidth() {
/* 191 */     return this.width;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getHeight() {
/* 198 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 205 */     return (Node)new SVGOMMaskElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 212 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMMaskElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */