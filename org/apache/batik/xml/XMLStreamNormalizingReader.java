/*     */ package org.apache.batik.xml;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.PushbackInputStream;
/*     */ import java.io.Reader;
/*     */ import org.apache.batik.util.io.CharDecoder;
/*     */ import org.apache.batik.util.io.StreamNormalizingReader;
/*     */ import org.apache.batik.util.io.UTF16Decoder;
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
/*     */ public class XMLStreamNormalizingReader
/*     */   extends StreamNormalizingReader
/*     */ {
/*     */   public XMLStreamNormalizingReader(InputStream is, String encod) throws IOException {
/*  45 */     PushbackInputStream pbis = new PushbackInputStream(is, 128);
/*  46 */     byte[] buf = new byte[4];
/*     */     
/*  48 */     int len = pbis.read(buf);
/*  49 */     if (len > 0) {
/*  50 */       pbis.unread(buf, 0, len);
/*     */     }
/*     */     
/*  53 */     if (len == 4) {
/*  54 */       switch (buf[0] & 0xFF) {
/*     */         case 0:
/*  56 */           if (buf[1] == 60 && buf[2] == 0 && buf[3] == 63) {
/*  57 */             this.charDecoder = (CharDecoder)new UTF16Decoder(pbis, true);
/*     */             return;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 60:
/*  63 */           switch (buf[1] & 0xFF) {
/*     */             case 0:
/*  65 */               if (buf[2] == 63 && buf[3] == 0) {
/*  66 */                 this.charDecoder = (CharDecoder)new UTF16Decoder(pbis, false);
/*     */                 return;
/*     */               } 
/*     */               break;
/*     */             
/*     */             case 63:
/*  72 */               if (buf[2] == 120 && buf[3] == 109) {
/*  73 */                 Reader r = XMLUtilities.createXMLDeclarationReader(pbis, "UTF8");
/*     */                 
/*  75 */                 String enc = XMLUtilities.getXMLDeclarationEncoding(r, "UTF-8");
/*     */                 
/*  77 */                 this.charDecoder = createCharDecoder(pbis, enc);
/*     */                 return;
/*     */               } 
/*     */               break;
/*     */           } 
/*     */           break;
/*     */         case 76:
/*  84 */           if (buf[1] == 111 && (buf[2] & 0xFF) == 167 && (buf[3] & 0xFF) == 148) {
/*     */ 
/*     */             
/*  87 */             Reader r = XMLUtilities.createXMLDeclarationReader(pbis, "CP037");
/*     */             
/*  89 */             String enc = XMLUtilities.getXMLDeclarationEncoding(r, "EBCDIC-CP-US");
/*     */             
/*  91 */             this.charDecoder = createCharDecoder(pbis, enc);
/*     */             return;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 254:
/*  97 */           if ((buf[1] & 0xFF) == 255) {
/*  98 */             this.charDecoder = createCharDecoder(pbis, "UTF-16");
/*     */             return;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 255:
/* 104 */           if ((buf[1] & 0xFF) == 254) {
/* 105 */             this.charDecoder = createCharDecoder(pbis, "UTF-16");
/*     */             return;
/*     */           } 
/*     */           break;
/*     */       } 
/*     */     }
/* 111 */     encod = (encod == null) ? "UTF-8" : encod;
/* 112 */     this.charDecoder = createCharDecoder(pbis, encod);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/xml/XMLStreamNormalizingReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */