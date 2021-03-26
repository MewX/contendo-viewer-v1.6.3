/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Attr;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedLength;
/*     */ import org.w3c.dom.svg.SVGRadialGradientElement;
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
/*     */ public class SVGOMRadialGradientElement
/*     */   extends SVGOMGradientElement
/*     */   implements SVGRadialGradientElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedLength cx;
/*     */   protected SVGOMAnimatedLength cy;
/*     */   protected AbstractSVGAnimatedLength fx;
/*     */   protected AbstractSVGAnimatedLength fy;
/*     */   protected SVGOMAnimatedLength r;
/*     */   
/*     */   static {
/*  45 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMGradientElement.xmlTraitInformation);
/*     */     
/*  47 */     t.put(null, "cx", new TraitInformation(true, 3, (short)1));
/*     */     
/*  49 */     t.put(null, "cy", new TraitInformation(true, 3, (short)2));
/*     */     
/*  51 */     t.put(null, "fx", new TraitInformation(true, 3, (short)1));
/*     */     
/*  53 */     t.put(null, "fy", new TraitInformation(true, 3, (short)2));
/*     */     
/*  55 */     t.put(null, "r", new TraitInformation(true, 3, (short)3));
/*     */     
/*  57 */     xmlTraitInformation = t;
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
/*     */   protected SVGOMRadialGradientElement() {}
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
/*     */   public SVGOMRadialGradientElement(String prefix, AbstractDocument owner) {
/*  97 */     super(prefix, owner);
/*  98 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 105 */     super.initializeAllLiveAttributes();
/* 106 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 113 */     this.cx = createLiveAnimatedLength((String)null, "cx", "50%", (short)2, false);
/*     */ 
/*     */     
/* 116 */     this.cy = createLiveAnimatedLength((String)null, "cy", "50%", (short)1, false);
/*     */ 
/*     */     
/* 119 */     this.r = createLiveAnimatedLength((String)null, "r", "50%", (short)0, false);
/*     */ 
/*     */     
/* 122 */     this.fx = new AbstractSVGAnimatedLength(this, null, "fx", (short)2, false)
/*     */       {
/*     */         protected String getDefaultValue()
/*     */         {
/* 126 */           Attr attr = SVGOMRadialGradientElement.this.getAttributeNodeNS(null, "cx");
/* 127 */           if (attr == null) {
/* 128 */             return "50%";
/*     */           }
/* 130 */           return attr.getValue();
/*     */         }
/*     */       };
/* 133 */     this.fy = new AbstractSVGAnimatedLength(this, null, "fy", (short)1, false)
/*     */       {
/*     */         protected String getDefaultValue()
/*     */         {
/* 137 */           Attr attr = SVGOMRadialGradientElement.this.getAttributeNodeNS(null, "cy");
/* 138 */           if (attr == null) {
/* 139 */             return "50%";
/*     */           }
/* 141 */           return attr.getValue();
/*     */         }
/*     */       };
/*     */     
/* 145 */     this.liveAttributeValues.put(null, "fx", this.fx);
/* 146 */     this.liveAttributeValues.put(null, "fy", this.fy);
/* 147 */     AnimatedAttributeListener l = ((SVGOMDocument)this.ownerDocument).getAnimatedAttributeListener();
/*     */     
/* 149 */     this.fx.addAnimatedAttributeListener(l);
/* 150 */     this.fy.addAnimatedAttributeListener(l);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 157 */     return "radialGradient";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getCx() {
/* 165 */     return this.cx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getCy() {
/* 173 */     return this.cy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getR() {
/* 181 */     return this.r;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getFx() {
/* 189 */     return this.fx;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedLength getFy() {
/* 197 */     return this.fy;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 204 */     return (Node)new SVGOMRadialGradientElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 211 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMRadialGradientElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */