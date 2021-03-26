/*     */ package org.apache.commons.codec.digest;
/*     */ 
/*     */ import java.security.MessageDigest;
/*     */ import java.security.SecureRandom;
/*     */ import java.util.Arrays;
/*     */ import java.util.Random;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.codec.Charsets;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Md5Crypt
/*     */ {
/*     */   static final String APR1_PREFIX = "$apr1$";
/*     */   private static final int BLOCKSIZE = 16;
/*     */   static final String MD5_PREFIX = "$1$";
/*     */   private static final int ROUNDS = 1000;
/*     */   
/*     */   public static String apr1Crypt(byte[] keyBytes) {
/*  78 */     return apr1Crypt(keyBytes, "$apr1$" + B64.getRandomSalt(8));
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
/*     */   public static String apr1Crypt(byte[] keyBytes, Random random) {
/*  95 */     return apr1Crypt(keyBytes, "$apr1$" + B64.getRandomSalt(8, random));
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
/*     */   public static String apr1Crypt(byte[] keyBytes, String salt) {
/* 118 */     if (salt != null && !salt.startsWith("$apr1$")) {
/* 119 */       salt = "$apr1$" + salt;
/*     */     }
/* 121 */     return md5Crypt(keyBytes, salt, "$apr1$");
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
/*     */   public static String apr1Crypt(String keyBytes) {
/* 139 */     return apr1Crypt(keyBytes.getBytes(Charsets.UTF_8));
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
/*     */   public static String apr1Crypt(String keyBytes, String salt) {
/* 161 */     return apr1Crypt(keyBytes.getBytes(Charsets.UTF_8), salt);
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
/*     */   public static String md5Crypt(byte[] keyBytes) {
/* 181 */     return md5Crypt(keyBytes, "$1$" + B64.getRandomSalt(8));
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
/*     */   public static String md5Crypt(byte[] keyBytes, Random random) {
/* 203 */     return md5Crypt(keyBytes, "$1$" + B64.getRandomSalt(8, random));
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
/*     */   public static String md5Crypt(byte[] keyBytes, String salt) {
/* 226 */     return md5Crypt(keyBytes, salt, "$1$");
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
/*     */   public static String md5Crypt(byte[] keyBytes, String salt, String prefix) {
/* 251 */     return md5Crypt(keyBytes, salt, prefix, new SecureRandom());
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
/*     */   public static String md5Crypt(byte[] keyBytes, String salt, String prefix, Random random) {
/*     */     String saltString;
/* 278 */     int keyLen = keyBytes.length;
/*     */ 
/*     */ 
/*     */     
/* 282 */     if (salt == null) {
/* 283 */       saltString = B64.getRandomSalt(8, random);
/*     */     } else {
/* 285 */       Pattern p = Pattern.compile("^" + prefix.replace("$", "\\$") + "([\\.\\/a-zA-Z0-9]{1,8}).*");
/* 286 */       Matcher m = p.matcher(salt);
/* 287 */       if (!m.find()) {
/* 288 */         throw new IllegalArgumentException("Invalid salt value: " + salt);
/*     */       }
/* 290 */       saltString = m.group(1);
/*     */     } 
/* 292 */     byte[] saltBytes = saltString.getBytes(Charsets.UTF_8);
/*     */     
/* 294 */     MessageDigest ctx = DigestUtils.getMd5Digest();
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 299 */     ctx.update(keyBytes);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 304 */     ctx.update(prefix.getBytes(Charsets.UTF_8));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 309 */     ctx.update(saltBytes);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 314 */     MessageDigest ctx1 = DigestUtils.getMd5Digest();
/* 315 */     ctx1.update(keyBytes);
/* 316 */     ctx1.update(saltBytes);
/* 317 */     ctx1.update(keyBytes);
/* 318 */     byte[] finalb = ctx1.digest();
/* 319 */     int ii = keyLen;
/* 320 */     while (ii > 0) {
/* 321 */       ctx.update(finalb, 0, (ii > 16) ? 16 : ii);
/* 322 */       ii -= 16;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 328 */     Arrays.fill(finalb, (byte)0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 333 */     ii = keyLen;
/* 334 */     int j = 0;
/* 335 */     while (ii > 0) {
/* 336 */       if ((ii & 0x1) == 1) {
/* 337 */         ctx.update(finalb[0]);
/*     */       } else {
/* 339 */         ctx.update(keyBytes[0]);
/*     */       } 
/* 341 */       ii >>= 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 347 */     StringBuilder passwd = new StringBuilder(prefix + saltString + "$");
/* 348 */     finalb = ctx.digest();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 354 */     for (int i = 0; i < 1000; i++) {
/* 355 */       ctx1 = DigestUtils.getMd5Digest();
/* 356 */       if ((i & 0x1) != 0) {
/* 357 */         ctx1.update(keyBytes);
/*     */       } else {
/* 359 */         ctx1.update(finalb, 0, 16);
/*     */       } 
/*     */       
/* 362 */       if (i % 3 != 0) {
/* 363 */         ctx1.update(saltBytes);
/*     */       }
/*     */       
/* 366 */       if (i % 7 != 0) {
/* 367 */         ctx1.update(keyBytes);
/*     */       }
/*     */       
/* 370 */       if ((i & 0x1) != 0) {
/* 371 */         ctx1.update(finalb, 0, 16);
/*     */       } else {
/* 373 */         ctx1.update(keyBytes);
/*     */       } 
/* 375 */       finalb = ctx1.digest();
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 381 */     B64.b64from24bit(finalb[0], finalb[6], finalb[12], 4, passwd);
/* 382 */     B64.b64from24bit(finalb[1], finalb[7], finalb[13], 4, passwd);
/* 383 */     B64.b64from24bit(finalb[2], finalb[8], finalb[14], 4, passwd);
/* 384 */     B64.b64from24bit(finalb[3], finalb[9], finalb[15], 4, passwd);
/* 385 */     B64.b64from24bit(finalb[4], finalb[10], finalb[5], 4, passwd);
/* 386 */     B64.b64from24bit((byte)0, (byte)0, finalb[11], 2, passwd);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 392 */     ctx.reset();
/* 393 */     ctx1.reset();
/* 394 */     Arrays.fill(keyBytes, (byte)0);
/* 395 */     Arrays.fill(saltBytes, (byte)0);
/* 396 */     Arrays.fill(finalb, (byte)0);
/*     */     
/* 398 */     return passwd.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/codec/digest/Md5Crypt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */