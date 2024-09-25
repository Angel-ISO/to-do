import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom'; 

import './dashboard.css';
import icon from '../../assets/profile.png';

export default function Dashboard() {
  const [dropdowns, setDropdowns] = useState({});
  const [isSidebarCollapsed, setIsSidebarCollapsed] = useState(false);
  const [isProfileMenuOpen, setIsProfileMenuOpen] = useState(false);
  const [username, setUsername] = useState(''); 
  const navigate = useNavigate();

  useEffect(() => {
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const user = JSON.parse(storedUser);
      setUsername(user.username); 
    }
  }, []);

  const toggleDropdown = (index) => {
    setDropdowns((prev) => ({
      ...prev,
      [index]: !prev[index],
    }));
  };

  const toggleSidebar = () => {
    setIsSidebarCollapsed(!isSidebarCollapsed);
  };

  const toggleProfileMenu = () => {
    setIsProfileMenuOpen(!isProfileMenuOpen);
  };

  const handleLogout = () => {
    localStorage.removeItem('user'); 
    navigate('/'); 
  };

  return (
    <div>
      <section id="sidebar" className={isSidebarCollapsed ? 'hide' : ''}>
        <a href="#" className="brand"><i className='bx bxs-smile icon'></i> To-Do booster</a>
        <ul className="side-menu">
          <li><a href="#" className="active"><i className='bx bxs-dashboard icon'></i> Principal</a></li>
          <li><a href="#"><i className='bx bxs-widget icon'></i> Task</a></li>
          <li><a href="#"><i className='bx bx-table icon'></i> Calendary</a></li>
          <li><a href="#"><i className='bx bxs-notepad icon'></i> History</a></li>
        </ul>
      </section>

      <section id="content">
        <nav>
          <i className='bx bx-menu toggle-sidebar' onClick={toggleSidebar}></i>
          <span className="divider"></span>
          <div className="profile">
            <span className="profile-name">{username}</span> {/* Mostrar el nombre del usuario */}
            <img src={icon} alt="profile" onClick={toggleProfileMenu}
            />
            <ul className={`profile-link ${isProfileMenuOpen ? 'show' : ''}`}>
              <li><a href="#"><i className='bx bxs-user-circle icon'></i> Profile</a></li>
              <li><a href="#"><i className='bx bxs-cog'></i> Settings</a></li>
              <li><a href="#" onClick={handleLogout}><i className='bx bxs-log-out-circle'></i> Logout</a></li>
            </ul>
          </div>
        </nav>

        <main className="welcome-container">
          <h1>Hello, {username}!</h1> 
          <p>Welcome to your dashboard.</p>
        </main>
      </section>
    </div>
  );
}
