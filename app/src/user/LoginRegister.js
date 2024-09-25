import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom'; 
import './LoginRegister.css';
import icon from '../assets/icon.png';
import Footer from '../fragments/Footer';
import { showSuccessAlert, showErrorAlert } from '../utils/alerts'; 

export default function LoginRegister() {
  const urlbase = 'http://localhost:8080/api/users';
  const [activeForm, setActiveForm] = useState('login');
  const [username, setUsername] = useState('');
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const navigate = useNavigate(); 

  const handleTabClick = (form) => {
    setActiveForm(form);
    setUsername('');
    setEmail('');
    setPassword('');
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${urlbase}`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: username,
          email: email,
          password: password,
        }),
      });

      if (response.ok) {
        showSuccessAlert('Usuario registrado exitosamente');
        handleTabClick('login'); 
      } else {
        const errorData = await response.json();
        showErrorAlert(errorData.message || 'Error en el registro');
      }
    } catch (error) {
      showErrorAlert('Ocurrió un error en el servidor');
    }
  };

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const response = await fetch(`${urlbase}/login`, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          username: username,
          password: password,
        }),
      });
  
      if (response.ok) {
        const data = await response.json();
        const { message, user } = data;
        showSuccessAlert('Inicio de sesión exitoso', message);
        localStorage.setItem('user', JSON.stringify(user));
  
        const userRoles = user.roles.map(role => role.name);
        if (userRoles.includes('ADMIN')) {
          navigate('/admin-home'); 
        } else {
          navigate('/home'); 
        }
      } else {
        const errorData = await response.json();
        showErrorAlert(errorData.message || 'Credenciales inválidas');
      }
    } catch (error) {
      showErrorAlert('Ocurrió un error en el servidor');
    }
  };


 

  return (
    <div>
      <header>
        <h1>App</h1>
        <img src={icon} alt="To-Do App Logo" className="app-image" />
      </header>

      <main>
        <div className="container">
          <div className="tabs">
            <div
              className={`tab ${activeForm === 'login' ? 'active' : ''}`}
              onClick={() => handleTabClick('login')}
            >
              Login
            </div>
            <div
              className={`tab ${activeForm === 'register' ? 'active' : ''}`}
              onClick={() => handleTabClick('register')}
            >
              Registro
            </div>
          </div>

          {/* Formulario de login */}
          {activeForm === 'login' && (
            <form className="form-container active" onSubmit={handleLogin}>
              <div className="form-group">
                <i className="fas fa-envelope"></i>
                <input
                  type="text"
                  placeholder="Username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <i className="fas fa-lock"></i>
                <input
                  type="password"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              <button type="submit">Iniciar sesión</button>
            </form>
          )}

          {/* Formulario de registro */}
          {activeForm === 'register' && (
            <form className="form-container active" onSubmit={handleRegister}>
              <div className="form-group">
                <i className="fas fa-user"></i>
                <input
                  type="text"
                  placeholder="Username"
                  value={username}
                  onChange={(e) => setUsername(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <i className="fas fa-envelope"></i>
                <input
                  type="email"
                  placeholder="Email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>
              <div className="form-group">
                <i className="fas fa-lock"></i>
                <input
                  type="password"
                  placeholder="Password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              <button type="submit">Registrarse</button>
            </form>
          )}
        </div>
      </main>
      <Footer />
    </div>
  );
}
