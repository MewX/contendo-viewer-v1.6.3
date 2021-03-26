/*     */ package org.apache.xpath.compiler;
/*     */ 
/*     */ import javax.xml.transform.TransformerException;
/*     */ import org.apache.xpath.Expression;
/*     */ import org.apache.xpath.functions.Function;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class FunctionTable
/*     */ {
/*     */   public static final int FUNC_CURRENT = 0;
/*     */   public static final int FUNC_LAST = 1;
/*     */   public static final int FUNC_POSITION = 2;
/*     */   public static final int FUNC_COUNT = 3;
/*     */   public static final int FUNC_ID = 4;
/*     */   public static final int FUNC_KEY = 5;
/*     */   public static final int FUNC_LOCAL_PART = 7;
/*     */   public static final int FUNC_NAMESPACE = 8;
/*     */   public static final int FUNC_QNAME = 9;
/*     */   public static final int FUNC_GENERATE_ID = 10;
/*     */   public static final int FUNC_NOT = 11;
/*     */   public static final int FUNC_TRUE = 12;
/*     */   public static final int FUNC_FALSE = 13;
/*     */   public static final int FUNC_BOOLEAN = 14;
/*     */   public static final int FUNC_NUMBER = 15;
/*     */   public static final int FUNC_FLOOR = 16;
/*     */   public static final int FUNC_CEILING = 17;
/*     */   public static final int FUNC_ROUND = 18;
/*     */   public static final int FUNC_SUM = 19;
/*     */   public static final int FUNC_STRING = 20;
/*     */   public static final int FUNC_STARTS_WITH = 21;
/*     */   public static final int FUNC_CONTAINS = 22;
/*     */   public static final int FUNC_SUBSTRING_BEFORE = 23;
/*     */   public static final int FUNC_SUBSTRING_AFTER = 24;
/*     */   public static final int FUNC_NORMALIZE_SPACE = 25;
/*     */   public static final int FUNC_TRANSLATE = 26;
/*     */   public static final int FUNC_CONCAT = 27;
/*     */   public static final int FUNC_SUBSTRING = 29;
/*     */   public static final int FUNC_STRING_LENGTH = 30;
/*     */   public static final int FUNC_SYSTEM_PROPERTY = 31;
/*     */   public static final int FUNC_LANG = 32;
/*     */   public static final int FUNC_EXT_FUNCTION_AVAILABLE = 33;
/*     */   public static final int FUNC_EXT_ELEM_AVAILABLE = 34;
/*     */   public static final int FUNC_UNPARSED_ENTITY_URI = 36;
/*     */   public static final int FUNC_DOCLOCATION = 35;
/*     */   public static FuncLoader[] m_functions;
/*     */   private static final int NUM_BUILT_IN_FUNCS = 37;
/*     */   private static final int NUM_ALLOWABLE_ADDINS = 30;
/* 156 */   static int m_funcNextFreeIndex = 37;
/*     */ 
/*     */   
/*     */   static {
/* 160 */     m_functions = new FuncLoader[67];
/* 161 */     m_functions[0] = new FuncLoader("FuncCurrent", 0);
/* 162 */     m_functions[1] = new FuncLoader("FuncLast", 1);
/* 163 */     m_functions[2] = new FuncLoader("FuncPosition", 2);
/*     */     
/* 165 */     m_functions[3] = new FuncLoader("FuncCount", 3);
/* 166 */     m_functions[4] = new FuncLoader("FuncId", 4);
/* 167 */     m_functions[5] = new FuncLoader("org.apache.xalan.templates.FuncKey", 5);
/*     */ 
/*     */ 
/*     */     
/* 171 */     m_functions[7] = new FuncLoader("FuncLocalPart", 7);
/*     */     
/* 173 */     m_functions[8] = new FuncLoader("FuncNamespace", 8);
/*     */     
/* 175 */     m_functions[9] = new FuncLoader("FuncQname", 9);
/* 176 */     m_functions[10] = new FuncLoader("FuncGenerateId", 10);
/*     */     
/* 178 */     m_functions[11] = new FuncLoader("FuncNot", 11);
/* 179 */     m_functions[12] = new FuncLoader("FuncTrue", 12);
/* 180 */     m_functions[13] = new FuncLoader("FuncFalse", 13);
/* 181 */     m_functions[14] = new FuncLoader("FuncBoolean", 14);
/* 182 */     m_functions[32] = new FuncLoader("FuncLang", 32);
/* 183 */     m_functions[15] = new FuncLoader("FuncNumber", 15);
/* 184 */     m_functions[16] = new FuncLoader("FuncFloor", 16);
/* 185 */     m_functions[17] = new FuncLoader("FuncCeiling", 17);
/* 186 */     m_functions[18] = new FuncLoader("FuncRound", 18);
/* 187 */     m_functions[19] = new FuncLoader("FuncSum", 19);
/* 188 */     m_functions[20] = new FuncLoader("FuncString", 20);
/* 189 */     m_functions[21] = new FuncLoader("FuncStartsWith", 21);
/*     */     
/* 191 */     m_functions[22] = new FuncLoader("FuncContains", 22);
/*     */     
/* 193 */     m_functions[23] = new FuncLoader("FuncSubstringBefore", 23);
/*     */     
/* 195 */     m_functions[24] = new FuncLoader("FuncSubstringAfter", 24);
/*     */     
/* 197 */     m_functions[25] = new FuncLoader("FuncNormalizeSpace", 25);
/*     */     
/* 199 */     m_functions[26] = new FuncLoader("FuncTranslate", 26);
/*     */     
/* 201 */     m_functions[27] = new FuncLoader("FuncConcat", 27);
/*     */ 
/*     */     
/* 204 */     m_functions[31] = new FuncLoader("FuncSystemProperty", 31);
/*     */     
/* 206 */     m_functions[33] = new FuncLoader("FuncExtFunctionAvailable", 33);
/*     */     
/* 208 */     m_functions[34] = new FuncLoader("FuncExtElementAvailable", 34);
/*     */     
/* 210 */     m_functions[29] = new FuncLoader("FuncSubstring", 29);
/*     */     
/* 212 */     m_functions[30] = new FuncLoader("FuncStringLength", 30);
/*     */     
/* 214 */     m_functions[35] = new FuncLoader("FuncDoclocation", 35);
/*     */     
/* 216 */     m_functions[36] = new FuncLoader("FuncUnparsedEntityURI", 36);
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
/*     */   public static Function getFunction(int which) throws TransformerException {
/* 235 */     return m_functions[which].getFunction();
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
/*     */   public static int installFunction(String name, Expression func) {
/*     */     int i;
/* 248 */     Object funcIndexObj = Keywords.m_functions.get(name);
/*     */     
/* 250 */     if (null != funcIndexObj) {
/*     */       
/* 252 */       i = ((Integer)funcIndexObj).intValue();
/*     */     }
/*     */     else {
/*     */       
/* 256 */       i = m_funcNextFreeIndex;
/*     */       
/* 258 */       m_funcNextFreeIndex++;
/*     */       
/* 260 */       Keywords.m_functions.put(name, new Integer(i));
/*     */     } 
/*     */     
/* 263 */     FuncLoader loader = new FuncLoader(func.getClass().getName(), i);
/*     */     
/* 265 */     m_functions[i] = loader;
/*     */     
/* 267 */     return i;
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
/*     */   public static void installFunction(Expression func, int funcIndex) {
/* 281 */     FuncLoader loader = new FuncLoader(func.getClass().getName(), funcIndex);
/*     */     
/* 283 */     m_functions[funcIndex] = loader;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/compiler/FunctionTable.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */