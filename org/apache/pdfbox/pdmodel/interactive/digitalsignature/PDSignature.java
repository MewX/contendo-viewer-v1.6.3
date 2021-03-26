/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
/*     */ 
/*     */ import java.io.ByteArrayOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.Calendar;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDSignature
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*  47 */   public static final COSName FILTER_ADOBE_PPKLITE = COSName.ADOBE_PPKLITE;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  52 */   public static final COSName FILTER_ENTRUST_PPKEF = COSName.ENTRUST_PPKEF;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   public static final COSName FILTER_CICI_SIGNIT = COSName.CICI_SIGNIT;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  62 */   public static final COSName FILTER_VERISIGN_PPKVS = COSName.VERISIGN_PPKVS;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  67 */   public static final COSName SUBFILTER_ADBE_X509_RSA_SHA1 = COSName.ADBE_X509_RSA_SHA1;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static final COSName SUBFILTER_ADBE_PKCS7_DETACHED = COSName.ADBE_PKCS7_DETACHED;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  77 */   public static final COSName SUBFILTER_ETSI_CADES_DETACHED = COSName.getPDFName("ETSI.CAdES.detached");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  82 */   public static final COSName SUBFILTER_ADBE_PKCS7_SHA1 = COSName.ADBE_PKCS7_SHA1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignature() {
/*  89 */     this.dictionary = new COSDictionary();
/*  90 */     this.dictionary.setItem(COSName.TYPE, (COSBase)COSName.SIG);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSignature(COSDictionary dict) {
/* 100 */     this.dictionary = dict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSDictionary getCOSObject() {
/* 111 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(COSName type) {
/* 121 */     this.dictionary.setItem(COSName.TYPE, (COSBase)type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilter(COSName filter) {
/* 131 */     this.dictionary.setItem(COSName.FILTER, (COSBase)filter);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubFilter(COSName subfilter) {
/* 141 */     this.dictionary.setItem(COSName.SUB_FILTER, (COSBase)subfilter);
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
/*     */   public void setName(String name) {
/* 153 */     this.dictionary.setString(COSName.NAME, name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLocation(String location) {
/* 163 */     this.dictionary.setString(COSName.LOCATION, location);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReason(String reason) {
/* 173 */     this.dictionary.setString(COSName.REASON, reason);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContactInfo(String contactInfo) {
/* 184 */     this.dictionary.setString(COSName.CONTACT_INFO, contactInfo);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSignDate(Calendar cal) {
/* 194 */     this.dictionary.setDate(COSName.M, cal);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFilter() {
/* 203 */     return this.dictionary.getNameAsString(COSName.FILTER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubFilter() {
/* 213 */     return this.dictionary.getNameAsString(COSName.SUB_FILTER);
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
/*     */   public String getName() {
/* 225 */     return this.dictionary.getString(COSName.NAME);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getLocation() {
/* 235 */     return this.dictionary.getString(COSName.LOCATION);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getReason() {
/* 245 */     return this.dictionary.getString(COSName.REASON);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getContactInfo() {
/* 256 */     return this.dictionary.getString(COSName.CONTACT_INFO);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Calendar getSignDate() {
/* 266 */     return this.dictionary.getDate(COSName.M);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setByteRange(int[] range) {
/* 276 */     if (range.length != 4) {
/*     */       return;
/*     */     }
/*     */     
/* 280 */     COSArray ary = new COSArray();
/* 281 */     for (int i : range)
/*     */     {
/* 283 */       ary.add((COSBase)COSInteger.get(i));
/*     */     }
/*     */     
/* 286 */     this.dictionary.setItem(COSName.BYTERANGE, (COSBase)ary);
/* 287 */     ary.setDirect(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int[] getByteRange() {
/* 297 */     COSArray byteRange = (COSArray)this.dictionary.getDictionaryObject(COSName.BYTERANGE);
/* 298 */     int[] ary = new int[byteRange.size()];
/* 299 */     for (int i = 0; i < ary.length; i++)
/*     */     {
/* 301 */       ary[i] = byteRange.getInt(i);
/*     */     }
/* 303 */     return ary;
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
/*     */   public byte[] getContents(InputStream pdfFile) throws IOException {
/* 315 */     int[] byteRange = getByteRange();
/* 316 */     int begin = byteRange[0] + byteRange[1] + 1;
/* 317 */     int len = byteRange[2] - begin;
/*     */     
/* 319 */     return getConvertedContents(new COSFilterInputStream(pdfFile, new int[] { begin, len }));
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
/*     */   public byte[] getContents(byte[] pdfFile) throws IOException {
/* 331 */     int[] byteRange = getByteRange();
/* 332 */     int begin = byteRange[0] + byteRange[1] + 1;
/* 333 */     int len = byteRange[2] - begin;
/*     */     
/* 335 */     return getConvertedContents(new COSFilterInputStream(pdfFile, new int[] { begin, len }));
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[] getConvertedContents(InputStream is) throws IOException {
/* 340 */     ByteArrayOutputStream byteOS = new ByteArrayOutputStream(1024);
/* 341 */     byte[] buffer = new byte[1024];
/*     */     int c;
/* 343 */     while ((c = is.read(buffer)) != -1) {
/*     */ 
/*     */       
/* 346 */       if (buffer[0] == 60 || buffer[0] == 40) {
/*     */         
/* 348 */         byteOS.write(buffer, 1, c);
/*     */         continue;
/*     */       } 
/* 351 */       if (buffer[c - 1] == 62 || buffer[c - 1] == 41) {
/*     */         
/* 353 */         byteOS.write(buffer, 0, c - 1);
/*     */         
/*     */         continue;
/*     */       } 
/* 357 */       byteOS.write(buffer, 0, c);
/*     */     } 
/*     */     
/* 360 */     is.close();
/*     */     
/* 362 */     return COSString.parseHex(byteOS.toString("ISO-8859-1")).getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setContents(byte[] bytes) {
/* 372 */     COSString string = new COSString(bytes);
/* 373 */     string.setForceHexForm(true);
/* 374 */     this.dictionary.setItem(COSName.CONTENTS, (COSBase)string);
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
/*     */   public byte[] getSignedContent(InputStream pdfFile) throws IOException {
/* 390 */     COSFilterInputStream fis = null;
/*     */ 
/*     */     
/*     */     try {
/* 394 */       fis = new COSFilterInputStream(pdfFile, getByteRange());
/* 395 */       return fis.toByteArray();
/*     */     }
/*     */     finally {
/*     */       
/* 399 */       if (fis != null)
/*     */       {
/* 401 */         fis.close();
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
/*     */   public byte[] getSignedContent(byte[] pdfFile) throws IOException {
/* 419 */     COSFilterInputStream fis = null;
/*     */     
/*     */     try {
/* 422 */       fis = new COSFilterInputStream(pdfFile, getByteRange());
/* 423 */       return fis.toByteArray();
/*     */     }
/*     */     finally {
/*     */       
/* 427 */       if (fis != null)
/*     */       {
/* 429 */         fis.close();
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
/*     */   public PDPropBuild getPropBuild() {
/* 441 */     PDPropBuild propBuild = null;
/* 442 */     COSDictionary propBuildDic = (COSDictionary)this.dictionary.getDictionaryObject(COSName.PROP_BUILD);
/* 443 */     if (propBuildDic != null)
/*     */     {
/* 445 */       propBuild = new PDPropBuild(propBuildDic);
/*     */     }
/* 447 */     return propBuild;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPropBuild(PDPropBuild propBuild) {
/* 457 */     this.dictionary.setItem(COSName.PROP_BUILD, propBuild);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/PDSignature.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */