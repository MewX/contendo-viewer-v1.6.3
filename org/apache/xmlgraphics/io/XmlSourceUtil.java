/*     */ package org.apache.xmlgraphics.io;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.Reader;
/*     */ import javax.xml.transform.Source;
/*     */ import javax.xml.transform.TransformerFactory;
/*     */ import javax.xml.transform.sax.SAXSource;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.xmlgraphics.image.loader.ImageSource;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageInputStreamAdapter;
/*     */ import org.apache.xmlgraphics.image.loader.util.ImageUtil;
/*     */ import org.xml.sax.InputSource;
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
/*     */ public final class XmlSourceUtil
/*     */ {
/*     */   public static InputStream getInputStream(Source src) {
/*     */     try {
/*  58 */       if (src instanceof StreamSource)
/*  59 */         return ((StreamSource)src).getInputStream(); 
/*  60 */       if (src instanceof javax.xml.transform.dom.DOMSource) {
/*  61 */         ByteArrayOutputStream outStream = new ByteArrayOutputStream();
/*  62 */         StreamResult xmlSource = new StreamResult(outStream);
/*  63 */         TransformerFactory.newInstance().newTransformer().transform(src, xmlSource);
/*  64 */         return new ByteArrayInputStream(outStream.toByteArray());
/*  65 */       }  if (src instanceof SAXSource)
/*  66 */         return ((SAXSource)src).getInputSource().getByteStream(); 
/*  67 */       if (src instanceof ImageSource) {
/*  68 */         return (InputStream)new ImageInputStreamAdapter(((ImageSource)src).getImageInputStream());
/*     */       }
/*  70 */     } catch (Exception exception) {}
/*     */ 
/*     */     
/*  73 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static InputStream needInputStream(Source src) {
/*  83 */     InputStream in = getInputStream(src);
/*  84 */     if (in != null) {
/*  85 */       return in;
/*     */     }
/*  87 */     throw new IllegalArgumentException("Source must be a StreamSource with an InputStream or an ImageSource");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasReader(Source src) {
/*  98 */     if (src instanceof StreamSource) {
/*  99 */       Reader reader = ((StreamSource)src).getReader();
/* 100 */       return (reader != null);
/* 101 */     }  if (src instanceof SAXSource) {
/* 102 */       InputSource is = ((SAXSource)src).getInputSource();
/* 103 */       if (is != null) {
/* 104 */         return (is.getCharacterStream() != null);
/*     */       }
/*     */     } 
/* 107 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void removeStreams(Source src) {
/* 116 */     if (src instanceof ImageSource) {
/* 117 */       ImageSource isrc = (ImageSource)src;
/* 118 */       isrc.setImageInputStream(null);
/* 119 */     } else if (src instanceof StreamSource) {
/* 120 */       StreamSource ssrc = (StreamSource)src;
/* 121 */       ssrc.setInputStream(null);
/* 122 */       ssrc.setReader(null);
/* 123 */     } else if (src instanceof SAXSource) {
/* 124 */       InputSource is = ((SAXSource)src).getInputSource();
/* 125 */       if (is != null) {
/* 126 */         is.setByteStream(null);
/* 127 */         is.setCharacterStream(null);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void closeQuietly(Source src) {
/* 138 */     if (src instanceof StreamSource) {
/* 139 */       StreamSource streamSource = (StreamSource)src;
/* 140 */       IOUtils.closeQuietly(streamSource.getReader());
/* 141 */     } else if (src instanceof ImageSource) {
/* 142 */       if (ImageUtil.getImageInputStream(src) != null) {
/*     */         try {
/* 144 */           ImageUtil.getImageInputStream(src).close();
/* 145 */         } catch (IOException iOException) {}
/*     */       
/*     */       }
/*     */     }
/* 149 */     else if (src instanceof SAXSource) {
/* 150 */       InputSource is = ((SAXSource)src).getInputSource();
/* 151 */       if (is != null) {
/* 152 */         IOUtils.closeQuietly(is.getByteStream());
/* 153 */         IOUtils.closeQuietly(is.getCharacterStream());
/*     */       } 
/*     */     } 
/* 156 */     removeStreams(src);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean hasInputStream(Source src) {
/* 165 */     return (getInputStream(src) != null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/io/XmlSourceUtil.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */