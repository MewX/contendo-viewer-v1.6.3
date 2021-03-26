/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFECompositeElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMFECompositeElement
/*     */   extends SVGOMFilterPrimitiveStandardAttributes
/*     */   implements SVGFECompositeElement
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
/*  52 */     t.put(null, "operator", new TraitInformation(true, 15));
/*     */     
/*  54 */     t.put(null, "k1", new TraitInformation(true, 2));
/*     */     
/*  56 */     t.put(null, "k2", new TraitInformation(true, 2));
/*     */     
/*  58 */     t.put(null, "k3", new TraitInformation(true, 2));
/*     */     
/*  60 */     t.put(null, "k4", new TraitInformation(true, 2));
/*     */     
/*  62 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  68 */   protected static final String[] OPERATOR_VALUES = new String[] { "", "over", "in", "out", "atop", "xor", "arithmetic" };
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
/*     */   protected SVGOMAnimatedEnumeration operator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber k1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber k2;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber k3;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber k4;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMFECompositeElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMFECompositeElement(String prefix, AbstractDocument owner) {
/* 125 */     super(prefix, owner);
/* 126 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 133 */     super.initializeAllLiveAttributes();
/* 134 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 141 */     this.in = createLiveAnimatedString(null, "in");
/* 142 */     this.in2 = createLiveAnimatedString(null, "in2");
/* 143 */     this.operator = createLiveAnimatedEnumeration(null, "operator", OPERATOR_VALUES, (short)1);
/*     */ 
/*     */     
/* 146 */     this.k1 = createLiveAnimatedNumber(null, "k1", 0.0F);
/* 147 */     this.k2 = createLiveAnimatedNumber(null, "k2", 0.0F);
/* 148 */     this.k3 = createLiveAnimatedNumber(null, "k3", 0.0F);
/* 149 */     this.k4 = createLiveAnimatedNumber(null, "k4", 0.0F);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 156 */     return "feComposite";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn1() {
/* 163 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn2() {
/* 170 */     return this.in2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getOperator() {
/* 177 */     return this.operator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getK1() {
/* 184 */     return this.k1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getK2() {
/* 191 */     return this.k2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getK3() {
/* 198 */     return this.k3;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getK4() {
/* 205 */     return this.k4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 212 */     return (Node)new SVGOMFECompositeElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 219 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFECompositeElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */