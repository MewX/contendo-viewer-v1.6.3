/*     */ package org.apache.xmlgraphics.xmp;
/*     */ 
/*     */ import java.io.OutputStream;
/*     */ import javax.xml.transform.Result;
/*     */ import javax.xml.transform.Transformer;
/*     */ import javax.xml.transform.TransformerConfigurationException;
/*     */ import javax.xml.transform.sax.SAXTransformerFactory;
/*     */ import javax.xml.transform.sax.TransformerHandler;
/*     */ import javax.xml.transform.stream.StreamResult;
/*     */ import org.xml.sax.SAXException;
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
/*     */ public final class XMPSerializer
/*     */ {
/*     */   private static final String DEFAULT_ENCODING = "UTF-8";
/*     */   
/*     */   public static void writeXML(Metadata meta, Result res) throws TransformerConfigurationException, SAXException {
/*  54 */     writeXML(meta, res, false, false);
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
/*     */   
/*     */   public static void writeXMPPacket(Metadata meta, OutputStream out, boolean readOnlyXMP) throws TransformerConfigurationException, SAXException {
/*  68 */     StreamResult res = new StreamResult(out);
/*  69 */     writeXML(meta, res, true, readOnlyXMP);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void writeXML(Metadata meta, Result res, boolean asXMPPacket, boolean readOnlyXMP) throws TransformerConfigurationException, SAXException {
/*  76 */     SAXTransformerFactory tFactory = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
/*  77 */     TransformerHandler handler = tFactory.newTransformerHandler();
/*  78 */     Transformer transformer = handler.getTransformer();
/*  79 */     if (asXMPPacket) {
/*  80 */       transformer.setOutputProperty("omit-xml-declaration", "yes");
/*     */     }
/*  82 */     transformer.setOutputProperty("encoding", "UTF-8");
/*     */     try {
/*  84 */       transformer.setOutputProperty("indent", "yes");
/*  85 */     } catch (IllegalArgumentException illegalArgumentException) {}
/*     */ 
/*     */     
/*  88 */     handler.setResult(res);
/*  89 */     handler.startDocument();
/*  90 */     if (asXMPPacket) {
/*  91 */       handler.processingInstruction("xpacket", "begin=\"﻿\" id=\"W5M0MpCehiHzreSzNTczkc9d\"");
/*     */     }
/*     */     
/*  94 */     meta.toSAX(handler);
/*  95 */     if (asXMPPacket) {
/*  96 */       if (readOnlyXMP) {
/*  97 */         handler.processingInstruction("xpacket", "end=\"r\"");
/*     */       } else {
/*     */         
/* 100 */         StringBuffer sb = new StringBuffer(101);
/* 101 */         sb.append('\n');
/* 102 */         for (int i = 0; i < 100; i++) {
/* 103 */           sb.append(" ");
/*     */         }
/* 105 */         char[] padding = sb.toString().toCharArray();
/* 106 */         for (int j = 0; j < 40; j++) {
/* 107 */           handler.characters(padding, 0, padding.length);
/*     */         }
/* 109 */         handler.characters(new char[] { '\n' }, 0, 1);
/* 110 */         handler.processingInstruction("xpacket", "end=\"w\"");
/*     */       } 
/*     */     }
/*     */     
/* 114 */     handler.endDocument();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPSerializer.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */