/*     */ package org.apache.pdfbox.pdmodel.common.function.type4;
/*     */ 
/*     */ import java.util.Stack;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class ArithmeticOperators
/*     */ {
/*     */   static class Abs
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  34 */       Number num = context.popNumber();
/*  35 */       if (num instanceof Integer) {
/*     */         
/*  37 */         context.getStack().push(Integer.valueOf(Math.abs(num.intValue())));
/*     */       }
/*     */       else {
/*     */         
/*  41 */         context.getStack().push(Float.valueOf(Math.abs(num.floatValue())));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Add
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  53 */       Number num2 = context.popNumber();
/*  54 */       Number num1 = context.popNumber();
/*  55 */       if (num1 instanceof Integer && num2 instanceof Integer) {
/*     */         
/*  57 */         long sum = num1.longValue() + num2.longValue();
/*  58 */         if (sum < -2147483648L || sum > 2147483647L)
/*     */         {
/*  60 */           context.getStack().push(Float.valueOf((float)sum));
/*     */         }
/*     */         else
/*     */         {
/*  64 */           context.getStack().push(Integer.valueOf((int)sum));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/*  69 */         float sum = num1.floatValue() + num2.floatValue();
/*  70 */         context.getStack().push(Float.valueOf(sum));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Atan
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/*  82 */       float den = context.popReal();
/*  83 */       float num = context.popReal();
/*  84 */       float atan = (float)Math.atan2(num, den);
/*  85 */       atan = (float)Math.toDegrees(atan) % 360.0F;
/*  86 */       if (atan < 0.0F)
/*     */       {
/*  88 */         atan += 360.0F;
/*     */       }
/*  90 */       context.getStack().push(Float.valueOf(atan));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Ceiling
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 101 */       Number num = context.popNumber();
/* 102 */       if (num instanceof Integer) {
/*     */         
/* 104 */         context.getStack().push(num);
/*     */       }
/*     */       else {
/*     */         
/* 108 */         context.getStack().push(Float.valueOf((float)Math.ceil(num.doubleValue())));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Cos
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 120 */       float angle = context.popReal();
/* 121 */       float cos = (float)Math.cos(Math.toRadians(angle));
/* 122 */       context.getStack().push(Float.valueOf(cos));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Cvi
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 133 */       Number num = context.popNumber();
/* 134 */       context.getStack().push(Integer.valueOf(num.intValue()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Cvr
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 145 */       Number num = context.popNumber();
/* 146 */       context.getStack().push(Float.valueOf(num.floatValue()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Div
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 157 */       Number num2 = context.popNumber();
/* 158 */       Number num1 = context.popNumber();
/* 159 */       context.getStack().push(Float.valueOf(num1.floatValue() / num2.floatValue()));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Exp
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 170 */       Number exp = context.popNumber();
/* 171 */       Number base = context.popNumber();
/* 172 */       double value = Math.pow(base.doubleValue(), exp.doubleValue());
/* 173 */       context.getStack().push(Float.valueOf((float)value));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Floor
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 184 */       Number num = context.popNumber();
/* 185 */       if (num instanceof Integer) {
/*     */         
/* 187 */         context.getStack().push(num);
/*     */       }
/*     */       else {
/*     */         
/* 191 */         context.getStack().push(Float.valueOf((float)Math.floor(num.doubleValue())));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class IDiv
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 203 */       int num2 = context.popInt();
/* 204 */       int num1 = context.popInt();
/* 205 */       context.getStack().push(Integer.valueOf(num1 / num2));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Ln
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 216 */       Number num = context.popNumber();
/* 217 */       context.getStack().push(Float.valueOf((float)Math.log(num.doubleValue())));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Log
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 228 */       Number num = context.popNumber();
/* 229 */       context.getStack().push(Float.valueOf((float)Math.log10(num.doubleValue())));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Mod
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 240 */       int int2 = context.popInt();
/* 241 */       int int1 = context.popInt();
/* 242 */       context.getStack().push(Integer.valueOf(int1 % int2));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Mul
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 253 */       Number num2 = context.popNumber();
/* 254 */       Number num1 = context.popNumber();
/* 255 */       if (num1 instanceof Integer && num2 instanceof Integer) {
/*     */         
/* 257 */         long result = num1.longValue() * num2.longValue();
/* 258 */         if (result >= -2147483648L && result <= 2147483647L)
/*     */         {
/* 260 */           context.getStack().push(Integer.valueOf((int)result));
/*     */         }
/*     */         else
/*     */         {
/* 264 */           context.getStack().push(Float.valueOf((float)result));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 269 */         double result = num1.doubleValue() * num2.doubleValue();
/* 270 */         context.getStack().push(Float.valueOf((float)result));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Neg
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 282 */       Number num = context.popNumber();
/* 283 */       if (num instanceof Integer) {
/*     */         
/* 285 */         int v = num.intValue();
/* 286 */         if (v == Integer.MIN_VALUE)
/*     */         {
/* 288 */           context.getStack().push(Float.valueOf(-num.floatValue()));
/*     */         }
/*     */         else
/*     */         {
/* 292 */           context.getStack().push(Integer.valueOf(-num.intValue()));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 297 */         context.getStack().push(Float.valueOf(-num.floatValue()));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Round
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 309 */       Number num = context.popNumber();
/* 310 */       if (num instanceof Integer) {
/*     */         
/* 312 */         context.getStack().push(Integer.valueOf(num.intValue()));
/*     */       }
/*     */       else {
/*     */         
/* 316 */         context.getStack().push(Float.valueOf((float)Math.round(num.doubleValue())));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Sin
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 328 */       float angle = context.popReal();
/* 329 */       float sin = (float)Math.sin(Math.toRadians(angle));
/* 330 */       context.getStack().push(Float.valueOf(sin));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Sqrt
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 341 */       float num = context.popReal();
/* 342 */       if (num < 0.0F)
/*     */       {
/* 344 */         throw new IllegalArgumentException("argument must be nonnegative");
/*     */       }
/* 346 */       context.getStack().push(Float.valueOf((float)Math.sqrt(num)));
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Sub
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 357 */       Stack<Object> stack = context.getStack();
/* 358 */       Number num2 = context.popNumber();
/* 359 */       Number num1 = context.popNumber();
/* 360 */       if (num1 instanceof Integer && num2 instanceof Integer) {
/*     */         
/* 362 */         long result = num1.longValue() - num2.longValue();
/* 363 */         if (result < -2147483648L || result > 2147483647L)
/*     */         {
/* 365 */           stack.push(Float.valueOf((float)result));
/*     */         }
/*     */         else
/*     */         {
/* 369 */           stack.push(Integer.valueOf((int)result));
/*     */         }
/*     */       
/*     */       } else {
/*     */         
/* 374 */         float result = num1.floatValue() - num2.floatValue();
/* 375 */         stack.push(Float.valueOf(result));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static class Truncate
/*     */     implements Operator
/*     */   {
/*     */     public void execute(ExecutionContext context) {
/* 387 */       Number num = context.popNumber();
/* 388 */       if (num instanceof Integer) {
/*     */         
/* 390 */         context.getStack().push(Integer.valueOf(num.intValue()));
/*     */       }
/*     */       else {
/*     */         
/* 394 */         context.getStack().push(Float.valueOf((int)num.floatValue()));
/*     */       } 
/*     */     }
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/pdfbox/pdmodel/common/function/type4/ArithmeticOperators.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */