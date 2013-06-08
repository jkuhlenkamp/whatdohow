package whatdohow

class User {
	
	static hasMany = [statements: Statement]
	
	String name

    static constraints = {
    }
}
