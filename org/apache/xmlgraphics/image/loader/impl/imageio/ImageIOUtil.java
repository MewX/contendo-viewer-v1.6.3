/*     */ package org.apache.xmlgraphics.image.loader.impl.imageio;
/*     */ 
/*     */ import javax.imageio.metadata.IIOMetadata;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.dom.DOMSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSize;
/*     */ import org.w3c.dom.Element;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.NodeList;
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
/*     */ public final class ImageIOUtil
/*     */ {
/*  50 */   public static final Object IMAGEIO_METADATA = IIOMetadata.class;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void extractResolution(IIOMetadata iiometa, ImageSize size) {
/*  58 */     if (iiometa != null && iiometa.isStandardMetadataFormatSupported()) {
/*  59 */       Element metanode = (Element)iiometa.getAsTree("javax_imageio_1.0");
/*     */       
/*  61 */       Element dim = getChild(metanode, "Dimension");
/*  62 */       if (dim != null) {
/*     */         
/*  64 */         double dpiHorz = size.getDpiHorizontal();
/*  65 */         double dpiVert = size.getDpiVertical();
/*  66 */         Element child = getChild(dim, "HorizontalPixelSize");
/*  67 */         if (child != null) {
/*  68 */           float value = Float.parseFloat(child.getAttribute("value"));
/*  69 */           if (value != 0.0F && !Float.isInfinite(value)) {
/*  70 */             dpiHorz = (25.4F / value);
/*     */           }
/*     */         } 
/*  73 */         child = getChild(dim, "VerticalPixelSize");
/*  74 */         if (child != null) {
/*  75 */           float value = Float.parseFloat(child.getAttribute("value"));
/*  76 */           if (value != 0.0F && !Float.isInfinite(value)) {
/*  77 */             dpiVert = (25.4F / value);
/*     */           }
/*     */         } 
/*  80 */         size.setResolution(dpiHorz, dpiVert);
/*  81 */         size.calcSizeFromPixels();
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Element getChild(Element el, String name) {
/*  93 */     NodeList nodes = el.getElementsByTagName(name);
/*  94 */     if (nodes.getLength() > 0) {
/*  95 */       return (Element)nodes.item(0);
/*     */     }
/*  97 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void dumpMetadataToSystemOut(IIOMetadata iiometa) {
/* 106 */     String[] metanames = iiometa.getMetadataFormatNames();
/* 107 */     for (int j = 0; j < metanames.length; j++) {
/* 108 */       System.out.println("--->" + metanames[j]);
/* 109 */       dumpNodeToSystemOut(iiometa.getAsTree(metanames[j]));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void dumpNodeToSystemOut(Node node) {
/* 118 */     Transformer trans = null;
/*     */     try {
/* 120 */       trans = TransformerFactory.newInstance().newTransformer();
/* 121 */       trans.setOutputProperty("omit-xml-declaration", "yes");
/* 122 */       trans.setOutputProperty("indent", "yes");
/* 123 */       Source src = new DOMSource(node);
/* 124 */       Result res = new StreamResult(System.out);
/* 125 */       trans.transform(src, res);
/* 126 */     } catch (TransformerConfigurationException e) {
/* 127 */       e.printStackTrace();
/* 128 */     } catch (TransformerException e) {
/* 129 */       e.printStackTrace();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/image/loader/impl/imageio/ImageIOUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */