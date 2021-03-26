/*    */ package org.apache.pdfbox.pdmodel;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*    */ import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
/*    */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDStructureElement;
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
/*    */ public class PDStructureElementNameTreeNode
/*    */   extends PDNameTreeNode<PDStructureElement>
/*    */ {
/*    */   public PDStructureElementNameTreeNode() {}
/*    */   
/*    */   public PDStructureElementNameTreeNode(COSDictionary dic) {
/* 48 */     super(dic);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDStructureElement convertCOSToPD(COSBase base) throws IOException {
/* 54 */     return new PDStructureElement((COSDictionary)base);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDNameTreeNode<PDStructureElement> createChildNode(COSDictionary dic) {
/* 60 */     return new PDStructureElementNameTreeNode(dic);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDStructureElementNameTreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */