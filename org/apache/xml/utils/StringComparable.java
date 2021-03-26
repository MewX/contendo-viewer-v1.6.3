/*     */ package org.apache.xml.utils;
/*     */ 
/*     */ import java.text.CollationElementIterator;
/*     */ import java.text.Collator;
/*     */ import java.text.RuleBasedCollator;
/*     */ import java.util.Locale;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StringComparable
/*     */   implements Comparable
/*     */ {
/*     */   public static final int UNKNOWN_CASE = -1;
/*     */   public static final int UPPER_CASE = 1;
/*     */   public static final int LOWER_CASE = 2;
/*     */   private String m_text;
/*     */   private Locale m_locale;
/*     */   private RuleBasedCollator m_collator;
/*     */   private String m_caseOrder;
/*  44 */   private int m_mask = -1;
/*     */   
/*     */   public StringComparable(String text, Locale locale, Collator collator, String caseOrder) {
/*  47 */     this.m_text = text;
/*  48 */     this.m_locale = locale;
/*  49 */     this.m_collator = (RuleBasedCollator)collator;
/*  50 */     this.m_caseOrder = caseOrder;
/*  51 */     this.m_mask = getMask(this.m_collator.getStrength());
/*     */   }
/*     */   
/*     */   public static final Comparable getComparator(String text, Locale locale, Collator collator, String caseOrder) {
/*  55 */     if (caseOrder == null || caseOrder.length() == 0) {
/*  56 */       return ((RuleBasedCollator)collator).getCollationKey(text);
/*     */     }
/*  58 */     return new StringComparable(text, locale, collator, caseOrder);
/*     */   }
/*     */   
/*     */   public final String toString() {
/*  62 */     return this.m_text;
/*     */   }
/*     */   public int compareTo(Object o) {
/*  65 */     String pattern = ((StringComparable)o).toString();
/*  66 */     if (this.m_text.equals(pattern)) {
/*  67 */       return 0;
/*     */     }
/*  69 */     int savedStrength = this.m_collator.getStrength();
/*  70 */     int comp = 0;
/*     */     
/*  72 */     if (savedStrength == 0 || savedStrength == 1) {
/*  73 */       comp = this.m_collator.compare(this.m_text, pattern);
/*     */     } else {
/*  75 */       this.m_collator.setStrength(1);
/*  76 */       comp = this.m_collator.compare(this.m_text, pattern);
/*  77 */       this.m_collator.setStrength(savedStrength);
/*     */     } 
/*  79 */     if (comp != 0) {
/*  80 */       return comp;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*  85 */     comp = getCaseDiff(this.m_text, pattern);
/*  86 */     if (comp != 0) {
/*  87 */       return comp;
/*     */     }
/*  89 */     return this.m_collator.compare(this.m_text, pattern);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private final int getCaseDiff(String text, String pattern) {
/*  95 */     int savedStrength = this.m_collator.getStrength();
/*  96 */     int savedDecomposition = this.m_collator.getDecomposition();
/*  97 */     this.m_collator.setStrength(2);
/*  98 */     this.m_collator.setDecomposition(1);
/*     */     
/* 100 */     int[] diff = getFirstCaseDiff(text, pattern, this.m_locale);
/* 101 */     this.m_collator.setStrength(savedStrength);
/* 102 */     this.m_collator.setDecomposition(savedDecomposition);
/* 103 */     if (diff != null) {
/* 104 */       if (this.m_caseOrder.equals("upper-first")) {
/* 105 */         if (diff[0] == 1) {
/* 106 */           return -1;
/*     */         }
/* 108 */         return 1;
/*     */       } 
/*     */       
/* 111 */       if (diff[0] == 2) {
/* 112 */         return -1;
/*     */       }
/* 114 */       return 1;
/*     */     } 
/*     */ 
/*     */     
/* 118 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int[] getFirstCaseDiff(String text, String pattern, Locale locale) {
/*     */     int[] diff;
/* 127 */     CollationElementIterator targIter = this.m_collator.getCollationElementIterator(text);
/* 128 */     CollationElementIterator patIter = this.m_collator.getCollationElementIterator(pattern);
/* 129 */     int startTarg = -1;
/* 130 */     int endTarg = -1;
/* 131 */     int startPatt = -1;
/* 132 */     int endPatt = -1;
/* 133 */     int done = getElement(-1);
/* 134 */     int patternElement = 0, targetElement = 0;
/* 135 */     boolean getPattern = true, getTarget = true;
/*     */     
/*     */     while (true) {
/* 138 */       if (getPattern) {
/* 139 */         startPatt = patIter.getOffset();
/* 140 */         patternElement = getElement(patIter.next());
/* 141 */         endPatt = patIter.getOffset();
/*     */       } 
/* 143 */       if (getTarget) {
/* 144 */         startTarg = targIter.getOffset();
/* 145 */         targetElement = getElement(targIter.next());
/* 146 */         endTarg = targIter.getOffset();
/*     */       } 
/* 148 */       getTarget = getPattern = true;
/* 149 */       if (patternElement == done || targetElement == done)
/* 150 */         return null; 
/* 151 */       if (targetElement == 0) {
/* 152 */         getPattern = false; continue;
/* 153 */       }  if (patternElement == 0) {
/* 154 */         getTarget = false; continue;
/* 155 */       }  if (targetElement != patternElement && 
/* 156 */         startPatt < endPatt && startTarg < endTarg)
/* 157 */       { String subText = text.substring(startTarg, endTarg);
/* 158 */         String subPatt = pattern.substring(startPatt, endPatt);
/* 159 */         String subTextUp = subText.toUpperCase(locale);
/* 160 */         String subPattUp = subPatt.toUpperCase(locale);
/* 161 */         if (this.m_collator.compare(subTextUp, subPattUp) != 0) {
/*     */           continue;
/*     */         }
/*     */         
/* 165 */         diff = new int[] { -1, -1 };
/* 166 */         if (this.m_collator.compare(subText, subTextUp) == 0) {
/* 167 */           diff[0] = 1;
/* 168 */         } else if (this.m_collator.compare(subText, subText.toLowerCase(locale)) == 0) {
/* 169 */           diff[0] = 2;
/*     */         } 
/* 171 */         if (this.m_collator.compare(subPatt, subPattUp) == 0) {
/* 172 */           diff[1] = 1;
/* 173 */         } else if (this.m_collator.compare(subPatt, subPatt.toLowerCase(locale)) == 0) {
/* 174 */           diff[1] = 2;
/*     */         } 
/*     */         
/* 177 */         if ((diff[0] == 1 && diff[1] == 2) || (diff[0] == 2 && diff[1] == 1))
/*     */           break;  } 
/* 179 */     }  return diff;
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
/*     */   private static final int getMask(int strength) {
/* 195 */     switch (strength) {
/*     */       case 0:
/* 197 */         return -65536;
/*     */       case 1:
/* 199 */         return -256;
/*     */     } 
/* 201 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final int getElement(int maxStrengthElement) {
/* 208 */     return maxStrengthElement & this.m_mask;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/utils/StringComparable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */