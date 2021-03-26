/*     */ package org.apache.commons.codec.digest;
/*     */ 
/*     */ import java.security.MessageDigest;
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
/*     */ public class Sha2Crypt
/*     */ {
/*     */   private static final int ROUNDS_DEFAULT = 5000;
/*     */   private static final int ROUNDS_MAX = 999999999;
/*     */   private static final int ROUNDS_MIN = 1000;
/*     */   private static final String ROUNDS_PREFIX = "rounds=";
/*     */   private static final int SHA256_BLOCKSIZE = 32;
/*     */   static final String SHA256_PREFIX = "$5$";
/*     */   private static final int SHA512_BLOCKSIZE = 64;
/*     */   static final String SHA512_PREFIX = "$6$";
/*  72 */   private static final Pattern SALT_PATTERN = Pattern.compile("^\\$([56])\\$(rounds=(\\d+)\\$)?([\\.\\/a-zA-Z0-9]{1,16}).*");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String sha256Crypt(byte[] keyBytes) {
/*  91 */     return sha256Crypt(keyBytes, null);
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
/*     */   public static String sha256Crypt(byte[] keyBytes, String salt) {
/* 112 */     if (salt == null) {
/* 113 */       salt = "$5$" + B64.getRandomSalt(8);
/*     */     }
/* 115 */     return sha2Crypt(keyBytes, salt, "$5$", 32, "SHA-256");
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
/*     */   public static String sha256Crypt(byte[] keyBytes, String salt, Random random) {
/* 137 */     if (salt == null) {
/* 138 */       salt = "$5$" + B64.getRandomSalt(8, random);
/*     */     }
/* 140 */     return sha2Crypt(keyBytes, salt, "$5$", 32, "SHA-256");
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
/*     */   private static String sha2Crypt(byte[] keyBytes, String salt, String saltPrefix, int blocksize, String algorithm) {
/* 171 */     int keyLen = keyBytes.length;
/*     */ 
/*     */     
/* 174 */     int rounds = 5000;
/* 175 */     boolean roundsCustom = false;
/* 176 */     if (salt == null) {
/* 177 */       throw new IllegalArgumentException("Salt must not be null");
/*     */     }
/*     */     
/* 180 */     Matcher m = SALT_PATTERN.matcher(salt);
/* 181 */     if (!m.find()) {
/* 182 */       throw new IllegalArgumentException("Invalid salt value: " + salt);
/*     */     }
/* 184 */     if (m.group(3) != null) {
/* 185 */       rounds = Integer.parseInt(m.group(3));
/* 186 */       rounds = Math.max(1000, Math.min(999999999, rounds));
/* 187 */       roundsCustom = true;
/*     */     } 
/* 189 */     String saltString = m.group(4);
/* 190 */     byte[] saltBytes = saltString.getBytes(Charsets.UTF_8);
/* 191 */     int saltLen = saltBytes.length;
/*     */ 
/*     */ 
/*     */     
/* 195 */     MessageDigest ctx = DigestUtils.getDigest(algorithm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 201 */     ctx.update(keyBytes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 216 */     ctx.update(saltBytes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 223 */     MessageDigest altCtx = DigestUtils.getDigest(algorithm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 229 */     altCtx.update(keyBytes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 235 */     altCtx.update(saltBytes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 241 */     altCtx.update(keyBytes);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 247 */     byte[] altResult = altCtx.digest();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 257 */     int cnt = keyBytes.length;
/* 258 */     while (cnt > blocksize) {
/* 259 */       ctx.update(altResult, 0, blocksize);
/* 260 */       cnt -= blocksize;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 265 */     ctx.update(altResult, 0, cnt);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 281 */     cnt = keyBytes.length;
/* 282 */     while (cnt > 0) {
/* 283 */       if ((cnt & 0x1) != 0) {
/* 284 */         ctx.update(altResult, 0, blocksize);
/*     */       } else {
/* 286 */         ctx.update(keyBytes);
/*     */       } 
/* 288 */       cnt >>= 1;
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 295 */     altResult = ctx.digest();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 301 */     altCtx = DigestUtils.getDigest(algorithm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 310 */     for (int i = 1; i <= keyLen; i++) {
/* 311 */       altCtx.update(keyBytes);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 318 */     byte[] tempResult = altCtx.digest();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 330 */     byte[] pBytes = new byte[keyLen];
/* 331 */     int cp = 0;
/* 332 */     while (cp < keyLen - blocksize) {
/* 333 */       System.arraycopy(tempResult, 0, pBytes, cp, blocksize);
/* 334 */       cp += blocksize;
/*     */     } 
/* 336 */     System.arraycopy(tempResult, 0, pBytes, cp, keyLen - cp);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 342 */     altCtx = DigestUtils.getDigest(algorithm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 351 */     for (int j = 1; j <= 16 + (altResult[0] & 0xFF); j++) {
/* 352 */       altCtx.update(saltBytes);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 359 */     tempResult = altCtx.digest();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 372 */     byte[] sBytes = new byte[saltLen];
/* 373 */     cp = 0;
/* 374 */     while (cp < saltLen - blocksize) {
/* 375 */       System.arraycopy(tempResult, 0, sBytes, cp, blocksize);
/* 376 */       cp += blocksize;
/*     */     } 
/* 378 */     System.arraycopy(tempResult, 0, sBytes, cp, saltLen - cp);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 391 */     for (int k = 0; k <= rounds - 1; k++) {
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 396 */       ctx = DigestUtils.getDigest(algorithm);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 403 */       if ((k & 0x1) != 0) {
/* 404 */         ctx.update(pBytes, 0, keyLen);
/*     */       } else {
/* 406 */         ctx.update(altResult, 0, blocksize);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 413 */       if (k % 3 != 0) {
/* 414 */         ctx.update(sBytes, 0, saltLen);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 421 */       if (k % 7 != 0) {
/* 422 */         ctx.update(pBytes, 0, keyLen);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 430 */       if ((k & 0x1) != 0) {
/* 431 */         ctx.update(altResult, 0, blocksize);
/*     */       } else {
/* 433 */         ctx.update(pBytes, 0, keyLen);
/*     */       } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 440 */       altResult = ctx.digest();
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
/* 458 */     StringBuilder buffer = new StringBuilder(saltPrefix);
/* 459 */     if (roundsCustom) {
/* 460 */       buffer.append("rounds=");
/* 461 */       buffer.append(rounds);
/* 462 */       buffer.append("$");
/*     */     } 
/* 464 */     buffer.append(saltString);
/* 465 */     buffer.append("$");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 491 */     if (blocksize == 32) {
/* 492 */       B64.b64from24bit(altResult[0], altResult[10], altResult[20], 4, buffer);
/* 493 */       B64.b64from24bit(altResult[21], altResult[1], altResult[11], 4, buffer);
/* 494 */       B64.b64from24bit(altResult[12], altResult[22], altResult[2], 4, buffer);
/* 495 */       B64.b64from24bit(altResult[3], altResult[13], altResult[23], 4, buffer);
/* 496 */       B64.b64from24bit(altResult[24], altResult[4], altResult[14], 4, buffer);
/* 497 */       B64.b64from24bit(altResult[15], altResult[25], altResult[5], 4, buffer);
/* 498 */       B64.b64from24bit(altResult[6], altResult[16], altResult[26], 4, buffer);
/* 499 */       B64.b64from24bit(altResult[27], altResult[7], altResult[17], 4, buffer);
/* 500 */       B64.b64from24bit(altResult[18], altResult[28], altResult[8], 4, buffer);
/* 501 */       B64.b64from24bit(altResult[9], altResult[19], altResult[29], 4, buffer);
/* 502 */       B64.b64from24bit((byte)0, altResult[31], altResult[30], 3, buffer);
/*     */     } else {
/* 504 */       B64.b64from24bit(altResult[0], altResult[21], altResult[42], 4, buffer);
/* 505 */       B64.b64from24bit(altResult[22], altResult[43], altResult[1], 4, buffer);
/* 506 */       B64.b64from24bit(altResult[44], altResult[2], altResult[23], 4, buffer);
/* 507 */       B64.b64from24bit(altResult[3], altResult[24], altResult[45], 4, buffer);
/* 508 */       B64.b64from24bit(altResult[25], altResult[46], altResult[4], 4, buffer);
/* 509 */       B64.b64from24bit(altResult[47], altResult[5], altResult[26], 4, buffer);
/* 510 */       B64.b64from24bit(altResult[6], altResult[27], altResult[48], 4, buffer);
/* 511 */       B64.b64from24bit(altResult[28], altResult[49], altResult[7], 4, buffer);
/* 512 */       B64.b64from24bit(altResult[50], altResult[8], altResult[29], 4, buffer);
/* 513 */       B64.b64from24bit(altResult[9], altResult[30], altResult[51], 4, buffer);
/* 514 */       B64.b64from24bit(altResult[31], altResult[52], altResult[10], 4, buffer);
/* 515 */       B64.b64from24bit(altResult[53], altResult[11], altResult[32], 4, buffer);
/* 516 */       B64.b64from24bit(altResult[12], altResult[33], altResult[54], 4, buffer);
/* 517 */       B64.b64from24bit(altResult[34], altResult[55], altResult[13], 4, buffer);
/* 518 */       B64.b64from24bit(altResult[56], altResult[14], altResult[35], 4, buffer);
/* 519 */       B64.b64from24bit(altResult[15], altResult[36], altResult[57], 4, buffer);
/* 520 */       B64.b64from24bit(altResult[37], altResult[58], altResult[16], 4, buffer);
/* 521 */       B64.b64from24bit(altResult[59], altResult[17], altResult[38], 4, buffer);
/* 522 */       B64.b64from24bit(altResult[18], altResult[39], altResult[60], 4, buffer);
/* 523 */       B64.b64from24bit(altResult[40], altResult[61], altResult[19], 4, buffer);
/* 524 */       B64.b64from24bit(altResult[62], altResult[20], altResult[41], 4, buffer);
/* 525 */       B64.b64from24bit((byte)0, (byte)0, altResult[63], 2, buffer);
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 533 */     Arrays.fill(tempResult, (byte)0);
/* 534 */     Arrays.fill(pBytes, (byte)0);
/* 535 */     Arrays.fill(sBytes, (byte)0);
/* 536 */     ctx.reset();
/* 537 */     altCtx.reset();
/* 538 */     Arrays.fill(keyBytes, (byte)0);
/* 539 */     Arrays.fill(saltBytes, (byte)0);
/*     */     
/* 541 */     return buffer.toString();
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
/*     */   public static String sha512Crypt(byte[] keyBytes) {
/* 561 */     return sha512Crypt(keyBytes, null);
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
/*     */   public static String sha512Crypt(byte[] keyBytes, String salt) {
/* 583 */     if (salt == null) {
/* 584 */       salt = "$6$" + B64.getRandomSalt(8);
/*     */     }
/* 586 */     return sha2Crypt(keyBytes, salt, "$6$", 64, "SHA-512");
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
/*     */   public static String sha512Crypt(byte[] keyBytes, String salt, Random random) {
/* 612 */     if (salt == null) {
/* 613 */       salt = "$6$" + B64.getRandomSalt(8, random);
/*     */     }
/* 615 */     return sha2Crypt(keyBytes, salt, "$6$", 64, "SHA-512");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/codec/digest/Sha2Crypt.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */