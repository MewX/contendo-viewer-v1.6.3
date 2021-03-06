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
/*     */ public class XMLErrorResources_pl
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
/* 179 */   public static final Object[][] contents = new Object[][] { { "ER0000", "{0}" }, { "ER_FUNCTION_NOT_SUPPORTED", "Nieobs??ugiwana funkcja!" }, { "ER_CANNOT_OVERWRITE_CAUSE", "Nie mo??na nadpisa?? przyczyny" }, { "ER_NO_DEFAULT_IMPL", "Nie znaleziono domy??lnej implementacji" }, { "ER_CHUNKEDINTARRAY_NOT_SUPPORTED", "ChunkedIntArray({0}) nie jest obecnie obs??ugiwane" }, { "ER_OFFSET_BIGGER_THAN_SLOT", "Przesuni??cie wi??ksze ni?? szczelina" }, { "ER_COROUTINE_NOT_AVAIL", "Koprocedura niedost??pna, id={0}" }, { "ER_COROUTINE_CO_EXIT", "CoroutineManager otrzyma?? ????danie co_exit()" }, { "ER_COJOINROUTINESET_FAILED", "co_joinCoroutineSet() nie powiod??o si??" }, { "ER_COROUTINE_PARAM", "B????d parametru koprocedury ({0})" }, { "ER_PARSER_DOTERMINATE_ANSWERS", "\nNIEOCZEKIWANE: Analizator doTerminate odpowiada {0}" }, { "ER_NO_PARSE_CALL_WHILE_PARSING", "Nie mo??na wywo??a?? parse podczas analizowania" }, { "ER_TYPED_ITERATOR_AXIS_NOT_IMPLEMENTED", "B????d: iterator okre??lonego typu dla osi {0} nie jest zaimplementowany" }, { "ER_ITERATOR_AXIS_NOT_IMPLEMENTED", "B????d: iterator dla osi {0} nie jest zaimplementowany" }, { "ER_ITERATOR_CLONE_NOT_SUPPORTED", "Kopia iteratora nie jest obs??ugiwana" }, { "ER_UNKNOWN_AXIS_TYPE", "Nieznany typ przej??cia osi {0}" }, { "ER_AXIS_NOT_SUPPORTED", "Nieobs??ugiwane przej??cie osi: {0}" }, { "ER_NO_DTMIDS_AVAIL", "Nie ma wi??cej dost??pnych identyfikator??w DTM" }, { "ER_NOT_SUPPORTED", "Nieobs??ugiwane: {0}" }, { "ER_NODE_NON_NULL", "W??ze?? musi by?? niepusty dla getDTMHandleFromNode" }, { "ER_COULD_NOT_RESOLVE_NODE", "Nie mo??na przet??umaczy?? w??z??a na uchwyt" }, { "ER_STARTPARSE_WHILE_PARSING", "Nie mo??na wywo??a?? startParse podczas analizowania" }, { "ER_STARTPARSE_NEEDS_SAXPARSER", "startParse potrzebuje niepustego SAXParser" }, { "ER_COULD_NOT_INIT_PARSER", "nie mo??na zainicjowa?? analizatora" }, { "ER_EXCEPTION_CREATING_POOL", "wyj??tek podczas tworzenia nowej instancji dla puli" }, { "ER_PATH_CONTAINS_INVALID_ESCAPE_SEQUENCE", "??cie??ka zawiera niepoprawn?? sekwencj?? o zmienionym znaczeniu" }, { "ER_SCHEME_REQUIRED", "Schemat jest wymagany!" }, { "ER_NO_SCHEME_IN_URI", "Nie znaleziono schematu w URI {0}" }, { "ER_NO_SCHEME_INURI", "Nie znaleziono schematu w URI" }, { "ER_PATH_INVALID_CHAR", "??cie??ka zawiera niepoprawny znak {0}" }, { "ER_SCHEME_FROM_NULL_STRING", "Nie mo??na ustawi?? schematu z pustego ci??gu znak??w" }, { "ER_SCHEME_NOT_CONFORMANT", "Schemat nie jest zgodny." }, { "ER_HOST_ADDRESS_NOT_WELLFORMED", "Host nie jest poprawnie skonstruowanym adresem" }, { "ER_PORT_WHEN_HOST_NULL", "Nie mo??na ustawi?? portu, kiedy host jest pusty" }, { "ER_INVALID_PORT", "Niepoprawny numer portu" }, { "ER_FRAG_FOR_GENERIC_URI", "Fragment mo??na ustawi?? tylko dla og??lnego URI" }, { "ER_FRAG_WHEN_PATH_NULL", "Nie mo??na ustawi?? fragmentu, kiedy ??cie??ka jest pusta" }, { "ER_FRAG_INVALID_CHAR", "Fragment zawiera niepoprawny znak" }, { "ER_PARSER_IN_USE", "Analizator jest ju?? u??ywany" }, { "ER_CANNOT_CHANGE_WHILE_PARSING", "Nie mo??na zmieni?? {0} {1} podczas analizowania" }, { "ER_SELF_CAUSATION_NOT_PERMITTED", "Powodowanie siebie jest niedozwolone" }, { "ER_NO_USERINFO_IF_NO_HOST", "Nie mo??na poda?? informacji o u??ytkowniku, je??li nie podano hosta" }, { "ER_NO_PORT_IF_NO_HOST", "Nie mo??na poda?? portu, je??li nie podano hosta" }, { "ER_NO_QUERY_STRING_IN_PATH", "Tekstu zapytania nie mo??na poda?? w tek??cie ??cie??ki i zapytania" }, { "ER_NO_FRAGMENT_STRING_IN_PATH", "Nie mo??na poda?? fragmentu jednocze??nie w ??cie??ce i fragmencie" }, { "ER_CANNOT_INIT_URI_EMPTY_PARMS", "Nie mo??na zainicjowa?? URI z pustymi parametrami" }, { "ER_METHOD_NOT_SUPPORTED", "Metoda nie jest jeszcze obs??ugiwana" }, { "ER_INCRSAXSRCFILTER_NOT_RESTARTABLE", "IncrementalSAXSource_Filter nie jest obecnie mo??liwy do ponownego uruchomienia" }, { "ER_XMLRDR_NOT_BEFORE_STARTPARSE", "XMLReader nie przed ????daniem startParse" }, { "ER_AXIS_TRAVERSER_NOT_SUPPORTED", "Nieobs??ugiwane przej??cie osi: {0}" }, { "ER_ERRORHANDLER_CREATED_WITH_NULL_PRINTWRITER", "Utworzono ListingErrorHandler z pustym PrintWriter!" }, { "ER_SYSTEMID_UNKNOWN", "Nieznany identyfikator systemu" }, { "ER_LOCATION_UNKNOWN", "Po??o??enie b????du jest nieznane" }, { "ER_PREFIX_MUST_RESOLVE", "Przedrostek musi da?? si?? przet??umaczy?? na przestrze?? nazw: {0}" }, { "ER_CREATEDOCUMENT_NOT_SUPPORTED", "Funkcja createDocument() nie jest obs??ugiwana w XPathContext!" }, { "ER_CHILD_HAS_NO_OWNER_DOCUMENT", "Potomek atrybutu nie ma dokumentu w??a??ciciela!" }, { "ER_CHILD_HAS_NO_OWNER_DOCUMENT_ELEMENT", "Potomek atrybutu nie ma elementu dokumentu w??a??ciciela!" }, { "ER_CANT_OUTPUT_TEXT_BEFORE_DOC", "Ostrze??enie: nie mo??na wyprowadzi?? tekstu przed elementem dokumentu!  Ignoruj??..." }, { "ER_CANT_HAVE_MORE_THAN_ONE_ROOT", "Nie mo??na mie?? wi??cej ni?? jeden element g????wny w DOM!" }, { "ER_ARG_LOCALNAME_NULL", "Argument 'localName' jest pusty" }, { "ER_ARG_LOCALNAME_INVALID", "Nazwa lokalna w QNAME powinna by?? poprawn?? nazw?? NCName" }, { "ER_ARG_PREFIX_INVALID", "Przedrostek w QNAME powinien by?? poprawn?? nazw?? NCName" }, { "BAD_CODE", "Parametr createMessage by?? spoza zakresu" }, { "FORMAT_FAILED", "Podczas wywo??ania messageFormat zg??oszony zosta?? wyj??tek" }, { "line", "Wiersz: " }, { "column", "Kolumna " }, { "ER_SERIALIZER_NOT_CONTENTHANDLER", "Klasa szereguj??ca ''{0}'' nie implementuje procedury obs??ugi org.xml.sax.ContentHandler." }, { "ER_RESOURCE_COULD_NOT_FIND", "Nie mo??na znale???? zasobu [ {0} ].\n {1}" }, { "ER_RESOURCE_COULD_NOT_LOAD", "Zas??b [ {0} ] nie m??g?? za??adowa??: {1} \n {2} \t {3}" }, { "ER_BUFFER_SIZE_LESSTHAN_ZERO", "Wielko???? bufora <=0" }, { "ER_INVALID_UTF16_SURROGATE", "Wykryto niepoprawny surogat UTF-16: {0} ?" }, { "ER_OIERROR", "B????d we/wy" }, { "ER_ILLEGAL_ATTRIBUTE_POSITION", "Nie mo??na doda?? atrybutu {0} po w??z??ach potomnych ani przed wyprodukowaniem elementu.  Atrybut zostanie zignorowany." }, { "ER_NAMESPACE_PREFIX", "Nie zadeklarowano przestrzeni nazw dla przedrostka ''{0}''." }, { "ER_STRAY_ATTIRBUTE", "Atrybut ''{0}'' poza elementem." }, { "ER_STRAY_NAMESPACE", "Deklaracja przestrzeni nazw ''{0}''=''{1}'' poza elementem." }, { "ER_COULD_NOT_LOAD_RESOURCE", "Nie mo??na za??adowa?? ''{0}'' (sprawd?? CLASSPATH), u??ywane s?? teraz warto??ci domy??lne" }, { "ER_COULD_NOT_LOAD_METHOD_PROPERTY", "Nie mo??na za??adowa?? pliku w??a??ciwo??ci ''{0}'' dla metody wyj??ciowej ''{1}'' (sprawd?? CLASSPATH)" } };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
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
/* 473 */         return (XMLErrorResources)ResourceBundle.getBundle(className, new Locale("pl", "PL")); } catch (MissingResourceException e2)
/*     */       
/*     */       { 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 481 */         throw new MissingResourceException("Could not load any resource bundles.", className, ""); }
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


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xml/res/XMLErrorResources_pl.class
 * Java compiler version: 1 (45.3)
 * JD-Core Version:       1.1.3
 */