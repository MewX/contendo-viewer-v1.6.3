/*     */ package org.apache.xmlgraphics.xmp;
/*     */ 
/*     */ import java.io.BufferedInputStream;
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.io.UnsupportedEncodingException;
/*     */ import javax.xml.transform.TransformerException;
/*     */ import javax.xml.transform.stream.StreamSource;
/*     */ import org.apache.commons.io.IOUtils;
/*     */ import org.apache.commons.io.output.ByteArrayOutputStream;
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
/*     */ public final class XMPPacketParser
/*     */ {
/*     */   private static final byte[] PACKET_HEADER;
/*     */   private static final byte[] PACKET_HEADER_END;
/*     */   private static final byte[] PACKET_TRAILER;
/*     */   
/*     */   static {
/*     */     try {
/*  52 */       PACKET_HEADER = "<?xpacket begin=".getBytes("US-ASCII");
/*  53 */       PACKET_HEADER_END = "?>".getBytes("US-ASCII");
/*  54 */       PACKET_TRAILER = "<?xpacket".getBytes("US-ASCII");
/*  55 */     } catch (UnsupportedEncodingException e) {
/*  56 */       throw new RuntimeException("Incompatible JVM! US-ASCII encoding not supported.");
/*     */     } 
/*     */   }
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
/*     */   public static Metadata parse(InputStream in) throws IOException, TransformerException {
/*     */     Metadata metadata;
/*  71 */     if (!in.markSupported()) {
/*  72 */       in = new BufferedInputStream(in);
/*     */     }
/*  74 */     boolean foundXMP = skipAfter(in, PACKET_HEADER);
/*  75 */     if (!foundXMP) {
/*  76 */       return null;
/*     */     }
/*     */     
/*  79 */     if (!skipAfter(in, PACKET_HEADER_END)) {
/*  80 */       throw new IOException("Invalid XMP packet header!");
/*     */     }
/*  82 */     ByteArrayOutputStream baout = null;
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  87 */       baout = new ByteArrayOutputStream();
/*     */       
/*  89 */       if (!skipAfter(in, PACKET_TRAILER, (OutputStream)baout)) {
/*  90 */         throw new IOException("XMP packet not properly terminated!");
/*     */       }
/*  92 */       metadata = XMPParser.parseXMP(new StreamSource(new ByteArrayInputStream(baout.toByteArray())));
/*     */     } finally {
/*     */       
/*  95 */       IOUtils.closeQuietly((OutputStream)baout);
/*     */     } 
/*  97 */     return metadata;
/*     */   }
/*     */   
/*     */   private static boolean skipAfter(InputStream in, byte[] match) throws IOException {
/* 101 */     return skipAfter(in, match, null);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean skipAfter(InputStream in, byte[] match, OutputStream out) throws IOException {
/* 106 */     int found = 0;
/* 107 */     int len = match.length;
/*     */     int b;
/* 109 */     while ((b = in.read()) >= 0) {
/* 110 */       if (b == match[found]) {
/* 111 */         found++;
/* 112 */         if (found == len)
/* 113 */           return true; 
/*     */         continue;
/*     */       } 
/* 116 */       if (out != null) {
/* 117 */         if (found > 0) {
/* 118 */           out.write(match, 0, found);
/*     */         }
/* 120 */         out.write(b);
/*     */       } 
/* 122 */       found = 0;
/*     */     } 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPPacketParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */