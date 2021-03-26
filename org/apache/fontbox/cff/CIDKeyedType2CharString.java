/*    */ package org.apache.fontbox.cff;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Locale;
/*    */ import org.apache.fontbox.type1.Type1CharStringReader;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CIDKeyedType2CharString
/*    */   extends Type2CharString
/*    */ {
/*    */   private final int cid;
/*    */   
/*    */   public CIDKeyedType2CharString(Type1CharStringReader font, String fontName, int cid, int gid, List<Object> sequence, int defaultWidthX, int nomWidthX) {
/* 47 */     super(font, fontName, String.format(Locale.US, "%04x", new Object[] { Integer.valueOf(cid) }), gid, sequence, defaultWidthX, nomWidthX);
/* 48 */     this.cid = cid;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getCID() {
/* 56 */     return this.cid;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CIDKeyedType2CharString.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */