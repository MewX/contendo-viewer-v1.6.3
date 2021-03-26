/*     */ package c.a.i;
/*     */ 
/*     */ import java.io.OutputStream;
/*     */ import java.io.PrintWriter;
/*     */ import java.io.Writer;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class j
/*     */   implements f
/*     */ {
/*     */   private PrintWriter e;
/*     */   private PrintWriter f;
/*     */   private g g;
/*     */   
/*     */   public j(OutputStream outstr, OutputStream errstr, int lw) {
/*  89 */     this.e = new PrintWriter(outstr, true);
/*  90 */     this.f = new PrintWriter(errstr, true);
/*  91 */     this.g = new g(lw);
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
/*     */   public j(Writer outstr, Writer errstr, int lw) {
/* 108 */     this.e = new PrintWriter(outstr, true);
/* 109 */     this.f = new PrintWriter(errstr, true);
/* 110 */     this.g = new g(lw);
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
/*     */   public j(PrintWriter outstr, PrintWriter errstr, int lw) {
/* 127 */     this.e = outstr;
/* 128 */     this.f = errstr;
/* 129 */     this.g = new g(lw);
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
/*     */   public void printmsg(int sev, String msg) {
/*     */     PrintWriter lout;
/*     */     String prefix;
/* 148 */     switch (sev) {
/*     */       case 0:
/* 150 */         prefix = "[LOG]: ";
/* 151 */         lout = this.e;
/*     */         break;
/*     */       case 1:
/* 154 */         prefix = "[INFO]: ";
/* 155 */         lout = this.e;
/*     */         break;
/*     */       case 2:
/* 158 */         prefix = "[WARNING]: ";
/* 159 */         lout = this.f;
/*     */         break;
/*     */       case 3:
/* 162 */         prefix = "[ERROR]: ";
/* 163 */         lout = this.f;
/*     */         break;
/*     */       default:
/* 166 */         throw new IllegalArgumentException("Severity " + sev + " not valid.");
/*     */     } 
/*     */     
/* 169 */     this.g.a(lout, 0, prefix.length(), prefix + msg);
/* 170 */     lout.flush();
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
/*     */   public void println(String str, int flind, int ind) {
/* 193 */     this.g.a(this.e, flind, ind, str);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void flush() {
/* 203 */     this.e.flush();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/c/a/i/j.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */