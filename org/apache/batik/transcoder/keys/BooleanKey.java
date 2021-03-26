/*    */ package org.apache.batik.transcoder.keys;
/*    */ 
/*    */ import org.apache.batik.transcoder.TranscodingHints;
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
/*    */ public class BooleanKey
/*    */   extends TranscodingHints.Key
/*    */ {
/*    */   public boolean isCompatibleValue(Object v) {
/* 32 */     return (v != null && v instanceof Boolean);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/keys/BooleanKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */