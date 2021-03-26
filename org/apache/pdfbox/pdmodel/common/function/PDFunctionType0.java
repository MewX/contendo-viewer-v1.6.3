/*     */ package org.apache.pdfbox.pdmodel.common.function;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import javax.imageio.stream.ImageInputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageInputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.pdmodel.common.PDRange;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDFunctionType0
/*     */   extends PDFunction
/*     */ {
/*  44 */   private static final Log LOG = LogFactory.getLog(PDFunctionType0.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   private COSArray encode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   private COSArray decode = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   private COSArray size = null;
/*     */ 
/*     */ 
/*     */   
/*  66 */   private int[][] samples = (int[][])null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFunctionType0(COSBase function) {
/*  75 */     super(function);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getFunctionType() {
/*  84 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSArray getSize() {
/*  95 */     if (this.size == null)
/*     */     {
/*  97 */       this.size = (COSArray)getCOSObject().getDictionaryObject(COSName.SIZE);
/*     */     }
/*  99 */     return this.size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[][] getSamples() {
/* 109 */     if (this.samples == null) {
/*     */       
/* 111 */       int arraySize = 1;
/* 112 */       int numberOfInputValues = getNumberOfInputParameters();
/* 113 */       int numberOfOutputValues = getNumberOfOutputParameters();
/* 114 */       COSArray sizes = getSize();
/* 115 */       for (int i = 0; i < numberOfInputValues; i++)
/*     */       {
/* 117 */         arraySize *= sizes.getInt(i);
/*     */       }
/* 119 */       this.samples = new int[arraySize][numberOfOutputValues];
/* 120 */       int bitsPerSample = getBitsPerSample();
/* 121 */       int index = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 127 */         ImageInputStream mciis = new MemoryCacheImageInputStream((InputStream)getPDStream().createInputStream());
/* 128 */         for (int j = 0; j < arraySize; j++) {
/*     */           
/* 130 */           for (int k = 0; k < numberOfOutputValues; k++)
/*     */           {
/*     */             
/* 133 */             this.samples[index][k] = (int)mciis.readBits(bitsPerSample);
/*     */           }
/* 135 */           index++;
/*     */         } 
/* 137 */         mciis.close();
/*     */       }
/* 139 */       catch (IOException exception) {
/*     */         
/* 141 */         LOG.error("IOException while reading the sample values of this function.", exception);
/*     */       } 
/*     */     } 
/* 144 */     return this.samples;
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
/*     */   public int getBitsPerSample() {
/* 156 */     return getCOSObject().getInt(COSName.BITS_PER_SAMPLE);
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
/*     */   public int getOrder() {
/* 168 */     return getCOSObject().getInt(COSName.ORDER, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBitsPerSample(int bps) {
/* 179 */     getCOSObject().setInt(COSName.BITS_PER_SAMPLE, bps);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSArray getEncodeValues() {
/* 189 */     if (this.encode == null) {
/*     */       
/* 191 */       this.encode = (COSArray)getCOSObject().getDictionaryObject(COSName.ENCODE);
/*     */       
/* 193 */       if (this.encode == null) {
/*     */         
/* 195 */         this.encode = new COSArray();
/* 196 */         COSArray sizeValues = getSize();
/* 197 */         int sizeValuesSize = sizeValues.size();
/* 198 */         for (int i = 0; i < sizeValuesSize; i++) {
/*     */           
/* 200 */           this.encode.add((COSBase)COSInteger.ZERO);
/* 201 */           this.encode.add((COSBase)COSInteger.get((sizeValues.getInt(i) - 1)));
/*     */         } 
/*     */       } 
/*     */     } 
/* 205 */     return this.encode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private COSArray getDecodeValues() {
/* 215 */     if (this.decode == null) {
/*     */       
/* 217 */       this.decode = (COSArray)getCOSObject().getDictionaryObject(COSName.DECODE);
/*     */       
/* 219 */       if (this.decode == null)
/*     */       {
/* 221 */         this.decode = getRangeValues();
/*     */       }
/*     */     } 
/* 224 */     return this.decode;
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
/*     */   public PDRange getEncodeForParameter(int paramNum) {
/* 236 */     PDRange retval = null;
/* 237 */     COSArray encodeValues = getEncodeValues();
/* 238 */     if (encodeValues != null && encodeValues.size() >= paramNum * 2 + 1)
/*     */     {
/* 240 */       retval = new PDRange(encodeValues, paramNum);
/*     */     }
/* 242 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setEncodeValues(COSArray encodeValues) {
/* 252 */     this.encode = encodeValues;
/* 253 */     getCOSObject().setItem(COSName.ENCODE, (COSBase)encodeValues);
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
/*     */   public PDRange getDecodeForParameter(int paramNum) {
/* 265 */     PDRange retval = null;
/* 266 */     COSArray decodeValues = getDecodeValues();
/* 267 */     if (decodeValues != null && decodeValues.size() >= paramNum * 2 + 1)
/*     */     {
/* 269 */       retval = new PDRange(decodeValues, paramNum);
/*     */     }
/* 271 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecodeValues(COSArray decodeValues) {
/* 281 */     this.decode = decodeValues;
/* 282 */     getCOSObject().setItem(COSName.DECODE, (COSBase)decodeValues);
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
/*     */   private int calcSampleIndex(int[] vector) {
/* 296 */     float[] sizeValues = getSize().toFloatArray();
/* 297 */     int index = 0;
/* 298 */     int sizeProduct = 1;
/* 299 */     int dimension = vector.length; int i;
/* 300 */     for (i = dimension - 2; i >= 0; i--)
/*     */     {
/* 302 */       sizeProduct = (int)(sizeProduct * sizeValues[i]);
/*     */     }
/* 304 */     for (i = dimension - 1; i >= 0; i--) {
/*     */       
/* 306 */       index += sizeProduct * vector[i];
/* 307 */       if (i - 1 >= 0)
/*     */       {
/* 309 */         sizeProduct = (int)(sizeProduct / sizeValues[i - 1]);
/*     */       }
/*     */     } 
/* 312 */     return index;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private class Rinterpol
/*     */   {
/*     */     private final float[] in;
/*     */ 
/*     */ 
/*     */     
/*     */     private final int[] inPrev;
/*     */ 
/*     */ 
/*     */     
/*     */     private final int[] inNext;
/*     */ 
/*     */     
/*     */     private final int numberOfInputValues;
/*     */ 
/*     */     
/* 334 */     private final int numberOfOutputValues = PDFunctionType0.this.getNumberOfOutputParameters();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Rinterpol(float[] input, int[] inputPrev, int[] inputNext) {
/* 346 */       this.in = input;
/* 347 */       this.inPrev = inputPrev;
/* 348 */       this.inNext = inputNext;
/* 349 */       this.numberOfInputValues = input.length;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     float[] rinterpolate() {
/* 359 */       return rinterpol(new int[this.numberOfInputValues], 0);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private float[] rinterpol(int[] coord, int step) {
/* 374 */       float[] resultSample = new float[this.numberOfOutputValues];
/* 375 */       if (step == this.in.length - 1) {
/*     */ 
/*     */         
/* 378 */         if (this.inPrev[step] == this.inNext[step]) {
/*     */           
/* 380 */           coord[step] = this.inPrev[step];
/* 381 */           int[] tmpSample = PDFunctionType0.this.getSamples()[PDFunctionType0.this.calcSampleIndex(coord)];
/* 382 */           for (int k = 0; k < this.numberOfOutputValues; k++)
/*     */           {
/* 384 */             resultSample[k] = tmpSample[k];
/*     */           }
/* 386 */           return resultSample;
/*     */         } 
/* 388 */         coord[step] = this.inPrev[step];
/* 389 */         int[] arrayOfInt1 = PDFunctionType0.this.getSamples()[PDFunctionType0.this.calcSampleIndex(coord)];
/* 390 */         coord[step] = this.inNext[step];
/* 391 */         int[] arrayOfInt2 = PDFunctionType0.this.getSamples()[PDFunctionType0.this.calcSampleIndex(coord)];
/* 392 */         for (int j = 0; j < this.numberOfOutputValues; j++)
/*     */         {
/* 394 */           resultSample[j] = PDFunctionType0.this.interpolate(this.in[step], this.inPrev[step], this.inNext[step], arrayOfInt1[j], arrayOfInt2[j]);
/*     */         }
/* 396 */         return resultSample;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 401 */       if (this.inPrev[step] == this.inNext[step]) {
/*     */         
/* 403 */         coord[step] = this.inPrev[step];
/* 404 */         return rinterpol(coord, step + 1);
/*     */       } 
/* 406 */       coord[step] = this.inPrev[step];
/* 407 */       float[] sample1 = rinterpol(coord, step + 1);
/* 408 */       coord[step] = this.inNext[step];
/* 409 */       float[] sample2 = rinterpol(coord, step + 1);
/* 410 */       for (int i = 0; i < this.numberOfOutputValues; i++)
/*     */       {
/* 412 */         resultSample[i] = PDFunctionType0.this.interpolate(this.in[step], this.inPrev[step], this.inNext[step], sample1[i], sample2[i]);
/*     */       }
/* 414 */       return resultSample;
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
/*     */   public float[] eval(float[] input) throws IOException {
/* 428 */     float[] sizeValues = getSize().toFloatArray();
/* 429 */     int bitsPerSample = getBitsPerSample();
/* 430 */     float maxSample = (float)(Math.pow(2.0D, bitsPerSample) - 1.0D);
/* 431 */     int numberOfInputValues = input.length;
/* 432 */     int numberOfOutputValues = getNumberOfOutputParameters();
/*     */     
/* 434 */     int[] inputPrev = new int[numberOfInputValues];
/* 435 */     int[] inputNext = new int[numberOfInputValues];
/* 436 */     input = (float[])input.clone();
/*     */     
/* 438 */     for (int i = 0; i < numberOfInputValues; i++) {
/*     */       
/* 440 */       PDRange domain = getDomainForInput(i);
/* 441 */       PDRange encodeValues = getEncodeForParameter(i);
/* 442 */       input[i] = clipToRange(input[i], domain.getMin(), domain.getMax());
/* 443 */       input[i] = interpolate(input[i], domain.getMin(), domain.getMax(), encodeValues
/* 444 */           .getMin(), encodeValues.getMax());
/* 445 */       input[i] = clipToRange(input[i], 0.0F, sizeValues[i] - 1.0F);
/* 446 */       inputPrev[i] = (int)Math.floor(input[i]);
/* 447 */       inputNext[i] = (int)Math.ceil(input[i]);
/*     */     } 
/*     */     
/* 450 */     float[] outputValues = (new Rinterpol(input, inputPrev, inputNext)).rinterpolate();
/*     */     
/* 452 */     for (int j = 0; j < numberOfOutputValues; j++) {
/*     */       
/* 454 */       PDRange range = getRangeForOutput(j);
/* 455 */       PDRange decodeValues = getDecodeForParameter(j);
/* 456 */       outputValues[j] = interpolate(outputValues[j], 0.0F, maxSample, decodeValues.getMin(), decodeValues.getMax());
/* 457 */       outputValues[j] = clipToRange(outputValues[j], range.getMin(), range.getMax());
/*     */     } 
/*     */     
/* 460 */     return outputValues;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/PDFunctionType0.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */