/*     */ package jp.cssj.homare.impl.a.c.a;
/*     */ 
/*     */ import java.awt.geom.AffineTransform;
/*     */ import java.net.URI;
/*     */ import jp.cssj.homare.css.c;
/*     */ import jp.cssj.homare.css.c.b;
/*     */ import jp.cssj.homare.css.c.j;
/*     */ import jp.cssj.homare.css.c.l;
/*     */ import jp.cssj.homare.css.e.l;
/*     */ import jp.cssj.homare.css.f.H;
/*     */ import jp.cssj.homare.css.f.a;
/*     */ import jp.cssj.homare.css.f.a.k;
/*     */ import jp.cssj.homare.css.f.ad;
/*     */ import jp.cssj.homare.ua.m;
/*     */ import jp.cssj.sakae.sac.css.LexicalUnit;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class G
/*     */   extends b
/*     */ {
/*  24 */   public static final j a = (j)new G();
/*     */   
/*     */   public static AffineTransform c(c style) {
/*  27 */     k value = (k)style.a(a);
/*  28 */     return value.b();
/*     */   }
/*     */   
/*     */   protected G() {
/*  32 */     super("-cssj-transform");
/*     */   }
/*     */   
/*     */   public ad b(c style) {
/*  36 */     return (ad)H.a;
/*     */   }
/*     */   
/*     */   public boolean c() {
/*  40 */     return false;
/*     */   }
/*     */   
/*     */   public ad a(ad value, c style) {
/*  44 */     if (value == H.a) {
/*  45 */       return (ad)k.a;
/*     */     }
/*  47 */     return value;
/*     */   }
/*     */   
/*     */   public ad a(LexicalUnit lu, m ua, URI uri) throws l {
/*  51 */     AffineTransform at = null; while (true) {
/*     */       String func;
/*  53 */       switch (lu.getLexicalUnitType()) {
/*     */         case 35:
/*  55 */           if (l.b(lu)) {
/*     */             break;
/*     */           }
/*  58 */           throw new l();
/*     */         case 41:
/*  60 */           func = lu.getFunctionName();
/*  61 */           if (func.equalsIgnoreCase("matrix")) {
/*  62 */             LexicalUnit params = lu.getParameters();
/*  63 */             double a = b(params);
/*  64 */             params = params.getNextLexicalUnit();
/*  65 */             double d1 = b(params);
/*  66 */             params = params.getNextLexicalUnit();
/*  67 */             double c = b(params);
/*  68 */             params = params.getNextLexicalUnit();
/*  69 */             double d = b(params);
/*  70 */             params = params.getNextLexicalUnit();
/*  71 */             double tx = a(ua, params);
/*  72 */             params = params.getNextLexicalUnit();
/*  73 */             double ty = a(ua, params);
/*  74 */             AffineTransform t = new AffineTransform(a, d1, c, d, tx, ty);
/*  75 */             if (at == null) {
/*  76 */               at = t; break;
/*     */             } 
/*  78 */             at.concatenate(t);
/*     */             break;
/*     */           } 
/*  81 */           if (func.equalsIgnoreCase("rotate")) {
/*  82 */             LexicalUnit params = lu.getParameters();
/*  83 */             double angle = a(params);
/*  84 */             if (at == null) {
/*  85 */               at = AffineTransform.getRotateInstance(angle); break;
/*     */             } 
/*  87 */             at.rotate(angle); break;
/*     */           } 
/*  89 */           if (func.equalsIgnoreCase("scale")) {
/*  90 */             double sy; LexicalUnit params = lu.getParameters();
/*  91 */             double sx = b(params);
/*  92 */             params = params.getNextLexicalUnit();
/*     */             
/*  94 */             if (params == null) {
/*  95 */               sy = sx;
/*     */             } else {
/*  97 */               sy = b(params);
/*     */             } 
/*  99 */             if (at == null) {
/* 100 */               at = AffineTransform.getScaleInstance(sx, sy); break;
/*     */             } 
/* 102 */             at.scale(sx, sy); break;
/*     */           } 
/* 104 */           if (func.equalsIgnoreCase("scaleX")) {
/* 105 */             LexicalUnit params = lu.getParameters();
/* 106 */             double sx = b(params);
/* 107 */             if (at == null) {
/* 108 */               at = AffineTransform.getScaleInstance(sx, 1.0D); break;
/*     */             } 
/* 110 */             at.scale(sx, 1.0D); break;
/*     */           } 
/* 112 */           if (func.equalsIgnoreCase("scaleY")) {
/* 113 */             LexicalUnit params = lu.getParameters();
/* 114 */             double sy = b(params);
/* 115 */             if (at == null) {
/* 116 */               at = AffineTransform.getScaleInstance(1.0D, sy); break;
/*     */             } 
/* 118 */             at.scale(1.0D, sy); break;
/*     */           } 
/* 120 */           if (func.equalsIgnoreCase("skew")) {
/* 121 */             double shy; LexicalUnit params = lu.getParameters();
/* 122 */             double shx = a(params);
/* 123 */             params = params.getNextLexicalUnit();
/*     */             
/* 125 */             if (params == null) {
/* 126 */               shy = 0.0D;
/*     */             } else {
/* 128 */               shy = a(params);
/*     */             } 
/* 130 */             if (at == null) {
/* 131 */               at = AffineTransform.getShearInstance(Math.tan(shx), Math.tan(shy)); break;
/*     */             } 
/* 133 */             at.shear(Math.tan(shx), Math.tan(shy)); break;
/*     */           } 
/* 135 */           if (func.equalsIgnoreCase("skewX")) {
/* 136 */             LexicalUnit params = lu.getParameters();
/* 137 */             double shx = a(params);
/* 138 */             if (at == null) {
/* 139 */               at = AffineTransform.getShearInstance(Math.tan(shx), 0.0D); break;
/*     */             } 
/* 141 */             at.shear(Math.tan(shx), 0.0D); break;
/*     */           } 
/* 143 */           if (func.equalsIgnoreCase("skewY")) {
/* 144 */             LexicalUnit params = lu.getParameters();
/* 145 */             double shy = a(params);
/* 146 */             if (at == null) {
/* 147 */               at = AffineTransform.getShearInstance(0.0D, Math.tan(shy)); break;
/*     */             } 
/* 149 */             at.shear(0.0D, Math.tan(shy)); break;
/*     */           } 
/* 151 */           if (func.equalsIgnoreCase("translate")) {
/* 152 */             double ty; LexicalUnit params = lu.getParameters();
/* 153 */             double tx = a(ua, params);
/* 154 */             params = params.getNextLexicalUnit();
/*     */             
/* 156 */             if (params == null) {
/* 157 */               ty = 0.0D;
/*     */             } else {
/* 159 */               ty = a(ua, params);
/*     */             } 
/* 161 */             if (at == null) {
/* 162 */               at = AffineTransform.getTranslateInstance(tx, ty); break;
/*     */             } 
/* 164 */             at.translate(tx, ty); break;
/*     */           } 
/* 166 */           if (func.equalsIgnoreCase("translateX")) {
/* 167 */             LexicalUnit params = lu.getParameters();
/* 168 */             double tx = a(ua, params);
/* 169 */             if (at == null) {
/* 170 */               at = AffineTransform.getTranslateInstance(tx, 0.0D); break;
/*     */             } 
/* 172 */             at.translate(tx, 0.0D); break;
/*     */           } 
/* 174 */           if (func.equalsIgnoreCase("translateY")) {
/* 175 */             LexicalUnit params = lu.getParameters();
/* 176 */             double ty = a(ua, params);
/* 177 */             if (at == null) {
/* 178 */               at = AffineTransform.getTranslateInstance(0.0D, ty); break;
/*     */             } 
/* 180 */             at.translate(0.0D, ty);
/*     */             break;
/*     */           } 
/* 183 */           throw new l();
/*     */ 
/*     */         
/*     */         default:
/* 187 */           throw new l();
/*     */       } 
/* 189 */       lu = lu.getNextLexicalUnit();
/*     */       
/* 191 */       if (lu == null) {
/* 192 */         if (at == null) {
/* 193 */           return (ad)H.a;
/*     */         }
/* 195 */         return (ad)k.a(at);
/*     */       } 
/*     */     }  } private double a(LexicalUnit lu) throws l {
/*     */     double angle;
/* 199 */     if (lu == null) {
/* 200 */       throw new l();
/*     */     }
/* 202 */     if (lu.getLexicalUnitType() == 0) {
/* 203 */       lu = lu.getNextLexicalUnit();
/* 204 */       if (lu == null) {
/* 205 */         throw new l();
/*     */       }
/*     */     } 
/*     */     
/* 209 */     if (lu.getLexicalUnitType() == 28) {
/* 210 */       angle = lu.getFloatValue() * Math.PI / 180.0D;
/*     */     } else {
/* 212 */       angle = b(lu);
/*     */     } 
/* 214 */     return angle;
/*     */   }
/*     */   
/*     */   private float b(LexicalUnit lu) throws l {
/* 218 */     if (lu == null) {
/* 219 */       throw new l();
/*     */     }
/* 221 */     if (lu.getLexicalUnitType() == 0) {
/* 222 */       lu = lu.getNextLexicalUnit();
/* 223 */       if (lu == null) {
/* 224 */         throw new l();
/*     */       }
/*     */     } 
/* 227 */     switch (lu.getLexicalUnitType()) {
/*     */       case 14:
/* 229 */         return lu.getFloatValue();
/*     */       case 13:
/* 231 */         return lu.getIntegerValue();
/*     */     } 
/* 233 */     throw new l();
/*     */   }
/*     */ 
/*     */   
/*     */   private double a(m ua, LexicalUnit lu) throws l {
/* 238 */     if (lu == null) {
/* 239 */       throw new l();
/*     */     }
/* 241 */     if (lu.getLexicalUnitType() == 0) {
/* 242 */       lu = lu.getNextLexicalUnit();
/* 243 */       if (lu == null) {
/* 244 */         throw new l();
/*     */       }
/*     */     } 
/* 247 */     a length = l.b(ua, lu);
/* 248 */     if (length == null)
/* 249 */     { switch (lu.getLexicalUnitType())
/*     */       { case 14:
/* 251 */           length = a.a(ua, lu.getFloatValue(), (short)17);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 260 */           return length.c();case 13: length = a.a(ua, lu.getIntegerValue(), (short)17); return length.c(); }  throw new l(); }  return length.c();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/jp/cssj/homare/impl/a/c/a/G.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */