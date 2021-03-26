/*    */ package org.apache.batik.ext.awt.image.codec.imageio;
/*    */ 
/*    */ import javax.imageio.metadata.IIOMetadata;
/*    */ import javax.xml.transform.Result;
/*    */ import javax.xml.transform.Source;
/*    */ import javax.xml.transform.Transformer;
/*    */ import javax.xml.transform.TransformerFactory;
/*    */ import javax.xml.transform.dom.DOMSource;
/*    */ import javax.xml.transform.stream.StreamResult;
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
/*    */ public class ImageIODebugUtil
/*    */ {
/*    */   public static void dumpMetadata(IIOMetadata meta) {
/* 39 */     String format = meta.getNativeMetadataFormatName();
/* 40 */     Node node = meta.getAsTree(format);
/* 41 */     dumpNode(node);
/*    */   }
/*    */   
/*    */   public static void dumpNode(Node node) {
/*    */     try {
/* 46 */       TransformerFactory tf = TransformerFactory.newInstance();
/* 47 */       Transformer t = tf.newTransformer();
/* 48 */       Source src = new DOMSource(node);
/* 49 */       Result res = new StreamResult(System.out);
/* 50 */       t.transform(src, res);
/* 51 */       System.out.println();
/* 52 */     } catch (Exception e) {
/* 53 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/ext/awt/image/codec/imageio/ImageIODebugUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */