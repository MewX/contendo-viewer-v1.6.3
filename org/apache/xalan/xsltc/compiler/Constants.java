package org.apache.xalan.xsltc.compiler;

import org.apache.bcel.generic.InstructionConstants;

public interface Constants extends InstructionConstants {
  public static final int INTERNAL = 0;
  
  public static final int UNSUPPORTED = 1;
  
  public static final int FATAL = 2;
  
  public static final int ERROR = 3;
  
  public static final int WARNING = 4;
  
  public static final String EMPTYSTRING = "";
  
  public static final String NAMESPACE_FEATURE = "http://xml.org/sax/features/namespaces";
  
  public static final String TRANSLET_INTF = "org.apache.xalan.xsltc.Translet";
  
  public static final String TRANSLET_INTF_SIG = "Lorg/apache/xalan/xsltc/Translet;";
  
  public static final String ATTRIBUTES_SIG = "Lorg/apache/xalan/xsltc/runtime/Attributes;";
  
  public static final String NODE_ITERATOR_SIG = "Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String DOM_INTF_SIG = "Lorg/apache/xalan/xsltc/DOM;";
  
  public static final String DOM_IMPL_CLASS = "org/apache/xalan/xsltc/DOM";
  
  public static final String SAX_IMPL_CLASS = "org/apache/xalan/xsltc/DOM/SAXImpl";
  
  public static final String DOM_IMPL_SIG = "Lorg/apache/xalan/xsltc/dom/SAXImpl;";
  
  public static final String SAX_IMPL_SIG = "Lorg/apache/xalan/xsltc/dom/SAXImpl;";
  
  public static final String DOM_ADAPTER_CLASS = "org/apache/xalan/xsltc/dom/DOMAdapter";
  
  public static final String DOM_ADAPTER_SIG = "Lorg/apache/xalan/xsltc/dom/DOMAdapter;";
  
  public static final String MULTI_DOM_CLASS = "org.apache.xalan.xsltc.dom.MultiDOM";
  
  public static final String MULTI_DOM_SIG = "Lorg/apache/xalan/xsltc/dom/MultiDOM;";
  
  public static final String STRING = "java.lang.String";
  
  public static final int ACC_PUBLIC = 1;
  
  public static final int ACC_SUPER = 32;
  
  public static final int ACC_FINAL = 16;
  
  public static final int ACC_PRIVATE = 2;
  
  public static final int ACC_PROTECTED = 4;
  
  public static final int ACC_STATIC = 8;
  
  public static final String STRING_SIG = "Ljava/lang/String;";
  
  public static final String STRING_BUFFER_SIG = "Ljava/lang/StringBuffer;";
  
  public static final String OBJECT_SIG = "Ljava/lang/Object;";
  
  public static final String DOUBLE_SIG = "Ljava/lang/Double;";
  
  public static final String INTEGER_SIG = "Ljava/lang/Integer;";
  
  public static final String COLLATOR_CLASS = "java/text/Collator";
  
  public static final String COLLATOR_SIG = "Ljava/text/Collator;";
  
  public static final String NODE = "int";
  
  public static final String NODE_ITERATOR = "org.apache.xml.dtm.DTMAxisIterator";
  
  public static final String NODE_ITERATOR_BASE = "org.apache.xml.dtm.ref.DTMAxisIteratorBase";
  
  public static final String SORT_ITERATOR = "org.apache.xalan.xsltc.dom.SortingIterator";
  
  public static final String SORT_ITERATOR_SIG = "Lorg.apache.xalan.xsltc.dom.SortingIterator;";
  
  public static final String NODE_SORT_RECORD = "org.apache.xalan.xsltc.dom.NodeSortRecord";
  
  public static final String NODE_SORT_FACTORY = "org/apache/xalan/xsltc/dom/NodeSortRecordFactory";
  
  public static final String NODE_SORT_RECORD_SIG = "Lorg/apache/xalan/xsltc/dom/NodeSortRecord;";
  
  public static final String NODE_SORT_FACTORY_SIG = "Lorg/apache/xalan/xsltc/dom/NodeSortRecordFactory;";
  
  public static final String LOCALE_CLASS = "java.util.Locale";
  
  public static final String LOCALE_SIG = "Ljava/util/Locale;";
  
  public static final String STRING_VALUE_HANDLER = "org.apache.xalan.xsltc.runtime.StringValueHandler";
  
  public static final String STRING_VALUE_HANDLER_SIG = "Lorg/apache/xalan/xsltc/runtime/StringValueHandler;";
  
  public static final String OUTPUT_HANDLER = "org/apache/xml/serializer/SerializationHandler";
  
  public static final String OUTPUT_HANDLER_SIG = "Lorg/apache/xml/serializer/SerializationHandler;";
  
  public static final String FILTER_INTERFACE = "org.apache.xalan.xsltc.dom.Filter";
  
  public static final String FILTER_INTERFACE_SIG = "Lorg/apache/xalan/xsltc/dom/Filter;";
  
  public static final String UNION_ITERATOR_CLASS = "org.apache.xalan.xsltc.dom.UnionIterator";
  
  public static final String STEP_ITERATOR_CLASS = "org.apache.xalan.xsltc.dom.StepIterator";
  
  public static final String CACHED_NODE_LIST_ITERATOR_CLASS = "org.apache.xalan.xsltc.dom.CachedNodeListIterator";
  
  public static final String NTH_ITERATOR_CLASS = "org.apache.xalan.xsltc.dom.NthIterator";
  
  public static final String ABSOLUTE_ITERATOR = "org.apache.xalan.xsltc.dom.AbsoluteIterator";
  
  public static final String DUP_FILTERED_ITERATOR = "org.apache.xalan.xsltc.dom.DupFilterIterator";
  
  public static final String CURRENT_NODE_LIST_ITERATOR = "org.apache.xalan.xsltc.dom.CurrentNodeListIterator";
  
  public static final String CURRENT_NODE_LIST_FILTER = "org.apache.xalan.xsltc.dom.CurrentNodeListFilter";
  
  public static final String CURRENT_NODE_LIST_ITERATOR_SIG = "Lorg/apache/xalan/xsltc/dom/CurrentNodeListIterator;";
  
  public static final String CURRENT_NODE_LIST_FILTER_SIG = "Lorg/apache/xalan/xsltc/dom/CurrentNodeListFilter;";
  
  public static final String FILTER_STEP_ITERATOR = "org.apache.xalan.xsltc.dom.FilteredStepIterator";
  
  public static final String FILTER_ITERATOR = "org.apache.xalan.xsltc.dom.FilterIterator";
  
  public static final String SINGLETON_ITERATOR = "org.apache.xalan.xsltc.dom.SingletonIterator";
  
  public static final String MATCHING_ITERATOR = "org.apache.xalan.xsltc.dom.MatchingIterator";
  
  public static final String NODE_SIG = "I";
  
  public static final String GET_PARENT = "getParent";
  
  public static final String GET_PARENT_SIG = "(I)I";
  
  public static final String NEXT_SIG = "()I";
  
  public static final String NEXT = "next";
  
  public static final String NEXTID = "nextNodeID";
  
  public static final String MAKE_NODE = "makeNode";
  
  public static final String MAKE_NODE_LIST = "makeNodeList";
  
  public static final String GET_UNPARSED_ENTITY_URI = "getUnparsedEntityURI";
  
  public static final String STRING_TO_REAL = "stringToReal";
  
  public static final String STRING_TO_REAL_SIG = "(Ljava/lang/String;)D";
  
  public static final String STRING_TO_INT = "stringToInt";
  
  public static final String STRING_TO_INT_SIG = "(Ljava/lang/String;)I";
  
  public static final String XSLT_PACKAGE = "org.apache.xalan.xsltc";
  
  public static final String COMPILER_PACKAGE = "org.apache.xalan.xsltc.compiler";
  
  public static final String RUNTIME_PACKAGE = "org.apache.xalan.xsltc.runtime";
  
  public static final String TRANSLET_CLASS = "org.apache.xalan.xsltc.runtime.AbstractTranslet";
  
  public static final String TRANSLET_SIG = "Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;";
  
  public static final String UNION_ITERATOR_SIG = "Lorg/apache/xalan/xsltc/dom/UnionIterator;";
  
  public static final String TRANSLET_OUTPUT_SIG = "Lorg/apache/xml/serializer/SerializationHandler;";
  
  public static final String MAKE_NODE_SIG = "(I)Lorg/w3c/dom/Node;";
  
  public static final String MAKE_NODE_SIG2 = "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/w3c/dom/Node;";
  
  public static final String MAKE_NODE_LIST_SIG = "(I)Lorg/w3c/dom/NodeList;";
  
  public static final String MAKE_NODE_LIST_SIG2 = "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/w3c/dom/NodeList;";
  
  public static final String STREAM_XML_OUTPUT = "org.apache.xml.serializer.ToXMLStream";
  
  public static final String OUTPUT_BASE = "org.apache.xml.serializer.SerializerBase";
  
  public static final String LOAD_DOCUMENT_CLASS = "org.apache.xalan.xsltc.dom.LoadDocument";
  
  public static final String KEY_INDEX_CLASS = "org/apache/xalan/xsltc/dom/KeyIndex";
  
  public static final String KEY_INDEX_SIG = "Lorg/apache/xalan/xsltc/dom/KeyIndex;";
  
  public static final String DOM_INTF = "org.apache.xalan.xsltc.DOM";
  
  public static final String DOM_IMPL = "org.apache.xalan.xsltc.dom.SAXImpl";
  
  public static final String SAX_IMPL = "org.apache.xalan.xsltc.dom.SAXImpl";
  
  public static final String STRING_CLASS = "java.lang.String";
  
  public static final String OBJECT_CLASS = "java.lang.Object";
  
  public static final String BOOLEAN_CLASS = "java.lang.Boolean";
  
  public static final String STRING_BUFFER_CLASS = "java.lang.StringBuffer";
  
  public static final String STRING_WRITER = "java.io.StringWriter";
  
  public static final String WRITER_SIG = "Ljava/io/Writer;";
  
  public static final String TRANSLET_OUTPUT_BASE = "org.apache.xalan.xsltc.TransletOutputBase";
  
  public static final String CALL_FUNCTION_CLASS = "org.apache.xalan.xsltc.runtime.CallFunction";
  
  public static final String TRANSLET_OUTPUT_INTERFACE = "org.apache.xml.serializer.SerializationHandler";
  
  public static final String BASIS_LIBRARY_CLASS = "org.apache.xalan.xsltc.runtime.BasisLibrary";
  
  public static final String ATTRIBUTE_LIST_IMPL_CLASS = "org.apache.xalan.xsltc.runtime.AttributeListImpl";
  
  public static final String DOUBLE_CLASS = "java.lang.Double";
  
  public static final String INTEGER_CLASS = "java.lang.Integer";
  
  public static final String RUNTIME_NODE_CLASS = "org.apache.xalan.xsltc.runtime.Node";
  
  public static final String MATH_CLASS = "java.lang.Math";
  
  public static final String BOOLEAN_VALUE = "booleanValue";
  
  public static final String BOOLEAN_VALUE_SIG = "()Z";
  
  public static final String INT_VALUE = "intValue";
  
  public static final String INT_VALUE_SIG = "()I";
  
  public static final String DOUBLE_VALUE = "doubleValue";
  
  public static final String DOUBLE_VALUE_SIG = "()D";
  
  public static final String DOM_PNAME = "dom";
  
  public static final String NODE_PNAME = "node";
  
  public static final String TRANSLET_OUTPUT_PNAME = "handler";
  
  public static final String ITERATOR_PNAME = "iterator";
  
  public static final String DOCUMENT_PNAME = "document";
  
  public static final String TRANSLET_PNAME = "translet";
  
  public static final String INVOKE_METHOD = "invokeMethod";
  
  public static final String GET_NODE_NAME = "getNodeNameX";
  
  public static final String CHARACTERSW = "characters";
  
  public static final String GET_CHILDREN = "getChildren";
  
  public static final String GET_TYPED_CHILDREN = "getTypedChildren";
  
  public static final String CHARACTERS = "characters";
  
  public static final String APPLY_TEMPLATES = "applyTemplates";
  
  public static final String GET_NODE_TYPE = "getNodeType";
  
  public static final String GET_NODE_VALUE = "getStringValueX";
  
  public static final String GET_ELEMENT_VALUE = "getElementValue";
  
  public static final String GET_ATTRIBUTE_VALUE = "getAttributeValue";
  
  public static final String HAS_ATTRIBUTE = "hasAttribute";
  
  public static final String ADD_ITERATOR = "addIterator";
  
  public static final String SET_START_NODE = "setStartNode";
  
  public static final String RESET = "reset";
  
  public static final String ATTR_SET_SIG = "(Lorg/apache/xalan/xsltc/DOM;Lorg/apache/xml/dtm/DTMAxisIterator;Lorg/apache/xml/serializer/SerializationHandler;)V";
  
  public static final String GET_NODE_NAME_SIG = "(I)Ljava/lang/String;";
  
  public static final String CHARACTERSW_SIG = "(Ljava/lang/String;Lorg/apache/xml/serializer/SerializationHandler;)V";
  
  public static final String CHARACTERS_SIG = "(ILorg/apache/xml/serializer/SerializationHandler;)V";
  
  public static final String GET_CHILDREN_SIG = "(I)Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String GET_TYPED_CHILDREN_SIG = "(I)Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String GET_NODE_TYPE_SIG = "()S";
  
  public static final String GET_NODE_VALUE_SIG = "(I)Ljava/lang/String;";
  
  public static final String GET_ELEMENT_VALUE_SIG = "(I)Ljava/lang/String;";
  
  public static final String GET_ATTRIBUTE_VALUE_SIG = "(II)Ljava/lang/String;";
  
  public static final String HAS_ATTRIBUTE_SIG = "(II)Z";
  
  public static final String GET_ITERATOR_SIG = "()Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String NAMES_INDEX = "namesArray";
  
  public static final String NAMES_INDEX_SIG = "[Ljava/lang/String;";
  
  public static final String URIS_INDEX = "urisArray";
  
  public static final String URIS_INDEX_SIG = "[Ljava/lang/String;";
  
  public static final String TYPES_INDEX = "typesArray";
  
  public static final String TYPES_INDEX_SIG = "[I";
  
  public static final String NAMESPACE_INDEX = "namespaceArray";
  
  public static final String NAMESPACE_INDEX_SIG = "[Ljava/lang/String;";
  
  public static final String HASIDCALL_INDEX = "_hasIdCall";
  
  public static final String HASIDCALL_INDEX_SIG = "Z";
  
  public static final String TRANSLET_VERSION_INDEX = "transletVersion";
  
  public static final String TRANSLET_VERSION_INDEX_SIG = "I";
  
  public static final String DOM_FIELD = "_dom";
  
  public static final String STATIC_NAMES_ARRAY_FIELD = "_sNamesArray";
  
  public static final String STATIC_URIS_ARRAY_FIELD = "_sUrisArray";
  
  public static final String STATIC_TYPES_ARRAY_FIELD = "_sTypesArray";
  
  public static final String STATIC_NAMESPACE_ARRAY_FIELD = "_sNamespaceArray";
  
  public static final String STATIC_CHAR_DATA_FIELD = "_scharData";
  
  public static final String STATIC_CHAR_DATA_FIELD_SIG = "[C";
  
  public static final String FORMAT_SYMBOLS_FIELD = "format_symbols";
  
  public static final String ITERATOR_FIELD_SIG = "Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String NODE_FIELD = "node";
  
  public static final String NODE_FIELD_SIG = "I";
  
  public static final String EMPTYATTR_FIELD = "EmptyAttributes";
  
  public static final String ATTRIBUTE_LIST_FIELD = "attributeList";
  
  public static final String CLEAR_ATTRIBUTES = "clear";
  
  public static final String ADD_ATTRIBUTE = "addAttribute";
  
  public static final String ATTRIBUTE_LIST_IMPL_SIG = "Lorg/apache/xalan/xsltc/runtime/AttributeListImpl;";
  
  public static final String CLEAR_ATTRIBUTES_SIG = "()Lorg/apache/xalan/xsltc/runtime/AttributeListImpl;";
  
  public static final String ADD_ATTRIBUTE_SIG = "(Ljava/lang/String;Ljava/lang/String;)Lorg/apache/xalan/xsltc/runtime/AttributeListImpl;";
  
  public static final String ADD_ITERATOR_SIG = "(Lorg/apache/xml/dtm/DTMAxisIterator;)Lorg/apache/xalan/xsltc/dom/UnionIterator;";
  
  public static final String ORDER_ITERATOR = "orderNodes";
  
  public static final String ORDER_ITERATOR_SIG = "(Lorg/apache/xml/dtm/DTMAxisIterator;I)Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String SET_START_NODE_SIG = "(I)Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String NODE_COUNTER = "org.apache.xalan.xsltc.dom.NodeCounter";
  
  public static final String NODE_COUNTER_SIG = "Lorg/apache/xalan/xsltc/dom/NodeCounter;";
  
  public static final String DEFAULT_NODE_COUNTER = "org.apache.xalan.xsltc.dom.DefaultNodeCounter";
  
  public static final String DEFAULT_NODE_COUNTER_SIG = "Lorg/apache/xalan/xsltc/dom/DefaultNodeCounter;";
  
  public static final String TRANSLET_FIELD = "translet";
  
  public static final String TRANSLET_FIELD_SIG = "Lorg/apache/xalan/xsltc/runtime/AbstractTranslet;";
  
  public static final String RESET_SIG = "()Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String GET_PARAMETER = "getParameter";
  
  public static final String ADD_PARAMETER = "addParameter";
  
  public static final String PUSH_PARAM_FRAME = "pushParamFrame";
  
  public static final String PUSH_PARAM_FRAME_SIG = "()V";
  
  public static final String POP_PARAM_FRAME = "popParamFrame";
  
  public static final String POP_PARAM_FRAME_SIG = "()V";
  
  public static final String GET_PARAMETER_SIG = "(Ljava/lang/String;)Ljava/lang/Object;";
  
  public static final String ADD_PARAMETER_SIG = "(Ljava/lang/String;Ljava/lang/Object;Z)Ljava/lang/Object;";
  
  public static final String STRIP_SPACE = "stripSpace";
  
  public static final String STRIP_SPACE_INTF = "org/apache/xalan/xsltc/StripFilter";
  
  public static final String STRIP_SPACE_SIG = "Lorg/apache/xalan/xsltc/StripFilter;";
  
  public static final String STRIP_SPACE_PARAMS = "(Lorg/apache/xalan/xsltc/DOM;II)Z";
  
  public static final String GET_NODE_VALUE_ITERATOR = "getNodeValueIterator";
  
  public static final String GET_NODE_VALUE_ITERATOR_SIG = "(Lorg/apache/xml/dtm/DTMAxisIterator;ILjava/lang/String;Z)Lorg/apache/xml/dtm/DTMAxisIterator;";
  
  public static final String GET_UNPARSED_ENTITY_URI_SIG = "(Ljava/lang/String;)Ljava/lang/String;";
  
  public static final int POSITION_INDEX = 2;
  
  public static final int LAST_INDEX = 3;
  
  public static final String XMLNS_PREFIX = "xmlns";
  
  public static final String XMLNS_STRING = "xmlns:";
  
  public static final String XMLNS_URI = "http://www.w3.org/2000/xmlns/";
  
  public static final String XSLT_URI = "http://www.w3.org/1999/XSL/Transform";
  
  public static final String XHTML_URI = "http://www.w3.org/1999/xhtml";
  
  public static final String TRANSLET_URI = "http://xml.apache.org/xalan/xsltc";
  
  public static final String REDIRECT_URI = "http://xml.apache.org/xalan/redirect";
  
  public static final String FALLBACK_CLASS = "org.apache.xalan.xsltc.compiler.Fallback";
  
  public static final int RTF_INITIAL_SIZE = 32;
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xalan/xsltc/compiler/Constants.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */