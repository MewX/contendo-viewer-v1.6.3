/*     */ package com.github.jaiimageio.impl.plugins.tiff;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.imageio.metadata.IIOMetadataFormat;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class TIFFMetadataFormat
/*     */   implements IIOMetadataFormat
/*     */ {
/*  64 */   protected Map elementInfoMap = new HashMap<Object, Object>();
/*  65 */   protected Map attrInfoMap = new HashMap<Object, Object>();
/*     */   
/*     */   protected String resourceBaseName;
/*     */   protected String rootName;
/*     */   
/*     */   public String getRootName() {
/*  71 */     return this.rootName;
/*     */   }
/*     */   
/*     */   private String getResource(String key, Locale locale) {
/*  75 */     if (locale == null) {
/*  76 */       locale = Locale.getDefault();
/*     */     }
/*     */     
/*     */     try {
/*  80 */       ResourceBundle bundle = ResourceBundle.getBundle(this.resourceBaseName, locale);
/*  81 */       return bundle.getString(key);
/*  82 */     } catch (MissingResourceException e) {
/*  83 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private TIFFElementInfo getElementInfo(String elementName) {
/*  88 */     if (elementName == null) {
/*  89 */       throw new IllegalArgumentException("elementName == null!");
/*     */     }
/*     */     
/*  92 */     TIFFElementInfo info = (TIFFElementInfo)this.elementInfoMap.get(elementName);
/*  93 */     if (info == null) {
/*  94 */       throw new IllegalArgumentException("No such element: " + elementName);
/*     */     }
/*     */     
/*  97 */     return info;
/*     */   }
/*     */   
/*     */   private TIFFAttrInfo getAttrInfo(String elementName, String attrName) {
/* 101 */     if (elementName == null) {
/* 102 */       throw new IllegalArgumentException("elementName == null!");
/*     */     }
/* 104 */     if (attrName == null) {
/* 105 */       throw new IllegalArgumentException("attrName == null!");
/*     */     }
/* 107 */     String key = elementName + "/" + attrName;
/* 108 */     TIFFAttrInfo info = (TIFFAttrInfo)this.attrInfoMap.get(key);
/* 109 */     if (info == null) {
/* 110 */       throw new IllegalArgumentException("No such attribute: " + key);
/*     */     }
/* 112 */     return info;
/*     */   }
/*     */   
/*     */   public int getElementMinChildren(String elementName) {
/* 116 */     TIFFElementInfo info = getElementInfo(elementName);
/* 117 */     return info.minChildren;
/*     */   }
/*     */   
/*     */   public int getElementMaxChildren(String elementName) {
/* 121 */     TIFFElementInfo info = getElementInfo(elementName);
/* 122 */     return info.maxChildren;
/*     */   }
/*     */   
/*     */   public String getElementDescription(String elementName, Locale locale) {
/* 126 */     if (!this.elementInfoMap.containsKey(elementName)) {
/* 127 */       throw new IllegalArgumentException("No such element: " + elementName);
/*     */     }
/*     */     
/* 130 */     return getResource(elementName, locale);
/*     */   }
/*     */   
/*     */   public int getChildPolicy(String elementName) {
/* 134 */     TIFFElementInfo info = getElementInfo(elementName);
/* 135 */     return info.childPolicy;
/*     */   }
/*     */   
/*     */   public String[] getChildNames(String elementName) {
/* 139 */     TIFFElementInfo info = getElementInfo(elementName);
/* 140 */     return info.childNames;
/*     */   }
/*     */   
/*     */   public String[] getAttributeNames(String elementName) {
/* 144 */     TIFFElementInfo info = getElementInfo(elementName);
/* 145 */     return info.attributeNames;
/*     */   }
/*     */   
/*     */   public int getAttributeValueType(String elementName, String attrName) {
/* 149 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 150 */     return info.valueType;
/*     */   }
/*     */   
/*     */   public int getAttributeDataType(String elementName, String attrName) {
/* 154 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 155 */     return info.dataType;
/*     */   }
/*     */   
/*     */   public boolean isAttributeRequired(String elementName, String attrName) {
/* 159 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 160 */     return info.isRequired;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAttributeDefaultValue(String elementName, String attrName) {
/* 165 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 166 */     return info.defaultValue;
/*     */   }
/*     */ 
/*     */   
/*     */   public String[] getAttributeEnumerations(String elementName, String attrName) {
/* 171 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 172 */     return info.enumerations;
/*     */   }
/*     */   
/*     */   public String getAttributeMinValue(String elementName, String attrName) {
/* 176 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 177 */     return info.minValue;
/*     */   }
/*     */   
/*     */   public String getAttributeMaxValue(String elementName, String attrName) {
/* 181 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 182 */     return info.maxValue;
/*     */   }
/*     */   
/*     */   public int getAttributeListMinLength(String elementName, String attrName) {
/* 186 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 187 */     return info.listMinLength;
/*     */   }
/*     */   
/*     */   public int getAttributeListMaxLength(String elementName, String attrName) {
/* 191 */     TIFFAttrInfo info = getAttrInfo(elementName, attrName);
/* 192 */     return info.listMaxLength;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAttributeDescription(String elementName, String attrName, Locale locale) {
/* 197 */     String key = elementName + "/" + attrName;
/* 198 */     if (!this.attrInfoMap.containsKey(key)) {
/* 199 */       throw new IllegalArgumentException("No such attribute: " + key);
/*     */     }
/* 201 */     return getResource(key, locale);
/*     */   }
/*     */   
/*     */   public int getObjectValueType(String elementName) {
/* 205 */     TIFFElementInfo info = getElementInfo(elementName);
/* 206 */     return info.objectValueType;
/*     */   }
/*     */   
/*     */   public Class getObjectClass(String elementName) {
/* 210 */     TIFFElementInfo info = getElementInfo(elementName);
/* 211 */     if (info.objectValueType == 0) {
/* 212 */       throw new IllegalArgumentException("Element cannot contain an object value: " + elementName);
/*     */     }
/*     */     
/* 215 */     return info.objectClass;
/*     */   }
/*     */   
/*     */   public Object getObjectDefaultValue(String elementName) {
/* 219 */     TIFFElementInfo info = getElementInfo(elementName);
/* 220 */     if (info.objectValueType == 0) {
/* 221 */       throw new IllegalArgumentException("Element cannot contain an object value: " + elementName);
/*     */     }
/*     */     
/* 224 */     return info.objectDefaultValue;
/*     */   }
/*     */   
/*     */   public Object[] getObjectEnumerations(String elementName) {
/* 228 */     TIFFElementInfo info = getElementInfo(elementName);
/* 229 */     if (info.objectValueType == 0) {
/* 230 */       throw new IllegalArgumentException("Element cannot contain an object value: " + elementName);
/*     */     }
/*     */     
/* 233 */     return info.objectEnumerations;
/*     */   }
/*     */   
/*     */   public Comparable getObjectMinValue(String elementName) {
/* 237 */     TIFFElementInfo info = getElementInfo(elementName);
/* 238 */     if (info.objectValueType == 0) {
/* 239 */       throw new IllegalArgumentException("Element cannot contain an object value: " + elementName);
/*     */     }
/*     */     
/* 242 */     return info.objectMinValue;
/*     */   }
/*     */   
/*     */   public Comparable getObjectMaxValue(String elementName) {
/* 246 */     TIFFElementInfo info = getElementInfo(elementName);
/* 247 */     if (info.objectValueType == 0) {
/* 248 */       throw new IllegalArgumentException("Element cannot contain an object value: " + elementName);
/*     */     }
/*     */     
/* 251 */     return info.objectMaxValue;
/*     */   }
/*     */   
/*     */   public int getObjectArrayMinLength(String elementName) {
/* 255 */     TIFFElementInfo info = getElementInfo(elementName);
/* 256 */     if (info.objectValueType == 0) {
/* 257 */       throw new IllegalArgumentException("Element cannot contain an object value: " + elementName);
/*     */     }
/*     */     
/* 260 */     return info.objectArrayMinLength;
/*     */   }
/*     */   
/*     */   public int getObjectArrayMaxLength(String elementName) {
/* 264 */     TIFFElementInfo info = getElementInfo(elementName);
/* 265 */     if (info.objectValueType == 0) {
/* 266 */       throw new IllegalArgumentException("Element cannot contain an object value: " + elementName);
/*     */     }
/*     */     
/* 269 */     return info.objectArrayMaxLength;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/github/jaiimageio/impl/plugins/tiff/TIFFMetadataFormat.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */