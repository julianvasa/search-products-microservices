# search-products-microservices
Search products 

- All products are returned
- Products are grouped by `brand`, sorted alphabetically
- Property `brand` should be omitted on products
- Products inside a `brand` should be sorted ascending by `price`
- Property `onSale` should be converted to a property `event` of type String with the value "ON SALE"

Example:

```JSON
{
	"Brand A" : [{
		"id": 1,
		"name": "Product A",
		"price": 7.99,
		"event": "ON SALE"
	},
	{
		"id": 2,
		"name": "Product B",
		"price": 12.99
	}],
	"Brand B" : [{
		"id": 3,
		"name": "Product C",
		"price": 10.99
	},
	{
		"id": 4,
		"name": "Product D",
		"price": 14.99
	}]
}
