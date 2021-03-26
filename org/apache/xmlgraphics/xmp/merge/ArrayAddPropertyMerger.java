/*    */ package org.apache.xmlgraphics.xmp.merge;
/*    */ 
/*    */ import org.apache.xmlgraphics.xmp.Metadata;
/*    */ import org.apache.xmlgraphics.xmp.XMPArray;
/*    */ import org.apache.xmlgraphics.xmp.XMPArrayType;
/*    */ import org.apache.xmlgraphics.xmp.XMPProperty;
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
/*    */ public class ArrayAddPropertyMerger
/*    */   implements PropertyMerger
/*    */ {
/*    */   public void merge(XMPProperty sourceProp, Metadata target) {
/* 37 */     XMPProperty existing = target.getProperty(sourceProp.getName());
/* 38 */     if (existing == null) {
/*    */       
/* 40 */       target.setProperty(sourceProp);
/*    */     } else {
/* 42 */       XMPArray array = existing.convertSimpleValueToArray(XMPArrayType.SEQ);
/* 43 */       XMPArray otherArray = sourceProp.getArrayValue();
/* 44 */       if (otherArray == null) {
/* 45 */         if (sourceProp.getXMLLang() != null) {
/* 46 */           array.add(sourceProp.getValue().toString(), sourceProp.getXMLLang());
/*    */         } else {
/* 48 */           array.add(sourceProp.getValue());
/*    */         } 
/*    */       } else {
/*    */         
/* 52 */         for (int i = 0, c = otherArray.getSize(); i < c; i++)
/* 53 */           array.add(otherArray.getValue(i)); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/merge/ArrayAddPropertyMerger.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */