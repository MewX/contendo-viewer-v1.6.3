/*     */ package org.apache.commons.lang3;
/*     */ 
/*     */ import java.util.Random;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RandomStringUtils
/*     */ {
/*  55 */   private static final Random RANDOM = new Random();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String random(int count) {
/*  81 */     return random(count, false, false);
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
/*     */   public static String randomAscii(int count) {
/*  95 */     return random(count, 32, 127, false, false);
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
/*     */   public static String randomAscii(int minLengthInclusive, int maxLengthExclusive) {
/* 111 */     return randomAscii(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
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
/*     */   public static String randomAlphabetic(int count) {
/* 125 */     return random(count, true, false);
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
/*     */   public static String randomAlphabetic(int minLengthInclusive, int maxLengthExclusive) {
/* 140 */     return randomAlphabetic(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
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
/*     */   public static String randomAlphanumeric(int count) {
/* 154 */     return random(count, true, true);
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
/*     */   public static String randomAlphanumeric(int minLengthInclusive, int maxLengthExclusive) {
/* 170 */     return randomAlphanumeric(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
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
/*     */   public static String randomGraph(int count) {
/* 185 */     return random(count, 33, 126, false, false);
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
/*     */   public static String randomGraph(int minLengthInclusive, int maxLengthExclusive) {
/* 200 */     return randomGraph(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
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
/*     */   public static String randomNumeric(int count) {
/* 214 */     return random(count, false, true);
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
/*     */   public static String randomNumeric(int minLengthInclusive, int maxLengthExclusive) {
/* 229 */     return randomNumeric(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
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
/*     */   public static String randomPrint(int count) {
/* 244 */     return random(count, 32, 126, false, false);
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
/*     */   public static String randomPrint(int minLengthInclusive, int maxLengthExclusive) {
/* 259 */     return randomPrint(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
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
/*     */   public static String random(int count, boolean letters, boolean numbers) {
/* 277 */     return random(count, 0, 0, letters, numbers);
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
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers) {
/* 297 */     return random(count, start, end, letters, numbers, null, RANDOM);
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
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers, char... chars) {
/* 321 */     return random(count, start, end, letters, numbers, chars, RANDOM);
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
/*     */   public static String random(int count, int start, int end, boolean letters, boolean numbers, char[] chars, Random random) {
/* 359 */     if (count == 0)
/* 360 */       return ""; 
/* 361 */     if (count < 0) {
/* 362 */       throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
/*     */     }
/* 364 */     if (chars != null && chars.length == 0) {
/* 365 */       throw new IllegalArgumentException("The chars array must not be empty");
/*     */     }
/*     */     
/* 368 */     if (start == 0 && end == 0) {
/* 369 */       if (chars != null) {
/* 370 */         end = chars.length;
/*     */       }
/* 372 */       else if (!letters && !numbers) {
/* 373 */         end = 1114111;
/*     */       } else {
/* 375 */         end = 123;
/* 376 */         start = 32;
/*     */       }
/*     */     
/*     */     }
/* 380 */     else if (end <= start) {
/* 381 */       throw new IllegalArgumentException("Parameter end (" + end + ") must be greater than start (" + start + ")");
/*     */     } 
/*     */ 
/*     */     
/* 385 */     int zero_digit_ascii = 48;
/* 386 */     int first_letter_ascii = 65;
/*     */     
/* 388 */     if (chars == null && ((numbers && end <= 48) || (letters && end <= 65)))
/*     */     {
/* 390 */       throw new IllegalArgumentException("Parameter end (" + end + ") must be greater then (" + '0' + ") for generating digits or greater then (" + 'A' + ") for generating letters.");
/*     */     }
/*     */ 
/*     */     
/* 394 */     StringBuilder builder = new StringBuilder(count);
/* 395 */     int gap = end - start;
/*     */     
/* 397 */     while (count-- != 0) {
/*     */       int codePoint;
/* 399 */       if (chars == null) {
/* 400 */         codePoint = random.nextInt(gap) + start;
/*     */         
/* 402 */         switch (Character.getType(codePoint)) {
/*     */           case 0:
/*     */           case 18:
/*     */           case 19:
/* 406 */             count++;
/*     */             continue;
/*     */         } 
/*     */       
/*     */       } else {
/* 411 */         codePoint = chars[random.nextInt(gap) + start];
/*     */       } 
/*     */       
/* 414 */       int numberOfChars = Character.charCount(codePoint);
/* 415 */       if (count == 0 && numberOfChars > 1) {
/* 416 */         count++;
/*     */         
/*     */         continue;
/*     */       } 
/* 420 */       if ((letters && Character.isLetter(codePoint)) || (numbers && 
/* 421 */         Character.isDigit(codePoint)) || (!letters && !numbers)) {
/*     */         
/* 423 */         builder.appendCodePoint(codePoint);
/*     */         
/* 425 */         if (numberOfChars == 2) {
/* 426 */           count--;
/*     */         }
/*     */         continue;
/*     */       } 
/* 430 */       count++;
/*     */     } 
/*     */     
/* 433 */     return builder.toString();
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
/*     */   public static String random(int count, String chars) {
/* 452 */     if (chars == null) {
/* 453 */       return random(count, 0, 0, false, false, null, RANDOM);
/*     */     }
/* 455 */     return random(count, chars.toCharArray());
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
/*     */   public static String random(int count, char... chars) {
/* 471 */     if (chars == null) {
/* 472 */       return random(count, 0, 0, false, false, null, RANDOM);
/*     */     }
/* 474 */     return random(count, 0, chars.length, false, false, chars, RANDOM);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/RandomStringUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */