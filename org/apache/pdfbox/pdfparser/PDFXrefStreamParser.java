/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObjectKey;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFXrefStreamParser
/*     */   extends BaseParser
/*     */ {
/*     */   private final COSStream stream;
/*     */   private final XrefTrailerResolver xrefTrailerResolver;
/*     */   
/*     */   public PDFXrefStreamParser(COSStream stream, COSDocument document, XrefTrailerResolver resolver) throws IOException {
/*  56 */     super(new InputStreamSource((InputStream)stream.createInputStream()));
/*  57 */     this.stream = stream;
/*  58 */     this.document = document;
/*  59 */     this.xrefTrailerResolver = resolver;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parse() throws IOException {
/*     */     COSArray indexArray;
/*  68 */     COSBase w = this.stream.getDictionaryObject(COSName.W);
/*  69 */     if (!(w instanceof COSArray))
/*     */     {
/*  71 */       throw new IOException("/W array is missing in Xref stream");
/*     */     }
/*  73 */     COSArray xrefFormat = (COSArray)w;
/*     */     
/*  75 */     COSBase base = this.stream.getDictionaryObject(COSName.INDEX);
/*     */     
/*  77 */     if (base instanceof COSArray) {
/*     */       
/*  79 */       indexArray = (COSArray)base;
/*     */     
/*     */     }
/*     */     else {
/*     */       
/*  84 */       indexArray = new COSArray();
/*  85 */       indexArray.add((COSBase)COSInteger.ZERO);
/*  86 */       indexArray.add((COSBase)COSInteger.get(this.stream.getInt(COSName.SIZE, 0)));
/*     */     } 
/*     */     
/*  89 */     List<Long> objNums = new ArrayList<Long>();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  94 */     Iterator<COSBase> indexIter = indexArray.iterator();
/*  95 */     while (indexIter.hasNext()) {
/*     */       
/*  97 */       base = indexIter.next();
/*  98 */       if (!(base instanceof COSInteger))
/*     */       {
/* 100 */         throw new IOException("Xref stream must have integer in /Index array");
/*     */       }
/* 102 */       long objID = ((COSInteger)base).longValue();
/* 103 */       if (!indexIter.hasNext()) {
/*     */         break;
/*     */       }
/*     */       
/* 107 */       base = indexIter.next();
/* 108 */       if (!(base instanceof COSInteger))
/*     */       {
/* 110 */         throw new IOException("Xref stream must have integer in /Index array");
/*     */       }
/* 112 */       int size = ((COSInteger)base).intValue();
/* 113 */       for (int i = 0; i < size; i++)
/*     */       {
/* 115 */         objNums.add(Long.valueOf(objID + i));
/*     */       }
/*     */     } 
/* 118 */     Iterator<Long> objIter = objNums.iterator();
/*     */ 
/*     */ 
/*     */     
/* 122 */     int w0 = xrefFormat.getInt(0, 0);
/* 123 */     int w1 = xrefFormat.getInt(1, 0);
/* 124 */     int w2 = xrefFormat.getInt(2, 0);
/* 125 */     int lineSize = w0 + w1 + w2;
/*     */     
/* 127 */     while (!this.seqSource.isEOF() && objIter.hasNext()) {
/*     */       int type, offset, i, genNum, j; COSObjectKey objKey; int objstmObjNr, k;
/* 129 */       byte[] currLine = new byte[lineSize];
/* 130 */       this.seqSource.read(currLine);
/*     */ 
/*     */       
/* 133 */       if (w0 == 0) {
/*     */ 
/*     */ 
/*     */         
/* 137 */         type = 1;
/*     */       }
/*     */       else {
/*     */         
/* 141 */         type = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 146 */         for (int m = 0; m < w0; m++)
/*     */         {
/* 148 */           type += (currLine[m] & 0xFF) << (w0 - m - 1) * 8;
/*     */         }
/*     */       } 
/*     */       
/* 152 */       Long objID = objIter.next();
/*     */ 
/*     */ 
/*     */       
/* 156 */       switch (type) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 1:
/* 164 */           offset = 0;
/* 165 */           for (i = 0; i < w1; i++)
/*     */           {
/* 167 */             offset += (currLine[i + w0] & 0xFF) << (w1 - i - 1) * 8;
/*     */           }
/* 169 */           genNum = 0;
/* 170 */           for (j = 0; j < w2; j++)
/*     */           {
/* 172 */             genNum += (currLine[j + w0 + w1] & 0xFF) << (w2 - j - 1) * 8;
/*     */           }
/* 174 */           objKey = new COSObjectKey(objID.longValue(), genNum);
/* 175 */           this.xrefTrailerResolver.setXRef(objKey, offset);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/*     */         case 2:
/* 193 */           objstmObjNr = 0;
/* 194 */           for (k = 0; k < w1; k++)
/*     */           {
/* 196 */             objstmObjNr += (currLine[k + w0] & 0xFF) << (w1 - k - 1) * 8;
/*     */           }
/* 198 */           objKey = new COSObjectKey(objID.longValue(), 0);
/* 199 */           this.xrefTrailerResolver.setXRef(objKey, -objstmObjNr);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/PDFXrefStreamParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */