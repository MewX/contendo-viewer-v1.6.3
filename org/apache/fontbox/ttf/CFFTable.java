/*    */ package org.apache.fontbox.ttf;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.fontbox.cff.CFFFont;
/*    */ import org.apache.fontbox.cff.CFFParser;
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
/*    */ public class CFFTable
/*    */   extends TTFTable
/*    */ {
/*    */   public static final String TAG = "CFF ";
/*    */   private CFFFont cffFont;
/*    */   
/*    */   CFFTable(TrueTypeFont font) {
/* 38 */     super(font);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/* 50 */     byte[] bytes = data.read((int)getLength());
/*    */     
/* 52 */     CFFParser parser = new CFFParser();
/* 53 */     this.cffFont = parser.parse(bytes, new ByteSource(this.font)).get(0);
/*    */     
/* 55 */     this.initialized = true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public CFFFont getFont() {
/* 63 */     return this.cffFont;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   private static class ByteSource
/*    */     implements CFFParser.ByteSource
/*    */   {
/*    */     private final TrueTypeFont ttf;
/*    */ 
/*    */     
/*    */     ByteSource(TrueTypeFont ttf) {
/* 75 */       this.ttf = ttf;
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public byte[] getBytes() throws IOException {
/* 81 */       return this.ttf.getTableBytes(this.ttf.getTableMap().get("CFF "));
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/CFFTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */