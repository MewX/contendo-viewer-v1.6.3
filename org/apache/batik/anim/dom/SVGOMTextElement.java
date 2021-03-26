/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import org.apache.batik.anim.values.AnimatableMotionPointValue;
/*     */ import org.apache.batik.anim.values.AnimatableValue;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGMotionAnimatableElement;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedTransformList;
/*     */ import org.w3c.dom.svg.SVGElement;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGMatrix;
/*     */ import org.w3c.dom.svg.SVGRect;
/*     */ import org.w3c.dom.svg.SVGTextElement;
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
/*     */ 
/*     */ public class SVGOMTextElement
/*     */   extends SVGOMTextPositioningElement
/*     */   implements SVGMotionAnimatableElement, SVGTextElement
/*     */ {
/*     */   protected static final String X_DEFAULT_VALUE = "0";
/*     */   protected static final String Y_DEFAULT_VALUE = "0";
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedTransformList transform;
/*     */   protected AffineTransform motionTransform;
/*     */   
/*     */   static {
/*  58 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGOMTextPositioningElement.xmlTraitInformation);
/*     */     
/*  60 */     t.put(null, "transform", new TraitInformation(true, 9));
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
/*     */   protected SVGOMTextElement() {}
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
/*     */   public SVGOMTextElement(String prefix, AbstractDocument owner) {
/*  87 */     super(prefix, owner);
/*  88 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/*  95 */     super.initializeAllLiveAttributes();
/*  96 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 103 */     this.transform = createLiveAnimatedTransformList((String)null, "transform", "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 111 */     return "text";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getNearestViewportElement() {
/* 121 */     return SVGLocatableSupport.getNearestViewportElement((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGElement getFarthestViewportElement() {
/* 129 */     return SVGLocatableSupport.getFarthestViewportElement((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGRect getBBox() {
/* 136 */     return SVGLocatableSupport.getBBox((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getCTM() {
/* 143 */     return SVGLocatableSupport.getCTM((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getScreenCTM() {
/* 151 */     return SVGLocatableSupport.getScreenCTM((Element)this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGMatrix getTransformToElement(SVGElement element) throws SVGException {
/* 160 */     return SVGLocatableSupport.getTransformToElement((Element)this, element);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedTransformList getTransform() {
/* 170 */     return this.transform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDefaultXValue() {
/* 177 */     return "0";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getDefaultYValue() {
/* 184 */     return "0";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 191 */     return (Node)new SVGOMTextElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 198 */     return xmlTraitInformation;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getMotionTransform() {
/* 208 */     return this.motionTransform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateOtherValue(String type, AnimatableValue val) {
/* 217 */     if (type.equals("motion")) {
/* 218 */       if (this.motionTransform == null) {
/* 219 */         this.motionTransform = new AffineTransform();
/*     */       }
/* 221 */       if (val == null) {
/* 222 */         this.motionTransform.setToIdentity();
/*     */       } else {
/* 224 */         AnimatableMotionPointValue p = (AnimatableMotionPointValue)val;
/* 225 */         this.motionTransform.setToTranslation(p.getX(), p.getY());
/* 226 */         this.motionTransform.rotate(p.getAngle());
/*     */       } 
/* 228 */       SVGOMDocument d = (SVGOMDocument)this.ownerDocument;
/* 229 */       d.getAnimatedAttributeListener().otherAnimationChanged((Element)this, type);
/*     */     } else {
/* 231 */       super.updateOtherValue(type, val);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMTextElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */