/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.awt.geom.GeneralPath;
/*     */ import java.io.IOException;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CFFCIDFont
/*     */   extends CFFFont
/*     */ {
/*     */   private String registry;
/*     */   private String ordering;
/*     */   private int supplement;
/*  40 */   private List<Map<String, Object>> fontDictionaries = new LinkedList<Map<String, Object>>();
/*  41 */   private List<Map<String, Object>> privateDictionaries = new LinkedList<Map<String, Object>>();
/*     */   
/*     */   private FDSelect fdSelect;
/*  44 */   private final Map<Integer, CIDKeyedType2CharString> charStringCache = new ConcurrentHashMap<Integer, CIDKeyedType2CharString>();
/*     */ 
/*     */   
/*  47 */   private final PrivateType1CharStringReader reader = new PrivateType1CharStringReader();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRegistry() {
/*  55 */     return this.registry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setRegistry(String registry) {
/*  65 */     this.registry = registry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOrdering() {
/*  75 */     return this.ordering;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setOrdering(String ordering) {
/*  85 */     this.ordering = ordering;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSupplement() {
/*  95 */     return this.supplement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setSupplement(int supplement) {
/* 105 */     this.supplement = supplement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Map<String, Object>> getFontDicts() {
/* 115 */     return this.fontDictionaries;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFontDict(List<Map<String, Object>> fontDict) {
/* 125 */     this.fontDictionaries = fontDict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Map<String, Object>> getPrivDicts() {
/* 135 */     return this.privateDictionaries;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setPrivDict(List<Map<String, Object>> privDict) {
/* 145 */     this.privateDictionaries = privDict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FDSelect getFdSelect() {
/* 155 */     return this.fdSelect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setFdSelect(FDSelect fdSelect) {
/* 165 */     this.fdSelect = fdSelect;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getDefaultWidthX(int gid) {
/* 175 */     int fdArrayIndex = this.fdSelect.getFDIndex(gid);
/* 176 */     if (fdArrayIndex == -1)
/*     */     {
/* 178 */       return 1000;
/*     */     }
/* 180 */     Map<String, Object> privDict = this.privateDictionaries.get(fdArrayIndex);
/* 181 */     return privDict.containsKey("defaultWidthX") ? ((Number)privDict.get("defaultWidthX")).intValue() : 1000;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int getNominalWidthX(int gid) {
/* 191 */     int fdArrayIndex = this.fdSelect.getFDIndex(gid);
/* 192 */     if (fdArrayIndex == -1)
/*     */     {
/* 194 */       return 0;
/*     */     }
/* 196 */     Map<String, Object> privDict = this.privateDictionaries.get(fdArrayIndex);
/* 197 */     return privDict.containsKey("nominalWidthX") ? ((Number)privDict.get("nominalWidthX")).intValue() : 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private byte[][] getLocalSubrIndex(int gid) {
/* 207 */     int fdArrayIndex = this.fdSelect.getFDIndex(gid);
/* 208 */     if (fdArrayIndex == -1)
/*     */     {
/* 210 */       return (byte[][])null;
/*     */     }
/* 212 */     Map<String, Object> privDict = this.privateDictionaries.get(fdArrayIndex);
/* 213 */     return (byte[][])privDict.get("Subrs");
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
/*     */   public CIDKeyedType2CharString getType2CharString(int cid) throws IOException {
/* 225 */     CIDKeyedType2CharString type2 = this.charStringCache.get(Integer.valueOf(cid));
/* 226 */     if (type2 == null) {
/*     */       
/* 228 */       int gid = this.charset.getGIDForCID(cid);
/*     */       
/* 230 */       byte[] bytes = this.charStrings[gid];
/* 231 */       if (bytes == null)
/*     */       {
/* 233 */         bytes = this.charStrings[0];
/*     */       }
/* 235 */       Type2CharStringParser parser = new Type2CharStringParser(this.fontName, cid);
/* 236 */       List<Object> type2seq = parser.parse(bytes, this.globalSubrIndex, getLocalSubrIndex(gid));
/*     */       
/* 238 */       type2 = new CIDKeyedType2CharString(this.reader, this.fontName, cid, gid, type2seq, getDefaultWidthX(gid), getNominalWidthX(gid));
/* 239 */       this.charStringCache.put(Integer.valueOf(cid), type2);
/*     */     } 
/* 241 */     return type2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<Number> getFontMatrix() {
/* 248 */     return (List<Number>)this.topDict.get("FontMatrix");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public GeneralPath getPath(String selector) throws IOException {
/* 254 */     int cid = selectorToCID(selector);
/* 255 */     return getType2CharString(cid).getPath();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getWidth(String selector) throws IOException {
/* 261 */     int cid = selectorToCID(selector);
/* 262 */     return getType2CharString(cid).getWidth();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasGlyph(String selector) throws IOException {
/* 268 */     int cid = selectorToCID(selector);
/* 269 */     return (cid != 0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int selectorToCID(String selector) {
/* 277 */     if (!selector.startsWith("\\"))
/*     */     {
/* 279 */       throw new IllegalArgumentException("Invalid selector");
/*     */     }
/* 281 */     return Integer.parseInt(selector.substring(1));
/*     */   }
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
/* 293 */       return CFFCIDFont.this.getType2CharString(0);
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFCIDFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */