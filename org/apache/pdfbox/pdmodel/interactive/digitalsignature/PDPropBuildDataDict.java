/*     */ package org.apache.pdfbox.pdmodel.interactive.digitalsignature;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public class PDPropBuildDataDict
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDPropBuildDataDict() {
/*  40 */     this.dictionary = new COSDictionary();
/*     */     
/*  42 */     this.dictionary.setDirect(true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDPropBuildDataDict(COSDictionary dict) {
/*  52 */     this.dictionary = dict;
/*     */     
/*  54 */     this.dictionary.setDirect(true);
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
/*  65 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/*  74 */     return this.dictionary.getNameAsString(COSName.NAME);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  84 */     this.dictionary.setName(COSName.NAME, name);
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
/*     */   public String getDate() {
/*  96 */     return this.dictionary.getString(COSName.DATE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDate(String date) {
/* 106 */     this.dictionary.setString(COSName.DATE, date);
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
/*     */   public void setVersion(String applicationVersion) {
/* 122 */     this.dictionary.setString("REx", applicationVersion);
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
/*     */   public String getVersion() {
/* 134 */     return this.dictionary.getString("REx");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public long getRevision() {
/* 144 */     return this.dictionary.getLong(COSName.R);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRevision(long revision) {
/* 154 */     this.dictionary.setLong(COSName.R, revision);
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
/*     */   public long getMinimumRevision() {
/* 168 */     return this.dictionary.getLong(COSName.V);
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
/*     */   public void setMinimumRevision(long revision) {
/* 182 */     this.dictionary.setLong(COSName.V, revision);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean getPreRelease() {
/* 193 */     return this.dictionary.getBoolean(COSName.PRE_RELEASE, false);
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
/*     */   public void setPreRelease(boolean preRelease) {
/* 205 */     this.dictionary.setBoolean(COSName.PRE_RELEASE, preRelease);
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
/*     */   public String getOS() {
/* 222 */     COSBase cosBase = this.dictionary.getItem(COSName.OS);
/* 223 */     if (cosBase instanceof COSArray)
/*     */     {
/* 225 */       return ((COSArray)cosBase).getName(0);
/*     */     }
/*     */     
/* 228 */     return this.dictionary.getString(COSName.OS);
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
/*     */   public void setOS(String os) {
/* 240 */     if (os == null) {
/*     */       
/* 242 */       this.dictionary.removeItem(COSName.OS);
/*     */     } else {
/*     */       COSArray cOSArray;
/*     */       
/* 246 */       COSBase osArray = this.dictionary.getItem(COSName.OS);
/* 247 */       if (!(osArray instanceof COSArray)) {
/*     */         
/* 249 */         cOSArray = new COSArray();
/* 250 */         cOSArray.setDirect(true);
/* 251 */         this.dictionary.setItem(COSName.OS, (COSBase)cOSArray);
/*     */       } 
/* 253 */       cOSArray.add(0, (COSBase)COSName.getPDFName(os));
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
/*     */   public boolean getNonEFontNoWarn() {
/* 268 */     return this.dictionary.getBoolean(COSName.NON_EFONT_NO_WARN, true);
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
/*     */   public boolean getTrustedMode() {
/* 291 */     return this.dictionary.getBoolean(COSName.TRUSTED_MODE, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTrustedMode(boolean trustedMode) {
/* 301 */     this.dictionary.setBoolean(COSName.TRUSTED_MODE, trustedMode);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/digitalsignature/PDPropBuildDataDict.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */