/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedBoolean;
/*     */ import org.w3c.dom.svg.SVGAnimatedEnumeration;
/*     */ import org.w3c.dom.svg.SVGAnimatedInteger;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumberList;
/*     */ import org.w3c.dom.svg.SVGAnimatedString;
/*     */ import org.w3c.dom.svg.SVGFEConvolveMatrixElement;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMFEConvolveMatrixElement
/*     */   extends SVGOMFilterPrimitiveStandardAttributes
/*     */   implements SVGFEConvolveMatrixElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   
/*     */   static {
/*  49 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMFilterPrimitiveStandardAttributes.xmlTraitInformation);
/*     */     
/*  51 */     t.put(null, "in", new TraitInformation(true, 16));
/*     */     
/*  53 */     t.put(null, "order", new TraitInformation(true, 4));
/*     */     
/*  55 */     t.put(null, "kernelUnitLength", new TraitInformation(true, 4));
/*     */     
/*  57 */     t.put(null, "kernelMatrix", new TraitInformation(true, 13));
/*     */     
/*  59 */     t.put(null, "divisor", new TraitInformation(true, 2));
/*     */     
/*  61 */     t.put(null, "bias", new TraitInformation(true, 2));
/*     */     
/*  63 */     t.put(null, "targetX", new TraitInformation(true, 1));
/*     */     
/*  65 */     t.put(null, "targetY", new TraitInformation(true, 1));
/*     */     
/*  67 */     t.put(null, "edgeMode", new TraitInformation(true, 15));
/*     */     
/*  69 */     t.put(null, "preserveAlpha", new TraitInformation(true, 49));
/*     */     
/*  71 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   protected static final String[] EDGE_MODE_VALUES = new String[] { "", "duplicate", "wrap", "none" };
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
/*     */   protected SVGOMAnimatedEnumeration edgeMode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedNumber bias;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMAnimatedBoolean preserveAlpha;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMFEConvolveMatrixElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMFEConvolveMatrixElement(String prefix, AbstractDocument owner) {
/* 117 */     super(prefix, owner);
/* 118 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 125 */     super.initializeAllLiveAttributes();
/* 126 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 133 */     this.in = createLiveAnimatedString(null, "in");
/* 134 */     this.edgeMode = createLiveAnimatedEnumeration(null, "edgeMode", EDGE_MODE_VALUES, (short)1);
/*     */ 
/*     */     
/* 137 */     this.bias = createLiveAnimatedNumber(null, "bias", 0.0F);
/* 138 */     this.preserveAlpha = createLiveAnimatedBoolean(null, "preserveAlpha", false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 147 */     return "feConvolveMatrix";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedString getIn1() {
/* 154 */     return this.in;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedEnumeration getEdgeMode() {
/* 161 */     return this.edgeMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumberList getKernelMatrix() {
/* 168 */     throw new UnsupportedOperationException("SVGFEConvolveMatrixElement.getKernelMatrix is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedInteger getOrderX() {
/* 176 */     throw new UnsupportedOperationException("SVGFEConvolveMatrixElement.getOrderX is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedInteger getOrderY() {
/* 184 */     throw new UnsupportedOperationException("SVGFEConvolveMatrixElement.getOrderY is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedInteger getTargetX() {
/* 193 */     throw new UnsupportedOperationException("SVGFEConvolveMatrixElement.getTargetX is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedInteger getTargetY() {
/* 202 */     throw new UnsupportedOperationException("SVGFEConvolveMatrixElement.getTargetY is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getDivisor() {
/* 211 */     throw new UnsupportedOperationException("SVGFEConvolveMatrixElement.getDivisor is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getBias() {
/* 220 */     return this.bias;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getKernelUnitLengthX() {
/* 228 */     throw new UnsupportedOperationException("SVGFEConvolveMatrixElement.getKernelUnitLengthX is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getKernelUnitLengthY() {
/* 237 */     throw new UnsupportedOperationException("SVGFEConvolveMatrixElement.getKernelUnitLengthY is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedBoolean getPreserveAlpha() {
/* 246 */     return this.preserveAlpha;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 253 */     return (Node)new SVGOMFEConvolveMatrixElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 260 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMFEConvolveMatrixElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */