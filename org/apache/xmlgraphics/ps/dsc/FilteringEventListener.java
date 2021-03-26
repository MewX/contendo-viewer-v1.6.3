/*    */ package org.apache.xmlgraphics.ps.dsc;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.xmlgraphics.ps.dsc.events.DSCEvent;
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
/*    */ public class FilteringEventListener
/*    */   implements DSCListener
/*    */ {
/*    */   private DSCFilter filter;
/*    */   
/*    */   public FilteringEventListener(DSCFilter filter) {
/* 38 */     this.filter = filter;
/*    */   }
/*    */ 
/*    */   
/*    */   public void processEvent(DSCEvent event, DSCParser parser) throws IOException, DSCException {
/* 43 */     if (!this.filter.accept(event))
/* 44 */       parser.next(); 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/ps/dsc/FilteringEventListener.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */