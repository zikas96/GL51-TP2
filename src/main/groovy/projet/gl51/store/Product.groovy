package projet.gl51.store

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(excludes="id")
class Product {
	String id = UUID.randomUUID().toString()
	String name
	String description
	double price
	double idealTemperature
}
