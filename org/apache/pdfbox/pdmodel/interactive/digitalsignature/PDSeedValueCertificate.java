/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSArrayList;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDSeedValueCertificate
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final int FLAG_SUBJECT = 1;
/*     */   public static final int FLAG_ISSUER = 2;
/*     */   public static final int FLAG_OID = 4;
/*     */   public static final int FLAG_SUBJECT_DN = 8;
/*     */   public static final int FLAG_KEY_USAGE = 32;
/*     */   public static final int FLAG_URL = 64;
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDSeedValueCertificate() {
/*  74 */     this.dictionary = new COSDictionary();
/*  75 */     this.dictionary.setItem(COSName.TYPE, (COSBase)COSName.SV_CERT);
/*  76 */     this.dictionary.setDirect(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSeedValueCertificate(COSDictionary dict) {
/*  86 */     this.dictionary = dict;
/*  87 */     this.dictionary.setDirect(true);
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
/*  98 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSubjectRequired() {
/* 107 */     return getCOSObject().getFlag(COSName.FF, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubjectRequired(boolean flag) {
/* 117 */     getCOSObject().setFlag(COSName.FF, 1, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIssuerRequired() {
/* 126 */     return getCOSObject().getFlag(COSName.FF, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setIssuerRequired(boolean flag) {
/* 136 */     getCOSObject().setFlag(COSName.FF, 2, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOIDRequired() {
/* 145 */     return getCOSObject().getFlag(COSName.FF, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOIDRequired(boolean flag) {
/* 155 */     getCOSObject().setFlag(COSName.FF, 4, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSubjectDNRequired() {
/* 164 */     return getCOSObject().getFlag(COSName.FF, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubjectDNRequired(boolean flag) {
/* 174 */     getCOSObject().setFlag(COSName.FF, 8, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isKeyUsageRequired() {
/* 183 */     return getCOSObject().getFlag(COSName.FF, 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyUsageRequired(boolean flag) {
/* 193 */     getCOSObject().setFlag(COSName.FF, 32, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isURLRequired() {
/* 202 */     return getCOSObject().getFlag(COSName.FF, 64);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURLRequired(boolean flag) {
/* 212 */     getCOSObject().setFlag(COSName.FF, 64, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<byte[]> getSubject() {
/* 222 */     COSBase base = this.dictionary.getDictionaryObject(COSName.SUBJECT);
/* 223 */     if (base instanceof COSArray) {
/*     */       
/* 225 */       COSArray array = (COSArray)base;
/* 226 */       return getListOfByteArraysFromCOSArray(array);
/*     */     } 
/* 228 */     return null;
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
/*     */   public void setSubject(List<byte[]> subjects) {
/* 242 */     COSArray array = new COSArray();
/* 243 */     for (byte[] subject : subjects)
/*     */     {
/* 245 */       array.add((COSBase)new COSString(subject));
/*     */     }
/* 247 */     this.dictionary.setItem(COSName.SUBJECT, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addSubject(byte[] subject) {
/*     */     COSArray array;
/* 258 */     COSBase base = this.dictionary.getDictionaryObject(COSName.SUBJECT);
/*     */     
/* 260 */     if (base instanceof COSArray) {
/*     */       
/* 262 */       array = (COSArray)base;
/*     */     }
/*     */     else {
/*     */       
/* 266 */       array = new COSArray();
/*     */     } 
/* 268 */     COSString string = new COSString(subject);
/* 269 */     array.add((COSBase)string);
/* 270 */     this.dictionary.setItem(COSName.SUBJECT, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeSubject(byte[] subject) {
/* 280 */     COSBase base = this.dictionary.getDictionaryObject(COSName.SUBJECT);
/* 281 */     if (base instanceof COSArray) {
/*     */       
/* 283 */       COSArray array = (COSArray)base;
/* 284 */       array.remove((COSBase)new COSString(subject));
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
/*     */   public List<Map<String, String>> getSubjectDN() {
/* 296 */     COSBase base = this.dictionary.getDictionaryObject(COSName.SUBJECT_DN);
/* 297 */     if (base instanceof COSArray) {
/*     */       
/* 299 */       COSArray cosArray = (COSArray)base;
/* 300 */       List subjectDNList = cosArray.toList();
/* 301 */       List<Map<String, String>> result = new LinkedList<Map<String, String>>();
/* 302 */       for (Object subjectDNItem : subjectDNList) {
/*     */         
/* 304 */         if (subjectDNItem instanceof COSDictionary) {
/*     */           
/* 306 */           COSDictionary subjectDNItemDict = (COSDictionary)subjectDNItem;
/* 307 */           Map<String, String> subjectDNMap = new HashMap<String, String>();
/* 308 */           for (COSName key : subjectDNItemDict.keySet())
/*     */           {
/* 310 */             subjectDNMap.put(key.getName(), subjectDNItemDict.getString(key));
/*     */           }
/* 312 */           result.add(subjectDNMap);
/*     */         } 
/*     */       } 
/* 315 */       return result;
/*     */     } 
/* 317 */     return null;
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
/*     */   public void setSubjectDN(List<Map<String, String>> subjectDN) {
/* 330 */     List<COSDictionary> subjectDNDict = new LinkedList<COSDictionary>();
/* 331 */     for (Map<String, String> subjectDNItem : subjectDN) {
/*     */       
/* 333 */       COSDictionary dict = new COSDictionary();
/* 334 */       for (Map.Entry<String, String> entry : subjectDNItem.entrySet())
/*     */       {
/* 336 */         dict.setItem(entry.getKey(), (COSBase)new COSString(entry.getValue()));
/*     */       }
/* 338 */       subjectDNDict.add(dict);
/*     */     } 
/* 340 */     this.dictionary.setItem(COSName.SUBJECT_DN, 
/* 341 */         (COSBase)COSArrayList.converterToCOSArray(subjectDNDict));
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
/*     */   
/*     */   public List<String> getKeyUsage() {
/* 364 */     COSBase base = this.dictionary.getDictionaryObject(COSName.KEY_USAGE);
/* 365 */     if (base instanceof COSArray) {
/*     */       
/* 367 */       COSArray array = (COSArray)base;
/* 368 */       List<String> keyUsageExtensions = new LinkedList<String>();
/* 369 */       for (COSBase item : array) {
/*     */         
/* 371 */         if (item instanceof COSString)
/*     */         {
/* 373 */           keyUsageExtensions.add(((COSString)item).getString());
/*     */         }
/*     */       } 
/* 376 */       return keyUsageExtensions;
/*     */     } 
/* 378 */     return null;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void setKeyUsage(List<String> keyUsageExtensions) {
/* 403 */     this.dictionary.setItem(COSName.KEY_USAGE, 
/* 404 */         (COSBase)COSArrayList.converterToCOSArray(keyUsageExtensions));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addKeyUsage(String keyUsageExtension) {
/*     */     COSArray array;
/* 415 */     String allowedChars = "01X";
/* 416 */     for (int c = 0; c < keyUsageExtension.length(); c++) {
/*     */       
/* 418 */       if (allowedChars.indexOf(keyUsageExtension.charAt(c)) == -1)
/*     */       {
/* 420 */         throw new IllegalArgumentException("characters can only be 0, 1, X");
/*     */       }
/*     */     } 
/* 423 */     COSBase base = this.dictionary.getDictionaryObject(COSName.KEY_USAGE);
/*     */     
/* 425 */     if (base instanceof COSArray) {
/*     */       
/* 427 */       array = (COSArray)base;
/*     */     }
/*     */     else {
/*     */       
/* 431 */       array = new COSArray();
/*     */     } 
/* 433 */     COSString string = new COSString(keyUsageExtension);
/* 434 */     array.add((COSBase)string);
/* 435 */     this.dictionary.setItem(COSName.KEY_USAGE, (COSBase)array);
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
/*     */   public void addKeyUsage(char digitalSignature, char nonRepudiation, char keyEncipherment, char dataEncipherment, char keyAgreement, char keyCertSign, char cRLSign, char encipherOnly, char decipherOnly) {
/* 455 */     String string = "" + digitalSignature + nonRepudiation + keyEncipherment + dataEncipherment + keyAgreement + keyCertSign + cRLSign + encipherOnly + decipherOnly;
/*     */     
/* 457 */     addKeyUsage(string);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeKeyUsage(String keyUsageExtension) {
/* 467 */     COSBase base = this.dictionary.getDictionaryObject(COSName.KEY_USAGE);
/* 468 */     if (base instanceof COSArray) {
/*     */       
/* 470 */       COSArray array = (COSArray)base;
/* 471 */       array.remove((COSBase)new COSString(keyUsageExtension));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<byte[]> getIssuer() {
/* 482 */     COSBase base = this.dictionary.getDictionaryObject(COSName.ISSUER);
/* 483 */     if (base instanceof COSArray) {
/*     */       
/* 485 */       COSArray array = (COSArray)base;
/* 486 */       return getListOfByteArraysFromCOSArray(array);
/*     */     } 
/* 488 */     return null;
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
/*     */   public void setIssuer(List<byte[]> issuers) {
/* 500 */     COSArray array = new COSArray();
/* 501 */     for (byte[] issuer : issuers)
/*     */     {
/* 503 */       array.add((COSBase)new COSString(issuer));
/*     */     }
/* 505 */     this.dictionary.setItem(COSName.ISSUER, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addIssuer(byte[] issuer) {
/*     */     COSArray array;
/* 517 */     COSBase base = this.dictionary.getDictionaryObject(COSName.ISSUER);
/*     */     
/* 519 */     if (base instanceof COSArray) {
/*     */       
/* 521 */       array = (COSArray)base;
/*     */     }
/*     */     else {
/*     */       
/* 525 */       array = new COSArray();
/*     */     } 
/* 527 */     COSString string = new COSString(issuer);
/* 528 */     array.add((COSBase)string);
/* 529 */     this.dictionary.setItem(COSName.ISSUER, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeIssuer(byte[] issuer) {
/* 539 */     COSBase base = this.dictionary.getDictionaryObject(COSName.ISSUER);
/* 540 */     if (base instanceof COSArray) {
/*     */       
/* 542 */       COSArray array = (COSArray)base;
/* 543 */       array.remove((COSBase)new COSString(issuer));
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
/*     */   public List<byte[]> getOID() {
/* 555 */     COSBase base = this.dictionary.getDictionaryObject(COSName.OID);
/* 556 */     if (base instanceof COSArray) {
/*     */       
/* 558 */       COSArray array = (COSArray)base;
/* 559 */       return getListOfByteArraysFromCOSArray(array);
/*     */     } 
/* 561 */     return null;
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
/*     */   public void setOID(List<byte[]> oidByteStrings) {
/* 573 */     COSArray array = new COSArray();
/* 574 */     for (byte[] oid : oidByteStrings)
/*     */     {
/* 576 */       array.add((COSBase)new COSString(oid));
/*     */     }
/* 578 */     this.dictionary.setItem(COSName.OID, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addOID(byte[] oid) {
/*     */     COSArray array;
/* 588 */     COSBase base = this.dictionary.getDictionaryObject(COSName.OID);
/*     */     
/* 590 */     if (base instanceof COSArray) {
/*     */       
/* 592 */       array = (COSArray)base;
/*     */     }
/*     */     else {
/*     */       
/* 596 */       array = new COSArray();
/*     */     } 
/* 598 */     COSString string = new COSString(oid);
/* 599 */     array.add((COSBase)string);
/* 600 */     this.dictionary.setItem(COSName.OID, (COSBase)array);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeOID(byte[] oid) {
/* 610 */     COSBase base = this.dictionary.getDictionaryObject(COSName.OID);
/* 611 */     if (base instanceof COSArray) {
/*     */       
/* 613 */       COSArray array = (COSArray)base;
/* 614 */       array.remove((COSBase)new COSString(oid));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getURL() {
/* 625 */     return this.dictionary.getString(COSName.URL);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURL(String url) {
/* 635 */     this.dictionary.setString(COSName.URL, url);
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
/*     */   public String getURLType() {
/* 655 */     return this.dictionary.getNameAsString(COSName.URL_TYPE);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public void setURLType(String urlType) {
/* 680 */     this.dictionary.setName(COSName.URL_TYPE, urlType);
/*     */   }
/*     */ 
/*     */   
/*     */   private static List<byte[]> getListOfByteArraysFromCOSArray(COSArray array) {
/* 685 */     List<byte[]> result = (List)new LinkedList<byte>();
/* 686 */     for (COSBase item : array) {
/*     */       
/* 688 */       if (item instanceof COSString)
/*     */       {
/* 690 */         result.add(((COSString)item).getBytes());
/*     */       }
/*     */     } 
/* 693 */     return result;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/PDSeedValueCertificate.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */