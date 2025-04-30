import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Register() {
  const navigate = useNavigate();

  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [confirmPassword, setConfirmPassword] = useState('');
  const [error, setError] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    if (password !== confirmPassword) {
      setError("Passwords don't match");
      return;
    }

    const response = await fetch('http://localhost:8080/api/auth/register', {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json',
      },
      body: JSON.stringify({ username, password }),
    });

    const data = await response.json();
    if (response.ok) {
      navigate('/login');  // Redirect to login page after registration
    } else {
      setError(data.message || 'Registration failed');
    }
  };

  return React.createElement(
    'div',
    null,
    React.createElement('h2', null, 'Register'),
    error && React.createElement('p', { style: { color: 'red' } }, error),
    React.createElement(
      'form',
      { onSubmit: handleSubmit },
      React.createElement(
        'div',
        null,
        React.createElement('label', null, 'Username:'),
        React.createElement('input', {
          type: 'text',
          value: username,
          onChange: (e) => setUsername(e.target.value),
          required: true,
        })
      ),
      React.createElement(
        'div',
        null,
        React.createElement('label', null, 'Password:'),
        React.createElement('input', {
          type: 'password',
          value: password,
          onChange: (e) => setPassword(e.target.value),
          required: true,
        })
      ),
      React.createElement(
        'div',
        null,
        React.createElement('label', null, 'Confirm Password:'),
        React.createElement('input', {
          type: 'password',
          value: confirmPassword,
          onChange: (e) => setConfirmPassword(e.target.value),
          required: true,
        })
      ),
      React.createElement(
        'button',
        { type: 'submit' },
        'Register'
      )
    )
  );
}

export default Register;
