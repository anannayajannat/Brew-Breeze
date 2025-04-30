import React from 'react';
import { useAuth } from '../context/AuthContext';

function Home() {
  const { user } = useAuth();

  return (
    <div>
      <h1>Welcome, {user ? user.username : 'Guest'}</h1>
      {/* Add more content based on user role or other conditions */}
    </div>
  );
}

export default Home;
