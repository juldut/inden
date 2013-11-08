package inden



import org.junit.*
import grails.test.mixin.*

@TestFor(ThistoryIndenController)
@Mock(ThistoryInden)
class ThistoryIndenControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/thistoryInden/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.thistoryIndenInstanceList.size() == 0
        assert model.thistoryIndenInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.thistoryIndenInstance != null
    }

    void testSave() {
        controller.save()

        assert model.thistoryIndenInstance != null
        assert view == '/thistoryInden/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/thistoryInden/show/1'
        assert controller.flash.message != null
        assert ThistoryInden.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/thistoryInden/list'

        populateValidParams(params)
        def thistoryInden = new ThistoryInden(params)

        assert thistoryInden.save() != null

        params.id = thistoryInden.id

        def model = controller.show()

        assert model.thistoryIndenInstance == thistoryInden
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/thistoryInden/list'

        populateValidParams(params)
        def thistoryInden = new ThistoryInden(params)

        assert thistoryInden.save() != null

        params.id = thistoryInden.id

        def model = controller.edit()

        assert model.thistoryIndenInstance == thistoryInden
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/thistoryInden/list'

        response.reset()

        populateValidParams(params)
        def thistoryInden = new ThistoryInden(params)

        assert thistoryInden.save() != null

        // test invalid parameters in update
        params.id = thistoryInden.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/thistoryInden/edit"
        assert model.thistoryIndenInstance != null

        thistoryInden.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/thistoryInden/show/$thistoryInden.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        thistoryInden.clearErrors()

        populateValidParams(params)
        params.id = thistoryInden.id
        params.version = -1
        controller.update()

        assert view == "/thistoryInden/edit"
        assert model.thistoryIndenInstance != null
        assert model.thistoryIndenInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/thistoryInden/list'

        response.reset()

        populateValidParams(params)
        def thistoryInden = new ThistoryInden(params)

        assert thistoryInden.save() != null
        assert ThistoryInden.count() == 1

        params.id = thistoryInden.id

        controller.delete()

        assert ThistoryInden.count() == 0
        assert ThistoryInden.get(thistoryInden.id) == null
        assert response.redirectedUrl == '/thistoryInden/list'
    }
}
