/*     */ package org.apache.regexp;
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
/*     */ public class recompile
/*     */ {
/*     */   public static void main(String[] paramArrayOfString) {
/* 109 */     RECompiler rECompiler = new RECompiler();
/*     */ 
/*     */     
/* 112 */     if (paramArrayOfString.length <= 0 || paramArrayOfString.length % 2 != 0) {
/*     */       
/* 114 */       System.out.println("Usage: recompile <patternname> <pattern>");
/* 115 */       System.exit(0);
/*     */     } 
/*     */ 
/*     */     
/* 119 */     for (byte b = 0; b < paramArrayOfString.length; b += 2) {
/*     */ 
/*     */       
/*     */       try {
/*     */         
/* 124 */         String str1 = paramArrayOfString[b];
/* 125 */         String str2 = paramArrayOfString[b + 1];
/* 126 */         String str3 = String.valueOf(str1) + "PatternInstructions";
/*     */ 
/*     */         
/* 129 */         System.out.print("\n    // Pre-compiled regular expression '" + str2 + "'\n" + 
/* 130 */             "    private static char[] " + str3 + " = \n    {");
/*     */ 
/*     */         
/* 133 */         REProgram rEProgram = rECompiler.compile(str2);
/*     */ 
/*     */         
/* 136 */         byte b1 = 7;
/*     */ 
/*     */         
/* 139 */         char[] arrayOfChar = rEProgram.getInstructions();
/* 140 */         for (byte b2 = 0; b2 < arrayOfChar.length; b2++) {
/*     */ 
/*     */           
/* 143 */           if (b2 % b1 == 0)
/*     */           {
/* 145 */             System.out.print("\n        ");
/*     */           }
/*     */ 
/*     */           
/* 149 */           String str = Integer.toHexString(arrayOfChar[b2]);
/* 150 */           while (str.length() < 4)
/*     */           {
/* 152 */             str = "0" + str;
/*     */           }
/* 154 */           System.out.print("0x" + str + ", ");
/*     */         } 
/*     */ 
/*     */         
/* 158 */         System.out.println("\n    };");
/* 159 */         System.out.println("\n    private static RE " + str1 + "Pattern = new RE(new REProgram(" + str3 + "));");
/*     */       }
/* 161 */       catch (RESyntaxException rESyntaxException) {
/*     */         
/* 163 */         System.out.println("Syntax error in expression \"" + paramArrayOfString[b] + "\": " + rESyntaxException.toString());
/*     */       }
/* 165 */       catch (Exception exception) {
/*     */         
/* 167 */         System.out.println("Unexpected exception: " + exception.toString());
/*     */       }
/* 169 */       catch (Error error) {
/*     */         
/* 171 */         System.out.println("Internal error: " + error.toString());
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/recompile.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */