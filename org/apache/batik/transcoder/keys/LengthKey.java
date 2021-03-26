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
/*    */ public class LengthKey
/*    */   extends TranscodingHints.Key
/*    */ {
/*    */   public boolean isCompatibleValue(Object v) {
/* 33 */     return (v instanceof Float && ((Float)v).floatValue() > 0.0F);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/keys/LengthKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */