import { useState, useEffect } from "react";
import { Box, TextField, Button, FormControl, InputLabel, Select, MenuItem } from "@mui/material";
import {getAllRealisateurs} from "../../api/RealisateurApi";
import {getAllGenres} from "../../api/GenreApi";

function CreateFilmForm({ film, onSubmit }) {
    const [title, setTitle] = useState(film?.titre || '');
    const [duration, setDuration] = useState(film?.duree || '');
    const [releaseDate, setReleaseDate] = useState(film?.dateSortie || '');
    const [synopsis, setSynopsis] = useState(film?.synopsis || '');
    const [realisateur, setRealisateur] = useState(film?.realisateur || '');
    const [realisateurId, setRealisateurId] = useState(realisateur?.id || '');
    const [genreId, setGenreId] = useState(film?.genre?.id || '');
    const [coverImage, setCoverImage] = useState(null);
    const [preview, setPreview] = useState(film?.couverture || null); // Prévisualisation de l'image

    const [realisateurs, setRealisateurs] = useState([]);
    const [genres, setGenres] = useState([]);

    useEffect(() => {
        getAllRealisateurs(0, 20).then(response => {
            setRealisateurs(response.data.data);
        }).catch(err => console.log(err));
    }, []);

    useEffect(() => {
        getAllGenres().then(response => {
            setGenres(response.data);
        }).catch(err => console.log(err));
    }, []);

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            setCoverImage(file);
            setPreview(URL.createObjectURL(file));
        }
    };

    const handleCreateFilm = (e) => {
        e.preventDefault();
        const newFilm = {
            title,
            duration,
            releaseDate,
            synopsis,
            realisateurId,
            genreId,
            coverImage
        };

        onSubmit(newFilm);

        setTitle('');
        setDuration('');
        setReleaseDate('');
        setSynopsis('');
        setRealisateur('');
        setRealisateurId('');
        setGenreId('');
        setCoverImage(null);
        setPreview(null);
    };

    return (
        <Box
            component="form"
            onSubmit={handleCreateFilm}
            sx={{
                display: 'flex',
                flexDirection: 'column',
                gap: 2,
                maxWidth: 500,
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
            <TextField
                label="Date de sortie"
                variant="outlined"
                type="date"
                InputLabelProps={{ shrink: true }}
                value={releaseDate}
                onChange={(e) => setReleaseDate(e.target.value)}
                required
            />
            <TextField
                label="Synopsis"
                variant="outlined"
                multiline
                rows={4}
                value={synopsis}
                onChange={(e) => setSynopsis(e.target.value)}
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

            <input
                type="file"
                accept="image/*"
                onChange={handleImageChange}
            />
            {preview && (
                <img
                    src={preview}
                    alt="Prévisualisation"
                    style={{ width: '100%', maxHeight: 300, objectFit: 'cover', borderRadius: 8 }}
                />
            )}

            <Button type="submit" variant="contained" color="primary">
                Ajouter un film
            </Button>
        </Box>
    );
}

export default CreateFilmForm;
