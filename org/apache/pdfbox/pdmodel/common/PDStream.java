/*     */ package org.apache.pdfbox.pdmodel.common;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSInputStream;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.filter.DecodeOptions;
/*     */ import org.apache.pdfbox.filter.Filter;
/*     */ import org.apache.pdfbox.filter.FilterFactory;
/*     */ import org.apache.pdfbox.io.IOUtils;
/*     */ import org.apache.pdfbox.pdmodel.PDDocument;
/*     */ import org.apache.pdfbox.pdmodel.common.filespecification.PDFileSpecification;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDStream
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSStream stream;
/*     */   
/*     */   public PDStream(PDDocument document) {
/*  58 */     this.stream = document.getDocument().createCOSStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream(COSDocument document) {
/*  68 */     this.stream = document.createCOSStream();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDStream(COSStream str) {
/*  78 */     this.stream = str;
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
/*     */   public PDStream(PDDocument doc, InputStream input) throws IOException {
/*  91 */     this(doc, input, (COSBase)null);
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
/*     */   public PDStream(PDDocument doc, InputStream input, COSName filter) throws IOException {
/* 105 */     this(doc, input, (COSBase)filter);
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
/*     */   public PDStream(PDDocument doc, InputStream input, COSArray filters) throws IOException {
/* 119 */     this(doc, input, (COSBase)filters);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private PDStream(PDDocument doc, InputStream input, COSBase filters) throws IOException {
/* 128 */     OutputStream output = null;
/*     */     
/*     */     try {
/* 131 */       this.stream = doc.getDocument().createCOSStream();
/* 132 */       output = this.stream.createOutputStream(filters);
/* 133 */       IOUtils.copy(input, output);
/*     */     }
/*     */     finally {
/*     */       
/* 137 */       if (output != null)
/*     */       {
/* 139 */         output.close();
/*     */       }
/* 141 */       if (input != null)
/*     */       {
/* 143 */         input.close();
/*     */       }
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
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void addCompression() {
/* 162 */     List<COSName> filters = getFilters();
/* 163 */     if (filters == null)
/*     */     {
/* 165 */       if (this.stream.getLength() > 0L) {
/*     */         
/* 167 */         OutputStream out = null;
/*     */         
/*     */         try {
/* 170 */           byte[] bytes = IOUtils.toByteArray((InputStream)this.stream.createInputStream());
/* 171 */           out = this.stream.createOutputStream((COSBase)COSName.FLATE_DECODE);
/* 172 */           out.write(bytes);
/*     */         }
/* 174 */         catch (IOException e) {
/*     */ 
/*     */           
/* 177 */           throw new RuntimeException(e);
/*     */         }
/*     */         finally {
/*     */           
/* 181 */           IOUtils.closeQuietly(out);
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 186 */         filters = new ArrayList<COSName>();
/* 187 */         filters.add(COSName.FLATE_DECODE);
/* 188 */         setFilters(filters);
/*     */       } 
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
/*     */   public COSStream getCOSObject() {
/* 202 */     return this.stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OutputStream createOutputStream() throws IOException {
/* 213 */     return this.stream.createOutputStream();
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
/*     */   public OutputStream createOutputStream(COSName filter) throws IOException {
/* 225 */     return this.stream.createOutputStream((COSBase)filter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSInputStream createInputStream() throws IOException {
/* 236 */     return this.stream.createInputStream();
/*     */   }
/*     */ 
/*     */   
/*     */   public COSInputStream createInputStream(DecodeOptions options) throws IOException {
/* 241 */     return this.stream.createInputStream(options);
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
/*     */   public InputStream createInputStream(List<String> stopFilters) throws IOException {
/* 255 */     InputStream is = this.stream.createRawInputStream();
/* 256 */     ByteArrayOutputStream os = new ByteArrayOutputStream();
/* 257 */     List<COSName> filters = getFilters();
/* 258 */     if (filters != null)
/*     */     {
/* 260 */       for (int i = 0; i < filters.size(); i++) {
/*     */         
/* 262 */         COSName nextFilter = filters.get(i);
/* 263 */         if (stopFilters != null && stopFilters.contains(nextFilter.getName())) {
/*     */           break;
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 269 */         Filter filter = FilterFactory.INSTANCE.getFilter(nextFilter);
/* 270 */         filter.decode(is, os, (COSDictionary)this.stream, i);
/* 271 */         IOUtils.closeQuietly(is);
/* 272 */         is = new ByteArrayInputStream(os.toByteArray());
/* 273 */         os.reset();
/*     */       } 
/*     */     }
/*     */     
/* 277 */     return is;
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
/*     */   @Deprecated
/*     */   public COSStream getStream() {
/* 290 */     return this.stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getLength() {
/* 301 */     return this.stream.getInt(COSName.LENGTH, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<COSName> getFilters() {
/* 312 */     List<COSName> retval = null;
/* 313 */     COSBase filters = this.stream.getFilters();
/* 314 */     if (filters instanceof COSName) {
/*     */       
/* 316 */       COSName name = (COSName)filters;
/* 317 */       retval = new COSArrayList<COSName>(name, (COSBase)name, (COSDictionary)this.stream, COSName.FILTER);
/*     */     }
/* 319 */     else if (filters instanceof COSArray) {
/*     */       
/* 321 */       retval = ((COSArray)filters).toList();
/*     */     } 
/* 323 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilters(List<COSName> filters) {
/* 333 */     COSArray cOSArray = COSArrayList.converterToCOSArray(filters);
/* 334 */     this.stream.setItem(COSName.FILTER, (COSBase)cOSArray);
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
/*     */   public List<Object> getDecodeParms() throws IOException {
/* 346 */     List<Object> retval = null;
/*     */     
/* 348 */     COSBase dp = this.stream.getDictionaryObject(COSName.DECODE_PARMS);
/* 349 */     if (dp == null)
/*     */     {
/*     */ 
/*     */       
/* 353 */       dp = this.stream.getDictionaryObject(COSName.DP);
/*     */     }
/* 355 */     if (dp instanceof COSDictionary) {
/*     */ 
/*     */       
/* 358 */       Map<?, ?> map = COSDictionaryMap.convertBasicTypesToMap((COSDictionary)dp);
/* 359 */       retval = new COSArrayList(map, dp, (COSDictionary)this.stream, COSName.DECODE_PARMS);
/*     */     
/*     */     }
/* 362 */     else if (dp instanceof COSArray) {
/*     */       
/* 364 */       COSArray array = (COSArray)dp;
/* 365 */       List<Object> actuals = new ArrayList();
/* 366 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/* 368 */         actuals.add(
/* 369 */             COSDictionaryMap.convertBasicTypesToMap((COSDictionary)array
/* 370 */               .getObject(i)));
/*     */       }
/* 372 */       retval = new COSArrayList(actuals, array);
/*     */     } 
/*     */     
/* 375 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecodeParms(List<?> decodeParams) {
/* 385 */     this.stream.setItem(COSName.DECODE_PARMS, 
/* 386 */         (COSBase)COSArrayList.converterToCOSArray(decodeParams));
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
/*     */   public PDFileSpecification getFile() throws IOException {
/* 398 */     COSBase f = this.stream.getDictionaryObject(COSName.F);
/* 399 */     return PDFileSpecification.createFS(f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFile(PDFileSpecification f) {
/* 409 */     this.stream.setItem(COSName.F, (COSObjectable)f);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getFileFilters() {
/* 420 */     List<String> retval = null;
/* 421 */     COSBase filters = this.stream.getDictionaryObject(COSName.F_FILTER);
/* 422 */     if (filters instanceof COSName) {
/*     */       
/* 424 */       COSName name = (COSName)filters;
/* 425 */       retval = new COSArrayList<String>(name.getName(), (COSBase)name, (COSDictionary)this.stream, COSName.F_FILTER);
/*     */     
/*     */     }
/* 428 */     else if (filters instanceof COSArray) {
/*     */ 
/*     */       
/* 431 */       retval = COSArrayList.convertCOSNameCOSArrayToList((COSArray)filters);
/*     */     } 
/* 433 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileFilters(List<String> filters) {
/* 443 */     COSArray cOSArray = COSArrayList.convertStringListToCOSNameCOSArray(filters);
/* 444 */     this.stream.setItem(COSName.F_FILTER, (COSBase)cOSArray);
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
/*     */   public List<Object> getFileDecodeParams() throws IOException {
/* 456 */     List<Object> retval = null;
/*     */     
/* 458 */     COSBase dp = this.stream.getDictionaryObject(COSName.F_DECODE_PARMS);
/* 459 */     if (dp instanceof COSDictionary) {
/*     */ 
/*     */       
/* 462 */       Map<?, ?> map = COSDictionaryMap.convertBasicTypesToMap((COSDictionary)dp);
/* 463 */       retval = new COSArrayList(map, dp, (COSDictionary)this.stream, COSName.F_DECODE_PARMS);
/*     */     
/*     */     }
/* 466 */     else if (dp instanceof COSArray) {
/*     */       
/* 468 */       COSArray array = (COSArray)dp;
/* 469 */       List<Object> actuals = new ArrayList();
/* 470 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/* 472 */         actuals.add(
/* 473 */             COSDictionaryMap.convertBasicTypesToMap((COSDictionary)array
/* 474 */               .getObject(i)));
/*     */       }
/* 476 */       retval = new COSArrayList(actuals, array);
/*     */     } 
/*     */     
/* 479 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFileDecodeParams(List<?> decodeParams) {
/* 489 */     this.stream.setItem("FDecodeParams", 
/* 490 */         (COSBase)COSArrayList.converterToCOSArray(decodeParams));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] toByteArray() throws IOException {
/*     */     COSInputStream cOSInputStream;
/* 501 */     ByteArrayOutputStream output = new ByteArrayOutputStream();
/* 502 */     InputStream is = null;
/*     */     
/*     */     try {
/* 505 */       cOSInputStream = createInputStream();
/* 506 */       IOUtils.copy((InputStream)cOSInputStream, output);
/*     */     }
/*     */     finally {
/*     */       
/* 510 */       if (cOSInputStream != null)
/*     */       {
/* 512 */         cOSInputStream.close();
/*     */       }
/*     */     } 
/* 515 */     return output.toByteArray();
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
/*     */   public PDMetadata getMetadata() {
/* 528 */     PDMetadata retval = null;
/* 529 */     COSBase mdStream = this.stream.getDictionaryObject(COSName.METADATA);
/* 530 */     if (mdStream != null)
/*     */     {
/* 532 */       if (mdStream instanceof COSStream) {
/*     */         
/* 534 */         retval = new PDMetadata((COSStream)mdStream);
/*     */       }
/* 536 */       else if (!(mdStream instanceof org.apache.pdfbox.cos.COSNull)) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 542 */         throw new IllegalStateException("Expected a COSStream but was a " + mdStream
/*     */             
/* 544 */             .getClass().getSimpleName());
/*     */       } 
/*     */     }
/* 547 */     return retval;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMetadata(PDMetadata meta) {
/* 557 */     this.stream.setItem(COSName.METADATA, meta);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getDecodedStreamLength() {
/* 567 */     return this.stream.getInt(COSName.DL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDecodedStreamLength(int decodedStreamLength) {
/* 577 */     this.stream.setInt(COSName.DL, decodedStreamLength);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/PDStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */