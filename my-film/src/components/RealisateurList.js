import React, { useState, useEffect } from 'react';
import { getAllRealisateurs } from '../api/RealisateurApi';
import { MenuItem } from '@mui/material';

export default function RealisateurList() {
    const [realisateurs, setRealisateurs] = useState([]);

    useEffect(() => {
        getAllRealisateurs().then(reponse => {
        setRealisateurs(reponse.data);
        }).catch(err => {
        console.log(err);
    })
    }, [])

    return realisateurs.map(realisateur => {
    return <MenuItem key={realisateur.id} value={realisateur.id}>
    {realisateur.prenom} {realisateur.nom}
    </MenuItem>})
    
}

;