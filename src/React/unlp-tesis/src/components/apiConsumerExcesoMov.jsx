import React,{useState, useEffect} from "react";

const ApiConsumerExcesoMov = () => {
    const [Incidencias, setIncidencias] = useState([]);
    useEffect(() => {
        fetch('http://localhost:8080/incidencias/ajustebajo/?page=0&size=5')
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
        <div>
            <h5>Listado de incidencias encontradas con exceso de movimientos</h5>
            <table className="table table-striped">
                <thead>
                    <tr>
                        <th >id</th>
                        <th>Traza</th>
                        <th>Ajuste</th>
                        <th>Fecha Ingreso</th>
                        <th>Resuelta</th>
                    </tr>
                </thead>
                <tbody>
                    {Incidencias.map((Incidencia) =>{
                        return(
                            <tr key={Incidencia.id}>
                                <td>{Incidencia.id}</td>
                                <td>{Incidencia.case_id}</td>
                                <td>{Incidencia.ajuste}</td>
                                <td>{Incidencia.fechaIngreso}</td>
                                <td>{Incidencia.resuelta ? 'Si' : 'No'}</td>
                            </tr>
                        )
                    })}
                </tbody>
            </table>
        </div>
    )

}

export default ApiConsumerExcesoMov;

