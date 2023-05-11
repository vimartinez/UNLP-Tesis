import React,{useState} from "react";
import {  Link } from 'react-router-dom';

const Header = (data) => {

    return (
        <div>
            <nav className="navbar navbar-expand-lg bg-body-tertiary">
                <div className="container-fluid border-bottom pb-3 mb-3">
                    <a className="navbar-brand" href="#">{data.name}</a>
                    <div className="collapse navbar-collapse" id="navbarNav">
                    <ul className="navbar-nav">
                        <li className="nav-item">
                            <Link to="/" className="nav-link active" aria-current="page">Inicio</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/ajustebajo" className="nav-link">Trazas con Ajuste bajo</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/excesomovimientos" className="nav-link">Trazas con Exceso de Movimientos</Link>
                        </li>
                        <li className="nav-item">
                            <Link to="/about" className="nav-link">Acerca de ..</Link>
                        </li>
                    </ul>
                    </div>
                </div>
            </nav>
        </div>
        
    )
}


export default Header;