/*     */ package org.apache.pdfbox.pdmodel.encryption;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSBoolean;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDEncryption
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final int VERSION0_UNDOCUMENTED_UNSUPPORTED = 0;
/*     */   public static final int VERSION1_40_BIT_ALGORITHM = 1;
/*     */   public static final int VERSION2_VARIABLE_LENGTH_ALGORITHM = 2;
/*     */   public static final int VERSION3_UNPUBLISHED_ALGORITHM = 3;
/*     */   public static final int VERSION4_SECURITY_HANDLER = 4;
/*     */   public static final String DEFAULT_NAME = "Standard";
/*     */   public static final int DEFAULT_LENGTH = 40;
/*     */   public static final int DEFAULT_VERSION = 0;
/*     */   private final COSDictionary dictionary;
/*     */   private SecurityHandler securityHandler;
/*     */   
/*     */   public PDEncryption() {
/*  87 */     this.dictionary = new COSDictionary();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDEncryption(COSDictionary dictionary) {
/*  96 */     this.dictionary = dictionary;
/*  97 */     this.securityHandler = SecurityHandlerFactory.INSTANCE.newSecurityHandlerForFilter(getFilter());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public SecurityHandler getSecurityHandler() throws IOException {
/* 107 */     if (this.securityHandler == null)
/*     */     {
/* 109 */       throw new IOException("No security handler for filter " + getFilter());
/*     */     }
/* 111 */     return this.securityHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSecurityHandler(SecurityHandler securityHandler) {
/* 120 */     this.securityHandler = securityHandler;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasSecurityHandler() {
/* 130 */     return (this.securityHandler == null);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public COSDictionary getCOSDictionary() {
/* 142 */     return this.dictionary;
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
/* 153 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilter(String filter) {
/* 163 */     this.dictionary.setItem(COSName.FILTER, (COSBase)COSName.getPDFName(filter));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final String getFilter() {
/* 173 */     return this.dictionary.getNameAsString(COSName.FILTER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getSubFilter() {
/* 183 */     return this.dictionary.getNameAsString(COSName.SUB_FILTER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubFilter(String subfilter) {
/* 193 */     this.dictionary.setName(COSName.SUB_FILTER, subfilter);
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
/*     */   public void setVersion(int version) {
/* 206 */     this.dictionary.setInt(COSName.V, version);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getVersion() {
/* 217 */     return this.dictionary.getInt(COSName.V, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLength(int length) {
/* 227 */     this.dictionary.setInt(COSName.LENGTH, length);
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
/* 238 */     return this.dictionary.getInt(COSName.LENGTH, 40);
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
/*     */   public void setRevision(int revision) {
/* 254 */     this.dictionary.setInt(COSName.R, revision);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRevision() {
/* 265 */     return this.dictionary.getInt(COSName.R, 0);
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
/*     */   public void setOwnerKey(byte[] o) throws IOException {
/* 277 */     this.dictionary.setItem(COSName.O, (COSBase)new COSString(o));
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
/*     */   public byte[] getOwnerKey() throws IOException {
/* 289 */     byte[] o = null;
/* 290 */     COSString owner = (COSString)this.dictionary.getDictionaryObject(COSName.O);
/* 291 */     if (owner != null)
/*     */     {
/* 293 */       o = owner.getBytes();
/*     */     }
/* 295 */     return o;
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
/*     */   public void setUserKey(byte[] u) throws IOException {
/* 307 */     this.dictionary.setItem(COSName.U, (COSBase)new COSString(u));
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
/*     */   public byte[] getUserKey() throws IOException {
/* 319 */     byte[] u = null;
/* 320 */     COSString user = (COSString)this.dictionary.getDictionaryObject(COSName.U);
/* 321 */     if (user != null)
/*     */     {
/* 323 */       u = user.getBytes();
/*     */     }
/* 325 */     return u;
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
/*     */   public void setOwnerEncryptionKey(byte[] oe) throws IOException {
/* 337 */     this.dictionary.setItem(COSName.OE, (COSBase)new COSString(oe));
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
/*     */   public byte[] getOwnerEncryptionKey() throws IOException {
/* 349 */     byte[] oe = null;
/* 350 */     COSString ownerEncryptionKey = (COSString)this.dictionary.getDictionaryObject(COSName.OE);
/* 351 */     if (ownerEncryptionKey != null)
/*     */     {
/* 353 */       oe = ownerEncryptionKey.getBytes();
/*     */     }
/* 355 */     return oe;
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
/*     */   public void setUserEncryptionKey(byte[] ue) throws IOException {
/* 367 */     this.dictionary.setItem(COSName.UE, (COSBase)new COSString(ue));
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
/*     */   public byte[] getUserEncryptionKey() throws IOException {
/* 379 */     byte[] ue = null;
/* 380 */     COSString userEncryptionKey = (COSString)this.dictionary.getDictionaryObject(COSName.UE);
/* 381 */     if (userEncryptionKey != null)
/*     */     {
/* 383 */       ue = userEncryptionKey.getBytes();
/*     */     }
/* 385 */     return ue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPermissions(int permissions) {
/* 395 */     this.dictionary.setInt(COSName.P, permissions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getPermissions() {
/* 405 */     return this.dictionary.getInt(COSName.P, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isEncryptMetaData() {
/* 416 */     boolean encryptMetaData = true;
/*     */     
/* 418 */     COSBase value = this.dictionary.getDictionaryObject(COSName.ENCRYPT_META_DATA);
/*     */     
/* 420 */     if (value instanceof COSBoolean)
/*     */     {
/* 422 */       encryptMetaData = ((COSBoolean)value).getValue();
/*     */     }
/*     */     
/* 425 */     return encryptMetaData;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRecipients(byte[][] recipients) throws IOException {
/* 436 */     COSArray array = new COSArray();
/* 437 */     for (byte[] recipient : recipients) {
/*     */       
/* 439 */       COSString recip = new COSString(recipient);
/* 440 */       array.add((COSBase)recip);
/*     */     } 
/* 442 */     this.dictionary.setItem(COSName.RECIPIENTS, (COSBase)array);
/* 443 */     array.setDirect(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRecipientsLength() {
/* 453 */     COSArray array = (COSArray)this.dictionary.getItem(COSName.RECIPIENTS);
/* 454 */     return array.size();
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
/*     */   public COSString getRecipientStringAt(int i) {
/* 466 */     COSArray array = (COSArray)this.dictionary.getItem(COSName.RECIPIENTS);
/* 467 */     return (COSString)array.get(i);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCryptFilterDictionary getStdCryptFilterDictionary() {
/* 477 */     return getCryptFilterDictionary(COSName.STD_CF);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDCryptFilterDictionary getDefaultCryptFilterDictionary() {
/* 487 */     return getCryptFilterDictionary(COSName.DEFAULT_CRYPT_FILTER);
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
/*     */   public PDCryptFilterDictionary getCryptFilterDictionary(COSName cryptFilterName) {
/* 500 */     COSBase base = this.dictionary.getDictionaryObject(COSName.CF);
/* 501 */     if (base instanceof COSDictionary) {
/*     */       
/* 503 */       COSBase base2 = ((COSDictionary)base).getDictionaryObject(cryptFilterName);
/* 504 */       if (base2 instanceof COSDictionary)
/*     */       {
/* 506 */         return new PDCryptFilterDictionary((COSDictionary)base2);
/*     */       }
/*     */     } 
/* 509 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCryptFilterDictionary(COSName cryptFilterName, PDCryptFilterDictionary cryptFilterDictionary) {
/* 520 */     COSDictionary cfDictionary = this.dictionary.getCOSDictionary(COSName.CF);
/* 521 */     if (cfDictionary == null) {
/*     */       
/* 523 */       cfDictionary = new COSDictionary();
/* 524 */       this.dictionary.setItem(COSName.CF, (COSBase)cfDictionary);
/*     */     } 
/* 526 */     cfDictionary.setDirect(true);
/* 527 */     cfDictionary.setItem(cryptFilterName, (COSBase)cryptFilterDictionary.getCOSObject());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStdCryptFilterDictionary(PDCryptFilterDictionary cryptFilterDictionary) {
/* 537 */     cryptFilterDictionary.getCOSObject().setDirect(true);
/* 538 */     setCryptFilterDictionary(COSName.STD_CF, cryptFilterDictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDefaultCryptFilterDictionary(PDCryptFilterDictionary defaultFilterDictionary) {
/* 548 */     defaultFilterDictionary.getCOSObject().setDirect(true);
/* 549 */     setCryptFilterDictionary(COSName.DEFAULT_CRYPT_FILTER, defaultFilterDictionary);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getStreamFilterName() {
/* 560 */     COSName stmF = (COSName)this.dictionary.getDictionaryObject(COSName.STM_F);
/* 561 */     if (stmF == null)
/*     */     {
/* 563 */       stmF = COSName.IDENTITY;
/*     */     }
/* 565 */     return stmF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStreamFilterName(COSName streamFilterName) {
/* 575 */     this.dictionary.setItem(COSName.STM_F, (COSBase)streamFilterName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSName getStringFilterName() {
/* 586 */     COSName strF = (COSName)this.dictionary.getDictionaryObject(COSName.STR_F);
/* 587 */     if (strF == null)
/*     */     {
/* 589 */       strF = COSName.IDENTITY;
/*     */     }
/* 591 */     return strF;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setStringFilterName(COSName stringFilterName) {
/* 601 */     this.dictionary.setItem(COSName.STR_F, (COSBase)stringFilterName);
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
/*     */   public void setPerms(byte[] perms) throws IOException {
/* 613 */     this.dictionary.setItem(COSName.PERMS, (COSBase)new COSString(perms));
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
/*     */   public byte[] getPerms() throws IOException {
/* 625 */     byte[] perms = null;
/* 626 */     COSString permsCosString = (COSString)this.dictionary.getDictionaryObject(COSName.PERMS);
/* 627 */     if (permsCosString != null)
/*     */     {
/* 629 */       perms = permsCosString.getBytes();
/*     */     }
/* 631 */     return perms;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeV45filters() {
/* 639 */     this.dictionary.setItem(COSName.CF, null);
/* 640 */     this.dictionary.setItem(COSName.STM_F, null);
/* 641 */     this.dictionary.setItem(COSName.STR_F, null);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/encryption/PDEncryption.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */