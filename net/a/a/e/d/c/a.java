/*     */ package net.a.a.e.d.c;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
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
/*     */ public final class a
/*     */ {
/*  34 */   private static final Map<Character, String> a = new HashMap<Character, String>();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  39 */   private static final Map<Character, String> b = new HashMap<Character, String>(200);
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
/*     */   private static String a(String paramString, Map<Character, String> paramMap) {
/*  53 */     StringBuffer stringBuffer = new StringBuffer();
/*  54 */     for (byte b = 0; b < paramString.length(); b++) {
/*  55 */       char c = paramString.charAt(b);
/*  56 */       String str = paramMap.get(Character.valueOf(c));
/*  57 */       if (str == null) {
/*  58 */         stringBuffer.append(c);
/*     */       } else {
/*  60 */         stringBuffer.append(str);
/*     */       } 
/*     */     } 
/*  63 */     return stringBuffer.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String a(String paramString) {
/*  72 */     return a(paramString, b);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static String b(String paramString) {
/*  82 */     return 
/*  83 */       a(paramString, a);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  89 */     a.put(Character.valueOf('⁡'), "");
/*  90 */     a.put(Character.valueOf('​'), "");
/*  91 */     a.put(Character.valueOf('⁢'), "");
/*  92 */     a.put(Character.valueOf('ⅈ'), "");
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
/* 107 */     a.put(Character.valueOf('̲'), "¯");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     b.put(Character.valueOf(''), "‱");
/* 117 */     b.put(Character.valueOf(''), "");
/* 118 */     b.put(Character.valueOf(''), "+");
/* 119 */     b.put(Character.valueOf(''), "<");
/* 120 */     b.put(Character.valueOf(''), ">");
/* 121 */     b.put(Character.valueOf(''), "");
/* 122 */     b.put(Character.valueOf(''), "");
/* 123 */     b.put(Character.valueOf(''), "∈");
/* 124 */     b.put(Character.valueOf(''), "ƒ");
/* 125 */     b.put(Character.valueOf(''), "…");
/* 126 */     b.put(Character.valueOf(''), "→");
/* 127 */     b.put(Character.valueOf(''), "̂");
/* 128 */     b.put(Character.valueOf(''), "̌");
/* 129 */     b.put(Character.valueOf(''), "̆");
/* 130 */     b.put(Character.valueOf(''), "́");
/* 131 */     b.put(Character.valueOf(''), "̀");
/* 132 */     b.put(Character.valueOf(''), "̃");
/* 133 */     b.put(Character.valueOf(''), "̄");
/*     */     
/* 135 */     b.put(Character.valueOf(''), "→");
/* 136 */     b.put(Character.valueOf(''), "˙");
/* 137 */     b.put(Character.valueOf(''), "̈");
/* 138 */     b.put(Character.valueOf(''), "⃛");
/* 139 */     b.put(Character.valueOf(''), "̊");
/* 140 */     b.put(Character.valueOf(''), "(");
/* 141 */     b.put(Character.valueOf(''), ")");
/* 142 */     b.put(Character.valueOf(''), "〚");
/* 143 */     b.put(Character.valueOf(''), "〛");
/* 144 */     b.put(Character.valueOf(''), "⍳");
/* 145 */     b.put(Character.valueOf(''), "/");
/* 146 */     b.put(Character.valueOf(''), "\\");
/* 147 */     b.put(Character.valueOf(''), "❏");
/* 148 */     b.put(Character.valueOf(''), "Γ");
/* 149 */     b.put(Character.valueOf(''), "Δ");
/* 150 */     b.put(Character.valueOf(''), "Θ");
/* 151 */     b.put(Character.valueOf(''), "Λ");
/* 152 */     b.put(Character.valueOf(''), "Ξ");
/* 153 */     b.put(Character.valueOf(''), "Π");
/* 154 */     b.put(Character.valueOf(''), "Σ");
/* 155 */     b.put(Character.valueOf(''), "Υ");
/* 156 */     b.put(Character.valueOf(''), "Φ");
/* 157 */     b.put(Character.valueOf(''), "Ψ");
/* 158 */     b.put(Character.valueOf(''), "Ω");
/* 159 */     b.put(Character.valueOf(''), "α");
/* 160 */     b.put(Character.valueOf(''), "β");
/* 161 */     b.put(Character.valueOf(''), "γ");
/* 162 */     b.put(Character.valueOf(''), "δ");
/* 163 */     b.put(Character.valueOf(''), "ε");
/* 164 */     b.put(Character.valueOf(''), "ζ");
/* 165 */     b.put(Character.valueOf(''), "η");
/* 166 */     b.put(Character.valueOf(''), "θ");
/* 167 */     b.put(Character.valueOf(''), "ι");
/* 168 */     b.put(Character.valueOf(''), "κ");
/* 169 */     b.put(Character.valueOf(''), "λ");
/* 170 */     b.put(Character.valueOf(''), "μ");
/* 171 */     b.put(Character.valueOf(''), "ν");
/* 172 */     b.put(Character.valueOf(''), "ξ");
/* 173 */     b.put(Character.valueOf(''), "ο");
/* 174 */     b.put(Character.valueOf(''), "π");
/* 175 */     b.put(Character.valueOf(''), "ρ");
/* 176 */     b.put(Character.valueOf(''), "σ");
/* 177 */     b.put(Character.valueOf(''), "τ");
/* 178 */     b.put(Character.valueOf(''), "υ");
/* 179 */     b.put(Character.valueOf(''), "φ");
/* 180 */     b.put(Character.valueOf(''), "χ");
/* 181 */     b.put(Character.valueOf(''), "ψ");
/* 182 */     b.put(Character.valueOf(''), "ω");
/* 183 */     b.put(Character.valueOf(''), "ε");
/* 184 */     b.put(Character.valueOf(''), "ϑ");
/* 185 */     b.put(Character.valueOf(''), "ϖ");
/* 186 */     b.put(Character.valueOf(''), "ϱ");
/* 187 */     b.put(Character.valueOf(''), "ϛ");
/* 188 */     b.put(Character.valueOf(''), "℘");
/* 189 */     b.put(Character.valueOf(''), "∂");
/* 190 */     b.put(Character.valueOf(''), "℩");
/* 191 */     b.put(Character.valueOf(''), "ℇ");
/* 192 */     b.put(Character.valueOf(''), "℧");
/* 193 */     b.put(Character.valueOf(''), "⊤");
/* 194 */     b.put(Character.valueOf(''), "ƛ");
/* 195 */     b.put(Character.valueOf(''), "←");
/* 196 */     b.put(Character.valueOf(''), "↑");
/* 197 */     b.put(Character.valueOf(''), "↓");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 203 */     b.put(Character.valueOf(''), new String(new int[] { 119964 }, 0, 1));
/*     */     
/* 205 */     b.put(Character.valueOf(''), new String(new int[] { 119966 }, 0, 1));
/*     */     
/* 207 */     b.put(Character.valueOf(''), new String(new int[] { 119967 }, 0, 1));
/*     */     
/* 209 */     b.put(Character.valueOf(''), new String(new int[] { 119970 }, 0, 1));
/*     */     
/* 211 */     b.put(Character.valueOf(''), new String(new int[] { 119973 }, 0, 1));
/*     */     
/* 213 */     b.put(Character.valueOf(''), new String(new int[] { 119974 }, 0, 1));
/*     */     
/* 215 */     b.put(Character.valueOf(''), new String(new int[] { 119977 }, 0, 1));
/*     */     
/* 217 */     b.put(Character.valueOf(''), new String(new int[] { 119978 }, 0, 1));
/*     */     
/* 219 */     b.put(Character.valueOf(''), new String(new int[] { 119979 }, 0, 1));
/*     */     
/* 221 */     b.put(Character.valueOf(''), new String(new int[] { 119980 }, 0, 1));
/*     */     
/* 223 */     b.put(Character.valueOf(''), new String(new int[] { 119982 }, 0, 1));
/*     */     
/* 225 */     b.put(Character.valueOf(''), new String(new int[] { 119983 }, 0, 1));
/*     */     
/* 227 */     b.put(Character.valueOf(''), new String(new int[] { 119984 }, 0, 1));
/*     */     
/* 229 */     b.put(Character.valueOf(''), new String(new int[] { 119985 }, 0, 1));
/*     */     
/* 231 */     b.put(Character.valueOf(''), new String(new int[] { 119986 }, 0, 1));
/*     */     
/* 233 */     b.put(Character.valueOf(''), new String(new int[] { 119987 }, 0, 1));
/*     */     
/* 235 */     b.put(Character.valueOf(''), new String(new int[] { 119988 }, 0, 1));
/*     */     
/* 237 */     b.put(Character.valueOf(''), new String(new int[] { 119989 }, 0, 1));
/*     */ 
/*     */ 
/*     */     
/* 241 */     b.put(Character.valueOf(''), new String(new int[] { 120068 }, 0, 1));
/*     */     
/* 243 */     b.put(Character.valueOf(''), new String(new int[] { 120069 }, 0, 1));
/*     */     
/* 245 */     b.put(Character.valueOf(''), new String(new int[] { 120071 }, 0, 1));
/*     */     
/* 247 */     b.put(Character.valueOf(''), new String(new int[] { 120072 }, 0, 1));
/*     */     
/* 249 */     b.put(Character.valueOf(''), new String(new int[] { 120073 }, 0, 1));
/*     */     
/* 251 */     b.put(Character.valueOf(''), new String(new int[] { 120074 }, 0, 1));
/*     */     
/* 253 */     b.put(Character.valueOf(''), new String(new int[] { 120077 }, 0, 1));
/*     */     
/* 255 */     b.put(Character.valueOf(''), new String(new int[] { 120078 }, 0, 1));
/*     */     
/* 257 */     b.put(Character.valueOf(''), new String(new int[] { 120079 }, 0, 1));
/*     */     
/* 259 */     b.put(Character.valueOf(''), new String(new int[] { 120080 }, 0, 1));
/*     */     
/* 261 */     b.put(Character.valueOf(''), new String(new int[] { 120081 }, 0, 1));
/*     */     
/* 263 */     b.put(Character.valueOf(''), new String(new int[] { 120082 }, 0, 1));
/*     */     
/* 265 */     b.put(Character.valueOf(''), new String(new int[] { 120083 }, 0, 1));
/*     */     
/* 267 */     b.put(Character.valueOf(''), new String(new int[] { 120084 }, 0, 1));
/*     */     
/* 269 */     b.put(Character.valueOf(''), new String(new int[] { 120086 }, 0, 1));
/*     */     
/* 271 */     b.put(Character.valueOf(''), new String(new int[] { 120087 }, 0, 1));
/*     */     
/* 273 */     b.put(Character.valueOf(''), new String(new int[] { 120088 }, 0, 1));
/*     */     
/* 275 */     b.put(Character.valueOf(''), new String(new int[] { 120089 }, 0, 1));
/*     */     
/* 277 */     b.put(Character.valueOf(''), new String(new int[] { 120090 }, 0, 1));
/*     */     
/* 279 */     b.put(Character.valueOf(''), new String(new int[] { 120091 }, 0, 1));
/*     */     
/* 281 */     b.put(Character.valueOf(''), new String(new int[] { 120092 }, 0, 1));
/*     */ 
/*     */ 
/*     */     
/* 285 */     b.put(Character.valueOf(''), new String(new int[] { 120120 }, 0, 1));
/*     */     
/* 287 */     b.put(Character.valueOf(''), new String(new int[] { 120121 }, 0, 1));
/*     */     
/* 289 */     b.put(Character.valueOf(''), new String(new int[] { 120123 }, 0, 1));
/*     */     
/* 291 */     b.put(Character.valueOf(''), new String(new int[] { 120124 }, 0, 1));
/*     */     
/* 293 */     b.put(Character.valueOf(''), new String(new int[] { 120125 }, 0, 1));
/*     */     
/* 295 */     b.put(Character.valueOf(''), new String(new int[] { 120126 }, 0, 1));
/*     */     
/* 297 */     b.put(Character.valueOf(''), new String(new int[] { 120128 }, 0, 1));
/*     */     
/* 299 */     b.put(Character.valueOf(''), new String(new int[] { 120129 }, 0, 1));
/*     */     
/* 301 */     b.put(Character.valueOf(''), new String(new int[] { 120130 }, 0, 1));
/*     */     
/* 303 */     b.put(Character.valueOf(''), new String(new int[] { 120131 }, 0, 1));
/*     */     
/* 305 */     b.put(Character.valueOf(''), new String(new int[] { 120132 }, 0, 1));
/*     */     
/* 307 */     b.put(Character.valueOf(''), new String(new int[] { 120134 }, 0, 1));
/*     */     
/* 309 */     b.put(Character.valueOf(''), new String(new int[] { 120138 }, 0, 1));
/*     */     
/* 311 */     b.put(Character.valueOf(''), new String(new int[] { 120139 }, 0, 1));
/*     */     
/* 313 */     b.put(Character.valueOf(''), new String(new int[] { 120140 }, 0, 1));
/*     */     
/* 315 */     b.put(Character.valueOf(''), new String(new int[] { 120141 }, 0, 1));
/*     */     
/* 317 */     b.put(Character.valueOf(''), new String(new int[] { 120142 }, 0, 1));
/*     */     
/* 319 */     b.put(Character.valueOf(''), new String(new int[] { 120143 }, 0, 1));
/*     */     
/* 321 */     b.put(Character.valueOf(''), new String(new int[] { 120144 }, 0, 1));
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/d/c/a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */