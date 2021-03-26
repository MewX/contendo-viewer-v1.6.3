/*     */ package org.apache.xalan.transformer;
/*     */ 
/*     */ import java.text.NumberFormat;
/*     */ import java.util.Locale;
/*     */ import java.util.NoSuchElementException;
/*     */ import org.w3c.dom.Element;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class NumeratorFormatter
/*     */ {
/*     */   protected Element m_xslNumberElement;
/*     */   NumberFormatStringTokenizer m_formatTokenizer;
/*     */   Locale m_locale;
/*     */   NumberFormat m_formatter;
/*     */   TransformerImpl m_processor;
/*  53 */   private static final DecimalToRoman[] m_romanConvertTable = new DecimalToRoman[] { new DecimalToRoman(1000L, "M", 900L, "CM"), new DecimalToRoman(500L, "D", 400L, "CD"), new DecimalToRoman(100L, "C", 90L, "XC"), new DecimalToRoman(50L, "L", 40L, "XL"), new DecimalToRoman(10L, "X", 9L, "IX"), new DecimalToRoman(5L, "V", 4L, "IV"), new DecimalToRoman(1L, "I", 1L, "I") };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   private static final char[] m_alphaCountTable = new char[] { 'Z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   NumeratorFormatter(Element xslNumberElement, TransformerImpl processor) {
/*  84 */     this.m_xslNumberElement = xslNumberElement;
/*  85 */     this.m_processor = processor;
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
/*     */   protected String int2alphaCount(int val, char[] table) {
/* 103 */     int radix = table.length;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 108 */     char[] buf = new char[100];
/*     */ 
/*     */     
/* 111 */     int charPos = buf.length - 1;
/*     */ 
/*     */     
/* 114 */     int lookupIndex = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 140 */     int correction = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 149 */       correction = (lookupIndex == 0 || (correction != 0 && lookupIndex == radix - 1)) ? (radix - 1) : 0;
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 154 */       lookupIndex = (val + correction) % radix;
/*     */ 
/*     */       
/* 157 */       val /= radix;
/*     */ 
/*     */       
/* 160 */       if (lookupIndex == 0 && val == 0) {
/*     */         break;
/*     */       }
/*     */       
/* 164 */       buf[charPos--] = table[lookupIndex];
/*     */     }
/* 166 */     while (val > 0);
/*     */     
/* 168 */     return new String(buf, charPos + 1, buf.length - charPos - 1);
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
/*     */   String long2roman(long val, boolean prefixesAreOK) {
/* 183 */     if (val <= 0L)
/*     */     {
/* 185 */       return "#E(" + val + ")";
/*     */     }
/*     */     
/* 188 */     String roman = "";
/* 189 */     int place = 0;
/*     */     
/* 191 */     if (val <= 3999L)
/*     */     
/*     */     { while (true)
/*     */       { while (true) {
/* 195 */           if (val < (m_romanConvertTable[place]).m_postValue) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 201 */             if (prefixesAreOK)
/*     */             {
/* 203 */               if (val >= (m_romanConvertTable[place]).m_preValue) {
/*     */                 
/* 205 */                 roman = roman + (m_romanConvertTable[place]).m_preLetter;
/* 206 */                 val -= (m_romanConvertTable[place]).m_preValue;
/*     */               } 
/*     */             }
/*     */             
/* 210 */             place++;
/*     */             
/* 212 */             if (val <= 0L)
/*     */               break; 
/*     */             continue;
/*     */           } 
/*     */           roman = roman + (m_romanConvertTable[place]).m_postLetter;
/*     */           val -= (m_romanConvertTable[place]).m_postValue;
/*     */         } 
/* 219 */         return roman; }  } else { roman = "#error"; return roman; }
/*     */     
/*     */     roman = roman + (m_romanConvertTable[place]).m_postLetter;
/*     */     val -= (m_romanConvertTable[place]).m_postValue;
/*     */     continue;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   class NumberFormatStringTokenizer
/*     */   {
/*     */     private int currentPosition;
/*     */ 
/*     */     
/*     */     private int maxPosition;
/*     */ 
/*     */     
/*     */     private String str;
/*     */ 
/*     */     
/*     */     private final NumeratorFormatter this$0;
/*     */ 
/*     */ 
/*     */     
/*     */     NumberFormatStringTokenizer(NumeratorFormatter this$0, String str) {
/* 244 */       this.this$0 = this$0;
/* 245 */       this.str = str;
/* 246 */       this.maxPosition = str.length();
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void reset() {
/* 255 */       this.currentPosition = 0;
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
/*     */     String nextToken() {
/* 268 */       if (this.currentPosition >= this.maxPosition)
/*     */       {
/* 270 */         throw new NoSuchElementException();
/*     */       }
/*     */       
/* 273 */       int start = this.currentPosition;
/*     */ 
/*     */       
/* 276 */       while (this.currentPosition < this.maxPosition && Character.isLetterOrDigit(this.str.charAt(this.currentPosition)))
/*     */       {
/* 278 */         this.currentPosition++;
/*     */       }
/*     */       
/* 281 */       if (start == this.currentPosition && !Character.isLetterOrDigit(this.str.charAt(this.currentPosition)))
/*     */       {
/*     */         
/* 284 */         this.currentPosition++;
/*     */       }
/*     */       
/* 287 */       return this.str.substring(start, this.currentPosition);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     boolean hasMoreTokens() {
/* 297 */       return !(this.currentPosition >= this.maxPosition);
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
/*     */     int countTokens() {
/* 312 */       int count = 0;
/* 313 */       int currpos = this.currentPosition;
/*     */       
/* 315 */       while (currpos < this.maxPosition) {
/*     */         
/* 317 */         int start = currpos;
/*     */ 
/*     */         
/* 320 */         while (currpos < this.maxPosition && Character.isLetterOrDigit(this.str.charAt(currpos)))
/*     */         {
/* 322 */           currpos++;
/*     */         }
/*     */         
/* 325 */         if (start == currpos && !Character.isLetterOrDigit(this.str.charAt(currpos)))
/*     */         {
/*     */           
/* 328 */           currpos++;
/*     */         }
/*     */         
/* 331 */         count++;
/*     */       } 
/*     */       
/* 334 */       return count;
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/transformer/NumeratorFormatter.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */