/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.apache.batik.parser.PathHandler;
/*     */ import org.w3c.dom.svg.SVGPathSeg;
/*     */ import org.w3c.dom.svg.SVGPathSegArcAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegArcRel;
/*     */ import org.w3c.dom.svg.SVGPathSegCurvetoCubicAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegCurvetoCubicRel;
/*     */ import org.w3c.dom.svg.SVGPathSegCurvetoCubicSmoothAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegCurvetoCubicSmoothRel;
/*     */ import org.w3c.dom.svg.SVGPathSegCurvetoQuadraticAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegCurvetoQuadraticRel;
/*     */ import org.w3c.dom.svg.SVGPathSegCurvetoQuadraticSmoothAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegCurvetoQuadraticSmoothRel;
/*     */ import org.w3c.dom.svg.SVGPathSegLinetoAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegLinetoHorizontalAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegLinetoHorizontalRel;
/*     */ import org.w3c.dom.svg.SVGPathSegLinetoRel;
/*     */ import org.w3c.dom.svg.SVGPathSegLinetoVerticalAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegLinetoVerticalRel;
/*     */ import org.w3c.dom.svg.SVGPathSegList;
/*     */ import org.w3c.dom.svg.SVGPathSegMovetoAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegMovetoRel;
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
/*     */ public abstract class SVGAnimatedPathDataSupport
/*     */ {
/*     */   public static void handlePathSegList(SVGPathSegList p, PathHandler h) {
/*  58 */     int n = p.getNumberOfItems();
/*  59 */     h.startPath();
/*  60 */     for (int i = 0; i < n; i++) {
/*  61 */       SVGPathSegMovetoAbs sVGPathSegMovetoAbs; SVGPathSegMovetoRel sVGPathSegMovetoRel; SVGPathSegLinetoAbs sVGPathSegLinetoAbs; SVGPathSegLinetoRel sVGPathSegLinetoRel; SVGPathSegCurvetoCubicAbs sVGPathSegCurvetoCubicAbs; SVGPathSegCurvetoCubicRel sVGPathSegCurvetoCubicRel; SVGPathSegCurvetoQuadraticAbs sVGPathSegCurvetoQuadraticAbs; SVGPathSegCurvetoQuadraticRel sVGPathSegCurvetoQuadraticRel; SVGPathSegArcAbs sVGPathSegArcAbs; SVGPathSegArcRel sVGPathSegArcRel; SVGPathSegLinetoHorizontalAbs sVGPathSegLinetoHorizontalAbs; SVGPathSegLinetoHorizontalRel sVGPathSegLinetoHorizontalRel; SVGPathSegLinetoVerticalAbs sVGPathSegLinetoVerticalAbs; SVGPathSegLinetoVerticalRel sVGPathSegLinetoVerticalRel; SVGPathSegCurvetoCubicSmoothAbs sVGPathSegCurvetoCubicSmoothAbs; SVGPathSegCurvetoCubicSmoothRel sVGPathSegCurvetoCubicSmoothRel; SVGPathSegCurvetoQuadraticSmoothAbs sVGPathSegCurvetoQuadraticSmoothAbs; SVGPathSegCurvetoQuadraticSmoothRel s; SVGPathSeg seg = p.getItem(i);
/*  62 */       switch (seg.getPathSegType()) {
/*     */         case 1:
/*  64 */           h.closePath();
/*     */           break;
/*     */         case 2:
/*  67 */           sVGPathSegMovetoAbs = (SVGPathSegMovetoAbs)seg;
/*  68 */           h.movetoAbs(sVGPathSegMovetoAbs.getX(), sVGPathSegMovetoAbs.getY());
/*     */           break;
/*     */         
/*     */         case 3:
/*  72 */           sVGPathSegMovetoRel = (SVGPathSegMovetoRel)seg;
/*  73 */           h.movetoRel(sVGPathSegMovetoRel.getX(), sVGPathSegMovetoRel.getY());
/*     */           break;
/*     */         
/*     */         case 4:
/*  77 */           sVGPathSegLinetoAbs = (SVGPathSegLinetoAbs)seg;
/*  78 */           h.linetoAbs(sVGPathSegLinetoAbs.getX(), sVGPathSegLinetoAbs.getY());
/*     */           break;
/*     */         
/*     */         case 5:
/*  82 */           sVGPathSegLinetoRel = (SVGPathSegLinetoRel)seg;
/*  83 */           h.linetoRel(sVGPathSegLinetoRel.getX(), sVGPathSegLinetoRel.getY());
/*     */           break;
/*     */         
/*     */         case 6:
/*  87 */           sVGPathSegCurvetoCubicAbs = (SVGPathSegCurvetoCubicAbs)seg;
/*     */           
/*  89 */           h.curvetoCubicAbs(sVGPathSegCurvetoCubicAbs.getX1(), sVGPathSegCurvetoCubicAbs.getY1(), sVGPathSegCurvetoCubicAbs.getX2(), sVGPathSegCurvetoCubicAbs.getY2(), sVGPathSegCurvetoCubicAbs.getX(), sVGPathSegCurvetoCubicAbs.getY());
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 7:
/*  95 */           sVGPathSegCurvetoCubicRel = (SVGPathSegCurvetoCubicRel)seg;
/*     */           
/*  97 */           h.curvetoCubicRel(sVGPathSegCurvetoCubicRel.getX1(), sVGPathSegCurvetoCubicRel.getY1(), sVGPathSegCurvetoCubicRel.getX2(), sVGPathSegCurvetoCubicRel.getY2(), sVGPathSegCurvetoCubicRel.getX(), sVGPathSegCurvetoCubicRel.getY());
/*     */           break;
/*     */ 
/*     */ 
/*     */         
/*     */         case 8:
/* 103 */           sVGPathSegCurvetoQuadraticAbs = (SVGPathSegCurvetoQuadraticAbs)seg;
/*     */           
/* 105 */           h.curvetoQuadraticAbs(sVGPathSegCurvetoQuadraticAbs.getX1(), sVGPathSegCurvetoQuadraticAbs.getY1(), sVGPathSegCurvetoQuadraticAbs.getX(), sVGPathSegCurvetoQuadraticAbs.getY());
/*     */           break;
/*     */ 
/*     */         
/*     */         case 9:
/* 110 */           sVGPathSegCurvetoQuadraticRel = (SVGPathSegCurvetoQuadraticRel)seg;
/*     */           
/* 112 */           h.curvetoQuadraticRel(sVGPathSegCurvetoQuadraticRel.getX1(), sVGPathSegCurvetoQuadraticRel.getY1(), sVGPathSegCurvetoQuadraticRel.getX(), sVGPathSegCurvetoQuadraticRel.getY());
/*     */           break;
/*     */ 
/*     */         
/*     */         case 10:
/* 117 */           sVGPathSegArcAbs = (SVGPathSegArcAbs)seg;
/* 118 */           h.arcAbs(sVGPathSegArcAbs.getR1(), sVGPathSegArcAbs.getR2(), sVGPathSegArcAbs.getAngle(), sVGPathSegArcAbs.getLargeArcFlag(), sVGPathSegArcAbs.getSweepFlag(), sVGPathSegArcAbs.getX(), sVGPathSegArcAbs.getY());
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 11:
/* 125 */           sVGPathSegArcRel = (SVGPathSegArcRel)seg;
/* 126 */           h.arcRel(sVGPathSegArcRel.getR1(), sVGPathSegArcRel.getR2(), sVGPathSegArcRel.getAngle(), sVGPathSegArcRel.getLargeArcFlag(), sVGPathSegArcRel.getSweepFlag(), sVGPathSegArcRel.getX(), sVGPathSegArcRel.getY());
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 12:
/* 133 */           sVGPathSegLinetoHorizontalAbs = (SVGPathSegLinetoHorizontalAbs)seg;
/*     */           
/* 135 */           h.linetoHorizontalAbs(sVGPathSegLinetoHorizontalAbs.getX());
/*     */           break;
/*     */         
/*     */         case 13:
/* 139 */           sVGPathSegLinetoHorizontalRel = (SVGPathSegLinetoHorizontalRel)seg;
/*     */           
/* 141 */           h.linetoHorizontalRel(sVGPathSegLinetoHorizontalRel.getX());
/*     */           break;
/*     */         
/*     */         case 14:
/* 145 */           sVGPathSegLinetoVerticalAbs = (SVGPathSegLinetoVerticalAbs)seg;
/*     */           
/* 147 */           h.linetoVerticalAbs(sVGPathSegLinetoVerticalAbs.getY());
/*     */           break;
/*     */         
/*     */         case 15:
/* 151 */           sVGPathSegLinetoVerticalRel = (SVGPathSegLinetoVerticalRel)seg;
/*     */           
/* 153 */           h.linetoVerticalRel(sVGPathSegLinetoVerticalRel.getY());
/*     */           break;
/*     */         
/*     */         case 16:
/* 157 */           sVGPathSegCurvetoCubicSmoothAbs = (SVGPathSegCurvetoCubicSmoothAbs)seg;
/*     */           
/* 159 */           h.curvetoCubicSmoothAbs(sVGPathSegCurvetoCubicSmoothAbs.getX2(), sVGPathSegCurvetoCubicSmoothAbs.getY2(), sVGPathSegCurvetoCubicSmoothAbs.getX(), sVGPathSegCurvetoCubicSmoothAbs.getY());
/*     */           break;
/*     */ 
/*     */         
/*     */         case 17:
/* 164 */           sVGPathSegCurvetoCubicSmoothRel = (SVGPathSegCurvetoCubicSmoothRel)seg;
/*     */           
/* 166 */           h.curvetoCubicSmoothRel(sVGPathSegCurvetoCubicSmoothRel.getX2(), sVGPathSegCurvetoCubicSmoothRel.getY2(), sVGPathSegCurvetoCubicSmoothRel.getX(), sVGPathSegCurvetoCubicSmoothRel.getY());
/*     */           break;
/*     */ 
/*     */         
/*     */         case 18:
/* 171 */           sVGPathSegCurvetoQuadraticSmoothAbs = (SVGPathSegCurvetoQuadraticSmoothAbs)seg;
/*     */           
/* 173 */           h.curvetoQuadraticSmoothAbs(sVGPathSegCurvetoQuadraticSmoothAbs.getX(), sVGPathSegCurvetoQuadraticSmoothAbs.getY());
/*     */           break;
/*     */         
/*     */         case 19:
/* 177 */           s = (SVGPathSegCurvetoQuadraticSmoothRel)seg;
/*     */           
/* 179 */           h.curvetoQuadraticSmoothRel(s.getX(), s.getY());
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 184 */     h.endPath();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/SVGAnimatedPathDataSupport.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */