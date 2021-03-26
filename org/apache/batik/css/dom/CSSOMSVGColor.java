/*      */ package org.apache.batik.css.dom;
/*      */ 
/*      */ import java.util.ArrayList;
/*      */ import org.apache.batik.css.engine.value.FloatValue;
/*      */ import org.apache.batik.css.engine.value.Value;
/*      */ import org.apache.batik.css.engine.value.svg.ICCColor;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.css.CSSPrimitiveValue;
/*      */ import org.w3c.dom.css.CSSValue;
/*      */ import org.w3c.dom.css.Counter;
/*      */ import org.w3c.dom.css.RGBColor;
/*      */ import org.w3c.dom.css.Rect;
/*      */ import org.w3c.dom.svg.SVGColor;
/*      */ import org.w3c.dom.svg.SVGICCColor;
/*      */ import org.w3c.dom.svg.SVGNumber;
/*      */ import org.w3c.dom.svg.SVGNumberList;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class CSSOMSVGColor
/*      */   implements RGBColor, SVGColor, SVGICCColor, SVGNumberList
/*      */ {
/*      */   protected ValueProvider valueProvider;
/*      */   protected ModificationHandler handler;
/*      */   protected RedComponent redComponent;
/*      */   protected GreenComponent greenComponent;
/*      */   protected BlueComponent blueComponent;
/*      */   protected ArrayList iccColors;
/*      */   
/*      */   public CSSOMSVGColor(ValueProvider vp) {
/*   85 */     this.valueProvider = vp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setModificationHandler(ModificationHandler h) {
/*   92 */     this.handler = h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCssText() {
/*   99 */     return this.valueProvider.getValue().getCssText();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCssText(String cssText) throws DOMException {
/*  107 */     if (this.handler == null) {
/*  108 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  111 */     this.iccColors = null;
/*  112 */     this.handler.textChanged(cssText);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getCssValueType() {
/*  121 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getColorType() {
/*      */     int primitiveType;
/*  129 */     Value value = this.valueProvider.getValue();
/*  130 */     int cssValueType = value.getCssValueType();
/*  131 */     switch (cssValueType) {
/*      */       case 1:
/*  133 */         primitiveType = value.getPrimitiveType();
/*  134 */         switch (primitiveType) {
/*      */           case 21:
/*  136 */             if (value.getStringValue().equalsIgnoreCase("currentcolor"))
/*      */             {
/*  138 */               return 3; } 
/*  139 */             return 1;
/*      */           
/*      */           case 25:
/*  142 */             return 1;
/*      */         } 
/*      */         
/*  145 */         throw new IllegalStateException("Found unexpected PrimitiveType:" + primitiveType);
/*      */       
/*      */       case 2:
/*  148 */         return 2;
/*      */     } 
/*      */     
/*  151 */     throw new IllegalStateException("Found unexpected CssValueType:" + cssValueType);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RGBColor getRGBColor() {
/*  159 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RGBColor getRgbColor() {
/*  167 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRGBColor(String color) {
/*  175 */     if (this.handler == null) {
/*  176 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  179 */     this.handler.rgbColorChanged(color);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGICCColor getICCColor() {
/*  188 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGICCColor getIccColor() {
/*  196 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setRGBColorICCColor(String rgb, String icc) {
/*  204 */     if (this.handler == null) {
/*  205 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  208 */     this.iccColors = null;
/*  209 */     this.handler.rgbColorICCColorChanged(rgb, icc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColor(short type, String rgb, String icc) {
/*  218 */     if (this.handler == null) {
/*  219 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  222 */     this.iccColors = null;
/*  223 */     this.handler.colorChanged(type, rgb, icc);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getRed() {
/*  233 */     this.valueProvider.getValue().getRed();
/*  234 */     if (this.redComponent == null) {
/*  235 */       this.redComponent = new RedComponent();
/*      */     }
/*  237 */     return this.redComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getGreen() {
/*  244 */     this.valueProvider.getValue().getGreen();
/*  245 */     if (this.greenComponent == null) {
/*  246 */       this.greenComponent = new GreenComponent();
/*      */     }
/*  248 */     return this.greenComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getBlue() {
/*  256 */     this.valueProvider.getValue().getBlue();
/*  257 */     if (this.blueComponent == null) {
/*  258 */       this.blueComponent = new BlueComponent();
/*      */     }
/*  260 */     return this.blueComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getColorProfile() {
/*  269 */     if (getColorType() != 2) {
/*  270 */       throw new DOMException((short)12, "");
/*      */     }
/*  272 */     Value value = this.valueProvider.getValue();
/*  273 */     return ((ICCColor)value.item(1)).getColorProfile();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setColorProfile(String colorProfile) throws DOMException {
/*  280 */     if (this.handler == null) {
/*  281 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  284 */     this.handler.colorProfileChanged(colorProfile);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGNumberList getColors() {
/*  292 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getNumberOfItems() {
/*  301 */     if (getColorType() != 2) {
/*  302 */       throw new DOMException((short)12, "");
/*      */     }
/*  304 */     Value value = this.valueProvider.getValue();
/*  305 */     return ((ICCColor)value.item(1)).getNumberOfColors();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() throws DOMException {
/*  312 */     if (this.handler == null) {
/*  313 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  316 */     this.iccColors = null;
/*  317 */     this.handler.colorsCleared();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGNumber initialize(SVGNumber newItem) throws DOMException {
/*  325 */     if (this.handler == null) {
/*  326 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  329 */     float f = newItem.getValue();
/*  330 */     this.iccColors = new ArrayList();
/*  331 */     SVGNumber result = new ColorNumber(f);
/*  332 */     this.iccColors.add(result);
/*  333 */     this.handler.colorsInitialized(f);
/*  334 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGNumber getItem(int index) throws DOMException {
/*  342 */     if (getColorType() != 2) {
/*  343 */       throw new DOMException((short)1, "");
/*      */     }
/*  345 */     int n = getNumberOfItems();
/*  346 */     if (index < 0 || index >= n) {
/*  347 */       throw new DOMException((short)1, "");
/*      */     }
/*  349 */     if (this.iccColors == null) {
/*  350 */       this.iccColors = new ArrayList(n);
/*  351 */       for (int i = this.iccColors.size(); i < n; i++) {
/*  352 */         this.iccColors.add(null);
/*      */       }
/*      */     } 
/*  355 */     Value value = this.valueProvider.getValue().item(1);
/*  356 */     float f = ((ICCColor)value).getColor(index);
/*  357 */     SVGNumber result = new ColorNumber(f);
/*  358 */     this.iccColors.set(index, result);
/*  359 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGNumber insertItemBefore(SVGNumber newItem, int index) throws DOMException {
/*  368 */     if (this.handler == null) {
/*  369 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  372 */     int n = getNumberOfItems();
/*  373 */     if (index < 0 || index > n) {
/*  374 */       throw new DOMException((short)1, "");
/*      */     }
/*  376 */     if (this.iccColors == null) {
/*  377 */       this.iccColors = new ArrayList(n);
/*  378 */       for (int i = this.iccColors.size(); i < n; i++) {
/*  379 */         this.iccColors.add(null);
/*      */       }
/*      */     } 
/*  382 */     float f = newItem.getValue();
/*  383 */     SVGNumber result = new ColorNumber(f);
/*  384 */     this.iccColors.add(index, result);
/*  385 */     this.handler.colorInsertedBefore(f, index);
/*  386 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGNumber replaceItem(SVGNumber newItem, int index) throws DOMException {
/*  396 */     if (this.handler == null) {
/*  397 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  400 */     int n = getNumberOfItems();
/*  401 */     if (index < 0 || index >= n) {
/*  402 */       throw new DOMException((short)1, "");
/*      */     }
/*  404 */     if (this.iccColors == null) {
/*  405 */       this.iccColors = new ArrayList(n);
/*  406 */       for (int i = this.iccColors.size(); i < n; i++) {
/*  407 */         this.iccColors.add(null);
/*      */       }
/*      */     } 
/*  410 */     float f = newItem.getValue();
/*  411 */     SVGNumber result = new ColorNumber(f);
/*  412 */     this.iccColors.set(index, result);
/*  413 */     this.handler.colorReplaced(f, index);
/*  414 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGNumber removeItem(int index) throws DOMException {
/*  422 */     if (this.handler == null) {
/*  423 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  426 */     int n = getNumberOfItems();
/*  427 */     if (index < 0 || index >= n) {
/*  428 */       throw new DOMException((short)1, "");
/*      */     }
/*  430 */     SVGNumber result = null;
/*  431 */     if (this.iccColors != null) {
/*  432 */       result = this.iccColors.get(index);
/*      */     }
/*  434 */     if (result == null) {
/*  435 */       Value value = this.valueProvider.getValue().item(1);
/*  436 */       result = new ColorNumber(((ICCColor)value).getColor(index));
/*      */     } 
/*      */     
/*  439 */     this.handler.colorRemoved(index);
/*  440 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public SVGNumber appendItem(SVGNumber newItem) throws DOMException {
/*  448 */     if (this.handler == null) {
/*  449 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  452 */     if (this.iccColors == null) {
/*  453 */       int n = getNumberOfItems();
/*  454 */       this.iccColors = new ArrayList(n);
/*  455 */       for (int i = 0; i < n; i++) {
/*  456 */         this.iccColors.add(null);
/*      */       }
/*      */     } 
/*  459 */     float f = newItem.getValue();
/*  460 */     SVGNumber result = new ColorNumber(f);
/*  461 */     this.iccColors.add(result);
/*  462 */     this.handler.colorAppend(f);
/*  463 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ColorNumber
/*      */     implements SVGNumber
/*      */   {
/*      */     protected float value;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ColorNumber(float f) {
/*  481 */       this.value = f;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getValue() {
/*  488 */       if (CSSOMSVGColor.this.iccColors == null) {
/*  489 */         return this.value;
/*      */       }
/*  491 */       int idx = CSSOMSVGColor.this.iccColors.indexOf(this);
/*  492 */       if (idx == -1) {
/*  493 */         return this.value;
/*      */       }
/*  495 */       Value value = CSSOMSVGColor.this.valueProvider.getValue().item(1);
/*  496 */       return ((ICCColor)value).getColor(idx);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setValue(float f) {
/*  503 */       this.value = f;
/*  504 */       if (CSSOMSVGColor.this.iccColors == null) {
/*      */         return;
/*      */       }
/*  507 */       int idx = CSSOMSVGColor.this.iccColors.indexOf(this);
/*  508 */       if (idx == -1) {
/*      */         return;
/*      */       }
/*  511 */       if (CSSOMSVGColor.this.handler == null) {
/*  512 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/*  515 */       CSSOMSVGColor.this.handler.colorReplaced(f, idx);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface ValueProvider
/*      */   {
/*      */     Value getValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static interface ModificationHandler
/*      */   {
/*      */     void textChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void redTextChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void redFloatValueChanged(short param1Short, float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void greenTextChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void greenFloatValueChanged(short param1Short, float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void blueTextChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void blueFloatValueChanged(short param1Short, float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void rgbColorChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void rgbColorICCColorChanged(String param1String1, String param1String2) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void colorChanged(short param1Short, String param1String1, String param1String2) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void colorProfileChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void colorsCleared() throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void colorsInitialized(float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void colorInsertedBefore(float param1Float, int param1Int) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void colorReplaced(float param1Float, int param1Int) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void colorRemoved(int param1Int) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void colorAppend(float param1Float) throws DOMException;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public abstract class AbstractModificationHandler
/*      */     implements ModificationHandler
/*      */   {
/*      */     protected abstract Value getValue();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void redTextChanged(String text) throws DOMException {
/*  642 */       StringBuffer sb = new StringBuffer(40);
/*  643 */       Value value = getValue();
/*  644 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 1:
/*  646 */           sb.append("rgb(");
/*  647 */           sb.append(text); sb.append(',');
/*  648 */           sb.append(value.getGreen().getCssText()); sb.append(',');
/*  649 */           sb.append(value.getBlue().getCssText()); sb.append(')');
/*      */           break;
/*      */         
/*      */         case 2:
/*  653 */           sb.append("rgb(");
/*  654 */           sb.append(text); sb.append(',');
/*  655 */           sb.append(value.item(0).getGreen().getCssText());
/*  656 */           sb.append(',');
/*  657 */           sb.append(value.item(0).getBlue().getCssText());
/*  658 */           sb.append(')');
/*  659 */           sb.append(value.item(1).getCssText());
/*      */           break;
/*      */         
/*      */         default:
/*  663 */           throw new DOMException((short)7, "");
/*      */       } 
/*      */       
/*  666 */       textChanged(sb.toString());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void redFloatValueChanged(short unit, float fValue) throws DOMException {
/*  674 */       StringBuffer sb = new StringBuffer(40);
/*  675 */       Value value = getValue();
/*  676 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 1:
/*  678 */           sb.append("rgb(");
/*  679 */           sb.append(FloatValue.getCssText(unit, fValue)); sb.append(',');
/*  680 */           sb.append(value.getGreen().getCssText()); sb.append(',');
/*  681 */           sb.append(value.getBlue().getCssText()); sb.append(')');
/*      */           break;
/*      */         
/*      */         case 2:
/*  685 */           sb.append("rgb(");
/*  686 */           sb.append(FloatValue.getCssText(unit, fValue));
/*  687 */           sb.append(',');
/*  688 */           sb.append(value.item(0).getGreen().getCssText());
/*  689 */           sb.append(',');
/*  690 */           sb.append(value.item(0).getBlue().getCssText());
/*  691 */           sb.append(')');
/*  692 */           sb.append(value.item(1).getCssText());
/*      */           break;
/*      */         
/*      */         default:
/*  696 */           throw new DOMException((short)7, "");
/*      */       } 
/*      */       
/*  699 */       textChanged(sb.toString());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void greenTextChanged(String text) throws DOMException {
/*  706 */       StringBuffer sb = new StringBuffer(40);
/*  707 */       Value value = getValue();
/*  708 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 1:
/*  710 */           sb.append("rgb(");
/*  711 */           sb.append(value.getRed().getCssText()); sb.append(',');
/*  712 */           sb.append(text); sb.append(',');
/*  713 */           sb.append(value.getBlue().getCssText()); sb.append(')');
/*      */           break;
/*      */         
/*      */         case 2:
/*  717 */           sb.append("rgb(");
/*  718 */           sb.append(value.item(0).getRed().getCssText());
/*  719 */           sb.append(',');
/*  720 */           sb.append(text);
/*  721 */           sb.append(',');
/*  722 */           sb.append(value.item(0).getBlue().getCssText());
/*  723 */           sb.append(')');
/*  724 */           sb.append(value.item(1).getCssText());
/*      */           break;
/*      */         
/*      */         default:
/*  728 */           throw new DOMException((short)7, "");
/*      */       } 
/*      */       
/*  731 */       textChanged(sb.toString());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void greenFloatValueChanged(short unit, float fValue) throws DOMException {
/*  739 */       StringBuffer sb = new StringBuffer(40);
/*  740 */       Value value = getValue();
/*  741 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 1:
/*  743 */           sb.append("rgb(");
/*  744 */           sb.append(value.getRed().getCssText()); sb.append(',');
/*  745 */           sb.append(FloatValue.getCssText(unit, fValue)); sb.append(',');
/*  746 */           sb.append(value.getBlue().getCssText()); sb.append(')');
/*      */           break;
/*      */         
/*      */         case 2:
/*  750 */           sb.append("rgb(");
/*  751 */           sb.append(value.item(0).getRed().getCssText());
/*  752 */           sb.append(',');
/*  753 */           sb.append(FloatValue.getCssText(unit, fValue));
/*  754 */           sb.append(',');
/*  755 */           sb.append(value.item(0).getBlue().getCssText());
/*  756 */           sb.append(')');
/*  757 */           sb.append(value.item(1).getCssText());
/*      */           break;
/*      */         
/*      */         default:
/*  761 */           throw new DOMException((short)7, "");
/*      */       } 
/*      */       
/*  764 */       textChanged(sb.toString());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void blueTextChanged(String text) throws DOMException {
/*  771 */       StringBuffer sb = new StringBuffer(40);
/*  772 */       Value value = getValue();
/*  773 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 1:
/*  775 */           sb.append("rgb(");
/*  776 */           sb.append(value.getRed().getCssText()); sb.append(',');
/*  777 */           sb.append(value.getGreen().getCssText()); sb.append(',');
/*  778 */           sb.append(text); sb.append(')');
/*      */           break;
/*      */         
/*      */         case 2:
/*  782 */           sb.append("rgb(");
/*  783 */           sb.append(value.item(0).getRed().getCssText());
/*  784 */           sb.append(',');
/*  785 */           sb.append(value.item(0).getGreen().getCssText());
/*  786 */           sb.append(',');
/*  787 */           sb.append(text);
/*  788 */           sb.append(')');
/*  789 */           sb.append(value.item(1).getCssText());
/*      */           break;
/*      */         
/*      */         default:
/*  793 */           throw new DOMException((short)7, "");
/*      */       } 
/*      */       
/*  796 */       textChanged(sb.toString());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void blueFloatValueChanged(short unit, float fValue) throws DOMException {
/*  804 */       StringBuffer sb = new StringBuffer(40);
/*  805 */       Value value = getValue();
/*  806 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 1:
/*  808 */           sb.append("rgb(");
/*  809 */           sb.append(value.getRed().getCssText()); sb.append(',');
/*  810 */           sb.append(value.getGreen().getCssText()); sb.append(',');
/*  811 */           sb.append(FloatValue.getCssText(unit, fValue)); sb.append(')');
/*      */           break;
/*      */         
/*      */         case 2:
/*  815 */           sb.append("rgb(");
/*  816 */           sb.append(value.item(0).getRed().getCssText());
/*  817 */           sb.append(',');
/*  818 */           sb.append(value.item(0).getGreen().getCssText());
/*  819 */           sb.append(',');
/*  820 */           sb.append(FloatValue.getCssText(unit, fValue));
/*  821 */           sb.append(')');
/*  822 */           sb.append(value.item(1).getCssText());
/*      */           break;
/*      */         
/*      */         default:
/*  826 */           throw new DOMException((short)7, "");
/*      */       } 
/*      */       
/*  829 */       textChanged(sb.toString());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void rgbColorChanged(String text) throws DOMException {
/*  836 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 1:
/*      */           break;
/*      */         
/*      */         case 2:
/*  841 */           text = text + getValue().item(1).getCssText();
/*      */           break;
/*      */         
/*      */         default:
/*  845 */           throw new DOMException((short)7, "");
/*      */       } 
/*      */       
/*  848 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void rgbColorICCColorChanged(String rgb, String icc) throws DOMException {
/*  856 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 2:
/*  858 */           textChanged(rgb + ' ' + icc);
/*      */           return;
/*      */       } 
/*      */       
/*  862 */       throw new DOMException((short)7, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void colorChanged(short type, String rgb, String icc) throws DOMException {
/*  872 */       switch (type) {
/*      */         case 3:
/*  874 */           textChanged("currentcolor");
/*      */           return;
/*      */         
/*      */         case 1:
/*  878 */           textChanged(rgb);
/*      */           return;
/*      */         
/*      */         case 2:
/*  882 */           textChanged(rgb + ' ' + icc);
/*      */           return;
/*      */       } 
/*      */       
/*  886 */       throw new DOMException((short)9, "");
/*      */     }
/*      */ 
/*      */     
/*      */     public void colorProfileChanged(String cp) throws DOMException {
/*      */       StringBuffer sb;
/*      */       ICCColor iccc;
/*      */       int i;
/*  894 */       Value value = getValue();
/*  895 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 2:
/*  897 */           sb = new StringBuffer(value.item(0).getCssText());
/*      */           
/*  899 */           sb.append(" icc-color(");
/*  900 */           sb.append(cp);
/*  901 */           iccc = (ICCColor)value.item(1);
/*  902 */           for (i = 0; i < iccc.getLength(); i++) {
/*  903 */             sb.append(',');
/*  904 */             sb.append(iccc.getColor(i));
/*      */           } 
/*  906 */           sb.append(')');
/*  907 */           textChanged(sb.toString());
/*      */           return;
/*      */       } 
/*      */       
/*  911 */       throw new DOMException((short)7, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void colorsCleared() throws DOMException {
/*      */       StringBuffer sb;
/*      */       ICCColor iccc;
/*  920 */       Value value = getValue();
/*  921 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 2:
/*  923 */           sb = new StringBuffer(value.item(0).getCssText());
/*      */           
/*  925 */           sb.append(" icc-color(");
/*  926 */           iccc = (ICCColor)value.item(1);
/*  927 */           sb.append(iccc.getColorProfile());
/*  928 */           sb.append(')');
/*  929 */           textChanged(sb.toString());
/*      */           return;
/*      */       } 
/*      */       
/*  933 */       throw new DOMException((short)7, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void colorsInitialized(float f) throws DOMException {
/*      */       StringBuffer sb;
/*      */       ICCColor iccc;
/*  942 */       Value value = getValue();
/*  943 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 2:
/*  945 */           sb = new StringBuffer(value.item(0).getCssText());
/*      */           
/*  947 */           sb.append(" icc-color(");
/*  948 */           iccc = (ICCColor)value.item(1);
/*  949 */           sb.append(iccc.getColorProfile());
/*  950 */           sb.append(',');
/*  951 */           sb.append(f);
/*  952 */           sb.append(')');
/*  953 */           textChanged(sb.toString());
/*      */           return;
/*      */       } 
/*      */       
/*  957 */       throw new DOMException((short)7, "");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void colorInsertedBefore(float f, int idx) throws DOMException {
/*      */       StringBuffer sb;
/*      */       ICCColor iccc;
/*      */       int i;
/*  966 */       Value value = getValue();
/*  967 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 2:
/*  969 */           sb = new StringBuffer(value.item(0).getCssText());
/*      */           
/*  971 */           sb.append(" icc-color(");
/*  972 */           iccc = (ICCColor)value.item(1);
/*  973 */           sb.append(iccc.getColorProfile());
/*  974 */           for (i = 0; i < idx; i++) {
/*  975 */             sb.append(',');
/*  976 */             sb.append(iccc.getColor(i));
/*      */           } 
/*  978 */           sb.append(',');
/*  979 */           sb.append(f);
/*  980 */           for (i = idx; i < iccc.getLength(); i++) {
/*  981 */             sb.append(',');
/*  982 */             sb.append(iccc.getColor(i));
/*      */           } 
/*  984 */           sb.append(')');
/*  985 */           textChanged(sb.toString());
/*      */           return;
/*      */       } 
/*      */       
/*  989 */       throw new DOMException((short)7, "");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void colorReplaced(float f, int idx) throws DOMException {
/*      */       StringBuffer sb;
/*      */       ICCColor iccc;
/*      */       int i;
/*  998 */       Value value = getValue();
/*  999 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 2:
/* 1001 */           sb = new StringBuffer(value.item(0).getCssText());
/*      */           
/* 1003 */           sb.append(" icc-color(");
/* 1004 */           iccc = (ICCColor)value.item(1);
/* 1005 */           sb.append(iccc.getColorProfile());
/* 1006 */           for (i = 0; i < idx; i++) {
/* 1007 */             sb.append(',');
/* 1008 */             sb.append(iccc.getColor(i));
/*      */           } 
/* 1010 */           sb.append(',');
/* 1011 */           sb.append(f);
/* 1012 */           for (i = idx + 1; i < iccc.getLength(); i++) {
/* 1013 */             sb.append(',');
/* 1014 */             sb.append(iccc.getColor(i));
/*      */           } 
/* 1016 */           sb.append(')');
/* 1017 */           textChanged(sb.toString());
/*      */           return;
/*      */       } 
/*      */       
/* 1021 */       throw new DOMException((short)7, "");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void colorRemoved(int idx) throws DOMException {
/*      */       StringBuffer sb;
/*      */       ICCColor iccc;
/*      */       int i;
/* 1030 */       Value value = getValue();
/* 1031 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 2:
/* 1033 */           sb = new StringBuffer(value.item(0).getCssText());
/*      */           
/* 1035 */           sb.append(" icc-color(");
/* 1036 */           iccc = (ICCColor)value.item(1);
/* 1037 */           sb.append(iccc.getColorProfile());
/* 1038 */           for (i = 0; i < idx; i++) {
/* 1039 */             sb.append(',');
/* 1040 */             sb.append(iccc.getColor(i));
/*      */           } 
/* 1042 */           for (i = idx + 1; i < iccc.getLength(); i++) {
/* 1043 */             sb.append(',');
/* 1044 */             sb.append(iccc.getColor(i));
/*      */           } 
/* 1046 */           sb.append(')');
/* 1047 */           textChanged(sb.toString());
/*      */           return;
/*      */       } 
/*      */       
/* 1051 */       throw new DOMException((short)7, "");
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*      */     public void colorAppend(float f) throws DOMException {
/*      */       StringBuffer sb;
/*      */       ICCColor iccc;
/*      */       int i;
/* 1060 */       Value value = getValue();
/* 1061 */       switch (CSSOMSVGColor.this.getColorType()) {
/*      */         case 2:
/* 1063 */           sb = new StringBuffer(value.item(0).getCssText());
/*      */           
/* 1065 */           sb.append(" icc-color(");
/* 1066 */           iccc = (ICCColor)value.item(1);
/* 1067 */           sb.append(iccc.getColorProfile());
/* 1068 */           for (i = 0; i < iccc.getLength(); i++) {
/* 1069 */             sb.append(',');
/* 1070 */             sb.append(iccc.getColor(i));
/*      */           } 
/* 1072 */           sb.append(',');
/* 1073 */           sb.append(f);
/* 1074 */           sb.append(')');
/* 1075 */           textChanged(sb.toString());
/*      */           return;
/*      */       } 
/*      */       
/* 1079 */       throw new DOMException((short)7, "");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract class AbstractComponent
/*      */     implements CSSPrimitiveValue
/*      */   {
/*      */     protected abstract Value getValue();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getCssText() {
/* 1100 */       return getValue().getCssText();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short getCssValueType() {
/* 1108 */       return getValue().getCssValueType();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short getPrimitiveType() {
/* 1116 */       return getValue().getPrimitiveType();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getFloatValue(short unitType) throws DOMException {
/* 1124 */       return CSSOMValue.convertFloatValue(unitType, getValue());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getStringValue() throws DOMException {
/* 1132 */       return CSSOMSVGColor.this.valueProvider.getValue().getStringValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Counter getCounterValue() throws DOMException {
/* 1140 */       throw new DOMException((short)15, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Rect getRectValue() throws DOMException {
/* 1148 */       throw new DOMException((short)15, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public RGBColor getRGBColorValue() throws DOMException {
/* 1156 */       throw new DOMException((short)15, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLength() {
/* 1166 */       throw new DOMException((short)15, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public CSSValue item(int index) {
/* 1174 */       throw new DOMException((short)15, "");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected abstract class FloatComponent
/*      */     extends AbstractComponent
/*      */   {
/*      */     public void setStringValue(short stringType, String stringValue) throws DOMException {
/* 1189 */       throw new DOMException((short)15, "");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class RedComponent
/*      */     extends FloatComponent
/*      */   {
/*      */     protected Value getValue() {
/* 1202 */       return CSSOMSVGColor.this.valueProvider.getValue().getRed();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1210 */       if (CSSOMSVGColor.this.handler == null) {
/* 1211 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1214 */       getValue();
/* 1215 */       CSSOMSVGColor.this.handler.redTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1225 */       if (CSSOMSVGColor.this.handler == null) {
/* 1226 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1229 */       getValue();
/* 1230 */       CSSOMSVGColor.this.handler.redFloatValueChanged(unitType, floatValue);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class GreenComponent
/*      */     extends FloatComponent
/*      */   {
/*      */     protected Value getValue() {
/* 1246 */       return CSSOMSVGColor.this.valueProvider.getValue().getGreen();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1254 */       if (CSSOMSVGColor.this.handler == null) {
/* 1255 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1258 */       getValue();
/* 1259 */       CSSOMSVGColor.this.handler.greenTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1269 */       if (CSSOMSVGColor.this.handler == null) {
/* 1270 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1273 */       getValue();
/* 1274 */       CSSOMSVGColor.this.handler.greenFloatValueChanged(unitType, floatValue);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class BlueComponent
/*      */     extends FloatComponent
/*      */   {
/*      */     protected Value getValue() {
/* 1289 */       return CSSOMSVGColor.this.valueProvider.getValue().getBlue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1297 */       if (CSSOMSVGColor.this.handler == null) {
/* 1298 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1301 */       getValue();
/* 1302 */       CSSOMSVGColor.this.handler.blueTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1312 */       if (CSSOMSVGColor.this.handler == null) {
/* 1313 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1316 */       getValue();
/* 1317 */       CSSOMSVGColor.this.handler.blueFloatValueChanged(unitType, floatValue);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMSVGColor.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */