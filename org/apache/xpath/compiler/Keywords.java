/*     */ package org.apache.xpath.compiler;
/*     */ 
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
/*     */ public class Keywords
/*     */ {
/*  31 */   static Hashtable m_keywords = new Hashtable();
/*     */ 
/*     */   
/*  34 */   static Hashtable m_axisnames = new Hashtable();
/*     */ 
/*     */   
/*  37 */   static Hashtable m_functions = new Hashtable();
/*     */ 
/*     */   
/*  40 */   static Hashtable m_nodetypes = new Hashtable();
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_ANCESTORS_STRING = "ancestor";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_ANCESTORS_OR_SELF_STRING = "ancestor-or-self";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_ATTRIBUTES_STRING = "attribute";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_CHILDREN_STRING = "child";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_DESCENDANTS_STRING = "descendant";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_DESCENDANTS_OR_SELF_STRING = "descendant-or-self";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_FOLLOWING_STRING = "following";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_FOLLOWING_SIBLINGS_STRING = "following-sibling";
/*     */ 
/*     */ 
/*     */   
/*     */   private static final String FROM_PARENT_STRING = "parent";
/*     */ 
/*     */   
/*     */   private static final String FROM_PRECEDING_STRING = "preceding";
/*     */ 
/*     */   
/*     */   private static final String FROM_PRECEDING_SIBLINGS_STRING = "preceding-sibling";
/*     */ 
/*     */   
/*     */   private static final String FROM_SELF_STRING = "self";
/*     */ 
/*     */   
/*     */   private static final String FROM_NAMESPACE_STRING = "namespace";
/*     */ 
/*     */   
/*     */   private static final String FROM_SELF_ABBREVIATED_STRING = ".";
/*     */ 
/*     */   
/*     */   private static final String NODETYPE_COMMENT_STRING = "comment";
/*     */ 
/*     */   
/*     */   private static final String NODETYPE_TEXT_STRING = "text";
/*     */ 
/*     */   
/*     */   private static final String NODETYPE_PI_STRING = "processing-instruction";
/*     */ 
/*     */   
/*     */   private static final String NODETYPE_NODE_STRING = "node";
/*     */ 
/*     */   
/*     */   private static final String NODETYPE_ANYELEMENT_STRING = "*";
/*     */ 
/*     */   
/*     */   private static final String FUNC_CURRENT_STRING = "current";
/*     */ 
/*     */   
/*     */   private static final String FUNC_LAST_STRING = "last";
/*     */ 
/*     */   
/*     */   private static final String FUNC_POSITION_STRING = "position";
/*     */ 
/*     */   
/*     */   private static final String FUNC_COUNT_STRING = "count";
/*     */ 
/*     */   
/*     */   static final String FUNC_ID_STRING = "id";
/*     */ 
/*     */   
/*     */   public static final String FUNC_KEY_STRING = "key";
/*     */ 
/*     */   
/*     */   private static final String FUNC_LOCAL_PART_STRING = "local-name";
/*     */ 
/*     */   
/*     */   private static final String FUNC_NAMESPACE_STRING = "namespace-uri";
/*     */ 
/*     */   
/*     */   private static final String FUNC_NAME_STRING = "name";
/*     */ 
/*     */   
/*     */   private static final String FUNC_GENERATE_ID_STRING = "generate-id";
/*     */ 
/*     */   
/*     */   private static final String FUNC_NOT_STRING = "not";
/*     */ 
/*     */   
/*     */   private static final String FUNC_TRUE_STRING = "true";
/*     */ 
/*     */   
/*     */   private static final String FUNC_FALSE_STRING = "false";
/*     */ 
/*     */   
/*     */   private static final String FUNC_BOOLEAN_STRING = "boolean";
/*     */ 
/*     */   
/*     */   private static final String FUNC_LANG_STRING = "lang";
/*     */ 
/*     */   
/*     */   private static final String FUNC_NUMBER_STRING = "number";
/*     */ 
/*     */   
/*     */   private static final String FUNC_FLOOR_STRING = "floor";
/*     */ 
/*     */   
/*     */   private static final String FUNC_CEILING_STRING = "ceiling";
/*     */ 
/*     */   
/*     */   private static final String FUNC_ROUND_STRING = "round";
/*     */ 
/*     */   
/*     */   private static final String FUNC_SUM_STRING = "sum";
/*     */ 
/*     */   
/*     */   private static final String FUNC_STRING_STRING = "string";
/*     */ 
/*     */   
/*     */   private static final String FUNC_STARTS_WITH_STRING = "starts-with";
/*     */ 
/*     */   
/*     */   private static final String FUNC_CONTAINS_STRING = "contains";
/*     */ 
/*     */   
/*     */   private static final String FUNC_SUBSTRING_BEFORE_STRING = "substring-before";
/*     */ 
/*     */   
/*     */   private static final String FUNC_SUBSTRING_AFTER_STRING = "substring-after";
/*     */ 
/*     */   
/*     */   private static final String FUNC_NORMALIZE_SPACE_STRING = "normalize-space";
/*     */ 
/*     */   
/*     */   private static final String FUNC_TRANSLATE_STRING = "translate";
/*     */ 
/*     */   
/*     */   private static final String FUNC_CONCAT_STRING = "concat";
/*     */ 
/*     */   
/*     */   private static final String FUNC_SYSTEM_PROPERTY_STRING = "system-property";
/*     */ 
/*     */   
/*     */   private static final String FUNC_EXT_FUNCTION_AVAILABLE_STRING = "function-available";
/*     */ 
/*     */   
/*     */   private static final String FUNC_EXT_ELEM_AVAILABLE_STRING = "element-available";
/*     */ 
/*     */   
/*     */   private static final String FUNC_SUBSTRING_STRING = "substring";
/*     */ 
/*     */   
/*     */   private static final String FUNC_STRING_LENGTH_STRING = "string-length";
/*     */ 
/*     */   
/*     */   private static final String FUNC_UNPARSED_ENTITY_URI_STRING = "unparsed-entity-uri";
/*     */ 
/*     */   
/*     */   private static final String FUNC_DOCLOCATION_STRING = "document-location";
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 216 */     m_axisnames.put("ancestor", new Integer(37));
/*     */     
/* 218 */     m_axisnames.put("ancestor-or-self", new Integer(38));
/*     */     
/* 220 */     m_axisnames.put("attribute", new Integer(39));
/*     */     
/* 222 */     m_axisnames.put("child", new Integer(40));
/*     */     
/* 224 */     m_axisnames.put("descendant", new Integer(41));
/*     */     
/* 226 */     m_axisnames.put("descendant-or-self", new Integer(42));
/*     */     
/* 228 */     m_axisnames.put("following", new Integer(43));
/*     */     
/* 230 */     m_axisnames.put("following-sibling", new Integer(44));
/*     */     
/* 232 */     m_axisnames.put("parent", new Integer(45));
/*     */     
/* 234 */     m_axisnames.put("preceding", new Integer(46));
/*     */     
/* 236 */     m_axisnames.put("preceding-sibling", new Integer(47));
/*     */     
/* 238 */     m_axisnames.put("self", new Integer(48));
/*     */     
/* 240 */     m_axisnames.put("namespace", new Integer(49));
/*     */     
/* 242 */     m_nodetypes.put("comment", new Integer(1030));
/*     */     
/* 244 */     m_nodetypes.put("text", new Integer(1031));
/*     */     
/* 246 */     m_nodetypes.put("processing-instruction", new Integer(1032));
/*     */     
/* 248 */     m_nodetypes.put("node", new Integer(1033));
/*     */     
/* 250 */     m_nodetypes.put("*", new Integer(36));
/*     */     
/* 252 */     m_keywords.put(".", new Integer(48));
/*     */     
/* 254 */     m_keywords.put("id", new Integer(4));
/*     */     
/* 256 */     m_keywords.put("key", new Integer(5));
/*     */     
/* 258 */     m_functions.put("current", new Integer(0));
/*     */     
/* 260 */     m_functions.put("last", new Integer(1));
/*     */     
/* 262 */     m_functions.put("position", new Integer(2));
/*     */     
/* 264 */     m_functions.put("count", new Integer(3));
/*     */     
/* 266 */     m_functions.put("id", new Integer(4));
/*     */     
/* 268 */     m_functions.put("key", new Integer(5));
/*     */     
/* 270 */     m_functions.put("local-name", new Integer(7));
/*     */     
/* 272 */     m_functions.put("namespace-uri", new Integer(8));
/*     */     
/* 274 */     m_functions.put("name", new Integer(9));
/*     */     
/* 276 */     m_functions.put("generate-id", new Integer(10));
/*     */     
/* 278 */     m_functions.put("not", new Integer(11));
/*     */     
/* 280 */     m_functions.put("true", new Integer(12));
/*     */     
/* 282 */     m_functions.put("false", new Integer(13));
/*     */     
/* 284 */     m_functions.put("boolean", new Integer(14));
/*     */     
/* 286 */     m_functions.put("lang", new Integer(32));
/*     */     
/* 288 */     m_functions.put("number", new Integer(15));
/*     */     
/* 290 */     m_functions.put("floor", new Integer(16));
/*     */     
/* 292 */     m_functions.put("ceiling", new Integer(17));
/*     */     
/* 294 */     m_functions.put("round", new Integer(18));
/*     */     
/* 296 */     m_functions.put("sum", new Integer(19));
/*     */     
/* 298 */     m_functions.put("string", new Integer(20));
/*     */     
/* 300 */     m_functions.put("starts-with", new Integer(21));
/*     */     
/* 302 */     m_functions.put("contains", new Integer(22));
/*     */     
/* 304 */     m_functions.put("substring-before", new Integer(23));
/*     */     
/* 306 */     m_functions.put("substring-after", new Integer(24));
/*     */     
/* 308 */     m_functions.put("normalize-space", new Integer(25));
/*     */     
/* 310 */     m_functions.put("translate", new Integer(26));
/*     */     
/* 312 */     m_functions.put("concat", new Integer(27));
/*     */ 
/*     */ 
/*     */     
/* 316 */     m_functions.put("system-property", new Integer(31));
/*     */     
/* 318 */     m_functions.put("function-available", new Integer(33));
/*     */     
/* 320 */     m_functions.put("element-available", new Integer(34));
/*     */     
/* 322 */     m_functions.put("substring", new Integer(29));
/*     */     
/* 324 */     m_functions.put("string-length", new Integer(30));
/*     */     
/* 326 */     m_functions.put("unparsed-entity-uri", new Integer(36));
/*     */ 
/*     */ 
/*     */     
/* 330 */     m_functions.put("comment", new Integer(1030));
/*     */     
/* 332 */     m_functions.put("text", new Integer(1031));
/*     */     
/* 334 */     m_functions.put("processing-instruction", new Integer(1032));
/*     */     
/* 336 */     m_functions.put("node", new Integer(1033));
/*     */     
/* 338 */     m_functions.put("document-location", new Integer(35));
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
/*     */   public static boolean functionAvailable(String methName) {
/*     */     
/* 354 */     try { Object tblEntry = m_functions.get(methName);
/*     */       
/* 356 */       if (null == tblEntry) {
/* 357 */         return false;
/*     */       }
/* 359 */       int funcType = ((Integer)tblEntry).intValue();
/*     */       
/* 361 */       switch (funcType) {
/*     */         
/*     */         case 1030:
/*     */         case 1031:
/*     */         case 1032:
/*     */         case 1033:
/* 367 */           return false;
/*     */       } 
/* 369 */       return true; } catch (Exception e)
/*     */     
/*     */     { 
/*     */ 
/*     */       
/* 374 */       return false; }
/*     */   
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xpath/compiler/Keywords.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */