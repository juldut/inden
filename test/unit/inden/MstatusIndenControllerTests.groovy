package inden



import org.junit.*
import grails.test.mixin.*

@TestFor(MstatusIndenController)
@Mock(MstatusInden)
class MstatusIndenControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/mstatusInden/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.mstatusIndenInstanceList.size() == 0
        assert model.mstatusIndenInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.mstatusIndenInstance != null
    }

    void testSave() {
        controller.save()

        assert model.mstatusIndenInstance != null
        assert view == '/mstatusInden/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/mstatusInden/show/1'
        assert controller.flash.message != null
        assert MstatusInden.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/mstatusInden/list'

        populateValidParams(params)
        def mstatusInden = new MstatusInden(params)

        assert mstatusInden.save() != null

        params.id = mstatusInden.id

        def model = controller.show()

        assert model.mstatusIndenInstance == mstatusInden
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/mstatusInden/list'

        populateValidParams(params)
        def mstatusInden = new MstatusInden(params)

        assert mstatusInden.save() != null

        params.id = mstatusInden.id

        def model = controller.edit()

        assert model.mstatusIndenInstance == mstatusInden
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/mstatusInden/list'

        response.reset()

        populateValidParams(params)
        def mstatusInden = new MstatusInden(params)

        assert mstatusInden.save() != null

        // test invalid parameters in update
        params.id = mstatusInden.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/mstatusInden/edit"
        assert model.mstatusIndenInstance != null

        mstatusInden.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/mstatusInden/show/$mstatusInden.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        mstatusInden.clearErrors()

        populateValidParams(params)
        params.id = mstatusInden.id
        params.version = -1
        controller.update()

        assert view == "/mstatusInden/edit"
        assert model.mstatusIndenInstance != null
        assert model.mstatusIndenInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/mstatusInden/list'

        response.reset()

        populateValidParams(params)
        def mstatusInden = new MstatusInden(params)

        assert mstatusInden.save() != null
        assert MstatusInden.count() == 1

        params.id = mstatusInden.id

        controller.delete()

        assert MstatusInden.count() == 0
        assert MstatusInden.get(mstatusInden.id) == null
        assert response.redirectedUrl == '/mstatusInden/list'
    }
}
