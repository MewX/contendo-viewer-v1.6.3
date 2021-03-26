/*     */ package org.apache.batik.anim.values;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import org.apache.batik.anim.dom.AnimationTarget;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AnimatablePathDataValue
/*     */   extends AnimatableValue
/*     */ {
/*     */   protected short[] commands;
/*     */   protected float[] parameters;
/*     */   
/*     */   protected AnimatablePathDataValue(AnimationTarget target) {
/*  48 */     super(target);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatablePathDataValue(AnimationTarget target, short[] commands, float[] parameters) {
/*  56 */     super(target);
/*  57 */     this.commands = commands;
/*  58 */     this.parameters = parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue interpolate(AnimatableValue result, AnimatableValue to, float interpolation, AnimatableValue accumulation, int multiplier) {
/*  68 */     AnimatablePathDataValue base, res, toValue = (AnimatablePathDataValue)to;
/*  69 */     AnimatablePathDataValue accValue = (AnimatablePathDataValue)accumulation;
/*     */ 
/*     */     
/*  72 */     boolean hasTo = (to != null);
/*  73 */     boolean hasAcc = (accumulation != null);
/*  74 */     boolean canInterpolate = (hasTo && toValue.parameters.length == this.parameters.length && Arrays.equals(toValue.commands, this.commands));
/*     */ 
/*     */     
/*  77 */     boolean canAccumulate = (hasAcc && accValue.parameters.length == this.parameters.length && Arrays.equals(accValue.commands, this.commands));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (!canInterpolate && hasTo && interpolation >= 0.5D) {
/*  83 */       base = toValue;
/*     */     } else {
/*  85 */       base = this;
/*     */     } 
/*  87 */     int cmdCount = base.commands.length;
/*  88 */     int paramCount = base.parameters.length;
/*     */ 
/*     */     
/*  91 */     if (result == null) {
/*  92 */       res = new AnimatablePathDataValue(this.target);
/*  93 */       res.commands = new short[cmdCount];
/*  94 */       res.parameters = new float[paramCount];
/*  95 */       System.arraycopy(base.commands, 0, res.commands, 0, cmdCount);
/*     */     } else {
/*  97 */       res = (AnimatablePathDataValue)result;
/*  98 */       if (res.commands == null || res.commands.length != cmdCount) {
/*  99 */         res.commands = new short[cmdCount];
/* 100 */         System.arraycopy(base.commands, 0, res.commands, 0, cmdCount);
/* 101 */         res.hasChanged = true;
/*     */       }
/* 103 */       else if (!Arrays.equals(base.commands, res.commands)) {
/* 104 */         System.arraycopy(base.commands, 0, res.commands, 0, cmdCount);
/*     */         
/* 106 */         res.hasChanged = true;
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 111 */     for (int i = 0; i < paramCount; i++) {
/* 112 */       float newValue = base.parameters[i];
/* 113 */       if (canInterpolate) {
/* 114 */         newValue += interpolation * (toValue.parameters[i] - newValue);
/*     */       }
/* 116 */       if (canAccumulate) {
/* 117 */         newValue += multiplier * accValue.parameters[i];
/*     */       }
/* 119 */       if (res.parameters[i] != newValue) {
/* 120 */         res.parameters[i] = newValue;
/* 121 */         res.hasChanged = true;
/*     */       } 
/*     */     } 
/*     */     
/* 125 */     return res;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public short[] getCommands() {
/* 132 */     return this.commands;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float[] getParameters() {
/* 139 */     return this.parameters;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canPace() {
/* 147 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public float distanceTo(AnimatableValue other) {
/* 155 */     return 0.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AnimatableValue getZeroValue() {
/* 162 */     short[] cmds = new short[this.commands.length];
/* 163 */     System.arraycopy(this.commands, 0, cmds, 0, this.commands.length);
/* 164 */     float[] params = new float[this.parameters.length];
/* 165 */     return new AnimatablePathDataValue(this.target, cmds, params);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 171 */   protected static final char[] PATH_COMMANDS = new char[] { ' ', 'z', 'M', 'm', 'L', 'l', 'C', 'c', 'Q', 'q', 'A', 'a', 'H', 'h', 'V', 'v', 'S', 's', 'T', 't' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 179 */   protected static final int[] PATH_PARAMS = new int[] { 0, 0, 2, 2, 2, 2, 6, 6, 4, 4, 7, 7, 1, 1, 1, 1, 4, 4, 2, 2 };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toStringRep() {
/* 187 */     StringBuffer sb = new StringBuffer();
/* 188 */     int k = 0;
/* 189 */     for (short command : this.commands) {
/* 190 */       sb.append(PATH_COMMANDS[command]);
/* 191 */       for (int j = 0; j < PATH_PARAMS[command]; j++) {
/* 192 */         sb.append(' ');
/* 193 */         sb.append(this.parameters[k++]);
/*     */       } 
/*     */     } 
/* 196 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/anim/values/AnimatablePathDataValue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */