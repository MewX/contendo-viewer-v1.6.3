/*     */ package jp.cssj.homare.css.e;
/*     */ 
/*     */ import jp.cssj.homare.css.f.F;
/*     */ import jp.cssj.homare.css.f.G;
/*     */ import jp.cssj.homare.impl.a.b.c;
/*     */ import jp.cssj.homare.impl.a.b.d;
/*     */ import jp.cssj.homare.impl.a.b.h;
/*     */ import jp.cssj.sakae.c.a.h;
/*     */ import jp.cssj.sakae.c.b.b;
/*     */ import jp.cssj.sakae.c.c.b;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class e
/*     */ {
/*     */   private static final String a = " abcdefghijklmnopqrstuvwxyz";
/*     */   private static final String b = " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
/*     */   private static final String c = " αβγδεζηθικλμνξοπρστυφχψω";
/*     */   private static final String d = "　あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん";
/*     */   private static final String e = "　アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン";
/*     */   private static final String f = "　いろはにほへとちりぬるをわかよたれそつねならむうゐのおくやまけふこえてあさきゆめみしゑひもせすん";
/*     */   private static final String g = "　イロハニホヘトチリヌルヲワカヨタレソツネナラムウヰノオクヤマケフコエテアサキユメミシヱヒモセスン";
/*     */   
/*     */   public static G a(String ident) {
/*  28 */     ident = ident.toLowerCase();
/*  29 */     if (ident.equals("disc"))
/*  30 */       return G.y; 
/*  31 */     if (ident.equals("circle"))
/*  32 */       return G.z; 
/*  33 */     if (ident.equals("square"))
/*  34 */       return G.A; 
/*  35 */     if (ident.equals("decimal"))
/*  36 */       return G.B; 
/*  37 */     if (ident.equals("decimal-leading-zero"))
/*  38 */       return G.C; 
/*  39 */     if (ident.equals("lower-roman"))
/*  40 */       return G.D; 
/*  41 */     if (ident.equals("upper-roman"))
/*  42 */       return G.E; 
/*  43 */     if (ident.equals("lower-greek"))
/*  44 */       return G.F; 
/*  45 */     if (ident.equals("lower-alpha"))
/*  46 */       return G.G; 
/*  47 */     if (ident.equals("lower-latin"))
/*  48 */       return G.H; 
/*  49 */     if (ident.equals("upper-alpha"))
/*  50 */       return G.I; 
/*  51 */     if (ident.equals("upper-latin"))
/*  52 */       return G.J; 
/*  53 */     if (ident.equals("hebrew"))
/*  54 */       return G.B; 
/*  55 */     if (ident.equals("armenian"))
/*  56 */       return G.L; 
/*  57 */     if (ident.equals("georgian"))
/*  58 */       return G.M; 
/*  59 */     if (ident.equals("cjk-ideographic"))
/*  60 */       return G.N; 
/*  61 */     if (ident.equals("hiragana"))
/*  62 */       return G.O; 
/*  63 */     if (ident.equals("katakana"))
/*  64 */       return G.P; 
/*  65 */     if (ident.equals("hiragana-iroha"))
/*  66 */       return G.Q; 
/*  67 */     if (ident.equals("katakana-iroha"))
/*  68 */       return G.R; 
/*  69 */     if (ident.equals("-cssj-full-width-decimal") || ident.equals("-cssj-decimal-full-width"))
/*  70 */       return G.S; 
/*  71 */     if (ident.equals("-cssj-cjk-decimal"))
/*  72 */       return G.T; 
/*  73 */     if (ident.equals("none")) {
/*  74 */       return G.x;
/*     */     }
/*  76 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static F b(String ident) {
/*  86 */     ident = ident.toLowerCase();
/*  87 */     if (ident.equals("inside"))
/*  88 */       return F.c; 
/*  89 */     if (ident.equals("outside")) {
/*  90 */       return F.d;
/*     */     }
/*  92 */     return null;
/*     */   }
/*     */   
/*     */   public static b a(short listStyleType, b color, h fontStyle) {
/*  96 */     switch (listStyleType) {
/*     */       case 1:
/*  98 */         return (b)new d(fontStyle, color);
/*     */ 
/*     */       
/*     */       case 2:
/* 102 */         return (b)new c(fontStyle, color);
/*     */ 
/*     */       
/*     */       case 3:
/* 106 */         return (b)new h(fontStyle, color);
/*     */ 
/*     */       
/*     */       case 0:
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/*     */       case 16:
/*     */       case 17:
/*     */       case 18:
/*     */       case 19:
/*     */       case 20:
/*     */       case 21:
/*     */       case 22:
/* 129 */         return null;
/*     */     } 
/* 131 */     throw new IllegalArgumentException(String.valueOf(listStyleType));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(int number, short listStyleType) {
/*     */     String str;
/*     */     char[] ch;
/*     */     int i;
/* 145 */     switch (listStyleType) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 150 */         return null;
/*     */ 
/*     */       
/*     */       case 4:
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/* 157 */         return String.valueOf(number);
/*     */ 
/*     */       
/*     */       case 5:
/* 161 */         str = String.valueOf(number);
/* 162 */         if (str.length() == 1) {
/* 163 */           return "0" + str;
/*     */         }
/* 165 */         return str;
/*     */ 
/*     */       
/*     */       case 9:
/*     */       case 10:
/* 170 */         return a(number, " abcdefghijklmnopqrstuvwxyz");
/*     */ 
/*     */       
/*     */       case 11:
/*     */       case 12:
/* 175 */         return a(number, " ABCDEFGHIJKLMNOPQRSTUVWXYZ");
/*     */ 
/*     */       
/*     */       case 6:
/* 179 */         return a(number).toLowerCase();
/*     */ 
/*     */       
/*     */       case 7:
/* 183 */         return a(number);
/*     */ 
/*     */       
/*     */       case 8:
/* 187 */         return a(number, " αβγδεζηθικλμνξοπρστυφχψω");
/*     */       
/*     */       case 16:
/* 190 */         return b(number);
/*     */ 
/*     */       
/*     */       case 17:
/* 194 */         return a(number, "　あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもやゆよらりるれろわをん");
/*     */ 
/*     */       
/*     */       case 18:
/* 198 */         return a(number, "　アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン");
/*     */ 
/*     */       
/*     */       case 19:
/* 202 */         return a(number, "　いろはにほへとちりぬるをわかよたれそつねならむうゐのおくやまけふこえてあさきゆめみしゑひもせすん");
/*     */ 
/*     */       
/*     */       case 20:
/* 206 */         return a(number, "　イロハニホヘトチリヌルヲワカヨタレソツネナラムウヰノオクヤマケフコエテアサキユメミシヱヒモセスン");
/*     */ 
/*     */       
/*     */       case 21:
/* 210 */         ch = String.valueOf(number).toCharArray();
/* 211 */         for (i = 0; i < ch.length; i++) {
/* 212 */           ch[i] = (char)(ch[i] + 65248);
/*     */         }
/* 214 */         return new String(ch);
/*     */ 
/*     */       
/*     */       case 22:
/* 218 */         ch = String.valueOf(number).toCharArray();
/* 219 */         for (i = 0; i < ch.length; i++) {
/* 220 */           switch (ch[i]) {
/*     */             case '0':
/* 222 */               ch[i] = '〇';
/*     */               break;
/*     */             case '1':
/* 225 */               ch[i] = '一';
/*     */               break;
/*     */             case '2':
/* 228 */               ch[i] = '二';
/*     */               break;
/*     */             case '3':
/* 231 */               ch[i] = '三';
/*     */               break;
/*     */             case '4':
/* 234 */               ch[i] = '四';
/*     */               break;
/*     */             case '5':
/* 237 */               ch[i] = '五';
/*     */               break;
/*     */             case '6':
/* 240 */               ch[i] = '六';
/*     */               break;
/*     */             case '7':
/* 243 */               ch[i] = '七';
/*     */               break;
/*     */             case '8':
/* 246 */               ch[i] = '八';
/*     */               break;
/*     */             case '9':
/* 249 */               ch[i] = '九';
/*     */               break;
/*     */           } 
/*     */         } 
/* 253 */         return new String(ch);
/*     */     } 
/*     */     
/* 256 */     throw new IllegalArgumentException();
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
/*     */   private static String a(int number, String chars) {
/* 275 */     if (number >= 0 && number < chars.length()) {
/* 276 */       return String.valueOf(chars.charAt(number));
/*     */     }
/* 278 */     return String.valueOf(chars.charAt(0));
/*     */   }
/*     */   
/* 281 */   private static final String[][] h = new String[][] { { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" }, { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" }, { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" }, { "", "M", "MM", "MMM" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String a(int number) {
/* 292 */     if (number > 3999)
/* 293 */       return "overflow"; 
/* 294 */     if (number < 1) {
/* 295 */       return "underflow";
/*     */     }
/*     */     
/* 298 */     String numstr = String.valueOf(number);
/* 299 */     StringBuffer result = new StringBuffer();
/*     */     
/* 301 */     for (int i = 0; i < numstr.length(); i++) {
/* 302 */       result.append(h[numstr.length() - i - 1][Integer.parseInt(String.valueOf(numstr.charAt(i)))]);
/*     */     }
/*     */     
/* 305 */     return result.toString();
/*     */   }
/*     */   
/* 308 */   private static final String[][] i = new String[][] { { "", "一", "二", "三", "四", "五", "六", "七", "八", "九" }, { "", "十", "二十", "三十", "四十", "五十", "六十", "七十", "八十", "九十" }, { "", "百", "二百", "三百", "四百", "五百", "六百", "七百", "八百", "九百" }, { "", "千", "二千", "三千", "四千", "五千", "六千", "七千", "八千", "九千" }, { "", "一万", "二万", "三万", "四万", "五万", "六万", "七万", "八万", "九万" }, { "", "十", "二十", "三十", "四十", "五十", "六十", "七十", "八十", "九十" }, { "", "百", "二百", "三百", "四百", "五百", "六百", "七百", "八百", "九百" }, { "", "一千", "二千", "三千", "四千", "五千", "六千", "七千", "八千", "九千" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static String b(int number) {
/* 324 */     if (number > 99999999)
/* 325 */       return "overflow"; 
/* 326 */     if (number < 1) {
/* 327 */       return "零";
/*     */     }
/*     */     
/* 330 */     String numstr = String.valueOf(number);
/* 331 */     StringBuffer result = new StringBuffer();
/*     */     
/* 333 */     for (int i = 0; i < numstr.length(); i++) {
/* 334 */       result.append(e.i[numstr.length() - i - 1][Integer.parseInt(String.valueOf(numstr.charAt(i)))]);
/*     */     }
/*     */     
/* 337 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(short listStyleType) {
/* 347 */     switch (listStyleType) {
/*     */       case 0:
/*     */       case 1:
/*     */       case 2:
/*     */       case 3:
/* 352 */         return null;
/*     */ 
/*     */       
/*     */       case 4:
/*     */       case 5:
/*     */       case 6:
/*     */       case 7:
/*     */       case 8:
/*     */       case 9:
/*     */       case 10:
/*     */       case 11:
/*     */       case 12:
/*     */       case 13:
/*     */       case 14:
/*     */       case 15:
/* 367 */         return ".";
/*     */ 
/*     */       
/*     */       case 21:
/* 371 */         return "．";
/*     */ 
/*     */       
/*     */       case 16:
/*     */       case 17:
/*     */       case 18:
/*     */       case 19:
/*     */       case 20:
/*     */       case 22:
/* 380 */         return "、";
/*     */     } 
/*     */ 
/*     */     
/* 384 */     throw new IllegalArgumentException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/e/e.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */