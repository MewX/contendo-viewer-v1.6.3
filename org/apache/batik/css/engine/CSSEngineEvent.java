/*    */ package org.apache.batik.css.engine;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ import org.w3c.dom.Element;
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
/*    */ public class CSSEngineEvent
/*    */   extends EventObject
/*    */ {
/*    */   protected Element element;
/*    */   protected int[] properties;
/*    */   
/*    */   public CSSEngineEvent(CSSEngine source, Element elt, int[] props) {
/* 47 */     super(source);
/* 48 */     this.element = elt;
/* 49 */     this.properties = props;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Element getElement() {
/* 56 */     return this.element;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int[] getProperties() {
/* 63 */     return this.properties;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/css/engine/CSSEngineEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */