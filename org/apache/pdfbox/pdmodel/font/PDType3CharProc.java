/*     */ package org.apache.pdfbox.pdmodel.font;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.contentstream.PDContentStream;
/*     */ import org.apache.pdfbox.contentstream.operator.Operator;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdfparser.PDFStreamParser;
/*     */ import org.apache.pdfbox.pdmodel.PDResources;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRectangle;
/*     */ import org.apache.pdfbox.pdmodel.common.PDStream;
/*     */ import org.apache.pdfbox.util.Matrix;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PDType3CharProc
/*     */   implements PDContentStream, COSObjectable
/*     */ {
/*     */   private final PDType3Font font;
/*     */   private final COSStream charStream;
/*     */   
/*     */   public PDType3CharProc(PDType3Font font, COSStream charStream) {
/*  49 */     this.font = font;
/*  50 */     this.charStream = charStream;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStream getCOSObject() {
/*  56 */     return this.charStream;
/*     */   }
/*     */ 
/*     */   
/*     */   public PDType3Font getFont() {
/*  61 */     return this.font;
/*     */   }
/*     */ 
/*     */   
/*     */   public PDStream getContentStream() {
/*  66 */     return new PDStream(this.charStream);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public InputStream getContents() throws IOException {
/*  72 */     return (InputStream)this.charStream.createInputStream();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDResources getResources() {
/*  78 */     return this.font.getResources();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public PDRectangle getBBox() {
/*  84 */     return this.font.getFontBBox();
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
/*     */   public PDRectangle getGlyphBBox() throws IOException {
/*  96 */     List<COSBase> arguments = new ArrayList<COSBase>();
/*  97 */     PDFStreamParser parser = new PDFStreamParser(this);
/*  98 */     Object token = parser.parseNextToken();
/*  99 */     while (token != null) {
/*     */       
/* 101 */       if (token instanceof COSObject) {
/*     */         
/* 103 */         arguments.add(((COSObject)token).getObject());
/*     */       } else {
/* 105 */         if (token instanceof Operator) {
/*     */           
/* 107 */           if (((Operator)token).getName().equals("d1") && arguments.size() == 6) {
/*     */             
/* 109 */             for (int i = 0; i < 6; i++) {
/*     */               
/* 111 */               if (!(arguments.get(i) instanceof COSNumber))
/*     */               {
/* 113 */                 return null;
/*     */               }
/*     */             } 
/* 116 */             return new PDRectangle(((COSNumber)arguments
/* 117 */                 .get(2)).floatValue(), ((COSNumber)arguments
/* 118 */                 .get(3)).floatValue(), ((COSNumber)arguments
/* 119 */                 .get(4)).floatValue() - ((COSNumber)arguments.get(2)).floatValue(), ((COSNumber)arguments
/* 120 */                 .get(5)).floatValue() - ((COSNumber)arguments.get(3)).floatValue());
/*     */           } 
/*     */ 
/*     */           
/* 124 */           return null;
/*     */         } 
/*     */ 
/*     */ 
/*     */         
/* 129 */         arguments.add((COSBase)token);
/*     */       } 
/* 131 */       token = parser.parseNextToken();
/*     */     } 
/* 133 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Matrix getMatrix() {
/* 139 */     return this.font.getFontMatrix();
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
/*     */   public float getWidth() throws IOException {
/* 151 */     List<COSBase> arguments = new ArrayList<COSBase>();
/* 152 */     PDFStreamParser parser = new PDFStreamParser(this);
/* 153 */     Object token = parser.parseNextToken();
/* 154 */     while (token != null) {
/*     */       
/* 156 */       if (token instanceof COSObject) {
/*     */         
/* 158 */         arguments.add(((COSObject)token).getObject());
/*     */       } else {
/* 160 */         if (token instanceof Operator)
/*     */         {
/* 162 */           return parseWidth((Operator)token, arguments);
/*     */         }
/*     */ 
/*     */         
/* 166 */         arguments.add((COSBase)token);
/*     */       } 
/* 168 */       token = parser.parseNextToken();
/*     */     } 
/* 170 */     throw new IOException("Unexpected end of stream");
/*     */   }
/*     */ 
/*     */   
/*     */   private float parseWidth(Operator operator, List<COSBase> arguments) throws IOException {
/* 175 */     if (operator.getName().equals("d0") || operator.getName().equals("d1")) {
/*     */       
/* 177 */       COSBase obj = arguments.get(0);
/* 178 */       if (obj instanceof COSNumber)
/*     */       {
/* 180 */         return ((COSNumber)obj).floatValue();
/*     */       }
/* 182 */       throw new IOException("Unexpected argument type: " + obj.getClass().getName());
/*     */     } 
/*     */ 
/*     */     
/* 186 */     throw new IOException("First operator must be d0 or d1");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/PDType3CharProc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */