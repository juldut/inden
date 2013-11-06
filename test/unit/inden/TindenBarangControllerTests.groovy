package inden



import org.junit.*
import grails.test.mixin.*

@TestFor(TindenBarangController)
@Mock(TindenBarang)
class TindenBarangControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/tindenBarang/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.tindenBarangInstanceList.size() == 0
        assert model.tindenBarangInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.tindenBarangInstance != null
    }

    void testSave() {
        controller.save()

        assert model.tindenBarangInstance != null
        assert view == '/tindenBarang/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/tindenBarang/show/1'
        assert controller.flash.message != null
        assert TindenBarang.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/tindenBarang/list'

        populateValidParams(params)
        def tindenBarang = new TindenBarang(params)

        assert tindenBarang.save() != null

        params.id = tindenBarang.id

        def model = controller.show()

        assert model.tindenBarangInstance == tindenBarang
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/tindenBarang/list'

        populateValidParams(params)
        def tindenBarang = new TindenBarang(params)

        assert tindenBarang.save() != null

        params.id = tindenBarang.id

        def model = controller.edit()

        assert model.tindenBarangInstance == tindenBarang
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/tindenBarang/list'

        response.reset()

        populateValidParams(params)
        def tindenBarang = new TindenBarang(params)

        assert tindenBarang.save() != null

        // test invalid parameters in update
        params.id = tindenBarang.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/tindenBarang/edit"
        assert model.tindenBarangInstance != null

        tindenBarang.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/tindenBarang/show/$tindenBarang.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        tindenBarang.clearErrors()

        populateValidParams(params)
        params.id = tindenBarang.id
        params.version = -1
        controller.update()

        assert view == "/tindenBarang/edit"
        assert model.tindenBarangInstance != null
        assert model.tindenBarangInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/tindenBarang/list'

        response.reset()

        populateValidParams(params)
        def tindenBarang = new TindenBarang(params)

        assert tindenBarang.save() != null
        assert TindenBarang.count() == 1

        params.id = tindenBarang.id

        controller.delete()

        assert TindenBarang.count() == 0
        assert TindenBarang.get(tindenBarang.id) == null
        assert response.redirectedUrl == '/tindenBarang/list'
    }
}
