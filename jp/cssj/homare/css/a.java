/*     */ package jp.cssj.homare.css;
/*     */ 
/*     */ import java.util.Locale;
/*     */ import org.xml.sax.Attributes;
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
/*     */ public class a
/*     */ {
/*     */   private static final boolean K = false;
/*     */   public static final byte a = 1;
/*     */   public static final byte b = 2;
/*     */   public static final byte c = 3;
/*     */   public static final byte d = 4;
/*     */   public static final byte e = 5;
/*     */   public static final byte f = 6;
/*     */   public static final byte g = 7;
/*     */   public static final byte h = 101;
/*  31 */   public static final a i = new a(new byte[] { 1, 3, 5 });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  36 */   public static final a j = new a(new byte[] { 2, 4 });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  41 */   public static final a k = new a(new byte[] { 3, 5 });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  46 */   public static final a l = new a(new byte[] { 1, 2, 5 });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  51 */   public static final a m = new a(new byte[] { 2, 5 });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  56 */   public static final a n = new a(new byte[] { 3, 4 });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  61 */   public static final a o = new a(new byte[] { 1 });
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final a p = new a((byte[])null);
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  71 */   public static final a q = new a("first-line");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  76 */   public static final a r = new a("first-letter");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  81 */   public static final a s = new a("before");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  86 */   public static final a t = new a("after");
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  91 */   public static final a u = new a((String)null);
/*  92 */   public static final a v = new a("ruby");
/*  93 */   public static final a w = new a("rb");
/*  94 */   public static final a x = new a("table");
/*  95 */   public static final a y = new a("tbody");
/*  96 */   public static final a z = new a("tr");
/*  97 */   public static final a A = new a("td");
/*     */ 
/*     */ 
/*     */   
/*     */   public final String B;
/*     */ 
/*     */ 
/*     */   
/*     */   public final String C;
/*     */ 
/*     */ 
/*     */   
/*     */   public final String D;
/*     */ 
/*     */ 
/*     */   
/*     */   public final String[] E;
/*     */ 
/*     */ 
/*     */   
/*     */   public final byte[] F;
/*     */ 
/*     */ 
/*     */   
/*     */   public final Locale G;
/*     */ 
/*     */ 
/*     */   
/*     */   public final Attributes H;
/*     */ 
/*     */ 
/*     */   
/*     */   public final a I;
/*     */ 
/*     */   
/*     */   public final int J;
/*     */ 
/*     */ 
/*     */   
/*     */   public a(String uri, String lName, String id, String[] styleClasses, byte[] pseudoClasses, Locale lang, Attributes atts, a precedingElement, int charOffset) {
/* 137 */     this.B = uri;
/* 138 */     this.C = lName;
/* 139 */     this.D = id;
/* 140 */     this.E = styleClasses;
/* 141 */     this.F = pseudoClasses;
/* 142 */     this.G = lang;
/* 143 */     this.H = atts;
/* 144 */     this.I = precedingElement;
/* 145 */     this.J = charOffset;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private a(String pseudoElement) {
/* 154 */     this(null, pseudoElement, null, null, null, null, null, null, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private a(byte[] pseudoClasses) {
/* 163 */     this(null, null, null, null, pseudoClasses, null, null, null, -1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(String styleClass) {
/* 173 */     if (this.E != null) {
/* 174 */       for (int i = 0; i < this.E.length; i++) {
/* 175 */         if (styleClass.equalsIgnoreCase(this.E[i])) {
/* 176 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 180 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(byte pseudoClass) {
/* 190 */     if (pseudoClass == 0) {
/* 191 */       return false;
/*     */     }
/* 193 */     if (this.F != null) {
/* 194 */       for (int i = 0; i < this.F.length; i++) {
/* 195 */         if (pseudoClass == this.F[i]) {
/* 196 */           return true;
/*     */         }
/*     */       } 
/*     */     }
/* 200 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a() {
/* 204 */     return (this.H == null && this.C != null);
/*     */   }
/*     */   
/*     */   public String toString() {
/* 208 */     StringBuffer buff = new StringBuffer();
/* 209 */     buff.append(super.toString());
/* 210 */     buff.append("@uri='");
/* 211 */     buff.append(this.B);
/* 212 */     buff.append("',lName='");
/* 213 */     buff.append(this.C);
/* 214 */     buff.append("'");
/* 215 */     if (this.D != null) {
/* 216 */       buff.append(",id='");
/* 217 */       buff.append(this.D);
/* 218 */       buff.append("'");
/*     */     } 
/* 220 */     if (this.F != null) {
/* 221 */       buff.append(",pseudoClasses='");
/* 222 */       for (int i = 0; i < this.F.length; i++) {
/* 223 */         if (i > 0) {
/* 224 */           buff.append(",");
/*     */         }
/* 226 */         buff.append(this.F[i]);
/*     */       } 
/* 228 */       buff.append("'");
/*     */     } 
/* 230 */     if (this.E != null) {
/* 231 */       buff.append(",styleClasses='");
/* 232 */       for (int i = 0; i < this.E.length; i++) {
/* 233 */         if (i > 0) {
/* 234 */           buff.append(",");
/*     */         }
/* 236 */         buff.append(this.E[i]);
/*     */       } 
/* 238 */       buff.append("'");
/*     */     } 
/* 240 */     if (this.G != null) {
/* 241 */       buff.append(",lang='");
/* 242 */       buff.append(this.G);
/* 243 */       buff.append("'");
/*     */     } 
/* 245 */     if (this.I != null) {
/* 246 */       buff.append(",precedingElement='");
/* 247 */       buff.append(this.I.b());
/* 248 */       buff.append("'");
/*     */     } 
/* 250 */     if (this.H != null && this.H.getLength() > 0) {
/* 251 */       buff.append("[");
/* 252 */       for (int i = 0; i < this.H.getLength(); i++) {
/* 253 */         if (i > 0) {
/* 254 */           buff.append(",");
/*     */         }
/* 256 */         buff.append(this.H.getLocalName(i));
/* 257 */         buff.append('=');
/* 258 */         buff.append(this.H.getValue(i));
/*     */       } 
/* 260 */       buff.append("]");
/*     */     } 
/* 262 */     return buff.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String b() {
/* 272 */     return this.C;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/css/a.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */