import React,{useState, useEffect} from "react";
import { useParams, useNavigate } from 'react-router-dom';

const DetalleIncidencia = (data) => {

    const navigate = useNavigate();
    const params = useParams();
    //console.log(params)

    const [Traza, setTraza] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/trazas/'+params.id)
        .then((response) => response.json())
        .then((data) => {
        //    console.log(data);
            setTraza(data);
        })
        .catch((err) => {
            console.log(err.message)
        });
    },[]);

    const [Envio, setEnvio] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/envios/'+params.id)
        .then((response) => response.json())
        .then((data2) => {
       //     console.log(data2);
            setEnvio(data2);
        })
        .catch((err) => {
            console.log(err.message)
        });
    },[]);

    const [Reclamo, setReclamo] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/reclamos/'+params.id)
        .then((response) => response.json())
        .then((data3) => {
            console.log(data3);
            if (data3.length > 0) setReclamo(data3);
        })
        .catch((err) => {
            console.log(err.message)
        });
    },[]);

    return (
            <div className="container text-center">
                <h3> Detalle incidencia  {params.id} </h3>
                <div className="row">
                    <div className="col">
                        <div className="card" >
                            <div className="card-header">  <b>Datos del Envío</b>  </div>
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item"><b>Dirección de Entrega: </b> {Envio ? Envio.direccion : "No disponible" }</li>
                                <li className="list-group-item"><b>Localidad: </b> {Envio ? Envio.localidad : "No disponible" }</li>
                                <li className="list-group-item"><b>Partido: </b>{Envio ? Envio.partido : "No disponible" }</li>
                                <li className="list-group-item"><b>Provincia: </b>{Envio ? Envio.provincia : "No disponible" }</li>
                                <li className="list-group-item"><b>Nro de Seguimiento: </b>{Envio ? Envio.seguimiento : "No disponible" }</li>
                            </ul>
                        </div>
                    </div>
                    <div className="col">
                        <div className="card" >
                            <div className="card-header"> <b>Datos de Sucursal</b>   </div>
                            <ul className="list-group list-group-flush">
                                <li className="list-group-item"><b>Nombre: </b>{Envio ? (Envio.sucursal ? Envio.sucursal.descripcion : "No disponible") : "No disponible" }</li>
                                <li className="list-group-item"><b>Dirección: </b> {Envio ? (Envio.sucursal ? Envio.sucursal.direccion : "No disponible") : "No disponible" }</li>
                                <li className="list-group-item"><b>Partido: </b> {Envio ? (Envio.sucursal ? Envio.sucursal.partido : "No disponible") : "No disponible" }</li>
                                <li className="list-group-item"><b>Provincia: </b>{Envio ? (Envio.sucursal ? Envio.sucursal.provincia : "No disponible") : "No disponible" }</li>
                                <li className="list-group-item"><b>Telefono: </b>{Envio ? (Envio.sucursal ? Envio.sucursal.telefono : "No disponible") : "No disponible" }</li>
                                <li className="list-group-item"><b>Mail: </b>{Envio ? (Envio.sucursal ? Envio.sucursal.mail : "No disponible") : "No disponible" }</li>
                            </ul>
                        </div>  
                    </div>
                </div>
                <br/>
                <div className="row">
                    <div className="col">
                            <div className="card" >
                                <div className="card-header"> <b>Movimientos</b>  </div>
                                    <table className="table table-striped ">
                                        <thead>
                                            <tr>
                                                <th >id</th>
                                                <th>Evento</th>
                                                <th>Fecha Evento</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            {Traza.map((movimiento) =>{
                                                return(
                                                    <tr key={movimiento.id}>
                                                        <td>{movimiento.id}</td>
                                                        <td>{movimiento.activity}</td>
                                                        <td>{movimiento.timestamp}</td>
                                                    </tr>
                                                )
                                            })}
                                        </tbody>
                                    </table>  
                                </div>    
                    </div>
                <div className="col">
                        <div className="card" >
                            <div className="card-header"> <b>Reclamos del envío</b>   </div>
                            <table className="table table-striped ">
                                    <thead>
                                        <tr>
                                            <th >id</th>
                                            <th>Fecha Reclamo</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        {Reclamo.map((rec) =>{
                                            return(
                                                <tr key={rec.id}>
                                                    <td>{rec.id}</td>
                                                    <td>{Reclamo ? rec.fechaReclamo : "No se registran reclamos"}</td>
                                                </tr>
                                            )
                                        })}
                                    </tbody>
                                </table> 
                        </div>  
                    </div>
                </div>
            <br />
            <button type="button" className="btn btn-sm btn-outline-secondary"  onClick={() => navigate(-1)}>Registrar Reclamo</button> {'\u00A0'}
            <button type="button" className="btn btn-sm btn-outline-secondary"  onClick={() => navigate(-1)}>Cerrar Incidencia</button> {'\u00A0'}
            <button type="button" className="btn btn-sm btn-outline-secondary"  onClick={() => navigate(-1)}>Volver</button>
        </div>     
    )
}

export default DetalleIncidencia;