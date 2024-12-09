import {Box, Button, TextField} from "@mui/material";
import {useState} from "react";

function CreateRealisateurForm({ realisateur , onSubmit }){
    const [prenom, setPrenom] = useState(realisateur?.prenom || '');
    const [nom, setNom] = useState(realisateur?.nom || '');
    const [dateNaissance, setDateNaissance] = useState(realisateur?.dateNaissance || '');

    const handleCreateRealisateur = (e) => {
        e.preventDefault();

        const newRealisateur = {
            prenom,
            nom,
            dateNaissance,
        };
        onSubmit(newRealisateur);

        setPrenom('');
        setNom('');
        setDateNaissance('');
    }

    return (
        <Box
            component="form"
            onSubmit={handleCreateRealisateur}
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
                label="Prénom"
                variant="outlined"
                value={prenom}
                onChange={(e) => setPrenom(e.target.value)}
                required
            />
            <TextField
                label="Nom"
                variant="outlined"
                value={nom}
                onChange={(e) => setNom(e.target.value)}
                required
            />
            <TextField
                label="Date de naissance"
                variant="outlined"
                type="date"
                value={dateNaissance}
                onChange={(e) => setDateNaissance(e.target.value)}
                required
            />
            <Button type="submit" variant="contained" color="primary">
                Ajouter un réalisateur
            </Button>
        </Box>
    );
}

export default CreateRealisateurForm;