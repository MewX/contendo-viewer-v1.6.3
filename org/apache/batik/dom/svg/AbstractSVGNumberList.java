/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.apache.batik.parser.NumberListHandler;
/*     */ import org.apache.batik.parser.NumberListParser;
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGNumber;
/*     */ import org.w3c.dom.svg.SVGNumberList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGNumberList
/*     */   extends AbstractSVGList
/*     */   implements SVGNumberList
/*     */ {
/*     */   public static final String SVG_NUMBER_LIST_SEPARATOR = " ";
/*     */   
/*     */   protected String getItemSeparator() {
/*  51 */     return " ";
/*     */   }
/*     */ 
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
/*     */   protected abstract Element getElement();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumber initialize(SVGNumber newItem) throws DOMException, SVGException {
/*  78 */     return (SVGNumber)initializeImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumber getItem(int index) throws DOMException {
/*  86 */     return (SVGNumber)getItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumber insertItemBefore(SVGNumber newItem, int index) throws DOMException, SVGException {
/*  96 */     return (SVGNumber)insertItemBeforeImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumber replaceItem(SVGNumber newItem, int index) throws DOMException, SVGException {
/* 105 */     return (SVGNumber)replaceItemImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumber removeItem(int index) throws DOMException {
/* 112 */     return (SVGNumber)removeItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGNumber appendItem(SVGNumber newItem) throws DOMException, SVGException {
/* 121 */     return (SVGNumber)appendItemImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGItem createSVGItem(Object newItem) {
/* 128 */     SVGNumber l = (SVGNumber)newItem;
/* 129 */     return new SVGNumberItem(l.getValue());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void doParse(String value, ListHandler handler) throws ParseException {
/* 140 */     NumberListParser NumberListParser = new NumberListParser();
/* 141 */     NumberListBuilder builder = new NumberListBuilder(handler);
/*     */     
/* 143 */     NumberListParser.setNumberListHandler(builder);
/* 144 */     NumberListParser.parse(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkItemType(Object newItem) throws SVGException {
/* 151 */     if (!(newItem instanceof SVGNumber))
/*     */     {
/* 153 */       createSVGException((short)0, "expected SVGNumber", (Object[])null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class NumberListBuilder
/*     */     implements NumberListHandler
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
/*     */ 
/*     */     
/*     */     public NumberListBuilder(ListHandler listHandler) {
/* 179 */       this.listHandler = listHandler;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startNumberList() throws ParseException {
/* 186 */       this.listHandler.startList();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startNumber() throws ParseException {
/* 193 */       this.currentValue = 0.0F;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void numberValue(float v) throws ParseException {
/* 200 */       this.currentValue = v;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endNumber() throws ParseException {
/* 207 */       this.listHandler.item(new SVGNumberItem(this.currentValue));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endNumberList() throws ParseException {
/* 214 */       this.listHandler.endList();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGNumberList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */