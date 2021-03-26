/*     */ package org.a.a;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.io.OutputStream;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Writer;
/*     */ import java.math.BigDecimal;
/*     */ import java.math.BigInteger;
/*     */ import java.nio.charset.Charset;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import java.util.Map;
/*     */ import javax.json.JsonArray;
/*     */ import javax.json.JsonException;
/*     */ import javax.json.JsonNumber;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonString;
/*     */ import javax.json.JsonValue;
/*     */ import javax.json.stream.JsonGenerationException;
/*     */ import javax.json.stream.JsonGenerator;
/*     */ import org.a.a.a.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class e
/*     */   implements JsonGenerator
/*     */ {
/*  62 */   private static final char[] a = "-2147483648".toCharArray();
/*  63 */   private static final int[] b = new int[] { 9, 99, 999, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE };
/*     */ 
/*     */   
/*  66 */   private static final char[] c = new char[] { '0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  79 */   private static final char[] d = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private final a f;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  95 */   private static final char[] e = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' }; private final Writer g;
/*     */   
/*     */   private enum b { public static b[] a() {
/*     */       return (b[])e.clone();
/*     */     }
/*     */     
/* 101 */     a,
/* 102 */     b,
/* 103 */     c,
/* 104 */     d;
/*     */     public static b a(String name) {
/*     */       return Enum.<b>valueOf(b.class, name);
/*     */     } }
/*     */   
/* 109 */   private a h = new a(b.a);
/* 110 */   private final Deque<a> i = new ArrayDeque<>();
/*     */ 
/*     */   
/*     */   private final char[] j;
/*     */ 
/*     */   
/* 116 */   private int k = 0;
/*     */   
/*     */   e(Writer writer, a bufferPool) {
/* 119 */     this.g = writer;
/* 120 */     this.f = bufferPool;
/* 121 */     this.j = bufferPool.a();
/*     */   }
/*     */   
/*     */   e(OutputStream out, a bufferPool) {
/* 125 */     this(out, StandardCharsets.UTF_8, bufferPool);
/*     */   }
/*     */   
/*     */   e(OutputStream out, Charset encoding, a bufferPool) {
/* 129 */     this(new OutputStreamWriter(out, encoding), bufferPool);
/*     */   }
/*     */ 
/*     */   
/*     */   public void flush() {
/* 134 */     c();
/*     */     try {
/* 136 */       this.g.flush();
/* 137 */     } catch (IOException ioe) {
/* 138 */       throw new JsonException(h.g(), ioe);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeStartObject() {
/* 144 */     if (this.h.b == b.b) {
/* 145 */       throw new JsonGenerationException(h.a(this.h.b));
/*     */     }
/* 147 */     if (this.h.b == b.a && !this.h.a) {
/* 148 */       throw new JsonGenerationException(h.l());
/*     */     }
/* 150 */     a();
/* 151 */     a('{');
/* 152 */     this.i.push(this.h);
/* 153 */     this.h = new a(b.b);
/* 154 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeStartObject(String name) {
/* 159 */     if (this.h.b != b.b) {
/* 160 */       throw new JsonGenerationException(
/* 161 */           h.a(this.h.b));
/*     */     }
/* 163 */     c(name);
/* 164 */     a('{');
/* 165 */     this.i.push(this.h);
/* 166 */     this.h = new a(b.b);
/* 167 */     return this;
/*     */   }
/*     */   
/*     */   private JsonGenerator c(String name) {
/* 171 */     a();
/* 172 */     a(name);
/* 173 */     b();
/* 174 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(String name, String fieldValue) {
/* 179 */     if (this.h.b != b.b) {
/* 180 */       throw new JsonGenerationException(
/* 181 */           h.a(this.h.b));
/*     */     }
/* 183 */     c(name);
/* 184 */     a(fieldValue);
/* 185 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(String name, int value) {
/* 190 */     if (this.h.b != b.b) {
/* 191 */       throw new JsonGenerationException(
/* 192 */           h.a(this.h.b));
/*     */     }
/* 194 */     c(name);
/* 195 */     a(value);
/* 196 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(String name, long value) {
/* 201 */     if (this.h.b != b.b) {
/* 202 */       throw new JsonGenerationException(
/* 203 */           h.a(this.h.b));
/*     */     }
/* 205 */     c(name);
/* 206 */     b(String.valueOf(value));
/* 207 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(String name, double value) {
/* 212 */     if (this.h.b != b.b) {
/* 213 */       throw new JsonGenerationException(
/* 214 */           h.a(this.h.b));
/*     */     }
/* 216 */     if (Double.isInfinite(value) || Double.isNaN(value)) {
/* 217 */       throw new NumberFormatException(h.j());
/*     */     }
/* 219 */     c(name);
/* 220 */     b(String.valueOf(value));
/* 221 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(String name, BigInteger value) {
/* 226 */     if (this.h.b != b.b) {
/* 227 */       throw new JsonGenerationException(
/* 228 */           h.a(this.h.b));
/*     */     }
/* 230 */     c(name);
/* 231 */     b(String.valueOf(value));
/* 232 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(String name, BigDecimal value) {
/* 237 */     if (this.h.b != b.b) {
/* 238 */       throw new JsonGenerationException(
/* 239 */           h.a(this.h.b));
/*     */     }
/* 241 */     c(name);
/* 242 */     b(String.valueOf(value));
/* 243 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(String name, boolean value) {
/* 248 */     if (this.h.b != b.b) {
/* 249 */       throw new JsonGenerationException(
/* 250 */           h.a(this.h.b));
/*     */     }
/* 252 */     c(name);
/* 253 */     b(value ? "true" : "false");
/* 254 */     return this;
/*     */   }
/*     */   
/*     */   public JsonGenerator writeNull(String name)
/*     */   {
/* 259 */     if (this.h.b != b.b) {
/* 260 */       throw new JsonGenerationException(
/* 261 */           h.a(this.h.b));
/*     */     }
/* 263 */     c(name);
/* 264 */     b("null");
/* 265 */     return this; } public JsonGenerator write(JsonValue value) {
/*     */     JsonArray array;
/*     */     JsonObject object;
/*     */     JsonString str;
/*     */     JsonNumber number;
/* 270 */     d();
/*     */     
/* 272 */     switch (null.a[value.getValueType().ordinal()]) {
/*     */       case 1:
/* 274 */         array = (JsonArray)value;
/* 275 */         writeStartArray();
/* 276 */         for (JsonValue child : array) {
/* 277 */           write(child);
/*     */         }
/* 279 */         writeEnd();
/*     */         break;
/*     */       case 2:
/* 282 */         object = (JsonObject)value;
/* 283 */         writeStartObject();
/* 284 */         for (Map.Entry<String, JsonValue> member : (Iterable<Map.Entry<String, JsonValue>>)object.entrySet()) {
/* 285 */           write(member.getKey(), member.getValue());
/*     */         }
/* 287 */         writeEnd();
/*     */         break;
/*     */       case 3:
/* 290 */         str = (JsonString)value;
/* 291 */         write(str.getString());
/*     */         break;
/*     */       case 4:
/* 294 */         number = (JsonNumber)value;
/* 295 */         d(number.toString());
/*     */         break;
/*     */       case 5:
/* 298 */         write(true);
/*     */         break;
/*     */       case 6:
/* 301 */         write(false);
/*     */         break;
/*     */       case 7:
/* 304 */         writeNull();
/*     */         break;
/*     */     } 
/*     */     
/* 308 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeStartArray() {
/* 313 */     if (this.h.b == b.b) {
/* 314 */       throw new JsonGenerationException(h.a(this.h.b));
/*     */     }
/* 316 */     if (this.h.b == b.a && !this.h.a) {
/* 317 */       throw new JsonGenerationException(h.l());
/*     */     }
/* 319 */     a();
/* 320 */     a('[');
/* 321 */     this.i.push(this.h);
/* 322 */     this.h = new a(b.d);
/* 323 */     return this;
/*     */   }
/*     */   
/*     */   public JsonGenerator writeStartArray(String name)
/*     */   {
/* 328 */     if (this.h.b != b.b) {
/* 329 */       throw new JsonGenerationException(
/* 330 */           h.a(this.h.b));
/*     */     }
/* 332 */     c(name);
/* 333 */     a('[');
/* 334 */     this.i.push(this.h);
/* 335 */     this.h = new a(b.d);
/* 336 */     return this; } public JsonGenerator write(String name, JsonValue value) {
/*     */     JsonArray array;
/*     */     JsonObject object;
/*     */     JsonString str;
/*     */     JsonNumber number;
/* 341 */     if (this.h.b != b.b) {
/* 342 */       throw new JsonGenerationException(
/* 343 */           h.a(this.h.b));
/*     */     }
/* 345 */     switch (null.a[value.getValueType().ordinal()]) {
/*     */       case 1:
/* 347 */         array = (JsonArray)value;
/* 348 */         writeStartArray(name);
/* 349 */         for (JsonValue child : array) {
/* 350 */           write(child);
/*     */         }
/* 352 */         writeEnd();
/*     */         break;
/*     */       case 2:
/* 355 */         object = (JsonObject)value;
/* 356 */         writeStartObject(name);
/* 357 */         for (Map.Entry<String, JsonValue> member : (Iterable<Map.Entry<String, JsonValue>>)object.entrySet()) {
/* 358 */           write(member.getKey(), member.getValue());
/*     */         }
/* 360 */         writeEnd();
/*     */         break;
/*     */       case 3:
/* 363 */         str = (JsonString)value;
/* 364 */         write(name, str.getString());
/*     */         break;
/*     */       case 4:
/* 367 */         number = (JsonNumber)value;
/* 368 */         a(name, number.toString());
/*     */         break;
/*     */       case 5:
/* 371 */         write(name, true);
/*     */         break;
/*     */       case 6:
/* 374 */         write(name, false);
/*     */         break;
/*     */       case 7:
/* 377 */         writeNull(name);
/*     */         break;
/*     */     } 
/* 380 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(String value) {
/* 385 */     d();
/* 386 */     a();
/* 387 */     a(value);
/* 388 */     e();
/* 389 */     return this;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public JsonGenerator write(int value) {
/* 395 */     d();
/* 396 */     a();
/* 397 */     a(value);
/* 398 */     e();
/* 399 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(long value) {
/* 404 */     d();
/* 405 */     d(String.valueOf(value));
/* 406 */     e();
/* 407 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(double value) {
/* 412 */     d();
/* 413 */     if (Double.isInfinite(value) || Double.isNaN(value)) {
/* 414 */       throw new NumberFormatException(h.j());
/*     */     }
/* 416 */     d(String.valueOf(value));
/* 417 */     e();
/* 418 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(BigInteger value) {
/* 423 */     d();
/* 424 */     d(value.toString());
/* 425 */     e();
/* 426 */     return this;
/*     */   }
/*     */   
/*     */   private void d() {
/* 430 */     if ((!this.h.a && this.h.b != b.d && this.h.b != b.c) || (this.h.a && this.h.b == b.b))
/*     */     {
/* 432 */       throw new JsonGenerationException(
/* 433 */           h.a(this.h.b));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(BigDecimal value) {
/* 439 */     d();
/* 440 */     d(value.toString());
/* 441 */     e();
/*     */     
/* 443 */     return this;
/*     */   }
/*     */   
/*     */   private void e() {
/* 447 */     if (this.h.b == b.c) {
/* 448 */       this.h = this.i.pop();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator write(boolean value) {
/* 454 */     d();
/* 455 */     a();
/* 456 */     b(value ? "true" : "false");
/* 457 */     e();
/* 458 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeNull() {
/* 463 */     d();
/* 464 */     a();
/* 465 */     b("null");
/* 466 */     e();
/* 467 */     return this;
/*     */   }
/*     */   
/*     */   private void d(String value) {
/* 471 */     a();
/* 472 */     b(value);
/*     */   }
/*     */   
/*     */   private void a(String name, String value) {
/* 476 */     a();
/* 477 */     a(name);
/* 478 */     b();
/* 479 */     b(value);
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeKey(String name) {
/* 484 */     if (this.h.b != b.b) {
/* 485 */       throw new JsonGenerationException(
/* 486 */           h.a(this.h.b));
/*     */     }
/* 488 */     c(name);
/* 489 */     this.i.push(this.h);
/* 490 */     this.h = new a(b.c);
/* 491 */     this.h.a = false;
/* 492 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public JsonGenerator writeEnd() {
/* 497 */     if (this.h.b == b.a) {
/* 498 */       throw new JsonGenerationException("writeEnd() cannot be called in no context");
/*     */     }
/* 500 */     a((this.h.b == b.d) ? 93 : 125);
/* 501 */     this.h = this.i.pop();
/* 502 */     e();
/* 503 */     return this;
/*     */   }
/*     */   
/*     */   protected void a() {
/* 507 */     if (!this.h.a && this.h.b != b.c) {
/* 508 */       a(',');
/*     */     }
/* 510 */     this.h.a = false;
/*     */   }
/*     */   
/*     */   protected void b() {
/* 514 */     a(':');
/*     */   }
/*     */   
/*     */   private static class a {
/*     */     boolean a = true;
/*     */     final e.b b;
/*     */     
/*     */     a(e.b scope) {
/* 522 */       this.b = scope;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 529 */     if (this.h.b != b.a || this.h.a) {
/* 530 */       throw new JsonGenerationException(h.k());
/*     */     }
/* 532 */     c();
/*     */     try {
/* 534 */       this.g.close();
/* 535 */     } catch (IOException ioe) {
/* 536 */       throw new JsonException(h.h(), ioe);
/*     */     } 
/* 538 */     this.f.a(this.j);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void a(String string) {
/* 549 */     a('"');
/* 550 */     int len = string.length();
/* 551 */     for (int i = 0; i < len; i++) {
/* 552 */       String hex; int begin = i, end = i;
/* 553 */       char c = string.charAt(i);
/*     */ 
/*     */       
/* 556 */       while (c >= ' ' && c <= 1114111 && c != '"' && c != '\\') {
/* 557 */         end = ++i;
/* 558 */         if (i < len) {
/* 559 */           c = string.charAt(i);
/*     */         }
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 565 */       if (begin < end) {
/* 566 */         a(string, begin, end);
/* 567 */         if (i == len) {
/*     */           break;
/*     */         }
/*     */       } 
/*     */       
/* 572 */       switch (c) {
/*     */         case '"':
/*     */         case '\\':
/* 575 */           a('\\'); a(c);
/*     */           break;
/*     */         case '\b':
/* 578 */           a('\\'); a('b');
/*     */           break;
/*     */         case '\f':
/* 581 */           a('\\'); a('f');
/*     */           break;
/*     */         case '\n':
/* 584 */           a('\\'); a('n');
/*     */           break;
/*     */         case '\r':
/* 587 */           a('\\'); a('r');
/*     */           break;
/*     */         case '\t':
/* 590 */           a('\\'); a('t');
/*     */           break;
/*     */         default:
/* 593 */           hex = "000" + Integer.toHexString(c);
/* 594 */           b("\\u" + hex.substring(hex.length() - 4)); break;
/*     */       } 
/*     */     } 
/* 597 */     a('"');
/*     */   }
/*     */   
/*     */   void a(String str, int begin, int end) {
/* 601 */     while (begin < end) {
/* 602 */       int no = Math.min(this.j.length - this.k, end - begin);
/* 603 */       str.getChars(begin, begin + no, this.j, this.k);
/* 604 */       begin += no;
/* 605 */       this.k += no;
/* 606 */       if (this.k >= this.j.length) {
/* 607 */         c();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   void b(String str) {
/* 613 */     a(str, 0, str.length());
/*     */   }
/*     */   
/*     */   void a(char c) {
/* 617 */     if (this.k >= this.j.length) {
/* 618 */       c();
/*     */     }
/* 620 */     this.j[this.k++] = c;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   void a(int num) {
/*     */     int size;
/* 627 */     if (num == Integer.MIN_VALUE) {
/* 628 */       size = a.length;
/*     */     } else {
/* 630 */       size = (num < 0) ? (b(-num) + 1) : b(num);
/*     */     } 
/* 632 */     if (this.k + size >= this.j.length) {
/* 633 */       c();
/*     */     }
/* 635 */     if (num == Integer.MIN_VALUE) {
/* 636 */       System.arraycopy(a, 0, this.j, this.k, size);
/*     */     } else {
/* 638 */       a(num, this.j, this.k + size);
/*     */     } 
/* 640 */     this.k += size;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void c() {
/*     */     try {
/* 649 */       if (this.k > 0) {
/* 650 */         this.g.write(this.j, 0, this.k);
/* 651 */         this.k = 0;
/*     */       } 
/* 653 */     } catch (IOException ioe) {
/* 654 */       throw new JsonException(h.i(), ioe);
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
/*     */   private static int b(int x) {
/* 682 */     for (int i = 0;; i++) {
/* 683 */       if (x <= b[i]) {
/* 684 */         return i + 1;
/*     */       }
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
/*     */   private static void a(int i, char[] buf, int index) {
/* 698 */     int charPos = index;
/* 699 */     char sign = Character.MIN_VALUE;
/*     */     
/* 701 */     if (i < 0) {
/* 702 */       sign = '-';
/* 703 */       i = -i;
/*     */     } 
/*     */ 
/*     */     
/* 707 */     while (i >= 65536) {
/* 708 */       int q = i / 100;
/*     */       
/* 710 */       int r = i - (q << 6) + (q << 5) + (q << 2);
/* 711 */       i = q;
/* 712 */       buf[--charPos] = d[r];
/* 713 */       buf[--charPos] = c[r];
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 719 */       int q = i * 52429 >>> 19;
/* 720 */       int r = i - (q << 3) + (q << 1);
/* 721 */       buf[--charPos] = e[r];
/* 722 */       i = q;
/* 723 */     } while (i != 0);
/*     */     
/* 725 */     if (sign != '\000')
/* 726 */       buf[--charPos] = sign; 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */