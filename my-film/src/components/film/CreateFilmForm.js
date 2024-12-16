import React, { useState, useEffect } from 'react';
import { TextField, Select, MenuItem, InputLabel, FormControl, Button, Box } from '@mui/material';
import { getAllRealisateurs } from '../../api/RealisateurApi';
import { getAllGenres } from '../../api/GenreApi';

function CreateFilmForm({ film , onSubmit }) {
    const [title, setTitle] = useState(film?.titre ||'');
    const [duration, setDuration] = useState(film?.duree || '');
    const [realisateur, setRealisateur] = useState(film?.realisateur || '');
    const [realisateurId, setRealisateurId] = useState(realisateur?.id || '');
    const [genreId, setGenreId] = useState(film?.genre?.id || '');

    const [realisateurs, setRealisateurs] = useState([]);
    const [genres, setGenres] = useState([]);

    useEffect(() => {
        getAllRealisateurs(0,20).then(reponse => {
        setRealisateurs(reponse.data.data);
        }).catch(err => {
        console.log(err);
    })
    }, [])

    useEffect(() => {
        getAllGenres().then(response => {
            setGenres(response.data);
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
            realisateurId: realisateurId,
            genreId: genreId
        };
        // Appel de la fonction onSubmit passée en props
        onSubmit(newFilm);

        // Réinitialisation des champs après soumission
        setTitle('');
        setDuration('');
        setRealisateur('')
        setRealisateurId('');
        setGenreId('');
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
                    value={realisateurId}
                    onChange={(e) => {
                        const selectedRealisateur = realisateurs.find(r => r.id === e.target.value);
                        setRealisateur(selectedRealisateur);
                        setRealisateurId(e.target.value);
                    }}
                >
                    {realisateurs.length > 0 ? (
                        realisateurs.map((realisateur) => (
                            <MenuItem key={realisateur.id} value={realisateur.id}>
                                {realisateur.prenom} {realisateur.nom}
                            </MenuItem>
                        ))
                    ) : (
                        <MenuItem disabled>
                            Aucun réalisateur disponible
                        </MenuItem>
                    )}
                </Select>
            </FormControl>
            <FormControl required>
                <InputLabel id="genre-label">Genre</InputLabel>
                <Select
                    labelId="genre-label"
                    value={genreId}
                    onChange={(e) => {
                        setGenreId(e.target.value);
                    }}
                >
                    {genres.length > 0 ? (
                        genres.map((genre) => (
                            <MenuItem key={genre.id} value={genre.id}>
                                {genre.name}
                            </MenuItem>
                        ))
                    ) : (
                        <MenuItem disabled>
                            Aucun genre disponible
                        </MenuItem>
                    )}
                </Select>
            </FormControl>
            <Button type="submit" variant="contained" color="primary">
                Ajouter un film
            </Button>
        </Box>
    );
}

export default CreateFilmForm;
