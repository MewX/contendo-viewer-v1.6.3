/*     */ package org.apache.xmlgraphics.xmp;
/*     */ 
/*     */ import java.util.Date;
/*     */ import java.util.TimeZone;
/*     */ import org.apache.xmlgraphics.util.DateFormatUtil;
/*     */ import org.apache.xmlgraphics.util.QName;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMPSchemaAdapter
/*     */ {
/*     */   protected Metadata meta;
/*     */   private XMPSchema schema;
/*     */   private boolean compact = true;
/*     */   
/*     */   public XMPSchemaAdapter(Metadata meta, XMPSchema schema) {
/*  44 */     if (meta == null) {
/*  45 */       throw new NullPointerException("Parameter meta must not be null");
/*     */     }
/*  47 */     if (schema == null) {
/*  48 */       throw new NullPointerException("Parameter schema must not be null");
/*     */     }
/*  50 */     this.meta = meta;
/*  51 */     this.schema = schema;
/*     */   }
/*     */ 
/*     */   
/*     */   public XMPSchema getSchema() {
/*  56 */     return this.schema;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected QName getQName(String propName) {
/*  65 */     return new QName(getSchema().getNamespace(), getSchema().getPreferredPrefix(), propName);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addStringToArray(String propName, String value, XMPArrayType arrayType) {
/*  75 */     if (value == null || value.length() == 0) {
/*  76 */       throw new IllegalArgumentException("Value must not be empty");
/*     */     }
/*  78 */     addObjectToArray(propName, value, arrayType);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addObjectToArray(String propName, Object value, XMPArrayType arrayType) {
/*  88 */     if (value == null) {
/*  89 */       throw new IllegalArgumentException("Value must not be null");
/*     */     }
/*  91 */     QName name = getQName(propName);
/*  92 */     XMPProperty prop = this.meta.getProperty(name);
/*  93 */     if (prop == null) {
/*  94 */       prop = new XMPProperty(name, value);
/*  95 */       this.meta.setProperty(prop);
/*  96 */       if (!this.compact) {
/*  97 */         prop.convertSimpleValueToArray(arrayType);
/*     */       }
/*     */     } else {
/* 100 */       prop.convertSimpleValueToArray(arrayType);
/* 101 */       prop.getArrayValue().add(value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean removeStringFromArray(String propName, String value) {
/* 112 */     if (value == null) {
/* 113 */       return false;
/*     */     }
/* 115 */     QName name = getQName(propName);
/* 116 */     XMPProperty prop = this.meta.getProperty(name);
/* 117 */     if (prop != null) {
/* 118 */       if (prop.isArray()) {
/* 119 */         XMPArray arr = prop.getArrayValue();
/* 120 */         boolean removed = arr.remove(value);
/* 121 */         if (arr.isEmpty()) {
/* 122 */           this.meta.removeProperty(name);
/*     */         }
/* 124 */         return removed;
/*     */       } 
/* 126 */       Object currentValue = prop.getValue();
/* 127 */       if (value.equals(currentValue)) {
/* 128 */         this.meta.removeProperty(name);
/* 129 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/* 133 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addStringToSeq(String propName, String value) {
/* 142 */     addStringToArray(propName, value, XMPArrayType.SEQ);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addStringToBag(String propName, String value) {
/* 151 */     addStringToArray(propName, value, XMPArrayType.BAG);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatISO8601Date(Date dt) {
/* 160 */     return formatISO8601Date(dt, TimeZone.getDefault());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String formatISO8601Date(Date dt, TimeZone tz) {
/* 170 */     return DateFormatUtil.formatISO8601(dt, tz);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void addDateToSeq(String propName, Date value) {
/* 179 */     String dt = formatISO8601Date(value);
/* 180 */     addStringToSeq(propName, dt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setDateValue(String propName, Date value) {
/* 189 */     String dt = formatISO8601Date(value);
/* 190 */     setValue(propName, dt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Date getDateValue(String propName) {
/* 199 */     String dt = getValue(propName);
/* 200 */     if (dt == null) {
/* 201 */       return null;
/*     */     }
/* 203 */     return DateFormatUtil.parseISO8601Date(dt);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setLangAlt(String propName, String lang, String value) {
/* 214 */     if (lang == null) {
/* 215 */       lang = "x-default";
/*     */     }
/* 217 */     QName name = getQName(propName);
/* 218 */     XMPProperty prop = this.meta.getProperty(name);
/*     */     
/* 220 */     if (prop == null) {
/* 221 */       if (value != null && value.length() > 0) {
/* 222 */         prop = new XMPProperty(name, value);
/* 223 */         prop.setXMLLang(lang);
/* 224 */         this.meta.setProperty(prop);
/*     */       } 
/*     */     } else {
/* 227 */       prop.convertSimpleValueToArray(XMPArrayType.ALT);
/* 228 */       XMPArray array = prop.getArrayValue();
/* 229 */       array.removeLangValue(lang);
/* 230 */       if (value != null && value.length() > 0) {
/* 231 */         array.add(value, lang);
/*     */       }
/* 233 */       else if (array.isEmpty()) {
/* 234 */         this.meta.removeProperty(name);
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
/*     */   protected void setValue(String propName, String value) {
/* 246 */     QName name = getQName(propName);
/* 247 */     XMPProperty prop = this.meta.getProperty(name);
/* 248 */     if (value != null && value.length() > 0) {
/* 249 */       if (prop != null) {
/* 250 */         prop.setValue(value);
/*     */       } else {
/* 252 */         prop = new XMPProperty(name, value);
/* 253 */         this.meta.setProperty(prop);
/*     */       }
/*     */     
/* 256 */     } else if (prop != null) {
/* 257 */       this.meta.removeProperty(name);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getValue(String propName) {
/* 268 */     QName name = getQName(propName);
/* 269 */     XMPProperty prop = this.meta.getProperty(name);
/* 270 */     if (prop == null) {
/* 271 */       return null;
/*     */     }
/* 273 */     return prop.getValue().toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String removeLangAlt(String lang, String propName) {
/* 284 */     QName name = getQName(propName);
/* 285 */     XMPProperty prop = this.meta.getProperty(name);
/*     */     
/* 287 */     if (prop != null && lang != null) {
/* 288 */       XMPArray array = prop.getArrayValue();
/* 289 */       if (array != null) {
/* 290 */         String str = array.removeLangValue(lang);
/* 291 */         if (array.isEmpty()) {
/* 292 */           this.meta.removeProperty(name);
/*     */         }
/* 294 */         return str;
/*     */       } 
/* 296 */       String removed = prop.getValue().toString();
/* 297 */       if (lang.equals(prop.getXMLLang())) {
/* 298 */         this.meta.removeProperty(name);
/*     */       }
/* 300 */       return removed;
/*     */     } 
/*     */     
/* 303 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String getLangAlt(String lang, String propName) {
/* 314 */     XMPProperty prop = this.meta.getProperty(getQName(propName));
/*     */     
/* 316 */     if (prop == null) {
/* 317 */       return null;
/*     */     }
/* 319 */     XMPArray array = prop.getArrayValue();
/* 320 */     if (array != null) {
/* 321 */       return array.getLangValue(lang);
/*     */     }
/* 323 */     return prop.getValue().toString();
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
/*     */   protected PropertyAccess findQualifiedStructure(String propName, QName qualifier, String qualifierValue) {
/* 337 */     XMPProperty prop = this.meta.getProperty(getQName(propName));
/*     */     
/* 339 */     if (prop != null) {
/* 340 */       XMPArray array = prop.getArrayValue();
/* 341 */       if (array != null) {
/* 342 */         for (int i = 0, c = array.getSize(); i < c; i++) {
/* 343 */           Object value = array.getValue(i);
/* 344 */           if (value instanceof PropertyAccess) {
/* 345 */             PropertyAccess pa = (PropertyAccess)value;
/* 346 */             XMPProperty q = pa.getProperty(qualifier);
/* 347 */             if (q != null && q.getValue().equals(qualifierValue)) {
/* 348 */               return pa;
/*     */             }
/*     */           } 
/*     */         } 
/* 352 */       } else if (prop.getStructureValue() != null) {
/* 353 */         PropertyAccess pa = prop.getStructureValue();
/* 354 */         XMPProperty q = pa.getProperty(qualifier);
/* 355 */         if (q != null && q.getValue().equals(qualifierValue)) {
/* 356 */           return pa;
/*     */         }
/*     */       } 
/*     */     } 
/* 360 */     return null;
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
/*     */   protected Object findQualifiedValue(String propName, QName qualifier, String qualifierValue) {
/* 372 */     PropertyAccess pa = findQualifiedStructure(propName, qualifier, qualifierValue);
/* 373 */     if (pa != null) {
/* 374 */       XMPProperty rdfValue = pa.getValueProperty();
/* 375 */       if (rdfValue != null) {
/* 376 */         return rdfValue.getValue();
/*     */       }
/*     */     } 
/* 379 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Object[] getObjectArray(String propName) {
/* 388 */     XMPProperty prop = this.meta.getProperty(getQName(propName));
/* 389 */     if (prop == null) {
/* 390 */       return null;
/*     */     }
/* 392 */     XMPArray array = prop.getArrayValue();
/* 393 */     if (array != null) {
/* 394 */       return array.toObjectArray();
/*     */     }
/* 396 */     return new Object[] { prop.getValue() };
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String[] getStringArray(String propName) {
/* 407 */     Object[] arr = getObjectArray(propName);
/* 408 */     if (arr == null) {
/* 409 */       return null;
/*     */     }
/* 411 */     String[] res = new String[arr.length];
/* 412 */     for (int i = 0, c = res.length; i < c; i++) {
/* 413 */       Object o = arr[i];
/* 414 */       if (o instanceof PropertyAccess) {
/* 415 */         XMPProperty prop = ((PropertyAccess)o).getValueProperty();
/* 416 */         res[i] = prop.getValue().toString();
/*     */       } else {
/* 418 */         res[i] = o.toString();
/*     */       } 
/*     */     } 
/* 421 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Date[] getDateArray(String propName) {
/* 430 */     Object[] arr = getObjectArray(propName);
/* 431 */     if (arr == null) {
/* 432 */       return null;
/*     */     }
/* 434 */     Date[] res = new Date[arr.length];
/* 435 */     for (int i = 0, c = res.length; i < c; i++) {
/* 436 */       Object obj = arr[i];
/* 437 */       if (obj instanceof Date) {
/* 438 */         res[i] = (Date)((Date)obj).clone();
/*     */       } else {
/* 440 */         res[i] = DateFormatUtil.parseISO8601Date(obj.toString());
/*     */       } 
/*     */     } 
/* 443 */     return res;
/*     */   }
/*     */   
/*     */   public void setCompact(boolean c) {
/* 447 */     this.compact = c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xmlgraphics/xmp/XMPSchemaAdapter.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */