/*     */ package jp.cssj.sakae.c.d.a.a;
/*     */ 
/*     */ import jp.cssj.sakae.c.d.a.a;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class c
/*     */   implements a
/*     */ {
/*  12 */   private static final b a = new a("#$%&*+-/0123456789=@ABCDEFGHIJKLMNOPQRSTUVWXYZ\\^_abcdefghijklmnopqrstuvwxyz|~");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  18 */   private static final b b = new b() {
/*     */       public boolean a(char c) {
/*  20 */         if (c > 'ÿ') {
/*  21 */           return false;
/*     */         }
/*  23 */         if (c > '') {
/*  24 */           return true;
/*     */         }
/*  26 */         return c.a().a(c);
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String c = "～〜ヽヾゝゞ ー ァ ィ ゥ ェ ォ ッ ャ ュ ョ ヮ ヵ ヶ ぁ ぃ ぅ ぇ ぉ っ ゃ ゅ ょ ゎ ゕ ゖ ㇰ ㇱ ㇳ ㇲ ㇳ ㇴ ㇵ ㇶ ㇷ ㇸ ㇹ ㇺ ㇻ ㇼ ㇽ ㇾ ㇿ 々 〻　・”";
/*     */ 
/*     */   
/*     */   private static final String d = "﹅﹆";
/*     */ 
/*     */ 
/*     */   
/*     */   protected b a(char c1) {
/*  40 */     if ("～〜ヽヾゝゞ ー ァ ィ ゥ ェ ォ ッ ャ ュ ョ ヮ ヵ ヶ ぁ ぃ ぅ ぇ ぉ っ ゃ ゅ ょ ゎ ゕ ゖ ㇰ ㇱ ㇳ ㇲ ㇳ ㇴ ㇵ ㇶ ㇷ ㇸ ㇹ ㇺ ㇻ ㇼ ㇽ ㇾ ㇿ 々 〻　・”".indexOf(c1) != -1) {
/*  41 */       return b.a;
/*     */     }
/*  43 */     if ("﹅﹆".indexOf(c1) != -1) {
/*  44 */       return b.b;
/*     */     }
/*  46 */     int type = Character.getType(c1);
/*     */     
/*  48 */     switch (type) {
/*     */       
/*     */       case 4:
/*     */       case 22:
/*     */       case 24:
/*     */       case 27:
/*  54 */         return b.a;
/*     */     } 
/*  56 */     return b.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected b b(char c1) {
/*  63 */     int type = Character.getType(c1);
/*     */     
/*  65 */     if (c1 <= 'ÿ' || b.a(c1)) {
/*     */       
/*  67 */       switch (type) {
/*     */         
/*     */         case 21:
/*  70 */           return b.a;
/*     */ 
/*     */         
/*     */         case 22:
/*  74 */           return b.b;
/*     */       } 
/*     */       
/*  77 */       if (c1 == ' ' || c1 == '-' || c1 == '!' || c1 == '?')
/*     */       {
/*  79 */         return b.b;
/*     */       }
/*     */       
/*  82 */       return b;
/*     */     } 
/*     */ 
/*     */     
/*  86 */     switch (type) {
/*     */       
/*     */       case 21:
/*  89 */         return b.a;
/*     */     } 
/*     */     
/*  92 */     if (c1 == '─' || c1 == '“') {
/*  93 */       return b.a;
/*     */     }
/*     */     
/*  96 */     return b.b;
/*     */   }
/*     */   
/*     */   public boolean a(char c1, char c2) {
/* 100 */     return (b(c1).a(c2) || a(c2).a(c1));
/*     */   }
/*     */   
/*     */   public boolean b(char c1, char c2) {
/* 104 */     if (Character.isWhitespace(c1) || Character.isWhitespace(c2)) {
/* 105 */       return (c1 != '　');
/*     */     }
/* 107 */     if (c(c1) || c(c2)) {
/* 108 */       return true;
/*     */     }
/* 110 */     return false;
/*     */   }
/*     */   
/*     */   protected boolean c(char c1) {
/* 114 */     Character.UnicodeBlock unicodeBlock = Character.UnicodeBlock.of(c1);
/* 115 */     if (unicodeBlock == null) {
/* 116 */       return true;
/*     */     }
/* 118 */     if (unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY) {
/* 119 */       return true;
/*     */     }
/* 121 */     if (unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_FORMS) {
/* 122 */       return true;
/*     */     }
/* 124 */     if (unicodeBlock == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS) {
/* 125 */       return true;
/*     */     }
/* 127 */     if (unicodeBlock == Character.UnicodeBlock.CJK_RADICALS_SUPPLEMENT) {
/* 128 */       return true;
/*     */     }
/* 130 */     if (unicodeBlock == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION) {
/* 131 */       return true;
/*     */     }
/* 133 */     if (unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS) {
/* 134 */       return true;
/*     */     }
/* 136 */     if (unicodeBlock == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A) {
/* 137 */       return true;
/*     */     }
/* 139 */     if (unicodeBlock == Character.UnicodeBlock.HIRAGANA) {
/* 140 */       return true;
/*     */     }
/* 142 */     if (unicodeBlock == Character.UnicodeBlock.KATAKANA) {
/* 143 */       return true;
/*     */     }
/* 145 */     if (unicodeBlock == Character.UnicodeBlock.HANGUL_SYLLABLES) {
/* 146 */       return true;
/*     */     }
/* 148 */     if (unicodeBlock == Character.UnicodeBlock.HANGUL_JAMO) {
/* 149 */       return true;
/*     */     }
/* 151 */     if (unicodeBlock == Character.UnicodeBlock.HANGUL_COMPATIBILITY_JAMO) {
/* 152 */       return true;
/*     */     }
/* 154 */     return false;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/sakae/c/d/a/a/c.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */