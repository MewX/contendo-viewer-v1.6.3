/*    */ package org.apache.pdfbox.pdmodel;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import org.apache.pdfbox.cos.COSBase;
/*    */ import org.apache.pdfbox.cos.COSDictionary;
/*    */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*    */ import org.apache.pdfbox.pdmodel.common.PDNameTreeNode;
/*    */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionFactory;
/*    */ import org.apache.pdfbox.pdmodel.interactive.action.PDActionJavaScript;
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
/*    */ public class PDJavascriptNameTreeNode
/*    */   extends PDNameTreeNode<PDActionJavaScript>
/*    */ {
/*    */   public PDJavascriptNameTreeNode() {}
/*    */   
/*    */   public PDJavascriptNameTreeNode(COSDictionary dic) {
/* 48 */     super(dic);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDActionJavaScript convertCOSToPD(COSBase base) throws IOException {
/* 54 */     if (!(base instanceof COSDictionary))
/*    */     {
/* 56 */       throw new IOException("Error creating Javascript object, expected a COSDictionary and not " + base);
/*    */     }
/* 58 */     return (PDActionJavaScript)PDActionFactory.createAction((COSDictionary)base);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   protected PDNameTreeNode<PDActionJavaScript> createChildNode(COSDictionary dic) {
/* 64 */     return new PDJavascriptNameTreeNode(dic);
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/PDJavascriptNameTreeNode.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */