/*    */ package org.apache.pdfbox.pdmodel.font.encoding;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.pdfbox.cos.COSBase;
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
/*    */ public class BuiltInEncoding
/*    */   extends Encoding
/*    */ {
/*    */   public BuiltInEncoding(Map<Integer, String> codeToName) {
/* 37 */     for (Map.Entry<Integer, String> entry : codeToName.entrySet())
/*    */     {
/* 39 */       add(((Integer)entry.getKey()).intValue(), entry.getValue());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 46 */     throw new UnsupportedOperationException("Built-in encodings cannot be serialized");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEncodingName() {
/* 52 */     return "built-in (TTF)";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/BuiltInEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */