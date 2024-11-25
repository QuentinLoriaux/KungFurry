import React, { useState, useEffect } from 'react';
import { TextField, Select, MenuItem, InputLabel, FormControl, Button, Box } from '@mui/material';
import { getAllRealisateurs } from '../api/RealisateurApi';

function CreateFilmForm({ film , onSubmit }) {
    const [title, setTitle] = useState(film?.title ||'');
    const [duration, setDuration] = useState(film?.duration || '');
    const [realisateur, setRealisateur] = useState(film?.realisateur || '');

    const [realisateurs, setRealisateurs] = useState([]);

    useEffect(() => {
        getAllRealisateurs().then(reponse => {
        setRealisateurs(reponse.data);
        }).catch(err => {
        console.log(err);
    })
    }, [])

     // Handle form submission
     const handleCreateFilm = (e) => {
        e.preventDefault();

        // Construct the new film object
        const newFilm = {
            title,
            duration,
            realisateur,
        };
        // Appel de la fonction onSubmit passée en props
        onSubmit(newFilm);

        // Réinitialisation des champs après soumission
        setTitle('');
        setDuration('');
        setRealisateur('')

    };

    return (
        <Box 
            component="form"
            onSubmit={handleCreateFilm}
            sx={{
                display: 'flex',
                flexDirection: 'column',
                gap: 2,
                maxWidth: 400,
                margin: 'auto',
                padding: 2,
                border: '1px solid #ccc',
                borderRadius: 2,
                boxShadow: 3,
            }}
        >
            <TextField
                label="Titre"
                variant="outlined"
                value={title}
                onChange={(e) => setTitle(e.target.value)}
                required
            />
            <TextField
                label="Durée (en minutes)"
                variant="outlined"
                type="number"
                value={duration}
                onChange={(e) => setDuration(e.target.value)}
                required
            />
            <FormControl required>
                <InputLabel id="realisateur-label">Réalisateur</InputLabel>
                <Select
                    labelId="realisateur-label"
                    value={realisateur}
                    onChange={(e) => setRealisateur(e.target.value)}
                >
                    {
                        realisateurs.map(realisateur => {
                        return <MenuItem key={realisateur.id} value={realisateur.id}>
                        {realisateur.prenom} {realisateur.nom}
                        </MenuItem>})
                    }
                </Select>
            </FormControl>
            <Button type="submit" variant="contained" color="primary">
                Ajouter un film
            </Button>
        </Box>
    );
}

export default CreateFilmForm;
