/*     */ package org.apache.batik.anim.dom;
/*     */ 
/*     */ import org.apache.batik.dom.svg.AbstractSVGList;
/*     */ import org.apache.batik.dom.svg.ListHandler;
/*     */ import org.apache.batik.dom.svg.SVGItem;
/*     */ import org.apache.batik.parser.LengthListHandler;
/*     */ import org.apache.batik.parser.LengthListParser;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGLength;
/*     */ import org.w3c.dom.svg.SVGLengthList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGLengthList
/*     */   extends AbstractSVGList
/*     */   implements SVGLengthList
/*     */ {
/*     */   protected short direction;
/*     */   public static final String SVG_LENGTH_LIST_SEPARATOR = " ";
/*     */   
/*     */   protected String getItemSeparator() {
/*  59 */     return " ";
/*     */   }
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
/*     */   protected abstract Element getElement();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected AbstractSVGLengthList(short direction) {
/*  79 */     this.direction = direction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength initialize(SVGLength newItem) throws DOMException, SVGException {
/*  87 */     return (SVGLength)initializeImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength getItem(int index) throws DOMException {
/*  94 */     return (SVGLength)getItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength insertItemBefore(SVGLength newItem, int index) throws DOMException, SVGException {
/* 103 */     return (SVGLength)insertItemBeforeImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength replaceItem(SVGLength newItem, int index) throws DOMException, SVGException {
/* 112 */     return (SVGLength)replaceItemImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength removeItem(int index) throws DOMException {
/* 119 */     return (SVGLength)removeItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGLength appendItem(SVGLength newItem) throws DOMException, SVGException {
/* 127 */     return (SVGLength)appendItemImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGItem createSVGItem(Object newItem) {
/* 134 */     SVGLength l = (SVGLength)newItem;
/* 135 */     return new SVGLengthItem(l.getUnitType(), l.getValueInSpecifiedUnits(), this.direction);
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
/*     */   protected void doParse(String value, ListHandler handler) throws ParseException {
/* 148 */     LengthListParser lengthListParser = new LengthListParser();
/*     */     
/* 150 */     LengthListBuilder builder = new LengthListBuilder(handler);
/*     */     
/* 152 */     lengthListParser.setLengthListHandler(builder);
/* 153 */     lengthListParser.parse(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkItemType(Object newItem) throws SVGException {
/* 160 */     if (!(newItem instanceof SVGLength)) {
/* 161 */       createSVGException((short)0, "expected.length", (Object[])null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected class SVGLengthItem
/*     */     extends AbstractSVGLength
/*     */     implements SVGItem
/*     */   {
/*     */     protected AbstractSVGList parentList;
/*     */ 
/*     */     
/*     */     public SVGLengthItem(short type, float value, short direction) {
/* 175 */       super(direction);
/* 176 */       this.unitType = type;
/* 177 */       this.value = value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected SVGOMElement getAssociatedElement() {
/* 184 */       return (SVGOMElement)AbstractSVGLengthList.this.getElement();
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
/*     */     public void setParent(AbstractSVGList list) {
/* 197 */       this.parentList = list;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public AbstractSVGList getParent() {
/* 204 */       return this.parentList;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected void reset() {
/* 211 */       if (this.parentList != null) {
/* 212 */         this.parentList.itemChanged();
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class LengthListBuilder
/*     */     implements LengthListHandler
/*     */   {
/*     */     protected ListHandler listHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected float currentValue;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     protected short currentType;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public LengthListBuilder(ListHandler listHandler) {
/* 243 */       this.listHandler = listHandler;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startLengthList() throws ParseException {
/* 250 */       this.listHandler.startList();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startLength() throws ParseException {
/* 257 */       this.currentType = 1;
/* 258 */       this.currentValue = 0.0F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void lengthValue(float v) throws ParseException {
/* 265 */       this.currentValue = v;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void em() throws ParseException {
/* 272 */       this.currentType = 3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void ex() throws ParseException {
/* 279 */       this.currentType = 4;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void in() throws ParseException {
/* 286 */       this.currentType = 8;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void cm() throws ParseException {
/* 293 */       this.currentType = 6;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void mm() throws ParseException {
/* 300 */       this.currentType = 7;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void pc() throws ParseException {
/* 307 */       this.currentType = 10;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void pt() throws ParseException {
/* 314 */       this.currentType = 3;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void px() throws ParseException {
/* 321 */       this.currentType = 5;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void percentage() throws ParseException {
/* 328 */       this.currentType = 2;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endLength() throws ParseException {
/* 335 */       this.listHandler.item(new AbstractSVGLengthList.SVGLengthItem(this.currentType, this.currentValue, AbstractSVGLengthList.this.direction));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endLengthList() throws ParseException {
/* 343 */       this.listHandler.endList();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/dom/AbstractSVGLengthList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */