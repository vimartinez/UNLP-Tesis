import React from "react";

const Hero = (data) => {

    return (
        <div>
            <div className="container my-5">
            <div className="p-5 text-center bg-body-tertiary rounded-3">
                <h1 className="text-body-emphasis">{data.name}</h1>
                <p className="lead">
                Aplicación para gestión de las incidencias encontradas por el módulo de Minería de Procesos.
                </p>
            </div>
            </div>
        </div>

    )
}

export default Hero;