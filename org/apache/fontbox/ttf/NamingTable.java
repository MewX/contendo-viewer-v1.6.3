/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.Charset;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import org.apache.fontbox.util.Charsets;
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
/*     */ public class NamingTable
/*     */   extends TTFTable
/*     */ {
/*     */   public static final String TAG = "name";
/*     */   private List<NameRecord> nameRecords;
/*     */   private Map<Integer, Map<Integer, Map<Integer, Map<Integer, String>>>> lookupTable;
/*  44 */   private String fontFamily = null;
/*  45 */   private String fontSubFamily = null;
/*  46 */   private String psName = null;
/*     */ 
/*     */   
/*     */   NamingTable(TrueTypeFont font) {
/*  50 */     super(font);
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
/*     */   public void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/*  63 */     int formatSelector = data.readUnsignedShort();
/*  64 */     int numberOfNameRecords = data.readUnsignedShort();
/*  65 */     int offsetToStartOfStringStorage = data.readUnsignedShort();
/*  66 */     this.nameRecords = new ArrayList<NameRecord>(numberOfNameRecords);
/*  67 */     for (int i = 0; i < numberOfNameRecords; i++) {
/*     */       
/*  69 */       NameRecord nr = new NameRecord();
/*  70 */       nr.initData(ttf, data);
/*  71 */       this.nameRecords.add(nr);
/*     */     } 
/*     */     
/*  74 */     for (NameRecord nr : this.nameRecords) {
/*     */ 
/*     */       
/*  77 */       if (nr.getStringOffset() > getLength()) {
/*     */         
/*  79 */         nr.setString(null);
/*     */         
/*     */         continue;
/*     */       } 
/*  83 */       data.seek(getOffset() + 6L + (numberOfNameRecords * 2 * 6) + nr.getStringOffset());
/*  84 */       int platform = nr.getPlatformId();
/*  85 */       int encoding = nr.getPlatformEncodingId();
/*  86 */       Charset charset = Charsets.ISO_8859_1;
/*  87 */       if (platform == 3 && (encoding == 0 || encoding == 1)) {
/*     */         
/*  89 */         charset = Charsets.UTF_16;
/*     */       }
/*  91 */       else if (platform == 0) {
/*     */         
/*  93 */         charset = Charsets.UTF_16;
/*     */       }
/*  95 */       else if (platform == 2) {
/*     */         
/*  97 */         switch (encoding) {
/*     */           
/*     */           case 0:
/* 100 */             charset = Charsets.US_ASCII;
/*     */             break;
/*     */           
/*     */           case 1:
/* 104 */             charset = Charsets.ISO_10646;
/*     */             break;
/*     */           case 2:
/* 107 */             charset = Charsets.ISO_8859_1;
/*     */             break;
/*     */         } 
/*     */ 
/*     */       
/*     */       } 
/* 113 */       String string = data.readString(nr.getStringLength(), charset);
/* 114 */       nr.setString(string);
/*     */     } 
/*     */ 
/*     */     
/* 118 */     this.lookupTable = new HashMap<Integer, Map<Integer, Map<Integer, Map<Integer, String>>>>(this.nameRecords.size());
/* 119 */     for (NameRecord nr : this.nameRecords) {
/*     */ 
/*     */       
/* 122 */       Map<Integer, Map<Integer, Map<Integer, String>>> platformLookup = this.lookupTable.get(Integer.valueOf(nr.getNameId()));
/* 123 */       if (platformLookup == null) {
/*     */         
/* 125 */         platformLookup = new HashMap<Integer, Map<Integer, Map<Integer, String>>>();
/* 126 */         this.lookupTable.put(Integer.valueOf(nr.getNameId()), platformLookup);
/*     */       } 
/*     */       
/* 129 */       Map<Integer, Map<Integer, String>> encodingLookup = platformLookup.get(Integer.valueOf(nr.getPlatformId()));
/* 130 */       if (encodingLookup == null) {
/*     */         
/* 132 */         encodingLookup = new HashMap<Integer, Map<Integer, String>>();
/* 133 */         platformLookup.put(Integer.valueOf(nr.getPlatformId()), encodingLookup);
/*     */       } 
/*     */       
/* 136 */       Map<Integer, String> languageLookup = encodingLookup.get(Integer.valueOf(nr.getPlatformEncodingId()));
/* 137 */       if (languageLookup == null) {
/*     */         
/* 139 */         languageLookup = new HashMap<Integer, String>();
/* 140 */         encodingLookup.put(Integer.valueOf(nr.getPlatformEncodingId()), languageLookup);
/*     */       } 
/*     */       
/* 143 */       languageLookup.put(Integer.valueOf(nr.getLanguageId()), nr.getString());
/*     */     } 
/*     */ 
/*     */     
/* 147 */     this.fontFamily = getEnglishName(1);
/* 148 */     this.fontSubFamily = getEnglishName(2);
/*     */ 
/*     */     
/* 151 */     this.psName = getName(6, 1, 0, 0);
/*     */ 
/*     */ 
/*     */     
/* 155 */     if (this.psName == null)
/*     */     {
/* 157 */       this.psName = getName(6, 3, 1, 1033);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 162 */     if (this.psName != null)
/*     */     {
/* 164 */       this.psName = this.psName.trim();
/*     */     }
/*     */     
/* 167 */     this.initialized = true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getEnglishName(int nameId) {
/* 176 */     for (int i = 4; i >= 0; i--) {
/*     */ 
/*     */       
/* 179 */       String nameUni = getName(nameId, 0, i, 0);
/*     */ 
/*     */ 
/*     */       
/* 183 */       if (nameUni != null)
/*     */       {
/* 185 */         return nameUni;
/*     */       }
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 191 */     String nameWin = getName(nameId, 3, 1, 1033);
/*     */ 
/*     */ 
/*     */     
/* 195 */     if (nameWin != null)
/*     */     {
/* 197 */       return nameWin;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 202 */     String nameMac = getName(nameId, 1, 0, 0);
/*     */ 
/*     */ 
/*     */     
/* 206 */     if (nameMac != null)
/*     */     {
/* 208 */       return nameMac;
/*     */     }
/*     */     
/* 211 */     return null;
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
/*     */   public String getName(int nameId, int platformId, int encodingId, int languageId) {
/* 225 */     Map<Integer, Map<Integer, Map<Integer, String>>> platforms = this.lookupTable.get(Integer.valueOf(nameId));
/* 226 */     if (platforms == null)
/*     */     {
/* 228 */       return null;
/*     */     }
/* 230 */     Map<Integer, Map<Integer, String>> encodings = platforms.get(Integer.valueOf(platformId));
/* 231 */     if (encodings == null)
/*     */     {
/* 233 */       return null;
/*     */     }
/* 235 */     Map<Integer, String> languages = encodings.get(Integer.valueOf(encodingId));
/* 236 */     if (languages == null)
/*     */     {
/* 238 */       return null;
/*     */     }
/* 240 */     return languages.get(Integer.valueOf(languageId));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<NameRecord> getNameRecords() {
/* 250 */     return this.nameRecords;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontFamily() {
/* 260 */     return this.fontFamily;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getFontSubFamily() {
/* 270 */     return this.fontSubFamily;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPostScriptName() {
/* 280 */     return this.psName;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/NamingTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */