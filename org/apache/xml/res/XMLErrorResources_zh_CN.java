/*     */ package org.apache.xml.res;
/*     */ 
/*     */ import java.util.ListResourceBundle;
/*     */ import java.util.Locale;
/*     */ import java.util.MissingResourceException;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class XMLErrorResources_zh_CN
/*     */   extends ListResourceBundle
/*     */ {
/*     */   public static final int MAX_CODE = 61;
/*     */   public static final int MAX_WARNING = 0;
/*     */   public static final int MAX_OTHERS = 4;
/*     */   public static final int MAX_MESSAGES = 62;
/*     */   public static final String ER_FUNCTION_NOT_SUPPORTED = "ER_FUNCTION_NOT_SUPPORTED";
/*     */   public static final String ER_CANNOT_OVERWRITE_CAUSE = "ER_CANNOT_OVERWRITE_CAUSE";
/*     */   public static final String ER_NO_DEFAULT_IMPL = "ER_NO_DEFAULT_IMPL";
/*     */   public static final String ER_CHUNKEDINTARRAY_NOT_SUPPORTED = "ER_CHUNKEDINTARRAY_NOT_SUPPORTED";
/*     */   public static final String ER_OFFSET_BIGGER_THAN_SLOT = "ER_OFFSET_BIGGER_THAN_SLOT";
/*     */   public static final String ER_COROUTINE_NOT_AVAIL = "ER_COROUTINE_NOT_AVAIL";
/*     */   public static final String ER_COROUTINE_CO_EXIT = "ER_COROUTINE_CO_EXIT";
/*     */   public static final String ER_COJOINROUTINESET_FAILED = "ER_COJOINROUTINESET_FAILED";
/*     */   public static final String ER_COROUTINE_PARAM = "ER_COROUTINE_PARAM";
/*     */   public static final String ER_PARSER_DOTERMINATE_ANSWERS = "ER_PARSER_DOTERMINATE_ANSWERS";
/*     */   public static final String ER_NO_PARSE_CALL_WHILE_PARSING = "ER_NO_PARSE_CALL_WHILE_PARSING";
/*     */   public static final String ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED = "ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED";
/*     */   public static final String ER_ITERATOR_AXIS_NOT_IMPLEMENTED = "ER_ITERATOR_AXIS_NOT_IMPLEMENTED";
/*     */   public static final String ER_ITERATOR_CLONE_NOT_SUPPORTED = "ER_ITERATOR_CLONE_NOT_SUPPORTED";
/*     */   public static final String ER_UNKNOWN_AXIS_TYPE = "ER_UNKNOWN_AXIS_TYPE";
/*     */   public static final String ER_AXIS_NOT_SUPPORTED = "ER_AXIS_NOT_SUPPORTED";
/*     */   public static final String ER_NO_DTMIDS_AVAIL = "ER_NO_DTMIDS_AVAIL";
/*     */   public static final String ER_NOT_SUPPORTED = "ER_NOT_SUPPORTED";
/*     */   public static final String ER_NODE_NON_NULL = "ER_NODE_NON_NULL";
/*     */   public static final String ER_COULD_NOT_RESOLVE_NODE = "ER_COULD_NOT_RESOLVE_NODE";
/*     */   public static final String ER_STARTPARSE_WHILE_PARSING = "ER_STARTPARSE_WHILE_PARSING";
/*     */   public static final String ER_STARTPARSE_NEEDS_SAXPARSER = "ER_STARTPARSE_NEEDS_SAXPARSER";
/*     */   public static final String ER_COULD_NOT_INIT_PARSER = "ER_COULD_NOT_INIT_PARSER";
/*     */   public static final String ER_EXCEPTION_CREATING_POOL = "ER_EXCEPTION_CREATING_POOL";
/*     */   public static final String ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE = "ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE";
/*     */   public static final String ER_SCHEME_REQUIRED = "ER_SCHEME_REQUIRED";
/*     */   public static final String ER_NO_SCHEME_IN_URI = "ER_NO_SCHEME_IN_URI";
/*     */   public static final String ER_NO_SCHEME_INURI = "ER_NO_SCHEME_INURI";
/*     */   public static final String ER_PATH_INVALID_CHAR = "ER_PATH_INVALID_CHAR";
/*     */   public static final String ER_SCHEME_FROM_NULL_STRING = "ER_SCHEME_FROM_NULL_STRING";
/*     */   public static final String ER_SCHEME_NOT_CONFORMANT = "ER_SCHEME_NOT_CONFORMANT";
/*     */   public static final String ER_HOST_ADDRESS_NOT_WELLFORMED = "ER_HOST_ADDRESS_NOT_WELLFORMED";
/*     */   public static final String ER_PORT_WHEN_HOST_NULL = "ER_PORT_WHEN_HOST_NULL";
/*     */   public static final String ER_INVALID_PORT = "ER_INVALID_PORT";
/*     */   public static final String ER_FRAG_FOR_GENERIC_URI = "ER_FRAG_FOR_GENERIC_URI";
/*     */   public static final String ER_FRAG_WHEN_PATH_NULL = "ER_FRAG_WHEN_PATH_NULL";
/*     */   public static final String ER_FRAG_INVALID_CHAR = "ER_FRAG_INVALID_CHAR";
/*     */   public static final String ER_PARSER_IN_USE = "ER_PARSER_IN_USE";
/*     */   public static final String ER_CANNOT_CHANGE_WHILE_PARSING = "ER_CANNOT_CHANGE_WHILE_PARSING";
/*     */   public static final String ER_SELF_CAUSATION_NOT_PERMITTED = "ER_SELF_CAUSATION_NOT_PERMITTED";
/*     */   public static final String ER_NO_USERINFO_IF_NO_HOST = "ER_NO_USERINFO_IF_NO_HOST";
/*     */   public static final String ER_NO_PORT_IF_NO_HOST = "ER_NO_PORT_IF_NO_HOST";
/*     */   public static final String ER_NO_QUERY_STRING_IN_PATH = "ER_NO_QUERY_STRING_IN_PATH";
/*     */   public static final String ER_NO_FRAGMENT_STRING_IN_PATH = "ER_NO_FRAGMENT_STRING_IN_PATH";
/*     */   public static final String ER_CANNOT_INIT_URI_EMPTY_PARMS = "ER_CANNOT_INIT_URI_EMPTY_PARMS";
/*     */   public static final String ER_METHOD_NOT_SUPPORTED = "ER_METHOD_NOT_SUPPORTED";
/*     */   public static final String ER_INCRSAXSRCFILTER_NOT_RESTARTABLE = "ER_INCRSAXSRCFILTER_NOT_RESTARTABLE";
/*     */   public static final String ER_XMLRDR_NOT_BEFORE_STARTPARSE = "ER_XMLRDR_NOT_BEFORE_STARTPARSE";
/*     */   public static final String ER_AXIS_TRAVERSER_NOT_SUPPORTED = "ER_AXIS_TRAVERSER_NOT_SUPPORTED";
/*     */   public static final String ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER = "ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER";
/*     */   public static final String ER_SYSTEMID_UNKNOWN = "ER_SYSTEMID_UNKNOWN";
/*     */   public static final String ER_LOCATION_UNKNOWN = "ER_LOCATION_UNKNOWN";
/*     */   public static final String ER_PREFIX_MUST_RESOLVE = "ER_PREFIX_MUST_RESOLVE";
/*     */   public static final String ER_CREATEDOCUMENT_NOT_SUPPORTED = "ER_CREATEDOCUMENT_NOT_SUPPORTED";
/*     */   public static final String ER_CHILD_HAS_NO_OWNER_DOCUMENT = "ER_CHILD_HAS_NO_OWNER_DOCUMENT";
/*     */   public static final String ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT = "ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT";
/*     */   public static final String ER_CANT_OUTPUT_TEXT_BEFORE_DOC = "ER_CANT_OUTPUT_TEXT_BEFORE_DOC";
/*     */   public static final String ER_CANT_HAVE_MORE_THAN_ONE_ROOT = "ER_CANT_HAVE_MORE_THAN_ONE_ROOT";
/*     */   public static final String ER_ARG_LOCALNAME_NULL = "ER_ARG_LOCALNAME_NULL";
/*     */   public static final String ER_ARG_LOCALNAME_INVALID = "ER_ARG_LOCALNAME_INVALID";
/*     */   public static final String ER_ARG_PREFIX_INVALID = "ER_ARG_PREFIX_INVALID";
/*     */   public static final String ER_RESOURCE_COULD_NOT_FIND = "ER_RESOURCE_COULD_NOT_FIND";
/*     */   public static final String ER_RESOURCE_COULD_NOT_LOAD = "ER_RESOURCE_COULD_NOT_LOAD";
/*     */   public static final String ER_BUFFER_SIZE_LESSTHAN_ZERO = "ER_BUFFER_SIZE_LESSTHAN_ZERO";
/*     */   public static final String ER_INVALID_UTF16_SURROGATE = "ER_INVALID_UTF16_SURROGATE";
/*     */   public static final String ER_OIERROR = "ER_OIERROR";
/*     */   public static final String ER_NAMESPACE_PREFIX = "ER_NAMESPACE_PREFIX";
/*     */   public static final String ER_STRAY_ATTRIBUTE = "ER_STRAY_ATTIRBUTE";
/*     */   public static final String ER_STRAY_NAMESPACE = "ER_STRAY_NAMESPACE";
/*     */   public static final String ER_COULD_NOT_LOAD_RESOURCE = "ER_COULD_NOT_LOAD_RESOURCE";
/*     */   public static final String ER_COULD_NOT_LOAD_METHOD_PROPERTY = "ER_COULD_NOT_LOAD_METHOD_PROPERTY";
/*     */   public static final String ER_SERIALIZER_NOT_CONTENTHANDLER = "ER_SERIALIZER_NOT_CONTENTHANDLER";
/*     */   public static final String ER_ILLEGAL_ATTRIBUTE_POSITION = "ER_ILLEGAL_ATTRIBUTE_POSITION";
/* 179 */   public static final Object[][] contents = new Object[][] { { "ER0000", "{0}" }, { "ER_FUNCTION_NOT_SUPPORTED", "不支持函数！" }, { "ER_CANNOT_OVERWRITE_CAUSE", "无法覆盖原因" }, { "ER_NO_DEFAULT_IMPL", "找不到缺省实现" }, { "ER_CHUNKEDINTARRAY_NOT_SUPPORTED", "当前不支持 ChunkedIntArray({0})" }, { "ER_OFFSET_BIGGER_THAN_SLOT", "偏移大于槽" }, { "ER_COROUTINE_NOT_AVAIL", "协同程序不可用，id={0}" }, { "ER_COROUTINE_CO_EXIT", "CoroutineManager 接收到 co_exit() 请求" }, { "ER_COJOINROUTINESET_FAILED", "co_joinCoroutineSet() 失败" }, { "ER_COROUTINE_PARAM", "协同程序参数错误（{0}）" }, { "ER_PARSER_DOTERMINATE_ANSWERS", "\n意外：解析器 doTerminate 应答 {0}" }, { "ER_NO_PARSE_CALL_WHILE_PARSING", "分析时可能没有调用 parse" }, { "ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED", "错误：没有实现为轴 {0} 输入的迭代器" }, { "ER_ITERATOR_AXIS_NOT_IMPLEMENTED", "错误：没有实现轴 {0} 的迭代器" }, { "ER_ITERATOR_CLONE_NOT_SUPPORTED", "不支持迭代器克隆" }, { "ER_UNKNOWN_AXIS_TYPE", "未知的轴遍历类型：{0}" }, { "ER_AXIS_NOT_SUPPORTED", "不支持轴遍历程序：{0}" }, { "ER_NO_DTMIDS_AVAIL", "无更多的 DTM 标识可用" }, { "ER_NOT_SUPPORTED", "不支持：{0}" }, { "ER_NODE_NON_NULL", "节点对于 getDTMHandleFromNode 必须是非空的" }, { "ER_COULD_NOT_RESOLVE_NODE", "无法将节点解析到句柄" }, { "ER_STARTPARSE_WHILE_PARSING", "分析时可能没有调用 startParse" }, { "ER_STARTPARSE_NEEDS_SAXPARSER", "startParse 需要非空的 SAXParser" }, { "ER_COULD_NOT_INIT_PARSER", "无法用以下工具初始化解析器" }, { "ER_EXCEPTION_CREATING_POOL", "为池创建新实例时发生异常" }, { "ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE", "路径包含无效的转义序列" }, { "ER_SCHEME_REQUIRED", "模式是必需的！" }, { "ER_NO_SCHEME_IN_URI", "在 URI 中找不到模式：{0}" }, { "ER_NO_SCHEME_INURI", "URI 中未找到模式" }, { "ER_PATH_INVALID_CHAR", "路径包含非法字符：{0}" }, { "ER_SCHEME_FROM_NULL_STRING", "无法从空字符串设置模式" }, { "ER_SCHEME_NOT_CONFORMANT", "模式不一致。" }, { "ER_HOST_ADDRESS_NOT_WELLFORMED", "主机不是格式良好的地址" }, { "ER_PORT_WHEN_HOST_NULL", "主机为空时，无法设置端口" }, { "ER_INVALID_PORT", "无效的端口号" }, { "ER_FRAG_FOR_GENERIC_URI", "只能为一般 URI 设置片段" }, { "ER_FRAG_WHEN_PATH_NULL", "路径为空时，无法设置片段" }, { "ER_FRAG_INVALID_CHAR", "片段包含无效的字符" }, { "ER_PARSER_IN_USE", "解析器已在使用" }, { "ER_CANNOT_CHANGE_WHILE_PARSING", "分析时无法更改 {0} {1}" }, { "ER_SELF_CAUSATION_NOT_PERMITTED", "不允许自成因果关系" }, { "ER_NO_USERINFO_IF_NO_HOST", "如果没有指定主机，则不可以指定 Userinfo" }, { "ER_NO_PORT_IF_NO_HOST", "如果没有指定主机，则不可以指定端口" }, { "ER_NO_QUERY_STRING_IN_PATH", "路径和查询字符串中不能指定查询字符串" }, { "ER_NO_FRAGMENT_STRING_IN_PATH", "路径和片段中都无法指定片段" }, { "ER_CANNOT_INIT_URI_EMPTY_PARMS", "无法以空参数初始化 URI" }, { "ER_METHOD_NOT_SUPPORTED", "尚不支持方法" }, { "ER_INCRSAXSRCFILTER_NOT_RESTARTABLE", "当前不可重新启动 IncrementalSAXSource_Filter" }, { "ER_XMLRDR_NOT_BEFORE_STARTPARSE", "XMLReader 不在 startParse 请求之前" }, { "ER_AXIS_TRAVERSER_NOT_SUPPORTED", "不支持轴遍历程序：{0}" }, { "ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER", "以空的 PrintWriter 创建了 ListingErrorHandler！" }, { "ER_SYSTEMID_UNKNOWN", "SystemId 未知" }, { "ER_LOCATION_UNKNOWN", "错误位置未知" }, { "ER_PREFIX_MUST_RESOLVE", "前缀必须解析为名称空间：{0}" }, { "ER_CREATEDOCUMENT_NOT_SUPPORTED", "XPathContext 中不支持 createDocument ()！" }, { "ER_CHILD_HAS_NO_OWNER_DOCUMENT", "子属性没有所有者文档！" }, { "ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT", "子属性没有所有者文档元素！" }, { "ER_CANT_OUTPUT_TEXT_BEFORE_DOC", "警告：无法输出 document 元素前的文本！忽略..." }, { "ER_CANT_HAVE_MORE_THAN_ONE_ROOT", "DOM 上不能有多个根！" }, { "ER_ARG_LOCALNAME_NULL", "自变量“localName”为空" }, { "ER_ARG_LOCALNAME_INVALID", "QNAME 中的本地名应当是有效的 NCName" }, { "ER_ARG_PREFIX_INVALID", "QNAME 中的前缀应当是有效的 NCName" }, { "BAD_CODE", "createMessage 的参数超出范围" }, { "FORMAT_FAILED", "在 messageFormat 调用过程中抛出的异常" }, { "line", "行号" }, { "column", "列号" }, { "ER_SERIALIZER_NOT_CONTENTHANDLER", "串行器类“{0}”不实现 org.xml.sax.ContentHandler." }, { "ER_RESOURCE_COULD_NOT_FIND", "找不到资源 [ {0} ]。\n {1}" }, { "ER_RESOURCE_COULD_NOT_LOAD", "资源 [ {0} ] 无法装入：{1} \n {2} \t {3}" }, { "ER_BUFFER_SIZE_LESSTHAN_ZERO", "缓冲区大小 <=0" }, { "ER_INVALID_UTF16_SURROGATE", "检测到无效的 UTF-16 替代者：{0}？" }, { "ER_OIERROR", "IO 错误" }, { "ER_ILLEGAL_ATTRIBUTE_POSITION", "在生成子节点之后或在生成元素之前无法添加属性 {0}。将忽略属性。" }, { "ER_NAMESPACE_PREFIX", "没有说明名称空间前缀“{0}”。" }, { "ER_STRAY_ATTIRBUTE", "属性“{0}”在元素外。" }, { "ER_STRAY_NAMESPACE", "名称空间说明“{0}”=“{1}”在元素外。" }, { "ER_COULD_NOT_LOAD_RESOURCE", "无法装入“{0}”（检查 CLASSPATH），现在只使用缺省值" }, { "ER_COULD_NOT_LOAD_METHOD_PROPERTY", "无法为输出方法“{1}”装载属性文件“{0}”（检查 CLASSPATH）" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Object[][] getContents() {
/* 441 */     return contents;
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
/*     */   public static final XMLErrorResources loadResourceBundle(String className) throws MissingResourceException {
/* 456 */     Locale locale = Locale.getDefault();
/* 457 */     String suffix = getResourceSuffix(locale);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 463 */     try { return (XMLErrorResources)ResourceBundle.getBundle(className + suffix, locale); } catch (MissingResourceException e)
/*     */     
/*     */     { 
/*     */       
/*     */       try { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 473 */         return (XMLErrorResources)ResourceBundle.getBundle(className, new Locale("zh", "CN")); } catch (MissingResourceException e2)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 481 */         throw new MissingResourceException("无法装入任何资源包。", className, ""); }
/*     */        }
/*     */   
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
/*     */   private static final String getResourceSuffix(Locale locale) {
/* 498 */     String suffix = "_" + locale.getLanguage();
/* 499 */     String country = locale.getCountry();
/*     */     
/* 501 */     if (country.equals("TW")) {
/* 502 */       suffix = suffix + "_" + country;
/*     */     }
/* 504 */     return suffix;
/*     */   }
/*     */ }


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/res/XMLErrorResources_zh_CN.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */