/*    */ package org.apache.xmlgraphics.util;
/*    */ 
/*    */ import javax.imageio.metadata.IIOMetadata;
/*    */ import javax.xml.transform.Result;
/*    */ import javax.xml.transform.Source;
/*    */ import javax.xml.transform.Transformer;
/*    */ import javax.xml.transform.TransformerConfigurationException;
/*    */ import javax.xml.transform.TransformerException;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ImageIODebugUtil
/*    */ {
/*    */   public static void dumpMetadata(IIOMetadata meta, boolean nativeFormat) {
/*    */     String format;
/* 48 */     if (nativeFormat) {
/* 49 */       format = meta.getNativeMetadataFormatName();
/*    */     } else {
/* 51 */       format = "javax_imageio_1.0";
/*    */     } 
/* 53 */     Node node = meta.getAsTree(format);
/* 54 */     dumpNode(node);
/*    */   }
/*    */   
/*    */   public static void dumpNode(Node node) {
/*    */     try {
/* 59 */       TransformerFactory tf = TransformerFactory.newInstance();
/* 60 */       Transformer t = tf.newTransformer();
/* 61 */       t.setOutputProperty("omit-xml-declaration", "yes");
/* 62 */       Source src = new DOMSource(node);
/* 63 */       Result res = new StreamResult(System.out);
/* 64 */       t.transform(src, res);
/* 65 */     } catch (TransformerConfigurationException e) {
/* 66 */       e.printStackTrace();
/* 67 */     } catch (TransformerException e) {
/* 68 */       e.printStackTrace();
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/util/ImageIODebugUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */