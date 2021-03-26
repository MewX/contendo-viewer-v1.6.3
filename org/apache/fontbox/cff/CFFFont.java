/*     */ package org.apache.fontbox.cff;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.fontbox.FontBoxFont;
/*     */ import org.apache.fontbox.util.BoundingBox;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class CFFFont
/*     */   implements FontBoxFont
/*     */ {
/*     */   protected String fontName;
/*  37 */   protected final Map<String, Object> topDict = new LinkedHashMap<String, Object>();
/*     */ 
/*     */   
/*     */   protected CFFCharset charset;
/*     */ 
/*     */   
/*     */   protected byte[][] charStrings;
/*     */   
/*     */   protected byte[][] globalSubrIndex;
/*     */   
/*     */   private CFFParser.ByteSource source;
/*     */ 
/*     */   
/*     */   public String getName() {
/*  51 */     return this.fontName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setName(String name) {
/*  61 */     this.fontName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addValueToTopDict(String name, Object value) {
/*  72 */     if (value != null)
/*     */     {
/*  74 */       this.topDict.put(name, value);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Map<String, Object> getTopDict() {
/*  85 */     return this.topDict;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract List<Number> getFontMatrix();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public BoundingBox getFontBBox() {
/* 100 */     List<Number> numbers = (List<Number>)this.topDict.get("FontBBox");
/* 101 */     return new BoundingBox(numbers);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CFFCharset getCharset() {
/* 111 */     return this.charset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setCharset(CFFCharset charset) {
/* 121 */     this.charset = charset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public final List<byte[]> getCharStringBytes() {
/* 131 */     return (List)Arrays.asList(this.charStrings);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void setData(CFFParser.ByteSource source) {
/* 139 */     this.source = source;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte[] getData() throws IOException {
/* 147 */     return this.source.getBytes();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getNumCharStrings() {
/* 155 */     return this.charStrings.length;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void setGlobalSubrIndex(byte[][] globalSubrIndexValue) {
/* 165 */     this.globalSubrIndex = globalSubrIndexValue;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<byte[]> getGlobalSubrIndex() {
/* 175 */     return (List)Arrays.asList(this.globalSubrIndex);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract Type2CharString getType2CharString(int paramInt) throws IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 189 */     return getClass().getSimpleName() + "[name=" + this.fontName + ", topDict=" + this.topDict + ", charset=" + this.charset + ", charStrings=" + 
/* 190 */       Arrays.deepToString((Object[])this.charStrings) + "]";
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cff/CFFFont.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */