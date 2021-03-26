/*     */ package c.a.c.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class a
/*     */ {
/*     */   private boolean g = false;
/*     */   static final int a = 42;
/*     */   boolean b = false;
/*     */   b c;
/*     */   int d;
/*  77 */   int e = 7;
/*     */ 
/*     */   
/*  80 */   int f = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   a(b out) {
/*  89 */     this.c = out;
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
/*     */   final void a(int[] symbuf, int nsym) {
/* 104 */     int bbuf = this.d;
/* 105 */     int bpos = this.e;
/*     */     
/* 107 */     for (int i = 0; i < nsym; i++) {
/* 108 */       bbuf |= (symbuf[i] & 0x1) << bpos--;
/* 109 */       if (bpos < 0) {
/* 110 */         if (bbuf != 255) {
/* 111 */           if (this.b) {
/* 112 */             this.c.a(255);
/* 113 */             this.b = false;
/* 114 */             this.f++;
/*     */           } 
/* 116 */           this.c.a(bbuf);
/* 117 */           this.f++;
/* 118 */           bpos = 7;
/*     */         } else {
/*     */           
/* 121 */           this.b = true;
/* 122 */           bpos = 6;
/*     */         } 
/* 124 */         bbuf = 0;
/*     */       } 
/*     */     } 
/* 127 */     this.d = bbuf;
/* 128 */     this.e = bpos;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   final void a(int bit) {
/* 138 */     this.d |= (bit & 0x1) << this.e--;
/* 139 */     if (this.e < 0) {
/* 140 */       if (this.d != 255) {
/* 141 */         if (this.b) {
/* 142 */           this.c.a(255);
/* 143 */           this.b = false;
/* 144 */           this.f++;
/*     */         } 
/*     */         
/* 147 */         this.c.a(this.d);
/* 148 */         this.f++;
/* 149 */         this.e = 7;
/*     */       } else {
/*     */         
/* 152 */         this.b = true;
/* 153 */         this.e = 6;
/*     */       } 
/* 155 */       this.d = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void a() {
/* 164 */     if (this.b) {
/* 165 */       if (this.e != 6) {
/*     */         
/* 167 */         this.c.a(255);
/* 168 */         this.b = false;
/* 169 */         this.f++;
/*     */ 
/*     */         
/* 172 */         this.d |= 42 >>> 6 - this.e;
/*     */         
/* 174 */         this.c.a(this.d);
/* 175 */         this.f++;
/* 176 */         this.e = 7;
/* 177 */         this.d = 0;
/* 178 */       } else if (this.g) {
/* 179 */         this.c.a(255);
/* 180 */         this.f++;
/* 181 */         this.c.a(42);
/* 182 */         this.f++;
/* 183 */         this.e = 7;
/* 184 */         this.d = 0;
/* 185 */         this.b = false;
/*     */       }
/*     */     
/*     */     }
/* 189 */     else if (this.e != 7) {
/*     */ 
/*     */       
/* 192 */       this.d |= 42 >>> 6 - this.e;
/*     */       
/* 194 */       this.c.a(this.d);
/* 195 */       this.f++;
/* 196 */       this.e = 7;
/* 197 */       this.d = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int b() {
/* 206 */     a();
/* 207 */     int savedNb = this.f;
/* 208 */     c();
/* 209 */     return savedNb;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void c() {
/* 218 */     this.b = false;
/* 219 */     this.e = 7;
/* 220 */     this.d = 0;
/* 221 */     this.f = 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   int d() {
/* 232 */     if (this.b)
/*     */     {
/*     */       
/* 235 */       return this.f + 2;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 240 */     return this.f + ((this.e == 7) ? 0 : 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void a(boolean isPredTerm) {
/* 251 */     this.g = isPredTerm;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/b/a.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */