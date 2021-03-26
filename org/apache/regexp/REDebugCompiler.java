/*     */ package org.apache.regexp;
/*     */ 
/*     */ import java.io.PrintWriter;
/*     */ import java.util.Hashtable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class REDebugCompiler
/*     */   extends RECompiler
/*     */ {
/*  75 */   static Hashtable hashOpcode = new Hashtable();
/*     */   
/*     */   static {
/*  78 */     hashOpcode.put(new Integer(56), "OP_RELUCTANTSTAR");
/*  79 */     hashOpcode.put(new Integer(61), "OP_RELUCTANTPLUS");
/*  80 */     hashOpcode.put(new Integer(47), "OP_RELUCTANTMAYBE");
/*  81 */     hashOpcode.put(new Integer(69), "OP_END");
/*  82 */     hashOpcode.put(new Integer(94), "OP_BOL");
/*  83 */     hashOpcode.put(new Integer(36), "OP_EOL");
/*  84 */     hashOpcode.put(new Integer(46), "OP_ANY");
/*  85 */     hashOpcode.put(new Integer(91), "OP_ANYOF");
/*  86 */     hashOpcode.put(new Integer(124), "OP_BRANCH");
/*  87 */     hashOpcode.put(new Integer(65), "OP_ATOM");
/*  88 */     hashOpcode.put(new Integer(42), "OP_STAR");
/*  89 */     hashOpcode.put(new Integer(43), "OP_PLUS");
/*  90 */     hashOpcode.put(new Integer(63), "OP_MAYBE");
/*  91 */     hashOpcode.put(new Integer(78), "OP_NOTHING");
/*  92 */     hashOpcode.put(new Integer(71), "OP_GOTO");
/*  93 */     hashOpcode.put(new Integer(92), "OP_ESCAPE");
/*  94 */     hashOpcode.put(new Integer(40), "OP_OPEN");
/*  95 */     hashOpcode.put(new Integer(41), "OP_CLOSE");
/*  96 */     hashOpcode.put(new Integer(35), "OP_BACKREF");
/*  97 */     hashOpcode.put(new Integer(80), "OP_POSIXCLASS");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String opcodeToString(char paramChar) {
/* 108 */     String str = (String)hashOpcode.get(new Integer(paramChar));
/*     */ 
/*     */     
/* 111 */     if (str == null)
/*     */     {
/* 113 */       str = "OP_????";
/*     */     }
/* 115 */     return str;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String charToString(char paramChar) {
/* 126 */     if (paramChar < ' ' || paramChar > '')
/*     */     {
/* 128 */       return "\\" + paramChar;
/*     */     }
/*     */ 
/*     */     
/* 132 */     return String.valueOf(paramChar);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   String nodeToString(int paramInt) {
/* 143 */     char c1 = this.instruction[paramInt];
/* 144 */     char c2 = this.instruction[paramInt + 1];
/*     */ 
/*     */     
/* 147 */     return String.valueOf(opcodeToString(c1)) + ", opdata = " + c2;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void dumpProgram(PrintWriter paramPrintWriter) {
/* 157 */     for (byte b = 0; b < this.lenInstruction; ) {
/*     */ 
/*     */       
/* 160 */       char c1 = this.instruction[b];
/* 161 */       char c2 = this.instruction[b + 1];
/* 162 */       short s = (short)this.instruction[b + 2];
/*     */ 
/*     */       
/* 165 */       paramPrintWriter.print(String.valueOf(b) + ". " + nodeToString(b) + ", next = ");
/*     */ 
/*     */       
/* 168 */       if (s == 0) {
/*     */         
/* 170 */         paramPrintWriter.print("none");
/*     */       }
/*     */       else {
/*     */         
/* 174 */         paramPrintWriter.print(b + s);
/*     */       } 
/*     */ 
/*     */       
/* 178 */       b += 3;
/*     */ 
/*     */       
/* 181 */       if (c1 == '[') {
/*     */ 
/*     */         
/* 184 */         paramPrintWriter.print(", [");
/*     */ 
/*     */         
/* 187 */         char c = c2;
/* 188 */         for (byte b1 = 0; b1 < c; b1++) {
/*     */ 
/*     */           
/* 191 */           char c3 = this.instruction[b++];
/* 192 */           char c4 = this.instruction[b++];
/*     */ 
/*     */           
/* 195 */           if (c3 == c4) {
/*     */             
/* 197 */             paramPrintWriter.print(charToString(c3));
/*     */           }
/*     */           else {
/*     */             
/* 201 */             paramPrintWriter.print(String.valueOf(charToString(c3)) + "-" + charToString(c4));
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 206 */         paramPrintWriter.print("]");
/*     */       } 
/*     */ 
/*     */       
/* 210 */       if (c1 == 'A') {
/*     */ 
/*     */         
/* 213 */         paramPrintWriter.print(", \"");
/*     */ 
/*     */         
/* 216 */         for (char c = c2; c-- != '\000';)
/*     */         {
/* 218 */           paramPrintWriter.print(charToString(this.instruction[b++]));
/*     */         }
/*     */ 
/*     */         
/* 222 */         paramPrintWriter.print("\"");
/*     */       } 
/*     */ 
/*     */       
/* 226 */       paramPrintWriter.println("");
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/regexp/REDebugCompiler.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */