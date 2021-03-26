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
/*    */ 
/*    */ public class PaintKey
/*    */   extends TranscodingHints.Key
/*    */ {
/*    */   public boolean isCompatibleValue(Object v) {
/* 34 */     return v instanceof java.awt.Paint;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/keys/PaintKey.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */