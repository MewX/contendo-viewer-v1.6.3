/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDSeedValue
/*     */   implements COSObjectable
/*     */ {
/*     */   public static final int FLAG_FILTER = 1;
/*     */   public static final int FLAG_SUBFILTER = 2;
/*     */   public static final int FLAG_V = 4;
/*     */   public static final int FLAG_REASON = 8;
/*     */   public static final int FLAG_LEGAL_ATTESTATION = 16;
/*     */   public static final int FLAG_ADD_REV_INFO = 32;
/*     */   public static final int FLAG_DIGEST_METHOD = 64;
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDSeedValue() {
/*  78 */     this.dictionary = new COSDictionary();
/*  79 */     this.dictionary.setItem(COSName.TYPE, (COSBase)COSName.SV);
/*  80 */     this.dictionary.setDirect(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDSeedValue(COSDictionary dict) {
/*  90 */     this.dictionary = dict;
/*  91 */     this.dictionary.setDirect(true);
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
/* 102 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFilterRequired() {
/* 111 */     return getCOSObject().getFlag(COSName.FF, 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilterRequired(boolean flag) {
/* 121 */     getCOSObject().setFlag(COSName.FF, 1, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSubFilterRequired() {
/* 130 */     return getCOSObject().getFlag(COSName.FF, 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSubFilterRequired(boolean flag) {
/* 140 */     getCOSObject().setFlag(COSName.FF, 2, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isDigestMethodRequired() {
/* 149 */     return getCOSObject().getFlag(COSName.FF, 64);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDigestMethodRequired(boolean flag) {
/* 159 */     getCOSObject().setFlag(COSName.FF, 64, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVRequired() {
/* 168 */     return getCOSObject().getFlag(COSName.FF, 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVRequired(boolean flag) {
/* 178 */     getCOSObject().setFlag(COSName.FF, 4, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isReasonRequired() {
/* 187 */     return getCOSObject().getFlag(COSName.FF, 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setReasonRequired(boolean flag) {
/* 197 */     getCOSObject().setFlag(COSName.FF, 8, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isLegalAttestationRequired() {
/* 206 */     return getCOSObject().getFlag(COSName.FF, 16);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setLegalAttestationRequired(boolean flag) {
/* 216 */     getCOSObject().setFlag(COSName.FF, 16, flag);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isAddRevInfoRequired() {
/* 225 */     return getCOSObject().getFlag(COSName.FF, 32);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAddRevInfoRequired(boolean flag) {
/* 235 */     getCOSObject().setFlag(COSName.FF, 32, flag);
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
/*     */   public String getFilter() {
/* 249 */     return this.dictionary.getNameAsString(COSName.FILTER);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setFilter(COSName filter) {
/* 259 */     this.dictionary.setItem(COSName.FILTER, (COSBase)filter);
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
/*     */   public List<String> getSubFilter() {
/*     */     COSArrayList cOSArrayList;
/* 274 */     List<String> retval = null;
/* 275 */     COSArray fields = (COSArray)this.dictionary.getDictionaryObject(COSName.SUB_FILTER);
/*     */     
/* 277 */     if (fields != null) {
/*     */       
/* 279 */       List<String> actuals = new ArrayList<String>();
/* 280 */       for (int i = 0; i < fields.size(); i++) {
/*     */         
/* 282 */         String element = fields.getName(i);
/* 283 */         if (element != null)
/*     */         {
/* 285 */           actuals.add(element);
/*     */         }
/*     */       } 
/* 288 */       cOSArrayList = new COSArrayList(actuals, fields);
/*     */     } 
/* 290 */     return (List<String>)cOSArrayList;
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
/*     */   public void setSubFilter(List<COSName> subfilter) {
/* 302 */     this.dictionary.setItem(COSName.SUB_FILTER, (COSBase)COSArrayList.converterToCOSArray(subfilter));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getDigestMethod() {
/*     */     COSArrayList cOSArrayList;
/* 314 */     List<String> retval = null;
/* 315 */     COSArray fields = (COSArray)this.dictionary.getDictionaryObject(COSName.DIGEST_METHOD);
/*     */     
/* 317 */     if (fields != null) {
/*     */       
/* 319 */       List<String> actuals = new ArrayList<String>();
/* 320 */       for (int i = 0; i < fields.size(); i++) {
/*     */         
/* 322 */         String element = fields.getName(i);
/* 323 */         if (element != null)
/*     */         {
/* 325 */           actuals.add(element);
/*     */         }
/*     */       } 
/* 328 */       cOSArrayList = new COSArrayList(actuals, fields);
/*     */     } 
/* 330 */     return (List<String>)cOSArrayList;
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
/*     */   public void setDigestMethod(List<COSName> digestMethod) {
/* 348 */     for (COSName cosName : digestMethod) {
/*     */       
/* 350 */       if (!cosName.equals(COSName.DIGEST_SHA1) && 
/* 351 */         !cosName.equals(COSName.DIGEST_SHA256) && 
/* 352 */         !cosName.equals(COSName.DIGEST_SHA384) && 
/* 353 */         !cosName.equals(COSName.DIGEST_SHA512) && 
/* 354 */         !cosName.equals(COSName.DIGEST_RIPEMD160))
/*     */       {
/* 356 */         throw new IllegalArgumentException("Specified digest " + cosName.getName() + " isn't allowed.");
/*     */       }
/*     */     } 
/* 359 */     this.dictionary.setItem(COSName.DIGEST_METHOD, (COSBase)COSArrayList.converterToCOSArray(digestMethod));
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
/*     */   public float getV() {
/* 374 */     return this.dictionary.getFloat(COSName.V);
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
/*     */   public void setV(float minimumRequiredCapability) {
/* 389 */     this.dictionary.setFloat(COSName.V, minimumRequiredCapability);
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
/*     */   public List<String> getReasons() {
/*     */     COSArrayList cOSArrayList;
/* 403 */     List<String> retval = null;
/* 404 */     COSArray fields = (COSArray)this.dictionary.getDictionaryObject(COSName.REASONS);
/*     */     
/* 406 */     if (fields != null) {
/*     */       
/* 408 */       List<String> actuals = new ArrayList<String>();
/* 409 */       for (int i = 0; i < fields.size(); i++) {
/*     */         
/* 411 */         String element = fields.getString(i);
/* 412 */         if (element != null)
/*     */         {
/* 414 */           actuals.add(element);
/*     */         }
/*     */       } 
/* 417 */       cOSArrayList = new COSArrayList(actuals, fields);
/*     */     } 
/* 419 */     return (List<String>)cOSArrayList;
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
/*     */   @Deprecated
/*     */   public void setReasonsd(List<String> reasons) {
/* 434 */     setReasons(reasons);
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
/*     */   public void setReasons(List<String> reasons) {
/* 446 */     this.dictionary.setItem(COSName.REASONS, (COSBase)COSArrayList.converterToCOSArray(reasons));
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
/*     */   public PDSeedValueMDP getMDP() {
/* 464 */     COSDictionary dict = (COSDictionary)this.dictionary.getDictionaryObject(COSName.MDP);
/* 465 */     PDSeedValueMDP mdp = null;
/* 466 */     if (dict != null)
/*     */     {
/* 468 */       mdp = new PDSeedValueMDP(dict);
/*     */     }
/* 470 */     return mdp;
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
/*     */   public void setMPD(PDSeedValueMDP mdp) {
/* 488 */     if (mdp != null)
/*     */     {
/* 490 */       this.dictionary.setItem(COSName.MDP, (COSBase)mdp.getCOSObject());
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
/*     */   public PDSeedValueCertificate getSeedValueCertificate() {
/* 502 */     COSBase base = this.dictionary.getDictionaryObject(COSName.CERT);
/* 503 */     PDSeedValueCertificate certificate = null;
/* 504 */     if (base instanceof COSDictionary) {
/*     */       
/* 506 */       COSDictionary dict = (COSDictionary)base;
/* 507 */       certificate = new PDSeedValueCertificate(dict);
/*     */     } 
/* 509 */     return certificate;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSeedValueCertificate(PDSeedValueCertificate certificate) {
/* 520 */     this.dictionary.setItem(COSName.CERT, certificate);
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
/*     */   public PDSeedValueTimeStamp getTimeStamp() {
/* 532 */     COSDictionary dict = (COSDictionary)this.dictionary.getDictionaryObject(COSName.TIME_STAMP);
/* 533 */     PDSeedValueTimeStamp timestamp = null;
/* 534 */     if (dict != null)
/*     */     {
/* 536 */       timestamp = new PDSeedValueTimeStamp(dict);
/*     */     }
/* 538 */     return timestamp;
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
/*     */   public void setTimeStamp(PDSeedValueTimeStamp timestamp) {
/* 550 */     if (timestamp != null)
/*     */     {
/* 552 */       this.dictionary.setItem(COSName.TIME_STAMP, (COSBase)timestamp.getCOSObject());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<String> getLegalAttestation() {
/*     */     COSArrayList cOSArrayList;
/* 564 */     List<String> retval = null;
/* 565 */     COSArray fields = (COSArray)this.dictionary.getDictionaryObject(COSName.LEGAL_ATTESTATION);
/*     */     
/* 567 */     if (fields != null) {
/*     */       
/* 569 */       List<String> actuals = new ArrayList<String>();
/* 570 */       for (int i = 0; i < fields.size(); i++) {
/*     */         
/* 572 */         String element = fields.getString(i);
/* 573 */         if (element != null)
/*     */         {
/* 575 */           actuals.add(element);
/*     */         }
/*     */       } 
/* 578 */       cOSArrayList = new COSArrayList(actuals, fields);
/*     */     } 
/* 580 */     return (List<String>)cOSArrayList;
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
/*     */   public void setLegalAttestation(List<String> legalAttestation) {
/* 592 */     this.dictionary.setItem(COSName.LEGAL_ATTESTATION, (COSBase)COSArrayList.converterToCOSArray(legalAttestation));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/PDSeedValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */