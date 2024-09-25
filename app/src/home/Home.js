import React, { useEffect } from 'react'
import Footer from '../fragments/Footer'
import Dashboard from '../fragments/dashboard/Dashboard'
import { useNavigate } from 'react-router-dom'; 


export default function Home() {
  const navigate = useNavigate();

  useEffect(() => {
    const user = JSON.parse(localStorage.getItem('user'));
    if (!user) {
      navigate('/');
    } else {
     
      const userRoles = user.roles.map(role => role.name);
      if (userRoles.includes('ADMIN')) {
        navigate('/admin-home'); 
      }
      }
    } , [navigate]);
  return (
    <div>
      <div className="main-content">
        <div className="content">
          <Dashboard />
        </div>
      </div>
      <Footer /> 
      {/* Llama al componente footer */}
    </div>
  )
}
