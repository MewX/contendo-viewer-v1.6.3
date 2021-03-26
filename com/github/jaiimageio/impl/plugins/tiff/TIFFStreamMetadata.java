/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import java.nio.ByteOrder;
/*     */ import javax.imageio.metadata.IIOInvalidTreeException;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
/*     */ import org.w3c.dom.NamedNodeMap;
/*     */ import org.w3c.dom.Node;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TIFFStreamMetadata
/*     */   extends IIOMetadata
/*     */ {
/*     */   static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_tiff_stream_1.0";
/*     */   static final String nativeMetadataFormatClassName = "com.github.jaiimageio.impl.plugins.tiff.TIFFStreamMetadataFormat";
/*  63 */   private static final String bigEndianString = ByteOrder.BIG_ENDIAN
/*  64 */     .toString();
/*  65 */   private static final String littleEndianString = ByteOrder.LITTLE_ENDIAN
/*  66 */     .toString();
/*     */   
/*  68 */   public ByteOrder byteOrder = ByteOrder.BIG_ENDIAN;
/*     */   
/*     */   public TIFFStreamMetadata() {
/*  71 */     super(false, "com_sun_media_imageio_plugins_tiff_stream_1.0", "com.github.jaiimageio.impl.plugins.tiff.TIFFStreamMetadataFormat", null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/*  78 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void fatal(Node node, String reason) throws IIOInvalidTreeException {
/*  84 */     throw new IIOInvalidTreeException(reason, node);
/*     */   }
/*     */   
/*     */   public Node getAsTree(String formatName) {
/*  88 */     IIOMetadataNode root = new IIOMetadataNode("com_sun_media_imageio_plugins_tiff_stream_1.0");
/*     */     
/*  90 */     IIOMetadataNode byteOrderNode = new IIOMetadataNode("ByteOrder");
/*  91 */     byteOrderNode.setAttribute("value", this.byteOrder.toString());
/*     */     
/*  93 */     root.appendChild(byteOrderNode);
/*  94 */     return root;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void mergeNativeTree(Node root) throws IIOInvalidTreeException {
/* 101 */     Node node = root;
/* 102 */     if (!node.getNodeName().equals("com_sun_media_imageio_plugins_tiff_stream_1.0")) {
/* 103 */       fatal(node, "Root must be com_sun_media_imageio_plugins_tiff_stream_1.0");
/*     */     }
/*     */     
/* 106 */     node = node.getFirstChild();
/* 107 */     if (node == null || !node.getNodeName().equals("ByteOrder")) {
/* 108 */       fatal(node, "Root must have \"ByteOrder\" child");
/*     */     }
/*     */     
/* 111 */     NamedNodeMap attrs = node.getAttributes();
/* 112 */     String order = attrs.getNamedItem("value").getNodeValue();
/*     */     
/* 114 */     if (order == null) {
/* 115 */       fatal(node, "ByteOrder node must have a \"value\" attribute");
/*     */     }
/* 117 */     if (order.equals(bigEndianString)) {
/* 118 */       this.byteOrder = ByteOrder.BIG_ENDIAN;
/* 119 */     } else if (order.equals(littleEndianString)) {
/* 120 */       this.byteOrder = ByteOrder.LITTLE_ENDIAN;
/*     */     } else {
/* 122 */       fatal(node, "Incorrect value for ByteOrder \"value\" attribute");
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/* 128 */     if (formatName.equals("com_sun_media_imageio_plugins_tiff_stream_1.0")) {
/* 129 */       if (root == null) {
/* 130 */         throw new IllegalArgumentException("root == null!");
/*     */       }
/* 132 */       mergeNativeTree(root);
/*     */     } else {
/* 134 */       throw new IllegalArgumentException("Not a recognized format!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset() {
/* 139 */     this.byteOrder = ByteOrder.BIG_ENDIAN;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFStreamMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */