/*     */ package org.apache.pdfbox.pdmodel.font.encoding;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class GlyphList
/*     */ {
/*  36 */   private static final Log LOG = LogFactory.getLog(GlyphList.class);
/*     */ 
/*     */   
/*  39 */   private static final GlyphList DEFAULT = load("glyphlist.txt", 4281);
/*     */ 
/*     */   
/*  42 */   private static final GlyphList ZAPF_DINGBATS = load("zapfdingbats.txt", 201);
/*     */   
/*     */   private final Map<String, String> nameToUnicode;
/*     */   
/*     */   private final Map<String, String> unicodeToName;
/*     */   
/*     */   private static GlyphList load(String filename, int numberOfEntries) {
/*  49 */     String path = "/org/apache/pdfbox/resources/glyphlist/";
/*     */     
/*     */     try {
/*  52 */       return new GlyphList(GlyphList.class.getResourceAsStream(path + filename), numberOfEntries);
/*     */     }
/*  54 */     catch (IOException e) {
/*     */       
/*  56 */       throw new RuntimeException(e);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*     */     try {
/*  65 */       String location = System.getProperty("glyphlist_ext");
/*  66 */       if (location != null)
/*     */       {
/*  68 */         throw new UnsupportedOperationException("glyphlist_ext is no longer supported, use GlyphList.DEFAULT.addGlyphs(Properties) instead");
/*     */       
/*     */       }
/*     */     }
/*  72 */     catch (SecurityException securityException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static GlyphList getAdobeGlyphList() {
/*  83 */     return DEFAULT;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static GlyphList getZapfDingbats() {
/*  91 */     return ZAPF_DINGBATS;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  99 */   private final Map<String, String> uniNameToUnicodeCache = new ConcurrentHashMap<String, String>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GlyphList(InputStream input, int numberOfEntries) throws IOException {
/* 110 */     this.nameToUnicode = new HashMap<String, String>(numberOfEntries);
/* 111 */     this.unicodeToName = new HashMap<String, String>(numberOfEntries);
/* 112 */     loadList(input);
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
/*     */   public GlyphList(GlyphList glyphList, InputStream input) throws IOException {
/* 124 */     this.nameToUnicode = new HashMap<String, String>(glyphList.nameToUnicode);
/* 125 */     this.unicodeToName = new HashMap<String, String>(glyphList.unicodeToName);
/* 126 */     loadList(input);
/*     */   }
/*     */ 
/*     */   
/*     */   private void loadList(InputStream input) throws IOException {
/* 131 */     BufferedReader in = new BufferedReader(new InputStreamReader(input, "ISO-8859-1"));
/*     */     
/*     */     try {
/* 134 */       while (in.ready()) {
/*     */         
/* 136 */         String line = in.readLine();
/* 137 */         if (line != null && !line.startsWith("#"))
/*     */         {
/* 139 */           String[] parts = line.split(";");
/* 140 */           if (parts.length < 2)
/*     */           {
/* 142 */             throw new IOException("Invalid glyph list entry: " + line);
/*     */           }
/*     */           
/* 145 */           String name = parts[0];
/* 146 */           String[] unicodeList = parts[1].split(" ");
/*     */           
/* 148 */           if (this.nameToUnicode.containsKey(name))
/*     */           {
/* 150 */             LOG.warn("duplicate value for " + name + " -> " + parts[1] + " " + (String)this.nameToUnicode
/* 151 */                 .get(name));
/*     */           }
/*     */           
/* 154 */           int[] codePoints = new int[unicodeList.length];
/* 155 */           int index = 0;
/* 156 */           for (String hex : unicodeList)
/*     */           {
/* 158 */             codePoints[index++] = Integer.parseInt(hex, 16);
/*     */           }
/* 160 */           String string = new String(codePoints, 0, codePoints.length);
/*     */ 
/*     */           
/* 163 */           this.nameToUnicode.put(name, string);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 173 */           boolean forceOverride = (WinAnsiEncoding.INSTANCE.contains(name) || MacRomanEncoding.INSTANCE.contains(name) || MacExpertEncoding.INSTANCE.contains(name) || SymbolEncoding.INSTANCE.contains(name) || ZapfDingbatsEncoding.INSTANCE.contains(name));
/* 174 */           if (!this.unicodeToName.containsKey(string) || forceOverride)
/*     */           {
/* 176 */             this.unicodeToName.put(string, name);
/*     */           }
/*     */         }
/*     */       
/*     */       } 
/*     */     } finally {
/*     */       
/* 183 */       in.close();
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
/*     */   public String codePointToName(int codePoint) {
/* 195 */     String name = this.unicodeToName.get(new String(new int[] { codePoint }, 0, 1));
/* 196 */     if (name == null)
/*     */     {
/* 198 */       return ".notdef";
/*     */     }
/* 200 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String sequenceToName(String unicodeSequence) {
/* 211 */     String name = this.unicodeToName.get(unicodeSequence);
/* 212 */     if (name == null)
/*     */     {
/* 214 */       return ".notdef";
/*     */     }
/* 216 */     return name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toUnicode(String name) {
/* 227 */     if (name == null)
/*     */     {
/* 229 */       return null;
/*     */     }
/*     */     
/* 232 */     String unicode = this.nameToUnicode.get(name);
/* 233 */     if (unicode != null)
/*     */     {
/* 235 */       return unicode;
/*     */     }
/*     */ 
/*     */     
/* 239 */     unicode = this.uniNameToUnicodeCache.get(name);
/* 240 */     if (unicode == null) {
/*     */ 
/*     */       
/* 243 */       if (name.indexOf('.') > 0) {
/*     */         
/* 245 */         unicode = toUnicode(name.substring(0, name.indexOf('.')));
/*     */       }
/* 247 */       else if (name.startsWith("uni") && name.length() == 7) {
/*     */ 
/*     */         
/* 250 */         int nameLength = name.length();
/* 251 */         StringBuilder uniStr = new StringBuilder();
/*     */         
/*     */         try {
/* 254 */           for (int chPos = 3; chPos + 4 <= nameLength; chPos += 4) {
/*     */             
/* 256 */             int codePoint = Integer.parseInt(name.substring(chPos, chPos + 4), 16);
/* 257 */             if (codePoint > 55295 && codePoint < 57344) {
/*     */               
/* 259 */               LOG.warn("Unicode character name with disallowed code area: " + name);
/*     */             }
/*     */             else {
/*     */               
/* 263 */               uniStr.append((char)codePoint);
/*     */             } 
/*     */           } 
/* 266 */           unicode = uniStr.toString();
/*     */         }
/* 268 */         catch (NumberFormatException nfe) {
/*     */           
/* 270 */           LOG.warn("Not a number in Unicode character name: " + name);
/*     */         }
/*     */       
/* 273 */       } else if (name.startsWith("u") && name.length() == 5) {
/*     */ 
/*     */         
/*     */         try {
/*     */           
/* 278 */           int codePoint = Integer.parseInt(name.substring(1), 16);
/* 279 */           if (codePoint > 55295 && codePoint < 57344)
/*     */           {
/* 281 */             LOG.warn("Unicode character name with disallowed code area: " + name);
/*     */           }
/*     */           else
/*     */           {
/* 285 */             unicode = String.valueOf((char)codePoint);
/*     */           }
/*     */         
/* 288 */         } catch (NumberFormatException nfe) {
/*     */           
/* 290 */           LOG.warn("Not a number in Unicode character name: " + name);
/*     */         } 
/*     */       } 
/* 293 */       if (unicode != null)
/*     */       {
/*     */         
/* 296 */         this.uniNameToUnicodeCache.put(name, unicode);
/*     */       }
/*     */     } 
/* 299 */     return unicode;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/font/encoding/GlyphList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */