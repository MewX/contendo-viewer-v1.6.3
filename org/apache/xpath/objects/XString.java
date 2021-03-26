/*      */ package org.apache.xpath.objects;
/*      */ 
/*      */ import java.util.Locale;
/*      */ import javax.xml.transform.TransformerException;
/*      */ import org.apache.xml.dtm.DTM;
/*      */ import org.apache.xml.utils.WrappedRuntimeException;
/*      */ import org.apache.xml.utils.XMLCharacterRecognizer;
/*      */ import org.apache.xml.utils.XMLString;
/*      */ import org.apache.xml.utils.XMLStringFactory;
/*      */ import org.apache.xpath.ExpressionOwner;
/*      */ import org.apache.xpath.XPathContext;
/*      */ import org.apache.xpath.XPathVisitor;
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
/*      */ public class XString
/*      */   extends XObject
/*      */   implements XMLString
/*      */ {
/*   40 */   public static XString EMPTYSTRING = new XString("");
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   protected XString(Object val) {
/*   49 */     super(val);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XString(String val) {
/*   59 */     super(val);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int getType() {
/*   69 */     return 3;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String getTypeString() {
/*   80 */     return "#STRING";
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean hasString() {
/*   90 */     return true;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double num() {
/*  101 */     return toDouble();
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
/*      */   public double toDouble() {
/*  113 */     int end = length();
/*      */     
/*  115 */     if (0 == end) {
/*  116 */       return Double.NaN;
/*      */     }
/*  118 */     double result = 0.0D;
/*  119 */     int start = 0;
/*  120 */     int punctPos = end - 1;
/*      */ 
/*      */     
/*  123 */     for (int i = start; i < end; i++) {
/*      */       
/*  125 */       char c = charAt(i);
/*      */       
/*  127 */       if (!XMLCharacterRecognizer.isWhiteSpace(c)) {
/*      */         break;
/*      */       }
/*      */ 
/*      */       
/*  132 */       start++;
/*      */     } 
/*      */     
/*  135 */     double sign = 1.0D;
/*      */     
/*  137 */     if (start < end && charAt(start) == '-') {
/*      */       
/*  139 */       sign = -1.0D;
/*      */       
/*  141 */       start++;
/*      */     } 
/*      */     
/*  144 */     int digitsFound = 0;
/*      */     
/*  146 */     for (int j = start; j < end; j++) {
/*      */       
/*  148 */       char c = charAt(j);
/*      */       
/*  150 */       if (c != '.') {
/*      */         
/*  152 */         if (XMLCharacterRecognizer.isWhiteSpace(c))
/*      */           break; 
/*  154 */         if (Character.isDigit(c))
/*      */         {
/*  156 */           result = result * 10.0D + (c - 48);
/*      */           
/*  158 */           digitsFound++;
/*      */         }
/*      */         else
/*      */         {
/*  162 */           return Double.NaN;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/*  167 */         punctPos = j;
/*      */         
/*      */         break;
/*      */       } 
/*      */     } 
/*      */     
/*  173 */     if (charAt(punctPos) == '.') {
/*      */       
/*  175 */       double fractPart = 0.0D;
/*      */       
/*  177 */       for (int k = end - 1; k > punctPos; k--) {
/*      */         
/*  179 */         char c = charAt(k);
/*      */         
/*  181 */         if (XMLCharacterRecognizer.isWhiteSpace(c))
/*      */           break; 
/*  183 */         if (Character.isDigit(c)) {
/*      */           
/*  185 */           fractPart = fractPart / 10.0D + (c - 48);
/*      */           
/*  187 */           digitsFound++;
/*      */         }
/*      */         else {
/*      */           
/*  191 */           return Double.NaN;
/*      */         } 
/*      */       } 
/*      */       
/*  195 */       result += fractPart / 10.0D;
/*      */     } 
/*      */     
/*  198 */     if (0 == digitsFound) {
/*  199 */       return Double.NaN;
/*      */     }
/*  201 */     return result * sign;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean bool() {
/*  212 */     return (str().length() > 0);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLString xstr() {
/*  222 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public String str() {
/*  232 */     return (null != this.m_obj) ? (String)this.m_obj : "";
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
/*      */   public int rtf(XPathContext support) {
/*  245 */     DTM frag = support.createDocumentFragment();
/*      */     
/*  247 */     frag.appendTextChild(str());
/*      */     
/*  249 */     return frag.getDocument();
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
/*      */   public void dispatchCharactersEvents(ContentHandler ch) throws SAXException {
/*  267 */     String str = str();
/*      */     
/*  269 */     ch.characters(str.toCharArray(), 0, str.length());
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
/*      */   public void dispatchAsComment(LexicalHandler lh) throws SAXException {
/*  285 */     String str = str();
/*      */     
/*  287 */     lh.comment(str.toCharArray(), 0, str.length());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public int length() {
/*  298 */     return str().length();
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
/*      */   public char charAt(int index) {
/*  316 */     return str().charAt(index);
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
/*      */   public void getChars(int srcBegin, int srcEnd, char[] dst, int dstBegin) {
/*  342 */     str().getChars(srcBegin, srcEnd, dst, dstBegin);
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
/*      */   public boolean equals(XObject obj2) {
/*  360 */     int t = obj2.getType();
/*      */ 
/*      */     
/*  363 */     try { if (4 == t) {
/*  364 */         return obj2.equals(this);
/*      */       }
/*      */ 
/*      */       
/*  368 */       if (1 == t) {
/*  369 */         return (obj2.bool() == bool());
/*      */       }
/*      */       
/*  372 */       if (2 == t)
/*  373 */         return (obj2.num() == num());  } catch (TransformerException te)
/*      */     
/*      */     { 
/*      */       
/*  377 */       throw new WrappedRuntimeException(te); }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  382 */     return xstr().equals(obj2.xstr());
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
/*      */   public boolean equals(XMLString obj2) {
/*  401 */     if (!obj2.hasString()) {
/*  402 */       return obj2.equals(this);
/*      */     }
/*  404 */     return str().equals(obj2.toString());
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
/*      */   public boolean equals(Object obj2) {
/*  425 */     if (null == obj2) {
/*  426 */       return false;
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  431 */     if (obj2 instanceof XNodeSet)
/*  432 */       return obj2.equals(this); 
/*  433 */     if (obj2 instanceof XNumber) {
/*  434 */       return obj2.equals(this);
/*      */     }
/*  436 */     return str().equals(obj2.toString());
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
/*      */   public boolean equalsIgnoreCase(String anotherString) {
/*  456 */     return str().equalsIgnoreCase(anotherString);
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
/*      */   public int compareTo(XMLString xstr) {
/*  476 */     int len1 = length();
/*  477 */     int len2 = xstr.length();
/*  478 */     int n = Math.min(len1, len2);
/*  479 */     int i = 0;
/*  480 */     int j = 0;
/*      */     
/*  482 */     while (n-- != 0) {
/*      */       
/*  484 */       char c1 = charAt(i);
/*  485 */       char c2 = xstr.charAt(j);
/*      */       
/*  487 */       if (c1 != c2)
/*      */       {
/*  489 */         return c1 - c2;
/*      */       }
/*      */       
/*  492 */       i++;
/*  493 */       j++;
/*      */     } 
/*      */     
/*  496 */     return len1 - len2;
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
/*      */   public int compareToIgnoreCase(XMLString str) {
/*  527 */     throw new WrappedRuntimeException(new NoSuchMethodException("Java 1.2 method, not yet implemented"));
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
/*      */   public boolean startsWith(String prefix, int toffset) {
/*  553 */     return str().startsWith(prefix, toffset);
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
/*      */   public boolean startsWith(String prefix) {
/*  572 */     return startsWith(prefix, 0);
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
/*      */   public boolean startsWith(XMLString prefix, int toffset) {
/*  597 */     int to = toffset;
/*  598 */     int tlim = length();
/*  599 */     int po = 0;
/*  600 */     int pc = prefix.length();
/*      */ 
/*      */     
/*  603 */     if (toffset < 0 || toffset > tlim - pc)
/*      */     {
/*  605 */       return false;
/*      */     }
/*      */     
/*  608 */     while (--pc >= 0) {
/*      */       
/*  610 */       if (charAt(to) != prefix.charAt(po))
/*      */       {
/*  612 */         return false;
/*      */       }
/*      */       
/*  615 */       to++;
/*  616 */       po++;
/*      */     } 
/*      */     
/*  619 */     return true;
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
/*      */   public boolean startsWith(XMLString prefix) {
/*  638 */     return startsWith(prefix, 0);
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
/*      */   public boolean endsWith(String suffix) {
/*  656 */     return str().endsWith(suffix);
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
/*      */   public int hashCode() {
/*  674 */     return str().hashCode();
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
/*      */   public int indexOf(int ch) {
/*  696 */     return str().indexOf(ch);
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
/*      */   public int indexOf(int ch, int fromIndex) {
/*  729 */     return str().indexOf(ch, fromIndex);
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
/*      */   public int lastIndexOf(int ch) {
/*  749 */     return str().lastIndexOf(ch);
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
/*      */   public int lastIndexOf(int ch, int fromIndex) {
/*  777 */     return str().lastIndexOf(ch, fromIndex);
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
/*      */   public int indexOf(String str) {
/*  799 */     return str().indexOf(str);
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
/*      */   public int indexOf(XMLString str) {
/*  821 */     return str().indexOf(str.toString());
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
/*      */   public int indexOf(String str, int fromIndex) {
/*  852 */     return str().indexOf(str, fromIndex);
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
/*      */   public int lastIndexOf(String str) {
/*  875 */     return str().lastIndexOf(str);
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
/*      */   public int lastIndexOf(String str, int fromIndex) {
/*  900 */     return str().lastIndexOf(str, fromIndex);
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
/*      */   public XMLString substring(int beginIndex) {
/*  922 */     return new XString(str().substring(beginIndex));
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
/*      */   public XMLString substring(int beginIndex, int endIndex) {
/*  943 */     return new XString(str().substring(beginIndex, endIndex));
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
/*      */   public XMLString concat(String str) {
/*  960 */     return new XString(str().concat(str));
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
/*      */   public XMLString toLowerCase(Locale locale) {
/*  974 */     return new XString(str().toLowerCase(locale));
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
/*      */   public XMLString toLowerCase() {
/*  989 */     return new XString(str().toLowerCase());
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
/*      */   public XMLString toUpperCase(Locale locale) {
/* 1002 */     return new XString(str().toUpperCase(locale));
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
/*      */   public XMLString toUpperCase() {
/* 1033 */     return new XString(str().toUpperCase());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public XMLString trim() {
/* 1043 */     return new XString(str().trim());
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
/*      */   private static boolean isSpace(char ch) {
/* 1055 */     return XMLCharacterRecognizer.isWhiteSpace(ch);
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
/*      */   public XMLString fixWhiteSpace(boolean trimHead, boolean trimTail, boolean doublePunctuationSpaces) {
/* 1077 */     int len = length();
/* 1078 */     char[] buf = new char[len];
/*      */     
/* 1080 */     getChars(0, len, buf, 0);
/*      */     
/* 1082 */     boolean edit = false;
/*      */     
/*      */     int s;
/* 1085 */     for (s = 0; s < len; s++) {
/*      */       
/* 1087 */       if (isSpace(buf[s])) {
/*      */         break;
/*      */       }
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/* 1094 */     int d = s;
/* 1095 */     boolean pres = false;
/*      */     
/* 1097 */     for (; s < len; s++) {
/*      */       
/* 1099 */       char c = buf[s];
/*      */       
/* 1101 */       if (isSpace(c)) {
/*      */         
/* 1103 */         if (!pres) {
/*      */           
/* 1105 */           if (' ' != c)
/*      */           {
/* 1107 */             edit = true;
/*      */           }
/*      */           
/* 1110 */           buf[d++] = ' ';
/*      */           
/* 1112 */           if (doublePunctuationSpaces && s != 0)
/*      */           {
/* 1114 */             char prevChar = buf[s - 1];
/*      */             
/* 1116 */             if (prevChar != '.' && prevChar != '!' && prevChar != '?')
/*      */             {
/*      */               
/* 1119 */               pres = true;
/*      */             }
/*      */           }
/*      */           else
/*      */           {
/* 1124 */             pres = true;
/*      */           }
/*      */         
/*      */         } else {
/*      */           
/* 1129 */           edit = true;
/* 1130 */           pres = true;
/*      */         }
/*      */       
/*      */       } else {
/*      */         
/* 1135 */         buf[d++] = c;
/* 1136 */         pres = false;
/*      */       } 
/*      */     } 
/*      */     
/* 1140 */     if (trimTail && 1 <= d && ' ' == buf[d - 1]) {
/*      */       
/* 1142 */       edit = true;
/*      */       
/* 1144 */       d--;
/*      */     } 
/*      */     
/* 1147 */     int start = 0;
/*      */     
/* 1149 */     if (trimHead && 0 < d && ' ' == buf[0]) {
/*      */       
/* 1151 */       edit = true;
/*      */       
/* 1153 */       start++;
/*      */     } 
/*      */     
/* 1156 */     XMLStringFactory xsf = XMLStringFactoryImpl.getFactory();
/*      */     
/* 1158 */     return edit ? xsf.newstr(new String(buf, start, d - start)) : this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void callVisitors(ExpressionOwner owner, XPathVisitor visitor) {
/* 1166 */     visitor.visitStringLiteral(owner, this);
/*      */   }
/*      */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/objects/XString.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */