/*    */ package org.apache.batik.bridge.svg12;
/*    */ 
/*    */ import java.util.EventObject;
/*    */ import org.apache.batik.anim.dom.XBLOMContentElement;
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
/*    */ public class ContentSelectionChangedEvent
/*    */   extends EventObject
/*    */ {
/*    */   public ContentSelectionChangedEvent(XBLOMContentElement c) {
/* 39 */     super(c);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public XBLOMContentElement getContentElement() {
/* 46 */     return (XBLOMContentElement)this.source;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/bridge/svg12/ContentSelectionChangedEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */