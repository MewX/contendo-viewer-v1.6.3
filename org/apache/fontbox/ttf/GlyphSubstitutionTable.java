/*     */ package org.apache.fontbox.ttf;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ 
/*     */ public class GlyphSubstitutionTable
/*     */   extends TTFTable
/*     */ {
/*  42 */   private static final Log LOG = LogFactory.getLog(GlyphSubstitutionTable.class);
/*     */   
/*     */   public static final String TAG = "GSUB";
/*     */   
/*     */   private LinkedHashMap<String, ScriptTable> scriptList;
/*     */   
/*     */   private FeatureRecord[] featureList;
/*     */   
/*     */   private LookupTable[] lookupList;
/*  51 */   private final Map<Integer, Integer> lookupCache = new HashMap<Integer, Integer>();
/*  52 */   private final Map<Integer, Integer> reverseLookup = new HashMap<Integer, Integer>();
/*     */   
/*     */   private String lastUsedSupportedScript;
/*     */ 
/*     */   
/*     */   GlyphSubstitutionTable(TrueTypeFont font) {
/*  58 */     super(font);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void read(TrueTypeFont ttf, TTFDataStream data) throws IOException {
/*  64 */     long start = data.getCurrentPosition();
/*     */     
/*  66 */     int majorVersion = data.readUnsignedShort();
/*  67 */     int minorVersion = data.readUnsignedShort();
/*  68 */     int scriptListOffset = data.readUnsignedShort();
/*  69 */     int featureListOffset = data.readUnsignedShort();
/*  70 */     int lookupListOffset = data.readUnsignedShort();
/*     */     
/*  72 */     long featureVariationsOffset = -1L;
/*  73 */     if (minorVersion == 1L)
/*     */     {
/*  75 */       featureVariationsOffset = data.readUnsignedInt();
/*     */     }
/*     */     
/*  78 */     this.scriptList = readScriptList(data, start + scriptListOffset);
/*  79 */     this.featureList = readFeatureList(data, start + featureListOffset);
/*  80 */     this.lookupList = readLookupList(data, start + lookupListOffset);
/*     */   }
/*     */ 
/*     */   
/*     */   LinkedHashMap<String, ScriptTable> readScriptList(TTFDataStream data, long offset) throws IOException {
/*  85 */     data.seek(offset);
/*  86 */     int scriptCount = data.readUnsignedShort();
/*  87 */     ScriptRecord[] scriptRecords = new ScriptRecord[scriptCount];
/*  88 */     int[] scriptOffsets = new int[scriptCount]; int i;
/*  89 */     for (i = 0; i < scriptCount; i++) {
/*     */       
/*  91 */       ScriptRecord scriptRecord = new ScriptRecord();
/*  92 */       scriptRecord.scriptTag = data.readString(4);
/*  93 */       scriptOffsets[i] = data.readUnsignedShort();
/*  94 */       scriptRecords[i] = scriptRecord;
/*     */     } 
/*  96 */     for (i = 0; i < scriptCount; i++)
/*     */     {
/*  98 */       (scriptRecords[i]).scriptTable = readScriptTable(data, offset + scriptOffsets[i]);
/*     */     }
/* 100 */     LinkedHashMap<String, ScriptTable> resultScriptList = new LinkedHashMap<String, ScriptTable>(scriptCount);
/* 101 */     for (ScriptRecord scriptRecord : scriptRecords)
/*     */     {
/* 103 */       resultScriptList.put(scriptRecord.scriptTag, scriptRecord.scriptTable);
/*     */     }
/* 105 */     return resultScriptList;
/*     */   }
/*     */ 
/*     */   
/*     */   ScriptTable readScriptTable(TTFDataStream data, long offset) throws IOException {
/* 110 */     data.seek(offset);
/* 111 */     ScriptTable scriptTable = new ScriptTable();
/* 112 */     int defaultLangSys = data.readUnsignedShort();
/* 113 */     int langSysCount = data.readUnsignedShort();
/* 114 */     LangSysRecord[] langSysRecords = new LangSysRecord[langSysCount];
/* 115 */     int[] langSysOffsets = new int[langSysCount];
/* 116 */     String prevLangSysTag = ""; int i;
/* 117 */     for (i = 0; i < langSysCount; i++) {
/*     */       
/* 119 */       LangSysRecord langSysRecord = new LangSysRecord();
/* 120 */       langSysRecord.langSysTag = data.readString(4);
/* 121 */       if (i > 0 && langSysRecord.langSysTag.compareTo(prevLangSysTag) <= 0)
/*     */       {
/*     */ 
/*     */         
/* 125 */         throw new IOException("LangSysRecords not alphabetically sorted by LangSys tag: " + langSysRecord.langSysTag + " <= " + prevLangSysTag);
/*     */       }
/*     */       
/* 128 */       langSysOffsets[i] = data.readUnsignedShort();
/* 129 */       langSysRecords[i] = langSysRecord;
/* 130 */       prevLangSysTag = langSysRecord.langSysTag;
/*     */     } 
/* 132 */     if (defaultLangSys != 0)
/*     */     {
/* 134 */       scriptTable.defaultLangSysTable = readLangSysTable(data, offset + defaultLangSys);
/*     */     }
/* 136 */     for (i = 0; i < langSysCount; i++)
/*     */     {
/* 138 */       (langSysRecords[i]).langSysTable = readLangSysTable(data, offset + langSysOffsets[i]);
/*     */     }
/* 140 */     scriptTable.langSysTables = new LinkedHashMap<String, LangSysTable>(langSysCount);
/* 141 */     for (LangSysRecord langSysRecord : langSysRecords)
/*     */     {
/* 143 */       scriptTable.langSysTables.put(langSysRecord.langSysTag, langSysRecord.langSysTable);
/*     */     }
/* 145 */     return scriptTable;
/*     */   }
/*     */ 
/*     */   
/*     */   LangSysTable readLangSysTable(TTFDataStream data, long offset) throws IOException {
/* 150 */     data.seek(offset);
/* 151 */     LangSysTable langSysTable = new LangSysTable();
/*     */     
/* 153 */     int lookupOrder = data.readUnsignedShort();
/* 154 */     langSysTable.requiredFeatureIndex = data.readUnsignedShort();
/* 155 */     int featureIndexCount = data.readUnsignedShort();
/* 156 */     langSysTable.featureIndices = new int[featureIndexCount];
/* 157 */     for (int i = 0; i < featureIndexCount; i++)
/*     */     {
/* 159 */       langSysTable.featureIndices[i] = data.readUnsignedShort();
/*     */     }
/* 161 */     return langSysTable;
/*     */   }
/*     */ 
/*     */   
/*     */   FeatureRecord[] readFeatureList(TTFDataStream data, long offset) throws IOException {
/* 166 */     data.seek(offset);
/* 167 */     int featureCount = data.readUnsignedShort();
/* 168 */     FeatureRecord[] featureRecords = new FeatureRecord[featureCount];
/* 169 */     int[] featureOffsets = new int[featureCount]; int i;
/* 170 */     for (i = 0; i < featureCount; i++) {
/*     */       
/* 172 */       FeatureRecord featureRecord = new FeatureRecord();
/* 173 */       featureRecord.featureTag = data.readString(4);
/* 174 */       featureOffsets[i] = data.readUnsignedShort();
/* 175 */       featureRecords[i] = featureRecord;
/*     */     } 
/* 177 */     for (i = 0; i < featureCount; i++)
/*     */     {
/* 179 */       (featureRecords[i]).featureTable = readFeatureTable(data, offset + featureOffsets[i]);
/*     */     }
/* 181 */     return featureRecords;
/*     */   }
/*     */ 
/*     */   
/*     */   FeatureTable readFeatureTable(TTFDataStream data, long offset) throws IOException {
/* 186 */     data.seek(offset);
/* 187 */     FeatureTable featureTable = new FeatureTable();
/*     */     
/* 189 */     int featureParams = data.readUnsignedShort();
/* 190 */     int lookupIndexCount = data.readUnsignedShort();
/* 191 */     featureTable.lookupListIndices = new int[lookupIndexCount];
/* 192 */     for (int i = 0; i < lookupIndexCount; i++)
/*     */     {
/* 194 */       featureTable.lookupListIndices[i] = data.readUnsignedShort();
/*     */     }
/* 196 */     return featureTable;
/*     */   }
/*     */ 
/*     */   
/*     */   LookupTable[] readLookupList(TTFDataStream data, long offset) throws IOException {
/* 201 */     data.seek(offset);
/* 202 */     int lookupCount = data.readUnsignedShort();
/* 203 */     int[] lookups = new int[lookupCount];
/* 204 */     for (int i = 0; i < lookupCount; i++)
/*     */     {
/* 206 */       lookups[i] = data.readUnsignedShort();
/*     */     }
/* 208 */     LookupTable[] lookupTables = new LookupTable[lookupCount];
/* 209 */     for (int j = 0; j < lookupCount; j++)
/*     */     {
/* 211 */       lookupTables[j] = readLookupTable(data, offset + lookups[j]);
/*     */     }
/* 213 */     return lookupTables;
/*     */   }
/*     */ 
/*     */   
/*     */   LookupTable readLookupTable(TTFDataStream data, long offset) throws IOException {
/* 218 */     data.seek(offset);
/* 219 */     LookupTable lookupTable = new LookupTable();
/* 220 */     lookupTable.lookupType = data.readUnsignedShort();
/* 221 */     lookupTable.lookupFlag = data.readUnsignedShort();
/* 222 */     int subTableCount = data.readUnsignedShort();
/* 223 */     int[] subTableOffets = new int[subTableCount]; int i;
/* 224 */     for (i = 0; i < subTableCount; i++)
/*     */     {
/* 226 */       subTableOffets[i] = data.readUnsignedShort();
/*     */     }
/* 228 */     if ((lookupTable.lookupFlag & 0x10) != 0)
/*     */     {
/* 230 */       lookupTable.markFilteringSet = data.readUnsignedShort();
/*     */     }
/* 232 */     lookupTable.subTables = new LookupSubTable[subTableCount];
/* 233 */     switch (lookupTable.lookupType)
/*     */     
/*     */     { case 1:
/* 236 */         for (i = 0; i < subTableCount; i++)
/*     */         {
/* 238 */           lookupTable.subTables[i] = readLookupSubTable(data, offset + subTableOffets[i]);
/*     */         }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 245 */         return lookupTable; }  LOG.debug("Type " + lookupTable.lookupType + " GSUB lookup table is not supported and will be ignored"); return lookupTable;
/*     */   } LookupSubTable readLookupSubTable(TTFDataStream data, long offset) throws IOException {
/*     */     LookupTypeSingleSubstFormat1 lookupTypeSingleSubstFormat1;
/*     */     LookupTypeSingleSubstFormat2 lookupSubTable;
/*     */     int coverageOffset, glyphCount, i;
/* 250 */     data.seek(offset);
/* 251 */     int substFormat = data.readUnsignedShort();
/* 252 */     switch (substFormat) {
/*     */ 
/*     */       
/*     */       case 1:
/* 256 */         lookupTypeSingleSubstFormat1 = new LookupTypeSingleSubstFormat1();
/* 257 */         lookupTypeSingleSubstFormat1.substFormat = substFormat;
/* 258 */         coverageOffset = data.readUnsignedShort();
/* 259 */         lookupTypeSingleSubstFormat1.deltaGlyphID = data.readSignedShort();
/* 260 */         lookupTypeSingleSubstFormat1.coverageTable = readCoverageTable(data, offset + coverageOffset);
/* 261 */         return lookupTypeSingleSubstFormat1;
/*     */ 
/*     */       
/*     */       case 2:
/* 265 */         lookupSubTable = new LookupTypeSingleSubstFormat2();
/* 266 */         lookupSubTable.substFormat = substFormat;
/* 267 */         coverageOffset = data.readUnsignedShort();
/* 268 */         glyphCount = data.readUnsignedShort();
/* 269 */         lookupSubTable.substituteGlyphIDs = new int[glyphCount];
/* 270 */         for (i = 0; i < glyphCount; i++)
/*     */         {
/* 272 */           lookupSubTable.substituteGlyphIDs[i] = data.readUnsignedShort();
/*     */         }
/* 274 */         lookupSubTable.coverageTable = readCoverageTable(data, offset + coverageOffset);
/* 275 */         return lookupSubTable;
/*     */     } 
/*     */     
/* 278 */     throw new IllegalArgumentException("Unknown substFormat: " + substFormat);
/*     */   }
/*     */   CoverageTable readCoverageTable(TTFDataStream data, long offset) throws IOException {
/*     */     CoverageTableFormat1 coverageTableFormat1;
/*     */     CoverageTableFormat2 coverageTable;
/*     */     int glyphCount, rangeCount, i;
/* 284 */     data.seek(offset);
/* 285 */     int coverageFormat = data.readUnsignedShort();
/* 286 */     switch (coverageFormat) {
/*     */ 
/*     */       
/*     */       case 1:
/* 290 */         coverageTableFormat1 = new CoverageTableFormat1();
/* 291 */         coverageTableFormat1.coverageFormat = coverageFormat;
/* 292 */         glyphCount = data.readUnsignedShort();
/* 293 */         coverageTableFormat1.glyphArray = new int[glyphCount];
/* 294 */         for (i = 0; i < glyphCount; i++)
/*     */         {
/* 296 */           coverageTableFormat1.glyphArray[i] = data.readUnsignedShort();
/*     */         }
/* 298 */         return coverageTableFormat1;
/*     */ 
/*     */       
/*     */       case 2:
/* 302 */         coverageTable = new CoverageTableFormat2();
/* 303 */         coverageTable.coverageFormat = coverageFormat;
/* 304 */         rangeCount = data.readUnsignedShort();
/* 305 */         coverageTable.rangeRecords = new RangeRecord[rangeCount];
/* 306 */         for (i = 0; i < rangeCount; i++)
/*     */         {
/* 308 */           coverageTable.rangeRecords[i] = readRangeRecord(data);
/*     */         }
/* 310 */         return coverageTable;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 315 */     throw new IllegalArgumentException("Unknown coverage format: " + coverageFormat);
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
/*     */   private String selectScriptTag(String[] tags) {
/* 329 */     if (tags.length == 1) {
/*     */       
/* 331 */       String tag = tags[0];
/* 332 */       if ("Inherited".equals(tag) || ("DFLT"
/* 333 */         .equals(tag) && !this.scriptList.containsKey(tag))) {
/*     */ 
/*     */         
/* 336 */         if (this.lastUsedSupportedScript == null)
/*     */         {
/*     */           
/* 339 */           this.lastUsedSupportedScript = this.scriptList.keySet().iterator().next();
/*     */         }
/*     */ 
/*     */         
/* 343 */         return this.lastUsedSupportedScript;
/*     */       } 
/*     */     } 
/* 346 */     for (String tag : tags) {
/*     */       
/* 348 */       if (this.scriptList.containsKey(tag)) {
/*     */ 
/*     */ 
/*     */         
/* 352 */         this.lastUsedSupportedScript = tag;
/* 353 */         return this.lastUsedSupportedScript;
/*     */       } 
/*     */     } 
/* 356 */     return tags[0];
/*     */   }
/*     */ 
/*     */   
/*     */   private Collection<LangSysTable> getLangSysTables(String scriptTag) {
/* 361 */     Collection<LangSysTable> result = Collections.emptyList();
/* 362 */     ScriptTable scriptTable = this.scriptList.get(scriptTag);
/* 363 */     if (scriptTable != null)
/*     */     {
/* 365 */       if (scriptTable.defaultLangSysTable == null) {
/*     */         
/* 367 */         result = scriptTable.langSysTables.values();
/*     */       }
/*     */       else {
/*     */         
/* 371 */         result = new ArrayList<LangSysTable>(scriptTable.langSysTables.values());
/* 372 */         result.add(scriptTable.defaultLangSysTable);
/*     */       } 
/*     */     }
/* 375 */     return result;
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
/*     */ 
/*     */   
/*     */   private List<FeatureRecord> getFeatureRecords(Collection<LangSysTable> langSysTables, final List<String> enabledFeatures) {
/* 394 */     if (langSysTables.isEmpty())
/*     */     {
/* 396 */       return Collections.emptyList();
/*     */     }
/* 398 */     List<FeatureRecord> result = new ArrayList<FeatureRecord>();
/* 399 */     for (LangSysTable langSysTable : langSysTables) {
/*     */       
/* 401 */       int required = langSysTable.requiredFeatureIndex;
/* 402 */       if (required != 65535)
/*     */       {
/* 404 */         result.add(this.featureList[required]);
/*     */       }
/* 406 */       for (int featureIndex : langSysTable.featureIndices) {
/*     */         
/* 408 */         if (enabledFeatures == null || enabledFeatures
/* 409 */           .contains((this.featureList[featureIndex]).featureTag))
/*     */         {
/* 411 */           result.add(this.featureList[featureIndex]);
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 418 */     if (containsFeature(result, "vrt2"))
/*     */     {
/* 420 */       removeFeature(result, "vert");
/*     */     }
/*     */     
/* 423 */     if (enabledFeatures != null && result.size() > 1)
/*     */     {
/* 425 */       Collections.sort(result, new Comparator<FeatureRecord>()
/*     */           {
/*     */             
/*     */             public int compare(GlyphSubstitutionTable.FeatureRecord o1, GlyphSubstitutionTable.FeatureRecord o2)
/*     */             {
/* 430 */               int i1 = enabledFeatures.indexOf(o1.featureTag);
/* 431 */               int i2 = enabledFeatures.indexOf(o2.featureTag);
/* 432 */               return (i1 < i2) ? -1 : ((i1 == i2) ? 0 : 1);
/*     */             }
/*     */           });
/*     */     }
/*     */     
/* 437 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean containsFeature(List<FeatureRecord> featureRecords, String featureTag) {
/* 442 */     for (FeatureRecord featureRecord : featureRecords) {
/*     */       
/* 444 */       if (featureRecord.featureTag.equals(featureTag))
/*     */       {
/* 446 */         return true;
/*     */       }
/*     */     } 
/* 449 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void removeFeature(List<FeatureRecord> featureRecords, String featureTag) {
/* 454 */     Iterator<FeatureRecord> iter = featureRecords.iterator();
/* 455 */     while (iter.hasNext()) {
/*     */       
/* 457 */       if (((FeatureRecord)iter.next()).featureTag.equals(featureTag))
/*     */       {
/* 459 */         iter.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private int applyFeature(FeatureRecord featureRecord, int gid) {
/* 466 */     for (int lookupListIndex : featureRecord.featureTable.lookupListIndices) {
/*     */       
/* 468 */       LookupTable lookupTable = this.lookupList[lookupListIndex];
/* 469 */       if (lookupTable.lookupType != 1) {
/*     */         
/* 471 */         LOG.debug("Skipping GSUB feature '" + featureRecord.featureTag + "' because it requires unsupported lookup table type " + lookupTable.lookupType);
/*     */       }
/*     */       else {
/*     */         
/* 475 */         gid = doLookup(lookupTable, gid);
/*     */       } 
/* 477 */     }  return gid;
/*     */   }
/*     */ 
/*     */   
/*     */   private int doLookup(LookupTable lookupTable, int gid) {
/* 482 */     for (LookupSubTable lookupSubtable : lookupTable.subTables) {
/*     */       
/* 484 */       int coverageIndex = lookupSubtable.coverageTable.getCoverageIndex(gid);
/* 485 */       if (coverageIndex >= 0)
/*     */       {
/* 487 */         return lookupSubtable.doSubstitution(gid, coverageIndex);
/*     */       }
/*     */     } 
/* 490 */     return gid;
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
/*     */   
/*     */   public int getSubstitution(int gid, String[] scriptTags, List<String> enabledFeatures) {
/* 508 */     if (gid == -1)
/*     */     {
/* 510 */       return -1;
/*     */     }
/* 512 */     Integer cached = this.lookupCache.get(Integer.valueOf(gid));
/* 513 */     if (cached != null)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 518 */       return cached.intValue();
/*     */     }
/* 520 */     String scriptTag = selectScriptTag(scriptTags);
/* 521 */     Collection<LangSysTable> langSysTables = getLangSysTables(scriptTag);
/* 522 */     List<FeatureRecord> featureRecords = getFeatureRecords(langSysTables, enabledFeatures);
/* 523 */     int sgid = gid;
/* 524 */     for (FeatureRecord featureRecord : featureRecords)
/*     */     {
/* 526 */       sgid = applyFeature(featureRecord, sgid);
/*     */     }
/* 528 */     this.lookupCache.put(Integer.valueOf(gid), Integer.valueOf(sgid));
/* 529 */     this.reverseLookup.put(Integer.valueOf(sgid), Integer.valueOf(gid));
/* 530 */     return sgid;
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
/*     */   public int getUnsubstitution(int sgid) {
/* 544 */     Integer gid = this.reverseLookup.get(Integer.valueOf(sgid));
/* 545 */     if (gid == null) {
/*     */       
/* 547 */       LOG.warn("Trying to un-substitute a never-before-seen gid: " + sgid);
/* 548 */       return sgid;
/*     */     } 
/* 550 */     return gid.intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   RangeRecord readRangeRecord(TTFDataStream data) throws IOException {
/* 555 */     RangeRecord rangeRecord = new RangeRecord();
/* 556 */     rangeRecord.startGlyphID = data.readUnsignedShort();
/* 557 */     rangeRecord.endGlyphID = data.readUnsignedShort();
/* 558 */     rangeRecord.startCoverageIndex = data.readUnsignedShort();
/* 559 */     return rangeRecord;
/*     */   }
/*     */ 
/*     */   
/*     */   static class ScriptRecord
/*     */   {
/*     */     String scriptTag;
/*     */     
/*     */     GlyphSubstitutionTable.ScriptTable scriptTable;
/*     */ 
/*     */     
/*     */     public String toString() {
/* 571 */       return String.format("ScriptRecord[scriptTag=%s]", new Object[] { this.scriptTag });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class ScriptTable
/*     */   {
/*     */     GlyphSubstitutionTable.LangSysTable defaultLangSysTable;
/*     */     
/*     */     LinkedHashMap<String, GlyphSubstitutionTable.LangSysTable> langSysTables;
/*     */     
/*     */     public String toString() {
/* 583 */       return String.format("ScriptTable[hasDefault=%s,langSysRecordsCount=%d]", new Object[] {
/* 584 */             Boolean.valueOf((this.defaultLangSysTable != null)), Integer.valueOf(this.langSysTables.size())
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class LangSysRecord
/*     */   {
/*     */     String langSysTag;
/*     */     
/*     */     GlyphSubstitutionTable.LangSysTable langSysTable;
/*     */     
/*     */     public String toString() {
/* 597 */       return String.format("LangSysRecord[langSysTag=%s]", new Object[] { this.langSysTag });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class LangSysTable
/*     */   {
/*     */     int requiredFeatureIndex;
/*     */     
/*     */     int[] featureIndices;
/*     */     
/*     */     public String toString() {
/* 609 */       return String.format("LangSysTable[requiredFeatureIndex=%d]", new Object[] { Integer.valueOf(this.requiredFeatureIndex) });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class FeatureRecord
/*     */   {
/*     */     String featureTag;
/*     */     
/*     */     GlyphSubstitutionTable.FeatureTable featureTable;
/*     */     
/*     */     public String toString() {
/* 621 */       return String.format("FeatureRecord[featureTag=%s]", new Object[] { this.featureTag });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class FeatureTable
/*     */   {
/*     */     int[] lookupListIndices;
/*     */ 
/*     */     
/*     */     public String toString() {
/* 632 */       return String.format("FeatureTable[lookupListIndiciesCount=%d]", new Object[] {
/* 633 */             Integer.valueOf(this.lookupListIndices.length)
/*     */           });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class LookupTable
/*     */   {
/*     */     int lookupType;
/*     */     int lookupFlag;
/*     */     int markFilteringSet;
/*     */     GlyphSubstitutionTable.LookupSubTable[] subTables;
/*     */     
/*     */     public String toString() {
/* 647 */       return String.format("LookupTable[lookupType=%d,lookupFlag=%d,markFilteringSet=%d]", new Object[] {
/* 648 */             Integer.valueOf(this.lookupType), Integer.valueOf(this.lookupFlag), Integer.valueOf(this.markFilteringSet)
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class LookupSubTable
/*     */   {
/*     */     int substFormat;
/*     */     GlyphSubstitutionTable.CoverageTable coverageTable;
/*     */     
/*     */     abstract int doSubstitution(int param1Int1, int param1Int2);
/*     */   }
/*     */   
/*     */   static class LookupTypeSingleSubstFormat1
/*     */     extends LookupSubTable
/*     */   {
/*     */     short deltaGlyphID;
/*     */     
/*     */     int doSubstitution(int gid, int coverageIndex) {
/* 667 */       return (coverageIndex < 0) ? gid : (gid + this.deltaGlyphID);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 673 */       return String.format("LookupTypeSingleSubstFormat1[substFormat=%d,deltaGlyphID=%d]", new Object[] {
/* 674 */             Integer.valueOf(this.substFormat), Short.valueOf(this.deltaGlyphID)
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   static class LookupTypeSingleSubstFormat2
/*     */     extends LookupSubTable
/*     */   {
/*     */     int[] substituteGlyphIDs;
/*     */     
/*     */     int doSubstitution(int gid, int coverageIndex) {
/* 685 */       return (coverageIndex < 0) ? gid : this.substituteGlyphIDs[coverageIndex];
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 691 */       return String.format("LookupTypeSingleSubstFormat2[substFormat=%d,substituteGlyphIDs=%s]", new Object[] {
/*     */             
/* 693 */             Integer.valueOf(this.substFormat), Arrays.toString(this.substituteGlyphIDs)
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   static abstract class CoverageTable
/*     */   {
/*     */     int coverageFormat;
/*     */     
/*     */     abstract int getCoverageIndex(int param1Int);
/*     */   }
/*     */   
/*     */   static class CoverageTableFormat1
/*     */     extends CoverageTable
/*     */   {
/*     */     int[] glyphArray;
/*     */     
/*     */     int getCoverageIndex(int gid) {
/* 711 */       return Arrays.binarySearch(this.glyphArray, gid);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 717 */       return String.format("CoverageTableFormat1[coverageFormat=%d,glyphArray=%s]", new Object[] {
/* 718 */             Integer.valueOf(this.coverageFormat), Arrays.toString(this.glyphArray)
/*     */           });
/*     */     }
/*     */   }
/*     */   
/*     */   static class CoverageTableFormat2
/*     */     extends CoverageTable
/*     */   {
/*     */     GlyphSubstitutionTable.RangeRecord[] rangeRecords;
/*     */     
/*     */     int getCoverageIndex(int gid) {
/* 729 */       for (GlyphSubstitutionTable.RangeRecord rangeRecord : this.rangeRecords) {
/*     */         
/* 731 */         if (rangeRecord.startGlyphID <= gid && gid <= rangeRecord.endGlyphID)
/*     */         {
/* 733 */           return rangeRecord.startCoverageIndex + gid - rangeRecord.startGlyphID;
/*     */         }
/*     */       } 
/* 736 */       return -1;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 742 */       return String.format("CoverageTableFormat2[coverageFormat=%d]", new Object[] { Integer.valueOf(this.coverageFormat) });
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   static class RangeRecord
/*     */   {
/*     */     int startGlyphID;
/*     */     
/*     */     int endGlyphID;
/*     */     int startCoverageIndex;
/*     */     
/*     */     public String toString() {
/* 755 */       return String.format("RangeRecord[startGlyphID=%d,endGlyphID=%d,startCoverageIndex=%d]", new Object[] {
/* 756 */             Integer.valueOf(this.startGlyphID), Integer.valueOf(this.endGlyphID), Integer.valueOf(this.startCoverageIndex)
/*     */           });
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/ttf/GlyphSubstitutionTable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */