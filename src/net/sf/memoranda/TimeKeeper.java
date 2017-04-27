package net.sf.memoranda;

/**
Class:	Time Keeper

Description: Class To store information and retrieve information regarding Lines of Code and relevant fields.
*/
public class TimeKeeper {
	private String _proj = null;	// the project name
    private String _phase = null; // the phase name
    private int _hours;	// number of hours spent in phase
    /**
     * Constructor for LOC
     * @param isProj, the name of the project.
     * @param isPhase, the name of the PSP phase.
     * @param isHours, the number of hours in phase.
     */
    public TimeKeeper(String isProj, String isPhase, int isHours) {
        _proj = isProj;
        _phase = isPhase;
        _hours = isHours;
    }
    
    /**
     *Constructor for TimeKeeper
     *@param isProj, the name of the Project.
  */
    public TimeKeeper(String isProj) {
        _proj = isProj;         
    }
    
    /**
    Method: getProj
    Inputs: none
    Returns: String

    Description: Returns the name of the Project for the given Time Keeper object.
  */
    public String getProj() {
        return _proj;
    }
    
    /**
    Method: getPhase
    Inputs: None
    Returns: String

    Description: Returns the name of the phase.
  */
    public String getPhase() {
        return _phase;
    }
    /**
    Method: getHours
    Inputs: None
    Returns: int

    Description: Returns the number of hours spent within a phase of PSP.
  */
    public int getHours() {
    	return _hours;
    }
}
