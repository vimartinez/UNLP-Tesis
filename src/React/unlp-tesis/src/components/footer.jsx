import React,{useState} from "react";
import {  Link } from 'react-router-dom';

const Footer = (data) => {

    return (
        <div>
             <br/><br/><br/>
            <footer className="py-3 my-4">
                <p className="text-center text-body-secondary border-top pb-3 mb-3"> {data.name}</p>
            </footer>
        </div>
        
    )
}

export default Footer;