package projet.gl51.store

import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Delete
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Patch
import io.micronaut.http.annotation.Post

import javax.inject.Inject

import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus


@Controller("/store/product")
class ProductController {

	@Inject
	ProductStorage storage

    @Get("/")
    List<Product> index() {
        storage.all()
    }

    @Get("/{id}")
    HttpResponse<Product> get(String id) {
		try {
			HttpResponse.ok(storage.getByID(id))
		}
		catch(NotExistingProductException e) {
			HttpResponse.notFound()
		}
    }

    @Post("/")
    String create(@Body Product p) {
        storage.save(p)
    }

    @Patch("/{id}")
    HttpStatus update(String id, @Body Product p) {
		try {
			storage.update(id, p)
			HttpStatus.NO_CONTENT
		}
        catch(NotExistingProductException e){
			HttpStatus.NOT_FOUND
		}
    }

    @Delete("/{id}")
    HttpStatus delete(String id) {
		storage.delete(id)
		HttpStatus.OK
    }
}