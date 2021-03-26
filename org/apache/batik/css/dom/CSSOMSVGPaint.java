/*     */ package org.apache.batik.css.dom;
/*     */ 
/*     */ import org.apache.batik.css.engine.value.FloatValue;
/*     */ import org.apache.batik.css.engine.value.Value;
/*     */ import org.apache.batik.css.engine.value.svg.ICCColor;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGPaint;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CSSOMSVGPaint
/*     */   extends CSSOMSVGColor
/*     */   implements SVGPaint
/*     */ {
/*     */   public CSSOMSVGPaint(CSSOMSVGColor.ValueProvider vp) {
/*  43 */     super(vp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setModificationHandler(CSSOMSVGColor.ModificationHandler h) {
/*  50 */     if (!(h instanceof PaintModificationHandler)) {
/*  51 */       throw new IllegalArgumentException();
/*     */     }
/*  53 */     super.setModificationHandler(h);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getColorType() {
/*  61 */     throw new DOMException((short)15, "");
/*     */   }
/*     */ 
/*     */   
/*     */   public short getPaintType() {
/*     */     String str;
/*     */     Value v0, v1;
/*     */     String str1;
/*  69 */     Value value = this.valueProvider.getValue();
/*  70 */     switch (value.getCssValueType()) {
/*     */       case 1:
/*  72 */         switch (value.getPrimitiveType()) {
/*     */           case 21:
/*  74 */             str = value.getStringValue();
/*  75 */             if (str.equalsIgnoreCase("none"))
/*  76 */               return 101; 
/*  77 */             if (str.equalsIgnoreCase("currentcolor"))
/*     */             {
/*  79 */               return 102;
/*     */             }
/*  81 */             return 1;
/*     */           
/*     */           case 25:
/*  84 */             return 1;
/*     */           
/*     */           case 20:
/*  87 */             return 107;
/*     */         } 
/*     */         
/*     */         break;
/*     */       case 2:
/*  92 */         v0 = value.item(0);
/*  93 */         v1 = value.item(1);
/*  94 */         switch (v0.getPrimitiveType()) {
/*     */           case 21:
/*  96 */             return 2;
/*     */           case 20:
/*  98 */             if (v1.getCssValueType() == 2)
/*     */             {
/* 100 */               return 106;
/*     */             }
/* 102 */             switch (v1.getPrimitiveType()) {
/*     */               case 21:
/* 104 */                 str1 = v1.getStringValue();
/* 105 */                 if (str1.equalsIgnoreCase("none"))
/* 106 */                   return 103; 
/* 107 */                 if (str1.equalsIgnoreCase("currentcolor"))
/*     */                 {
/* 109 */                   return 104;
/*     */                 }
/* 111 */                 return 105;
/*     */               
/*     */               case 25:
/* 114 */                 return 105;
/*     */             } 
/*     */           
/*     */           case 25:
/* 118 */             return 2;
/*     */         }  break;
/*     */     } 
/* 121 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getUri() {
/* 128 */     switch (getPaintType()) {
/*     */       case 107:
/* 130 */         return this.valueProvider.getValue().getStringValue();
/*     */       
/*     */       case 103:
/*     */       case 104:
/*     */       case 105:
/*     */       case 106:
/* 136 */         return this.valueProvider.getValue().item(0).getStringValue();
/*     */     } 
/* 138 */     throw new InternalError();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setUri(String uri) {
/* 145 */     if (this.handler == null) {
/* 146 */       throw new DOMException((short)7, "");
/*     */     }
/*     */     
/* 149 */     ((PaintModificationHandler)this.handler).uriChanged(uri);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPaint(short paintType, String uri, String rgbColor, String iccColor) {
/* 159 */     if (this.handler == null) {
/* 160 */       throw new DOMException((short)7, "");
/*     */     }
/*     */     
/* 163 */     ((PaintModificationHandler)this.handler).paintChanged(paintType, uri, rgbColor, iccColor);
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
/*     */   public abstract class AbstractModificationHandler
/*     */     implements PaintModificationHandler
/*     */   {
/*     */     protected abstract Value getValue();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void redTextChanged(String text) throws DOMException {
/* 199 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 1:
/* 201 */           text = "rgb(" + text + ", " + getValue().getGreen().getCssText() + ", " + getValue().getBlue().getCssText() + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 208 */           text = "rgb(" + text + ", " + getValue().item(0).getGreen().getCssText() + ", " + getValue().item(0).getBlue().getCssText() + ") " + getValue().item(1).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 105:
/* 216 */           text = getValue().item(0) + " rgb(" + text + ", " + getValue().item(1).getGreen().getCssText() + ", " + getValue().item(1).getBlue().getCssText() + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 106:
/* 224 */           text = getValue().item(0) + " rgb(" + text + ", " + getValue().item(1).getGreen().getCssText() + ", " + getValue().item(1).getBlue().getCssText() + ") " + getValue().item(2).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 233 */           throw new DOMException((short)7, "");
/*     */       } 
/*     */       
/* 236 */       textChanged(text);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void redFloatValueChanged(short unit, float value) throws DOMException {
/*     */       String text;
/* 245 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 1:
/* 247 */           text = "rgb(" + FloatValue.getCssText(unit, value) + ", " + getValue().getGreen().getCssText() + ", " + getValue().getBlue().getCssText() + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 254 */           text = "rgb(" + FloatValue.getCssText(unit, value) + ", " + getValue().item(0).getGreen().getCssText() + ", " + getValue().item(0).getBlue().getCssText() + ") " + getValue().item(1).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 105:
/* 262 */           text = getValue().item(0) + " rgb(" + FloatValue.getCssText(unit, value) + ", " + getValue().item(1).getGreen().getCssText() + ", " + getValue().item(1).getBlue().getCssText() + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 106:
/* 270 */           text = getValue().item(0) + " rgb(" + FloatValue.getCssText(unit, value) + ", " + getValue().item(1).getGreen().getCssText() + ", " + getValue().item(1).getBlue().getCssText() + ") " + getValue().item(2).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 279 */           throw new DOMException((short)7, "");
/*     */       } 
/*     */       
/* 282 */       textChanged(text);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void greenTextChanged(String text) throws DOMException {
/* 289 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 1:
/* 291 */           text = "rgb(" + getValue().getRed().getCssText() + ", " + text + ", " + getValue().getBlue().getCssText() + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 298 */           text = "rgb(" + getValue().item(0).getRed().getCssText() + ", " + text + ", " + getValue().item(0).getBlue().getCssText() + ") " + getValue().item(1).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 105:
/* 306 */           text = getValue().item(0) + " rgb(" + getValue().item(1).getRed().getCssText() + ", " + text + ", " + getValue().item(1).getBlue().getCssText() + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 106:
/* 314 */           text = getValue().item(0) + " rgb(" + getValue().item(1).getRed().getCssText() + ", " + text + ", " + getValue().item(1).getBlue().getCssText() + ") " + getValue().item(2).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 323 */           throw new DOMException((short)7, "");
/*     */       } 
/*     */       
/* 326 */       textChanged(text);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void greenFloatValueChanged(short unit, float value) throws DOMException {
/*     */       String text;
/* 335 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 1:
/* 337 */           text = "rgb(" + getValue().getRed().getCssText() + ", " + FloatValue.getCssText(unit, value) + ", " + getValue().getBlue().getCssText() + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 344 */           text = "rgb(" + getValue().item(0).getRed().getCssText() + ", " + FloatValue.getCssText(unit, value) + ", " + getValue().item(0).getBlue().getCssText() + ") " + getValue().item(1).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 105:
/* 352 */           text = getValue().item(0) + " rgb(" + getValue().item(1).getRed().getCssText() + ", " + FloatValue.getCssText(unit, value) + ", " + getValue().item(1).getBlue().getCssText() + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 106:
/* 360 */           text = getValue().item(0) + " rgb(" + getValue().item(1).getRed().getCssText() + ", " + FloatValue.getCssText(unit, value) + ", " + getValue().item(1).getBlue().getCssText() + ") " + getValue().item(2).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 369 */           throw new DOMException((short)7, "");
/*     */       } 
/*     */       
/* 372 */       textChanged(text);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void blueTextChanged(String text) throws DOMException {
/* 379 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 1:
/* 381 */           text = "rgb(" + getValue().getRed().getCssText() + ", " + getValue().getGreen().getCssText() + ", " + text + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 388 */           text = "rgb(" + getValue().item(0).getRed().getCssText() + ", " + getValue().item(0).getGreen().getCssText() + ", " + text + ") " + getValue().item(1).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 105:
/* 396 */           text = getValue().item(0) + " rgb(" + getValue().item(1).getRed().getCssText() + ", " + getValue().item(1).getGreen().getCssText() + ", " + text + ")";
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 106:
/* 404 */           text = getValue().item(0) + " rgb(" + getValue().item(1).getRed().getCssText() + ", " + getValue().item(1).getGreen().getCssText() + ", " + text + ") " + getValue().item(2).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 413 */           throw new DOMException((short)7, "");
/*     */       } 
/*     */       
/* 416 */       textChanged(text);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void blueFloatValueChanged(short unit, float value) throws DOMException {
/*     */       String text;
/* 425 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 1:
/* 427 */           text = "rgb(" + getValue().getRed().getCssText() + ", " + getValue().getGreen().getCssText() + ", " + FloatValue.getCssText(unit, value) + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 434 */           text = "rgb(" + getValue().item(0).getRed().getCssText() + ", " + getValue().item(0).getGreen().getCssText() + ", " + FloatValue.getCssText(unit, value) + ") " + getValue().item(1).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 105:
/* 442 */           text = getValue().item(0) + " rgb(" + getValue().item(1).getRed().getCssText() + ", " + getValue().item(1).getGreen().getCssText() + ", " + FloatValue.getCssText(unit, value) + ')';
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 106:
/* 450 */           text = getValue().item(0) + " rgb(" + getValue().item(1).getRed().getCssText() + ", " + getValue().item(1).getGreen().getCssText() + ", " + FloatValue.getCssText(unit, value) + ") " + getValue().item(2).getCssText();
/*     */           break;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         default:
/* 459 */           throw new DOMException((short)7, "");
/*     */       } 
/*     */       
/* 462 */       textChanged(text);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void rgbColorChanged(String text) throws DOMException {
/* 469 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 1:
/*     */           break;
/*     */         
/*     */         case 2:
/* 474 */           text = text + getValue().item(1).getCssText();
/*     */           break;
/*     */         
/*     */         case 105:
/* 478 */           text = getValue().item(0).getCssText() + ' ' + text;
/*     */           break;
/*     */         
/*     */         case 106:
/* 482 */           text = getValue().item(0).getCssText() + ' ' + text + ' ' + getValue().item(2).getCssText();
/*     */           break;
/*     */ 
/*     */         
/*     */         default:
/* 487 */           throw new DOMException((short)7, "");
/*     */       } 
/*     */       
/* 490 */       textChanged(text);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void rgbColorICCColorChanged(String rgb, String icc) throws DOMException {
/* 498 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 2:
/* 500 */           textChanged(rgb + ' ' + icc);
/*     */           return;
/*     */         
/*     */         case 106:
/* 504 */           textChanged(getValue().item(0).getCssText() + ' ' + rgb + ' ' + icc);
/*     */           return;
/*     */       } 
/*     */ 
/*     */       
/* 509 */       throw new DOMException((short)7, "");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void colorChanged(short type, String rgb, String icc) throws DOMException {
/* 519 */       switch (type) {
/*     */         case 102:
/* 521 */           textChanged("currentcolor");
/*     */           return;
/*     */         
/*     */         case 1:
/* 525 */           textChanged(rgb);
/*     */           return;
/*     */         
/*     */         case 2:
/* 529 */           textChanged(rgb + ' ' + icc);
/*     */           return;
/*     */       } 
/*     */       
/* 533 */       throw new DOMException((short)9, "");
/*     */     }
/*     */ 
/*     */     
/*     */     public void colorProfileChanged(String cp) throws DOMException {
/*     */       StringBuffer sb;
/*     */       ICCColor iccc;
/*     */       int i;
/* 541 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 2:
/* 543 */           sb = new StringBuffer(getValue().item(0).getCssText());
/*     */           
/* 545 */           sb.append(" icc-color(");
/* 546 */           sb.append(cp);
/* 547 */           iccc = (ICCColor)getValue().item(1);
/* 548 */           for (i = 0; i < iccc.getLength(); i++) {
/* 549 */             sb.append(',');
/* 550 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 552 */           sb.append(')');
/* 553 */           textChanged(sb.toString());
/*     */           return;
/*     */         
/*     */         case 106:
/* 557 */           sb = new StringBuffer(getValue().item(0).getCssText());
/* 558 */           sb.append(' ');
/* 559 */           sb.append(getValue().item(1).getCssText());
/* 560 */           sb.append(" icc-color(");
/* 561 */           sb.append(cp);
/* 562 */           iccc = (ICCColor)getValue().item(1);
/* 563 */           for (i = 0; i < iccc.getLength(); i++) {
/* 564 */             sb.append(',');
/* 565 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 567 */           sb.append(')');
/* 568 */           textChanged(sb.toString());
/*     */           return;
/*     */       } 
/*     */       
/* 572 */       throw new DOMException((short)7, "");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void colorsCleared() throws DOMException {
/*     */       StringBuffer sb;
/*     */       ICCColor iccc;
/* 581 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 2:
/* 583 */           sb = new StringBuffer(getValue().item(0).getCssText());
/*     */           
/* 585 */           sb.append(" icc-color(");
/* 586 */           iccc = (ICCColor)getValue().item(1);
/* 587 */           sb.append(iccc.getColorProfile());
/* 588 */           sb.append(')');
/* 589 */           textChanged(sb.toString());
/*     */           return;
/*     */         
/*     */         case 106:
/* 593 */           sb = new StringBuffer(getValue().item(0).getCssText());
/* 594 */           sb.append(' ');
/* 595 */           sb.append(getValue().item(1).getCssText());
/* 596 */           sb.append(" icc-color(");
/* 597 */           iccc = (ICCColor)getValue().item(1);
/* 598 */           sb.append(iccc.getColorProfile());
/* 599 */           sb.append(')');
/* 600 */           textChanged(sb.toString());
/*     */           return;
/*     */       } 
/*     */       
/* 604 */       throw new DOMException((short)7, "");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void colorsInitialized(float f) throws DOMException {
/*     */       StringBuffer sb;
/*     */       ICCColor iccc;
/* 613 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 2:
/* 615 */           sb = new StringBuffer(getValue().item(0).getCssText());
/*     */           
/* 617 */           sb.append(" icc-color(");
/* 618 */           iccc = (ICCColor)getValue().item(1);
/* 619 */           sb.append(iccc.getColorProfile());
/* 620 */           sb.append(',');
/* 621 */           sb.append(f);
/* 622 */           sb.append(')');
/* 623 */           textChanged(sb.toString());
/*     */           return;
/*     */         
/*     */         case 106:
/* 627 */           sb = new StringBuffer(getValue().item(0).getCssText());
/* 628 */           sb.append(' ');
/* 629 */           sb.append(getValue().item(1).getCssText());
/* 630 */           sb.append(" icc-color(");
/* 631 */           iccc = (ICCColor)getValue().item(1);
/* 632 */           sb.append(iccc.getColorProfile());
/* 633 */           sb.append(',');
/* 634 */           sb.append(f);
/* 635 */           sb.append(')');
/* 636 */           textChanged(sb.toString());
/*     */           return;
/*     */       } 
/*     */       
/* 640 */       throw new DOMException((short)7, "");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void colorInsertedBefore(float f, int idx) throws DOMException {
/*     */       StringBuffer sb;
/*     */       ICCColor iccc;
/*     */       int i;
/* 649 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 2:
/* 651 */           sb = new StringBuffer(getValue().item(0).getCssText());
/*     */           
/* 653 */           sb.append(" icc-color(");
/* 654 */           iccc = (ICCColor)getValue().item(1);
/* 655 */           sb.append(iccc.getColorProfile());
/* 656 */           for (i = 0; i < idx; i++) {
/* 657 */             sb.append(',');
/* 658 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 660 */           sb.append(',');
/* 661 */           sb.append(f);
/* 662 */           for (i = idx; i < iccc.getLength(); i++) {
/* 663 */             sb.append(',');
/* 664 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 666 */           sb.append(')');
/* 667 */           textChanged(sb.toString());
/*     */           return;
/*     */         
/*     */         case 106:
/* 671 */           sb = new StringBuffer(getValue().item(0).getCssText());
/* 672 */           sb.append(' ');
/* 673 */           sb.append(getValue().item(1).getCssText());
/* 674 */           sb.append(" icc-color(");
/* 675 */           iccc = (ICCColor)getValue().item(1);
/* 676 */           sb.append(iccc.getColorProfile());
/* 677 */           for (i = 0; i < idx; i++) {
/* 678 */             sb.append(',');
/* 679 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 681 */           sb.append(',');
/* 682 */           sb.append(f);
/* 683 */           for (i = idx; i < iccc.getLength(); i++) {
/* 684 */             sb.append(',');
/* 685 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 687 */           sb.append(')');
/* 688 */           textChanged(sb.toString());
/*     */           return;
/*     */       } 
/*     */       
/* 692 */       throw new DOMException((short)7, "");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void colorReplaced(float f, int idx) throws DOMException {
/*     */       StringBuffer sb;
/*     */       ICCColor iccc;
/*     */       int i;
/* 701 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 2:
/* 703 */           sb = new StringBuffer(getValue().item(0).getCssText());
/*     */           
/* 705 */           sb.append(" icc-color(");
/* 706 */           iccc = (ICCColor)getValue().item(1);
/* 707 */           sb.append(iccc.getColorProfile());
/* 708 */           for (i = 0; i < idx; i++) {
/* 709 */             sb.append(',');
/* 710 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 712 */           sb.append(',');
/* 713 */           sb.append(f);
/* 714 */           for (i = idx + 1; i < iccc.getLength(); i++) {
/* 715 */             sb.append(',');
/* 716 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 718 */           sb.append(')');
/* 719 */           textChanged(sb.toString());
/*     */           return;
/*     */         
/*     */         case 106:
/* 723 */           sb = new StringBuffer(getValue().item(0).getCssText());
/* 724 */           sb.append(' ');
/* 725 */           sb.append(getValue().item(1).getCssText());
/* 726 */           sb.append(" icc-color(");
/* 727 */           iccc = (ICCColor)getValue().item(1);
/* 728 */           sb.append(iccc.getColorProfile());
/* 729 */           for (i = 0; i < idx; i++) {
/* 730 */             sb.append(',');
/* 731 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 733 */           sb.append(',');
/* 734 */           sb.append(f);
/* 735 */           for (i = idx + 1; i < iccc.getLength(); i++) {
/* 736 */             sb.append(',');
/* 737 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 739 */           sb.append(')');
/* 740 */           textChanged(sb.toString());
/*     */           return;
/*     */       } 
/*     */       
/* 744 */       throw new DOMException((short)7, "");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void colorRemoved(int idx) throws DOMException {
/*     */       StringBuffer sb;
/*     */       ICCColor iccc;
/*     */       int i;
/* 753 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 2:
/* 755 */           sb = new StringBuffer(getValue().item(0).getCssText());
/*     */           
/* 757 */           sb.append(" icc-color(");
/* 758 */           iccc = (ICCColor)getValue().item(1);
/* 759 */           sb.append(iccc.getColorProfile());
/* 760 */           for (i = 0; i < idx; i++) {
/* 761 */             sb.append(',');
/* 762 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 764 */           for (i = idx + 1; i < iccc.getLength(); i++) {
/* 765 */             sb.append(',');
/* 766 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 768 */           sb.append(')');
/* 769 */           textChanged(sb.toString());
/*     */           return;
/*     */         
/*     */         case 106:
/* 773 */           sb = new StringBuffer(getValue().item(0).getCssText());
/* 774 */           sb.append(' ');
/* 775 */           sb.append(getValue().item(1).getCssText());
/* 776 */           sb.append(" icc-color(");
/* 777 */           iccc = (ICCColor)getValue().item(1);
/* 778 */           sb.append(iccc.getColorProfile());
/* 779 */           for (i = 0; i < idx; i++) {
/* 780 */             sb.append(',');
/* 781 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 783 */           for (i = idx + 1; i < iccc.getLength(); i++) {
/* 784 */             sb.append(',');
/* 785 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 787 */           sb.append(')');
/* 788 */           textChanged(sb.toString());
/*     */           return;
/*     */       } 
/*     */       
/* 792 */       throw new DOMException((short)7, "");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void colorAppend(float f) throws DOMException {
/*     */       StringBuffer sb;
/*     */       ICCColor iccc;
/*     */       int i;
/* 801 */       switch (CSSOMSVGPaint.this.getPaintType()) {
/*     */         case 2:
/* 803 */           sb = new StringBuffer(getValue().item(0).getCssText());
/*     */           
/* 805 */           sb.append(" icc-color(");
/* 806 */           iccc = (ICCColor)getValue().item(1);
/* 807 */           sb.append(iccc.getColorProfile());
/* 808 */           for (i = 0; i < iccc.getLength(); i++) {
/* 809 */             sb.append(',');
/* 810 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 812 */           sb.append(',');
/* 813 */           sb.append(f);
/* 814 */           sb.append(')');
/* 815 */           textChanged(sb.toString());
/*     */           return;
/*     */         
/*     */         case 106:
/* 819 */           sb = new StringBuffer(getValue().item(0).getCssText());
/* 820 */           sb.append(' ');
/* 821 */           sb.append(getValue().item(1).getCssText());
/* 822 */           sb.append(" icc-color(");
/* 823 */           iccc = (ICCColor)getValue().item(1);
/* 824 */           sb.append(iccc.getColorProfile());
/* 825 */           for (i = 0; i < iccc.getLength(); i++) {
/* 826 */             sb.append(',');
/* 827 */             sb.append(iccc.getColor(i));
/*     */           } 
/* 829 */           sb.append(',');
/* 830 */           sb.append(f);
/* 831 */           sb.append(')');
/* 832 */           textChanged(sb.toString());
/*     */           return;
/*     */       } 
/*     */       
/* 836 */       throw new DOMException((short)7, "");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void uriChanged(String uri) {
/* 845 */       textChanged("url(" + uri + ") none");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void paintChanged(short type, String uri, String rgb, String icc) {
/* 853 */       switch (type) {
/*     */         case 101:
/* 855 */           textChanged("none");
/*     */           break;
/*     */         
/*     */         case 102:
/* 859 */           textChanged("currentcolor");
/*     */           break;
/*     */         
/*     */         case 1:
/* 863 */           textChanged(rgb);
/*     */           break;
/*     */         
/*     */         case 2:
/* 867 */           textChanged(rgb + ' ' + icc);
/*     */           break;
/*     */         
/*     */         case 107:
/* 871 */           textChanged("url(" + uri + ')');
/*     */           break;
/*     */         
/*     */         case 103:
/* 875 */           textChanged("url(" + uri + ") none");
/*     */           break;
/*     */         
/*     */         case 104:
/* 879 */           textChanged("url(" + uri + ") currentcolor");
/*     */           break;
/*     */         
/*     */         case 105:
/* 883 */           textChanged("url(" + uri + ") " + rgb);
/*     */           break;
/*     */         
/*     */         case 106:
/* 887 */           textChanged("url(" + uri + ") " + rgb + ' ' + icc);
/*     */           break;
/*     */       } 
/*     */     }
/*     */   }
/*     */   
/*     */   public static interface PaintModificationHandler extends CSSOMSVGColor.ModificationHandler {
/*     */     void uriChanged(String param1String);
/*     */     
/*     */     void paintChanged(short param1Short, String param1String1, String param1String2, String param1String3);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMSVGPaint.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */