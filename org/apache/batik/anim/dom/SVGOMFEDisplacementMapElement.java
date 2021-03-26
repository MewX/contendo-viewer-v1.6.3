/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFEDisplacementMapElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMFEDisplacementMapElement
/*     */   extends SVGOMFilterPrimitiveStandardAttributes
/*     */   implements SVGFEDisplacementMapElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  46 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMFilterPrimitiveStandardAttributes.xmlTraitInformation);
/*     */     
/*  48 */     t.put(null, "in", new TraitInformation(true, 16));
/*     */     
/*  50 */     t.put(null, "in2", new TraitInformation(true, 16));
/*     */     
/*  52 */     t.put(null, "scale", new TraitInformation(true, 2));
/*     */     
/*  54 */     t.put(null, "xChannelSelector", new TraitInformation(true, 15));
/*     */     
/*  56 */     t.put(null, "yChannelSelector", new TraitInformation(true, 15));
/*     */     
/*  58 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  64 */   protected static final String[] CHANNEL_SELECTOR_VALUES = new String[] { "", "R", "G", "B", "A" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedString in;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedString in2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber scale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration xChannelSelector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedEnumeration yChannelSelector;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMFEDisplacementMapElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMFEDisplacementMapElement(String prefix, AbstractDocument owner) {
/* 110 */     super(prefix, owner);
/* 111 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 118 */     super.initializeAllLiveAttributes();
/* 119 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 126 */     this.in = createLiveAnimatedString(null, "in");
/* 127 */     this.in2 = createLiveAnimatedString(null, "in2");
/* 128 */     this.scale = createLiveAnimatedNumber(null, "scale", 0.0F);
/* 129 */     this.xChannelSelector = createLiveAnimatedEnumeration(null, "xChannelSelector", CHANNEL_SELECTOR_VALUES, (short)4);
/*     */ 
/*     */ 
/*     */     
/* 133 */     this.yChannelSelector = createLiveAnimatedEnumeration(null, "yChannelSelector", CHANNEL_SELECTOR_VALUES, (short)4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 143 */     return "feDisplacementMap";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn1() {
/* 151 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn2() {
/* 159 */     return this.in2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getScale() {
/* 167 */     return this.scale;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getXChannelSelector() {
/* 175 */     return this.xChannelSelector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getYChannelSelector() {
/* 183 */     return this.yChannelSelector;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 190 */     return (Node)new SVGOMFEDisplacementMapElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 197 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFEDisplacementMapElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */