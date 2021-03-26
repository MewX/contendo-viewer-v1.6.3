/*     */ package org.apache.fontbox.cmap;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.HashMap;
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
/*     */ public class CMap
/*     */ {
/*  35 */   private static final Log LOG = LogFactory.getLog(CMap.class);
/*     */   
/*  37 */   private int wmode = 0;
/*  38 */   private String cmapName = null;
/*  39 */   private String cmapVersion = null;
/*  40 */   private int cmapType = -1;
/*     */   
/*  42 */   private String registry = null;
/*  43 */   private String ordering = null;
/*  44 */   private int supplement = 0;
/*     */   
/*  46 */   private int minCodeLength = 4;
/*     */   
/*     */   private int maxCodeLength;
/*     */   
/*  50 */   private final List<CodespaceRange> codespaceRanges = new ArrayList<CodespaceRange>();
/*     */ 
/*     */   
/*  53 */   private final Map<Integer, String> charToUnicode = new HashMap<Integer, String>();
/*     */ 
/*     */   
/*  56 */   private final Map<Integer, Integer> codeToCid = new HashMap<Integer, Integer>();
/*  57 */   private final List<CIDRange> codeToCidRanges = new ArrayList<CIDRange>();
/*     */   
/*     */   private static final String SPACE = " ";
/*  60 */   private int spaceMapping = -1;
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
/*     */   public boolean hasCIDMappings() {
/*  76 */     return (!this.codeToCid.isEmpty() || !this.codeToCidRanges.isEmpty());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasUnicodeMappings() {
/*  86 */     return !this.charToUnicode.isEmpty();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toUnicode(int code) {
/*  97 */     return this.charToUnicode.get(Integer.valueOf(code));
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
/*     */   public int readCode(InputStream in) throws IOException {
/* 110 */     byte[] bytes = new byte[this.maxCodeLength];
/* 111 */     in.read(bytes, 0, this.minCodeLength);
/* 112 */     for (int i = this.minCodeLength - 1; i < this.maxCodeLength; i++) {
/*     */       
/* 114 */       int byteCount = i + 1;
/* 115 */       for (CodespaceRange range : this.codespaceRanges) {
/*     */         
/* 117 */         if (range.isFullMatch(bytes, byteCount))
/*     */         {
/* 119 */           return toInt(bytes, byteCount);
/*     */         }
/*     */       } 
/* 122 */       if (byteCount < this.maxCodeLength)
/*     */       {
/* 124 */         bytes[byteCount] = (byte)in.read();
/*     */       }
/*     */     } 
/* 127 */     String seq = "";
/* 128 */     for (int j = 0; j < this.maxCodeLength; j++) {
/*     */       
/* 130 */       seq = seq + String.format("0x%02X (%04o) ", new Object[] { Byte.valueOf(bytes[j]), Byte.valueOf(bytes[j]) });
/*     */     } 
/* 132 */     LOG.warn("Invalid character code sequence " + seq + "in CMap " + this.cmapName);
/* 133 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static int toInt(byte[] data, int dataLen) {
/* 141 */     int code = 0;
/* 142 */     for (int i = 0; i < dataLen; i++) {
/*     */       
/* 144 */       code <<= 8;
/* 145 */       code |= data[i] & 0xFF;
/*     */     } 
/* 147 */     return code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int toCID(int code) {
/* 158 */     Integer cid = this.codeToCid.get(Integer.valueOf(code));
/* 159 */     if (cid != null)
/*     */     {
/* 161 */       return cid.intValue();
/*     */     }
/* 163 */     for (CIDRange range : this.codeToCidRanges) {
/*     */       
/* 165 */       int ch = range.map((char)code);
/* 166 */       if (ch != -1)
/*     */       {
/* 168 */         return ch;
/*     */       }
/*     */     } 
/* 171 */     return 0;
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
/*     */   private int getCodeFromArray(byte[] data, int offset, int length) {
/* 183 */     int code = 0;
/* 184 */     for (int i = 0; i < length; i++) {
/*     */       
/* 186 */       code <<= 8;
/* 187 */       code |= (data[offset + i] + 256) % 256;
/*     */     } 
/* 189 */     return code;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addCharMapping(byte[] codes, String unicode) {
/* 200 */     int code = getCodeFromArray(codes, 0, codes.length);
/* 201 */     this.charToUnicode.put(Integer.valueOf(code), unicode);
/*     */ 
/*     */     
/* 204 */     if (" ".equals(unicode))
/*     */     {
/* 206 */       this.spaceMapping = code;
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
/*     */   void addCIDMapping(int code, int cid) {
/* 218 */     this.codeToCid.put(Integer.valueOf(cid), Integer.valueOf(code));
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
/*     */   void addCIDRange(char from, char to, int cid) {
/* 231 */     CIDRange lastRange = null;
/* 232 */     if (!this.codeToCidRanges.isEmpty())
/*     */     {
/* 234 */       lastRange = this.codeToCidRanges.get(this.codeToCidRanges.size() - 1);
/*     */     }
/* 236 */     if (lastRange == null || !lastRange.extend(from, to, cid))
/*     */     {
/* 238 */       this.codeToCidRanges.add(new CIDRange(from, to, cid));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void addCodespaceRange(CodespaceRange range) {
/* 249 */     this.codespaceRanges.add(range);
/* 250 */     this.maxCodeLength = Math.max(this.maxCodeLength, range.getCodeLength());
/* 251 */     this.minCodeLength = Math.min(this.minCodeLength, range.getCodeLength());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void useCmap(CMap cmap) {
/* 262 */     for (CodespaceRange codespaceRange : cmap.codespaceRanges)
/*     */     {
/* 264 */       addCodespaceRange(codespaceRange);
/*     */     }
/* 266 */     this.charToUnicode.putAll(cmap.charToUnicode);
/* 267 */     this.codeToCid.putAll(cmap.codeToCid);
/* 268 */     this.codeToCidRanges.addAll(cmap.codeToCidRanges);
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
/*     */   public int getWMode() {
/* 280 */     return this.wmode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setWMode(int newWMode) {
/* 290 */     this.wmode = newWMode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getName() {
/* 300 */     return this.cmapName;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/* 310 */     this.cmapName = name;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVersion() {
/* 320 */     return this.cmapVersion;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVersion(String version) {
/* 330 */     this.cmapVersion = version;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getType() {
/* 340 */     return this.cmapType;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(int type) {
/* 350 */     this.cmapType = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getRegistry() {
/* 360 */     return this.registry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRegistry(String newRegistry) {
/* 370 */     this.registry = newRegistry;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getOrdering() {
/* 380 */     return this.ordering;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOrdering(String newOrdering) {
/* 390 */     this.ordering = newOrdering;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSupplement() {
/* 400 */     return this.supplement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSupplement(int newSupplement) {
/* 410 */     this.supplement = newSupplement;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getSpaceMapping() {
/* 420 */     return this.spaceMapping;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 426 */     return this.cmapName;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/fontbox/cmap/CMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */