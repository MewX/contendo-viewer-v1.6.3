/*     */ package org.apache.batik.parser;
/*     */ 
/*     */ import java.io.IOException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class NumberParser
/*     */   extends AbstractParser
/*     */ {
/*     */   protected float parseFloat() throws ParseException, IOException {
/*  35 */     int mant = 0;
/*  36 */     int mantDig = 0;
/*  37 */     boolean mantPos = true;
/*  38 */     boolean mantRead = false;
/*     */     
/*  40 */     int exp = 0;
/*  41 */     int expDig = 0;
/*  42 */     int expAdj = 0;
/*  43 */     boolean expPos = true;
/*     */     
/*  45 */     switch (this.current) {
/*     */       case 45:
/*  47 */         mantPos = false;
/*     */       
/*     */       case 43:
/*  50 */         this.current = this.reader.read();
/*     */         break;
/*     */     } 
/*  53 */     switch (this.current)
/*     */     { default:
/*  55 */         reportUnexpectedCharacterError(this.current);
/*  56 */         return 0.0F;
/*     */       
/*     */       case 46:
/*     */         break;
/*     */       
/*     */       case 48:
/*  62 */         mantRead = true;
/*     */         while (true)
/*  64 */         { this.current = this.reader.read();
/*  65 */           switch (this.current) { case 49: // Byte code: goto -> 287
/*     */             case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: break;
/*     */             case 46:
/*     */             case 69:
/*     */             case 101:
/*     */               break;
/*     */             default:
/*  72 */               return 0.0F;
/*     */             case 48:  }  }  break;
/*     */       case 49: case 50: case 51: case 52: case 53:
/*     */       case 54:
/*     */       case 55:
/*     */       case 56:
/*     */       case 57:
/*  79 */         mantRead = true;
/*     */         while (true)
/*  81 */         { if (mantDig < 9) {
/*  82 */             mantDig++;
/*  83 */             mant = mant * 10 + this.current - 48;
/*     */           } else {
/*  85 */             expAdj++;
/*     */           } 
/*  87 */           this.current = this.reader.read();
/*  88 */           switch (this.current) { case 48: case 49:
/*     */             case 50:
/*     */             case 51:
/*     */             case 52:
/*     */             case 53:
/*     */             case 54:
/*     */             case 55:
/*     */             case 56:
/*     */             case 57:
/*  97 */               break; }  }  break; }  if (this.current == 46)
/*  98 */     { this.current = this.reader.read();
/*  99 */       switch (this.current)
/*     */       
/*     */       { default:
/* 102 */           if (!mantRead) {
/* 103 */             reportUnexpectedCharacterError(this.current);
/* 104 */             return 0.0F;
/*     */           } 
/*     */           break;
/*     */         
/*     */         case 48:
/* 109 */           if (mantDig == 0)
/*     */           { while (true)
/* 111 */             { this.current = this.reader.read();
/* 112 */               expAdj--;
/* 113 */               switch (this.current) { case 49: continue label80;case 50: continue label80;case 51: continue label80;case 52: continue label80;case 53: continue label80;
/*     */                 case 54: continue label80;
/*     */                 case 55: continue label80;
/*     */                 case 56: continue label80;
/*     */                 case 57: continue label80;
/* 118 */                 default: if (!mantRead)
/* 119 */                     return 0.0F;  break;
/*     */                 case 48: break; }  }  break; } 
/*     */         case 49: case 50:
/*     */         case 51:
/*     */         case 52:
/*     */         case 53:
/*     */         case 54:
/*     */         case 55:
/*     */         case 56:
/*     */         case 57:
/* 129 */           label80: while (true) { if (mantDig < 9) {
/* 130 */               mantDig++;
/* 131 */               mant = mant * 10 + this.current - 48;
/* 132 */               expAdj--;
/*     */             } 
/* 134 */             this.current = this.reader.read();
/* 135 */             switch (this.current) { case 48:
/*     */               case 49:
/*     */               case 50:
/*     */               case 51:
/*     */               case 52:
/*     */               case 53:
/*     */               case 54:
/*     */               case 55:
/*     */               case 56:
/*     */               case 57:
/* 145 */                 break; }  }  break; }  }  switch (this.current) { case 69:
/*     */       case 101:
/* 147 */         this.current = this.reader.read();
/* 148 */         switch (this.current)
/*     */         { default:
/* 150 */             reportUnexpectedCharacterError(this.current);
/* 151 */             return 0.0F;
/*     */           case 45:
/* 153 */             expPos = false;
/*     */           case 43:
/* 155 */             this.current = this.reader.read();
/* 156 */             switch (this.current)
/*     */             { default:
/* 158 */                 reportUnexpectedCharacterError(this.current);
/* 159 */                 return 0.0F;
/*     */               case 48: case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56: case 57: break; }  break;
/*     */           case 48: case 49: case 50: case 51: case 52:
/*     */           case 53:
/*     */           case 54:
/*     */           case 55:
/*     */           case 56:
/*     */           case 57:
/* 167 */             break; }  switch (this.current)
/*     */         { case 48:
/*     */             while (true)
/* 170 */             { this.current = this.reader.read();
/* 171 */               switch (this.current) { case 49: continue label81;
/*     */                 case 50: continue label81;
/*     */                 case 51: continue label81;
/*     */                 case 52: continue label81;
/*     */                 case 53: continue label81;
/*     */                 case 54: continue label81;
/*     */                 case 55: continue label81;
/*     */                 case 56: continue label81;
/*     */                 case 57: continue label81;
/*     */                 default: break;
/*     */                 case 48: break; }  }  break;
/*     */           case 49: case 50: case 51: case 52: case 53: case 54: case 55: case 56:
/*     */           case 57:
/* 184 */             label81: while (true) { if (expDig < 3) {
/* 185 */                 expDig++;
/* 186 */                 exp = exp * 10 + this.current - 48;
/*     */               } 
/* 188 */               this.current = this.reader.read();
/* 189 */               switch (this.current) { case 48:
/*     */                 case 49:
/*     */                 case 50:
/*     */                 case 51:
/*     */                 case 52:
/*     */                 case 53:
/*     */                 case 54:
/*     */                 case 55:
/*     */                 case 56:
/*     */                 case 57:
/*     */                   break; }  }  break; }  break; }
/* 200 */      if (!expPos) {
/* 201 */       exp = -exp;
/*     */     }
/* 203 */     exp += expAdj;
/* 204 */     if (!mantPos) {
/* 205 */       mant = -mant;
/*     */     }
/*     */     
/* 208 */     return buildFloat(mant, exp);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static float buildFloat(int mant, int exp) {
/* 215 */     if (exp < -125 || mant == 0) {
/* 216 */       return 0.0F;
/*     */     }
/*     */     
/* 219 */     if (exp >= 128) {
/* 220 */       return (mant > 0) ? Float.POSITIVE_INFINITY : Float.NEGATIVE_INFINITY;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 225 */     if (exp == 0) {
/* 226 */       return mant;
/*     */     }
/*     */     
/* 229 */     if (mant >= 67108864) {
/* 230 */       mant++;
/*     */     }
/*     */     
/* 233 */     return (float)((exp > 0) ? (mant * pow10[exp]) : (mant / pow10[-exp]));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 239 */   private static final double[] pow10 = new double[128];
/*     */   
/*     */   static {
/* 242 */     for (int i = 0; i < pow10.length; i++)
/* 243 */       pow10[i] = Math.pow(10.0D, i); 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/batik/parser/NumberParser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */