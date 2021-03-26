/*     */ package com.levigo.jbig2;
/*     */ 
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
/*     */ public class JBIG2ImageMetadata
/*     */   extends IIOMetadata
/*     */ {
/*     */   static final String IMAGE_METADATA_FORMAT_NAME = "jbig2";
/*     */   private final JBIG2Page page;
/*     */   
/*     */   public JBIG2ImageMetadata(JBIG2Page paramJBIG2Page) {
/*  36 */     super(true, "jbig2", JBIG2ImageMetadataFormat.class.getName(), null, null);
/*     */     
/*  38 */     if (paramJBIG2Page == null) {
/*  39 */       throw new IllegalArgumentException("page must not be null");
/*     */     }
/*  41 */     this.page = paramJBIG2Page;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReadOnly() {
/*  46 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Node getAsTree(String paramString) {
/*  51 */     if (paramString.equals(this.nativeMetadataFormatName))
/*  52 */       return getNativeTree(); 
/*  53 */     if (paramString.equals("javax_imageio_1.0")) {
/*  54 */       return getStandardTree();
/*     */     }
/*  56 */     throw new IllegalArgumentException("Not a recognized format!");
/*     */   }
/*     */ 
/*     */   
/*     */   private Node getNativeTree() {
/*  61 */     IIOMetadataNode iIOMetadataNode = new IIOMetadataNode(this.nativeMetadataFormatName);
/*  62 */     iIOMetadataNode.appendChild(getStandardDimensionNode());
/*  63 */     return iIOMetadataNode;
/*     */   }
/*     */ 
/*     */   
/*     */   public IIOMetadataNode getStandardDimensionNode() {
/*  68 */     IIOMetadataNode iIOMetadataNode1 = new IIOMetadataNode("Dimension");
/*  69 */     IIOMetadataNode iIOMetadataNode2 = null;
/*     */     
/*  71 */     iIOMetadataNode2 = new IIOMetadataNode("PixelAspectRatio");
/*  72 */     iIOMetadataNode2.setAttribute("value", "1.0");
/*  73 */     iIOMetadataNode1.appendChild(iIOMetadataNode2);
/*     */     
/*  75 */     iIOMetadataNode2 = new IIOMetadataNode("ImageOrientation");
/*  76 */     iIOMetadataNode2.setAttribute("value", "Normal");
/*  77 */     iIOMetadataNode1.appendChild(iIOMetadataNode2);
/*     */     
/*  79 */     if (this.page.getResolutionX() != 0) {
/*  80 */       String str = Float.toString(25.4F / this.page.getResolutionX() / 39.3701F);
/*     */       
/*  82 */       iIOMetadataNode2 = new IIOMetadataNode("HorizontalPixelSize");
/*  83 */       iIOMetadataNode2.setAttribute("value", str);
/*  84 */       iIOMetadataNode1.appendChild(iIOMetadataNode2);
/*     */     } 
/*     */     
/*  87 */     if (this.page.getResolutionY() != 0) {
/*  88 */       String str = Float.toString(25.4F / this.page.getResolutionY() / 39.3701F);
/*     */       
/*  90 */       iIOMetadataNode2 = new IIOMetadataNode("VerticalPixelSize");
/*  91 */       iIOMetadataNode2.setAttribute("value", str);
/*  92 */       iIOMetadataNode1.appendChild(iIOMetadataNode2);
/*     */     } 
/*     */     
/*  95 */     return iIOMetadataNode1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void mergeTree(String paramString, Node paramNode) {
/* 100 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */ 
/*     */   
/*     */   public void reset() {
/* 105 */     throw new IllegalStateException("Metadata is read-only!");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/levigo/jbig2/JBIG2ImageMetadata.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */