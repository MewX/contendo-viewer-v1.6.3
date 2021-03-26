/*     */ package net.a.a.e.c.b;
/*     */ 
/*     */ import net.a.a.c;
/*     */ import net.a.a.c.d;
/*     */ import net.a.a.e.c.a;
/*     */ import org.apache.batik.dom.AbstractDocument;
/*     */ import org.apache.commons.logging.Log;
/*     */ import org.apache.commons.logging.LogFactory;
/*     */ import org.w3c.dom.Node;
/*     */ import org.w3c.dom.mathml.MathMLStyleElement;
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
/*     */ public final class k
/*     */   extends a
/*     */   implements MathMLStyleElement
/*     */ {
/*     */   public static final String r = "scriptminsize";
/*     */   public static final String s = "scriptlevel";
/*     */   public static final String t = "scriptsizemultiplier";
/*     */   public static final String u = "displaystyle";
/*     */   public static final String v = "mstyle";
/*  61 */   private static final Log w = LogFactory.getLog(k.class);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static final long x = 1L;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public k(String paramString, AbstractDocument paramAbstractDocument) {
/*  74 */     super(paramString, paramAbstractDocument);
/*     */     
/*  76 */     a("displaystyle", "");
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected Node newNode() {
/*  82 */     return (Node)new k(this.nodeName, this.ownerDocument);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getScriptlevel() {
/*  89 */     return a("scriptlevel");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScriptlevel(String paramString) {
/*  97 */     setAttribute("scriptlevel", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getScriptminsize() {
/* 104 */     return a("scriptminsize");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setScriptminsize(String paramString) {
/* 112 */     setAttribute("scriptminsize", paramString);
/*     */   }
/*     */   
/*     */   private class a
/*     */     implements c {
/*     */     private final c b;
/*     */     
/*     */     protected a(k this$0, c param1c) {
/* 120 */       this.b = param1c;
/*     */     }
/*     */ 
/*     */     
/*     */     public Object a(d param1d) {
/* 125 */       Object object = this.a.b(this.b).a(param1d);
/* 126 */       if (d.a.equals(param1d)) {
/* 127 */         object = c(object);
/* 128 */       } else if (d.e.equals(param1d)) {
/* 129 */         object = b(object);
/* 130 */       } else if (d.c.equals(param1d)) {
/* 131 */         object = a(object);
/*     */       } 
/* 133 */       return object;
/*     */     }
/*     */     
/*     */     private Object a(Object param1Object) {
/* 137 */       String str = this.a.getScriptminsize();
/* 138 */       if (str != null && str.length() > 0) {
/* 139 */         return Float.valueOf(net.a.a.e.d.a.a.a(str, this.b, "pt"));
/*     */       }
/*     */       
/* 142 */       return param1Object;
/*     */     }
/*     */ 
/*     */     
/*     */     private Object b(Object param1Object) {
/* 147 */       Object object = param1Object;
/* 148 */       String str = this.a.getScriptlevel();
/* 149 */       if (str == null) {
/* 150 */         str = "";
/*     */       }
/* 152 */       str = str.trim();
/* 153 */       if (str.length() > 0) {
/* 154 */         char c1 = str.charAt(0);
/* 155 */         boolean bool = false;
/* 156 */         if (c1 == '+') {
/* 157 */           bool = true;
/* 158 */           str = str.substring(1);
/* 159 */         } else if (c1 == '-') {
/* 160 */           bool = true;
/*     */         } 
/*     */         try {
/* 163 */           int i = Integer.parseInt(str);
/* 164 */           if (bool) {
/* 165 */             object = Integer.valueOf(((Integer)object).intValue() + i);
/*     */           } else {
/* 167 */             object = Integer.valueOf(i);
/*     */           } 
/* 169 */         } catch (NumberFormatException numberFormatException) {
/* 170 */           k.i()
/* 171 */             .warn("Error in scriptlevel attribute for mstyle: " + str);
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 176 */       return object;
/*     */     }
/*     */     
/*     */     private Object c(Object param1Object) {
/* 180 */       Object object = param1Object;
/* 181 */       String str = this.a.getDisplaystyle();
/* 182 */       if ("true".equalsIgnoreCase(str)) {
/* 183 */         object = net.a.a.c.a.a;
/*     */       }
/* 185 */       if ("false".equalsIgnoreCase(str)) {
/* 186 */         object = net.a.a.c.a.b;
/*     */       }
/* 188 */       return object;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public c a(int paramInt, c paramc) {
/* 196 */     return new a(this, paramc);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getBackground() {
/* 201 */     return getMathbackground();
/*     */   }
/*     */ 
/*     */   
/*     */   public String a() {
/* 206 */     return getMathcolor();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getDisplaystyle() {
/* 211 */     return a("displaystyle");
/*     */   }
/*     */ 
/*     */   
/*     */   public String getScriptsizemultiplier() {
/* 216 */     return a("scriptsizemultiplier");
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBackground(String paramString) {
/* 221 */     setMathbackground(paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(String paramString) {
/* 226 */     setMathcolor(paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisplaystyle(String paramString) {
/* 231 */     setAttribute("displaystyle", paramString);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setScriptsizemultiplier(String paramString) {
/* 236 */     setAttribute("scriptsizemultiplier", paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getMediummathspace() {
/* 242 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNegativemediummathspace() {
/* 248 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNegativethickmathspace() {
/* 254 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNegativethinmathspace() {
/* 260 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNegativeverythickmathspace() {
/* 266 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNegativeverythinmathspace() {
/* 272 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNegativeveryverythickmathspace() {
/* 278 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNegativeveryverythinmathspace() {
/* 284 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getThickmathspace() {
/* 290 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getThinmathspace() {
/* 296 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVerythickmathspace() {
/* 302 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVerythinmathspace() {
/* 308 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVeryverythickmathspace() {
/* 314 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String getVeryverythinmathspace() {
/* 320 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setMediummathspace(String paramString) {
/* 326 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNegativemediummathspace(String paramString) {
/* 332 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNegativethickmathspace(String paramString) {
/* 338 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNegativethinmathspace(String paramString) {
/* 344 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNegativeverythickmathspace(String paramString) {
/* 351 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNegativeverythinmathspace(String paramString) {
/* 358 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNegativeveryverythickmathspace(String paramString) {
/* 365 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setNegativeveryverythinmathspace(String paramString) {
/* 372 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThickmathspace(String paramString) {
/* 378 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setThinmathspace(String paramString) {
/* 384 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVerythickmathspace(String paramString) {
/* 390 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVerythinmathspace(String paramString) {
/* 396 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVeryverythickmathspace(String paramString) {
/* 402 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVeryverythinmathspace(String paramString) {
/* 408 */     throw new UnsupportedOperationException();
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/net/a/a/e/c/b/k.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */