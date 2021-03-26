/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.TransformListHandler;
/*     */ import org.apache.batik.parser.TransformListParser;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGMatrix;
/*     */ import org.w3c.dom.svg.SVGTransform;
/*     */ import org.w3c.dom.svg.SVGTransformList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGTransformList
/*     */   extends AbstractSVGList
/*     */   implements SVGTransformList
/*     */ {
/*     */   public static final String SVG_TRANSFORMATION_LIST_SEPARATOR = "";
/*     */   
/*     */   protected String getItemSeparator() {
/*  54 */     return "";
/*     */   }
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
/*     */   public SVGTransform initialize(SVGTransform newItem) throws DOMException, SVGException {
/*  70 */     return (SVGTransform)initializeImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform getItem(int index) throws DOMException {
/*  77 */     return (SVGTransform)getItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform insertItemBefore(SVGTransform newItem, int index) throws DOMException, SVGException {
/*  86 */     return (SVGTransform)insertItemBeforeImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform replaceItem(SVGTransform newItem, int index) throws DOMException, SVGException {
/*  95 */     return (SVGTransform)replaceItemImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform removeItem(int index) throws DOMException {
/* 102 */     return (SVGTransform)removeItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform appendItem(SVGTransform newItem) throws DOMException, SVGException {
/* 110 */     return (SVGTransform)appendItemImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform createSVGTransformFromMatrix(SVGMatrix matrix) {
/* 118 */     SVGOMTransform transform = new SVGOMTransform();
/* 119 */     transform.setMatrix(matrix);
/* 120 */     return transform;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGTransform consolidate() {
/* 127 */     revalidate();
/*     */     
/* 129 */     int size = this.itemList.size();
/* 130 */     if (size == 0)
/* 131 */       return null; 
/* 132 */     if (size == 1) {
/* 133 */       return getItem(0);
/*     */     }
/*     */     
/* 136 */     SVGTransformItem t = (SVGTransformItem)getItemImpl(0);
/* 137 */     AffineTransform at = (AffineTransform)t.affineTransform.clone();
/*     */     
/* 139 */     for (int i = 1; i < size; i++) {
/* 140 */       t = (SVGTransformItem)getItemImpl(i);
/* 141 */       at.concatenate(t.affineTransform);
/*     */     } 
/* 143 */     SVGOMMatrix matrix = new SVGOMMatrix(at);
/* 144 */     return initialize(createSVGTransformFromMatrix(matrix));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AffineTransform getAffineTransform() {
/* 152 */     AffineTransform at = new AffineTransform();
/* 153 */     for (int i = 0; i < getNumberOfItems(); i++) {
/* 154 */       SVGTransformItem item = (SVGTransformItem)getItem(i);
/* 155 */       at.concatenate(item.affineTransform);
/*     */     } 
/* 157 */     return at;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGItem createSVGItem(Object newItem) {
/* 164 */     return new SVGTransformItem((SVGTransform)newItem);
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
/* 176 */     TransformListParser transformListParser = new TransformListParser();
/* 177 */     TransformListBuilder builder = new TransformListBuilder(handler);
/* 178 */     transformListParser.setTransformListHandler(builder);
/* 179 */     transformListParser.parse(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkItemType(Object newItem) {
/* 186 */     if (!(newItem instanceof SVGTransform)) {
/* 187 */       createSVGException((short)0, "expected.transform", (Object[])null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public class SVGTransformItem
/*     */     extends AbstractSVGTransform
/*     */     implements SVGItem
/*     */   {
/*     */     protected boolean xOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected boolean angleOnly;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected AbstractSVGList parent;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected String itemStringValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransformItem() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public SVGTransformItem(SVGTransform transform) {
/* 232 */       assign(transform);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void resetAttribute() {
/* 240 */       if (this.parent != null) {
/* 241 */         this.itemStringValue = null;
/* 242 */         this.parent.itemChanged();
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setParent(AbstractSVGList list) {
/* 251 */       this.parent = list;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AbstractSVGList getParent() {
/* 258 */       return this.parent;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String getValueAsString() {
/* 266 */       if (this.itemStringValue == null) {
/* 267 */         this.itemStringValue = getStringValue();
/*     */       }
/* 269 */       return this.itemStringValue;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void assign(SVGTransform transform) {
/* 277 */       this.type = transform.getType();
/* 278 */       SVGMatrix matrix = transform.getMatrix();
/* 279 */       switch (this.type) {
/*     */         case 2:
/* 281 */           setTranslate(matrix.getE(), matrix.getF());
/*     */           break;
/*     */         case 3:
/* 284 */           setScale(matrix.getA(), matrix.getD());
/*     */           break;
/*     */         case 4:
/* 287 */           if (matrix.getE() == 0.0F) {
/* 288 */             rotate(transform.getAngle()); break;
/*     */           } 
/* 290 */           this.angleOnly = false;
/* 291 */           if (matrix.getA() == 1.0F) {
/* 292 */             setRotate(transform.getAngle(), matrix.getE(), matrix.getF()); break;
/*     */           } 
/* 294 */           if (transform instanceof AbstractSVGTransform) {
/* 295 */             AbstractSVGTransform internal = (AbstractSVGTransform)transform;
/*     */             
/* 297 */             setRotate(internal.getAngle(), internal.getX(), internal.getY());
/*     */           } 
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 5:
/* 306 */           setSkewX(transform.getAngle());
/*     */           break;
/*     */         case 6:
/* 309 */           setSkewY(transform.getAngle());
/*     */           break;
/*     */         case 1:
/* 312 */           setMatrix(matrix);
/*     */           break;
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void translate(float x) {
/* 321 */       this.xOnly = true;
/* 322 */       setTranslate(x, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void rotate(float angle) {
/* 329 */       this.angleOnly = true;
/* 330 */       setRotate(angle, 0.0F, 0.0F);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void scale(float x) {
/* 337 */       this.xOnly = true;
/* 338 */       setScale(x, x);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void matrix(float a, float b, float c, float d, float e, float f) {
/* 346 */       setMatrix(new SVGOMMatrix(new AffineTransform(a, b, c, d, e, f)));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setMatrix(SVGMatrix matrix) {
/* 353 */       super.setMatrix(matrix);
/* 354 */       resetAttribute();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setTranslate(float tx, float ty) {
/* 361 */       super.setTranslate(tx, ty);
/* 362 */       resetAttribute();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setScale(float sx, float sy) {
/* 369 */       super.setScale(sx, sy);
/* 370 */       resetAttribute();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setRotate(float angle, float cx, float cy) {
/* 378 */       super.setRotate(angle, cx, cy);
/* 379 */       resetAttribute();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setSkewX(float angle) {
/* 386 */       super.setSkewX(angle);
/* 387 */       resetAttribute();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void setSkewY(float angle) {
/* 394 */       super.setSkewY(angle);
/* 395 */       resetAttribute();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGMatrix createMatrix() {
/* 402 */       return new AbstractSVGMatrix() {
/*     */           protected AffineTransform getAffineTransform() {
/* 404 */             return AbstractSVGTransformList.SVGTransformItem.this.affineTransform;
/*     */           }
/*     */           public void setA(float a) throws DOMException {
/* 407 */             AbstractSVGTransformList.SVGTransformItem.this.type = 1;
/* 408 */             super.setA(a);
/* 409 */             AbstractSVGTransformList.SVGTransformItem.this.resetAttribute();
/*     */           }
/*     */           public void setB(float b) throws DOMException {
/* 412 */             AbstractSVGTransformList.SVGTransformItem.this.type = 1;
/* 413 */             super.setB(b);
/* 414 */             AbstractSVGTransformList.SVGTransformItem.this.resetAttribute();
/*     */           }
/*     */           public void setC(float c) throws DOMException {
/* 417 */             AbstractSVGTransformList.SVGTransformItem.this.type = 1;
/* 418 */             super.setC(c);
/* 419 */             AbstractSVGTransformList.SVGTransformItem.this.resetAttribute();
/*     */           }
/*     */           public void setD(float d) throws DOMException {
/* 422 */             AbstractSVGTransformList.SVGTransformItem.this.type = 1;
/* 423 */             super.setD(d);
/* 424 */             AbstractSVGTransformList.SVGTransformItem.this.resetAttribute();
/*     */           }
/*     */           public void setE(float e) throws DOMException {
/* 427 */             AbstractSVGTransformList.SVGTransformItem.this.type = 1;
/* 428 */             super.setE(e);
/* 429 */             AbstractSVGTransformList.SVGTransformItem.this.resetAttribute();
/*     */           }
/*     */           public void setF(float f) throws DOMException {
/* 432 */             AbstractSVGTransformList.SVGTransformItem.this.type = 1;
/* 433 */             super.setF(f);
/* 434 */             AbstractSVGTransformList.SVGTransformItem.this.resetAttribute();
/*     */           }
/*     */         };
/*     */     }
/*     */ 
/*     */     
/*     */     protected String getStringValue() {
/*     */       double[] matrix;
/*     */       int i;
/* 443 */       StringBuffer buf = new StringBuffer();
/* 444 */       switch (this.type) {
/*     */         case 2:
/* 446 */           buf.append("translate(");
/* 447 */           buf.append((float)this.affineTransform.getTranslateX());
/* 448 */           if (!this.xOnly) {
/* 449 */             buf.append(' ');
/* 450 */             buf.append((float)this.affineTransform.getTranslateY());
/*     */           } 
/* 452 */           buf.append(')');
/*     */           break;
/*     */         case 4:
/* 455 */           buf.append("rotate(");
/* 456 */           buf.append(this.angle);
/* 457 */           if (!this.angleOnly) {
/* 458 */             buf.append(' ');
/* 459 */             buf.append(this.x);
/* 460 */             buf.append(' ');
/* 461 */             buf.append(this.y);
/*     */           } 
/* 463 */           buf.append(')');
/*     */           break;
/*     */         case 3:
/* 466 */           buf.append("scale(");
/* 467 */           buf.append((float)this.affineTransform.getScaleX());
/* 468 */           if (!this.xOnly) {
/* 469 */             buf.append(' ');
/* 470 */             buf.append((float)this.affineTransform.getScaleY());
/*     */           } 
/* 472 */           buf.append(')');
/*     */           break;
/*     */         case 5:
/* 475 */           buf.append("skewX(");
/* 476 */           buf.append(this.angle);
/* 477 */           buf.append(')');
/*     */           break;
/*     */         case 6:
/* 480 */           buf.append("skewY(");
/* 481 */           buf.append(this.angle);
/* 482 */           buf.append(')');
/*     */           break;
/*     */         case 1:
/* 485 */           buf.append("matrix(");
/* 486 */           matrix = new double[6];
/* 487 */           this.affineTransform.getMatrix(matrix);
/* 488 */           for (i = 0; i < 6; i++) {
/* 489 */             if (i != 0) {
/* 490 */               buf.append(' ');
/*     */             }
/* 492 */             buf.append((float)matrix[i]);
/*     */           } 
/* 494 */           buf.append(')');
/*     */           break;
/*     */       } 
/* 497 */       return buf.toString();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class TransformListBuilder
/*     */     implements TransformListHandler
/*     */   {
/*     */     protected ListHandler listHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public TransformListBuilder(ListHandler listHandler) {
/* 517 */       this.listHandler = listHandler;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startTransformList() throws ParseException {
/* 524 */       this.listHandler.startList();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void matrix(float a, float b, float c, float d, float e, float f) throws ParseException {
/* 533 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 534 */       item.matrix(a, b, c, d, e, f);
/* 535 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void rotate(float theta) throws ParseException {
/* 542 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 543 */       item.rotate(theta);
/* 544 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void rotate(float theta, float cx, float cy) throws ParseException {
/* 552 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 553 */       item.setRotate(theta, cx, cy);
/* 554 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void translate(float tx) throws ParseException {
/* 561 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 562 */       item.translate(tx);
/* 563 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void translate(float tx, float ty) throws ParseException {
/* 570 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 571 */       item.setTranslate(tx, ty);
/* 572 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void scale(float sx) throws ParseException {
/* 579 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 580 */       item.scale(sx);
/* 581 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void scale(float sx, float sy) throws ParseException {
/* 588 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 589 */       item.setScale(sx, sy);
/* 590 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void skewX(float skx) throws ParseException {
/* 597 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 598 */       item.setSkewX(skx);
/* 599 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void skewY(float sky) throws ParseException {
/* 606 */       AbstractSVGTransformList.SVGTransformItem item = new AbstractSVGTransformList.SVGTransformItem();
/* 607 */       item.setSkewY(sky);
/* 608 */       this.listHandler.item(item);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endTransformList() throws ParseException {
/* 615 */       this.listHandler.endList();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGTransformList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */