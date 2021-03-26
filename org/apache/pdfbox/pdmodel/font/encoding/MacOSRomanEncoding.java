/*    */ package org.apache.pdfbox.pdmodel.font.encoding;
/*    */ 
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
/*    */ public class MacOSRomanEncoding
/*    */   extends MacRomanEncoding
/*    */ {
/*    */   private static final int CHAR_CODE = 0;
/*    */   private static final int CHAR_NAME = 1;
/* 35 */   private static final Object[][] MAC_OS_ROMAN_ENCODING_TABLE = new Object[][] { 
/* 36 */       { Integer.valueOf(255), "notequal"
/* 37 */       }, { Integer.valueOf(260), "infinity"
/* 38 */       }, { Integer.valueOf(262), "lessequal"
/* 39 */       }, { Integer.valueOf(263), "greaterequal"
/* 40 */       }, { Integer.valueOf(266), "partialdiff"
/* 41 */       }, { Integer.valueOf(267), "summation"
/* 42 */       }, { Integer.valueOf(270), "product"
/* 43 */       }, { Integer.valueOf(271), "pi"
/* 44 */       }, { Integer.valueOf(272), "integral"
/* 45 */       }, { Integer.valueOf(275), "Omega" }, 
/* 46 */       { Integer.valueOf(303), "radical"
/* 47 */       }, { Integer.valueOf(305), "approxequal"
/* 48 */       }, { Integer.valueOf(306), "Delta"
/* 49 */       }, { Integer.valueOf(327), "lozenge"
/* 50 */       }, { Integer.valueOf(333), "Euro"
/* 51 */       }, { Integer.valueOf(360), "apple" } };
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 59 */   public static final MacOSRomanEncoding INSTANCE = new MacOSRomanEncoding();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MacOSRomanEncoding() {
/* 69 */     for (Object[] encodingEntry : MAC_OS_ROMAN_ENCODING_TABLE)
/*    */     {
/* 71 */       add(((Integer)encodingEntry[0]).intValue(), encodingEntry[1].toString());
/*    */     }
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public COSBase getCOSObject() {
/* 79 */     return null;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/MacOSRomanEncoding.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */