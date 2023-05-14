import React,{useState, useEffect} from "react";
import { Link, useNavigate } from 'react-router-dom';

const ApiConsumerExcesoMov = () => {
    const navigate = useNavigate();
    const [Incidencias, setIncidencias] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/incidencias/excesotiempo/?page=0&size=5')
        .then((response) => response.json())
        .then((data) => {
            console.log(data);
            setIncidencias(data.incidencias);
        })
        .catch((err) => {
            console.log(err.message)
        });
    },[]);


    return(
        <div className="container-md">
            <h3>Listado de incidencias encontradas con exceso de movimientos</h3>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th >id</th>
                        <th>Traza</th>
                        <th>Fecha Ingreso</th>
                        <th>Resuelta</th>
                    </tr>
                </thead>
                <tbody>
                    {Incidencias.map((Incidencia) =>{
                        return(
                            <tr key={Incidencia.id}>
                                <td><Link to={`/detalleincidencia/${Incidencia.case_id}`} className="nav-link">{Incidencia.id}</Link></td>
                                <td><Link to={`/detalleincidencia/${Incidencia.case_id}`} className="nav-link">{Incidencia.case_id}</Link></td>
                                <td><Link to={`/detalleincidencia/${Incidencia.case_id}`} className="nav-link">{Incidencia.fechaIngreso}</Link></td>
                                <td><Link to={`/detalleincidencia/${Incidencia.case_id}`} className="nav-link">{Incidencia.resuelta ? 'Si' : 'No'}</Link></td> 
                            </tr>
                        )
                    })}
                </tbody>
            </table>
            <button type="button" className="btn btn-sm btn-outline-secondary"  onClick={() => navigate(-1)}>Volver</button> 
        </div>
    )

}

export default ApiConsumerExcesoMov;

