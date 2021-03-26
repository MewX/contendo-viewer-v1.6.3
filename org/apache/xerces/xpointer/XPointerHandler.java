package org.apache.xerces.xpointer;

import java.util.ArrayList;
import java.util.HashMap;
import org.apache.xerces.impl.XMLErrorReporter;
import org.apache.xerces.util.SymbolTable;
import org.apache.xerces.util.XMLChar;
import org.apache.xerces.util.XMLSymbols;
import org.apache.xerces.xinclude.XIncludeHandler;
import org.apache.xerces.xinclude.XIncludeNamespaceSupport;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLDocumentHandler;
import org.apache.xerces.xni.XMLString;
import org.apache.xerces.xni.XNIException;
import org.apache.xerces.xni.parser.XMLConfigurationException;
import org.apache.xerces.xni.parser.XMLErrorHandler;

public final class XPointerHandler extends XIncludeHandler implements XPointerProcessor {
  protected ArrayList fXPointerParts = null;
  
  protected XPointerPart fXPointerPart = null;
  
  protected boolean fFoundMatchingPtrPart = false;
  
  protected XMLErrorReporter fXPointerErrorReporter;
  
  protected XMLErrorHandler fErrorHandler;
  
  protected SymbolTable fSymbolTable = null;
  
  private final String ELEMENT_SCHEME_NAME = "element";
  
  protected boolean fIsXPointerResolved = false;
  
  protected boolean fFixupBase = false;
  
  protected boolean fFixupLang = false;
  
  public XPointerHandler() {
    this.fXPointerParts = new ArrayList();
    this.fSymbolTable = new SymbolTable();
  }
  
  public XPointerHandler(SymbolTable paramSymbolTable, XMLErrorHandler paramXMLErrorHandler, XMLErrorReporter paramXMLErrorReporter) {
    this.fXPointerParts = new ArrayList();
    this.fSymbolTable = paramSymbolTable;
    this.fErrorHandler = paramXMLErrorHandler;
    this.fXPointerErrorReporter = paramXMLErrorReporter;
  }
  
  public void setDocumentHandler(XMLDocumentHandler paramXMLDocumentHandler) {
    this.fDocumentHandler = paramXMLDocumentHandler;
  }
  
  public void parseXPointer(String paramString) throws XNIException {
    init();
    Tokens tokens = new Tokens(this.fSymbolTable);
    Scanner scanner = new Scanner(this, this.fSymbolTable) {
        private final XPointerHandler this$0;
        
        protected void addToken(XPointerHandler.Tokens param1Tokens, int param1Int) throws XNIException {
          if (param1Int == 0 || param1Int == 1 || param1Int == 3 || param1Int == 4 || param1Int == 2) {
            super.addToken(param1Tokens, param1Int);
            return;
          } 
          this.this$0.reportError("InvalidXPointerToken", new Object[] { XPointerHandler.Tokens.access$200(param1Tokens, param1Int) });
        }
      };
    int i = paramString.length();
    boolean bool = scanner.scanExpr(this.fSymbolTable, tokens, paramString, 0, i);
    if (!bool)
      reportError("InvalidXPointerExpression", new Object[] { paramString }); 
    while (tokens.hasMore()) {
      String str1;
      ShortHandPointer shortHandPointer;
      String str2;
      String str3;
      byte b1;
      byte b2;
      String str4;
      String str5;
      String str6;
      int j = tokens.nextToken();
      switch (j) {
        case 2:
          j = tokens.nextToken();
          str1 = tokens.getTokenString(j);
          if (str1 == null)
            reportError("InvalidXPointerExpression", new Object[] { paramString }); 
          shortHandPointer = new ShortHandPointer(this.fSymbolTable);
          shortHandPointer.setSchemeName(str1);
          this.fXPointerParts.add(shortHandPointer);
          continue;
        case 3:
          j = tokens.nextToken();
          str1 = tokens.getTokenString(j);
          j = tokens.nextToken();
          str2 = tokens.getTokenString(j);
          str3 = str1 + str2;
          b1 = 0;
          b2 = 0;
          j = tokens.nextToken();
          str4 = tokens.getTokenString(j);
          if (str4 != "XPTRTOKEN_OPEN_PAREN")
            if (j == 2) {
              reportError("MultipleShortHandPointers", new Object[] { paramString });
            } else {
              reportError("InvalidXPointerExpression", new Object[] { paramString });
            }  
          b1++;
          str5 = null;
          while (tokens.hasMore()) {
            j = tokens.nextToken();
            str5 = tokens.getTokenString(j);
            if (str5 != "XPTRTOKEN_OPEN_PAREN")
              break; 
            b1++;
          } 
          j = tokens.nextToken();
          str5 = tokens.getTokenString(j);
          j = tokens.nextToken();
          str6 = tokens.getTokenString(j);
          if (str6 != "XPTRTOKEN_CLOSE_PAREN")
            reportError("SchemeDataNotFollowedByCloseParenthesis", new Object[] { paramString }); 
          b2++;
          while (tokens.hasMore() && tokens.getTokenString(tokens.peekToken()) == "XPTRTOKEN_OPEN_PAREN")
            b2++; 
          if (b1 != b2)
            reportError("UnbalancedParenthesisInXPointerExpression", new Object[] { paramString, new Integer(b1), new Integer(b2) }); 
          if (str3.equals("element")) {
            ElementSchemePointer elementSchemePointer = new ElementSchemePointer(this.fSymbolTable, this.fErrorReporter);
            elementSchemePointer.setSchemeName(str3);
            elementSchemePointer.setSchemeData(str5);
            try {
              elementSchemePointer.parseXPointer(str5);
              this.fXPointerParts.add(elementSchemePointer);
            } catch (XNIException xNIException) {
              throw new XNIException(xNIException);
            } 
            continue;
          } 
          reportWarning("SchemeUnsupported", new Object[] { str3 });
          continue;
      } 
      reportError("InvalidXPointerExpression", new Object[] { paramString });
    } 
  }
  
  public boolean resolveXPointer(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations, int paramInt) throws XNIException {
    boolean bool = false;
    if (!this.fFoundMatchingPtrPart) {
      for (byte b = 0; b < this.fXPointerParts.size(); b++) {
        this.fXPointerPart = this.fXPointerParts.get(b);
        if (this.fXPointerPart.resolveXPointer(paramQName, paramXMLAttributes, paramAugmentations, paramInt)) {
          this.fFoundMatchingPtrPart = true;
          bool = true;
        } 
      } 
    } else if (this.fXPointerPart.resolveXPointer(paramQName, paramXMLAttributes, paramAugmentations, paramInt)) {
      bool = true;
    } 
    if (!this.fIsXPointerResolved)
      this.fIsXPointerResolved = bool; 
    return bool;
  }
  
  public boolean isFragmentResolved() throws XNIException {
    boolean bool = (this.fXPointerPart != null) ? this.fXPointerPart.isFragmentResolved() : false;
    if (!this.fIsXPointerResolved)
      this.fIsXPointerResolved = bool; 
    return bool;
  }
  
  public boolean isChildFragmentResolved() throws XNIException {
    return (this.fXPointerPart != null) ? this.fXPointerPart.isChildFragmentResolved() : false;
  }
  
  public boolean isXPointerResolved() throws XNIException {
    return this.fIsXPointerResolved;
  }
  
  public XPointerPart getXPointerPart() {
    return this.fXPointerPart;
  }
  
  private void reportError(String paramString, Object[] paramArrayOfObject) throws XNIException {
    throw new XNIException(this.fErrorReporter.getMessageFormatter("http://www.w3.org/TR/XPTR").formatMessage(this.fErrorReporter.getLocale(), paramString, paramArrayOfObject));
  }
  
  private void reportWarning(String paramString, Object[] paramArrayOfObject) throws XNIException {
    this.fXPointerErrorReporter.reportError("http://www.w3.org/TR/XPTR", paramString, paramArrayOfObject, (short)0);
  }
  
  protected void initErrorReporter() {
    if (this.fXPointerErrorReporter == null)
      this.fXPointerErrorReporter = new XMLErrorReporter(); 
    if (this.fErrorHandler == null)
      this.fErrorHandler = new XPointerErrorHandler(); 
    this.fXPointerErrorReporter.putMessageFormatter("http://www.w3.org/TR/XPTR", new XPointerMessageFormatter());
  }
  
  protected void init() {
    this.fXPointerParts.clear();
    this.fXPointerPart = null;
    this.fFoundMatchingPtrPart = false;
    this.fIsXPointerResolved = false;
    initErrorReporter();
  }
  
  public ArrayList getPointerParts() {
    return this.fXPointerParts;
  }
  
  public void comment(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!isChildFragmentResolved())
      return; 
    super.comment(paramXMLString, paramAugmentations);
  }
  
  public void processingInstruction(String paramString, XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!isChildFragmentResolved())
      return; 
    super.processingInstruction(paramString, paramXMLString, paramAugmentations);
  }
  
  public void startElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    if (!resolveXPointer(paramQName, paramXMLAttributes, paramAugmentations, 0)) {
      if (this.fFixupBase)
        processXMLBaseAttributes(paramXMLAttributes); 
      if (this.fFixupLang)
        processXMLLangAttributes(paramXMLAttributes); 
      this.fNamespaceContext.setContextInvalid();
      return;
    } 
    super.startElement(paramQName, paramXMLAttributes, paramAugmentations);
  }
  
  public void emptyElement(QName paramQName, XMLAttributes paramXMLAttributes, Augmentations paramAugmentations) throws XNIException {
    if (!resolveXPointer(paramQName, paramXMLAttributes, paramAugmentations, 2)) {
      if (this.fFixupBase)
        processXMLBaseAttributes(paramXMLAttributes); 
      if (this.fFixupLang)
        processXMLLangAttributes(paramXMLAttributes); 
      this.fNamespaceContext.setContextInvalid();
      return;
    } 
    super.emptyElement(paramQName, paramXMLAttributes, paramAugmentations);
  }
  
  public void characters(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!isChildFragmentResolved())
      return; 
    super.characters(paramXMLString, paramAugmentations);
  }
  
  public void ignorableWhitespace(XMLString paramXMLString, Augmentations paramAugmentations) throws XNIException {
    if (!isChildFragmentResolved())
      return; 
    super.ignorableWhitespace(paramXMLString, paramAugmentations);
  }
  
  public void endElement(QName paramQName, Augmentations paramAugmentations) throws XNIException {
    if (!resolveXPointer(paramQName, (XMLAttributes)null, paramAugmentations, 1))
      return; 
    super.endElement(paramQName, paramAugmentations);
  }
  
  public void startCDATA(Augmentations paramAugmentations) throws XNIException {
    if (!isChildFragmentResolved())
      return; 
    super.startCDATA(paramAugmentations);
  }
  
  public void endCDATA(Augmentations paramAugmentations) throws XNIException {
    if (!isChildFragmentResolved())
      return; 
    super.endCDATA(paramAugmentations);
  }
  
  public void setProperty(String paramString, Object paramObject) throws XMLConfigurationException {
    if (paramString == "http://apache.org/xml/properties/internal/error-reporter")
      if (paramObject != null) {
        this.fXPointerErrorReporter = (XMLErrorReporter)paramObject;
      } else {
        this.fXPointerErrorReporter = null;
      }  
    if (paramString == "http://apache.org/xml/properties/internal/error-handler")
      if (paramObject != null) {
        this.fErrorHandler = (XMLErrorHandler)paramObject;
      } else {
        this.fErrorHandler = null;
      }  
    if (paramString == "http://apache.org/xml/features/xinclude/fixup-language")
      if (paramObject != null) {
        this.fFixupLang = ((Boolean)paramObject).booleanValue();
      } else {
        this.fFixupLang = false;
      }  
    if (paramString == "http://apache.org/xml/features/xinclude/fixup-base-uris")
      if (paramObject != null) {
        this.fFixupBase = ((Boolean)paramObject).booleanValue();
      } else {
        this.fFixupBase = false;
      }  
    if (paramString == "http://apache.org/xml/properties/internal/namespace-context")
      this.fNamespaceContext = (XIncludeNamespaceSupport)paramObject; 
    super.setProperty(paramString, paramObject);
  }
  
  private class Scanner {
    private static final byte CHARTYPE_INVALID = 0;
    
    private static final byte CHARTYPE_OTHER = 1;
    
    private static final byte CHARTYPE_WHITESPACE = 2;
    
    private static final byte CHARTYPE_CARRET = 3;
    
    private static final byte CHARTYPE_OPEN_PAREN = 4;
    
    private static final byte CHARTYPE_CLOSE_PAREN = 5;
    
    private static final byte CHARTYPE_MINUS = 6;
    
    private static final byte CHARTYPE_PERIOD = 7;
    
    private static final byte CHARTYPE_SLASH = 8;
    
    private static final byte CHARTYPE_DIGIT = 9;
    
    private static final byte CHARTYPE_COLON = 10;
    
    private static final byte CHARTYPE_EQUAL = 11;
    
    private static final byte CHARTYPE_LETTER = 12;
    
    private static final byte CHARTYPE_UNDERSCORE = 13;
    
    private static final byte CHARTYPE_NONASCII = 14;
    
    private final byte[] fASCIICharMap;
    
    private SymbolTable fSymbolTable;
    
    private final XPointerHandler this$0;
    
    private Scanner(XPointerHandler this$0, SymbolTable param1SymbolTable) {
      XPointerHandler.this = XPointerHandler.this;
      this.fASCIICharMap = new byte[] { 
          0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 
          2, 0, 0, 2, 0, 0, 0, 0, 0, 0, 
          0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 
          0, 0, 2, 1, 1, 1, 1, 1, 1, 1, 
          4, 5, 1, 1, 1, 6, 7, 8, 9, 9, 
          9, 9, 9, 9, 9, 9, 9, 9, 10, 1, 
          1, 11, 1, 1, 1, 12, 12, 12, 12, 12, 
          12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 
          12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 
          12, 1, 1, 1, 3, 13, 1, 12, 12, 12, 
          12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 
          12, 12, 12, 12, 12, 12, 12, 12, 12, 12, 
          12, 12, 12, 1, 1, 1, 1, 1 };
      this.fSymbolTable = param1SymbolTable;
    }
    
    private boolean scanExpr(SymbolTable param1SymbolTable, XPointerHandler.Tokens param1Tokens, String param1String, int param1Int1, int param1Int2) throws XNIException {
      byte b1 = 0;
      byte b2 = 0;
      boolean bool = false;
      String str1 = null;
      String str2 = null;
      String str3 = null;
      StringBuffer stringBuffer = new StringBuffer();
      while (param1Int1 != param1Int2) {
        char c;
        for (c = param1String.charAt(param1Int1); (c == ' ' || c == '\n' || c == '\t' || c == '\r') && ++param1Int1 != param1Int2; c = param1String.charAt(param1Int1));
        if (param1Int1 == param1Int2)
          break; 
        boolean bool1 = (c >= '') ? true : this.fASCIICharMap[c];
        switch (bool1) {
          case true:
            addToken(param1Tokens, 0);
            b1++;
            param1Int1++;
          case true:
            addToken(param1Tokens, 1);
            b2++;
            param1Int1++;
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
          case true:
            if (b1 == 0) {
              int i;
              int j = param1Int1;
              param1Int1 = scanNCName(param1String, param1Int2, param1Int1);
              if (param1Int1 == j) {
                XPointerHandler.this.reportError("InvalidShortHandPointer", new Object[] { param1String });
                return false;
              } 
              if (param1Int1 < param1Int2) {
                c = param1String.charAt(param1Int1);
              } else {
                i = -1;
              } 
              str1 = param1SymbolTable.addSymbol(param1String.substring(j, param1Int1));
              str2 = XMLSymbols.EMPTY_STRING;
              if (i == 58) {
                if (++param1Int1 == param1Int2)
                  return false; 
                i = param1String.charAt(param1Int1);
                str2 = str1;
                j = param1Int1;
                param1Int1 = scanNCName(param1String, param1Int2, param1Int1);
                if (param1Int1 == j)
                  return false; 
                if (param1Int1 < param1Int2) {
                  i = param1String.charAt(param1Int1);
                } else {
                  i = -1;
                } 
                bool = true;
                str1 = param1SymbolTable.addSymbol(param1String.substring(j, param1Int1));
              } 
              if (param1Int1 != param1Int2) {
                addToken(param1Tokens, 3);
                param1Tokens.addToken(str2);
                param1Tokens.addToken(str1);
                bool = false;
              } else if (param1Int1 == param1Int2) {
                addToken(param1Tokens, 2);
                param1Tokens.addToken(str1);
                bool = false;
              } 
              b2 = 0;
              continue;
            } 
            if (b1 > 0 && b2 == 0 && str1 != null) {
              int i = param1Int1;
              param1Int1 = scanData(param1String, stringBuffer, param1Int2, param1Int1);
              if (param1Int1 == i) {
                XPointerHandler.this.reportError("InvalidSchemeDataInXPointer", new Object[] { param1String });
                return false;
              } 
              if (param1Int1 < param1Int2) {
                c = param1String.charAt(param1Int1);
              } else {
                byte b = -1;
              } 
              str3 = param1SymbolTable.addSymbol(stringBuffer.toString());
              addToken(param1Tokens, 4);
              param1Tokens.addToken(str3);
              b1 = 0;
              stringBuffer.delete(0, stringBuffer.length());
              continue;
            } 
            return false;
        } 
      } 
      return true;
    }
    
    private int scanNCName(String param1String, int param1Int1, int param1Int2) {
      char c = param1String.charAt(param1Int2);
      if (c >= '') {
        if (!XMLChar.isNameStart(c))
          return param1Int2; 
      } else {
        byte b = this.fASCIICharMap[c];
        if (b != 12 && b != 13)
          return param1Int2; 
      } 
      while (++param1Int2 < param1Int1) {
        c = param1String.charAt(param1Int2);
        if (c >= '') {
          if (!XMLChar.isName(c))
            break; 
          continue;
        } 
        byte b = this.fASCIICharMap[c];
        if (b != 12 && b != 9 && b != 7 && b != 6 && b != 13)
          break; 
      } 
      return param1Int2;
    }
    
    private int scanData(String param1String, StringBuffer param1StringBuffer, int param1Int1, int param1Int2) {
      while (param1Int2 != param1Int1) {
        char c = param1String.charAt(param1Int2);
        boolean bool = (c >= '') ? true : this.fASCIICharMap[c];
        if (bool == 4) {
          param1StringBuffer.append(c);
          param1Int2 = scanData(param1String, param1StringBuffer, param1Int1, ++param1Int2);
          if (param1Int2 == param1Int1)
            return param1Int2; 
          c = param1String.charAt(param1Int2);
          bool = (c >= '') ? true : this.fASCIICharMap[c];
          if (bool != 5)
            return param1Int1; 
          param1StringBuffer.append((char)c);
          param1Int2++;
          continue;
        } 
        if (bool == 5)
          return param1Int2; 
        if (bool == 3) {
          c = param1String.charAt(++param1Int2);
          bool = (c >= '') ? true : this.fASCIICharMap[c];
          if (bool != 3 && bool != 4 && bool != 5)
            break; 
          param1StringBuffer.append((char)c);
          param1Int2++;
          continue;
        } 
        param1StringBuffer.append((char)c);
        param1Int2++;
      } 
      return param1Int2;
    }
    
    protected void addToken(XPointerHandler.Tokens param1Tokens, int param1Int) throws XNIException {
      param1Tokens.addToken(param1Int);
    }
  }
  
  private final class Tokens {
    private static final int XPTRTOKEN_OPEN_PAREN = 0;
    
    private static final int XPTRTOKEN_CLOSE_PAREN = 1;
    
    private static final int XPTRTOKEN_SHORTHAND = 2;
    
    private static final int XPTRTOKEN_SCHEMENAME = 3;
    
    private static final int XPTRTOKEN_SCHEMEDATA = 4;
    
    private final String[] fgTokenNames;
    
    private static final int INITIAL_TOKEN_COUNT = 256;
    
    private int[] fTokens;
    
    private int fTokenCount;
    
    private int fCurrentTokenIndex;
    
    private SymbolTable fSymbolTable;
    
    private HashMap fTokenNames;
    
    private final XPointerHandler this$0;
    
    private Tokens(XPointerHandler this$0, SymbolTable param1SymbolTable) {
      XPointerHandler.this = XPointerHandler.this;
      this.fgTokenNames = new String[] { "XPTRTOKEN_OPEN_PAREN", "XPTRTOKEN_CLOSE_PAREN", "XPTRTOKEN_SHORTHAND", "XPTRTOKEN_SCHEMENAME", "XPTRTOKEN_SCHEMEDATA" };
      this.fTokens = new int[256];
      this.fTokenCount = 0;
      this.fTokenNames = new HashMap();
      this.fSymbolTable = param1SymbolTable;
      this.fTokenNames.put(new Integer(0), "XPTRTOKEN_OPEN_PAREN");
      this.fTokenNames.put(new Integer(1), "XPTRTOKEN_CLOSE_PAREN");
      this.fTokenNames.put(new Integer(2), "XPTRTOKEN_SHORTHAND");
      this.fTokenNames.put(new Integer(3), "XPTRTOKEN_SCHEMENAME");
      this.fTokenNames.put(new Integer(4), "XPTRTOKEN_SCHEMEDATA");
    }
    
    private String getTokenString(int param1Int) {
      return (String)this.fTokenNames.get(new Integer(param1Int));
    }
    
    private void addToken(String param1String) {
      Integer integer = (Integer)this.fTokenNames.get(param1String);
      if (integer == null) {
        integer = new Integer(this.fTokenNames.size());
        this.fTokenNames.put(integer, param1String);
      } 
      addToken(integer.intValue());
    }
    
    private void addToken(int param1Int) {
      try {
        this.fTokens[this.fTokenCount] = param1Int;
      } catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException) {
        int[] arrayOfInt = this.fTokens;
        this.fTokens = new int[this.fTokenCount << 1];
        System.arraycopy(arrayOfInt, 0, this.fTokens, 0, this.fTokenCount);
        this.fTokens[this.fTokenCount] = param1Int;
      } 
      this.fTokenCount++;
    }
    
    private void rewind() {
      this.fCurrentTokenIndex = 0;
    }
    
    private boolean hasMore() {
      return (this.fCurrentTokenIndex < this.fTokenCount);
    }
    
    private int nextToken() throws XNIException {
      if (this.fCurrentTokenIndex == this.fTokenCount)
        XPointerHandler.this.reportError("XPointerProcessingError", (Object[])null); 
      return this.fTokens[this.fCurrentTokenIndex++];
    }
    
    private int peekToken() throws XNIException {
      if (this.fCurrentTokenIndex == this.fTokenCount)
        XPointerHandler.this.reportError("XPointerProcessingError", (Object[])null); 
      return this.fTokens[this.fCurrentTokenIndex];
    }
    
    private String nextTokenAsString() throws XNIException {
      String str = getTokenString(nextToken());
      if (str == null)
        XPointerHandler.this.reportError("XPointerProcessingError", (Object[])null); 
      return str;
    }
  }
}


/* Location:              /mnt/r/ConTenDoViewer.jar!/org/apache/xerces/xpointer/XPointerHandler.class
 * Java compiler version: 3 (47.0)
 * JD-Core Version:       1.1.3
 */