/*     */ package jp.cssj.sakae.b.b.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/*     */   int a;
/*     */   int b;
/*     */   byte[] c;
/*     */   int d;
/*     */   String e;
/*     */   
/*     */   public a(int length, int type, byte[] data, int crc) {
/*  62 */     this.a = length;
/*  63 */     this.b = type;
/*  64 */     this.c = data;
/*  65 */     this.d = crc;
/*     */     
/*  67 */     this.e = new String();
/*  68 */     this.e += (char)(type >> 24);
/*  69 */     this.e += (char)(type >> 16 & 0xFF);
/*  70 */     this.e += (char)(type >> 8 & 0xFF);
/*  71 */     this.e += (char)(type & 0xFF);
/*     */   }
/*     */   
/*     */   public int a() {
/*  75 */     return this.a;
/*     */   }
/*     */   
/*     */   public int b() {
/*  79 */     return this.b;
/*     */   }
/*     */   
/*     */   public String c() {
/*  83 */     return this.e;
/*     */   }
/*     */   
/*     */   public byte[] d() {
/*  87 */     return this.c;
/*     */   }
/*     */   
/*     */   public byte a(int offset) {
/*  91 */     return this.c[offset];
/*     */   }
/*     */   
/*     */   public int b(int offset) {
/*  95 */     return this.c[offset] & 0xFF;
/*     */   }
/*     */   
/*     */   public int c(int offset) {
/*  99 */     return (this.c[offset] & 0xFF) << 8 | this.c[offset + 1] & 0xFF;
/*     */   }
/*     */   
/*     */   public int d(int offset) {
/* 103 */     return (this.c[offset] & 0xFF) << 24 | (this.c[offset + 1] & 0xFF) << 16 | (this.c[offset + 2] & 0xFF) << 8 | this.c[offset + 3] & 0xFF;
/*     */   }
/*     */ 
/*     */   
/*     */   public String e(int offset) {
/* 108 */     String s = new String();
/* 109 */     s = s + (char)this.c[offset];
/* 110 */     s = s + (char)this.c[offset + 1];
/* 111 */     s = s + (char)this.c[offset + 2];
/* 112 */     s = s + (char)this.c[offset + 3];
/* 113 */     return s;
/*     */   }
/*     */   
/*     */   public boolean a(String typeName) {
/* 117 */     return this.e.equals(typeName);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/b/b/a/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */