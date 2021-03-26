/*     */ package org.apache.xalan.xsltc.dom;
/*     */ 
/*     */ import java.util.Vector;
/*     */ import org.apache.xalan.xsltc.DOM;
/*     */ import org.apache.xalan.xsltc.Translet;
/*     */ import org.apache.xml.dtm.DTMAxisIterator;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class NodeCounter
/*     */   implements Axis
/*     */ {
/*     */   public static final int END = -1;
/*  37 */   protected int _node = -1;
/*  38 */   protected int _nodeType = -1;
/*  39 */   protected int _value = Integer.MIN_VALUE;
/*     */   
/*     */   public final DOM _document;
/*     */   
/*     */   public final DTMAxisIterator _iterator;
/*     */   
/*     */   public final Translet _translet;
/*     */   protected String _format;
/*     */   protected String _lang;
/*     */   protected String _letterValue;
/*     */   protected String _groupSep;
/*     */   protected int _groupSize;
/*     */   private boolean separFirst = true;
/*     */   private boolean separLast = false;
/*  53 */   private Vector separToks = null;
/*  54 */   private Vector formatToks = null;
/*  55 */   private int nSepars = 0;
/*  56 */   private int nFormats = 0;
/*     */   
/*  58 */   private static String[] Thousands = new String[] { "", "m", "mm", "mmm" };
/*     */   
/*  60 */   private static String[] Hundreds = new String[] { "", "c", "cc", "ccc", "cd", "d", "dc", "dcc", "dccc", "cm" };
/*     */   
/*  62 */   private static String[] Tens = new String[] { "", "x", "xx", "xxx", "xl", "l", "lx", "lxx", "lxxx", "xc" };
/*     */   
/*  64 */   private static String[] Ones = new String[] { "", "i", "ii", "iii", "iv", "v", "vi", "vii", "viii", "ix" };
/*     */ 
/*     */ 
/*     */   
/*     */   protected NodeCounter(Translet translet, DOM document, DTMAxisIterator iterator) {
/*  69 */     this._translet = translet;
/*  70 */     this._document = document;
/*  71 */     this._iterator = iterator;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract NodeCounter setStartNode(int paramInt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeCounter setValue(int value) {
/*  85 */     this._value = value;
/*  86 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void setFormatting(String format, String lang, String letterValue, String groupSep, String groupSize) {
/*  94 */     this._lang = lang;
/*  95 */     this._format = format;
/*  96 */     this._groupSep = groupSep;
/*  97 */     this._letterValue = letterValue;
/*     */ 
/*     */     
/* 100 */     try { this._groupSize = Integer.parseInt(groupSize); } catch (NumberFormatException e)
/*     */     
/*     */     { 
/* 103 */       this._groupSize = 0; }
/*     */ 
/*     */     
/* 106 */     int length = this._format.length();
/* 107 */     boolean isFirst = true;
/* 108 */     this.separFirst = true;
/* 109 */     this.separLast = false;
/*     */     
/* 111 */     this.separToks = new Vector();
/* 112 */     this.formatToks = new Vector();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 118 */     for (int j = 0, i = 0; i < length; ) {
/* 119 */       char c = this._format.charAt(i);
/* 120 */       for (j = i; Character.isLetterOrDigit(c) && 
/* 121 */         ++i != length;) {
/* 122 */         c = this._format.charAt(i);
/*     */       }
/* 124 */       if (i > j) {
/* 125 */         if (isFirst) {
/* 126 */           this.separToks.addElement(".");
/* 127 */           isFirst = this.separFirst = false;
/*     */         } 
/* 129 */         this.formatToks.addElement(this._format.substring(j, i));
/*     */       } 
/*     */       
/* 132 */       if (i == length)
/*     */         break; 
/* 134 */       c = this._format.charAt(i);
/* 135 */       for (j = i; !Character.isLetterOrDigit(c) && 
/* 136 */         ++i != length; ) {
/* 137 */         c = this._format.charAt(i);
/* 138 */         isFirst = false;
/*     */       } 
/* 140 */       if (i > j) {
/* 141 */         this.separToks.addElement(this._format.substring(j, i));
/*     */       }
/*     */     } 
/*     */     
/* 145 */     this.nSepars = this.separToks.size();
/* 146 */     this.nFormats = this.formatToks.size();
/* 147 */     if (this.nSepars > this.nFormats) this.separLast = true;
/*     */     
/* 149 */     if (this.separFirst) this.nSepars--; 
/* 150 */     if (this.separLast) this.nSepars--; 
/* 151 */     if (this.nSepars == 0) {
/* 152 */       this.separToks.insertElementAt(".", 1);
/* 153 */       this.nSepars++;
/*     */     } 
/* 155 */     if (this.separFirst) this.nSepars++;
/*     */   
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NodeCounter setDefaultFormatting() {
/* 162 */     setFormatting("1", "en", "alphabetic", null, null);
/* 163 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public abstract String getCounter();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getCounter(String format, String lang, String letterValue, String groupSep, String groupSize) {
/* 179 */     setFormatting(format, lang, letterValue, groupSep, groupSize);
/* 180 */     return getCounter();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matchesCount(int node) {
/* 189 */     return (this._nodeType == this._document.getExpandedTypeID(node));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matchesFrom(int node) {
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String formatNumbers(int value) {
/* 204 */     return formatNumbers(new int[] { value });
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected String formatNumbers(int[] values) {
/* 212 */     int nValues = values.length;
/* 213 */     int length = this._format.length();
/*     */     
/* 215 */     boolean isEmpty = true;
/* 216 */     for (int i = 0; i < nValues; i++) {
/* 217 */       if (values[i] != Integer.MIN_VALUE)
/* 218 */         isEmpty = false; 
/* 219 */     }  if (isEmpty) return "";
/*     */ 
/*     */     
/* 222 */     boolean isFirst = true;
/* 223 */     int t = 0, n = 0, s = 1;
/* 224 */     StringBuffer buffer = new StringBuffer();
/*     */ 
/*     */     
/* 227 */     if (this.separFirst) buffer.append(this.separToks.elementAt(0));
/*     */ 
/*     */     
/* 230 */     while (n < nValues) {
/* 231 */       int value = values[n];
/* 232 */       if (value != Integer.MIN_VALUE) {
/* 233 */         if (!isFirst) buffer.append(this.separToks.elementAt(s++)); 
/* 234 */         formatValue(value, this.formatToks.elementAt(t++), buffer);
/* 235 */         if (t == this.nFormats) t--; 
/* 236 */         if (s >= this.nSepars) s--; 
/* 237 */         isFirst = false;
/*     */       } 
/* 239 */       n++;
/*     */     } 
/*     */ 
/*     */     
/* 243 */     if (this.separLast) buffer.append(this.separToks.lastElement()); 
/* 244 */     return buffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void formatValue(int value, String format, StringBuffer buffer) {
/* 253 */     char c = format.charAt(0);
/*     */     
/* 255 */     if (Character.isDigit(c)) {
/* 256 */       char zero = (char)(c - Character.getNumericValue(c));
/*     */       
/* 258 */       StringBuffer temp = buffer;
/* 259 */       if (this._groupSize > 0) {
/* 260 */         temp = new StringBuffer();
/*     */       }
/* 262 */       String s = "";
/* 263 */       int n = value;
/* 264 */       while (n > 0) {
/* 265 */         s = (char)(zero + n % 10) + s;
/* 266 */         n /= 10;
/*     */       } 
/*     */       
/* 269 */       for (int i = 0; i < format.length() - s.length(); i++) {
/* 270 */         temp.append(zero);
/*     */       }
/* 272 */       temp.append(s);
/*     */       
/* 274 */       if (this._groupSize > 0) {
/* 275 */         for (int j = 0; j < temp.length(); j++) {
/* 276 */           if (j != 0 && (temp.length() - j) % this._groupSize == 0) {
/* 277 */             buffer.append(this._groupSep);
/*     */           }
/* 279 */           buffer.append(temp.charAt(j));
/*     */         }
/*     */       
/*     */       }
/* 283 */     } else if (c == 'i' && !this._letterValue.equals("alphabetic")) {
/* 284 */       buffer.append(romanValue(value));
/*     */     }
/* 286 */     else if (c == 'I' && !this._letterValue.equals("alphabetic")) {
/* 287 */       buffer.append(romanValue(value).toUpperCase());
/*     */     } else {
/*     */       
/* 290 */       int min = c;
/* 291 */       int max = c;
/*     */ 
/*     */       
/* 294 */       if (c >= 'α' && c <= 'ω') {
/* 295 */         max = 969;
/*     */       }
/*     */       else {
/*     */         
/* 299 */         while (Character.isLetterOrDigit((char)(max + 1))) {
/* 300 */           max++;
/*     */         }
/*     */       } 
/* 303 */       buffer.append(alphaValue(value, min, max));
/*     */     } 
/*     */   }
/*     */   
/*     */   private String alphaValue(int value, int min, int max) {
/* 308 */     if (value <= 0) {
/* 309 */       return "" + value;
/*     */     }
/*     */     
/* 312 */     int range = max - min + 1;
/* 313 */     char last = (char)((value - 1) % range + min);
/* 314 */     if (value > range) {
/* 315 */       return alphaValue((value - 1) / range, min, max) + last;
/*     */     }
/*     */     
/* 318 */     return "" + last;
/*     */   }
/*     */ 
/*     */   
/*     */   private String romanValue(int n) {
/* 323 */     if (n <= 0 || n > 4000) {
/* 324 */       return "" + n;
/*     */     }
/* 326 */     return Thousands[n / 1000] + Hundreds[n / 100 % 10] + Tens[n / 10 % 10] + Ones[n % 10];
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/dom/NodeCounter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */