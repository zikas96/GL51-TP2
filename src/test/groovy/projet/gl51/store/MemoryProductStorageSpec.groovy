package projet.gl51.store

import spock.lang.Specification

class MemoryProductStorageSpec extends Specification {

    ProductStorage storage = new MemoryProductStorage()


    void "test save"() {
		setup:
		Integer beforeSize = storage.all().size()
		Product beforeProduct = new Product(name: name, description: description, price: price, idealTemperature: idealTemperature)
		String productId = storage.save(beforeProduct)
		
		when:
		List<Product> products = storage.all()
		
		then:
		products.size() == beforeSize + 1
		Product afterProduct = products.find { it.id == productId }
		beforeProduct.equals(afterProduct)
		
		where:
		name | description | price | idealTemperature
		"Ordi" | "Ordinateur" | 1500 | 10 
		"Table" | "Grande table" | 2000 | 5 
    }
	
	void "test update"() {
	
	}
	
	void "test getByID"() {
	
	}
	
	void "test delete"() {
	
	}
	
	void "test all"() {
		expect:
		storage.all() == []
	}
}
