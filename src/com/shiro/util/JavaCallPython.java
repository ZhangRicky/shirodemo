package com.shiro.util;

import org.python.core.Py;
import org.python.core.PySystemState;
import org.python.util.PythonInterpreter;

/**
 * Javaµ÷ÓÃpythonÊ¾Àý
 * @author Mark
 *
 */
public class JavaCallPython {
	public static void main(String[] args) {
		PythonInterpreter interpreter = new PythonInterpreter();   
	    PySystemState sys = Py.getSystemState();   
//	    sys.path.add("D:\\jython2.5.2\\Lib");  
	    System.out.println(sys.path.toString());   
	    
	    String py="py =['physics', 'chemistry', 1997, 2000]";
	    interpreter.exec("print("+py+")");
	    
	      
//	    interpreter.exec("import sys");  
//	    interpreter.exec("print sys.path");  
//	    interpreter.exec("import urllib");  
//	    interpreter.exec("print urllib");  
	}
}
