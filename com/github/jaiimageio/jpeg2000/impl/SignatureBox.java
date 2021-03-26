/*    */ package com.github.jaiimageio.jpeg2000.impl;
/*    */ 
/*    */ import javax.imageio.metadata.IIOInvalidTreeException;
/*    */ import javax.imageio.metadata.IIOMetadataNode;
/*    */ import org.w3c.dom.Node;
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
/*    */ public class SignatureBox
/*    */   extends Box
/*    */ {
/*    */   public SignatureBox() {
/* 60 */     super(12, 1783636000, null);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SignatureBox(Node node) throws IIOInvalidTreeException {
/* 67 */     super(node);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public SignatureBox(byte[] data) throws IIOInvalidTreeException {
/* 74 */     super(12, 1783636000, data);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public IIOMetadataNode getNativeNode() {
/* 82 */     IIOMetadataNode node = new IIOMetadataNode(Box.getName(getType()));
/* 83 */     setDefaultAttributes(node);
/* 84 */     node.setAttribute("Signature", Integer.toString(218793738));
/* 85 */     return node;
/*    */   }
/*    */   
/*    */   protected void compose() {
/* 89 */     if (this.data != null)
/*    */       return; 
/* 91 */     this.data = new byte[] { 13, 10, -121, 10 };
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/SignatureBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */