/*     */ package c.a.i;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class g
/*     */ {
/*     */   public int a;
/*     */   private static final int b = -2;
/*     */   private static final int c = -1;
/*     */   
/*     */   public g(int linewidth) {
/*  80 */     this.a = linewidth;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int a() {
/*  91 */     return this.a;
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
/*     */   public void a(int linewidth) {
/* 103 */     if (linewidth < 1) {
/* 104 */       throw new IllegalArgumentException();
/*     */     }
/* 106 */     this.a = linewidth;
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
/*     */   public void a(PrintWriter out, int flind, int ind, String msg) {
/* 131 */     int start = 0;
/* 132 */     int end = 0;
/* 133 */     int pend = 0;
/* 134 */     int efflw = this.a - flind;
/* 135 */     int lind = flind;
/* 136 */     while ((end = a(msg, pend)) != -1) {
/* 137 */       if (end == -2) {
/* 138 */         for (int i = 0; i < lind; i++) {
/* 139 */           out.print(" ");
/*     */         }
/* 141 */         out.println(msg.substring(start, pend));
/* 142 */         if (b(msg, pend) == msg.length()) {
/*     */           
/* 144 */           out.println("");
/* 145 */           start = pend;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } else {
/* 150 */         if (efflw > end - pend) {
/* 151 */           efflw -= end - pend;
/* 152 */           pend = end;
/*     */           
/*     */           continue;
/*     */         } 
/* 156 */         for (int i = 0; i < lind; i++) {
/* 157 */           out.print(" ");
/*     */         }
/* 159 */         if (start == pend) {
/*     */           
/* 161 */           out.println(msg.substring(start, end));
/* 162 */           pend = end;
/*     */         } else {
/*     */           
/* 165 */           out.println(msg.substring(start, pend));
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 170 */       lind = ind;
/* 171 */       efflw = this.a - ind;
/* 172 */       start = b(msg, pend);
/* 173 */       pend = start;
/* 174 */       if (start == -1) {
/*     */         break;
/*     */       }
/*     */     } 
/* 178 */     if (pend != start) {
/* 179 */       for (int i = 0; i < lind; i++) {
/* 180 */         out.print(" ");
/*     */       }
/* 182 */       out.println(msg.substring(start, pend));
/*     */     } 
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
/*     */   private int a(String str, int from) {
/* 211 */     int len = str.length();
/* 212 */     char c = Character.MIN_VALUE;
/*     */     
/* 214 */     while (from < len && (c = str.charAt(from)) != '\n' && 
/* 215 */       Character.isWhitespace(c)) {
/* 216 */       from++;
/*     */     }
/* 218 */     if (c == '\n') {
/* 219 */       return -2;
/*     */     }
/* 221 */     if (from >= len) {
/* 222 */       return -1;
/*     */     }
/*     */     
/* 225 */     while (from < len && !Character.isWhitespace(str.charAt(from))) {
/* 226 */       from++;
/*     */     }
/* 228 */     return from;
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
/*     */   private int b(String str, int from) {
/* 253 */     int len = str.length();
/* 254 */     char c = Character.MIN_VALUE;
/*     */     
/* 256 */     while (from < len && (c = str.charAt(from)) != '\n' && 
/* 257 */       Character.isWhitespace(c)) {
/* 258 */       from++;
/*     */     }
/* 260 */     if (from >= len) {
/* 261 */       return -1;
/*     */     }
/* 263 */     if (c == '\n') {
/* 264 */       return from + 1;
/*     */     }
/*     */     
/* 267 */     return from;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/i/g.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */