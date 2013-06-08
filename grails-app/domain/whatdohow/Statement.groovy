package whatdohow

class Statement {
	
	static belongsto = [user: User]
	
	String statewhat
	String statedo
	String statehow
	String location

    static constraints = {
		statewhat blank: false
		statedo blank: false
		statehow blank: false
    }
}
