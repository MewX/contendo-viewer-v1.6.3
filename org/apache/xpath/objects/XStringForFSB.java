/*     */ package org.apache.xpath.objects;
/*     */ 
/*     */ import org.apache.xml.utils.FastStringBuffer;
/*     */ import org.apache.xml.utils.XMLCharacterRecognizer;
/*     */ import org.apache.xml.utils.XMLString;
/*     */ import org.apache.xml.utils.XMLStringFactory;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.ext.LexicalHandler;
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
/*     */ public class XStringForFSB
/*     */   extends XString
/*     */ {
/*     */   int m_start;
/*     */   int m_length;
/*  41 */   protected String m_strCache = null;
/*     */ 
/*     */   
/*  44 */   protected int m_hash = 0;
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
/*     */   public XStringForFSB(FastStringBuffer val, int start, int length) {
/*  56 */     super(val);
/*     */     
/*  58 */     this.m_start = start;
/*  59 */     this.m_length = length;
/*     */     
/*  61 */     if (null == val) {
/*  62 */       throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_FASTSTRINGBUFFER_CANNOT_BE_NULL", null));
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
/*     */   private XStringForFSB(String val) {
/*  74 */     super(val);
/*     */     
/*  76 */     throw new IllegalArgumentException(XPATHMessages.createXPATHMessage("ER_FSB_CANNOT_TAKE_STRING", null));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public FastStringBuffer fsb() {
/*  87 */     return (FastStringBuffer)this.m_obj;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void appendToFsb(FastStringBuffer fsb) {
/*  98 */     fsb.append(str());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasString() {
/* 108 */     return (null != this.m_strCache);
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
/*     */   public Object object() {
/* 125 */     return str();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String str() {
/* 136 */     if (null == this.m_strCache)
/*     */     {
/* 138 */       this.m_strCache = fsb().getString(this.m_start, this.m_length);
/*     */     }
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
/* 164 */     return this.m_strCache;
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
/*     */   public void dispatchCharactersEvents(ContentHandler ch) throws SAXException {
/* 181 */     fsb().sendSAXcharacters(ch, this.m_start, this.m_length);
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
/*     */   public void dispatchAsComment(LexicalHandler lh) throws SAXException {
/* 196 */     fsb().sendSAXComment(lh, this.m_start, this.m_length);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int length() {
/* 207 */     return this.m_length;
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
/*     */   public char charAt(int index) {
/* 225 */     return fsb().charAt(this.m_start + index);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
/* 254 */     int n = srcEnd - srcBegin;
/*     */     
/* 256 */     if (n > this.m_length) {
/* 257 */       n = this.m_length;
/*     */     }
/* 259 */     if (n > dst.length - dstBegin) {
/* 260 */       n = dst.length - dstBegin;
/*     */     }
/* 262 */     int end = srcBegin + this.m_start + n;
/* 263 */     int d = dstBegin;
/* 264 */     FastStringBuffer fsb = fsb();
/*     */     
/* 266 */     for (int i = srcBegin + this.m_start; i < end; i++)
/*     */     {
/* 268 */       dst[d++] = fsb.charAt(i);
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
/*     */   public boolean equals(XMLString obj2) {
/* 290 */     if (this == obj2)
/*     */     {
/* 292 */       return true;
/*     */     }
/*     */     
/* 295 */     int n = this.m_length;
/*     */     
/* 297 */     if (n == obj2.length()) {
/*     */       
/* 299 */       FastStringBuffer fsb = fsb();
/* 300 */       int i = this.m_start;
/* 301 */       int j = 0;
/*     */       
/* 303 */       while (n-- != 0) {
/*     */         
/* 305 */         if (fsb.charAt(i) != obj2.charAt(j))
/*     */         {
/* 307 */           return false;
/*     */         }
/*     */         
/* 310 */         i++;
/* 311 */         j++;
/*     */       } 
/*     */       
/* 314 */       return true;
/*     */     } 
/*     */     
/* 317 */     return false;
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
/*     */   public boolean equals(XObject obj2) {
/* 332 */     if (this == obj2)
/*     */     {
/* 334 */       return true;
/*     */     }
/* 336 */     if (obj2.getType() == 2) {
/* 337 */       return obj2.equals(this);
/*     */     }
/* 339 */     String str = obj2.str();
/* 340 */     int n = this.m_length;
/*     */     
/* 342 */     if (n == str.length()) {
/*     */       
/* 344 */       FastStringBuffer fsb = fsb();
/* 345 */       int i = this.m_start;
/* 346 */       int j = 0;
/*     */       
/* 348 */       while (n-- != 0) {
/*     */         
/* 350 */         if (fsb.charAt(i) != str.charAt(j))
/*     */         {
/* 352 */           return false;
/*     */         }
/*     */         
/* 355 */         i++;
/* 356 */         j++;
/*     */       } 
/*     */       
/* 359 */       return true;
/*     */     } 
/*     */     
/* 362 */     return false;
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
/*     */   public boolean equals(String anotherString) {
/* 379 */     int n = this.m_length;
/*     */     
/* 381 */     if (n == anotherString.length()) {
/*     */       
/* 383 */       FastStringBuffer fsb = fsb();
/* 384 */       int i = this.m_start;
/* 385 */       int j = 0;
/*     */       
/* 387 */       while (n-- != 0) {
/*     */         
/* 389 */         if (fsb.charAt(i) != anotherString.charAt(j))
/*     */         {
/* 391 */           return false;
/*     */         }
/*     */         
/* 394 */         i++;
/* 395 */         j++;
/*     */       } 
/*     */       
/* 398 */       return true;
/*     */     } 
/*     */     
/* 401 */     return false;
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
/*     */   public boolean equals(Object obj2) {
/* 422 */     if (null == obj2) {
/* 423 */       return false;
/*     */     }
/* 425 */     if (obj2 instanceof XNumber) {
/* 426 */       return obj2.equals(this);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 431 */     if (obj2 instanceof XNodeSet)
/* 432 */       return obj2.equals(this); 
/* 433 */     if (obj2 instanceof XStringForFSB) {
/* 434 */       return equals(this);
/*     */     }
/* 436 */     return equals(obj2.toString());
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
/*     */   public boolean equalsIgnoreCase(String anotherString) {
/* 456 */     return (this.m_length == anotherString.length()) ? str().equalsIgnoreCase(anotherString) : false;
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
/*     */   public int compareTo(XMLString xstr) {
/* 477 */     int len1 = this.m_length;
/* 478 */     int len2 = xstr.length();
/* 479 */     int n = Math.min(len1, len2);
/* 480 */     FastStringBuffer fsb = fsb();
/* 481 */     int i = this.m_start;
/* 482 */     int j = 0;
/*     */     
/* 484 */     while (n-- != 0) {
/*     */       
/* 486 */       char c1 = fsb.charAt(i);
/* 487 */       char c2 = xstr.charAt(j);
/*     */       
/* 489 */       if (c1 != c2)
/*     */       {
/* 491 */         return c1 - c2;
/*     */       }
/*     */       
/* 494 */       i++;
/* 495 */       j++;
/*     */     } 
/*     */     
/* 498 */     return len1 - len2;
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
/*     */ 
/*     */ 
/*     */   
/*     */   public int compareToIgnoreCase(XMLString xstr) {
/* 524 */     int len1 = this.m_length;
/* 525 */     int len2 = xstr.length();
/* 526 */     int n = Math.min(len1, len2);
/* 527 */     FastStringBuffer fsb = fsb();
/* 528 */     int i = this.m_start;
/* 529 */     int j = 0;
/*     */     
/* 531 */     while (n-- != 0) {
/*     */       
/* 533 */       char c1 = Character.toLowerCase(fsb.charAt(i));
/* 534 */       char c2 = Character.toLowerCase(xstr.charAt(j));
/*     */       
/* 536 */       if (c1 != c2)
/*     */       {
/* 538 */         return c1 - c2;
/*     */       }
/*     */       
/* 541 */       i++;
/* 542 */       j++;
/*     */     } 
/*     */     
/* 545 */     return len1 - len2;
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
/*     */   public int hashCode() {
/* 589 */     return super.hashCode();
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
/*     */ 
/*     */   
/*     */   public boolean startsWith(XMLString prefix, int toffset) {
/* 614 */     FastStringBuffer fsb = fsb();
/* 615 */     int to = this.m_start + toffset;
/* 616 */     int tlim = this.m_start + this.m_length;
/* 617 */     int po = 0;
/* 618 */     int pc = prefix.length();
/*     */ 
/*     */     
/* 621 */     if (toffset < 0 || toffset > this.m_length - pc)
/*     */     {
/* 623 */       return false;
/*     */     }
/*     */     
/* 626 */     while (--pc >= 0) {
/*     */       
/* 628 */       if (fsb.charAt(to) != prefix.charAt(po))
/*     */       {
/* 630 */         return false;
/*     */       }
/*     */       
/* 633 */       to++;
/* 634 */       po++;
/*     */     } 
/*     */     
/* 637 */     return true;
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
/*     */   public boolean startsWith(XMLString prefix) {
/* 657 */     return startsWith(prefix, 0);
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
/*     */   public int indexOf(int ch) {
/* 679 */     return indexOf(ch, 0);
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
/*     */   public int indexOf(int ch, int fromIndex) {
/* 713 */     int max = this.m_start + this.m_length;
/* 714 */     FastStringBuffer fsb = fsb();
/*     */     
/* 716 */     if (fromIndex < 0) {
/*     */       
/* 718 */       fromIndex = 0;
/*     */     }
/* 720 */     else if (fromIndex >= this.m_length) {
/*     */ 
/*     */ 
/*     */       
/* 724 */       return -1;
/*     */     } 
/*     */     
/* 727 */     for (int i = this.m_start + fromIndex; i < max; i++) {
/*     */       
/* 729 */       if (fsb.charAt(i) == ch)
/*     */       {
/* 731 */         return i - this.m_start;
/*     */       }
/*     */     } 
/*     */     
/* 735 */     return -1;
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
/*     */   public XMLString substring(int beginIndex) {
/* 758 */     int len = this.m_length - beginIndex;
/*     */     
/* 760 */     if (len <= 0) {
/* 761 */       return XString.EMPTYSTRING;
/*     */     }
/*     */     
/* 764 */     int start = this.m_start + beginIndex;
/*     */     
/* 766 */     return new XStringForFSB(fsb(), start, len);
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
/*     */   public XMLString substring(int beginIndex, int endIndex) {
/* 789 */     int len = endIndex - beginIndex;
/*     */     
/* 791 */     if (len > this.m_length) {
/* 792 */       len = this.m_length;
/*     */     }
/* 794 */     if (len <= 0) {
/* 795 */       return XString.EMPTYSTRING;
/*     */     }
/*     */     
/* 798 */     int start = this.m_start + beginIndex;
/*     */     
/* 800 */     return new XStringForFSB(fsb(), start, len);
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
/*     */   public XMLString concat(String str) {
/* 818 */     return new XString(str().concat(str));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public XMLString trim() {
/* 828 */     return fixWhiteSpace(true, true, false);
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
/*     */   private static boolean isSpace(char ch) {
/* 840 */     return XMLCharacterRecognizer.isWhiteSpace(ch);
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
/*     */   public XMLString fixWhiteSpace(boolean trimHead, boolean trimTail, boolean doublePunctuationSpaces) {
/* 861 */     int end = this.m_length + this.m_start;
/* 862 */     char[] buf = new char[this.m_length];
/* 863 */     FastStringBuffer fsb = fsb();
/* 864 */     boolean edit = false;
/*     */ 
/*     */     
/* 867 */     int d = 0;
/* 868 */     boolean pres = false;
/*     */     
/* 870 */     for (int s = this.m_start; s < end; s++) {
/*     */       
/* 872 */       char c = fsb.charAt(s);
/*     */       
/* 874 */       if (isSpace(c)) {
/*     */         
/* 876 */         if (!pres) {
/*     */           
/* 878 */           if (' ' != c)
/*     */           {
/* 880 */             edit = true;
/*     */           }
/*     */           
/* 883 */           buf[d++] = ' ';
/*     */           
/* 885 */           if (doublePunctuationSpaces && d != 0)
/*     */           {
/* 887 */             char prevChar = buf[d - 1];
/*     */             
/* 889 */             if (prevChar != '.' && prevChar != '!' && prevChar != '?')
/*     */             {
/*     */               
/* 892 */               pres = true;
/*     */             }
/*     */           }
/*     */           else
/*     */           {
/* 897 */             pres = true;
/*     */           }
/*     */         
/*     */         } else {
/*     */           
/* 902 */           edit = true;
/* 903 */           pres = true;
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 908 */         buf[d++] = c;
/* 909 */         pres = false;
/*     */       } 
/*     */     } 
/*     */     
/* 913 */     if (trimTail && 1 <= d && ' ' == buf[d - 1]) {
/*     */       
/* 915 */       edit = true;
/*     */       
/* 917 */       d--;
/*     */     } 
/*     */     
/* 920 */     int start = 0;
/*     */     
/* 922 */     if (trimHead && 0 < d && ' ' == buf[0]) {
/*     */       
/* 924 */       edit = true;
/*     */       
/* 926 */       start++;
/*     */     } 
/*     */     
/* 929 */     XMLStringFactory xsf = XMLStringFactoryImpl.getFactory();
/*     */     
/* 931 */     return edit ? xsf.newstr(buf, start, d - start) : this;
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
/*     */   public double toDouble() {
/* 952 */     if (this.m_length == 0) {
/* 953 */       return Double.NaN;
/*     */     }
/*     */     
/* 956 */     String valueString = fsb().getString(this.m_start, this.m_length);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */ 
/*     */     
/* 965 */     for (i = 0; i < this.m_length && 
/* 966 */       XMLCharacterRecognizer.isWhiteSpace(valueString.charAt(i)); i++);
/*     */     
/* 968 */     if (valueString.charAt(i) == '-')
/* 969 */       i++; 
/* 970 */     for (; i < this.m_length; i++) {
/* 971 */       char c = valueString.charAt(i);
/* 972 */       if (c != '.' && (c < '0' || c > '9'))
/*     */         break; 
/*     */     } 
/* 975 */     for (; i < this.m_length && 
/* 976 */       XMLCharacterRecognizer.isWhiteSpace(valueString.charAt(i)); i++);
/*     */     
/* 978 */     if (i != this.m_length) {
/* 979 */       return Double.NaN;
/*     */     }
/*     */     
/* 982 */     try { return (new Double(valueString)).doubleValue(); } catch (NumberFormatException nfe)
/*     */     
/*     */     { 
/* 985 */       return Double.NaN; }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XStringForFSB.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */