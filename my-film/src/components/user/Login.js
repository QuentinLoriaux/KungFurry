import React, { useState } from 'react';
import axios from 'axios';
import {Box, Button, TextField} from "@mui/material";

const Login = ({ setAuth }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async (e) => {
        e.preventDefault();
        try {
            const payload = {
                username: username,
                password: password,
                roleId: 0,
            }

            const response = await axios.post('http://localhost:8080/user/login', payload, { withCredentials: true });
            setAuth(response.data);
            alert('Connexion réussie!'+response.data);
        } catch (error) {
            console.error('Erreur lors de la connexion:', error);
            alert('Échec de la connexion. Veuillez réessayer.');
        }
    };

    return (
        <Box
            component="form"
            onSubmit={handleLogin}
            sx={{
                display: 'flex',
                flexDirection: 'column',
                gap: 2,
                maxWidth: 600,
                margin: 'auto',
                padding: 2,
                border: '1px solid #ccc',
                borderRadius: 2,
                boxShadow: 3,
            }}
        >
            <TextField
                label="Nom d'utilisateur"
                variant="outlined"
                value={username}
                onChange={(e) => setUsername(e.target.value)}
                required
            />
            <TextField
                label="Mot de passe"
                variant="outlined"
                type="password"
                value={password}
                onChange={(e) => setPassword(e.target.value)}
                required
            />
            <Button type="submit" variant="contained" color="primary">
                Se connecter
            </Button>
        </Box>
    );
};

export default Login;