import React, {useCallback, useState} from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Box from "@mui/material/Box";
import { Link } from 'react-router-dom';
import Dialog from "@mui/material/Dialog";
import DialogContent from "@mui/material/DialogContent";
import Login from "./components/user/Login";

export default function Header({ setIsLoggedIn, isLoggedIn, handleLogout, setAuthData }) {
    const [open, setOpen] = useState(false);

    // Memoized handlers to prevent unnecessary re-renders
    const handleOpen = useCallback(() => setOpen(true), []);
    const handleClose = useCallback(() => setOpen(false), []);
    const handleLogin = useCallback(
        (data) => {
            setIsLoggedIn(true);
            setAuthData(data);
            handleClose();
        },
        [setIsLoggedIn, setAuthData, handleClose]
    );

    return (
        <AppBar position="static">
            <Toolbar>
                <Box sx={{ flexGrow: 1, display: "flex", gap: 2 }}>
                    <Typography
                        variant="h6"
                        component={Link}
                        to="/"
                        sx={{ textDecoration: "none", color: "inherit" }}
                    >
                        Films
                    </Typography>
                    <Typography
                        variant="h6"
                        component={Link}
                        to="/realisateurs"
                        sx={{ textDecoration: "none", color: "inherit" }}
                    >
                        Réalisateurs
                    </Typography>
                    {isLoggedIn && (
                        <Typography
                            variant="h6"
                            component={Link}
                            to="/users"
                            sx={{ textDecoration: "none", color: "inherit" }}
                        >
                            Utilisateurs
                        </Typography>
                    )}
                </Box>

                <Typography
                    variant="h6"
                    onClick={isLoggedIn ? handleLogout : handleOpen}
                    sx={{
                        textDecoration: "none",
                        color: "inherit",
                        marginLeft: "auto",
                        cursor: "pointer",
                    }}
                >
                    {isLoggedIn ? "Déconnexion" : "Connexion"}
                </Typography>

                <Dialog open={open} onClose={handleClose}>
                    <DialogContent>
                        <Login setAuth={handleLogin} />
                    </DialogContent>
                </Dialog>
            </Toolbar>
        </AppBar>
    );
}
