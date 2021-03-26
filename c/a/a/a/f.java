/*     */ package c.a.a.a;
/*     */ 
/*     */ import java.io.ByteArrayInputStream;
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class f
/*     */ {
/*     */   c.a.f.f a;
/*     */   ByteArrayInputStream b;
/*     */   boolean c;
/*     */   int d;
/*     */   int e;
/*     */   int f;
/*     */   
/*     */   f(c.a.f.f in) {
/*  93 */     this.a = in;
/*  94 */     this.c = false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   f(ByteArrayInputStream bais) {
/* 105 */     this.b = bais;
/* 106 */     this.c = true;
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
/*     */   final int a() throws IOException {
/* 120 */     if (this.e == 0) {
/* 121 */       if (this.d != 255) {
/* 122 */         if (this.c) {
/* 123 */           this.d = this.b.read();
/*     */         } else {
/* 125 */           this.d = this.a.read();
/* 126 */         }  this.e = 8;
/* 127 */         if (this.d == 255) {
/* 128 */           if (this.c) {
/* 129 */             this.f = this.b.read();
/*     */           } else {
/* 131 */             this.f = this.a.read();
/*     */           } 
/*     */         }
/*     */       } else {
/* 135 */         this.d = this.f;
/* 136 */         this.e = 7;
/*     */       } 
/*     */     }
/* 139 */     return this.d >> --this.e & 0x1;
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
/*     */   final int a(int n) throws IOException {
/* 159 */     if (n <= this.e) {
/* 160 */       return this.d >> (this.e -= n) & (1 << n) - 1;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 166 */     int bits = 0;
/*     */     
/*     */     do {
/* 169 */       bits <<= this.e;
/* 170 */       n -= this.e;
/* 171 */       bits |= a(this.e);
/*     */       
/* 173 */       if (this.d != 255) {
/* 174 */         if (this.c) {
/* 175 */           this.d = this.b.read();
/*     */         } else {
/* 177 */           this.d = this.a.read();
/*     */         } 
/* 179 */         this.e = 8;
/* 180 */         if (this.d == 255) {
/* 181 */           if (this.c) {
/* 182 */             this.f = this.b.read();
/*     */           } else {
/* 184 */             this.f = this.a.read();
/*     */           } 
/*     */         }
/*     */       } else {
/* 188 */         this.d = this.f;
/* 189 */         this.e = 7;
/*     */       } 
/* 191 */     } while (n > this.e);
/*     */     
/* 193 */     bits <<= n;
/* 194 */     bits |= this.d >> (this.e -= n) & (1 << n) - 1;
/*     */     
/* 196 */     return bits;
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
/*     */   void b() {
/* 211 */     this.d = 0;
/* 212 */     this.e = 0;
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
/*     */   void a(c.a.f.f in) {
/* 227 */     this.a = in;
/* 228 */     this.d = 0;
/* 229 */     this.e = 0;
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
/*     */   void a(ByteArrayInputStream bais) {
/* 244 */     this.b = bais;
/* 245 */     this.d = 0;
/* 246 */     this.e = 0;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/a/a/f.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */