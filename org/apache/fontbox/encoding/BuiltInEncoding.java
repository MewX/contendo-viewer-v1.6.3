/*    */ package org.apache.fontbox.encoding;
/*    */ 
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
/*    */ 
/*    */ 
/*    */ public class BuiltInEncoding
/*    */   extends Encoding
/*    */ {
/*    */   public BuiltInEncoding(Map<Integer, String> codeToName) {
/* 35 */     for (Map.Entry<Integer, String> entry : codeToName.entrySet())
/*    */     {
/* 37 */       addCharacterEncoding(((Integer)entry.getKey()).intValue(), entry.getValue());
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/encoding/BuiltInEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */