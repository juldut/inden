package inden



import org.junit.*
import grails.test.mixin.*

@TestFor(MloginController)
@Mock(Mlogin)
class MloginControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/mlogin/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.mloginInstanceList.size() == 0
        assert model.mloginInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.mloginInstance != null
    }

    void testSave() {
        controller.save()

        assert model.mloginInstance != null
        assert view == '/mlogin/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/mlogin/show/1'
        assert controller.flash.message != null
        assert Mlogin.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/mlogin/list'

        populateValidParams(params)
        def mlogin = new Mlogin(params)

        assert mlogin.save() != null

        params.id = mlogin.id

        def model = controller.show()

        assert model.mloginInstance == mlogin
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/mlogin/list'

        populateValidParams(params)
        def mlogin = new Mlogin(params)

        assert mlogin.save() != null

        params.id = mlogin.id

        def model = controller.edit()

        assert model.mloginInstance == mlogin
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/mlogin/list'

        response.reset()

        populateValidParams(params)
        def mlogin = new Mlogin(params)

        assert mlogin.save() != null

        // test invalid parameters in update
        params.id = mlogin.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/mlogin/edit"
        assert model.mloginInstance != null

        mlogin.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/mlogin/show/$mlogin.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        mlogin.clearErrors()

        populateValidParams(params)
        params.id = mlogin.id
        params.version = -1
        controller.update()

        assert view == "/mlogin/edit"
        assert model.mloginInstance != null
        assert model.mloginInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/mlogin/list'

        response.reset()

        populateValidParams(params)
        def mlogin = new Mlogin(params)

        assert mlogin.save() != null
        assert Mlogin.count() == 1

        params.id = mlogin.id

        controller.delete()

        assert Mlogin.count() == 0
        assert Mlogin.get(mlogin.id) == null
        assert response.redirectedUrl == '/mlogin/list'
    }
}
