/*    */ package org.apache.batik.transcoder.wmf.tosvg;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
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
/*    */ public class MetaRecord
/*    */ {
/*    */   public int functionId;
/*    */   public int numPoints;
/* 38 */   private final List ptVector = new ArrayList();
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void EnsureCapacity(int cc) {}
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void AddElement(Object obj) {
/* 51 */     this.ptVector.add(obj);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final void addElement(int iValue) {
/* 61 */     this.ptVector.add(Integer.valueOf(iValue));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public Integer ElementAt(int offset) {
/* 70 */     return this.ptVector.get(offset);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public final int elementAt(int offset) {
/* 80 */     return ((Integer)this.ptVector.get(offset)).intValue();
/*    */   }
/*    */   
/*    */   public static class ByteRecord
/*    */     extends MetaRecord
/*    */   {
/*    */     public final byte[] bstr;
/*    */     
/*    */     public ByteRecord(byte[] bstr) {
/* 89 */       this.bstr = bstr;
/*    */     }
/*    */   }
/*    */   
/*    */   public static class StringRecord extends MetaRecord {
/*    */     public final String text;
/*    */     
/*    */     public StringRecord(String newText) {
/* 97 */       this.text = newText;
/*    */     }
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/transcoder/wmf/tosvg/MetaRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */