/*     */ package com.a.a.i;
/*     */ 
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.regex.Pattern;
/*     */ 
/*     */ public class f
/*     */ {
/*  10 */   static final String[] a = new String[] { 
/*  11 */       "[Aa\\uFF21\\uFF41]", 
/*  12 */       "[Bb\\uFF22\\uFF42]", 
/*  13 */       "[Cc\\uFF23\\uFF43]", 
/*  14 */       "[DdＤｄ]", 
/*  15 */       "[EeＥｅ]", 
/*  16 */       "[FfＦｆ]", 
/*  17 */       "[GgＧｇ]", 
/*  18 */       "[HhＨｈ]", 
/*  19 */       "[IiＩｉ]", 
/*  20 */       "[JjＪｊ]", 
/*  21 */       "[KkＫｋ]", 
/*  22 */       "[LlＬｌ]", 
/*  23 */       "[MmＭｍ]", 
/*  24 */       "[NnＮｎ]", 
/*  25 */       "[OoＯｏ]", 
/*  26 */       "[PpＰｐ]", 
/*  27 */       "[QqＱｑ]", 
/*  28 */       "[RrＲｒ]", 
/*  29 */       "[SsＳｓ]", 
/*  30 */       "[TtＴｔ]", 
/*  31 */       "[UuＵｕ]", 
/*  32 */       "[VvＶｖ]", 
/*  33 */       "[WwＷｗ]", 
/*  34 */       "[XxＸｘ]", 
/*  35 */       "[YyＹｙ]", 
/*  36 */       "[ZzＺｚ]", 
/*  37 */       "[あぁアァｱ]", 
/*  38 */       "[いぃイィｲゐヰ]", 
/*  39 */       "[うぅウゥｳ]", 
/*  40 */       "[えぇエェｴゑヱ]", 
/*  41 */       "[おぉオォｵ]", 
/*  42 */       "[かヶカヵｶがガ]", 
/*  43 */       "[きキｷぎギ]", 
/*  44 */       "[くクｸぐグ]", 
/*  45 */       "[けケｹげゲ]", 
/*  46 */       "[こコｺごゴ]", 
/*  47 */       "[さサｻざザ]", 
/*  48 */       "[しシｼじジ]", 
/*  49 */       "[すスｽずズ]", 
/*  50 */       "[せセｾぜゼ]", 
/*  51 */       "[そソｿぞゾ]", 
/*  52 */       "[たタﾀだダ]", 
/*  53 */       "[ちチﾁぢヂ]", 
/*  54 */       "[つツﾂづヅ]", 
/*  55 */       "[てテﾃでデ]", 
/*  56 */       "[とトﾄどド]", 
/*  57 */       "[なナﾅ]", 
/*  58 */       "[にニﾆ]", 
/*  59 */       "[ぬヌﾇ]", 
/*  60 */       "[ねネﾈ]", 
/*  61 */       "[のノﾉ]", 
/*  62 */       "[はハﾊばバぱ]", 
/*  63 */       "[ひヒﾋびビぴ]", 
/*  64 */       "[ふフﾌぶブぷ]", 
/*  65 */       "[へヘﾍべべぺ]", 
/*  66 */       "[ほホﾎぼボぽ]", 
/*  67 */       "[まマﾏ]", 
/*  68 */       "[みミﾐ]", 
/*  69 */       "[むムﾑ]", 
/*  70 */       "[めメﾒ]", 
/*  71 */       "[もモﾓ]", 
/*  72 */       "[やゃヤャﾔ]", 
/*  73 */       "[ゆゅユュﾕ]", 
/*  74 */       "[よょヨョﾖ]", 
/*  75 */       "[らラﾗ]", 
/*  76 */       "[りリﾘ]", 
/*  77 */       "[るルﾙ]", 
/*  78 */       "[れレﾚ]", 
/*  79 */       "[ろロﾛ]", 
/*  80 */       "[わゎワヮﾜ]", 
/*  81 */       "[をヲｦ]", 
/*  82 */       "[んンﾝ]", 
/*  83 */       "[\\!！]", 
/*  84 */       "[\"”“″]", 
/*  85 */       "[#＃♯]", 
/*  86 */       "[$＄]", 
/*  87 */       "[%％]", 
/*  88 */       "[&＆]", 
/*  89 */       "[*＊]", 
/*  90 */       "[+＋]", 
/*  91 */       "[\\-ー－―‐]", 
/*  92 */       "[=＝]", 
/*  93 */       "[?？]", 
/*  94 */       "[_＿]", 
/*  95 */       "['’‘]", 
/*  96 */       "[\\(（\\[［「【『{｛〔]", 
/*  97 */       "[\\)）\\]］」】』]}｝〕]", 
/*  98 */       "[〈《<＜≪≦く]", 
/*  99 */       "[〉》>＞≫≧]", 
/* 100 */       "[\\^＾]", 
/* 101 */       "[\\\\￥]", 
/* 102 */       "[\\|｜]", 
/* 103 */       "[@＠]", 
/* 104 */       "[\\~～]", 
/* 105 */       "[;；:：]", 
/* 106 */       "[.．,，、。]" };
/*     */ 
/*     */   
/* 109 */   static Map<Character, String> b = Collections.synchronizedMap(new HashMap<Character, String>());
/*     */   
/*     */   static class a implements CharSequence {
/*     */     final char a;
/*     */     
/*     */     a(char c) {
/* 115 */       this.a = c;
/*     */     }
/*     */ 
/*     */     
/*     */     public int length() {
/* 120 */       return 1;
/*     */     }
/*     */ 
/*     */     
/*     */     public char charAt(int index) {
/* 125 */       if (index != 0) throw new IndexOutOfBoundsException(); 
/* 126 */       return this.a;
/*     */     }
/*     */ 
/*     */     
/*     */     public CharSequence subSequence(int start, int end) {
/* 131 */       if (start != 0 && end != 1) throw new IndexOutOfBoundsException(); 
/* 132 */       return this;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static String a(char c) {
/* 138 */     String pattern = b.get(Character.valueOf(c));
/* 139 */     if (pattern == null) {
/* 140 */       CharSequence cs = new a(c);
/* 141 */       for (int i = 0, m = a.length; i < m; i++) {
/* 142 */         if (Pattern.matches(a[i], cs)) {
/* 143 */           pattern = a[i];
/*     */           break;
/*     */         } 
/*     */       } 
/* 147 */       if (pattern == null) {
/* 148 */         pattern = String.valueOf(c);
/*     */       }
/* 150 */       b.put(Character.valueOf(c), pattern);
/*     */     } 
/* 152 */     return pattern;
/*     */   }
/*     */   
/*     */   public static String a(String text) {
/* 156 */     StringBuilder sb = new StringBuilder();
/*     */     
/* 158 */     for (int i = 0, m = text.length(); i < m; i++) {
/* 159 */       char c = text.charAt(i);
/* 160 */       if (!Character.isWhitespace(c) && c != '　') {
/*     */         
/* 162 */         if (sb.length() > 0) {
/* 163 */           sb.append("[\\s\\r\\n\\u3000\\W]*");
/*     */         }
/* 165 */         sb.append(a(c));
/*     */       } 
/*     */     } 
/* 168 */     return "(?imu)" + sb.toString();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/com/a/a/i/f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */