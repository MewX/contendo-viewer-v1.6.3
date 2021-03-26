/*     */ package c.a.c;
/*     */ 
/*     */ import c.a.a.h;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class d
/*     */   implements h
/*     */ {
/*     */   public int a;
/*     */   public int b;
/*     */   public int c;
/*     */   public int d;
/*     */   public int e;
/*     */   public int f;
/*     */   
/*     */   public d(int type, int cs, int ce, int rs, int re, int lye) {
/* 103 */     this.a = type;
/* 104 */     this.b = cs;
/* 105 */     this.c = ce;
/* 106 */     this.d = rs;
/* 107 */     this.e = re;
/* 108 */     this.f = lye;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 112 */     String str = "type= ";
/* 113 */     switch (this.a) {
/*     */       case 0:
/* 115 */         str = str + "layer, ";
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 132 */         str = str + "comp.: " + this.b + "-" + this.c + ", ";
/* 133 */         str = str + "res.: " + this.d + "-" + this.e + ", ";
/* 134 */         str = str + "layer: up to " + this.f;
/* 135 */         return str;case 1: str = str + "res, "; str = str + "comp.: " + this.b + "-" + this.c + ", "; str = str + "res.: " + this.d + "-" + this.e + ", "; str = str + "layer: up to " + this.f; return str;case 2: str = str + "res-pos, "; str = str + "comp.: " + this.b + "-" + this.c + ", "; str = str + "res.: " + this.d + "-" + this.e + ", "; str = str + "layer: up to " + this.f; return str;case 3: str = str + "pos-comp, "; str = str + "comp.: " + this.b + "-" + this.c + ", "; str = str + "res.: " + this.d + "-" + this.e + ", "; str = str + "layer: up to " + this.f; return str;case 4: str = str + "pos-comp, "; str = str + "comp.: " + this.b + "-" + this.c + ", "; str = str + "res.: " + this.d + "-" + this.e + ", "; str = str + "layer: up to " + this.f; return str;
/*     */     } 
/*     */     throw new Error("Unknown progression type");
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/c/d.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */