/*     */ package jp.cssj.b.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class g
/*     */ {
/*     */   public static boolean a(String type1, String type2) {
/*  24 */     if (type2 == null || type1 == null) {
/*  25 */       return false;
/*     */     }
/*  27 */     type1 = a(type1);
/*  28 */     type2 = a(type2);
/*  29 */     return type1.equals(type2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(String type) {
/*  40 */     if (type == null) {
/*  41 */       return null;
/*     */     }
/*  43 */     int semi = type.indexOf(';');
/*  44 */     if (semi != -1) {
/*  45 */       return type.substring(0, semi).trim();
/*     */     }
/*  47 */     return type.trim();
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
/*     */   public static String b(String type, String name) {
/*  61 */     int state = 0;
/*  62 */     StringBuffer buff = new StringBuffer();
/*  63 */     String pname = "", value = "";
/*     */     
/*  65 */     for (int i = 0; i < type.length(); i++) {
/*  66 */       char c = type.charAt(i);
/*  67 */       switch (state) {
/*     */         case 0:
/*  69 */           if (c == '=') {
/*  70 */             pname = buff.toString().trim();
/*  71 */             buff = new StringBuffer(); break;
/*  72 */           }  if (c == ';') {
/*  73 */             if (name.equalsIgnoreCase(pname)) {
/*  74 */               return value + buff.toString().trim();
/*     */             }
/*  76 */             pname = "";
/*  77 */             value = "";
/*  78 */             buff = new StringBuffer(); break;
/*  79 */           }  if (c == '"') {
/*  80 */             state = 1;
/*  81 */             buff = new StringBuffer(buff.toString().trim()); break;
/*  82 */           }  if (c == '\'') {
/*  83 */             state = 2;
/*  84 */             buff = new StringBuffer(buff.toString().trim()); break;
/*     */           } 
/*  86 */           buff.append(c);
/*     */           break;
/*     */         
/*     */         case 1:
/*  90 */           if (c == '"') {
/*  91 */             value = buff.toString();
/*  92 */             buff = new StringBuffer();
/*  93 */             state = 0; break;
/*     */           } 
/*  95 */           buff.append(c);
/*     */           break;
/*     */         
/*     */         case 2:
/*  99 */           if (c == '\'') {
/* 100 */             value = buff.toString();
/* 101 */             buff = new StringBuffer();
/* 102 */             state = 0; break;
/*     */           } 
/* 104 */           buff.append(c);
/*     */           break;
/*     */       } 
/*     */     
/*     */     } 
/* 109 */     if (name.equalsIgnoreCase(pname)) {
/* 110 */       return value + buff.toString().trim();
/*     */     }
/* 112 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/b/a/g.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */