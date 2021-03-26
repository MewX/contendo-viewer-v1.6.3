/*     */ package org.a.a;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.ResourceBundle;
/*     */ import javax.json.JsonObject;
/*     */ import javax.json.JsonValue;
/*     */ import javax.json.stream.JsonLocation;
/*     */ import javax.json.stream.JsonParser;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class h
/*     */ {
/*  58 */   private static final ResourceBundle a = ResourceBundle.getBundle("org.glassfish.json.messages");
/*     */ 
/*     */   
/*     */   static String a() {
/*  62 */     return a("internal.error", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   static String a(int unexpected, JsonLocation location) {
/*  67 */     return a("tokenizer.unexpected.char", new Object[] { Integer.valueOf(unexpected), location });
/*     */   }
/*     */   
/*     */   static String a(int unexpected, JsonLocation location, char expected) {
/*  71 */     return a("tokenizer.expected.char", new Object[] { Integer.valueOf(unexpected), location, Character.valueOf(expected) });
/*     */   }
/*     */   
/*     */   static String b() {
/*  75 */     return a("tokenizer.io.err", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static String a(JsonParser.Event event) {
/*  81 */     return a("parser.getString.err", new Object[] { event });
/*     */   }
/*     */   
/*     */   static String b(JsonParser.Event event) {
/*  85 */     return a("parser.isIntegralNumber.err", new Object[] { event });
/*     */   }
/*     */   
/*     */   static String c(JsonParser.Event event) {
/*  89 */     return a("parser.getInt.err", new Object[] { event });
/*     */   }
/*     */   
/*     */   static String d(JsonParser.Event event) {
/*  93 */     return a("parser.getLong.err", new Object[] { event });
/*     */   }
/*     */   
/*     */   static String e(JsonParser.Event event) {
/*  97 */     return a("parser.getBigDecimal.err", new Object[] { event });
/*     */   }
/*     */   
/*     */   static String f(JsonParser.Event event) {
/* 101 */     return a("parser.getArray.err", new Object[] { event });
/*     */   }
/*     */   
/*     */   static String g(JsonParser.Event event) {
/* 105 */     return a("parser.getObject.err", new Object[] { event });
/*     */   }
/*     */   
/*     */   static String h(JsonParser.Event event) {
/* 109 */     return a("parser.getValue.err", new Object[] { event });
/*     */   }
/*     */   
/*     */   static String c() {
/* 113 */     return a("parser.getValueStream.err", new Object[0]);
/*     */   }
/*     */   
/*     */   static String a(v.a token) {
/* 117 */     return a("parser.expected.eof", new Object[] { token });
/*     */   }
/*     */   
/*     */   static String d() {
/* 121 */     return a("parser.tokenizer.close.io", new Object[0]);
/*     */   }
/*     */   
/*     */   static String a(v.a token, JsonLocation location, String expectedTokens) {
/* 125 */     return a("parser.invalid.token", new Object[] { token, location, expectedTokens });
/*     */   }
/*     */   
/*     */   static String a(JsonValue.ValueType type) {
/* 129 */     return a("parser.state.err", new Object[] { type });
/*     */   }
/*     */   
/*     */   static String a(JsonValue value) {
/* 133 */     return a("parser.scope.err", new Object[] { value });
/*     */   }
/*     */   
/*     */   static String e() {
/* 137 */     return a("parser.input.enc.detect.failed", new Object[0]);
/*     */   }
/*     */   
/*     */   static String f() {
/* 141 */     return a("parser.input.enc.detect.ioerr", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   static String g() {
/* 146 */     return a("generator.flush.io.err", new Object[0]);
/*     */   }
/*     */   
/*     */   static String h() {
/* 150 */     return a("generator.close.io.err", new Object[0]);
/*     */   }
/*     */   
/*     */   static String i() {
/* 154 */     return a("generator.write.io.err", new Object[0]);
/*     */   }
/*     */   
/*     */   static String a(Object scope) {
/* 158 */     return a("generator.illegal.method", new Object[] { scope });
/*     */   }
/*     */   
/*     */   static String j() {
/* 162 */     return a("generator.double.infinite.nan", new Object[0]);
/*     */   }
/*     */   
/*     */   static String k() {
/* 166 */     return a("generator.incomplete.json", new Object[0]);
/*     */   }
/*     */   
/*     */   static String l() {
/* 170 */     return a("generator.illegal.multiple.text", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static String m() {
/* 177 */     return a("writer.write.already.called", new Object[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   static String n() {
/* 182 */     return a("reader.read.already.called", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static String o() {
/* 188 */     return a("objbuilder.name.null", new Object[0]);
/*     */   }
/*     */   
/*     */   static String p() {
/* 192 */     return a("objbuilder.value.null", new Object[0]);
/*     */   }
/*     */   
/*     */   static String q() {
/* 196 */     return a("objbuilder.object.builder.null", new Object[0]);
/*     */   }
/*     */   
/*     */   static String r() {
/* 200 */     return a("objbuilder.array.builder.null", new Object[0]);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static String s() {
/* 206 */     return a("arrbuilder.value.null", new Object[0]);
/*     */   }
/*     */   
/*     */   static String t() {
/* 210 */     return a("arrbuilder.object.builder.null", new Object[0]);
/*     */   }
/*     */   
/*     */   static String u() {
/* 214 */     return a("arrbuilder.array.builder.null", new Object[0]);
/*     */   }
/*     */   
/*     */   static String a(int index, int size) {
/* 218 */     return a("arrbuilder.valuelist.null", new Object[] { Integer.valueOf(index), Integer.valueOf(size) });
/*     */   }
/*     */ 
/*     */   
/*     */   static String v() {
/* 223 */     return a("pointer.format.invalid", new Object[0]);
/*     */   }
/*     */   
/*     */   static String a(JsonObject object, String key) {
/* 227 */     return a("pointer.mapping.missing", new Object[] { object, key });
/*     */   }
/*     */   
/*     */   static String b(JsonValue.ValueType type) {
/* 231 */     return a("pointer.reference.invalid", new Object[] { type.name() });
/*     */   }
/*     */   
/*     */   static String a(String token) {
/* 235 */     return a("pointer.array.index.err", new Object[] { token });
/*     */   }
/*     */   
/*     */   static String b(String token) {
/* 239 */     return a("pointer.array.index.illegal", new Object[] { token });
/*     */   }
/*     */ 
/*     */   
/*     */   static String w() {
/* 244 */     return a("noderef.value.add.err", new Object[0]);
/*     */   }
/*     */   
/*     */   static String x() {
/* 248 */     return a("noderef.value.cannot.remove", new Object[0]);
/*     */   }
/*     */   
/*     */   static String c(String key) {
/* 252 */     return a("noderef.object.missing", new Object[] { key });
/*     */   }
/*     */   
/*     */   static String b(int index, int size) {
/* 256 */     return a("noderef.array.index.err", new Object[] { Integer.valueOf(index), Integer.valueOf(size) });
/*     */   }
/*     */ 
/*     */   
/*     */   static String y() {
/* 261 */     return a("patch.must.be.array", new Object[0]);
/*     */   }
/*     */   
/*     */   static String a(String from, String path) {
/* 265 */     return a("patch.move.proper.prefix", new Object[] { from, path });
/*     */   }
/*     */   
/*     */   static String d(String from) {
/* 269 */     return a("patch.move.target.null", new Object[] { from });
/*     */   }
/*     */   
/*     */   static String z() {
/* 273 */     return a("patch.test.failed", new Object[0]);
/*     */   }
/*     */   
/*     */   static String e(String operation) {
/* 277 */     return a("patch.illegal.operation", new Object[] { operation });
/*     */   }
/*     */   
/*     */   static String b(String operation, String member) {
/* 281 */     return a("patch.member.missing", new Object[] { operation, member });
/*     */   }
/*     */ 
/*     */   
/*     */   private static String a(String key, Object... args) {
/*     */     try {
/* 287 */       String msg = a.getString(key);
/* 288 */       return MessageFormat.format(msg, args);
/* 289 */     } catch (Exception e) {
/* 290 */       return b(key, args);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static String b(String key, Object... args) {
/* 295 */     StringBuilder sb = new StringBuilder();
/* 296 */     sb.append("[failed to localize] ");
/* 297 */     sb.append(key);
/* 298 */     if (args != null) {
/* 299 */       sb.append('(');
/* 300 */       for (int i = 0; i < args.length; i++) {
/* 301 */         if (i != 0)
/* 302 */           sb.append(", "); 
/* 303 */         sb.append(String.valueOf(args[i]));
/*     */       } 
/* 305 */       sb.append(')');
/*     */     } 
/* 307 */     return sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/a/a/h.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */