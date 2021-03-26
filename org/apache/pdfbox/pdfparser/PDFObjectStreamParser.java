/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFObjectStreamParser
/*     */   extends BaseParser
/*     */ {
/*  41 */   private static final Log LOG = LogFactory.getLog(PDFObjectStreamParser.class);
/*     */   
/*  43 */   private List<COSObject> streamObjects = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final COSStream stream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFObjectStreamParser(COSStream stream, COSDocument document) throws IOException {
/*  55 */     super(new InputStreamSource((InputStream)stream.createInputStream()));
/*  56 */     this.stream = stream;
/*  57 */     this.document = document;
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
/*     */   public void parse() throws IOException {
/*     */     try {
/*  71 */       int numberOfObjects = this.stream.getInt("N");
/*  72 */       if (numberOfObjects == -1)
/*     */       {
/*  74 */         throw new IOException("/N entry missing in object stream");
/*     */       }
/*  76 */       List<Long> objectNumbers = new ArrayList<Long>(numberOfObjects);
/*  77 */       this.streamObjects = new ArrayList<COSObject>(numberOfObjects);
/*  78 */       for (int i = 0; i < numberOfObjects; i++) {
/*     */         
/*  80 */         long objectNumber = readObjectNumber();
/*     */         
/*  82 */         readLong();
/*  83 */         objectNumbers.add(Long.valueOf(objectNumber));
/*     */       } 
/*     */ 
/*     */       
/*  87 */       int objectCounter = 0; COSBase cosObject;
/*  88 */       while ((cosObject = parseDirObject()) != null)
/*     */       {
/*  90 */         COSObject object = new COSObject(cosObject);
/*  91 */         object.setGenerationNumber(0);
/*  92 */         if (objectCounter >= objectNumbers.size()) {
/*     */           
/*  94 */           LOG.error("/ObjStm (object stream) has more objects than /N " + numberOfObjects);
/*     */           break;
/*     */         } 
/*  97 */         object.setObjectNumber(((Long)objectNumbers.get(objectCounter)).longValue());
/*  98 */         this.streamObjects.add(object);
/*  99 */         if (LOG.isDebugEnabled())
/*     */         {
/* 101 */           LOG.debug("parsed=" + object);
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 106 */         if (!this.seqSource.isEOF() && this.seqSource.peek() == 101)
/*     */         {
/* 108 */           readLine();
/*     */         }
/* 110 */         objectCounter++;
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 115 */       this.seqSource.close();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<COSObject> getObjects() {
/* 126 */     return this.streamObjects;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/PDFObjectStreamParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */