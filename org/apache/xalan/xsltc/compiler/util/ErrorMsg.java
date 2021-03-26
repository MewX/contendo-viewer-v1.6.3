/*     */ package org.apache.xalan.xsltc.compiler.util;
/*     */ 
/*     */ import java.text.MessageFormat;
/*     */ import java.util.Locale;
/*     */ import java.util.ResourceBundle;
/*     */ import org.apache.xalan.xsltc.compiler.Stylesheet;
/*     */ import org.apache.xalan.xsltc.compiler.SyntaxTreeNode;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class ErrorMsg
/*     */ {
/*     */   private String _code;
/*     */   private int _line;
/*  40 */   private String _message = null;
/*  41 */   private String _url = null;
/*  42 */   Object[] _params = null;
/*     */   
/*     */   public static final String MULTIPLE_STYLESHEET_ERR = "MULTIPLE_STYLESHEET_ERR";
/*     */   
/*     */   public static final String TEMPLATE_REDEF_ERR = "TEMPLATE_REDEF_ERR";
/*     */   
/*     */   public static final String TEMPLATE_UNDEF_ERR = "TEMPLATE_UNDEF_ERR";
/*     */   
/*     */   public static final String VARIABLE_REDEF_ERR = "VARIABLE_REDEF_ERR";
/*     */   
/*     */   public static final String VARIABLE_UNDEF_ERR = "VARIABLE_UNDEF_ERR";
/*     */   
/*     */   public static final String CLASS_NOT_FOUND_ERR = "CLASS_NOT_FOUND_ERR";
/*     */   
/*     */   public static final String METHOD_NOT_FOUND_ERR = "METHOD_NOT_FOUND_ERR";
/*     */   
/*     */   public static final String ARGUMENT_CONVERSION_ERR = "ARGUMENT_CONVERSION_ERR";
/*     */   
/*     */   public static final String FILE_NOT_FOUND_ERR = "FILE_NOT_FOUND_ERR";
/*     */   
/*     */   public static final String INVALID_URI_ERR = "INVALID_URI_ERR";
/*     */   
/*     */   public static final String FILE_ACCESS_ERR = "FILE_ACCESS_ERR";
/*     */   
/*     */   public static final String MISSING_ROOT_ERR = "MISSING_ROOT_ERR";
/*     */   
/*     */   public static final String NAMESPACE_UNDEF_ERR = "NAMESPACE_UNDEF_ERR";
/*     */   
/*     */   public static final String FUNCTION_RESOLVE_ERR = "FUNCTION_RESOLVE_ERR";
/*     */   
/*     */   public static final String NEED_LITERAL_ERR = "NEED_LITERAL_ERR";
/*     */   
/*     */   public static final String XPATH_PARSER_ERR = "XPATH_PARSER_ERR";
/*     */   
/*     */   public static final String REQUIRED_ATTR_ERR = "REQUIRED_ATTR_ERR";
/*     */   
/*     */   public static final String ILLEGAL_CHAR_ERR = "ILLEGAL_CHAR_ERR";
/*     */   
/*     */   public static final String ILLEGAL_PI_ERR = "ILLEGAL_PI_ERR";
/*     */   
/*     */   public static final String STRAY_ATTRIBUTE_ERR = "STRAY_ATTRIBUTE_ERR";
/*     */   public static final String ILLEGAL_ATTRIBUTE_ERR = "ILLEGAL_ATTRIBUTE_ERR";
/*     */   public static final String CIRCULAR_INCLUDE_ERR = "CIRCULAR_INCLUDE_ERR";
/*     */   public static final String RESULT_TREE_SORT_ERR = "RESULT_TREE_SORT_ERR";
/*     */   public static final String SYMBOLS_REDEF_ERR = "SYMBOLS_REDEF_ERR";
/*     */   public static final String XSL_VERSION_ERR = "XSL_VERSION_ERR";
/*     */   public static final String CIRCULAR_VARIABLE_ERR = "CIRCULAR_VARIABLE_ERR";
/*     */   public static final String ILLEGAL_BINARY_OP_ERR = "ILLEGAL_BINARY_OP_ERR";
/*     */   public static final String ILLEGAL_ARG_ERR = "ILLEGAL_ARG_ERR";
/*     */   public static final String DOCUMENT_ARG_ERR = "DOCUMENT_ARG_ERR";
/*     */   public static final String MISSING_WHEN_ERR = "MISSING_WHEN_ERR";
/*     */   public static final String MULTIPLE_OTHERWISE_ERR = "MULTIPLE_OTHERWISE_ERR";
/*     */   public static final String STRAY_OTHERWISE_ERR = "STRAY_OTHERWISE_ERR";
/*     */   public static final String STRAY_WHEN_ERR = "STRAY_WHEN_ERR";
/*     */   public static final String WHEN_ELEMENT_ERR = "WHEN_ELEMENT_ERR";
/*     */   public static final String UNNAMED_ATTRIBSET_ERR = "UNNAMED_ATTRIBSET_ERR";
/*     */   public static final String ILLEGAL_CHILD_ERR = "ILLEGAL_CHILD_ERR";
/*     */   public static final String ILLEGAL_ELEM_NAME_ERR = "ILLEGAL_ELEM_NAME_ERR";
/*     */   public static final String ILLEGAL_ATTR_NAME_ERR = "ILLEGAL_ATTR_NAME_ERR";
/*     */   public static final String ILLEGAL_TEXT_NODE_ERR = "ILLEGAL_TEXT_NODE_ERR";
/*     */   public static final String SAX_PARSER_CONFIG_ERR = "SAX_PARSER_CONFIG_ERR";
/*     */   public static final String INTERNAL_ERR = "INTERNAL_ERR";
/*     */   public static final String UNSUPPORTED_XSL_ERR = "UNSUPPORTED_XSL_ERR";
/*     */   public static final String UNSUPPORTED_EXT_ERR = "UNSUPPORTED_EXT_ERR";
/*     */   public static final String MISSING_XSLT_URI_ERR = "MISSING_XSLT_URI_ERR";
/*     */   public static final String MISSING_XSLT_TARGET_ERR = "MISSING_XSLT_TARGET_ERR";
/*     */   public static final String NOT_IMPLEMENTED_ERR = "NOT_IMPLEMENTED_ERR";
/*     */   public static final String NOT_STYLESHEET_ERR = "NOT_STYLESHEET_ERR";
/*     */   public static final String ELEMENT_PARSE_ERR = "ELEMENT_PARSE_ERR";
/*     */   public static final String KEY_USE_ATTR_ERR = "KEY_USE_ATTR_ERR";
/*     */   public static final String OUTPUT_VERSION_ERR = "OUTPUT_VERSION_ERR";
/*     */   public static final String ILLEGAL_RELAT_OP_ERR = "ILLEGAL_RELAT_OP_ERR";
/*     */   public static final String ATTRIBSET_UNDEF_ERR = "ATTRIBSET_UNDEF_ERR";
/*     */   public static final String ATTR_VAL_TEMPLATE_ERR = "ATTR_VAL_TEMPLATE_ERR";
/*     */   public static final String UNKNOWN_SIG_TYPE_ERR = "UNKNOWN_SIG_TYPE_ERR";
/*     */   public static final String DATA_CONVERSION_ERR = "DATA_CONVERSION_ERR";
/*     */   public static final String NO_TRANSLET_CLASS_ERR = "NO_TRANSLET_CLASS_ERR";
/*     */   public static final String NO_MAIN_TRANSLET_ERR = "NO_MAIN_TRANSLET_ERR";
/*     */   public static final String TRANSLET_CLASS_ERR = "TRANSLET_CLASS_ERR";
/*     */   public static final String TRANSLET_OBJECT_ERR = "TRANSLET_OBJECT_ERR";
/*     */   public static final String ERROR_LISTENER_NULL_ERR = "ERROR_LISTENER_NULL_ERR";
/*     */   public static final String JAXP_UNKNOWN_SOURCE_ERR = "JAXP_UNKNOWN_SOURCE_ERR";
/*     */   public static final String JAXP_NO_SOURCE_ERR = "JAXP_NO_SOURCE_ERR";
/*     */   public static final String JAXP_COMPILE_ERR = "JAXP_COMPILE_ERR";
/*     */   public static final String JAXP_INVALID_ATTR_ERR = "JAXP_INVALID_ATTR_ERR";
/*     */   public static final String JAXP_SET_RESULT_ERR = "JAXP_SET_RESULT_ERR";
/*     */   public static final String JAXP_NO_TRANSLET_ERR = "JAXP_NO_TRANSLET_ERR";
/*     */   public static final String JAXP_NO_HANDLER_ERR = "JAXP_NO_HANDLER_ERR";
/*     */   public static final String JAXP_NO_RESULT_ERR = "JAXP_NO_RESULT_ERR";
/*     */   public static final String JAXP_UNKNOWN_PROP_ERR = "JAXP_UNKNOWN_PROP_ERR";
/*     */   public static final String SAX2DOM_ADAPTER_ERR = "SAX2DOM_ADAPTER_ERR";
/*     */   public static final String XSLTC_SOURCE_ERR = "XSLTC_SOURCE_ERR";
/*     */   public static final String ER_RESULT_NULL = "ER_RESULT_NULL";
/*     */   public static final String JAXP_INVALID_SET_PARAM_VALUE = "JAXP_INVALID_SET_PARAM_VALUE";
/*     */   public static final String COMPILE_STDIN_ERR = "COMPILE_STDIN_ERR";
/*     */   public static final String COMPILE_USAGE_STR = "COMPILE_USAGE_STR";
/*     */   public static final String TRANSFORM_USAGE_STR = "TRANSFORM_USAGE_STR";
/*     */   public static final String STRAY_SORT_ERR = "STRAY_SORT_ERR";
/*     */   public static final String UNSUPPORTED_ENCODING = "UNSUPPORTED_ENCODING";
/*     */   public static final String SYNTAX_ERR = "SYNTAX_ERR";
/*     */   public static final String CONSTRUCTOR_NOT_FOUND = "CONSTRUCTOR_NOT_FOUND";
/*     */   public static final String NO_JAVA_FUNCT_THIS_REF = "NO_JAVA_FUNCT_THIS_REF";
/*     */   public static final String TYPE_CHECK_ERR = "TYPE_CHECK_ERR";
/*     */   public static final String TYPE_CHECK_UNK_LOC_ERR = "TYPE_CHECK_UNK_LOC_ERR";
/*     */   public static final String ILLEGAL_CMDLINE_OPTION_ERR = "ILLEGAL_CMDLINE_OPTION_ERR";
/*     */   public static final String CMDLINE_OPT_MISSING_ARG_ERR = "CMDLINE_OPT_MISSING_ARG_ERR";
/*     */   public static final String WARNING_PLUS_WRAPPED_MSG = "WARNING_PLUS_WRAPPED_MSG";
/*     */   public static final String WARNING_MSG = "WARNING_MSG";
/*     */   public static final String FATAL_ERR_PLUS_WRAPPED_MSG = "FATAL_ERR_PLUS_WRAPPED_MSG";
/*     */   public static final String FATAL_ERR_MSG = "FATAL_ERR_MSG";
/*     */   public static final String ERROR_PLUS_WRAPPED_MSG = "ERROR_PLUS_WRAPPED_MSG";
/*     */   public static final String ERROR_MSG = "ERROR_MSG";
/*     */   public static final String TRANSFORM_WITH_TRANSLET_STR = "TRANSFORM_WITH_TRANSLET_STR";
/*     */   public static final String TRANSFORM_WITH_JAR_STR = "TRANSFORM_WITH_JAR_STR";
/*     */   public static final String COULD_NOT_CREATE_TRANS_FACT = "COULD_NOT_CREATE_TRANS_FACT";
/*     */   public static final String TRANSLET_NAME_JAVA_CONFLICT = "TRANSLET_NAME_JAVA_CONFLICT";
/*     */   public static final String INVALID_QNAME_ERR = "INVALID_QNAME_ERR";
/*     */   public static final String INVALID_NCNAME_ERR = "INVALID_NCNAME_ERR";
/*     */   public static final String INVALID_METHOD_IN_OUTPUT = "INVALID_METHOD_IN_OUTPUT";
/* 161 */   private static ResourceBundle _bundle = ResourceBundle.getBundle("org.apache.xalan.xsltc.compiler.util.ErrorMessages", Locale.getDefault());
/*     */   
/*     */   public static final String ERROR_MESSAGES_KEY = "ERROR_MESSAGES_KEY";
/*     */   public static final String COMPILER_ERROR_KEY = "COMPILER_ERROR_KEY";
/*     */   
/*     */   public ErrorMsg(String code) {
/* 167 */     this._code = code;
/* 168 */     this._line = 0;
/*     */   }
/*     */   public static final String COMPILER_WARNING_KEY = "COMPILER_WARNING_KEY"; public static final String RUNTIME_ERROR_KEY = "RUNTIME_ERROR_KEY";
/*     */   public ErrorMsg(Throwable e) {
/* 172 */     this._code = null;
/* 173 */     this._message = e.getMessage();
/* 174 */     this._line = 0;
/*     */   }
/*     */   
/*     */   public ErrorMsg(String message, int line) {
/* 178 */     this._code = null;
/* 179 */     this._message = message;
/* 180 */     this._line = line;
/*     */   }
/*     */   
/*     */   public ErrorMsg(String code, int line, Object param) {
/* 184 */     this._code = code;
/* 185 */     this._line = line;
/* 186 */     this._params = new Object[] { param };
/*     */   }
/*     */   
/*     */   public ErrorMsg(String code, Object param) {
/* 190 */     this(code);
/* 191 */     this._params = new Object[1];
/* 192 */     this._params[0] = param;
/*     */   }
/*     */   
/*     */   public ErrorMsg(String code, Object param1, Object param2) {
/* 196 */     this(code);
/* 197 */     this._params = new Object[2];
/* 198 */     this._params[0] = param1;
/* 199 */     this._params[1] = param2;
/*     */   }
/*     */   
/*     */   public ErrorMsg(String code, SyntaxTreeNode node) {
/* 203 */     this._code = code;
/* 204 */     this._url = getFileName(node);
/* 205 */     this._line = node.getLineNumber();
/*     */   }
/*     */   
/*     */   public ErrorMsg(String code, Object param1, SyntaxTreeNode node) {
/* 209 */     this._code = code;
/* 210 */     this._url = getFileName(node);
/* 211 */     this._line = node.getLineNumber();
/* 212 */     this._params = new Object[1];
/* 213 */     this._params[0] = param1;
/*     */   }
/*     */ 
/*     */   
/*     */   public ErrorMsg(String code, Object param1, Object param2, SyntaxTreeNode node) {
/* 218 */     this._code = code;
/* 219 */     this._url = getFileName(node);
/* 220 */     this._line = node.getLineNumber();
/* 221 */     this._params = new Object[2];
/* 222 */     this._params[0] = param1;
/* 223 */     this._params[1] = param2;
/*     */   }
/*     */   
/*     */   private String getFileName(SyntaxTreeNode node) {
/* 227 */     Stylesheet stylesheet = node.getStylesheet();
/* 228 */     if (stylesheet != null) {
/* 229 */       return stylesheet.getSystemId();
/*     */     }
/* 231 */     return null;
/*     */   }
/*     */   
/*     */   private String formatLine() {
/* 235 */     StringBuffer result = new StringBuffer();
/* 236 */     if (this._url != null) {
/* 237 */       result.append(this._url);
/* 238 */       result.append(": ");
/*     */     } 
/* 240 */     if (this._line > 0) {
/* 241 */       result.append("line ");
/* 242 */       result.append(Integer.toString(this._line));
/* 243 */       result.append(": ");
/*     */     } 
/* 245 */     return result.toString();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 254 */     String suffix = (this._params == null) ? ((null != this._code) ? new String(getErrorMessage()) : this._message) : MessageFormat.format(getErrorMessage(), this._params);
/*     */ 
/*     */     
/* 257 */     return formatLine() + suffix;
/*     */   }
/*     */   
/*     */   public String toString(Object obj) {
/* 261 */     Object[] params = new Object[1];
/* 262 */     params[0] = obj.toString();
/* 263 */     String suffix = MessageFormat.format(getErrorMessage(), params);
/* 264 */     return formatLine() + suffix;
/*     */   }
/*     */   
/*     */   public String toString(Object obj0, Object obj1) {
/* 268 */     Object[] params = new Object[2];
/* 269 */     params[0] = obj0.toString();
/* 270 */     params[1] = obj1.toString();
/* 271 */     String suffix = MessageFormat.format(getErrorMessage(), params);
/* 272 */     return formatLine() + suffix;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String getErrorMessage() {
/* 283 */     return _bundle.getString(this._code);
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/util/ErrorMsg.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */