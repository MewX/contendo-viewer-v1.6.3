/*    */ package org.apache.commons.collections.bidimap;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.commons.collections.BidiMap;
/*    */ import org.apache.commons.collections.MapIterator;
/*    */ import org.apache.commons.collections.map.AbstractMapDecorator;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class AbstractBidiMapDecorator
/*    */   extends AbstractMapDecorator
/*    */   implements BidiMap
/*    */ {
/*    */   protected AbstractBidiMapDecorator(BidiMap map) {
/* 49 */     super((Map)map);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   protected BidiMap getBidiMap() {
/* 58 */     return (BidiMap)this.map;
/*    */   }
/*    */ 
/*    */   
/*    */   public MapIterator mapIterator() {
/* 63 */     return getBidiMap().mapIterator();
/*    */   }
/*    */   
/*    */   public Object getKey(Object value) {
/* 67 */     return getBidiMap().getKey(value);
/*    */   }
/*    */   
/*    */   public Object removeValue(Object value) {
/* 71 */     return getBidiMap().removeValue(value);
/*    */   }
/*    */   
/*    */   public BidiMap inverseBidiMap() {
/* 75 */     return getBidiMap().inverseBidiMap();
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/collections/bidimap/AbstractBidiMapDecorator.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */