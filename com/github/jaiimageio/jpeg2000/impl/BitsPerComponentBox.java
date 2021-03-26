/*    */ package com.github.jaiimageio.jpeg2000.impl;
/*    */ 
/*    */ import javax.imageio.metadata.IIOInvalidTreeException;
/*    */ import org.w3c.dom.Node;
/*    */ import org.w3c.dom.NodeList;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BitsPerComponentBox
/*    */   extends Box
/*    */ {
/*    */   public BitsPerComponentBox(byte[] bitDepth) {
/* 66 */     super(8 + bitDepth.length, 1651532643, null);
/* 67 */     this.data = bitDepth;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public BitsPerComponentBox(Node node) throws IIOInvalidTreeException {
/* 74 */     super(node);
/* 75 */     NodeList children = node.getChildNodes();
/*    */     
/* 77 */     for (int i = 0; i < children.getLength(); i++) {
/* 78 */       Node child = children.item(i);
/* 79 */       String name = child.getNodeName();
/*    */       
/* 81 */       if ("BitDepth".equals(name)) {
/* 82 */         this.data = Box.getByteArrayElementValue(child);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getBitDepth() {
/* 89 */     return this.data;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/jpeg2000/impl/BitsPerComponentBox.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */