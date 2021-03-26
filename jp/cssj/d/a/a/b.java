/*     */ package jp.cssj.d.a.a;
/*     */ 
/*     */ import org.xml.sax.ContentHandler;
/*     */ import org.xml.sax.SAXException;
/*     */ import org.xml.sax.helpers.AttributesImpl;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class b
/*     */ {
/*  12 */   static final AttributesImpl a = new AttributesImpl(); private static final String b = "０１２３４５６７８９“”．－"; private static final String c = "〇一二三四五六七八九〝〟・―"; private static final String d = "0123456789.,"; private static final String e = "０１２３４５６７８９・，";
/*     */   static {
/*  14 */     a.addAttribute("", "class", "class", "CDATA", "x-epub-tcy pre");
/*     */   }
/*     */   
/*     */   public static void a(char[] ch, int off, int len, ContentHandler handler) throws SAXException {
/*  18 */     a(ch, off, len);
/*  19 */     int end = off + len;
/*  20 */     int run = 0;
/*  21 */     int i = off; while (true) { if (i < end)
/*  22 */       { char c = ch[i];
/*  23 */         if (c >= '0' && c <= '9') {
/*  24 */           run++;
/*     */           continue;
/*     */         } 
/*  27 */         if (run == 2)
/*  28 */         { Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(c);
/*  29 */           if (unicodeBlock != Character.UnicodeBlock.CJK_COMPATIBILITY && unicodeBlock != Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS && unicodeBlock != Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS && unicodeBlock != Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS_SUPPLEMENT && unicodeBlock != Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT && unicodeBlock != Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION && unicodeBlock != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS && unicodeBlock != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A && unicodeBlock != Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B && unicodeBlock != Character.UnicodeBlock.LETTERLIKE_SYMBOLS && unicodeBlock != Character.UnicodeBlock.HIRAGANA && unicodeBlock != Character.UnicodeBlock.KATAKANA && unicodeBlock != Character.UnicodeBlock.KATAKANA_PHONETIC_EXTENSIONS && unicodeBlock != Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS)
/*     */           
/*     */           { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*  39 */             run++; }
/*     */           else
/*     */           
/*  42 */           { int tlen = i - off - run;
/*  43 */             if (tlen > 0) {
/*  44 */               b(ch, off, tlen);
/*  45 */               handler.characters(ch, off, tlen);
/*     */             } 
/*  47 */             handler.startElement("http://www.w3.org/1999/xhtml", "span", "span", a);
/*  48 */             handler.characters(ch, i - run, run);
/*  49 */             handler.endElement("http://www.w3.org/1999/xhtml", "span", "span");
/*  50 */             len -= i - off;
/*  51 */             off = i;
/*     */             
/*  53 */             run = 0; }  continue; }  } else { break; }  run = 0; i++; }
/*     */     
/*  55 */     if (len == 0) {
/*     */       return;
/*     */     }
/*  58 */     b(ch, off, len);
/*  59 */     handler.characters(ch, off, len);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void a(char[] ch, int off, int len) {
/*  67 */     int end = off + len;
/*  68 */     for (int i = off; i < end; i++) {
/*  69 */       if (ch[i] == 'h' && end - i >= 5 && ch[i + 1] == 't' && ch[i + 2] == 't' && ch[i + 3] == 'p' && ch[i + 4] == ':') {
/*     */         
/*  71 */         for (i += 5; i < end && 
/*  72 */           !Character.isWhitespace(ch[i]) && ch[i] <= 'ÿ'; i++);
/*     */ 
/*     */ 
/*     */         
/*  76 */         if (i >= end) {
/*     */           break;
/*     */         }
/*     */       } 
/*  80 */       ch[i] = a(ch[i]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static char a(char c) {
/*  85 */     int ix = "０１２３４５６７８９“”．－".indexOf(c);
/*  86 */     if (ix != -1) {
/*  87 */       c = "〇一二三四五六七八九〝〟・―".charAt(ix);
/*     */     }
/*  89 */     return c;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void b(char[] ch, int off, int len) {
/*  97 */     int end = off + len;
/*  98 */     for (int i = off; i < end; i++) {
/*  99 */       if (ch[i] == 'h' && end - i >= 5 && ch[i + 1] == 't' && ch[i + 2] == 't' && ch[i + 3] == 'p' && ch[i + 4] == ':') {
/*     */         
/* 101 */         for (i += 5; i < end && 
/* 102 */           !Character.isWhitespace(ch[i]) && ch[i] <= 'ÿ'; i++);
/*     */ 
/*     */ 
/*     */         
/* 106 */         if (i >= end) {
/*     */           break;
/*     */         }
/*     */       } 
/* 110 */       ch[i] = b(ch[i]);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static char b(char c) {
/* 115 */     int ix = "0123456789.,".indexOf(c);
/* 116 */     if (ix != -1) {
/* 117 */       c = "０１２３４５６７８９・，".charAt(ix);
/*     */     }
/* 119 */     return c;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/d/a/a/b.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */