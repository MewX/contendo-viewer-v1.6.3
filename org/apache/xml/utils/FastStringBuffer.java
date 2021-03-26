/*      */ package org.apache.xml.utils;
/*      */ 
/*      */ import org.xml.sax.ContentHandler;
/*      */ import org.xml.sax.SAXException;
/*      */ import org.xml.sax.ext.LexicalHandler;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public class FastStringBuffer
/*      */ {
/*      */   static final int DEBUG_FORCE_INIT_BITS = 0;
/*      */   static boolean DEBUG_FORCE_FIXED_CHUNKSIZE = true;
/*      */   public static final int SUPPRESS_LEADING_WS = 1;
/*      */   public static final int SUPPRESS_TRAILING_WS = 2;
/*      */   public static final int SUPPRESS_BOTH = 3;
/*      */   private static final int CARRY_WS = 4;
/*   99 */   int m_chunkBits = 15;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  106 */   int m_maxChunkBits = 15;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  116 */   int m_rebundleBits = 2;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int m_chunkSize;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   int m_chunkMask;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   char[][] m_array;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  151 */   int m_lastChunk = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  160 */   int m_firstFree = 0;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  169 */   FastStringBuffer m_innerFSB = null;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FastStringBuffer(int initChunkBits, int maxChunkBits, int rebundleBits) {
/*  205 */     if (DEBUG_FORCE_FIXED_CHUNKSIZE) maxChunkBits = initChunkBits;
/*      */ 
/*      */     
/*  208 */     this.m_array = new char[16][];
/*      */ 
/*      */     
/*  211 */     if (initChunkBits > maxChunkBits) {
/*  212 */       initChunkBits = maxChunkBits;
/*      */     }
/*  214 */     this.m_chunkBits = initChunkBits;
/*  215 */     this.m_maxChunkBits = maxChunkBits;
/*  216 */     this.m_rebundleBits = rebundleBits;
/*  217 */     this.m_chunkSize = 1 << initChunkBits;
/*  218 */     this.m_chunkMask = this.m_chunkSize - 1;
/*  219 */     this.m_array[0] = new char[this.m_chunkSize];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FastStringBuffer(int initChunkBits, int maxChunkBits) {
/*  230 */     this(initChunkBits, maxChunkBits, 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FastStringBuffer(int initChunkBits) {
/*  246 */     this(initChunkBits, 15, 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public FastStringBuffer() {
/*  260 */     this(10, 15, 2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int size() {
/*  270 */     return (this.m_lastChunk << this.m_chunkBits) + this.m_firstFree;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final int length() {
/*  280 */     return (this.m_lastChunk << this.m_chunkBits) + this.m_firstFree;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void reset() {
/*  291 */     this.m_lastChunk = 0;
/*  292 */     this.m_firstFree = 0;
/*      */ 
/*      */     
/*  295 */     FastStringBuffer innermost = this;
/*      */     
/*  297 */     while (innermost.m_innerFSB != null)
/*      */     {
/*  299 */       innermost = innermost.m_innerFSB;
/*      */     }
/*      */     
/*  302 */     this.m_chunkBits = innermost.m_chunkBits;
/*  303 */     this.m_chunkSize = innermost.m_chunkSize;
/*  304 */     this.m_chunkMask = innermost.m_chunkMask;
/*      */ 
/*      */     
/*  307 */     this.m_innerFSB = null;
/*  308 */     this.m_array = new char[16][0];
/*  309 */     this.m_array[0] = new char[this.m_chunkSize];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void setLength(int l) {
/*  326 */     this.m_lastChunk = l >>> this.m_chunkBits;
/*      */     
/*  328 */     if (this.m_lastChunk == 0 && this.m_innerFSB != null) {
/*      */ 
/*      */       
/*  331 */       this.m_innerFSB.setLength(l, this);
/*      */     }
/*      */     else {
/*      */       
/*  335 */       this.m_firstFree = l & this.m_chunkMask;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */       
/*  342 */       if (this.m_firstFree == 0 && this.m_lastChunk > 0) {
/*      */         
/*  344 */         this.m_lastChunk--;
/*  345 */         this.m_firstFree = this.m_chunkSize;
/*      */       } 
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private final void setLength(int l, FastStringBuffer rootFSB) {
/*  360 */     this.m_lastChunk = l >>> this.m_chunkBits;
/*      */     
/*  362 */     if (this.m_lastChunk == 0 && this.m_innerFSB != null) {
/*      */       
/*  364 */       this.m_innerFSB.setLength(l, rootFSB);
/*      */ 
/*      */     
/*      */     }
/*      */     else {
/*      */ 
/*      */       
/*  371 */       rootFSB.m_chunkBits = this.m_chunkBits;
/*  372 */       rootFSB.m_maxChunkBits = this.m_maxChunkBits;
/*  373 */       rootFSB.m_rebundleBits = this.m_rebundleBits;
/*  374 */       rootFSB.m_chunkSize = this.m_chunkSize;
/*  375 */       rootFSB.m_chunkMask = this.m_chunkMask;
/*  376 */       rootFSB.m_array = this.m_array;
/*  377 */       rootFSB.m_innerFSB = this.m_innerFSB;
/*  378 */       rootFSB.m_lastChunk = this.m_lastChunk;
/*      */ 
/*      */       
/*  381 */       rootFSB.m_firstFree = l & this.m_chunkMask;
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final String toString() {
/*  401 */     int length = (this.m_lastChunk << this.m_chunkBits) + this.m_firstFree;
/*      */     
/*  403 */     return getString(new StringBuffer(length), 0, 0, length).toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void append(char value) {
/*      */     char[] arrayOfChar;
/*  423 */     boolean lastchunk = (this.m_lastChunk + 1 == this.m_array.length);
/*      */     
/*  425 */     if (this.m_firstFree < this.m_chunkSize) {
/*  426 */       arrayOfChar = this.m_array[this.m_lastChunk];
/*      */     
/*      */     }
/*      */     else {
/*      */       
/*  431 */       int i = this.m_array.length;
/*      */       
/*  433 */       if (this.m_lastChunk + 1 == i) {
/*      */         
/*  435 */         char[][] newarray = new char[i + 16][];
/*      */         
/*  437 */         System.arraycopy(this.m_array, 0, newarray, 0, i);
/*      */         
/*  439 */         this.m_array = newarray;
/*      */       } 
/*      */ 
/*      */       
/*  443 */       arrayOfChar = this.m_array[++this.m_lastChunk];
/*      */       
/*  445 */       if (arrayOfChar == null) {
/*      */ 
/*      */ 
/*      */         
/*  449 */         if (this.m_lastChunk == 1 << this.m_rebundleBits && this.m_chunkBits < this.m_maxChunkBits)
/*      */         {
/*      */ 
/*      */ 
/*      */ 
/*      */           
/*  455 */           this.m_innerFSB = new FastStringBuffer(this);
/*      */         }
/*      */ 
/*      */         
/*  459 */         arrayOfChar = this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
/*      */       } 
/*      */       
/*  462 */       this.m_firstFree = 0;
/*      */     } 
/*      */ 
/*      */     
/*  466 */     arrayOfChar[this.m_firstFree++] = value;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void append(String value) {
/*  481 */     if (value == null)
/*      */       return; 
/*  483 */     int strlen = value.length();
/*      */     
/*  485 */     if (0 == strlen) {
/*      */       return;
/*      */     }
/*  488 */     int copyfrom = 0;
/*  489 */     char[] chunk = this.m_array[this.m_lastChunk];
/*  490 */     int available = this.m_chunkSize - this.m_firstFree;
/*      */ 
/*      */     
/*  493 */     while (strlen > 0) {
/*      */ 
/*      */ 
/*      */       
/*  497 */       if (available > strlen) {
/*  498 */         available = strlen;
/*      */       }
/*  500 */       value.getChars(copyfrom, copyfrom + available, this.m_array[this.m_lastChunk], this.m_firstFree);
/*      */ 
/*      */       
/*  503 */       strlen -= available;
/*  504 */       copyfrom += available;
/*      */ 
/*      */       
/*  507 */       if (strlen > 0) {
/*      */ 
/*      */ 
/*      */         
/*  511 */         int i = this.m_array.length;
/*      */         
/*  513 */         if (this.m_lastChunk + 1 == i) {
/*      */           
/*  515 */           char[][] newarray = new char[i + 16][];
/*      */           
/*  517 */           System.arraycopy(this.m_array, 0, newarray, 0, i);
/*      */           
/*  519 */           this.m_array = newarray;
/*      */         } 
/*      */ 
/*      */         
/*  523 */         chunk = this.m_array[++this.m_lastChunk];
/*      */         
/*  525 */         if (chunk == null) {
/*      */ 
/*      */ 
/*      */           
/*  529 */           if (this.m_lastChunk == 1 << this.m_rebundleBits && this.m_chunkBits < this.m_maxChunkBits)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  535 */             this.m_innerFSB = new FastStringBuffer(this);
/*      */           }
/*      */ 
/*      */           
/*  539 */           chunk = this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
/*      */         } 
/*      */         
/*  542 */         available = this.m_chunkSize;
/*  543 */         this.m_firstFree = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  548 */     this.m_firstFree += available;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void append(StringBuffer value) {
/*  563 */     if (value == null)
/*      */       return; 
/*  565 */     int strlen = value.length();
/*      */     
/*  567 */     if (0 == strlen) {
/*      */       return;
/*      */     }
/*  570 */     int copyfrom = 0;
/*  571 */     char[] chunk = this.m_array[this.m_lastChunk];
/*  572 */     int available = this.m_chunkSize - this.m_firstFree;
/*      */ 
/*      */     
/*  575 */     while (strlen > 0) {
/*      */ 
/*      */ 
/*      */       
/*  579 */       if (available > strlen) {
/*  580 */         available = strlen;
/*      */       }
/*  582 */       value.getChars(copyfrom, copyfrom + available, this.m_array[this.m_lastChunk], this.m_firstFree);
/*      */ 
/*      */       
/*  585 */       strlen -= available;
/*  586 */       copyfrom += available;
/*      */ 
/*      */       
/*  589 */       if (strlen > 0) {
/*      */ 
/*      */ 
/*      */         
/*  593 */         int i = this.m_array.length;
/*      */         
/*  595 */         if (this.m_lastChunk + 1 == i) {
/*      */           
/*  597 */           char[][] newarray = new char[i + 16][];
/*      */           
/*  599 */           System.arraycopy(this.m_array, 0, newarray, 0, i);
/*      */           
/*  601 */           this.m_array = newarray;
/*      */         } 
/*      */ 
/*      */         
/*  605 */         chunk = this.m_array[++this.m_lastChunk];
/*      */         
/*  607 */         if (chunk == null) {
/*      */ 
/*      */ 
/*      */           
/*  611 */           if (this.m_lastChunk == 1 << this.m_rebundleBits && this.m_chunkBits < this.m_maxChunkBits)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  617 */             this.m_innerFSB = new FastStringBuffer(this);
/*      */           }
/*      */ 
/*      */           
/*  621 */           chunk = this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
/*      */         } 
/*      */         
/*  624 */         available = this.m_chunkSize;
/*  625 */         this.m_firstFree = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  630 */     this.m_firstFree += available;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void append(char[] chars, int start, int length) {
/*  648 */     int strlen = length;
/*      */     
/*  650 */     if (0 == strlen) {
/*      */       return;
/*      */     }
/*  653 */     int copyfrom = start;
/*  654 */     char[] chunk = this.m_array[this.m_lastChunk];
/*  655 */     int available = this.m_chunkSize - this.m_firstFree;
/*      */ 
/*      */     
/*  658 */     while (strlen > 0) {
/*      */ 
/*      */ 
/*      */       
/*  662 */       if (available > strlen) {
/*  663 */         available = strlen;
/*      */       }
/*  665 */       System.arraycopy(chars, copyfrom, this.m_array[this.m_lastChunk], this.m_firstFree, available);
/*      */ 
/*      */       
/*  668 */       strlen -= available;
/*  669 */       copyfrom += available;
/*      */ 
/*      */       
/*  672 */       if (strlen > 0) {
/*      */ 
/*      */ 
/*      */         
/*  676 */         int i = this.m_array.length;
/*      */         
/*  678 */         if (this.m_lastChunk + 1 == i) {
/*      */           
/*  680 */           char[][] newarray = new char[i + 16][];
/*      */           
/*  682 */           System.arraycopy(this.m_array, 0, newarray, 0, i);
/*      */           
/*  684 */           this.m_array = newarray;
/*      */         } 
/*      */ 
/*      */         
/*  688 */         chunk = this.m_array[++this.m_lastChunk];
/*      */         
/*  690 */         if (chunk == null) {
/*      */ 
/*      */ 
/*      */           
/*  694 */           if (this.m_lastChunk == 1 << this.m_rebundleBits && this.m_chunkBits < this.m_maxChunkBits)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  700 */             this.m_innerFSB = new FastStringBuffer(this);
/*      */           }
/*      */ 
/*      */           
/*  704 */           chunk = this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
/*      */         } 
/*      */         
/*  707 */         available = this.m_chunkSize;
/*  708 */         this.m_firstFree = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  713 */     this.m_firstFree += available;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public final void append(FastStringBuffer value) {
/*  733 */     if (value == null)
/*      */       return; 
/*  735 */     int strlen = value.length();
/*      */     
/*  737 */     if (0 == strlen) {
/*      */       return;
/*      */     }
/*  740 */     int copyfrom = 0;
/*  741 */     char[] chunk = this.m_array[this.m_lastChunk];
/*  742 */     int available = this.m_chunkSize - this.m_firstFree;
/*      */ 
/*      */     
/*  745 */     while (strlen > 0) {
/*      */ 
/*      */ 
/*      */       
/*  749 */       if (available > strlen) {
/*  750 */         available = strlen;
/*      */       }
/*  752 */       int sourcechunk = copyfrom + value.m_chunkSize - 1 >>> value.m_chunkBits;
/*      */       
/*  754 */       int sourcecolumn = copyfrom & value.m_chunkMask;
/*  755 */       int runlength = value.m_chunkSize - sourcecolumn;
/*      */       
/*  757 */       if (runlength > available) {
/*  758 */         runlength = available;
/*      */       }
/*  760 */       System.arraycopy(value.m_array[sourcechunk], sourcecolumn, this.m_array[this.m_lastChunk], this.m_firstFree, runlength);
/*      */ 
/*      */       
/*  763 */       if (runlength != available) {
/*  764 */         System.arraycopy(value.m_array[sourcechunk + 1], 0, this.m_array[this.m_lastChunk], this.m_firstFree + runlength, available - runlength);
/*      */       }
/*      */ 
/*      */       
/*  768 */       strlen -= available;
/*  769 */       copyfrom += available;
/*      */ 
/*      */       
/*  772 */       if (strlen > 0) {
/*      */ 
/*      */ 
/*      */         
/*  776 */         int i = this.m_array.length;
/*      */         
/*  778 */         if (this.m_lastChunk + 1 == i) {
/*      */           
/*  780 */           char[][] newarray = new char[i + 16][];
/*      */           
/*  782 */           System.arraycopy(this.m_array, 0, newarray, 0, i);
/*      */           
/*  784 */           this.m_array = newarray;
/*      */         } 
/*      */ 
/*      */         
/*  788 */         chunk = this.m_array[++this.m_lastChunk];
/*      */         
/*  790 */         if (chunk == null) {
/*      */ 
/*      */ 
/*      */           
/*  794 */           if (this.m_lastChunk == 1 << this.m_rebundleBits && this.m_chunkBits < this.m_maxChunkBits)
/*      */           {
/*      */ 
/*      */ 
/*      */ 
/*      */             
/*  800 */             this.m_innerFSB = new FastStringBuffer(this);
/*      */           }
/*      */ 
/*      */           
/*  804 */           chunk = this.m_array[this.m_lastChunk] = new char[this.m_chunkSize];
/*      */         } 
/*      */         
/*  807 */         available = this.m_chunkSize;
/*  808 */         this.m_firstFree = 0;
/*      */       } 
/*      */     } 
/*      */ 
/*      */     
/*  813 */     this.m_firstFree += available;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean isWhitespace(int start, int length) {
/*  828 */     int sourcechunk = start >>> this.m_chunkBits;
/*  829 */     int sourcecolumn = start & this.m_chunkMask;
/*  830 */     int available = this.m_chunkSize - sourcecolumn;
/*      */ 
/*      */     
/*  833 */     while (length > 0) {
/*      */       boolean bool;
/*  835 */       int runlength = (length <= available) ? length : available;
/*      */       
/*  837 */       if (sourcechunk == 0 && this.m_innerFSB != null) {
/*  838 */         bool = this.m_innerFSB.isWhitespace(sourcecolumn, runlength);
/*      */       } else {
/*  840 */         bool = XMLCharacterRecognizer.isWhiteSpace(this.m_array[sourcechunk], sourcecolumn, runlength);
/*      */       } 
/*      */       
/*  843 */       if (!bool) {
/*  844 */         return false;
/*      */       }
/*  846 */       length -= runlength;
/*      */       
/*  848 */       sourcechunk++;
/*      */       
/*  850 */       sourcecolumn = 0;
/*  851 */       available = this.m_chunkSize;
/*      */     } 
/*      */     
/*  854 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getString(int start, int length) {
/*  865 */     int startColumn = start & this.m_chunkMask;
/*  866 */     int startChunk = start >>> this.m_chunkBits;
/*  867 */     if (startColumn + length < this.m_chunkMask && this.m_innerFSB == null) {
/*  868 */       return getOneChunkString(startChunk, startColumn, length);
/*      */     }
/*  870 */     return getString(new StringBuffer(length), startChunk, startColumn, length).toString();
/*      */   }
/*      */ 
/*      */ 
/*      */   
/*      */   protected String getOneChunkString(int startChunk, int startColumn, int length) {
/*  876 */     return new String(this.m_array[startChunk], startColumn, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   StringBuffer getString(StringBuffer sb, int start, int length) {
/*  887 */     return getString(sb, start >>> this.m_chunkBits, start & this.m_chunkMask, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   StringBuffer getString(StringBuffer sb, int startChunk, int startColumn, int length) {
/*  918 */     int stop = (startChunk << this.m_chunkBits) + startColumn + length;
/*  919 */     int stopChunk = stop >>> this.m_chunkBits;
/*  920 */     int stopColumn = stop & this.m_chunkMask;
/*      */ 
/*      */ 
/*      */     
/*  924 */     for (int i = startChunk; i < stopChunk; i++) {
/*      */       
/*  926 */       if (i == 0 && this.m_innerFSB != null) {
/*  927 */         this.m_innerFSB.getString(sb, startColumn, this.m_chunkSize - startColumn);
/*      */       } else {
/*  929 */         sb.append(this.m_array[i], startColumn, this.m_chunkSize - startColumn);
/*      */       } 
/*  931 */       startColumn = 0;
/*      */     } 
/*      */     
/*  934 */     if (stopChunk == 0 && this.m_innerFSB != null) {
/*  935 */       this.m_innerFSB.getString(sb, startColumn, stopColumn - startColumn);
/*  936 */     } else if (stopColumn > startColumn) {
/*  937 */       sb.append(this.m_array[stopChunk], startColumn, stopColumn - startColumn);
/*      */     } 
/*  939 */     return sb;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public char charAt(int pos) {
/*  951 */     int startChunk = pos >>> this.m_chunkBits;
/*      */     
/*  953 */     if (startChunk == 0 && this.m_innerFSB != null) {
/*  954 */       return this.m_innerFSB.charAt(pos & this.m_chunkMask);
/*      */     }
/*  956 */     return this.m_array[startChunk][pos & this.m_chunkMask];
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendSAXcharacters(ContentHandler ch, int start, int length) throws SAXException {
/*  983 */     int startChunk = start >>> this.m_chunkBits;
/*  984 */     int startColumn = start & this.m_chunkMask;
/*  985 */     if (startColumn + length < this.m_chunkMask && this.m_innerFSB == null) {
/*  986 */       ch.characters(this.m_array[startChunk], startColumn, length);
/*      */       
/*      */       return;
/*      */     } 
/*  990 */     int stop = start + length;
/*  991 */     int stopChunk = stop >>> this.m_chunkBits;
/*  992 */     int stopColumn = stop & this.m_chunkMask;
/*      */     
/*  994 */     for (int i = startChunk; i < stopChunk; i++) {
/*      */       
/*  996 */       if (i == 0 && this.m_innerFSB != null) {
/*  997 */         this.m_innerFSB.sendSAXcharacters(ch, startColumn, this.m_chunkSize - startColumn);
/*      */       } else {
/*      */         
/* 1000 */         ch.characters(this.m_array[i], startColumn, this.m_chunkSize - startColumn);
/*      */       } 
/* 1002 */       startColumn = 0;
/*      */     } 
/*      */ 
/*      */     
/* 1006 */     if (stopChunk == 0 && this.m_innerFSB != null) {
/* 1007 */       this.m_innerFSB.sendSAXcharacters(ch, startColumn, stopColumn - startColumn);
/* 1008 */     } else if (stopColumn > startColumn) {
/*      */       
/* 1010 */       ch.characters(this.m_array[stopChunk], startColumn, stopColumn - startColumn);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int sendNormalizedSAXcharacters(ContentHandler ch, int start, int length) throws SAXException {
/* 1046 */     int stateForNextChunk = 1;
/*      */     
/* 1048 */     int stop = start + length;
/* 1049 */     int startChunk = start >>> this.m_chunkBits;
/* 1050 */     int startColumn = start & this.m_chunkMask;
/* 1051 */     int stopChunk = stop >>> this.m_chunkBits;
/* 1052 */     int stopColumn = stop & this.m_chunkMask;
/*      */     
/* 1054 */     for (int i = startChunk; i < stopChunk; i++) {
/*      */       
/* 1056 */       if (i == 0 && this.m_innerFSB != null) {
/* 1057 */         stateForNextChunk = this.m_innerFSB.sendNormalizedSAXcharacters(ch, startColumn, this.m_chunkSize - startColumn);
/*      */       }
/*      */       else {
/*      */         
/* 1061 */         stateForNextChunk = sendNormalizedSAXcharacters(this.m_array[i], startColumn, this.m_chunkSize - startColumn, ch, stateForNextChunk);
/*      */       } 
/*      */ 
/*      */ 
/*      */       
/* 1066 */       startColumn = 0;
/*      */     } 
/*      */ 
/*      */     
/* 1070 */     if (stopChunk == 0 && this.m_innerFSB != null) {
/* 1071 */       stateForNextChunk = this.m_innerFSB.sendNormalizedSAXcharacters(ch, startColumn, stopColumn - startColumn);
/*      */     }
/* 1073 */     else if (stopColumn > startColumn) {
/*      */       
/* 1075 */       stateForNextChunk = sendNormalizedSAXcharacters(this.m_array[stopChunk], startColumn, stopColumn - startColumn, ch, stateForNextChunk | 0x2);
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1080 */     return stateForNextChunk;
/*      */   }
/*      */   
/* 1083 */   static final char[] SINGLE_SPACE = new char[] { ' ' };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   static int sendNormalizedSAXcharacters(char[] ch, int start, int length, ContentHandler handler, int edgeTreatmentFlags) throws SAXException {
/* 1134 */     boolean processingLeadingWhitespace = ((edgeTreatmentFlags & 0x1) != 0);
/*      */     
/* 1136 */     boolean seenWhitespace = ((edgeTreatmentFlags & 0x4) != 0);
/* 1137 */     boolean suppressTrailingWhitespace = ((edgeTreatmentFlags & 0x2) != 0);
/*      */     
/* 1139 */     int currPos = start;
/* 1140 */     int limit = start + length;
/*      */ 
/*      */     
/* 1143 */     if (processingLeadingWhitespace) {
/*      */       
/* 1145 */       while (currPos < limit && XMLCharacterRecognizer.isWhiteSpace(ch[currPos])) {
/* 1146 */         currPos++;
/*      */       }
/*      */ 
/*      */       
/* 1150 */       if (currPos == limit) {
/* 1151 */         return edgeTreatmentFlags;
/*      */       }
/*      */     } 
/*      */ 
/*      */     
/* 1156 */     while (currPos < limit) {
/* 1157 */       int startNonWhitespace = currPos;
/*      */ 
/*      */ 
/*      */       
/* 1161 */       while (currPos < limit && !XMLCharacterRecognizer.isWhiteSpace(ch[currPos])) {
/* 1162 */         currPos++;
/*      */       }
/*      */ 
/*      */       
/* 1166 */       if (startNonWhitespace != currPos) {
/* 1167 */         if (seenWhitespace) {
/* 1168 */           handler.characters(SINGLE_SPACE, 0, 1);
/* 1169 */           seenWhitespace = false;
/*      */         } 
/* 1171 */         handler.characters(ch, startNonWhitespace, currPos - startNonWhitespace);
/*      */       } 
/*      */ 
/*      */       
/* 1175 */       int startWhitespace = currPos;
/*      */ 
/*      */ 
/*      */       
/* 1179 */       while (currPos < limit && XMLCharacterRecognizer.isWhiteSpace(ch[currPos])) {
/* 1180 */         currPos++;
/*      */       }
/* 1182 */       if (startWhitespace != currPos) {
/* 1183 */         seenWhitespace = true;
/*      */       }
/*      */     } 
/*      */     
/* 1187 */     return (seenWhitespace ? 4 : 0) | edgeTreatmentFlags & 0x2;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static void sendNormalizedSAXcharacters(char[] ch, int start, int length, ContentHandler handler) throws SAXException {
/* 1206 */     sendNormalizedSAXcharacters(ch, start, length, handler, 3);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void sendSAXComment(LexicalHandler ch, int start, int length) throws SAXException {
/* 1228 */     String comment = getString(start, length);
/* 1229 */     ch.comment(comment.toCharArray(), 0, length);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {}
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   private FastStringBuffer(FastStringBuffer source) {
/* 1270 */     this.m_chunkBits = source.m_chunkBits;
/* 1271 */     this.m_maxChunkBits = source.m_maxChunkBits;
/* 1272 */     this.m_rebundleBits = source.m_rebundleBits;
/* 1273 */     this.m_chunkSize = source.m_chunkSize;
/* 1274 */     this.m_chunkMask = source.m_chunkMask;
/* 1275 */     this.m_array = source.m_array;
/* 1276 */     this.m_innerFSB = source.m_innerFSB;
/*      */ 
/*      */ 
/*      */     
/* 1280 */     source.m_lastChunk--;
/* 1281 */     this.m_firstFree = source.m_chunkSize;
/*      */ 
/*      */     
/* 1284 */     source.m_array = new char[16][];
/* 1285 */     source.m_innerFSB = this;
/*      */ 
/*      */ 
/*      */ 
/*      */     
/* 1290 */     source.m_lastChunk = 1;
/* 1291 */     source.m_firstFree = 0;
/* 1292 */     source.m_chunkBits += this.m_rebundleBits;
/* 1293 */     source.m_chunkSize = 1 << source.m_chunkBits;
/* 1294 */     source.m_chunkMask = source.m_chunkSize - 1;
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/FastStringBuffer.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */