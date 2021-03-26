/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.awt.geom.NoninvertibleTransformException;
/*     */ import org.apache.batik.css.engine.CSSStylableElement;
/*     */ import org.apache.batik.css.engine.SVGCSSEngine;
/*     */ import org.apache.batik.dom.svg.AbstractSVGMatrix;
/*     */ import org.apache.batik.dom.svg.SVGContext;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGElement;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGMatrix;
/*     */ import org.w3c.dom.svg.SVGRect;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGLocatableSupport
/*     */ {
/*     */   public static SVGElement getNearestViewportElement(Element e) {
/*     */     CSSStylableElement cSSStylableElement;
/*  55 */     Element elt = e;
/*  56 */     while (elt != null) {
/*  57 */       cSSStylableElement = SVGCSSEngine.getParentCSSStylableElement(elt);
/*  58 */       if (cSSStylableElement instanceof org.w3c.dom.svg.SVGFitToViewBox) {
/*     */         break;
/*     */       }
/*     */     } 
/*  62 */     return (SVGElement)cSSStylableElement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGElement getFarthestViewportElement(Element elt) {
/*  70 */     Element rootSVG = elt.getOwnerDocument().getDocumentElement();
/*  71 */     if (elt == rootSVG) {
/*  72 */       return null;
/*     */     }
/*  74 */     return (SVGElement)rootSVG;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGRect getBBox(Element elt) {
/*  81 */     final SVGOMElement svgelt = (SVGOMElement)elt;
/*  82 */     SVGContext svgctx = svgelt.getSVGContext();
/*  83 */     if (svgctx == null) return null; 
/*  84 */     if (svgctx.getBBox() == null) return null;
/*     */     
/*  86 */     return new SVGRect() {
/*     */         public float getX() {
/*  88 */           return (float)svgelt.getSVGContext().getBBox().getX();
/*     */         }
/*     */         public void setX(float x) throws DOMException {
/*  91 */           throw svgelt.createDOMException((short)7, "readonly.rect", null);
/*     */         }
/*     */ 
/*     */         
/*     */         public float getY() {
/*  96 */           return (float)svgelt.getSVGContext().getBBox().getY();
/*     */         }
/*     */         public void setY(float y) throws DOMException {
/*  99 */           throw svgelt.createDOMException((short)7, "readonly.rect", null);
/*     */         }
/*     */ 
/*     */         
/*     */         public float getWidth() {
/* 104 */           return (float)svgelt.getSVGContext().getBBox().getWidth();
/*     */         }
/*     */         public void setWidth(float width) throws DOMException {
/* 107 */           throw svgelt.createDOMException((short)7, "readonly.rect", null);
/*     */         }
/*     */ 
/*     */         
/*     */         public float getHeight() {
/* 112 */           return (float)svgelt.getSVGContext().getBBox().getHeight();
/*     */         }
/*     */         public void setHeight(float height) throws DOMException {
/* 115 */           throw svgelt.createDOMException((short)7, "readonly.rect", null);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGMatrix getCTM(Element elt) {
/* 126 */     final SVGOMElement svgelt = (SVGOMElement)elt;
/* 127 */     return (SVGMatrix)new AbstractSVGMatrix() {
/*     */         protected AffineTransform getAffineTransform() {
/* 129 */           return svgelt.getSVGContext().getCTM();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGMatrix getScreenCTM(Element elt) {
/* 138 */     final SVGOMElement svgelt = (SVGOMElement)elt;
/* 139 */     return (SVGMatrix)new AbstractSVGMatrix() {
/*     */         protected AffineTransform getAffineTransform() {
/* 141 */           SVGContext context = svgelt.getSVGContext();
/* 142 */           AffineTransform ret = context.getGlobalTransform();
/* 143 */           AffineTransform scrnTrans = context.getScreenTransform();
/* 144 */           if (scrnTrans != null)
/* 145 */             ret.preConcatenate(scrnTrans); 
/* 146 */           return ret;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGMatrix getTransformToElement(Element elt, SVGElement element) throws SVGException {
/* 158 */     final SVGOMElement currentElt = (SVGOMElement)elt;
/* 159 */     final SVGOMElement targetElt = (SVGOMElement)element;
/* 160 */     return (SVGMatrix)new AbstractSVGMatrix() {
/*     */         protected AffineTransform getAffineTransform() {
/* 162 */           AffineTransform cat = currentElt.getSVGContext().getGlobalTransform();
/*     */           
/* 164 */           if (cat == null) {
/* 165 */             cat = new AffineTransform();
/*     */           }
/* 167 */           AffineTransform tat = targetElt.getSVGContext().getGlobalTransform();
/*     */           
/* 169 */           if (tat == null) {
/* 170 */             tat = new AffineTransform();
/*     */           }
/* 172 */           AffineTransform at = new AffineTransform(cat);
/*     */           try {
/* 174 */             at.preConcatenate(tat.createInverse());
/* 175 */             return at;
/* 176 */           } catch (NoninvertibleTransformException ex) {
/* 177 */             throw currentElt.createSVGException((short)2, "noninvertiblematrix", null);
/*     */           } 
/*     */         }
/*     */       };
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGLocatableSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */