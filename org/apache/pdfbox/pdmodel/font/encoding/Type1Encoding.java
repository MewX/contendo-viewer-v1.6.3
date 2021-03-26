/*    */ package org.apache.pdfbox.pdmodel.font.encoding;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.fontbox.afm.CharMetric;
/*    */ import org.apache.fontbox.afm.FontMetrics;
/*    */ import org.apache.fontbox.encoding.Encoding;
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
/*    */ public class Type1Encoding
/*    */   extends Encoding
/*    */ {
/*    */   public static Type1Encoding fromFontBox(Encoding encoding) {
/* 38 */     Map<Integer, String> codeToName = encoding.getCodeToNameMap();
/* 39 */     Type1Encoding enc = new Type1Encoding();
/*    */     
/* 41 */     for (Map.Entry<Integer, String> entry : codeToName.entrySet())
/*    */     {
/* 43 */       enc.add(((Integer)entry.getKey()).intValue(), entry.getValue());
/*    */     }
/*    */     
/* 46 */     return enc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type1Encoding() {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Type1Encoding(FontMetrics fontMetrics) {
/* 63 */     for (CharMetric nextMetric : fontMetrics.getCharMetrics())
/*    */     {
/* 65 */       add(nextMetric.getCharacterCode(), nextMetric.getName());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 72 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getEncodingName() {
/* 78 */     return "built-in (Type 1)";
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/Type1Encoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */