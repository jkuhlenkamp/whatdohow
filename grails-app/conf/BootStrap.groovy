import java.nio.file.attribute.UserDefinedFileAttributeView;
import whatdohow.Statement
import whatdohow.User

class BootStrap {

    def init = { servletContext ->
		def e = new User(name:"Erik").save()
		def j = new User(name:"J?").save()
		
		new Statement(
			user: j,
			statewhat: "Wings",
			statedo: "eat",
			statehow: "fast",
			location: "BadUsPub",
		).save()
		
		new Statement(
			user: j,
			statewhat: "Fruits",
			statedo: "buy",
			statehow: "fresh",
			location: "Fairway",
		).save()
		
		new Statement(
			user: j,
			statewhat: "Groceries",
			statedo: "shop",
			statehow: "expensive",
			location: "Fairway",
		).save()
		
		new Statement(
			user: j,
			statewhat: "TV",
			statedo: "watch",
			statehow: "crowded",
			location: "BadUsPub",
		).save()
		
		new Statement(
			user: j,
			statewhat: "Statue of liberty",
			statedo: "sightseeing",
			statehow: "spectacular",
			location: "Liberty Island",
		).save()
		
		new Statement(
			user: j,
			statewhat: "Banana",
			statedo: "buy",
			statehow: "cheap",
			location: "Pormello",
		).save()
		
		new Statement(
			user: j,
			statewhat: "Club",
			statedo: "dance",
			statehow: "posh",
			location: "Cassiopeia",
		).save()
		
		new Statement(
			user: j,
			statewhat: "Beer",
			statedo: "dance",
			statehow: "cheap",
			location: "DrinkingHole",
		).save()
		
		new Statement(
			user: j,
			statewhat: "World Trade Center",
			statedo: "sightseeing",
			statehow: "Big",
			location: "World Trade Center",
		).save()
		
    }
    def destroy = {
    }
}
