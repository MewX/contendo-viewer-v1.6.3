/*     */ package org.apache.commons.lang3.arch;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Processor
/*     */ {
/*     */   private final Arch arch;
/*     */   private final Type type;
/*     */   
/*     */   public enum Arch
/*     */   {
/*  42 */     BIT_32,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  47 */     BIT_64,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  52 */     UNKNOWN;
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
/*     */   public enum Type
/*     */   {
/*  70 */     X86,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     IA_64,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  80 */     PPC,
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  85 */     UNKNOWN;
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
/*     */   public Processor(Arch arch, Type type) {
/*  99 */     this.arch = arch;
/* 100 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Arch getArch() {
/* 111 */     return this.arch;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType() {
/* 122 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is32Bit() {
/* 131 */     return Arch.BIT_32.equals(this.arch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean is64Bit() {
/* 140 */     return Arch.BIT_64.equals(this.arch);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isX86() {
/* 149 */     return Type.X86.equals(this.type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIA64() {
/* 158 */     return Type.IA_64.equals(this.type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPPC() {
/* 167 */     return Type.PPC.equals(this.type);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/commons/lang3/arch/Processor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */