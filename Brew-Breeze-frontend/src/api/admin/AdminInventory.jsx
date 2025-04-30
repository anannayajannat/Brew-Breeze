import React, { useState, useEffect } from 'react';

function AdminInventory() {
    const [products, setProducts] = useState([]);
    const [newProduct, setNewProduct] = useState({
        name: '',
        description: '',
        price: '',
        category: 'COFFEE', // default category
        image: null,
    });
    const [loading, setLoading] = useState(true);

    // Fetch products on load
    useEffect(() => {
        fetch('http://localhost:8080/api/admin/inventory', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            }
        })
            .then(res => {
                if (!res.ok) {
                    throw new Error('Failed to fetch products');
                }
                return res.json();
            })
            .then(data => {
                setProducts(data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching products:', err);
                setLoading(false);
            });
    }, []);

    // Handle form input change for adding a new product
    const handleInputChange = (e) => {
        const { name, value } = e.target;
        setNewProduct({
            ...newProduct,
            [name]: value,
        });
    };

    const handleFileChange = (e) => {
        setNewProduct({
            ...newProduct,
            image: e.target.files[0], // single file upload
        });
    };

    // Handle product submission
    const handleAddProduct = (e) => {
        e.preventDefault();
        const formData = new FormData();
        formData.append('name', newProduct.name);
        formData.append('description', newProduct.description);
        formData.append('price', newProduct.price);
        formData.append('category', newProduct.category);
        formData.append('image', newProduct.image);

        fetch('http://localhost:8080/api/products/add', {
            method: 'POST',
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            },
            body: formData,
        })
            .then(res => res.json())
            .then(data => {
                setProducts([...products, data]); // Add new product to list
                setNewProduct({ name: '', description: '', price: '', category: 'COFFEE', image: null }); // Reset form
            })
            .catch(err => console.error('Error adding product:', err));
    };

    // Handle product deletion
    const handleDeleteProduct = (productId) => {
        fetch(`http://localhost:8080/api/admin/inventory/delete/${productId}`, {
            method: 'DELETE',
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`,
            },
        })
            .then(() => {
                setProducts(products.filter(product => product.id !== productId)); // Remove deleted product from list
            })
            .catch(err => console.error('Error deleting product:', err));
    };

    if (loading) {
        return <h2>Loading inventory...</h2>;
    }

    return (
        <div>
            <h1>Admin Inventory Management</h1>

            {/* Product Form */}
            <h3>Add a New Product</h3>
            <form onSubmit={handleAddProduct}>
                <div>
                    <label>Name:</label>
                    <input
                        type="text"
                        name="name"
                        value={newProduct.name}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Description:</label>
                    <textarea
                        name="description"
                        value={newProduct.description}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Price:</label>
                    <input
                        type="number"
                        name="price"
                        value={newProduct.price}
                        onChange={handleInputChange}
                        required
                    />
                </div>
                <div>
                    <label>Category:</label>
                    <select
                        name="category"
                        value={newProduct.category}
                        onChange={handleInputChange}
                        required
                    >
                        <option value="COFFEE">Coffee</option>
                        <option value="BAKERY">Bakery</option>
                    </select>
                </div>
                <div>
                    <label>Image:</label>
                    <input type="file" onChange={handleFileChange} required />
                </div>
                <button type="submit">Add Product</button>
            </form>

            {/* Display Products */}
            <h3>Product Inventory</h3>
            {products.length === 0 ? (
                <p>No products available.</p>
            ) : (
                <table border="1" cellPadding="8" cellSpacing="0">
                    <thead>
                        <tr>
                            <th>Product ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Category</th>
                            <th>Image</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        {products.map(product => (
                            <tr key={product.id}>
                                <td>{product.id}</td>
                                <td>{product.name}</td>
                                <td>{product.description}</td>
                                <td>{product.price}</td>
                                <td>{product.category}</td>
                                <td>
                                    <img
                                        src={`http://localhost:8080/${product.image}`} // Adjust image path if needed
                                        alt={product.name}
                                        width="50"
                                    />
                                </td>
                                <td>
                                    <button onClick={() => handleDeleteProduct(product.id)}>Delete</button>
                                </td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default AdminInventory;
