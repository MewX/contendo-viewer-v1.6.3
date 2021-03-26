/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.fontbox.EncodedFont;
/*     */ import org.apache.fontbox.encoding.Encoding;
/*     */ import org.apache.fontbox.type1.Type1CharStringReader;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CFFType1Font
/*     */   extends CFFFont
/*     */   implements EncodedFont
/*     */ {
/*  36 */   private final Map<String, Object> privateDict = new LinkedHashMap<String, Object>();
/*     */   
/*     */   private CFFEncoding encoding;
/*  39 */   private final Map<Integer, Type2CharString> charStringCache = new ConcurrentHashMap<Integer, Type2CharString>();
/*     */ 
/*     */   
/*  42 */   private final PrivateType1CharStringReader reader = new PrivateType1CharStringReader();
/*     */ 
/*     */ 
/*     */   
/*     */   private class PrivateType1CharStringReader
/*     */     implements Type1CharStringReader
/*     */   {
/*     */     private PrivateType1CharStringReader() {}
/*     */ 
/*     */     
/*     */     public Type1CharString getType1CharString(String name) throws IOException {
/*  53 */       return CFFType1Font.this.getType1CharString(name);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(String name) throws IOException {
/*  60 */     return getType1CharString(name).getPath();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth(String name) throws IOException {
/*  66 */     return getType1CharString(name).getWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(String name) {
/*  72 */     int sid = this.charset.getSID(name);
/*  73 */     int gid = this.charset.getGIDForSID(sid);
/*  74 */     return (gid != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getFontMatrix() {
/*  80 */     return (List<Number>)this.topDict.get("FontMatrix");
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
/*     */   public Type1CharString getType1CharString(String name) throws IOException {
/*  92 */     int gid = nameToGID(name);
/*     */ 
/*     */     
/*  95 */     return getType2CharString(gid, name);
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
/*     */   public int nameToGID(String name) {
/* 107 */     int sid = this.charset.getSID(name);
/* 108 */     return this.charset.getGIDForSID(sid);
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
/*     */   public Type2CharString getType2CharString(int gid) throws IOException {
/* 120 */     String name = "GID+" + gid;
/* 121 */     return getType2CharString(gid, name);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Type2CharString getType2CharString(int gid, String name) throws IOException {
/* 127 */     Type2CharString type2 = this.charStringCache.get(Integer.valueOf(gid));
/* 128 */     if (type2 == null) {
/*     */       
/* 130 */       byte[] bytes = null;
/* 131 */       if (gid < this.charStrings.length)
/*     */       {
/* 133 */         bytes = this.charStrings[gid];
/*     */       }
/* 135 */       if (bytes == null)
/*     */       {
/*     */         
/* 138 */         bytes = this.charStrings[0];
/*     */       }
/* 140 */       Type2CharStringParser parser = new Type2CharStringParser(this.fontName, name);
/* 141 */       List<Object> type2seq = parser.parse(bytes, this.globalSubrIndex, getLocalSubrIndex());
/*     */       
/* 143 */       type2 = new Type2CharString(this.reader, this.fontName, name, gid, type2seq, getDefaultWidthX(), getNominalWidthX());
/* 144 */       this.charStringCache.put(Integer.valueOf(gid), type2);
/*     */     } 
/* 146 */     return type2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getPrivateDict() {
/* 156 */     return this.privateDict;
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
/*     */   void addToPrivateDict(String name, Object value) {
/* 168 */     if (value != null)
/*     */     {
/* 170 */       this.privateDict.put(name, value);
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
/*     */   public CFFEncoding getEncoding() {
/* 182 */     return this.encoding;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setEncoding(CFFEncoding encoding) {
/* 192 */     this.encoding = encoding;
/*     */   }
/*     */ 
/*     */   
/*     */   private byte[][] getLocalSubrIndex() {
/* 197 */     return (byte[][])this.privateDict.get("Subrs");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private Object getProperty(String name) {
/* 203 */     Object topDictValue = this.topDict.get(name);
/* 204 */     if (topDictValue != null)
/*     */     {
/* 206 */       return topDictValue;
/*     */     }
/* 208 */     Object privateDictValue = this.privateDict.get(name);
/* 209 */     if (privateDictValue != null)
/*     */     {
/* 211 */       return privateDictValue;
/*     */     }
/* 213 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private int getDefaultWidthX() {
/* 218 */     Number num = (Number)getProperty("defaultWidthX");
/* 219 */     if (num == null)
/*     */     {
/* 221 */       return 1000;
/*     */     }
/* 223 */     return num.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   private int getNominalWidthX() {
/* 228 */     Number num = (Number)getProperty("nominalWidthX");
/* 229 */     if (num == null)
/*     */     {
/* 231 */       return 0;
/*     */     }
/* 233 */     return num.intValue();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFType1Font.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */