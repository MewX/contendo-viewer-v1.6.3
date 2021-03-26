/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumberList;
/*     */ import org.w3c.dom.svg.SVGComponentTransferFunctionElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class SVGOMComponentTransferFunctionElement
/*     */   extends SVGOMElement
/*     */   implements SVGComponentTransferFunctionElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  45 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMElement.xmlTraitInformation);
/*     */     
/*  47 */     t.put(null, "type", new TraitInformation(true, 15));
/*     */     
/*  49 */     t.put(null, "tableValues", new TraitInformation(true, 13));
/*     */     
/*  51 */     t.put(null, "slope", new TraitInformation(true, 2));
/*     */     
/*  53 */     t.put(null, "intercept", new TraitInformation(true, 2));
/*     */     
/*  55 */     t.put(null, "amplitude", new TraitInformation(true, 2));
/*     */     
/*  57 */     t.put(null, "exponent", new TraitInformation(true, 2));
/*     */     
/*  59 */     t.put(null, "offset", new TraitInformation(true, 2));
/*     */     
/*  61 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   protected static final String[] TYPE_VALUES = new String[] { "", "identity", "table", "discrete", "linear", "gamma" };
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
/*     */   protected SVGOMAnimatedNumberList tableValues;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber slope;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber intercept;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber amplitude;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber exponent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber offset;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMComponentTransferFunctionElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMComponentTransferFunctionElement(String prefix, AbstractDocument owner) {
/* 124 */     super(prefix, owner);
/* 125 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 132 */     super.initializeAllLiveAttributes();
/* 133 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 140 */     this.type = createLiveAnimatedEnumeration(null, "type", TYPE_VALUES, (short)1);
/*     */ 
/*     */     
/* 143 */     this.tableValues = createLiveAnimatedNumberList(null, "tableValues", "", false);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 148 */     this.slope = createLiveAnimatedNumber(null, "slope", 1.0F);
/* 149 */     this.intercept = createLiveAnimatedNumber(null, "intercept", 0.0F);
/* 150 */     this.amplitude = createLiveAnimatedNumber(null, "amplitude", 1.0F);
/* 151 */     this.exponent = createLiveAnimatedNumber(null, "exponent", 1.0F);
/* 152 */     this.offset = createLiveAnimatedNumber(null, "exponent", 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getType() {
/* 160 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumberList getTableValues() {
/* 169 */     throw new UnsupportedOperationException("SVGComponentTransferFunctionElement.getTableValues is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getSlope() {
/* 179 */     return this.slope;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getIntercept() {
/* 187 */     return this.intercept;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getAmplitude() {
/* 195 */     return this.amplitude;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getExponent() {
/* 203 */     return this.exponent;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getOffset() {
/* 211 */     return this.offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 218 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMComponentTransferFunctionElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */