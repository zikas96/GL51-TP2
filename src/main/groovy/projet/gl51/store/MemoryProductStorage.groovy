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
		// TODO Auto-generated method stub

	}

	@Override
	Product getByID(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	void delete(String id) {
		// TODO Auto-generated method stub

	}

	@Override
	List<Product> all() {
		products
	}

}
