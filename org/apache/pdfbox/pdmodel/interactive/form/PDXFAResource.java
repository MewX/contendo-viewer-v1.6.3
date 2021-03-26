/*     */ package org.apache.pdfbox.pdmodel.interactive.form;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.xml.parsers.ParserConfigurationException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSInputStream;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.util.XMLUtil;
/*     */ import org.w3c.dom.Document;
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
/*     */ public final class PDXFAResource
/*     */   implements COSObjectable
/*     */ {
/*     */   private static final int BUFFER_SIZE = 1024;
/*     */   private final COSBase xfa;
/*     */   
/*     */   public PDXFAResource(COSBase xfaBase) {
/*  55 */     this.xfa = xfaBase;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSBase getCOSObject() {
/*  64 */     return this.xfa;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getBytes() throws IOException {
/*     */     COSInputStream cOSInputStream;
/*  87 */     ByteArrayOutputStream baos = new ByteArrayOutputStream();
/*  88 */     InputStream is = null;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  94 */       if (getCOSObject() instanceof COSArray) {
/*     */         
/*  96 */         byte[] xfaBytes = new byte[1024];
/*  97 */         COSArray cosArray = (COSArray)getCOSObject();
/*  98 */         for (int i = 1; i < cosArray.size(); i += 2)
/*     */         {
/* 100 */           COSBase cosObj = cosArray.getObject(i);
/* 101 */           if (cosObj instanceof COSStream)
/*     */           {
/* 103 */             cOSInputStream = ((COSStream)cosObj).createInputStream();
/*     */             int nRead;
/* 105 */             while ((nRead = cOSInputStream.read(xfaBytes, 0, xfaBytes.length)) != -1)
/*     */             {
/* 107 */               baos.write(xfaBytes, 0, nRead);
/*     */             }
/* 109 */             baos.flush();
/*     */           }
/*     */         
/*     */         }
/*     */       
/* 114 */       } else if (this.xfa.getCOSObject() instanceof COSStream) {
/*     */         
/* 116 */         byte[] xfaBytes = new byte[1024];
/* 117 */         cOSInputStream = ((COSStream)this.xfa.getCOSObject()).createInputStream();
/*     */         int nRead;
/* 119 */         while ((nRead = cOSInputStream.read(xfaBytes, 0, xfaBytes.length)) != -1)
/*     */         {
/* 121 */           baos.write(xfaBytes, 0, nRead);
/*     */         }
/* 123 */         baos.flush();
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 128 */       if (cOSInputStream != null)
/*     */       {
/* 130 */         cOSInputStream.close();
/*     */       }
/*     */     } 
/* 133 */     return baos.toByteArray();
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
/*     */ 
/*     */ 
/*     */   
/*     */   public Document getDocument() throws ParserConfigurationException, SAXException, IOException {
/* 150 */     return 
/* 151 */       XMLUtil.parse(new ByteArrayInputStream(getBytes()), true);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/form/PDXFAResource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */