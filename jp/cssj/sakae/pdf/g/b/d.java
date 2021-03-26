/*     */ package jp.cssj.sakae.pdf.g.b;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import jp.cssj.sakae.pdf.b;
/*     */ import jp.cssj.sakae.pdf.c;
/*     */ import jp.cssj.sakae.pdf.f.a;
/*     */ import jp.cssj.sakae.pdf.f.e;
/*     */ import jp.cssj.sakae.pdf.f.f;
/*     */ import jp.cssj.sakae.pdf.f.g;
/*     */ import jp.cssj.sakae.pdf.f.h;
/*     */ import jp.cssj.sakae.pdf.f.i;
/*     */ import jp.cssj.sakae.pdf.k;
/*     */ 
/*     */ 
/*     */ public class d
/*     */ {
/*  19 */   private static final byte[] a = new byte[] { 40, -65, 78, 94, 78, 117, -118, 65, 100, 0, 78, 86, -1, -6, 1, 8, 46, 46, 0, -74, -48, 104, 62, Byte.MIN_VALUE, 47, 12, -87, -2, 100, 83, 105, 122 };
/*     */   
/*     */   private final MessageDigest b;
/*     */   
/*     */   private final byte[] c;
/*     */   
/*     */   private final b d;
/*     */   private final int e;
/*     */   private final short f;
/*     */   private b g;
/*     */   private e h;
/*     */   
/*     */   private static byte[] a(byte[] password) {
/*  32 */     byte[] result = new byte[32];
/*  33 */     if (password.length < 32) {
/*  34 */       System.arraycopy(password, 0, result, 0, password.length);
/*  35 */       System.arraycopy(a, 0, result, password.length, 32 - password.length);
/*     */     } else {
/*  37 */       System.arraycopy(password, 0, result, 0, 32);
/*     */     } 
/*  39 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public d(c mainFlow, k xref, byte[][] fileid, a params) throws IOException {
/*     */     jp.cssj.sakae.pdf.f.d d1;
/*     */     e e1;
/*     */     f f;
/*     */     int length;
/*     */     g v1Params;
/*     */     h v2Params;
/*     */     i v4Params;
/*     */     String filterName;
/*     */     byte[] userKey, digest;
/*     */     b b1;
/*     */     byte[] key2;
/*     */     int i;
/*     */     try {
/*  59 */       this.b = MessageDigest.getInstance("MD5");
/*  60 */     } catch (NoSuchAlgorithmException noSuchAlgorithmException) {
/*  61 */       throw new RuntimeException(noSuchAlgorithmException);
/*     */     } 
/*     */ 
/*     */     
/*  65 */     this.d = xref.a();
/*  66 */     mainFlow.a(this.d);
/*  67 */     mainFlow.g();
/*     */     
/*  69 */     mainFlow.a("Filter");
/*  70 */     mainFlow.a("Standard");
/*  71 */     mainFlow.k();
/*     */     
/*  73 */     short v = params.a();
/*  74 */     mainFlow.a("V");
/*  75 */     mainFlow.a(v);
/*  76 */     mainFlow.k();
/*     */ 
/*     */ 
/*     */     
/*  80 */     switch (v) {
/*     */       
/*     */       case 1:
/*  83 */         this.f = 1;
/*  84 */         v1Params = (g)params;
/*  85 */         d1 = v1Params.d();
/*  86 */         length = 40;
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 2:
/*  92 */         this.f = 1;
/*  93 */         v2Params = (h)params;
/*  94 */         e1 = v2Params.d();
/*     */         
/*  96 */         length = v2Params.e();
/*  97 */         if (length != 40) {
/*  98 */           mainFlow.a("Length");
/*  99 */           mainFlow.a(length);
/* 100 */           mainFlow.k();
/*     */         } 
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 4:
/* 107 */         v4Params = (i)params;
/* 108 */         f = v4Params.d();
/*     */         
/* 110 */         if (!v4Params.g()) {
/* 111 */           mainFlow.a("EncryptMetadata");
/* 112 */           mainFlow.a(false);
/* 113 */           mainFlow.k();
/*     */         } 
/*     */         
/* 116 */         filterName = "StdCF";
/* 117 */         mainFlow.a("CF");
/* 118 */         mainFlow.g();
/* 119 */         mainFlow.a(filterName);
/* 120 */         mainFlow.g();
/*     */         
/* 122 */         mainFlow.a("Type");
/* 123 */         mainFlow.a("CryptFilter");
/* 124 */         mainFlow.k();
/*     */         
/* 126 */         this.f = v4Params.f();
/* 127 */         mainFlow.a("CFM");
/* 128 */         switch (this.f) {
/*     */           case 1:
/* 130 */             mainFlow.a("V2");
/*     */             break;
/*     */           case 2:
/* 133 */             mainFlow.a("AESV2");
/*     */             break;
/*     */           default:
/* 136 */             throw new IllegalStateException();
/*     */         } 
/* 138 */         mainFlow.k();
/*     */         
/* 140 */         length = v4Params.e();
/* 141 */         if (length != 40) {
/* 142 */           mainFlow.a("Length");
/* 143 */           mainFlow.a(length);
/* 144 */           mainFlow.k();
/*     */         } 
/*     */         
/* 147 */         mainFlow.h();
/* 148 */         mainFlow.h();
/*     */         
/* 150 */         mainFlow.a("StmF");
/* 151 */         mainFlow.a(filterName);
/* 152 */         mainFlow.k();
/*     */         
/* 154 */         mainFlow.a("StrF");
/* 155 */         mainFlow.a(filterName);
/* 156 */         mainFlow.k();
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 161 */         throw new IllegalArgumentException();
/*     */     } 
/* 163 */     this.e = length / 8;
/*     */     
/* 165 */     short r = f.a();
/* 166 */     mainFlow.a("R");
/* 167 */     mainFlow.a(r);
/* 168 */     mainFlow.k();
/*     */     
/* 170 */     int pflags = f.b();
/* 171 */     mainFlow.a("P");
/* 172 */     mainFlow.a(pflags);
/* 173 */     mainFlow.k();
/*     */ 
/*     */     
/* 176 */     byte[] ownerPass = params.b().getBytes("ISO-8859-1");
/* 177 */     byte[] userPass = params.c().getBytes("ISO-8859-1");
/* 178 */     if (ownerPass.length == 0) {
/* 179 */       ownerPass = userPass;
/*     */     }
/*     */ 
/*     */     
/* 183 */     this.b.reset();
/* 184 */     this.b.update(a(ownerPass));
/*     */     
/* 186 */     if (r >= 3)
/*     */     {
/* 188 */       for (int j = 0; j < 50; j++) {
/* 189 */         byte[] arrayOfByte = this.b.digest();
/* 190 */         this.b.update(arrayOfByte);
/*     */       } 
/*     */     }
/* 193 */     byte[] key = this.b.digest();
/* 194 */     byte[] ownerKey = a(userPass);
/* 195 */     b arcfour = new b(key, this.e);
/* 196 */     ownerKey = arcfour.a(ownerKey);
/* 197 */     if (r >= 3) {
/*     */       
/* 199 */       byte[] arrayOfByte = new byte[this.e];
/* 200 */       for (int j = 1; j <= 19; j++) {
/* 201 */         for (int m = 0; m < this.e; m++) {
/* 202 */           arrayOfByte[m] = (byte)(key[m] ^ j);
/*     */         }
/* 204 */         b arcfour2 = new b(arrayOfByte, this.e);
/* 205 */         ownerKey = arcfour2.a(ownerKey);
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 211 */     this.b.reset();
/* 212 */     this.b.update(a(userPass));
/* 213 */     this.b.update(ownerKey);
/*     */     
/* 215 */     key = new byte[4];
/* 216 */     key[0] = (byte)(pflags & 0xFF);
/* 217 */     key[1] = (byte)(pflags >>> 8 & 0xFF);
/* 218 */     key[2] = (byte)(pflags >>> 16 & 0xFF);
/* 219 */     key[3] = (byte)(pflags >>> 24 & 0xFF);
/* 220 */     this.b.update(key);
/*     */     
/* 222 */     this.b.update(fileid[0]);
/* 223 */     if (r >= 3)
/*     */     {
/* 225 */       for (int j = 0; j < 50; j++) {
/* 226 */         byte[] arrayOfByte = this.b.digest();
/* 227 */         this.b.update(arrayOfByte);
/*     */       } 
/*     */     }
/* 230 */     this.c = this.b.digest();
/*     */ 
/*     */ 
/*     */     
/* 234 */     switch (r) {
/*     */       
/*     */       case 2:
/* 237 */         userKey = new byte[a.length];
/* 238 */         System.arraycopy(a, 0, userKey, 0, a.length);
/* 239 */         arcfour = new b(this.c, this.e);
/* 240 */         userKey = arcfour.a(userKey);
/*     */         break;
/*     */ 
/*     */ 
/*     */       
/*     */       case 3:
/*     */       case 4:
/* 247 */         this.b.reset();
/* 248 */         this.b.update(a);
/* 249 */         this.b.update(fileid[0]);
/* 250 */         digest = this.b.digest();
/* 251 */         b1 = new b(this.c, this.e);
/* 252 */         digest = b1.a(digest);
/* 253 */         key2 = new byte[this.e];
/* 254 */         for (i = 1; i <= 19; i++) {
/* 255 */           for (int j = 0; j < this.e; j++) {
/* 256 */             key2[j] = (byte)(this.c[j] ^ i);
/*     */           }
/* 258 */           b arcfour2 = new b(key2, this.e);
/* 259 */           digest = arcfour2.a(digest);
/*     */         } 
/* 261 */         userKey = new byte[32];
/* 262 */         System.arraycopy(digest, 0, userKey, 0, digest.length);
/*     */         break;
/*     */ 
/*     */       
/*     */       default:
/* 267 */         throw new IllegalArgumentException();
/*     */     } 
/*     */     
/* 270 */     mainFlow.a("O");
/* 271 */     mainFlow.a(ownerKey, 0, ownerKey.length);
/* 272 */     mainFlow.k();
/*     */     
/* 274 */     mainFlow.a("U");
/* 275 */     mainFlow.a(userKey, 0, userKey.length);
/* 276 */     mainFlow.k();
/*     */     
/* 278 */     mainFlow.h();
/* 279 */     mainFlow.a();
/*     */   }
/*     */   
/*     */   public e a(b keyRef) {
/* 283 */     if (this.g != keyRef)
/* 284 */     { byte[] work, arckey; int keyLen = Math.min(this.e + 5, 16);
/* 285 */       switch (this.f)
/*     */       { case 1:
/* 287 */           work = new byte[this.e + 5];
/* 288 */           System.arraycopy(this.c, 0, work, 0, this.e);
/* 289 */           work[this.e] = (byte)(keyRef.a & 0xFF);
/* 290 */           work[this.e + 1] = (byte)(keyRef.a >>> 8 & 0xFF);
/* 291 */           work[this.e + 2] = (byte)(keyRef.a >>> 16 & 0xFF);
/* 292 */           work[this.e + 3] = (byte)(keyRef.b & 0xFF);
/* 293 */           work[this.e + 4] = (byte)(keyRef.b >>> 8 & 0xFF);
/* 294 */           this.b.reset();
/* 295 */           this.b.update(work);
/* 296 */           arckey = this.b.digest();
/* 297 */           this.g = keyRef;
/* 298 */           this.h = new b(arckey, keyLen);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 328 */           return this.h;case 2: work = new byte[this.e + 5 + 4]; System.arraycopy(this.c, 0, work, 0, this.e); work[this.e] = (byte)(keyRef.a & 0xFF); work[this.e + 1] = (byte)(keyRef.a >>> 8 & 0xFF); work[this.e + 2] = (byte)(keyRef.a >>> 16 & 0xFF); work[this.e + 3] = (byte)(keyRef.b & 0xFF); work[this.e + 4] = (byte)(keyRef.b >>> 8 & 0xFF); work[this.e + 5] = 115; work[this.e + 6] = 65; work[this.e + 7] = 108; work[this.e + 8] = 84; this.b.reset(); this.b.update(work); arckey = this.b.digest(); this.g = keyRef; this.h = new b(arckey, keyLen); this.h = new a(arckey, keyLen); return this.h; }  throw new IllegalStateException(); }  return this.h;
/*     */   }
/*     */   
/*     */   public b a() {
/* 332 */     return this.d;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/pdf/g/b/d.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */