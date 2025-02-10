import React, {useState} from "react";
import {deleteRealisateur, updateRealisateur} from "../../api/RealisateurApi";
import CardContent from "@mui/material/CardContent";
import Typography from "@mui/material/Typography";
import IconButton from "@mui/material/IconButton";
import DeleteIcon from "@mui/icons-material/Delete";
import Dialog from "@mui/material/Dialog";
import DialogTitle from "@mui/material/DialogTitle";
import DialogContent from "@mui/material/DialogContent";
import EditIcon from "@mui/icons-material/Edit";
import Card from "@mui/material/Card";
import CreateRealisateurForm from "./CreateRealisateurForm";

export default function RealisateurCard(props) {
    const [open, setOpen] = useState(false);

    const handleClickOnEditButton = () => {
        setOpen(true);
    };

    const handleClose = () => {
        setOpen(false);
    };


    const handleEditSubmit = (updatedRealisateur) => {
        updateRealisateur(props.realisateur.id ,updatedRealisateur, props.token).then(response => {
            console.log('Realisateur edited successfully:', response.data);
            props.setRealisateurs((prevRealisateurs) => prevRealisateurs.map((realisateur) => {
                if (realisateur.id === response.data.id) {
                    return response.data;
                }
                return realisateur;
            }));
            handleClose();
        }).catch(error => {
            console.error('Error editing realisateur:', error);
            alert('An error occurred while editing the realisateur.');
            handleClose(); // Close the dialog even if an error occurred
        });
    };

    const handleClickOnDeleteButton = () => {
        const id = props.realisateur.id;
        deleteRealisateur(id, props.token)
            .then(() => {
                props.setRealisateurs((prevRealisateurs) => prevRealisateurs.filter((realisateur) => realisateur.id !== id));
            })
            .catch((error) => {
                console.error('Erreur lors de la suppression du realisateur:', error);
                alert('Une erreur est survenue lors de la suppression du realisateur.');
            });
    };

    return (
        <Card variant="outlined" sx={{ margin: 2, maxWidth: 300 }}>
            <CardContent>
                <Typography variant="h5" gutterBottom>
                    {props.realisateur.prenom} {props.realisateur.nom}
                </Typography>
                <Typography variant="body1">
                    Né(e) le {props.realisateur.dateNaissance}
                </Typography>
                <Typography variant="body1">
                    {props.realisateur.celebre ? "Célèbre" : "Non célèbre"}
                </Typography>
            </CardContent>

            <IconButton onClick={handleClickOnDeleteButton}>
                <DeleteIcon/>
            </IconButton>
            <IconButton onClick={handleClickOnEditButton}>
                <Dialog onClose={handleClose} open={open}>
                    <DialogTitle>Modifier un réalisateur</DialogTitle>
                    <DialogContent>
                        <CreateRealisateurForm realisateur={props.realisateur} onSubmit={handleEditSubmit}/>
                    </DialogContent>
                </Dialog>
                <EditIcon/>
            </IconButton>
        </Card>
    )
}
