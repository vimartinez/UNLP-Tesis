import React, { useState } from "react";
import { Routes, Route, Link } from 'react-router-dom';
import Hero from "./hero";
import ApiConsumerAjusteBajo from "./apiConsumerAjusteBajo";
import ApiConsumerExcesoMov from "./apiConsumerExcesoMov";
import DetalleIncidencia from "./detalleIncidencia";
import About from "./about";

const Main = (data) => {

    return (
            <div>
                <Routes>
                    <Route path="/" element={<Hero name="Gestión de Incidencias"  />} exact />
                    <Route path="/detalleincidencia/:id" element={<DetalleIncidencia  />} exact />
                    <Route path="/ajustebajo" element={<ApiConsumerAjusteBajo  />} exact />
                    <Route path="/excesomovimientos" element={<ApiConsumerExcesoMov />} exact />
                    <Route path="/about" element={<About />} />
                </Routes>
            </div>
    )
}


export default Main;



