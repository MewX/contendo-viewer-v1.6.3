/*     */ package org.apache.batik.anim.values;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Vector;
/*     */ import org.apache.batik.anim.dom.AnimationTarget;
/*     */ import org.apache.batik.dom.svg.AbstractSVGTransform;
/*     */ import org.apache.batik.dom.svg.SVGOMTransform;
/*     */ import org.w3c.dom.svg.SVGMatrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnimatableTransformListValue
/*     */   extends AnimatableValue
/*     */ {
/*  43 */   protected static SVGOMTransform IDENTITY_SKEWX = new SVGOMTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  48 */   protected static SVGOMTransform IDENTITY_SKEWY = new SVGOMTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  53 */   protected static SVGOMTransform IDENTITY_SCALE = new SVGOMTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected static SVGOMTransform IDENTITY_ROTATE = new SVGOMTransform();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  63 */   protected static SVGOMTransform IDENTITY_TRANSLATE = new SVGOMTransform(); protected Vector transforms;
/*     */   
/*     */   static {
/*  66 */     IDENTITY_SKEWX.setSkewX(0.0F);
/*  67 */     IDENTITY_SKEWY.setSkewY(0.0F);
/*  68 */     IDENTITY_SCALE.setScale(0.0F, 0.0F);
/*  69 */     IDENTITY_ROTATE.setRotate(0.0F, 0.0F, 0.0F);
/*  70 */     IDENTITY_TRANSLATE.setTranslate(0.0F, 0.0F);
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
/*     */   protected AnimatableTransformListValue(AnimationTarget target) {
/*  82 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableTransformListValue(AnimationTarget target, AbstractSVGTransform t) {
/*  90 */     super(target);
/*  91 */     this.transforms = new Vector();
/*  92 */     this.transforms.add(t);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableTransformListValue(AnimationTarget target, List<?> transforms) {
/* 100 */     super(target);
/*     */     
/* 102 */     this.transforms = new Vector(transforms);
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
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/* 114 */     AnimatableTransformListValue res, toTransformList = (AnimatableTransformListValue)to;
/*     */     
/* 116 */     AnimatableTransformListValue accTransformList = (AnimatableTransformListValue)accumulation;
/*     */ 
/*     */     
/* 119 */     int accSize = (accumulation == null) ? 0 : accTransformList.transforms.size();
/* 120 */     int newSize = this.transforms.size() + accSize * multiplier;
/*     */ 
/*     */     
/* 123 */     if (result == null) {
/* 124 */       res = new AnimatableTransformListValue(this.target);
/* 125 */       res.transforms = new Vector(newSize);
/* 126 */       res.transforms.setSize(newSize);
/*     */     } else {
/* 128 */       res = (AnimatableTransformListValue)result;
/* 129 */       if (res.transforms == null) {
/* 130 */         res.transforms = new Vector(newSize);
/* 131 */         res.transforms.setSize(newSize);
/* 132 */       } else if (res.transforms.size() != newSize) {
/* 133 */         res.transforms.setSize(newSize);
/*     */       } 
/*     */     } 
/*     */     
/* 137 */     int index = 0;
/* 138 */     for (int j = 0; j < multiplier; j++) {
/* 139 */       for (int k = 0; k < accSize; k++, index++) {
/* 140 */         res.transforms.setElementAt(accTransformList.transforms.elementAt(k), index);
/*     */       }
/*     */     } 
/*     */     
/* 144 */     for (int i = 0; i < this.transforms.size() - 1; i++, index++) {
/* 145 */       res.transforms.setElementAt(this.transforms.elementAt(i), index);
/*     */     }
/*     */     
/* 148 */     if (to != null) {
/* 149 */       int type; AbstractSVGTransform tt = toTransformList.transforms.lastElement();
/*     */       
/* 151 */       AbstractSVGTransform ft = null;
/*     */       
/* 153 */       if (this.transforms.isEmpty()) {
/*     */         SVGOMTransform sVGOMTransform;
/*     */         
/* 156 */         type = tt.getType();
/* 157 */         switch (type) {
/*     */           case 5:
/* 159 */             sVGOMTransform = IDENTITY_SKEWX;
/*     */             break;
/*     */           case 6:
/* 162 */             sVGOMTransform = IDENTITY_SKEWY;
/*     */             break;
/*     */           case 3:
/* 165 */             sVGOMTransform = IDENTITY_SCALE;
/*     */             break;
/*     */           case 4:
/* 168 */             sVGOMTransform = IDENTITY_ROTATE;
/*     */             break;
/*     */           case 2:
/* 171 */             sVGOMTransform = IDENTITY_TRANSLATE;
/*     */             break;
/*     */         } 
/*     */       } else {
/* 175 */         ft = this.transforms.lastElement();
/* 176 */         type = ft.getType();
/*     */       } 
/* 178 */       if (type == tt.getType()) {
/*     */         SVGOMTransform sVGOMTransform; float x, y; SVGMatrix fm, tm;
/* 180 */         if (res.transforms.isEmpty()) {
/* 181 */           sVGOMTransform = new SVGOMTransform();
/* 182 */           res.transforms.add(sVGOMTransform);
/*     */         } else {
/* 184 */           AbstractSVGTransform t = res.transforms.elementAt(index);
/* 185 */           if (t == null) {
/* 186 */             sVGOMTransform = new SVGOMTransform();
/* 187 */             res.transforms.setElementAt(sVGOMTransform, index);
/*     */           } 
/*     */         } 
/* 190 */         float r = 0.0F;
/* 191 */         switch (type) {
/*     */           case 5:
/*     */           case 6:
/* 194 */             r = ft.getAngle();
/* 195 */             r += interpolation * (tt.getAngle() - r);
/* 196 */             if (type == 5) {
/* 197 */               sVGOMTransform.setSkewX(r); break;
/* 198 */             }  if (type == 6) {
/* 199 */               sVGOMTransform.setSkewY(r);
/*     */             }
/*     */             break;
/*     */           case 3:
/* 203 */             fm = ft.getMatrix();
/* 204 */             tm = tt.getMatrix();
/* 205 */             x = fm.getA();
/* 206 */             y = fm.getD();
/* 207 */             x += interpolation * (tm.getA() - x);
/* 208 */             y += interpolation * (tm.getD() - y);
/* 209 */             sVGOMTransform.setScale(x, y);
/*     */             break;
/*     */           
/*     */           case 4:
/* 213 */             x = ft.getX();
/* 214 */             y = ft.getY();
/* 215 */             x += interpolation * (tt.getX() - x);
/* 216 */             y += interpolation * (tt.getY() - y);
/* 217 */             r = ft.getAngle();
/* 218 */             r += interpolation * (tt.getAngle() - r);
/* 219 */             sVGOMTransform.setRotate(r, x, y);
/*     */             break;
/*     */           
/*     */           case 2:
/* 223 */             fm = ft.getMatrix();
/* 224 */             tm = tt.getMatrix();
/* 225 */             x = fm.getE();
/* 226 */             y = fm.getF();
/* 227 */             x += interpolation * (tm.getE() - x);
/* 228 */             y += interpolation * (tm.getF() - y);
/* 229 */             sVGOMTransform.setTranslate(x, y);
/*     */             break;
/*     */         } 
/*     */       } 
/*     */     } else {
/*     */       SVGOMTransform sVGOMTransform;
/* 235 */       AbstractSVGTransform ft = this.transforms.lastElement();
/*     */       
/* 237 */       AbstractSVGTransform t = res.transforms.elementAt(index);
/*     */       
/* 239 */       if (t == null) {
/* 240 */         sVGOMTransform = new SVGOMTransform();
/* 241 */         res.transforms.setElementAt(sVGOMTransform, index);
/*     */       } 
/* 243 */       sVGOMTransform.assign(ft);
/*     */     } 
/*     */ 
/*     */     
/* 247 */     res.hasChanged = true;
/*     */     
/* 249 */     return res;
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
/*     */   public static AnimatableTransformListValue interpolate(AnimatableTransformListValue res, AnimatableTransformListValue value1, AnimatableTransformListValue value2, AnimatableTransformListValue to1, AnimatableTransformListValue to2, float interpolation1, float interpolation2, AnimatableTransformListValue accumulation, int multiplier) {
/*     */     SVGOMTransform sVGOMTransform;
/*     */     float x, y;
/* 268 */     int accSize = (accumulation == null) ? 0 : accumulation.transforms.size();
/* 269 */     int newSize = accSize * multiplier + 1;
/*     */     
/* 271 */     if (res == null) {
/* 272 */       res = new AnimatableTransformListValue(to1.target);
/* 273 */       res.transforms = new Vector(newSize);
/* 274 */       res.transforms.setSize(newSize);
/*     */     }
/* 276 */     else if (res.transforms == null) {
/* 277 */       res.transforms = new Vector(newSize);
/* 278 */       res.transforms.setSize(newSize);
/* 279 */     } else if (res.transforms.size() != newSize) {
/* 280 */       res.transforms.setSize(newSize);
/*     */     } 
/*     */ 
/*     */     
/* 284 */     int index = 0;
/* 285 */     for (int j = 0; j < multiplier; j++) {
/* 286 */       for (int i = 0; i < accSize; i++, index++) {
/* 287 */         res.transforms.setElementAt(accumulation.transforms.elementAt(i), index);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 292 */     AbstractSVGTransform ft1 = value1.transforms.lastElement();
/*     */     
/* 294 */     AbstractSVGTransform ft2 = value2.transforms.lastElement();
/*     */ 
/*     */     
/* 297 */     AbstractSVGTransform t = res.transforms.elementAt(index);
/*     */     
/* 299 */     if (t == null) {
/* 300 */       sVGOMTransform = new SVGOMTransform();
/* 301 */       res.transforms.setElementAt(sVGOMTransform, index);
/*     */     } 
/*     */     
/* 304 */     int type = ft1.getType();
/*     */ 
/*     */     
/* 307 */     if (type == 3) {
/* 308 */       x = ft1.getMatrix().getA();
/* 309 */       y = ft2.getMatrix().getD();
/*     */     } else {
/* 311 */       x = ft1.getMatrix().getE();
/* 312 */       y = ft2.getMatrix().getF();
/*     */     } 
/*     */     
/* 315 */     if (to1 != null) {
/* 316 */       AbstractSVGTransform tt1 = to1.transforms.lastElement();
/*     */       
/* 318 */       AbstractSVGTransform tt2 = to2.transforms.lastElement();
/*     */ 
/*     */       
/* 321 */       if (type == 3) {
/* 322 */         x += interpolation1 * (tt1.getMatrix().getA() - x);
/* 323 */         y += interpolation2 * (tt2.getMatrix().getD() - y);
/*     */       } else {
/* 325 */         x += interpolation1 * (tt1.getMatrix().getE() - x);
/* 326 */         y += interpolation2 * (tt2.getMatrix().getF() - y);
/*     */       } 
/*     */     } 
/*     */     
/* 330 */     if (type == 3) {
/* 331 */       sVGOMTransform.setScale(x, y);
/*     */     } else {
/* 333 */       sVGOMTransform.setTranslate(x, y);
/*     */     } 
/*     */ 
/*     */     
/* 337 */     res.hasChanged = true;
/*     */     
/* 339 */     return res;
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
/*     */   public static AnimatableTransformListValue interpolate(AnimatableTransformListValue res, AnimatableTransformListValue value1, AnimatableTransformListValue value2, AnimatableTransformListValue value3, AnimatableTransformListValue to1, AnimatableTransformListValue to2, AnimatableTransformListValue to3, float interpolation1, float interpolation2, float interpolation3, AnimatableTransformListValue accumulation, int multiplier) {
/*     */     SVGOMTransform sVGOMTransform;
/* 361 */     int accSize = (accumulation == null) ? 0 : accumulation.transforms.size();
/* 362 */     int newSize = accSize * multiplier + 1;
/*     */     
/* 364 */     if (res == null) {
/* 365 */       res = new AnimatableTransformListValue(to1.target);
/* 366 */       res.transforms = new Vector(newSize);
/* 367 */       res.transforms.setSize(newSize);
/*     */     }
/* 369 */     else if (res.transforms == null) {
/* 370 */       res.transforms = new Vector(newSize);
/* 371 */       res.transforms.setSize(newSize);
/* 372 */     } else if (res.transforms.size() != newSize) {
/* 373 */       res.transforms.setSize(newSize);
/*     */     } 
/*     */ 
/*     */     
/* 377 */     int index = 0;
/* 378 */     for (int j = 0; j < multiplier; j++) {
/* 379 */       for (int i = 0; i < accSize; i++, index++) {
/* 380 */         res.transforms.setElementAt(accumulation.transforms.elementAt(i), index);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 385 */     AbstractSVGTransform ft1 = value1.transforms.lastElement();
/*     */     
/* 387 */     AbstractSVGTransform ft2 = value2.transforms.lastElement();
/*     */     
/* 389 */     AbstractSVGTransform ft3 = value3.transforms.lastElement();
/*     */ 
/*     */     
/* 392 */     AbstractSVGTransform t = res.transforms.elementAt(index);
/*     */     
/* 394 */     if (t == null) {
/* 395 */       sVGOMTransform = new SVGOMTransform();
/* 396 */       res.transforms.setElementAt(sVGOMTransform, index);
/*     */     } 
/*     */ 
/*     */     
/* 400 */     float r = ft1.getAngle();
/* 401 */     float x = ft2.getX();
/* 402 */     float y = ft3.getY();
/*     */     
/* 404 */     if (to1 != null) {
/* 405 */       AbstractSVGTransform tt1 = to1.transforms.lastElement();
/*     */       
/* 407 */       AbstractSVGTransform tt2 = to2.transforms.lastElement();
/*     */       
/* 409 */       AbstractSVGTransform tt3 = to3.transforms.lastElement();
/*     */ 
/*     */       
/* 412 */       r += interpolation1 * (tt1.getAngle() - r);
/* 413 */       x += interpolation2 * (tt2.getX() - x);
/* 414 */       y += interpolation3 * (tt3.getY() - y);
/*     */     } 
/* 416 */     sVGOMTransform.setRotate(r, x, y);
/*     */ 
/*     */     
/* 419 */     res.hasChanged = true;
/*     */     
/* 421 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Iterator getTransforms() {
/* 428 */     return this.transforms.iterator();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 436 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 444 */     AnimatableTransformListValue o = (AnimatableTransformListValue)other;
/* 445 */     if (this.transforms.isEmpty() || o.transforms.isEmpty()) {
/* 446 */       return 0.0F;
/*     */     }
/* 448 */     AbstractSVGTransform t1 = this.transforms.lastElement();
/* 449 */     AbstractSVGTransform t2 = o.transforms.lastElement();
/* 450 */     short type1 = t1.getType();
/* 451 */     if (type1 != t2.getType()) {
/* 452 */       return 0.0F;
/*     */     }
/* 454 */     SVGMatrix m1 = t1.getMatrix();
/* 455 */     SVGMatrix m2 = t2.getMatrix();
/* 456 */     switch (type1) {
/*     */       case 2:
/* 458 */         return Math.abs(m1.getE() - m2.getE()) + Math.abs(m1.getF() - m2.getF());
/*     */       case 3:
/* 460 */         return Math.abs(m1.getA() - m2.getA()) + Math.abs(m1.getD() - m2.getD());
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/* 464 */         return Math.abs(t1.getAngle() - t2.getAngle());
/*     */     } 
/* 466 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo1(AnimatableValue other) {
/* 474 */     AnimatableTransformListValue o = (AnimatableTransformListValue)other;
/* 475 */     if (this.transforms.isEmpty() || o.transforms.isEmpty()) {
/* 476 */       return 0.0F;
/*     */     }
/* 478 */     AbstractSVGTransform t1 = this.transforms.lastElement();
/* 479 */     AbstractSVGTransform t2 = o.transforms.lastElement();
/* 480 */     short type1 = t1.getType();
/* 481 */     if (type1 != t2.getType()) {
/* 482 */       return 0.0F;
/*     */     }
/* 484 */     SVGMatrix m1 = t1.getMatrix();
/* 485 */     SVGMatrix m2 = t2.getMatrix();
/* 486 */     switch (type1) {
/*     */       case 2:
/* 488 */         return Math.abs(m1.getE() - m2.getE());
/*     */       case 3:
/* 490 */         return Math.abs(m1.getA() - m2.getA());
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/* 494 */         return Math.abs(t1.getAngle() - t2.getAngle());
/*     */     } 
/* 496 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo2(AnimatableValue other) {
/* 504 */     AnimatableTransformListValue o = (AnimatableTransformListValue)other;
/* 505 */     if (this.transforms.isEmpty() || o.transforms.isEmpty()) {
/* 506 */       return 0.0F;
/*     */     }
/* 508 */     AbstractSVGTransform t1 = this.transforms.lastElement();
/* 509 */     AbstractSVGTransform t2 = o.transforms.lastElement();
/* 510 */     short type1 = t1.getType();
/* 511 */     if (type1 != t2.getType()) {
/* 512 */       return 0.0F;
/*     */     }
/* 514 */     SVGMatrix m1 = t1.getMatrix();
/* 515 */     SVGMatrix m2 = t2.getMatrix();
/* 516 */     switch (type1) {
/*     */       case 2:
/* 518 */         return Math.abs(m1.getF() - m2.getF());
/*     */       case 3:
/* 520 */         return Math.abs(m1.getD() - m2.getD());
/*     */       case 4:
/* 522 */         return Math.abs(t1.getX() - t2.getX());
/*     */     } 
/* 524 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo3(AnimatableValue other) {
/* 532 */     AnimatableTransformListValue o = (AnimatableTransformListValue)other;
/* 533 */     if (this.transforms.isEmpty() || o.transforms.isEmpty()) {
/* 534 */       return 0.0F;
/*     */     }
/* 536 */     AbstractSVGTransform t1 = this.transforms.lastElement();
/* 537 */     AbstractSVGTransform t2 = o.transforms.lastElement();
/* 538 */     short type1 = t1.getType();
/* 539 */     if (type1 != t2.getType()) {
/* 540 */       return 0.0F;
/*     */     }
/* 542 */     if (type1 == 4) {
/* 543 */       return Math.abs(t1.getY() - t2.getY());
/*     */     }
/* 545 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 553 */     return new AnimatableTransformListValue(this.target, new Vector(5));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringRep() {
/* 560 */     StringBuffer sb = new StringBuffer();
/* 561 */     Iterator<AbstractSVGTransform> i = this.transforms.iterator();
/* 562 */     while (i.hasNext()) {
/* 563 */       AbstractSVGTransform t = i.next();
/* 564 */       if (t == null) {
/* 565 */         sb.append("null");
/*     */       } else {
/* 567 */         SVGMatrix m = t.getMatrix();
/* 568 */         switch (t.getType()) {
/*     */           case 2:
/* 570 */             sb.append("translate(");
/* 571 */             sb.append(m.getE());
/* 572 */             sb.append(',');
/* 573 */             sb.append(m.getF());
/* 574 */             sb.append(')');
/*     */             break;
/*     */           case 3:
/* 577 */             sb.append("scale(");
/* 578 */             sb.append(m.getA());
/* 579 */             sb.append(',');
/* 580 */             sb.append(m.getD());
/* 581 */             sb.append(')');
/*     */             break;
/*     */           case 5:
/* 584 */             sb.append("skewX(");
/* 585 */             sb.append(t.getAngle());
/* 586 */             sb.append(')');
/*     */             break;
/*     */           case 6:
/* 589 */             sb.append("skewY(");
/* 590 */             sb.append(t.getAngle());
/* 591 */             sb.append(')');
/*     */             break;
/*     */           case 4:
/* 594 */             sb.append("rotate(");
/* 595 */             sb.append(t.getAngle());
/* 596 */             sb.append(',');
/* 597 */             sb.append(t.getX());
/* 598 */             sb.append(',');
/* 599 */             sb.append(t.getY());
/* 600 */             sb.append(')');
/*     */             break;
/*     */         } 
/*     */       } 
/* 604 */       if (i.hasNext()) {
/* 605 */         sb.append(' ');
/*     */       }
/*     */     } 
/* 608 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatableTransformListValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */