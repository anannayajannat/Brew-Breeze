import React, { useEffect, useState } from 'react';

function AdminOrders() {
    const [orders, setOrders] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        fetch('http://localhost:8080/api/admin/orders', {
            headers: {
                Authorization: `Bearer ${localStorage.getItem('token')}`, // Assuming JWT token is stored in localStorage
            }
        })
            .then(res => {
                if (!res.ok) {
                    throw new Error('Failed to fetch orders');
                }
                return res.json();
            })
            .then(data => {
                setOrders(data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Error fetching orders:', err);
                setLoading(false);
            });
    }, []);

    if (loading) {
        return <h2>Loading orders...</h2>;
    }

    return (
        <div>
            <h1>Admin Order Management</h1>
            {orders.length === 0 ? (
                <p>No orders yet.</p>
            ) : (
                <table border="1" cellPadding="8" cellSpacing="0">
                    <thead>
                        <tr>
                            <th>Order ID</th>
                            <th>Customer Name</th>
                            <th>Items</th>
                            <th>Total Amount</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        {orders.map(order => (
                            <tr key={order.id}>
                                <td>{order.id}</td>
                                <td>{order.customerName}</td>
                                <td>
                                    <ul>
                                        {order.products.map(product => (
                                            <li key={product.id}>
                                                {product.name} - {product.price}
                                            </li>
                                        ))}
                                    </ul>
                                </td>
                                <td>${order.totalAmount}</td>
                                <td>{order.status}</td>
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default AdminOrders;
