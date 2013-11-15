package inden



import org.junit.*
import grails.test.mixin.*

@TestFor(MloginMloginRoleController)
@Mock(MloginMloginRole)
class MloginMloginRoleControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/mloginMloginRole/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.mloginMloginRoleInstanceList.size() == 0
        assert model.mloginMloginRoleInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.mloginMloginRoleInstance != null
    }

    void testSave() {
        controller.save()

        assert model.mloginMloginRoleInstance != null
        assert view == '/mloginMloginRole/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/mloginMloginRole/show/1'
        assert controller.flash.message != null
        assert MloginMloginRole.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/mloginMloginRole/list'

        populateValidParams(params)
        def mloginMloginRole = new MloginMloginRole(params)

        assert mloginMloginRole.save() != null

        params.id = mloginMloginRole.id

        def model = controller.show()

        assert model.mloginMloginRoleInstance == mloginMloginRole
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/mloginMloginRole/list'

        populateValidParams(params)
        def mloginMloginRole = new MloginMloginRole(params)

        assert mloginMloginRole.save() != null

        params.id = mloginMloginRole.id

        def model = controller.edit()

        assert model.mloginMloginRoleInstance == mloginMloginRole
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/mloginMloginRole/list'

        response.reset()

        populateValidParams(params)
        def mloginMloginRole = new MloginMloginRole(params)

        assert mloginMloginRole.save() != null

        // test invalid parameters in update
        params.id = mloginMloginRole.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/mloginMloginRole/edit"
        assert model.mloginMloginRoleInstance != null

        mloginMloginRole.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/mloginMloginRole/show/$mloginMloginRole.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        mloginMloginRole.clearErrors()

        populateValidParams(params)
        params.id = mloginMloginRole.id
        params.version = -1
        controller.update()

        assert view == "/mloginMloginRole/edit"
        assert model.mloginMloginRoleInstance != null
        assert model.mloginMloginRoleInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/mloginMloginRole/list'

        response.reset()

        populateValidParams(params)
        def mloginMloginRole = new MloginMloginRole(params)

        assert mloginMloginRole.save() != null
        assert MloginMloginRole.count() == 1

        params.id = mloginMloginRole.id

        controller.delete()

        assert MloginMloginRole.count() == 0
        assert MloginMloginRole.get(mloginMloginRole.id) == null
        assert response.redirectedUrl == '/mloginMloginRole/list'
    }
}
