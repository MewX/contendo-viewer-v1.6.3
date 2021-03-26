/*     */ package org.apache.batik.gvt.text;
/*     */ 
/*     */ import java.text.AttributedCharacterIterator;
/*     */ import java.text.AttributedString;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ArabicTextHandler
/*     */ {
/*     */   private static final int arabicStart = 1536;
/*     */   private static final int arabicEnd = 1791;
/*  40 */   private static final AttributedCharacterIterator.Attribute ARABIC_FORM = GVTAttributedCharacterIterator.TextAttribute.ARABIC_FORM;
/*     */   
/*  42 */   private static final Integer ARABIC_NONE = GVTAttributedCharacterIterator.TextAttribute.ARABIC_NONE;
/*     */   
/*  44 */   private static final Integer ARABIC_ISOLATED = GVTAttributedCharacterIterator.TextAttribute.ARABIC_ISOLATED;
/*     */   
/*  46 */   private static final Integer ARABIC_TERMINAL = GVTAttributedCharacterIterator.TextAttribute.ARABIC_TERMINAL;
/*     */   
/*  48 */   private static final Integer ARABIC_INITIAL = GVTAttributedCharacterIterator.TextAttribute.ARABIC_INITIAL;
/*     */   
/*  50 */   private static final Integer ARABIC_MEDIAL = GVTAttributedCharacterIterator.TextAttribute.ARABIC_MEDIAL;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static AttributedString assignArabicForms(AttributedString as) {
/*  72 */     if (!containsArabic(as)) {
/*  73 */       return as;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     AttributedCharacterIterator aci = as.getIterator();
/*  81 */     int numChars = aci.getEndIndex() - aci.getBeginIndex();
/*  82 */     int[] charOrder = null;
/*  83 */     if (numChars >= 3) {
/*  84 */       char prevChar = aci.first();
/*  85 */       char c1 = aci.next();
/*  86 */       int i = 1;
/*  87 */       char nextChar = aci.next();
/*  88 */       for (; nextChar != Character.MAX_VALUE; 
/*  89 */         prevChar = c1, c1 = nextChar, nextChar = aci.next(), i++) {
/*  90 */         if (arabicCharTransparent(c1) && 
/*  91 */           hasSubstitute(prevChar, nextChar)) {
/*     */           
/*  93 */           if (charOrder == null) {
/*  94 */             charOrder = new int[numChars];
/*  95 */             for (int j = 0; j < numChars; j++) {
/*  96 */               charOrder[j] = j + aci.getBeginIndex();
/*     */             }
/*     */           } 
/*  99 */           int temp = charOrder[i];
/* 100 */           charOrder[i] = charOrder[i - 1];
/* 101 */           charOrder[i - 1] = temp;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 107 */     if (charOrder != null) {
/*     */       
/* 109 */       StringBuffer reorderedString = new StringBuffer(numChars);
/*     */       
/* 111 */       for (int i = 0; i < numChars; i++) {
/* 112 */         char c1 = aci.setIndex(charOrder[i]);
/* 113 */         reorderedString.append(c1);
/*     */       } 
/*     */       
/* 116 */       AttributedString reorderedAS = new AttributedString(reorderedString.toString());
/*     */       
/* 118 */       for (int j = 0; j < numChars; j++) {
/* 119 */         aci.setIndex(charOrder[j]);
/* 120 */         Map<AttributedCharacterIterator.Attribute, Object> attributes = aci.getAttributes();
/* 121 */         reorderedAS.addAttributes(attributes, j, j + 1);
/*     */       } 
/*     */       
/* 124 */       if (charOrder[0] != aci.getBeginIndex()) {
/*     */ 
/*     */ 
/*     */         
/* 128 */         aci.setIndex(charOrder[0]);
/* 129 */         Float x = (Float)aci.getAttribute(GVTAttributedCharacterIterator.TextAttribute.X);
/*     */         
/* 131 */         Float y = (Float)aci.getAttribute(GVTAttributedCharacterIterator.TextAttribute.Y);
/*     */ 
/*     */         
/* 134 */         if (x != null && !x.isNaN()) {
/* 135 */           reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.X, Float.valueOf(Float.NaN), charOrder[0], charOrder[0] + 1);
/*     */ 
/*     */           
/* 138 */           reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.X, x, 0, 1);
/*     */         } 
/*     */ 
/*     */         
/* 142 */         if (y != null && !y.isNaN()) {
/* 143 */           reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.Y, Float.valueOf(Float.NaN), charOrder[0], charOrder[0] + 1);
/*     */ 
/*     */           
/* 146 */           reorderedAS.addAttribute(GVTAttributedCharacterIterator.TextAttribute.Y, y, 0, 1);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 151 */       as = reorderedAS;
/*     */     } 
/*     */ 
/*     */     
/* 155 */     aci = as.getIterator();
/* 156 */     int runStart = -1;
/* 157 */     int idx = aci.getBeginIndex();
/* 158 */     int c = aci.first();
/* 159 */     for (; c != 65535; 
/* 160 */       c = aci.next(), idx++) {
/* 161 */       if (c >= 1536 && c <= 1791) {
/* 162 */         if (runStart == -1)
/* 163 */           runStart = idx; 
/* 164 */       } else if (runStart != -1) {
/* 165 */         as.addAttribute(ARABIC_FORM, ARABIC_NONE, runStart, idx);
/* 166 */         runStart = -1;
/*     */       } 
/*     */     } 
/* 169 */     if (runStart != -1) {
/* 170 */       as.addAttribute(ARABIC_FORM, ARABIC_NONE, runStart, idx);
/*     */     }
/* 172 */     aci = as.getIterator();
/* 173 */     int end = aci.getBeginIndex();
/*     */     
/* 175 */     Integer currentForm = ARABIC_NONE;
/*     */     
/* 177 */     while (aci.setIndex(end) != Character.MAX_VALUE) {
/* 178 */       int start = aci.getRunStart(ARABIC_FORM);
/* 179 */       end = aci.getRunLimit(ARABIC_FORM);
/* 180 */       char currentChar = aci.setIndex(start);
/* 181 */       currentForm = (Integer)aci.getAttribute(ARABIC_FORM);
/*     */       
/* 183 */       if (currentForm == null) {
/*     */         continue;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 189 */       int currentIndex = start;
/* 190 */       int prevCharIndex = start - 1;
/* 191 */       while (currentIndex < end) {
/* 192 */         char prevChar = currentChar;
/* 193 */         currentChar = aci.setIndex(currentIndex);
/* 194 */         while (arabicCharTransparent(currentChar) && currentIndex < end) {
/*     */           
/* 196 */           currentIndex++;
/* 197 */           currentChar = aci.setIndex(currentIndex);
/*     */         } 
/* 199 */         if (currentIndex >= end) {
/*     */           break;
/*     */         }
/*     */         
/* 203 */         Integer prevForm = currentForm;
/* 204 */         currentForm = ARABIC_NONE;
/* 205 */         if (prevCharIndex >= start) {
/*     */           
/* 207 */           if (arabicCharShapesRight(prevChar) && arabicCharShapesLeft(currentChar)) {
/*     */ 
/*     */             
/* 210 */             prevForm = Integer.valueOf(prevForm.intValue() + 1);
/* 211 */             as.addAttribute(ARABIC_FORM, prevForm, prevCharIndex, prevCharIndex + 1);
/*     */ 
/*     */ 
/*     */             
/* 215 */             currentForm = ARABIC_INITIAL;
/* 216 */           } else if (arabicCharShaped(currentChar)) {
/*     */             
/* 218 */             currentForm = ARABIC_ISOLATED;
/*     */           
/*     */           }
/*     */         
/*     */         }
/* 223 */         else if (arabicCharShaped(currentChar)) {
/*     */           
/* 225 */           currentForm = ARABIC_ISOLATED;
/*     */         } 
/* 227 */         if (currentForm != ARABIC_NONE) {
/* 228 */           as.addAttribute(ARABIC_FORM, currentForm, currentIndex, currentIndex + 1);
/*     */         }
/* 230 */         prevCharIndex = currentIndex;
/* 231 */         currentIndex++;
/*     */       } 
/*     */     } 
/* 234 */     return as;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean arabicChar(char c) {
/* 245 */     if (c >= '؀' && c <= 'ۿ') {
/* 246 */       return true;
/*     */     }
/* 248 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsArabic(AttributedString as) {
/* 258 */     return containsArabic(as.getIterator());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean containsArabic(AttributedCharacterIterator aci) {
/* 268 */     char c = aci.first();
/* 269 */     for (; c != Character.MAX_VALUE; 
/* 270 */       c = aci.next()) {
/* 271 */       if (arabicChar(c)) {
/* 272 */         return true;
/*     */       }
/*     */     } 
/* 275 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean arabicCharTransparent(char c) {
/* 285 */     int charVal = c;
/* 286 */     if (charVal < 1611 || charVal > 1773) {
/* 287 */       return false;
/*     */     }
/* 289 */     if (charVal <= 1621 || charVal == 1648 || (charVal >= 1750 && charVal <= 1764) || (charVal >= 1767 && charVal <= 1768) || charVal >= 1770)
/*     */     {
/*     */ 
/*     */ 
/*     */       
/* 294 */       return true;
/*     */     }
/* 296 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean arabicCharShapesRight(char c) {
/* 307 */     int charVal = c;
/* 308 */     if ((charVal >= 1570 && charVal <= 1573) || charVal == 1575 || charVal == 1577 || (charVal >= 1583 && charVal <= 1586) || charVal == 1608 || (charVal >= 1649 && charVal <= 1651) || (charVal >= 1653 && charVal <= 1655) || (charVal >= 1672 && charVal <= 1689) || charVal == 1728 || (charVal >= 1730 && charVal <= 1739) || charVal == 1741 || charVal == 1743 || (charVal >= 1746 && charVal <= 1747) || arabicCharShapesDuel(c))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 323 */       return true;
/*     */     }
/* 325 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean arabicCharShapesDuel(char c) {
/* 335 */     int charVal = c;
/*     */     
/* 337 */     if (charVal == 1574 || charVal == 1576 || (charVal >= 1578 && charVal <= 1582) || (charVal >= 1587 && charVal <= 1594) || (charVal >= 1601 && charVal <= 1607) || (charVal >= 1609 && charVal <= 1610) || (charVal >= 1656 && charVal <= 1671) || (charVal >= 1690 && charVal <= 1727) || charVal == 1729 || charVal == 1740 || charVal == 1742 || (charVal >= 1744 && charVal <= 1745) || (charVal >= 1786 && charVal <= 1788))
/*     */     {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 350 */       return true;
/*     */     }
/* 352 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean arabicCharShapesLeft(char c) {
/* 363 */     return arabicCharShapesDuel(c);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean arabicCharShaped(char c) {
/* 373 */     return arabicCharShapesRight(c);
/*     */   }
/*     */   
/*     */   public static boolean hasSubstitute(char ch1, char ch2) {
/* 377 */     if (ch1 < doubleCharFirst || ch1 > doubleCharLast) return false;
/*     */     
/* 379 */     int[][] remaps = doubleCharRemappings[ch1 - doubleCharFirst];
/* 380 */     if (remaps == null) return false; 
/* 381 */     for (int[] remap : remaps) {
/* 382 */       if (remap[0] == ch2)
/* 383 */         return true; 
/*     */     } 
/* 385 */     return false;
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
/*     */   public static int getSubstituteChar(char ch1, char ch2, int form) {
/* 400 */     if (form == 0) return -1; 
/* 401 */     if (ch1 < doubleCharFirst || ch1 > doubleCharLast) return -1;
/*     */     
/* 403 */     int[][] remaps = doubleCharRemappings[ch1 - doubleCharFirst];
/* 404 */     if (remaps == null) return -1; 
/* 405 */     for (int[] remap : remaps) {
/* 406 */       if (remap[0] == ch2)
/* 407 */         return remap[form]; 
/*     */     } 
/* 409 */     return -1;
/*     */   }
/*     */   
/*     */   public static int getSubstituteChar(char ch, int form) {
/* 413 */     if (form == 0) return -1; 
/* 414 */     if (ch < singleCharFirst || ch > singleCharLast) return -1;
/*     */     
/* 416 */     int[] chars = singleCharRemappings[ch - singleCharFirst];
/* 417 */     if (chars == null) return -1;
/*     */     
/* 419 */     return chars[form - 1];
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
/*     */   public static String createSubstituteString(AttributedCharacterIterator aci) {
/* 435 */     int start = aci.getBeginIndex();
/* 436 */     int end = aci.getEndIndex();
/* 437 */     int numChar = end - start;
/* 438 */     StringBuffer substString = new StringBuffer(numChar);
/* 439 */     for (int i = start; i < end; i++) {
/* 440 */       char c = aci.setIndex(i);
/* 441 */       if (!arabicChar(c)) {
/* 442 */         substString.append(c);
/*     */         
/*     */         continue;
/*     */       } 
/* 446 */       Integer form = (Integer)aci.getAttribute(ARABIC_FORM);
/*     */       
/* 448 */       if (charStartsLigature(c) && i + 1 < end) {
/* 449 */         char nextChar = aci.setIndex(i + 1);
/* 450 */         Integer nextForm = (Integer)aci.getAttribute(ARABIC_FORM);
/* 451 */         if (form != null && nextForm != null) {
/* 452 */           if (form.equals(ARABIC_TERMINAL) && nextForm.equals(ARABIC_INITIAL)) {
/*     */ 
/*     */             
/* 455 */             int substChar = getSubstituteChar(c, nextChar, ARABIC_ISOLATED.intValue());
/*     */             
/* 457 */             if (substChar > -1) {
/* 458 */               substString.append((char)substChar);
/* 459 */               i++;
/*     */               continue;
/*     */             } 
/* 462 */           } else if (form.equals(ARABIC_TERMINAL)) {
/*     */             
/* 464 */             int substChar = getSubstituteChar(c, nextChar, ARABIC_TERMINAL.intValue());
/*     */             
/* 466 */             if (substChar > -1) {
/* 467 */               substString.append((char)substChar);
/* 468 */               i++;
/*     */               continue;
/*     */             } 
/* 471 */           } else if (form.equals(ARABIC_MEDIAL) && nextForm.equals(ARABIC_MEDIAL)) {
/*     */ 
/*     */             
/* 474 */             int substChar = getSubstituteChar(c, nextChar, ARABIC_MEDIAL.intValue());
/*     */             
/* 476 */             if (substChar > -1) {
/* 477 */               substString.append((char)substChar);
/* 478 */               i++;
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */       
/* 487 */       if (form != null && form.intValue() > 0) {
/* 488 */         int substChar = getSubstituteChar(c, form.intValue());
/* 489 */         if (substChar > -1) {
/* 490 */           c = (char)substChar;
/*     */         }
/*     */       } 
/* 493 */       substString.append(c);
/*     */       continue;
/*     */     } 
/* 496 */     return substString.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean charStartsLigature(char c) {
/* 507 */     int charVal = c;
/* 508 */     if (charVal == 1611 || charVal == 1612 || charVal == 1613 || charVal == 1614 || charVal == 1615 || charVal == 1616 || charVal == 1617 || charVal == 1618 || charVal == 1570 || charVal == 1571 || charVal == 1573 || charVal == 1575)
/*     */     {
/*     */ 
/*     */       
/* 512 */       return true;
/*     */     }
/* 514 */     return false;
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
/*     */   public static int getNumChars(char c) {
/* 527 */     if (isLigature(c))
/*     */     {
/* 529 */       return 2; } 
/* 530 */     return 1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean isLigature(char c) {
/* 541 */     int charVal = c;
/* 542 */     if (charVal < 65136 || charVal > 65276) {
/* 543 */       return false;
/*     */     }
/* 545 */     if (charVal <= 65138 || charVal == 65140 || (charVal >= 65142 && charVal <= 65151) || charVal >= 65269)
/*     */     {
/*     */ 
/*     */       
/* 549 */       return true;
/*     */     }
/* 551 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 561 */   static int singleCharFirst = 1569;
/* 562 */   static int singleCharLast = 1610;
/* 563 */   static int[][] singleCharRemappings = new int[][] { { 65152, -1, -1, -1 }, { 65153, 65154, -1, -1 }, { 65155, 65156, -1, -1 }, { 65157, 65158, -1, -1 }, { 65159, 65160, -1, -1 }, { 65161, 65162, 65163, 65164 }, { 65165, 65166, -1, -1 }, { 65167, 65168, 65169, 65170 }, { 65171, 65172, -1, -1 }, { 65173, 65174, 65175, 65176 }, { 65177, 65178, 65179, 65180 }, { 65181, 65182, 65183, 65184 }, { 65185, 65186, 65187, 65188 }, { 65189, 65190, 65191, 65192 }, { 65193, 65194, -1, -1 }, { 65195, 65196, -1, -1 }, { 65197, 65198, -1, -1 }, { 65199, 65200, -1, -1 }, { 65201, 65202, 65203, 65204 }, { 65205, 65206, 65207, 65208 }, { 65209, 65210, 65211, 65212 }, { 65213, 65214, 65215, 65216 }, { 65217, 65218, 65219, 65220 }, { 65221, 65222, 65223, 65224 }, { 65225, 65226, 65227, 65228 }, { 65229, 65230, 65231, 65232 }, null, null, null, null, null, null, { 65233, 65234, 65235, 65236 }, { 65237, 65238, 65239, 65240 }, { 65241, 65242, 65243, 65244 }, { 65245, 65246, 65247, 65248 }, { 65249, 65250, 65251, 65252 }, { 65253, 65254, 65255, 65256 }, { 65257, 65258, 65259, 65260 }, { 65261, 65262, -1, -1 }, { 65263, 65264, -1, -1 }, { 65265, 65266, 65267, 65268 } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 610 */   static int doubleCharFirst = 1570;
/* 611 */   static int doubleCharLast = 1618;
/* 612 */   static int[][][] doubleCharRemappings = new int[][][] { { { 1604, 65269, 65270, -1, -1 } }, { { 1604, 65271, 65272, -1, -1 } }, (int[][])null, { { 1604, 65273, 65274, -1, -1 } }, (int[][])null, { { 1604, 65275, 65276, -1, -1 } }, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, (int[][])null, { { 32, 65136, -1, -1, -1 }, { 1600, -1, -1, -1, 65137 } }, { { 32, 65138, -1, -1, -1 } }, { { 32, 65140, -1, -1, -1 } }, { { 32, 65142, -1, -1, -1 }, { 1600, -1, -1, -1, 65143 } }, { { 32, 65144, -1, -1, -1 }, { 1600, -1, -1, -1, 65145 } }, { { 32, 65146, -1, -1, -1 }, { 1600, -1, -1, -1, 65147 } }, { { 32, 65148, -1, -1, -1 }, { 1600, -1, -1, -1, 65149 } }, { { 32, 65150, -1, -1, -1 }, { 1600, -1, -1, -1, 65151 } } };
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/gvt/text/ArabicTextHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */