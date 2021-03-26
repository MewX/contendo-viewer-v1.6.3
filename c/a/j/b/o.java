/*     */ package c.a.j.b;
/*     */ 
/*     */ import c.a.d;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class o
/*     */   extends d
/*     */ {
/*     */   public o(int nt, int nc, byte type) {
/*  73 */     super(nt, nc, type);
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
/*     */   public int e(int t, int c) {
/*  90 */     j[][] an = (j[][])b(t, c);
/*  91 */     return an[0][0].j();
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
/*     */   public j[] f(int t, int c) {
/* 115 */     j[][] an = (j[][])b(t, c);
/* 116 */     return an[0];
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
/*     */   public j[] g(int t, int c) {
/* 140 */     j[][] an = (j[][])b(t, c);
/* 141 */     return an[1];
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 146 */     String str = "";
/*     */ 
/*     */     
/* 149 */     str = str + "nTiles=" + this.j + "\nnComp=" + this.k + "\n\n";
/*     */     
/* 151 */     for (int t = 0; t < this.j; t++) {
/* 152 */       for (int c = 0; c < this.k; c++) {
/* 153 */         j[][] an = (j[][])b(t, c);
/*     */         
/* 155 */         str = str + "(t:" + t + ",c:" + c + ")\n";
/*     */ 
/*     */         
/* 158 */         str = str + "\tH:"; int i;
/* 159 */         for (i = 0; i < (an[0]).length; i++) {
/* 160 */           str = str + " " + an[0][i];
/*     */         }
/* 162 */         str = str + "\n\tV:";
/* 163 */         for (i = 0; i < (an[1]).length; i++)
/* 164 */           str = str + " " + an[1][i]; 
/* 165 */         str = str + "\n";
/*     */       } 
/*     */     } 
/*     */     
/* 169 */     return str;
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
/*     */   public boolean h(int t, int c) {
/* 185 */     j[] hfilter = f(t, c);
/* 186 */     j[] vfilter = g(t, c);
/*     */ 
/*     */     
/* 189 */     for (int i = hfilter.length - 1; i >= 0; i--) {
/* 190 */       if (!hfilter[i].k() || !vfilter[i].k())
/* 191 */         return false; 
/* 192 */     }  return true;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/j/b/o.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */