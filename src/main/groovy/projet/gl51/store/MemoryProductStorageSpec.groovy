package projet.gl51.store

class MemoryProductStorage implements ProductStorage {

	private List<Product> products = []

	@Override
	String save(Product p) {
		products.add(p)
		p.id
	}

	@Override
	void update(String id, Product p) {
		Integer productIndex = products.findIndexOf { it.id == id }
		p.id = id;
		products.set(productIndex, p)
	}

	@Override
	Product getByID(String id) {
		Product product = products.find { it.id == id }
		if (product == null) throw new NotExistingProductException()
		product
	}

	@Override
	void delete(String id) {
	    products.removeIf {it.id==id}
	}

	@Override
	List<Product> all() {
		products
	}

} 