/*     */ package org.apache.pdfbox.pdmodel.interactive.annotation;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColor;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDColorSpace;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceCMYK;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceGray;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDDeviceRGB;
/*     */ import org.apache.pdfbox.pdmodel.graphics.form.PDFormXObject;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PDAppearanceCharacteristicsDictionary
/*     */   implements COSObjectable
/*     */ {
/*     */   private final COSDictionary dictionary;
/*     */   
/*     */   public PDAppearanceCharacteristicsDictionary(COSDictionary dict) {
/*  48 */     this.dictionary = dict;
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
/*  59 */     return this.dictionary;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getRotation() {
/*  69 */     return getCOSObject().getInt(COSName.R, 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRotation(int rotation) {
/*  79 */     getCOSObject().setInt(COSName.R, rotation);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getBorderColour() {
/*  89 */     return getColor(COSName.BC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBorderColour(PDColor c) {
/*  99 */     getCOSObject().setItem(COSName.BC, (COSBase)c.toCOSArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDColor getBackground() {
/* 109 */     return getColor(COSName.BG);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBackground(PDColor c) {
/* 119 */     getCOSObject().setItem(COSName.BG, (COSBase)c.toCOSArray());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNormalCaption() {
/* 129 */     return getCOSObject().getString(COSName.CA);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNormalCaption(String caption) {
/* 139 */     getCOSObject().setString(COSName.CA, caption);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRolloverCaption() {
/* 149 */     return getCOSObject().getString(COSName.RC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRolloverCaption(String caption) {
/* 159 */     getCOSObject().setString(COSName.RC, caption);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getAlternateCaption() {
/* 169 */     return getCOSObject().getString(COSName.AC);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAlternateCaption(String caption) {
/* 179 */     getCOSObject().setString(COSName.AC, caption);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormXObject getNormalIcon() {
/* 189 */     COSBase i = getCOSObject().getDictionaryObject(COSName.I);
/* 190 */     if (i instanceof COSStream)
/*     */     {
/* 192 */       return new PDFormXObject((COSStream)i);
/*     */     }
/* 194 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormXObject getRolloverIcon() {
/* 204 */     COSBase i = getCOSObject().getDictionaryObject(COSName.RI);
/* 205 */     if (i instanceof COSStream)
/*     */     {
/* 207 */       return new PDFormXObject((COSStream)i);
/*     */     }
/* 209 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFormXObject getAlternateIcon() {
/* 219 */     COSBase i = getCOSObject().getDictionaryObject(COSName.IX);
/* 220 */     if (i instanceof COSStream)
/*     */     {
/* 222 */       return new PDFormXObject((COSStream)i);
/*     */     }
/* 224 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private PDColor getColor(COSName itemName) {
/* 229 */     COSBase c = getCOSObject().getItem(itemName);
/* 230 */     if (c instanceof COSArray) {
/*     */       PDDeviceGray pDDeviceGray; PDDeviceRGB pDDeviceRGB;
/*     */       PDDeviceCMYK pDDeviceCMYK;
/* 233 */       switch (((COSArray)c).size()) {
/*     */         
/*     */         case 1:
/* 236 */           pDDeviceGray = PDDeviceGray.INSTANCE;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 247 */           return new PDColor((COSArray)c, (PDColorSpace)pDDeviceGray);case 3: pDDeviceRGB = PDDeviceRGB.INSTANCE; return new PDColor((COSArray)c, (PDColorSpace)pDDeviceRGB);case 4: pDDeviceCMYK = PDDeviceCMYK.INSTANCE; return new PDColor((COSArray)c, (PDColorSpace)pDDeviceCMYK);
/*     */       }  return null;
/* 249 */     }  return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/interactive/annotation/PDAppearanceCharacteristicsDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */