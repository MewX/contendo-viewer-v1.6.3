/*     */ package org.apache.regexp;
/*     */ 
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileReader;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.PrintWriter;
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
/*     */ public class RETest
/*     */ {
/*  73 */   RE r = new RE();
/*  74 */   REDebugCompiler compiler = new REDebugCompiler();
/*     */   
/*     */   static final boolean showSuccesses = false;
/*     */   
/*     */   char[] re1Instructions;
/*     */   
/*     */   REProgram re1;
/*     */   
/*     */   String expr;
/*     */   
/*     */   int n;
/*     */   
/*     */   int failures;
/*     */   
/*     */   public static void main(String[] paramArrayOfString) {
/*     */     try {
/*  90 */       test();
/*     */     }
/*  92 */     catch (Exception exception) {
/*     */       
/*  94 */       exception.printStackTrace();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean test() throws Exception {
/* 105 */     RETest rETest = new RETest();
/* 106 */     rETest.runAutomatedTests("docs/RETest.txt");
/* 107 */     return !(rETest.failures != 0);
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
/*     */   void runInteractiveTests(String paramString) {
/*     */     try {
/* 155 */       this.r.setProgram(this.compiler.compile(paramString));
/*     */ 
/*     */       
/* 158 */       say("\n" + paramString + "\n");
/*     */ 
/*     */       
/* 161 */       this.compiler.dumpProgram(new PrintWriter(System.out));
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       while (true) {
/* 167 */         BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
/* 168 */         System.out.print("> ");
/* 169 */         System.out.flush();
/* 170 */         String str = bufferedReader.readLine();
/*     */ 
/*     */         
/* 173 */         if (this.r.match(str)) {
/*     */           
/* 175 */           say("Match successful.");
/*     */         }
/*     */         else {
/*     */           
/* 179 */           say("Match failed.");
/*     */         } 
/*     */ 
/*     */         
/* 183 */         showParens(this.r);
/*     */       }
/*     */     
/* 186 */     } catch (Exception exception) {
/*     */       
/* 188 */       say("Error: " + exception.toString());
/* 189 */       exception.printStackTrace();
/*     */       return;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void die(String paramString) {
/* 199 */     say("FATAL ERROR: " + paramString);
/* 200 */     System.exit(0);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void fail(String paramString) {
/* 209 */     this.failures++;
/* 210 */     say("\n");
/* 211 */     say("*******************************************************");
/* 212 */     say("*********************  FAILURE!  **********************");
/* 213 */     say("*******************************************************");
/* 214 */     say("\n");
/* 215 */     say(paramString);
/* 216 */     say("");
/* 217 */     this.compiler.dumpProgram(new PrintWriter(System.out));
/* 218 */     say("\n");
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
/*     */   void success(String paramString) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void say(String paramString) {
/* 240 */     System.out.println(paramString);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void show() {
/* 248 */     say("\n-----------------------\n");
/* 249 */     say("Expression #" + this.n + " \"" + this.expr + "\" ");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void showParens(RE paramRE) {
/* 259 */     for (byte b = 0; b < paramRE.getParenCount(); b++)
/*     */     {
/*     */       
/* 262 */       say("$" + b + " = " + paramRE.getParen(b));
/*     */     }
/*     */   }
/*     */   
/*     */   public RETest() {
/* 267 */     this.re1Instructions = 
/* 268 */       new char[] { 
/* 269 */         '|', '\032', '|', '\r', 'A', 
/* 270 */         '\001', '\004', 'a', '|', '\003', 'G', 
/* 271 */         '￶', '|', '\003', 'N', 
/* 272 */         '\003', 'A', '\001', '\004', 'b', 'E' };
/*     */ 
/*     */ 
/*     */     
/* 276 */     this.re1 = new REProgram(this.re1Instructions);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 282 */     this.n = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 287 */     this.failures = 0; } public RETest(String[] paramArrayOfString) { this.re1Instructions = new char[] { '|', '\032', '|', '\r', 'A', '\001', '\004', 'a', '|', '\003', 'G', '￶', '|', '\003', 'N', '\003', 'A', '\001', '\004', 'b', 'E' }; this.re1 = new REProgram(this.re1Instructions); this.n = 0; this.failures = 0; try { if (paramArrayOfString.length == 2) { runInteractiveTests(paramArrayOfString[1]); }
/*     */       else if (paramArrayOfString.length == 1)
/*     */       { runAutomatedTests(paramArrayOfString[0]); }
/*     */       else
/*     */       { System.out.println("Usage: RETest ([-i] [regex]) ([/path/to/testfile.txt])"); }
/*     */        }
/*     */     catch (Exception exception)
/*     */     { exception.printStackTrace(); }
/* 295 */      } void runAutomatedTests(String paramString) throws Exception { long l = System.currentTimeMillis();
/*     */ 
/*     */     
/* 298 */     RE rE = new RE(this.re1);
/* 299 */     say("a*b");
/* 300 */     say("aaaab = " + rE.match("aaab"));
/* 301 */     showParens(rE);
/* 302 */     say("b = " + rE.match("b"));
/* 303 */     showParens(rE);
/* 304 */     say("c = " + rE.match("c"));
/* 305 */     showParens(rE);
/* 306 */     say("ccccaaaaab = " + rE.match("ccccaaaaab"));
/* 307 */     showParens(rE);
/*     */     
/* 309 */     rE = new RE("a*b");
/* 310 */     String[] arrayOfString = rE.split("xxxxaabxxxxbyyyyaaabzzz");
/* 311 */     rE = new RE("x+");
/* 312 */     arrayOfString = rE.grep((Object[])arrayOfString);
/* 313 */     for (byte b = 0; b < arrayOfString.length; b++)
/*     */     {
/* 315 */       System.out.println("s[" + b + "] = " + arrayOfString[b]);
/*     */     }
/*     */     
/* 318 */     rE = new RE("a*b");
/* 319 */     String str = rE.subst("aaaabfooaaabgarplyaaabwackyb", "-");
/* 320 */     System.out.println("s = " + str);
/*     */ 
/*     */     
/* 323 */     File file = new File(paramString);
/* 324 */     if (!file.exists())
/* 325 */       throw new Exception("Could not find: " + paramString); 
/* 326 */     BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
/*     */ 
/*     */     
/*     */     try {
/* 330 */       while (bufferedReader.ready()) {
/*     */ 
/*     */         
/* 333 */         String str1 = "";
/*     */         
/* 335 */         while (bufferedReader.ready()) {
/*     */           
/* 337 */           str1 = bufferedReader.readLine();
/* 338 */           if (str1 != null) {
/*     */ 
/*     */ 
/*     */             
/* 342 */             str1 = str1.trim();
/* 343 */             if (!str1.startsWith("#")) {
/*     */ 
/*     */ 
/*     */               
/* 347 */               if (!str1.equals("")) {
/*     */                 
/* 349 */                 System.out.println("Script error.  Line = " + str1);
/* 350 */                 System.exit(0);
/*     */               }  continue;
/*     */             } 
/*     */           }  break;
/*     */         } 
/* 355 */         if (bufferedReader.ready()) {
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 361 */           this.expr = bufferedReader.readLine();
/* 362 */           this.n++;
/* 363 */           say("");
/* 364 */           say(String.valueOf(this.n) + ". " + this.expr);
/* 365 */           say("");
/*     */ 
/*     */ 
/*     */           
/*     */           try {
/* 370 */             rE.setProgram(this.compiler.compile(this.expr));
/*     */ 
/*     */           
/*     */           }
/* 374 */           catch (Exception exception) {
/*     */ 
/*     */             
/* 377 */             String str3 = bufferedReader.readLine().trim();
/*     */ 
/*     */             
/* 380 */             if (str3.equals("ERR")) {
/*     */               
/* 382 */               say("   Match: ERR");
/* 383 */               success("Produces an error (" + exception.toString() + "), as expected.");
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/* 388 */             fail("Produces the unexpected error \"" + exception.getMessage() + "\"");
/*     */           }
/* 390 */           catch (Error error) {
/*     */ 
/*     */             
/* 393 */             fail("Compiler threw fatal error \"" + error.getMessage() + "\"");
/* 394 */             error.printStackTrace();
/*     */           } 
/*     */ 
/*     */           
/* 398 */           String str2 = bufferedReader.readLine().trim();
/* 399 */           say("   Match against: '" + str2 + "'");
/*     */ 
/*     */           
/* 402 */           if (str2.equals("ERR")) {
/*     */             
/* 404 */             fail("Was expected to be an error, but wasn't.");
/*     */ 
/*     */             
/*     */             continue;
/*     */           } 
/*     */ 
/*     */           
/*     */           try {
/* 412 */             boolean bool = rE.match(str2);
/*     */ 
/*     */             
/* 415 */             String str3 = bufferedReader.readLine().trim();
/*     */ 
/*     */             
/* 418 */             if (bool) {
/*     */ 
/*     */               
/* 421 */               say("   Match: YES");
/*     */ 
/*     */               
/* 424 */               if (str3.equals("NO")) {
/*     */                 
/* 426 */                 fail("Matched \"" + str2 + "\", when not expected to.");
/*     */                 continue;
/*     */               } 
/* 429 */               if (str3.equals("YES")) {
/*     */ 
/*     */                 
/* 432 */                 success("Matched \"" + str2 + "\", as expected:");
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/* 440 */                 say("   Paren count: " + rE.getParenCount());
/*     */ 
/*     */                 
/* 443 */                 for (byte b1 = 0; b1 < rE.getParenCount(); b1++) {
/*     */ 
/*     */                   
/* 446 */                   String str4 = bufferedReader.readLine().trim();
/* 447 */                   say("   Paren " + b1 + " : " + rE.getParen(b1));
/*     */ 
/*     */                   
/* 450 */                   if (!str4.equals(rE.getParen(b1)))
/*     */                   {
/*     */                     
/* 453 */                     fail("Register " + b1 + " should be = \"" + str4 + "\", but is \"" + rE.getParen(b1) + "\" instead.");
/*     */                   }
/*     */                 } 
/*     */                 
/*     */                 continue;
/*     */               } 
/*     */               
/* 460 */               die("Test script error!");
/*     */ 
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/* 466 */             say("   Match: NO");
/*     */ 
/*     */             
/* 469 */             if (str3.equals("YES")) {
/*     */ 
/*     */               
/* 472 */               fail("Did not match \"" + str2 + "\", when expected to.");
/*     */               continue;
/*     */             } 
/* 475 */             if (str3.equals("NO")) {
/*     */ 
/*     */               
/* 478 */               success("Did not match \"" + str2 + "\", as expected.");
/*     */               
/*     */               continue;
/*     */             } 
/*     */             
/* 483 */             die("Test script error!");
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           }
/* 489 */           catch (Exception exception) {
/*     */             
/* 491 */             fail("Matcher threw exception: " + exception.toString());
/* 492 */             exception.printStackTrace();
/*     */ 
/*     */           
/*     */           }
/* 496 */           catch (Error error) {
/*     */             
/* 498 */             fail("Matcher threw fatal error \"" + error.getMessage() + "\"");
/* 499 */             error.printStackTrace();
/*     */           }  continue;
/*     */         } 
/*     */         break;
/*     */       } 
/*     */     } finally {
/* 505 */       bufferedReader.close();
/*     */     } 
/*     */ 
/*     */     
/* 509 */     System.out.println("\n\nMatch time = " + (System.currentTimeMillis() - l) + " ms.");
/*     */ 
/*     */     
/* 512 */     System.out.println("\nTests complete.  " + this.n + " tests, " + this.failures + " failure(s)."); }
/*     */ 
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/RETest.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */