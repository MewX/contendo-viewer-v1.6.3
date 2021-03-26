/*     */ package org.apache.pdfbox.pdmodel.documentinterchange.taggedpdf;
/*     */ 
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSFloat;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSNumber;
/*     */ import org.apache.pdfbox.cos.COSString;
/*     */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*     */ import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDAttributeObject;
/*     */ import org.apache.pdfbox.pdmodel.graphics.color.PDGamma;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class PDStandardAttributeObject
/*     */   extends PDAttributeObject
/*     */ {
/*     */   protected static final float UNSPECIFIED = -1.0F;
/*     */   
/*     */   public PDStandardAttributeObject() {}
/*     */   
/*     */   public PDStandardAttributeObject(COSDictionary dictionary) {
/*  51 */     super(dictionary);
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
/*     */   public boolean isSpecified(String name) {
/*  64 */     return (getCOSObject().getDictionaryObject(name) != null);
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
/*     */   protected String getString(String name) {
/*  76 */     return getCOSObject().getString(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setString(String name, String value) {
/*  87 */     COSBase oldBase = getCOSObject().getDictionaryObject(name);
/*  88 */     getCOSObject().setString(name, value);
/*  89 */     COSBase newBase = getCOSObject().getDictionaryObject(name);
/*  90 */     potentiallyNotifyChanged(oldBase, newBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String[] getArrayOfString(String name) {
/* 101 */     COSBase v = getCOSObject().getDictionaryObject(name);
/* 102 */     if (v instanceof COSArray) {
/*     */       
/* 104 */       COSArray array = (COSArray)v;
/* 105 */       String[] strings = new String[array.size()];
/* 106 */       for (int i = 0; i < array.size(); i++)
/*     */       {
/* 108 */         strings[i] = ((COSName)array.getObject(i)).getName();
/*     */       }
/* 110 */       return strings;
/*     */     } 
/* 112 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setArrayOfString(String name, String[] values) {
/* 123 */     COSBase oldBase = getCOSObject().getDictionaryObject(name);
/* 124 */     COSArray array = new COSArray();
/* 125 */     for (String value : values)
/*     */     {
/* 127 */       array.add((COSBase)new COSString(value));
/*     */     }
/* 129 */     getCOSObject().setItem(name, (COSBase)array);
/* 130 */     COSBase newBase = getCOSObject().getDictionaryObject(name);
/* 131 */     potentiallyNotifyChanged(oldBase, newBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getName(String name) {
/* 142 */     return getCOSObject().getNameAsString(name);
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
/*     */   protected String getName(String name, String defaultValue) {
/* 154 */     return getCOSObject().getNameAsString(name, defaultValue);
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
/*     */   protected Object getNameOrArrayOfName(String name, String defaultValue) {
/* 166 */     COSBase v = getCOSObject().getDictionaryObject(name);
/* 167 */     if (v instanceof COSArray) {
/*     */       
/* 169 */       COSArray array = (COSArray)v;
/* 170 */       String[] names = new String[array.size()];
/* 171 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 173 */         COSBase item = array.getObject(i);
/* 174 */         if (item instanceof COSName)
/*     */         {
/* 176 */           names[i] = ((COSName)item).getName();
/*     */         }
/*     */       } 
/* 179 */       return names;
/*     */     } 
/* 181 */     if (v instanceof COSName)
/*     */     {
/* 183 */       return ((COSName)v).getName();
/*     */     }
/* 185 */     return defaultValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setName(String name, String value) {
/* 196 */     COSBase oldBase = getCOSObject().getDictionaryObject(name);
/* 197 */     getCOSObject().setName(name, value);
/* 198 */     COSBase newBase = getCOSObject().getDictionaryObject(name);
/* 199 */     potentiallyNotifyChanged(oldBase, newBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setArrayOfName(String name, String[] values) {
/* 210 */     COSBase oldBase = getCOSObject().getDictionaryObject(name);
/* 211 */     COSArray array = new COSArray();
/* 212 */     for (String value : values)
/*     */     {
/* 214 */       array.add((COSBase)COSName.getPDFName(value));
/*     */     }
/* 216 */     getCOSObject().setItem(name, (COSBase)array);
/* 217 */     COSBase newBase = getCOSObject().getDictionaryObject(name);
/* 218 */     potentiallyNotifyChanged(oldBase, newBase);
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
/*     */   protected Object getNumberOrName(String name, String defaultValue) {
/* 230 */     COSBase value = getCOSObject().getDictionaryObject(name);
/* 231 */     if (value instanceof COSNumber)
/*     */     {
/* 233 */       return Float.valueOf(((COSNumber)value).floatValue());
/*     */     }
/* 235 */     if (value instanceof COSName)
/*     */     {
/* 237 */       return ((COSName)value).getName();
/*     */     }
/* 239 */     return defaultValue;
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
/*     */   protected int getInteger(String name, int defaultValue) {
/* 251 */     return getCOSObject().getInt(name, defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setInteger(String name, int value) {
/* 262 */     COSBase oldBase = getCOSObject().getDictionaryObject(name);
/* 263 */     getCOSObject().setInt(name, value);
/* 264 */     COSBase newBase = getCOSObject().getDictionaryObject(name);
/* 265 */     potentiallyNotifyChanged(oldBase, newBase);
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
/*     */   protected float getNumber(String name, float defaultValue) {
/* 277 */     return getCOSObject().getFloat(name, defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected float getNumber(String name) {
/* 288 */     return getCOSObject().getFloat(name);
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
/*     */   protected Object getNumberOrArrayOfNumber(String name, float defaultValue) {
/* 305 */     COSBase v = getCOSObject().getDictionaryObject(name);
/* 306 */     if (v instanceof COSArray) {
/*     */       
/* 308 */       COSArray array = (COSArray)v;
/* 309 */       float[] values = new float[array.size()];
/* 310 */       for (int i = 0; i < array.size(); i++) {
/*     */         
/* 312 */         COSBase item = array.getObject(i);
/* 313 */         if (item instanceof COSNumber)
/*     */         {
/* 315 */           values[i] = ((COSNumber)item).floatValue();
/*     */         }
/*     */       } 
/* 318 */       return values;
/*     */     } 
/* 320 */     if (v instanceof COSNumber)
/*     */     {
/* 322 */       return Float.valueOf(((COSNumber)v).floatValue());
/*     */     }
/* 324 */     if (defaultValue == -1.0F)
/*     */     {
/* 326 */       return null;
/*     */     }
/* 328 */     return Float.valueOf(defaultValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setNumber(String name, float value) {
/* 339 */     COSBase oldBase = getCOSObject().getDictionaryObject(name);
/* 340 */     getCOSObject().setFloat(name, value);
/* 341 */     COSBase newBase = getCOSObject().getDictionaryObject(name);
/* 342 */     potentiallyNotifyChanged(oldBase, newBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setNumber(String name, int value) {
/* 353 */     COSBase oldBase = getCOSObject().getDictionaryObject(name);
/* 354 */     getCOSObject().setInt(name, value);
/* 355 */     COSBase newBase = getCOSObject().getDictionaryObject(name);
/* 356 */     potentiallyNotifyChanged(oldBase, newBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setArrayOfNumber(String name, float[] values) {
/* 367 */     COSArray array = new COSArray();
/* 368 */     for (float value : values)
/*     */     {
/* 370 */       array.add((COSBase)new COSFloat(value));
/*     */     }
/* 372 */     COSBase oldBase = getCOSObject().getDictionaryObject(name);
/* 373 */     getCOSObject().setItem(name, (COSBase)array);
/* 374 */     COSBase newBase = getCOSObject().getDictionaryObject(name);
/* 375 */     potentiallyNotifyChanged(oldBase, newBase);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected PDGamma getColor(String name) {
/* 386 */     COSArray c = (COSArray)getCOSObject().getDictionaryObject(name);
/* 387 */     if (c != null)
/*     */     {
/* 389 */       return new PDGamma(c);
/*     */     }
/* 391 */     return null;
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
/*     */   protected Object getColorOrFourColors(String name) {
/* 403 */     COSArray array = (COSArray)getCOSObject().getDictionaryObject(name);
/* 404 */     if (array == null)
/*     */     {
/* 406 */       return null;
/*     */     }
/* 408 */     if (array.size() == 3)
/*     */     {
/*     */       
/* 411 */       return new PDGamma(array);
/*     */     }
/* 413 */     if (array.size() == 4)
/*     */     {
/* 415 */       return new PDFourColours(array);
/*     */     }
/* 417 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setColor(String name, PDGamma value) {
/* 428 */     COSBase oldValue = getCOSObject().getDictionaryObject(name);
/* 429 */     getCOSObject().setItem(name, (COSObjectable)value);
/* 430 */     COSBase newValue = (value == null) ? null : value.getCOSObject();
/* 431 */     potentiallyNotifyChanged(oldValue, newValue);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setFourColors(String name, PDFourColours value) {
/* 442 */     COSBase oldValue = getCOSObject().getDictionaryObject(name);
/* 443 */     getCOSObject().setItem(name, value);
/* 444 */     COSBase newValue = (value == null) ? null : value.getCOSObject();
/* 445 */     potentiallyNotifyChanged(oldValue, newValue);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/documentinterchange/taggedpdf/PDStandardAttributeObject.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */