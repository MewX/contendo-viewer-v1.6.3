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
/*    */ 
/*    */ public class StringKey
/*    */   extends TranscodingHints.Key
/*    */ {
/*    */   public boolean isCompatibleValue(Object v) {
/* 33 */     return v instanceof String;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/keys/StringKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */