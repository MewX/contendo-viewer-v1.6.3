/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGFESpotLightElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMFESpotLightElement
/*     */   extends SVGOMElement
/*     */   implements SVGFESpotLightElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedNumber x;
/*     */   protected SVGOMAnimatedNumber y;
/*     */   protected SVGOMAnimatedNumber z;
/*     */   protected SVGOMAnimatedNumber pointsAtX;
/*     */   protected SVGOMAnimatedNumber pointsAtY;
/*     */   protected SVGOMAnimatedNumber pointsAtZ;
/*     */   protected SVGOMAnimatedNumber specularExponent;
/*     */   protected SVGOMAnimatedNumber limitingConeAngle;
/*     */   
/*     */   static {
/*  44 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMElement.xmlTraitInformation);
/*     */     
/*  46 */     t.put(null, "x", new TraitInformation(true, 2));
/*     */     
/*  48 */     t.put(null, "y", new TraitInformation(true, 2));
/*     */     
/*  50 */     t.put(null, "z", new TraitInformation(true, 2));
/*     */     
/*  52 */     t.put(null, "pointsAtX", new TraitInformation(true, 2));
/*     */     
/*  54 */     t.put(null, "pointsAtY", new TraitInformation(true, 2));
/*     */     
/*  56 */     t.put(null, "pointsAtZ", new TraitInformation(true, 2));
/*     */     
/*  58 */     t.put(null, "specularExponent", new TraitInformation(true, 2));
/*     */     
/*  60 */     t.put(null, "limitingConeAngle", new TraitInformation(true, 2));
/*     */     
/*  62 */     xmlTraitInformation = t;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMFESpotLightElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMFESpotLightElement(String prefix, AbstractDocument owner) {
/* 118 */     super(prefix, owner);
/* 119 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 126 */     super.initializeAllLiveAttributes();
/* 127 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 134 */     this.x = createLiveAnimatedNumber(null, "x", 0.0F);
/* 135 */     this.y = createLiveAnimatedNumber(null, "y", 0.0F);
/* 136 */     this.z = createLiveAnimatedNumber(null, "z", 0.0F);
/* 137 */     this.pointsAtX = createLiveAnimatedNumber(null, "pointsAtX", 0.0F);
/*     */     
/* 139 */     this.pointsAtY = createLiveAnimatedNumber(null, "pointsAtY", 0.0F);
/*     */     
/* 141 */     this.pointsAtZ = createLiveAnimatedNumber(null, "pointsAtZ", 0.0F);
/*     */     
/* 143 */     this.specularExponent = createLiveAnimatedNumber(null, "specularExponent", 1.0F);
/*     */     
/* 145 */     this.limitingConeAngle = createLiveAnimatedNumber(null, "limitingConeAngle", 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 154 */     return "feSpotLight";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getX() {
/* 161 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getY() {
/* 168 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getZ() {
/* 175 */     return this.z;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getPointsAtX() {
/* 182 */     return this.pointsAtX;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getPointsAtY() {
/* 189 */     return this.pointsAtY;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getPointsAtZ() {
/* 196 */     return this.pointsAtZ;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getSpecularExponent() {
/* 204 */     return this.specularExponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getLimitingConeAngle() {
/* 212 */     return this.limitingConeAngle;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 219 */     return (Node)new SVGOMFESpotLightElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 226 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFESpotLightElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */