/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedInteger;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGFETurbulenceElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMFETurbulenceElement
/*     */   extends SVGOMFilterPrimitiveStandardAttributes
/*     */   implements SVGFETurbulenceElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  46 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMFilterPrimitiveStandardAttributes.xmlTraitInformation);
/*     */     
/*  48 */     t.put(null, "baseFrequency", new TraitInformation(true, 4));
/*     */     
/*  50 */     t.put(null, "numOctaves", new TraitInformation(true, 1));
/*     */     
/*  52 */     t.put(null, "seed", new TraitInformation(true, 2));
/*     */     
/*  54 */     t.put(null, "stitchTiles", new TraitInformation(true, 15));
/*     */     
/*  56 */     t.put(null, "type", new TraitInformation(true, 15));
/*     */     
/*  58 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   protected static final String[] STITCH_TILES_VALUES = new String[] { "", "stitch", "noStitch" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  73 */   protected static final String[] TYPE_VALUES = new String[] { "", "fractalNoise", "turbulence" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedInteger numOctaves;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber seed;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration stitchTiles;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration type;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMFETurbulenceElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMFETurbulenceElement(String prefix, AbstractDocument owner) {
/* 112 */     super(prefix, owner);
/* 113 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 120 */     super.initializeAllLiveAttributes();
/* 121 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 128 */     this.numOctaves = createLiveAnimatedInteger(null, "numOctaves", 1);
/*     */     
/* 130 */     this.seed = createLiveAnimatedNumber(null, "seed", 0.0F);
/* 131 */     this.stitchTiles = createLiveAnimatedEnumeration(null, "stitchTiles", STITCH_TILES_VALUES, (short)2);
/*     */ 
/*     */ 
/*     */     
/* 135 */     this.type = createLiveAnimatedEnumeration(null, "type", TYPE_VALUES, (short)2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 144 */     return "feTurbulence";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getBaseFrequencyX() {
/* 152 */     throw new UnsupportedOperationException("SVGFETurbulenceElement.getBaseFrequencyX is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getBaseFrequencyY() {
/* 161 */     throw new UnsupportedOperationException("SVGFETurbulenceElement.getBaseFrequencyY is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedInteger getNumOctaves() {
/* 169 */     return this.numOctaves;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getSeed() {
/* 176 */     return this.seed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getStitchTiles() {
/* 183 */     return this.stitchTiles;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getType() {
/* 190 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 197 */     return (Node)new SVGOMFETurbulenceElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 204 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFETurbulenceElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */