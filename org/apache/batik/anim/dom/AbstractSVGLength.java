/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.parser.LengthHandler;
/*     */ import org.apache.batik.parser.LengthParser;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.UnitProcessor;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGLength;
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
/*     */ public abstract class AbstractSVGLength
/*     */   implements SVGLength
/*     */ {
/*     */   public static final short HORIZONTAL_LENGTH = 2;
/*     */   public static final short VERTICAL_LENGTH = 1;
/*     */   public static final short OTHER_LENGTH = 0;
/*     */   protected short unitType;
/*     */   protected float value;
/*     */   protected short direction;
/*     */   protected UnitProcessor.Context context;
/*  92 */   protected static final String[] UNITS = new String[] { "", "", "%", "em", "ex", "px", "cm", "mm", "in", "pt", "pc" };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected abstract SVGOMElement getAssociatedElement();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AbstractSVGLength(short direction) {
/* 105 */     this.context = new DefaultContext();
/* 106 */     this.direction = direction;
/* 107 */     this.value = 0.0F;
/* 108 */     this.unitType = 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short getUnitType() {
/* 115 */     revalidate();
/* 116 */     return this.unitType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getValue() {
/* 123 */     revalidate();
/*     */     try {
/* 125 */       return UnitProcessor.svgToUserSpace(this.value, this.unitType, this.direction, this.context);
/*     */     }
/* 127 */     catch (IllegalArgumentException ex) {
/*     */ 
/*     */       
/* 130 */       return 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValue(float value) throws DOMException {
/* 138 */     this.value = UnitProcessor.userSpaceToSVG(value, this.unitType, this.direction, this.context);
/*     */     
/* 140 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float getValueInSpecifiedUnits() {
/* 147 */     revalidate();
/* 148 */     return this.value;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueInSpecifiedUnits(float value) throws DOMException {
/* 156 */     revalidate();
/* 157 */     this.value = value;
/* 158 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getValueAsString() {
/* 165 */     revalidate();
/* 166 */     if (this.unitType == 0) {
/* 167 */       return "";
/*     */     }
/* 169 */     return Float.toString(this.value) + UNITS[this.unitType];
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setValueAsString(String value) throws DOMException {
/* 176 */     parse(value);
/* 177 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void newValueSpecifiedUnits(short unit, float value) {
/* 185 */     this.unitType = unit;
/* 186 */     this.value = value;
/* 187 */     reset();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void convertToSpecifiedUnits(short unit) {
/* 195 */     float v = getValue();
/* 196 */     this.unitType = unit;
/* 197 */     setValue(v);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void reset() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void revalidate() {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void parse(String s) {
/*     */     try {
/* 227 */       LengthParser lengthParser = new LengthParser();
/* 228 */       UnitProcessor.UnitResolver ur = new UnitProcessor.UnitResolver();
/*     */       
/* 230 */       lengthParser.setLengthHandler((LengthHandler)ur);
/* 231 */       lengthParser.parse(s);
/* 232 */       this.unitType = ur.unit;
/* 233 */       this.value = ur.value;
/* 234 */     } catch (ParseException e) {
/* 235 */       this.unitType = 0;
/* 236 */       this.value = 0.0F;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class DefaultContext
/*     */     implements UnitProcessor.Context
/*     */   {
/*     */     public Element getElement() {
/* 249 */       return (Element)AbstractSVGLength.this.getAssociatedElement();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getPixelUnitToMillimeter() {
/* 256 */       return AbstractSVGLength.this.getAssociatedElement().getSVGContext().getPixelUnitToMillimeter();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getPixelToMM() {
/* 266 */       return getPixelUnitToMillimeter();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getFontSize() {
/* 273 */       return AbstractSVGLength.this.getAssociatedElement().getSVGContext().getFontSize();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getXHeight() {
/* 280 */       return 0.5F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getViewportWidth() {
/* 287 */       return AbstractSVGLength.this.getAssociatedElement().getSVGContext().getViewportWidth();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public float getViewportHeight() {
/* 294 */       return AbstractSVGLength.this.getAssociatedElement().getSVGContext().getViewportHeight();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AbstractSVGLength.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */