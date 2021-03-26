/*     */ package org.apache.xpath.compiler;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xml.utils.ObjectVector;
/*     */ import org.apache.xpath.res.XPATHMessages;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class OpMap
/*     */ {
/*     */   protected String m_currentPattern;
/*     */   static final int MAXTOKENQUEUESIZE = 500;
/*     */   static final int BLOCKTOKENQUEUESIZE = 500;
/*     */   
/*     */   public String toString() {
/*  45 */     return this.m_currentPattern;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getPatternString() {
/*  55 */     return this.m_currentPattern;
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
/*  73 */   ObjectVector m_tokenQueue = new ObjectVector(500, 500);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ObjectVector getTokenQueue() {
/*  82 */     return this.m_tokenQueue;
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
/*     */   public Object getToken(int pos) {
/*  94 */     return this.m_tokenQueue.elementAt(pos);
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
/*     */   public int getTokenQueueSize() {
/* 109 */     return this.m_tokenQueue.size();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/* 119 */   OpMapVector m_opMap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static final int MAPINDEX_LENGTH = 1;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public OpMapVector getOpMap() {
/* 131 */     return this.m_opMap;
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
/*     */   void shrink() {
/* 150 */     int n = this.m_opMap.elementAt(1);
/* 151 */     this.m_opMap.setToSize(n + 4);
/*     */     
/* 153 */     this.m_opMap.setElementAt(0, n);
/* 154 */     this.m_opMap.setElementAt(0, n + 1);
/* 155 */     this.m_opMap.setElementAt(0, n + 2);
/*     */ 
/*     */     
/* 158 */     n = this.m_tokenQueue.size();
/* 159 */     this.m_tokenQueue.setToSize(n + 4);
/*     */     
/* 161 */     this.m_tokenQueue.setElementAt(null, n);
/* 162 */     this.m_tokenQueue.setElementAt(null, n + 1);
/* 163 */     this.m_tokenQueue.setElementAt(null, n + 2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOp(int opPos) {
/* 174 */     return this.m_opMap.elementAt(opPos);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOp(int opPos, int value) {
/* 185 */     this.m_opMap.setElementAt(value, opPos);
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
/*     */   public int getNextOpPos(int opPos) {
/* 198 */     return opPos + this.m_opMap.elementAt(opPos + 1);
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
/*     */   public int getNextStepPos(int opPos) {
/* 211 */     int stepType = getOp(opPos);
/*     */     
/* 213 */     if (stepType >= 37 && stepType <= 53)
/*     */     {
/*     */       
/* 216 */       return getNextOpPos(opPos);
/*     */     }
/* 218 */     if (stepType >= 22 && stepType <= 25) {
/*     */ 
/*     */       
/* 221 */       int newOpPos = getNextOpPos(opPos);
/*     */       
/* 223 */       while (29 == getOp(newOpPos))
/*     */       {
/* 225 */         newOpPos = getNextOpPos(newOpPos);
/*     */       }
/*     */       
/* 228 */       stepType = getOp(newOpPos);
/*     */       
/* 230 */       if (stepType < 37 || stepType > 53)
/*     */       {
/*     */         
/* 233 */         return -1;
/*     */       }
/*     */       
/* 236 */       return newOpPos;
/*     */     } 
/*     */ 
/*     */     
/* 240 */     throw new RuntimeException(XPATHMessages.createXPATHMessage("ER_UNKNOWN_STEP", new Object[] { (new Integer(stepType)).toString() }));
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
/*     */   public static int getNextOpPos(int[] opMap, int opPos) {
/* 256 */     return opPos + opMap[opPos + 1];
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
/*     */   public int getFirstPredicateOpPos(int opPos) throws TransformerException {
/* 275 */     int stepType = this.m_opMap.elementAt(opPos);
/*     */     
/* 277 */     if (stepType >= 37 && stepType <= 53)
/*     */     {
/*     */       
/* 280 */       return opPos + this.m_opMap.elementAt(opPos + 2);
/*     */     }
/* 282 */     if (stepType >= 22 && stepType <= 25)
/*     */     {
/*     */       
/* 285 */       return opPos + this.m_opMap.elementAt(opPos + 1);
/*     */     }
/* 287 */     if (-2 == stepType)
/*     */     {
/* 289 */       return -2;
/*     */     }
/*     */ 
/*     */     
/* 293 */     error("ER_UNKNOWN_OPCODE", new Object[] { String.valueOf(stepType) });
/*     */     
/* 295 */     return -1;
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
/*     */   public void error(String msg, Object[] args) throws TransformerException {
/* 315 */     String fmsg = XPATHMessages.createXPATHMessage(msg, args);
/*     */ 
/*     */     
/* 318 */     throw new TransformerException(fmsg);
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
/*     */   public static int getFirstChildPos(int opPos) {
/* 331 */     return opPos + 2;
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
/*     */   public int getArgLength(int opPos) {
/* 343 */     return this.m_opMap.elementAt(opPos + 1);
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
/*     */   public int getArgLengthOfStep(int opPos) {
/* 355 */     return this.m_opMap.elementAt(opPos + 1 + 1) - 3;
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
/*     */   public static int getFirstChildPosOfStep(int opPos) {
/* 367 */     return opPos + 3;
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
/*     */   public int getStepTestType(int opPosOfStep) {
/* 379 */     return this.m_opMap.elementAt(opPosOfStep + 3);
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
/*     */   public String getStepNS(int opPosOfStep) {
/* 392 */     int argLenOfStep = getArgLengthOfStep(opPosOfStep);
/*     */ 
/*     */     
/* 395 */     if (argLenOfStep == 3) {
/*     */       
/* 397 */       int index = this.m_opMap.elementAt(opPosOfStep + 4);
/*     */       
/* 399 */       if (index >= 0)
/* 400 */         return (String)this.m_tokenQueue.elementAt(index); 
/* 401 */       if (-3 == index) {
/* 402 */         return "*";
/*     */       }
/* 404 */       return null;
/*     */     } 
/*     */     
/* 407 */     return null;
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
/*     */   public String getStepLocalName(int opPosOfStep) {
/* 419 */     int index, argLenOfStep = getArgLengthOfStep(opPosOfStep);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 424 */     switch (argLenOfStep) {
/*     */       
/*     */       case 0:
/* 427 */         index = -2;
/*     */         break;
/*     */       case 1:
/* 430 */         index = -3;
/*     */         break;
/*     */       case 2:
/* 433 */         index = this.m_opMap.elementAt(opPosOfStep + 4);
/*     */         break;
/*     */       case 3:
/* 436 */         index = this.m_opMap.elementAt(opPosOfStep + 5);
/*     */         break;
/*     */       default:
/* 439 */         index = -2;
/*     */         break;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 445 */     if (index >= 0)
/* 446 */       return this.m_tokenQueue.elementAt(index).toString(); 
/* 447 */     if (-3 == index) {
/* 448 */       return "*";
/*     */     }
/* 450 */     return null;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/compiler/OpMap.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */