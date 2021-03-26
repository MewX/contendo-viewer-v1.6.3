/*     */ package org.apache.pdfbox.pdfparser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeMap;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.pdfbox.cos.COSArray;
/*     */ import org.apache.pdfbox.cos.COSBase;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSDocument;
/*     */ import org.apache.pdfbox.cos.COSInteger;
/*     */ import org.apache.pdfbox.cos.COSName;
/*     */ import org.apache.pdfbox.cos.COSObject;
/*     */ import org.apache.pdfbox.cos.COSStream;
/*     */ import org.apache.pdfbox.pdfwriter.COSWriterXRefEntry;
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
/*     */ public class PDFXRefStream
/*     */   implements PDFXRef
/*     */ {
/*     */   private static final int ENTRY_OBJSTREAM = 2;
/*     */   private static final int ENTRY_NORMAL = 1;
/*     */   private static final int ENTRY_FREE = 0;
/*     */   private final Map<Long, Object> streamData;
/*     */   private final Set<Long> objectNumbers;
/*     */   private final COSStream stream;
/*  57 */   private long size = -1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public PDFXRefStream() {
/*  67 */     this.stream = new COSStream();
/*  68 */     this.streamData = new TreeMap<Long, Object>();
/*  69 */     this.objectNumbers = new TreeSet<Long>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public PDFXRefStream(COSDocument cosDocument) {
/*  79 */     this.stream = cosDocument.createCOSStream();
/*  80 */     this.streamData = new TreeMap<Long, Object>();
/*  81 */     this.objectNumbers = new TreeSet<Long>();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public COSStream getStream() throws IOException {
/*  91 */     this.stream.setItem(COSName.TYPE, (COSBase)COSName.XREF);
/*  92 */     if (this.size == -1L)
/*     */     {
/*  94 */       throw new IllegalArgumentException("size is not set in xrefstream");
/*     */     }
/*  96 */     this.stream.setLong(COSName.SIZE, this.size);
/*     */     
/*  98 */     List<Long> indexEntry = getIndexEntry();
/*  99 */     COSArray indexAsArray = new COSArray();
/* 100 */     for (Long i : indexEntry)
/*     */     {
/* 102 */       indexAsArray.add((COSBase)COSInteger.get(i.longValue()));
/*     */     }
/* 104 */     this.stream.setItem(COSName.INDEX, (COSBase)indexAsArray);
/*     */     
/* 106 */     int[] wEntry = getWEntry();
/* 107 */     COSArray wAsArray = new COSArray();
/* 108 */     for (int j : wEntry)
/*     */     {
/* 110 */       wAsArray.add((COSBase)COSInteger.get(j));
/*     */     }
/* 112 */     this.stream.setItem(COSName.W, (COSBase)wAsArray);
/*     */     
/* 114 */     OutputStream outputStream = this.stream.createOutputStream((COSBase)COSName.FLATE_DECODE);
/* 115 */     writeStreamData(outputStream, wEntry);
/* 116 */     outputStream.flush();
/* 117 */     outputStream.close();
/*     */     
/* 119 */     Set<COSName> keySet = this.stream.keySet();
/* 120 */     for (COSName cosName : keySet) {
/*     */ 
/*     */ 
/*     */       
/* 124 */       if (COSName.ROOT.equals(cosName) || COSName.INFO.equals(cosName) || COSName.PREV.equals(cosName)) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */       
/* 129 */       if (COSName.ENCRYPT.equals(cosName)) {
/*     */         continue;
/*     */       }
/*     */       
/* 133 */       COSBase dictionaryObject = this.stream.getDictionaryObject(cosName);
/* 134 */       dictionaryObject.setDirect(true);
/*     */     } 
/* 136 */     return this.stream;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addTrailerInfo(COSDictionary trailerDict) {
/* 146 */     Set<Map.Entry<COSName, COSBase>> entrySet = trailerDict.entrySet();
/* 147 */     for (Map.Entry<COSName, COSBase> entry : entrySet) {
/*     */       
/* 149 */       COSName key = entry.getKey();
/* 150 */       if (COSName.INFO.equals(key) || COSName.ROOT.equals(key) || COSName.ENCRYPT.equals(key) || COSName.ID
/* 151 */         .equals(key) || COSName.PREV.equals(key))
/*     */       {
/* 153 */         this.stream.setItem(key, entry.getValue());
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
/*     */   public void addEntry(COSWriterXRefEntry entry) {
/* 165 */     this.objectNumbers.add(Long.valueOf(entry.getKey().getNumber()));
/* 166 */     if (entry.isFree()) {
/*     */ 
/*     */       
/* 169 */       FreeReference value = new FreeReference();
/* 170 */       value.nextGenNumber = entry.getKey().getGeneration();
/* 171 */       value.nextFree = entry.getKey().getNumber();
/* 172 */       this.streamData.put(Long.valueOf(value.nextFree), value);
/*     */     
/*     */     }
/*     */     else {
/*     */ 
/*     */       
/* 178 */       NormalReference value = new NormalReference();
/* 179 */       value.genNumber = entry.getKey().getGeneration();
/* 180 */       value.offset = entry.getOffset();
/* 181 */       this.streamData.put(Long.valueOf(entry.getKey().getNumber()), value);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private int[] getWEntry() {
/* 192 */     long[] wMax = new long[3];
/* 193 */     for (Object entry : this.streamData.values()) {
/*     */       
/* 195 */       if (entry instanceof FreeReference) {
/*     */         
/* 197 */         FreeReference free = (FreeReference)entry;
/* 198 */         wMax[0] = Math.max(wMax[0], 0L);
/* 199 */         wMax[1] = Math.max(wMax[1], free.nextFree);
/* 200 */         wMax[2] = Math.max(wMax[2], free.nextGenNumber); continue;
/*     */       } 
/* 202 */       if (entry instanceof NormalReference) {
/*     */         
/* 204 */         NormalReference ref = (NormalReference)entry;
/* 205 */         wMax[0] = Math.max(wMax[0], 1L);
/* 206 */         wMax[1] = Math.max(wMax[1], ref.offset);
/* 207 */         wMax[2] = Math.max(wMax[2], ref.genNumber); continue;
/*     */       } 
/* 209 */       if (entry instanceof ObjectStreamReference) {
/*     */         
/* 211 */         ObjectStreamReference objStream = (ObjectStreamReference)entry;
/* 212 */         wMax[0] = Math.max(wMax[0], 2L);
/* 213 */         wMax[1] = Math.max(wMax[1], objStream.offset);
/* 214 */         wMax[2] = Math.max(wMax[2], objStream.objectNumberOfObjectStream);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 219 */       throw new RuntimeException("unexpected reference type");
/*     */     } 
/*     */ 
/*     */     
/* 223 */     int[] w = new int[3];
/* 224 */     for (int i = 0; i < w.length; i++) {
/*     */       
/* 226 */       while (wMax[i] > 0L) {
/*     */         
/* 228 */         w[i] = w[i] + 1;
/* 229 */         wMax[i] = wMax[i] >> 8L;
/*     */       } 
/*     */     } 
/* 232 */     return w;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSize(long streamSize) {
/* 242 */     this.size = streamSize;
/*     */   }
/*     */ 
/*     */   
/*     */   private List<Long> getIndexEntry() {
/* 247 */     LinkedList<Long> linkedList = new LinkedList<Long>();
/* 248 */     Long first = null;
/* 249 */     Long length = null;
/* 250 */     Set<Long> objNumbers = new TreeSet<Long>();
/*     */     
/* 252 */     objNumbers.add(Long.valueOf(0L));
/* 253 */     objNumbers.addAll(this.objectNumbers);
/* 254 */     for (Long objNumber : objNumbers) {
/*     */       
/* 256 */       if (first == null) {
/*     */         
/* 258 */         first = objNumber;
/* 259 */         length = Long.valueOf(1L);
/*     */       } 
/* 261 */       if (first.longValue() + length.longValue() == objNumber.longValue())
/*     */       {
/* 263 */         length = Long.valueOf(length.longValue() + 1L);
/*     */       }
/* 265 */       if (first.longValue() + length.longValue() < objNumber.longValue()) {
/*     */         
/* 267 */         linkedList.add(first);
/* 268 */         linkedList.add(length);
/* 269 */         first = objNumber;
/* 270 */         length = Long.valueOf(1L);
/*     */       } 
/*     */     } 
/* 273 */     linkedList.add(first);
/* 274 */     linkedList.add(length);
/*     */     
/* 276 */     return linkedList;
/*     */   }
/*     */ 
/*     */   
/*     */   private void writeNumber(OutputStream os, long number, int bytes) throws IOException {
/* 281 */     byte[] buffer = new byte[bytes]; int i;
/* 282 */     for (i = 0; i < bytes; i++) {
/*     */       
/* 284 */       buffer[i] = (byte)(int)(number & 0xFFL);
/* 285 */       number >>= 8L;
/*     */     } 
/*     */     
/* 288 */     for (i = 0; i < bytes; i++)
/*     */     {
/* 290 */       os.write(buffer[bytes - i - 1]);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void writeStreamData(OutputStream os, int[] w) throws IOException {
/* 297 */     writeNumber(os, 0L, w[0]);
/* 298 */     writeNumber(os, 0L, w[1]);
/* 299 */     writeNumber(os, 65535L, w[2]);
/*     */     
/* 301 */     for (Object entry : this.streamData.values()) {
/*     */       
/* 303 */       if (entry instanceof FreeReference) {
/*     */         
/* 305 */         FreeReference free = (FreeReference)entry;
/* 306 */         writeNumber(os, 0L, w[0]);
/* 307 */         writeNumber(os, free.nextFree, w[1]);
/* 308 */         writeNumber(os, free.nextGenNumber, w[2]); continue;
/*     */       } 
/* 310 */       if (entry instanceof NormalReference) {
/*     */         
/* 312 */         NormalReference ref = (NormalReference)entry;
/* 313 */         writeNumber(os, 1L, w[0]);
/* 314 */         writeNumber(os, ref.offset, w[1]);
/* 315 */         writeNumber(os, ref.genNumber, w[2]); continue;
/*     */       } 
/* 317 */       if (entry instanceof ObjectStreamReference) {
/*     */         
/* 319 */         ObjectStreamReference objStream = (ObjectStreamReference)entry;
/* 320 */         writeNumber(os, 2L, w[0]);
/* 321 */         writeNumber(os, objStream.offset, w[1]);
/* 322 */         writeNumber(os, objStream.objectNumberOfObjectStream, w[2]);
/*     */         
/*     */         continue;
/*     */       } 
/*     */       
/* 327 */       throw new RuntimeException("unexpected reference type");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class ObjectStreamReference
/*     */   {
/*     */     long objectNumberOfObjectStream;
/*     */ 
/*     */ 
/*     */     
/*     */     long offset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class NormalReference
/*     */   {
/*     */     int genNumber;
/*     */ 
/*     */     
/*     */     long offset;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static class FreeReference
/*     */   {
/*     */     int nextGenNumber;
/*     */ 
/*     */     
/*     */     long nextFree;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public COSObject getObject(int objectNumber) {
/* 367 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdfparser/PDFXRefStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */