/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.apache.batik.parser.DefaultPathHandler;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PathHandler;
/*     */ import org.apache.batik.parser.PathParser;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGException;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGPathSegList
/*     */   extends AbstractSVGList
/*     */   implements SVGPathSegConstants, SVGPathSegList
/*     */ {
/*     */   public static final String SVG_PATHSEG_LIST_SEPARATOR = " ";
/*     */   
/*     */   protected String getItemSeparator() {
/*  76 */     return " ";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract SVGException createSVGException(short paramShort, String paramString, Object[] paramArrayOfObject);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSeg initialize(SVGPathSeg newItem) throws DOMException, SVGException {
/*  94 */     return (SVGPathSeg)initializeImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSeg getItem(int index) throws DOMException {
/* 102 */     return (SVGPathSeg)getItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSeg insertItemBefore(SVGPathSeg newItem, int index) throws DOMException, SVGException {
/* 110 */     return (SVGPathSeg)insertItemBeforeImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSeg replaceItem(SVGPathSeg newItem, int index) throws DOMException, SVGException {
/* 118 */     return (SVGPathSeg)replaceItemImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSeg removeItem(int index) throws DOMException {
/* 126 */     return (SVGPathSeg)removeItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSeg appendItem(SVGPathSeg newItem) throws DOMException, SVGException {
/* 134 */     return (SVGPathSeg)appendItemImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGItem createSVGItem(Object newItem) {
/* 141 */     SVGPathSeg pathSeg = (SVGPathSeg)newItem;
/*     */     
/* 143 */     return createPathSegItem(pathSeg);
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
/*     */   protected void doParse(String value, ListHandler handler) throws ParseException {
/* 155 */     PathParser pathParser = new PathParser();
/*     */     
/* 157 */     PathSegListBuilder builder = new PathSegListBuilder(handler);
/*     */     
/* 159 */     pathParser.setPathHandler((PathHandler)builder);
/* 160 */     pathParser.parse(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkItemType(Object newItem) {
/* 168 */     if (!(newItem instanceof SVGPathSeg)) {
/* 169 */       createSVGException((short)0, "expected SVGPathSeg", (Object[])null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGPathSegItem createPathSegItem(SVGPathSeg pathSeg) {
/* 180 */     SVGPathSegItem pathSegItem = null;
/*     */     
/* 182 */     short type = pathSeg.getPathSegType();
/*     */     
/* 184 */     switch (type) {
/*     */       case 10:
/*     */       case 11:
/* 187 */         pathSegItem = new SVGPathSegArcItem(pathSeg);
/*     */         break;
/*     */       case 1:
/* 190 */         pathSegItem = new SVGPathSegItem(pathSeg);
/*     */         break;
/*     */       case 6:
/*     */       case 7:
/* 194 */         pathSegItem = new SVGPathSegCurvetoCubicItem(pathSeg);
/*     */         break;
/*     */       case 16:
/*     */       case 17:
/* 198 */         pathSegItem = new SVGPathSegCurvetoCubicSmoothItem(pathSeg);
/*     */         break;
/*     */       case 8:
/*     */       case 9:
/* 202 */         pathSegItem = new SVGPathSegCurvetoQuadraticItem(pathSeg);
/*     */         break;
/*     */       case 18:
/*     */       case 19:
/* 206 */         pathSegItem = new SVGPathSegCurvetoQuadraticSmoothItem(pathSeg);
/*     */         break;
/*     */       case 2:
/*     */       case 3:
/*     */       case 4:
/*     */       case 5:
/* 212 */         pathSegItem = new SVGPathSegMovetoLinetoItem(pathSeg);
/*     */         break;
/*     */       case 12:
/*     */       case 13:
/* 216 */         pathSegItem = new SVGPathSegLinetoHorizontalItem(pathSeg);
/*     */         break;
/*     */       case 14:
/*     */       case 15:
/* 220 */         pathSegItem = new SVGPathSegLinetoVerticalItem(pathSeg);
/*     */         break;
/*     */     } 
/*     */     
/* 224 */     return pathSegItem;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class SVGPathSegMovetoLinetoItem
/*     */     extends SVGPathSegItem
/*     */     implements SVGPathSegLinetoAbs, SVGPathSegLinetoRel, SVGPathSegMovetoAbs, SVGPathSegMovetoRel
/*     */   {
/*     */     public SVGPathSegMovetoLinetoItem(short type, String letter, float x, float y) {
/* 235 */       super(type, letter);
/* 236 */       setX(x);
/* 237 */       setY(y);
/*     */     }
/*     */     
/*     */     public SVGPathSegMovetoLinetoItem(SVGPathSeg pathSeg) {
/* 241 */       this.type = pathSeg.getPathSegType();
/* 242 */       switch (this.type) {
/*     */         case 5:
/* 244 */           this.letter = "l";
/* 245 */           setX(((SVGPathSegLinetoRel)pathSeg).getX());
/* 246 */           setY(((SVGPathSegLinetoRel)pathSeg).getY());
/*     */           break;
/*     */         case 4:
/* 249 */           this.letter = "L";
/* 250 */           setX(((SVGPathSegLinetoAbs)pathSeg).getX());
/* 251 */           setY(((SVGPathSegLinetoAbs)pathSeg).getY());
/*     */           break;
/*     */         case 3:
/* 254 */           this.letter = "m";
/* 255 */           setX(((SVGPathSegMovetoRel)pathSeg).getX());
/* 256 */           setY(((SVGPathSegMovetoRel)pathSeg).getY());
/*     */           break;
/*     */         case 2:
/* 259 */           this.letter = "M";
/* 260 */           setX(((SVGPathSegMovetoAbs)pathSeg).getX());
/* 261 */           setY(((SVGPathSegMovetoAbs)pathSeg).getY());
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void setX(float x) {
/* 268 */       super.setX(x);
/* 269 */       resetAttribute();
/*     */     }
/*     */     public void setY(float y) {
/* 272 */       super.setY(y);
/* 273 */       resetAttribute();
/*     */     }
/*     */     
/*     */     protected String getStringValue() {
/* 277 */       return this.letter + ' ' + Float.toString(getX()) + ' ' + Float.toString(getY());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class SVGPathSegCurvetoCubicItem
/*     */     extends SVGPathSegItem
/*     */     implements SVGPathSegCurvetoCubicAbs, SVGPathSegCurvetoCubicRel
/*     */   {
/*     */     public SVGPathSegCurvetoCubicItem(short type, String letter, float x1, float y1, float x2, float y2, float x, float y) {
/* 292 */       super(type, letter);
/* 293 */       setX(x);
/* 294 */       setY(y);
/* 295 */       setX1(x1);
/* 296 */       setY1(y1);
/* 297 */       setX2(x2);
/* 298 */       setY2(y2);
/*     */     }
/*     */     
/*     */     public SVGPathSegCurvetoCubicItem(SVGPathSeg pathSeg) {
/* 302 */       this.type = pathSeg.getPathSegType();
/* 303 */       switch (this.type) {
/*     */         case 6:
/* 305 */           this.letter = "C";
/* 306 */           setX(((SVGPathSegCurvetoCubicAbs)pathSeg).getX());
/* 307 */           setY(((SVGPathSegCurvetoCubicAbs)pathSeg).getY());
/* 308 */           setX1(((SVGPathSegCurvetoCubicAbs)pathSeg).getX1());
/* 309 */           setY1(((SVGPathSegCurvetoCubicAbs)pathSeg).getY1());
/* 310 */           setX2(((SVGPathSegCurvetoCubicAbs)pathSeg).getX2());
/* 311 */           setY2(((SVGPathSegCurvetoCubicAbs)pathSeg).getY2());
/*     */           break;
/*     */         case 7:
/* 314 */           this.letter = "c";
/* 315 */           setX(((SVGPathSegCurvetoCubicRel)pathSeg).getX());
/* 316 */           setY(((SVGPathSegCurvetoCubicRel)pathSeg).getY());
/* 317 */           setX1(((SVGPathSegCurvetoCubicRel)pathSeg).getX1());
/* 318 */           setY1(((SVGPathSegCurvetoCubicRel)pathSeg).getY1());
/* 319 */           setX2(((SVGPathSegCurvetoCubicRel)pathSeg).getX2());
/* 320 */           setY2(((SVGPathSegCurvetoCubicRel)pathSeg).getY2());
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void setX(float x) {
/* 327 */       super.setX(x);
/* 328 */       resetAttribute();
/*     */     }
/*     */     public void setY(float y) {
/* 331 */       super.setY(y);
/* 332 */       resetAttribute();
/*     */     }
/*     */     
/*     */     public void setX1(float x1) {
/* 336 */       super.setX1(x1);
/* 337 */       resetAttribute();
/*     */     }
/*     */     public void setY1(float y1) {
/* 340 */       super.setY1(y1);
/* 341 */       resetAttribute();
/*     */     }
/*     */     
/*     */     public void setX2(float x2) {
/* 345 */       super.setX2(x2);
/* 346 */       resetAttribute();
/*     */     }
/*     */     public void setY2(float y2) {
/* 349 */       super.setY2(y2);
/* 350 */       resetAttribute();
/*     */     }
/*     */     
/*     */     protected String getStringValue() {
/* 354 */       return this.letter + ' ' + Float.toString(getX1()) + ' ' + Float.toString(getY1()) + ' ' + Float.toString(getX2()) + ' ' + Float.toString(getY2()) + ' ' + Float.toString(getX()) + ' ' + Float.toString(getY());
/*     */     }
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
/*     */   public class SVGPathSegCurvetoQuadraticItem
/*     */     extends SVGPathSegItem
/*     */     implements SVGPathSegCurvetoQuadraticAbs, SVGPathSegCurvetoQuadraticRel
/*     */   {
/*     */     public SVGPathSegCurvetoQuadraticItem(short type, String letter, float x1, float y1, float x, float y) {
/* 376 */       super(type, letter);
/* 377 */       setX(x);
/* 378 */       setY(y);
/* 379 */       setX1(x1);
/* 380 */       setY1(y1);
/*     */     }
/*     */     
/*     */     public SVGPathSegCurvetoQuadraticItem(SVGPathSeg pathSeg) {
/* 384 */       this.type = pathSeg.getPathSegType();
/* 385 */       switch (this.type) {
/*     */         case 8:
/* 387 */           this.letter = "Q";
/* 388 */           setX(((SVGPathSegCurvetoQuadraticAbs)pathSeg).getX());
/* 389 */           setY(((SVGPathSegCurvetoQuadraticAbs)pathSeg).getY());
/* 390 */           setX1(((SVGPathSegCurvetoQuadraticAbs)pathSeg).getX1());
/* 391 */           setY1(((SVGPathSegCurvetoQuadraticAbs)pathSeg).getY1());
/*     */           break;
/*     */         case 9:
/* 394 */           this.letter = "q";
/* 395 */           setX(((SVGPathSegCurvetoQuadraticRel)pathSeg).getX());
/* 396 */           setY(((SVGPathSegCurvetoQuadraticRel)pathSeg).getY());
/* 397 */           setX1(((SVGPathSegCurvetoQuadraticRel)pathSeg).getX1());
/* 398 */           setY1(((SVGPathSegCurvetoQuadraticRel)pathSeg).getY1());
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void setX(float x) {
/* 406 */       super.setX(x);
/* 407 */       resetAttribute();
/*     */     }
/*     */     public void setY(float y) {
/* 410 */       super.setY(y);
/* 411 */       resetAttribute();
/*     */     }
/*     */     
/*     */     public void setX1(float x1) {
/* 415 */       super.setX1(x1);
/* 416 */       resetAttribute();
/*     */     }
/*     */     public void setY1(float y1) {
/* 419 */       super.setY1(y1);
/* 420 */       resetAttribute();
/*     */     }
/*     */ 
/*     */     
/*     */     protected String getStringValue() {
/* 425 */       return this.letter + ' ' + Float.toString(getX1()) + ' ' + Float.toString(getY1()) + ' ' + Float.toString(getX()) + ' ' + Float.toString(getY());
/*     */     }
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
/*     */   public class SVGPathSegArcItem
/*     */     extends SVGPathSegItem
/*     */     implements SVGPathSegArcAbs, SVGPathSegArcRel
/*     */   {
/*     */     public SVGPathSegArcItem(short type, String letter, float r1, float r2, float angle, boolean largeArcFlag, boolean sweepFlag, float x, float y) {
/* 445 */       super(type, letter);
/* 446 */       setX(x);
/* 447 */       setY(y);
/* 448 */       setR1(r1);
/* 449 */       setR2(r2);
/* 450 */       setAngle(angle);
/* 451 */       setLargeArcFlag(largeArcFlag);
/* 452 */       setSweepFlag(sweepFlag);
/*     */     }
/*     */     
/*     */     public SVGPathSegArcItem(SVGPathSeg pathSeg) {
/* 456 */       this.type = pathSeg.getPathSegType();
/* 457 */       switch (this.type) {
/*     */         case 10:
/* 459 */           this.letter = "A";
/* 460 */           setX(((SVGPathSegArcAbs)pathSeg).getX());
/* 461 */           setY(((SVGPathSegArcAbs)pathSeg).getY());
/* 462 */           setR1(((SVGPathSegArcAbs)pathSeg).getR1());
/* 463 */           setR2(((SVGPathSegArcAbs)pathSeg).getR2());
/* 464 */           setAngle(((SVGPathSegArcAbs)pathSeg).getAngle());
/* 465 */           setLargeArcFlag(((SVGPathSegArcAbs)pathSeg).getLargeArcFlag());
/* 466 */           setSweepFlag(((SVGPathSegArcAbs)pathSeg).getSweepFlag());
/*     */           break;
/*     */         case 11:
/* 469 */           this.letter = "a";
/* 470 */           setX(((SVGPathSegArcRel)pathSeg).getX());
/* 471 */           setY(((SVGPathSegArcRel)pathSeg).getY());
/* 472 */           setR1(((SVGPathSegArcRel)pathSeg).getR1());
/* 473 */           setR2(((SVGPathSegArcRel)pathSeg).getR2());
/* 474 */           setAngle(((SVGPathSegArcRel)pathSeg).getAngle());
/* 475 */           setLargeArcFlag(((SVGPathSegArcRel)pathSeg).getLargeArcFlag());
/* 476 */           setSweepFlag(((SVGPathSegArcRel)pathSeg).getSweepFlag());
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void setX(float x) {
/* 483 */       super.setX(x);
/* 484 */       resetAttribute();
/*     */     }
/*     */     public void setY(float y) {
/* 487 */       super.setY(y);
/* 488 */       resetAttribute();
/*     */     }
/*     */     
/*     */     public void setR1(float r1) {
/* 492 */       super.setR1(r1);
/* 493 */       resetAttribute();
/*     */     }
/*     */     public void setR2(float r2) {
/* 496 */       super.setR2(r2);
/* 497 */       resetAttribute();
/*     */     }
/*     */     
/*     */     public void setAngle(float angle) {
/* 501 */       super.setAngle(angle);
/* 502 */       resetAttribute();
/*     */     }
/*     */     
/*     */     public boolean getSweepFlag() {
/* 506 */       return isSweepFlag();
/*     */     }
/*     */     
/*     */     public void setSweepFlag(boolean sweepFlag) {
/* 510 */       super.setSweepFlag(sweepFlag);
/* 511 */       resetAttribute();
/*     */     }
/*     */     
/*     */     public boolean getLargeArcFlag() {
/* 515 */       return isLargeArcFlag();
/*     */     }
/*     */     
/*     */     public void setLargeArcFlag(boolean largeArcFlag) {
/* 519 */       super.setLargeArcFlag(largeArcFlag);
/* 520 */       resetAttribute();
/*     */     }
/*     */     
/*     */     protected String getStringValue() {
/* 524 */       return this.letter + ' ' + Float.toString(getR1()) + ' ' + Float.toString(getR2()) + ' ' + Float.toString(getAngle()) + ' ' + (isLargeArcFlag() ? "1" : "0") + ' ' + (isSweepFlag() ? "1" : "0") + ' ' + Float.toString(getX()) + ' ' + Float.toString(getY());
/*     */     }
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
/*     */   public class SVGPathSegLinetoHorizontalItem
/*     */     extends SVGPathSegItem
/*     */     implements SVGPathSegLinetoHorizontalAbs, SVGPathSegLinetoHorizontalRel
/*     */   {
/*     */     public SVGPathSegLinetoHorizontalItem(short type, String letter, float value) {
/* 549 */       super(type, letter);
/* 550 */       setX(value);
/*     */     }
/*     */     public SVGPathSegLinetoHorizontalItem(SVGPathSeg pathSeg) {
/* 553 */       this.type = pathSeg.getPathSegType();
/* 554 */       switch (this.type) {
/*     */         case 12:
/* 556 */           this.letter = "H";
/* 557 */           setX(((SVGPathSegLinetoHorizontalAbs)pathSeg).getX());
/*     */           break;
/*     */         case 13:
/* 560 */           this.letter = "h";
/* 561 */           setX(((SVGPathSegLinetoHorizontalRel)pathSeg).getX());
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void setX(float x) {
/* 568 */       super.setX(x);
/* 569 */       resetAttribute();
/*     */     }
/*     */     
/*     */     protected String getStringValue() {
/* 573 */       return this.letter + ' ' + Float.toString(getX());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class SVGPathSegLinetoVerticalItem
/*     */     extends SVGPathSegItem
/*     */     implements SVGPathSegLinetoVerticalAbs, SVGPathSegLinetoVerticalRel
/*     */   {
/*     */     public SVGPathSegLinetoVerticalItem(short type, String letter, float value) {
/* 586 */       super(type, letter);
/* 587 */       setY(value);
/*     */     }
/*     */     
/*     */     public SVGPathSegLinetoVerticalItem(SVGPathSeg pathSeg) {
/* 591 */       this.type = pathSeg.getPathSegType();
/* 592 */       switch (this.type) {
/*     */         case 14:
/* 594 */           this.letter = "V";
/* 595 */           setY(((SVGPathSegLinetoVerticalAbs)pathSeg).getY());
/*     */           break;
/*     */         case 15:
/* 598 */           this.letter = "v";
/* 599 */           setY(((SVGPathSegLinetoVerticalRel)pathSeg).getY());
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void setY(float y) {
/* 606 */       super.setY(y);
/* 607 */       resetAttribute();
/*     */     }
/*     */     
/*     */     protected String getStringValue() {
/* 611 */       return this.letter + ' ' + Float.toString(getY());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class SVGPathSegCurvetoCubicSmoothItem
/*     */     extends SVGPathSegItem
/*     */     implements SVGPathSegCurvetoCubicSmoothAbs, SVGPathSegCurvetoCubicSmoothRel
/*     */   {
/*     */     public SVGPathSegCurvetoCubicSmoothItem(short type, String letter, float x2, float y2, float x, float y) {
/* 623 */       super(type, letter);
/* 624 */       setX(x);
/* 625 */       setY(y);
/* 626 */       setX2(x2);
/* 627 */       setY2(y2);
/*     */     }
/*     */     
/*     */     public SVGPathSegCurvetoCubicSmoothItem(SVGPathSeg pathSeg) {
/* 631 */       this.type = pathSeg.getPathSegType();
/* 632 */       switch (this.type) {
/*     */         case 16:
/* 634 */           this.letter = "S";
/* 635 */           setX(((SVGPathSegCurvetoCubicSmoothAbs)pathSeg).getX());
/* 636 */           setY(((SVGPathSegCurvetoCubicSmoothAbs)pathSeg).getY());
/* 637 */           setX2(((SVGPathSegCurvetoCubicSmoothAbs)pathSeg).getX2());
/* 638 */           setY2(((SVGPathSegCurvetoCubicSmoothAbs)pathSeg).getY2());
/*     */           break;
/*     */         case 17:
/* 641 */           this.letter = "s";
/* 642 */           setX(((SVGPathSegCurvetoCubicSmoothRel)pathSeg).getX());
/* 643 */           setY(((SVGPathSegCurvetoCubicSmoothRel)pathSeg).getY());
/* 644 */           setX2(((SVGPathSegCurvetoCubicSmoothRel)pathSeg).getX2());
/* 645 */           setY2(((SVGPathSegCurvetoCubicSmoothRel)pathSeg).getY2());
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void setX(float x) {
/* 652 */       super.setX(x);
/* 653 */       resetAttribute();
/*     */     }
/*     */     public void setY(float y) {
/* 656 */       super.setY(y);
/* 657 */       resetAttribute();
/*     */     }
/*     */     
/*     */     public void setX2(float x2) {
/* 661 */       super.setX2(x2);
/* 662 */       resetAttribute();
/*     */     }
/*     */     public void setY2(float y2) {
/* 665 */       super.setY2(y2);
/* 666 */       resetAttribute();
/*     */     }
/*     */     
/*     */     protected String getStringValue() {
/* 670 */       return this.letter + ' ' + Float.toString(getX2()) + ' ' + Float.toString(getY2()) + ' ' + Float.toString(getX()) + ' ' + Float.toString(getY());
/*     */     }
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
/*     */   public class SVGPathSegCurvetoQuadraticSmoothItem
/*     */     extends SVGPathSegItem
/*     */     implements SVGPathSegCurvetoQuadraticSmoothAbs, SVGPathSegCurvetoQuadraticSmoothRel
/*     */   {
/*     */     public SVGPathSegCurvetoQuadraticSmoothItem(short type, String letter, float x, float y) {
/* 688 */       super(type, letter);
/* 689 */       setX(x);
/* 690 */       setY(y);
/*     */     }
/*     */     
/*     */     public SVGPathSegCurvetoQuadraticSmoothItem(SVGPathSeg pathSeg) {
/* 694 */       this.type = pathSeg.getPathSegType();
/* 695 */       switch (this.type) {
/*     */         case 18:
/* 697 */           this.letter = "T";
/* 698 */           setX(((SVGPathSegCurvetoQuadraticSmoothAbs)pathSeg).getX());
/* 699 */           setY(((SVGPathSegCurvetoQuadraticSmoothAbs)pathSeg).getY());
/*     */           break;
/*     */         case 19:
/* 702 */           this.letter = "t";
/* 703 */           setX(((SVGPathSegCurvetoQuadraticSmoothRel)pathSeg).getX());
/* 704 */           setY(((SVGPathSegCurvetoQuadraticSmoothRel)pathSeg).getY());
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public void setX(float x) {
/* 711 */       super.setX(x);
/* 712 */       resetAttribute();
/*     */     }
/*     */     public void setY(float y) {
/* 715 */       super.setY(y);
/* 716 */       resetAttribute();
/*     */     }
/*     */     
/*     */     protected String getStringValue() {
/* 720 */       return this.letter + ' ' + Float.toString(getX()) + ' ' + Float.toString(getY());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class PathSegListBuilder
/*     */     extends DefaultPathHandler
/*     */   {
/*     */     protected ListHandler listHandler;
/*     */ 
/*     */     
/*     */     public PathSegListBuilder(ListHandler listHandler) {
/* 733 */       this.listHandler = listHandler;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void startPath() throws ParseException {
/* 739 */       this.listHandler.startList();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endPath() throws ParseException {
/* 746 */       this.listHandler.endList();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void movetoRel(float x, float y) throws ParseException {
/* 753 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem((short)3, "m", x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void movetoAbs(float x, float y) throws ParseException {
/* 762 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem((short)2, "M", x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void closePath() throws ParseException {
/* 771 */       this.listHandler.item(new SVGPathSegItem((short)1, "z"));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoRel(float x, float y) throws ParseException {
/* 780 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem((short)5, "l", x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoAbs(float x, float y) throws ParseException {
/* 789 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegMovetoLinetoItem((short)4, "L", x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoHorizontalRel(float x) throws ParseException {
/* 798 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegLinetoHorizontalItem((short)13, "h", x));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoHorizontalAbs(float x) throws ParseException {
/* 807 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegLinetoHorizontalItem((short)12, "H", x));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoVerticalRel(float y) throws ParseException {
/* 816 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegLinetoVerticalItem((short)15, "v", y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void linetoVerticalAbs(float y) throws ParseException {
/* 825 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegLinetoVerticalItem((short)14, "V", y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoCubicRel(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {
/* 837 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoCubicItem((short)7, "c", x1, y1, x2, y2, x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoCubicAbs(float x1, float y1, float x2, float y2, float x, float y) throws ParseException {
/* 849 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoCubicItem((short)6, "C", x1, y1, x2, y2, x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoCubicSmoothRel(float x2, float y2, float x, float y) throws ParseException {
/* 860 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoCubicSmoothItem((short)17, "s", x2, y2, x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoCubicSmoothAbs(float x2, float y2, float x, float y) throws ParseException {
/* 871 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoCubicSmoothItem((short)16, "S", x2, y2, x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoQuadraticRel(float x1, float y1, float x, float y) throws ParseException {
/* 882 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticItem((short)9, "q", x1, y1, x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoQuadraticAbs(float x1, float y1, float x, float y) throws ParseException {
/* 893 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticItem((short)8, "Q", x1, y1, x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoQuadraticSmoothRel(float x, float y) throws ParseException {
/* 903 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticSmoothItem((short)19, "t", x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void curvetoQuadraticSmoothAbs(float x, float y) throws ParseException {
/* 913 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegCurvetoQuadraticSmoothItem((short)18, "T", x, y));
/*     */     }
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
/*     */     public void arcRel(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {
/* 926 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegArcItem((short)11, "a", rx, ry, xAxisRotation, largeArcFlag, sweepFlag, x, y));
/*     */     }
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
/*     */     public void arcAbs(float rx, float ry, float xAxisRotation, boolean largeArcFlag, boolean sweepFlag, float x, float y) throws ParseException {
/* 939 */       this.listHandler.item(new AbstractSVGPathSegList.SVGPathSegArcItem((short)10, "A", rx, ry, xAxisRotation, largeArcFlag, sweepFlag, x, y));
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGPathSegList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */