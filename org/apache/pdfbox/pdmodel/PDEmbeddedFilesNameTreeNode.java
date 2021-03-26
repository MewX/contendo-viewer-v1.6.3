/*    */ package org.apache.pdfbox.pdmodel;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*    */ import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
/*    */ import org.apache.pdfbox.pdmodel.common.filespecification.PDComplexFileSpecification;
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
/*    */ public class PDEmbeddedFilesNameTreeNode
/*    */   extends PDNameTreeNode<PDComplexFileSpecification>
/*    */ {
/*    */   public PDEmbeddedFilesNameTreeNode() {}
/*    */   
/*    */   public PDEmbeddedFilesNameTreeNode(COSDictionary dic) {
/* 47 */     super(dic);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDComplexFileSpecification convertCOSToPD(COSBase base) throws IOException {
/* 53 */     return new PDComplexFileSpecification((COSDictionary)base);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDNameTreeNode<PDComplexFileSpecification> createChildNode(COSDictionary dic) {
/* 59 */     return new PDEmbeddedFilesNameTreeNode(dic);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDEmbeddedFilesNameTreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */