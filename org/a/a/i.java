/*     */ package org.a.a;
/*     */ 
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import javax.json.JsonNumber;
/*     */ import javax.json.JsonValue;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ abstract class i
/*     */   implements JsonNumber
/*     */ {
/*     */   static JsonNumber a(int num) {
/*  56 */     return new b(num);
/*     */   }
/*     */   
/*     */   static JsonNumber a(long num) {
/*  60 */     return new c(num);
/*     */   }
/*     */   
/*     */   static JsonNumber a(BigInteger value) {
/*  64 */     return new a(new BigDecimal(value));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static JsonNumber a(double value) {
/*  70 */     return new a(BigDecimal.valueOf(value));
/*     */   }
/*     */   
/*     */   static JsonNumber a(BigDecimal value) {
/*  74 */     return new a(value);
/*     */   }
/*     */   
/*     */   private static final class b
/*     */     extends i {
/*     */     private final int a;
/*     */     private BigDecimal b;
/*     */     
/*     */     b(int num) {
/*  83 */       this.a = num;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isIntegral() {
/*  88 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public int intValue() {
/*  93 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public int intValueExact() {
/*  98 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public long longValue() {
/* 103 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public long longValueExact() {
/* 108 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public double doubleValue() {
/* 113 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BigDecimal bigDecimalValue() {
/* 120 */       BigDecimal bd = this.b;
/* 121 */       if (bd == null) {
/* 122 */         this.b = bd = new BigDecimal(this.a);
/*     */       }
/* 124 */       return bd;
/*     */     }
/*     */ 
/*     */     
/*     */     public Number numberValue() {
/* 129 */       return Integer.valueOf(this.a);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 134 */       return Integer.toString(this.a);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class c
/*     */     extends i {
/*     */     private final long a;
/*     */     private BigDecimal b;
/*     */     
/*     */     c(long num) {
/* 144 */       this.a = num;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean isIntegral() {
/* 149 */       return true;
/*     */     }
/*     */ 
/*     */     
/*     */     public int intValue() {
/* 154 */       return (int)this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public int intValueExact() {
/* 159 */       return Math.toIntExact(this.a);
/*     */     }
/*     */ 
/*     */     
/*     */     public long longValue() {
/* 164 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public long longValueExact() {
/* 169 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public double doubleValue() {
/* 174 */       return this.a;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public BigDecimal bigDecimalValue() {
/* 181 */       BigDecimal bd = this.b;
/* 182 */       if (bd == null) {
/* 183 */         this.b = bd = new BigDecimal(this.a);
/*     */       }
/* 185 */       return bd;
/*     */     }
/*     */ 
/*     */     
/*     */     public Number numberValue() {
/* 190 */       return Long.valueOf(this.a);
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 195 */       return Long.toString(this.a);
/*     */     }
/*     */   }
/*     */   
/*     */   private static final class a
/*     */     extends i
/*     */   {
/*     */     private final BigDecimal a;
/*     */     
/*     */     a(BigDecimal value) {
/* 205 */       this.a = value;
/*     */     }
/*     */ 
/*     */     
/*     */     public BigDecimal bigDecimalValue() {
/* 210 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public Number numberValue() {
/* 215 */       return bigDecimalValue();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isIntegral() {
/* 222 */     return (bigDecimalValue().scale() == 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public int intValue() {
/* 227 */     return bigDecimalValue().intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public int intValueExact() {
/* 232 */     return bigDecimalValue().intValueExact();
/*     */   }
/*     */ 
/*     */   
/*     */   public long longValue() {
/* 237 */     return bigDecimalValue().longValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public long longValueExact() {
/* 242 */     return bigDecimalValue().longValueExact();
/*     */   }
/*     */ 
/*     */   
/*     */   public double doubleValue() {
/* 247 */     return bigDecimalValue().doubleValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigInteger bigIntegerValue() {
/* 252 */     return bigDecimalValue().toBigInteger();
/*     */   }
/*     */ 
/*     */   
/*     */   public BigInteger bigIntegerValueExact() {
/* 257 */     return bigDecimalValue().toBigIntegerExact();
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonValue.ValueType getValueType() {
/* 262 */     return JsonValue.ValueType.NUMBER;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 267 */     return bigDecimalValue().hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 272 */     if (this == obj) {
/* 273 */       return true;
/*     */     }
/* 275 */     if (!(obj instanceof JsonNumber)) {
/* 276 */       return false;
/*     */     }
/* 278 */     JsonNumber other = (JsonNumber)obj;
/* 279 */     return bigDecimalValue().equals(other.bigDecimalValue());
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 284 */     return bigDecimalValue().toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/i.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */