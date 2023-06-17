import React,{useState} from "react";
import {  Link } from 'react-router-dom';

const Header = (data) => {

    return (
        <div>
            <nav class="navbar navbar-expand-lg navbar-dark bg-secondary mb-4">
            <div class="container-fluid border-bottom pb-3 mb-2 ">
                <a class="navbar-brand" href="#">UNLP - Tesis</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarCollapse" aria-controls="navbarCollapse" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                <ul class="navbar-nav me-auto mb-2 mb-md-0">
                    <li className="nav-item">
                            <Link to="/" className="nav-link active" aria-current="page">Inicio</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/ajustebajo" className="nav-link">Ajuste bajo</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/excesomovimientos" className="nav-link">Exceso de Movimientos</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/about" className="nav-link">Acerca de ..</Link>
                        </li>
                </ul>
                <form class="d-flex" role="search">
                    <input class="form-control me-2" type="search" placeholder="Incidencia" aria-label="Search" />
                    <button class="btn btn-success" type="submit">Buscar</button>
                </form>
                </div>
            </div>
</nav>
        </div>
        
    )
}


export default Header;