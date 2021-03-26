/*     */ package com.github.jaiimageio.impl.plugins.pcx;
/*     */ 
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
/*     */ public class PCXMetadata
/*     */   extends IIOMetadata
/*     */   implements PCXConstants, Cloneable
/*     */ {
/*     */   short version;
/*     */   byte bitsPerPixel;
/*     */   boolean gotxmin;
/*     */   boolean gotymin;
/*     */   short xmin;
/*     */   short ymin;
/*     */   int vdpi;
/*     */   int hdpi;
/*     */   int hsize;
/*     */   int vsize;
/*     */   
/*     */   PCXMetadata() {
/*  67 */     super(true, null, null, null, null);
/*  68 */     reset();
/*     */   }
/*     */   
/*     */   public Node getAsTree(String formatName) {
/*  72 */     if (formatName.equals("javax_imageio_1.0")) {
/*  73 */       return getStandardTree();
/*     */     }
/*  75 */     throw new IllegalArgumentException("Not a recognized format!");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/*  80 */     return false;
/*     */   }
/*     */   
/*     */   public void mergeTree(String formatName, Node root) throws IIOInvalidTreeException {
/*  84 */     if (formatName.equals("javax_imageio_1.0")) {
/*  85 */       if (root == null) {
/*  86 */         throw new IllegalArgumentException("root == null!");
/*     */       }
/*  88 */       mergeStandardTree(root);
/*     */     } else {
/*  90 */       throw new IllegalArgumentException("Not a recognized format!");
/*     */     } 
/*     */   }
/*     */   
/*     */   public void reset() {
/*  95 */     this.version = 5;
/*  96 */     this.bitsPerPixel = 0;
/*  97 */     this.gotxmin = false;
/*  98 */     this.gotymin = false;
/*  99 */     this.xmin = 0;
/* 100 */     this.ymin = 0;
/* 101 */     this.vdpi = 72;
/* 102 */     this.hdpi = 72;
/* 103 */     this.hsize = 0;
/* 104 */     this.vsize = 0;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardDocumentNode() {
/*     */     String versionString;
/* 109 */     switch (this.version) {
/*     */       case 0:
/* 111 */         versionString = "2.5";
/*     */         break;
/*     */       case 2:
/* 114 */         versionString = "2.8 with palette";
/*     */         break;
/*     */       case 3:
/* 117 */         versionString = "2.8 without palette";
/*     */         break;
/*     */       case 4:
/* 120 */         versionString = "PC Paintbrush for Windows";
/*     */         break;
/*     */       case 5:
/* 123 */         versionString = "3.0";
/*     */         break;
/*     */       
/*     */       default:
/* 127 */         versionString = null;
/*     */         break;
/*     */     } 
/* 130 */     IIOMetadataNode documentNode = null;
/* 131 */     if (versionString != null) {
/* 132 */       documentNode = new IIOMetadataNode("Document");
/* 133 */       IIOMetadataNode node = new IIOMetadataNode("FormatVersion");
/* 134 */       node.setAttribute("value", versionString);
/* 135 */       documentNode.appendChild(node);
/*     */     } 
/*     */     
/* 138 */     return documentNode;
/*     */   }
/*     */   
/*     */   public IIOMetadataNode getStandardDimensionNode() {
/* 142 */     IIOMetadataNode dimensionNode = new IIOMetadataNode("Dimension");
/* 143 */     IIOMetadataNode node = null;
/*     */     
/* 145 */     node = new IIOMetadataNode("HorizontalPixelOffset");
/* 146 */     node.setAttribute("value", String.valueOf(this.xmin));
/* 147 */     dimensionNode.appendChild(node);
/*     */     
/* 149 */     node = new IIOMetadataNode("VerticalPixelOffset");
/* 150 */     node.setAttribute("value", String.valueOf(this.ymin));
/* 151 */     dimensionNode.appendChild(node);
/*     */     
/* 153 */     node = new IIOMetadataNode("HorizontalPixelSize");
/* 154 */     node.setAttribute("value", String.valueOf(254.0D / this.hdpi));
/* 155 */     dimensionNode.appendChild(node);
/*     */     
/* 157 */     node = new IIOMetadataNode("VerticalPixelSize");
/* 158 */     node.setAttribute("value", String.valueOf(254.0D / this.vdpi));
/* 159 */     dimensionNode.appendChild(node);
/*     */     
/* 161 */     if (this.hsize != 0) {
/* 162 */       node = new IIOMetadataNode("HorizontalScreenSize");
/* 163 */       node.setAttribute("value", String.valueOf(this.hsize));
/* 164 */       dimensionNode.appendChild(node);
/*     */     } 
/*     */     
/* 167 */     if (this.vsize != 0) {
/* 168 */       node = new IIOMetadataNode("VerticalScreenSize");
/* 169 */       node.setAttribute("value", String.valueOf(this.vsize));
/* 170 */       dimensionNode.appendChild(node);
/*     */     } 
/*     */     
/* 173 */     return dimensionNode;
/*     */   }
/*     */   
/*     */   private void mergeStandardTree(Node root) throws IIOInvalidTreeException {
/* 177 */     Node node = root;
/* 178 */     if (!node.getNodeName().equals("javax_imageio_1.0")) {
/* 179 */       throw new IIOInvalidTreeException("Root must be javax_imageio_1.0", node);
/*     */     }
/*     */ 
/*     */     
/* 183 */     node = node.getFirstChild();
/* 184 */     while (node != null) {
/* 185 */       String name = node.getNodeName();
/*     */       
/* 187 */       if (name.equals("Dimension")) {
/* 188 */         Node child = node.getFirstChild();
/*     */         
/* 190 */         while (child != null) {
/* 191 */           String childName = child.getNodeName();
/* 192 */           if (childName.equals("HorizontalPixelOffset")) {
/* 193 */             String hpo = getAttribute(child, "value");
/* 194 */             this.xmin = Short.valueOf(hpo).shortValue();
/* 195 */             this.gotxmin = true;
/* 196 */           } else if (childName.equals("VerticalPixelOffset")) {
/* 197 */             String vpo = getAttribute(child, "value");
/* 198 */             this.ymin = Short.valueOf(vpo).shortValue();
/* 199 */             this.gotymin = true;
/* 200 */           } else if (childName.equals("HorizontalPixelSize")) {
/* 201 */             String hps = getAttribute(child, "value");
/* 202 */             this.hdpi = (int)(254.0F / Float.parseFloat(hps) + 0.5F);
/* 203 */           } else if (childName.equals("VerticalPixelSize")) {
/* 204 */             String vps = getAttribute(child, "value");
/* 205 */             this.vdpi = (int)(254.0F / Float.parseFloat(vps) + 0.5F);
/* 206 */           } else if (childName.equals("HorizontalScreenSize")) {
/* 207 */             String hss = getAttribute(child, "value");
/* 208 */             this.hsize = Integer.valueOf(hss).intValue();
/* 209 */           } else if (childName.equals("VerticalScreenSize")) {
/* 210 */             String vss = getAttribute(child, "value");
/* 211 */             this.vsize = Integer.valueOf(vss).intValue();
/*     */           } 
/*     */           
/* 214 */           child = child.getNextSibling();
/*     */         } 
/*     */       } 
/*     */       
/* 218 */       node = node.getNextSibling();
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String getAttribute(Node node, String attrName) {
/* 223 */     NamedNodeMap attrs = node.getAttributes();
/* 224 */     Node attr = attrs.getNamedItem(attrName);
/* 225 */     return (attr != null) ? attr.getNodeValue() : null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/pcx/PCXMetadata.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */