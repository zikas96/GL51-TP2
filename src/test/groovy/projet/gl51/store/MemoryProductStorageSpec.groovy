package projet.gl51.store

import javax.inject.Inject
import io.micronaut.test.annotation.MicronautTest
import spock.lang.Specification

@MicronautTest
class MemoryProductStorageSpec extends Specification {

	@Inject
    ProductStorage storage


	void "empty storage returns empty list"() {
		expect:
		storage.all() == []
	}

    void "adding a product returns the product in the list with a new id"() {
		setup:
		Integer beforeSize = storage.all().size()
		Product beforeProduct = new Product(name: name, description: description, price: price, idealTemperature: idealTemperature)
		String productId = storage.save(beforeProduct)

		when:
		List<Product> products = storage.all()
		List<Product> afterProducts = products.findAll { it.id == productId }

		then:
		productId != ""
		products.size() == beforeSize + 1
		afterProducts.size() == 1
		beforeProduct.equals(afterProducts[0])

		where:
		name | description | price | idealTemperature
		"Ordi" | "Ordinateur" | 1500 | 10
		"Table" | "Grande table" | 2000 | 5
    }

	void "deleting a product removes it from the list"() {
		setup:
		Integer beforeSize = storage.all().size()
		String productId = storage.save(new Product(name: name, description: description, price: price, idealTemperature: idealTemperature))

		when:
		storage.delete(productId)
		List<Product> products = storage.all()
		Product afterProduct = products.find { it.id == productId }

		then:
		products.size() == beforeSize
		afterProduct == null

		where:
		name | description | price | idealTemperature
		"Ordi" | "Ordinateur" | 1500 | 10
		"Table" | "Grande table" | 2000 | 5
	}

	void "modifying a product changes it in the list"() {
		setup:
		String productId = storage.save(new Product(name: name, description: description, price: price, idealTemperature: idealTemperature))

		when:
		Product newProduct = new Product(name: name1, description: description1, price: price1, idealTemperature: idealTemperature1)
		storage.update(productId, newProduct)
		Product afterProduct = storage.all().find { it.id == productId }

		then:
		newProduct.equals(afterProduct)

		where:
		name | description | price | idealTemperature | name1 | description1 | price1| idealTemperature1
		"Ordi" | "Ordinateur" | 1500 | 10 | "Table" | "Grande table" | 2000 | 5
		"Table" | "Grande table" | 2000 | 5 | "Ordi" | "Ordinateur" | 1500 | 10
	}

	void "getting a product by its id returns it if it does exist"() {
		setup:
		Product beforeProduct = new Product(name: name, description: description, price: price, idealTemperature: idealTemperature)
		String productId = storage.save(beforeProduct)

		when:
		Product afterProduct = storage.getByID(productId)

		then:
		beforeProduct.equals(afterProduct)

		where:
		name | description | price | idealTemperature
		"Ordi" | "Ordinateur" | 1500 | 10
		"Table" | "Grande table" | 2000 | 5
	}

	void "getting a product by its id throws a NotExistingProductException if it does not exits"() {
		setup:
		Product beforeProduct = new Product(name: name, description: description, price: price, idealTemperature: idealTemperature)
		String productId = storage.save(beforeProduct)
		storage.delete(productId)

		when:
		Product afterProduct = storage.getByID(productId)

		then:
		thrown NotExistingProductException

		where:
		name | description | price | idealTemperature
		"Ordi" | "Ordinateur" | 1500 | 10
		"Table" | "Grande table" | 2000 | 5
	}
}
