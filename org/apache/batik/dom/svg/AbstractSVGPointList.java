/*     */ package org.apache.batik.dom.svg;
/*     */ 
/*     */ import org.apache.batik.parser.ParseException;
/*     */ import org.apache.batik.parser.PointsHandler;
/*     */ import org.apache.batik.parser.PointsParser;
/*     */ import org.w3c.dom.DOMException;
/*     */ import org.w3c.dom.svg.SVGException;
/*     */ import org.w3c.dom.svg.SVGPoint;
/*     */ import org.w3c.dom.svg.SVGPointList;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AbstractSVGPointList
/*     */   extends AbstractSVGList
/*     */   implements SVGPointList
/*     */ {
/*     */   public static final String SVG_POINT_LIST_SEPARATOR = " ";
/*     */   
/*     */   protected String getItemSeparator() {
/*  49 */     return " ";
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
/*     */   public SVGPoint initialize(SVGPoint newItem) throws DOMException, SVGException {
/*  65 */     return (SVGPoint)initializeImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint getItem(int index) throws DOMException {
/*  72 */     return (SVGPoint)getItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint insertItemBefore(SVGPoint newItem, int index) throws DOMException, SVGException {
/*  81 */     return (SVGPoint)insertItemBeforeImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint replaceItem(SVGPoint newItem, int index) throws DOMException, SVGException {
/*  90 */     return (SVGPoint)replaceItemImpl(newItem, index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint removeItem(int index) throws DOMException {
/*  97 */     return (SVGPoint)removeItemImpl(index);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SVGPoint appendItem(SVGPoint newItem) throws DOMException, SVGException {
/* 105 */     return (SVGPoint)appendItemImpl(newItem);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected SVGItem createSVGItem(Object newItem) {
/* 112 */     SVGPoint point = (SVGPoint)newItem;
/* 113 */     return new SVGPointItem(point.getX(), point.getY());
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
/* 124 */     PointsParser pointsParser = new PointsParser();
/* 125 */     PointsListBuilder builder = new PointsListBuilder(handler);
/* 126 */     pointsParser.setPointsHandler(builder);
/* 127 */     pointsParser.parse(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void checkItemType(Object newItem) throws SVGException {
/* 134 */     if (!(newItem instanceof SVGPoint)) {
/* 135 */       createSVGException((short)0, "expected.point", (Object[])null);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected class PointsListBuilder
/*     */     implements PointsHandler
/*     */   {
/*     */     protected ListHandler listHandler;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public PointsListBuilder(ListHandler listHandler) {
/* 156 */       this.listHandler = listHandler;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void startPoints() throws ParseException {
/* 163 */       this.listHandler.startList();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void point(float x, float y) throws ParseException {
/* 170 */       this.listHandler.item(new SVGPointItem(x, y));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public void endPoints() throws ParseException {
/* 177 */       this.listHandler.endList();
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/dom/svg/AbstractSVGPointList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */