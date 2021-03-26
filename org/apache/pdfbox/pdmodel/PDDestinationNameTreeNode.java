/*    */ package org.apache.pdfbox.pdmodel;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.cos.COSName;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*    */ import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PDDestinationNameTreeNode
/*    */   extends PDNameTreeNode<PDPageDestination>
/*    */ {
/*    */   public PDDestinationNameTreeNode() {}
/*    */   
/*    */   public PDDestinationNameTreeNode(COSDictionary dic) {
/* 49 */     super(dic);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDPageDestination convertCOSToPD(COSBase base) throws IOException {
/* 55 */     COSBase destination = base;
/* 56 */     if (base instanceof COSDictionary)
/*    */     {
/*    */ 
/*    */ 
/*    */       
/* 61 */       destination = ((COSDictionary)base).getDictionaryObject(COSName.D);
/*    */     }
/* 63 */     return (PDPageDestination)PDDestination.create(destination);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDNameTreeNode<PDPageDestination> createChildNode(COSDictionary dic) {
/* 69 */     return new PDDestinationNameTreeNode(dic);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDDestinationNameTreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */