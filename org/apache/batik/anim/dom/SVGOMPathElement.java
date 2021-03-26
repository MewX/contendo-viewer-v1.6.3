/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.batik.dom.svg.SVGPathSegConstants;
/*     */ import org.apache.batik.util.DoublyIndexedTable;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.svg.SVGAnimatedNumber;
/*     */ import org.w3c.dom.svg.SVGPathElement;
/*     */ import org.w3c.dom.svg.SVGPathSegArcAbs;
/*     */ import org.w3c.dom.svg.SVGPathSegArcRel;
/*     */ import org.w3c.dom.svg.SVGPathSegClosePath;
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
/*     */ import org.w3c.dom.svg.SVGPoint;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class SVGOMPathElement
/*     */   extends SVGGraphicsElement
/*     */   implements SVGPathSegConstants, SVGPathElement
/*     */ {
/*     */   protected static DoublyIndexedTable xmlTraitInformation;
/*     */   protected SVGOMAnimatedPathData d;
/*     */   
/*     */   static {
/*  68 */     DoublyIndexedTable t = new DoublyIndexedTable(SVGGraphicsElement.xmlTraitInformation);
/*     */     
/*  70 */     t.put(null, "d", new TraitInformation(true, 22));
/*     */     
/*  72 */     t.put(null, "pathLength", new TraitInformation(true, 2));
/*     */     
/*  74 */     xmlTraitInformation = t;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGOMPathElement() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMPathElement(String prefix, AbstractDocument owner) {
/*  94 */     super(prefix, owner);
/*  95 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initializeAllLiveAttributes() {
/* 102 */     super.initializeAllLiveAttributes();
/* 103 */     initializeLiveAttributes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void initializeLiveAttributes() {
/* 110 */     this.d = createLiveAnimatedPathData(null, "d", "");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocalName() {
/* 117 */     return "path";
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGAnimatedNumber getPathLength() {
/* 124 */     throw new UnsupportedOperationException("SVGPathElement.getPathLength is not implemented");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getTotalLength() {
/* 132 */     return SVGPathSupport.getTotalLength(this);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint getPointAtLength(float distance) {
/* 139 */     return SVGPathSupport.getPointAtLength(this, distance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPathSegAtLength(float distance) {
/* 146 */     return SVGPathSupport.getPathSegAtLength(this, distance);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGOMAnimatedPathData getAnimatedPathData() {
/* 154 */     return this.d;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegList getPathSegList() {
/* 161 */     return this.d.getPathSegList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegList getNormalizedPathSegList() {
/* 168 */     return this.d.getNormalizedPathSegList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegList getAnimatedPathSegList() {
/* 175 */     return this.d.getAnimatedPathSegList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegList getAnimatedNormalizedPathSegList() {
/* 183 */     return this.d.getAnimatedNormalizedPathSegList();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegClosePath createSVGPathSegClosePath() {
/* 192 */     return new SVGPathSegClosePath() {
/*     */         public short getPathSegType() {
/* 194 */           return 1;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 197 */           return "z";
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegMovetoAbs createSVGPathSegMovetoAbs(final float x_value, final float y_value) {
/* 207 */     return new SVGPathSegMovetoAbs() {
/* 208 */         protected float x = x_value;
/* 209 */         protected float y = y_value;
/*     */         
/*     */         public short getPathSegType() {
/* 212 */           return 2;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 215 */           return "M";
/*     */         }
/*     */         public float getX() {
/* 218 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 221 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 224 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 227 */           this.y = y;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegMovetoRel createSVGPathSegMovetoRel(final float x_value, final float y_value) {
/* 237 */     return new SVGPathSegMovetoRel() {
/* 238 */         protected float x = x_value;
/* 239 */         protected float y = y_value;
/*     */         
/*     */         public short getPathSegType() {
/* 242 */           return 3;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 245 */           return "m";
/*     */         }
/*     */         public float getX() {
/* 248 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 251 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 254 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 257 */           this.y = y;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegLinetoAbs createSVGPathSegLinetoAbs(final float x_value, final float y_value) {
/* 267 */     return new SVGPathSegLinetoAbs() {
/* 268 */         protected float x = x_value;
/* 269 */         protected float y = y_value;
/*     */         
/*     */         public short getPathSegType() {
/* 272 */           return 4;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 275 */           return "L";
/*     */         }
/*     */         public float getX() {
/* 278 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 281 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 284 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 287 */           this.y = y;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegLinetoRel createSVGPathSegLinetoRel(final float x_value, final float y_value) {
/* 297 */     return new SVGPathSegLinetoRel() {
/* 298 */         protected float x = x_value;
/* 299 */         protected float y = y_value;
/*     */         
/*     */         public short getPathSegType() {
/* 302 */           return 5;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 305 */           return "l";
/*     */         }
/*     */         public float getX() {
/* 308 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 311 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 314 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 317 */           this.y = y;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegLinetoHorizontalAbs createSVGPathSegLinetoHorizontalAbs(final float x_value) {
/* 327 */     return new SVGPathSegLinetoHorizontalAbs() {
/* 328 */         protected float x = x_value;
/*     */         
/*     */         public short getPathSegType() {
/* 331 */           return 12;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 334 */           return "H";
/*     */         }
/*     */         public float getX() {
/* 337 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 340 */           this.x = x;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegLinetoHorizontalRel createSVGPathSegLinetoHorizontalRel(final float x_value) {
/* 350 */     return new SVGPathSegLinetoHorizontalRel() {
/* 351 */         protected float x = x_value;
/*     */         
/*     */         public short getPathSegType() {
/* 354 */           return 13;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 357 */           return "h";
/*     */         }
/*     */         public float getX() {
/* 360 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 363 */           this.x = x;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegLinetoVerticalAbs createSVGPathSegLinetoVerticalAbs(final float y_value) {
/* 373 */     return new SVGPathSegLinetoVerticalAbs() {
/* 374 */         protected float y = y_value;
/*     */         
/*     */         public short getPathSegType() {
/* 377 */           return 14;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 380 */           return "V";
/*     */         }
/*     */         public float getY() {
/* 383 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 386 */           this.y = y;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegLinetoVerticalRel createSVGPathSegLinetoVerticalRel(final float y_value) {
/* 396 */     return new SVGPathSegLinetoVerticalRel() {
/* 397 */         protected float y = y_value;
/*     */         
/*     */         public short getPathSegType() {
/* 400 */           return 15;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 403 */           return "v";
/*     */         }
/*     */         public float getY() {
/* 406 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 409 */           this.y = y;
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
/*     */   
/*     */   public SVGPathSegCurvetoCubicAbs createSVGPathSegCurvetoCubicAbs(final float x_value, final float y_value, final float x1_value, final float y1_value, final float x2_value, final float y2_value) {
/* 422 */     return new SVGPathSegCurvetoCubicAbs() {
/* 423 */         protected float x = x_value;
/* 424 */         protected float y = y_value;
/* 425 */         protected float x1 = x1_value;
/* 426 */         protected float y1 = y1_value;
/* 427 */         protected float x2 = x2_value;
/* 428 */         protected float y2 = y2_value;
/*     */         
/*     */         public short getPathSegType() {
/* 431 */           return 6;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 434 */           return "C";
/*     */         }
/*     */         public float getX() {
/* 437 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 440 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 443 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 446 */           this.y = y;
/*     */         }
/*     */         public float getX1() {
/* 449 */           return this.x1;
/*     */         }
/*     */         public void setX1(float x1) {
/* 452 */           this.x1 = x1;
/*     */         }
/*     */         public float getY1() {
/* 455 */           return this.y1;
/*     */         }
/*     */         public void setY1(float y1) {
/* 458 */           this.y1 = y1;
/*     */         }
/*     */         public float getX2() {
/* 461 */           return this.x2;
/*     */         }
/*     */         public void setX2(float x2) {
/* 464 */           this.x2 = x2;
/*     */         }
/*     */         public float getY2() {
/* 467 */           return this.y2;
/*     */         }
/*     */         public void setY2(float y2) {
/* 470 */           this.y2 = y2;
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
/*     */   
/*     */   public SVGPathSegCurvetoCubicRel createSVGPathSegCurvetoCubicRel(final float x_value, final float y_value, final float x1_value, final float y1_value, final float x2_value, final float y2_value) {
/* 483 */     return new SVGPathSegCurvetoCubicRel() {
/* 484 */         protected float x = x_value;
/* 485 */         protected float y = y_value;
/* 486 */         protected float x1 = x1_value;
/* 487 */         protected float y1 = y1_value;
/* 488 */         protected float x2 = x2_value;
/* 489 */         protected float y2 = y2_value;
/*     */         
/*     */         public short getPathSegType() {
/* 492 */           return 7;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 495 */           return "c";
/*     */         }
/*     */         public float getX() {
/* 498 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 501 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 504 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 507 */           this.y = y;
/*     */         }
/*     */         public float getX1() {
/* 510 */           return this.x1;
/*     */         }
/*     */         public void setX1(float x1) {
/* 513 */           this.x1 = x1;
/*     */         }
/*     */         public float getY1() {
/* 516 */           return this.y1;
/*     */         }
/*     */         public void setY1(float y1) {
/* 519 */           this.y1 = y1;
/*     */         }
/*     */         public float getX2() {
/* 522 */           return this.x2;
/*     */         }
/*     */         public void setX2(float x2) {
/* 525 */           this.x2 = x2;
/*     */         }
/*     */         public float getY2() {
/* 528 */           return this.y2;
/*     */         }
/*     */         public void setY2(float y2) {
/* 531 */           this.y2 = y2;
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
/*     */   public SVGPathSegCurvetoQuadraticAbs createSVGPathSegCurvetoQuadraticAbs(final float x_value, final float y_value, final float x1_value, final float y1_value) {
/* 543 */     return new SVGPathSegCurvetoQuadraticAbs() {
/* 544 */         protected float x = x_value;
/* 545 */         protected float y = y_value;
/* 546 */         protected float x1 = x1_value;
/* 547 */         protected float y1 = y1_value;
/*     */         
/*     */         public short getPathSegType() {
/* 550 */           return 8;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 553 */           return "Q";
/*     */         }
/*     */         public float getX() {
/* 556 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 559 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 562 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 565 */           this.y = y;
/*     */         }
/*     */         public float getX1() {
/* 568 */           return this.x1;
/*     */         }
/*     */         public void setX1(float x1) {
/* 571 */           this.x1 = x1;
/*     */         }
/*     */         public float getY1() {
/* 574 */           return this.y1;
/*     */         }
/*     */         public void setY1(float y1) {
/* 577 */           this.y1 = y1;
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
/*     */   public SVGPathSegCurvetoQuadraticRel createSVGPathSegCurvetoQuadraticRel(final float x_value, final float y_value, final float x1_value, final float y1_value) {
/* 589 */     return new SVGPathSegCurvetoQuadraticRel() {
/* 590 */         protected float x = x_value;
/* 591 */         protected float y = y_value;
/* 592 */         protected float x1 = x1_value;
/* 593 */         protected float y1 = y1_value;
/*     */         
/*     */         public short getPathSegType() {
/* 596 */           return 9;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 599 */           return "q";
/*     */         }
/*     */         public float getX() {
/* 602 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 605 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 608 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 611 */           this.y = y;
/*     */         }
/*     */         public float getX1() {
/* 614 */           return this.x1;
/*     */         }
/*     */         public void setX1(float x1) {
/* 617 */           this.x1 = x1;
/*     */         }
/*     */         public float getY1() {
/* 620 */           return this.y1;
/*     */         }
/*     */         public void setY1(float y1) {
/* 623 */           this.y1 = y1;
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
/*     */   
/*     */   public SVGPathSegCurvetoCubicSmoothAbs createSVGPathSegCurvetoCubicSmoothAbs(final float x_value, final float y_value, final float x2_value, final float y2_value) {
/* 636 */     return new SVGPathSegCurvetoCubicSmoothAbs() {
/* 637 */         protected float x = x_value;
/* 638 */         protected float y = y_value;
/* 639 */         protected float x2 = x2_value;
/* 640 */         protected float y2 = y2_value;
/*     */         
/*     */         public short getPathSegType() {
/* 643 */           return 16;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 646 */           return "S";
/*     */         }
/*     */         public float getX() {
/* 649 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 652 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 655 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 658 */           this.y = y;
/*     */         }
/*     */         public float getX2() {
/* 661 */           return this.x2;
/*     */         }
/*     */         public void setX2(float x2) {
/* 664 */           this.x2 = x2;
/*     */         }
/*     */         public float getY2() {
/* 667 */           return this.y2;
/*     */         }
/*     */         public void setY2(float y2) {
/* 670 */           this.y2 = y2;
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
/*     */   
/*     */   public SVGPathSegCurvetoCubicSmoothRel createSVGPathSegCurvetoCubicSmoothRel(final float x_value, final float y_value, final float x2_value, final float y2_value) {
/* 683 */     return new SVGPathSegCurvetoCubicSmoothRel() {
/* 684 */         protected float x = x_value;
/* 685 */         protected float y = y_value;
/* 686 */         protected float x2 = x2_value;
/* 687 */         protected float y2 = y2_value;
/*     */         
/*     */         public short getPathSegType() {
/* 690 */           return 17;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 693 */           return "s";
/*     */         }
/*     */         public float getX() {
/* 696 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 699 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 702 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 705 */           this.y = y;
/*     */         }
/*     */         public float getX2() {
/* 708 */           return this.x2;
/*     */         }
/*     */         public void setX2(float x2) {
/* 711 */           this.x2 = x2;
/*     */         }
/*     */         public float getY2() {
/* 714 */           return this.y2;
/*     */         }
/*     */         public void setY2(float y2) {
/* 717 */           this.y2 = y2;
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
/*     */   public SVGPathSegCurvetoQuadraticSmoothAbs createSVGPathSegCurvetoQuadraticSmoothAbs(final float x_value, final float y_value) {
/* 729 */     return new SVGPathSegCurvetoQuadraticSmoothAbs() {
/* 730 */         protected float x = x_value;
/* 731 */         protected float y = y_value;
/*     */         
/*     */         public short getPathSegType() {
/* 734 */           return 18;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 737 */           return "T";
/*     */         }
/*     */         public float getX() {
/* 740 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 743 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 746 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 749 */           this.y = y;
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
/*     */   
/*     */   public SVGPathSegCurvetoQuadraticSmoothRel createSVGPathSegCurvetoQuadraticSmoothRel(final float x_value, final float y_value) {
/* 762 */     return new SVGPathSegCurvetoQuadraticSmoothRel() {
/* 763 */         protected float x = x_value;
/* 764 */         protected float y = y_value;
/*     */         
/*     */         public short getPathSegType() {
/* 767 */           return 19;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 770 */           return "t";
/*     */         }
/*     */         public float getX() {
/* 773 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 776 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 779 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 782 */           this.y = y;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegArcAbs createSVGPathSegArcAbs(final float x_value, final float y_value, final float r1_value, final float r2_value, final float angle_value, final boolean largeArcFlag_value, final boolean sweepFlag_value) {
/* 797 */     return new SVGPathSegArcAbs() {
/* 798 */         protected float x = x_value;
/* 799 */         protected float y = y_value;
/* 800 */         protected float r1 = r1_value;
/* 801 */         protected float r2 = r2_value;
/* 802 */         protected float angle = angle_value;
/* 803 */         protected boolean largeArcFlag = largeArcFlag_value;
/* 804 */         protected boolean sweepFlag = sweepFlag_value;
/*     */         
/*     */         public short getPathSegType() {
/* 807 */           return 10;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 810 */           return "A";
/*     */         }
/*     */         public float getX() {
/* 813 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 816 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 819 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 822 */           this.y = y;
/*     */         }
/*     */         public float getR1() {
/* 825 */           return this.r1;
/*     */         }
/*     */         public void setR1(float r1) {
/* 828 */           this.r1 = r1;
/*     */         }
/*     */         public float getR2() {
/* 831 */           return this.r2;
/*     */         }
/*     */         public void setR2(float r2) {
/* 834 */           this.r2 = r2;
/*     */         }
/*     */         public float getAngle() {
/* 837 */           return this.angle;
/*     */         }
/*     */         public void setAngle(float angle) {
/* 840 */           this.angle = angle;
/*     */         }
/*     */         public boolean getLargeArcFlag() {
/* 843 */           return this.largeArcFlag;
/*     */         }
/*     */         public void setLargeArcFlag(boolean largeArcFlag) {
/* 846 */           this.largeArcFlag = largeArcFlag;
/*     */         }
/*     */         public boolean getSweepFlag() {
/* 849 */           return this.sweepFlag;
/*     */         }
/*     */         public void setSweepFlag(boolean sweepFlag) {
/* 852 */           this.sweepFlag = sweepFlag;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPathSegArcRel createSVGPathSegArcRel(final float x_value, final float y_value, final float r1_value, final float r2_value, final float angle_value, final boolean largeArcFlag_value, final boolean sweepFlag_value) {
/* 869 */     return new SVGPathSegArcRel() {
/* 870 */         protected float x = x_value;
/* 871 */         protected float y = y_value;
/* 872 */         protected float r1 = r1_value;
/* 873 */         protected float r2 = r2_value;
/* 874 */         protected float angle = angle_value;
/* 875 */         protected boolean largeArcFlag = largeArcFlag_value;
/* 876 */         protected boolean sweepFlag = sweepFlag_value;
/*     */         
/*     */         public short getPathSegType() {
/* 879 */           return 11;
/*     */         }
/*     */         public String getPathSegTypeAsLetter() {
/* 882 */           return "a";
/*     */         }
/*     */         public float getX() {
/* 885 */           return this.x;
/*     */         }
/*     */         public void setX(float x) {
/* 888 */           this.x = x;
/*     */         }
/*     */         public float getY() {
/* 891 */           return this.y;
/*     */         }
/*     */         public void setY(float y) {
/* 894 */           this.y = y;
/*     */         }
/*     */         public float getR1() {
/* 897 */           return this.r1;
/*     */         }
/*     */         public void setR1(float r1) {
/* 900 */           this.r1 = r1;
/*     */         }
/*     */         public float getR2() {
/* 903 */           return this.r2;
/*     */         }
/*     */         public void setR2(float r2) {
/* 906 */           this.r2 = r2;
/*     */         }
/*     */         public float getAngle() {
/* 909 */           return this.angle;
/*     */         }
/*     */         public void setAngle(float angle) {
/* 912 */           this.angle = angle;
/*     */         }
/*     */         public boolean getLargeArcFlag() {
/* 915 */           return this.largeArcFlag;
/*     */         }
/*     */         public void setLargeArcFlag(boolean largeArcFlag) {
/* 918 */           this.largeArcFlag = largeArcFlag;
/*     */         }
/*     */         public boolean getSweepFlag() {
/* 921 */           return this.sweepFlag;
/*     */         }
/*     */         public void setSweepFlag(boolean sweepFlag) {
/* 924 */           this.sweepFlag = sweepFlag;
/*     */         }
/*     */       };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/* 935 */     return (Node)new SVGOMPathElement();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected DoublyIndexedTable getTraitInformationTable() {
/* 942 */     return xmlTraitInformation;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/SVGOMPathElement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */