package whatdohow



import org.junit.*
import grails.test.mixin.*

@TestFor(StatementController)
@Mock(Statement)
class StatementControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/statement/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.statementInstanceList.size() == 0
        assert model.statementInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.statementInstance != null
    }

    void testSave() {
        controller.save()

        assert model.statementInstance != null
        assert view == '/statement/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/statement/show/1'
        assert controller.flash.message != null
        assert Statement.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/statement/list'

        populateValidParams(params)
        def statement = new Statement(params)

        assert statement.save() != null

        params.id = statement.id

        def model = controller.show()

        assert model.statementInstance == statement
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/statement/list'

        populateValidParams(params)
        def statement = new Statement(params)

        assert statement.save() != null

        params.id = statement.id

        def model = controller.edit()

        assert model.statementInstance == statement
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/statement/list'

        response.reset()

        populateValidParams(params)
        def statement = new Statement(params)

        assert statement.save() != null

        // test invalid parameters in update
        params.id = statement.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/statement/edit"
        assert model.statementInstance != null

        statement.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/statement/show/$statement.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        statement.clearErrors()

        populateValidParams(params)
        params.id = statement.id
        params.version = -1
        controller.update()

        assert view == "/statement/edit"
        assert model.statementInstance != null
        assert model.statementInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/statement/list'

        response.reset()

        populateValidParams(params)
        def statement = new Statement(params)

        assert statement.save() != null
        assert Statement.count() == 1

        params.id = statement.id

        controller.delete()

        assert Statement.count() == 0
        assert Statement.get(statement.id) == null
        assert response.redirectedUrl == '/statement/list'
    }
}
