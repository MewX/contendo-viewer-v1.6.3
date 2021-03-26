/*     */ package org.apache.pdfbox.filter;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.io.OutputStream;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import javax.imageio.stream.MemoryCacheImageInputStream;
/*     */ import javax.imageio.stream.MemoryCacheImageOutputStream;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.apache.pdfbox.cos.COSDictionary;
/*     */ import org.apache.pdfbox.cos.COSName;
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
/*     */ public class LZWFilter
/*     */   extends Filter
/*     */ {
/*  46 */   private static final Log LOG = LogFactory.getLog(LZWFilter.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final long CLEAR_TABLE = 256L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final long EOD = 257L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public DecodeResult decode(InputStream encoded, OutputStream decoded, COSDictionary parameters, int index) throws IOException {
/*  68 */     COSDictionary decodeParams = getDecodeParams(parameters, index);
/*  69 */     int earlyChange = decodeParams.getInt(COSName.EARLY_CHANGE, 1);
/*     */     
/*  71 */     if (earlyChange != 0 && earlyChange != 1)
/*     */     {
/*  73 */       earlyChange = 1;
/*     */     }
/*     */     
/*  76 */     doLZWDecode(encoded, Predictor.wrapPredictor(decoded, decodeParams), earlyChange);
/*  77 */     return new DecodeResult(parameters);
/*     */   }
/*     */ 
/*     */   
/*     */   private void doLZWDecode(InputStream encoded, OutputStream decoded, int earlyChange) throws IOException {
/*  82 */     List<byte[]> codeTable = (List)new ArrayList<byte>();
/*  83 */     int chunk = 9;
/*  84 */     MemoryCacheImageInputStream in = new MemoryCacheImageInputStream(encoded);
/*     */     
/*  86 */     long prevCommand = -1L;
/*     */     
/*     */     try {
/*     */       long nextCommand;
/*  90 */       while ((nextCommand = in.readBits(chunk)) != 257L)
/*     */       {
/*  92 */         if (nextCommand == 256L) {
/*     */           
/*  94 */           chunk = 9;
/*  95 */           codeTable = createCodeTable();
/*  96 */           prevCommand = -1L;
/*     */           
/*     */           continue;
/*     */         } 
/* 100 */         if (nextCommand < codeTable.size()) {
/*     */           
/* 102 */           byte[] data = codeTable.get((int)nextCommand);
/* 103 */           byte firstByte = data[0];
/* 104 */           decoded.write(data);
/* 105 */           if (prevCommand != -1L)
/*     */           {
/* 107 */             checkIndexBounds(codeTable, prevCommand, in);
/* 108 */             data = codeTable.get((int)prevCommand);
/* 109 */             byte[] newData = Arrays.copyOf(data, data.length + 1);
/* 110 */             newData[data.length] = firstByte;
/* 111 */             codeTable.add(newData);
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 116 */           checkIndexBounds(codeTable, prevCommand, in);
/* 117 */           byte[] data = codeTable.get((int)prevCommand);
/* 118 */           byte[] newData = Arrays.copyOf(data, data.length + 1);
/* 119 */           newData[data.length] = data[0];
/* 120 */           decoded.write(newData);
/* 121 */           codeTable.add(newData);
/*     */         } 
/*     */         
/* 124 */         chunk = calculateChunk(codeTable.size(), earlyChange);
/* 125 */         prevCommand = nextCommand;
/*     */       }
/*     */     
/*     */     }
/* 129 */     catch (EOFException ex) {
/*     */       
/* 131 */       LOG.warn("Premature EOF in LZW stream, EOD code missing");
/*     */     } 
/* 133 */     decoded.flush();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void checkIndexBounds(List<byte[]> codeTable, long index, MemoryCacheImageInputStream in) throws IOException {
/* 139 */     if (index < 0L)
/*     */     {
/* 141 */       throw new IOException("negative array index: " + index + " near offset " + in
/* 142 */           .getStreamPosition());
/*     */     }
/* 144 */     if (index >= codeTable.size())
/*     */     {
/* 146 */       throw new IOException("array index overflow: " + index + " >= " + codeTable
/* 147 */           .size() + " near offset " + in
/* 148 */           .getStreamPosition());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void encode(InputStream rawData, OutputStream encoded, COSDictionary parameters) throws IOException {
/* 159 */     List<byte[]> codeTable = createCodeTable();
/* 160 */     int chunk = 9;
/*     */     
/* 162 */     byte[] inputPattern = null;
/* 163 */     MemoryCacheImageOutputStream out = new MemoryCacheImageOutputStream(encoded);
/* 164 */     out.writeBits(256L, chunk);
/* 165 */     int foundCode = -1;
/*     */     int r;
/* 167 */     while ((r = rawData.read()) != -1) {
/*     */       
/* 169 */       byte by = (byte)r;
/* 170 */       if (inputPattern == null) {
/*     */         
/* 172 */         inputPattern = new byte[] { by };
/* 173 */         foundCode = by & 0xFF;
/*     */         
/*     */         continue;
/*     */       } 
/* 177 */       inputPattern = Arrays.copyOf(inputPattern, inputPattern.length + 1);
/* 178 */       inputPattern[inputPattern.length - 1] = by;
/* 179 */       int newFoundCode = findPatternCode(codeTable, inputPattern);
/* 180 */       if (newFoundCode == -1) {
/*     */ 
/*     */         
/* 183 */         chunk = calculateChunk(codeTable.size() - 1, 1);
/* 184 */         out.writeBits(foundCode, chunk);
/*     */         
/* 186 */         codeTable.add(inputPattern);
/*     */         
/* 188 */         if (codeTable.size() == 4096) {
/*     */ 
/*     */           
/* 191 */           out.writeBits(256L, chunk);
/* 192 */           codeTable = createCodeTable();
/*     */         } 
/*     */         
/* 195 */         inputPattern = new byte[] { by };
/* 196 */         foundCode = by & 0xFF;
/*     */         
/*     */         continue;
/*     */       } 
/* 200 */       foundCode = newFoundCode;
/*     */     } 
/*     */ 
/*     */     
/* 204 */     if (foundCode != -1) {
/*     */       
/* 206 */       chunk = calculateChunk(codeTable.size() - 1, 1);
/* 207 */       out.writeBits(foundCode, chunk);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 215 */     chunk = calculateChunk(codeTable.size(), 1);
/*     */     
/* 217 */     out.writeBits(257L, chunk);
/*     */ 
/*     */     
/* 220 */     out.writeBits(0L, 7);
/*     */ 
/*     */     
/* 223 */     out.flush();
/* 224 */     out.close();
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
/*     */   private int findPatternCode(List<byte[]> codeTable, byte[] pattern) {
/* 237 */     int foundCode = -1;
/* 238 */     int foundLen = 0;
/* 239 */     for (int i = codeTable.size() - 1; i >= 0; i--) {
/*     */       
/* 241 */       if (i <= 257L) {
/*     */ 
/*     */         
/* 244 */         if (foundCode != -1)
/*     */         {
/*     */           
/* 247 */           return foundCode;
/*     */         }
/* 249 */         if (pattern.length > 1)
/*     */         {
/*     */           
/* 252 */           return -1;
/*     */         }
/*     */       } 
/* 255 */       byte[] tryPattern = codeTable.get(i);
/* 256 */       if ((foundCode != -1 || tryPattern.length > foundLen) && Arrays.equals(tryPattern, pattern)) {
/*     */         
/* 258 */         foundCode = i;
/* 259 */         foundLen = tryPattern.length;
/*     */       } 
/*     */     } 
/* 262 */     return foundCode;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<byte[]> createCodeTable() {
/* 271 */     List<byte[]> codeTable = (List)new ArrayList<byte>(4096);
/* 272 */     for (int i = 0; i < 256; i++) {
/*     */       
/* 274 */       codeTable.add(new byte[] { (byte)(i & 0xFF) });
/*     */     } 
/* 276 */     codeTable.add(null);
/* 277 */     codeTable.add(null);
/* 278 */     return codeTable;
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
/*     */   private int calculateChunk(int tabSize, int earlyChange) {
/* 291 */     if (tabSize >= 2048 - earlyChange)
/*     */     {
/* 293 */       return 12;
/*     */     }
/* 295 */     if (tabSize >= 1024 - earlyChange)
/*     */     {
/* 297 */       return 11;
/*     */     }
/* 299 */     if (tabSize >= 512 - earlyChange)
/*     */     {
/* 301 */       return 10;
/*     */     }
/* 303 */     return 9;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/filter/LZWFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */