/*    */ package org.apache.pdfbox.pdmodel.interactive.action;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSArray;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*    */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDDestination;
/*    */ import org.apache.pdfbox.pdmodel.interactive.documentnavigation.destination.PDPageDestination;
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
/*    */ public class PDActionGoTo
/*    */   extends PDAction
/*    */ {
/*    */   public static final String SUB_TYPE = "GoTo";
/*    */   
/*    */   public PDActionGoTo() {
/* 46 */     setSubType("GoTo");
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public PDActionGoTo(COSDictionary a) {
/* 56 */     super(a);
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
/*    */   public PDDestination getDestination() throws IOException {
/* 68 */     return PDDestination.create(getCOSObject().getDictionaryObject(COSName.D));
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
/*    */   public void setDestination(PDDestination d) {
/* 80 */     if (d instanceof PDPageDestination) {
/*    */       
/* 82 */       PDPageDestination pageDest = (PDPageDestination)d;
/* 83 */       COSArray destArray = pageDest.getCOSObject();
/* 84 */       if (destArray.size() >= 1) {
/*    */         
/* 86 */         COSBase page = destArray.getObject(0);
/* 87 */         if (!(page instanceof COSDictionary))
/*    */         {
/* 89 */           throw new IllegalArgumentException("Destination of a GoTo action must be a page dictionary object");
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 94 */     getCOSObject().setItem(COSName.D, (COSObjectable)d);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/action/PDActionGoTo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */