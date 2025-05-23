// Generated from MiniLang.g4 by ANTLR 4.4

	package inf5153.miniLang.parser ;	
	
	import inf5153.miniLang.ast.* ; 

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class MiniLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__12=1, T__11=2, T__10=3, T__9=4, T__8=5, T__7=6, T__6=7, T__5=8, T__4=9, 
		T__3=10, T__2=11, T__1=12, T__0=13, IF=14, THEN=15, ELSE=16, WHILE=17, 
		PRINT=18, READ=19, NUMBER=20, IDENTIFIER=21, WS=22, STRINGLITERAL=23;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'"
	};
	public static final String[] ruleNames = {
		"T__12", "T__11", "T__10", "T__9", "T__8", "T__7", "T__6", "T__5", "T__4", 
		"T__3", "T__2", "T__1", "T__0", "IF", "THEN", "ELSE", "WHILE", "PRINT", 
		"READ", "NUMBER", "IDENTIFIER", "WS", "STRINGLITERAL"
	};


	public MiniLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "MiniLang.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\31\u0085\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2"+
		"\3\2\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3"+
		"\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\6\25m\n\25"+
		"\r\25\16\25n\3\26\6\26r\n\26\r\26\16\26s\3\27\6\27w\n\27\r\27\16\27x\3"+
		"\27\3\27\3\30\3\30\7\30\177\n\30\f\30\16\30\u0082\13\30\3\30\3\30\2\2"+
		"\31\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35"+
		"\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\3\2\6\3\2\62;\4\2C\\c|\5\2"+
		"\13\f\17\17\"\"\4\2\f\f$$\u0088\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2"+
		"\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2"+
		"\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2"+
		"\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2"+
		"\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\3\61\3\2\2\2\5\63\3\2\2\2\7\66\3\2\2"+
		"\2\t8\3\2\2\2\13:\3\2\2\2\r=\3\2\2\2\17?\3\2\2\2\21A\3\2\2\2\23C\3\2\2"+
		"\2\25E\3\2\2\2\27G\3\2\2\2\31I\3\2\2\2\33K\3\2\2\2\35M\3\2\2\2\37P\3\2"+
		"\2\2!U\3\2\2\2#Z\3\2\2\2%`\3\2\2\2\'f\3\2\2\2)l\3\2\2\2+q\3\2\2\2-v\3"+
		"\2\2\2/|\3\2\2\2\61\62\7\61\2\2\62\4\3\2\2\2\63\64\7#\2\2\64\65\7?\2\2"+
		"\65\6\3\2\2\2\66\67\7=\2\2\67\b\3\2\2\289\7}\2\29\n\3\2\2\2:;\7?\2\2;"+
		"<\7?\2\2<\f\3\2\2\2=>\7?\2\2>\16\3\2\2\2?@\7\177\2\2@\20\3\2\2\2AB\7*"+
		"\2\2B\22\3\2\2\2CD\7+\2\2D\24\3\2\2\2EF\7,\2\2F\26\3\2\2\2GH\7-\2\2H\30"+
		"\3\2\2\2IJ\7.\2\2J\32\3\2\2\2KL\7/\2\2L\34\3\2\2\2MN\7k\2\2NO\7h\2\2O"+
		"\36\3\2\2\2PQ\7v\2\2QR\7j\2\2RS\7g\2\2ST\7p\2\2T \3\2\2\2UV\7g\2\2VW\7"+
		"n\2\2WX\7u\2\2XY\7g\2\2Y\"\3\2\2\2Z[\7y\2\2[\\\7j\2\2\\]\7k\2\2]^\7n\2"+
		"\2^_\7g\2\2_$\3\2\2\2`a\7r\2\2ab\7t\2\2bc\7k\2\2cd\7p\2\2de\7v\2\2e&\3"+
		"\2\2\2fg\7t\2\2gh\7g\2\2hi\7c\2\2ij\7f\2\2j(\3\2\2\2km\t\2\2\2lk\3\2\2"+
		"\2mn\3\2\2\2nl\3\2\2\2no\3\2\2\2o*\3\2\2\2pr\t\3\2\2qp\3\2\2\2rs\3\2\2"+
		"\2sq\3\2\2\2st\3\2\2\2t,\3\2\2\2uw\t\4\2\2vu\3\2\2\2wx\3\2\2\2xv\3\2\2"+
		"\2xy\3\2\2\2yz\3\2\2\2z{\b\27\2\2{.\3\2\2\2|\u0080\7$\2\2}\177\n\5\2\2"+
		"~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7$\2\2\u0084\60\3\2\2\2"+
		"\7\2nsx\u0080\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}