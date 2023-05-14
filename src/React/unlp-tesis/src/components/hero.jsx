import React,{useState, useEffect} from "react";
import {  Link } from 'react-router-dom';

const Hero = (data) => {

    const [IncidenciasExcesoTiempo, setIncidencias] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/incidencias/excesotiempo/count')
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            setIncidencias(data);
        })
        .catch((err) => {
            console.log(err.message)
        });
    },[]);

    const [IncidenciasAjusteBajo, setIncidencias2] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/incidencias/ajustebajo/count')
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            setIncidencias2(data);
        })
        .catch((err) => {
            console.log(err.message)
        });
    },[]);

    return (
        <div>
            <div className="container my-1">
                <div className="p-5 text-center bg-body-tertiary rounded-3">
                    <h1 className="text-body-emphasis">{data.name}</h1>
                    <p className="lead">
                    Aplicación para gestión de las incidencias encontradas por el módulo de Minería de Procesos.
                    </p>
                    <div className="row row-cols-1 row-cols-sm-2 row-cols-md-2 g-3">
                        <div className="col">
                            <div className="card shadow-sm">
                                <svg className="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                                    <title>Placeholder</title><rect width="100%" height="100%" fill="#55595c"></rect>
                                    <text x="45%" y="50%" font-size="45" fill="#eceeef" dy=".3em">{IncidenciasAjusteBajo}</text>
                                </svg>
                                <div className="card-body">
                                    <p className="card-text">Candidad de incidencias con ajuste bajo sin resolver.</p>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="btn-group">
                                        <button type="button" className="btn btn-sm btn-outline-secondary"><Link to="/excesomovimientos" className="nav-link">Ver Detalle</Link></button>
                                        </div>
                                        <small className="text-body-secondary">Incidencias</small>
                                    </div>
                                </div>
                            </div>
                            </div>
                            <div className="col">
                            <div className="card shadow-sm">
                                <svg className="bd-placeholder-img card-img-top" width="100%" height="225" xmlns="http://www.w3.org/2000/svg" role="img" aria-label="Placeholder: Thumbnail" preserveAspectRatio="xMidYMid slice" focusable="false">
                                    <title>Placeholder</title>
                                    <rect width="100%" height="100%" fill="#55595c"></rect>
                                    <text x="40%" y="50%" font-size="45" fill="#eceeef" dy=".3em">{IncidenciasExcesoTiempo}</text>
                                </svg>
                                <div className="card-body">
                                    <p className="card-text">Candidad de incidencias con exceso de movimientos sin resolver.</p>
                                    <div className="d-flex justify-content-between align-items-center">
                                        <div className="btn-group">
                                        <button type="button" className="btn btn-sm btn-outline-secondary"><Link to="/ajustebajo" className="nav-link">Ver Detalle</Link></button>
                                        </div>
                                        <small className="text-body-secondary">Incidencias</small>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    )
}

export default Hero;