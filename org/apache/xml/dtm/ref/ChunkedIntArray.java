/*     */ package org.apache.xml.dtm.ref;
/*     */ 
/*     */ import org.apache.xml.res.XMLMessages;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class ChunkedIntArray
/*     */ {
/*  40 */   final int slotsize = 4;
/*     */   
/*     */   static final int lowbits = 10;
/*     */   
/*     */   static final int chunkalloc = 1024;
/*     */   
/*     */   static final int lowmask = 1023;
/*  47 */   ChunksVector chunks = new ChunksVector(this);
/*  48 */   final int[] fastArray = new int[1024];
/*  49 */   int lastUsed = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   ChunkedIntArray(int slotsize) {
/*  57 */     if (4 < slotsize)
/*  58 */       throw new ArrayIndexOutOfBoundsException(XMLMessages.createXMLMessage("ER_CHUNKEDINTARRAY_NOT_SUPPORTED", new Object[] { Integer.toString(slotsize) })); 
/*  59 */     if (4 > slotsize)
/*  60 */       System.out.println("*****WARNING: ChunkedIntArray(" + slotsize + ") wasting " + (4 - slotsize) + " words per slot"); 
/*  61 */     this.chunks.addElement(this.fastArray);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int appendSlot(int w0, int w1, int w2, int w3) {
/*  84 */     int slotsize = 4;
/*  85 */     int newoffset = (this.lastUsed + 1) * 4;
/*  86 */     int chunkpos = newoffset >> 10;
/*  87 */     int slotpos = newoffset & 0x3FF;
/*     */ 
/*     */     
/*  90 */     if (chunkpos > this.chunks.size() - 1)
/*  91 */       this.chunks.addElement(new int[1024]); 
/*  92 */     int[] chunk = this.chunks.elementAt(chunkpos);
/*  93 */     chunk[slotpos] = w0;
/*  94 */     chunk[slotpos + 1] = w1;
/*  95 */     chunk[slotpos + 2] = w2;
/*  96 */     chunk[slotpos + 3] = w3;
/*     */     
/*  98 */     return ++this.lastUsed;
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
/*     */ 
/*     */   
/*     */   int readEntry(int position, int offset) throws ArrayIndexOutOfBoundsException {
/* 119 */     if (offset >= 4)
/* 120 */       throw new ArrayIndexOutOfBoundsException(XMLMessages.createXMLMessage("ER_OFFSET_BIGGER_THAN_SLOT", null)); 
/* 121 */     position *= 4;
/* 122 */     int chunkpos = position >> 10;
/* 123 */     int slotpos = position & 0x3FF;
/* 124 */     int[] chunk = this.chunks.elementAt(chunkpos);
/* 125 */     return chunk[slotpos + offset];
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
/*     */   int specialFind(int startPos, int position) {
/* 139 */     int ancestor = startPos;
/* 140 */     while (ancestor > 0) {
/*     */ 
/*     */       
/* 143 */       ancestor *= 4;
/* 144 */       int chunkpos = ancestor >> 10;
/* 145 */       int slotpos = ancestor & 0x3FF;
/* 146 */       int[] chunk = this.chunks.elementAt(chunkpos);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 151 */       ancestor = chunk[slotpos + 1];
/*     */       
/* 153 */       if (ancestor == position) {
/*     */         break;
/*     */       }
/*     */     } 
/* 157 */     if (ancestor <= 0)
/*     */     {
/* 159 */       return position;
/*     */     }
/* 161 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int slotsUsed() {
/* 169 */     return this.lastUsed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void discardLast() {
/* 179 */     this.lastUsed--;
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
/*     */ 
/*     */   
/*     */   void writeEntry(int position, int offset, int value) throws ArrayIndexOutOfBoundsException {
/* 200 */     if (offset >= 4)
/* 201 */       throw new ArrayIndexOutOfBoundsException(XMLMessages.createXMLMessage("ER_OFFSET_BIGGER_THAN_SLOT", null)); 
/* 202 */     position *= 4;
/* 203 */     int chunkpos = position >> 10;
/* 204 */     int slotpos = position & 0x3FF;
/* 205 */     int[] chunk = this.chunks.elementAt(chunkpos);
/* 206 */     chunk[slotpos + offset] = value;
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
/*     */   void writeSlot(int position, int w0, int w1, int w2, int w3) {
/* 221 */     position *= 4;
/* 222 */     int chunkpos = position >> 10;
/* 223 */     int slotpos = position & 0x3FF;
/*     */ 
/*     */     
/* 226 */     if (chunkpos > this.chunks.size() - 1)
/* 227 */       this.chunks.addElement(new int[1024]); 
/* 228 */     int[] chunk = this.chunks.elementAt(chunkpos);
/* 229 */     chunk[slotpos] = w0;
/* 230 */     chunk[slotpos + 1] = w1;
/* 231 */     chunk[slotpos + 2] = w2;
/* 232 */     chunk[slotpos + 3] = w3;
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
/*     */ 
/*     */ 
/*     */   
/*     */   void readSlot(int position, int[] buffer) {
/* 254 */     position *= 4;
/* 255 */     int chunkpos = position >> 10;
/* 256 */     int slotpos = position & 0x3FF;
/*     */ 
/*     */     
/* 259 */     if (chunkpos > this.chunks.size() - 1)
/* 260 */       this.chunks.addElement(new int[1024]); 
/* 261 */     int[] chunk = this.chunks.elementAt(chunkpos);
/* 262 */     System.arraycopy(chunk, slotpos, buffer, 0, 4);
/*     */   }
/*     */   
/*     */   class ChunksVector
/*     */   {
/*     */     final int BLOCKSIZE = 64;
/*     */     int[][] m_map;
/*     */     int m_mapSize;
/*     */     int pos;
/*     */     private final ChunkedIntArray this$0;
/*     */     
/*     */     ChunksVector(ChunkedIntArray this$0) {
/* 274 */       this.this$0 = this$0;
/*     */       this.BLOCKSIZE = 64;
/*     */       this.m_map = new int[64][];
/*     */       this.m_mapSize = 64;
/*     */       this.pos = 0; } final int size() {
/* 279 */       return this.pos;
/*     */     }
/*     */ 
/*     */     
/*     */     void addElement(int[] value) {
/* 284 */       if (this.pos >= this.m_mapSize) {
/*     */         
/* 286 */         int orgMapSize = this.m_mapSize;
/* 287 */         while (this.pos >= this.m_mapSize)
/* 288 */           this.m_mapSize += 64; 
/* 289 */         int[][] newMap = new int[this.m_mapSize][];
/* 290 */         System.arraycopy(this.m_map, 0, newMap, 0, orgMapSize);
/* 291 */         this.m_map = newMap;
/*     */       } 
/*     */ 
/*     */       
/* 295 */       this.m_map[this.pos] = value;
/* 296 */       this.pos++;
/*     */     }
/*     */ 
/*     */     
/*     */     final int[] elementAt(int pos) {
/* 301 */       return this.m_map[pos];
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/dtm/ref/ChunkedIntArray.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */