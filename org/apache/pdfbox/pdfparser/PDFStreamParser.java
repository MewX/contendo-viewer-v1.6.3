/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.contentstream.PDContentStream;
/*     */ import org.apache.pdfbox.contentstream.operator.Operator;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNull;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFStreamParser
/*     */   extends BaseParser
/*     */ {
/*  49 */   private static final Log LOG = LogFactory.getLog(PDFStreamParser.class);
/*     */   
/*  51 */   private final List<Object> streamObjects = new ArrayList(100);
/*     */   
/*     */   private static final int MAX_BIN_CHAR_TEST_LENGTH = 10;
/*  54 */   private final byte[] binCharTestArr = new byte[10];
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public PDFStreamParser(PDStream stream) throws IOException {
/*  67 */     super(new InputStreamSource((InputStream)stream.createInputStream()));
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
/*     */   @Deprecated
/*     */   public PDFStreamParser(COSStream stream) throws IOException {
/*  81 */     super(new InputStreamSource((InputStream)stream.createInputStream()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFStreamParser(PDContentStream contentStream) throws IOException {
/*  92 */     super(new InputStreamSource(contentStream.getContents()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFStreamParser(byte[] bytes) throws IOException {
/* 103 */     super(new InputStreamSource(new ByteArrayInputStream(bytes)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void parse() throws IOException {
/*     */     Object token;
/* 115 */     while ((token = parseNextToken()) != null)
/*     */     {
/* 117 */       this.streamObjects.add(token);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Object> getTokens() {
/* 128 */     return this.streamObjects;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Object parseNextToken() throws IOException {
/*     */     Object retval;
/*     */     int leftBracket;
/*     */     String nullString, str1, line;
/*     */     StringBuilder buf;
/*     */     String next, id;
/*     */     boolean dotNotRead;
/*     */     ByteArrayOutputStream imageData;
/*     */     int lastByte, currentByte;
/* 142 */     skipSpaces();
/* 143 */     int nextByte = this.seqSource.peek();
/* 144 */     if ((byte)nextByte == -1)
/*     */     {
/* 146 */       return null;
/*     */     }
/* 148 */     char c = (char)nextByte;
/* 149 */     switch (c)
/*     */     
/*     */     { 
/*     */       
/*     */       case '<':
/* 154 */         leftBracket = this.seqSource.read();
/*     */ 
/*     */         
/* 157 */         c = (char)this.seqSource.peek();
/*     */ 
/*     */         
/* 160 */         this.seqSource.unread(leftBracket);
/*     */         
/* 162 */         if (c == '<') {
/*     */           
/* 164 */           retval = parseCOSDictionary();
/*     */         }
/*     */         else {
/*     */           
/* 168 */           retval = parseCOSString();
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 366 */         return retval;case '[': retval = parseCOSArray(); return retval;case '(': retval = parseCOSString(); return retval;case '/': retval = parseCOSName(); return retval;case 'n': nullString = readString(); if (nullString.equals("null")) { retval = COSNull.NULL; } else { retval = Operator.getOperator(nullString); }  return retval;case 'f': case 't': str1 = readString(); if (str1.equals("true")) { retval = COSBoolean.TRUE; } else if (str1.equals("false")) { retval = COSBoolean.FALSE; } else { retval = Operator.getOperator(str1); }  return retval;case 'R': line = readString(); if (line.equals("R")) { retval = new COSObject(null); } else { retval = Operator.getOperator(line); }  return retval;case '+': case '-': case '.': case '0': case '1': case '2': case '3': case '4': case '5': case '6': case '7': case '8': case '9': buf = new StringBuilder(); buf.append(c); this.seqSource.read(); if (c == '-' && this.seqSource.peek() == c) this.seqSource.read();  dotNotRead = (c != '.'); while (Character.isDigit(c = (char)this.seqSource.peek()) || (dotNotRead && c == '.') || c == '-') { if (c != '-') buf.append(c);  this.seqSource.read(); if (dotNotRead && c == '.') dotNotRead = false;  }  retval = COSNumber.get(buf.toString()); return retval;case 'B': next = readString(); retval = Operator.getOperator(next); if (next.equals("BI")) { Operator beginImageOP = (Operator)retval; COSDictionary imageParams = new COSDictionary(); beginImageOP.setImageParameters(imageParams); Object nextToken = null; while (nextToken = parseNextToken() instanceof COSName) { Object value = parseNextToken(); imageParams.setItem((COSName)nextToken, (COSBase)value); }  if (nextToken instanceof Operator) { Operator operator1 = (Operator)nextToken; if (operator1.getImageData() == null || (operator1.getImageData()).length == 0) LOG.warn("empty inline image at stream offset " + this.seqSource.getPosition());  beginImageOP.setImageData(operator1.getImageData()); }  }  return retval;case 'I': id = "" + (char)this.seqSource.read() + (char)this.seqSource.read(); if (!id.equals("ID")) throw new IOException("Error: Expected operator 'ID' actual='" + id + "' at stream offset " + this.seqSource.getPosition());  imageData = new ByteArrayOutputStream(); if (isWhitespace()) this.seqSource.read();  lastByte = this.seqSource.read(); currentByte = this.seqSource.read(); while ((lastByte != 69 || currentByte != 73 || !hasNextSpaceOrReturn() || !hasNoFollowingBinData(this.seqSource)) && !this.seqSource.isEOF()) { imageData.write(lastByte); lastByte = currentByte; currentByte = this.seqSource.read(); }  retval = Operator.getOperator("ID"); ((Operator)retval).setImageData(imageData.toByteArray()); return retval;case ']': this.seqSource.read(); retval = COSNull.NULL; return retval; }  String operator = readOperator(); if (operator.trim().length() == 0) { retval = null; } else { retval = Operator.getOperator(operator); }  return retval;
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
/*     */   private boolean hasNoFollowingBinData(SequentialSource pdfSource) throws IOException {
/* 380 */     int readBytes = pdfSource.read(this.binCharTestArr, 0, 10);
/* 381 */     boolean noBinData = true;
/* 382 */     int startOpIdx = -1;
/* 383 */     int endOpIdx = -1;
/*     */     
/* 385 */     if (readBytes > 0) {
/*     */       
/* 387 */       for (int bIdx = 0; bIdx < readBytes; bIdx++) {
/*     */         
/* 389 */         byte b = this.binCharTestArr[bIdx];
/* 390 */         if ((b != 0 && b < 9) || (b > 10 && b < 32 && b != 13)) {
/*     */ 
/*     */           
/* 393 */           noBinData = false;
/*     */           
/*     */           break;
/*     */         } 
/* 397 */         if (startOpIdx == -1 && b != 0 && b != 9 && b != 32 && b != 10 && b != 13) {
/*     */           
/* 399 */           startOpIdx = bIdx;
/*     */         }
/* 401 */         else if (startOpIdx != -1 && endOpIdx == -1 && (b == 0 || b == 9 || b == 32 || b == 10 || b == 13)) {
/*     */ 
/*     */           
/* 404 */           endOpIdx = bIdx;
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 409 */       if (endOpIdx != -1 && startOpIdx != -1) {
/*     */ 
/*     */         
/* 412 */         String s = new String(this.binCharTestArr, startOpIdx, endOpIdx - startOpIdx);
/* 413 */         if (!"Q".equals(s) && !"EMC".equals(s) && !"S".equals(s))
/*     */         {
/* 415 */           noBinData = false;
/*     */         }
/*     */       } 
/*     */ 
/*     */       
/* 420 */       if (readBytes == 10) {
/*     */ 
/*     */         
/* 423 */         if (startOpIdx != -1 && endOpIdx == -1)
/*     */         {
/* 425 */           endOpIdx = 10;
/*     */         }
/* 427 */         if (endOpIdx != -1 && startOpIdx != -1 && endOpIdx - startOpIdx > 3)
/*     */         {
/* 429 */           noBinData = false;
/*     */         }
/*     */       } 
/* 432 */       pdfSource.unread(this.binCharTestArr, 0, readBytes);
/*     */     } 
/* 434 */     if (!noBinData)
/*     */     {
/* 436 */       LOG.warn("ignoring 'EI' assumed to be in the middle of inline image at stream offset " + pdfSource
/* 437 */           .getPosition());
/*     */     }
/* 439 */     return noBinData;
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
/*     */   protected String readOperator() throws IOException {
/* 451 */     skipSpaces();
/*     */ 
/*     */ 
/*     */     
/* 455 */     StringBuilder buffer = new StringBuilder(4);
/* 456 */     int nextChar = this.seqSource.peek();
/* 457 */     while (nextChar != -1 && 
/*     */       
/* 459 */       !isWhitespace(nextChar) && 
/* 460 */       !isClosing(nextChar) && nextChar != 91 && nextChar != 60 && nextChar != 40 && nextChar != 47 && (nextChar < 48 || nextChar > 57)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 468 */       char currentChar = (char)this.seqSource.read();
/* 469 */       nextChar = this.seqSource.peek();
/* 470 */       buffer.append(currentChar);
/*     */       
/* 472 */       if (currentChar == 'd' && (nextChar == 48 || nextChar == 49)) {
/*     */         
/* 474 */         buffer.append((char)this.seqSource.read());
/* 475 */         nextChar = this.seqSource.peek();
/*     */       } 
/*     */     } 
/* 478 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean isSpaceOrReturn(int c) {
/* 484 */     return (c == 10 || c == 13 || c == 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private boolean hasNextSpaceOrReturn() throws IOException {
/* 495 */     return isSpaceOrReturn(this.seqSource.peek());
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/PDFStreamParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */