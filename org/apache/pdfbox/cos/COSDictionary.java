/*      */ package org.apache.pdfbox.cos;
/*      */ 
/*      */ import java.io.IOException;
/*      */ import java.io.InputStream;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Arrays;
/*      */ import java.util.Calendar;
/*      */ import java.util.Collection;
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.List;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import org.apache.pdfbox.io.IOUtils;
/*      */ import org.apache.pdfbox.pdmodel.common.COSObjectable;
/*      */ import org.apache.pdfbox.util.DateConverter;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class COSDictionary
/*      */   extends COSBase
/*      */   implements COSUpdateInfo
/*      */ {
/*      */   private static final String PATH_SEPARATOR = "/";
/*      */   private boolean needToBeUpdated;
/*   48 */   protected Map<COSName, COSBase> items = new LinkedHashMap<COSName, COSBase>();
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSDictionary() {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSDictionary(COSDictionary dict) {
/*   65 */     this.items.putAll(dict.items);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsValue(Object value) {
/*   77 */     boolean contains = this.items.containsValue(value);
/*   78 */     if (!contains && value instanceof COSObject)
/*      */     {
/*   80 */       contains = this.items.containsValue(((COSObject)value).getObject());
/*      */     }
/*   82 */     return contains;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSName getKeyForValue(Object value) {
/*   93 */     for (Map.Entry<COSName, COSBase> entry : this.items.entrySet()) {
/*      */       
/*   95 */       Object nextValue = entry.getValue();
/*   96 */       if (nextValue.equals(value) || (nextValue instanceof COSObject && ((COSObject)nextValue)
/*   97 */         .getObject()
/*   98 */         .equals(value)))
/*      */       {
/*  100 */         return entry.getKey();
/*      */       }
/*      */     } 
/*  103 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int size() {
/*  113 */     return this.items.size();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void clear() {
/*  121 */     this.items.clear();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSBase getDictionaryObject(String key) {
/*  134 */     return getDictionaryObject(COSName.getPDFName(key));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSBase getDictionaryObject(COSName firstKey, COSName secondKey) {
/*  150 */     COSBase retval = getDictionaryObject(firstKey);
/*  151 */     if (retval == null && secondKey != null)
/*      */     {
/*  153 */       retval = getDictionaryObject(secondKey);
/*      */     }
/*  155 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSBase getDictionaryObject(String[] keyList) {
/*  173 */     COSBase retval = null;
/*  174 */     for (int i = 0; i < keyList.length && retval == null; i++)
/*      */     {
/*  176 */       retval = getDictionaryObject(COSName.getPDFName(keyList[i]));
/*      */     }
/*  178 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSBase getDictionaryObject(COSName key) {
/*  191 */     COSBase retval = this.items.get(key);
/*  192 */     if (retval instanceof COSObject)
/*      */     {
/*  194 */       retval = ((COSObject)retval).getObject();
/*      */     }
/*  196 */     if (retval instanceof COSNull)
/*      */     {
/*  198 */       retval = null;
/*      */     }
/*  200 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItem(COSName key, COSBase value) {
/*  211 */     if (value == null) {
/*      */       
/*  213 */       removeItem(key);
/*      */     }
/*      */     else {
/*      */       
/*  217 */       this.items.put(key, value);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItem(COSName key, COSObjectable value) {
/*  229 */     COSBase base = null;
/*  230 */     if (value != null)
/*      */     {
/*  232 */       base = value.getCOSObject();
/*      */     }
/*  234 */     setItem(key, base);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItem(String key, COSObjectable value) {
/*  245 */     setItem(COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBoolean(String key, boolean value) {
/*  256 */     setItem(COSName.getPDFName(key), COSBoolean.getBoolean(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setBoolean(COSName key, boolean value) {
/*  267 */     setItem(key, COSBoolean.getBoolean(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setItem(String key, COSBase value) {
/*  278 */     setItem(COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setName(String key, String value) {
/*  290 */     setName(COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setName(COSName key, String value) {
/*  302 */     COSName name = null;
/*  303 */     if (value != null)
/*      */     {
/*  305 */       name = COSName.getPDFName(value);
/*      */     }
/*  307 */     setItem(key, name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDate(String key, Calendar date) {
/*  318 */     setDate(COSName.getPDFName(key), date);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setDate(COSName key, Calendar date) {
/*  329 */     setString(key, DateConverter.toString(date));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmbeddedDate(String embedded, String key, Calendar date) {
/*  341 */     setEmbeddedDate(embedded, COSName.getPDFName(key), date);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmbeddedDate(String embedded, COSName key, Calendar date) {
/*  353 */     COSDictionary dic = (COSDictionary)getDictionaryObject(embedded);
/*  354 */     if (dic == null && date != null) {
/*      */       
/*  356 */       dic = new COSDictionary();
/*  357 */       setItem(embedded, dic);
/*      */     } 
/*  359 */     if (dic != null)
/*      */     {
/*  361 */       dic.setDate(key, date);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setString(String key, String value) {
/*  374 */     setString(COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setString(COSName key, String value) {
/*  386 */     COSString name = null;
/*  387 */     if (value != null)
/*      */     {
/*  389 */       name = new COSString(value);
/*      */     }
/*  391 */     setItem(key, name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmbeddedString(String embedded, String key, String value) {
/*  404 */     setEmbeddedString(embedded, COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmbeddedString(String embedded, COSName key, String value) {
/*  417 */     COSDictionary dic = (COSDictionary)getDictionaryObject(embedded);
/*  418 */     if (dic == null && value != null) {
/*      */       
/*  420 */       dic = new COSDictionary();
/*  421 */       setItem(embedded, dic);
/*      */     } 
/*  423 */     if (dic != null)
/*      */     {
/*  425 */       dic.setString(key, value);
/*      */     }
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInt(String key, int value) {
/*  437 */     setInt(COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setInt(COSName key, int value) {
/*  448 */     setItem(key, COSInteger.get(value));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLong(String key, long value) {
/*  459 */     setLong(COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setLong(COSName key, long value) {
/*  470 */     COSInteger intVal = COSInteger.get(value);
/*  471 */     setItem(key, intVal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmbeddedInt(String embeddedDictionary, String key, int value) {
/*  483 */     setEmbeddedInt(embeddedDictionary, COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setEmbeddedInt(String embeddedDictionary, COSName key, int value) {
/*  495 */     COSDictionary embedded = (COSDictionary)getDictionaryObject(embeddedDictionary);
/*  496 */     if (embedded == null) {
/*      */       
/*  498 */       embedded = new COSDictionary();
/*  499 */       setItem(embeddedDictionary, embedded);
/*      */     } 
/*  501 */     embedded.setInt(key, value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloat(String key, float value) {
/*  512 */     setFloat(COSName.getPDFName(key), value);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFloat(COSName key, float value) {
/*  523 */     COSFloat fltVal = new COSFloat(value);
/*  524 */     setItem(key, fltVal);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void setFlag(COSName field, int bitFlag, boolean value) {
/*  536 */     int currentFlags = getInt(field, 0);
/*  537 */     if (value) {
/*      */       
/*  539 */       currentFlags |= bitFlag;
/*      */     }
/*      */     else {
/*      */       
/*  543 */       currentFlags &= bitFlag ^ 0xFFFFFFFF;
/*      */     } 
/*  545 */     setInt(field, currentFlags);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSName getCOSName(COSName key) {
/*  557 */     COSBase name = getDictionaryObject(key);
/*  558 */     if (name instanceof COSName)
/*      */     {
/*  560 */       return (COSName)name;
/*      */     }
/*  562 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSObject getCOSObject(COSName key) {
/*  574 */     COSBase object = getItem(key);
/*  575 */     if (object instanceof COSObject)
/*      */     {
/*  577 */       return (COSObject)object;
/*      */     }
/*  579 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSDictionary getCOSDictionary(COSName key) {
/*  591 */     COSBase dictionary = getDictionaryObject(key);
/*  592 */     if (dictionary instanceof COSDictionary)
/*      */     {
/*  594 */       return (COSDictionary)dictionary;
/*      */     }
/*  596 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSStream getCOSStream(COSName key) {
/*  608 */     COSBase base = getDictionaryObject(key);
/*  609 */     if (base instanceof COSStream)
/*      */     {
/*  611 */       return (COSStream)base;
/*      */     }
/*  613 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSArray getCOSArray(COSName key) {
/*  625 */     COSBase array = getDictionaryObject(key);
/*  626 */     if (array instanceof COSArray)
/*      */     {
/*  628 */       return (COSArray)array;
/*      */     }
/*  630 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSName getCOSName(COSName key, COSName defaultValue) {
/*  643 */     COSBase name = getDictionaryObject(key);
/*  644 */     if (name instanceof COSName)
/*      */     {
/*  646 */       return (COSName)name;
/*      */     }
/*  648 */     return defaultValue;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNameAsString(String key) {
/*  660 */     return getNameAsString(COSName.getPDFName(key));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNameAsString(COSName key) {
/*  672 */     String retval = null;
/*  673 */     COSBase name = getDictionaryObject(key);
/*  674 */     if (name instanceof COSName) {
/*      */       
/*  676 */       retval = ((COSName)name).getName();
/*      */     }
/*  678 */     else if (name instanceof COSString) {
/*      */       
/*  680 */       retval = ((COSString)name).getString();
/*      */     } 
/*  682 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNameAsString(String key, String defaultValue) {
/*  695 */     return getNameAsString(COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getNameAsString(COSName key, String defaultValue) {
/*  708 */     String retval = getNameAsString(key);
/*  709 */     if (retval == null)
/*      */     {
/*  711 */       retval = defaultValue;
/*      */     }
/*  713 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(String key) {
/*  725 */     return getString(COSName.getPDFName(key));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(COSName key) {
/*  737 */     String retval = null;
/*  738 */     COSBase value = getDictionaryObject(key);
/*  739 */     if (value instanceof COSString)
/*      */     {
/*  741 */       retval = ((COSString)value).getString();
/*      */     }
/*  743 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(String key, String defaultValue) {
/*  756 */     return getString(COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(COSName key, String defaultValue) {
/*  769 */     String retval = getString(key);
/*  770 */     if (retval == null)
/*      */     {
/*  772 */       retval = defaultValue;
/*      */     }
/*  774 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEmbeddedString(String embedded, String key) {
/*  787 */     return getEmbeddedString(embedded, COSName.getPDFName(key), (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEmbeddedString(String embedded, COSName key) {
/*  800 */     return getEmbeddedString(embedded, key, (String)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEmbeddedString(String embedded, String key, String defaultValue) {
/*  814 */     return getEmbeddedString(embedded, COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getEmbeddedString(String embedded, COSName key, String defaultValue) {
/*  828 */     String retval = defaultValue;
/*  829 */     COSBase base = getDictionaryObject(embedded);
/*  830 */     if (base instanceof COSDictionary)
/*      */     {
/*  832 */       retval = ((COSDictionary)base).getString(key, defaultValue);
/*      */     }
/*  834 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getDate(String key) {
/*  846 */     return getDate(COSName.getPDFName(key));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getDate(COSName key) {
/*  858 */     COSBase base = getDictionaryObject(key);
/*  859 */     if (base instanceof COSString)
/*      */     {
/*  861 */       return DateConverter.toCalendar((COSString)base);
/*      */     }
/*  863 */     return null;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getDate(String key, Calendar defaultValue) {
/*  875 */     return getDate(COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getDate(COSName key, Calendar defaultValue) {
/*  887 */     Calendar retval = getDate(key);
/*  888 */     if (retval == null)
/*      */     {
/*  890 */       retval = defaultValue;
/*      */     }
/*  892 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getEmbeddedDate(String embedded, String key) throws IOException {
/*  906 */     return getEmbeddedDate(embedded, COSName.getPDFName(key), (Calendar)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getEmbeddedDate(String embedded, COSName key) throws IOException {
/*  921 */     return getEmbeddedDate(embedded, key, (Calendar)null);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getEmbeddedDate(String embedded, String key, Calendar defaultValue) throws IOException {
/*  936 */     return getEmbeddedDate(embedded, COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Calendar getEmbeddedDate(String embedded, COSName key, Calendar defaultValue) throws IOException {
/*  951 */     Calendar retval = defaultValue;
/*  952 */     COSDictionary eDic = (COSDictionary)getDictionaryObject(embedded);
/*  953 */     if (eDic != null)
/*      */     {
/*  955 */       retval = eDic.getDate(key, defaultValue);
/*      */     }
/*  957 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(String key, boolean defaultValue) {
/*  971 */     return getBoolean(COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(COSName key, boolean defaultValue) {
/*  985 */     return getBoolean(key, null, defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getBoolean(COSName firstKey, COSName secondKey, boolean defaultValue) {
/* 1000 */     boolean retval = defaultValue;
/* 1001 */     COSBase bool = getDictionaryObject(firstKey, secondKey);
/* 1002 */     if (bool instanceof COSBoolean)
/*      */     {
/* 1004 */       retval = ((COSBoolean)bool).getValue();
/*      */     }
/* 1006 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEmbeddedInt(String embeddedDictionary, String key) {
/* 1019 */     return getEmbeddedInt(embeddedDictionary, COSName.getPDFName(key));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEmbeddedInt(String embeddedDictionary, COSName key) {
/* 1032 */     return getEmbeddedInt(embeddedDictionary, key, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEmbeddedInt(String embeddedDictionary, String key, int defaultValue) {
/* 1046 */     return getEmbeddedInt(embeddedDictionary, COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getEmbeddedInt(String embeddedDictionary, COSName key, int defaultValue) {
/* 1060 */     int retval = defaultValue;
/* 1061 */     COSDictionary embedded = (COSDictionary)getDictionaryObject(embeddedDictionary);
/* 1062 */     if (embedded != null)
/*      */     {
/* 1064 */       retval = embedded.getInt(key, defaultValue);
/*      */     }
/* 1066 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(String key) {
/* 1078 */     return getInt(COSName.getPDFName(key), -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(COSName key) {
/* 1090 */     return getInt(key, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(String[] keyList, int defaultValue) {
/* 1106 */     int retval = defaultValue;
/* 1107 */     COSBase obj = getDictionaryObject(keyList);
/* 1108 */     if (obj instanceof COSNumber)
/*      */     {
/* 1110 */       retval = ((COSNumber)obj).intValue();
/*      */     }
/* 1112 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(String key, int defaultValue) {
/* 1125 */     return getInt(COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(COSName key, int defaultValue) {
/* 1138 */     return getInt(key, null, defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(COSName firstKey, COSName secondKey) {
/* 1151 */     return getInt(firstKey, secondKey, -1);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getInt(COSName firstKey, COSName secondKey, int defaultValue) {
/* 1165 */     int retval = defaultValue;
/* 1166 */     COSBase obj = getDictionaryObject(firstKey, secondKey);
/* 1167 */     if (obj instanceof COSNumber)
/*      */     {
/* 1169 */       retval = ((COSNumber)obj).intValue();
/*      */     }
/* 1171 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(String key) {
/* 1184 */     return getLong(COSName.getPDFName(key), -1L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(COSName key) {
/* 1196 */     return getLong(key, -1L);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(String[] keyList, long defaultValue) {
/* 1212 */     long retval = defaultValue;
/* 1213 */     COSBase obj = getDictionaryObject(keyList);
/* 1214 */     if (obj instanceof COSNumber)
/*      */     {
/* 1216 */       retval = ((COSNumber)obj).longValue();
/*      */     }
/* 1218 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(String key, long defaultValue) {
/* 1231 */     return getLong(COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public long getLong(COSName key, long defaultValue) {
/* 1244 */     long retval = defaultValue;
/* 1245 */     COSBase obj = getDictionaryObject(key);
/* 1246 */     if (obj instanceof COSNumber)
/*      */     {
/* 1248 */       retval = ((COSNumber)obj).longValue();
/*      */     }
/* 1250 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(String key) {
/* 1262 */     return getFloat(COSName.getPDFName(key), -1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(COSName key) {
/* 1274 */     return getFloat(key, -1.0F);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(String key, float defaultValue) {
/* 1287 */     return getFloat(COSName.getPDFName(key), defaultValue);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public float getFloat(COSName key, float defaultValue) {
/* 1300 */     float retval = defaultValue;
/* 1301 */     COSBase obj = getDictionaryObject(key);
/* 1302 */     if (obj instanceof COSNumber)
/*      */     {
/* 1304 */       retval = ((COSNumber)obj).floatValue();
/*      */     }
/* 1306 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean getFlag(COSName field, int bitFlag) {
/* 1319 */     int ff = getInt(field, 0);
/* 1320 */     return ((ff & bitFlag) == bitFlag);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void removeItem(COSName key) {
/* 1330 */     this.items.remove(key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSBase getItem(COSName key) {
/* 1342 */     return this.items.get(key);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSBase getItem(String key) {
/* 1354 */     return getItem(COSName.getPDFName(key));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSBase getItem(COSName firstKey, COSName secondKey) {
/* 1369 */     COSBase retval = getItem(firstKey);
/* 1370 */     if (retval == null && secondKey != null)
/*      */     {
/* 1372 */       retval = getItem(secondKey);
/*      */     }
/* 1374 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<COSName> keySet() {
/* 1386 */     return this.items.keySet();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Set<Map.Entry<COSName, COSBase>> entrySet() {
/* 1398 */     return this.items.entrySet();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Collection<COSBase> getValues() {
/* 1408 */     return this.items.values();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public Object accept(ICOSVisitor visitor) throws IOException {
/* 1422 */     return visitor.visitFromDictionary(this);
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isNeedToBeUpdated() {
/* 1428 */     return this.needToBeUpdated;
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   public void setNeedToBeUpdated(boolean flag) {
/* 1434 */     this.needToBeUpdated = flag;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void addAll(COSDictionary dic) {
/* 1445 */     for (Map.Entry<COSName, COSBase> entry : dic.entrySet()) {
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/* 1451 */       if (!((COSName)entry.getKey()).getName().equals("Size") || 
/* 1452 */         !this.items.containsKey(COSName.getPDFName("Size")))
/*      */       {
/* 1454 */         setItem(entry.getKey(), entry.getValue());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(COSName name) {
/* 1467 */     return this.items.containsKey(name);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean containsKey(String name) {
/* 1478 */     return containsKey(COSName.getPDFName(name));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void mergeInto(COSDictionary dic) {
/* 1495 */     for (Map.Entry<COSName, COSBase> entry : dic.entrySet()) {
/*      */       
/* 1497 */       if (getItem(entry.getKey()) == null)
/*      */       {
/* 1499 */         setItem(entry.getKey(), entry.getValue());
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSBase getObjectFromPath(String objPath) {
/* 1513 */     String[] path = objPath.split("/");
/* 1514 */     COSBase retval = this;
/* 1515 */     for (String pathString : path) {
/*      */       
/* 1517 */       if (retval instanceof COSArray) {
/*      */         
/* 1519 */         int idx = Integer.parseInt(pathString.replaceAll("\\[", "").replaceAll("\\]", ""));
/* 1520 */         retval = ((COSArray)retval).getObject(idx);
/*      */       }
/* 1522 */       else if (retval instanceof COSDictionary) {
/*      */         
/* 1524 */         retval = ((COSDictionary)retval).getDictionaryObject(pathString);
/*      */       } 
/*      */     } 
/* 1527 */     return retval;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public COSDictionary asUnmodifiableDictionary() {
/* 1537 */     return new UnmodifiableCOSDictionary(this);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String toString() {
/*      */     try {
/* 1548 */       return getDictionaryString(this, new ArrayList<COSBase>());
/*      */     }
/* 1550 */     catch (IOException e) {
/*      */       
/* 1552 */       return "COSDictionary{" + e.getMessage() + "}";
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   private static String getDictionaryString(COSBase base, List<COSBase> objs) throws IOException {
/* 1558 */     if (base == null)
/*      */     {
/* 1560 */       return "null";
/*      */     }
/* 1562 */     if (objs.contains(base))
/*      */     {
/*      */       
/* 1565 */       return String.valueOf(base.hashCode());
/*      */     }
/* 1567 */     objs.add(base);
/* 1568 */     if (base instanceof COSDictionary) {
/*      */       
/* 1570 */       StringBuilder sb = new StringBuilder();
/* 1571 */       sb.append("COSDictionary{");
/* 1572 */       for (Map.Entry<COSName, COSBase> x : ((COSDictionary)base).entrySet()) {
/*      */         
/* 1574 */         sb.append(x.getKey());
/* 1575 */         sb.append(":");
/* 1576 */         sb.append(getDictionaryString(x.getValue(), objs));
/* 1577 */         sb.append(";");
/*      */       } 
/* 1579 */       sb.append("}");
/* 1580 */       if (base instanceof COSStream) {
/*      */         
/* 1582 */         InputStream stream = ((COSStream)base).createRawInputStream();
/* 1583 */         byte[] b = IOUtils.toByteArray(stream);
/* 1584 */         sb.append("COSStream{").append(Arrays.hashCode(b)).append("}");
/* 1585 */         stream.close();
/*      */       } 
/* 1587 */       return sb.toString();
/*      */     } 
/* 1589 */     if (base instanceof COSArray) {
/*      */       
/* 1591 */       StringBuilder sb = new StringBuilder();
/* 1592 */       sb.append("COSArray{");
/* 1593 */       for (COSBase x : ((COSArray)base).toList()) {
/*      */         
/* 1595 */         sb.append(getDictionaryString(x, objs));
/* 1596 */         sb.append(";");
/*      */       } 
/* 1598 */       sb.append("}");
/* 1599 */       return sb.toString();
/*      */     } 
/* 1601 */     if (base instanceof COSObject) {
/*      */       
/* 1603 */       COSObject obj = (COSObject)base;
/* 1604 */       return "COSObject{" + getDictionaryString(obj.getObject(), objs) + "}";
/*      */     } 
/* 1606 */     return base.toString();
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/cos/COSDictionary.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */