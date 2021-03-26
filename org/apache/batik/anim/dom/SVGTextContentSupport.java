/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import java.awt.geom.Point2D;
/*     */ import java.awt.geom.Rectangle2D;
/*     */ import org.apache.batik.dom.svg.SVGOMPoint;
/*     */ import org.apache.batik.dom.svg.SVGTextContent;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGPoint;
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
/*     */ public class SVGTextContentSupport
/*     */ {
/*     */   public static int getNumberOfChars(Element elt) {
/*  46 */     SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/*  48 */     return ((SVGTextContent)svgelt.getSVGContext()).getNumberOfChars();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGRect getExtentOfChar(Element elt, final int charnum) {
/*  56 */     final SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/*  58 */     if (charnum < 0 || charnum >= getNumberOfChars(elt))
/*     */     {
/*  60 */       throw svgelt.createDOMException((short)1, "", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  65 */     final SVGTextContent context = (SVGTextContent)svgelt.getSVGContext();
/*     */     
/*  67 */     return new SVGRect() {
/*     */         public float getX() {
/*  69 */           return (float)SVGTextContentSupport.getExtent(svgelt, context, charnum).getX();
/*     */         }
/*     */         
/*     */         public void setX(float x) throws DOMException {
/*  73 */           throw svgelt.createDOMException((short)7, "readonly.rect", null);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public float getY() {
/*  79 */           return (float)SVGTextContentSupport.getExtent(svgelt, context, charnum).getY();
/*     */         }
/*     */         
/*     */         public void setY(float y) throws DOMException {
/*  83 */           throw svgelt.createDOMException((short)7, "readonly.rect", null);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public float getWidth() {
/*  89 */           return (float)SVGTextContentSupport.getExtent(svgelt, context, charnum).getWidth();
/*     */         }
/*     */         
/*     */         public void setWidth(float width) throws DOMException {
/*  93 */           throw svgelt.createDOMException((short)7, "readonly.rect", null);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/*     */         public float getHeight() {
/*  99 */           return (float)SVGTextContentSupport.getExtent(svgelt, context, charnum).getHeight();
/*     */         }
/*     */         
/*     */         public void setHeight(float height) throws DOMException {
/* 103 */           throw svgelt.createDOMException((short)7, "readonly.rect", null);
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Rectangle2D getExtent(SVGOMElement svgelt, SVGTextContent context, int charnum) {
/* 112 */     Rectangle2D r2d = context.getExtentOfChar(charnum);
/* 113 */     if (r2d == null) throw svgelt.createDOMException((short)1, "", null);
/*     */     
/* 115 */     return r2d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGPoint getStartPositionOfChar(Element elt, final int charnum) throws DOMException {
/* 125 */     SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/* 127 */     if (charnum < 0 || charnum >= getNumberOfChars(elt))
/*     */     {
/* 129 */       throw svgelt.createDOMException((short)1, "", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 134 */     final SVGTextContent context = (SVGTextContent)svgelt.getSVGContext();
/*     */     
/* 136 */     return (SVGPoint)new SVGTextPoint(svgelt) {
/*     */         public float getX() {
/* 138 */           return (float)SVGTextContentSupport.getStartPos(this.svgelt, context, charnum).getX();
/*     */         }
/*     */         
/*     */         public float getY() {
/* 142 */           return (float)SVGTextContentSupport.getStartPos(this.svgelt, context, charnum).getY();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Point2D getStartPos(SVGOMElement svgelt, SVGTextContent context, int charnum) {
/* 150 */     Point2D p2d = context.getStartPositionOfChar(charnum);
/* 151 */     if (p2d == null) throw svgelt.createDOMException((short)1, "", null);
/*     */     
/* 153 */     return p2d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static SVGPoint getEndPositionOfChar(Element elt, final int charnum) throws DOMException {
/* 163 */     SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/* 165 */     if (charnum < 0 || charnum >= getNumberOfChars(elt))
/*     */     {
/* 167 */       throw svgelt.createDOMException((short)1, "", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 172 */     final SVGTextContent context = (SVGTextContent)svgelt.getSVGContext();
/*     */     
/* 174 */     return (SVGPoint)new SVGTextPoint(svgelt) {
/*     */         public float getX() {
/* 176 */           return (float)SVGTextContentSupport.getEndPos(this.svgelt, context, charnum).getX();
/*     */         }
/*     */         
/*     */         public float getY() {
/* 180 */           return (float)SVGTextContentSupport.getEndPos(this.svgelt, context, charnum).getY();
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected static Point2D getEndPos(SVGOMElement svgelt, SVGTextContent context, int charnum) {
/* 188 */     Point2D p2d = context.getEndPositionOfChar(charnum);
/* 189 */     if (p2d == null) throw svgelt.createDOMException((short)1, "", null);
/*     */     
/* 191 */     return p2d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void selectSubString(Element elt, int charnum, int nchars) {
/* 200 */     SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/* 202 */     if (charnum < 0 || charnum >= getNumberOfChars(elt))
/*     */     {
/* 204 */       throw svgelt.createDOMException((short)1, "", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 209 */     SVGTextContent context = (SVGTextContent)svgelt.getSVGContext();
/*     */     
/* 211 */     context.selectSubString(charnum, nchars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getRotationOfChar(Element elt, int charnum) {
/* 219 */     SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/* 221 */     if (charnum < 0 || charnum >= getNumberOfChars(elt))
/*     */     {
/* 223 */       throw svgelt.createDOMException((short)1, "", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 228 */     SVGTextContent context = (SVGTextContent)svgelt.getSVGContext();
/*     */     
/* 230 */     return context.getRotationOfChar(charnum);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getComputedTextLength(Element elt) {
/* 239 */     SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/* 241 */     SVGTextContent context = (SVGTextContent)svgelt.getSVGContext();
/*     */     
/* 243 */     return context.getComputedTextLength();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float getSubStringLength(Element elt, int charnum, int nchars) {
/* 252 */     SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/* 254 */     if (charnum < 0 || charnum >= getNumberOfChars(elt))
/*     */     {
/* 256 */       throw svgelt.createDOMException((short)1, "", null);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 261 */     SVGTextContent context = (SVGTextContent)svgelt.getSVGContext();
/*     */     
/* 263 */     return context.getSubStringLength(charnum, nchars);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static int getCharNumAtPosition(Element elt, float x, float y) throws DOMException {
/* 272 */     SVGOMElement svgelt = (SVGOMElement)elt;
/*     */     
/* 274 */     SVGTextContent context = (SVGTextContent)svgelt.getSVGContext();
/*     */     
/* 276 */     return context.getCharNumAtPosition(x, y);
/*     */   }
/*     */   
/*     */   public static class SVGTextPoint
/*     */     extends SVGOMPoint {
/*     */     SVGTextPoint(SVGOMElement elem) {
/* 282 */       this.svgelt = elem;
/*     */     } SVGOMElement svgelt;
/*     */     public void setX(float x) throws DOMException {
/* 285 */       throw this.svgelt.createDOMException((short)7, "readonly.point", null);
/*     */     }
/*     */ 
/*     */     
/*     */     public void setY(float y) throws DOMException {
/* 290 */       throw this.svgelt.createDOMException((short)7, "readonly.point", null);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGTextContentSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */