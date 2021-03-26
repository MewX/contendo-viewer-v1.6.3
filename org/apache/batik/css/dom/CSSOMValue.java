/*      */ package org.apache.batik.css.dom;
/*      */ 
/*      */ import org.apache.batik.css.engine.value.FloatValue;
/*      */ import org.apache.batik.css.engine.value.ListValue;
/*      */ import org.apache.batik.css.engine.value.StringValue;
/*      */ import org.apache.batik.css.engine.value.Value;
/*      */ import org.w3c.dom.DOMException;
/*      */ import org.w3c.dom.css.CSSPrimitiveValue;
/*      */ import org.w3c.dom.css.CSSValue;
/*      */ import org.w3c.dom.css.CSSValueList;
/*      */ import org.w3c.dom.css.Counter;
/*      */ import org.w3c.dom.css.RGBColor;
/*      */ import org.w3c.dom.css.Rect;
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
/*      */ public class CSSOMValue
/*      */   implements CSSPrimitiveValue, CSSValueList, Counter, RGBColor, Rect
/*      */ {
/*      */   protected ValueProvider valueProvider;
/*      */   protected ModificationHandler handler;
/*      */   protected LeftComponent leftComponent;
/*      */   protected RightComponent rightComponent;
/*      */   protected BottomComponent bottomComponent;
/*      */   protected TopComponent topComponent;
/*      */   protected RedComponent redComponent;
/*      */   protected GreenComponent greenComponent;
/*      */   protected BlueComponent blueComponent;
/*      */   protected CSSValue[] items;
/*      */   
/*      */   public CSSOMValue(ValueProvider vp) {
/*  102 */     this.valueProvider = vp;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setModificationHandler(ModificationHandler h) {
/*  109 */     this.handler = h;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getCssText() {
/*  116 */     return this.valueProvider.getValue().getCssText();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setCssText(String cssText) throws DOMException {
/*  124 */     if (this.handler == null) {
/*  125 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  128 */     this.handler.textChanged(cssText);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getCssValueType() {
/*  137 */     return this.valueProvider.getValue().getCssValueType();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public short getPrimitiveType() {
/*  145 */     return this.valueProvider.getValue().getPrimitiveType();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloatValue(short unitType, float floatValue) throws DOMException {
/*  154 */     if (this.handler == null) {
/*  155 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  158 */     this.handler.floatValueChanged(unitType, floatValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloatValue(short unitType) throws DOMException {
/*  167 */     return convertFloatValue(unitType, this.valueProvider.getValue());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static float convertFloatValue(short unitType, Value value) {
/*  174 */     switch (unitType) {
/*      */       case 1:
/*      */       case 2:
/*      */       case 3:
/*      */       case 4:
/*      */       case 5:
/*      */       case 18:
/*  181 */         if (value.getPrimitiveType() == unitType) {
/*  182 */           return value.getFloatValue();
/*      */         }
/*      */         break;
/*      */       case 6:
/*  186 */         return toCentimeters(value);
/*      */       case 7:
/*  188 */         return toMillimeters(value);
/*      */       case 8:
/*  190 */         return toInches(value);
/*      */       case 9:
/*  192 */         return toPoints(value);
/*      */       case 10:
/*  194 */         return toPicas(value);
/*      */       case 11:
/*  196 */         return toDegrees(value);
/*      */       case 12:
/*  198 */         return toRadians(value);
/*      */       case 13:
/*  200 */         return toGradians(value);
/*      */       case 14:
/*  202 */         return toMilliseconds(value);
/*      */       case 15:
/*  204 */         return toSeconds(value);
/*      */       case 16:
/*  206 */         return toHertz(value);
/*      */       case 17:
/*  208 */         return tokHertz(value);
/*      */     } 
/*  210 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toCentimeters(Value value) {
/*  217 */     switch (value.getPrimitiveType()) {
/*      */       case 6:
/*  219 */         return value.getFloatValue();
/*      */       case 7:
/*  221 */         return value.getFloatValue() / 10.0F;
/*      */       case 8:
/*  223 */         return value.getFloatValue() * 2.54F;
/*      */       case 9:
/*  225 */         return value.getFloatValue() * 2.54F / 72.0F;
/*      */       case 10:
/*  227 */         return value.getFloatValue() * 2.54F / 6.0F;
/*      */     } 
/*  229 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toInches(Value value) {
/*  237 */     switch (value.getPrimitiveType()) {
/*      */       case 6:
/*  239 */         return value.getFloatValue() / 2.54F;
/*      */       case 7:
/*  241 */         return value.getFloatValue() / 25.4F;
/*      */       case 8:
/*  243 */         return value.getFloatValue();
/*      */       case 9:
/*  245 */         return value.getFloatValue() / 72.0F;
/*      */       case 10:
/*  247 */         return value.getFloatValue() / 6.0F;
/*      */     } 
/*  249 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toMillimeters(Value value) {
/*  257 */     switch (value.getPrimitiveType()) {
/*      */       case 6:
/*  259 */         return value.getFloatValue() * 10.0F;
/*      */       case 7:
/*  261 */         return value.getFloatValue();
/*      */       case 8:
/*  263 */         return value.getFloatValue() * 25.4F;
/*      */       case 9:
/*  265 */         return value.getFloatValue() * 25.4F / 72.0F;
/*      */       case 10:
/*  267 */         return value.getFloatValue() * 25.4F / 6.0F;
/*      */     } 
/*  269 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toPoints(Value value) {
/*  277 */     switch (value.getPrimitiveType()) {
/*      */       case 6:
/*  279 */         return value.getFloatValue() * 72.0F / 2.54F;
/*      */       case 7:
/*  281 */         return value.getFloatValue() * 72.0F / 25.4F;
/*      */       case 8:
/*  283 */         return value.getFloatValue() * 72.0F;
/*      */       case 9:
/*  285 */         return value.getFloatValue();
/*      */       case 10:
/*  287 */         return value.getFloatValue() * 12.0F;
/*      */     } 
/*  289 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toPicas(Value value) {
/*  297 */     switch (value.getPrimitiveType()) {
/*      */       case 6:
/*  299 */         return value.getFloatValue() * 6.0F / 2.54F;
/*      */       case 7:
/*  301 */         return value.getFloatValue() * 6.0F / 25.4F;
/*      */       case 8:
/*  303 */         return value.getFloatValue() * 6.0F;
/*      */       case 9:
/*  305 */         return value.getFloatValue() / 12.0F;
/*      */       case 10:
/*  307 */         return value.getFloatValue();
/*      */     } 
/*  309 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toDegrees(Value value) {
/*  317 */     switch (value.getPrimitiveType()) {
/*      */       case 11:
/*  319 */         return value.getFloatValue();
/*      */       case 12:
/*  321 */         return (float)Math.toDegrees(value.getFloatValue());
/*      */       case 13:
/*  323 */         return value.getFloatValue() * 9.0F / 5.0F;
/*      */     } 
/*  325 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toRadians(Value value) {
/*  333 */     switch (value.getPrimitiveType()) {
/*      */       case 11:
/*  335 */         return value.getFloatValue() * 5.0F / 9.0F;
/*      */       case 12:
/*  337 */         return value.getFloatValue();
/*      */       case 13:
/*  339 */         return (float)((value.getFloatValue() * 100.0F) / Math.PI);
/*      */     } 
/*  341 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toGradians(Value value) {
/*  349 */     switch (value.getPrimitiveType()) {
/*      */       case 11:
/*  351 */         return (float)(value.getFloatValue() * Math.PI / 180.0D);
/*      */       case 12:
/*  353 */         return (float)(value.getFloatValue() * Math.PI / 100.0D);
/*      */       case 13:
/*  355 */         return value.getFloatValue();
/*      */     } 
/*  357 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toMilliseconds(Value value) {
/*  365 */     switch (value.getPrimitiveType()) {
/*      */       case 14:
/*  367 */         return value.getFloatValue();
/*      */       case 15:
/*  369 */         return value.getFloatValue() * 1000.0F;
/*      */     } 
/*  371 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toSeconds(Value value) {
/*  379 */     switch (value.getPrimitiveType()) {
/*      */       case 14:
/*  381 */         return value.getFloatValue() / 1000.0F;
/*      */       case 15:
/*  383 */         return value.getFloatValue();
/*      */     } 
/*  385 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float toHertz(Value value) {
/*  393 */     switch (value.getPrimitiveType()) {
/*      */       case 16:
/*  395 */         return value.getFloatValue();
/*      */       case 17:
/*  397 */         return value.getFloatValue() / 1000.0F;
/*      */     } 
/*  399 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected static float tokHertz(Value value) {
/*  407 */     switch (value.getPrimitiveType()) {
/*      */       case 16:
/*  409 */         return value.getFloatValue() * 1000.0F;
/*      */       case 17:
/*  411 */         return value.getFloatValue();
/*      */     } 
/*  413 */     throw new DOMException((short)15, "");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setStringValue(short stringType, String stringValue) throws DOMException {
/*  423 */     if (this.handler == null) {
/*  424 */       throw new DOMException((short)7, "");
/*      */     }
/*      */     
/*  427 */     this.handler.stringValueChanged(stringType, stringValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getStringValue() throws DOMException {
/*  436 */     return this.valueProvider.getValue().getStringValue();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Counter getCounterValue() throws DOMException {
/*  444 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Rect getRectValue() throws DOMException {
/*  452 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public RGBColor getRGBColorValue() throws DOMException {
/*  460 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getLength() {
/*  469 */     return this.valueProvider.getValue().getLength();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSValue item(int index) {
/*  476 */     int len = this.valueProvider.getValue().getLength();
/*  477 */     if (index < 0 || index >= len) {
/*  478 */       return null;
/*      */     }
/*  480 */     if (this.items == null) {
/*  481 */       this.items = new CSSValue[this.valueProvider.getValue().getLength()];
/*  482 */     } else if (this.items.length < len) {
/*  483 */       CSSValue[] nitems = new CSSValue[len];
/*  484 */       System.arraycopy(this.items, 0, nitems, 0, this.items.length);
/*  485 */       this.items = nitems;
/*      */     } 
/*  487 */     CSSValue result = this.items[index];
/*  488 */     if (result == null) {
/*  489 */       this.items[index] = result = new ListComponent(index);
/*      */     }
/*  491 */     return result;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getIdentifier() {
/*  500 */     return this.valueProvider.getValue().getIdentifier();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getListStyle() {
/*  507 */     return this.valueProvider.getValue().getListStyle();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getSeparator() {
/*  514 */     return this.valueProvider.getValue().getSeparator();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getTop() {
/*  523 */     this.valueProvider.getValue().getTop();
/*  524 */     if (this.topComponent == null) {
/*  525 */       this.topComponent = new TopComponent();
/*      */     }
/*  527 */     return this.topComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getRight() {
/*  534 */     this.valueProvider.getValue().getRight();
/*  535 */     if (this.rightComponent == null) {
/*  536 */       this.rightComponent = new RightComponent();
/*      */     }
/*  538 */     return this.rightComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getBottom() {
/*  545 */     this.valueProvider.getValue().getBottom();
/*  546 */     if (this.bottomComponent == null) {
/*  547 */       this.bottomComponent = new BottomComponent();
/*      */     }
/*  549 */     return this.bottomComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getLeft() {
/*  556 */     this.valueProvider.getValue().getLeft();
/*  557 */     if (this.leftComponent == null) {
/*  558 */       this.leftComponent = new LeftComponent();
/*      */     }
/*  560 */     return this.leftComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getRed() {
/*  569 */     this.valueProvider.getValue().getRed();
/*  570 */     if (this.redComponent == null) {
/*  571 */       this.redComponent = new RedComponent();
/*      */     }
/*  573 */     return this.redComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getGreen() {
/*  580 */     this.valueProvider.getValue().getGreen();
/*  581 */     if (this.greenComponent == null) {
/*  582 */       this.greenComponent = new GreenComponent();
/*      */     }
/*  584 */     return this.greenComponent;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public CSSPrimitiveValue getBlue() {
/*  592 */     this.valueProvider.getValue().getBlue();
/*  593 */     if (this.blueComponent == null) {
/*  594 */       this.blueComponent = new BlueComponent();
/*      */     }
/*  596 */     return this.blueComponent;
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
/*      */     void floatValueChanged(short param1Short, float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void stringValueChanged(short param1Short, String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void leftTextChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void leftFloatValueChanged(short param1Short, float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void topTextChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void topFloatValueChanged(short param1Short, float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void rightTextChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void rightFloatValueChanged(short param1Short, float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void bottomTextChanged(String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void bottomFloatValueChanged(short param1Short, float param1Float) throws DOMException;
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
/*      */     void listTextChanged(int param1Int, String param1String) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void listFloatValueChanged(int param1Int, short param1Short, float param1Float) throws DOMException;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     void listStringValueChanged(int param1Int, short param1Short, String param1String) throws DOMException;
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
/*      */     public void floatValueChanged(short unit, float value) throws DOMException {
/*  742 */       textChanged(FloatValue.getCssText(unit, value));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void stringValueChanged(short type, String value) throws DOMException {
/*  750 */       textChanged(StringValue.getCssText(type, value));
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void leftTextChanged(String text) throws DOMException {
/*  757 */       Value val = getValue();
/*  758 */       text = "rect(" + val.getTop().getCssText() + ", " + val.getRight().getCssText() + ", " + val.getBottom().getCssText() + ", " + text + ')';
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  763 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void leftFloatValueChanged(short unit, float value) throws DOMException {
/*  771 */       Value val = getValue();
/*  772 */       String text = "rect(" + val.getTop().getCssText() + ", " + val.getRight().getCssText() + ", " + val.getBottom().getCssText() + ", " + FloatValue.getCssText(unit, value) + ')';
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  777 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void topTextChanged(String text) throws DOMException {
/*  784 */       Value val = getValue();
/*  785 */       text = "rect(" + text + ", " + val.getRight().getCssText() + ", " + val.getBottom().getCssText() + ", " + val.getLeft().getCssText() + ')';
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  790 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void topFloatValueChanged(short unit, float value) throws DOMException {
/*  798 */       Value val = getValue();
/*  799 */       String text = "rect(" + FloatValue.getCssText(unit, value) + ", " + val.getRight().getCssText() + ", " + val.getBottom().getCssText() + ", " + val.getLeft().getCssText() + ')';
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  804 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void rightTextChanged(String text) throws DOMException {
/*  811 */       Value val = getValue();
/*  812 */       text = "rect(" + val.getTop().getCssText() + ", " + text + ", " + val.getBottom().getCssText() + ", " + val.getLeft().getCssText() + ')';
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  817 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void rightFloatValueChanged(short unit, float value) throws DOMException {
/*  825 */       Value val = getValue();
/*  826 */       String text = "rect(" + val.getTop().getCssText() + ", " + FloatValue.getCssText(unit, value) + ", " + val.getBottom().getCssText() + ", " + val.getLeft().getCssText() + ')';
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  831 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void bottomTextChanged(String text) throws DOMException {
/*  838 */       Value val = getValue();
/*  839 */       text = "rect(" + val.getTop().getCssText() + ", " + val.getRight().getCssText() + ", " + text + ", " + val.getLeft().getCssText() + ')';
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  844 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void bottomFloatValueChanged(short unit, float value) throws DOMException {
/*  852 */       Value val = getValue();
/*  853 */       String text = "rect(" + val.getTop().getCssText() + ", " + val.getRight().getCssText() + ", " + FloatValue.getCssText(unit, value) + ", " + val.getLeft().getCssText() + ')';
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  858 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void redTextChanged(String text) throws DOMException {
/*  865 */       Value val = getValue();
/*  866 */       text = "rgb(" + text + ", " + val.getGreen().getCssText() + ", " + val.getBlue().getCssText() + ')';
/*      */ 
/*      */ 
/*      */       
/*  870 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void redFloatValueChanged(short unit, float value) throws DOMException {
/*  878 */       Value val = getValue();
/*  879 */       String text = "rgb(" + FloatValue.getCssText(unit, value) + ", " + val.getGreen().getCssText() + ", " + val.getBlue().getCssText() + ')';
/*      */ 
/*      */ 
/*      */       
/*  883 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void greenTextChanged(String text) throws DOMException {
/*  890 */       Value val = getValue();
/*  891 */       text = "rgb(" + val.getRed().getCssText() + ", " + text + ", " + val.getBlue().getCssText() + ')';
/*      */ 
/*      */ 
/*      */       
/*  895 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void greenFloatValueChanged(short unit, float value) throws DOMException {
/*  903 */       Value val = getValue();
/*  904 */       String text = "rgb(" + val.getRed().getCssText() + ", " + FloatValue.getCssText(unit, value) + ", " + val.getBlue().getCssText() + ')';
/*      */ 
/*      */ 
/*      */       
/*  908 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void blueTextChanged(String text) throws DOMException {
/*  915 */       Value val = getValue();
/*  916 */       text = "rgb(" + val.getRed().getCssText() + ", " + val.getGreen().getCssText() + ", " + text + ')';
/*      */ 
/*      */ 
/*      */       
/*  920 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void blueFloatValueChanged(short unit, float value) throws DOMException {
/*  928 */       Value val = getValue();
/*  929 */       String text = "rgb(" + val.getRed().getCssText() + ", " + val.getGreen().getCssText() + ", " + FloatValue.getCssText(unit, value) + ')';
/*      */ 
/*      */ 
/*      */       
/*  933 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void listTextChanged(int idx, String text) throws DOMException {
/*  940 */       ListValue lv = (ListValue)getValue();
/*  941 */       int len = lv.getLength();
/*  942 */       StringBuffer sb = new StringBuffer(len * 8); int i;
/*  943 */       for (i = 0; i < idx; i++) {
/*  944 */         sb.append(lv.item(i).getCssText());
/*  945 */         sb.append(lv.getSeparatorChar());
/*      */       } 
/*  947 */       sb.append(text);
/*  948 */       for (i = idx + 1; i < len; i++) {
/*  949 */         sb.append(lv.getSeparatorChar());
/*  950 */         sb.append(lv.item(i).getCssText());
/*      */       } 
/*  952 */       text = sb.toString();
/*  953 */       textChanged(text);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void listFloatValueChanged(int idx, short unit, float value) throws DOMException {
/*  961 */       ListValue lv = (ListValue)getValue();
/*  962 */       int len = lv.getLength();
/*  963 */       StringBuffer sb = new StringBuffer(len * 8); int i;
/*  964 */       for (i = 0; i < idx; i++) {
/*  965 */         sb.append(lv.item(i).getCssText());
/*  966 */         sb.append(lv.getSeparatorChar());
/*      */       } 
/*  968 */       sb.append(FloatValue.getCssText(unit, value));
/*  969 */       for (i = idx + 1; i < len; i++) {
/*  970 */         sb.append(lv.getSeparatorChar());
/*  971 */         sb.append(lv.item(i).getCssText());
/*      */       } 
/*  973 */       textChanged(sb.toString());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void listStringValueChanged(int idx, short unit, String value) throws DOMException {
/*  981 */       ListValue lv = (ListValue)getValue();
/*  982 */       int len = lv.getLength();
/*  983 */       StringBuffer sb = new StringBuffer(len * 8); int i;
/*  984 */       for (i = 0; i < idx; i++) {
/*  985 */         sb.append(lv.item(i).getCssText());
/*  986 */         sb.append(lv.getSeparatorChar());
/*      */       } 
/*  988 */       sb.append(StringValue.getCssText(unit, value));
/*  989 */       for (i = idx + 1; i < len; i++) {
/*  990 */         sb.append(lv.getSeparatorChar());
/*  991 */         sb.append(lv.item(i).getCssText());
/*      */       } 
/*  993 */       textChanged(sb.toString());
/*      */     }
/*      */   }
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
/*      */     public String getCssText() {
/* 1012 */       return getValue().getCssText();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short getCssValueType() {
/* 1020 */       return getValue().getCssValueType();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public short getPrimitiveType() {
/* 1028 */       return getValue().getPrimitiveType();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public float getFloatValue(short unitType) throws DOMException {
/* 1036 */       return CSSOMValue.convertFloatValue(unitType, getValue());
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public String getStringValue() throws DOMException {
/* 1044 */       return CSSOMValue.this.valueProvider.getValue().getStringValue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Counter getCounterValue() throws DOMException {
/* 1052 */       throw new DOMException((short)15, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public Rect getRectValue() throws DOMException {
/* 1060 */       throw new DOMException((short)15, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public RGBColor getRGBColorValue() throws DOMException {
/* 1068 */       throw new DOMException((short)15, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public int getLength() {
/* 1078 */       throw new DOMException((short)15, "");
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public CSSValue item(int index) {
/* 1086 */       throw new DOMException((short)15, "");
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
/* 1101 */       throw new DOMException((short)15, "");
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class LeftComponent
/*      */     extends FloatComponent
/*      */   {
/*      */     protected Value getValue() {
/* 1114 */       return CSSOMValue.this.valueProvider.getValue().getLeft();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1122 */       if (CSSOMValue.this.handler == null) {
/* 1123 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1126 */       getValue();
/* 1127 */       CSSOMValue.this.handler.leftTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1137 */       if (CSSOMValue.this.handler == null) {
/* 1138 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1141 */       getValue();
/* 1142 */       CSSOMValue.this.handler.leftFloatValueChanged(unitType, floatValue);
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
/*      */   protected class TopComponent
/*      */     extends FloatComponent
/*      */   {
/*      */     protected Value getValue() {
/* 1157 */       return CSSOMValue.this.valueProvider.getValue().getTop();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1165 */       if (CSSOMValue.this.handler == null) {
/* 1166 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1169 */       getValue();
/* 1170 */       CSSOMValue.this.handler.topTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1180 */       if (CSSOMValue.this.handler == null) {
/* 1181 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1184 */       getValue();
/* 1185 */       CSSOMValue.this.handler.topFloatValueChanged(unitType, floatValue);
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
/*      */   protected class RightComponent
/*      */     extends FloatComponent
/*      */   {
/*      */     protected Value getValue() {
/* 1200 */       return CSSOMValue.this.valueProvider.getValue().getRight();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1208 */       if (CSSOMValue.this.handler == null) {
/* 1209 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1212 */       getValue();
/* 1213 */       CSSOMValue.this.handler.rightTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1223 */       if (CSSOMValue.this.handler == null) {
/* 1224 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1227 */       getValue();
/* 1228 */       CSSOMValue.this.handler.rightFloatValueChanged(unitType, floatValue);
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
/*      */   protected class BottomComponent
/*      */     extends FloatComponent
/*      */   {
/*      */     protected Value getValue() {
/* 1244 */       return CSSOMValue.this.valueProvider.getValue().getBottom();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1252 */       if (CSSOMValue.this.handler == null) {
/* 1253 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1256 */       getValue();
/* 1257 */       CSSOMValue.this.handler.bottomTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1267 */       if (CSSOMValue.this.handler == null) {
/* 1268 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1271 */       getValue();
/* 1272 */       CSSOMValue.this.handler.bottomFloatValueChanged(unitType, floatValue);
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
/*      */   protected class RedComponent
/*      */     extends FloatComponent
/*      */   {
/*      */     protected Value getValue() {
/* 1288 */       return CSSOMValue.this.valueProvider.getValue().getRed();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1296 */       if (CSSOMValue.this.handler == null) {
/* 1297 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1300 */       getValue();
/* 1301 */       CSSOMValue.this.handler.redTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1311 */       if (CSSOMValue.this.handler == null) {
/* 1312 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1315 */       getValue();
/* 1316 */       CSSOMValue.this.handler.redFloatValueChanged(unitType, floatValue);
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
/* 1332 */       return CSSOMValue.this.valueProvider.getValue().getGreen();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1340 */       if (CSSOMValue.this.handler == null) {
/* 1341 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1344 */       getValue();
/* 1345 */       CSSOMValue.this.handler.greenTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1355 */       if (CSSOMValue.this.handler == null) {
/* 1356 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1359 */       getValue();
/* 1360 */       CSSOMValue.this.handler.greenFloatValueChanged(unitType, floatValue);
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
/* 1375 */       return CSSOMValue.this.valueProvider.getValue().getBlue();
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1383 */       if (CSSOMValue.this.handler == null) {
/* 1384 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1387 */       getValue();
/* 1388 */       CSSOMValue.this.handler.blueTextChanged(cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1398 */       if (CSSOMValue.this.handler == null) {
/* 1399 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1402 */       getValue();
/* 1403 */       CSSOMValue.this.handler.blueFloatValueChanged(unitType, floatValue);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected class ListComponent
/*      */     extends AbstractComponent
/*      */   {
/*      */     protected int index;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public ListComponent(int idx) {
/* 1423 */       this.index = idx;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     protected Value getValue() {
/* 1430 */       if (this.index >= CSSOMValue.this.valueProvider.getValue().getLength()) {
/* 1431 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1434 */       return CSSOMValue.this.valueProvider.getValue().item(this.index);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setCssText(String cssText) throws DOMException {
/* 1442 */       if (CSSOMValue.this.handler == null) {
/* 1443 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1446 */       getValue();
/* 1447 */       CSSOMValue.this.handler.listTextChanged(this.index, cssText);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setFloatValue(short unitType, float floatValue) throws DOMException {
/* 1457 */       if (CSSOMValue.this.handler == null) {
/* 1458 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1461 */       getValue();
/* 1462 */       CSSOMValue.this.handler.listFloatValueChanged(this.index, unitType, floatValue);
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*      */     public void setStringValue(short stringType, String stringValue) throws DOMException {
/* 1472 */       if (CSSOMValue.this.handler == null) {
/* 1473 */         throw new DOMException((short)7, "");
/*      */       }
/*      */       
/* 1476 */       getValue();
/* 1477 */       CSSOMValue.this.handler.listStringValueChanged(this.index, stringType, stringValue);
/*      */     }
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/dom/CSSOMValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */