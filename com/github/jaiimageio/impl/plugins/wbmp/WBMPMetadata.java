/*     */ package com.github.jaiimageio.impl.plugins.wbmp;
/*     */ 
/*     */ import com.github.jaiimageio.impl.common.ImageUtil;
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.imageio.metadata.IIOMetadataNode;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class WBMPMetadata
/*     */   extends IIOMetadata
/*     */ {
/*     */   public static final String nativeMetadataFormatName = "com_sun_media_imageio_plugins_wbmp_image_1.0";
/*     */   public int wbmpType;
/*     */   public int width;
/*     */   public int height;
/*     */   
/*     */   public WBMPMetadata() {
/*  74 */     super(true, "com_sun_media_imageio_plugins_wbmp_image_1.0", "com.github.jaiimageio.impl.plugins.wbmp.WBMPMetadataFormat", null, null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/*  81 */     return true;
/*     */   }
/*     */   
/*     */   public Node getAsTree(String formatName) {
/*  85 */     if (formatName.equals("com_sun_media_imageio_plugins_wbmp_image_1.0"))
/*  86 */       return getNativeTree(); 
/*  87 */     if (formatName
/*  88 */       .equals("javax_imageio_1.0")) {
/*  89 */       return getStandardTree();
/*     */     }
/*  91 */     throw new IllegalArgumentException(I18N.getString("WBMPMetadata0"));
/*     */   }
/*     */ 
/*     */   
/*     */   private Node getNativeTree() {
/*  96 */     IIOMetadataNode root = new IIOMetadataNode("com_sun_media_imageio_plugins_wbmp_image_1.0");
/*     */ 
/*     */     
/*  99 */     addChildNode(root, "WBMPType", new Integer(this.wbmpType));
/* 100 */     addChildNode(root, "Width", new Integer(this.width));
/* 101 */     addChildNode(root, "Height", new Integer(this.height));
/*     */     
/* 103 */     return root;
/*     */   }
/*     */   
/*     */   public void setFromTree(String formatName, Node root) {
/* 107 */     throw new IllegalStateException(I18N.getString("WBMPMetadata1"));
/*     */   }
/*     */   
/*     */   public void mergeTree(String formatName, Node root) {
/* 111 */     throw new IllegalStateException(I18N.getString("WBMPMetadata1"));
/*     */   }
/*     */   
/*     */   public void reset() {
/* 115 */     throw new IllegalStateException(I18N.getString("WBMPMetadata1"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private IIOMetadataNode addChildNode(IIOMetadataNode root, String name, Object object) {
/* 121 */     IIOMetadataNode child = new IIOMetadataNode(name);
/* 122 */     if (object != null) {
/* 123 */       child.setUserObject(object);
/* 124 */       child.setNodeValue(ImageUtil.convertObjectToString(object));
/*     */     } 
/* 126 */     root.appendChild(child);
/* 127 */     return child;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected IIOMetadataNode getStandardChromaNode() {
/* 133 */     IIOMetadataNode node = new IIOMetadataNode("Chroma");
/*     */     
/* 135 */     IIOMetadataNode subNode = new IIOMetadataNode("ColorSpaceType");
/* 136 */     subNode.setAttribute("name", "GRAY");
/* 137 */     node.appendChild(subNode);
/*     */     
/* 139 */     subNode = new IIOMetadataNode("NumChannels");
/* 140 */     subNode.setAttribute("value", "1");
/* 141 */     node.appendChild(subNode);
/*     */     
/* 143 */     subNode = new IIOMetadataNode("BlackIsZero");
/* 144 */     subNode.setAttribute("value", "TRUE");
/* 145 */     node.appendChild(subNode);
/*     */     
/* 147 */     return node;
/*     */   }
/*     */ 
/*     */   
/*     */   protected IIOMetadataNode getStandardDataNode() {
/* 152 */     IIOMetadataNode node = new IIOMetadataNode("Data");
/*     */     
/* 154 */     IIOMetadataNode subNode = new IIOMetadataNode("SampleFormat");
/* 155 */     subNode.setAttribute("value", "UnsignedIntegral");
/* 156 */     node.appendChild(subNode);
/*     */     
/* 158 */     subNode = new IIOMetadataNode("BitsPerSample");
/* 159 */     subNode.setAttribute("value", "1");
/* 160 */     node.appendChild(subNode);
/*     */     
/* 162 */     return node;
/*     */   }
/*     */   
/*     */   protected IIOMetadataNode getStandardDimensionNode() {
/* 166 */     IIOMetadataNode dimension_node = new IIOMetadataNode("Dimension");
/* 167 */     IIOMetadataNode node = null;
/*     */ 
/*     */ 
/*     */     
/* 171 */     node = new IIOMetadataNode("ImageOrientation");
/* 172 */     node.setAttribute("value", "Normal");
/* 173 */     dimension_node.appendChild(node);
/*     */     
/* 175 */     return dimension_node;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/wbmp/WBMPMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */