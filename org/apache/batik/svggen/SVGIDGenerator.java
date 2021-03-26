/*    */ package org.apache.batik.svggen;
/*    */ 
/*    */ import java.util.HashMap;
/*    */ import java.util.Map;
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
/*    */ public class SVGIDGenerator
/*    */ {
/* 32 */   private Map prefixMap = new HashMap<Object, Object>();
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
/*    */   public String generateID(String prefix) {
/* 46 */     Integer maxId = (Integer)this.prefixMap.get(prefix);
/* 47 */     if (maxId == null) {
/* 48 */       maxId = Integer.valueOf(0);
/* 49 */       this.prefixMap.put(prefix, maxId);
/*    */     } 
/*    */     
/* 52 */     maxId = Integer.valueOf(maxId.intValue() + 1);
/* 53 */     this.prefixMap.put(prefix, maxId);
/* 54 */     return prefix + maxId;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/svggen/SVGIDGenerator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */