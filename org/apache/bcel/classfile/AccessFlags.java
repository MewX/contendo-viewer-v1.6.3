/*     */ package org.apache.bcel.classfile;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class AccessFlags
/*     */ {
/*     */   protected int access_flags;
/*     */   
/*     */   public AccessFlags() {}
/*     */   
/*     */   public AccessFlags(int a) {
/*  75 */     this.access_flags = a;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final int getAccessFlags() {
/*  81 */     return this.access_flags;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void setAccessFlags(int access_flags) {
/*  87 */     this.access_flags = access_flags;
/*     */   }
/*     */   
/*     */   private final void setFlag(int flag, boolean set) {
/*  91 */     if ((this.access_flags & flag) != 0) {
/*  92 */       if (!set) {
/*  93 */         this.access_flags ^= flag;
/*     */       }
/*  95 */     } else if (set) {
/*  96 */       this.access_flags |= flag;
/*     */     } 
/*     */   }
/*     */   public final void isPublic(boolean flag) {
/* 100 */     setFlag(1, flag);
/*     */   } public final boolean isPublic() {
/* 102 */     return ((this.access_flags & 0x1) != 0);
/*     */   }
/*     */   public final void isPrivate(boolean flag) {
/* 105 */     setFlag(2, flag);
/*     */   } public final boolean isPrivate() {
/* 107 */     return ((this.access_flags & 0x2) != 0);
/*     */   }
/*     */   public final void isProtected(boolean flag) {
/* 110 */     setFlag(4, flag);
/*     */   } public final boolean isProtected() {
/* 112 */     return ((this.access_flags & 0x4) != 0);
/*     */   }
/*     */   public final void isStatic(boolean flag) {
/* 115 */     setFlag(8, flag);
/*     */   } public final boolean isStatic() {
/* 117 */     return ((this.access_flags & 0x8) != 0);
/*     */   }
/*     */   public final void isFinal(boolean flag) {
/* 120 */     setFlag(16, flag);
/*     */   } public final boolean isFinal() {
/* 122 */     return ((this.access_flags & 0x10) != 0);
/*     */   }
/*     */   public final void isSynchronized(boolean flag) {
/* 125 */     setFlag(32, flag);
/*     */   } public final boolean isSynchronized() {
/* 127 */     return ((this.access_flags & 0x20) != 0);
/*     */   }
/*     */   public final void isVolatile(boolean flag) {
/* 130 */     setFlag(64, flag);
/*     */   } public final boolean isVolatile() {
/* 132 */     return ((this.access_flags & 0x40) != 0);
/*     */   }
/*     */   public final void isTransient(boolean flag) {
/* 135 */     setFlag(128, flag);
/*     */   } public final boolean isTransient() {
/* 137 */     return ((this.access_flags & 0x80) != 0);
/*     */   }
/*     */   public final void isNative(boolean flag) {
/* 140 */     setFlag(256, flag);
/*     */   } public final boolean isNative() {
/* 142 */     return ((this.access_flags & 0x100) != 0);
/*     */   }
/*     */   public final void isInterface(boolean flag) {
/* 145 */     setFlag(512, flag);
/*     */   } public final boolean isInterface() {
/* 147 */     return ((this.access_flags & 0x200) != 0);
/*     */   }
/*     */   public final void isAbstract(boolean flag) {
/* 150 */     setFlag(1024, flag);
/*     */   } public final boolean isAbstract() {
/* 152 */     return ((this.access_flags & 0x400) != 0);
/*     */   }
/*     */   public final void isStrictfp(boolean flag) {
/* 155 */     setFlag(2048, flag);
/*     */   } public final boolean isStrictfp() {
/* 157 */     return ((this.access_flags & 0x800) != 0);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/bcel/classfile/AccessFlags.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */