/*    */ package com.levigo.jbig2.decoder.huffman;
/*    */ 
/*    */ import java.io.IOException;
/*    */ import javax.imageio.stream.ImageInputStream;
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
/*    */ class OutOfBandNode
/*    */   extends Node
/*    */ {
/*    */   protected OutOfBandNode(HuffmanTable.Code paramCode) {}
/*    */   
/*    */   protected long decode(ImageInputStream paramImageInputStream) throws IOException {
/* 37 */     return Long.MAX_VALUE;
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/decoder/huffman/OutOfBandNode.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */