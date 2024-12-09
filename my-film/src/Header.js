import React, {useState} from 'react';
import AppBar from '@mui/material/AppBar';
import Toolbar from '@mui/material/Toolbar';
import Typography from '@mui/material/Typography';
import Box from "@mui/material/Box";
import { Link } from 'react-router-dom';
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import Login from "./components/user/Login";

export default function Header() {
    const [open, setOpen] = useState(false);

    const handleOpen = () => setOpen(true);
    const handleClose = () => setOpen(false);

    return (
        <AppBar position="static">
            <Toolbar>
                <Box sx={{ flexGrow: 1, display: 'flex', gap: 2 }}>
                    <Typography
                        variant="h6"
                        component={Link}
                        to="/"
                        sx={{ textDecoration: 'none', color: 'inherit' }}
                    >
                        Films
                    </Typography>
                    <Typography
                        variant="h6"
                        component={Link}
                        to="/realisateurs"
                        sx={{ textDecoration: 'none', color: 'inherit' }}
                    >
                        Réalisateurs
                    </Typography>
                    <Typography
                        variant="h6"
                        component={Link}
                        to="/users"
                        sx={{ textDecoration: 'none', color: 'inherit' }}
                    >
                        Utilisateurs
                    </Typography>
                </Box>

                <Typography
                    variant="h6"
                    onClick={handleOpen}
                    sx={{
                        textDecoration: "none",
                        color: "inherit",
                        marginLeft: "auto",
                        cursor: "pointer",
                    }}
                >
                    Connexion
                </Typography>

                <Dialog open={open} onClose={handleClose}>
                    <DialogTitle>Connexion</DialogTitle>
                    <DialogContent>
                        <Login />
                    </DialogContent>
                </Dialog>
            </Toolbar>
        </AppBar>
    );
}
