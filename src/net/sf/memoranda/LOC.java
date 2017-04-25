package net.sf.memoranda;

/**
Class:	LOC

Description: Class To store information and retrieve information regarding Lines of Code and relevant fields.
*/
public class LOC {
	private String _proj = null;	// the project name
    private String _class = null; // the class name
    private int _lines;	// number of lines of code
    /**
     * Constructor for LOC
     * @param isProj, the name of the Project.
     * @param isClass, the name of the class.
     * @param isLines, the number of lines of code.
     */
    public LOC(String isProj, String isClass, int isLines) {
        _proj = isProj;
        _class = isClass;
        _lines = isLines;
    }
    
    /**
     *Constructor for LOC
     *@param isProj, the name of the Project.
  */
    public LOC(String isProj) {
        _proj = isProj;         
    }
    
    /**
    Method: getProject
    Inputs: none
    Returns: String

    Description: Returns the name of the project for the given LOC object.
  */
    public String getProject() {
        return _proj;
    }
    
    /**
    Method: getLinesClass
    Inputs: None
    Returns: String

    Description: Returns the Class name within the LOC Object
  */
    public String getLinesClass() {
        return _class;
    }
    /**
    Method: getLines
    Inputs: None
    Returns: int

    Description: Returns the number of lines of code within the LOC Object.
  */
    public int getLines() {
    	return _lines;
    }
}
