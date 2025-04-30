import React, { useState } from 'react';
import axios from 'axios';

const AddProduct = () => {
  const [product, setProduct] = useState({
    name: '',
    description: '',
    price: '',
    category: 'COFFEE', // Default to COFFEE
    image: null,
  });

  const [error, setError] = useState('');
  const [successMessage, setSuccessMessage] = useState('');

  const handleChange = (e) => {
    const { name, value } = e.target;
    setProduct((prev) => ({
      ...prev,
      [name]: value,
    }));
  };

  const handleFileChange = (e) => {
    setProduct((prev) => ({
      ...prev,
      image: e.target.files[0],
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    // Form validation
    if (!product.name || !product.description || !product.price || !product.image) {
      setError('All fields are required!');
      return;
    }

    const formData = new FormData();
    formData.append('name', product.name);
    formData.append('description', product.description);
    formData.append('price', product.price);
    formData.append('category', product.category);
    formData.append('image', product.image);

    try {
      const response = await axios.post('http://localhost:8081/api/products/add', formData, {
        headers: {
          'Content-Type': 'multipart/form-data',
        },
      });
      setSuccessMessage('Product added successfully!');
      setError('');
      console.log('Product added:', response.data);
      // Clear the form after submission
      setProduct({
        name: '',
        description: '',
        price: '',
        category: 'COFFEE',
        image: null,
      });
    } catch (error) {
      setError('Error adding product. Please try again.');
      setSuccessMessage('');
      console.error('Error adding product:', error);
    }
  };

  return (
    <div className="container">
      <h2>Add New Product</h2>

      {/* Error and Success Message */}
      {error && <div className="alert alert-danger">{error}</div>}
      {successMessage && <div className="alert alert-success">{successMessage}</div>}

      <form onSubmit={handleSubmit}>
        <div className="form-group">
          <label>Product Name</label>
          <input
            type="text"
            className="form-control"
            name="name"
            value={product.name}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Description</label>
          <textarea
            className="form-control"
            name="description"
            value={product.description}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Price</label>
          <input
            type="number"
            className="form-control"
            name="price"
            value={product.price}
            onChange={handleChange}
            required
          />
        </div>

        <div className="form-group">
          <label>Category</label>
          <select
            className="form-control"
            name="category"
            value={product.category}
            onChange={handleChange}
          >
            <option value="COFFEE">Coffee</option>
            <option value="BAKERY">Bakery</option>
            <option value="TEA">Tea</option>
            <option value="SPECIALTY">Specialty</option>
          </select>
        </div>

        <div className="form-group">
          <label>Image</label>
          <input
            type="file"
            className="form-control"
            name="image"
            onChange={handleFileChange}
            required
          />
          {product.image && (
            <div className="mt-2">
              <strong>Selected Image:</strong>
              <img
                src={URL.createObjectURL(product.image)}
                alt="Image preview"
                style={{ width: '100px', height: '100px', objectFit: 'cover' }}
              />
            </div>
          )}
        </div>

        <button type="submit" className="btn btn-primary">
          Add Product
        </button>
      </form>
    </div>
  );
};

export default AddProduct;
